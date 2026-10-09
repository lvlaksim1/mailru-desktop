package ru.mail.libverify.notifications;

import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.Menu;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.pm.ShortcutInfoCompat;
import androidx.core.content.pm.ShortcutManagerCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.graphics.drawable.IconCompat;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.libverify.R;
import ru.mail.verify.core.utils.FileLog;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes10.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B\u0007¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"Lru/mail/libverify/notifications/SmsCodeNotificationActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "Lru/mail/libverify/i/b;", "<init>", "()V", "libverify_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSmsCodeNotificationActivity.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SmsCodeNotificationActivity.kt\nru/mail/libverify/notifications/SmsCodeNotificationActivity\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 ColorDrawable.kt\nandroidx/core/graphics/drawable/ColorDrawableKt\n*L\n1#1,283:1\n1#2:284\n28#3:285\n*S KotlinDebug\n*F\n+ 1 SmsCodeNotificationActivity.kt\nru/mail/libverify/notifications/SmsCodeNotificationActivity\n*L\n224#1:285\n*E\n"})
public final class SmsCodeNotificationActivity extends AppCompatActivity implements ru.mail.libverify.i.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private String f87572a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private String f87573b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @Nullable
    private AlertDialog f87574c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private boolean f87575d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @NotNull
    private final Lazy f87576e = LazyKt.lazy(new a());

    /* JADX INFO: compiled from: ProGuard */
    static final class a extends Lambda implements Function0<Drawable> {
        a() {
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public final Drawable invoke() {
            Integer numValueOf;
            Drawable drawable = ResourcesCompat.getDrawable(SmsCodeNotificationActivity.this.getResources(), R.drawable.libverify_ic_sms_white, SmsCodeNotificationActivity.this.getTheme());
            try {
                numValueOf = Integer.valueOf(ResourcesCompat.getColor(SmsCodeNotificationActivity.this.getResources(), R.color.libverify_secondary_icon_color, SmsCodeNotificationActivity.this.getTheme()));
            } catch (Resources.NotFoundException unused) {
                numValueOf = null;
            }
            if (drawable == null || numValueOf == null) {
                throw new IllegalStateException("Check failed.");
            }
            Drawable drawableWrap = DrawableCompat.wrap(drawable);
            DrawableCompat.setTint(drawableWrap, numValueOf.intValue());
            return drawableWrap;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void b(SmsCodeNotificationActivity smsCodeNotificationActivity, DialogInterface dialogInterface, int i10) {
        try {
            String str = smsCodeNotificationActivity.f87572a;
            if (str == null) {
                str = null;
            }
            ru.mail.libverify.i.e.c(smsCodeNotificationActivity, str).send();
        } catch (PendingIntent.CanceledException e10) {
            FileLog.e("SmsCodeActivity", "failed to open settings", e10);
        }
        smsCodeNotificationActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void c(SmsCodeNotificationActivity smsCodeNotificationActivity, DialogInterface dialogInterface, int i10) {
        try {
            String str = smsCodeNotificationActivity.f87572a;
            if (str == null) {
                str = null;
            }
            ru.mail.libverify.i.e.b(smsCodeNotificationActivity, str).send();
        } catch (PendingIntent.CanceledException e10) {
            FileLog.e("SmsCodeActivity", "failed to confirm notification", e10);
        }
        smsCodeNotificationActivity.finish();
    }

    @Override // ru.mail.libverify.i.b
    public final void a(@Nullable ru.mail.libverify.api.j.b bVar) {
        boolean z10;
        AlertDialog alertDialog;
        if (bVar == null) {
            String str = this.f87572a;
            Notification notificationA = ru.mail.libverify.notifications.a.C0388a.a(this, str != null ? str : null);
            if (notificationA == null) {
                finish();
                return;
            }
            AlertDialog alertDialogA = a(notificationA.extras.getString("android.title"), notificationA.tickerText.toString(), "", "", false);
            this.f87574c = alertDialogA;
            alertDialogA.show();
            Linkify.addLinks((TextView) this.f87574c.findViewById(android.R.id.message), 3);
            return;
        }
        String str2 = bVar.f86855f;
        String str3 = this.f87572a;
        if (str3 == null) {
            str3 = null;
        }
        if (!Intrinsics.areEqual(str2, str3)) {
            StringBuilder sb2 = new StringBuilder("no such notification with id ");
            String str4 = this.f87572a;
            sb2.append(str4 != null ? str4 : null);
            FileLog.e("SmsCodeActivity", sb2.toString());
            finish();
            return;
        }
        if (this.f87575d) {
            StringBuilder sb3 = new StringBuilder("activity with id ");
            String str5 = this.f87572a;
            sb3.append(str5 != null ? str5 : null);
            sb3.append(" has been already deactivated");
            FileLog.d("SmsCodeActivity", sb3.toString());
            return;
        }
        String str6 = bVar.f86851b;
        this.f87573b = str6;
        this.f87574c = a(str6, bVar.f86850a, bVar.f86852c, bVar.f86856g, bVar.f86853d.booleanValue());
        try {
            if (!isFinishing() && (alertDialog = this.f87574c) != null) {
                alertDialog.show();
            }
        } catch (WindowManager.BadTokenException e10) {
            e10.printStackTrace();
        }
        if (bVar.f86859j) {
            String str7 = bVar.f86857h;
            String string = (str7 == null || str7.length() == 0) ? getResources().getString(R.string.notification_history_shortcut_name) : bVar.f86857h;
            if (ru.mail.libverify.n0.e.b(this, "com.android.launcher.permission.INSTALL_SHORTCUT") && ru.mail.libverify.n0.e.b(this, "com.android.launcher.permission.UNINSTALL_SHORTCUT")) {
                Intent intent = new Intent(getApplicationContext(), (Class<?>) SettingsActivity.class);
                intent.setAction("ACTION_SHOW_DIALOGS");
                intent.addFlags(SQLiteDatabase.CREATE_IF_NECESSARY);
                intent.addFlags(67108864);
                ShortcutManagerCompat.removeDynamicShortcuts(this, CollectionsKt.listOf(string));
                ShortcutManagerCompat.pushDynamicShortcut(this, new ShortcutInfoCompat.Builder(this, string).setShortLabel(string).setIcon(IconCompat.createWithResource(this, R.drawable.libverify_ic_sms_white)).setIntent(intent).build());
                z10 = true;
            } else {
                z10 = false;
            }
            ru.mail.libverify.d0.a.a(this, ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.UI_NOTIFICATION_HISTORY_SHORTCUT_CREATED, Boolean.valueOf(z10)));
        }
        AlertDialog alertDialog2 = this.f87574c;
        if (alertDialog2 != null) {
            Linkify.addLinks((TextView) alertDialog2.findViewById(android.R.id.message), 3);
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected final void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_sms_code_notification);
        if (getIntent() == null) {
            finish();
            return;
        }
        FileLog.v("SmsCodeActivity", "create with %s", ru.mail.libverify.n0.e.a(getIntent().getExtras()));
        String stringExtra = getIntent().getStringExtra("notification_id");
        if (stringExtra == null) {
            finish();
            return;
        }
        this.f87572a = stringExtra;
        ru.mail.libverify.d0.a.a(this, ru.mail.libverify.p0.e.a(ru.mail.libverify.p0.a.UI_NOTIFICATION_OPENED, stringExtra));
        ru.mail.libverify.p0.a aVar = ru.mail.libverify.p0.a.UI_NOTIFICATION_GET_INFO;
        String str = this.f87572a;
        if (str == null) {
            str = null;
        }
        ru.mail.libverify.d0.a.a(this, ru.mail.libverify.p0.e.a(aVar, str, new ru.mail.libverify.i.a(this)));
    }

    @Override // android.app.Activity
    public final boolean onCreateOptionsMenu(@NotNull Menu menu) {
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected final void onPause() {
        String str = this.f87573b;
        if (str != null) {
            int i10 = R.drawable.libverify_ic_sms_white;
            setTitle(str);
            TypedValue typedValue = new TypedValue();
            getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true);
            int i11 = typedValue.data;
            ActionBar supportActionBar = getSupportActionBar();
            if (supportActionBar != null) {
                supportActionBar.setBackgroundDrawable(new ColorDrawable(i11));
            }
            int i12 = Build.VERSION.SDK_INT;
            if (i12 < 35) {
                getWindow().addFlags(Integer.MIN_VALUE);
                getWindow().setStatusBarColor(i11);
            }
            Bitmap bitmapDecodeResource = BitmapFactory.decodeResource(getResources(), i10);
            setTaskDescription(i12 >= 33 ? g.a().setLabel(str).setIcon(i10).setPrimaryColor(i11).build() : new ActivityManager.TaskDescription(str, bitmapDecodeResource, i11));
            bitmapDecodeResource.recycle();
        }
        super.onPause();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected final void onStop() {
        super.onStop();
        this.f87575d = true;
        AlertDialog alertDialog = this.f87574c;
        if (alertDialog != null) {
            alertDialog.dismiss();
        }
    }

    private final AlertDialog a(String str, String str2, String str3, String str4, boolean z10) {
        FileLog.v("SmsCodeActivity", "build dialog for notification " + str);
        if (str4 != null && str4.length() != 0) {
            str2 = str2 + '\n' + str4;
        }
        if (str3 == null || str3.length() == 0) {
            str3 = getString(R.string.notification_event_confirm);
        }
        AlertDialog.Builder neutralButton = new AlertDialog.Builder(this).setTitle(str).setIcon((Drawable) this.f87576e.getValue()).setMessage(str2).setNegativeButton(getString(R.string.notification_event_close), new DialogInterface.OnClickListener() { // from class: ru.mail.libverify.notifications.h
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                SmsCodeNotificationActivity.a(this.f87587a, dialogInterface, i10);
            }
        }).setNeutralButton(getString(R.string.notification_settings), new DialogInterface.OnClickListener() { // from class: ru.mail.libverify.notifications.i
            @Override // android.content.DialogInterface.OnClickListener
            public final void onClick(DialogInterface dialogInterface, int i10) {
                SmsCodeNotificationActivity.b(this.f87588a, dialogInterface, i10);
            }
        });
        if (z10) {
            neutralButton.setPositiveButton(str3, new DialogInterface.OnClickListener() { // from class: ru.mail.libverify.notifications.j
                @Override // android.content.DialogInterface.OnClickListener
                public final void onClick(DialogInterface dialogInterface, int i10) {
                    SmsCodeNotificationActivity.c(this.f87589a, dialogInterface, i10);
                }
            });
        }
        AlertDialog alertDialogCreate = neutralButton.create();
        alertDialogCreate.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: ru.mail.libverify.notifications.k
            @Override // android.content.DialogInterface.OnDismissListener
            public final void onDismiss(DialogInterface dialogInterface) {
                SmsCodeNotificationActivity.a(this.f87590a, dialogInterface);
            }
        });
        return alertDialogCreate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(SmsCodeNotificationActivity smsCodeNotificationActivity, DialogInterface dialogInterface, int i10) {
        try {
            String str = smsCodeNotificationActivity.f87572a;
            if (str == null) {
                str = null;
            }
            ru.mail.libverify.i.e.a(smsCodeNotificationActivity, str).send();
        } catch (PendingIntent.CanceledException e10) {
            FileLog.e("SmsCodeActivity", "failed to confirm notification", e10);
        }
        smsCodeNotificationActivity.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(SmsCodeNotificationActivity smsCodeNotificationActivity, DialogInterface dialogInterface) {
        smsCodeNotificationActivity.finish();
    }
}
