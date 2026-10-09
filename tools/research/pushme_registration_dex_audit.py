#!/usr/bin/env python3
"""Offline recovery of Mail.ru APK PushMe registration serialization/capabilities.

No request to Mail.ru, Google or PushMe is made. The only inputs are the
archived original APK parts checked into the research branch.
"""
from pathlib import Path
from androguard.core.dex import DEX
import hashlib, io, os, sys, zipfile

parts = Path(sys.argv[1])
dest = Path(sys.argv[2])
files = sorted(parts.glob("Mail_ru_15.107.0.148045_APKs.zip.part-*"))
assert len(files) > 1, "Original APK archive is missing"
blob = b"".join(p.read_bytes() for p in files)
sha = hashlib.sha256(blob).hexdigest()
assert sha in (parts.parent / "SHA256SUMS.txt").read_text("utf-8"), "APK SHA256 mismatch"
wanted = {
    "Lcom/vk/pushme/network/PushMeApiImpl;": {"setSettingsInternal","mapSubscriptions"},
    "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;": {"invoke","mapToNetworkModels"},
    "Lru/mail/util/push/provider/factory/CapabilitiesProviderFactory;": None,
    "Lru/mail/util/analytics/Distributors;": None,
}
keys = ("Capabilities","capabilities","FilterAccessor","MailFilter")
report = [
    "# Регистрация PushMe: оригинальный APK / статический разбор DEX",
    "",
    "APK SHA-256: `" + sha + "`",
    "Данный отчёт полностью построен из локального DEX без сетевых вызовов.",
    "",
]
classes_seen = set()
with zipfile.ZipFile(io.BytesIO(blob)) as outer:
    names = sorted((n for n in outer.namelist() if n.endswith(".apk")),
                   key=lambda v:(not ("base" in v.lower() or "master" in v.lower()), len(v)))
    for apkname in names[:3]:
        with zipfile.ZipFile(io.BytesIO(outer.read(apkname))) as apk:
            for dexname in sorted(n for n in apk.namelist() if n.startswith("classes") and n.endswith(".dex")):
                dex=DEX(apk.read(dexname))
                for cls in dex.get_classes():
                    name = cls.get_name()
                    relevant = name in wanted or (
                        name.startswith("Lru/mail/util/push/provider/") and
                        any(k in name for k in keys)
                    )
                    if not relevant: continue
                    classes_seen.add(name)
                    report.append("## Class " + name + " @ " + apkname + "/" + dexname)
                    report.append("")
                    for method in cls.get_methods():
                        if name in wanted and wanted[name] is not None and method.get_name() not in wanted[name]:
                            continue
                        code=method.get_code()
                        if code is None: continue
                        report.append("### " + method.get_name() + " " + method.get_descriptor())
                        report.append("~~~")
                        index=0
                        for instr in code.get_bc().get_instructions():
                            report.append(f"{index:05x} {instr.get_name()} {instr.get_output(index)}")
                            index += instr.get_length()
                        report.extend(["~~~",""])
                del dex
        if len(classes_seen)>4: break
assert "Lcom/vk/pushme/network/PushMeApiImpl;" in classes_seen
report.insert(5, "Classes: " + str(len(classes_seen)))
dest.parent.mkdir(parents=True,exist_ok=True)
dest.write_text("\n".join(report),"utf-8")
print("CLASS_COUNT",len(classes_seen),"BYTES",dest.stat().st_size, "SHA",sha)
print("\n".join(sorted(classes_seen)))
