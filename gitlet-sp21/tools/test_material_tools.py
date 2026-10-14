"""Regressions for material-audit failures and documented tester behavior.

Fixtures live in the OS temporary directory; official starter files stay intact.
"""
from contextlib import redirect_stdout
from hashlib import sha1, sha256
from importlib.util import module_from_spec, spec_from_file_location
from io import StringIO
from pathlib import Path
from tempfile import TemporaryDirectory
from unittest.mock import patch
import json
import os
import shutil
import subprocess
import unittest

import audit_materials as audit


class MaterialAuditTests(unittest.TestCase):
    def setUp(self):
        self.temp = TemporaryDirectory(prefix="gitlet-material-audit-")
        self.addCleanup(self.temp.cleanup)
        self.root = Path(self.temp.name)
        for folder in ("docs", "sources", "verification", "proj2/gitlet"):
            (self.root / folder).mkdir(parents=True)
        self.original = b"package gitlet;\n"
        self.starter = self.root / "proj2/gitlet/Main.java"
        self.starter.write_bytes(self.original)
        item = {"path": "proj2/gitlet/Main.java",
                "git_blob_sha1": sha1(b"blob " + str(len(self.original)).encode() +
                                      b"\0" + self.original).hexdigest(),
                "sha256": sha256(self.original).hexdigest()}
        (self.root / "sources/starter-manifest.json").write_text(
            json.dumps({"files": [item]}), encoding="utf-8")
        (self.root / "README.md").write_text(
            f"# Fixture\n\n[merge]({audit.SPEC}#merge)\n\n"
            "[report](verification/materials-audit.json)\n", encoding="utf-8")
        for name in ("sources/README.md", "verification/README.md"):
            (self.root / name).write_text("# Fixture\n", encoding="utf-8")
        self.records = [{"url": url, "status": 200} for url in audit.CORE]
        self.records[0]["anchor_ids"] = ["merge", "init"]

    def run_audit(self, expected_code):
        records = {record["url"]: record for record in self.records}
        code = 0
        with patch.object(audit, "ROOT", self.root), \
                patch.object(audit, "check_source", side_effect=lambda url: records[url]), \
                redirect_stdout(StringIO()):
            try:
                audit.main()
            except SystemExit as exc:
                code = exc.code
        self.assertEqual(code, expected_code)
        return json.loads((self.root / "verification/materials-audit.json").read_text(encoding="utf-8"))

    def test_valid_sources_and_first_report(self):
        report = self.run_audit(0)
        self.assertEqual(report["failures"], [])
        self.assertEqual(report["starter_files_verified"], 1)
        self.assertEqual(report["unique_spec_anchors_checked"], 1)

    def test_unavailable_spec_is_failure_and_not_checked(self):
        self.records[0] = {"url": audit.SPEC, "error": "simulated network failure"}
        report = self.run_audit(1)
        self.assertFalse(report["spec_anchor_check_available"])
        self.assertEqual(report["unique_spec_anchors_checked"], 0)
        self.assertTrue(any("Source check failed" in failure for failure in report["failures"]))

    def test_unavailable_secondary_source_is_failure(self):
        self.records[1] = {"url": audit.CORE[1], "error": "simulated network failure"}
        report = self.run_audit(1)
        self.assertEqual(report["core_urls_validated"], len(audit.CORE) - 1)

    def test_changed_starter_is_reported_without_overwriting(self):
        modified = b"student changes\n"
        self.starter.write_bytes(modified)
        report = self.run_audit(1)
        self.assertEqual(report["starter_files_verified"], 0)
        self.assertEqual(self.starter.read_bytes(), modified)

    def test_missing_starter_is_reported(self):
        self.starter.unlink()
        report = self.run_audit(1)
        self.assertTrue(any("Cannot read starter file" in item for item in report["failures"]))

    def test_unknown_spec_anchor_is_failure(self):
        (self.root / "docs/bad.md").write_text(
            f"[bad]({audit.SPEC}#does-not-exist)\n", encoding="utf-8")
        report = self.run_audit(1)
        self.assertTrue(any("Unknown spec anchor" in item for item in report["failures"]))

    def test_unclosed_code_block_is_failure(self):
        (self.root / "docs/bad.md").write_text("```python\nprint(1)\n", encoding="utf-8")
        report = self.run_audit(1)
        self.assertTrue(any("unclosed fenced" in item for item in report["failures"]))

    def test_html_response_at_pdf_url_is_rejected(self):
        class Response:
            url = audit.CORE[-1]
            status = 200
            headers = {"Content-Type": "text/html"}

            def __enter__(self):
                return self

            def __exit__(self, *_):
                return False

            def read(self):
                return b"<html>Access page</html>"

        with patch.object(audit, "urlopen", return_value=Response()):
            record = audit.check_source(audit.CORE[-1])
        self.assertIn("validation_error", record)


class OfficialTesterBehaviorTests(unittest.TestCase):
    @classmethod
    def setUpClass(cls):
        path = Path(__file__).resolve().parents[1] / "proj2/testing/tester.py"
        spec = spec_from_file_location("official_gitlet_tester", path)
        cls.tester = module_from_spec(spec)
        spec.loader.exec_module(cls.tester)

    def test_file_comparison_normalizes_line_endings(self):
        with TemporaryDirectory(prefix="gitlet-tester-behavior-") as directory:
            folder = Path(directory)
            (folder / "actual.txt").write_bytes(b"one\r\ntwo\r\n")
            (folder / "expected.txt").write_bytes(b"one\ntwo\n")
            self.tester.src_dir = directory
            self.assertNotEqual((folder / "actual.txt").read_bytes(),
                                (folder / "expected.txt").read_bytes())
            self.assertTrue(self.tester.correctFileOutput("actual.txt", "expected.txt", directory))

    def test_zero_tolerance_still_ignores_trailing_blank_lines(self):
        self.tester.output_tolerance = 0
        self.assertTrue(self.tester.correctProgramOutput(["result"], "result\n\n", [], False))


class LauncherFreshCompileTests(unittest.TestCase):
    @unittest.skipUnless(os.name == "nt", "Windows launcher")
    def test_removed_source_cannot_be_satisfied_by_stale_class(self):
        jdk = Path.home() / ".jdks/ms-17.0.18"
        if not (jdk / "bin/javac.exe").exists():
            self.skipTest("Local JDK 17 is unavailable")
        with TemporaryDirectory(prefix="gitlet-fresh-compile-") as directory:
            root = Path(directory)
            (root / "tools").mkdir()
            sources = root / "proj2/gitlet"
            sources.mkdir(parents=True)
            launcher = root / "tools/check.ps1"
            shutil.copyfile(Path(__file__).with_name("check.ps1"), launcher)
            (sources / "Main.java").write_text(
                "package gitlet; public class Main { Helper helper; }\n", encoding="utf-8")
            (sources / "Helper.java").write_text(
                "package gitlet; class Helper {}\n", encoding="utf-8")
            ps = Path(os.environ["SystemRoot"]) / "System32/WindowsPowerShell/v1.0/powershell.exe"
            command = [str(ps), "-NoProfile", "-ExecutionPolicy", "Bypass", "-File", str(launcher),
                       "-Mode", "Compile", "-Jdk", str(jdk)]
            first = subprocess.run(command, capture_output=True, timeout=45)
            self.assertEqual(first.returncode, 0, first.stderr.decode(errors="replace"))
            helper_class = root / "proj2/out/classes/gitlet/Helper.class"
            self.assertTrue(helper_class.exists())
            (sources / "Helper.java").unlink()
            second = subprocess.run(command, capture_output=True, timeout=45)
            self.assertNotEqual(second.returncode, 0)
            self.assertFalse(helper_class.exists())


if __name__ == "__main__":
    unittest.main(verbosity=2)
