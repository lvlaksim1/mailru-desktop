package ru.mail.data.cmd.server;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
public class TokenResult {
    private final int mCodeLength;
    private final int mCodeWait;
    private final String mRegTokenId;

    public TokenResult(String str, int i10, int i11) {
        this.mRegTokenId = str;
        this.mCodeLength = i10;
        this.mCodeWait = i11;
    }

    public int getCodeLength() {
        return this.mCodeLength;
    }

    public int getCodeWait() {
        return this.mCodeWait;
    }

    public String getRegTokenId() {
        return this.mRegTokenId;
    }
}
