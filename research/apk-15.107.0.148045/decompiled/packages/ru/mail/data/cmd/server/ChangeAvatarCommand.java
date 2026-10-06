package ru.mail.data.cmd.server;

import android.content.Context;
import com.google.android.gms.analytics.ecommerce.ProductAction;
import com.vk.superapp.api.dto.story.actions.WebActionSituationalTemplate;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.auth.request.AccountInfo;
import ru.mail.logic.content.ChangeAvatarError;
import ru.mail.mailbox.cmd.CommandStatus;
import ru.mail.mailbox.cmd.EmptyResult;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.network.HostProvider;
import ru.mail.network.HostProviderAnnotation;
import ru.mail.network.NetworkCommand;
import ru.mail.network.UrlPath;
import ru.mail.network.requestbody.MultipartRequestBody;
import ru.mail.serverapi.FolderState;
import ru.mail.serverapi.MailAuthorizationApiType;
import ru.mail.serverapi.ServerCommandEmailParams;
import ru.mail.serverapi.WithSampling;
import ru.mail.util.log.Log;
import ru.ok.android.api.core.ApiUris;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@HostProviderAnnotation(defHostStrRes = "string/change_avatar_default_host", defSchemeStrRes = "string/change_avatar_default_scheme", prefKey = "change_avatar")
@UrlPath(pathSegments = {ApiUris.AUTHORITY_API, "v1", "user", WebActionSituationalTemplate.AVATARS, ProductAction.ACTION_ADD})
@WithSampling
public class ChangeAvatarCommand extends UploadFileCommand<Params, EmptyResult, ProgressData> {
    public static final String AVATAR = "avatar";
    private static final String INVALID_DATA_ERROR_CODE = "400";
    private static final Log LOG = Log.getLog("ChangeAvatarCommand");

    /* JADX INFO: compiled from: ProGuard */
    public static class Params extends ServerCommandEmailParams {
        private final String mFilePath;

        public Params(@NotNull String str, @NotNull AccountInfo accountInfo, @Nullable FolderState folderState) {
            super(accountInfo, folderState);
            this.mFilePath = str;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof Params) && super.equals(obj) && this.mFilePath.equals(((Params) obj).mFilePath);
        }

        public String getFilePath() {
            return this.mFilePath;
        }

        @Override // ru.mail.serverapi.ServerCommandBaseParams
        public int hashCode() {
            return (super.hashCode() * 31) + this.mFilePath.hashCode();
        }
    }

    public ChangeAvatarCommand(Context context, Params params, ProgressListener<ProgressData> progressListener, boolean z10) {
        this(context, params, progressListener, null, z10);
    }

    private CommandStatus<?> processInvalidDataError(String str) throws JSONException {
        JSONObject jSONObject = new JSONObject(str).getJSONObject("body");
        String string = jSONObject.getJSONObject("mainphoto").getString("error");
        String string2 = jSONObject.getJSONObject("avatar").getString("error");
        ChangeAvatarError changeAvatarError = ChangeAvatarError.UNKNOWN_ERROR;
        if ("required_any".equals(string) && "required_any".equals(string2)) {
            changeAvatarError = ChangeAvatarError.NO_IMAGES_SEND;
        } else if ("filesize_limit_exceeded".equals(string2)) {
            changeAvatarError = ChangeAvatarError.AVATAR_FILE_SIZE_LIMIT_EXCEEDED;
        } else if ("size_too_small".equals(string)) {
            changeAvatarError = ChangeAvatarError.PHOTO_TOO_SMALL;
        } else if ("size_too_big".equals(string2)) {
            changeAvatarError = ChangeAvatarError.AVATAR_IMAGE_SIZE_LIMIT_EXCEEDED;
        } else if ("invalid_format".equals(string2)) {
            changeAvatarError = ChangeAvatarError.UNKNOWN_FORMAT;
        }
        return new CommandStatus.ERROR(changeAvatarError);
    }

    @Override // ru.mail.serverapi.ServerCommandBase
    protected MailAuthorizationApiType getDefaultApiType() {
        return MailAuthorizationApiType.TORNADO_MPOP;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // ru.mail.data.cmd.server.UploadFileCommand
    protected void onPrepareMultipartBody(MultipartRequestBody multipartRequestBody) {
        FileInputStream fileInputStream;
        if (isCancelled()) {
            return;
        }
        try {
            fileInputStream = new FileInputStream(((Params) getParams()).mFilePath);
        } catch (FileNotFoundException e10) {
            LOG.e("buildHttpEntity()", e10);
            fileInputStream = null;
        }
        if (fileInputStream != null) {
            multipartRequestBody.addInputStreamPart("avatar", fileInputStream, "avatar.png");
        }
    }

    @Override // ru.mail.network.ProgressUpdatable
    public void onProgressUpdate(long j10, long j11, long j12) {
        LOG.d("onProgressUpdate " + j11 + " of " + j12);
        if (isCancelled()) {
            return;
        }
        notifyObservers(new ProgressData(j11, j12));
    }

    @Override // ru.mail.network.NetworkCommand
    protected CommandStatus<?> processResponse(NetworkCommand.Response response) {
        response.createStringFromData();
        String respString = response.getRespString();
        if (respString != null) {
            try {
                if (INVALID_DATA_ERROR_CODE.equals(new JSONObject(respString).getString("status"))) {
                    return processInvalidDataError(respString);
                }
            } catch (JSONException e10) {
                return new CommandStatus.ERROR(e10);
            }
        }
        return super.processResponse(response);
    }

    ChangeAvatarCommand(Context context, Params params, ProgressListener<ProgressData> progressListener, HostProvider hostProvider, boolean z10) {
        super(context, params, hostProvider, z10);
        addObserver(progressListener);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // ru.mail.network.NetworkCommand
    public EmptyResult onPostExecuteRequest(NetworkCommand.Response response) {
        return new EmptyResult();
    }
}
