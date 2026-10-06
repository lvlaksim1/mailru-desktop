package ru.mail.data.cmd.server;

import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.io.IOException;
import ru.mail.mailapp.BuildConfig;
import ru.mail.mailapp.R;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@HostProviderAnnotation(defHost = R.string.rb_default_host, defScheme = R.string.rb_default_scheme, needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "rb")
@UrlPath(pathSegments = {"mobile", BuildConfig.TRANSLATIONS_SLOT})
public class RBTranslationsCommand extends RequestTranslationsCommand<RbTranslationParams> {
    private final Log mLog;

    /* JADX INFO: compiled from: ProGuard */
    public static class RbTranslationParams extends ServerCommandBaseParams {

        @Keep
        @Param(method = HttpMethod.GET, name = "appbuild")
        private static final int APP_BUILD = 148045;

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    public RBTranslationsCommand(Context context, boolean z10) {
        super(context, new RbTranslationParams(), z10);
        this.mLog = Log.getLog("RBTranslationsRequest");
    }

    @Override // ru.mail.network.NetworkCommand
    @Nullable
    protected String getTag() {
        return "ad_translations";
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    @NonNull
    protected RequestBody onPrepareRequestBody() throws IOException {
        return new MyTargetRequestBodyCreator(this.mLog).create(providePostParams());
    }
}
