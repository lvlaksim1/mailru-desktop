package ru.mail.data.cmd.server;

import android.text.TextUtils;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommand;
import ru.mail.offline.attaches.storage.api.MailOfflineAttachmentPersistedCacheStatus;
import ru.mail.serverapi.MailCommandStatus;
import ru.mail.ui.quickactions.QuickActionOptionProvider;
import ru.mail.util.log.Log;
import ru.mail.utils.UtilExtensionsKt;
import ru.mail.utils.rfc822.Rfc822Token;
import ru.mail.utils.rfc822.Rfc822Tokenizer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 '2\u00020\u0001:\u0004$%&'B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e0\u000fR\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0010J*\u0010\u0011\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e0\u000fR\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0010H\u0002J*\u0010\u0012\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e0\u000fR\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0010H\u0002J*\u0010\u0013\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e0\u000fR\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0010H\u0002J\u0016\u0010\u0014\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J}\u0010\u0017\u001a\b\u0012\u0002\b\u0003\u0018\u00010\t2\u0006\u0010\f\u001a\u00020\r2\u0012\u0010\u000e\u001a\u000e0\u000fR\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00102Q\u0010\u0018\u001aM\u0012\u0013\u0012\u00110\u001a¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u001e¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0013\u0012\u00110 ¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(!\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\t0\u0019H\u0002J\u0010\u0010\"\u001a\u00020#2\u0006\u0010\f\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lru/mail/data/cmd/server/ErrorHandler;", "", "stringProvider", "Lru/mail/data/cmd/server/ErrorStringProvider;", "<init>", "(Lru/mail/data/cmd/server/ErrorStringProvider;)V", "log", "Lru/mail/util/log/Log;", "execute", "Lru/mail/mailbox/cmd/CommandStatus;", "enumErrorClass", "Lru/mail/data/cmd/server/ErrorHandler$EnumErrorHandlerName;", "response", "Lru/mail/network/NetworkCommand$Response;", "customDelegate", "Lru/mail/network/NetworkCommand$NetworkCommandBaseDelegate;", "Lru/mail/network/NetworkCommand;", "handleTornadoUploadRequestErrors", "handleAddToCloudBundleErrors", "handleTornadoSendMsgErrors", "getSendMessageStatusCommand", "correspondents", "Lorg/json/JSONArray;", "provideStatusAndBody", "block", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "name", "status", "Lorg/json/JSONObject;", "body", "", "bodyHaseError", "getBody", "Lru/mail/data/cmd/server/ErrorHandler$Result;", QuickActionOptionProvider.OPTION_TAG, "EnumErrorHandlerName", "Result", "Companion", "message-send_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nErrorHandler.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ErrorHandler.kt\nru/mail/data/cmd/server/ErrorHandler\n+ 2 UtilExtensions.kt\nru/mail/utils/UtilExtensionsKt\n*L\n1#1,324:1\n76#2,2:325\n76#2,4:327\n79#2:331\n*S KotlinDebug\n*F\n+ 1 ErrorHandler.kt\nru/mail/data/cmd/server/ErrorHandler\n*L\n221#1:325,2\n245#1:327,4\n221#1:331\n*E\n"})
public final class ErrorHandler {
    public static final long DEFAULT_ATTACH_SIZE = 26214400;

    @NotNull
    private final Log log;

    @NotNull
    private final ErrorStringProvider stringProvider;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Lru/mail/data/cmd/server/ErrorHandler$EnumErrorHandlerName;", "", "<init>", "(Ljava/lang/String;I)V", "TORNADO_UPLOAD_REQUEST", "ADD_TO_CLOUD_BUNDLE", "TORNADO_SEND_REQUEST", "message-send_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum EnumErrorHandlerName {
        TORNADO_UPLOAD_REQUEST,
        ADD_TO_CLOUD_BUNDLE,
        TORNADO_SEND_REQUEST;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        @NotNull
        public static EnumEntries<EnumErrorHandlerName> getEntries() {
            return $ENTRIES;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u0019\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u000e\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000f\u001a\u00020\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lru/mail/data/cmd/server/ErrorHandler$Result;", "", "body", "Lorg/json/JSONObject;", "bodyWithError", "", "<init>", "(Lorg/json/JSONObject;Z)V", "getBody", "()Lorg/json/JSONObject;", "getBodyWithError", "()Z", "component1", "component2", "copy", "equals", "other", "hashCode", "", "toString", "", "message-send_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final /* data */ class Result {

        @Nullable
        private final JSONObject body;
        private final boolean bodyWithError;

        public Result(@Nullable JSONObject jSONObject, boolean z10) {
            this.body = jSONObject;
            this.bodyWithError = z10;
        }

        public static /* synthetic */ Result copy$default(Result result, JSONObject jSONObject, boolean z10, int i10, Object obj) {
            if ((i10 & 1) != 0) {
                jSONObject = result.body;
            }
            if ((i10 & 2) != 0) {
                z10 = result.bodyWithError;
            }
            return result.copy(jSONObject, z10);
        }

        @Nullable
        /* JADX INFO: renamed from: component1, reason: from getter */
        public final JSONObject getBody() {
            return this.body;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final boolean getBodyWithError() {
            return this.bodyWithError;
        }

        @NotNull
        public final Result copy(@Nullable JSONObject body, boolean bodyWithError) {
            return new Result(body, bodyWithError);
        }

        public boolean equals(@Nullable Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof Result)) {
                return false;
            }
            Result result = (Result) other;
            return Intrinsics.areEqual(this.body, result.body) && this.bodyWithError == result.bodyWithError;
        }

        @Nullable
        public final JSONObject getBody() {
            return this.body;
        }

        public final boolean getBodyWithError() {
            return this.bodyWithError;
        }

        public int hashCode() {
            JSONObject jSONObject = this.body;
            return ((jSONObject == null ? 0 : jSONObject.hashCode()) * 31) + Boolean.hashCode(this.bodyWithError);
        }

        @NotNull
        public String toString() {
            return "Result(body=" + this.body + ", bodyWithError=" + this.bodyWithError + ")";
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lru/mail/data/cmd/server/ErrorHandler$TAG;", "", "<init>", "()V", "BODY", "", MailOfflineAttachmentPersistedCacheStatus.ERROR, "VALUE", "FILE", "SIZE", "INVALID", "DISABLED", "DISABLED_FROM_REGINFO", "MBOX_QUOTAS_SEND_LIMIT_EXCEEDED", "MBOX_QUOTAS_ATTACH", "MBOX_QUOTAS_LINK_ATTACH", "MBOX_QUOTAS_LIMIT_EXCEEDED", "FILE_EXISTS", "CORRESPONDENTS_TO", "CORRESPONDENTS_CC", "CORRESPONDENTS_BCC", "SEND_DATE", "message-send_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class TAG {

        @NotNull
        public static final String BODY = "body";

        @NotNull
        public static final String CORRESPONDENTS_BCC = "correspondents.bcc";

        @NotNull
        public static final String CORRESPONDENTS_CC = "correspondents.cc";

        @NotNull
        public static final String CORRESPONDENTS_TO = "correspondents.to";

        @NotNull
        public static final String DISABLED = "disabled";

        @NotNull
        public static final String DISABLED_FROM_REGINFO = "disabled_from_reginfo";

        @NotNull
        public static final String ERROR = "error";

        @NotNull
        public static final String FILE = "file";

        @NotNull
        public static final String FILE_EXISTS = "file_exists";

        @NotNull
        public static final TAG INSTANCE = new TAG();

        @NotNull
        public static final String INVALID = "invalid";

        @NotNull
        public static final String MBOX_QUOTAS_ATTACH = "mbox_quotas.attach";

        @NotNull
        public static final String MBOX_QUOTAS_LIMIT_EXCEEDED = "mbox_size_limit_exceeded";

        @NotNull
        public static final String MBOX_QUOTAS_LINK_ATTACH = "mbox_quotas.link_attach";

        @NotNull
        public static final String MBOX_QUOTAS_SEND_LIMIT_EXCEEDED = "mbox_quotas.box_send";

        @NotNull
        public static final String SEND_DATE = "send_date";

        @NotNull
        public static final String SIZE = "size";

        @NotNull
        public static final String VALUE = "value";

        private TAG() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[EnumErrorHandlerName.values().length];
            try {
                iArr[EnumErrorHandlerName.TORNADO_UPLOAD_REQUEST.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[EnumErrorHandlerName.ADD_TO_CLOUD_BUNDLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[EnumErrorHandlerName.TORNADO_SEND_REQUEST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public ErrorHandler(@NotNull ErrorStringProvider stringProvider) {
        Intrinsics.checkNotNullParameter(stringProvider, "stringProvider");
        this.stringProvider = stringProvider;
        this.log = Log.INSTANCE.getLog("ErrorHandler");
    }

    private final Result getBody(NetworkCommand.Response response) throws JSONException {
        JSONObject jSONObject = new JSONObject(response.getRespString());
        if (!jSONObject.has("body")) {
            return new Result(null, false);
        }
        String strOptString = jSONObject.optString("error", "");
        Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
        return strOptString.length() == 0 ? new Result(jSONObject.getJSONObject("body"), false) : new Result(jSONObject, true);
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00cf  */
    /* JADX WARN: Code duplicated, block: B:45:0x00db  */
    /* JADX WARN: Code duplicated, block: B:47:0x00de  */
    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    private final CommandStatus<?> getSendMessageStatusCommand(JSONArray correspondents) throws JSONException {
        String string;
        CommandStatus.SIMPLE_ERROR simple_error;
        String string2;
        Rfc822Token[] rfc822TokenArr;
        int length = correspondents.length();
        CommandStatus.SIMPLE_ERROR simple_error2 = null;
        for (int i10 = 0; i10 < length; i10++) {
            JSONObject jSONObject = correspondents.getJSONObject(i10);
            Intrinsics.checkNotNullExpressionValue(jSONObject, "getJSONObject(...)");
            if (jSONObject.has("error") && jSONObject.has("value") && (string = jSONObject.getString("error")) != null) {
                switch (string.hashCode()) {
                    case 270940796:
                        if (string.equals("disabled")) {
                            string2 = jSONObject.getString("value");
                            rfc822TokenArr = Rfc822Tokenizer.tokenize(string2);
                            if (!(rfc822TokenArr.length == 0)) {
                                string2 = rfc822TokenArr[0].getAddress();
                            }
                            ErrorStringProvider errorStringProvider = this.stringProvider;
                            Intrinsics.checkNotNull(string2);
                            simple_error = new CommandStatus.SIMPLE_ERROR(errorStringProvider.getInvalidRecipient(string2));
                            simple_error2 = simple_error;
                        }
                        break;
                    case 1077690512:
                        if (string.equals(TAG.DISABLED_FROM_REGINFO)) {
                            string2 = jSONObject.getString("value");
                            rfc822TokenArr = Rfc822Tokenizer.tokenize(string2);
                            if (!(rfc822TokenArr.length == 0)) {
                                string2 = rfc822TokenArr[0].getAddress();
                            }
                            ErrorStringProvider errorStringProvider2 = this.stringProvider;
                            Intrinsics.checkNotNull(string2);
                            simple_error = new CommandStatus.SIMPLE_ERROR(errorStringProvider2.getInvalidRecipient(string2));
                            simple_error2 = simple_error;
                        }
                        break;
                    case 1384993748:
                        if (string.equals(TAG.MBOX_QUOTAS_LIMIT_EXCEEDED)) {
                            ArrayList arrayList = new ArrayList();
                            int length2 = correspondents.length();
                            for (int i11 = 0; i11 < length2; i11++) {
                                JSONObject jSONObject2 = correspondents.getJSONObject(i11);
                                Intrinsics.checkNotNullExpressionValue(jSONObject2, "getJSONObject(...)");
                                if (Intrinsics.areEqual(jSONObject2.getString("error"), TAG.MBOX_QUOTAS_LIMIT_EXCEEDED)) {
                                    String strOptString = jSONObject2.optString("value", "");
                                    Intrinsics.checkNotNullExpressionValue(strOptString, "optString(...)");
                                    Rfc822Token[] rfc822TokenArr2 = Rfc822Tokenizer.tokenize(strOptString);
                                    if (!(rfc822TokenArr2.length == 0)) {
                                        String address = rfc822TokenArr2[0].getAddress();
                                        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
                                        arrayList.add(address);
                                    }
                                }
                            }
                            if (arrayList.size() == 1) {
                                simple_error = new CommandStatus.SIMPLE_ERROR(this.stringProvider.getQuotasEmailLimitExceeded((String) arrayList.get(0)));
                            } else {
                                ErrorStringProvider errorStringProvider3 = this.stringProvider;
                                String strJoin = TextUtils.join(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, arrayList);
                                Intrinsics.checkNotNullExpressionValue(strJoin, "join(...)");
                                simple_error = new CommandStatus.SIMPLE_ERROR(errorStringProvider3.getQuotasEmailLimitExceeded(strJoin));
                            }
                            simple_error2 = simple_error;
                        }
                        break;
                    case 1959784951:
                        if (string.equals("invalid")) {
                            string2 = jSONObject.getString("value");
                            rfc822TokenArr = Rfc822Tokenizer.tokenize(string2);
                            if (!(rfc822TokenArr.length == 0)) {
                                string2 = rfc822TokenArr[0].getAddress();
                            }
                            ErrorStringProvider errorStringProvider4 = this.stringProvider;
                            Intrinsics.checkNotNull(string2);
                            simple_error = new CommandStatus.SIMPLE_ERROR(errorStringProvider4.getInvalidRecipient(string2));
                            simple_error2 = simple_error;
                        }
                        break;
                }
            }
        }
        return simple_error2;
    }

    private final CommandStatus<?> handleAddToCloudBundleErrors(NetworkCommand.Response response, NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate) {
        return provideStatusAndBody(response, customDelegate, new Function3() { // from class: ru.mail.data.cmd.server.g
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ErrorHandler.handleAddToCloudBundleErrors$lambda$0(this.f85208a, ((Integer) obj).intValue(), (JSONObject) obj2, ((Boolean) obj3).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CommandStatus handleAddToCloudBundleErrors$lambda$0(ErrorHandler errorHandler, int i10, JSONObject body, boolean z10) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (i10 != 400) {
            return null;
        }
        if (z10) {
            return new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getWrongEmail());
        }
        if (body.has("size")) {
            if (body.getJSONObject("size").has("error")) {
                return new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getAttachToLarge(""));
            }
            return null;
        }
        if (body.has("error") && body.getString("error").equals(TAG.FILE_EXISTS)) {
            return new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getFileExistError());
        }
        if (!body.has(TAG.MBOX_QUOTAS_LINK_ATTACH)) {
            return null;
        }
        long jOptLong = body.getJSONObject(TAG.MBOX_QUOTAS_LINK_ATTACH).optLong("value", 0L);
        return new CommandStatus.SIMPLE_ERROR(jOptLong > 0 ? errorHandler.stringProvider.getAttachmentsTooBigQuota(UtilExtensionsKt.kbToMb(jOptLong)) : errorHandler.stringProvider.getAttachmentsDisable());
    }

    private final CommandStatus<?> handleTornadoSendMsgErrors(final NetworkCommand.Response response, final NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate) {
        return provideStatusAndBody(response, customDelegate, new Function3() { // from class: ru.mail.data.cmd.server.e
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ErrorHandler.handleTornadoSendMsgErrors$lambda$0(customDelegate, response, this, ((Integer) obj).intValue(), (JSONObject) obj2, ((Boolean) obj3).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CommandStatus handleTornadoSendMsgErrors$lambda$0(NetworkCommand.NetworkCommandBaseDelegate networkCommandBaseDelegate, NetworkCommand.Response response, ErrorHandler errorHandler, int i10, JSONObject body, boolean z10) throws JSONException {
        JSONArray jSONArray;
        Intrinsics.checkNotNullParameter(body, "body");
        if (i10 == 500) {
            CommandStatus<?> commandStatusOnBadRequest = networkCommandBaseDelegate.onBadRequest(new JSONObject(response.getRespString()));
            return Intrinsics.areEqual(commandStatusOnBadRequest.getClass(), MailCommandStatus.FAILED_BACKEND_QUOTE.class) ? commandStatusOnBadRequest : new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getWrongEmail());
        }
        if (i10 != 403 && i10 > 400 && i10 < 600) {
            return new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getWrongEmail());
        }
        if (i10 != 400) {
            return null;
        }
        if (body.has(TAG.CORRESPONDENTS_TO)) {
            jSONArray = body.getJSONArray(TAG.CORRESPONDENTS_TO);
        } else if (body.has("correspondents.cc")) {
            jSONArray = body.getJSONArray("correspondents.cc");
        } else {
            jSONArray = body.has("correspondents.bcc") ? body.getJSONArray("correspondents.bcc") : null;
        }
        if (jSONArray != null) {
            return errorHandler.getSendMessageStatusCommand(jSONArray);
        }
        if (!body.has("send_date")) {
            return body.has(TAG.MBOX_QUOTAS_SEND_LIMIT_EXCEEDED) ? new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getSendLimitExceeded()) : new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getWrongEmail());
        }
        JSONObject jSONObject = body.getJSONObject("send_date");
        if (jSONObject.has("error") && jSONObject.has("value") && Intrinsics.areEqual(jSONObject.getString("error"), "invalid")) {
            return MailCommandStatus.SimpleErrorStatusFactory.INVALID_SEND_DATE.getStatus(errorHandler.stringProvider.getSendDateError());
        }
        return null;
    }

    private final CommandStatus<?> handleTornadoUploadRequestErrors(NetworkCommand.Response response, NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate) {
        return provideStatusAndBody(response, customDelegate, new Function3() { // from class: ru.mail.data.cmd.server.f
            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return ErrorHandler.handleTornadoUploadRequestErrors$lambda$0(this.f85207a, ((Integer) obj).intValue(), (JSONObject) obj2, ((Boolean) obj3).booleanValue());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final CommandStatus handleTornadoUploadRequestErrors$lambda$0(ErrorHandler errorHandler, int i10, JSONObject body, boolean z10) {
        Intrinsics.checkNotNullParameter(body, "body");
        if (!z10 && i10 == 400) {
            if (body.has("file")) {
                if (body.getJSONObject("file").has("error")) {
                    return new CommandStatus.SIMPLE_ERROR(errorHandler.stringProvider.getAttachmentsTooBigQuota(UtilExtensionsKt.byteToMb(26214400L)));
                }
                return null;
            }
            if (body.has(TAG.MBOX_QUOTAS_ATTACH)) {
                long jOptLong = body.getJSONObject(TAG.MBOX_QUOTAS_ATTACH).optLong("value", 0L);
                return new CommandStatus.SIMPLE_ERROR(jOptLong > 0 ? errorHandler.stringProvider.getAttachmentsTooBigQuota(UtilExtensionsKt.kbToMb(jOptLong)) : errorHandler.stringProvider.getAttachmentsDisable());
            }
        }
        return null;
    }

    private final CommandStatus<?> provideStatusAndBody(NetworkCommand.Response response, NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate, Function3<? super Integer, ? super JSONObject, ? super Boolean, ? extends CommandStatus<?>> block) {
        try {
            if (response.getStatusCode() != 200) {
                return null;
            }
            String responseStatus = customDelegate.getResponseStatus(response.getRespString());
            Intrinsics.checkNotNullExpressionValue(responseStatus, "getResponseStatus(...)");
            int i10 = Integer.parseInt(responseStatus);
            Result body = getBody(response);
            JSONObject body2 = body.getBody();
            if (body2 != null) {
                return block.invoke(Integer.valueOf(i10), body2, Boolean.valueOf(body.getBodyWithError()));
            }
            return null;
        } catch (JSONException e10) {
            this.log.e("parsing json error", e10);
            return null;
        }
    }

    @Nullable
    public final CommandStatus<?> execute(@NotNull EnumErrorHandlerName enumErrorClass, @NotNull NetworkCommand.Response response, @NotNull NetworkCommand<?, ?>.NetworkCommandBaseDelegate customDelegate) {
        Intrinsics.checkNotNullParameter(enumErrorClass, "enumErrorClass");
        Intrinsics.checkNotNullParameter(response, "response");
        Intrinsics.checkNotNullParameter(customDelegate, "customDelegate");
        int i10 = WhenMappings.$EnumSwitchMapping$0[enumErrorClass.ordinal()];
        if (i10 == 1) {
            return handleTornadoUploadRequestErrors(response, customDelegate);
        }
        if (i10 == 2) {
            return handleAddToCloudBundleErrors(response, customDelegate);
        }
        if (i10 == 3) {
            return handleTornadoSendMsgErrors(response, customDelegate);
        }
        throw new NoWhenBranchMatchedException();
    }
}
