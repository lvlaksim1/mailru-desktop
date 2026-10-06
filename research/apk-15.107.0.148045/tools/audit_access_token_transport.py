from pathlib import Path
import json,re

ROOT=Path("research/apk-15.107.0.148045")
SRC=ROOT/"decompiled"/"packages"
disc=json.loads((ROOT/"FULL_ACCESS_TOKEN_DISCOVERY.json").read_text(encoding="utf-8"))

files=list(SRC.rglob("*.java"))
by_name={}
by_rel={}
for p in files:
    by_name.setdefault(p.stem,[]).append(p)
    by_rel[str(p.relative_to(SRC))]=p

def pick(name):
    xs=by_name.get(name,[])
    if not xs:return None
    return sorted(xs,key=lambda p:(0 if "ru/mail/data/cmd/server" in str(p) else 1,len(str(p))))[0]

def src_text(name):
    p=pick(name)
    return (p.read_text(encoding="utf-8",errors="replace"),p) if p else ("",None)

def base_of(text,name):
    m=re.search(r'class\s+'+re.escape(name)+r'(?:<[^>{}]*>)?\s+extends\s+([A-Za-z0-9_$.]+)',text)
    return m.group(1).split(".")[0] if m else ""

def method_body(text,name):
    m=re.search(r'(?:protected|public|private)\s+(?:final\s+)?(?:void|boolean|[A-Za-z0-9_$.<>?]+)\s+'+re.escape(name)+r'\s*\([^)]*\)\s*\{',text)
    if not m:return None
    start=m.end()
    depth=1;i=start
    while i<len(text) and depth:
        if text[i]=='{':depth+=1
        elif text[i]=='}':depth-=1
        i+=1
    return text[start:i-1] if depth==0 else text[start:start+3000]

def chain(name):
    out=[];seen=set()
    while name and name not in seen:
        seen.add(name);out.append(name)
        t,p=src_text(name)
        if not p:break
        name=base_of(t,name)
    return out

def effective_override(cls,method):
    for name in chain(cls):
        t,p=src_text(name)
        if not p:continue
        b=method_body(t,method)
        if b is not None:
            return name,b
    return None,None

def token_transport(cmd):
    cls=cmd["class"]
    if not cmd.get("access_token_capable"):
        return {"uses_mail_access_token":False,"transport":"not-classified","detail":""}
    owner,url_body=effective_override(cls,"onSetupSessionInUrl")
    set_owner,set_body=effective_override(cls,"setUpSession")
    details=[]
    uses=False
    transport=[]
    if url_body is None:
        uses=True;transport.append("query:access_token");details.append("inherited TornadoSession URL setup")
    else:
        pairs=re.findall(r'appendQueryParameter\("([^"]+)"\s*,\s*peekAuthToken\(\)\)',url_body)
        if pairs:
            uses=True
            for q in pairs:transport.append("query:"+q)
            details.append("custom URL token setup in "+owner)
        elif "super.onSetupSessionInUrl" in url_body:
            uses=True;transport.append("query:access_token")
            details.append("delegates to TornadoSession URL setup in "+owner)
        elif "peekAuthToken()" in url_body:
            uses=True;transport.append("custom-url")
            details.append("custom URL token use in "+owner)
        elif not url_body.strip():
            details.append("URL session setup disabled in "+owner)
        else:
            details.append("custom URL setup without visible token in "+owner)

    if set_body is not None:
        if "peekAuthToken()" in set_body:
            uses=True
            if '"Authorization"' in set_body and '"Bearer "' in set_body:
                transport.append("header:Authorization Bearer")
            else:
                transport.append("custom-header")
            details.append("header/session token use in "+set_owner)
        elif not set_body.strip():
            details.append("network session setup disabled in "+set_owner)
    return {"uses_mail_access_token":uses,"transport":",".join(dict.fromkeys(transport)) or "none","detail":"; ".join(details)}

def host_resource(cls):
    # explicit annotations nearest first
    for name in chain(cls):
        t,p=src_text(name)
        if not p:continue
        m=re.search(r'@HostProviderAnnotation\(([^)]*)\)',t)
        if m:
            a=m.group(1)
            x=re.search(r'defHostStrRes\s*=\s*"string/([^"]+)"',a)
            if x:return x.group(1),"annotation:"+name
            x=re.search(r'defHost\s*=\s*R\.string\.([A-Za-z0-9_]+)',a)
            if x:return x.group(1),"annotation:"+name
    # custom host provider overrides
    for name in chain(cls):
        t,p=src_text(name)
        if not p:continue
        b=method_body(t,"getHostProvider")
        if b:
            candidates=re.findall(r'R\.string\.([A-Za-z0-9_]*host[A-Za-z0-9_]*)',b)
            if candidates:
                # prefer explicit service host over fallback/wrapped resources
                for x in candidates:
                    if x not in ("mail_api_default_host","new_mail_api_default_host"):
                        return x,"getHostProvider:"+name
                return candidates[0],"getHostProvider:"+name
    return "mail_api_default_host","default"

host_values={
 "mail_api_default_host":"alt-aj-https.mail.ru",
 "new_mail_api_default_host":"alt-aj-https.mail.ru",
 "search_new_host":"go.mail.ru",
 "swa_default_host":"alt-auth.mail.ru",
 "cloud_dispatcher_default_host":"dispatcher.cloud.mail.ru",
 "change_avatar_default_host":"alt-aj-https.mail.ru",
 "oauth_default_host":"o2.mail.ru",
 "account_default_host":"account.mail.ru",
 "calls_default_host":"alt-calls.mail.ru",
}

audited=[]
for cmd in disc["access_token_commands"]:
    tr=token_transport(cmd)
    hr,hs=host_resource(cmd["class"])
    audited.append({**cmd,**tr,"actual_host_resource":hr,"actual_host":host_values.get(hr,hr),"host_evidence":hs})

same=[x for x in audited if x["uses_mail_access_token"]]
falsepos=[x for x in audited if not x["uses_mail_access_token"]]
by_transport={}
for x in same:by_transport.setdefault(x["transport"],[]).append(x["class"])
by_host={}
for x in same:by_host.setdefault(x["actual_host"],[]).append(x["class"])

out={"package":disc["package"],"version":disc["version"],"audited":audited,
     "same_mail_access_token":same,"false_positives":falsepos,
     "summary":{"candidates":len(audited),"confirmed_same_token":len(same),"false_positive_or_no_transport":len(falsepos),
                "by_transport":by_transport,"by_host":by_host}}
(ROOT/"ACCESS_TOKEN_TRANSPORT_AUDIT.json").write_text(json.dumps(out,ensure_ascii=False,indent=2)+"\n",encoding="utf-8")

md=["# Проверка фактической передачи почтового access_token","",
    "Проверяется не только тип TORNADO, но и ближайшие переопределения onSetupSessionInUrl/setUpSession.","",
    "Кандидатов: **%d**."%len(audited),
    "Фактически используют тот же почтовый токен по сохранённому коду: **%d**."%len(same),
    "Ложноположительные/без передачи токена: **%d**."%len(falsepos),"",
    "## Способы передачи",""]
for k,v in sorted(by_transport.items()):
    md.append("- "+k+": "+str(len(v))+" команд")
md += ["","## Узлы",""]
for k,v in sorted(by_host.items()):
    md.append("- "+k+": "+str(len(v))+" команд")
md += ["","## Исключённые после проверки",""]
for x in falsepos:
    md.append("- "+x["class"]+" — "+(x["path"] or x["path_raw"])+" — "+x["detail"])
md += ["","## Полная таблица","",
       "| класс | маршрут | узел | передача токена | примечание |","|---|---|---|---|---|"]
for x in audited:
    if x["uses_mail_access_token"]:
        md.append("| "+x["class"]+" | "+(x["path"] or x["path_raw"])+" | "+x["actual_host"]+" | "+x["transport"]+" | "+x["detail"].replace("|","/")+" |")
(ROOT/"ACCESS_TOKEN_TRANSPORT_AUDIT.md").write_text("\n".join(md)+"\n",encoding="utf-8")

# audit run 1
