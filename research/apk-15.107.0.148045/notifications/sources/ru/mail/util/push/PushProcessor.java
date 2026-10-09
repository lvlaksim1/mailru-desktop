package ru.mail.util.push;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.data.entities.MailboxProfileExtKt;
import ru.mail.data.transport.Transport;
import ru.mail.locator.Locator;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.util.DaysOfUsageCounter;
import ru.mail.util.log.Log;
import ru.mail.util.push.calendar.CalendarNotificationPush;
import ru.mail.util.push.parser.CalendarPushParser;
import ru.mail.util.push.parser.OrderPushParser;
import ru.mail.util.push.parser.PortalPushParser;
import ru.mail.util.push.parser.WalletPushParser;
import ru.mail.util.push.wallet.WalletNotificationPush;
import ru.mail.utils.UriUtils;
import ru.mail.utils.streams.StringUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public class PushProcessor {
    public static final String DATAKEY_ACCOUNT = "account";
    public static final String DATAKEY_ACK = "ack";
    public static final String DATAKEY_ACTION = "action";
    public static final String DATAKEY_CALLBACK_URL = "callback_url";
    public static final String DATAKEY_CATEGORIES = "categories";
    public static final String DATAKEY_CATEGORY = "category";
    public static final String DATAKEY_CLASS_NAME = "class_name";
    public static final String DATAKEY_COLLAPSE_KEY = "collapse_key";
    public static final String DATAKEY_COUNTER = "counter";
    public static final String DATAKEY_COUNTER_ACCOUNT = "counter_account";
    public static final String DATAKEY_DELETE_MSG = "delete_msg";
    public static final String DATAKEY_EVENT = "event";
    public static final String DATAKEY_EXTRAS = "extras";
    public static final String DATAKEY_FOLDER_FROM = "folder_from";
    public static final String DATAKEY_FOLDER_ID = "folder_id";
    public static final String DATAKEY_FOLDER_TO = "folder_to";
    public static final String DATAKEY_HAS_ATTACHMENT = "has_attachment";
    public static final String DATAKEY_ID = "id";
    public static final String DATAKEY_IMPORTANCE = "importance";
    public static final String DATAKEY_PACKAGE = "package";
    public static final String DATAKEY_PUSH_ID = "pushme_push_id";
    public static final String DATAKEY_SENDER = "sender";
    public static final String DATAKEY_SENDER_ORIG = "sender_orig";
    public static final String DATAKEY_SNIPPET = "snippet";
    public static final String DATAKEY_TEXT = "text";
    public static final String DATAKEY_THREAD_HAS_MULTIPLE_MESSAGES = "thread_id_mm";
    public static final String DATAKEY_THREAD_ID = "thread_id";
    public static final String DATAKEY_TIME = "uts";
    public static final String DATAKEY_TYPE = "mime-type";
    public static final String DATAKEY_URI = "uri";
    public static final String DATA_KEY_HUB_LINK = "hub_link";
    public static final String DATA_KEY_LANG_FILTER = "lang_filter";
    private static final String DATA_KEY_MEDIA = "media";
    public static final String DATA_KEY_OPEN = "open";
    private static final String DATA_KEY_OPEN_URL = "open_url";
    private static final String DATA_KEY_RESTORE_PASSWORD_EXTRA = "extra";
    private static final String DATA_KEY_RESTORE_PASSWORD_EXTRA_ACCOUNT = "account";
    private static final String DATA_KEY_RESTORE_PASSWORD_TITLE = "message_text";
    private static final String DATA_KEY_RESTORE_PASSWORD_URL = "url";
    private static final String DATA_KEY_TEXT = "text";
    private static final String DATA_KEY_TITLE = "title";
    private static final String DATA_KEY_TYPE = "type";
    private static final Log LOG = Log.getLog("PushProcessor");
    private static final String REMINDER_ACTUAL_MSG_CUSTOM_SENDER = "custom_sender";
    private static final String REMINDER_ACTUAL_MSG_CUSTOM_TEXT = "custom_text";
    private static final String REMINDER_ACTUAL_MSG_IMG_URL = "img_url";

    private static boolean fillPushData(Context context, Map<String, String> map, NewMailPush newMailPush, Configuration.PushCategoryMapper pushCategoryMapper, boolean z10, @Nullable Long l10) {
        String str = map.get(DATAKEY_TIME);
        newMailPush.setTimestamp(TextUtils.isEmpty(str) ? System.currentTimeMillis() : Long.parseLong(str) * 1000);
        newMailPush.setProfileId(map.get("account"));
        String str2 = map.get(DATAKEY_SENDER_ORIG);
        if (TextUtils.isEmpty(str2)) {
            str2 = map.get("sender");
        }
        newMailPush.setSender(str2);
        newMailPush.setSubject(map.get("text"));
        newMailPush.setMessageId(map.get("id"));
        newMailPush.setFolderId(getFolderId(map));
        newMailPush.setHasAttachments("1".equals(map.get(DATAKEY_HAS_ATTACHMENT)));
        newMailPush.setSnippet(map.get("snippet"));
        newMailPush.setIsImportant("1".equals(map.get(DATAKEY_IMPORTANCE)));
        newMailPush.setOpenUrl(getOpenUrl(context, map));
        newMailPush.setThreadId(prepareThreadId(map.get("thread_id")));
        newMailPush.setThreadHasMultipleMessages("1".equals(map.get(DATAKEY_THREAD_HAS_MULTIPLE_MESSAGES)));
        newMailPush.setMailCategory(getCategory(map.get("categories"), pushCategoryMapper));
        newMailPush.setPushMeSdkPushId(l10);
        if (z10) {
            return fillRemindData(map, newMailPush);
        }
        return true;
    }

    private static boolean fillRemindData(Map<String, String> map, NewMailPush newMailPush) {
        String str = map.get(REMINDER_ACTUAL_MSG_CUSTOM_TEXT);
        if (str == null || ru.mail.auth.d1.a(str)) {
            LOG.w("Custom subject is empty for email reminder push");
            return false;
        }
        newMailPush.setCustomSubject(str);
        newMailPush.setImageUrl(map.get("img_url"));
        newMailPush.setCustomSender(map.get("custom_sender"));
        return true;
    }

    private static String getAckUrl(Map<String, String> map) {
        String str = map.get(DATA_KEY_HUB_LINK);
        if (str != null) {
            try {
                return new JSONObject(str).getString(DATAKEY_ACK);
            } catch (JSONException unused) {
                LOG.d("Can't parse ack from hub link");
            }
        }
        return map.get(DATAKEY_ACK);
    }

    private static MailItemTransactionCategory getCategory(@Nullable String str, Configuration.PushCategoryMapper pushCategoryMapper) {
        if (TextUtils.isEmpty(str)) {
            return MailItemTransactionCategory.NO_CATEGORIES;
        }
        try {
            JSONArray jSONArray = new JSONArray(str);
            for (int i10 = 0; i10 < jSONArray.length(); i10++) {
                MailItemTransactionCategory category = pushCategoryMapper.getCategory(jSONArray.getInt(i10));
                if (category != null) {
                    return category;
                }
            }
        } catch (NumberFormatException | JSONException e10) {
            LOG.e("Cant parse: " + str, e10);
        }
        return MailItemTransactionCategory.NO_CATEGORIES;
    }

    private static long getFolderId(Map<String, String> map) {
        try {
            return Long.parseLong(map.get("folder_id"));
        } catch (NumberFormatException e10) {
            LOG.w("cannot parse DATAKEY_FOLDER_ID:", e10);
            return 0L;
        }
    }

    @Nullable
    private static String getOpenUrl(Context context, Map<String, String> map) {
        if (!shouldSendAckOpenAnalytics(context)) {
            return null;
        }
        String str = map.get(DATA_KEY_HUB_LINK);
        if (str != null) {
            try {
                return new JSONObject(str).getString("open");
            } catch (JSONException unused) {
                LOG.d("Can't parse open url from hub link");
            }
        }
        return map.get(DATAKEY_ACK);
    }

    private static Transport getTransport(Context context, String str) {
        return MailboxProfileExtKt.createTransport(((CommonDataManager) Locator.from(context).locate(CommonDataManager.class)).extractProfile(str).getTransportType());
    }

    private static PushMessage handleAckPush(Map<String, String> map) {
        return new PingPush(UriUtils.mergeQueryParameters(Uri.parse(getAckUrl(map)), new Uri.Builder().appendQueryParameter("action", DATAKEY_ACK).build()).toString());
    }

    private static CalendarNotificationPush handleCalendarNotification(Map<String, String> map, int i10) {
        try {
            return new CalendarPushParser().parse(map, i10);
        } catch (Exception e10) {
            LOG.e("Error in PushProcessor", e10);
            return null;
        }
    }

    private static CountPush handleCountPush(Map<String, String> map) {
        int i10 = Integer.parseInt(map.get("counter"));
        int i11 = Integer.parseInt(map.get(DATAKEY_COUNTER_ACCOUNT));
        CountPush countPush = new CountPush();
        countPush.setProfileId(map.get("account"));
        countPush.setCounter(i10);
        countPush.setCounterAccount(i11);
        LOG.d("Widget counter = " + i10 + "has been parsed");
        return countPush;
    }

    private static DeleteNotificationPush handleDeleteNotificationPush(Map<String, String> map) {
        DeleteNotificationPush deleteNotificationPush = new DeleteNotificationPush();
        deleteNotificationPush.setProfileId(map.get("account"));
        deleteNotificationPush.setMessageId(map.get("id"));
        deleteNotificationPush.setCollapseKey(map.get("collapse_key"));
        deleteNotificationPush.setIsNeedDeleteMsg(map.get(DATAKEY_DELETE_MSG));
        LOG.d("DeleteNotificationPush has been parsed, " + deleteNotificationPush);
        return deleteNotificationPush;
    }

    private static MovePush handleMovePush(Context context, Map<String, String> map) {
        MailAppDependencies.analytics(context).sendPushAnalytics("MoveMail");
        return new MovePush(map.get("account"), map.get("id"), Long.parseLong(map.get("folder_from")), Long.parseLong(map.get(DATAKEY_FOLDER_TO)));
    }

    private static NewMailPush handleNewMailPush(Context context, Map<String, String> map, int i10, @Nullable Long l10) {
        NewMailPush newMailPush = new NewMailPush();
        ConfigurationWithRawData configuration = ConfigurationRepository.from(context).getConfiguration();
        if (!fillPushData(context, map, newMailPush, configuration.getPushCategoryMapper(), isReminderPushAndNeedHandle(context, i10, configuration), l10)) {
            LOG.w("There is wrong push data in the push: " + newMailPush);
            return null;
        }
        if (TextUtils.isEmpty(newMailPush.getProfileId()) || !isValidMessageId(context, newMailPush.getMessageId(), newMailPush.getProfileId())) {
            LOG.w("There is no profile or invalid message id in the push" + newMailPush);
            return null;
        }
        LOG.d("Push message has been parsed " + newMailPush);
        MailAppDependencies.analytics(context).sendNewMailPushReceived(newMailPush.getMailCategory().toString(), newMailPush.hasAttachments(), newMailPush.isReminder());
        return newMailPush;
    }

    private static PingPush handlePingPush(Map<String, String> map) {
        return new PingPush(map.get(DATAKEY_CALLBACK_URL));
    }

    private static PortalPush handlePortalNotification(Map<String, String> map) {
        try {
            return new PortalPushParser(map).parse();
        } catch (Exception e10) {
            LOG.e("Unable to parse portal push", e10);
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0016  */
    private static PushMessage handlePromoteUrl(Context context, Map<String, String> map, @Nullable Long l10) {
        String str;
        if (map.containsKey("type")) {
            str = map.get("type");
            if (TextUtils.isEmpty(str)) {
                str = "unknown";
            }
        } else {
            str = "unknown";
        }
        String str2 = str;
        MailAppDependencies.analytics(context).sendPromotePushReceived(str2);
        return new PromoteUrlPushMessage(1002, Uri.parse(map.get(DATA_KEY_OPEN_URL)), map.get("title"), map.get("text"), map.get(DATA_KEY_LANG_FILTER), map.get("account"), str2, getOpenUrl(context, map), l10, map.get(DATA_KEY_MEDIA));
    }

    private static PushMessage handleRatBindPush(Map<String, String> map) {
        String str = map.get("account");
        if (str != null) {
            return new RatBindPush(PushEvent.EVENT_RAT_BIND, str);
        }
        return null;
    }

    private static RemoteCommandPush handleRemoteCommandPush(Context context, Map<String, String> map) {
        RemoteCommandPush remoteCommandPush = new RemoteCommandPush();
        remoteCommandPush.setAction(map.get("action"));
        remoteCommandPush.setPackage(context.getPackageName());
        remoteCommandPush.setUri(map.get("uri"));
        remoteCommandPush.setCategory(map.get("category"));
        remoteCommandPush.setComponentPackage(map.get("package"));
        remoteCommandPush.setComponentClassName(map.get(DATAKEY_CLASS_NAME));
        remoteCommandPush.setType(map.get(DATAKEY_TYPE));
        remoteCommandPush.setExtras(parseExtras(map));
        return remoteCommandPush;
    }

    private static PushMessage handleRestorePasswordPush(Context context, Map<String, String> map) {
        try {
            PasswordRestorePush passwordRestorePush = new PasswordRestorePush(1003, map.get("url"), new JSONObject(map.get(DATA_KEY_RESTORE_PASSWORD_EXTRA)).getString("account"), map.get(DATA_KEY_RESTORE_PASSWORD_TITLE));
            MailAppDependencies.analytics(context).sendPushAnalytics("PushRestore");
            return passwordRestorePush;
        } catch (Exception e10) {
            throw new NumberFormatException(e10.getMessage());
        }
    }

    private static PushMessage handleSendLogsPush(Context context) {
        MailAppDependencies.analytics(context).sendLogsPushReceived();
        return new SendLogsPush();
    }

    private static PushMessage handleUpdateOrderStatus(Map<String, String> map) {
        return new OrderPushParser().parse(map);
    }

    private static WalletNotificationPush handleWalletNotification(Map<String, String> map, int i10) {
        try {
            return new WalletPushParser().parse(map, i10);
        } catch (Exception e10) {
            LOG.e("Unable to parse wallet push", e10);
            return null;
        }
    }

    private static PushMessage handleWalletNotificationByConfig(Context context, Map<String, String> map, int i10) {
        return ConfigurationRepository.from(context).getConfiguration().getWalletAppConfig().getWalletPushesEnabled() ? handleWalletNotification(map, i10) : handleCalendarNotification(map, 3000);
    }

    private static boolean hasAckUrl(Map<String, String> map) {
        if (map.containsKey(DATA_KEY_HUB_LINK)) {
            try {
                return !new JSONObject(map.get(DATA_KEY_HUB_LINK)).getString(DATAKEY_ACK).isEmpty();
            } catch (JSONException unused) {
                LOG.d("Can't parse ack from hub link");
            }
        }
        return map.containsKey(DATAKEY_ACK);
    }

    private static boolean isReminderPushAndNeedHandle(Context context, int i10, Configuration configuration) {
        boolean z10 = i10 == 40;
        boolean zIsReminderPushEnabled = configuration.isReminderPushEnabled();
        if (z10 && zIsReminderPushEnabled && configuration.isReminderPushOnlyForInactiveUsers()) {
            zIsReminderPushEnabled = DaysOfUsageCounter.getDaysPassed(context) > 0;
            if (!zIsReminderPushEnabled) {
                MailAppDependencies.analytics(context).onReminderPushSkipped();
            }
        }
        return z10 && zIsReminderPushEnabled;
    }

    private static boolean isValidMessageId(Context context, String str, String str2) {
        return !TextUtils.isEmpty(str) && getTransport(context, str2).isValidMessageId(str);
    }

    public static List<PushMessage> makePushMessages(Context context, Map<String, String> map, @Nullable Long l10) {
        return Collections.unmodifiableList(makePushMessagesInternal(context, map, l10));
    }

    private static List<PushMessage> makePushMessagesInternal(Context context, Map<String, String> map, @Nullable Long l10) {
        PushMessage pushMessageHandleNewMailPush;
        try {
            int i10 = Integer.parseInt(map.get("event"));
            Log log = LOG;
            log.i("handling push message with id " + i10);
            switch (i10) {
                case 4:
                case 40:
                    pushMessageHandleNewMailPush = handleNewMailPush(context, map, i10, l10);
                    break;
                case 6:
                    pushMessageHandleNewMailPush = handleDeleteNotificationPush(map);
                    break;
                case 10:
                    pushMessageHandleNewMailPush = handleCountPush(map);
                    break;
                case 13:
                    pushMessageHandleNewMailPush = handleMovePush(context, map);
                    break;
                case 20:
                    log.d("Bad cookie push");
                    pushMessageHandleNewMailPush = null;
                    break;
                case 30:
                    pushMessageHandleNewMailPush = handleRemoteCommandPush(context, map);
                    break;
                case 101:
                    pushMessageHandleNewMailPush = handlePingPush(map);
                    break;
                case 228:
                    pushMessageHandleNewMailPush = handleSendLogsPush(context);
                    break;
                case PushEvent.EVENT_RAT_BIND /* 229 */:
                    pushMessageHandleNewMailPush = handleRatBindPush(map);
                    break;
                case 1002:
                    pushMessageHandleNewMailPush = handlePromoteUrl(context, map, l10);
                    break;
                case 1003:
                    pushMessageHandleNewMailPush = handleRestorePasswordPush(context, map);
                    break;
                case 2002:
                    pushMessageHandleNewMailPush = handleUpdateOrderStatus(map);
                    break;
                case 3000:
                case 3001:
                    pushMessageHandleNewMailPush = handleCalendarNotification(map, i10);
                    break;
                case 4001:
                    pushMessageHandleNewMailPush = handlePortalNotification(map);
                    break;
                case 5000:
                    pushMessageHandleNewMailPush = handleWalletNotificationByConfig(context, map, i10);
                    break;
                default:
                    log.w("Unknown event id = " + i10);
                    pushMessageHandleNewMailPush = null;
                    break;
            }
            if (pushMessageHandleNewMailPush != null && shouldSendAckOpenAnalytics(context) && hasAckUrl(map)) {
                return Arrays.asList(pushMessageHandleNewMailPush, handleAckPush(map));
            }
            return pushMessageHandleNewMailPush == null ? Collections.EMPTY_LIST : Collections.singletonList(pushMessageHandleNewMailPush);
        } catch (NumberFormatException e10) {
            MailAppDependencies.analytics(context).sendParsePushMessageError(map, e10.getMessage());
            return Collections.EMPTY_LIST;
        }
    }

    @NonNull
    private static HashMap<String, String> parseExtras(Map<String, String> map) {
        String str = map.get(DATAKEY_EXTRAS);
        HashMap<String, String> map2 = new HashMap<>();
        if (!TextUtils.isEmpty(map.get("account"))) {
            map2.put("account", map.get("account"));
        }
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                Iterator<String> itKeys = jSONObject.keys();
                while (itKeys.hasNext()) {
                    String next = itKeys.next();
                    map2.put(next, jSONObject.getString(next));
                }
            }
        } catch (JSONException e10) {
            e10.printStackTrace();
        }
        return map2;
    }

    @Nullable
    private static String prepareThreadId(String str) {
        LOG.d("raw threadId=" + str);
        if (str == null) {
            return str;
        }
        if (str.length() > 1 && str.endsWith("\u0000")) {
            str = str.substring(0, str.length() - 1);
        }
        if (StringUtils.countMatches(str, '0') == str.length()) {
            return null;
        }
        return str;
    }

    private static boolean shouldSendAckOpenAnalytics(@NonNull Context context) {
        return !ConfigurationRepository.from(context).getConfiguration().getPushMeSdk().getInitSdk();
    }
}
