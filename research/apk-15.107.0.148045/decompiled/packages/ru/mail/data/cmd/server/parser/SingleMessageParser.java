package ru.mail.data.cmd.server.parser;

import java.util.HashSet;
import org.json.JSONException;
import org.json.JSONObject;
import ru.mail.data.cmd.server.TornadoSendRequest;
import ru.mail.data.entities.Collector;
import ru.mail.data.entities.MailMessage;
import ru.mail.kit.shortcut.deeplink.ShortcutContract;
import ru.mail.search.metasearch.util.analytics.AnalyticsUtilsExtKt;
import ru.mail.ui.fragments.mailbox.mailview.clicker.UrlOpener;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class SingleMessageParser<T> {
    private final CustomTagsParser mCustomTagsParser = new CustomTagsParser();
    int mSnippetLimit;
    private final ColoredTagsParser mTagParser;

    public SingleMessageParser(int i10, boolean z10) {
        this.mSnippetLimit = i10;
        this.mTagParser = new ColoredTagsParser(z10);
    }

    private String getTrimmedStr(String str, int i10) {
        return (str == null || str.length() <= i10) ? str : str.substring(0, i10);
    }

    protected String getTrimmedSnippet(String str) {
        return getTrimmedStr(str, this.mSnippetLimit);
    }

    protected String getTrimmedSubject(String str) {
        return getTrimmedStr(str, this.mSnippetLimit / 2);
    }

    public abstract MailMessage parse(T t10) throws JSONException;

    protected void parseAddresses(JSONObject jSONObject, MailMessage mailMessage) throws JSONException {
        if (jSONObject.has("correspondents")) {
            JSONObject jSONObject2 = jSONObject.getJSONObject("correspondents");
            mailMessage.setFrom(Commons.convertAddressesToString(jSONObject2.getJSONArray("from")));
            mailMessage.setTo(Commons.convertAddressesToString(jSONObject2.getJSONArray("to")));
        }
    }

    protected void parseCustomTags(JSONObject jSONObject, MailMessage mailMessage) throws JSONException {
        mailMessage.setCustomTags(this.mCustomTagsParser.parse(jSONObject));
        mailMessage.setCustomTagAssociations(new HashSet(this.mCustomTagsParser.parseAssociations(jSONObject, mailMessage)));
    }

    protected void parseFlags(JSONObject jSONObject, MailMessage mailMessage) throws JSONException {
        if (!jSONObject.has(Collector.FLAGS)) {
            mailMessage.setUnread(true);
            return;
        }
        JSONObject jSONObject2 = (JSONObject) jSONObject.get(Collector.FLAGS);
        mailMessage.setUnread(jSONObject2.getBoolean(AnalyticsUtilsExtKt.FILTER_UNREAD));
        mailMessage.setFlagged(jSONObject2.getBoolean(AnalyticsUtilsExtKt.FILTER_FLAGGED));
        mailMessage.setPinned(jSONObject2.optBoolean(ShortcutContract.TYPE_PINNED));
        mailMessage.setReplied(jSONObject2.getBoolean(TornadoSendRequest.FIELD_REPLY));
        mailMessage.setForwarded(jSONObject2.getBoolean("forward"));
        mailMessage.setHasAttaches(jSONObject2.getBoolean("attach"));
        mailMessage.setNewsletter(jSONObject2.optBoolean("newsletter"));
        mailMessage.setMaybePhishing(jSONObject2.optBoolean("maybe_phishing"));
        mailMessage.setBimiMessage(jSONObject2.optBoolean("bimi_msg"));
        mailMessage.setBimiImportantMessage(jSONObject2.optBoolean("bimi_important_msg"));
        mailMessage.setOfficial(jSONObject2.optBoolean("official"));
        mailMessage.setOfficialNewsletter(jSONObject2.optBoolean("official_newsletter"));
        mailMessage.setSenderVerified(jSONObject2.optBoolean("show_definitely_spam"));
        mailMessage.setCanUnsubscribe(jSONObject2.optBoolean("have_unsubscribe_list"));
        mailMessage.setLabels(this.mTagParser.parse(jSONObject2, mailMessage));
        mailMessage.setEnglish(jSONObject2.optBoolean("in_english"));
        mailMessage.setHasExternalLinkWarning(jSONObject2.optBoolean(UrlOpener.EXTERNAL_LINKS_WARNING_KEY, false));
        mailMessage.setRelevant(jSONObject2.optBoolean("is_relevant"));
        mailMessage.setSenderHasEmojis(jSONObject2.optBoolean("sender_has_emojis", false));
        mailMessage.setSubjectHasEmojis(jSONObject2.optBoolean("subject_has_emojis", false));
        mailMessage.setSnippetHasEmojis(jSONObject2.optBoolean("snippet_has_emojis", false));
        mailMessage.setInternalAuthPassed(jSONObject2.optBoolean("internal_auth_passed", false));
    }
}
