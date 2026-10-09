package ru.mail.util.push;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes13.dex */
public enum PushMessageType {
    SINGLE_MESSAGE("single_message"),
    SUMMARY_MESSAGES_IN_THREAD("messages_in_one_thread"),
    SUMMARY_MESSAGES("messages_in_folders_and_threads");

    private String mConfigurationName;

    PushMessageType(String str) {
        this.mConfigurationName = str;
    }

    public String getConfigurationName() {
        return this.mConfigurationName;
    }
}
