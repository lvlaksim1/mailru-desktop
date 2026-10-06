package ru.mail.serverapi;

import android.net.Uri;
import java.util.Collection;
import java.util.regex.Pattern;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
public interface PlatformInfo {
    void appendLoginExperiment(Uri.Builder builder);

    String getAppsFlyerId();

    String getBehaviorName();

    String getConnectionQuality();

    String getCurrentDistributor();

    int getDarkThemeEnabled();

    int getDeviceAge();

    Pattern getExistingLoginSuppressedOauth();

    String getFirstDistributor();

    Collection<String> getSegments();

    Collection<String> getShortSegments();

    boolean isAppBackgrounded();
}
