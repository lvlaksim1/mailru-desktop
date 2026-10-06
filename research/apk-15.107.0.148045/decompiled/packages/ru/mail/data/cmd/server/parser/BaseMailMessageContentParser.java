package ru.mail.data.cmd.server.parser;

import java.util.Collection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ru.mail.data.entities.AttachLink;
import ru.mail.data.entities.MailMessageContent;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public abstract class BaseMailMessageContentParser extends JSONParser<MailMessageContent> {
    private static final Log LOG = Log.getLog("BaseMailMessageContentParser");

    private String getGroupId(String str) {
        Matcher matcher = Pattern.compile("/([a-zA-Z0-9]+)/").matcher(str);
        if (!matcher.find()) {
            return null;
        }
        String strGroup = matcher.group(0);
        return strGroup.substring(1, strGroup.length() - 1);
    }

    protected static String trimStartMessage(String str) {
        if (str == null || str.length() <= 0) {
            return str;
        }
        int i10 = 0;
        if (str.charAt(0) != '\n' && str.charAt(0) != ' ') {
            return str;
        }
        int length = str.length();
        while (i10 < length && (str.charAt(i10) == '\n' || str.charAt(i10) == ' ')) {
            i10++;
        }
        return i10 < length ? str.substring(i10) : str;
    }

    protected String deleteBodyOffsets(String str) {
        int i10 = 0;
        while (i10 < str.length() && (str.charAt(i10) == '\n' || str.charAt(i10) == '\t')) {
            try {
                i10++;
            } catch (Throwable th2) {
                LOG.e("cannot delete offset from converted body:", th2);
                return str;
            }
        }
        String strSubstring = str.substring(i10);
        if (strSubstring.equals("")) {
            return str;
        }
        int length = strSubstring.length() - 1;
        while (length >= 0 && (strSubstring.charAt(length) == '\n' || strSubstring.charAt(length) == '\t')) {
            length--;
        }
        return strSubstring.substring(0, length + 1);
    }

    protected void setAttachLinkInfo(MailMessageContent mailMessageContent) {
        Collection<AttachLink> attachLinksList = mailMessageContent.getAttachLinksList();
        if (attachLinksList.isEmpty()) {
            return;
        }
        AttachLink next = attachLinksList.iterator().next();
        mailMessageContent.setAttachLinkGroupId(getGroupId(next.getDownloadLink()));
        mailMessageContent.setAttachLinkDueDate(next.getDueDate());
    }
}
