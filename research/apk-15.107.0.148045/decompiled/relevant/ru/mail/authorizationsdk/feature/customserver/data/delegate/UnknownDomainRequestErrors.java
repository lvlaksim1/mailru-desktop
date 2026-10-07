package ru.mail.authorizationsdk.feature.customserver.data.delegate;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.android_utils.wrapper.Resources;
import ru.mail.authorizationsdk.R;
import ru.mail.authorizationsdk.feature.customserver.domain.model.InvalidFieldName;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u00072\u0010\b\u0002\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\fJ\u0016\u0010\u000e\u001a\u00020\u00072\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/authorizationsdk/feature/customserver/data/delegate/UnknownDomainRequestErrors;", "", "resources", "Lru/mail/android_utils/wrapper/Resources;", "<init>", "(Lru/mail/android_utils/wrapper/Resources;)V", "getErrorMessage", "", "errorCode", "", "errorBody", "invalidFields", "", "Lru/mail/authorizationsdk/feature/customserver/domain/model/InvalidFieldName;", "getFields", "getFieldName", "field", "Companion", "authorizationsdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUnknownDomainRequestErrors.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UnknownDomainRequestErrors.kt\nru/mail/authorizationsdk/feature/customserver/data/delegate/UnknownDomainRequestErrors\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,80:1\n1617#2,9:81\n1869#2:90\n1870#2:92\n1626#2:93\n1#3:91\n*S KotlinDebug\n*F\n+ 1 UnknownDomainRequestErrors.kt\nru/mail/authorizationsdk/feature/customserver/data/delegate/UnknownDomainRequestErrors\n*L\n61#1:81,9\n61#1:90\n61#1:92\n61#1:93\n61#1:91\n*E\n"})
public final class UnknownDomainRequestErrors {

    @NotNull
    private static final String COLLECT_AUTH_FAIL = "collect_auth_fail";

    @NotNull
    private static final String COLLECT_TIMEOUT = "collect_timeout";

    @NotNull
    private static final String SMTP_AUTH_FAIL = "smtp_auth_fail";

    @NotNull
    private static final String SMTP_TIMEOUT = "smtp_timeout";

    @NotNull
    private final Resources resources;
    public static final int $stable = 8;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[InvalidFieldName.values().length];
            try {
                iArr[InvalidFieldName.COLLECT_SERVER.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[InvalidFieldName.COLLECT_PORT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[InvalidFieldName.SMTP_SERVER.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[InvalidFieldName.SMTP_PORT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[InvalidFieldName.CODE.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public UnknownDomainRequestErrors(@NotNull Resources resources) {
        Intrinsics.checkNotNullParameter(resources, "resources");
        this.resources = resources;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ String getErrorMessage$default(UnknownDomainRequestErrors unknownDomainRequestErrors, int i10, String str, List list, int i11, Object obj) {
        if ((i11 & 4) != 0) {
            list = null;
        }
        return unknownDomainRequestErrors.getErrorMessage(i10, str, list);
    }

    private final String getFieldName(InvalidFieldName field) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[field.ordinal()];
        if (i10 == 1) {
            return this.resources.getString(R.string.error_code_unknow_domain_field_host);
        }
        if (i10 == 2) {
            return this.resources.getString(R.string.error_code_unknow_domain_field_port);
        }
        if (i10 == 3) {
            return this.resources.getString(R.string.error_code_unknow_domain_field_host);
        }
        if (i10 == 4) {
            return this.resources.getString(R.string.error_code_unknow_domain_field_port);
        }
        if (i10 != 5) {
            return null;
        }
        return this.resources.getString(R.string.error_code_unknow_domain_field_captcha);
    }

    private final String getFields(List<? extends InvalidFieldName> invalidFields) {
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = invalidFields.iterator();
        while (it.hasNext()) {
            String fieldName = getFieldName((InvalidFieldName) it.next());
            if (fieldName != null) {
                arrayList.add(fieldName);
            }
        }
        return CollectionsKt.joinToString$default(arrayList, AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER, null, null, 0, null, null, 62, null);
    }

    /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue */
    @NotNull
    public final String getErrorMessage(int errorCode, @Nullable String errorBody, @Nullable List<? extends InvalidFieldName> invalidFields) {
        if (errorCode != 400) {
            if (errorCode == 404) {
                return this.resources.getString(R.string.error_code_unknow_domain_404);
            }
            if (errorCode == 408) {
                return this.resources.getString(R.string.error_code_unknow_domain_408);
            }
            if (errorCode == 429) {
                return this.resources.getString(R.string.error_code_unknow_domain_429);
            }
            if (errorCode != 500) {
                return errorCode != 503 ? this.resources.getString(R.string.error_code_unknow_domain_500_unknow) : this.resources.getString(R.string.error_code_unknow_domain_503);
            }
            if (errorBody != null) {
                switch (errorBody.hashCode()) {
                    case -1555664724:
                        if (errorBody.equals(SMTP_AUTH_FAIL)) {
                            return this.resources.getString(R.string.error_code_unknow_domain_500_smtp_auth_fail);
                        }
                        break;
                    case -1482412936:
                        if (errorBody.equals(SMTP_TIMEOUT)) {
                            return this.resources.getString(R.string.error_code_unknow_domain_500_smtp_timeout);
                        }
                        break;
                    case -1150981504:
                        if (errorBody.equals(COLLECT_AUTH_FAIL)) {
                            return this.resources.getString(R.string.error_code_unknow_domain_500_collect_auth_fail);
                        }
                        break;
                    case 824150860:
                        if (errorBody.equals(COLLECT_TIMEOUT)) {
                            return this.resources.getString(R.string.error_code_unknow_domain_500_collect_timeout);
                        }
                        break;
                }
            }
            return this.resources.getString(R.string.error_code_unknow_domain_500_unknow);
        }
        List<? extends InvalidFieldName> list = invalidFields;
        if (list == null || list.isEmpty()) {
            return this.resources.getString(R.string.error_code_unknow_domain_400);
        }
        if (invalidFields.size() == 1) {
            return this.resources.getString(R.string.error_code_unknow_domain_400_single) + StringUtils.SPACE + getFields(invalidFields);
        }
        if ((invalidFields.contains(InvalidFieldName.COLLECT_SERVER) && invalidFields.contains(InvalidFieldName.SMTP_SERVER)) || (invalidFields.contains(InvalidFieldName.COLLECT_PORT) && invalidFields.contains(InvalidFieldName.SMTP_PORT))) {
            return this.resources.getString(R.string.error_code_unknow_domain_400);
        }
        if (invalidFields.size() >= 4) {
            return this.resources.getString(R.string.error_code_unknow_domain_400);
        }
        return this.resources.getString(R.string.error_code_unknow_domain_400_plural) + StringUtils.SPACE + getFields(invalidFields);
    }
}
