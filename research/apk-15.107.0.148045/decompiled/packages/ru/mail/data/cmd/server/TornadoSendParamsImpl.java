package ru.mail.data.cmd.server;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.util.TextUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.SendInlineAttach2cid;
import ru.mail.logic.content.DataManager;
import ru.mail.logic.content.MailboxContext;
import ru.mail.mailbox.cmd.ProgressListener;
import ru.mail.network.HttpMethod;
import ru.mail.network.Param;
import ru.mail.network.ParamNameValuePair;
import ru.mail.network.ServerParamsFactory;
import ru.mail.ui.fragments.adapter.AttachmentsEditor;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TornadoSendParamsImpl extends TornadoSendEditableParams {
    public static final String BLOCK_QUOTE_PLACEHOLDER = "__BODY_HTML_PLACEHOLDER__";

    @Param(method = HttpMethod.GET, name = "htmlencoded")
    private static final String HTML_ENCODED = String.valueOf(false);
    public static final String MESSAGE_ID = "message_id";

    @Param(method = HttpMethod.POST, name = "attaches", type = Param.Type.COMPLEX_OBJECT)
    private final Attaches mAttaches;
    private AttachmentsEditor mAttachmentsEditor;

    @Param(method = HttpMethod.POST, name = TornadoSendRequest.FIELD_QUOTE)
    @Nullable
    private String mBackendQuote;

    @Param(getterName = "getBody", method = HttpMethod.POST, name = "body", type = Param.Type.PARENT_OBJECT, useGetter = true)
    private final Body mBody;
    private String mBodyHtmlWithQuote;

    @Param(method = HttpMethod.POST, name = "correspondents", type = Param.Type.PARENT_OBJECT)
    private final Correspondents mCorrespondents;

    @Param(method = HttpMethod.POST, name = "from")
    private String mFrom;
    private boolean mHasInlineAttaches;

    @Param(method = HttpMethod.POST, name = "id")
    private String mId;
    private String mOriginalBodyHtml;

    @Param(method = HttpMethod.POST, name = "priority")
    private int mPriority;
    private ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> mProgressListener;

    @Param(method = HttpMethod.POST, name = "receipt")
    private boolean mReadVerify;
    private List<SendInlineAttach2cid> mSendAttaches2cid;

    @Param(method = HttpMethod.POST, name = "send_date")
    private long mSendDate;

    @Param(method = HttpMethod.POST, name = "source", type = Param.Type.PARENT_OBJECT)
    private final Source mSource;
    private String mSourceId;

    @Param(method = HttpMethod.POST, name = "subject")
    private String mSubject;

    /* JADX INFO: compiled from: ProGuard */
    private class Attaches implements ServerParamsFactory {
        @Nullable
        private static String optAttachId(@Nullable String str) {
            if (TextUtils.isEmpty(str)) {
                return null;
            }
            return Uri.parse(str).getQueryParameter("id");
        }

        @Override // ru.mail.network.ServerParamsFactory
        public List<NameValuePair> createParams() {
            ArrayList arrayList = new ArrayList();
            try {
                JSONObject jSONObject = new JSONObject();
                JSONArray jSONArray = new JSONArray();
                for (String str : TornadoSendParamsImpl.this.mAttachmentsEditor.getAttachmentIdsOnServer()) {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("id", str);
                    jSONObject2.put("type", "attach");
                    jSONArray.put(jSONObject2);
                }
                for (String str2 : TornadoSendParamsImpl.this.mAttachmentsEditor.getCloudStockAttachmentIdsOnServer()) {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("id", str2);
                    jSONObject3.put("type", "cloud_stock");
                    jSONArray.put(jSONObject3);
                }
                if (TornadoSendParamsImpl.this.mAttachmentsEditor.getCloudAttachmentBundleId() != null) {
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("id", TornadoSendParamsImpl.this.mAttachmentsEditor.getCloudAttachmentBundleId());
                    jSONObject4.put("type", "cloud_stock");
                    jSONArray.put(jSONObject4);
                }
                for (SendInlineAttach2cid sendInlineAttach2cid : TornadoSendParamsImpl.this.mSendAttaches2cid) {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put(TornadoSendRequest.FIELD_ATTACHES_CONTENT_ID, sendInlineAttach2cid.getCidTo());
                    jSONObject5.put("type", "inline");
                    jSONObject5.put("part_id", optAttachId(sendInlineAttach2cid.getAttachLinkFrom()));
                    jSONArray.put(jSONObject5);
                }
                jSONObject.put("list", jSONArray);
                arrayList.add(new ParamNameValuePair("attaches", jSONObject.toString()));
                return arrayList;
            } catch (JSONException e10) {
                e10.printStackTrace();
                return arrayList;
            }
        }

        private Attaches() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class Body implements ServerParamsFactory {

        @Param(method = HttpMethod.POST, name = TornadoSendRequest.FIELD_BODY_HTML)
        private String mHtml;

        @Param(method = HttpMethod.POST, name = "text")
        private String mText;

        @Override // ru.mail.network.ServerParamsFactory
        public List<NameValuePair> createParams() {
            return HttpMethod.parsePostParams(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Body body = (Body) obj;
                String str = this.mHtml;
                if (str == null ? body.mHtml != null : !str.equals(body.mHtml)) {
                    return false;
                }
                String str2 = this.mText;
                String str3 = body.mText;
                if (str2 != null) {
                    return str2.equals(str3);
                }
                if (str3 == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            String str = this.mHtml;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mText;
            return iHashCode + (str2 != null ? str2.hashCode() : 0);
        }

        private Body() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    private static class Correspondents implements ServerParamsFactory {

        @Param(method = HttpMethod.POST, name = "bcc")
        private String mBcc;

        @Param(method = HttpMethod.POST, name = "cc")
        private String mCc;

        @Param(method = HttpMethod.POST, name = "to")
        private String mTo;

        @Override // ru.mail.network.ServerParamsFactory
        public List<NameValuePair> createParams() {
            return HttpMethod.parsePostParams(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Correspondents correspondents = (Correspondents) obj;
                String str = this.mTo;
                if (str == null ? correspondents.mTo != null : !str.equals(correspondents.mTo)) {
                    return false;
                }
                String str2 = this.mCc;
                if (str2 == null ? correspondents.mCc != null : !str2.equals(correspondents.mCc)) {
                    return false;
                }
                String str3 = this.mBcc;
                String str4 = correspondents.mBcc;
                if (str3 != null) {
                    return str3.equals(str4);
                }
                if (str4 == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            String str = this.mTo;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mCc;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mBcc;
            return iHashCode2 + (str3 != null ? str3.hashCode() : 0);
        }

        private Correspondents() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Source implements ServerParamsFactory {

        @Param(method = HttpMethod.POST, name = TornadoSendRequest.FIELD_DRAFT)
        private String mDraft;

        @Param(method = HttpMethod.POST, name = "forward")
        private String mForward;

        @Param(method = HttpMethod.POST, name = TornadoSendRequest.FIELD_REPLY)
        private String mReply;

        @Param(method = HttpMethod.POST, name = TornadoSendRequest.FIELD_SCHEDULE)
        private String mSchedule;

        @Override // ru.mail.network.ServerParamsFactory
        public List<NameValuePair> createParams() {
            return HttpMethod.parsePostParams(this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj != null && getClass() == obj.getClass()) {
                Source source = (Source) obj;
                String str = this.mDraft;
                if (str == null ? source.mDraft != null : !str.equals(source.mDraft)) {
                    return false;
                }
                String str2 = this.mReply;
                if (str2 == null ? source.mReply != null : !str2.equals(source.mReply)) {
                    return false;
                }
                String str3 = this.mForward;
                if (str3 == null ? source.mForward != null : !str3.equals(source.mForward)) {
                    return false;
                }
                String str4 = this.mSchedule;
                String str5 = source.mSchedule;
                if (str4 != null) {
                    return str4.equals(str5);
                }
                if (str5 == null) {
                    return true;
                }
            }
            return false;
        }

        public int hashCode() {
            String str = this.mDraft;
            int iHashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.mReply;
            int iHashCode2 = (iHashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
            String str3 = this.mForward;
            int iHashCode3 = (iHashCode2 + (str3 != null ? str3.hashCode() : 0)) * 31;
            String str4 = this.mSchedule;
            return iHashCode3 + (str4 != null ? str4.hashCode() : 0);
        }
    }

    public TornadoSendParamsImpl(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager) {
        super(mailboxContext, dataManager);
        this.mSendAttaches2cid = new ArrayList();
        this.mSource = new Source();
        this.mCorrespondents = new Correspondents();
        this.mBody = new Body();
        this.mAttaches = new Attaches();
        this.mAttachmentsEditor = new AttachmentsEditor();
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public TornadoSendEditableParams edit(MailboxContext mailboxContext, DataManager dataManager) {
        return new TornadoSendParamsImpl(mailboxContext, dataManager, this);
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass() || !super.equals(obj)) {
            return false;
        }
        TornadoSendParamsImpl tornadoSendParamsImpl = (TornadoSendParamsImpl) obj;
        if (this.mPriority != tornadoSendParamsImpl.mPriority || this.mHasInlineAttaches != tornadoSendParamsImpl.mHasInlineAttaches) {
            return false;
        }
        String str = this.mId;
        if (str == null ? tornadoSendParamsImpl.mId != null : !str.equals(tornadoSendParamsImpl.mId)) {
            return false;
        }
        Source source = this.mSource;
        if (source == null ? tornadoSendParamsImpl.mSource != null : !source.equals(tornadoSendParamsImpl.mSource)) {
            return false;
        }
        String str2 = this.mFrom;
        if (str2 == null ? tornadoSendParamsImpl.mFrom != null : !str2.equals(tornadoSendParamsImpl.mFrom)) {
            return false;
        }
        String str3 = this.mSubject;
        if (str3 == null ? tornadoSendParamsImpl.mSubject != null : !str3.equals(tornadoSendParamsImpl.mSubject)) {
            return false;
        }
        Correspondents correspondents = this.mCorrespondents;
        if (correspondents == null ? tornadoSendParamsImpl.mCorrespondents != null : !correspondents.equals(tornadoSendParamsImpl.mCorrespondents)) {
            return false;
        }
        Body body = this.mBody;
        if (body == null ? tornadoSendParamsImpl.mBody != null : !body.equals(tornadoSendParamsImpl.mBody)) {
            return false;
        }
        String str4 = this.mSourceId;
        if (str4 == null ? tornadoSendParamsImpl.mSourceId != null : !str4.equals(tornadoSendParamsImpl.mSourceId)) {
            return false;
        }
        AttachmentsEditor attachmentsEditor = this.mAttachmentsEditor;
        if (attachmentsEditor == null ? tornadoSendParamsImpl.mAttachmentsEditor == null : attachmentsEditor.equals(tornadoSendParamsImpl.mAttachmentsEditor)) {
            return this.mReadVerify == tornadoSendParamsImpl.mReadVerify && this.mSendDate == tornadoSendParamsImpl.mSendDate;
        }
        return false;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public AttachmentsEditor getAttachmentsEditor() {
        return this.mAttachmentsEditor;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getBcc() {
        return this.mCorrespondents.mBcc;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getBlockQuote() {
        return this.mBackendQuote;
    }

    public Body getBody() {
        if (this.mBackendQuote == null) {
            this.mBody.mHtml = this.mOriginalBodyHtml;
        } else {
            this.mBody.mHtml = this.mBodyHtmlWithQuote;
        }
        return this.mBody;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getBodyText() {
        return this.mBody.mText;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getCc() {
        return this.mCorrespondents.mCc;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getFrom() {
        return this.mFrom;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getId() {
        return this.mId;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getOriginalBodyHtml() {
        return this.mOriginalBodyHtml;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public TornadoSendParams.Priority getPriority() {
        for (TornadoSendParams.Priority priority : TornadoSendParams.Priority.values()) {
            if (priority.getValue() == this.mPriority) {
                return priority;
            }
        }
        return null;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> getProgressListener() {
        return this.mProgressListener;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public long getSendDate() {
        return this.mSendDate;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getSourceId() {
        return this.mSourceId;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getSubject() {
        return this.mSubject;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public String getTo() {
        return this.mCorrespondents.mTo;
    }

    @Override // ru.mail.serverapi.ServerCommandBaseParams
    public int hashCode() {
        int iHashCode = super.hashCode() * 31;
        String str = this.mId;
        int iHashCode2 = (iHashCode + (str != null ? str.hashCode() : 0)) * 31;
        Source source = this.mSource;
        int iHashCode3 = (iHashCode2 + (source != null ? source.hashCode() : 0)) * 31;
        String str2 = this.mFrom;
        int iHashCode4 = (iHashCode3 + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.mSubject;
        int iHashCode5 = (((iHashCode4 + (str3 != null ? str3.hashCode() : 0)) * 31) + this.mPriority) * 31;
        long j10 = this.mSendDate;
        int i10 = (iHashCode5 + ((int) (j10 ^ (j10 >>> 32)))) * 31;
        Correspondents correspondents = this.mCorrespondents;
        int iHashCode6 = (i10 + (correspondents != null ? correspondents.hashCode() : 0)) * 31;
        Body body = this.mBody;
        int iHashCode7 = (iHashCode6 + (body != null ? body.hashCode() : 0)) * 31;
        String str4 = this.mSourceId;
        int iHashCode8 = (iHashCode7 + (str4 != null ? str4.hashCode() : 0)) * 31;
        AttachmentsEditor attachmentsEditor = this.mAttachmentsEditor;
        return ((((iHashCode8 + (attachmentsEditor != null ? attachmentsEditor.hashCode() : 0)) * 31) + (this.mHasInlineAttaches ? 1 : 0)) * 31) + (this.mReadVerify ? 1 : 0);
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public boolean isHasInlineAttaches() {
        return this.mHasInlineAttaches;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendParams
    public boolean needReadVerify() {
        return this.mReadVerify;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setAttachmentsEditor(AttachmentsEditor attachmentsEditor) {
        this.mAttachmentsEditor = attachmentsEditor;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setBcc(String str) {
        this.mCorrespondents.mBcc = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public TornadoSendParams setBlockQuote(String str) {
        this.mBackendQuote = str;
        return this;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setBodyHtmlWithQuote(String str) {
        this.mBodyHtmlWithQuote = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setBodyText(String str) {
        this.mBody.mText = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setCc(String str) {
        this.mCorrespondents.mCc = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setDraft(String str) {
        this.mSource.mDraft = str;
        this.mSourceId = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setForward(String str) {
        this.mSource.mForward = str;
        this.mSourceId = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setFrom(String str) {
        this.mFrom = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setHasInlineAttaches(boolean z10) {
        this.mHasInlineAttaches = z10;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setId(String str) {
        this.mId = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setOriginalBodyHtml(String str) {
        this.mOriginalBodyHtml = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setPriority(TornadoSendParams.Priority priority) {
        this.mPriority = priority.getValue();
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setProgressListener(ProgressListener<ru.mail.logic.cmd.attachments.ProgressData> progressListener) {
        this.mProgressListener = progressListener;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setReadVerify(boolean z10) {
        this.mReadVerify = z10;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setRedirect(String str) {
        this.mSourceId = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setReply(String str) {
        this.mSource.mReply = str;
        this.mSourceId = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setSendAttaches2cid(Collection<SendInlineAttach2cid> collection) {
        this.mSendAttaches2cid = new ArrayList(collection);
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setSendDate(long j10) {
        this.mSendDate = j10;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setSubject(String str) {
        this.mSubject = str;
    }

    @Override // ru.mail.data.cmd.server.TornadoSendEditableParams
    public void setTo(String str) {
        this.mCorrespondents.mTo = str;
    }

    private TornadoSendParamsImpl(@NotNull MailboxContext mailboxContext, @NotNull DataManager dataManager, @NotNull TornadoSendParamsImpl tornadoSendParamsImpl) {
        this(mailboxContext, dataManager);
        this.mId = tornadoSendParamsImpl.mId;
        this.mSource.mDraft = tornadoSendParamsImpl.mSource.mDraft;
        this.mSource.mReply = tornadoSendParamsImpl.mSource.mReply;
        this.mSource.mForward = tornadoSendParamsImpl.mSource.mForward;
        this.mSource.mSchedule = tornadoSendParamsImpl.mSource.mSchedule;
        this.mSourceId = tornadoSendParamsImpl.mSourceId;
        this.mFrom = tornadoSendParamsImpl.mFrom;
        this.mSubject = tornadoSendParamsImpl.mSubject;
        this.mPriority = tornadoSendParamsImpl.mPriority;
        this.mSendDate = tornadoSendParamsImpl.mSendDate;
        this.mReadVerify = tornadoSendParamsImpl.mReadVerify;
        this.mCorrespondents.mTo = tornadoSendParamsImpl.mCorrespondents.mTo;
        this.mCorrespondents.mBcc = tornadoSendParamsImpl.mCorrespondents.mBcc;
        this.mCorrespondents.mCc = tornadoSendParamsImpl.mCorrespondents.mCc;
        this.mBodyHtmlWithQuote = tornadoSendParamsImpl.mBodyHtmlWithQuote;
        this.mOriginalBodyHtml = tornadoSendParamsImpl.mOriginalBodyHtml;
        this.mBody.mText = tornadoSendParamsImpl.mBody.mText;
        this.mProgressListener = tornadoSendParamsImpl.mProgressListener;
        this.mHasInlineAttaches = tornadoSendParamsImpl.mHasInlineAttaches;
        this.mAttachmentsEditor = tornadoSendParamsImpl.mAttachmentsEditor;
        this.mBackendQuote = tornadoSendParamsImpl.mBackendQuote;
        this.mSendAttaches2cid = tornadoSendParamsImpl.mSendAttaches2cid;
    }
}
