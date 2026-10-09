package ru.mail.util.push;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import androidx.annotation.Nullable;
import java.io.File;
import ru.mail.logic.share.MailFileProvider;
import ru.mail.sdk.BuildConfigVariablesHolder;
import ru.mail.utils.FileUtils;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public class NotificationSound {
    public static final String KEY_PREF_PUSH_SOUND = "push_sound";
    public static final String KEY_PREF_PUSH_SOUND_FILE_NAME = "push_sound_file_name";
    public static final String KEY_PREF_PUSH_SOUND_FILE_PATH = "push_sound_file_path";
    public static final int MAX_SOUND_SIZE_MB = 1;
    private static final String SOUND_FILE_DIR = "ringtone";
    private static final String SOUND_FILE_NAME = "notification_sound";

    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX WARN: Unknown enum class pattern. Please report as an issue! */
    /* JADX INFO: compiled from: ProGuard */
    public static class Sound {
        private final String mName;
        private final int mNameId;
        private final int mRawId;
        public static final Sound NEW_MESSAGE_BELLS = new Sound("NEW_MESSAGE_BELLS", 0, "NEW_MESSAGE_BELLS", ru.mail.runtime.utils.R.raw.new_message_bells, ru.mail.mails.R.string.sound_1);
        public static final Sound NEW_MESSAGE_CLING = new Sound("NEW_MESSAGE_CLING", 1, "NEW_MESSAGE_CLING", ru.mail.runtime.utils.R.raw.new_message_cling, ru.mail.mails.R.string.sound_2);
        public static final Sound NEW_MESSAGE_PLINK = new Sound("NEW_MESSAGE_PLINK", 2, "NEW_MESSAGE_PLINK", ru.mail.runtime.utils.R.raw.new_message_plink, ru.mail.mails.R.string.sound_3);
        public static final Sound NEW_MESSAGE_LOGO_01 = new Sound("NEW_MESSAGE_LOGO_01", 3, "NEW_MESSAGE_LOGO_01", ru.mail.runtime.utils.R.raw.new_message_logo_01, ru.mail.mails.R.string.sound_4);
        public static final Sound EMPTY = new Sound("EMPTY", 4, "EMPTY", 0, ru.mail.mails.R.string.sound_no);
        public static final Sound FILE = new AnonymousClass1("FILE", 5, "FILE", 0, 0);
        private static final /* synthetic */ Sound[] $VALUES = $values();

        /* JADX INFO: renamed from: ru.mail.util.push.NotificationSound$Sound$1, reason: invalid class name */
        /* JADX INFO: compiled from: ProGuard */
        final enum AnonymousClass1 extends Sound {
            @Override // ru.mail.util.push.NotificationSound.Sound
            public String getSummary(Context context) {
                return new NotificationSound().getSoundName(context);
            }

            private AnonymousClass1(String str, int i10, String str2, int i11, int i12) {
                super(str, i10, str2, i11, i12);
            }
        }

        private static /* synthetic */ Sound[] $values() {
            return new Sound[]{NEW_MESSAGE_BELLS, NEW_MESSAGE_CLING, NEW_MESSAGE_PLINK, NEW_MESSAGE_LOGO_01, EMPTY, FILE};
        }

        public static Sound valueOf(String str) {
            return (Sound) Enum.valueOf(Sound.class, str);
        }

        public static Sound[] values() {
            return (Sound[]) $VALUES.clone();
        }

        public String getName() {
            return this.mName;
        }

        public int getRawId() {
            return this.mRawId;
        }

        public String getSummary(Context context) {
            return context.getString(this.mNameId);
        }

        private Sound(String str, int i10, String str2, int i11, int i12) {
            super(str, i10);
            this.mRawId = i11;
            this.mNameId = i12;
            this.mName = str2;
        }
    }

    @Nullable
    private File getSoundFile(Context context) {
        String string = PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_PUSH_SOUND_FILE_PATH, null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return new File(string);
    }

    @Nullable
    private Sound parseSound(String str) {
        for (Sound sound : Sound.values()) {
            if (TextUtils.equals(sound.getName(), str)) {
                return sound;
            }
        }
        return null;
    }

    public void applySound(Context context, Sound sound) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_PREF_PUSH_SOUND, sound.toString()).apply();
    }

    public Sound getDefaultSound(Context context) {
        return Sound.valueOf(context.getString(ru.mail.mails.R.string.prefs_push_sound_default));
    }

    public Sound getSound(Context context) {
        Sound sound = parseSound(PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_PUSH_SOUND, context.getString(ru.mail.mails.R.string.prefs_push_sound_default)));
        if (sound != null && (sound != Sound.FILE || verifySoundFile(context))) {
            return sound;
        }
        applySound(context, getDefaultSound(context));
        return getDefaultSound(context);
    }

    public String getSoundName(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getString(KEY_PREF_PUSH_SOUND_FILE_NAME, "");
    }

    public Uri getSoundUri(Context context) {
        Sound sound = getSound(context);
        if (sound != Sound.FILE) {
            return getSoundUri(context, sound);
        }
        return MailFileProvider.getContentUriForFile(context, BuildConfigVariablesHolder.soundContentProviderAuthority, getSoundFile(context));
    }

    public boolean setSoundFile(Context context, File file) {
        File file2 = new File(context.getFilesDir(), SOUND_FILE_DIR);
        if (!file2.exists() && !file2.mkdir()) {
            return false;
        }
        File file3 = new File(file2, SOUND_FILE_NAME);
        if (!FileUtils.copyFile(file, file3)) {
            return false;
        }
        PreferenceManager.getDefaultSharedPreferences(context).edit().putString(KEY_PREF_PUSH_SOUND_FILE_PATH, file3.getAbsolutePath()).putString(KEY_PREF_PUSH_SOUND_FILE_NAME, file.getName()).apply();
        return true;
    }

    public boolean verifyCanBePlayed(Context context, File file) {
        return MediaPlayer.create(context, Uri.fromFile(file)) != null;
    }

    public boolean verifySoundFile(Context context) {
        return verifySoundFile(getSoundFile(context));
    }

    public boolean verifySoundSize(File file) {
        return (((double) file.length()) / 1024.0d) / 1024.0d <= 1.0d;
    }

    public boolean verifySoundFile(File file) {
        return file != null && file.exists() && file.canRead();
    }

    public Uri getSoundUri(Context context, Sound sound) {
        if (sound == Sound.FILE) {
            return Uri.fromFile(getSoundFile(context));
        }
        if (sound == Sound.EMPTY) {
            return null;
        }
        String resourceName = context.getResources().getResourceName(sound.getRawId());
        return Uri.parse("android.resource://" + context.getPackageName() + "/raw/" + resourceName.substring(resourceName.indexOf("raw/") + 4));
    }
}
