package com.vk.id.captcha.web;

import android.os.Handler;
import android.util.Log;
import android.webkit.JavascriptInterface;
import com.vk.id.captcha.api.VKCaptcha;
import com.vk.id.captcha.sensors.SensorsDataRepository;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.kotlett.runtime.action.KotlettCallbackSpec;

/* JADX INFO: renamed from: com.vk.id.captcha.web.e, reason: from Kotlin metadata */
/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0000\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018BK\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u001c\u0010\u0007\u001a\u0018\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b\u0012\u0004\u0012\u00020\u00060\b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f¢\u0006\u0002\u0010\u0010J\u0010\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000fH\u0017J\u0010\u0010\u0015\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000fH\u0017J\u0010\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000fH\u0017J\u0010\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000fH\u0017R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R$\u0010\u0007\u001a\u0018\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\n0\tj\u0002`\u000b\u0012\u0004\u0012\u00020\u00060\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/vk/id/captcha/web/VKCaptchaJSInterface;", "Lcom/vk/id/captcha/web/VKCaptchaBridge;", KotlettCallbackSpec.PARAMETER_HANDLER_ID, "Landroid/os/Handler;", "onClose", "Lkotlin/Function0;", "", "onDataUpdate", "Lkotlin/Function1;", "", "Lcom/vk/id/captcha/sensors/model/SensorData;", "Lcom/vk/id/captcha/sensors/model/SensorsData;", "sensorsDataRepository", "Lcom/vk/id/captcha/sensors/SensorsDataRepository;", "domain", "", "(Landroid/os/Handler;Lkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;Lcom/vk/id/captcha/sensors/SensorsDataRepository;Ljava/lang/String;)V", "isClosedByUser", "", "VKCaptchaCloseCaptcha", "data", "VKCaptchaGetResult", "VKCaptchaListenSensorsStart", "VKCaptchaListenSensorsStop", "Companion", "captcha_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class VKCaptchaJSInterface {

    @NotNull
    public static final String VK_CAPTCHA_JS_INTERFACE = "AndroidBridge";

    @NotNull
    public static final String VK_CAPTCHA_WEB_VIEW = "VKCaptchaWebView";

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @NotNull
    private final Handler f50843a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @NotNull
    private Function0<Unit> f50844b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    @NotNull
    private final Function1<List<? extends com.vk.id.captcha.sensors.a.a>, Unit> f50845c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    @NotNull
    private final SensorsDataRepository f50846d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private final String f50847e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f50848f;

    /* JADX INFO: renamed from: com.vk.id.captcha.web.e$b */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0010\u0010\u0002\u001a\f\u0012\u0004\u0012\u00020\u00040\u0003j\u0002`\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/vk/id/captcha/sensors/model/SensorData;", "Lcom/vk/id/captcha/sensors/model/SensorsData;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
    static final class b extends Lambda implements Function1<List<? extends com.vk.id.captcha.sensors.a.a>, Unit> {
        b() {
            super(1);
        }

        public final void a(@NotNull List<? extends com.vk.id.captcha.sensors.a.a> list) {
            Intrinsics.checkNotNullParameter(list, "");
            VKCaptchaJSInterface.this.f50845c.invoke(list);
        }

        @Override // kotlin.jvm.functions.Function1
        public final /* synthetic */ Unit invoke(List<? extends com.vk.id.captcha.sensors.a.a> list) {
            a(list);
            return Unit.INSTANCE;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public VKCaptchaJSInterface(@NotNull Handler handler, @NotNull Function0<Unit> function0, @NotNull Function1<? super List<? extends com.vk.id.captcha.sensors.a.a>, Unit> function1, @NotNull SensorsDataRepository sensorsDataRepository, @Nullable String str) {
        Intrinsics.checkNotNullParameter(handler, "");
        Intrinsics.checkNotNullParameter(function0, "");
        Intrinsics.checkNotNullParameter(function1, "");
        Intrinsics.checkNotNullParameter(sensorsDataRepository, "");
        this.f50843a = handler;
        this.f50844b = function0;
        this.f50845c = function1;
        this.f50846d = sensorsDataRepository;
        this.f50847e = str;
        this.f50848f = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void a(JSONObject jSONObject, VKCaptchaJSInterface vKCaptchaJSInterface) throws JSONException {
        Intrinsics.checkNotNullParameter(jSONObject, "");
        Intrinsics.checkNotNullParameter(vKCaptchaJSInterface, "");
        VKCaptcha vKCaptcha = VKCaptcha.INSTANCE;
        String string = jSONObject.getString("token");
        Intrinsics.checkNotNullExpressionValue(string, "");
        vKCaptcha.setResult$captcha_release(string, vKCaptchaJSInterface.f50847e);
    }

    @JavascriptInterface
    public final void VKCaptchaCloseCaptcha(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "");
        if (this.f50848f) {
            VKCaptcha.INSTANCE.closeCaptcha();
        }
        this.f50844b.invoke();
    }

    @JavascriptInterface
    public final void VKCaptchaGetResult(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "");
        try {
            final JSONObject jSONObject = new JSONObject(data);
            this.f50848f = false;
            this.f50843a.post(new Runnable() { // from class: com.vk.id.captcha.web.m
                @Override // java.lang.Runnable
                public final void run() throws JSONException {
                    VKCaptchaJSInterface.a(jSONObject, this);
                }
            });
            this.f50846d.a();
        } catch (JSONException e10) {
            Log.e(VK_CAPTCHA_WEB_VIEW, "Error when parsing json\n Error:" + e10);
        }
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$PrimitiveArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @JavascriptInterface
    public final void VKCaptchaListenSensorsStart(@NotNull String data) {
        com.vk.id.captcha.sensors.a.b bVar;
        Intrinsics.checkNotNullParameter(data, "");
        try {
            JSONObject jSONObject = new JSONObject(data);
            int iOptInt = jSONObject.optInt("period", -1);
            JSONArray jSONArray = jSONObject.getJSONArray("bridge_sensors_list");
            if (iOptInt == -1) {
                throw new IllegalStateException("No period value was provided from WebView");
            }
            SensorsDataRepository sensorsDataRepository = this.f50846d;
            Intrinsics.checkNotNull(jSONArray);
            Intrinsics.checkNotNullParameter(jSONArray, "");
            ArrayList arrayList = new ArrayList();
            int length = jSONArray.length();
            for (int i10 = 0; i10 < length; i10++) {
                String string = jSONArray.get(i10).toString();
                Intrinsics.checkNotNullParameter(string, "");
                int iHashCode = string.hashCode();
                if (iHashCode != -1068318794) {
                    if (iHashCode != 325741829) {
                        if (iHashCode == 697872463 && string.equals("accelerometer")) {
                            bVar = com.vk.id.captcha.sensors.a.b.ACCELEROMETER;
                        } else {
                            Log.e(VK_CAPTCHA_WEB_VIEW, "Incorrect or unsupported sensor type\n Sensor: " + string);
                            bVar = null;
                        }
                    } else if (string.equals("gyroscope")) {
                        bVar = com.vk.id.captcha.sensors.a.b.GYROSCOPE;
                    } else {
                        Log.e(VK_CAPTCHA_WEB_VIEW, "Incorrect or unsupported sensor type\n Sensor: " + string);
                        bVar = null;
                    }
                } else if (string.equals("motion")) {
                    bVar = com.vk.id.captcha.sensors.a.b.MOTION;
                } else {
                    Log.e(VK_CAPTCHA_WEB_VIEW, "Incorrect or unsupported sensor type\n Sensor: " + string);
                    bVar = null;
                }
                if (bVar != null) {
                    arrayList.add(bVar);
                }
            }
            sensorsDataRepository.a(arrayList, iOptInt, new b());
        } catch (JSONException e10) {
            Log.e(VK_CAPTCHA_WEB_VIEW, "Error when parsing json\n Error:" + e10);
        }
    }

    @JavascriptInterface
    public final void VKCaptchaListenSensorsStop(@NotNull String data) {
        Intrinsics.checkNotNullParameter(data, "");
        this.f50846d.a();
    }
}
