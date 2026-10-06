package ru.mail.data.cmd.server;

import androidx.compose.runtime.internal.StabilityInferred;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.cloud.stories.data.gson.parsers.BlockParser;
import ru.mail.logic.child.ParentalMode;
import ru.mail.remotelayout.data.dto.dialog.toast.ToastDialogDto;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 1)
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0007¨\u0006\b"}, d2 = {"Lru/mail/data/cmd/server/ParentalModeNetworkMapper;", "", "<init>", "()V", BlockParser.MAP_TYPE, "Lru/mail/logic/child/ParentalMode;", "from", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class ParentalModeNetworkMapper {
    public static final int $stable = 0;

    @NotNull
    public static final ParentalModeNetworkMapper INSTANCE = new ParentalModeNetworkMapper();

    private ParentalModeNetworkMapper() {
    }

    @JvmStatic
    @NotNull
    public static final ParentalMode map(@Nullable String from) {
        String lowerCase;
        if (from != null) {
            Locale ENGLISH = Locale.ENGLISH;
            Intrinsics.checkNotNullExpressionValue(ENGLISH, "ENGLISH");
            lowerCase = from.toLowerCase(ENGLISH);
            Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        } else {
            lowerCase = null;
        }
        if (Intrinsics.areEqual(lowerCase, ToastDialogDto.KEY_TYPE_PARENT)) {
            return ParentalMode.PARENT;
        }
        return Intrinsics.areEqual(lowerCase, ToastDialogDto.KEY_TYPE_CHILD) ? ParentalMode.CHILD : ParentalMode.OFF;
    }
}
