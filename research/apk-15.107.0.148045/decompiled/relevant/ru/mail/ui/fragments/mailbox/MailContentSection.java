package ru.mail.ui.fragments.mailbox;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;
import androidx.compose.runtime.internal.StabilityInferred;
import androidx.lifecycle.FlowExtKt;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwnerKt;
import com.nobu_games.android.view.web.FullHeightMailMessageContainer;
import com.nobu_games.android.view.web.MailMessageContainer;
import com.nobu_games.android.view.web.MailMessageContainerImpl;
import com.nobu_games.android.view.web.MailMessageViewer;
import com.nobu_games.android.view.web.MailWebView;
import com.nobu_games.android.view.web.ScrollRegistry;
import com.vk.superapp.api.internal.requests.utils.WebRequestHelper;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SpillingKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.StateFlow;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.auth.AuthenticatorConfig;
import ru.mail.cloud.app.utils.analytics.events.ScreenViewEvent;
import ru.mail.config.Configuration;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.glasha.di.SharedFoldersModuleEntryPoint;
import ru.mail.glasha.presentation.SharedFolderClickListener;
import ru.mail.js.bridge.MessageRenderJsBridge;
import ru.mail.js.bridge.MessageRenderStatusListener;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;
import ru.mail.logic.content.FolderMatcher;
import ru.mail.logic.content.feature.features.AlternativeMessageContainerFeature;
import ru.mail.logic.content.feature.features.NullWebViewBaseUrlFeature;
import ru.mail.logic.content.impl.CommonDataManager;
import ru.mail.mails.R;
import ru.mail.march.viewmodel.ExtensionsKt;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;
import ru.mail.ui.fragments.AbstractWebViewHandlerFragment;
import ru.mail.ui.fragments.mailbox.mailview.MessageBody;
import ru.mail.ui.fragments.mailbox.mailview.viewmodel.MessageBodyViewModel;
import ru.mail.ui.webview.LoadingErrorListener;
import ru.mail.ui.webview.MailOverrideUrlLoadingDelegate;
import ru.mail.ui.webview.WebEventsInterface;
import ru.mail.util.log.Logger;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0013\b\u0007\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004BS\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\b\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0006\u0010\u0011\u001a\u00020\u0012\u0012\u0006\u0010\u0013\u001a\u00020\u0014\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016¢\u0006\u0004\b\u0017\u0010\u0018J \u0010:\u001a\u00020;2\u0006\u0010*\u001a\u00020+2\u0006\u0010<\u001a\u00020\u00122\u0006\u0010=\u001a\u00020\u001aH\u0002J\f\u0010>\u001a\u00020;*\u00020+H\u0003J*\u0010?\u001a\u00020;2\u0006\u0010@\u001a\u00020%2\u0006\u0010A\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00142\b\u0010D\u001a\u0004\u0018\u00010BH\u0002J:\u0010E\u001a\u00020;2\u0006\u0010@\u001a\u00020%2\u0006\u0010F\u001a\u00020B2\u0006\u0010G\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00142\u0006\u0010H\u001a\u00020\u00142\b\u0010D\u001a\u0004\u0018\u00010BH\u0002J\b\u0010I\u001a\u00020;H\u0016J\b\u0010J\u001a\u00020;H\u0016J\u0010\u0010K\u001a\u00020;2\u0006\u0010*\u001a\u00020+H\u0002J\b\u0010L\u001a\u00020MH\u0002J+\u0010N\u001a\u00020;2!\u0010O\u001a\u001d\u0012\u0013\u0012\u00110+¢\u0006\f\bQ\u0012\b\bR\u0012\u0004\b\b(S\u0012\u0004\u0012\u00020;0PH\u0002J\u0010\u0010T\u001a\u00020;2\u0006\u0010@\u001a\u00020%H\u0002J2\u0010U\u001a\u00020;2\u0006\u0010V\u001a\u00020B2\u0006\u0010C\u001a\u00020\u00142\u0006\u0010W\u001a\u00020\u00142\b\u0010D\u001a\u0004\u0018\u00010B2\u0006\u0010X\u001a\u00020BH\u0002J\b\u0010Y\u001a\u00020\u0014H\u0002J\"\u0010Z\u001a\u00020;2\u0006\u0010[\u001a\u00020B2\u0006\u0010V\u001a\u00020B2\b\u0010D\u001a\u0004\u0018\u00010BH\u0002J\"\u0010\\\u001a\u00020;2\u0006\u0010V\u001a\u00020B2\u0006\u0010W\u001a\u00020\u00142\b\u0010D\u001a\u0004\u0018\u00010BH\u0003J\b\u0010]\u001a\u00020\u0014H\u0002J\u000e\u0010^\u001a\u00020;2\u0006\u0010_\u001a\u00020\u0014J\u0016\u0010`\u001a\u00020;2\u0006\u0010a\u001a\u00020\u00142\u0006\u0010b\u001a\u00020cJ\u0006\u0010d\u001a\u00020;J\u0006\u0010e\u001a\u00020;J\u0006\u0010f\u001a\u00020;J\u0006\u0010g\u001a\u00020;J\u0010\u0010h\u001a\u00020;2\u0006\u0010i\u001a\u00020MH\u0016J\b\u0010j\u001a\u00020;H\u0016J\b\u0010k\u001a\u00020;H\u0016J\u0010\u0010l\u001a\u00020;2\u0006\u0010m\u001a\u00020MH\u0016J\u0010\u0010n\u001a\u00020;2\u0006\u0010i\u001a\u00020MH\u0016J\u0010\u0010o\u001a\u00020;2\u0006\u0010p\u001a\u00020\u0014H\u0016J\b\u0010q\u001a\u00020;H\u0016J\b\u0010r\u001a\u00020;H\u0016J\u0006\u0010s\u001a\u00020;J\u0006\u0010t\u001a\u00020;J\u0006\u0010u\u001a\u00020;R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0019\u001a\n \u001b*\u0004\u0018\u00010\u001a0\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u001e\u001a\n \u001b*\u0004\u0018\u00010\u001f0\u001fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010 \u001a\u00020!¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010$\u001a\u0004\u0018\u00010%X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010'\"\u0004\b(\u0010)R\u001c\u0010*\u001a\u0004\u0018\u00010+X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R\u0011\u00100\u001a\u000201¢\u0006\b\n\u0000\u001a\u0004\b2\u00103R\u001a\u00104\u001a\u00020\u0014X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00106\"\u0004\b7\u00108R\u0011\u00109\u001a\u00020\u00148F¢\u0006\u0006\u001a\u0004\b9\u00106¨\u0006v"}, d2 = {"Lru/mail/ui/fragments/mailbox/MailContentSection;", "Lru/mail/ui/webview/WebEventsInterface$AmpListener;", "Lru/mail/ui/fragments/mailbox/AmpBridge$AmpLoadingListener;", "Lru/mail/ui/fragments/mailbox/SubmitHandlerListener;", "Lru/mail/js/bridge/MessageRenderStatusListener;", "fragment", "Lru/mail/ui/fragments/AbstractWebViewHandlerFragment;", "fragmentView", "Landroid/view/View;", "messageBodyViewModel", "Lru/mail/ui/fragments/mailbox/mailview/viewmodel/MessageBodyViewModel;", "configuration", "Lru/mail/config/Configuration;", "renderListener", "Lru/mail/ui/fragments/mailbox/MessageRenderListener;", "webViewInlineImageDownloader", "Lru/mail/ui/fragments/mailbox/WebViewInlineImageDownloader;", "logger", "Lru/mail/util/log/Logger;", "isZoomSupported", "", "sharedFolderClickListener", "Lru/mail/glasha/presentation/SharedFolderClickListener;", "<init>", "(Lru/mail/ui/fragments/AbstractWebViewHandlerFragment;Landroid/view/View;Lru/mail/ui/fragments/mailbox/mailview/viewmodel/MessageBodyViewModel;Lru/mail/config/Configuration;Lru/mail/ui/fragments/mailbox/MessageRenderListener;Lru/mail/ui/fragments/mailbox/WebViewInlineImageDownloader;Lru/mail/util/log/Logger;ZLru/mail/glasha/presentation/SharedFolderClickListener;)V", "appContext", "Landroid/content/Context;", "kotlin.jvm.PlatformType", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "dataManager", "Lru/mail/logic/content/impl/CommonDataManager;", "mailContentScroll", "Lcom/nobu_games/android/view/web/ScrollRegistry;", "getMailContentScroll", "()Lcom/nobu_games/android/view/web/ScrollRegistry;", "messageContent", "Lru/mail/data/entities/MailMessageContent;", "getMessageContent", "()Lru/mail/data/entities/MailMessageContent;", "setMessageContent", "(Lru/mail/data/entities/MailMessageContent;)V", "mailMessageViewer", "Lcom/nobu_games/android/view/web/MailMessageViewer;", "getMailMessageViewer", "()Lcom/nobu_games/android/view/web/MailMessageViewer;", "setMailMessageViewer", "(Lcom/nobu_games/android/view/web/MailMessageViewer;)V", "mailMessageContainer", "Lcom/nobu_games/android/view/web/MailMessageContainer;", "getMailMessageContainer", "()Lcom/nobu_games/android/view/web/MailMessageContainer;", "usingAmp", "getUsingAmp", "()Z", "setUsingAmp", "(Z)V", "isGeckoUsed", "initAndTunesWebView", "", "log", "context", "configure", "showHtmlContent", "content", "bodyWithScripts", "", "isImageAllowed", "authToken", "showAmpContent", "ampBody", "assets", "isDarkTheme", "onAmpFatalError", "onAmpContentLoaded", "switchFromAmpToHtmlPart", "getDefaultMixedContentMode", "", "postOnUiWithDestroyCheck", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", ScreenViewEvent.SCREEN_VIEWER, "attachNewWebViewClient", "onContentReady", "body", "isAmp", "messageId", "checkIsNotReadyForRendering", "startRenderContent", "baseUri", "renderContent", "isNullWebViewBaseUrlSupported", "applyDarkThemeState", "darkThemeEnabled", "onEmailBodyLoaded", "isVisible", "folderId", "", "onPause", "onHidden", "onShown", "onResume", "onHeightMeasured", "contentHeight", "onDOMContentLoaded", "onDOMContentLoadedImmediate", "onMessageLoaded", "height", "onMessageLoadedImmediate", "onQuotesStateChanged", "isExpanded", "onFirstJsCalled", "onSubmitClick", "onDestroyView", "finishActionMode", "requestLayout", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMailContentSection.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MailContentSection.kt\nru/mail/ui/fragments/mailbox/MailContentSection\n+ 2 View.kt\nandroidx/core/view/ViewKt\n+ 3 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 4 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n+ 5 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt\n*L\n1#1,525:1\n257#2,2:526\n17#3:528\n19#3:532\n46#4:529\n51#4:531\n105#5:530\n*S KotlinDebug\n*F\n+ 1 MailContentSection.kt\nru/mail/ui/fragments/mailbox/MailContentSection\n*L\n107#1:526,2\n138#1:528\n138#1:532\n138#1:529\n138#1:531\n138#1:530\n*E\n"})
public final class MailContentSection implements WebEventsInterface.AmpListener, AmpBridge.AmpLoadingListener, SubmitHandlerListener, MessageRenderStatusListener {
    public static final int $stable = 8;
    private final Context appContext;

    @NotNull
    private final Configuration configuration;
    private final CommonDataManager dataManager;

    @NotNull
    private final AbstractWebViewHandlerFragment fragment;

    @NotNull
    private final Handler handler;
    private final boolean isZoomSupported;

    @NotNull
    private final Logger logger;

    @NotNull
    private final ScrollRegistry mailContentScroll;

    @NotNull
    private final MailMessageContainer mailMessageContainer;

    @Nullable
    private MailMessageViewer mailMessageViewer;

    @NotNull
    private final MessageBodyViewModel messageBodyViewModel;

    @Nullable
    private volatile MailMessageContent messageContent;

    @Nullable
    private final MessageRenderListener renderListener;

    @Nullable
    private final SharedFolderClickListener sharedFolderClickListener;
    private volatile boolean usingAmp;

    @NotNull
    private final WebViewInlineImageDownloader webViewInlineImageDownloader;

    /* JADX INFO: renamed from: ru.mail.ui.fragments.mailbox.MailContentSection$6, reason: invalid class name */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n"}, d2 = {"<anonymous>", "", "it", "Lru/mail/ui/fragments/mailbox/mailview/viewmodel/MessageBodyViewModel$State;"}, k = 3, mv = {2, 2, 0}, xi = 48)
    @DebugMetadata(c = "ru.mail.ui.fragments.mailbox.MailContentSection$6", f = "MailContentSection.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    static final class AnonymousClass6 extends SuspendLambda implements Function2<MessageBodyViewModel.State, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        AnonymousClass6(Continuation<? super AnonymousClass6> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass6 anonymousClass6 = MailContentSection.this.new AnonymousClass6(continuation);
            anonymousClass6.L$0 = obj;
            return anonymousClass6;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            MessageBodyViewModel.State state = (MessageBodyViewModel.State) this.L$0;
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MessageBody body = state.getBody();
            if (body instanceof MessageBody.Amp) {
                MessageBody.Amp amp = (MessageBody.Amp) body;
                MailContentSection.this.showAmpContent(amp.getContent(), amp.getBody(), amp.getAssets(), state.isImageAllowed(), state.isDarkTheme(), state.getAuthToken());
            } else {
                if (!(body instanceof MessageBody.Html)) {
                    throw new NoWhenBranchMatchedException();
                }
                MessageBody.Html html = (MessageBody.Html) body;
                MailContentSection.this.showHtmlContent(html.getContent(), html.getBody(), state.isImageAllowed(), state.getAuthToken());
            }
            return Unit.INSTANCE;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(MessageBodyViewModel.State state, Continuation<? super Unit> continuation) {
            return ((AnonymousClass6) create(state, continuation)).invokeSuspend(Unit.INSTANCE);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MailContentSection(@NotNull AbstractWebViewHandlerFragment fragment, @NotNull View fragmentView, @NotNull MessageBodyViewModel messageBodyViewModel, @NotNull Configuration configuration, @Nullable MessageRenderListener messageRenderListener, @NotNull WebViewInlineImageDownloader webViewInlineImageDownloader, @NotNull Logger logger, boolean z10, @Nullable SharedFolderClickListener sharedFolderClickListener) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(fragmentView, "fragmentView");
        Intrinsics.checkNotNullParameter(messageBodyViewModel, "messageBodyViewModel");
        Intrinsics.checkNotNullParameter(configuration, "configuration");
        Intrinsics.checkNotNullParameter(webViewInlineImageDownloader, "webViewInlineImageDownloader");
        Intrinsics.checkNotNullParameter(logger, "logger");
        this.fragment = fragment;
        this.messageBodyViewModel = messageBodyViewModel;
        this.configuration = configuration;
        this.renderListener = messageRenderListener;
        this.webViewInlineImageDownloader = webViewInlineImageDownloader;
        this.logger = logger;
        this.isZoomSupported = z10;
        this.sharedFolderClickListener = sharedFolderClickListener;
        Context appContext = fragment.requireContext().getApplicationContext();
        this.appContext = appContext;
        this.handler = new Handler(Looper.getMainLooper());
        this.dataManager = CommonDataManager.from(appContext);
        ScrollRegistry scrollRegistry = new ScrollRegistry();
        this.mailContentScroll = scrollRegistry;
        MailMessageContainer mailMessageContainer = (MailMessageContainerImpl) fragmentView.findViewById(R.id.mailbox_mailmessage_content_view_legacy);
        if (CommonDataManager.from(appContext).isFeatureSupported(AlternativeMessageContainerFeature.INSTANCE, appContext)) {
            FullHeightMailMessageContainer fullHeightMailMessageContainer = (FullHeightMailMessageContainer) fragmentView.findViewById(R.id.mailbox_mailmessage_content_view);
            if (fullHeightMailMessageContainer != null) {
                mailMessageContainer = fullHeightMailMessageContainer;
            }
        } else {
            Intrinsics.checkNotNull(mailMessageContainer);
        }
        this.mailMessageContainer = mailMessageContainer;
        mailMessageContainer.setScrollListener(scrollRegistry);
        mailMessageContainer.setNeedDrawBody(true);
        ((View) mailMessageContainer).setVisibility(0);
        MailMessageViewer mailMessageViewer = mailMessageContainer.getMailMessageViewer();
        mailMessageViewer.addJavascriptInterface(new MessageRenderJsBridge(this), MessageRenderJsBridge.JS_CLASS_NAME);
        mailMessageViewer.addJavascriptInterface(new SubmitHandlerJsBridge(this, appContext), SubmitHandlerJsBridge.JS_CLASS_NAME);
        mailMessageViewer.setVisibility(4);
        Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
        initAndTunesWebView(mailMessageViewer, logger, appContext);
        mailMessageViewer.setLoadingErrorListener(new LoadingErrorListener() { // from class: ru.mail.ui.fragments.mailbox.p4
            @Override // ru.mail.ui.webview.LoadingErrorListener
            public final void onContentTooLargeError() {
                MailContentSection.d(this.f99679a);
            }
        });
        this.mailMessageViewer = mailMessageViewer;
        Intrinsics.checkNotNull(mailMessageViewer, "null cannot be cast to non-null type android.view.View");
        fragment.registerForContextMenu((View) mailMessageViewer);
        ExtensionsKt.collectOnLifecycle(fragment, messageBodyViewModel.getEffect(), new Function1() { // from class: ru.mail.ui.fragments.mailbox.q4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailContentSection._init_$lambda$2(this.f99858a, (MessageBodyViewModel.Effect) obj);
            }
        });
        StateFlow<MessageBodyViewModel.State> state = messageBodyViewModel.getState();
        Lifecycle lifecycleRegistry = fragment.getLifecycle();
        Intrinsics.checkNotNullExpressionValue(lifecycleRegistry, "<get-lifecycle>(...)");
        final Flow flowFilterNotNull = FlowKt.filterNotNull(FlowExtKt.flowWithLifecycle(state, lifecycleRegistry, Lifecycle.State.RESUMED));
        FlowKt.launchIn(FlowKt.onEach(FlowKt.distinctUntilChanged(new Flow<MessageBodyViewModel.State>() { // from class: ru.mail.ui.fragments.mailbox.MailContentSection$special$$inlined$filter$1

            /* JADX INFO: renamed from: ru.mail.ui.fragments.mailbox.MailContentSection$special$$inlined$filter$1$2, reason: invalid class name */
            /* JADX INFO: compiled from: ProGuard */
            @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
            @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 MailContentSection.kt\nru/mail/ui/fragments/mailbox/MailContentSection\n*L\n1#1,49:1\n18#2:50\n19#2:52\n138#3:51\n*E\n"})
            public static final class AnonymousClass2<T> implements FlowCollector {
                final /* synthetic */ FlowCollector $this_unsafeFlow;

                /* JADX INFO: renamed from: ru.mail.ui.fragments.mailbox.MailContentSection$special$$inlined$filter$1$2$1, reason: invalid class name */
                @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
                @DebugMetadata(c = "ru.mail.ui.fragments.mailbox.MailContentSection$special$$inlined$filter$1$2", f = "MailContentSection.kt", i = {0, 0, 0, 0, 0}, l = {50}, m = "emit", n = {"value", "$completion", "value", "$this$filter_u24lambda_u240", "$i$a$-unsafeTransform-FlowKt__TransformKt$filter$1"}, s = {"L$0", "L$1", "L$2", "L$3", "I$0"}, v = 1)
                public static final class AnonymousClass1 extends ContinuationImpl {
                    int I$0;
                    Object L$0;
                    Object L$1;
                    Object L$2;
                    Object L$3;
                    int label;
                    /* synthetic */ Object result;

                    public AnonymousClass1(Continuation continuation) {
                        super(continuation);
                    }

                    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                    public final Object invokeSuspend(Object obj) {
                        this.result = obj;
                        this.label |= Integer.MIN_VALUE;
                        return AnonymousClass2.this.emit(null, this);
                    }
                }

                public AnonymousClass2(FlowCollector flowCollector) {
                    this.$this_unsafeFlow = flowCollector;
                }

                /* JADX WARN: Code duplicated, block: B:7:0x0013  */
                @Override // kotlinx.coroutines.flow.FlowCollector
                public final Object emit(Object obj, Continuation continuation) {
                    AnonymousClass1 anonymousClass1;
                    if (continuation instanceof AnonymousClass1) {
                        anonymousClass1 = (AnonymousClass1) continuation;
                        int i10 = anonymousClass1.label;
                        if ((i10 & Integer.MIN_VALUE) != 0) {
                            anonymousClass1.label = i10 - Integer.MIN_VALUE;
                        } else {
                            anonymousClass1 = new AnonymousClass1(continuation);
                        }
                    } else {
                        anonymousClass1 = new AnonymousClass1(continuation);
                    }
                    Object obj2 = anonymousClass1.result;
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i11 = anonymousClass1.label;
                    if (i11 == 0) {
                        ResultKt.throwOnFailure(obj2);
                        FlowCollector flowCollector = this.$this_unsafeFlow;
                        if (((MessageBodyViewModel.State) obj).isReadyToLoad()) {
                            anonymousClass1.L$0 = SpillingKt.nullOutSpilledVariable(obj);
                            anonymousClass1.L$1 = SpillingKt.nullOutSpilledVariable(anonymousClass1);
                            anonymousClass1.L$2 = SpillingKt.nullOutSpilledVariable(obj);
                            anonymousClass1.L$3 = SpillingKt.nullOutSpilledVariable(flowCollector);
                            anonymousClass1.I$0 = 0;
                            anonymousClass1.label = 1;
                            if (flowCollector.emit(obj, anonymousClass1) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        }
                    } else {
                        if (i11 != 1) {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        ResultKt.throwOnFailure(obj2);
                    }
                    return Unit.INSTANCE;
                }
            }

            @Override // kotlinx.coroutines.flow.Flow
            public Object collect(FlowCollector<? super MessageBodyViewModel.State> flowCollector, Continuation continuation) {
                Object objCollect = flowFilterNotNull.collect(new AnonymousClass2(flowCollector), continuation);
                return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
            }
        }, new Function2() { // from class: ru.mail.ui.fragments.mailbox.r4
            @Override // kotlin.jvm.functions.Function2
            public final Object invoke(Object obj, Object obj2) {
                return Boolean.valueOf(MailContentSection._init_$lambda$4((MessageBodyViewModel.State) obj, (MessageBodyViewModel.State) obj2));
            }
        }), new AnonymousClass6(null)), LifecycleOwnerKt.getLifecycleScope(fragment));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit _init_$lambda$2(MailContentSection mailContentSection, MessageBodyViewModel.Effect it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mailContentSection.fragment.finishWithWebviewError();
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean _init_$lambda$4(MessageBodyViewModel.State old, MessageBodyViewModel.State state) {
        Intrinsics.checkNotNullParameter(old, "old");
        Intrinsics.checkNotNullParameter(state, "new");
        return Intrinsics.areEqual(old, state) && !state.isContentLoading();
    }

    private final void attachNewWebViewClient(MailMessageContent content) {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        MailWebView mailWebView = mailMessageViewer instanceof MailWebView ? (MailWebView) mailMessageViewer : null;
        if (mailWebView != null) {
            this.fragment.attachNewWebViewClient(mailWebView, content, new MessageContentInlineAttachProvider(content), this.webViewInlineImageDownloader, new MailWebViewClient.AMPCallback() { // from class: ru.mail.ui.fragments.mailbox.j4
                @Override // ru.mail.ui.fragments.mailbox.MailWebViewClient.AMPCallback
                public final void onLoadingFailed() {
                    MailContentSection.attachNewWebViewClient$lambda$0$0(this.f99060a);
                }
            }, this.sharedFolderClickListener);
        } else if (mailMessageViewer != null) {
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            mailMessageViewer.setOverrideUrlLoadingDelegate(new MailOverrideUrlLoadingDelegate(appContext, this.fragment, content, this.sharedFolderClickListener));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void attachNewWebViewClient$lambda$0$0(final MailContentSection mailContentSection) {
        mailContentSection.postOnUiWithDestroyCheck(new Function1() { // from class: ru.mail.ui.fragments.mailbox.o4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailContentSection.attachNewWebViewClient$lambda$0$0$0(this.f99526a, (MailMessageViewer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit attachNewWebViewClient$lambda$0$0$0(MailContentSection mailContentSection, MailMessageViewer it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mailContentSection.switchFromAmpToHtmlPart(it);
        return Unit.INSTANCE;
    }

    private final boolean checkIsNotReadyForRendering() {
        boolean zIsAdded = this.fragment.isAdded();
        boolean z10 = !zIsAdded;
        boolean z11 = true;
        boolean z12 = this.mailMessageViewer == null;
        if (zIsAdded && !z12) {
            z11 = false;
        }
        if (z11) {
            MailAppDependencies.analytics(this.appContext).onMailViewNotReadyForRendering(z10, z12, false);
        }
        return z11;
    }

    @SuppressLint({"NewApi", "SetJavaScriptEnabled"})
    private final void configure(MailMessageViewer mailMessageViewer) {
        mailMessageViewer.setVerticalScrollBarEnabled(true);
        mailMessageViewer.setScrollBarStyle(0);
        mailMessageViewer.setOverScrollMode(2);
        mailMessageViewer.setLongClickable(true);
        mailMessageViewer.setInitialScale(1);
        mailMessageViewer.setCacheMode(2);
        mailMessageViewer.setSupportZoom(this.isZoomSupported);
        mailMessageViewer.setBuiltInZoomControls(true);
        mailMessageViewer.setUseWideViewPort(true);
        mailMessageViewer.setDisplayZoomControls(false);
        mailMessageViewer.setJavaScriptEnabled(true);
    }

    public static void d(MailContentSection mailContentSection) {
        MailMessageContent mailMessageContent = mailContentSection.messageContent;
        String id2 = mailMessageContent != null ? mailMessageContent.getId() : null;
        if (id2 == null) {
            Logger.w$default(mailContentSection.logger, "Failed to deliver RenderingFailed event", null, 2, null);
        }
        mailContentSection.messageBodyViewModel.getHandler().invoke(new MessageBodyViewModel.Event.RenderingFailed(id2));
    }

    private final int getDefaultMixedContentMode() {
        return !this.configuration.isWebViewMixedSourcesEnabled() ? 1 : 0;
    }

    private final void initAndTunesWebView(MailMessageViewer mailMessageViewer, final Logger log, Context context) {
        configure(mailMessageViewer);
        PreferenceHostProvider preferenceHostProvider = new PreferenceHostProvider(context, "new_mail_api", R.string.new_mail_api_default_scheme, R.string.new_mail_api_default_host);
        mailMessageViewer.setMixedContentMode(getDefaultMixedContentMode());
        String userAgent = preferenceHostProvider.getUserAgent();
        Intrinsics.checkNotNullExpressionValue(userAgent, "getUserAgent(...)");
        mailMessageViewer.setUserAgentString(userAgent);
        String userAgent2 = preferenceHostProvider.getUserAgent();
        Intrinsics.checkNotNullExpressionValue(userAgent2, "getUserAgent(...)");
        mailMessageViewer.setUserAgentString(userAgent2);
        mailMessageViewer.setWebChromeClient(new WebChromeClient() { // from class: ru.mail.ui.fragments.mailbox.MailContentSection$initAndTunesWebView$1$1
            @Override // android.webkit.WebChromeClient
            public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
                Intrinsics.checkNotNullParameter(consoleMessage, "consoleMessage");
                Logger.d$default(log, "Console message: " + consoleMessage.message(), null, 2, null);
                return super.onConsoleMessage(consoleMessage);
            }
        });
    }

    private final boolean isNullWebViewBaseUrlSupported() {
        return CommonDataManager.from(this.appContext).getMailboxContext().isFeatureSupported(NullWebViewBaseUrlFeature.INSTANCE, new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void onAmpContentLoaded$lambda$0(MailContentSection mailContentSection) {
        MessageRenderListener messageRenderListener = mailContentSection.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onAMPContentLoaded();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onAmpContentLoaded$lambda$1(MailContentSection mailContentSection, MailMessageViewer mailMessageViewer) {
        Intrinsics.checkNotNullParameter(mailMessageViewer, "mailMessageViewer");
        mailMessageViewer.setVisibility(0);
        Object obj = mailContentSection.mailMessageContainer;
        View view = obj instanceof View ? (View) obj : null;
        if (view != null) {
            view.setVerticalScrollBarEnabled(true);
        }
        Object obj2 = mailContentSection.mailMessageContainer;
        View view2 = obj2 instanceof View ? (View) obj2 : null;
        if (view2 != null) {
            view2.setHorizontalScrollBarEnabled(true);
        }
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit onAmpFatalError$lambda$0(MailContentSection mailContentSection, MailMessageViewer it) {
        Intrinsics.checkNotNullParameter(it, "it");
        mailContentSection.switchFromAmpToHtmlPart(it);
        return Unit.INSTANCE;
    }

    private final void onContentReady(String body, boolean isImageAllowed, boolean isAmp, String authToken, String messageId) {
        MailMessageViewer mailMessageViewer;
        if (checkIsNotReadyForRendering() || (mailMessageViewer = this.mailMessageViewer) == null) {
            return;
        }
        Logger.d$default(this.logger, "Setting message content to the WebView for message: " + messageId, null, 2, null);
        try {
            mailMessageViewer.setLoadsImagesAutomatically(isImageAllowed);
            mailMessageViewer.setBlockNetworkImage(!isImageAllowed);
            mailMessageViewer.clearCache(true);
            mailMessageViewer.clearHistory();
            mailMessageViewer.setTag(messageId);
            renderContent(body, isAmp, authToken);
            this.messageBodyViewModel.getHandler().invoke(new MessageBodyViewModel.Event.RenderingStarted(isGeckoUsed(), messageId, isAmp));
            mailMessageViewer.refreshDrawableState();
        } catch (RuntimeException e10) {
            this.logger.e("Web view init error on content ready", e10);
            this.fragment.finishWithWebviewError();
        }
    }

    private final void postOnUiWithDestroyCheck(final Function1<? super MailMessageViewer, Unit> action) {
        this.handler.post(new Runnable() { // from class: ru.mail.ui.fragments.mailbox.n4
            @Override // java.lang.Runnable
            public final void run() {
                MailContentSection.postOnUiWithDestroyCheck$lambda$0(this.f99305a, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void postOnUiWithDestroyCheck$lambda$0(MailContentSection mailContentSection, Function1 function1) {
        MailMessageViewer mailMessageViewer = mailContentSection.mailMessageViewer;
        if (mailMessageViewer != null) {
            function1.invoke(mailMessageViewer);
        } else {
            Logger.w$default(mailContentSection.logger, "Runnable message was received but WebView was already destroyed", null, 2, null);
        }
    }

    @SuppressLint({"NewApi"})
    private final void renderContent(String body, boolean isAmp, String authToken) {
        String str = "https://" + this.appContext.getString(!isAmp && (isNullWebViewBaseUrlSupported() || AuthenticatorConfig.getInstance().isOAuthEnabled()) ? R.string.null_webview_base_host : R.string.webview_base_host);
        PerformanceMonitor.from(this.appContext).loadMessageContent().stop();
        startRenderContent(str, body, authToken);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showAmpContent(final MailMessageContent content, String ampBody, String assets, boolean isImageAllowed, boolean isDarkTheme, String authToken) {
        this.usingAmp = true;
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            Context applicationContext = this.fragment.requireContext().getApplicationContext();
            Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
            mailMessageViewer.addJavascriptInterface(new WebEventsInterface(applicationContext, this.configuration.getWebViewConfig().getMailWebViewEventsConfig(), null), WebEventsInterface.JS_CLASS_NAME);
            mailMessageViewer.addJavascriptInterface(new AmpBridge(this.configuration.getAmpConfig(), isDarkTheme, ampBody, content.getFrom(), content.getTo(), this), AmpBridge.JS_CLASS_NAME);
            mailMessageViewer.setMixedContentMode(0);
            attachNewWebViewClient(content);
            mailMessageViewer.doOnContentFinishRendering(new Function0() { // from class: ru.mail.ui.fragments.mailbox.s4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MailContentSection.showAmpContent$lambda$0$0(this.f99896a, content);
                }
            });
        }
        String id2 = content.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
        onContentReady(assets, isImageAllowed, true, authToken, id2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showAmpContent$lambda$0$0(MailContentSection mailContentSection, MailMessageContent mailMessageContent) {
        Function1<MessageBodyViewModel.Event, Unit> handler = mailContentSection.messageBodyViewModel.getHandler();
        String id2 = mailMessageContent.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
        handler.invoke(new MessageBodyViewModel.Event.RenderingFinished(id2));
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showHtmlContent(final MailMessageContent content, final String bodyWithScripts, final boolean isImageAllowed, final String authToken) {
        this.usingAmp = false;
        postOnUiWithDestroyCheck(new Function1() { // from class: ru.mail.ui.fragments.mailbox.l4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailContentSection.showHtmlContent$lambda$0(this.f99089a, content, bodyWithScripts, isImageAllowed, authToken, (MailMessageViewer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showHtmlContent$lambda$0(final MailContentSection mailContentSection, final MailMessageContent mailMessageContent, String str, boolean z10, String str2, MailMessageViewer mailMessageViewer) {
        Intrinsics.checkNotNullParameter(mailMessageViewer, "mailMessageViewer");
        mailMessageViewer.setMixedContentMode(mailContentSection.getDefaultMixedContentMode());
        mailMessageViewer.setVisibility(0);
        Object obj = mailContentSection.mailMessageContainer;
        View view = obj instanceof View ? (View) obj : null;
        if (view != null) {
            view.setVerticalScrollBarEnabled(true);
        }
        Object obj2 = mailContentSection.mailMessageContainer;
        View view2 = obj2 instanceof View ? (View) obj2 : null;
        if (view2 != null) {
            view2.setHorizontalScrollBarEnabled(true);
        }
        mailContentSection.attachNewWebViewClient(mailMessageContent);
        if (mailContentSection.isGeckoUsed()) {
            mailMessageViewer.doOnContentFinishRendering(new Function0() { // from class: ru.mail.ui.fragments.mailbox.m4
                @Override // kotlin.jvm.functions.Function0
                public final Object invoke() {
                    return MailContentSection.showHtmlContent$lambda$0$0(this.f99125a, mailMessageContent);
                }
            });
        }
        String id2 = mailMessageContent.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
        mailContentSection.onContentReady(str, z10, false, str2, id2);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit showHtmlContent$lambda$0$0(MailContentSection mailContentSection, MailMessageContent mailMessageContent) {
        Function1<MessageBodyViewModel.Event, Unit> handler = mailContentSection.messageBodyViewModel.getHandler();
        String id2 = mailMessageContent.getId();
        Intrinsics.checkNotNullExpressionValue(id2, "getId(...)");
        handler.invoke(new MessageBodyViewModel.Event.RenderingFinished(id2));
        return Unit.INSTANCE;
    }

    private final void startRenderContent(String baseUri, String body, String authToken) {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.loadDataWithBaseURL(baseUri, body, WebRequestHelper.MIME_HTML, R7WebViewConfigInjector.UTF_8, null, authToken);
        }
        PerformanceMonitor.from(this.appContext).renderHtml().start();
    }

    private final void switchFromAmpToHtmlPart(MailMessageViewer mailMessageViewer) {
        try {
            mailMessageViewer.stopLoading();
            mailMessageViewer.loadUrl("about:blank");
            this.messageBodyViewModel.getHandler().invoke(MessageBodyViewModel.Event.AmpRenderingFailed.INSTANCE);
        } catch (RuntimeException e10) {
            this.logger.e("Web view init error on switch from amp", e10);
            this.fragment.finishWithWebviewError();
        }
        MailAppDependencies.analytics(this.appContext).ampLoadingError();
    }

    public final void applyDarkThemeState(boolean darkThemeEnabled) {
        try {
            MailMessageViewer mailMessageViewer = this.mailMessageViewer;
            if (mailMessageViewer != null) {
                mailMessageViewer.toggleDarkTheme(this.appContext, darkThemeEnabled);
            }
        } catch (RuntimeException e10) {
            this.logger.e("Web view init error on toggle dark mode", e10);
            this.fragment.finishWithWebviewError();
        }
    }

    public final void finishActionMode() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.finishActionMode();
        }
    }

    @NotNull
    public final ScrollRegistry getMailContentScroll() {
        return this.mailContentScroll;
    }

    @NotNull
    public final MailMessageContainer getMailMessageContainer() {
        return this.mailMessageContainer;
    }

    @Nullable
    public final MailMessageViewer getMailMessageViewer() {
        return this.mailMessageViewer;
    }

    @Nullable
    public final MailMessageContent getMessageContent() {
        return this.messageContent;
    }

    public final boolean getUsingAmp() {
        return this.usingAmp;
    }

    public final boolean isGeckoUsed() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        return (mailMessageViewer != null ? mailMessageViewer.getType() : null) == MailMessageViewer.Type.GECKO_VIEW;
    }

    @Override // ru.mail.ui.fragments.mailbox.AmpBridge.AmpLoadingListener
    public void onAmpContentLoaded() {
        this.handler.post(new Runnable() { // from class: ru.mail.ui.fragments.mailbox.t4
            @Override // java.lang.Runnable
            public final void run() {
                MailContentSection.onAmpContentLoaded$lambda$0(this.f99912a);
            }
        });
        postOnUiWithDestroyCheck(new Function1() { // from class: ru.mail.ui.fragments.mailbox.u4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailContentSection.onAmpContentLoaded$lambda$1(this.f99940a, (MailMessageViewer) obj);
            }
        });
    }

    @Override // ru.mail.ui.webview.WebEventsInterface.AmpListener
    public void onAmpFatalError() {
        postOnUiWithDestroyCheck(new Function1() { // from class: ru.mail.ui.fragments.mailbox.k4
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return MailContentSection.onAmpFatalError$lambda$0(this.f99075a, (MailMessageViewer) obj);
            }
        });
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onDOMContentLoaded() {
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onDOMContentLoaded();
        }
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onDOMContentLoadedImmediate() {
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onDOMContentLoadedImmediate(this.messageContent);
        }
    }

    public final void onDestroyView() {
        Object obj = this.mailMessageViewer;
        ViewGroup viewGroup = obj instanceof ViewGroup ? (ViewGroup) obj : null;
        if (viewGroup != null) {
            if (viewGroup.getParent() instanceof ViewGroup) {
                ViewParent parent = viewGroup.getParent();
                Intrinsics.checkNotNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                ((ViewGroup) parent).removeView(viewGroup);
            }
            viewGroup.removeAllViews();
            MailMessageViewer mailMessageViewer = this.mailMessageViewer;
            if (mailMessageViewer != null) {
                mailMessageViewer.destroy();
            }
            this.mailMessageViewer = null;
        }
    }

    public final void onEmailBodyLoaded(boolean isVisible, long folderId) {
        if (isVisible) {
            SharedFoldersModuleEntryPoint.Companion companion = SharedFoldersModuleEntryPoint.INSTANCE;
            Context appContext = this.appContext;
            Intrinsics.checkNotNullExpressionValue(appContext, "appContext");
            if (FolderMatcher.isSpam(folderId, companion.folderGrantsManager(appContext)) || this.messageContent == null) {
                return;
            }
            this.dataManager.onEmailBodyLoaded(this.messageContent);
        }
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onFirstJsCalled() {
        this.messageBodyViewModel.getHandler().invoke(new MessageBodyViewModel.Event.FirstJsCalled(this.usingAmp));
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onHeightMeasured(int contentHeight) {
        PerformanceMonitor.from(this.appContext).domLoad().stop();
        this.mailMessageContainer.setContentHeight(contentHeight);
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onHeightMeasured(contentHeight);
        }
    }

    public final void onHidden() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.setIsVisibleOnScreen(false);
        }
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onMessageLoaded(int height) {
        MailMessageContent mailMessageContent = this.messageContent;
        String id2 = mailMessageContent != null ? mailMessageContent.getId() : null;
        if (id2 != null) {
            this.messageBodyViewModel.getHandler().invoke(new MessageBodyViewModel.Event.RenderingFinished(id2));
        } else {
            Logger.w$default(this.logger, "Failed to deliver RenderingFinished event", null, 2, null);
        }
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onMessageLoaded(this.mailMessageContainer.getContentHeight(), this.messageContent);
        }
        this.mailContentScroll.invalidate(this.mailMessageContainer);
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onMessageLoadedImmediate(int contentHeight) {
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onMessageLoadedImmediate(this.messageContent);
        }
        PerformanceMonitor performanceMonitorFrom = PerformanceMonitor.from(this.appContext);
        performanceMonitorFrom.openMessage().stop();
        performanceMonitorFrom.openMessageByPush().stop();
        performanceMonitorFrom.renderHtml().stop();
    }

    public final void onPause() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.onPause();
        }
    }

    @Override // ru.mail.js.bridge.MessageRenderStatusListener
    public void onQuotesStateChanged(boolean isExpanded) {
        MessageRenderListener messageRenderListener = this.renderListener;
        if (messageRenderListener != null) {
            messageRenderListener.onQuotesStateChanged(isExpanded);
        }
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.requestLayout();
        }
    }

    public final void onResume() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.onResume();
        }
    }

    public final void onShown() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.setIsVisibleOnScreen(true);
        }
    }

    @Override // ru.mail.ui.fragments.mailbox.SubmitHandlerListener
    public void onSubmitClick() {
        this.fragment.showSubmitFormNotSupportedDialog();
    }

    public final void requestLayout() {
        MailMessageViewer mailMessageViewer = this.mailMessageViewer;
        if (mailMessageViewer != null) {
            mailMessageViewer.requestLayout();
        }
    }

    public final void setMailMessageViewer(@Nullable MailMessageViewer mailMessageViewer) {
        this.mailMessageViewer = mailMessageViewer;
    }

    public final void setMessageContent(@Nullable MailMessageContent mailMessageContent) {
        this.messageContent = mailMessageContent;
    }

    public final void setUsingAmp(boolean z10) {
        this.usingAmp = z10;
    }
}
