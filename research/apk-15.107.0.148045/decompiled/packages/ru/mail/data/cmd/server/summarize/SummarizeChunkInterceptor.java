package ru.mail.data.cmd.server.summarize;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;
import okio.Okio;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.kotlett.services.billing.analytics.Event;
import ru.mail.network.request.RequestTag;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 \u00182\u00020\u0001:\u0002\u0017\u0018B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J \u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u000bH\u0002J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000bH\u0002R\u001c\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\u0019"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor;", "Lokhttp3/Interceptor;", "<init>", "()V", "summarizeChunkReceiver", "Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor$SummarizeChunkReceiver;", "getSummarizeChunkReceiver", "()Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor$SummarizeChunkReceiver;", "setSummarizeChunkReceiver", "(Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor$SummarizeChunkReceiver;)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "handleSummarizeChunks", Event.Companion.Network.Fail.REQUEST_TAG, "Lokhttp3/Request;", "originalResponse", "requestTag", "", "stopRequest", "unzip", "response", "SummarizeChunkReceiver", "Companion", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSummarizeChunkInterceptor.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SummarizeChunkInterceptor.kt\nru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 GzipSource.kt\nokio/-GzipSourceExtensions\n*L\n1#1,122:1\n1#2:123\n221#3:124\n*S KotlinDebug\n*F\n+ 1 SummarizeChunkInterceptor.kt\nru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor\n*L\n104#1:124\n*E\n"})
public final class SummarizeChunkInterceptor implements Interceptor {
    private static final int CLIENT_CLOSED_CONNECTION = 499;

    @NotNull
    public static final String SUMMARIZE_TAG_PREFIX = "Summarize";

    @Nullable
    private SummarizeChunkReceiver summarizeChunkReceiver;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes9.dex */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0005H&¨\u0006\u0007À\u0006\u0003"}, d2 = {"Lru/mail/data/cmd/server/summarize/SummarizeChunkInterceptor$SummarizeChunkReceiver;", "", "onChunkReceived", "", "chunkJson", "", "tag", "mail-app-core_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface SummarizeChunkReceiver {
        void onChunkReceived(@NotNull String chunkJson);

        @NotNull
        String tag();
    }

    private final Response handleSummarizeChunks(Request request, Response originalResponse, String requestTag) throws IOException {
        ResponseBody responseBodyBody = originalResponse.body();
        Integer numValueOf = responseBodyBody != null ? Integer.valueOf((int) responseBodyBody.getContentLength()) : null;
        if (numValueOf != null && numValueOf.intValue() == -1) {
            SummarizeChunkReceiver summarizeChunkReceiver = this.summarizeChunkReceiver;
            if (summarizeChunkReceiver == null) {
                return stopRequest(request, originalResponse);
            }
            Response responseUnzip = unzip(originalResponse);
            ResponseBody responseBodyBody2 = responseUnzip.body();
            if (responseBodyBody2 != null) {
                BufferedSource source = responseBodyBody2.getSource();
                Buffer buffer = new Buffer();
                while (!source.exhausted() && Intrinsics.areEqual(requestTag, summarizeChunkReceiver.tag())) {
                    String utf8Line = source.readUtf8Line();
                    if (utf8Line != null) {
                        summarizeChunkReceiver.onChunkReceived(utf8Line);
                        buffer.clear();
                        byte[] bytes = utf8Line.getBytes(Charsets.UTF_8);
                        Intrinsics.checkNotNullExpressionValue(bytes, "getBytes(...)");
                        buffer.write(bytes);
                    }
                }
                if (!Intrinsics.areEqual(requestTag, summarizeChunkReceiver.tag())) {
                    return stopRequest(request, originalResponse);
                }
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                try {
                    gZIPOutputStream.write(buffer.readByteArray());
                    Unit unit = Unit.INSTANCE;
                    CloseableKt.closeFinally(gZIPOutputStream, null);
                    Response.Builder builderHeaders = responseUnzip.newBuilder().headers(responseUnzip.headers());
                    String strValueOf = String.valueOf(responseBodyBody2.get$contentType());
                    long contentLength = responseBodyBody2.getContentLength();
                    Buffer buffer2 = new Buffer();
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    Intrinsics.checkNotNullExpressionValue(byteArray, "toByteArray(...)");
                    return builderHeaders.body(new RealResponseBody(strValueOf, contentLength, buffer2.write(byteArray))).build();
                } catch (Throwable th2) {
                    try {
                        throw th2;
                    } catch (Throwable th3) {
                        CloseableKt.closeFinally(gZIPOutputStream, th2);
                        throw th3;
                    }
                }
            }
        }
        return originalResponse;
    }

    private final Response stopRequest(Request request, Response originalResponse) {
        this.summarizeChunkReceiver = null;
        Response.Builder builderMessage = new Response.Builder().code(499).protocol(Protocol.HTTP_2).message("summarize chunk request interrupted");
        ResponseBody responseBodyBody = originalResponse.body();
        return builderMessage.body(new RealResponseBody(String.valueOf(responseBodyBody != null ? responseBodyBody.get$contentType() : null), -1L, new Buffer())).request(request).build();
    }

    private final Response unzip(Response response) throws IOException {
        String str;
        ResponseBody responseBodyBody = response.body();
        if (responseBodyBody == null || (str = response.headers().get("Content-Encoding")) == null || !Intrinsics.areEqual(str, "gzip")) {
            return response;
        }
        return response.newBuilder().headers(response.headers().newBuilder().build()).body(new RealResponseBody(String.valueOf(responseBodyBody.get$contentType()), responseBodyBody.getContentLength(), Okio.buffer(new GzipSource(responseBodyBody.getSource())))).build();
    }

    @Nullable
    public final SummarizeChunkReceiver getSummarizeChunkReceiver() {
        return this.summarizeChunkReceiver;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        String tag;
        Intrinsics.checkNotNullParameter(chain, "chain");
        Request request = chain.request();
        Response responseProceed = chain.proceed(request);
        Object objTag = request.tag();
        RequestTag requestTag = objTag instanceof RequestTag ? (RequestTag) objTag : null;
        return (requestTag == null || (tag = requestTag.getTag()) == null || !StringsKt.startsWith$default(tag, SUMMARIZE_TAG_PREFIX, false, 2, (Object) null)) ? responseProceed : handleSummarizeChunks(request, responseProceed, tag);
    }

    public final void setSummarizeChunkReceiver(@Nullable SummarizeChunkReceiver summarizeChunkReceiver) {
        this.summarizeChunkReceiver = summarizeChunkReceiver;
    }
}
