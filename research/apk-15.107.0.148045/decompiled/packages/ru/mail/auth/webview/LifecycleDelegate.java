package ru.mail.auth.webview;

import android.content.Intent;
import android.os.Parcelable;
import androidx.fragment.app.FragmentActivity;
import ru.mail.auth.AuthMessageCallback;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public interface LifecycleDelegate extends Parcelable {
    void onCreate(FragmentActivity fragmentActivity, AuthMessageCallback authMessageCallback);

    void onDestroy();

    void onNewIntent(Intent intent);

    void onResume(FragmentActivity fragmentActivity, AuthMessageCallback authMessageCallback);

    void onStop();
}
