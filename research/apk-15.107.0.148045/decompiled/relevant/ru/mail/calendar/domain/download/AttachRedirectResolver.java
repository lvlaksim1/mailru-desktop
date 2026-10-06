package ru.mail.calendar.domain.download;

import android.accounts.Account;
import com.huawei.hms.support.feature.result.CommonConstant;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.Response;
import ru.mail.auth.am.AccountManagerDelegate;
import ru.mail.calendar.data.network.download.ClockloRedirectResponse;
import ru.mail.calendar.data.network.download.CloudAttachmentRedirectApi;
import ru.mail.calendar.data.network.download.OpenApiRequest;
import ru.mail.calendar.data.network.download.OpenApiResponse;
import ru.mail.calendar.di.CalendarOauthClientId;
import ru.mail.kit.auth.AuthManager;
import ru.mail.kit.auth.account.HostAccountInfo;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u000b\u0018\u0000  2\u00020\u0001:\u0001 B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0001\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ&\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u0011\u0010\u0012J6\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0082@¢\u0006\u0004\b\u0017\u0010\u0018J.\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\tH\u0086@¢\u0006\u0004\b\u001a\u0010\u001bJ.\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\t0\u000e2\u0006\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0082@¢\u0006\u0004\b\u001e\u0010\u001bJ\b\u0010\u001f\u001a\u00020\tH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/calendar/domain/download/AttachRedirectResolver;", "", "redirectApi", "Lru/mail/calendar/data/network/download/CloudAttachmentRedirectApi;", "accountManagerDelegate", "Lru/mail/auth/am/AccountManagerDelegate;", "portalAuthManager", "Lru/mail/kit/auth/AuthManager;", "clientId", "", "cloudWeblinkApiUrl", "<init>", "(Lru/mail/calendar/data/network/download/CloudAttachmentRedirectApi;Lru/mail/auth/am/AccountManagerDelegate;Lru/mail/kit/auth/AuthManager;Ljava/lang/String;Ljava/lang/String;)V", "resolve", "Lkotlin/Result;", "userAgent", "parsedPath", "resolve-0E7RQCE", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestIntermediateURL", CommonConstant.KEY_ACCESS_TOKEN, "refreshAttemptCount", "", "requestIntermediateURL-yxL6bBk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestIntermediateURLWithRetry", "requestIntermediateURLWithRetry-BWLJW6A", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "requestFinalURL", "intermediateURL", "requestFinalURL-BWLJW6A", "getToken", "Companion", "calendar-sdk_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class AttachRedirectResolver {
    private static final int HTTP_FORBIDDEN = 403;
    private static final int HTTP_OK_END = 299;
    private static final int HTTP_OK_START = 200;
    private static final int HTTP_REQUEST_TIMEOUT = 408;
    private static final int HTTP_SERVER_ERROR_END = 599;
    private static final int HTTP_SERVER_ERROR_START = 500;
    private static final int HTTP_TOO_MANY_REQUESTS = 429;
    private static final int HTTP_UNAUTHORIZED = 401;
    private static final int MAX_REQUEST_RETRIES_AFTER_REFRESH = 1;
    private static final int MAX_TOKEN_REFRESH_ATTEMPTS = 1;

    @NotNull
    private final AccountManagerDelegate accountManagerDelegate;

    @NotNull
    private final String clientId;

    @NotNull
    private final String cloudWeblinkApiUrl;

    @NotNull
    private final AuthManager portalAuthManager;

    @NotNull
    private final CloudAttachmentRedirectApi redirectApi;

    public AttachRedirectResolver(@NotNull CloudAttachmentRedirectApi redirectApi, @NotNull AccountManagerDelegate accountManagerDelegate, @NotNull AuthManager portalAuthManager, @CalendarOauthClientId @NotNull String clientId, @NotNull String cloudWeblinkApiUrl) {
        Intrinsics.checkNotNullParameter(redirectApi, "redirectApi");
        Intrinsics.checkNotNullParameter(accountManagerDelegate, "accountManagerDelegate");
        Intrinsics.checkNotNullParameter(portalAuthManager, "portalAuthManager");
        Intrinsics.checkNotNullParameter(clientId, "clientId");
        Intrinsics.checkNotNullParameter(cloudWeblinkApiUrl, "cloudWeblinkApiUrl");
        this.redirectApi = redirectApi;
        this.accountManagerDelegate = accountManagerDelegate;
        this.portalAuthManager = portalAuthManager;
        this.clientId = clientId;
        this.cloudWeblinkApiUrl = cloudWeblinkApiUrl;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    private final String getToken() {
        Account account;
        HostAccountInfo activeAccount = this.portalAuthManager.getActiveAccount();
        Account[] accounts = this.accountManagerDelegate.getAccounts();
        int length = accounts.length;
        int i10 = 0;
        while (true) {
            account = null;
            if (i10 >= length) {
                break;
            }
            Account account2 = accounts[i10];
            if (Intrinsics.areEqual(account2.name, activeAccount != null ? activeAccount.getLogin() : null)) {
                account = account2;
                break;
            }
            i10++;
        }
        if (account == null) {
            throw new IllegalStateException("Token take fail account invalid");
        }
        String userData = this.accountManagerDelegate.getUserData(account, this.clientId + "access_token");
        if (userData != null) {
            return userData;
        }
        throw new IllegalStateException("Token take fail token is null");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX INFO: renamed from: requestFinalURL-BWLJW6A, reason: not valid java name */
    public final Object m14893requestFinalURLBWLJW6A(String str, String str2, String str3, Continuation<? super Result<String>> continuation) {
        AttachRedirectResolver$requestFinalURL$1 attachRedirectResolver$requestFinalURL$1;
        String redirect;
        if (continuation instanceof AttachRedirectResolver$requestFinalURL$1) {
            attachRedirectResolver$requestFinalURL$1 = (AttachRedirectResolver$requestFinalURL$1) continuation;
            int i10 = attachRedirectResolver$requestFinalURL$1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                attachRedirectResolver$requestFinalURL$1.label = i10 - Integer.MIN_VALUE;
            } else {
                attachRedirectResolver$requestFinalURL$1 = new AttachRedirectResolver$requestFinalURL$1(this, continuation);
            }
        } else {
            attachRedirectResolver$requestFinalURL$1 = new AttachRedirectResolver$requestFinalURL$1(this, continuation);
        }
        AttachRedirectResolver$requestFinalURL$1 attachRedirectResolver$requestFinalURL$2 = attachRedirectResolver$requestFinalURL$1;
        Object finalUrl$default = attachRedirectResolver$requestFinalURL$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = attachRedirectResolver$requestFinalURL$2.label;
        try {
            if (i11 == 0) {
                ResultKt.throwOnFailure(finalUrl$default);
                attachRedirectResolver$requestFinalURL$2.L$0 = SpillingKt.nullOutSpilledVariable(str);
                attachRedirectResolver$requestFinalURL$2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                attachRedirectResolver$requestFinalURL$2.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                attachRedirectResolver$requestFinalURL$2.label = 1;
                finalUrl$default = CloudAttachmentRedirectApi.getFinalUrl$default(this.redirectApi, str3, str, "Bearer " + str2, null, attachRedirectResolver$requestFinalURL$2, 8, null);
                if (finalUrl$default == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i11 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(finalUrl$default);
            }
            Response response = (Response) finalUrl$default;
            int iCode = response.code();
            if (200 <= iCode && iCode < 300) {
                ClockloRedirectResponse clockloRedirectResponse = (ClockloRedirectResponse) response.body();
                if (clockloRedirectResponse == null || (redirect = clockloRedirectResponse.getRedirect()) == null || redirect.length() <= 0) {
                    Result.Companion companion = Result.INSTANCE;
                    return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.UNKNOWN.INSTANCE));
                }
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m13123constructorimpl(clockloRedirectResponse.getRedirect());
            }
            if (iCode != 401 && iCode != 403) {
                if (iCode != 408 && iCode != 429) {
                    if (500 > iCode || iCode >= 600) {
                        Result.Companion companion3 = Result.INSTANCE;
                        return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.UNKNOWN.INSTANCE));
                    }
                    Result.Companion companion4 = Result.INSTANCE;
                    return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
                }
                Result.Companion companion5 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
            }
            Result.Companion companion6 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.AUTH.INSTANCE));
        } catch (Exception unused) {
            Result.Companion companion7 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:8:0x0014  */
    /* JADX INFO: renamed from: requestIntermediateURL-yxL6bBk, reason: not valid java name */
    public final Object m14894requestIntermediateURLyxL6bBk(String str, String str2, String str3, int i10, Continuation<? super Result<String>> continuation) {
        AttachRedirectResolver$requestIntermediateURL$1 attachRedirectResolver$requestIntermediateURL$1;
        String url;
        if (continuation instanceof AttachRedirectResolver$requestIntermediateURL$1) {
            attachRedirectResolver$requestIntermediateURL$1 = (AttachRedirectResolver$requestIntermediateURL$1) continuation;
            int i11 = attachRedirectResolver$requestIntermediateURL$1.label;
            if ((i11 & Integer.MIN_VALUE) != 0) {
                attachRedirectResolver$requestIntermediateURL$1.label = i11 - Integer.MIN_VALUE;
            } else {
                attachRedirectResolver$requestIntermediateURL$1 = new AttachRedirectResolver$requestIntermediateURL$1(this, continuation);
            }
        } else {
            attachRedirectResolver$requestIntermediateURL$1 = new AttachRedirectResolver$requestIntermediateURL$1(this, continuation);
        }
        AttachRedirectResolver$requestIntermediateURL$1 attachRedirectResolver$requestIntermediateURL$2 = attachRedirectResolver$requestIntermediateURL$1;
        Object intermediateUrl = attachRedirectResolver$requestIntermediateURL$2.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i12 = attachRedirectResolver$requestIntermediateURL$2.label;
        try {
            if (i12 == 0) {
                ResultKt.throwOnFailure(intermediateUrl);
                OpenApiRequest openApiRequest = new OpenApiRequest(str3);
                attachRedirectResolver$requestIntermediateURL$2.L$0 = SpillingKt.nullOutSpilledVariable(str);
                attachRedirectResolver$requestIntermediateURL$2.L$1 = SpillingKt.nullOutSpilledVariable(str2);
                attachRedirectResolver$requestIntermediateURL$2.L$2 = SpillingKt.nullOutSpilledVariable(str3);
                attachRedirectResolver$requestIntermediateURL$2.I$0 = i10;
                attachRedirectResolver$requestIntermediateURL$2.label = 1;
                intermediateUrl = this.redirectApi.getIntermediateUrl(this.cloudWeblinkApiUrl, str, "Bearer " + str2, openApiRequest, attachRedirectResolver$requestIntermediateURL$2);
                if (intermediateUrl == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i12 != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i10 = attachRedirectResolver$requestIntermediateURL$2.I$0;
                ResultKt.throwOnFailure(intermediateUrl);
            }
            Response response = (Response) intermediateUrl;
            int iCode = response.code();
            if (200 <= iCode && iCode < 300) {
                OpenApiResponse openApiResponse = (OpenApiResponse) response.body();
                if (openApiResponse == null || (url = openApiResponse.getUrl()) == null || url.length() <= 0) {
                    Result.Companion companion = Result.INSTANCE;
                    return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.UNKNOWN.INSTANCE));
                }
                Result.Companion companion2 = Result.INSTANCE;
                return Result.m13123constructorimpl(openApiResponse.getUrl());
            }
            if (iCode == 403) {
                if (i10 < 1) {
                    Result.Companion companion3 = Result.INSTANCE;
                    return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NEED_REFRESH_TOKEN.INSTANCE));
                }
                Result.Companion companion4 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.AUTH.INSTANCE));
            }
            if (iCode == 401) {
                Result.Companion companion5 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.AUTH.INSTANCE));
            }
            if (iCode != 408 && iCode != 429) {
                if (500 > iCode || iCode >= 600) {
                    Result.Companion companion6 = Result.INSTANCE;
                    return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.UNKNOWN.INSTANCE));
                }
                Result.Companion companion7 = Result.INSTANCE;
                return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
            }
            Result.Companion companion8 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
        } catch (Exception unused) {
            Result.Companion companion9 = Result.INSTANCE;
            return Result.m13123constructorimpl(ResultKt.createFailure(AttachSaveError.NETWORK.INSTANCE));
        }
    }

    /* JADX WARN: Code duplicated, block: B:17:0x0071 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:18:0x0072  */
    /* JADX WARN: Code duplicated, block: B:22:0x0081 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:23:0x0083  */
    /* JADX WARN: Code duplicated, block: B:25:0x008e  */
    /* JADX WARN: Code duplicated, block: B:27:0x0093  */
    /* JADX WARN: Code duplicated, block: B:29:0x009e  */
    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:18:0x0072 -> B:19:0x0074). Please report as a decompilation issue!!! */
    /*  JADX ERROR: JadxOverflowException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:59)
        	at jadx.core.utils.ErrorsCounter.error(ErrorsCounter.java:31)
        	at jadx.core.dex.attributes.nodes.NotificationAttrNode.addError(NotificationAttrNode.java:19)
        */
    @org.jetbrains.annotations.Nullable
    /* JADX INFO: renamed from: requestIntermediateURLWithRetry-BWLJW6A, reason: not valid java name */
    public final java.lang.Object m14895requestIntermediateURLWithRetryBWLJW6A(@org.jetbrains.annotations.NotNull java.lang.String r11, @org.jetbrains.annotations.NotNull java.lang.String r12, @org.jetbrains.annotations.NotNull java.lang.String r13, @org.jetbrains.annotations.NotNull kotlin.coroutines.Continuation<? super kotlin.Result<java.lang.String>> r14) {
        /*
            r10 = this;
            boolean r0 = r14 instanceof ru.mail.calendar.domain.download.AttachRedirectResolver$requestIntermediateURLWithRetry$1
            if (r0 == 0) goto L13
            r0 = r14
            ru.mail.calendar.domain.download.AttachRedirectResolver$requestIntermediateURLWithRetry$1 r0 = (ru.mail.calendar.domain.download.AttachRedirectResolver$requestIntermediateURLWithRetry$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.label = r1
            goto L18
        L13:
            ru.mail.calendar.domain.download.AttachRedirectResolver$requestIntermediateURLWithRetry$1 r0 = new ru.mail.calendar.domain.download.AttachRedirectResolver$requestIntermediateURLWithRetry$1
            r0.<init>(r10, r14)
        L18:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L4e
            if (r2 != r3) goto L46
            int r11 = r0.I$1
            int r12 = r0.I$0
            java.lang.Object r13 = r0.L$3
            java.lang.String r13 = (java.lang.String) r13
            java.lang.Object r2 = r0.L$2
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r4 = r0.L$1
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r0.L$0
            java.lang.String r5 = (java.lang.String) r5
            kotlin.ResultKt.throwOnFailure(r14)
            kotlin.Result r14 = (kotlin.Result) r14
            java.lang.Object r14 = r14.getValue()
            r6 = r13
            r9 = r0
            r7 = r2
            goto L74
        L46:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r12 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r12)
            throw r11
        L4e:
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = 0
            r5 = r11
            r6 = r12
            r7 = r13
            r11 = r14
            r8 = r11
            r9 = r0
        L58:
            r9.L$0 = r5
            java.lang.Object r13 = kotlin.coroutines.jvm.internal.SpillingKt.nullOutSpilledVariable(r12)
            r9.L$1 = r13
            r9.L$2 = r7
            r9.L$3 = r6
            r9.I$0 = r8
            r9.I$1 = r11
            r9.label = r3
            r4 = r10
            java.lang.Object r14 = r4.m14894requestIntermediateURLyxL6bBk(r5, r6, r7, r8, r9)
            if (r14 != r1) goto L72
            return r1
        L72:
            r4 = r12
            r12 = r8
        L74:
            java.lang.Throwable r13 = kotlin.Result.m13126exceptionOrNullimpl(r14)
            ru.mail.calendar.domain.download.AttachSaveError$NEED_REFRESH_TOKEN r0 = ru.mail.calendar.domain.download.AttachSaveError.NEED_REFRESH_TOKEN.INSTANCE
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual(r13, r0)
            if (r13 != 0) goto L81
            return r14
        L81:
            if (r12 < r3) goto L8e
            ru.mail.calendar.domain.download.AttachSaveError$AUTH r11 = ru.mail.calendar.domain.download.AttachSaveError.AUTH.INSTANCE
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m13123constructorimpl(r11)
            return r11
        L8e:
            int r8 = r12 + 1
            int r11 = r11 + r3
            if (r11 <= r3) goto L9e
            ru.mail.calendar.domain.download.AttachSaveError$AUTH r11 = ru.mail.calendar.domain.download.AttachSaveError.AUTH.INSTANCE
            java.lang.Object r11 = kotlin.ResultKt.createFailure(r11)
            java.lang.Object r11 = kotlin.Result.m13123constructorimpl(r11)
            return r11
        L9e:
            r12 = r4
            goto L58
        */
        throw new UnsupportedOperationException("Method not decompiled: ru.mail.calendar.domain.download.AttachRedirectResolver.m14895requestIntermediateURLWithRetryBWLJW6A(java.lang.String, java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARN: Code duplicated, block: B:7:0x0013  */
    @Nullable
    /* JADX INFO: renamed from: resolve-0E7RQCE, reason: not valid java name */
    public final Object m14896resolve0E7RQCE(@NotNull String str, @NotNull String str2, @NotNull Continuation<? super Result<String>> continuation) {
        AttachRedirectResolver$resolve$1 attachRedirectResolver$resolve$1;
        String token;
        Object objM14895requestIntermediateURLWithRetryBWLJW6A;
        if (continuation instanceof AttachRedirectResolver$resolve$1) {
            attachRedirectResolver$resolve$1 = (AttachRedirectResolver$resolve$1) continuation;
            int i10 = attachRedirectResolver$resolve$1.label;
            if ((i10 & Integer.MIN_VALUE) != 0) {
                attachRedirectResolver$resolve$1.label = i10 - Integer.MIN_VALUE;
            } else {
                attachRedirectResolver$resolve$1 = new AttachRedirectResolver$resolve$1(this, continuation);
            }
        } else {
            attachRedirectResolver$resolve$1 = new AttachRedirectResolver$resolve$1(this, continuation);
        }
        Object obj = attachRedirectResolver$resolve$1.result;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i11 = attachRedirectResolver$resolve$1.label;
        if (i11 == 0) {
            ResultKt.throwOnFailure(obj);
            token = getToken();
            attachRedirectResolver$resolve$1.L$0 = str;
            attachRedirectResolver$resolve$1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
            attachRedirectResolver$resolve$1.L$2 = token;
            attachRedirectResolver$resolve$1.label = 1;
            objM14895requestIntermediateURLWithRetryBWLJW6A = m14895requestIntermediateURLWithRetryBWLJW6A(str, token, str2, attachRedirectResolver$resolve$1);
            if (objM14895requestIntermediateURLWithRetryBWLJW6A != coroutine_suspended) {
            }
        }
        if (i11 != 1) {
            if (i11 != 2) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return ((Result) obj).getValue();
        }
        String str3 = (String) attachRedirectResolver$resolve$1.L$2;
        str2 = (String) attachRedirectResolver$resolve$1.L$1;
        String str4 = (String) attachRedirectResolver$resolve$1.L$0;
        ResultKt.throwOnFailure(obj);
        Object value = ((Result) obj).getValue();
        token = str3;
        str = str4;
        objM14895requestIntermediateURLWithRetryBWLJW6A = value;
        if (!Result.m13129isSuccessimpl(objM14895requestIntermediateURLWithRetryBWLJW6A)) {
            return objM14895requestIntermediateURLWithRetryBWLJW6A;
        }
        ResultKt.throwOnFailure(objM14895requestIntermediateURLWithRetryBWLJW6A);
        String str5 = (String) objM14895requestIntermediateURLWithRetryBWLJW6A;
        attachRedirectResolver$resolve$1.L$0 = SpillingKt.nullOutSpilledVariable(str);
        attachRedirectResolver$resolve$1.L$1 = SpillingKt.nullOutSpilledVariable(str2);
        attachRedirectResolver$resolve$1.L$2 = SpillingKt.nullOutSpilledVariable(token);
        attachRedirectResolver$resolve$1.L$3 = SpillingKt.nullOutSpilledVariable(objM14895requestIntermediateURLWithRetryBWLJW6A);
        attachRedirectResolver$resolve$1.L$4 = SpillingKt.nullOutSpilledVariable(str5);
        attachRedirectResolver$resolve$1.label = 2;
        Object objM14893requestFinalURLBWLJW6A = m14893requestFinalURLBWLJW6A(str, token, str5, attachRedirectResolver$resolve$1);
        return objM14893requestFinalURLBWLJW6A == coroutine_suspended ? coroutine_suspended : objM14893requestFinalURLBWLJW6A;
    }
}
