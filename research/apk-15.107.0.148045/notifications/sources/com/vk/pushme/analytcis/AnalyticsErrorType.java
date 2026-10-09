package com.vk.pushme.analytcis;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes19.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/vk/pushme/analytcis/AnalyticsErrorType;", "", "<init>", "()V", AnalyticsErrorType.DELETE_TOKEN_ERROR, "", AnalyticsErrorType.PUSH_RECEIVED_ERROR, AnalyticsErrorType.PAYLOAD_PARSE_ERROR, AnalyticsErrorType.SEND_API_ANALYTIC_ERROR, "NO_PUSH_TOKEN_FOUND_ERROR", AnalyticsErrorType.MISSING_DEVICE_ID_ERROR, AnalyticsErrorType.MISSING_ANDROID_ID_ERROR, AnalyticsErrorType.INVALID_ACCOUNT_ERROR, AnalyticsErrorType.SERVER_ERROR, AnalyticsErrorType.TRANSPORT_PARSE_ERROR, AnalyticsErrorType.UNKNOWN_ERROR, "push-me-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AnalyticsErrorType {

    @NotNull
    public static final String DELETE_TOKEN_ERROR = "DELETE_TOKEN_ERROR";

    @NotNull
    public static final AnalyticsErrorType INSTANCE = new AnalyticsErrorType();

    @NotNull
    public static final String INVALID_ACCOUNT_ERROR = "INVALID_ACCOUNT_ERROR";

    @NotNull
    public static final String MISSING_ANDROID_ID_ERROR = "MISSING_ANDROID_ID_ERROR";

    @NotNull
    public static final String MISSING_DEVICE_ID_ERROR = "MISSING_DEVICE_ID_ERROR";

    @NotNull
    public static final String NO_PUSH_TOKEN_FOUND_ERROR = "NO_PUSH_TOKEN_FOUND";

    @NotNull
    public static final String PAYLOAD_PARSE_ERROR = "PAYLOAD_PARSE_ERROR";

    @NotNull
    public static final String PUSH_RECEIVED_ERROR = "PUSH_RECEIVED_ERROR";

    @NotNull
    public static final String SEND_API_ANALYTIC_ERROR = "SEND_API_ANALYTIC_ERROR";

    @NotNull
    public static final String SERVER_ERROR = "SERVER_ERROR";

    @NotNull
    public static final String TRANSPORT_PARSE_ERROR = "TRANSPORT_PARSE_ERROR";

    @NotNull
    public static final String UNKNOWN_ERROR = "UNKNOWN_ERROR";

    private AnalyticsErrorType() {
    }
}
