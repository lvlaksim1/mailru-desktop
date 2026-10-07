package ru.mail.auth.webview;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
public interface MailSecondStepView {
    void checkWebViewContent();

    void loadPage();

    void setErrorState();

    void setLoadedState();

    void setLoadingState();
}
