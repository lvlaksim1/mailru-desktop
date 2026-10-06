package com.vk.superapp.browser.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.vk.api.sdk.exceptions.VKApiCodes;
import com.vk.dto.common.id.UserId;
import com.vk.dto.common.id.UserIdKt;
import com.vk.push.pushsdk.utils.JsonMessageParser;
import com.vk.superapp.api.contract.SuperappApi;
import com.vk.superapp.bridges.SuperappBridgesKt;
import com.vk.superapp.bridges.SuperappUiRouterBridge;
import com.vk.superapp.browser.R;
import com.vk.superapp.browser.internal.bridges.BaseWebBridge;
import com.vk.superapp.browser.internal.bridges.EventFactory;
import com.vk.superapp.browser.internal.bridges.JsApiMethodType;
import com.vk.superapp.browser.internal.browser.VkBrowser;
import com.vk.superapp.browser.internal.commands.VkUiOpenQRCommand;
import com.vk.superapp.browser.internal.delegates.presenters.VkPayPresenter;
import com.vk.superapp.browser.internal.ui.identity.WebIdentityHelper;
import com.vk.superapp.browser.internal.utils.share.SharingController;
import com.vk.superapp.browser.ui.callback.OnWebCallback;
import com.vk.superapp.browser.utils.VkUiClipBoxFailed;
import com.vk.superapp.browser.utils.VkUiClipBoxResult;
import com.vk.superapp.browser.utils.VkUiRxEventKt;
import com.vk.superapp.browser.utils.VkUiUploadFailure;
import com.vk.superapp.browser.utils.VkUiUploadFailureType;
import com.vk.superapp.core.errors.VkAppsErrors;
import com.vk.superapp.core.extensions.RxExtKt;
import com.vk.superapp.js.bridge.events.AddToCommunity;
import com.vk.superapp.js.bridge.events.EventNames;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ'\u0010\u0014\u001a\u00020\u00132\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0011¢\u0006\u0004\b\u0014\u0010\u0015J\r\u0010\u0016\u001a\u00020\u0013¢\u0006\u0004\b\u0016\u0010\u0017¨\u0006\u0018"}, d2 = {"Lcom/vk/superapp/browser/ui/VkUiActivityResultDelegate;", "", "Landroid/content/Context;", "context", "Lcom/vk/superapp/browser/internal/browser/VkBrowser;", "browser", "", "appId", "Lcom/vk/superapp/browser/ui/callback/OnWebCallback;", "callback", "Lcom/vk/superapp/browser/internal/utils/share/SharingController;", "sharingController", "<init>", "(Landroid/content/Context;Lcom/vk/superapp/browser/internal/browser/VkBrowser;JLcom/vk/superapp/browser/ui/callback/OnWebCallback;Lcom/vk/superapp/browser/internal/utils/share/SharingController;)V", "", "requestCode", "resultCode", "Landroid/content/Intent;", "data", "", "onActivityResult", "(IILandroid/content/Intent;)V", "onDestroy", "()V", "browser_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nVkUiActivityResultDelegate.kt\nKotlin\n*S Kotlin\n*F\n+ 1 VkUiActivityResultDelegate.kt\ncom/vk/superapp/browser/ui/VkUiActivityResultDelegate\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 CommonExt.kt\ncom/vk/core/extensions/CommonExtKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,376:1\n1563#2:377\n1634#2,3:378\n1869#2,2:382\n55#3:381\n1#4:384\n*S KotlinDebug\n*F\n+ 1 VkUiActivityResultDelegate.kt\ncom/vk/superapp/browser/ui/VkUiActivityResultDelegate\n*L\n252#1:377\n252#1:378,3\n312#1:382,2\n310#1:381\n*E\n"})
public final class VkUiActivityResultDelegate {

    @Deprecated
    @NotNull
    public static final String KEY_ACCESS_TOKEN = "access_token";

    @Deprecated
    @NotNull
    public static final String KEY_ERROR = "error";

    @Deprecated
    @NotNull
    public static final String KEY_REQUEST_ID = "request_id";

    @Deprecated
    public static final int RESULT_INVALID_PARAMS = 3;

    @Deprecated
    @NotNull
    public static final String UNKNOWN_ERROR = "unknown_error";

    @NotNull
    private final Context resworbkvmoca;

    @NotNull
    private final VkBrowser resworbkvmocb;
    private final long resworbkvmocc;

    @NotNull
    private final OnWebCallback resworbkvmocd;

    @NotNull
    private final SharingController resworbkvmoce;

    @NotNull
    private final CompositeDisposable resworbkvmocf;

    public VkUiActivityResultDelegate(@NotNull Context context, @NotNull VkBrowser browser, long j10, @NotNull OnWebCallback callback, @NotNull SharingController sharingController) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(browser, "browser");
        Intrinsics.checkNotNullParameter(callback, "callback");
        Intrinsics.checkNotNullParameter(sharingController, "sharingController");
        this.resworbkvmoca = context;
        this.resworbkvmocb = browser;
        this.resworbkvmocc = j10;
        this.resworbkvmocd = callback;
        this.resworbkvmoce = sharingController;
        this.resworbkvmocf = new CompositeDisposable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(VkUiActivityResultDelegate vkUiActivityResultDelegate, long j10, Boolean bool) {
        vkUiActivityResultDelegate.resworbkvmocb.sendResponse(EventNames.AddToCommunity, new AddToCommunity.Response(null, new AddToCommunity.Response.Data(j10, null, 2, null), 1, null));
        SuperappUiRouterBridge superappUiRouter = SuperappBridgesKt.getSuperappUiRouter();
        String string = vkUiActivityResultDelegate.resworbkvmoca.getString(R.string.vk_apps_app_added_to_community);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        superappUiRouter.showToast(string);
        return Unit.INSTANCE;
    }

    private final void resworbkvmocb(int i10, Intent intent) {
        if (i10 != -1 || intent == null) {
            VkBrowser vkBrowser = this.resworbkvmocb;
            EventNames eventNames = EventNames.AddToCommunity;
            vkBrowser.sendError(eventNames, new AddToCommunity.Error(null, EventFactory.createUserDeniedError$default(EventFactory.INSTANCE, eventNames, vkBrowser, (String) null, 4, (Object) null), 1, null));
            return;
        }
        final long longExtra = intent.getLongExtra(VkBrowserFragment.KEY_PICKED_GROUP_ID, 0L);
        boolean booleanExtra = intent.getBooleanExtra(VkBrowserFragment.KEY_SHOULD_SEND_PUSH, false);
        if (longExtra > 0) {
            CompositeDisposable compositeDisposable = this.resworbkvmocf;
            Observable<Boolean> observableSendAppsAddToGroup = SuperappBridgesKt.getSuperappApi().getApp().sendAppsAddToGroup(this.resworbkvmocc, longExtra, booleanExtra);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.ui.t4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VkUiActivityResultDelegate.resworbkvmoca(this.f53282a, longExtra, (Boolean) obj);
                }
            };
            Consumer<? super Boolean> consumer = new Consumer() { // from class: com.vk.superapp.browser.ui.u4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    VkUiActivityResultDelegate.resworbkvmocc(function1, obj);
                }
            };
            final Function1 function2 = new Function1() { // from class: com.vk.superapp.browser.ui.v4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VkUiActivityResultDelegate.resworbkvmoca(this.f53299a, (Throwable) obj);
                }
            };
            compositeDisposable.add(observableSendAppsAddToGroup.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.ui.w4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    VkUiActivityResultDelegate.resworbkvmocd(function2, obj);
                }
            }));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocc(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocd(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public final void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        JSONObject jSONObject;
        String stringExtra;
        String string = null;
        if (requestCode == 100) {
            if (resultCode == -1 && data != null) {
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("access_token", data.getStringExtra("access_token"));
                VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.GET_AUTH_TOKEN, jSONObject2, null, 4, null);
                return;
            }
            if (data != null) {
                Bundle extras = data.getExtras();
                if (extras != null) {
                    string = extras.getString("error", "unknown_error");
                }
            } else {
                string = "unknown_error";
            }
            RuntimeException runtimeException = new RuntimeException(string);
            this.resworbkvmocb.sendFailureEvent(JsApiMethodType.GET_AUTH_TOKEN, runtimeException);
            this.resworbkvmocd.onWebLoadingError(runtimeException);
            return;
        }
        if (requestCode == 130) {
            this.resworbkvmocb.getState().getJs().getBridge().getJsAuthDelegate$browser_release().handleConfirmUserByServiceResult(resultCode, data);
            return;
        }
        if (requestCode == 1001) {
            this.resworbkvmocb.getState().getJs().getBridge().getJsCommonDelegate().handleOpenCodeReaderResult(resultCode, data != null ? VkUiOpenQRCommand.INSTANCE.unpackQrResultIntent(data) : null);
            return;
        }
        if (requestCode == 123) {
            if (resultCode == -1) {
                VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.ADD_MINI_APP_SNIPPET_TO_CHAT, BaseWebBridge.INSTANCE.createSuccessData(), null, 4, null);
                return;
            } else if (resultCode != 0) {
                VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.ADD_MINI_APP_SNIPPET_TO_CHAT, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, 12, null);
                return;
            } else {
                VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.ADD_MINI_APP_SNIPPET_TO_CHAT, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                return;
            }
        }
        if (requestCode == 124) {
            if (resultCode == -1) {
                VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.VERIFY_USER_BY_SERVICE, BaseWebBridge.INSTANCE.createSuccessData(), null, 4, null);
                return;
            } else if (resultCode != 0) {
                VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.VERIFY_USER_BY_SERVICE, VkAppsErrors.Client.UNKNOWN_ERROR, null, null, 12, null);
                return;
            } else {
                VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.VERIFY_USER_BY_SERVICE, VkAppsErrors.Client.INACTIVE_SCREEN, null, null, 12, null);
                return;
            }
        }
        switch (requestCode) {
            case 102:
            case 105:
                this.resworbkvmoce.handleShareResult(resultCode, data);
                break;
            case 103:
                this.resworbkvmocb.getState().getJs().getBridge().getJsVkclientDelegate().handleStoryBoxResult(resultCode);
                break;
            case 104:
                String stringExtra2 = data != null ? data.getStringExtra(VkPayPresenter.VKPAY_RESULT) : null;
                if (resultCode != -1 || stringExtra2 == null) {
                    VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.OPEN_PAY_FORM, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                } else {
                    VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.OPEN_PAY_FORM, new JSONObject(stringExtra2), null, 4, null);
                }
                break;
            case 106:
                resworbkvmocb(resultCode, data);
                break;
            case 107:
                if (resultCode == -1) {
                    String stringExtra3 = data != null ? data.getStringExtra(VkBrowserFragment.VK_WEB_APP_CLOSE_STATUS) : null;
                    String stringExtra4 = data != null ? data.getStringExtra(VkBrowserFragment.VK_WEB_APP_CLOSE_PAYLOAD) : null;
                    String stringExtra5 = data != null ? data.getStringExtra(KEY_REQUEST_ID) : null;
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("status", stringExtra3);
                    if (stringExtra4 != null) {
                        jSONObject3.put(JsonMessageParser.Keys.PAYLOAD_JSON_KEY, new JSONObject(stringExtra4));
                    }
                    if (stringExtra5 != null && !StringsKt.isBlank(stringExtra5)) {
                        jSONObject3.put(KEY_REQUEST_ID, stringExtra5);
                    }
                    VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.CLOSE_APP, jSONObject3, null, 4, null);
                }
                break;
            case 108:
                if (data == null || resultCode != -1) {
                    VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.GET_FRIENDS, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                } else {
                    this.resworbkvmocd.onWebFriendSelectResult(data);
                }
                break;
            case 109:
                this.resworbkvmocd.onWebIdentityContext(data);
                break;
            default:
                switch (requestCode) {
                    case 111:
                        if (data == null || !data.hasExtra(WebIdentityHelper.ARG_IDENTITY_EVENT)) {
                            VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.GET_PERSONAL_CARD, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                        } else {
                            String stringExtra6 = data.getStringExtra(WebIdentityHelper.ARG_IDENTITY_EVENT);
                            if (stringExtra6 == null) {
                                VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.GET_PERSONAL_CARD, VkAppsErrors.Client.MISSING_PARAMS, null, null, 12, null);
                            } else {
                                VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.GET_PERSONAL_CARD, new JSONObject(stringExtra6), null, 4, null);
                            }
                        }
                        break;
                    case 112:
                        this.resworbkvmocd.onWebPostResult(resultCode, data);
                        break;
                    case 113:
                        if (resultCode == -1) {
                            JSONObject jSONObjectPut = new JSONObject().put("result", true);
                            VkBrowser vkBrowser = this.resworbkvmocb;
                            JsApiMethodType jsApiMethodType = JsApiMethodType.SHOW_COMMUNITY_WIDGET_PREVIEW_BOX;
                            Intrinsics.checkNotNull(jSONObjectPut);
                            VkBrowser.DefaultImpls.sendSuccessEvent$default(vkBrowser, jsApiMethodType, jSONObjectPut, null, 4, null);
                        } else if (resultCode != 3) {
                            VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.SHOW_COMMUNITY_WIDGET_PREVIEW_BOX, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                        } else {
                            VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.SHOW_COMMUNITY_WIDGET_PREVIEW_BOX, VkAppsErrors.Client.INVALID_PARAMS, null, null, 12, null);
                        }
                        break;
                    default:
                        switch (requestCode) {
                            case 115:
                                resworbkvmoca(resultCode, data);
                                break;
                            case 116:
                                Bundle extras2 = data != null ? data.getExtras() : null;
                                if (resultCode == -1) {
                                    long j10 = extras2 != null ? extras2.getLong("ownerId") : 0L;
                                    int i10 = extras2 != null ? extras2.getInt("postId") : 0;
                                    if (j10 == 0 || i10 == 0) {
                                        jSONObject = null;
                                    } else {
                                        JSONObject jSONObject4 = new JSONObject();
                                        jSONObject4.put(VKApiCodes.PARAM_OWNER_ID, j10);
                                        jSONObject4.put("post_id", i10);
                                        jSONObject = jSONObject4;
                                    }
                                    if (jSONObject != null) {
                                        VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.SHOW_NEW_POST_BOX, jSONObject, null, 4, null);
                                    }
                                }
                                JSONObject jSONObject5 = new JSONObject();
                                if (extras2 != null) {
                                    jSONObject5.put("error_type", extras2.getInt("errorCode"));
                                }
                                ArrayList<String> stringArrayList = extras2 != null ? extras2.getStringArrayList("errorKeys") : null;
                                ArrayList<String> stringArrayList2 = extras2 != null ? extras2.getStringArrayList("errorValues") : null;
                                if (stringArrayList != null && stringArrayList2 != null) {
                                    JSONArray jSONArray = new JSONArray();
                                    for (Pair pair : CollectionsKt.zip(stringArrayList, stringArrayList2)) {
                                        String str = (String) pair.component1();
                                        String str2 = (String) pair.component2();
                                        JSONObject jSONObject6 = new JSONObject();
                                        jSONObject6.put("key", str);
                                        jSONObject6.put("value", str2);
                                        jSONArray.put(jSONObject6);
                                    }
                                    jSONObject5.put("error_data", jSONArray);
                                }
                                this.resworbkvmocb.sendEventFailed(JsApiMethodType.SHOW_NEW_POST_BOX, jSONObject5);
                                break;
                            case 117:
                                if (resultCode != -1 || data == null) {
                                    VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.USERS_SEARCH, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                                } else {
                                    UserId userId = (UserId) data.getParcelableExtra("user_id");
                                    if (userId == null) {
                                        userId = UserId.DEFAULT;
                                    }
                                    if (UserIdKt.isReal(userId)) {
                                        JSONObject jSONObject7 = new JSONObject();
                                        jSONObject7.put("id", userId);
                                        VkBrowser.DefaultImpls.sendSuccessEvent$default(this.resworbkvmocb, JsApiMethodType.USERS_SEARCH, jSONObject7, null, 4, null);
                                    } else {
                                        VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.USERS_SEARCH, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
                                    }
                                }
                                break;
                            case 118:
                                if (data == null || (stringExtra = data.getStringExtra(KEY_REQUEST_ID)) == null) {
                                    stringExtra = "";
                                }
                                if (resultCode == -1) {
                                    VkUiRxEventKt.getVkUiRxBus().publishEvent(new VkUiClipBoxResult(this.resworbkvmocc, stringExtra));
                                } else if (resultCode != 0) {
                                    VkUiRxEventKt.getVkUiRxBus().publishEvent(new VkUiClipBoxFailed(this.resworbkvmocc, stringExtra, new VkUiUploadFailure(VkUiUploadFailureType.ERROR)));
                                } else {
                                    VkUiRxEventKt.getVkUiRxBus().publishEvent(new VkUiClipBoxFailed(this.resworbkvmocc, stringExtra, new VkUiUploadFailure(VkUiUploadFailureType.CANCELLED)));
                                }
                                break;
                        }
                        break;
                }
                break;
        }
    }

    public final void onDestroy() {
        this.resworbkvmocf.clear();
        this.resworbkvmoce.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(VkUiActivityResultDelegate vkUiActivityResultDelegate, Throwable th2) {
        VkBrowser vkBrowser = vkUiActivityResultDelegate.resworbkvmocb;
        EventNames eventNames = EventNames.AddToCommunity;
        EventFactory eventFactory = EventFactory.INSTANCE;
        Intrinsics.checkNotNull(th2);
        vkBrowser.sendError(eventNames, new AddToCommunity.Error(null, eventFactory.createError(eventNames, vkBrowser, th2), 1, null));
        SuperappUiRouterBridge superappUiRouter = SuperappBridgesKt.getSuperappUiRouter();
        String string = vkUiActivityResultDelegate.resworbkvmoca.getString(R.string.vk_apps_common_network_error);
        Intrinsics.checkNotNullExpressionValue(string, "getString(...)");
        superappUiRouter.showToast(string);
        return Unit.INSTANCE;
    }

    private final void resworbkvmoca(int i10, Intent intent) {
        final List<Long> list;
        if (i10 == -1 && intent != null) {
            long[] longArrayExtra = intent.getLongArrayExtra(VkBrowserFragment.KEY_RESULT_IDS);
            if (longArrayExtra == null || (list = ArraysKt.toList(longArrayExtra)) == null) {
                return;
            }
            String stringExtra = intent.getStringExtra(VkBrowserFragment.KEY_REQUEST_KEY);
            CompositeDisposable compositeDisposable = this.resworbkvmocf;
            SuperappApi.App app = SuperappBridgesKt.getSuperappApi().getApp();
            long j10 = this.resworbkvmocc;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
            Iterator<T> it = list.iterator();
            while (it.hasNext()) {
                arrayList.add(UserIdKt.toUserId(((Number) it.next()).longValue()));
            }
            Observable observableWrapProgress$default = RxExtKt.wrapProgress$default(app.sendAppInviteRequest(j10, arrayList, stringExtra), this.resworbkvmoca, 0L, (Function1) null, 6, (Object) null);
            final Function1 function1 = new Function1() { // from class: com.vk.superapp.browser.ui.x4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VkUiActivityResultDelegate.resworbkvmoca(this.f53318a, (Boolean) obj);
                }
            };
            Consumer consumer = new Consumer() { // from class: com.vk.superapp.browser.ui.y4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    VkUiActivityResultDelegate.resworbkvmoca(function1, obj);
                }
            };
            final Function1 function2 = new Function1() { // from class: com.vk.superapp.browser.ui.z4
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return VkUiActivityResultDelegate.resworbkvmoca(list, this, (Throwable) obj);
                }
            };
            compositeDisposable.add(observableWrapProgress$default.subscribe(consumer, new Consumer() { // from class: com.vk.superapp.browser.ui.a5
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) {
                    VkUiActivityResultDelegate.resworbkvmocb(function2, obj);
                }
            }));
            return;
        }
        VkBrowser.DefaultImpls.sendFailureEvent$default(this.resworbkvmocb, JsApiMethodType.SHOW_INVITE_BOX, VkAppsErrors.Client.USER_DENIED, null, null, 12, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmocb(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void resworbkvmoca(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(VkUiActivityResultDelegate vkUiActivityResultDelegate, Boolean bool) throws JSONException {
        JSONObject jSONObjectPut = new JSONObject().put("success", true);
        VkBrowser vkBrowser = vkUiActivityResultDelegate.resworbkvmocb;
        JsApiMethodType jsApiMethodType = JsApiMethodType.SHOW_INVITE_BOX;
        Intrinsics.checkNotNull(jSONObjectPut);
        VkBrowser.DefaultImpls.sendSuccessEvent$default(vkBrowser, jsApiMethodType, jSONObjectPut, null, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit resworbkvmoca(List list, VkUiActivityResultDelegate vkUiActivityResultDelegate, Throwable th2) {
        Pair<String, ? extends Object> pair = TuplesKt.to("nonSentIds", list);
        VkBrowser vkBrowser = vkUiActivityResultDelegate.resworbkvmocb;
        JsApiMethodType jsApiMethodType = JsApiMethodType.SHOW_INVITE_BOX;
        VkAppsErrors vkAppsErrors = VkAppsErrors.INSTANCE;
        Intrinsics.checkNotNull(th2);
        vkBrowser.sendFailureEvent(jsApiMethodType, vkAppsErrors.provideForApi(th2), pair, vkAppsErrors.getErrorMessage(th2));
        return Unit.INSTANCE;
    }
}
