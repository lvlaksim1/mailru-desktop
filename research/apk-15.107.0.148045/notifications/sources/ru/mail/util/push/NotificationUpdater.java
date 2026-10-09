package ru.mail.util.push;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationChannelGroup;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.text.Spanned;
import android.text.TextUtils;
import androidx.collection.MutableObjectList;
import androidx.collection.MutableScatterMap;
import androidx.collection.MutableScatterSet;
import androidx.collection.ScatterMap;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.app.Person;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.IconCompat;
import androidx.core.text.HtmlCompat;
import com.google.android.gms.ads.RequestConfiguration;
import com.google.common.primitives.Longs;
import com.sun.mail.imap.IMAPStore;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.bouncycastle.i18n.ErrorBundle;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.PendingIntentCreator;
import ru.mail.android_utils.PendingIntentUtils;
import ru.mail.android_utils.SdkUtils;
import ru.mail.android_utils.intent.ExternalIntent;
import ru.mail.arbiter.RequestArbiter;
import ru.mail.asserter.core.AsserterConfigFactory;
import ru.mail.asserter.core.AsserterFactory;
import ru.mail.asserter.description.Descriptions;
import ru.mail.auth.AccountManagerWrapper;
import ru.mail.auth.Authenticator;
import ru.mail.cloud.autoupload.data.AutoUploadSettingsContract;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.PushConfigurationType;
import ru.mail.data.cmd.database.MetaThreadUpdater;
import ru.mail.data.cmd.database.SelectMTRCmd;
import ru.mail.data.cmd.server.parser.PushMessageParser;
import ru.mail.data.entities.MailBoxFolder;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.data.entities.MailboxProfileUtils;
import ru.mail.imageloader.ContextWrapper;
import ru.mail.imageloader.ImageLoader;
import ru.mail.imageloader.ImageLoaderRepository;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.locator.Locator;
import ru.mail.logic.content.MailItemTransactionCategory;
import ru.mail.logic.header.HeaderInfoBuilder;
import ru.mail.logic.navigation.Navigator;
import ru.mail.mailapp.service.MailServiceImpl;
import ru.mail.mailbox.cmd.Command;
import ru.mail.mailbox.cmd.CommandGroup;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.ExecutorSelector;
import ru.mail.mailbox.cmd.Priority;
import ru.mail.ui.ThreadMessagesActivity;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogCollector;
import ru.mail.utils.rfc822.Rfc822Token;
import ru.mail.utils.rfc822.Rfc822Tokenizer;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000Ö\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\b\u0007\u0018\u0000 À\u00012\u00020\u0001:\u0012¸\u0001¹\u0001º\u0001»\u0001¼\u0001½\u0001¾\u0001¿\u0001À\u0001B9\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007\u0012\u0014\b\u0002\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\t¢\u0006\u0004\b\f\u0010\rJ\u001c\u0010^\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0RJ0\u0010c\u001a\u00020_2\u0006\u0010`\u001a\u00020a2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020\n0R2\u0006\u0010d\u001a\u00020\u000b2\n\b\u0002\u0010e\u001a\u0004\u0018\u00010fJ\u000e\u0010g\u001a\u00020_2\u0006\u0010h\u001a\u00020aJ4\u0010i\u001a\u000e\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020l0j2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020n0R2\u0006\u0010d\u001a\u00020\u000b2\b\u0010e\u001a\u0004\u0018\u00010fH\u0002J\u0010\u0010o\u001a\u00020p2\u0006\u0010q\u001a\u00020nH\u0002J\u0012\u0010r\u001a\u00020l2\b\u0010s\u001a\u0004\u0018\u00010aH\u0002J\u001e\u0010t\u001a\u00020u2\u0006\u0010v\u001a\u00020p2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J \u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020a2\u0006\u0010z\u001a\u00020a2\u0006\u0010{\u001a\u00020aH\u0002J\u0018\u0010|\u001a\u00020x2\u0006\u0010y\u001a\u00020a2\u0006\u0010z\u001a\u00020aH\u0002J\u0010\u0010}\u001a\u00020x2\u0006\u0010~\u001a\u00020kH\u0002J\"\u0010\u007f\u001a\u0004\u0018\u00010a2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020n0R2\b\u0010s\u001a\u0004\u0018\u00010aH\u0002J\u0017\u0010\u0080\u0001\u001a\u00020\u000b2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J\u0019\u0010\u0081\u0001\u001a\u00020_2\u0006\u0010v\u001a\u00020p2\u0006\u0010q\u001a\u00020\nH\u0002J\u001a\u0010\u0082\u0001\u001a\u00020_2\u0007\u0010\u0083\u0001\u001a\u00020p2\u0006\u0010d\u001a\u00020\u000bH\u0002J\u0012\u0010\u0084\u0001\u001a\u00020k2\u0007\u0010\u0085\u0001\u001a\u00020\nH\u0002J*\u0010\u0086\u0001\u001a\u0014\u0012\u0004\u0012\u00020a0\u0087\u0001j\t\u0012\u0004\u0012\u00020a`\u0088\u00012\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J\u0019\u0010\u008a\u0001\u001a\u00030\u008b\u00012\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J1\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u00012\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0R2\n\u0010\u008e\u0001\u001a\u0005\u0018\u00010\u008f\u00012\b\u0010e\u001a\u0004\u0018\u00010fH\u0002J\u001e\u0010\u0090\u0001\u001a\u00030\u0091\u00012\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\b\u0010\u0094\u0001\u001a\u00030\u0095\u0001H\u0002J\u0013\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\nH\u0002J\u0015\u0010\u0099\u0001\u001a\u0005\u0018\u00010\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\nH\u0002J\u0013\u0010\u009a\u0001\u001a\u00030\u0097\u00012\u0007\u0010\u0098\u0001\u001a\u00020\nH\u0002J\u0013\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0007\u0010\u0098\u0001\u001a\u00020\nH\u0002J\u0018\u0010\u009d\u0001\u001a\u00020\u000b2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J\u0018\u0010\u009e\u0001\u001a\u00020\u000b2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J\u0016\u0010\u009f\u0001\u001a\u00020\u000b2\r\u0010\u0089\u0001\u001a\b\u0012\u0004\u0012\u00020n0RJ\u000f\u0010 \u0001\u001a\u00020a2\u0006\u0010q\u001a\u00020nJ\u0011\u0010¡\u0001\u001a\u00020a2\u0006\u0010q\u001a\u00020nH\u0002J\u000f\u0010¢\u0001\u001a\u00020a2\u0006\u0010q\u001a\u00020nJ\u0017\u0010£\u0001\u001a\u00020k2\f\u0010m\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002J,\u0010¤\u0001\u001a\u00020_2\u0006\u0010\u0018\u001a\u00020\u00192\u0007\u0010¥\u0001\u001a\u00020a2\u0007\u0010¦\u0001\u001a\u00020k2\u0007\u0010§\u0001\u001a\u00020lH\u0003J5\u0010¨\u0001\u001a\u00020_2\u0007\u0010¥\u0001\u001a\u00020a2\u0007\u0010¦\u0001\u001a\u00020k2\u0007\u0010§\u0001\u001a\u00020l2\u0007\u0010©\u0001\u001a\u00020k2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J>\u0010ª\u0001\u001a\u00020_2\u0007\u0010¦\u0001\u001a\u00020k2\u0007\u0010¥\u0001\u001a\u00020a2\u0007\u0010§\u0001\u001a\u00020l2\u0007\u0010«\u0001\u001a\u00020a2\u0007\u0010©\u0001\u001a\u00020k2\u0006\u0010\u0018\u001a\u00020\u0019H\u0002J\u0013\u0010¬\u0001\u001a\u00020\u000b2\b\u0010`\u001a\u0004\u0018\u00010aH\u0002J\u0013\u0010\u00ad\u0001\u001a\u00020a2\b\u0010s\u001a\u0004\u0018\u00010aH\u0002J\u001b\u0010®\u0001\u001a\n\u0012\u0005\u0012\u0003H°\u00010¯\u0001\"\u0007\b\u0000\u0010°\u0001\u0018\u0001H\u0082\bJ)\u0010±\u0001\u001a\u00020a2\u001d\u0010²\u0001\u001a\u0018\u0012\b\u0012\u00060$j\u0002`%\u0012\u0004\u0012\u00020_0\t¢\u0006\u0003\b³\u0001H\u0082\bJ&\u0010´\u0001\u001a\u00030µ\u00012\u0019\u0010´\u0001\u001a\u0014\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020_0\t¢\u0006\u0003\b³\u0001H\u0082\bJ\u0018\u0010¶\u0001\u001a\u00020\u000b2\r\u0010·\u0001\u001a\b\u0012\u0004\u0012\u00020n0RH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000e\u001a\n \u000f*\u0004\u0018\u00010\u00030\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0010\u001a\u00020\u00118BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0018\u001a\u00070\u0019¢\u0006\u0002\b\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001b\u001a\n \u000f*\u0004\u0018\u00010\u001c0\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001d\u001a\n \u000f*\u0004\u0018\u00010\u001e0\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001f\u001a\n \u000f*\u0004\u0018\u00010 0 X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010!\u001a\u00060\"R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010#\u001a\u00060$j\u0002`%X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010(\u001a\u00020)8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b,\u0010-\u001a\u0004\b*\u0010+R\u001b\u0010.\u001a\u00020/8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b2\u0010-\u001a\u0004\b0\u00101R\u001b\u00103\u001a\u0002048BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b7\u0010-\u001a\u0004\b5\u00106R\u001b\u00108\u001a\u0002098BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b<\u0010-\u001a\u0004\b:\u0010;R\u001b\u0010=\u001a\u00020>8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bA\u0010-\u001a\u0004\b?\u0010@R\u001b\u0010B\u001a\u00020C8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bF\u0010-\u001a\u0004\bD\u0010ER\u001f\u0010G\u001a\u00060HR\u00020\u00008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bK\u0010-\u001a\u0004\bI\u0010JR\u001b\u0010L\u001a\u00020M8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bP\u0010-\u001a\u0004\bN\u0010OR!\u0010Q\u001a\b\u0012\u0004\u0012\u00020S0R8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bV\u0010-\u001a\u0004\bT\u0010UR\u001b\u0010W\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bY\u0010-\u001a\u0004\bW\u0010XR\u001b\u0010Z\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b[\u0010-\u001a\u0004\bZ\u0010XR\u001b\u0010\\\u001a\u00020\u000b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b]\u0010-\u001a\u0004\b\\\u0010X¨\u0006Á\u0001"}, d2 = {"Lru/mail/util/push/NotificationUpdater;", "", "context", "Landroid/content/Context;", "notificationConfiguration", "Lru/mail/util/push/NotificationConfiguration;", "displayNotificationChecker", "Lru/mail/util/push/SafeDisplayNotificationChecker;", "shouldPublishNewMailNotification", "Lkotlin/Function1;", "Lru/mail/util/push/NewMailPush;", "", "<init>", "(Landroid/content/Context;Lru/mail/util/push/NotificationConfiguration;Lru/mail/util/push/SafeDisplayNotificationChecker;Lkotlin/jvm/functions/Function1;)V", "appContext", "kotlin.jvm.PlatformType", "resources", "Landroid/content/res/Resources;", "getResources", "()Landroid/content/res/Resources;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "notificationActionSupplier", "Lru/mail/util/push/NotificationActionSupplier;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "Lorg/jspecify/annotations/NonNull;", "configurationRepository", "Lru/mail/config/ConfigurationRepository;", "notificationChannels", "Lru/mail/util/push/NotificationChannels;", "accountManager", "Lru/mail/auth/AccountManagerWrapper;", "messagesNotificationsCreator", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;", "notificationLogBuilder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "personBuilder", "Landroidx/core/app/Person$Builder;", "requestArbiter", "Lru/mail/arbiter/RequestArbiter;", "getRequestArbiter", "()Lru/mail/arbiter/RequestArbiter;", "requestArbiter$delegate", "Lkotlin/Lazy;", "navigator", "Lru/mail/logic/navigation/Navigator;", "getNavigator", "()Lru/mail/logic/navigation/Navigator;", "navigator$delegate", "asserterConfigFactory", "Lru/mail/asserter/core/AsserterConfigFactory;", "getAsserterConfigFactory", "()Lru/mail/asserter/core/AsserterConfigFactory;", "asserterConfigFactory$delegate", "logCollector", "Lru/mail/util/log/LogCollector;", "getLogCollector", "()Lru/mail/util/log/LogCollector;", "logCollector$delegate", "imageLoaderRepository", "Lru/mail/imageloader/ImageLoaderRepository;", "getImageLoaderRepository", "()Lru/mail/imageloader/ImageLoaderRepository;", "imageLoaderRepository$delegate", "avatarLoader", "Lru/mail/util/push/NotificationUpdater$AvatarLoader;", "getAvatarLoader", "()Lru/mail/util/push/NotificationUpdater$AvatarLoader;", "avatarLoader$delegate", "updateNotificationCommand", "Lru/mail/util/push/NotificationUpdater$UpdateNotificationCommand;", "getUpdateNotificationCommand", "()Lru/mail/util/push/NotificationUpdater$UpdateNotificationCommand;", "updateNotificationCommand$delegate", "selectThreadRepresentationCommand", "Lru/mail/data/cmd/database/SelectMTRCmd;", "getSelectThreadRepresentationCommand", "()Lru/mail/data/cmd/database/SelectMTRCmd;", "selectThreadRepresentationCommand$delegate", "pushTypes", "", "Lru/mail/config/PushConfigurationType;", "getPushTypes", "()Ljava/util/List;", "pushTypes$delegate", "isColoredTagsOn", "()Z", "isColoredTagsOn$delegate", "isDetailedLogEnabled", "isDetailedLogEnabled$delegate", "isMessagingStyleWithIconsSupported", "isMessagingStyleWithIconsSupported$delegate", "updateNotificationBarSilently", "", "profile", "", "deletedPushes", "updateNotificationsBar", "silent", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "hideSummaryNotification", "profileId", "getStackingNotifications", "Landroidx/collection/ScatterMap;", "", "Landroid/app/Notification;", "pushList", "Lru/mail/util/push/Entity$NotificationData;", "createOneMessageBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "push", "getNotificationPublicVersion", "login", "createMailsInboxLines", "Landroidx/core/app/NotificationCompat$InboxStyle;", "notificationBuilder", "getNotificationMultipleLine", "", "sender", "subject", "snippet", "getNotificationFormattedTitle", "getAndMoreLine", "andMoreNumber", "getMultipleMessagesTitle", "hasOrdinalFolder", "setNotificationInfo", "setupNotificationSound", "builder", "getPendingIntentRequestCode", "message", "getPushesOpenUrls", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "messages", "getPushMeSdkIdentifiers", "", "getMailNotificationClickIntent", "Landroid/app/PendingIntent;", "notificationMeta", "Lru/mail/util/push/NotificationMeta;", "buildIntent", "Landroid/content/Intent;", PushProcessor.DATAKEY_EXTRAS, "Landroid/os/Bundle;", "action", "Lru/mail/util/push/AckPushProxyHandler$AllowedPushAction;", "getMailThreadRepresentation", "Lru/mail/data/entities/MailThreadRepresentation;", "latestPush", "selectMTRFromDatabase", "createMTRFromPushes", "createMailMessage", "Lru/mail/data/entities/MailMessage;", "hasMultipleMessagesInThreads", "isSingleThread", "isAllSingleMessageInThreads", "getTitle", "getSnippetText", "getSubjectText", "getThreadsWithPushesCount", "notifyWithLog", "tag", "id", "notification", "notifyWithRetry", "retryCount", "onNotifyOnPushFailed", "errorMessage", "needShowNotification", "getChannelId", "lazyLocate", "Lkotlin/Lazy;", RequestConfiguration.MAX_AD_CONTENT_RATING_T, "buildLogString", "buildString", "Lkotlin/ExtensionFunctionType;", "buildPerson", "Landroidx/core/app/Person;", "getLatestPushIsReminder", "notificationsData", "UpdateNotificationCommand", "UpdateNotificationBarMsg", "SummaryNotificationBuilder", "InboxLinesForThreadCreator", "MessagesNotificationsCreator", "WaitingTask", "HideSummaryNotifications", "AvatarLoader", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater\n+ 2 String.kt\nandroidx/core/text/StringKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1568:1\n708#1:1569\n708#1:1570\n708#1:1571\n708#1:1572\n708#1:1573\n714#1,5:1584\n28#2:1574\n28#2:1575\n28#2:1576\n28#2:1577\n1869#3,2:1578\n1869#3,2:1580\n1869#3,2:1582\n774#3:1589\n865#3,2:1590\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater\n*L\n105#1:1569\n106#1:1570\n107#1:1571\n108#1:1572\n109#1:1573\n611#1:1584,5\n248#1:1574\n249#1:1575\n263#1:1576\n279#1:1577\n321#1:1578,2\n515#1:1580,2\n590#1:1582,2\n127#1:1589\n127#1:1590,2\n*E\n"})
public final class NotificationUpdater {

    @NotNull
    public static final String EXTRA_ACCOUNT_ID = "account_id";

    @NotNull
    public static final String EXTRA_FROM_PUSH = "from_push";

    @NotNull
    public static final String EXTRA_MESSAGE_IDS = "message_id";

    @NotNull
    public static final String EXTRA_REPLY_MSG = "extra_reply_msg";
    private static final int MAX_MESSAGES_IN_NOTIFICATION = 5;
    private static final int MIN_NUMBER_OF_PUSHES_FOR_SUMMARY = 2;
    private static final int PUSH_VISIBILITY = 0;
    private static final int SUMMARY_NOTIFICATION_ID = 1073741824;
    private static final long TIMER_DEFAULT_DELAY = 5000;
    private final AccountManagerWrapper accountManager;
    private final Context appContext;

    /* JADX INFO: renamed from: asserterConfigFactory$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy asserterConfigFactory;

    /* JADX INFO: renamed from: avatarLoader$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy avatarLoader;
    private final ConfigurationRepository configurationRepository;

    @NotNull
    private final SafeDisplayNotificationChecker displayNotificationChecker;

    @NotNull
    private final Handler handler;

    /* JADX INFO: renamed from: imageLoaderRepository$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy imageLoaderRepository;

    /* JADX INFO: renamed from: isColoredTagsOn$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isColoredTagsOn;

    /* JADX INFO: renamed from: isDetailedLogEnabled$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isDetailedLogEnabled;

    /* JADX INFO: renamed from: isMessagingStyleWithIconsSupported$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy isMessagingStyleWithIconsSupported;

    /* JADX INFO: renamed from: logCollector$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy logCollector;

    @NotNull
    private final MessagesNotificationsCreator messagesNotificationsCreator;

    /* JADX INFO: renamed from: navigator$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy navigator;

    @NotNull
    private final NotificationActionSupplier notificationActionSupplier;
    private final NotificationChannels notificationChannels;

    @NotNull
    private final NotificationConfiguration notificationConfiguration;

    @NotNull
    private final StringBuilder notificationLogBuilder;

    @NotNull
    private final NotificationManagerCompat notificationManager;

    @NotNull
    private final Person.Builder personBuilder;

    /* JADX INFO: renamed from: pushTypes$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy pushTypes;

    /* JADX INFO: renamed from: requestArbiter$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy requestArbiter;

    /* JADX INFO: renamed from: selectThreadRepresentationCommand$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy selectThreadRepresentationCommand;

    @NotNull
    private final Function1<NewMailPush, Boolean> shouldPublishNewMailNotification;

    /* JADX INFO: renamed from: updateNotificationCommand$delegate, reason: from kotlin metadata */
    @NotNull
    private final Lazy updateNotificationCommand;

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    public static final int $stable = 8;

    @NotNull
    private static final Log LOG = Log.INSTANCE.getLog("NotificationUpdater");

    @NotNull
    private static final AtomicBoolean realNotify = new AtomicBoolean(true);

    @NotNull
    private static final Timer myTimer = new Timer();

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0013"}, d2 = {"Lru/mail/util/push/NotificationUpdater$AvatarLoader;", "", "applicationContext", "Landroid/content/Context;", "displayNotificationChecker", "Lru/mail/util/push/SafeDisplayNotificationChecker;", "<init>", "(Landroid/content/Context;Lru/mail/util/push/SafeDisplayNotificationChecker;)V", "loader", "Lru/mail/util/push/NotificationAvatarLoader;", "avatar", "Landroid/graphics/Bitmap;", "getAvatar", "()Landroid/graphics/Bitmap;", "load", "", "setAvatarIfPossible", "builder", "Landroidx/core/app/NotificationCompat$Builder;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    static final class AvatarLoader {

        @NotNull
        private final NotificationAvatarLoader loader;

        public AvatarLoader(@NotNull Context applicationContext, @NotNull SafeDisplayNotificationChecker displayNotificationChecker) {
            Intrinsics.checkNotNullParameter(applicationContext, "applicationContext");
            Intrinsics.checkNotNullParameter(displayNotificationChecker, "displayNotificationChecker");
            this.loader = new NotificationAvatarLoader(applicationContext, displayNotificationChecker.isSafelyDisplayNotification());
        }

        @Nullable
        public final Bitmap getAvatar() {
            return this.loader.getAvatar();
        }

        public final void load() {
            this.loader.load(ru.mail.mails.R.drawable.ic_notification_app);
        }

        public final void setAvatarIfPossible(@NotNull NotificationCompat.Builder builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            if (this.loader.isDefaultIcon()) {
                return;
            }
            builder.setLargeIcon(getAvatar());
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0002J!\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b2\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00170\u0019H\u0002¢\u0006\u0002\u0010\u001cJ\u0014\u0010\u001d\u001a\u00020\u001e2\n\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lru/mail/util/push/NotificationUpdater$Companion;", "", "<init>", "()V", "EXTRA_FROM_PUSH", "", "EXTRA_ACCOUNT_ID", "EXTRA_MESSAGE_IDS", "EXTRA_REPLY_MSG", "LOG", "Lru/mail/util/log/Log;", "PUSH_VISIBILITY", "", "MAX_MESSAGES_IN_NOTIFICATION", "MIN_NUMBER_OF_PUSHES_FOR_SUMMARY", "SUMMARY_NOTIFICATION_ID", "TIMER_DEFAULT_DELAY", "", "realNotify", "Ljava/util/concurrent/atomic/AtomicBoolean;", "myTimer", "Ljava/util/Timer;", "getLatestPush", "Lru/mail/util/push/Entity$NotificationData;", "pushList", "", "getMailIds", "", "(Ljava/util/List;)[Ljava/lang/String;", "pushesCountSameAsMaxNotificationsLines", "", "pushes", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,1568:1\n1969#2,14:1569\n1563#2:1583\n1634#2,3:1584\n37#3,2:1587\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$Companion\n*L\n1556#1:1569,14\n1560#1:1583\n1560#1:1584,3\n1560#1:1587,2\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final Entity.NotificationData getLatestPush(List<Entity.NotificationData> pushList) {
            Iterator<T> it = pushList.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            Object next = it.next();
            if (it.hasNext()) {
                long timestamp = ((Entity.NotificationData) next).getPush().getTimestamp();
                do {
                    Object next2 = it.next();
                    long timestamp2 = ((Entity.NotificationData) next2).getPush().getTimestamp();
                    if (timestamp < timestamp2) {
                        next = next2;
                        timestamp = timestamp2;
                    }
                } while (it.hasNext());
            }
            return (Entity.NotificationData) next;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final String[] getMailIds(List<Entity.NotificationData> pushList) {
            List<Entity.NotificationData> list = pushList;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(((Entity.NotificationData) it.next()).getPush().getMessageId());
            }
            return (String[]) arrayList.toArray(new String[0]);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean pushesCountSameAsMaxNotificationsLines(Collection<?> pushes) {
            return pushes.size() == 6;
        }

        private Companion() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lru/mail/util/push/NotificationUpdater$HideSummaryNotifications;", "Ljava/lang/Runnable;", "notificationManager", "Landroidx/core/app/NotificationManagerCompat;", "profileId", "", "<init>", "(Landroidx/core/app/NotificationManagerCompat;Ljava/lang/String;)V", "run", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class HideSummaryNotifications implements Runnable {

        @NotNull
        private final NotificationManagerCompat notificationManager;

        @NotNull
        private final String profileId;

        public HideSummaryNotifications(@NotNull NotificationManagerCompat notificationManager, @NotNull String profileId) {
            Intrinsics.checkNotNullParameter(notificationManager, "notificationManager");
            Intrinsics.checkNotNullParameter(profileId, "profileId");
            this.notificationManager = notificationManager;
            this.profileId = profileId;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.notificationManager.cancel(this.profileId, 1073741824);
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u001d\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0006\u0010\u0012\u001a\u00020\nJ\u0006\u0010\u0013\u001a\u00020\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J\u0010\u0010\u0017\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u000e\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0004J\u000e\u0010\u001d\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0004J\u0006\u0010\u001e\u001a\u00020\u0014J\u000e\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020!J\u0010\u0010\"\u001a\u00020\u00162\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019J\u0006\u0010#\u001a\u00020\u0014R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Lru/mail/util/push/NotificationUpdater$InboxLinesForThreadCreator;", "", "pushList", "", "Lru/mail/util/push/Entity$NotificationData;", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "<init>", "(Lru/mail/util/push/NotificationUpdater;Ljava/util/List;Landroidx/core/app/NotificationCompat$Builder;)V", "inboxStyle", "Landroidx/core/app/NotificationCompat$InboxStyle;", "pushInThreadsCounters", "Lru/mail/util/push/Entity$ThreadIdCountSender;", "threadIdAndSubjectList", "Lru/mail/util/push/Entity$ThreadIdAndSubjectList;", "addedCount", "", "threadsCount", "create", "initLinesForNotifications", "", "needGroupByThread", "", "isLineForThreadNotExist", "threadId", "", "updateLineInfoForThread", "push", "addNewLineInfoForThread", "addNewLineForOneMessage", "addThreadsLines", "isMessage", "lineInfo", "Lru/mail/util/push/Entity$ThreadIdAndSubject;", "needGroupMessagesForThread", "addAndMoreLine", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$InboxLinesForThreadCreator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,1568:1\n1869#2,2:1569\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$InboxLinesForThreadCreator\n*L\n997#1:1569,2\n*E\n"})
    private final class InboxLinesForThreadCreator {
        private int addedCount;

        @NotNull
        private final NotificationCompat.InboxStyle inboxStyle;

        @NotNull
        private final Entity.ThreadIdCountSender pushInThreadsCounters;

        @NotNull
        private final List<Entity.NotificationData> pushList;
        final /* synthetic */ NotificationUpdater this$0;

        @NotNull
        private final Entity.ThreadIdAndSubjectList threadIdAndSubjectList;
        private int threadsCount;

        public InboxLinesForThreadCreator(@NotNull NotificationUpdater notificationUpdater, @NotNull List<Entity.NotificationData> pushList, NotificationCompat.Builder notificationBuilder) {
            Intrinsics.checkNotNullParameter(pushList, "pushList");
            Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
            this.this$0 = notificationUpdater;
            this.pushList = pushList;
            this.inboxStyle = new NotificationCompat.InboxStyle(notificationBuilder);
            this.pushInThreadsCounters = new Entity.ThreadIdCountSender();
            this.threadIdAndSubjectList = new Entity.ThreadIdAndSubjectList();
            this.threadsCount = notificationUpdater.getThreadsWithPushesCount(pushList);
        }

        public final void addAndMoreLine() {
            if (this.addedCount < this.threadIdAndSubjectList.size()) {
                this.inboxStyle.addLine(this.this$0.getAndMoreLine(this.threadIdAndSubjectList.size() - this.addedCount));
            }
        }

        public final void addNewLineForOneMessage(@NotNull Entity.NotificationData push) {
            Intrinsics.checkNotNullParameter(push, "push");
            NotificationUpdater notificationUpdater = this.this$0;
            this.threadIdAndSubjectList.add(new Entity.ThreadIdAndSubject(null, notificationUpdater.getNotificationMultipleLine(notificationUpdater.getTitle(push), this.this$0.getSnippetText(push), "")));
        }

        public final void addNewLineInfoForThread(@NotNull Entity.NotificationData push) {
            Intrinsics.checkNotNullParameter(push, "push");
            String threadId = push.getPush().getThreadId();
            Intrinsics.checkNotNull(threadId);
            Entity.ThreadIdAndSubject threadIdAndSubject = new Entity.ThreadIdAndSubject(threadId, this.this$0.getSubjectText(push));
            this.pushInThreadsCounters.put(threadId, new Entity.CountAndSender(1, this.this$0.getTitle(push)));
            this.threadIdAndSubjectList.add(threadIdAndSubject);
        }

        public final void addThreadsLines() {
            boolean zPushesCountSameAsMaxNotificationsLines = NotificationUpdater.INSTANCE.pushesCountSameAsMaxNotificationsLines(this.threadIdAndSubjectList);
            for (int i10 = 0; i10 < this.threadIdAndSubjectList.size() && this.addedCount < (zPushesCountSameAsMaxNotificationsLines ? 1 : 0) + 5; i10++) {
                Entity.ThreadIdAndSubject threadIdAndSubject = this.threadIdAndSubjectList.get(i10);
                Intrinsics.checkNotNullExpressionValue(threadIdAndSubject, "get(...)");
                Entity.ThreadIdAndSubject threadIdAndSubject2 = threadIdAndSubject;
                if (isMessage(threadIdAndSubject2)) {
                    this.inboxStyle.addLine(threadIdAndSubject2.getSubj());
                } else {
                    String string = threadIdAndSubject2.getSubj().toString();
                    if (needGroupMessagesForThread(threadIdAndSubject2.getThreadId())) {
                        Entity.CountAndSender countAndSender = this.pushInThreadsCounters.get((Object) threadIdAndSubject2.getThreadId());
                        Intrinsics.checkNotNull(countAndSender);
                        int count = countAndSender.getCount();
                        String quantityString = this.this$0.getResources().getQuantityString(ru.mail.mails.R.plurals.stat_messages_title, count, Integer.valueOf(count));
                        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
                        this.inboxStyle.addLine(this.this$0.getNotificationMultipleLine(quantityString, string, ""));
                    } else {
                        Entity.CountAndSender countAndSender2 = this.pushInThreadsCounters.get((Object) threadIdAndSubject2.getThreadId());
                        Intrinsics.checkNotNull(countAndSender2);
                        this.inboxStyle.addLine(this.this$0.getNotificationMultipleLine(countAndSender2.getSender().toString(), string, ""));
                    }
                }
                this.addedCount++;
            }
        }

        @NotNull
        public final NotificationCompat.InboxStyle create() {
            initLinesForNotifications();
            addThreadsLines();
            addAndMoreLine();
            return this.inboxStyle;
        }

        public final void initLinesForNotifications() {
            for (Entity.NotificationData notificationData : this.pushList) {
                String threadId = notificationData.getPush().getThreadId();
                if (threadId == null || !needGroupByThread()) {
                    addNewLineForOneMessage(notificationData);
                } else if (isLineForThreadNotExist(threadId)) {
                    addNewLineInfoForThread(notificationData);
                } else {
                    updateLineInfoForThread(notificationData);
                }
            }
        }

        public final boolean isLineForThreadNotExist(@Nullable String threadId) {
            return !this.pushInThreadsCounters.containsKey((Object) threadId);
        }

        public final boolean isMessage(@NotNull Entity.ThreadIdAndSubject lineInfo) {
            Intrinsics.checkNotNullParameter(lineInfo, "lineInfo");
            return lineInfo.getThreadId() == null;
        }

        public final boolean needGroupByThread() {
            return this.threadsCount > 1;
        }

        public final boolean needGroupMessagesForThread(@Nullable String threadId) {
            Entity.CountAndSender countAndSender = this.pushInThreadsCounters.get((Object) threadId);
            Intrinsics.checkNotNull(countAndSender);
            return countAndSender.getCount() > 1;
        }

        public final void updateLineInfoForThread(@NotNull Entity.NotificationData push) {
            Intrinsics.checkNotNullParameter(push, "push");
            String threadId = push.getPush().getThreadId();
            Intrinsics.checkNotNull(threadId);
            Entity.CountAndSender countAndSender = (Entity.CountAndSender) this.pushInThreadsCounters.get((Object) threadId);
            Entity.ThreadIdCountSender threadIdCountSender = this.pushInThreadsCounters;
            Intrinsics.checkNotNull(countAndSender);
            threadIdCountSender.put(threadId, new Entity.CountAndSender(countAndSender.getCount() + 1, this.this$0.getTitle(push)));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001:\u0003\u0019\u001a\u001bB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J2\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u00052\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJD\u0010\u000f\u001a\u00020\u00102\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u001a\u0010\u0011\u001a\u0016\u0012\u0004\u0012\u00020\u0013\u0012\f\u0012\n0\u0014R\u00060\u0000R\u00020\u00150\u00122\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0013¨\u0006\u001c"}, d2 = {"Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;", "", "<init>", "(Lru/mail/util/push/NotificationUpdater;)V", "create", "Landroidx/collection/ScatterMap;", "", "Landroid/app/Notification;", "pushList", "", "Lru/mail/util/push/Entity$NotificationData;", "silentNotifications", "", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "initNotificationCreators", "", "notificationCreators", "Landroidx/collection/MutableScatterMap;", "", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$NotificationCreator;", "Lru/mail/util/push/NotificationUpdater;", "loadImage", "Landroid/graphics/Bitmap;", "url", "NotificationCreator", "SingleMessageNotificationCreator", "MessagingStyleNotificationCreator", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator\n+ 2 ScatterMap.kt\nandroidx/collection/ScatterMap\n+ 3 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 ScatterMap.kt\nandroidx/collection/MutableScatterMap\n+ 6 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,1568:1\n372#2,3:1569\n329#2,6:1572\n339#2,3:1579\n342#2,9:1583\n375#2:1592\n1399#3:1578\n1270#3:1582\n1869#4:1593\n1870#4:1596\n683#5:1594\n1#6:1595\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator\n*L\n1109#1:1569,3\n1109#1:1572,6\n1109#1:1579,3\n1109#1:1583,9\n1109#1:1592\n1109#1:1578\n1109#1:1582\n1127#1:1593\n1127#1:1596\n1133#1:1594\n1133#1:1595\n*E\n"})
    private final class MessagesNotificationsCreator {

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\n0\u0001R\u00060\u0002R\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u0015\u001a\u00020\u0016J\u000e\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\rJ\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u0005H\u0016J\u0018\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\"H\u0016J\b\u0010#\u001a\u00020\u0012H\u0016J\u0014\u0010$\u001a\u0004\u0018\u00010%2\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\n\u0010&\u001a\u0004\u0018\u00010%H\u0016J\u000e\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014¨\u0006*"}, d2 = {"Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$MessagingStyleNotificationCreator;", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$NotificationCreator;", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;", "Lru/mail/util/push/NotificationUpdater;", "useGroupNotificationSound", "", "silentNotifications", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "<init>", "(Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;ZZLru/mail/android_utils/intent/ExternalIntent;)V", "notificationsData", "", "Lru/mail/util/push/Entity$NotificationData;", "pushMessageType", "Lru/mail/util/push/PushMessageType;", "messagesIds", "", "", "getMessagesIds", "()[Ljava/lang/String;", "resolvePushMessageType", "", "addNewMessageToNotification", "notificationData", "createNotification", "Landroid/app/Notification;", "silent", "createStyle", "Landroidx/core/app/NotificationCompat$Style;", "push", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "getNotificationId", "", "getProfileId", "createClickNotificationIntent", "Landroid/app/PendingIntent;", "createDeleteIntent", "getMessagesSortedByAscending", "", "Landroidx/core/app/NotificationCompat$MessagingStyle$Message;", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$MessagingStyleNotificationCreator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n+ 4 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater\n*L\n1#1,1568:1\n1563#2:1569\n1634#2,3:1570\n1869#2,2:1582\n1869#2:1591\n1870#2:1599\n37#3,2:1573\n722#4,7:1575\n722#4,7:1584\n722#4,7:1592\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$MessagingStyleNotificationCreator\n*L\n1351#1:1569\n1351#1:1570,3\n1382#1:1582,2\n1472#1:1591\n1472#1:1599\n1351#1:1573,2\n1376#1:1575,7\n1414#1:1584,7\n1473#1:1592,7\n*E\n"})
        private final class MessagingStyleNotificationCreator extends NotificationCreator {

            @Nullable
            private final ExternalIntent externalIntent;

            @NotNull
            private final List<Entity.NotificationData> notificationsData;

            @NotNull
            private PushMessageType pushMessageType;
            private final boolean silentNotifications;
            private final boolean useGroupNotificationSound;

            public MessagingStyleNotificationCreator(boolean z10, @Nullable boolean z11, ExternalIntent externalIntent) {
                super();
                this.useGroupNotificationSound = z10;
                this.silentNotifications = z11;
                this.externalIntent = externalIntent;
                this.notificationsData = new MutableObjectList(0, 1, null).asMutableList();
                this.pushMessageType = PushMessageType.SINGLE_MESSAGE;
            }

            private final List<NotificationCompat.MessagingStyle.Message> getMessagesSortedByAscending() {
                MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
                Bitmap avatar = NotificationUpdater.this.getAvatarLoader().getAvatar();
                IconCompat iconCompatCreateWithBitmap = avatar != null ? IconCompat.createWithBitmap(avatar) : null;
                List<Entity.NotificationData> listReversed = CollectionsKt.reversed(this.notificationsData);
                NotificationUpdater notificationUpdater = NotificationUpdater.this;
                for (Entity.NotificationData notificationData : listReversed) {
                    notificationUpdater.personBuilder.setIcon(null);
                    notificationUpdater.personBuilder.setName(null);
                    Person.Builder builder = notificationUpdater.personBuilder;
                    builder.setName(notificationUpdater.getTitle(notificationData));
                    if (iconCompatCreateWithBitmap != null) {
                        builder.setIcon(iconCompatCreateWithBitmap);
                    }
                    Person personBuild = notificationUpdater.personBuilder.build();
                    Intrinsics.checkNotNullExpressionValue(personBuild, "build(...)");
                    notificationUpdater.personBuilder.setIcon(null);
                    notificationUpdater.personBuilder.setName(null);
                    mutableObjectList.add(new NotificationCompat.MessagingStyle.Message(notificationUpdater.getSnippetText(notificationData), notificationData.getPush().getTimestamp(), personBuild));
                }
                return mutableObjectList.asList();
            }

            public final void addNewMessageToNotification(@NotNull Entity.NotificationData notificationData) {
                Intrinsics.checkNotNullParameter(notificationData, "notificationData");
                this.notificationsData.add(notificationData);
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @Nullable
            public PendingIntent createClickNotificationIntent(@Nullable ExternalIntent externalIntent) {
                return NotificationUpdater.this.getMailNotificationClickIntent(this.notificationsData, new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, this.pushMessageType, false, false, false, NotificationUpdater.this.getLatestPushIsReminder(this.notificationsData), 28, null), externalIntent);
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @Nullable
            public PendingIntent createDeleteIntent() {
                Context context = NotificationUpdater.this.appContext;
                Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
                return NotificationIntentFactory.forMessageRemoval(context, getProfileId(), getMessagesIds(), new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, this.pushMessageType, false, false, false, false, 60, null));
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public Notification createNotification(boolean silent) {
                resolvePushMessageType();
                List<Entity.NotificationData> list = this.notificationsData;
                Entity.NotificationData notificationData = list.get(list.size() - 1);
                NotificationCompat.Builder builderCreateNotificationBuilder = createNotificationBuilder(notificationData, this.useGroupNotificationSound, this.silentNotifications, this.externalIntent);
                if (this.notificationsData.size() <= 1) {
                    Entity.NotificationData latestPush = NotificationUpdater.INSTANCE.getLatestPush(this.notificationsData);
                    return createOneMessageNotification(latestPush, createNotificationBuilder(latestPush, this.useGroupNotificationSound, silent, this.externalIntent));
                }
                List<NotificationCompat.MessagingStyle.Message> messagesSortedByAscending = getMessagesSortedByAscending();
                NotificationUpdater notificationUpdater = NotificationUpdater.this;
                notificationUpdater.personBuilder.setIcon(null);
                notificationUpdater.personBuilder.setName(null);
                notificationUpdater.personBuilder.setName(notificationUpdater.getTitle(notificationData));
                Person personBuild = notificationUpdater.personBuilder.build();
                Intrinsics.checkNotNullExpressionValue(personBuild, "build(...)");
                notificationUpdater.personBuilder.setIcon(null);
                notificationUpdater.personBuilder.setName(null);
                NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(personBuild);
                messagingStyle.setConversationTitle(NotificationUpdater.this.getSubjectText(notificationData));
                messagingStyle.setGroupConversation(true);
                Iterator<T> it = messagesSortedByAscending.iterator();
                while (it.hasNext()) {
                    messagingStyle.addMessage((NotificationCompat.MessagingStyle.Message) it.next());
                }
                builderCreateNotificationBuilder.setContentText(NotificationUpdater.this.getSnippetText(notificationData));
                builderCreateNotificationBuilder.setContentTitle(NotificationUpdater.this.getTitle(notificationData));
                builderCreateNotificationBuilder.setContentIntent(createClickNotificationIntent(this.externalIntent));
                NotificationUpdater.this.notificationActionSupplier.addSummarySingleMessageThreadButton(NotificationUpdater.INSTANCE.getLatestPush(this.notificationsData), this.notificationsData, builderCreateNotificationBuilder, getNotificationId());
                Notification notificationBuild = builderCreateNotificationBuilder.setStyle(messagingStyle).build();
                Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
                return notificationBuild;
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public NotificationCompat.Style createStyle(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder) {
                Intrinsics.checkNotNullParameter(push, "push");
                Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
                Bitmap avatar = NotificationUpdater.this.getAvatarLoader().getAvatar();
                NotificationUpdater notificationUpdater = NotificationUpdater.this;
                notificationUpdater.personBuilder.setIcon(null);
                notificationUpdater.personBuilder.setName(null);
                notificationUpdater.personBuilder.setName(notificationUpdater.getNotificationFormattedTitle(notificationUpdater.getTitle(push), notificationUpdater.getSubjectText(push)));
                if (avatar != null) {
                    notificationUpdater.personBuilder.setIcon(IconCompat.createWithBitmap(avatar));
                }
                Person personBuild = notificationUpdater.personBuilder.build();
                Intrinsics.checkNotNullExpressionValue(personBuild, "build(...)");
                notificationUpdater.personBuilder.setIcon(null);
                notificationUpdater.personBuilder.setName(null);
                NotificationCompat.MessagingStyle messagingStyle = new NotificationCompat.MessagingStyle(personBuild);
                messagingStyle.addMessage(new NotificationCompat.MessagingStyle.Message(NotificationUpdater.this.getSnippetText(push), push.getPush().getTimestamp(), personBuild));
                return messagingStyle;
            }

            @NotNull
            public final String[] getMessagesIds() {
                List<Entity.NotificationData> list = this.notificationsData;
                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    arrayList.add(((Entity.NotificationData) it.next()).getPush().getMessageId());
                }
                return (String[]) arrayList.toArray(new String[0]);
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            public int getNotificationId() {
                List<Entity.NotificationData> list = this.notificationsData;
                return list.get(list.size() - 1).getNotificationId();
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public String getProfileId() {
                List<Entity.NotificationData> list = this.notificationsData;
                String profileId = list.get(list.size() - 1).getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
                return profileId;
            }

            public final void resolvePushMessageType() {
                this.pushMessageType = this.notificationsData.size() > 1 ? PushMessageType.SUMMARY_MESSAGES_IN_THREAD : PushMessageType.SINGLE_MESSAGE;
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\b¢\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH&J\b\u0010\u000e\u001a\u00020\u000fH&J\b\u0010\u0010\u001a\u00020\u0011H&J\u0014\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H&J\n\u0010\u0016\u001a\u0004\u0018\u00010\u0013H&J(\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\u000b2\u0006\u0010\u0019\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\f\u001a\u00020\rH\u0002J\u0006\u0010\u001c\u001a\u00020\u001bJ\u0016\u0010\u001d\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r¨\u0006\u001e"}, d2 = {"Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$NotificationCreator;", "", "<init>", "(Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;)V", "createNotification", "Landroid/app/Notification;", "silent", "", "createStyle", "Landroidx/core/app/NotificationCompat$Style;", "push", "Lru/mail/util/push/Entity$NotificationData;", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "getNotificationId", "", "getProfileId", "", "createClickNotificationIntent", "Landroid/app/PendingIntent;", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "createDeleteIntent", "createNotificationBuilder", "lastNotificationInGroup", "useGroupNotificationSound", "addDeleteIntent", "", "sendAssertNotNull", "createOneMessageNotification", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        private abstract class NotificationCreator {
            public NotificationCreator() {
            }

            private final void addDeleteIntent(NotificationCompat.Builder notificationBuilder) {
                PendingIntent pendingIntentCreateDeleteIntent = null;
                for (int i10 = 0; i10 < 3; i10++) {
                    if (pendingIntentCreateDeleteIntent == null) {
                        pendingIntentCreateDeleteIntent = createDeleteIntent();
                    }
                }
                if (pendingIntentCreateDeleteIntent == null) {
                    sendAssertNotNull();
                }
                notificationBuilder.setDeleteIntent(pendingIntentCreateDeleteIntent);
            }

            @Nullable
            public abstract PendingIntent createClickNotificationIntent(@Nullable ExternalIntent externalIntent);

            @Nullable
            public abstract PendingIntent createDeleteIntent();

            @NotNull
            public abstract Notification createNotification(boolean silent);

            @NotNull
            public final NotificationCompat.Builder createNotificationBuilder(@NotNull Entity.NotificationData lastNotificationInGroup, boolean useGroupNotificationSound, boolean silent, @Nullable ExternalIntent externalIntent) {
                Intrinsics.checkNotNullParameter(lastNotificationInGroup, "lastNotificationInGroup");
                NotificationCompat.Builder builderCreateOneMessageBuilder = NotificationUpdater.this.createOneMessageBuilder(lastNotificationInGroup);
                addDeleteIntent(builderCreateOneMessageBuilder);
                NotificationUpdater.this.setNotificationInfo(builderCreateOneMessageBuilder, lastNotificationInGroup.getPush());
                builderCreateOneMessageBuilder.setGroup(getProfileId());
                if (useGroupNotificationSound) {
                    Intrinsics.checkNotNull(builderCreateOneMessageBuilder.setGroupAlertBehavior(1));
                } else {
                    NotificationUpdater.this.setupNotificationSound(builderCreateOneMessageBuilder, silent);
                }
                builderCreateOneMessageBuilder.setContentIntent(createClickNotificationIntent(externalIntent));
                return builderCreateOneMessageBuilder;
            }

            @NotNull
            public final Notification createOneMessageNotification(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder) {
                Bitmap bitmapLoadImage;
                Intrinsics.checkNotNullParameter(push, "push");
                Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
                if (NotificationUpdater.this.isDetailedLogEnabled()) {
                    NotificationUpdater.LOG.i("Add actions for msgId = " + push.getPush().getMessageId());
                }
                NotificationUpdater.this.notificationActionSupplier.addWearableActions(push, notificationBuilder, getNotificationId());
                NotificationUpdater.this.notificationActionSupplier.addButtonsActions(push, notificationBuilder, getNotificationId());
                String imageUrl = push.getPush().getImageUrl();
                if (imageUrl != null && !StringsKt.isBlank(imageUrl) && (bitmapLoadImage = MessagesNotificationsCreator.this.loadImage(imageUrl)) != null) {
                    notificationBuilder.setLargeIcon(bitmapLoadImage);
                }
                Notification notificationBuild = notificationBuilder.setStyle(createStyle(push, notificationBuilder)).build();
                Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
                return notificationBuild;
            }

            @NotNull
            public abstract NotificationCompat.Style createStyle(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder);

            public abstract int getNotificationId();

            @NotNull
            public abstract String getProfileId();

            public final void sendAssertNotNull() {
                AsserterFactory.createAsserter(NotificationUpdater.this.getAsserterConfigFactory().createAsserterConfiguration("DeleteIntentAsserter")).fail("Set null delete action on push message", new RuntimeException("Set null delete action on push message"), Descriptions.compositionOf(CollectionsKt.mutableListOf(Descriptions.logs(NotificationUpdater.this.getLogCollector()))));
            }
        }

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\n0\u0001R\u00060\u0002R\u00020\u0003B9\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0007H\u0016J\u0018\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\u0014\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001dH\u0016J\u0016\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u001b2\u0006\u0010\"\u001a\u00020\u001bR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006#"}, d2 = {"Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$SingleMessageNotificationCreator;", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$NotificationCreator;", "Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;", "Lru/mail/util/push/NotificationUpdater;", "notificationData", "Lru/mail/util/push/Entity$NotificationData;", "useGroupAlertNotification", "", "hasOrdinalFolder", "silentNotifications", "displayNotificationChecker", "Lru/mail/util/push/SafeDisplayNotificationChecker;", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "<init>", "(Lru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator;Lru/mail/util/push/Entity$NotificationData;ZZZLru/mail/util/push/SafeDisplayNotificationChecker;Lru/mail/android_utils/intent/ExternalIntent;)V", "createNotification", "Landroid/app/Notification;", "silent", "createStyle", "Landroidx/core/app/NotificationCompat$Style;", "push", "notificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "getNotificationId", "", "getProfileId", "", "createClickNotificationIntent", "Landroid/app/PendingIntent;", "createDeleteIntent", "getNotificationSingleText", "", "sender", "subject", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
        @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$SingleMessageNotificationCreator\n+ 2 String.kt\nandroidx/core/text/StringKt\n*L\n1#1,1568:1\n28#2:1569\n28#2:1570\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$MessagesNotificationsCreator$SingleMessageNotificationCreator\n*L\n1330#1:1569\n1331#1:1570\n*E\n"})
        private final class SingleMessageNotificationCreator extends NotificationCreator {

            @NotNull
            private final SafeDisplayNotificationChecker displayNotificationChecker;

            @Nullable
            private final ExternalIntent externalIntent;
            private final boolean hasOrdinalFolder;

            @NotNull
            private final Entity.NotificationData notificationData;
            private final boolean silentNotifications;
            final /* synthetic */ MessagesNotificationsCreator this$0;
            private final boolean useGroupAlertNotification;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            public SingleMessageNotificationCreator(@NotNull MessagesNotificationsCreator messagesNotificationsCreator, Entity.NotificationData notificationData, boolean z10, boolean z11, @NotNull boolean z12, @Nullable SafeDisplayNotificationChecker displayNotificationChecker, ExternalIntent externalIntent) {
                super();
                Intrinsics.checkNotNullParameter(notificationData, "notificationData");
                Intrinsics.checkNotNullParameter(displayNotificationChecker, "displayNotificationChecker");
                this.this$0 = messagesNotificationsCreator;
                this.notificationData = notificationData;
                this.useGroupAlertNotification = z10;
                this.hasOrdinalFolder = z11;
                this.silentNotifications = z12;
                this.displayNotificationChecker = displayNotificationChecker;
                this.externalIntent = externalIntent;
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @Nullable
            public PendingIntent createClickNotificationIntent(@Nullable ExternalIntent externalIntent) {
                return NotificationUpdater.this.getMailNotificationClickIntent(CollectionsKt.listOf(this.notificationData), new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, PushMessageType.SINGLE_MESSAGE, false, false, false, this.notificationData.getPush().isReminder(), 28, null), externalIntent);
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @Nullable
            public PendingIntent createDeleteIntent() {
                NotificationIntentFactory notificationIntentFactory = NotificationIntentFactory.INSTANCE;
                Context context = NotificationUpdater.this.appContext;
                Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
                NotificationMeta notificationMetaAsMeta = this.notificationData.asMeta(PushMessageType.SINGLE_MESSAGE);
                String profileId = getProfileId();
                String messageId = this.notificationData.getPush().getMessageId();
                Intrinsics.checkNotNullExpressionValue(messageId, "getMessageId(...)");
                return NotificationIntentFactory.forMessageRemoval(context, profileId, new String[]{messageId}, notificationMetaAsMeta);
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public Notification createNotification(boolean silent) {
                NotificationCompat.Builder builderCreateNotificationBuilder = createNotificationBuilder(this.notificationData, this.useGroupAlertNotification, this.silentNotifications, this.externalIntent);
                NotificationUpdater.this.getAvatarLoader().setAvatarIfPossible(builderCreateNotificationBuilder);
                if (this.hasOrdinalFolder && !this.displayNotificationChecker.isSafelyDisplayNotification()) {
                    return createOneMessageNotification(this.notificationData, builderCreateNotificationBuilder);
                }
                Notification notificationBuild = builderCreateNotificationBuilder.build();
                Intrinsics.checkNotNull(notificationBuild);
                return notificationBuild;
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public NotificationCompat.Style createStyle(@NotNull Entity.NotificationData push, @NotNull NotificationCompat.Builder notificationBuilder) {
                Intrinsics.checkNotNullParameter(push, "push");
                Intrinsics.checkNotNullParameter(notificationBuilder, "notificationBuilder");
                NotificationCompat.BigTextStyle bigContentTitle = new NotificationCompat.BigTextStyle(notificationBuilder).bigText(getNotificationSingleText(NotificationUpdater.this.getSubjectText(push), NotificationUpdater.this.getSnippetText(push))).setSummaryText(push.getPush().getProfileId()).setBigContentTitle(NotificationUpdater.this.getTitle(push));
                Intrinsics.checkNotNullExpressionValue(bigContentTitle, "setBigContentTitle(...)");
                return bigContentTitle;
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            public int getNotificationId() {
                return this.notificationData.getNotificationId();
            }

            @NotNull
            public final CharSequence getNotificationSingleText(@NotNull String sender, @NotNull String subject) {
                Intrinsics.checkNotNullParameter(sender, "sender");
                Intrinsics.checkNotNullParameter(subject, "subject");
                String string = NotificationUpdater.this.getResources().getString(ru.mail.mails.R.string.notification_single_text, TextUtils.htmlEncode(sender), TextUtils.htmlEncode(subject));
                Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
                Spanned spannedFromHtml = HtmlCompat.fromHtml(string, 63);
                Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
                return spannedFromHtml;
            }

            @Override // ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.NotificationCreator
            @NotNull
            public String getProfileId() {
                String profileId = this.notificationData.getPush().getProfileId();
                Intrinsics.checkNotNullExpressionValue(profileId, "getProfileId(...)");
                return profileId;
            }
        }

        public MessagesNotificationsCreator() {
        }

        private final void initNotificationCreators(List<Entity.NotificationData> pushList, MutableScatterMap<String, NotificationCreator> notificationCreators, boolean silentNotifications, ExternalIntent externalIntent) {
            boolean z10;
            ExternalIntent externalIntent2;
            boolean z11 = pushList.size() > 1;
            boolean zIsMessagingStyleWithIconsSupported = NotificationUpdater.this.isMessagingStyleWithIconsSupported();
            boolean zHasOrdinalFolder = NotificationUpdater.this.hasOrdinalFolder(pushList);
            NotificationUpdater notificationUpdater = NotificationUpdater.this;
            for (Entity.NotificationData notificationData : pushList) {
                NewMailPush push = notificationData.getPush();
                MailBoxFolder folder = notificationData.getFolder();
                if (folder == null || folder.isAccessRestricted()) {
                    z10 = silentNotifications;
                    externalIntent2 = externalIntent;
                    NotificationUpdater.LOG.w("Ignoring push for msgId = " + push.getMessageId());
                    Unit unit = Unit.INSTANCE;
                } else {
                    String threadId = push.getThreadId();
                    if (!zIsMessagingStyleWithIconsSupported || threadId == null) {
                        String messageId = push.getMessageId();
                        NotificationUpdater.LOG.d("Create SingleMessageNotificationCreator for " + messageId);
                        Intrinsics.checkNotNull(messageId);
                        z10 = silentNotifications;
                        externalIntent2 = externalIntent;
                        notificationCreators.put(messageId, new SingleMessageNotificationCreator(this, notificationData, z11, zHasOrdinalFolder, z10, notificationUpdater.displayNotificationChecker, externalIntent2));
                    } else {
                        NotificationCreator messagingStyleNotificationCreator = notificationCreators.get(threadId);
                        if (messagingStyleNotificationCreator == null) {
                            NotificationUpdater.LOG.d("Create MessagingStyleNotificationCreator for " + threadId);
                            messagingStyleNotificationCreator = new MessagingStyleNotificationCreator(z11, silentNotifications, externalIntent);
                            notificationCreators.set(threadId, messagingStyleNotificationCreator);
                        }
                        NotificationCreator notificationCreator = messagingStyleNotificationCreator;
                        String messageId2 = notificationData.getPush().getMessageId();
                        NotificationUpdater.LOG.d("Add msgId = " + messageId2 + " to MessagingStyleNotificationCreator for threadId = " + threadId);
                        Intrinsics.checkNotNull(notificationCreator, "null cannot be cast to non-null type ru.mail.util.push.NotificationUpdater.MessagesNotificationsCreator.MessagingStyleNotificationCreator");
                        ((MessagingStyleNotificationCreator) notificationCreator).addNewMessageToNotification(notificationData);
                        z10 = silentNotifications;
                        externalIntent2 = externalIntent;
                    }
                }
                silentNotifications = z10;
                externalIntent = externalIntent2;
            }
        }

        /* JADX WARN: Code duplicated, block: B:14:0x0068 A[DONT_INVERT] */
        /* JADX WARN: Code duplicated, block: B:15:0x006a A[LOOP:0: B:5:0x0027->B:15:0x006a, LOOP_END] */
        /* JADX WARN: Code duplicated, block: B:18:0x006d A[EDGE_INSN: B:18:0x006d->B:16:0x006d BREAK  A[LOOP:0: B:5:0x0027->B:15:0x006a], SYNTHETIC] */
        @NotNull
        public final ScatterMap<Integer, Notification> create(@NotNull List<Entity.NotificationData> pushList, boolean silentNotifications, @Nullable ExternalIntent externalIntent) {
            Intrinsics.checkNotNullParameter(pushList, "pushList");
            MutableScatterMap<String, NotificationCreator> mutableScatterMap = new MutableScatterMap<>(0, 1, null);
            MutableScatterMap mutableScatterMap2 = new MutableScatterMap(0, 1, null);
            initNotificationCreators(pushList, mutableScatterMap, silentNotifications, externalIntent);
            Object[] objArr = mutableScatterMap.values;
            long[] jArr = mutableScatterMap.metadata;
            int length = jArr.length - 2;
            if (length >= 0) {
                int i10 = 0;
                while (true) {
                    long j10 = jArr[i10];
                    if ((((~j10) << 7) & j10 & (-9187201950435737472L)) == -9187201950435737472L) {
                        if (i10 != length) {
                            break;
                            break;
                        }
                        i10++;
                    } else {
                        int i11 = 8 - ((~(i10 - length)) >>> 31);
                        for (int i12 = 0; i12 < i11; i12++) {
                            if ((255 & j10) < 128) {
                                NotificationCreator notificationCreator = (NotificationCreator) objArr[(i10 << 3) + i12];
                                mutableScatterMap2.put(Integer.valueOf(notificationCreator.getNotificationId()), notificationCreator.createNotification(silentNotifications));
                            }
                            j10 >>= 8;
                        }
                        if (i11 != 8) {
                            break;
                        }
                        if (i10 != length) {
                            break;
                        }
                        i10++;
                    }
                }
            }
            return mutableScatterMap2;
        }

        @Nullable
        public final Bitmap loadImage(@Nullable String url) {
            ImageLoader sharedImageLoader = NotificationUpdater.this.getImageLoaderRepository().getSharedImageLoader();
            ContextWrapper.Companion companion = ContextWrapper.INSTANCE;
            Context context = NotificationUpdater.this.appContext;
            Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
            return sharedImageLoader.loadImageByUrlDirectly(url, companion.toContextWrapper(context));
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0017\u001a\u00020\u0015H\u0002J\b\u0010\u0018\u001a\u00020\u000eH\u0002R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lru/mail/util/push/NotificationUpdater$SummaryNotificationBuilder;", "", "pushList", "", "Lru/mail/util/push/Entity$NotificationData;", "<init>", "(Lru/mail/util/push/NotificationUpdater;Ljava/util/List;)V", "latestPush", "login", "", "kotlin.jvm.PlatformType", "isThreadsEnabled", "", "messageType", "Lru/mail/util/push/PushMessageType;", "getSummaryNotification", "Landroid/app/Notification;", "silent", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "createSummaryNotificationBuilder", "Landroidx/core/app/NotificationCompat$Builder;", "createSummaryNotification", "notificationBuilder", "resolvePushMessageType", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private final class SummaryNotificationBuilder {
        private final boolean isThreadsEnabled;

        @NotNull
        private final Entity.NotificationData latestPush;
        private final String login;

        @NotNull
        private final PushMessageType messageType;

        @NotNull
        private final List<Entity.NotificationData> pushList;
        final /* synthetic */ NotificationUpdater this$0;

        public SummaryNotificationBuilder(@NotNull NotificationUpdater notificationUpdater, List<Entity.NotificationData> pushList) {
            Intrinsics.checkNotNullParameter(pushList, "pushList");
            this.this$0 = notificationUpdater;
            this.pushList = pushList;
            Entity.NotificationData latestPush = NotificationUpdater.INSTANCE.getLatestPush(pushList);
            this.latestPush = latestPush;
            String profileId = latestPush.getPush().getProfileId();
            this.login = profileId;
            this.isThreadsEnabled = MetaThreadUpdater.isThreadsAvailable(notificationUpdater.appContext, profileId);
            this.messageType = resolvePushMessageType();
        }

        private final Notification createSummaryNotification(NotificationCompat.Builder notificationBuilder) {
            String[] mailIds = NotificationUpdater.INSTANCE.getMailIds(this.pushList);
            NotificationCompat.InboxStyle inboxStyleCreate = this.isThreadsEnabled ? new InboxLinesForThreadCreator(this.this$0, this.pushList, notificationBuilder).create() : this.this$0.createMailsInboxLines(notificationBuilder, this.pushList);
            inboxStyleCreate.setSummaryText(this.login);
            inboxStyleCreate.setBigContentTitle(this.this$0.getMultipleMessagesTitle(this.pushList, this.login));
            if (this.messageType == PushMessageType.SUMMARY_MESSAGES_IN_THREAD) {
                int size = this.pushList.size();
                String quantityString = this.this$0.getResources().getQuantityString(ru.mail.mails.R.plurals.stat_messages_title, size, Integer.valueOf(size));
                Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
                notificationBuilder.setContentText(quantityString);
                this.this$0.notificationActionSupplier.addSummarySingleMessageThreadButton(this.latestPush, this.pushList, notificationBuilder, 1073741824);
            } else {
                NotificationActionSupplier notificationActionSupplier = this.this$0.notificationActionSupplier;
                String login = this.login;
                Intrinsics.checkNotNullExpressionValue(login, "login");
                notificationActionSupplier.addSummaryMessagesButtons(login, mailIds, notificationBuilder, 1073741824);
            }
            return inboxStyleCreate.build();
        }

        private final NotificationCompat.Builder createSummaryNotificationBuilder(ExternalIntent externalIntent) {
            if (this.this$0.isDetailedLogEnabled()) {
                NotificationUpdater.LOG.d("Create notification builder createSummaryNotificationBuilder");
            }
            NotificationCompat.Builder publicVersion = new NotificationCompat.Builder(this.this$0.appContext, this.this$0.getChannelId(this.login)).setColor(ContextCompat.getColor(this.this$0.appContext, ru.mail.mails.R.color.contrast_primary)).setVisibility(0).setGroupSummary(true).setGroupAlertBehavior(1).setContentTitle(this.this$0.getMultipleMessagesTitle(this.pushList, this.login)).setContentIntent(this.this$0.getMailNotificationClickIntent(this.pushList, new NotificationMeta(MailItemTransactionCategory.NO_CATEGORIES, this.messageType, false, false, false, this.this$0.getLatestPushIsReminder(this.pushList), 28, null), externalIntent)).setGroup(NotificationUpdater.INSTANCE.getLatestPush(this.pushList).getPush().getProfileId()).setPublicVersion(this.this$0.getNotificationPublicVersion(this.login));
            Intrinsics.checkNotNullExpressionValue(publicVersion, "setPublicVersion(...)");
            return publicVersion;
        }

        private final PushMessageType resolvePushMessageType() {
            return (this.isThreadsEnabled && this.this$0.getThreadsWithPushesCount(this.pushList) == 1) ? PushMessageType.SUMMARY_MESSAGES_IN_THREAD : PushMessageType.SUMMARY_MESSAGES;
        }

        @NotNull
        public final Notification getSummaryNotification(boolean silent, @Nullable ExternalIntent externalIntent) {
            NotificationCompat.Builder builderCreateSummaryNotificationBuilder = createSummaryNotificationBuilder(externalIntent);
            Context context = this.this$0.appContext;
            Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
            String login = this.login;
            Intrinsics.checkNotNullExpressionValue(login, "login");
            builderCreateSummaryNotificationBuilder.setDeleteIntent(NotificationIntentFactory.forSummaryRemoval(context, login, this.messageType));
            this.this$0.setNotificationInfo(builderCreateSummaryNotificationBuilder, this.latestPush.getPush());
            this.this$0.setupNotificationSound(builderCreateSummaryNotificationBuilder, silent);
            if (!this.this$0.hasOrdinalFolder(this.pushList) || this.this$0.displayNotificationChecker.isSafelyDisplayNotification()) {
                Notification notificationBuild = builderCreateSummaryNotificationBuilder.build();
                Intrinsics.checkNotNull(notificationBuild);
                return notificationBuild;
            }
            Notification notificationCreateSummaryNotification = createSummaryNotification(builderCreateSummaryNotificationBuilder);
            Intrinsics.checkNotNull(notificationCreateSummaryNotification);
            return notificationCreateSummaryNotification;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001BK\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\u0004\b\r\u0010\u000eJ\b\u0010\u0013\u001a\u00020\u0014H\u0007J\u0006\u0010\u0015\u001a\u00020\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\f\u001a\u0004\u0018\u00010\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0017"}, d2 = {"Lru/mail/util/push/NotificationUpdater$UpdateNotificationBarMsg;", "", "pushes", "", "Lru/mail/util/push/Entity$NotificationData;", "deletedPushes", "stackingPushes", "Landroidx/collection/ScatterMap;", "", "Landroid/app/Notification;", "profile", "", ErrorBundle.SUMMARY_ENTRY, "<init>", "(Lru/mail/util/push/NotificationUpdater;Ljava/util/List;Ljava/util/List;Landroidx/collection/ScatterMap;Ljava/lang/String;Landroid/app/Notification;)V", "getProfile", "()Ljava/lang/String;", "getSummary", "()Landroid/app/Notification;", "updateNotification", "", "shouldRemoveSummary", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$UpdateNotificationBarMsg\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ScatterMap.kt\nandroidx/collection/ScatterMap\n+ 4 ScatterMap.kt\nandroidx/collection/ScatterMapKt\n*L\n1#1,1568:1\n1869#2,2:1569\n357#3,4:1571\n329#3,6:1575\n339#3,3:1582\n342#3,9:1586\n361#3:1595\n1399#4:1581\n1270#4:1585\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$UpdateNotificationBarMsg\n*L\n839#1:1569,2\n863#1:1571,4\n863#1:1575,6\n863#1:1582,3\n863#1:1586,9\n863#1:1595\n863#1:1581\n863#1:1585\n*E\n"})
    private final class UpdateNotificationBarMsg {

        @NotNull
        private final List<Entity.NotificationData> deletedPushes;

        @NotNull
        private final String profile;

        @NotNull
        private final List<Entity.NotificationData> pushes;

        @Nullable
        private final ScatterMap<Integer, Notification> stackingPushes;

        @Nullable
        private final Notification summary;
        final /* synthetic */ NotificationUpdater this$0;

        public UpdateNotificationBarMsg(@NotNull NotificationUpdater notificationUpdater, @NotNull List<Entity.NotificationData> pushes, @Nullable List<Entity.NotificationData> deletedPushes, @NotNull ScatterMap<Integer, Notification> scatterMap, @Nullable String profile, Notification notification) {
            Intrinsics.checkNotNullParameter(pushes, "pushes");
            Intrinsics.checkNotNullParameter(deletedPushes, "deletedPushes");
            Intrinsics.checkNotNullParameter(profile, "profile");
            this.this$0 = notificationUpdater;
            this.pushes = pushes;
            this.deletedPushes = deletedPushes;
            this.stackingPushes = scatterMap;
            this.profile = profile;
            this.summary = notification;
        }

        @NotNull
        public final String getProfile() {
            return this.profile;
        }

        @Nullable
        public final Notification getSummary() {
            return this.summary;
        }

        public final boolean shouldRemoveSummary() {
            return this.pushes.isEmpty();
        }

        @SuppressLint({"MissingPermission"})
        public final void updateNotification() {
            Notification notification;
            if (shouldRemoveSummary()) {
                this.this$0.notificationManager.cancel(this.profile, 1073741824);
            }
            List<Entity.NotificationData> list = this.deletedPushes;
            NotificationUpdater notificationUpdater = this.this$0;
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                notificationUpdater.notificationManager.cancel(this.profile, ((Entity.NotificationData) it.next()).getNotificationId());
            }
            if (this.pushes.isEmpty()) {
                NotificationUpdater.LOG.w("Update notification with empty pushes");
                return;
            }
            if (!this.this$0.notificationManager.areNotificationsEnabled()) {
                NotificationUpdater.LOG.w("Update notification failed. Notifications are disabled");
                return;
            }
            if (!this.this$0.needShowNotification(this.profile)) {
                return;
            }
            if (this.pushes.size() != this.deletedPushes.size() && (notification = this.summary) != null) {
                NotificationUpdater notificationUpdater2 = this.this$0;
                notificationUpdater2.notifyWithRetry(this.profile, 1073741824, notification, 0, notificationUpdater2.notificationManager);
            }
            ScatterMap<Integer, Notification> scatterMap = this.stackingPushes;
            if (scatterMap == null) {
                return;
            }
            NotificationUpdater notificationUpdater3 = this.this$0;
            Object[] objArr = scatterMap.keys;
            Object[] objArr2 = scatterMap.values;
            long[] jArr = scatterMap.metadata;
            int length = jArr.length - 2;
            if (length < 0) {
                return;
            }
            int i10 = 0;
            while (true) {
                long j10 = jArr[i10];
                if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                    int i11 = 8 - ((~(i10 - length)) >>> 31);
                    for (int i12 = 0; i12 < i11; i12++) {
                        if ((255 & j10) < 128) {
                            int i13 = (i10 << 3) + i12;
                            Object obj = objArr[i13];
                            notificationUpdater3.notifyWithLog(notificationUpdater3.notificationManager, this.profile, ((Number) obj).intValue(), (Notification) objArr2[i13]);
                        }
                        j10 >>= 8;
                    }
                    if (i11 != 8) {
                        return;
                    }
                }
                if (i10 == length) {
                    return;
                } else {
                    i10++;
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ7\u0010\u000f\u001a\u0004\u0018\u0001H\u0010\"\u0004\b\u0000\u0010\u00102\u0010\u0010\u0011\u001a\f\u0012\u0002\b\u0003\u0012\u0004\u0012\u0002H\u00100\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0014¢\u0006\u0002\u0010\u0017J\"\u0010\u0018\u001a\u00020\u00052\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\t2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\tJ\u0016\u0010\u001c\u001a\u00020\u00072\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001a0\tH\u0002¨\u0006\u001d"}, d2 = {"Lru/mail/util/push/NotificationUpdater$UpdateNotificationCommand;", "Lru/mail/mailbox/cmd/CommandGroup;", "<init>", "(Lru/mail/util/push/NotificationUpdater;)V", "addUpdatePushesCommand", "", "profile", "", "deletedPushes", "", "Lru/mail/util/push/NewMailPush;", "silent", "", "externalIntent", "Lru/mail/android_utils/intent/ExternalIntent;", "onExecuteCommand", RequestConfiguration.MAX_AD_CONTENT_RATING_T, IMAPStore.ID_COMMAND, "Lru/mail/mailbox/cmd/Command;", "priority", "Lru/mail/mailbox/cmd/Priority;", "selector", "Lru/mail/mailbox/cmd/ExecutorSelector;", "(Lru/mail/mailbox/cmd/Command;Lru/mail/mailbox/cmd/Priority;Lru/mail/mailbox/cmd/ExecutorSelector;)Ljava/lang/Object;", "logUpdateCommand", "pushes", "Lru/mail/util/push/Entity$NotificationData;", "deletedEntities", "buildPushesMessage", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nNotificationUpdater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$UpdateNotificationCommand\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater\n*L\n1#1,1568:1\n774#2:1569\n865#2,2:1570\n1878#2,3:1574\n714#3,2:1572\n716#3,3:1577\n*S KotlinDebug\n*F\n+ 1 NotificationUpdater.kt\nru/mail/util/push/NotificationUpdater$UpdateNotificationCommand\n*L\n765#1:1569\n765#1:1570,2\n813#1:1574,3\n811#1:1572,2\n811#1:1577,3\n*E\n"})
    final class UpdateNotificationCommand extends CommandGroup {
        public UpdateNotificationCommand() {
        }

        private final String buildPushesMessage(List<Entity.NotificationData> pushes) {
            int size = pushes.size() - 1;
            NotificationUpdater notificationUpdater = NotificationUpdater.this;
            notificationUpdater.notificationLogBuilder.setLength(0);
            StringBuilder sb2 = notificationUpdater.notificationLogBuilder;
            sb2.append("[");
            int i10 = 0;
            for (Object obj : pushes) {
                int i11 = i10 + 1;
                if (i10 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                Entity.NotificationData notificationData = (Entity.NotificationData) obj;
                if (i10 == size) {
                    sb2.append(notificationData.getPush().getMessageId());
                } else {
                    sb2.append(notificationData.getPush().getMessageId());
                    sb2.append(AutoUploadSettingsContract.CUSTOM_FOLDER_PARSING_DIVIDER);
                }
                i10 = i11;
            }
            sb2.append("]");
            String string = notificationUpdater.notificationLogBuilder.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            notificationUpdater.notificationLogBuilder.setLength(0);
            return string;
        }

        public final void addUpdatePushesCommand(@NotNull String profile, @NotNull List<? extends NewMailPush> deletedPushes, boolean silent, @Nullable ExternalIntent externalIntent) {
            Intrinsics.checkNotNullParameter(profile, "profile");
            Intrinsics.checkNotNullParameter(deletedPushes, "deletedPushes");
            List pushTypes = NotificationUpdater.this.getPushTypes();
            Context context = NotificationUpdater.this.appContext;
            Intrinsics.checkNotNullExpressionValue(context, "access$getAppContext$p(...)");
            addCommand(new UpdatePushesCommand(pushTypes, context, profile, silent, externalIntent, deletedPushes));
        }

        public final void logUpdateCommand(@NotNull List<Entity.NotificationData> pushes, @NotNull List<Entity.NotificationData> deletedEntities) {
            Intrinsics.checkNotNullParameter(pushes, "pushes");
            Intrinsics.checkNotNullParameter(deletedEntities, "deletedEntities");
            if (!pushes.isEmpty()) {
                String strBuildPushesMessage = buildPushesMessage(pushes);
                NotificationUpdater.LOG.d("Push to show for msgId = " + strBuildPushesMessage);
            }
            if (deletedEntities.isEmpty()) {
                return;
            }
            String strBuildPushesMessage2 = buildPushesMessage(deletedEntities);
            NotificationUpdater.LOG.d("Push to cancel for msgId = " + strBuildPushesMessage2);
        }

        @Override // ru.mail.mailbox.cmd.CommandGroup
        @Nullable
        protected <T> T onExecuteCommand(@NotNull Command<?, T> command, @NotNull Priority priority, @NotNull ExecutorSelector selector) {
            Intrinsics.checkNotNullParameter(command, "command");
            Intrinsics.checkNotNullParameter(priority, "priority");
            Intrinsics.checkNotNullParameter(selector, "selector");
            T t10 = (T) super.onExecuteCommand(command, priority, selector);
            if ((command instanceof UpdatePushesCommand) && (t10 instanceof CommandStatus.OK)) {
                UpdatePushesCommand updatePushesCommand = (UpdatePushesCommand) command;
                boolean silent = updatePushesCommand.getSilent();
                String profile = updatePushesCommand.getProfile();
                List<Entity.NotificationData> pushes = updatePushesCommand.getPushes();
                NotificationUpdater notificationUpdater = NotificationUpdater.this;
                ArrayList arrayList = new ArrayList();
                for (T t11 : pushes) {
                    if (((Boolean) notificationUpdater.shouldPublishNewMailNotification.invoke(((Entity.NotificationData) t11).getPush())).booleanValue()) {
                        arrayList.add(t11);
                    }
                }
                List<Entity.NotificationData> list = CollectionsKt.toList(arrayList);
                ExternalIntent externalIntent = updatePushesCommand.getExternalIntent();
                List<Entity.NotificationData> list2 = CollectionsKt.toList(updatePushesCommand.getDeletedEntities());
                ScatterMap stackingNotifications = list.isEmpty() ? null : NotificationUpdater.this.getStackingNotifications(list, silent, externalIntent);
                Notification summaryNotification = list.size() >= 2 ? new SummaryNotificationBuilder(NotificationUpdater.this, list).getSummaryNotification(silent, externalIntent) : null;
                logUpdateCommand(list, list2);
                setResult(new CommandStatus.OK(new UpdateNotificationBarMsg(NotificationUpdater.this, list, list2, stackingNotifications, profile, summaryNotification)));
            }
            return t10;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\b\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"Lru/mail/util/push/NotificationUpdater$WaitingTask;", "Ljava/util/TimerTask;", "<init>", "()V", "run", "", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    private static final class WaitingTask extends TimerTask {
        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            NotificationUpdater.realNotify.compareAndSet(false, true);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public NotificationUpdater(@NotNull Context context, @NotNull NotificationConfiguration notificationConfiguration) {
        this(context, notificationConfiguration, null, null, 12, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationConfiguration, "notificationConfiguration");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$0(NewMailPush it) {
        Intrinsics.checkNotNullParameter(it, "it");
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final AvatarLoader avatarLoader_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        Context appContext = notificationUpdater.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        AvatarLoader avatarLoader = new AvatarLoader(appContext, notificationUpdater.displayNotificationChecker);
        avatarLoader.load();
        return avatarLoader;
    }

    private final Intent buildIntent(Bundle extras, AckPushProxyHandler.AllowedPushAction action) {
        Intent intentCreateInternalIntent = getNavigator().createInternalIntent(action.getAction());
        intentCreateInternalIntent.addCategory("android.intent.category.DEFAULT");
        intentCreateInternalIntent.addFlags(335544320);
        intentCreateInternalIntent.putExtras(extras);
        Intrinsics.checkNotNullExpressionValue(intentCreateInternalIntent, "apply(...)");
        return intentCreateInternalIntent;
    }

    private final String buildLogString(Function1<? super StringBuilder, Unit> buildString) {
        this.notificationLogBuilder.setLength(0);
        buildString.invoke(this.notificationLogBuilder);
        String string = this.notificationLogBuilder.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        this.notificationLogBuilder.setLength(0);
        return string;
    }

    private final Person buildPerson(Function1<? super Person.Builder, Unit> buildPerson) {
        this.personBuilder.setIcon(null);
        this.personBuilder.setName(null);
        buildPerson.invoke(this.personBuilder);
        Person personBuild = this.personBuilder.build();
        Intrinsics.checkNotNullExpressionValue(personBuild, "build(...)");
        this.personBuilder.setIcon(null);
        this.personBuilder.setName(null);
        return personBuild;
    }

    private final MailThreadRepresentation createMTRFromPushes(NewMailPush latestPush) {
        MailThreadRepresentation mailThreadRepresentation = new MailThreadRepresentation();
        MailThread mailThread = new MailThread();
        mailThread.setId(latestPush.getThreadId());
        mailThread.setAccountName(latestPush.getProfileId());
        mailThreadRepresentation.setMailThread(mailThread);
        mailThreadRepresentation.setFolderId(latestPush.getFolderId());
        mailThreadRepresentation.setLastMessage(createMailMessage(latestPush));
        return mailThreadRepresentation;
    }

    private final MailMessage createMailMessage(NewMailPush latestPush) {
        MailMessage mailMessage = new PushMessageParser(this.appContext, isColoredTagsOn()).parse(latestPush);
        Intrinsics.checkNotNullExpressionValue(mailMessage, "parse(...)");
        return mailMessage;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationCompat.InboxStyle createMailsInboxLines(NotificationCompat.Builder notificationBuilder, List<Entity.NotificationData> pushList) {
        NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle(notificationBuilder);
        boolean zPushesCountSameAsMaxNotificationsLines = INSTANCE.pushesCountSameAsMaxNotificationsLines(pushList);
        int i10 = 0;
        for (int i11 = 0; i11 < pushList.size() && i10 < (zPushesCountSameAsMaxNotificationsLines ? 1 : 0) + 5; i11++) {
            Entity.NotificationData notificationData = pushList.get(i11);
            inboxStyle.addLine(getNotificationMultipleLine(getTitle(notificationData), getSubjectText(notificationData), getSnippetText(notificationData)));
            i10++;
        }
        if (i10 < pushList.size()) {
            inboxStyle.addLine(getAndMoreLine(pushList.size() - i10));
        }
        return inboxStyle;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final NotificationCompat.Builder createOneMessageBuilder(Entity.NotificationData push) {
        if (isDetailedLogEnabled()) {
            LOG.d("Create notification builder createOneMessageBuilder");
        }
        String profileId = push.getPush().getProfileId();
        NotificationCompat.Builder publicVersion = new NotificationCompat.Builder(this.appContext, getChannelId(profileId)).setColor(ContextCompat.getColor(this.appContext, ru.mail.mails.R.color.contrast_primary)).setVisibility(0).setSmallIcon(ru.mail.mails.R.drawable.ic_status_bar).setContentTitle(getTitle(push)).setContentText(getSubjectText(push)).setPublicVersion(getNotificationPublicVersion(profileId));
        Intrinsics.checkNotNullExpressionValue(publicVersion, "setPublicVersion(...)");
        return publicVersion;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CharSequence getAndMoreLine(int andMoreNumber) {
        String string = getResources().getString(ru.mail.mails.R.string.and_more, String.valueOf(andMoreNumber));
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        String string2 = getResources().getString(ru.mail.mails.R.string.notification_multiple_line, TextUtils.htmlEncode(string), "");
        Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
        Spanned spannedFromHtml = HtmlCompat.fromHtml(string2, 63);
        Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
        return spannedFromHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AsserterConfigFactory getAsserterConfigFactory() {
        return (AsserterConfigFactory) this.asserterConfigFactory.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AvatarLoader getAvatarLoader() {
        return (AvatarLoader) this.avatarLoader.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getChannelId(String login) {
        String newMessageChannelId = this.notificationChannels.getNewMessageChannelId(login);
        return newMessageChannelId == null ? "" : newMessageChannelId;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ImageLoaderRepository getImageLoaderRepository() {
        return (ImageLoaderRepository) this.imageLoaderRepository.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean getLatestPushIsReminder(List<Entity.NotificationData> notificationsData) {
        if (notificationsData.isEmpty()) {
            return false;
        }
        return INSTANCE.getLatestPush(notificationsData).getPush().isReminder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final LogCollector getLogCollector() {
        return (LogCollector) this.logCollector.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final PendingIntent getMailNotificationClickIntent(List<Entity.NotificationData> messages, NotificationMeta notificationMeta, ExternalIntent externalIntent) {
        AckPushProxyHandler.AllowedPushAction allowedPushAction;
        Intent intentBuildIntent;
        boolean z10 = messages.size() == 1;
        NewMailPush push = messages.get(0).getPush();
        Bundle bundle = new Bundle();
        boolean zIsThreadsAvailable = MetaThreadUpdater.isThreadsAvailable(this.appContext, push.getProfileId());
        Parcelable parcelableCreateFromPush = HeaderInfoBuilder.createFromPush(push);
        if (!zIsThreadsAvailable || push.getThreadId() == null) {
            allowedPushAction = z10 ? AckPushProxyHandler.AllowedPushAction.SHOW_MESSAGE : AckPushProxyHandler.AllowedPushAction.SHOW_MESSAGES_IN_FOLDER;
        } else if (!isSingleThread(messages)) {
            allowedPushAction = AckPushProxyHandler.AllowedPushAction.SHOW_MESSAGES_IN_FOLDER;
        } else if (z10) {
            allowedPushAction = AckPushProxyHandler.AllowedPushAction.SHOW_THREAD_MESSAGE;
        } else {
            Parcelable mailThreadRepresentation = getMailThreadRepresentation(push);
            AckPushProxyHandler.AllowedPushAction allowedPushAction2 = AckPushProxyHandler.AllowedPushAction.SHOW_THREAD;
            bundle.putParcelable(ThreadMessagesActivity.EXT_THREAD_REPRESENTATION, mailThreadRepresentation);
            bundle.putBoolean(EXTRA_FROM_PUSH, true);
            allowedPushAction = allowedPushAction2;
        }
        bundle.putParcelable(NotificationIntentFactory.EXTRA_MAIL_HEADER_INFO, parcelableCreateFromPush);
        bundle.putSerializable(AckPushProxyHandler.PROXY_PUSH_ACTION_PARAM, allowedPushAction);
        bundle.putStringArrayList(AckPushProxyHandler.ACKNOWLEDGE_URL_PARAM, getPushesOpenUrls(messages));
        bundle.putLongArray(AckPushProxyHandler.PUSH_ME_SDK_PUSH_IDS, getPushMeSdkIdentifiers(messages));
        bundle.putSerializable(MailServiceImpl.EXTRA_NOTIFICATION_META, notificationMeta);
        if (externalIntent == null || (intentBuildIntent = externalIntent.applyActionIntent(buildIntent(bundle, allowedPushAction))) == null) {
            intentBuildIntent = buildIntent(bundle, allowedPushAction);
        }
        Intent intent = intentBuildIntent;
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        return PendingIntentCreator.getActivity$default(appContext, getPendingIntentRequestCode(push), intent, PendingIntentUtils.Companion.getPendingIntentFlags$default(PendingIntentUtils.INSTANCE, false, 1, null) | 1073741824, false, 16, null);
    }

    private final MailThreadRepresentation getMailThreadRepresentation(NewMailPush latestPush) {
        MailThreadRepresentation mailThreadRepresentationSelectMTRFromDatabase = selectMTRFromDatabase(latestPush);
        if (mailThreadRepresentationSelectMTRFromDatabase == null) {
            mailThreadRepresentationSelectMTRFromDatabase = createMTRFromPushes(latestPush);
        }
        mailThreadRepresentationSelectMTRFromDatabase.setUnread(true);
        return mailThreadRepresentationSelectMTRFromDatabase;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getMultipleMessagesTitle(List<Entity.NotificationData> pushList, String login) {
        if (!MetaThreadUpdater.isThreadsAvailable(this.appContext, login) || !hasMultipleMessagesInThreads(pushList)) {
            return getResources().getQuantityString(ru.mail.mails.R.plurals.stat_messages_title, pushList.size(), Integer.valueOf(pushList.size()));
        }
        int threadsWithPushesCount = getThreadsWithPushesCount(pushList);
        if (threadsWithPushesCount <= 1) {
            return getSubjectText(INSTANCE.getLatestPush(pushList));
        }
        String quantityString = getResources().getQuantityString(ru.mail.mails.R.plurals.stat_messages_title, pushList.size(), Integer.valueOf(pushList.size()));
        Intrinsics.checkNotNullExpressionValue(quantityString, "getQuantityString(...)");
        String quantityString2 = getResources().getQuantityString(ru.mail.mails.R.plurals.messages_in_threads_title, threadsWithPushesCount, Integer.valueOf(threadsWithPushesCount));
        Intrinsics.checkNotNullExpressionValue(quantityString2, "getQuantityString(...)");
        return getResources().getString(ru.mail.mails.R.string.push_with_threads_title, quantityString, quantityString2);
    }

    private final Navigator getNavigator() {
        return (Navigator) this.navigator.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CharSequence getNotificationFormattedTitle(String sender, String subject) {
        String string = getResources().getString(ru.mail.mails.R.string.notification_formatted_title, TextUtils.htmlEncode(sender), subject);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Spanned spannedFromHtml = HtmlCompat.fromHtml(string, 63);
        Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
        return spannedFromHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final CharSequence getNotificationMultipleLine(String sender, String subject, String snippet) {
        String strHtmlEncode = TextUtils.htmlEncode(sender);
        String string = getResources().getString(ru.mail.mails.R.string.new_notification_multiple_line, TextUtils.htmlEncode(subject), strHtmlEncode, snippet);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        Spanned spannedFromHtml = HtmlCompat.fromHtml(StringsKt.trim((CharSequence) string).toString(), 63);
        Intrinsics.checkNotNullExpressionValue(spannedFromHtml, "fromHtml(...)");
        return spannedFromHtml;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Notification getNotificationPublicVersion(String login) {
        if (isDetailedLogEnabled()) {
            LOG.d("Create notification builder getNotificationPublicVersion");
        }
        Notification notificationBuild = new NotificationCompat.Builder(this.appContext, getChannelId(login)).setContentTitle(getResources().getString(ru.mail.mails.R.string.push_private_title)).setContentText(getResources().getString(ru.mail.mails.R.string.push_private_text)).setColor(ContextCompat.getColor(this.appContext, ru.mail.mails.R.color.contrast_primary)).setSmallIcon(ru.mail.mails.R.drawable.ic_status_bar).build();
        Intrinsics.checkNotNullExpressionValue(notificationBuild, "build(...)");
        return notificationBuild;
    }

    private final int getPendingIntentRequestCode(NewMailPush message) {
        String customSubject;
        int iHashCode = 0;
        int iHashCode2 = (((((message.getMessageId() != null ? message.getMessageId().hashCode() : 0) * 31) + (message.getProfileId() != null ? message.getProfileId().hashCode() : 0)) * 31) + message.getEventId()) * 31;
        if (message.getCustomSubject() != null && (customSubject = message.getCustomSubject()) != null) {
            iHashCode = customSubject.hashCode();
        }
        return iHashCode2 + iHashCode;
    }

    private final long[] getPushMeSdkIdentifiers(List<Entity.NotificationData> messages) {
        MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
        Iterator<Entity.NotificationData> it = messages.iterator();
        while (it.hasNext()) {
            Long pushMeSdkPushId = it.next().getPush().getPushMeSdkPushId();
            if (pushMeSdkPushId != null) {
                mutableObjectList.add(pushMeSdkPushId);
            }
        }
        long[] array = Longs.toArray(mutableObjectList.asList());
        Intrinsics.checkNotNullExpressionValue(array, "toArray(...)");
        return array;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<PushConfigurationType> getPushTypes() {
        return (List) this.pushTypes.getValue();
    }

    private final ArrayList<String> getPushesOpenUrls(List<Entity.NotificationData> messages) {
        MutableObjectList mutableObjectList = new MutableObjectList(0, 1, null);
        Iterator<Entity.NotificationData> it = messages.iterator();
        while (it.hasNext()) {
            String openUrl = it.next().getPush().getOpenUrl();
            if (openUrl != null) {
                mutableObjectList.add(openUrl);
            }
        }
        return new ArrayList<>(mutableObjectList.asList());
    }

    private final RequestArbiter getRequestArbiter() {
        return (RequestArbiter) this.requestArbiter.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Resources getResources() {
        Resources resources = this.appContext.getResources();
        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
        return resources;
    }

    private final SelectMTRCmd getSelectThreadRepresentationCommand() {
        return (SelectMTRCmd) this.selectThreadRepresentationCommand.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getSnippetText(Entity.NotificationData push) {
        String snippet = push.getPush().getSnippet();
        if (snippet == null || StringsKt.isBlank(snippet) || this.displayNotificationChecker.isSafelyDisplayNotification()) {
            return "";
        }
        if (push.getFolder() == null || !push.getFolder().isAccessRestricted()) {
            return snippet;
        }
        Resources resources = getResources();
        int i10 = ru.mail.mails.R.string.push_lockf_mess;
        MailBoxFolder folder = push.getFolder();
        Context appContext = this.appContext;
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        String string = resources.getString(i10, folder.getName(appContext));
        Intrinsics.checkNotNull(string);
        return string;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ScatterMap<Integer, Notification> getStackingNotifications(List<Entity.NotificationData> pushList, boolean silent, ExternalIntent externalIntent) {
        return this.messagesNotificationsCreator.create(pushList, silent, externalIntent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final int getThreadsWithPushesCount(List<Entity.NotificationData> pushList) {
        int i10 = 0;
        MutableScatterSet mutableScatterSet = new MutableScatterSet(0, 1, null);
        Iterator<T> it = pushList.iterator();
        while (it.hasNext()) {
            String threadId = ((Entity.NotificationData) it.next()).getPush().getThreadId();
            if (threadId != null && !mutableScatterSet.contains(threadId)) {
                i10++;
                mutableScatterSet.add(threadId);
            } else if (threadId == null) {
                i10++;
            }
        }
        return i10;
    }

    private final UpdateNotificationCommand getUpdateNotificationCommand() {
        return (UpdateNotificationCommand) this.updateNotificationCommand.getValue();
    }

    private final boolean hasMultipleMessagesInThreads(List<Entity.NotificationData> messages) {
        return !isAllSingleMessageInThreads(messages);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasOrdinalFolder(List<Entity.NotificationData> pushList) {
        for (Entity.NotificationData notificationData : pushList) {
            if (notificationData.getFolder() != null && !notificationData.getFolder().isAccessRestricted()) {
                return true;
            }
        }
        return false;
    }

    private final boolean isColoredTagsOn() {
        return ((Boolean) this.isColoredTagsOn.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isColoredTagsOn_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        return notificationUpdater.configurationRepository.getConfiguration().getColoredTagsConfig().getEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isDetailedLogEnabled() {
        return ((Boolean) this.isDetailedLogEnabled.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isDetailedLogEnabled_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        return notificationUpdater.configurationRepository.getConfiguration().isNotificationDetailedLogEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isMessagingStyleWithIconsSupported() {
        return ((Boolean) this.isMessagingStyleWithIconsSupported.getValue()).booleanValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMessagingStyleWithIconsSupported_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        return notificationUpdater.configurationRepository.getConfiguration().getUseMessageStyleNotification() && SdkUtils.hasPie();
    }

    private final boolean isSingleThread(List<Entity.NotificationData> messages) {
        String threadId = messages.get(0).getPush().getThreadId();
        if (threadId == null) {
            return false;
        }
        Iterator<Entity.NotificationData> it = messages.subList(1, messages.size()).iterator();
        while (it.hasNext()) {
            if (!Intrinsics.areEqual(threadId, it.next().getPush().getThreadId())) {
                return false;
            }
        }
        return true;
    }

    private final /* synthetic */ <T> Lazy<T> lazyLocate() {
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        Intrinsics.needClassReification();
        return LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<T>() { // from class: ru.mail.util.push.NotificationUpdater.lazyLocate.1
            @Override // kotlin.jvm.functions.Function0
            public final T invoke() {
                Locator.Companion companion = Locator.INSTANCE;
                Context context = NotificationUpdater.this.appContext;
                Intrinsics.reifiedOperationMarker(4, RequestConfiguration.MAX_AD_CONTENT_RATING_T);
                return (T) companion.locate(context, Object.class);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean needShowNotification(String profile) {
        if (MailboxProfileUtils.isUnauthorized(profile, this.accountManager)) {
            LOG.w("No need to show notification. Account unauthorized");
            return false;
        }
        if (!MailboxProfileUtils.isAccountDeleted(this.accountManager, profile)) {
            return true;
        }
        LOG.w("No need to show notification. Account deleted");
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"MissingPermission"})
    public final void notifyWithLog(NotificationManagerCompat notificationManager, String tag, int id2, Notification notification) {
        notificationManager.notify(tag, id2, notification);
        if (isDetailedLogEnabled()) {
            this.notificationLogBuilder.setLength(0);
            StringBuilder sb2 = this.notificationLogBuilder;
            sb2.append("Show notification: ");
            sb2.append(notification);
            NotificationChannelGroup notificationChannelGroup = null;
            NotificationChannel notificationChannel = notification.getChannelId() != null ? notificationManager.getNotificationChannel(notification.getChannelId()) : null;
            if (notificationChannel != null && notificationChannel.getGroup() != null) {
                notificationChannelGroup = notificationManager.getNotificationChannelGroup(notificationChannel.getGroup());
            }
            sb2.append("\nat channel: ");
            sb2.append(notificationChannel);
            sb2.append("\nat group: ");
            sb2.append(notificationChannelGroup);
            String string = this.notificationLogBuilder.toString();
            Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
            this.notificationLogBuilder.setLength(0);
            LOG.d(string);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void notifyWithRetry(String tag, int id2, Notification notification, int retryCount, NotificationManagerCompat notificationManager) {
        try {
            notifyWithLog(notificationManager, tag, id2, notification);
        } catch (Exception e10) {
            LOG.e("Notify failed", e10);
            String message = e10.getMessage();
            Intrinsics.checkNotNull(message);
            onNotifyOnPushFailed(id2, tag, notification, message, retryCount, notificationManager);
        }
    }

    private final void onNotifyOnPushFailed(final int id2, String tag, Notification notification, String errorMessage, int retryCount, final NotificationManagerCompat notificationManager) {
        NotificationUpdater notificationUpdater;
        final String str;
        final Notification notification2;
        final int i10;
        if (retryCount <= 5) {
            notificationUpdater = this;
            str = tag;
            notification2 = notification;
            i10 = retryCount;
            this.handler.postDelayed(new Runnable() { // from class: ru.mail.util.push.NotificationUpdater.onNotifyOnPushFailed.1
                @Override // java.lang.Runnable
                public void run() {
                    if (NotificationUpdater.this.needShowNotification(str)) {
                        NotificationUpdater.this.notifyWithRetry(str, id2, notification2, i10 + 1, notificationManager);
                    }
                }
            }, 2000L);
        } else {
            notificationUpdater = this;
            str = tag;
            notification2 = notification;
            i10 = retryCount;
        }
        MailAppAnalytics mailAppAnalyticsAnalytics = MailAppDependencies.analytics(notificationUpdater.appContext);
        String string = notification2.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        mailAppAnalyticsAnalytics.sendNotifyOnPushFailedAnalytics(str, string, errorMessage, i10);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List pushTypes_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        List<PushConfigurationType> pushTypes = notificationUpdater.configurationRepository.getConfiguration().getPushTypes();
        ArrayList arrayList = new ArrayList();
        for (Object obj : pushTypes) {
            if (((PushConfigurationType) obj).getType() == PushMessageType.SINGLE_MESSAGE) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }

    private final MailThreadRepresentation selectMTRFromDatabase(NewMailPush latestPush) {
        try {
            SelectMTRCmd selectThreadRepresentationCommand = getSelectThreadRepresentationCommand();
            selectThreadRepresentationCommand.addSelectMTRCommand(this.appContext, latestPush.getThreadId(), latestPush.getFolderId());
            selectThreadRepresentationCommand.execute(getRequestArbiter()).getOrThrow();
            return getSelectThreadRepresentationCommand().getRepresentation();
        } catch (InterruptedException e10) {
            LOG.e("Unable to execute SelectMTRCmd command", e10);
            return null;
        } catch (ExecutionException e11) {
            LOG.e("Unable to execute SelectMTRCmd command", e11);
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SelectMTRCmd selectThreadRepresentationCommand_delegate$lambda$0() {
        return new SelectMTRCmd();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setNotificationInfo(NotificationCompat.Builder notificationBuilder, NewMailPush push) {
        String profileId = push.getProfileId();
        if (!this.displayNotificationChecker.isSafelyDisplayNotification()) {
            notificationBuilder.setSubText(profileId);
        }
        notificationBuilder.setSmallIcon(ru.mail.mails.R.drawable.ic_status_bar).setWhen(push.getTimestamp()).setAutoCancel(true).setLights(this.notificationConfiguration.getLightColor(), this.notificationConfiguration.getLightTimeOn(), this.notificationConfiguration.getLightTimeOff());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setupNotificationSound(NotificationCompat.Builder builder, boolean silent) {
        if (!silent) {
            AtomicBoolean atomicBoolean = realNotify;
            if (atomicBoolean.get()) {
                atomicBoolean.compareAndSet(true, false);
                myTimer.schedule(new WaitingTask(), 5000L);
                builder.setOnlyAlertOnce(false);
                return;
            }
        }
        builder.setOnlyAlertOnce(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final UpdateNotificationCommand updateNotificationCommand_delegate$lambda$0(NotificationUpdater notificationUpdater) {
        return notificationUpdater.new UpdateNotificationCommand();
    }

    public static /* synthetic */ void updateNotificationsBar$default(NotificationUpdater notificationUpdater, String str, List list, boolean z10, ExternalIntent externalIntent, int i10, Object obj) {
        if ((i10 & 8) != 0) {
            externalIntent = null;
        }
        notificationUpdater.updateNotificationsBar(str, list, z10, externalIntent);
    }

    @NotNull
    public final String getSubjectText(@NotNull Entity.NotificationData push) {
        Intrinsics.checkNotNullParameter(push, "push");
        if (this.displayNotificationChecker.isSafelyDisplayNotification()) {
            String string = getResources().getString(ru.mail.mails.R.string.push_private_text);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (push.getFolder() != null && push.getFolder().isAccessRestricted()) {
            Resources resources = getResources();
            int i10 = ru.mail.mails.R.string.push_lockf_mess;
            MailBoxFolder folder = push.getFolder();
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            String string2 = resources.getString(i10, folder.getName(appContext));
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        }
        String customSubject = push.getPush().getCustomSubject();
        if (customSubject != null && customSubject.length() != 0) {
            return customSubject;
        }
        String subject = push.getPush().getSubject();
        if (subject != null && subject.length() != 0) {
            return subject;
        }
        String string3 = getResources().getString(ru.mail.mails.R.string.mailbox_mailmessage_empty_subject);
        Intrinsics.checkNotNull(string3);
        return string3;
    }

    @NotNull
    public final String getTitle(@NotNull Entity.NotificationData push) {
        Intrinsics.checkNotNullParameter(push, "push");
        if (this.displayNotificationChecker.isSafelyDisplayNotification()) {
            String string = getResources().getString(ru.mail.mails.R.string.push_private_title);
            Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
            return string;
        }
        if (push.getFolder() != null && push.getFolder().isAccessRestricted()) {
            String string2 = getResources().getString(ru.mail.mails.R.string.push_lockf_title);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(...)");
            return string2;
        }
        String customSender = push.getPush().getCustomSender();
        if (customSender != null && customSender.length() != 0) {
            return customSender;
        }
        String sender = push.getPush().getSender();
        if (sender == null || sender.length() == 0) {
            String string3 = getResources().getString(ru.mail.mails.R.string.from_is_empty);
            Intrinsics.checkNotNull(string3);
            return string3;
        }
        Rfc822Token[] rfc822TokenArr = Rfc822Tokenizer.tokenize(sender);
        if (!(rfc822TokenArr.length == 0)) {
            String name = rfc822TokenArr[0].getName();
            Intrinsics.checkNotNullExpressionValue(name, "getName(...)");
            if (name.length() > 0) {
                String name2 = rfc822TokenArr[0].getName();
                Intrinsics.checkNotNull(name2);
                return name2;
            }
        }
        if (rfc822TokenArr.length == 0) {
            return sender;
        }
        String address = rfc822TokenArr[0].getAddress();
        Intrinsics.checkNotNullExpressionValue(address, "getAddress(...)");
        if (address.length() <= 0) {
            return sender;
        }
        String address2 = rfc822TokenArr[0].getAddress();
        Intrinsics.checkNotNull(address2);
        return address2;
    }

    public final void hideSummaryNotification(@NotNull String profileId) {
        Intrinsics.checkNotNullParameter(profileId, "profileId");
        this.handler.post(new HideSummaryNotifications(this.notificationManager, profileId));
    }

    public final boolean isAllSingleMessageInThreads(@NotNull List<Entity.NotificationData> messages) {
        Intrinsics.checkNotNullParameter(messages, "messages");
        MutableScatterSet mutableScatterSet = new MutableScatterSet(0, 1, null);
        Iterator<T> it = messages.iterator();
        int i10 = 0;
        while (it.hasNext()) {
            String threadId = ((Entity.NotificationData) it.next()).getPush().getThreadId();
            mutableScatterSet.add(threadId);
            i10 += threadId == null ? 1 : 0;
        }
        List listSingletonList = Collections.singletonList(null);
        Intrinsics.checkNotNullExpressionValue(listSingletonList, "singletonList(...)");
        mutableScatterSet.removeAll(listSingletonList);
        return mutableScatterSet.get_size() + i10 == messages.size();
    }

    public final void updateNotificationBarSilently(@NotNull String profile, @NotNull List<? extends NewMailPush> deletedPushes) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(deletedPushes, "deletedPushes");
        updateNotificationsBar$default(this, profile, deletedPushes, true, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void updateNotificationsBar(@NotNull String profile, @NotNull List<? extends NewMailPush> deletedPushes, boolean silent, @Nullable ExternalIntent externalIntent) {
        Intrinsics.checkNotNullParameter(profile, "profile");
        Intrinsics.checkNotNullParameter(deletedPushes, "deletedPushes");
        LOG.d("Updating notification bar for " + profile + " isSilent = " + silent);
        try {
            UpdateNotificationCommand updateNotificationCommand = getUpdateNotificationCommand();
            updateNotificationCommand.addUpdatePushesCommand(profile, deletedPushes, silent, externalIntent);
            Object orThrow = updateNotificationCommand.execute(getRequestArbiter()).getOrThrow();
            if ((orThrow instanceof CommandStatus.OK) && (((CommandStatus.OK) orThrow).getData() instanceof UpdateNotificationBarMsg)) {
                V data = ((CommandStatus.OK) orThrow).getData();
                Intrinsics.checkNotNull(data, "null cannot be cast to non-null type ru.mail.util.push.NotificationUpdater.UpdateNotificationBarMsg");
                ((UpdateNotificationBarMsg) data).updateNotification();
            }
        } catch (InterruptedException e10) {
            LOG.e("Unable to update notification bar", e10);
        } catch (ExecutionException e11) {
            LOG.e("Unable to update notification bar", e11);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @JvmOverloads
    public NotificationUpdater(@NotNull Context context, @NotNull NotificationConfiguration notificationConfiguration, @NotNull SafeDisplayNotificationChecker displayNotificationChecker) {
        this(context, notificationConfiguration, displayNotificationChecker, null, 8, null);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationConfiguration, "notificationConfiguration");
        Intrinsics.checkNotNullParameter(displayNotificationChecker, "displayNotificationChecker");
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public NotificationUpdater(@NotNull Context context, @NotNull NotificationConfiguration notificationConfiguration, @NotNull SafeDisplayNotificationChecker displayNotificationChecker, @NotNull Function1<? super NewMailPush, Boolean> shouldPublishNewMailNotification) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(notificationConfiguration, "notificationConfiguration");
        Intrinsics.checkNotNullParameter(displayNotificationChecker, "displayNotificationChecker");
        Intrinsics.checkNotNullParameter(shouldPublishNewMailNotification, "shouldPublishNewMailNotification");
        this.notificationConfiguration = notificationConfiguration;
        this.displayNotificationChecker = displayNotificationChecker;
        this.shouldPublishNewMailNotification = shouldPublishNewMailNotification;
        Context appContext = context.getApplicationContext();
        this.appContext = appContext;
        this.handler = new Handler(Looper.getMainLooper());
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        this.notificationActionSupplier = new NotificationActionSupplier(appContext);
        NotificationManagerCompat notificationManagerCompatFrom = NotificationManagerCompat.from(appContext);
        Intrinsics.checkNotNullExpressionValue(notificationManagerCompatFrom, "from(...)");
        this.notificationManager = notificationManagerCompatFrom;
        this.configurationRepository = ConfigurationRepository.from(appContext);
        this.notificationChannels = NotificationChannelsCompat.from(appContext);
        this.accountManager = Authenticator.getAccountManagerWrapper(appContext);
        this.messagesNotificationsCreator = new MessagesNotificationsCreator();
        this.notificationLogBuilder = new StringBuilder();
        this.personBuilder = new Person.Builder();
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.requestArbiter = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<RequestArbiter>() { // from class: ru.mail.util.push.NotificationUpdater$special$$inlined$lazyLocate$1
            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ru.mail.arbiter.RequestArbiter] */
            @Override // kotlin.jvm.functions.Function0
            public final RequestArbiter invoke() {
                return Locator.INSTANCE.locate(this.this$0.appContext, RequestArbiter.class);
            }
        });
        this.navigator = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<Navigator>() { // from class: ru.mail.util.push.NotificationUpdater$special$$inlined$lazyLocate$2
            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ru.mail.logic.navigation.Navigator] */
            @Override // kotlin.jvm.functions.Function0
            public final Navigator invoke() {
                return Locator.INSTANCE.locate(this.this$0.appContext, Navigator.class);
            }
        });
        this.asserterConfigFactory = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<AsserterConfigFactory>() { // from class: ru.mail.util.push.NotificationUpdater$special$$inlined$lazyLocate$3
            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ru.mail.asserter.core.AsserterConfigFactory] */
            @Override // kotlin.jvm.functions.Function0
            public final AsserterConfigFactory invoke() {
                return Locator.INSTANCE.locate(this.this$0.appContext, AsserterConfigFactory.class);
            }
        });
        this.logCollector = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<LogCollector>() { // from class: ru.mail.util.push.NotificationUpdater$special$$inlined$lazyLocate$4
            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ru.mail.util.log.LogCollector] */
            @Override // kotlin.jvm.functions.Function0
            public final LogCollector invoke() {
                return Locator.INSTANCE.locate(this.this$0.appContext, LogCollector.class);
            }
        });
        this.imageLoaderRepository = LazyKt.lazy(lazyThreadSafetyMode, (Function0) new Function0<ImageLoaderRepository>() { // from class: ru.mail.util.push.NotificationUpdater$special$$inlined$lazyLocate$5
            /* JADX WARN: Type inference failed for: r0v1, types: [java.lang.Object, ru.mail.imageloader.ImageLoaderRepository] */
            @Override // kotlin.jvm.functions.Function0
            public final ImageLoaderRepository invoke() {
                return Locator.INSTANCE.locate(this.this$0.appContext, ImageLoaderRepository.class);
            }
        });
        this.avatarLoader = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.w0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationUpdater.avatarLoader_delegate$lambda$0(this.f101215a);
            }
        });
        this.updateNotificationCommand = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.x0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationUpdater.updateNotificationCommand_delegate$lambda$0(this.f101217a);
            }
        });
        this.selectThreadRepresentationCommand = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.y0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationUpdater.selectThreadRepresentationCommand_delegate$lambda$0();
            }
        });
        this.pushTypes = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.z0
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return NotificationUpdater.pushTypes_delegate$lambda$0(this.f101222a);
            }
        });
        this.isColoredTagsOn = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.a1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NotificationUpdater.isColoredTagsOn_delegate$lambda$0(this.f101044a));
            }
        });
        this.isDetailedLogEnabled = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.b1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NotificationUpdater.isDetailedLogEnabled_delegate$lambda$0(this.f101048a));
            }
        });
        this.isMessagingStyleWithIconsSupported = LazyKt.lazy(new Function0() { // from class: ru.mail.util.push.c1
            @Override // kotlin.jvm.functions.Function0
            public final Object invoke() {
                return Boolean.valueOf(NotificationUpdater.isMessagingStyleWithIconsSupported_delegate$lambda$0(this.f101051a));
            }
        });
    }

    public /* synthetic */ NotificationUpdater(Context context, NotificationConfiguration notificationConfiguration, SafeDisplayNotificationChecker safeDisplayNotificationChecker, Function1 function1, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        if ((i10 & 4) != 0) {
            Context applicationContext = context.getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            safeDisplayNotificationChecker = new DefaultSafeDisplayNotificationChecker(applicationContext);
        }
        this(context, notificationConfiguration, safeDisplayNotificationChecker, (i10 & 8) != 0 ? new Function1() { // from class: ru.mail.util.push.v0
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return Boolean.valueOf(NotificationUpdater._init_$lambda$0((NewMailPush) obj));
            }
        } : function1);
    }
}
