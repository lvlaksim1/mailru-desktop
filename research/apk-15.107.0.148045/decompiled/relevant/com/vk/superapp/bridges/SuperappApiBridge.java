package com.vk.superapp.bridges;

import com.huawei.hms.push.AttributionReporter;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.my.target.common.NavigationType;
import com.vk.auth.restore.RestoreConstants;
import com.vk.superapp.api.contract.SuperappApi;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.wallet.impl.presentation.navigation.WalletFeatureImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ì\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H&J\b\u0010\b\u001a\u00020\tH&R\u0012\u0010\n\u001a\u00020\u0007X¦\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\r\u001a\u00020\u0007X¦\u000e¢\u0006\f\u001a\u0004\b\u000e\u0010\f\"\u0004\b\u000f\u0010\u0010R\u0012\u0010\u0011\u001a\u00020\u0012X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0012\u0010\u0015\u001a\u00020\u0016X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R\u0012\u0010\u0019\u001a\u00020\u001aX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001cR\u0012\u0010\u001d\u001a\u00020\u001eX¦\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u0012\u0010!\u001a\u00020\"X¦\u0004¢\u0006\u0006\u001a\u0004\b#\u0010$R\u0012\u0010%\u001a\u00020&X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R\u0012\u0010)\u001a\u00020*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0012\u0010-\u001a\u00020.X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u0012\u00101\u001a\u000202X¦\u0004¢\u0006\u0006\u001a\u0004\b3\u00104R\u0012\u00105\u001a\u000206X¦\u0004¢\u0006\u0006\u001a\u0004\b7\u00108R\u0012\u00109\u001a\u00020:X¦\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<R\u0012\u0010=\u001a\u00020>X¦\u0004¢\u0006\u0006\u001a\u0004\b?\u0010@R\u0012\u0010A\u001a\u00020BX¦\u0004¢\u0006\u0006\u001a\u0004\bC\u0010DR\u0012\u0010E\u001a\u00020FX¦\u0004¢\u0006\u0006\u001a\u0004\bG\u0010HR\u0012\u0010I\u001a\u00020JX¦\u0004¢\u0006\u0006\u001a\u0004\bK\u0010LR\u0012\u0010M\u001a\u00020NX¦\u0004¢\u0006\u0006\u001a\u0004\bO\u0010PR\u0012\u0010Q\u001a\u00020RX¦\u0004¢\u0006\u0006\u001a\u0004\bS\u0010TR\u0012\u0010U\u001a\u00020VX¦\u0004¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0012\u0010Y\u001a\u00020ZX¦\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0012\u0010]\u001a\u00020^X¦\u0004¢\u0006\u0006\u001a\u0004\b_\u0010`R\u0012\u0010a\u001a\u00020bX¦\u0004¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0012\u0010e\u001a\u00020fX¦\u0004¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX¦\u0004¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0012\u0010m\u001a\u00020nX¦\u0004¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0012\u0010q\u001a\u00020rX¦\u0004¢\u0006\u0006\u001a\u0004\bs\u0010tR\u0012\u0010u\u001a\u00020vX¦\u0004¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020zX¦\u0004¢\u0006\u0006\u001a\u0004\b{\u0010|R\u0013\u0010}\u001a\u00020~X¦\u0004¢\u0006\u0007\u001a\u0005\b\u007f\u0010\u0080\u0001R\u0016\u0010\u0081\u0001\u001a\u00030\u0082\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0083\u0001\u0010\u0084\u0001R\u0016\u0010\u0085\u0001\u001a\u00030\u0086\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001R\u0016\u0010\u0089\u0001\u001a\u00030\u008a\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0016\u0010\u008d\u0001\u001a\u00030\u008e\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0016\u0010\u0091\u0001\u001a\u00030\u0092\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0016\u0010\u009d\u0001\u001a\u00030\u009e\u0001X¦\u0004¢\u0006\b\u001a\u0006\b\u009f\u0001\u0010 \u0001R\u0016\u0010¡\u0001\u001a\u00030¢\u0001X¦\u0004¢\u0006\b\u001a\u0006\b£\u0001\u0010¤\u0001¨\u0006¥\u0001"}, d2 = {"Lcom/vk/superapp/bridges/SuperappApiBridge;", "", "getServerTime", "", "ignoreAccessToken", "", CommonConstant.KEY_ACCESS_TOKEN, "", "isWhitelistInternetEnabled", "", "vkApiHost", "getVkApiHost", "()Ljava/lang/String;", "endpoint", "getEndpoint", "setEndpoint", "(Ljava/lang/String;)V", "common", "Lcom/vk/superapp/api/contract/SuperappApi$Common;", "getCommon", "()Lcom/vk/superapp/api/contract/SuperappApi$Common;", "vkidok", "Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "getVkidok", "()Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "vkidmail", "Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "getVkidmail", "()Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "account", "Lcom/vk/superapp/api/contract/SuperappApi$Account;", "getAccount", "()Lcom/vk/superapp/api/contract/SuperappApi$Account;", "app", "Lcom/vk/superapp/api/contract/SuperappApi$App;", "getApp", "()Lcom/vk/superapp/api/contract/SuperappApi$App;", "superApp", "Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "getSuperApp", "()Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "users", "Lcom/vk/superapp/api/contract/SuperappApi$Users;", "getUsers", "()Lcom/vk/superapp/api/contract/SuperappApi$Users;", "friends", "Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "getFriends", "()Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "group", "Lcom/vk/superapp/api/contract/SuperappApi$Group;", "getGroup", "()Lcom/vk/superapp/api/contract/SuperappApi$Group;", "notification", "Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "getNotification", "()Lcom/vk/superapp/api/contract/SuperappApi$Notification;", AttributionReporter.SYSTEM_PERMISSION, "Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "getPermission", "()Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "stat", "Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "getStat", "()Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "storage", "Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "getStorage", "()Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "advertisement", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "getAdvertisement", "()Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "widgets", "Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "getWidgets", "()Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "auth", "Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "getAuth", "()Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", RestoreConstants.DEFAULT_URL_PATH, "Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "getRestore", "()Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "utils", "Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "getUtils", "()Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "settings", "Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "getSettings", "()Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "vkRun", "Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "getVkRun", "()Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "identity", "Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "getIdentity", "()Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "geo", "Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "getGeo", "()Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "database", "Lcom/vk/superapp/api/contract/SuperappApi$Database;", "getDatabase", "()Lcom/vk/superapp/api/contract/SuperappApi$Database;", "email", "Lcom/vk/superapp/api/contract/SuperappApi$Email;", "getEmail", "()Lcom/vk/superapp/api/contract/SuperappApi$Email;", "birthday", "Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "getBirthday", "()Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "messages", "Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "getMessages", "()Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "esia", "Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "getEsia", "()Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "accountVerification", "Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "getAccountVerification", "()Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "vkWorkout", "Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "getVkWorkout", "()Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "vkHealth", "Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "getVkHealth", "()Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "goodsOrders", "Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "getGoodsOrders", "()Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "translations", "Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "getTranslations", "()Lcom/vk/superapp/api/contract/SuperappApi$Translations;", WalletFeatureImpl.ORDERS, "Lcom/vk/superapp/api/contract/SuperappApi$Orders;", "getOrders", "()Lcom/vk/superapp/api/contract/SuperappApi$Orders;", NavigationType.STORE, "Lcom/vk/superapp/api/contract/SuperappApi$Store;", "getStore", "()Lcom/vk/superapp/api/contract/SuperappApi$Store;", "verification", "Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "getVerification", "()Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "captcha", "Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "getCaptcha", "()Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "qrWebToApp", "Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "getQrWebToApp", "()Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "bridges_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface SuperappApiBridge {
    @NotNull
    SuperappApi.Account getAccount();

    @NotNull
    SuperappApi.AccountVerification getAccountVerification();

    @NotNull
    SuperappApi.Advertisement getAdvertisement();

    @NotNull
    SuperappApi.App getApp();

    @NotNull
    SuperappApi.VkAuth getAuth();

    @NotNull
    SuperappApi.Birthday getBirthday();

    @NotNull
    SuperappApi.Captcha getCaptcha();

    @NotNull
    SuperappApi.Common getCommon();

    @NotNull
    SuperappApi.Database getDatabase();

    @NotNull
    SuperappApi.Email getEmail();

    @NotNull
    String getEndpoint();

    @NotNull
    SuperappApi.Esia getEsia();

    @NotNull
    SuperappApi.Friends getFriends();

    @NotNull
    SuperappApi.Geo getGeo();

    @NotNull
    SuperappApi.GoodsOrders getGoodsOrders();

    @NotNull
    SuperappApi.Group getGroup();

    @NotNull
    SuperappApi.Identity getIdentity();

    @NotNull
    SuperappApi.Messages getMessages();

    @NotNull
    SuperappApi.Notification getNotification();

    @NotNull
    SuperappApi.Orders getOrders();

    @NotNull
    SuperappApi.Permission getPermission();

    @NotNull
    SuperappApi.QrWebToApp getQrWebToApp();

    @NotNull
    SuperappApi.VkRestore getRestore();

    long getServerTime();

    @NotNull
    SuperappApi.Settings getSettings();

    @NotNull
    SuperappApi.Stat getStat();

    @NotNull
    SuperappApi.Storage getStorage();

    @NotNull
    SuperappApi.Store getStore();

    @NotNull
    SuperappApi.SuperApp getSuperApp();

    @NotNull
    SuperappApi.Translations getTranslations();

    @NotNull
    SuperappApi.Users getUsers();

    @NotNull
    SuperappApi.VkUtils getUtils();

    @NotNull
    SuperappApi.Verification getVerification();

    @NotNull
    String getVkApiHost();

    @NotNull
    SuperappApi.VkHealth getVkHealth();

    @NotNull
    SuperappApi.VkRun getVkRun();

    @NotNull
    SuperappApi.VkWorkout getVkWorkout();

    @NotNull
    SuperappApi.VkIdMail getVkidmail();

    @NotNull
    SuperappApi.VkidOk getVkidok();

    @NotNull
    SuperappApi.Widgets getWidgets();

    void ignoreAccessToken(@Nullable String accessToken);

    boolean isWhitelistInternetEnabled();

    void setEndpoint(@NotNull String str);
}
