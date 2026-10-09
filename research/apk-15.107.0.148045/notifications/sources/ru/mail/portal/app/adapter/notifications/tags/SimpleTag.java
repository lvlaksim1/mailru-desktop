package ru.mail.portal.app.adapter.notifications.tags;

import android.content.Context;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes16.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\u0005H\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\t\u0010\u000e\u001a\u00020\u0003HÂ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÂ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/SimpleTag;", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "title", "", "idForPusher", "", "<init>", "(Ljava/lang/String;I)V", "getTitle", "context", "Landroid/content/Context;", "getIdForPusher", "convertToJSON", "Lorg/json/JSONObject;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "Companion", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class SimpleTag implements Tag {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);

    @NotNull
    private static final String JSON_KEY_PUSHER_ID = "pusher_id";

    @NotNull
    private static final String JSON_KEY_TITLE = "title";
    private final int idForPusher;

    @NotNull
    private final String title;

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes11.dex */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000b"}, d2 = {"Lru/mail/portal/app/adapter/notifications/tags/SimpleTag$Companion;", "", "<init>", "()V", "JSON_KEY_TITLE", "", "JSON_KEY_PUSHER_ID", "fromJson", "Lru/mail/portal/app/adapter/notifications/tags/Tag;", "json", "Lorg/json/JSONObject;", "app-adapter_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @NotNull
        public final Tag fromJson(@NotNull JSONObject json) throws JSONException {
            Intrinsics.checkNotNullParameter(json, "json");
            String string = json.getString("title");
            int i10 = json.getInt(SimpleTag.JSON_KEY_PUSHER_ID);
            Intrinsics.checkNotNull(string);
            return new SimpleTag(string, i10);
        }

        private Companion() {
        }
    }

    public SimpleTag(@NotNull String title, int i10) {
        Intrinsics.checkNotNullParameter(title, "title");
        this.title = title;
        this.idForPusher = i10;
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    private final String getTitle() {
        return this.title;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    private final int getIdForPusher() {
        return this.idForPusher;
    }

    public static /* synthetic */ SimpleTag copy$default(SimpleTag simpleTag, String str, int i10, int i11, Object obj) {
        if ((i11 & 1) != 0) {
            str = simpleTag.title;
        }
        if ((i11 & 2) != 0) {
            i10 = simpleTag.idForPusher;
        }
        return simpleTag.copy(str, i10);
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.Tag
    @NotNull
    public JSONObject convertToJSON() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("title", this.title);
        jSONObject.put(JSON_KEY_PUSHER_ID, this.idForPusher);
        return jSONObject;
    }

    @NotNull
    public final SimpleTag copy(@NotNull String title, int idForPusher) {
        Intrinsics.checkNotNullParameter(title, "title");
        return new SimpleTag(title, idForPusher);
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SimpleTag)) {
            return false;
        }
        SimpleTag simpleTag = (SimpleTag) other;
        return Intrinsics.areEqual(this.title, simpleTag.title) && this.idForPusher == simpleTag.idForPusher;
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.Tag
    public int getIdForPusher() {
        return this.idForPusher;
    }

    @Override // ru.mail.portal.app.adapter.notifications.tags.Tag
    @NotNull
    public String getTitle(@NotNull Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return this.title;
    }

    public int hashCode() {
        return (this.title.hashCode() * 31) + Integer.hashCode(this.idForPusher);
    }

    @NotNull
    public String toString() {
        return "SimpleTag(title=" + this.title + ", idForPusher=" + this.idForPusher + ")";
    }
}
