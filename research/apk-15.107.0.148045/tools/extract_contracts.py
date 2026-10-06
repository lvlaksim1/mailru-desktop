from pathlib import Path
import json
import re

ROOT = Path("research/apk-15.107.0.148045")
SRC = ROOT / "decompiled" / "packages"
ROUTE_MAP = ROOT / "APK_ROUTE_MAP.md"

COMMON = [
    {"name": "access_token", "method": "GET/session", "source": "TornadoSession", "note": "added by session layer"},
    {"name": "lang", "method": "GET", "source": "ServerCommandBaseParams", "note": "common locale parameter"},
]

SEND_CLASSES = {"TornadoSendRequest", "TornadoDraftRequest", "TornadoScheduleRequest"}

def parse_routes():
    result = []
    for line in ROUTE_MAP.read_text(encoding="utf-8").splitlines():
        parts = [x.strip().strip("`") for x in line.split("|")]
        if len(parts) >= 5 and parts[1] in {"GET", "POST", "PUT", "DELETE", "PATCH"} and parts[2].startswith("/"):
            result.append({"method": parts[1], "route": parts[2], "class": parts[3]})
    return result

CLASS_FILES = {}
for p in SRC.rglob("*.java"):
    CLASS_FILES.setdefault(p.stem, []).append(p)

def pick_class_file(name):
    candidates = CLASS_FILES.get(name, [])
    if not candidates:
        return None
    return sorted(candidates, key=lambda p: (
        0 if "ru/mail/data/cmd/server" in str(p) else 1,
        0 if "ru/mail/serverapi" in str(p) else 1,
        len(str(p)),
    ))[0]

def parse_param_annotations(text):
    out = []
    pat = re.compile(
        r'@Param\((?P<args>[^)]*)\)\s*'
        r'(?:(?:@[A-Za-z0-9_$.]+(?:\([^)]*\))?)\s*)*'
        r'(?P<decl>(?:(?:private|protected|public|static|final|transient|volatile)\s+)+[^;\n]+;)',
        re.MULTILINE
    )
    for m in pat.finditer(text):
        args = m.group("args")
        decl = " ".join(m.group("decl").split())
        name_m = re.search(r'name\s*=\s*("[^"]+"|[A-Za-z0-9_$.]+)', args)
        method_m = re.search(r'method\s*=\s*HttpMethod\.([A-Z_]+)', args)
        getter_m = re.search(r'getterName\s*=\s*"([^"]+)"', args)
        type_m = re.search(r'type\s*=\s*Param\.Type\.([A-Z_]+)', args)
        raw_name = name_m.group(1) if name_m else ""
        if raw_name.startswith('"') and raw_name.endswith('"'):
            raw_name = raw_name[1:-1]
        out.append({
            "name": raw_name,
            "method": method_m.group(1) if method_m else "DEFAULT",
            "getter": getter_m.group(1) if getter_m else "",
            "type": type_m.group(1) if type_m else "",
            "field": decl,
        })
    return out

def class_base_names(text, cls):
    names = []
    m = re.search(r'class\s+' + re.escape(cls) + r'(?:<[^>{}]*>)?\s+extends\s+([A-Za-z0-9_$.]+)', text)
    if m:
        names.append(m.group(1).split(".")[0])
    m = re.search(r'class\s+' + re.escape(cls) + r'[^\n{]*extends\s+[A-Za-z0-9_$.]+<([^>]+)>', text)
    if m:
        for token in re.findall(r'\b([A-Z][A-Za-z0-9_]*)\b', m.group(1)):
            names.append(token)
    for m in re.finditer(r'class\s+Params\s+extends\s+([A-Za-z0-9_$.]+)', text):
        names.append(m.group(1).split(".")[0])
    return list(dict.fromkeys(x for x in names if x not in {"Object", "ServerCommandBaseParams", "ServerCommandEmailParams"}))

def response_keys(text):
    keys = set()
    for m in re.finditer(
        r'\.(?:getJSONObject|getJSONArray|getString|getInt|getLong|getBoolean|'
        r'optString|optJSONArray|optJSONObject|optInt|optLong|optBoolean|has)\("([^"]+)"\)',
        text
    ):
        keys.add(m.group(1))
    return sorted(keys)

def collect(cls, seen=None):
    seen = set() if seen is None else seen
    if not cls or cls in seen:
        return [], [], []
    seen.add(cls)
    p = pick_class_file(cls)
    if not p:
        return [], [], []
    text = p.read_text(encoding="utf-8", errors="replace")
    rel = str(p.relative_to(ROOT))
    params = []
    for x in parse_param_annotations(text):
        x = dict(x)
        x["source"] = rel
        params.append(x)
    keys = response_keys(text)
    sources = [rel]
    bases = class_base_names(text, cls)
    if cls in SEND_CLASSES:
        bases.append("TornadoSendParamsImpl")
    for base in list(dict.fromkeys(bases)):
        pp, kk, ss = collect(base, seen)
        params.extend(pp)
        keys.extend(kk)
        sources.extend(ss)
    unique = []
    used = set()
    for pinfo in params:
        k = (pinfo["name"], pinfo["method"], pinfo["getter"], pinfo["type"], pinfo["field"], pinfo["source"])
        if k not in used:
            used.add(k)
            unique.append(pinfo)
    return unique, sorted(set(keys)), list(dict.fromkeys(sources))

routes = parse_routes()
result = {
    "package": "ru.mail.mailapp",
    "version": "15.107.0.148045",
    "common_parameters": COMMON,
    "routes": [],
}

for item in routes:
    params, keys, sources = collect(item["class"])
    email = any("ServerCommandEmailParams" in (pick_class_file(s).read_text(errors="replace") if pick_class_file(s) else "") for s in [])
    result["routes"].append({
        **item,
        "parameters": params,
        "response_keys": keys,
        "sources": sources,
    })

(ROOT / "APK_REQUEST_CONTRACTS.json").write_text(
    json.dumps(result, ensure_ascii=False, indent=2) + "\n",
    encoding="utf-8",
)

md = [
    "# Договоры запросов AJ, автоматически извлечённые из APK",
    "",
    "Источник: ru.mail.mailapp 15.107.0.148045.",
    "",
    "Общее правило: access_token добавляется TornadoSession; lang добавляется ServerCommandBaseParams; email добавляется командами на ServerCommandEmailParams.",
    "",
]

for item in result["routes"]:
    md += [f'## {item["method"]} {item["route"]}', "", f'Класс: {item["class"]}', ""]
    if item["parameters"]:
        md += [
            "| имя/выражение | размещение | тип | получатель | поле | источник |",
            "|---|---|---|---|---|---|",
        ]
        for p in item["parameters"]:
            vals = [
                p["name"] or "(не задано)",
                p["method"],
                p["type"],
                p["getter"],
                p["field"],
                p["source"],
            ]
            vals = [str(x).replace("|", "/") for x in vals]
            md.append("| " + " | ".join(vals) + " |")
        md.append("")
    else:
        md += ["Явных @Param в доступной цепочке классов не найдено.", ""]
    if item["response_keys"]:
        md += ["Ключи JSON, читаемые непосредственно этой цепочкой: " + ", ".join(item["response_keys"]), ""]
    md += ["Исходники: " + ", ".join(item["sources"]), ""]

(ROOT / "APK_REQUEST_CONTRACTS.md").write_text("\n".join(md) + "\n", encoding="utf-8")

# extractor revision 2
