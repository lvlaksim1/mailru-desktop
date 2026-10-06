package ru.mail.data.cmd.server;

import android.net.Uri;
import android.util.Base64OutputStream;
import androidx.compose.runtime.internal.StabilityInferred;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.zip.DeflaterOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import org.apache.http.NameValuePair;
import org.jetbrains.annotations.NotNull;
import ru.mail.kotlett.divkit.VariableConstants;
import ru.mail.network.requestbody.ByteRequestBody;
import ru.mail.network.requestbody.RequestBody;
import ru.mail.util.log.Log;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0006\u001a\u00020\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lru/mail/data/cmd/server/MyTargetRequestBodyCreator;", "", "log", "Lru/mail/util/log/Log;", "<init>", "(Lru/mail/util/log/Log;)V", "create", "Lru/mail/network/requestbody/RequestBody;", "params", "", "Lorg/apache/http/NameValuePair;", "prepareByteArray", "", VariableConstants.TYPE_STRING, "", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMyTargetRequestBodyCreator.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MyTargetRequestBodyCreator.kt\nru/mail/data/cmd/server/MyTargetRequestBodyCreator\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,45:1\n1869#2,2:46\n*S KotlinDebug\n*F\n+ 1 MyTargetRequestBodyCreator.kt\nru/mail/data/cmd/server/MyTargetRequestBodyCreator\n*L\n17#1:46,2\n*E\n"})
public final class MyTargetRequestBodyCreator {

    @NotNull
    private static final String MY_TARGET_CONTENT_TYPE = "application/x-mtrgdata-v1";

    @NotNull
    private final Log log;
    public static final int $stable = 8;

    public MyTargetRequestBodyCreator(@NotNull Log log) {
        Intrinsics.checkNotNullParameter(log, "log");
        this.log = log;
    }

    private final byte[] prepareByteArray(String string) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(new Base64OutputStream(byteArrayOutputStream, 2));
        try {
            try {
                byte[] bytes = string.getBytes(Charsets.UTF_8);
                Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                deflaterOutputStream.write(bytes);
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(deflaterOutputStream, null);
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(deflaterOutputStream, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            this.log.d("Error occurred when request body prepare: " + th4);
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
        return byteArray;
    }

    @NotNull
    public final RequestBody create(@NotNull List<NameValuePair> params) {
        Intrinsics.checkNotNullParameter(params, "params");
        Uri.Builder builder = new Uri.Builder();
        for (NameValuePair nameValuePair : params) {
            builder.appendQueryParameter(nameValuePair.getName(), nameValuePair.getValue());
        }
        String encodedQuery = builder.build().getEncodedQuery();
        if (encodedQuery == null) {
            encodedQuery = "";
        }
        return new ByteRequestBody(prepareByteArray(encodedQuery), MY_TARGET_CONTENT_TYPE);
    }
}
