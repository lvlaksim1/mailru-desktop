package ru.mail.util.push.pusher.network;

import android.content.Context;
import androidx.compose.runtime.internal.StabilityInferred;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.data.cmd.server.ad.RbParams;
import ru.mail.data.cmd.server.pusher.pushme.PushMeCheckPushTokenCommand;
import ru.mail.data.cmd.server.pusher.pushme.PushMeRemovePushSettingsCmd;
import ru.mail.data.cmd.server.pusher.pushme.PushMeSendPushSettingsCommand;
import ru.mail.data.cmd.server.pusher.pushme.PushMeUnsubscribeByTokenCommand;
import ru.mail.locator.Locator;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.network.NetworkCommandStatus;
import ru.mail.network.NoAuthInfo;
import ru.mail.util.config.MigrateToPostUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0011\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J*\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0016J\u001e\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J \u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u000fH\u0016J\u0018\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u000fH\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0014\u001a\u00020\u000fH\u0016J&\u0010\u001b\u001a\u0010\u0012\u0002\b\u0003\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\r\u001a\u00020\u0010H\u0014J\u0014\u0010\u001f\u001a\u00020\t2\n\u0010 \u001a\u0006\u0012\u0002\b\u00030\u001dH\u0002R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006!"}, d2 = {"Lru/mail/util/push/pusher/network/PushMePusherInterfaceImpl;", "Lru/mail/util/push/pusher/network/PusherInterface;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "getContext", "()Landroid/content/Context;", "registerDeviceForPushes", "Lru/mail/util/push/pusher/network/PusherInterface$PushResult;", "accounts", "", "Lru/mail/util/push/pusher/network/PusherInterface$Account;", "settings", "", "", "Lorg/json/JSONArray;", "unregisterWithToken", "serviceToken", "unregisterWithDeviceId", "userIdentifier", "pusherApplication", RbParams.Default.URL_PARAM_KEY_DEVICE_ID, "unregisterWithPushToken", "pushToken", "checkIsTokenExists", "", "createSetSettingsCommand", "Lru/mail/mailbox/cmd/Command;", "Lru/mail/mailbox/cmd/CommandStatus;", "login", "mapResult", "result", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPushMePusherInterfaceImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PushMePusherInterfaceImpl.kt\nru/mail/util/push/pusher/network/PushMePusherInterfaceImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,124:1\n1563#2:125\n1634#2,3:126\n*S KotlinDebug\n*F\n+ 1 PushMePusherInterfaceImpl.kt\nru/mail/util/push/pusher/network/PushMePusherInterfaceImpl\n*L\n117#1:125\n117#1:126,3\n*E\n"})
public class PushMePusherInterfaceImpl implements PusherInterface {
    public static final int $stable = 8;

    @NotNull
    private final Context context;

    public PushMePusherInterfaceImpl(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final PusherInterface.PushResult mapResult(CommandStatus<?> result) {
        if (result instanceof CommandStatus.OK) {
            return PusherInterface.PushResult.OK.INSTANCE;
        }
        if (result instanceof NetworkCommandStatus.NO_AUTH) {
            T data = ((NetworkCommandStatus.NO_AUTH) result).getData();
            Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.network.NoAuthInfo");
            String login = ((NoAuthInfo) data).getLogin();
            Intrinsics.checkNotNullExpressionValue(login, "getLogin(...)");
            return new PusherInterface.PushResult.NoAuth(login);
        }
        if (!(result instanceof NetworkCommandStatus.NO_AUTH_MULTIPLE)) {
            return PusherInterface.PushResult.UnknownError.INSTANCE;
        }
        List<NoAuthInfo> data2 = ((NetworkCommandStatus.NO_AUTH_MULTIPLE) result).getData();
        Intrinsics.checkNotNull(data2, "null cannot be cast to non-null type kotlin.collections.List<ru.mail.network.NoAuthInfo>");
        List<NoAuthInfo> list = data2;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
        Iterator<T> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(((NoAuthInfo) it.next()).getLogin());
        }
        return new PusherInterface.PushResult.NoAuthMultiple(arrayList);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.util.push.pusher.network.PusherInterface
    public boolean checkIsTokenExists(@NotNull String pushToken, @NotNull String userIdentifier) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        PushMeCheckPushTokenCommand.Params params = new PushMeCheckPushTokenCommand.Params(userIdentifier, pushToken);
        Context context = this.context;
        CommandStatus<?> orThrow = new PushMeCheckPushTokenCommand(context, params, MigrateToPostUtils.is12154Enabled(context)).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
        if (!(orThrow instanceof CommandStatus.OK)) {
            throw new IllegalStateException("Execution failed");
        }
        V data = ((CommandStatus.OK) orThrow).getData();
        Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.data.cmd.server.pusher.pushme.PushMeCheckPushTokenCommand.Result");
        return ((PushMeCheckPushTokenCommand.Result) data).tokenExists();
    }

    @NotNull
    protected Command<?, CommandStatus<?>> createSetSettingsCommand(@NotNull String login, @NotNull JSONArray settings) {
        Intrinsics.checkNotNullParameter(login, "login");
        Intrinsics.checkNotNullParameter(settings, "settings");
        PushMeSendPushSettingsCommand.Params params = new PushMeSendPushSettingsCommand.Params(login, settings);
        Context context = this.context;
        return new PushMeSendPushSettingsCommand(context, params, MigrateToPostUtils.is12153Enabled(context));
    }

    @NotNull
    protected final Context getContext() {
        return this.context;
    }

    @Override // ru.mail.util.push.pusher.network.PusherInterface
    @NotNull
    public PusherInterface.PushResult registerDeviceForPushes(@NotNull Collection<PusherInterface.Account> accounts, @NotNull Map<String, String> settings) {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(settings, "settings");
        throw new IllegalStateException("You should use another method with JSONArray argument for push-me API");
    }

    @Override // ru.mail.util.push.pusher.network.PusherInterface
    @NotNull
    public PusherInterface.PushResult unregisterWithDeviceId(@NotNull String userIdentifier, @NotNull String pusherApplication, @NotNull String deviceId) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(userIdentifier, "userIdentifier");
        Intrinsics.checkNotNullParameter(pusherApplication, "pusherApplication");
        Intrinsics.checkNotNullParameter(deviceId, "deviceId");
        PushMeRemovePushSettingsCmd.Params params = new PushMeRemovePushSettingsCmd.Params(deviceId, userIdentifier, pusherApplication);
        Context context = this.context;
        CommandStatus<?> orThrow = new PushMeRemovePushSettingsCmd(context, params, MigrateToPostUtils.is12154Enabled(context)).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
        Intrinsics.checkNotNull(orThrow);
        return mapResult(orThrow);
    }

    @Override // ru.mail.util.push.pusher.network.PusherInterface
    @NotNull
    public PusherInterface.PushResult unregisterWithPushToken(@NotNull String pusherApplication, @NotNull String pushToken) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(pusherApplication, "pusherApplication");
        Intrinsics.checkNotNullParameter(pushToken, "pushToken");
        PushMeUnsubscribeByTokenCommand.Params params = new PushMeUnsubscribeByTokenCommand.Params(pushToken, pusherApplication);
        Context context = this.context;
        CommandStatus<?> orThrow = new PushMeUnsubscribeByTokenCommand(context, params, MigrateToPostUtils.is12153Enabled(context)).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
        Intrinsics.checkNotNull(orThrow);
        return mapResult(orThrow);
    }

    @Override // ru.mail.util.push.pusher.network.PusherInterface
    @NotNull
    public PusherInterface.PushResult unregisterWithToken(@NotNull String serviceToken) {
        Intrinsics.checkNotNullParameter(serviceToken, "serviceToken");
        throw new IllegalStateException("Push-me API does not support it currently.");
    }

    @Override // ru.mail.util.push.pusher.network.PusherInterface
    @NotNull
    public PusherInterface.PushResult registerDeviceForPushes(@NotNull Collection<PusherInterface.Account> accounts, @NotNull JSONArray settings) throws ExecutionException, InterruptedException {
        Intrinsics.checkNotNullParameter(accounts, "accounts");
        Intrinsics.checkNotNullParameter(settings, "settings");
        if (accounts.isEmpty()) {
            throw new IllegalArgumentException("You have to pass at least one account");
        }
        CommandStatus<?> orThrow = createSetSettingsCommand(((PusherInterface.Account) CollectionsKt.first(accounts)).getUserIdentifier(), settings).execute((RequestArbiter) Locator.INSTANCE.locate(this.context, RequestArbiter.class)).getOrThrow();
        Intrinsics.checkNotNull(orThrow);
        return mapResult(orThrow);
    }
}
