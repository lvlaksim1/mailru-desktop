package ru.mail.mini_mail;

import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.SetsKt;
import org.jetbrains.annotations.NotNull;
import ru.mail.mini_mail_api.HostProvider;
import ru.mail.mini_mail_stub.R;
import ru.mail.ui.fragments.settings.BaseSettingsActivity;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\bJ\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010O\u001a\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020R\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050Q0PH\u0016R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\tR\u001a\u0010\r\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u0007\"\u0004\b\u000f\u0010\tR\u001a\u0010\u0010\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0007\"\u0004\b\u0012\u0010\tR\u001a\u0010\u0013\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0007\"\u0004\b\u0015\u0010\tR\u001a\u0010\u0016\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\u0007\"\u0004\b\u0018\u0010\tR\u001a\u0010\u0019\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0007\"\u0004\b\u001b\u0010\tR\u001a\u0010\u001c\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\u0007\"\u0004\b\u001e\u0010\tR\u001a\u0010\u001f\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\u0007\"\u0004\b!\u0010\tR\u001a\u0010\"\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b#\u0010\u0007\"\u0004\b$\u0010\tR\u001a\u0010%\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b&\u0010\u0007\"\u0004\b'\u0010\tR\u001a\u0010(\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b)\u0010\u0007\"\u0004\b*\u0010\tR\u001a\u0010+\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b,\u0010\u0007\"\u0004\b-\u0010\tR\u001a\u0010.\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b/\u0010\u0007\"\u0004\b0\u0010\tR\u001a\u00101\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b2\u0010\u0007\"\u0004\b3\u0010\tR\u001a\u00104\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u0010\u0007\"\u0004\b6\u0010\tR\u001a\u00107\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b8\u0010\u0007\"\u0004\b9\u0010\tR\u001a\u0010:\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b;\u0010\u0007\"\u0004\b<\u0010\tR\u001a\u0010=\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b>\u0010\u0007\"\u0004\b?\u0010\tR\u001a\u0010@\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bA\u0010\u0007\"\u0004\bB\u0010\tR\u001a\u0010C\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bD\u0010\u0007\"\u0004\bE\u0010\tR\u001a\u0010F\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bG\u0010\u0007\"\u0004\bH\u0010\tR\u001a\u0010I\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bJ\u0010\u0007\"\u0004\bK\u0010\tR\u001a\u0010L\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\bM\u0010\u0007\"\u0004\bN\u0010\t¨\u0006S"}, d2 = {"Lru/mail/mini_mail/HostProviderImpl;", "Lru/mail/mini_mail_api/HostProvider;", "<init>", "()V", "preferenceSchemeAuth", "", "getPreferenceSchemeAuth", "()I", "setPreferenceSchemeAuth", "(I)V", "preferenceHostAuth", "getPreferenceHostAuth", "setPreferenceHostAuth", "preferenceSchemeDoregCaptcha", "getPreferenceSchemeDoregCaptcha", "setPreferenceSchemeDoregCaptcha", "preferenceHostDoregCaptcha", "getPreferenceHostDoregCaptcha", "setPreferenceHostDoregCaptcha", "preferenceSchemeDoreg", "getPreferenceSchemeDoreg", "setPreferenceSchemeDoreg", "preferenceHostDoreg", "getPreferenceHostDoreg", "setPreferenceHostDoreg", "preferenceSchemePush", "getPreferenceSchemePush", "setPreferenceSchemePush", "preferenceHostPush", "getPreferenceHostPush", "setPreferenceHostPush", "preferenceSchemeNewMailApi", "getPreferenceSchemeNewMailApi", "setPreferenceSchemeNewMailApi", "preferenceHostNewMailApi", "getPreferenceHostNewMailApi", "setPreferenceHostNewMailApi", "preferenceSchemeAvatar", "getPreferenceSchemeAvatar", "setPreferenceSchemeAvatar", "preferenceHostAvatar", "getPreferenceHostAvatar", "setPreferenceHostAvatar", "preferenceSchemeAttachPreview", "getPreferenceSchemeAttachPreview", "setPreferenceSchemeAttachPreview", "preferenceHostAttachPreview", "getPreferenceHostAttachPreview", "setPreferenceHostAttachPreview", "preferenceSchemeDomainSettings", "getPreferenceSchemeDomainSettings", "setPreferenceSchemeDomainSettings", "preferenceHostDomainSettings", "getPreferenceHostDomainSettings", "setPreferenceHostDomainSettings", "preferenceSchemeAuthstat", "getPreferenceSchemeAuthstat", "setPreferenceSchemeAuthstat", "preferenceHostAuthstat", "getPreferenceHostAuthstat", "setPreferenceHostAuthstat", "preferenceSchemeRegistration", "getPreferenceSchemeRegistration", "setPreferenceSchemeRegistration", "preferenceHostRegistration", "getPreferenceHostRegistration", "setPreferenceHostRegistration", "preferenceSchemeCloudDispatcher", "getPreferenceSchemeCloudDispatcher", "setPreferenceSchemeCloudDispatcher", "preferenceHostCloudDispatcher", "getPreferenceHostCloudDispatcher", "setPreferenceHostCloudDispatcher", "preferenceSchemeCloudReferer", "getPreferenceSchemeCloudReferer", "setPreferenceSchemeCloudReferer", "preferenceHostCloudReferer", "getPreferenceHostCloudReferer", "setPreferenceHostCloudReferer", "provideHosts", "", "Lkotlin/Triple;", "", "mini-mail-stub_release"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final class HostProviderImpl implements HostProvider {
    private int preferenceSchemeAuth = -1;
    private int preferenceHostAuth = -1;
    private int preferenceSchemeDoregCaptcha = -1;
    private int preferenceHostDoregCaptcha = -1;
    private int preferenceSchemeDoreg = -1;
    private int preferenceHostDoreg = -1;
    private int preferenceSchemePush = -1;
    private int preferenceHostPush = -1;
    private int preferenceSchemeNewMailApi = -1;
    private int preferenceHostNewMailApi = -1;
    private int preferenceSchemeAvatar = -1;
    private int preferenceHostAvatar = -1;
    private int preferenceSchemeAttachPreview = -1;
    private int preferenceHostAttachPreview = -1;
    private int preferenceSchemeDomainSettings = -1;
    private int preferenceHostDomainSettings = -1;
    private int preferenceSchemeAuthstat = -1;
    private int preferenceHostAuthstat = -1;
    private int preferenceSchemeRegistration = -1;
    private int preferenceHostRegistration = -1;
    private int preferenceSchemeCloudDispatcher = -1;
    private int preferenceHostCloudDispatcher = -1;
    private int preferenceSchemeCloudReferer = -1;
    private int preferenceHostCloudReferer = -1;

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostAttachPreview() {
        return this.preferenceHostAttachPreview;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostAuth() {
        return this.preferenceHostAuth;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostAuthstat() {
        return this.preferenceHostAuthstat;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostAvatar() {
        return this.preferenceHostAvatar;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostCloudDispatcher() {
        return this.preferenceHostCloudDispatcher;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostCloudReferer() {
        return this.preferenceHostCloudReferer;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostDomainSettings() {
        return this.preferenceHostDomainSettings;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostDoreg() {
        return this.preferenceHostDoreg;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostDoregCaptcha() {
        return this.preferenceHostDoregCaptcha;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostNewMailApi() {
        return this.preferenceHostNewMailApi;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostPush() {
        return this.preferenceHostPush;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceHostRegistration() {
        return this.preferenceHostRegistration;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeAttachPreview() {
        return this.preferenceSchemeAttachPreview;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeAuth() {
        return this.preferenceSchemeAuth;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeAuthstat() {
        return this.preferenceSchemeAuthstat;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeAvatar() {
        return this.preferenceSchemeAvatar;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeCloudDispatcher() {
        return this.preferenceSchemeCloudDispatcher;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeCloudReferer() {
        return this.preferenceSchemeCloudReferer;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeDomainSettings() {
        return this.preferenceSchemeDomainSettings;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeDoreg() {
        return this.preferenceSchemeDoreg;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeDoregCaptcha() {
        return this.preferenceSchemeDoregCaptcha;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeNewMailApi() {
        return this.preferenceSchemeNewMailApi;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemePush() {
        return this.preferenceSchemePush;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public int getPreferenceSchemeRegistration() {
        return this.preferenceSchemeRegistration;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    @NotNull
    public Set<Triple<String, Integer, Integer>> provideHosts() {
        Triple triple = new Triple("ru.mail.preference_scheme_auth", Integer.valueOf(R.string.auth_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeAuth()));
        Triple triple2 = new Triple("ru.mail.preference_host_auth", Integer.valueOf(R.string.auth_default_host_mini_mail), Integer.valueOf(getPreferenceHostAuth()));
        int i10 = R.string.doreg_captcha_default_scheme_mini_mail;
        return SetsKt.setOf((Object[]) new Triple[]{triple, triple2, new Triple("ru.mail.preference_scheme_doreg_captcha", Integer.valueOf(i10), Integer.valueOf(getPreferenceSchemeDoregCaptcha())), new Triple("ru.mail.preference_host_doreg_captcha", Integer.valueOf(R.string.doreg_captcha_default_host_mini_mail), Integer.valueOf(getPreferenceHostDoregCaptcha())), new Triple("ru.mail.preference_scheme_doreg", Integer.valueOf(i10), Integer.valueOf(getPreferenceSchemeDoreg())), new Triple("ru.mail.preference_host_doreg", Integer.valueOf(R.string.doreg_default_host_mini_mail), Integer.valueOf(getPreferenceHostDoreg())), new Triple("ru.mail.preference_scheme_push", Integer.valueOf(R.string.push_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemePush())), new Triple(BaseSettingsActivity.KEY_PREF_HOST_PUSH, Integer.valueOf(R.string.push_default_host_mini_mail), Integer.valueOf(getPreferenceHostPush())), new Triple("ru.mail.preference_scheme_new_mail_api", Integer.valueOf(R.string.mail_api_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeNewMailApi())), new Triple("ru.mail.preference_host_new_mail_api", Integer.valueOf(R.string.new_mail_api_default_host_mini_mail), Integer.valueOf(getPreferenceHostNewMailApi())), new Triple("ru.mail.preference_scheme_avatar", Integer.valueOf(R.string.avatar_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeAvatar())), new Triple("ru.mail.preference_host_avatar", Integer.valueOf(R.string.avatar_default_host_mini_mail), Integer.valueOf(getPreferenceHostAvatar())), new Triple("ru.mail.preference_scheme_attach_preview", Integer.valueOf(R.string.attach_preview_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeAttachPreview())), new Triple("ru.mail.preference_host_attach_preview", Integer.valueOf(R.string.attach_preview_default_host_mini_mail), Integer.valueOf(getPreferenceHostAttachPreview())), new Triple("ru.mail.preference_scheme_domain_settings", Integer.valueOf(R.string.domain_settings_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeDomainSettings())), new Triple("ru.mail.preference_host_domain_settings", Integer.valueOf(R.string.domain_settings_default_host_mini_mail), Integer.valueOf(getPreferenceHostDomainSettings())), new Triple("ru.mail.preference_scheme_authstat", Integer.valueOf(R.string.authstat_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeAuthstat())), new Triple("ru.mail.preference_host_authstat", Integer.valueOf(R.string.authstat_default_host_mini_mail), Integer.valueOf(getPreferenceHostAuthstat())), new Triple("ru.mail.preference_scheme_registration", Integer.valueOf(R.string.registration_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeRegistration())), new Triple("ru.mail.preference_host_registration", Integer.valueOf(R.string.registration_default_host_mini_mail), Integer.valueOf(getPreferenceHostRegistration())), new Triple("ru.mail.preference_scheme_cloud_dispatcher", Integer.valueOf(R.string.cloud_dispatcher_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeCloudDispatcher())), new Triple("ru.mail.preference_host_cloud_dispatcher", Integer.valueOf(R.string.cloud_dispatcher_default_host_mini_mail), Integer.valueOf(getPreferenceHostCloudDispatcher())), new Triple("ru.mail.preference_scheme_cloud_referer", Integer.valueOf(R.string.cloud_referer_default_scheme_mini_mail), Integer.valueOf(getPreferenceSchemeCloudReferer())), new Triple("ru.mail.preference_host_cloud_referer", Integer.valueOf(R.string.cloud_referer_default_host_mini_mail), Integer.valueOf(getPreferenceHostCloudReferer()))});
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostAttachPreview(int i10) {
        this.preferenceHostAttachPreview = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostAuth(int i10) {
        this.preferenceHostAuth = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostAuthstat(int i10) {
        this.preferenceHostAuthstat = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostAvatar(int i10) {
        this.preferenceHostAvatar = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostCloudDispatcher(int i10) {
        this.preferenceHostCloudDispatcher = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostCloudReferer(int i10) {
        this.preferenceHostCloudReferer = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostDomainSettings(int i10) {
        this.preferenceHostDomainSettings = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostDoreg(int i10) {
        this.preferenceHostDoreg = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostDoregCaptcha(int i10) {
        this.preferenceHostDoregCaptcha = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostNewMailApi(int i10) {
        this.preferenceHostNewMailApi = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostPush(int i10) {
        this.preferenceHostPush = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceHostRegistration(int i10) {
        this.preferenceHostRegistration = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeAttachPreview(int i10) {
        this.preferenceSchemeAttachPreview = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeAuth(int i10) {
        this.preferenceSchemeAuth = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeAuthstat(int i10) {
        this.preferenceSchemeAuthstat = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeAvatar(int i10) {
        this.preferenceSchemeAvatar = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeCloudDispatcher(int i10) {
        this.preferenceSchemeCloudDispatcher = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeCloudReferer(int i10) {
        this.preferenceSchemeCloudReferer = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeDomainSettings(int i10) {
        this.preferenceSchemeDomainSettings = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeDoreg(int i10) {
        this.preferenceSchemeDoreg = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeDoregCaptcha(int i10) {
        this.preferenceSchemeDoregCaptcha = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeNewMailApi(int i10) {
        this.preferenceSchemeNewMailApi = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemePush(int i10) {
        this.preferenceSchemePush = i10;
    }

    @Override // ru.mail.mini_mail_api.HostProvider
    public void setPreferenceSchemeRegistration(int i10) {
        this.preferenceSchemeRegistration = i10;
    }
}
