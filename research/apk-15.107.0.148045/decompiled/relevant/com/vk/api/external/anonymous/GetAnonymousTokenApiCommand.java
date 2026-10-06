package com.vk.api.external.anonymous;

import com.vk.api.external.InternalMethodCall;
import com.vk.api.sdk.JsonProvider;
import com.vk.api.sdk.ParserStrategy;
import com.vk.api.sdk.StreamProvider;
import com.vk.api.sdk.VKApiJSONResponseParser;
import com.vk.api.sdk.VKApiManager;
import com.vk.api.sdk.VKMethodCall;
import com.vk.api.sdk.VKResponse;
import com.vk.api.sdk.exceptions.VKApiException;
import com.vk.api.sdk.exceptions.VKApiExecutionException;
import com.vk.api.sdk.internal.ApiCommand;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;
import ru.ok.android.sdk.SharedKt;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tH\u0014¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/vk/api/external/anonymous/GetAnonymousTokenApiCommand;", "Lcom/vk/api/sdk/internal/ApiCommand;", "", "", "sendCurrentToken", "Lcom/vk/api/sdk/exceptions/VKApiExecutionException;", "error", "<init>", "(ZLcom/vk/api/sdk/exceptions/VKApiExecutionException;)V", "Lcom/vk/api/sdk/VKApiManager;", "manager", "onExecute", "(Lcom/vk/api/sdk/VKApiManager;)Ljava/lang/String;", "external_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetAnonymousTokenApiCommand.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetAnonymousTokenApiCommand.kt\ncom/vk/api/external/anonymous/GetAnonymousTokenApiCommand\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,36:1\n1#2:37\n*E\n"})
public final class GetAnonymousTokenApiCommand extends ApiCommand<String> {
    private final boolean lanretxesreganamipakvmoca;

    @Nullable
    private final VKApiExecutionException lanretxesreganamipakvmocb;

    public /* synthetic */ GetAnonymousTokenApiCommand(boolean z10, VKApiExecutionException vKApiExecutionException, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this(z10, (i10 & 2) != 0 ? null : vKApiExecutionException);
    }

    /* JADX INFO: compiled from: ProGuard */
    static final class lanretxesreganamipakvmoca<Result> implements VKApiJSONResponseParser {
        public static final lanretxesreganamipakvmoca<Result> lanretxesreganamipakvmoca = new lanretxesreganamipakvmoca<>();

        lanretxesreganamipakvmoca() {
        }

        @Override // com.vk.api.sdk.VKApiJSONResponseParser
        public final Object parse(JSONObject json) {
            Intrinsics.checkNotNullParameter(json, "json");
            return json.getJSONObject("response").optString("token");
        }

        @Override // com.vk.api.sdk.VKApiJSONResponseParser
        public final VKResponse<Result> parse(ParserStrategy<? extends StreamProvider, InputStream, Result> parserStrategy, ParserStrategy<? extends JsonProvider, JSONObject, Result> parserStrategy2) {
            return VKApiJSONResponseParser.DefaultImpls.parse(this, parserStrategy, parserStrategy2);
        }
    }

    public GetAnonymousTokenApiCommand(boolean z10, @Nullable VKApiExecutionException vKApiExecutionException) {
        this.lanretxesreganamipakvmoca = z10;
        this.lanretxesreganamipakvmocb = vKApiExecutionException;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.vk.api.sdk.internal.ApiCommand
    @NotNull
    public String onExecute(@NotNull VKApiManager manager) throws InterruptedException, VKApiException, IOException {
        VKApiExecutionException vKApiExecutionException;
        Map<String, String> requestParams;
        String str;
        Intrinsics.checkNotNullParameter(manager, "manager");
        VKMethodCall.Builder builderArgs = new InternalMethodCall.Builder().version(manager.getConfig().getVersion()).forceRemoveAuth(true).forceAnonymous(true).allowNoAuth(true).method("auth.getAnonymToken").args("client_id", String.valueOf(manager.getConfig().getAppId())).args(SharedKt.PARAM_CLIENT_SECRET, manager.getConfig().getClientSecret()).args("device_id", manager.getConfig().getDeviceId().getValue());
        if (this.lanretxesreganamipakvmoca && (vKApiExecutionException = this.lanretxesreganamipakvmocb) != null && (requestParams = vKApiExecutionException.getRequestParams()) != null && (str = requestParams.get("access_token")) != null) {
            if (StringsKt.isBlank(str)) {
                str = null;
            }
            if (str != null) {
                builderArgs.args("anonymous_token", str);
            }
        }
        Object objExecute = manager.execute(builderArgs.build(), (VKApiJSONResponseParser<Object>) lanretxesreganamipakvmoca.lanretxesreganamipakvmoca);
        Intrinsics.checkNotNullExpressionValue(objExecute, "execute(...)");
        return (String) objExecute;
    }
}
