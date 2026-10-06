package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.data.cmd.server.ad.GetAdParamsCommand;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.UrlPath;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/libero_default_host", defSchemeStrRes = "string/libero_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "libero_api")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "getAccountData.php"})
public class GetLiberoAdParamsCommand extends GetAdParamsCommand {
    public GetLiberoAdParamsCommand(Context context, GetAdParamsCommand.Params params) {
        super(context, params);
    }
}
