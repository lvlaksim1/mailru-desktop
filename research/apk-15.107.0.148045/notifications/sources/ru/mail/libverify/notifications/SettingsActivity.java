package ru.mail.libverify.notifications;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.annotation.UiThread;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.libverify.R;
import ru.mail.verify.core.utils.FileLog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lru/mail/libverify/notifications/SettingsActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "<init>", "()V", "libverify_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSettingsActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SettingsActivity.kt\nru/mail/libverify/notifications/SettingsActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 View.kt\nandroidx/core/view/ViewKt\n*L\n1#1,102:1\n1#2:103\n163#3,2:104\n*S KotlinDebug\n*F\n+ 1 SettingsActivity.kt\nru/mail/libverify/notifications/SettingsActivity\n*L\n89#1:104,2\n*E\n"})
public final class SettingsActivity extends AppCompatActivity {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f87571a = 0;

    @UiThread
    private static void a(View view) {
        ViewCompat.setOnApplyWindowInsetsListener(view, new OnApplyWindowInsetsListener() { // from class: ru.mail.libverify.notifications.b
            @Override // androidx.core.view.OnApplyWindowInsetsListener
            public final WindowInsetsCompat onApplyWindowInsets(View view2, WindowInsetsCompat windowInsetsCompat) {
                return SettingsActivity.a(view2, windowInsetsCompat);
            }
        });
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        EdgeToEdge.enable$default(this, null, null, 3, null);
        setContentView(R.layout.activity_settings);
        a(findViewById(R.id.root_view));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        if (getIntent() == null) {
            finish();
            return;
        }
        FileLog.v("SettingsActivity", "create with %s", ru.mail.libverify.n0.e.a(getIntent().getExtras()));
        if (bundle == null) {
            if (Intrinsics.areEqual(getIntent().getAction(), "ACTION_SHOW_DIALOGS")) {
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new ru.mail.libverify.k.b()).commit();
                return;
            }
            ru.mail.libverify.k.a aVar = new ru.mail.libverify.k.a();
            aVar.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, aVar).commit();
        }
    }

    @Override // android.app.Activity
    public final boolean onOptionsItemSelected(@NotNull MenuItem menuItem) {
        if (menuItem.getItemId() != 16908332) {
            return super.onOptionsItemSelected(menuItem);
        }
        getOnBackPressedDispatcher().onBackPressed();
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final WindowInsetsCompat a(View view, WindowInsetsCompat windowInsetsCompat) {
        Insets insets = windowInsetsCompat.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.displayCutout());
        view.setPadding(insets.left, insets.top, insets.right, insets.bottom);
        return WindowInsetsCompat.CONSUMED;
    }
}
