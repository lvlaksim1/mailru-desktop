from pathlib import Path
import json,re

ROOT=Path("research/apk-15.107.0.148045")
SRC=ROOT/"decompiled"/"packages"
disc=json.loads((ROOT/"FULL_ACCESS_TOKEN_DISCOVERY.json").read_text(encoding="utf-8"))

index={}
for p in SRC.rglob("*.java"):
    index[str(p.relative_to(SRC))]=p
    index.setdefault(p.stem,p)

def by_source(rel):
    p=SRC/rel
    return p if p.exists() else None

def parse_params(text):
    out=[]
    lines=text.splitlines()
    for i,line in enumerate(lines):
        if "@Param(" not in line: continue
        anns=[line.strip()]
        j=i+1
        while j<len(lines) and (not lines[j].strip() or lines[j].lstrip().startswith("@")):
            if lines[j].strip().startswith("@Param("):
                anns.append(lines[j].strip())
            j+=1
        field=""
        for k in range(i+1,min(i+10,len(lines))):
            s=lines[k].strip()
            if not s or s.startswith("@"): continue
            if ";" in s and re.search(r"\b(private|protected|public|static|final)\b",s):
                field=s; break
        for ann in anns:
            m=re.search(r'name\s*=\s*("[^"]+"|[A-Za-z0-9_$.]+)',ann)
            name=m.group(1) if m else ""
            if name.startswith('"'): name=name[1:-1]
            mm=re.search(r'method\s*=\s*HttpMethod\.([A-Z_]+)',ann)
            gm=re.search(r'getterName\s*=\s*"([^"]+)"',ann)
            tm=re.search(r'type\s*=\s*Param\.Type\.([A-Z_]+)',ann)
            out.append({"name":name,"method":mm.group(1) if mm else "DEFAULT","getter":gm.group(1) if gm else "","type":tm.group(1) if tm else "","field":field})
    return out

def response_keys(text):
    pats=[
      r'\.(?:getJSONObject|getJSONArray|getString|getInt|getLong|getBoolean|optString|optJSONArray|optJSONObject|optInt|optLong|optBoolean|has)\("([^"]+)"\)',
      r'\.put\("([^"]+)"'
    ]
    keys=set()
    for pat in pats:
        keys.update(re.findall(pat,text))
    return sorted(keys)

contracts=[]
missing=[]
for cmd in disc["access_token_commands"]:
    p=by_source(cmd["source"])
    if not p:
        # common retained path for ru/mail/data/cmd/server is exact under packages
        p=index.get(Path(cmd["source"]).stem)
        if not isinstance(p,Path): p=None
    if not p:
        missing.append(cmd)
        contracts.append({**cmd,"source_available":False,"params":[],"json_keys":[]})
        continue
    text=p.read_text(encoding="utf-8",errors="replace")
    contracts.append({**cmd,"source_available":True,"saved_source":str(p.relative_to(ROOT)),"params":parse_params(text),"json_keys":response_keys(text)})

out={"package":disc["package"],"version":disc["version"],"commands":contracts,"missing_sources":missing}
(ROOT/"ACCESS_TOKEN_CONTRACTS.json").write_text(json.dumps(out,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")

md=["# Договоры API, работающего через access_token","",
    "Автоматическая инвентаризация 101 команды из полного повторного разбора APK.","",
    "Исходники доступны для: **%d**."%sum(1 for x in contracts if x["source_available"]),
    "Требуют точечной декомпиляции: **%d**."%len(missing),""]
for x in contracts:
    md += ["## "+(x["path"] or x["path_raw"]),"",
           "Класс: "+x["class"]+"; исходный тип: "+x["auth_type"]+"; ресурс узла: "+x["host_resource"]+"."]
    if not x["source_available"]:
        md += ["Исходник не сохранён в текущей выборке.",""]
        continue
    if x["params"]:
        md += ["","| параметр | метод | тип | получатель | поле |","|---|---|---|---|---|"]
        for p in x["params"]:
            vals=[p["name"] or "(выражение/не задано)",p["method"],p["type"],p["getter"],p["field"]]
            md.append("| "+" | ".join(str(v).replace("|","/") for v in vals)+" |")
    if x["json_keys"]:
        md += ["","Ключи JSON в коде: "+", ".join(x["json_keys"])]
    md.append("")
if missing:
    md += ["## Недостающие исходники",""]
    for x in missing: md.append("- "+x["source"]+" — "+x["class"]+" — "+(x["path"] or x["path_raw"]))
(ROOT/"ACCESS_TOKEN_CONTRACTS.md").write_text("\n".join(md)+"\n",encoding="utf-8")

# run 1
