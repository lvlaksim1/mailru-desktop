package ru.mail.auth.request;

import ru.mail.network.HttpMethod;
import ru.mail.network.Param;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes8.dex */
abstract class AuthorizationHeaderParams {

    @Param(method = HttpMethod.HEADER_SET, name = "Authorization")
    private final String mAuthorization;

    public AuthorizationHeaderParams(String str) {
        this.mAuthorization = "Bearer " + str;
    }
}
