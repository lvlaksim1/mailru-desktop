package ru.mail.customtags.impl.api;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import ru.mail.data.entities.sync.folders.ColoredTagsSyncInfo;
import ru.mail.network.retrofit.ApiResult;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes9.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J.\u0010\u0002\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\tJ8\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u00040\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\f\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\rJ2\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0010\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\rJ2\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\b\b\u0001\u0010\u0006\u001a\u00020\u00072\b\b\u0003\u0010\b\u001a\u00020\u00072\b\b\u0001\u0010\u0012\u001a\u00020\u0007H§@¢\u0006\u0002\u0010\r¨\u0006\u0013À\u0006\u0003"}, d2 = {"Lru/mail/customtags/impl/api/CustomTagsApi;", "", "getCustomTags", "Lru/mail/network/retrofit/ApiResult;", "", "Lru/mail/customtags/impl/api/CustomTagDto;", "email", "", "mbox", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createCustomTags", "", ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS, "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteCustomTags", "", "ids", "editCustomTag", "tag", "feature-custom-tags-impl_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface CustomTagsApi {

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
    public static final class DefaultImpls {
    }

    static /* synthetic */ Object createCustomTags$default(CustomTagsApi customTagsApi, String str, String str2, String str3, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: createCustomTags");
        }
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        return customTagsApi.createCustomTags(str, str2, str3, continuation);
    }

    static /* synthetic */ Object deleteCustomTags$default(CustomTagsApi customTagsApi, String str, String str2, String str3, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: deleteCustomTags");
        }
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        return customTagsApi.deleteCustomTags(str, str2, str3, continuation);
    }

    static /* synthetic */ Object editCustomTag$default(CustomTagsApi customTagsApi, String str, String str2, String str3, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: editCustomTag");
        }
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        return customTagsApi.editCustomTag(str, str2, str3, continuation);
    }

    static /* synthetic */ Object getCustomTags$default(CustomTagsApi customTagsApi, String str, String str2, Continuation continuation, int i10, Object obj) {
        if (obj != null) {
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: getCustomTags");
        }
        if ((i10 & 2) != 0) {
            str2 = str;
        }
        return customTagsApi.getCustomTags(str, str2, continuation);
    }

    @FormUrlEncoded
    @POST("api/v1/colortags/create")
    @Nullable
    Object createCustomTags(@Field("email") @NotNull String str, @Field("mbox") @NotNull String str2, @Field(ColoredTagsSyncInfo.COL_NAME_CHANGED_TAGS) @NotNull String str3, @NotNull Continuation<? super ApiResult<List<Integer>>> continuation);

    @FormUrlEncoded
    @POST("api/v1/colortags/delete")
    @Nullable
    Object deleteCustomTags(@Field("email") @NotNull String str, @Field("mbox") @NotNull String str2, @Field("ids") @NotNull String str3, @NotNull Continuation<? super ApiResult<Unit>> continuation);

    @FormUrlEncoded
    @POST("api/v1/colortags/edit")
    @Nullable
    Object editCustomTag(@Field("email") @NotNull String str, @Field("mbox") @NotNull String str2, @Field("tag") @NotNull String str3, @NotNull Continuation<? super ApiResult<Unit>> continuation);

    @FormUrlEncoded
    @POST("api/v1/colortags")
    @Nullable
    Object getCustomTags(@Field("email") @NotNull String str, @Field("mbox") @NotNull String str2, @NotNull Continuation<? super ApiResult<List<CustomTagDto>>> continuation);
}
