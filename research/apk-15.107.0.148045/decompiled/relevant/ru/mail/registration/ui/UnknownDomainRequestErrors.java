package ru.mail.registration.ui;

import android.content.Context;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.Authenticator.R;
import ru.mail.auth.request.MailServerParametersRequest;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010H\u0007J\u001e\u0010\u0012\u001a\u00020\u00052\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0014\u001a\u00020\u00112\u0006\u0010\n\u001a\u00020\u000bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lru/mail/registration/ui/UnknownDomainRequestErrors;", "", "<init>", "()V", "COLLECT_AUTH_FAIL", "", "SMTP_AUTH_FAIL", "COLLECT_TIMEOUT", "SMTP_TIMEOUT", "getErrorMessage", "context", "Landroid/content/Context;", "errorCode", "", "errorBody", "invalidFields", "", "Lru/mail/auth/request/MailServerParametersRequest$InvalidFieldName;", "getFields", "getFieldName", "field", "authenticator_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUnknownDomainRequestErrors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnknownDomainRequestErrors.kt\nru/mail/registration/ui/UnknownDomainRequestErrors\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,84:1\n1617#2,9:85\n1869#2:94\n1870#2:96\n1626#2:97\n1#3:95\n*S KotlinDebug\n*F\n+ 1 UnknownDomainRequestErrors.kt\nru/mail/registration/ui/UnknownDomainRequestErrors\n*L\n65#1:85,9\n65#1:94\n65#1:96\n65#1:97\n65#1:95\n*E\n"})
public final class UnknownDomainRequestErrors {

    @NotNull
    private static final String COLLECT_AUTH_FAIL = "collect_auth_fail";

    @NotNull
    private static final String COLLECT_TIMEOUT = "collect_timeout";

    @NotNull
    public static final UnknownDomainRequestErrors INSTANCE = new UnknownDomainRequestErrors();

    @NotNull
    private static final String SMTP_AUTH_FAIL = "smtp_auth_fail";

    @NotNull
    private static final String SMTP_TIMEOUT = "smtp_timeout";

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MailServerParametersRequest.InvalidFieldName.values().length];
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.COLLECT_SERVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.COLLECT_PORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.SMTP_SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.SMTP_PORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[MailServerParametersRequest.InvalidFieldName.CODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    private UnknownDomainRequestErrors() {
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @NonNull
    @JvmStatic
    @NotNull
    public static final String getErrorMessage(@NotNull Context context, int errorCode, @Nullable String errorBody, @Nullable List<? extends MailServerParametersRequest.InvalidFieldName> invalidFields) {
        Intrinsics.checkNotNullParameter(context, "context");
        if (errorCode == 400) {
            if (invalidFields == null || invalidFields.isEmpty()) {
                String string = context.getString(R.string.error_code_unknow_domain_400);
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                return string;
            }
            if (invalidFields.size() == 1) {
                return context.getString(R.string.error_code_unknow_domain_400_single) + StringUtils.SPACE + INSTANCE.getFields(invalidFields, context);
            }
            if ((invalidFields.contains(MailServerParametersRequest.InvalidFieldName.COLLECT_SERVER) && invalidFields.contains(MailServerParametersRequest.InvalidFieldName.SMTP_SERVER)) || (invalidFields.contains(MailServerParametersRequest.InvalidFieldName.COLLECT_PORT) && invalidFields.contains(MailServerParametersRequest.InvalidFieldName.SMTP_PORT))) {
                String string2 = context.getString(R.string.error_code_unknow_domain_400);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
                return string2;
            }
            if (invalidFields.size() >= 4) {
                String string3 = context.getString(R.string.error_code_unknow_domain_400);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(...)");
                return string3;
            }
            return context.getString(R.string.error_code_unknow_domain_400_plural) + StringUtils.SPACE + INSTANCE.getFields(invalidFields, context);
        }
        if (errorCode == 404) {
            String string4 = context.getString(R.string.error_code_unknow_domain_404);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(...)");
            return string4;
        }
        if (errorCode == 408) {
            String string5 = context.getString(R.string.error_code_unknow_domain_408);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(...)");
            return string5;
        }
        if (errorCode == 429) {
            String string6 = context.getString(R.string.error_code_unknow_domain_429);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(...)");
            return string6;
        }
        if (errorCode != 500) {
            if (errorCode != 503) {
                String string7 = context.getString(R.string.error_code_unknow_domain_500_unknow);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(...)");
                return string7;
            }
            String string8 = context.getString(R.string.error_code_unknow_domain_503);
            Intrinsics.checkNotNullExpressionValue(string8, "getString(...)");
            return string8;
        }
        if (errorBody != null) {
            switch (errorBody.hashCode()) {
                case -1555664724:
                    if (errorBody.equals(SMTP_AUTH_FAIL)) {
                        String string9 = context.getString(R.string.error_code_unknow_domain_500_smtp_auth_fail);
                        Intrinsics.checkNotNullExpressionValue(string9, "getString(...)");
                        return string9;
                    }
                    break;
                case -1482412936:
                    if (errorBody.equals(SMTP_TIMEOUT)) {
                        String string10 = context.getString(R.string.error_code_unknow_domain_500_smtp_timeout);
                        Intrinsics.checkNotNullExpressionValue(string10, "getString(...)");
                        return string10;
                    }
                    break;
                case -1150981504:
                    if (errorBody.equals(COLLECT_AUTH_FAIL)) {
                        String string11 = context.getString(R.string.error_code_unknow_domain_500_collect_auth_fail);
                        Intrinsics.checkNotNullExpressionValue(string11, "getString(...)");
                        return string11;
                    }
                    break;
                case 824150860:
                    if (errorBody.equals(COLLECT_TIMEOUT)) {
                        String string12 = context.getString(R.string.error_code_unknow_domain_500_collect_timeout);
                        Intrinsics.checkNotNullExpressionValue(string12, "getString(...)");
                        return string12;
                    }
                    break;
            }
        }
        String string13 = context.getString(R.string.error_code_unknow_domain_500_unknow);
        Intrinsics.checkNotNullExpressionValue(string13, "getString(...)");
        return string13;
    }

    private final String getFieldName(MailServerParametersRequest.InvalidFieldName field, Context context) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[field.ordinal()];
        if (i10 == 1) {
            return context.getString(R.string.error_code_unknow_domain_field_host);
        }
        if (i10 == 2) {
            return context.getString(R.string.error_code_unknow_domain_field_port);
        }
        if (i10 == 3) {
            return context.getString(R.string.error_code_unknow_domain_field_host);
        }
        if (i10 == 4) {
            return context.getString(R.string.error_code_unknow_domain_field_port);
        }
        if (i10 != 5) {
            return null;
        }
        return context.getString(R.string.error_code_unknow_domain_field_captcha);
    }

    private final String getFields(List<? extends MailServerParametersRequest.InvalidFieldName> invalidFields, Context context) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = invalidFields.iterator();
        while (it.hasNext()) {
            String fieldName = INSTANCE.getFieldName((MailServerParametersRequest.InvalidFieldName) it.next(), context);
            if (fieldName != null) {
                arrayList.add(fieldName);
            }
        }
        return CollectionsKt.joinToString$default(arrayList, AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, null, null, 0, null, null, 62, null);
    }
}
