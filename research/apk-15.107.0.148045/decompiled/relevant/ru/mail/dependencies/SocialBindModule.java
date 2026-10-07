package ru.mail.dependencies;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.config.Configuration;
import ru.mail.credentialsexchanger.core.CredentialsExchanger;
import ru.mail.logic.auth.SuperAppKitIds;
import ru.mail.logic.cmd.socialbind.LudwigTokensApi;
import ru.mail.logic.cmd.socialbind.SocialBindAddApi;
import ru.mail.logic.cmd.socialbind.VerificationPasswordCheckApi;
import ru.mail.logic.cmd.socialbind.VkPreflightApi;
import ru.mail.logic.content.DataManager;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.AddSocialBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.BindVKIDNewFlowUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.BindVKIDUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLocalDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetLudwigTokenUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetMailDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetVKIDDataForVKIDBindUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.GetVKPreflightUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VKIDBindPromoInteractorImpl;
import ru.mail.ui.auth.universal.vkidbindpromo.implementations.VerifyPasswordCheckUseCase;
import ru.mail.ui.auth.universal.vkidbindpromo.interfaces.VKIDBindPromoInteractor;
import ru.mail.util.vk_account.VkAccountProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0001\u0010\b\u001a\u00020\t2\b\b\u0001\u0010\n\u001a\u00020\u000b2\b\b\u0001\u0010\f\u001a\u00020\rH\u0007J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J$\u0010\u001a\u001a\u00020\t2\b\b\u0001\u0010\u001b\u001a\u00020\u000f2\b\b\u0001\u0010\u001c\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0007JB\u0010 \u001a\u00020\r2\b\b\u0001\u0010!\u001a\u00020\"2\u0006\u0010\u0014\u001a\u00020\u00152\b\b\u0001\u0010#\u001a\u00020$2\b\b\u0001\u0010%\u001a\u00020&2\b\b\u0001\u0010'\u001a\u00020(2\b\b\u0001\u0010)\u001a\u00020*H\u0007J\u0018\u0010+\u001a\u00020$2\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/H\u0007J\u0018\u00100\u001a\u00020&2\u0006\u0010,\u001a\u00020-2\u0006\u00101\u001a\u000202H\u0007J\u0018\u00103\u001a\u00020(2\u0006\u0010,\u001a\u00020-2\u0006\u00104\u001a\u000205H\u0007J\u0018\u00106\u001a\u00020*2\u0006\u0010,\u001a\u00020-2\u0006\u00107\u001a\u000208H\u0007¨\u00069"}, d2 = {"Lru/mail/dependencies/SocialBindModule;", "", "<init>", "()V", "provideVKIDBindInteractor", "Lru/mail/ui/auth/universal/vkidbindpromo/interfaces/VKIDBindPromoInteractor;", "mailAppAnalytics", "Lru/mail/analytics/MailAppAnalytics;", "getLocalDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLocalDataForVKIDBindUseCase;", "bindVKIDUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDUseCase;", "bindVKIDNewFlowUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/BindVKIDNewFlowUseCase;", "provideGetMailDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetMailDataForVKIDBindUseCase;", "dataManager", "Lru/mail/logic/content/DataManager;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "configuration", "Lru/mail/config/Configuration;", "provideGetVKIDDataForVKIDBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKIDDataForVKIDBindUseCase;", "vkAccountProvider", "Lru/mail/util/vk_account/VkAccountProvider;", "provideGetLocalDataForVKIDBindUseCase", "getMailDataForVKIDBindUseCase", "getVKIDDataForVKIDBindUseCase", "provideBindVKIDUseCase", "credentialsExchanger", "Lru/mail/credentialsexchanger/core/CredentialsExchanger;", "provideBindVKIDNewFlowUseCase", "context", "Landroid/content/Context;", "getLudwigTokenUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetLudwigTokenUseCase;", "verifyPasswordCheckUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/VerifyPasswordCheckUseCase;", "getVKPreflightUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/GetVKPreflightUseCase;", "addSocialBindUseCase", "Lru/mail/ui/auth/universal/vkidbindpromo/implementations/AddSocialBindUseCase;", "provideGetLudwigTokenUseCase", "executorSelector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "ludwigTokensApi", "Lru/mail/logic/cmd/socialbind/LudwigTokensApi;", "provideVerifyPasswordCheckUseCase", "verificationPasswordCheckApi", "Lru/mail/logic/cmd/socialbind/VerificationPasswordCheckApi;", "provideGetVKPreflightUseCase", "preflightApi", "Lru/mail/logic/cmd/socialbind/VkPreflightApi;", "provideAddSocialBindUseCase", "socialBindAddApi", "Lru/mail/logic/cmd/socialbind/SocialBindAddApi;", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@Module
@InstallIn({SingletonComponent.class})
public final class SocialBindModule {
    public static final int $stable = 0;

    @NotNull
    public static final SocialBindModule INSTANCE = new SocialBindModule();

    private SocialBindModule() {
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final AddSocialBindUseCase provideAddSocialBindUseCase(@NotNull ExecutorSelector executorSelector, @NotNull SocialBindAddApi socialBindAddApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(socialBindAddApi, "socialBindAddApi");
        return new AddSocialBindUseCase(executorSelector, socialBindAddApi);
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final BindVKIDNewFlowUseCase provideBindVKIDNewFlowUseCase(@ApplicationContext @NotNull Context context, @NotNull Configuration configuration, @VKIDRatBindPush @NotNull GetLudwigTokenUseCase getLudwigTokenUseCase, @VKIDRatBindPush @NotNull VerifyPasswordCheckUseCase verifyPasswordCheckUseCase, @VKIDRatBindPush @NotNull GetVKPreflightUseCase getVKPreflightUseCase, @VKIDRatBindPush @NotNull AddSocialBindUseCase addSocialBindUseCase) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(getLudwigTokenUseCase, "getLudwigTokenUseCase");
        Intrinsics.checkNotNullParameter(verifyPasswordCheckUseCase, "verifyPasswordCheckUseCase");
        Intrinsics.checkNotNullParameter(getVKPreflightUseCase, "getVKPreflightUseCase");
        Intrinsics.checkNotNullParameter(addSocialBindUseCase, "addSocialBindUseCase");
        return new BindVKIDNewFlowUseCase(configuration, getLudwigTokenUseCase, verifyPasswordCheckUseCase, getVKPreflightUseCase, addSocialBindUseCase, SuperAppKitIds.INSTANCE.getClientId(context));
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final BindVKIDUseCase provideBindVKIDUseCase(@NotNull CredentialsExchanger credentialsExchanger) {
        Intrinsics.checkNotNullParameter(credentialsExchanger, "credentialsExchanger");
        return new BindVKIDUseCase(credentialsExchanger, true);
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final GetLocalDataForVKIDBindUseCase provideGetLocalDataForVKIDBindUseCase(@VKIDRatBindPush @NotNull GetMailDataForVKIDBindUseCase getMailDataForVKIDBindUseCase, @VKIDRatBindPush @NotNull GetVKIDDataForVKIDBindUseCase getVKIDDataForVKIDBindUseCase, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(getMailDataForVKIDBindUseCase, "getMailDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(getVKIDDataForVKIDBindUseCase, "getVKIDDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetLocalDataForVKIDBindUseCase(getMailDataForVKIDBindUseCase, getVKIDDataForVKIDBindUseCase, configuration.getRatBindPushConfig().getGetDataTimeout());
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final GetLudwigTokenUseCase provideGetLudwigTokenUseCase(@NotNull ExecutorSelector executorSelector, @NotNull LudwigTokensApi ludwigTokensApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(ludwigTokensApi, "ludwigTokensApi");
        return new GetLudwigTokenUseCase(executorSelector, ludwigTokensApi);
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final GetMailDataForVKIDBindUseCase provideGetMailDataForVKIDBindUseCase(@NotNull DataManager dataManager, @NotNull AccountManagerWrapper accountManager, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(dataManager, "dataManager");
        Intrinsics.checkNotNullParameter(accountManager, "accountManager");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetMailDataForVKIDBindUseCase(dataManager, accountManager, configuration.getRatBindPushConfig().getPasswordCheckingEnabled());
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final GetVKIDDataForVKIDBindUseCase provideGetVKIDDataForVKIDBindUseCase(@NotNull VkAccountProvider vkAccountProvider, @NotNull Configuration configuration) {
        Intrinsics.checkNotNullParameter(vkAccountProvider, "vkAccountProvider");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        return new GetVKIDDataForVKIDBindUseCase(vkAccountProvider, configuration.getRatBindPushConfig().getEverywhereSilentInfoEnabled());
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final GetVKPreflightUseCase provideGetVKPreflightUseCase(@NotNull ExecutorSelector executorSelector, @NotNull VkPreflightApi preflightApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(preflightApi, "preflightApi");
        return new GetVKPreflightUseCase(executorSelector, preflightApi);
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final VKIDBindPromoInteractor provideVKIDBindInteractor(@NotNull MailAppAnalytics mailAppAnalytics, @VKIDRatBindPush @NotNull GetLocalDataForVKIDBindUseCase getLocalDataForVKIDBindUseCase, @VKIDRatBindPush @NotNull BindVKIDUseCase bindVKIDUseCase, @VKIDRatBindPush @NotNull BindVKIDNewFlowUseCase bindVKIDNewFlowUseCase) {
        Intrinsics.checkNotNullParameter(mailAppAnalytics, "mailAppAnalytics");
        Intrinsics.checkNotNullParameter(getLocalDataForVKIDBindUseCase, "getLocalDataForVKIDBindUseCase");
        Intrinsics.checkNotNullParameter(bindVKIDUseCase, "bindVKIDUseCase");
        Intrinsics.checkNotNullParameter(bindVKIDNewFlowUseCase, "bindVKIDNewFlowUseCase");
        return new VKIDBindPromoInteractorImpl(mailAppAnalytics, getLocalDataForVKIDBindUseCase, bindVKIDUseCase, bindVKIDNewFlowUseCase);
    }

    @Provides
    @VKIDRatBindPush
    @NotNull
    public final VerifyPasswordCheckUseCase provideVerifyPasswordCheckUseCase(@NotNull ExecutorSelector executorSelector, @NotNull VerificationPasswordCheckApi verificationPasswordCheckApi) {
        Intrinsics.checkNotNullParameter(executorSelector, "executorSelector");
        Intrinsics.checkNotNullParameter(verificationPasswordCheckApi, "verificationPasswordCheckApi");
        return new VerifyPasswordCheckUseCase(executorSelector, verificationPasswordCheckApi);
    }
}
