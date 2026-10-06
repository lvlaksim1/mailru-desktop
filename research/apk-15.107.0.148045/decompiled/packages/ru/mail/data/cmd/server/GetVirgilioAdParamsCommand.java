package ru.mail.data.cmd.server;

import android.content.Context;
import ru.mail.data.cmd.server.ad.GetAdParamsCommand;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.UrlPath;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/virgilio_default_host", defSchemeStrRes = "string/virgilio_default_scheme", needPlatformParams = false, needSign = false, needUserAgent = false, prefKey = "virgilio_api")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "getAccountData.php"})
public class GetVirgilioAdParamsCommand extends GetAdParamsCommand {
    public GetVirgilioAdParamsCommand(Context context, GetAdParamsCommand.Params params) {
        super(context, params);
    }
}
