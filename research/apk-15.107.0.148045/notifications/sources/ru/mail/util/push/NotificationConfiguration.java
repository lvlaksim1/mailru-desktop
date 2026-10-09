package ru.mail.util.push;

import android.content.Context;
import android.net.Uri;
import android.preference.PreferenceManager;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import java.util.Calendar;
import ru.mail.ui.fragments.settings.TimePreferenceUtils;
import ru.mail.util.log.Log;
import ru.mail.utils.TimeUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class NotificationConfiguration {
    public static final String KEY_PREF_PUSH_BORDER_FROM = "push_border_from";
    public static final String KEY_PREF_PUSH_BORDER_TO = "push_border_to";
    public static final String KEY_PREF_PUSH_DISTURB_MODE = "prefs_key_push_disturb_mode";
    public static final String KEY_PREF_PUSH_DONT_DISTURB = "push_dont_disturb";
    public static final String KEY_PREF_PUSH_VIBRATION = "push_vibration";
    private static final int LED_BLINK_TIME_OFF = 1000;
    private static final int LED_BLINK_TIME_ON = 1000;
    private static final Log LOG = Log.getLog("NotificationConfiguration");
    private final Context mContext;

    /* JADX INFO: renamed from: ru.mail.util.push.NotificationConfiguration$1, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$ru$mail$util$push$NotificationConfiguration$PushDisturbMode;

        static {
            int[] iArr = new int[PushDisturbMode.values().length];
            $SwitchMap$ru$mail$util$push$NotificationConfiguration$PushDisturbMode = iArr;
            try {
                iArr[PushDisturbMode.DONT_SHOW.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$ru$mail$util$push$NotificationConfiguration$PushDisturbMode[PushDisturbMode.SILENT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes13.dex */
    public static abstract class PushDisturbMode {
        private static final /* synthetic */ PushDisturbMode[] $VALUES = $values();
        public static final PushDisturbMode DONT_SHOW;
        public static final PushDisturbMode SILENT;

        /* JADX INFO: renamed from: ru.mail.util.push.NotificationConfiguration$PushDisturbMode$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends PushDisturbMode {
            @Override // ru.mail.util.push.NotificationConfiguration.PushDisturbMode
            public int getRangeSummaryId() {
                return ru.mail.mails.R.string.mapp_set_notif_dont_disturb_range_silent;
            }

            private AnonymousClass1(String str, int i10) {
                super(str, i10);
            }
        }

        /* JADX INFO: renamed from: ru.mail.util.push.NotificationConfiguration$PushDisturbMode$2, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass2 extends PushDisturbMode {
            @Override // ru.mail.util.push.NotificationConfiguration.PushDisturbMode
            public int getRangeSummaryId() {
                return ru.mail.mails.R.string.mapp_set_notif_dont_disturb_range_dont_show;
            }

            private AnonymousClass2(String str, int i10) {
                super(str, i10);
            }
        }

        private static /* synthetic */ PushDisturbMode[] $values() {
            return new PushDisturbMode[]{SILENT, DONT_SHOW};
        }

        static {
            SILENT = new AnonymousClass1("SILENT", 0);
            DONT_SHOW = new AnonymousClass2("DONT_SHOW", 1);
        }

        public static PushDisturbMode valueOf(String str) {
            return (PushDisturbMode) Enum.valueOf(PushDisturbMode.class, str);
        }

        public static PushDisturbMode[] values() {
            return (PushDisturbMode[]) $VALUES.clone();
        }

        @StringRes
        public abstract int getRangeSummaryId();

        private PushDisturbMode(String str, int i10) {
            super(str, i10);
        }
    }

    public NotificationConfiguration(Context context) {
        this.mContext = context.getApplicationContext();
    }

    public static long getPushBorderFrom(Context context) {
        return getTimePreference(context, KEY_PREF_PUSH_BORDER_FROM, context.getResources().getInteger(ru.mail.mails.R.integer.prefs_from_def));
    }

    public static long getPushBorderTo(Context context) {
        return getTimePreference(context, KEY_PREF_PUSH_BORDER_TO, context.getResources().getInteger(ru.mail.mails.R.integer.prefs_to_def));
    }

    public static PushDisturbMode getPushDisturbMode(Context context) {
        return PushDisturbMode.valueOf(PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_PUSH_DISTURB_MODE, PushDisturbMode.SILENT.toString()));
    }

    protected static long getTimePreference(Context context, String str, long j10) {
        long j11 = PreferenceManager.getDefaultSharedPreferences(context).getLong(str, j10);
        Calendar calendar = Calendar.getInstance();
        calendar.set(11, TimePreferenceUtils.getHour(j11));
        calendar.set(12, TimePreferenceUtils.getMinute(j11));
        return calendar.getTime().getTime();
    }

    public static boolean isDontDisturbModeEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_PUSH_DONT_DISTURB, true);
    }

    public static boolean isPushVibrationEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(KEY_PREF_PUSH_VIBRATION, true);
    }

    private boolean isTimeInSilentRange(long j10) {
        return TimeUtils.isCurrentTimeInRange(getPushBorderFrom(this.mContext), getPushBorderTo(this.mContext), j10);
    }

    private boolean isTimeOutOfSilentRange(long j10) {
        return !isTimeInSilentRange(j10);
    }

    private boolean needShowInDontDisturbMode() {
        int i10 = AnonymousClass1.$SwitchMap$ru$mail$util$push$NotificationConfiguration$PushDisturbMode[getPushDisturbMode(this.mContext).ordinal()];
        if (i10 == 1) {
            return isTimeOutOfSilentRange(System.currentTimeMillis());
        }
        if (i10 == 2) {
            return true;
        }
        LOG.e("Unknown push mode");
        return false;
    }

    public boolean canShowNotificationNow() {
        return !isDontDisturbModeEnabled(this.mContext) || needShowInDontDisturbMode();
    }

    public int getLightColor() {
        return ContextCompat.getColor(this.mContext, ru.mail.mails.R.color.contrast_primary);
    }

    public int getLightTimeOff() {
        return 1000;
    }

    public int getLightTimeOn() {
        return 1000;
    }

    public Uri getSoundUri() {
        return new NotificationSound().getSoundUri(this.mContext);
    }

    public long[] getVibrationPattern() {
        return isPushVibrationEnabled(this.mContext) ? new long[]{0, 200, 50, 500} : new long[0];
    }
}
