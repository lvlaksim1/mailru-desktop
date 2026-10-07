package com.vk.toggle.anonymous;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import com.vk.toggle.FeatureManager;
import com.vk.toggle.garbage.FeatureSet;
import com.vk.toggle.internal.ToggleManager;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@ApiStatus.Internal
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u000f"}, d2 = {"Lcom/vk/toggle/anonymous/SakFeatures;", "Lcom/vk/toggle/garbage/FeatureSet;", "Lcom/vk/toggle/internal/ToggleManager;", "manager", "<init>", "(Lcom/vk/toggle/internal/ToggleManager;)V", "", "", "elggotbilkvmoca", "Ljava/util/List;", "getKeys", "()Ljava/util/List;", UserMetadata.KEYDATA_FILENAME, "Companion", "Type", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSakFeatures.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SakFeatures.kt\ncom/vk/toggle/anonymous/SakFeatures\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,136:1\n1563#2:137\n1634#2,3:138\n*S KotlinDebug\n*F\n+ 1 SakFeatures.kt\ncom/vk/toggle/anonymous/SakFeatures\n*L\n121#1:137\n121#1:138,3\n*E\n"})
public final class SakFeatures implements FeatureSet {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = new Companion(null);
    private static volatile ToggleManager elggotbilkvmocb;
    private static final BehaviorSubject<ToggleManager.Sync> elggotbilkvmocc;

    @ApiStatus.Internal
    @NotNull
    private static Observable<ToggleManager.Sync> elggotbilkvmocd;

    @NotNull
    private final ArrayList elggotbilkvmoca;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005@BX\u0086.¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0007R<\u0010\u000b\u001a0\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r \u000e*\u0017\u0012\f\u0012\n \u000e*\u0004\u0018\u00010\r0\r\u0018\u00010\f¢\u0006\u0002\b\u000f0\f¢\u0006\u0002\b\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u00118\u0006@\u0006X\u0087\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lcom/vk/toggle/anonymous/SakFeatures$Companion;", "", "<init>", "()V", "initializedManagerSak", "Lcom/vk/toggle/internal/ToggleManager;", "getInitializedManagerSak", "()Lcom/vk/toggle/internal/ToggleManager;", "value", "managerSak", "getManagerSak", "syncState", "Lio/reactivex/rxjava3/subjects/BehaviorSubject;", "Lcom/vk/toggle/internal/ToggleManager$Sync;", "kotlin.jvm.PlatformType", "Lio/reactivex/rxjava3/annotations/NonNull;", "syncStateUpdates", "Lio/reactivex/rxjava3/core/Observable;", "getSyncStateUpdates", "()Lio/reactivex/rxjava3/core/Observable;", "setSyncStateUpdates", "(Lio/reactivex/rxjava3/core/Observable;)V", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSakFeatures.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SakFeatures.kt\ncom/vk/toggle/anonymous/SakFeatures$Companion\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n1#2:137\n*E\n"})
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final ToggleManager getInitializedManagerSak() {
            Companion companion = SakFeatures.elggotbilkvmocb != null ? this : null;
            if (companion != null) {
                return companion.getManagerSak();
            }
            return null;
        }

        @NotNull
        public final ToggleManager getManagerSak() {
            return SakFeatures.elggotbilkvmocb;
        }

        @NotNull
        public final Observable<ToggleManager.Sync> getSyncStateUpdates() {
            return SakFeatures.elggotbilkvmocd;
        }

        public final void setSyncStateUpdates(@NotNull Observable<ToggleManager.Sync> observable) {
            Intrinsics.checkNotNullParameter(observable, "<set-?>");
            SakFeatures.elggotbilkvmocd = observable;
        }

        private Companion() {
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v68 com.vk.toggle.anonymous.SakFeatures$Type[], still in use, count: 1, list:
      (r0v68 com.vk.toggle.anonymous.SakFeatures$Type[]) from 0x048a: INVOKE (r0v68 com.vk.toggle.anonymous.SakFeatures$Type[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:1163)
    	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:164)
    	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:129)
    	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:101)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1511)
    	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:100)
    	at jadx.core.utils.InsnRemover.removeAllAndUnbind(InsnRemover.java:257)
    	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:187)
    	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:102)
     */
    /* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\bJ\b\u0086\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\u000f\u0010\u0004\u001a\u00020\u0003H\u0016¢\u0006\u0004\b\u0004\u0010\u0005J\u0015\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00030\u0006H\u0016¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\u000e\u001a\u00020\t8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rj\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013j\u0002\b\u0014j\u0002\b\u0015j\u0002\b\u0016j\u0002\b\u0017j\u0002\b\u0018j\u0002\b\u0019j\u0002\b\u001aj\u0002\b\u001bj\u0002\b\u001cj\u0002\b\u001dj\u0002\b\u001ej\u0002\b\u001fj\u0002\b j\u0002\b!j\u0002\b\"j\u0002\b#j\u0002\b$j\u0002\b%j\u0002\b&j\u0002\b'j\u0002\b(j\u0002\b)j\u0002\b*j\u0002\b+j\u0002\b,j\u0002\b-j\u0002\b.j\u0002\b/j\u0002\b0j\u0002\b1j\u0002\b2j\u0002\b3j\u0002\b4j\u0002\b5j\u0002\b6j\u0002\b7j\u0002\b8j\u0002\b9j\u0002\b:j\u0002\b;j\u0002\b<j\u0002\b=j\u0002\b>j\u0002\b?j\u0002\b@j\u0002\bAj\u0002\bBj\u0002\bCj\u0002\bDj\u0002\bEj\u0002\bFj\u0002\bGj\u0002\bHj\u0002\bIj\u0002\bJj\u0002\bKj\u0002\bLj\u0002\bMj\u0002\bNj\u0002\bOj\u0002\bPj\u0002\bQj\u0002\bR¨\u0006S"}, d2 = {"Lcom/vk/toggle/anonymous/SakFeatures$Type;", "Lcom/vk/toggle/FeatureManager$FeatureType;", "", "", "hasFeatureEnabled", "()Z", "Lio/reactivex/rxjava3/core/Observable;", "observeFeatureEnabled", "()Lio/reactivex/rxjava3/core/Observable;", "", "elggotbilkvmocc", "Ljava/lang/String;", "getKey", "()Ljava/lang/String;", "key", "FEATURE_STRONG_PASSWORD", "FEATURE_SIGN_ANONYMOUS_TOKEN", "FEATURE_TEST_ANONYMOUS_TOGGLE", "FEATURE_TINKOFF_APP_TO_APP_TOGGLE", "FEATURE_VKC_SMARTFLOW_METHODS_CACHE", "GET_USER_INFO_CUT_OFF_FROM_AUTH", "FEATURE_VKC_LIBVERIFY_CALLIN_AUTH", "FEATURE_VKC_LIBVERIFY_CALLIN_REG", "FEATURE_VKC_AVAILABLE_OAUTH_LIST", "FEATURE_NFT_AVATAR_ANONYM_DEBUG", "FEATURE_NFT_AVATAR_ANONYM_BETA", "FEATURE_NFT_AVATAR_ANONYM_RELEASE", "FEATURE_IM_FIX_FOLDER_NOT_FOUND", "FEATURE_CORE_COMPANION_DEVICE_ID", "FEATURE_CORE_STAT_FLUSH_ON_CLEAR", "INVITE_LINKS", "LOGOUT_DEBOUNCE", "FEATURE_VKM_SESSION_MANAGEMENT", "FEATURE_VKM_MULTI_ACCOUNT", "FEATURE_VKM_MULTI_ACCOUNT_BETA", "VKM_MULTIACCOUNT_LIMIT_SCREEN", "VKC_CREATE_ACCOUNT", "VKC_BACKUP_SENDING", "VKC_LIBVERIFY_SESSION", "VKC_SMARTFLOW_INTERNAL_ANDROID", "VKC_SMARTFLOW_OK_ANDROID", "VKC_LIBVERIFY_FACTORS_KZ", "VKC_PHONE_HINT_IM", "VKC_PHONE_HINT_INNER", "VKC_SDK_SESSION_MANAGEMENT", "VKC_LIBVERIFY_CONF_CHANGE", "VKC_SMARTFLOW_MAIL_ANDROID", "VKC_TRACER_PERF_SDK_START", "USERS_STORE_ONLY_CACHE", "VOIP_JOIN_TO_CALL_BY_PASSWORD_ANON", "VKC_HITMAN_CAPTCHA_ANDROID", "VKC_AUTH_COMMON_REFACTOR", "VKC_PHONE_REUSE_AUTH", "FEATURE_NETWORK_REPORT_CONFIG", "NEW_GEOBLOCK_ERROR", "VIDEO_FIX_MINIPLAYER_HEADSET", "VIDEO_FB_INIT_TYPE", "VKC_RESTORE_TO_VK_ID_HOST", "SAK_SEAMLESS_FLOW", "AUDIO_VIDEO_RELATED_TRACKS", "VKC_ONEPASS_PROMO", "CORE_DURING_UPDATE_TOGGLES", "CORE_SWITCH_VK_RU_DOMAIN_ANON", "SAK_COROUTINES_MIGRATION", "SAK_MAIL_PROMO_MAX", "SAK_SBER_ID_CLOUD", "SAK_MAX_AUTH_TIMER_ANDROID", "SAK_MAX_AUTH_CACHE_ANDROID", "SAK_LIBVERIFY_AB_SUFFIX", "CORE_CONTENT_INFO_BOTTOM_SHEET", "SAK_HANDLE_BAN_REASON", "SAK_MESSENGER_SKIP_SMS_ANDROID", "SAK_QR_WITH_CODE", "SAK_EXTEND_NETWORK_CHECK_TIMEOUT_ANDROID", "SAK_DEF_CLIENT_INSTALL_ANDROID", "SAK_HELP_BUTTON_ANDROID", "SA_DATING_TOKEN_SYNC_FIX", "SAK_EMAIL_ACTUALIZATION", "USERSSTORE_TRUSTED_PACKAGES", "SAK_DEBUG_STATS_SAMPLING", "SAK_PROF_SWITCH_CACHE", "SAK_LEGO_REDESIGN_VK", "SAK_LEGO_REDESIGN_VIDEO", "core_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nSakFeatures.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SakFeatures.kt\ncom/vk/toggle/anonymous/SakFeatures$Type\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,136:1\n1#2:137\n*E\n"})
    public static final class Type implements FeatureManager.FeatureType {
        FEATURE_STRONG_PASSWORD("vkc_strong_password_android"),
        FEATURE_SIGN_ANONYMOUS_TOKEN("sak_sign_anonymous_token"),
        FEATURE_TEST_ANONYMOUS_TOGGLE("vkc_test_anonymous_toggle"),
        FEATURE_TINKOFF_APP_TO_APP_TOGGLE("vkc_tinkoff_app_to_app_android"),
        FEATURE_VKC_SMARTFLOW_METHODS_CACHE("vkc_smartflow_methods_cache"),
        GET_USER_INFO_CUT_OFF_FROM_AUTH("vkc_get_user_info_cut_off"),
        FEATURE_VKC_LIBVERIFY_CALLIN_AUTH("vkc_callin_auth_android"),
        FEATURE_VKC_LIBVERIFY_CALLIN_REG("vkc_callin_reg_android"),
        FEATURE_VKC_AVAILABLE_OAUTH_LIST("vkc_available_oauth_list"),
        FEATURE_NFT_AVATAR_ANONYM_DEBUG("nft_avatar_anonym_debug"),
        FEATURE_NFT_AVATAR_ANONYM_BETA("nft_avatar_anonym_beta"),
        FEATURE_NFT_AVATAR_ANONYM_RELEASE("nft_avatar_anonym_release"),
        FEATURE_IM_FIX_FOLDER_NOT_FOUND("vkm_fix_folder_not_found"),
        FEATURE_CORE_COMPANION_DEVICE_ID("core_companion_device_id"),
        FEATURE_CORE_STAT_FLUSH_ON_CLEAR("core_stat_flush_on_clear"),
        INVITE_LINKS("vkc_noob_invite_links"),
        LOGOUT_DEBOUNCE("vkc_logout_debounce"),
        FEATURE_VKM_SESSION_MANAGEMENT("vkm_session_management"),
        FEATURE_VKM_MULTI_ACCOUNT("vkm_multi_account"),
        FEATURE_VKM_MULTI_ACCOUNT_BETA("vkm_multi_account_beta"),
        VKM_MULTIACCOUNT_LIMIT_SCREEN("vkm_multiaccount_limit_screen"),
        VKC_CREATE_ACCOUNT("vkc_create_account_android"),
        VKC_BACKUP_SENDING("vkc_backup_sending"),
        VKC_LIBVERIFY_SESSION("vkc_libverify_session"),
        VKC_SMARTFLOW_INTERNAL_ANDROID("vkc_smartflow_internal_android"),
        VKC_SMARTFLOW_OK_ANDROID("vkc_smartflow_ok_android"),
        VKC_LIBVERIFY_FACTORS_KZ("vkc_libverify_factors_kz"),
        VKC_PHONE_HINT_IM("vkc_phone_hint_im"),
        VKC_PHONE_HINT_INNER("vkc_phone_hint_inner"),
        VKC_SDK_SESSION_MANAGEMENT("vkc_sdk_session_management"),
        VKC_LIBVERIFY_CONF_CHANGE("vkc_libverify_conf_change"),
        VKC_SMARTFLOW_MAIL_ANDROID("vkc_smartflow_mail_android"),
        VKC_TRACER_PERF_SDK_START("vkc_tracer_perf_sdk_start"),
        USERS_STORE_ONLY_CACHE("vkc_usersstore_only_cache_anon"),
        VOIP_JOIN_TO_CALL_BY_PASSWORD_ANON("voip_join_by_password_anon"),
        VKC_HITMAN_CAPTCHA_ANDROID("vkc_hitman_captcha_android"),
        VKC_AUTH_COMMON_REFACTOR("vkc_auth_common_refactor"),
        VKC_PHONE_REUSE_AUTH("vkc_phonereuse_auth_android"),
        FEATURE_NETWORK_REPORT_CONFIG("video_network_report_config"),
        NEW_GEOBLOCK_ERROR("core_new_geoblock_error"),
        VIDEO_FIX_MINIPLAYER_HEADSET("video_fix_miniplayer_headset"),
        VIDEO_FB_INIT_TYPE("video_firebase_init_type"),
        VKC_RESTORE_TO_VK_ID_HOST("vkc_restore_to_vk_id_host"),
        SAK_SEAMLESS_FLOW("sak_seamless_flow"),
        AUDIO_VIDEO_RELATED_TRACKS("audio_video_related_tracks"),
        VKC_ONEPASS_PROMO("vkc_onepass_promo_android"),
        CORE_DURING_UPDATE_TOGGLES("core_during_update_toggles"),
        CORE_SWITCH_VK_RU_DOMAIN_ANON("core_switch_vk_ru_domain_anon"),
        SAK_COROUTINES_MIGRATION("sak_coroutines_migration"),
        SAK_MAIL_PROMO_MAX("sak_mail_promo_max_android"),
        SAK_SBER_ID_CLOUD("sak_sber_id_cloud"),
        SAK_MAX_AUTH_TIMER_ANDROID("sak_max_auth_timer_android"),
        SAK_MAX_AUTH_CACHE_ANDROID("sak_max_auth_cache_android"),
        SAK_LIBVERIFY_AB_SUFFIX("sak_libverify_ab_suffix"),
        CORE_CONTENT_INFO_BOTTOM_SHEET("core_content_info_bottom_sheet"),
        SAK_HANDLE_BAN_REASON("sak_handle_ban_reason"),
        SAK_MESSENGER_SKIP_SMS_ANDROID("sak_messenger_skip_sms_android"),
        SAK_QR_WITH_CODE("sak_qr_with_code"),
        SAK_EXTEND_NETWORK_CHECK_TIMEOUT_ANDROID("sak_extend_netcheck_delay_andr"),
        SAK_DEF_CLIENT_INSTALL_ANDROID("sak_def_client_install_android"),
        SAK_HELP_BUTTON_ANDROID("sak_help_button_android"),
        SA_DATING_TOKEN_SYNC_FIX("sa_dating_token_sync_fix"),
        SAK_EMAIL_ACTUALIZATION("sak_email_actualization"),
        USERSSTORE_TRUSTED_PACKAGES("sak_usersstore_sig_check"),
        SAK_DEBUG_STATS_SAMPLING("sak_debug_stats_sampling"),
        SAK_PROF_SWITCH_CACHE("sak_prof_switch_cache"),
        SAK_LEGO_REDESIGN_VK("sak_lego_redesign_vk"),
        SAK_LEGO_REDESIGN_VIDEO("sak_lego_redesign_video");

        private static final /* synthetic */ EnumEntries elggotbilkvmocb;

        /* JADX INFO: renamed from: elggotbilkvmocc, reason: from kotlin metadata */
        @NotNull
        private final String key;

        static {
            elggotbilkvmocb = EnumEntriesKt.enumEntries(typeArr);
        }

        private Type(String str) {
            super(str, i);
            this.key = str;
        }

        @NotNull
        public static EnumEntries<Type> getEntries() {
            return elggotbilkvmocb;
        }

        public static Type valueOf(String str) {
            return (Type) Enum.valueOf(Type.class, str);
        }

        public static Type[] values() {
            return (Type[]) elggotbilkvmoca.clone();
        }

        @Override // com.vk.toggle.FeatureManager.FeatureType
        @NotNull
        public String getKey() {
            return this.key;
        }

        @Override // com.vk.toggle.FeatureManager.FeatureType
        public boolean hasFeatureEnabled() {
            Object objM13123constructorimpl;
            try {
                Result.Companion companion = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(Boolean.valueOf(SakFeatures.INSTANCE.getManagerSak().isFeatureEnabled(this)));
            } catch (Throwable th2) {
                Result.Companion companion2 = Result.INSTANCE;
                objM13123constructorimpl = Result.m13123constructorimpl(ResultKt.createFailure(th2));
            }
            Boolean bool = Boolean.FALSE;
            if (Result.m13128isFailureimpl(objM13123constructorimpl)) {
                objM13123constructorimpl = bool;
            }
            return ((Boolean) objM13123constructorimpl).booleanValue();
        }

        @Override // com.vk.toggle.FeatureManager.FeatureType
        @NotNull
        public Observable<Boolean> observeFeatureEnabled() {
            return SakFeatures.INSTANCE.getManagerSak().observeIsFeatureEnabled(this);
        }
    }

    static {
        BehaviorSubject<ToggleManager.Sync> behaviorSubjectCreateDefault = BehaviorSubject.createDefault(ToggleManager.Sync.NotSynced);
        elggotbilkvmocc = behaviorSubjectCreateDefault;
        Observable<ToggleManager.Sync> observableDistinctUntilChanged = behaviorSubjectCreateDefault.hide().distinctUntilChanged();
        Intrinsics.checkNotNullExpressionValue(observableDistinctUntilChanged, "distinctUntilChanged(...)");
        elggotbilkvmocd = observableDistinctUntilChanged;
    }

    public SakFeatures(@NotNull ToggleManager manager) {
        Intrinsics.checkNotNullParameter(manager, "manager");
        elggotbilkvmocb = manager;
        elggotbilkvmocc.onNext(ToggleManager.Sync.Done);
        EnumEntries<Type> entries = Type.getEntries();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(entries, 10));
        Iterator<Type> it = entries.iterator();
        while (it.hasNext()) {
            arrayList.add(it.next().getKey());
        }
        this.elggotbilkvmoca = arrayList;
    }

    @Override // com.vk.toggle.garbage.FeatureSet
    public void clear() {
        FeatureSet.DefaultImpls.clear(this);
    }

    @Override // com.vk.toggle.garbage.FeatureSet
    public void debugNotify() {
        FeatureSet.DefaultImpls.debugNotify(this);
    }

    @Override // com.vk.toggle.garbage.FeatureSet
    @NotNull
    public Map<String, FeatureManager.Toggle> getDefaultFeatures() {
        return FeatureSet.DefaultImpls.getDefaultFeatures(this);
    }

    @Override // com.vk.toggle.garbage.FeatureSet
    @NotNull
    public List<String> getKeys() {
        return this.elggotbilkvmoca;
    }

    @Override // com.vk.toggle.garbage.FeatureSet
    @NotNull
    public List<String> getSupportedFeatures() {
        return FeatureSet.DefaultImpls.getSupportedFeatures(this);
    }
}
