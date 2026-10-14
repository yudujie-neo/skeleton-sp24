"""Collect official Spring 2021 starter files without changing existing files."""
from concurrent.futures import ThreadPoolExecutor
from hashlib import sha1, sha256
from pathlib import Path
from urllib.request import urlopen
from datetime import datetime, timezone, timedelta
import json

ROOT = Path(__file__).resolve().parents[1]
REPO = "https://api.github.com/repos/Berkeley-CS61B/skeleton-sp21"
REVISION = "160747451c147c59d8e3cbf70a7afee2b73bebdb"


def fetch(url):
    with urlopen(url, timeout=30) as response:
        return response.read()


def main():
    tree = json.loads(fetch(REPO + f"/git/trees/{REVISION}?recursive=1"))
    if tree.get("truncated"):
        raise RuntimeError("Incomplete upstream tree")
    revision = tree["sha"]
    entries = [entry for entry in tree["tree"]
               if entry["type"] == "blob"
               and entry["path"].startswith(("proj2/", "lab6/"))]

    def download(entry):
        url = ("https://raw.githubusercontent.com/Berkeley-CS61B/"
               f"skeleton-sp21/{revision}/{entry['path']}")
        data = fetch(url)
        git_hash = sha1(b"blob " + str(len(data)).encode() + b"\0" + data).hexdigest()
        if git_hash != entry["sha"]:
            raise RuntimeError("Git blob verification failed: " + entry["path"])
        target = ROOT / entry["path"]
        if target.exists() and target.read_bytes() != data:
            raise RuntimeError("Refusing to overwrite edited file: " + str(target))
        target.parent.mkdir(parents=True, exist_ok=True)
        if not target.exists():
            target.write_bytes(data)
        return {"path": entry["path"], "url": url, "bytes": len(data),
                "git_blob_sha1": git_hash, "sha256": sha256(data).hexdigest()}

    with ThreadPoolExecutor(max_workers=6) as pool:
        files = list(pool.map(download, entries))
    collected_date = datetime.now(timezone(timedelta(hours=8))).date().isoformat()
    manifest = {"collected_date": collected_date, "repository": REPO,
                "revision": revision, "files": files}
    (ROOT / "sources").mkdir(exist_ok=True)
    (ROOT / "sources" / "starter-manifest.json").write_text(
        json.dumps(manifest, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")
    print(f"Verified {len(files)} official files at {revision}")


if __name__ == "__main__":
    main()
