"""Verify local links and upstream provenance; store metadata, not page copies."""
from concurrent.futures import ThreadPoolExecutor
from datetime import datetime, timezone, timedelta
from hashlib import sha1, sha256
from html.parser import HTMLParser
from pathlib import Path
from urllib.parse import urldefrag, urljoin
from urllib.request import urlopen
import json
import re

ROOT = Path(__file__).resolve().parents[1]
SPEC = "https://sp21.datastructur.es/materials/proj/proj2/proj2"
CORE = [SPEC,
        "https://sp21.datastructur.es/materials/lab/lab6/lab6",
        "https://sp21.datastructur.es/materials/proj/proj2/design.html",
        "https://sp21.datastructur.es/materials/proj/proj2/capers-example",
        "https://sp21.datastructur.es/materials/guides/staff-gitlet",
        "https://sp21.datastructur.es/materials/proj/proj2/gitlet-design-notes.pdf",
        "https://cdn-uploads.piazza.com/attach/k5eevxebzpj25b/jqr7jm9igtc7l5/k97ipfmgmb3n/Gitlet_Slides.pdf"]


class PageIndex(HTMLParser):
    def __init__(self):
        super().__init__()
        self.ids = set()
        self.links = []
        self.current = None

    def handle_starttag(self, tag, attrs):
        attrs = dict(attrs)
        if "id" in attrs:
            self.ids.add(attrs["id"])
        if tag == "a":
            self.current = {"label": "", "url": attrs.get("href", "")}

    def handle_data(self, value):
        if self.current is not None:
            self.current["label"] += value

    def handle_endtag(self, tag):
        if tag == "a" and self.current is not None:
            self.links.append(self.current)
            self.current = None


def check_source(url):
    try:
        with urlopen(url, timeout=25) as response:
            data = response.read()
            record = {"url": url, "final_url": response.url,
                      "status": response.status,
                      "content_type": response.headers.get("Content-Type"),
                      "bytes": len(data), "sha256": sha256(data).hexdigest()}
        if url == SPEC:
            parser = PageIndex()
            parser.feed(data.decode("utf-8"))
            record["anchor_ids"] = sorted(parser.ids)
            record["links"] = [{"label": item["label"].strip(),
                                "url": urljoin(url, item["url"])}
                               for item in parser.links]
        if url.endswith(".pdf") and not data.startswith(b"%PDF-"):
            record["validation_error"] = "Response is not a PDF file"
        elif not url.endswith(".pdf") and "text/html" not in (record["content_type"] or ""):
            record["validation_error"] = "Expected an HTML page"
        return record
    except Exception as exc:
        return {"url": url, "error": str(exc)}


def main():
    manifest = json.loads((ROOT / "sources/starter-manifest.json").read_text(encoding="utf-8"))
    failures = []
    starter_files_verified = 0
    for item in manifest["files"]:
        try:
            data = (ROOT / item["path"]).read_bytes()
        except OSError as exc:
            failures.append(f"Cannot read starter file {item['path']}: {exc}")
            continue
        blob = sha1(b"blob " + str(len(data)).encode() + b"\0" + data).hexdigest()
        if blob != item["git_blob_sha1"] or sha256(data).hexdigest() != item["sha256"]:
            failures.append("Starter hash mismatch: " + item["path"])
        else:
            starter_files_verified += 1

    with ThreadPoolExecutor(max_workers=4) as pool:
        sources = list(pool.map(check_source, CORE))
    timestamp = datetime.now(timezone(timedelta(hours=8))).isoformat(timespec="seconds")
    for source in sources:
        if source.get("status") != 200 or source.get("validation_error"):
            detail = source.get("error") or source.get("validation_error") or str(source.get("status"))
            failures.append(f"Source check failed: {source['url']}: {detail}")
    (ROOT / "sources/source-index.json").write_text(
        json.dumps({"checked_at": timestamp, "pages": sources,
                    "video_playback_checked": False,
                    "forms_checked": False}, ensure_ascii=False, indent=2) + "\n",
        encoding="utf-8")

    spec_ids = set(sources[0].get("anchor_ids", []))
    anchors_available = bool(spec_ids) and sources[0].get("status") == 200
    if not anchors_available:
        failures.append("Official spec anchors could not be checked")
    generated_report = (ROOT / "verification/materials-audit.json").resolve()
    local_links = 0
    checked_anchors = set()
    markdown_files = [ROOT / "README.md", *sorted((ROOT / "docs").glob("*.md")),
                      *sorted((ROOT / "sources").glob("*.md")),
                      *sorted((ROOT / "verification").glob("*.md"))]
    for path in markdown_files:
        try:
            value = path.read_text(encoding="utf-8")
        except (OSError, UnicodeError) as exc:
            failures.append(f"Cannot read Markdown {path.name}: {exc}")
            continue
        if value.count("```") % 2:
            failures.append(f"{path.name}: unclosed fenced code block")
        if "\ufffd" in value:
            failures.append(f"{path.name}: Unicode replacement character")
        for line_no, line in enumerate(value.splitlines(), 1):
            if line.rstrip() != line:
                failures.append(f"{path.name}:{line_no}: trailing whitespace")
        for target in re.findall(r"\[[^\]]*\]\(([^)]+)\)", value):
            if target.startswith(("https://", "http://")):
                base, anchor = urldefrag(target)
                if base == SPEC and anchor:
                    checked_anchors.add(anchor)
                    if anchors_available and anchor not in spec_ids:
                        failures.append("Unknown spec anchor: " + anchor)
            elif not target.startswith("#"):
                local_links += 1
                resolved = (path.parent / urldefrag(target)[0]).resolve()
                if resolved != generated_report and not resolved.exists():
                    failures.append(f"{path.name}: missing {target}")
    report = {"checked_at": timestamp, "starter_files_verified": starter_files_verified,
              "starter_files_expected": len(manifest["files"]),
              "markdown_files_checked": len(markdown_files),
              "local_links_checked": local_links,
              "unique_spec_anchors_referenced": len(checked_anchors),
              "unique_spec_anchors_checked": len(checked_anchors) if anchors_available else 0,
              "spec_anchor_check_available": anchors_available,
              "core_urls_accessible": sum(s.get("status") == 200 for s in sources),
              "core_urls_validated": sum(s.get("status") == 200 and not s.get("validation_error")
                                         for s in sources),
              "core_urls_checked": len(CORE), "failures": failures}
    (ROOT / "verification/materials-audit.json").write_text(
        json.dumps(report, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(json.dumps(report, ensure_ascii=False))
    if failures:
        raise SystemExit(1)


if __name__ == "__main__":
    main()
