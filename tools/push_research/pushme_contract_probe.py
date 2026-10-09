#!/usr/bin/env python3
"""Offline, read-only contract check for original Mail.ru Android PushMeSdk.

No live Mail.ru request is implemented. All examples are fabricated; credentials
are never read from the environment, file system, or user mailbox.

Evidence: original APK com/vk/pushme/network/PushMeApiImpl.java,
com/vk/pushme/network/model/request/InternalSubscriptionRequest.java,
ru/mail/util/push/pusher/PushMeSDKPusherTransport.java.
"""
from __future__ import annotations
from dataclasses import dataclass
import json
from typing import Any

SERVER = "https://alt-push-me.mail.ru"
V2_PATH = "/api/v2/set_settings"
V1_PATH = "/api/v1/set_settings"
APPLICATION = "mail"
FIREBASE_WIRE_PLATFORM = "android"
STATUS_ENABLED = 0


@dataclass(frozen=True)
class PushMeSubscription:
    account: str
    access_token: str
    google_sender_token: str
    android_id: str
    device_id: str
    client_time_zone: str
    client: dict[str, str]
    capabilities: dict[str, Any]
    enabled_tag_ids: tuple[int, ...] = ()
    sdk_device_id: str | None = None

    def prepare(self) -> dict[str, Any]:
        for value in (
            self.account, self.access_token, self.google_sender_token,
            self.android_id, self.device_id, self.client_time_zone
        ):
            if not value or not value.strip():
                raise ValueError("required subscription field is blank")

        # SDK first creates generic settings, then merges per-account extras.
        capabilities = {}
        if self.enabled_tag_ids:
            # Android app's tag enumeration is still under original APK review.
            # Fail closed rather than infer a wrong JSON key from Kotlin constant.
            raise ValueError("enabled_tag_ids require verified original field name")
        capabilities.update(self.capabilities)
        settings = {
            "capabilities": capabilities,
            "client": dict(self.client),
            "device_id": self.device_id,
            "client_time_zone": self.client_time_zone,
            "badge": {"status": True, "mode": "unread"},
        }
        root = {
            "account": self.account,
            "application": APPLICATION,
            "platform": FIREBASE_WIRE_PLATFORM,
            "token": self.google_sender_token,
            "access_token": self.access_token,
            "android_id": self.android_id,
            "settings": settings,
            "status": STATUS_ENABLED,
        }
        if self.sdk_device_id is not None:
            root["sdk_device_id"] = self.sdk_device_id
        return root


def prepare_batch(subscriptions: list[PushMeSubscription]) -> bytes:
    if not subscriptions:
        raise ValueError("empty batch")
    return json.dumps(
        [sub.prepare() for sub in subscriptions],
        ensure_ascii=False,
        separators=(",", ":")
    ).encode("utf-8")


def evaluate_reply(response_json: str, account: str) -> str:
    """Classify (but never log) original validate_result / error.code."""
    parsed = json.loads(response_json)
    if not isinstance(parsed, dict):
        return "MALFORMED"
    error = parsed.get("error")
    if not isinstance(error, dict) or not isinstance(error.get("code"), int):
        return "MALFORMED"
    if error["code"] != 0:
        return "SERVER_REJECTED"
    validations = parsed.get("validate_result")
    if validations is None:
        return "CODE_OK_WITHOUT_ACCOUNT_VALIDATION"
    if not isinstance(validations, list):
        return "MALFORMED"
    for item in validations:
        if not isinstance(item, dict) or not isinstance(item.get("account"), str):
            return "MALFORMED"
        if item["account"].casefold() == account.casefold():
            if item.get("is_valid") is True:
                return "ACCOUNT_ACCEPTED"
            if item.get("is_valid") is False:
                return "ACCOUNT_REJECTED"
            return "MALFORMED"
    return "ACCOUNT_NOT_VALIDATED"


def self_test() -> None:
    fake = PushMeSubscription(
        account="probe@example.invalid",
        access_token="FAKE_OAUTH_DO_NOT_USE",
        google_sender_token="FAKE_GOOGLE_TOKEN_DO_NOT_USE",
        android_id="0123456789abcdef",
        device_id="probe-unique-device",
        client_time_zone="Europe/Moscow",
        client={"name": "mail", "version": "fake"},
        capabilities={}
    )
    payload = prepare_batch([fake])
    d = json.loads(payload)
    assert len(d) == 1
    assert SERVER + V2_PATH == "https://alt-push-me.mail.ru/api/v2/set_settings"
    assert d[0]["application"] == "mail"
    assert d[0]["platform"] == "android"
    assert d[0]["status"] == 0
    assert d[0]["settings"]["badge"] == {"status": True, "mode": "unread"}
    assert d[0]["settings"]["capabilities"] == {}
    assert d[0]["account"] == "probe@example.invalid"
    assert d[0]["token"] != d[0]["access_token"]
    assert evaluate_reply('{"error":{"code":0},"validate_result":[{"account":"probe@example.invalid","is_valid":true}]}', fake.account) == "ACCOUNT_ACCEPTED"
    assert evaluate_reply('{"error":{"code":0},"validate_result":[{"account":"probe@example.invalid","is_valid":false}]}', fake.account) == "ACCOUNT_REJECTED"
    assert evaluate_reply('{"error":{"code":3}}', fake.account) == "SERVER_REJECTED"
    assert evaluate_reply('{"error":{"code":0}}', fake.account) == "CODE_OK_WITHOUT_ACCOUNT_VALIDATION"
    assert evaluate_reply('{"error":{"code":0},"validate_result":[{"account":"other@example.invalid","is_valid":true}]}', fake.account) == "ACCOUNT_NOT_VALIDATED"
    try:
        prepare_batch([])
    except ValueError:
        pass
    else:
        raise AssertionError("empty batch must be rejected")
    try:
        fake_with_unknown_tags = PushMeSubscription(
            **{**fake.__dict__, "enabled_tag_ids": (1,)}
        )
        fake_with_unknown_tags.prepare()
    except ValueError:
        pass
    else:
        raise AssertionError("unknown tag setting must be rejected")
    print("pushme_offline_contract_tests=PASS")


if __name__ == "__main__":
    self_test()
