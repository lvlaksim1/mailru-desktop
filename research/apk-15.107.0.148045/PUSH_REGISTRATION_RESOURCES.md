# Original APK: push registration identifiers (non-secret allowlist)

APK Mail.ru 15.107.0.148045 (ZIP SHA-256 verified).
Only fixed public identifiers and matching manifest registrations are retained.
No API keys, service-account keys, auth tokens, personal data, full resources or APK binaries.

## Explicit resources

### push_sender_id
- resource 0x7f130d79 string/push_sender_id
- () "1098335887158"

### gcm_defaultSenderId
- resource 0x7f1306f3 string/gcm_defaultSenderId
- () "61247752867"

### google_app_id
- resource 0x7f13071f string/google_app_id
- () "1:61247752867:android:d199c9f145040309"

### project_id
- resource 0x7f130d0f string/project_id
- () "fluorcorpmailru"

### push_default_api
- resource 0x7f130d60 string/push_default_api
- () "api"

### push_default_host
- resource 0x7f130d62 string/push_default_host
- () "alt-push-me.mail.ru"

### push_default_scheme
- resource 0x7f130d65 string/push_default_scheme
- () "https"

## Manifest push-related components and events

- E: action (line=1899)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="ru.mail.mailapp.OPEN_WALLET_PUSH_ACTION" (Raw: "ru.mail.mailapp.OPEN_WALLET_PUSH_ACTION")
- E: service (line=1905)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="ru.mail.util.push.gcm.MailMessagingService" (Raw: "ru.mail.util.push.gcm.MailMessagingService")
- A: http://schemas.android.com/apk/res/android:exported(0x01010010)=true
- E: intent-filter (line=1907)
- E: action (line=1909)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="com.google.firebase.MESSAGING_EVENT" (Raw: "com.google.firebase.MESSAGING_EVENT")
- E: service (line=1915)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="ru.mail.util.push.huawei.MailMessagingService" (Raw: "ru.mail.util.push.huawei.MailMessagingService")
- A: http://schemas.android.com/apk/res/android:enabled(0x0101000e)=false
- A: http://schemas.android.com/apk/res/android:exported(0x01010010)=false
- E: intent-filter (line=1917)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="ru.mail.consent.ui.ConsentActivity" (Raw: "ru.mail.consent.ui.ConsentActivity")
- E: service (line=2713)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="ru.mail.rustoresdk.MailMessagingService" (Raw: "ru.mail.rustoresdk.MailMessagingService")
- E: intent-filter (line=2715)
- E: action (line=2717)
- E: intent-filter (line=3277)
- E: action (line=3279)
- E: service (line=3285)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="com.google.firebase.components.ComponentDiscoveryService" (Raw: "com.google.firebase.components.ComponentDiscoveryService")
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="com.google.android.gms.cloudmessaging.FINISHED_AFTER_HANDLED" (Raw: "com.google.android.gms.cloudmessaging.FINISHED_AFTER_HANDLED")
- A: http://schemas.android.com/apk/res/android:value(0x01010024)=true
- E: service (line=3331)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="com.google.firebase.messaging.FirebaseMessagingService" (Raw: "com.google.firebase.messaging.FirebaseMessagingService")
- A: http://schemas.android.com/apk/res/android:directBootAware(0x01010505)=true
- E: intent-filter (line=3333)
- A: http://schemas.android.com/apk/res/android:priority(0x0101001c)=-500
- E: action (line=3335)
- E: meta-data (line=3341)
- A: http://schemas.android.com/apk/res/android:name(0x01010003)="com.google.android.play.billingclient.version" (Raw: "com.google.android.play.billingclient.version")
- A: http://schemas.android.com/apk/res/android:value(0x01010024)="7.1.1" (Raw: "7.1.1")

## Boundaries

Static registration metadata does not establish successful enrollment or delivery to Windows.
The exact FCM registration and server acceptance must be tested separately with an owned test account.
