package ru.mail.logic.push;

import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.pushme.PushMeSdk;
import com.vk.pushme.model.Transport;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.arbiter.Pools;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandExecutor;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.util.log.Log;
import ru.mail.util.push.PushMessagesTransport;
import ru.mail.util.push.PushType;
import ru.mail.util.push.component.PushComponent;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u00112\u0014\u0012\u0004\u0012\u00020\u0002\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00030\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0014J\u0018\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0012\u0010\r\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lru/mail/logic/push/CopyPushTokensToPushMeSDK;", "Lru/mail/mailbox/cmd/Command;", "", "Lru/mail/mailbox/cmd/CommandStatus;", "component", "Lru/mail/util/push/component/PushComponent;", "<init>", "(Lru/mail/util/push/component/PushComponent;)V", "selectCodeExecutor", "Lru/mail/mailbox/cmd/CommandExecutor;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "onExecute", "mapTransport", "Lcom/vk/pushme/model/Transport;", "pushTransport", "Lru/mail/util/push/PushMessagesTransport;", "Companion", "mail-app_mail_ruRelease"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class CopyPushTokensToPushMeSDK extends Command<Unit, CommandStatus<Unit>> {

    @NotNull
    private final PushComponent component;
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("CopyPushTokensToPushMeSDK");

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PushType.values().length];
            try {
                iArr[PushType.GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[PushType.HMS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[PushType.VKPNS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CopyPushTokensToPushMeSDK(@NotNull PushComponent component) {
        super(null);
        Intrinsics.checkNotNullParameter(component, "component");
        this.component = component;
    }

    private final Transport mapTransport(PushMessagesTransport pushTransport) {
        int i10 = WhenMappings.$EnumSwitchMapping$0[pushTransport.getPushType().ordinal()];
        if (i10 == 1) {
            return Transport.FIREBASE;
        }
        if (i10 == 2) {
            return Transport.HUAWEI;
        }
        if (i10 != 3) {
            return null;
        }
        return Transport.VKPNS;
    }

    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    protected CommandExecutor selectCodeExecutor(@NotNull ExecutorSelector selector) {
        Intrinsics.checkNotNullParameter(selector, "selector");
        CommandExecutor singleCommandExecutor = selector.getSingleCommandExecutor(Pools.COMPUTATION);
        Intrinsics.checkNotNullExpressionValue(singleCommandExecutor, "getSingleCommandExecutor(...)");
        return singleCommandExecutor;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.mailbox.cmd.Command
    @NotNull
    public CommandStatus<Unit> onExecute(@Nullable ExecutorSelector selector) {
        for (PushMessagesTransport pushMessagesTransport : this.component.getPushMessagesTransports()) {
            Transport transportMapTransport = mapTransport(pushMessagesTransport);
            if (transportMapTransport != null) {
                try {
                    String pushTokenFromPushKit = pushMessagesTransport.getGcmPushKitWrapper().getPushTokenFromPushKit();
                    if (pushTokenFromPushKit == null || StringsKt.isBlank(pushTokenFromPushKit)) {
                        LOG.w("Token is missing for transport " + pushMessagesTransport.getPushType());
                    } else {
                        PushMeSdk.INSTANCE.onNewToken(pushTokenFromPushKit, transportMapTransport);
                    }
                } catch (Exception e10) {
                    LOG.e("Unable to get token for " + pushMessagesTransport.getPushType(), e10);
                }
            }
        }
        LOG.i("Command has finished");
        return new CommandStatus.OK();
    }
}
