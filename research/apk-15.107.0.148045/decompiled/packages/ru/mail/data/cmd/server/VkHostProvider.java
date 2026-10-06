package ru.mail.data.cmd.server;

import android.net.Uri;
import androidx.compose.runtime.internal.StabilityInferred;
import com.vk.auth.restore.RestoreConstants;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.network.HostProvider;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@StabilityInferred(parameters = 0)
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB1\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0004¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0013\u001a\u00020\u0012H\u0016J\n\u0010\u0014\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0012H\u0016J\u0018\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0019\u001a\u00020\u001aH\u0016R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0019\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006¢\u0006\n\n\u0002\u0010\u000e\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0007\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lru/mail/data/cmd/server/VkHostProvider;", "Lru/mail/network/HostProvider;", "queryParams", "", "", "pathSegments", "", "host", "<init>", "(Ljava/util/Map;[Ljava/lang/String;Ljava/lang/String;)V", "getQueryParams", "()Ljava/util/Map;", "getPathSegments", "()[Ljava/lang/String;", "[Ljava/lang/String;", "getHost", "()Ljava/lang/String;", "builder", "Landroid/net/Uri$Builder;", "getUrlBuilder", "getUserAgent", "getPlatformSpecificParams", "", "url", "sign", "signCreator", "Lru/mail/network/HostProvider$SignCreator;", "Companion", "mails_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class VkHostProvider implements HostProvider {

    @NotNull
    public static final String VK_API_VERSION = "5.228";

    @NotNull
    private final Uri.Builder builder;

    @NotNull
    private final String host;

    @NotNull
    private final String[] pathSegments;

    @NotNull
    private final Map<String, String> queryParams;
    public static final int $stable = 8;

    public VkHostProvider(@NotNull Map<String, String> queryParams, @NotNull String[] pathSegments, @NotNull String host) {
        Intrinsics.checkNotNullParameter(queryParams, "queryParams");
        Intrinsics.checkNotNullParameter(pathSegments, "pathSegments");
        Intrinsics.checkNotNullParameter(host, "host");
        this.queryParams = queryParams;
        this.pathSegments = pathSegments;
        this.host = host;
        this.builder = new Uri.Builder();
    }

    @NotNull
    public final String getHost() {
        return this.host;
    }

    @NotNull
    public final String[] getPathSegments() {
        return this.pathSegments;
    }

    @Override // ru.mail.network.HostProvider
    public void getPlatformSpecificParams(@NotNull Uri.Builder url) {
        Intrinsics.checkNotNullParameter(url, "url");
    }

    @NotNull
    public final Map<String, String> getQueryParams() {
        return this.queryParams;
    }

    @Override // ru.mail.network.HostProvider
    @NotNull
    public Uri.Builder getUrlBuilder() {
        this.builder.scheme(RestoreConstants.DEFAULT_URL_SCHEME).authority("api." + this.host);
        for (String str : this.pathSegments) {
            this.builder.appendPath(str);
        }
        for (String str2 : this.queryParams.keySet()) {
            this.builder.appendQueryParameter(str2, this.queryParams.get(str2));
        }
        return this.builder;
    }

    @Override // ru.mail.network.HostProvider
    @Nullable
    public String getUserAgent() {
        return null;
    }

    @Override // ru.mail.network.HostProvider
    public void sign(@NotNull Uri.Builder builder, @NotNull HostProvider.SignCreator signCreator) {
        Intrinsics.checkNotNullParameter(builder, "builder");
        Intrinsics.checkNotNullParameter(signCreator, "signCreator");
    }
}
