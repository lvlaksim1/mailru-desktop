package ru.mail.data.cmd.server.parser;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.entities.MailMessage;
import ru.mail.data.entities.MailThread;
import ru.mail.data.entities.MetaThread;
import ru.mail.logic.content.MailListItem;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class SmartStatusParser {
    private final BaseMessageParser mMessageParser;
    private final MetaThreadParser mMetaThreadParser;
    private Result mResult;
    private final ThreadParser mThreadParser;

    /* JADX INFO: compiled from: ProGuard */
    private static class BaseMessageParser extends JsonMessageParser {
        private static final String BASE_MESSAGE = "base_message";
        private JSONObject mJsonThreadItem;

        /* JADX INFO: Access modifiers changed from: private */
        public boolean isBaseMessage(JSONObject jSONObject) {
            return jSONObject.has(BASE_MESSAGE);
        }

        @Override // ru.mail.data.cmd.server.parser.JsonMessageParser
        protected String parseId(JSONObject jSONObject) throws JSONException {
            return super.parseId(this.mJsonThreadItem);
        }

        private BaseMessageParser(String str, int i10, boolean z10) {
            super(str, i10, z10);
        }

        @Override // ru.mail.data.cmd.server.parser.JsonMessageParser, ru.mail.data.cmd.server.parser.SingleMessageParser
        public MailMessage parse(JSONObject jSONObject) throws JSONException {
            this.mJsonThreadItem = jSONObject;
            return super.parse(jSONObject.getJSONObject(BASE_MESSAGE));
        }
    }

    public SmartStatusParser(String str, int i10, boolean z10) {
        this.mMessageParser = new BaseMessageParser(str, i10, z10);
        this.mMetaThreadParser = new MetaThreadParser(str);
        this.mThreadParser = new ThreadParser(str, z10);
    }

    private void parseThreadsItem(JSONObject jSONObject) throws JSONException {
        if (this.mMessageParser.isBaseMessage(jSONObject)) {
            this.mResult.add(this.mMessageParser.parse(jSONObject));
        } else if (this.mMetaThreadParser.isMetaThread(jSONObject)) {
            this.mResult.add(this.mMetaThreadParser.parse(jSONObject));
        } else {
            this.mResult.add(this.mThreadParser.parse(jSONObject));
        }
    }

    public Result parse(JSONArray jSONArray) throws JSONException {
        this.mResult = new Result();
        for (int i10 = 0; i10 < jSONArray.length(); i10++) {
            parseThreadsItem(jSONArray.getJSONObject(i10));
        }
        return this.mResult;
    }

    /* JADX INFO: compiled from: ProGuard */
    public static class Result {
        private final List<MailMessage> mMessages = new ArrayList();
        private final List<MailThread> mThreads = new ArrayList();
        private final List<MetaThread> mMetaThreads = new ArrayList();
        private final List<MailListItem<?>> mOrderedItems = new ArrayList();

        /* JADX INFO: Access modifiers changed from: private */
        public void add(MailMessage mailMessage) {
            this.mOrderedItems.add(mailMessage);
            this.mMessages.add(mailMessage);
        }

        public List<MailMessage> getMessages() {
            return this.mMessages;
        }

        public List<MetaThread> getMetaThreads() {
            return this.mMetaThreads;
        }

        public List<MailListItem<?>> getOrderedItems() {
            return this.mOrderedItems;
        }

        public List<MailThread> getThreads() {
            return this.mThreads;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void add(MailThread mailThread) {
            this.mOrderedItems.add(mailThread);
            this.mThreads.add(mailThread);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void add(MetaThread metaThread) {
            this.mOrderedItems.add(metaThread);
            this.mMetaThreads.add(metaThread);
        }
    }
}
