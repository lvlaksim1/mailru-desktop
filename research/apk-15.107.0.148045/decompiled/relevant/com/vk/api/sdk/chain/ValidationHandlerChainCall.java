package com.vk.api.sdk.chain;

import com.google.android.gms.ads.RequestConfiguration;
import com.vk.api.sdk.VKApiCredentials;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKApiValidationHandler;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u0004\u0018\u00018\u00002\u0006\u0010\u0010\u001a\u00020\u0011H\u0016¢\u0006\u0002\u0010\u0012J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0010\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u001a\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u0015\u001a\u00020\u0016H\u0004J\u0018\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u0018\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001c\u0010\u001f\u001a\u00020\u001e2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0002J^\u0010#\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0001\u0010\u0001\"\u0004\b\u0002\u0010$\"\u0004\b\u0003\u0010%2\u0006\u0010&\u001a\u0002H%2\b\u0010'\u001a\u0004\u0018\u0001H$2)\u0010(\u001a%\u0012\u0004\u0012\u0002H$\u0012\u0004\u0012\u0002H%\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010*\u0012\u0004\u0012\u00020\u00140)¢\u0006\u0002\b+H\u0004¢\u0006\u0002\u0010,J\u001c\u0010-\u001a\u00020\u00142\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0002J\u0016\u0010.\u001a\b\u0012\u0004\u0012\u0002000/2\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lcom/vk/api/sdk/chain/ValidationHandlerChainCall;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "Lcom/vk/api/sdk/chain/RetryChainCall;", "manager", "Lcom/vk/api/sdk/VKApiManager;", "retryLimit", "", "chain", "Lcom/vk/api/sdk/chain/ChainCall;", "validationLock", "Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;", "<init>", "(Lcom/vk/api/sdk/VKApiManager;ILcom/vk/api/sdk/chain/ChainCall;Lcom/vk/api/sdk/VKApiValidationHandler$ValidationLock;)V", "getChain", "()Lcom/vk/api/sdk/chain/ChainCall;", "call", "args", "Lcom/vk/api/sdk/chain/ChainArgs;", "(Lcom/vk/api/sdk/chain/ChainArgs;)Ljava/lang/Object;", "handleException", "", "ex", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "handleUserConfirmation", "handleValidation", "persistToken", "credentials", "Lcom/vk/api/sdk/VKApiValidationHandler$Credentials;", "handleCaptcha", "getDefaultCaptchaData", "Lcom/vk/api/sdk/VKApiValidationHandler$Captcha;", "getHitmanChallengeCaptchaData", "hitmanChallengeUrl", "", "hitmanChallengeDomain", "awaitValidation", "H", "E", "extra", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "handlerMethod", "Lkotlin/Function3;", "Lcom/vk/api/sdk/VKApiValidationHandler$Callback;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "handleCaptchaSolved", "setCredentialsToApiManager", "", "Lcom/vk/api/sdk/VKApiCredentials;", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nValidationHandlerChainCall.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ValidationHandlerChainCall.kt\ncom/vk/api/sdk/chain/ValidationHandlerChainCall\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,204:1\n360#2,7:205\n1#3:212\n*S KotlinDebug\n*F\n+ 1 ValidationHandlerChainCall.kt\ncom/vk/api/sdk/chain/ValidationHandlerChainCall\n*L\n187#1:205,7\n*E\n"})
public final class ValidationHandlerChainCall<T> extends RetryChainCall<T> {

    @NotNull
    private final ChainCall<T> chain;

    @NotNull
    private final VKApiValidationHandler.ValidationLock validationLock;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public ValidationHandlerChainCall(@NotNull VKApiManager manager, int i10, @NotNull ChainCall<? extends T> chain, @NotNull VKApiValidationHandler.ValidationLock validationLock) {
        super(manager, i10);
        Intrinsics.checkNotNullParameter(manager, "manager");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Intrinsics.checkNotNullParameter(validationLock, "validationLock");
        this.chain = chain;
        this.validationLock = validationLock;
    }

    private final VKApiValidationHandler.Captcha getDefaultCaptchaData(VKApiExecutionException ex, ChainArgs args) {
        return new VKApiValidationHandler.Captcha(ex.getCaptchaImg(), Integer.valueOf(ex.getCaptchaHeight()), Integer.valueOf(ex.getCaptchaWidth()), Double.valueOf(ex.getCaptchaRatio()), ex.getCaptchaIsRefreshEnabled(), ex.getCaptchaSid(), ex.getCaptchaIsSoundCaptchaAvailable(), ex.getCaptchaTrack(), ex.getAccessToken(), ex.getCaptchaRedirectUri(), null, null, args.getRequestDomain(), 3072, null);
    }

    private final VKApiValidationHandler.Captcha getHitmanChallengeCaptchaData(String hitmanChallengeUrl, String hitmanChallengeDomain) {
        return new VKApiValidationHandler.Captcha("", null, null, null, true, "", Boolean.FALSE, null, null, null, hitmanChallengeUrl, hitmanChallengeDomain, null, 4096, null);
    }

    private final void handleCaptcha(VKApiExecutionException ex, ChainArgs args) throws VKApiExecutionException {
        VKApiValidationHandler.CaptchaResult captchaResult = (VKApiValidationHandler.CaptchaResult) awaitValidation(args.getHitmanChallengeUrl() != null ? getHitmanChallengeCaptchaData(args.getHitmanChallengeUrl(), args.getHitmanChallengeDomain()) : getDefaultCaptchaData(ex, args), getManager().getValidationHandler(), ValidationHandlerChainCall$handleCaptcha$captchaResult$1.INSTANCE);
        if (captchaResult == null) {
            throw ex;
        }
        args.setCaptchaSid(ex.getCaptchaSid());
        args.setCaptchaAttempt(ex.getCaptchaAttempt());
        args.setCaptchaTimestamp(ex.getCaptchaTimestamp());
        if (captchaResult.isHitmanChallenge()) {
            args.setCaptchaSuccessToken("");
            args.setCaptchaKey("");
            args.setHitmanChallengeToken(captchaResult.getKey());
        } else if (captchaResult.isNotRobotCaptcha()) {
            args.setCaptchaSuccessToken(String.valueOf(captchaResult.getKey()));
            args.setCaptchaKey("");
            args.setHitmanChallengeToken(null);
        } else {
            args.setCaptchaKey(String.valueOf(captchaResult.getKey()));
            args.setCaptchaSuccessToken("");
            args.setHitmanChallengeToken(null);
        }
        args.setSoundCaptcha(Boolean.valueOf(captchaResult.isSoundCaptcha()));
        args.setCaptchaTrack(ex.getCaptchaTrack());
    }

    private final void handleCaptchaSolved(ChainArgs args, VKApiExecutionException ex) {
        VKApiValidationHandler validationHandler;
        if (args.hasCaptcha()) {
            if ((ex == null || !ex.isCaptchaError()) && (validationHandler = getManager().getValidationHandler()) != null) {
                validationHandler.handleCaptchaSolved();
            }
        }
    }

    static /* synthetic */ void handleCaptchaSolved$default(ValidationHandlerChainCall validationHandlerChainCall, ChainArgs chainArgs, VKApiExecutionException vKApiExecutionException, int i10, Object obj) {
        if ((i10 & 2) != 0) {
            vKApiExecutionException = null;
        }
        validationHandlerChainCall.handleCaptchaSolved(chainArgs, vKApiExecutionException);
    }

    private final void handleException(VKApiExecutionException ex, ChainArgs args) throws Exception {
        handleCaptchaSolved(args, ex);
        if (ex.isCaptchaError()) {
            handleCaptcha(ex, args);
            return;
        }
        if (ex.isValidationRequired()) {
            handleValidation(ex);
            return;
        }
        if (ex.isUserConfirmRequired()) {
            handleUserConfirmation(ex, args);
            return;
        }
        VKApiValidationHandler validationHandler = getManager().getValidationHandler();
        if (validationHandler == null) {
            throw ex;
        }
        validationHandler.tryToHandleException(ex, getManager());
    }

    private final void handleUserConfirmation(VKApiExecutionException ex, ChainArgs args) throws VKApiExecutionException {
        Boolean bool = (Boolean) awaitValidation(ex.getUserConfirmText(), getManager().getValidationHandler(), ValidationHandlerChainCall$handleUserConfirmation$confirmation$1.INSTANCE);
        if (bool == null || Intrinsics.areEqual(bool, Boolean.FALSE)) {
            throw ex;
        }
        args.setUserConfirmed(bool.booleanValue());
    }

    private final void handleValidation(VKApiExecutionException ex) throws VKApiExecutionException {
        persistToken((VKApiValidationHandler.Credentials) awaitValidation(ex.getValidationUrl(), getManager().getValidationHandler(), ValidationHandlerChainCall$handleValidation$credentials$1.INSTANCE), ex);
    }

    private final List<VKApiCredentials> setCredentialsToApiManager(VKApiValidationHandler.Credentials credentials) {
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        listCreateListBuilder.addAll(getManager().getExecutor().getCredentials().getValue());
        Iterator<VKApiCredentials> it = getManager().getExecutor().getCredentials().getValue().iterator();
        int i10 = 0;
        while (true) {
            if (!it.hasNext()) {
                i10 = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next().getUserId(), credentials.getUid())) {
                break;
            }
            i10++;
        }
        if (i10 != -1 && credentials.getToken() != null && credentials.getUid() != null) {
            VKApiCredentials vKApiCredentials = (VKApiCredentials) listCreateListBuilder.get(i10);
            listCreateListBuilder.remove(i10);
            listCreateListBuilder.add(i10, new VKApiCredentials(credentials.getToken(), credentials.getSecret(), credentials.getExpiresInSec(), credentials.getCreatedMs(), credentials.getUid(), vKApiCredentials.getUtilityTokens()));
        }
        List<VKApiCredentials> listBuild = CollectionsKt.build(listCreateListBuilder);
        getManager().setCredentials(listBuild);
        return listBuild;
    }

    @Nullable
    protected final <T, H, E> T awaitValidation(E extra, @Nullable H handler, @NotNull Function3<? super H, ? super E, ? super VKApiValidationHandler.Callback<T>, Unit> handlerMethod) throws InterruptedException {
        Intrinsics.checkNotNullParameter(handlerMethod, "handlerMethod");
        if (handler == null || !this.validationLock.acquire()) {
            return null;
        }
        VKApiValidationHandler.Callback callback = new VKApiValidationHandler.Callback(this.validationLock);
        handlerMethod.invoke(handler, extra, callback);
        this.validationLock.await();
        return (T) callback.getValue();
    }

    @Override // com.vk.api.sdk.chain.ChainCall
    @Nullable
    public T call(@NotNull ChainArgs args) throws Exception {
        Intrinsics.checkNotNullParameter(args, "args");
        int i10 = 0;
        while (i10 <= getRetryLimit()) {
            int i11 = i10 + 1;
            try {
                this.validationLock.await();
                T tCall = this.chain.call(args);
                handleCaptchaSolved$default(this, args, null, 2, null);
                return tCall;
            } catch (VKApiExecutionException e10) {
                if (!e10.isCaptchaError()) {
                    i10 = i11;
                }
                handleException(e10, args);
            }
        }
        throw new VKApiException("Can't confirm validation due to retry limit!");
    }

    @NotNull
    public final ChainCall<T> getChain() {
        return this.chain;
    }

    protected final void persistToken(@Nullable VKApiValidationHandler.Credentials credentials, @NotNull VKApiExecutionException ex) throws VKApiExecutionException {
        Intrinsics.checkNotNullParameter(ex, "ex");
        if (Intrinsics.areEqual(credentials, VKApiValidationHandler.Credentials.INSTANCE.getEMPTY())) {
            return;
        }
        if (credentials == null || !credentials.getIsValid()) {
            throw ex;
        }
        setCredentialsToApiManager(credentials);
    }
}
