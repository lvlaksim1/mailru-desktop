from pathlib import Path
import json, re

ROOT = Path("research/apk-15.107.0.148045")
SRC = ROOT / "decompiled" / "packages"

classes = {}
for p in SRC.rglob("*.java"):
    classes.setdefault(p.stem, []).append(p)

def pick(name):
    xs = classes.get(name, [])
    if not xs:
        return None
    return sorted(xs, key=lambda p: (0 if "ru/mail/data/cmd/server" in str(p) else 1, len(str(p))))[0]

def read(name):
    p = pick(name)
    return (p.read_text(encoding="utf-8", errors="replace"), p) if p else ("", None)

def base_of(src, name):
    m = re.search(r"class\s+" + re.escape(name) + r"(?:<[^>{}]*>)?\s+extends\s+([A-Za-z0-9_$.]+)", src)
    return m.group(1).split(".")[0] if m else ""

def own_auth(src):
    for m in re.finditer(r"getDefaultApiType\s*\([^)]*\)\s*\{([\s\S]{0,800}?)\n\s*\}", src):
        x = re.search(r"return\s+MailAuthorizationApiType\.([A-Z_]+)", m.group(1))
        if x:
            return x.group(1)
    return ""

def own_oauth(src):
    for m in re.finditer(r"isSupportOAuthAuthorization\s*\([^)]*\)\s*\{([\s\S]{0,500}?)\n\s*\}", src):
        body = m.group(1)
        if re.search(r"return\s+true\s*;", body):
            return True
        if re.search(r"return\s+false\s*;", body):
            return False
    return None

def auth_info(name, seen=None):
    seen = set() if seen is None else seen
    if not name or name in seen:
        return {"effective": "", "oauth": False, "chain": []}
    seen.add(name)
    src, p = read(name)
    if not p:
        return {"effective": "", "oauth": False, "chain": [name]}
    declared = own_auth(src)
    parent = auth_info(base_of(src, name), seen) if base_of(src, name) else {"effective": "", "oauth": False, "chain": []}
    effective = declared or parent["effective"] or ("LEGACY" if name == "ServerCommandBase" else "")
    override = own_oauth(src)
    if override is not None:
        oauth = override
    elif declared:
        oauth = declared in ("TORNADO", "TORNADO_MPOP")
    else:
        oauth = parent["oauth"] or effective in ("TORNADO", "TORNADO_MPOP")
    return {"effective": effective, "oauth": oauth, "chain": [name] + parent["chain"]}

def host_info(name, seen=None):
    seen = set() if seen is None else seen
    if not name or name in seen:
        return {"resource": "", "scheme_resource": "", "source": ""}
    seen.add(name)
    src, p = read(name)
    if not p:
        return {"resource": "", "scheme_resource": "", "source": ""}
    m = re.search(r"@HostProviderAnnotation\(([^)]*)\)", src)
    if m:
        args = m.group(1)
        hr = re.search(r'defHostStrRes\s*=\s*"([^"]+)"', args)
        sr = re.search(r'defSchemeStrRes\s*=\s*"([^"]+)"', args)
        return {"resource": hr.group(1).replace("string/","") if hr else "", "scheme_resource": sr.group(1).replace("string/","") if sr else "", "source": str(p.relative_to(ROOT))}
    return host_info(base_of(src, name), seen)

route_map = (ROOT / "APK_ROUTE_MAP.md").read_text(encoding="utf-8")
routes = []
for line in route_map.splitlines():
    parts = [x.strip().replace(chr(96), "") for x in line.split("|")]
    if len(parts) >= 5 and parts[1] in {"GET","POST","PUT","DELETE","PATCH"} and parts[2].startswith("/"):
        method, route, cls = parts[1], parts[2], parts[3]
        a = auth_info(cls)
        h = host_info(cls)
        capable = a["effective"] == "TORNADO" or (a["effective"] == "TORNADO_MPOP" and a["oauth"])
        routes.append({
            "method": method,
            "route": route,
            "class": cls,
            "default_auth": a["effective"],
            "oauth_supported": a["oauth"],
            "access_token_capable": capable,
            "host_resource": h["resource"] or "mail_api_default_host",
            "scheme_resource": h["scheme_resource"] or "mail_api_default_scheme",
            "auth_chain": a["chain"],
            "host_source": h["source"]
        })

known_hosts = {
    "mail_api_default_host": "aj-https.mail.ru / alt-aj-https.mail.ru family",
    "new_mail_api_default_host": "aj-https.mail.ru / alt-aj-https.mail.ru family",
    "search_new_host": "go.mail.ru",
    "attach_preview_default_host": "alt-apf.mail.ru",
    "avatar_default_host": "alt-mpandroid-filin.mail.ru",
    "push_default_host": "alt-push-me.mail.ru",
    "doreg_default_host": "alt-android-mobile-api.e.mail.ru"
}
for x in routes:
    x["host"] = known_hosts.get(x["host_resource"], x["host_resource"])

data = {
    "package": "ru.mail.mailapp",
    "version": "15.107.0.148045",
    "token_type": "ru.mail.oauth2.access",
    "token_parameter": "access_token",
    "routes": routes
}
(ROOT / "ACCESS_TOKEN_API_MAP.json").write_text(json.dumps(data, ensure_ascii=False, indent=2) + "\n", encoding="utf-8")

yes = [x for x in routes if x["access_token_capable"]]
no = [x for x in routes if not x["access_token_capable"]]

md = []
md.append("# Карта API по критерию access_token")
md.append("")
md.append("Критерий: любой узел допустим, если официальный клиент может выполнить операцию с токеном ru.mail.oauth2.access.")
md.append("")
md.append("Механизм: OAuthSession получает ru.mail.oauth2.access; TornadoSession передает его как access_token; ServerCommandBase переключает OAuth-совместимые команды на TORNADO.")
md.append("")
md.append("Всего маршрутов: " + str(len(routes)))
md.append("Совместимы с access_token: " + str(len(yes)))
md.append("Не подтверждены как access_token: " + str(len(no)))
md.append("")
md.append("## Совместимы с access_token")
md.append("")
md.append("| метод | маршрут | класс | тип | узел |")
md.append("|---|---|---|---|---|")
for x in yes:
    md.append("| " + x["method"] + " | " + x["route"] + " | " + x["class"] + " | " + x["default_auth"] + " | " + x["host"] + " |")
if no:
    md.append("")
    md.append("## Не подтверждены как access_token")
    md.append("")
    md.append("| метод | маршрут | класс | тип | узел |")
    md.append("|---|---|---|---|---|")
    for x in no:
        md.append("| " + x["method"] + " | " + x["route"] + " | " + x["class"] + " | " + (x["default_auth"] or "unknown") + " | " + x["host"] + " |")
(ROOT / "ACCESS_TOKEN_API_MAP.md").write_text("\n".join(md) + "\n", encoding="utf-8")
