# Research: original sender Google registration — single trial

An independent temporary Google check-in and ONE /c2dm/register3 request.
Original official APK package, public signing-certificate fingerprint and
R.string.push_sender_id were used; no mailbox account, OAuth, or Mail.ru
network endpoint was contacted.

- Google check-in: **OK**
- Google sender-specific registration: **TOKEN_ISSUED**
- Google MCS protected-channel login: **LOGIN_OK**
- Diagnostic class: TOKEN_NOT_PERSISTED

## Scope and limits

Temporary check-in/registration credentials were never logged or stored.
MCS login, if successful, establishes an authenticated delivery-channel session,
but does not prove receipt of any Mail.ru message.
The existence of a registration token, even if returned, would **not**
prove persistent delivery or acceptance by Mail.ru PushMe.
This is not the native FirebaseInstanceId.getToken implementation:
a Chrome-on-Windows check-in is a controlled approximation.
No repeated calls or retry loop. Delay >=5 seconds between network calls.
