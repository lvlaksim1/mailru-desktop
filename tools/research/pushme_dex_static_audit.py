#!/usr/bin/env python3
"""Offline-only static DEX audit of ORIGINAL Mail.ru APK; no HTTP to Mail.ru/Google.

Read the multipart archived APK from the research branch and dump the exact
Dalvik instructions for PushMe unsubscribe and batching paths. This is a
bytecode trace, not an assumed protocol implementation.
"""
from __future__ import annotations

import hashlib
import io
import os
from pathlib import Path
import sys
import zipfile
from androguard.core.dex import DEX

root = Path(sys.argv[1])
output = Path(sys.argv[2])
parts = sorted(root.glob("Mail_ru_15.107.0.148045_APKs.zip.part-*"))
if len(parts) < 2:
    raise SystemExit("Original APK parts missing")
archive = Path(os.environ.get("RUNNER_TEMP", "/tmp")) / "mailru-original-apks-static.zip"
with archive.open("wb") as dest:
    for part in parts:
        with part.open("rb") as src:
            while chunk := src.read(2 * 1024 * 1024):
                dest.write(chunk)
digest = hashlib.sha256(archive.read_bytes()).hexdigest()
checks = (root.parent / "SHA256SUMS.txt").read_text(encoding="utf-8")
if digest not in checks:
    raise SystemExit("Original APK archive SHA-256 mismatch")

wants = {
    "Lcom/vk/pushme/network/PushMeApiImpl;": {
        "unsubscribeByDeviceId", "unsubscribeByToken", "setSettingsInternal"},
    "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;": {"invoke"},
    "Lcom/vk/pushme/logic/request/UnsubscribeRequest;": {"execute", "enqueue"},
    "Lcom/vk/pushme/logic/SubscriptionBatcher;": {"batch"},
    "Lcom/vk/pushme/logic/usecase/SubscriptionUseCase;": {"invoke"},
    "Lru/mail/util/push/pusher/PushMeSDKPusherTransport;": {"unsubscribeAppByDeviceId"},
    "Lcom/vk/commonid/CommonIdProvider;": {"getCommonIdGenerated", "getCommonId", "generate", "create"},
    "Lcom/vk/commonid/CommonIdProvider$Companion;": {"getCommonIdGenerated", "getCommonIdGenerated$default", "getCommonId", "generate", "create"},
    "Lcom/vk/commonid/CommonIdPrefs;": {"getCommonId", "saveCommonId", "get", "set", "write", "read"},
    "Lcom/vk/commonid/a;": {"call"},
    "Lcom/vk/commonid/b;": {"call"},
}
results: dict[str, list[str]] = {}
scanned = 0
with zipfile.ZipFile(archive) as outer:
    names = [x for x in outer.namelist() if x.lower().endswith(".apk")]
    if not names:
        raise SystemExit("No APK in source package")
    names.sort(key=lambda n: (not ("base" in n.lower() or "master" in n.lower()), len(n)))
    for name in names[:3]:
        rawapk = outer.read(name)
        with zipfile.ZipFile(io.BytesIO(rawapk)) as inner:
            dexnames = sorted((n for n in inner.namelist() if
                               n.startswith("classes") and n.endswith(".dex")))
            for dexname in dexnames:
                scanned += 1
                parsed = DEX(inner.read(dexname))
                for cls in parsed.get_classes():
                    clsname = cls.get_name()
                    if clsname not in wants:
                        continue
                    for method in cls.get_methods():
                        mname = method.get_name()
                        if mname not in wants[clsname] and not (
                            clsname.startswith("Lcom/vk/commonid/")):
                            continue
                        code = method.get_code()
                        if code is None:
                            continue
                        lines = [f"## {clsname}::{method.get_name()} {method.get_descriptor()}",
                                 f"Source APK member: {name}; DEX: {dexname}", "",
                                 "~~~smali-like"]
                        idx = 0
                        for inst in code.get_bc().get_instructions():
                            lines.append(f"{idx:05x}: {inst.get_name()} {inst.get_output(idx)}")
                            idx += inst.get_length()
                        lines.extend(["~~~", ""])
                        key = clsname + "." + method.get_name()
                        results.setdefault(key, []).append("\n".join(lines))
        if len(results) >= 30:
            break

required = [
    "Lcom/vk/pushme/network/PushMeApiImpl;.unsubscribeByDeviceId",
    "Lcom/vk/pushme/logic/usecase/UnsubscribeUseCase;.invoke",
]
missing = [x for x in required if x not in results]
if missing:
    raise SystemExit(f"Required methods absent from original DEX: {missing}; scanned {scanned}")

output.parent.mkdir(parents=True, exist_ok=True)
intro = [
    "# Оригинальный Mail.ru APK: статическая трассировка PushMe (DEX)",
    "",
    f"Исходный пакет: ru.mail.mailapp v15.107.0.148045; SHA-256 ZIP: `{digest}`.",
    f"Исследовано файлов DEX: {scanned}. Никаких запросов к Google/PushMe не было.",
    "",
    "Ниже дословные инструкции Dalvik (псевдосинтаксис анализатора),",
    "включая адреса сетевого метода, имена полей и порядок операций.",
    "Отсутствующие детали в самих инструкциях не следует додумывать.",
    "",
]
for key in sorted(results):
    intro.extend(["", *results[key]])
output.write_text("\n".join(intro), encoding="utf-8")
print("Verified original archive SHA256", digest)
print("DEX scanned", scanned)
print("Methods found", list(sorted(results)))
print("Saved", output, output.stat().st_size)
