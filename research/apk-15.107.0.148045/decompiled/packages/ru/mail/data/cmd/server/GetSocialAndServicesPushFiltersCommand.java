package ru.mail.data.cmd.server;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Keep;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.data.cmd.server.parser.PushFilterParser;
import ru.mail.logic.pushfilters.PushFilter;
import ru.mail.logic.pushfilters.PushFilterEntity;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.HttpMethod;
import ru.mail.network.NetworkCommand;
import ru.mail.network.NetworkCommandWithSession;
import ru.mail.network.Param;
import ru.mail.network.PreferenceHostProvider;
import ru.mail.network.UrlPath;
import ru.mail.network.service.NetworkService;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.GetServerRequest;
import ru.mail.serverapi.ServerCommandBase;
import ru.mail.serverapi.ServerCommandBaseParams;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/push_default_host", defSchemeStrRes = "string/push_default_scheme", prefKey = "push")
@UrlPath(pathSegments = {"mail_filter"})
public class GetSocialAndServicesPushFiltersCommand extends GetServerRequest<Params, List<PushFilterEntity>> {
    public static final String APPLICATION_PARAM = "application";
    private static final Log LOG = Log.getLog("GetSocialAndServicesPushFiltersCommand");
    private static final String SERVICE = "service";
    private static final String SOCIAL = "social";

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandBaseParams {

        @Keep
        @Param(method = HttpMethod.GET, name = "application")
        private final String mApplication;

        public Params(@Nullable String str, @Nullable FolderState folderState, @Nullable String str2) {
            super(new AccountInfo(str), folderState);
            this.mApplication = str2;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        protected boolean needAppendActMode() {
            return false;
        }
    }

    public GetSocialAndServicesPushFiltersCommand(Context context, Params params) {
        super(context, params);
    }

    private String keyForType(PushFilter.Type type) {
        return type.equals(PushFilter.Type.SERVICE) ? "service" : SOCIAL;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommand
    protected HostProvider createHostProvider(HostProviderAnnotation hostProviderAnnotation) {
        return new PreferenceHostProvider(getContext(), hostProviderAnnotation, (Bundle) null, new HostProvider.Configuration() { // from class: ru.mail.data.cmd.server.GetSocialAndServicesPushFiltersCommand.1
            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needPlatformParams() {
                return false;
            }

            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needSign() {
                return false;
            }

            @Override // ru.mail.network.HostProvider.Configuration
            public boolean needUserAgent() {
                return true;
            }
        });
    }

    @Override // ru.mail.network.NetworkCommand
    protected List<String> getAllowedGetParams() {
        ArrayList arrayList = new ArrayList();
        arrayList.add("application");
        return arrayList;
    }

    public List<PushFilterEntity> load(String str, PushFilter.Type type) {
        List<PushFilterEntity> list = Collections.EMPTY_LIST;
        try {
            return new PushFilterParser(type).parse(new JSONObject(str).getJSONArray(keyForType(type)));
        } catch (JSONException e10) {
            LOG.e("Cannot parse push filters settings", e10);
            e10.printStackTrace();
            return list;
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public ServerCommandBase<Params, List<PushFilterEntity>>.ServerCommandBaseDelegate getCustomDelegate() {
        return new ServerCommandBase<Params, List<PushFilterEntity>>.LegacyDelegate() { // from class: ru.mail.data.cmd.server.GetSocialAndServicesPushFiltersCommand.2
            @Override // ru.mail.serverapi.ServerCommandBase.LegacyDelegate, ru.mail.network.NetworkCommand.NetworkCommandBaseDelegate
            protected String getResponseStatusImpl(String str) {
                return "OK";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    @NotNull
    public List<PushFilterEntity> onPostExecuteRequest(NetworkCommand.Response response) throws NetworkCommand.PostExecuteException {
        String respString = response.getRespString();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(load(respString, PushFilter.Type.SOCIAL));
        arrayList.addAll(load(respString, PushFilter.Type.SERVICE));
        return arrayList;
    }

    @Override // ru.mail.serverapi.ServerCommandBase, ru.mail.network.NetworkCommandWithSession
    protected void setUpSession(NetworkService networkService) throws NetworkCommandWithSession.BadSessionException {
    }
}
