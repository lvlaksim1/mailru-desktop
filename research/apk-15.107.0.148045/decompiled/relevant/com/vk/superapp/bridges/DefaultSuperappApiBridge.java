package com.vk.superapp.bridges;

import com.huawei.hms.push.AttributionReporter;
import com.huawei.hms.support.feature.result.CommonConstant;
import com.my.target.common.NavigationType;
import com.vk.auth.restore.RestoreConstants;
import com.vk.superapp.api.contract.DefaultSuperappApi;
import com.vk.superapp.api.contract.GeneratedSuperappApi;
import com.vk.superapp.api.contract.SuperappApi;
import com.vk.superapp.api.contract.SuperappCommonApi;
import com.vk.superapp.api.contract.SuperappGeoApi;
import com.vk.superapp.api.core.SuperappApiCore;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import ru.mail.wallet.impl.presentation.navigation.WalletFeatureImpl;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes12.dex */
@Metadata(d1 = {"\u0000Ò\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0016\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0005\u001a\u00020\u0004H\u0016¢\u0006\u0004\b\u0005\u0010\u0006J\u0019\u0010\n\u001a\u00020\t2\b\u0010\b\u001a\u0004\u0018\u00010\u0007H\u0016¢\u0006\u0004\b\n\u0010\u000bJ\u000f\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\r\u0010\u000eR\u001a\u0010\u0013\u001a\u00020\u00078\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0017\u001a\u00020\u00078\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0015\u0010\u0012\"\u0004\b\u0016\u0010\u000bR\"\u0010\u001f\u001a\u00020\u00188\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c\"\u0004\b\u001d\u0010\u001eR\u001a\u0010%\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u001a\u0010+\u001a\u00020&8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\"\u00103\u001a\u00020,8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b-\u0010.\u001a\u0004\b/\u00100\"\u0004\b1\u00102R\"\u0010;\u001a\u0002048\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b5\u00106\u001a\u0004\b7\u00108\"\u0004\b9\u0010:R\"\u0010C\u001a\u00020<8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b=\u0010>\u001a\u0004\b?\u0010@\"\u0004\bA\u0010BR\"\u0010K\u001a\u00020D8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bE\u0010F\u001a\u0004\bG\u0010H\"\u0004\bI\u0010JR\"\u0010S\u001a\u00020L8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bM\u0010N\u001a\u0004\bO\u0010P\"\u0004\bQ\u0010RR\"\u0010[\u001a\u00020T8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bU\u0010V\u001a\u0004\bW\u0010X\"\u0004\bY\u0010ZR\"\u0010c\u001a\u00020\\8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b]\u0010^\u001a\u0004\b_\u0010`\"\u0004\ba\u0010bR\"\u0010k\u001a\u00020d8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\be\u0010f\u001a\u0004\bg\u0010h\"\u0004\bi\u0010jR\"\u0010s\u001a\u00020l8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR\"\u0010{\u001a\u00020t8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bu\u0010v\u001a\u0004\bw\u0010x\"\u0004\by\u0010zR&\u0010\u0083\u0001\u001a\u00020|8\u0016@\u0016X\u0096\u000e¢\u0006\u0015\n\u0004\b}\u0010~\u001a\u0005\b\u007f\u0010\u0080\u0001\"\u0006\b\u0081\u0001\u0010\u0082\u0001R*\u0010\u008b\u0001\u001a\u00030\u0084\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b\u0085\u0001\u0010\u0086\u0001\u001a\u0006\b\u0087\u0001\u0010\u0088\u0001\"\u0006\b\u0089\u0001\u0010\u008a\u0001R*\u0010\u0093\u0001\u001a\u00030\u008c\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b\u008d\u0001\u0010\u008e\u0001\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001\"\u0006\b\u0091\u0001\u0010\u0092\u0001R \u0010\u0099\u0001\u001a\u00030\u0094\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0095\u0001\u0010\u0096\u0001\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R*\u0010¡\u0001\u001a\u00030\u009a\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b\u009b\u0001\u0010\u009c\u0001\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001\"\u0006\b\u009f\u0001\u0010 \u0001R*\u0010©\u0001\u001a\u00030¢\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b£\u0001\u0010¤\u0001\u001a\u0006\b¥\u0001\u0010¦\u0001\"\u0006\b§\u0001\u0010¨\u0001R*\u0010±\u0001\u001a\u00030ª\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b«\u0001\u0010¬\u0001\u001a\u0006\b\u00ad\u0001\u0010®\u0001\"\u0006\b¯\u0001\u0010°\u0001R*\u0010¹\u0001\u001a\u00030²\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b³\u0001\u0010´\u0001\u001a\u0006\bµ\u0001\u0010¶\u0001\"\u0006\b·\u0001\u0010¸\u0001R*\u0010Á\u0001\u001a\u00030º\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\b»\u0001\u0010¼\u0001\u001a\u0006\b½\u0001\u0010¾\u0001\"\u0006\b¿\u0001\u0010À\u0001R*\u0010É\u0001\u001a\u00030Â\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\bÃ\u0001\u0010Ä\u0001\u001a\u0006\bÅ\u0001\u0010Æ\u0001\"\u0006\bÇ\u0001\u0010È\u0001R*\u0010Ñ\u0001\u001a\u00030Ê\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\bË\u0001\u0010Ì\u0001\u001a\u0006\bÍ\u0001\u0010Î\u0001\"\u0006\bÏ\u0001\u0010Ð\u0001R*\u0010Ù\u0001\u001a\u00030Ò\u00018\u0016@\u0016X\u0096\u000e¢\u0006\u0018\n\u0006\bÓ\u0001\u0010Ô\u0001\u001a\u0006\bÕ\u0001\u0010Ö\u0001\"\u0006\b×\u0001\u0010Ø\u0001R \u0010ß\u0001\u001a\u00030Ú\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÛ\u0001\u0010Ü\u0001\u001a\u0006\bÝ\u0001\u0010Þ\u0001R \u0010å\u0001\u001a\u00030à\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bá\u0001\u0010â\u0001\u001a\u0006\bã\u0001\u0010ä\u0001R \u0010ë\u0001\u001a\u00030æ\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bç\u0001\u0010è\u0001\u001a\u0006\bé\u0001\u0010ê\u0001R \u0010ñ\u0001\u001a\u00030ì\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bí\u0001\u0010î\u0001\u001a\u0006\bï\u0001\u0010ð\u0001R \u0010÷\u0001\u001a\u00030ò\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bó\u0001\u0010ô\u0001\u001a\u0006\bõ\u0001\u0010ö\u0001R \u0010ý\u0001\u001a\u00030ø\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bù\u0001\u0010ú\u0001\u001a\u0006\bû\u0001\u0010ü\u0001R \u0010\u0083\u0002\u001a\u00030þ\u00018\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\bÿ\u0001\u0010\u0080\u0002\u001a\u0006\b\u0081\u0002\u0010\u0082\u0002R \u0010\u0089\u0002\u001a\u00030\u0084\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0085\u0002\u0010\u0086\u0002\u001a\u0006\b\u0087\u0002\u0010\u0088\u0002R \u0010\u008f\u0002\u001a\u00030\u008a\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u008b\u0002\u0010\u008c\u0002\u001a\u0006\b\u008d\u0002\u0010\u008e\u0002R \u0010\u0095\u0002\u001a\u00030\u0090\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0091\u0002\u0010\u0092\u0002\u001a\u0006\b\u0093\u0002\u0010\u0094\u0002R \u0010\u009b\u0002\u001a\u00030\u0096\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u0097\u0002\u0010\u0098\u0002\u001a\u0006\b\u0099\u0002\u0010\u009a\u0002R \u0010¡\u0002\u001a\u00030\u009c\u00028\u0016X\u0096\u0004¢\u0006\u0010\n\u0006\b\u009d\u0002\u0010\u009e\u0002\u001a\u0006\b\u009f\u0002\u0010 \u0002¨\u0006¢\u0002"}, d2 = {"Lcom/vk/superapp/bridges/DefaultSuperappApiBridge;", "Lcom/vk/superapp/bridges/SuperappApiBridge;", "<init>", "()V", "", "getServerTime", "()J", "", CommonConstant.KEY_ACCESS_TOKEN, "", "ignoreAccessToken", "(Ljava/lang/String;)V", "", "isWhitelistInternetEnabled", "()Z", "kdskvkvmoca", "Ljava/lang/String;", "getVkApiHost", "()Ljava/lang/String;", "vkApiHost", "kdskvkvmocb", "getEndpoint", "setEndpoint", "endpoint", "Lcom/vk/superapp/api/contract/SuperappApi$Common;", "kdskvkvmocc", "Lcom/vk/superapp/api/contract/SuperappApi$Common;", "getCommon", "()Lcom/vk/superapp/api/contract/SuperappApi$Common;", "setCommon", "(Lcom/vk/superapp/api/contract/SuperappApi$Common;)V", "common", "Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "kdskvkvmocd", "Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "getVkidok", "()Lcom/vk/superapp/api/contract/SuperappApi$VkidOk;", "vkidok", "Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "kdskvkvmoce", "Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "getVkidmail", "()Lcom/vk/superapp/api/contract/SuperappApi$VkIdMail;", "vkidmail", "Lcom/vk/superapp/api/contract/SuperappApi$Account;", "kdskvkvmocf", "Lcom/vk/superapp/api/contract/SuperappApi$Account;", "getAccount", "()Lcom/vk/superapp/api/contract/SuperappApi$Account;", "setAccount", "(Lcom/vk/superapp/api/contract/SuperappApi$Account;)V", "account", "Lcom/vk/superapp/api/contract/SuperappApi$App;", "kdskvkvmocg", "Lcom/vk/superapp/api/contract/SuperappApi$App;", "getApp", "()Lcom/vk/superapp/api/contract/SuperappApi$App;", "setApp", "(Lcom/vk/superapp/api/contract/SuperappApi$App;)V", "app", "Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "kdskvkvmoch", "Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "getSuperApp", "()Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;", "setSuperApp", "(Lcom/vk/superapp/api/contract/SuperappApi$SuperApp;)V", "superApp", "Lcom/vk/superapp/api/contract/SuperappApi$Users;", "kdskvkvmoci", "Lcom/vk/superapp/api/contract/SuperappApi$Users;", "getUsers", "()Lcom/vk/superapp/api/contract/SuperappApi$Users;", "setUsers", "(Lcom/vk/superapp/api/contract/SuperappApi$Users;)V", "users", "Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "kdskvkvmocj", "Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "getFriends", "()Lcom/vk/superapp/api/contract/SuperappApi$Friends;", "setFriends", "(Lcom/vk/superapp/api/contract/SuperappApi$Friends;)V", "friends", "Lcom/vk/superapp/api/contract/SuperappApi$Group;", "kdskvkvmock", "Lcom/vk/superapp/api/contract/SuperappApi$Group;", "getGroup", "()Lcom/vk/superapp/api/contract/SuperappApi$Group;", "setGroup", "(Lcom/vk/superapp/api/contract/SuperappApi$Group;)V", "group", "Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "kdskvkvmocl", "Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "getNotification", "()Lcom/vk/superapp/api/contract/SuperappApi$Notification;", "setNotification", "(Lcom/vk/superapp/api/contract/SuperappApi$Notification;)V", "notification", "Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "kdskvkvmocm", "Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "getPermission", "()Lcom/vk/superapp/api/contract/SuperappApi$Permission;", "setPermission", "(Lcom/vk/superapp/api/contract/SuperappApi$Permission;)V", AttributionReporter.SYSTEM_PERMISSION, "Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "kdskvkvmocn", "Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "getStat", "()Lcom/vk/superapp/api/contract/SuperappApi$Stat;", "setStat", "(Lcom/vk/superapp/api/contract/SuperappApi$Stat;)V", "stat", "Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "kdskvkvmoco", "Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "getStorage", "()Lcom/vk/superapp/api/contract/SuperappApi$Storage;", "setStorage", "(Lcom/vk/superapp/api/contract/SuperappApi$Storage;)V", "storage", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "kdskvkvmocp", "Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "getAdvertisement", "()Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;", "setAdvertisement", "(Lcom/vk/superapp/api/contract/SuperappApi$Advertisement;)V", "advertisement", "Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "kdskvkvmocq", "Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "getWidgets", "()Lcom/vk/superapp/api/contract/SuperappApi$Widgets;", "setWidgets", "(Lcom/vk/superapp/api/contract/SuperappApi$Widgets;)V", "widgets", "Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "kdskvkvmocr", "Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "getAuth", "()Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;", "setAuth", "(Lcom/vk/superapp/api/contract/SuperappApi$VkAuth;)V", "auth", "Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "kdskvkvmocs", "Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", "getRestore", "()Lcom/vk/superapp/api/contract/SuperappApi$VkRestore;", RestoreConstants.DEFAULT_URL_PATH, "Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "kdskvkvmoct", "Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "getUtils", "()Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;", "setUtils", "(Lcom/vk/superapp/api/contract/SuperappApi$VkUtils;)V", "utils", "Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "kdskvkvmocu", "Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "getSettings", "()Lcom/vk/superapp/api/contract/SuperappApi$Settings;", "setSettings", "(Lcom/vk/superapp/api/contract/SuperappApi$Settings;)V", "settings", "Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "kdskvkvmocv", "Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "getVkRun", "()Lcom/vk/superapp/api/contract/SuperappApi$VkRun;", "setVkRun", "(Lcom/vk/superapp/api/contract/SuperappApi$VkRun;)V", "vkRun", "Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "kdskvkvmocw", "Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "getIdentity", "()Lcom/vk/superapp/api/contract/SuperappApi$Identity;", "setIdentity", "(Lcom/vk/superapp/api/contract/SuperappApi$Identity;)V", "identity", "Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "kdskvkvmocx", "Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "getGeo", "()Lcom/vk/superapp/api/contract/SuperappApi$Geo;", "setGeo", "(Lcom/vk/superapp/api/contract/SuperappApi$Geo;)V", "geo", "Lcom/vk/superapp/api/contract/SuperappApi$Database;", "kdskvkvmocy", "Lcom/vk/superapp/api/contract/SuperappApi$Database;", "getDatabase", "()Lcom/vk/superapp/api/contract/SuperappApi$Database;", "setDatabase", "(Lcom/vk/superapp/api/contract/SuperappApi$Database;)V", "database", "Lcom/vk/superapp/api/contract/SuperappApi$Email;", "kdskvkvmocz", "Lcom/vk/superapp/api/contract/SuperappApi$Email;", "getEmail", "()Lcom/vk/superapp/api/contract/SuperappApi$Email;", "setEmail", "(Lcom/vk/superapp/api/contract/SuperappApi$Email;)V", "email", "Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "kdskvkvmocaa", "Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "getBirthday", "()Lcom/vk/superapp/api/contract/SuperappApi$Birthday;", "setBirthday", "(Lcom/vk/superapp/api/contract/SuperappApi$Birthday;)V", "birthday", "Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "kdskvkvmocab", "Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "getMessages", "()Lcom/vk/superapp/api/contract/SuperappApi$Messages;", "messages", "Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "kdskvkvmocac", "Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "getEsia", "()Lcom/vk/superapp/api/contract/SuperappApi$Esia;", "esia", "Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "kdskvkvmocad", "Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "getAccountVerification", "()Lcom/vk/superapp/api/contract/SuperappApi$AccountVerification;", "accountVerification", "Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "kdskvkvmocae", "Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "getVkWorkout", "()Lcom/vk/superapp/api/contract/SuperappApi$VkWorkout;", "vkWorkout", "Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "kdskvkvmocaf", "Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "getVkHealth", "()Lcom/vk/superapp/api/contract/SuperappApi$VkHealth;", "vkHealth", "Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "kdskvkvmocag", "Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "getGoodsOrders", "()Lcom/vk/superapp/api/contract/SuperappApi$GoodsOrders;", "goodsOrders", "Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "kdskvkvmocah", "Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "getTranslations", "()Lcom/vk/superapp/api/contract/SuperappApi$Translations;", "translations", "Lcom/vk/superapp/api/contract/SuperappApi$Orders;", "kdskvkvmocai", "Lcom/vk/superapp/api/contract/SuperappApi$Orders;", "getOrders", "()Lcom/vk/superapp/api/contract/SuperappApi$Orders;", WalletFeatureImpl.ORDERS, "Lcom/vk/superapp/api/contract/SuperappApi$Store;", "kdskvkvmocaj", "Lcom/vk/superapp/api/contract/SuperappApi$Store;", "getStore", "()Lcom/vk/superapp/api/contract/SuperappApi$Store;", NavigationType.STORE, "Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "kdskvkvmocak", "Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "getVerification", "()Lcom/vk/superapp/api/contract/SuperappApi$Verification;", "verification", "Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "kdskvkvmocal", "Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "getCaptcha", "()Lcom/vk/superapp/api/contract/SuperappApi$Captcha;", "captcha", "Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "kdskvkvmocam", "Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "getQrWebToApp", "()Lcom/vk/superapp/api/contract/SuperappApi$QrWebToApp;", "qrWebToApp", "superappkit_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public class DefaultSuperappApiBridge implements SuperappApiBridge {

    /* JADX INFO: renamed from: kdskvkvmoca, reason: from kotlin metadata */
    @NotNull
    private final String vkApiHost = new String();

    /* JADX INFO: renamed from: kdskvkvmocb, reason: from kotlin metadata */
    @NotNull
    private String endpoint = new String();

    /* JADX INFO: renamed from: kdskvkvmocc, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Common common = new SuperappCommonApi();

    @NotNull
    private final GeneratedSuperappApi.VkidOk kdskvkvmocd = new GeneratedSuperappApi.VkidOk();

    @NotNull
    private final GeneratedSuperappApi.VkIdMail kdskvkvmoce = new GeneratedSuperappApi.VkIdMail();

    /* JADX INFO: renamed from: kdskvkvmocf, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Account account = new GeneratedSuperappApi.Account();

    /* JADX INFO: renamed from: kdskvkvmocg, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.App app = new GeneratedSuperappApi.App();

    /* JADX INFO: renamed from: kdskvkvmoch, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.SuperApp superApp = new GeneratedSuperappApi.SuperApp();

    /* JADX INFO: renamed from: kdskvkvmoci, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Users users = new GeneratedSuperappApi.Users();

    /* JADX INFO: renamed from: kdskvkvmocj, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Friends friends = new GeneratedSuperappApi.Friends();

    /* JADX INFO: renamed from: kdskvkvmock, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Group group = new GeneratedSuperappApi.Group();

    /* JADX INFO: renamed from: kdskvkvmocl, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Notification notification = new GeneratedSuperappApi.Notification();

    /* JADX INFO: renamed from: kdskvkvmocm, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Permission permission = new GeneratedSuperappApi.Permission();

    /* JADX INFO: renamed from: kdskvkvmocn, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Stat stat = new GeneratedSuperappApi.Stat();

    /* JADX INFO: renamed from: kdskvkvmoco, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Storage storage = new GeneratedSuperappApi.Storage();

    /* JADX INFO: renamed from: kdskvkvmocp, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Advertisement advertisement = new GeneratedSuperappApi.Advertisement();

    /* JADX INFO: renamed from: kdskvkvmocq, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Widgets widgets = new GeneratedSuperappApi.Widgets();

    /* JADX INFO: renamed from: kdskvkvmocr, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.VkAuth auth = new DefaultSuperappApi.VkAuth();

    @NotNull
    private final GeneratedSuperappApi.VkRestore kdskvkvmocs = new GeneratedSuperappApi.VkRestore();

    /* JADX INFO: renamed from: kdskvkvmoct, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.VkUtils utils = new GeneratedSuperappApi.VkUtils();

    /* JADX INFO: renamed from: kdskvkvmocu, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Settings settings = new GeneratedSuperappApi.Settings();

    /* JADX INFO: renamed from: kdskvkvmocv, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.VkRun vkRun = new GeneratedSuperappApi.VkRun();

    /* JADX INFO: renamed from: kdskvkvmocw, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Identity identity = new GeneratedSuperappApi.Identity();

    /* JADX INFO: renamed from: kdskvkvmocx, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Geo geo = new SuperappGeoApi();

    /* JADX INFO: renamed from: kdskvkvmocy, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Database database = new GeneratedSuperappApi.Database();

    /* JADX INFO: renamed from: kdskvkvmocz, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Email email = new GeneratedSuperappApi.Email();

    /* JADX INFO: renamed from: kdskvkvmocaa, reason: from kotlin metadata */
    @NotNull
    private SuperappApi.Birthday birthday = new GeneratedSuperappApi.Birthday();

    @NotNull
    private final GeneratedSuperappApi.Messages kdskvkvmocab = new GeneratedSuperappApi.Messages();

    @NotNull
    private final GeneratedSuperappApi.Esia kdskvkvmocac = new GeneratedSuperappApi.Esia();

    @NotNull
    private final GeneratedSuperappApi.AccountVerification kdskvkvmocad = new GeneratedSuperappApi.AccountVerification();

    @NotNull
    private final GeneratedSuperappApi.VkWorkout kdskvkvmocae = new GeneratedSuperappApi.VkWorkout();

    @NotNull
    private final GeneratedSuperappApi.VkHealth kdskvkvmocaf = new GeneratedSuperappApi.VkHealth();

    @NotNull
    private final GeneratedSuperappApi.GoodsOrders kdskvkvmocag = new GeneratedSuperappApi.GoodsOrders();

    @NotNull
    private final GeneratedSuperappApi.Translations kdskvkvmocah = new GeneratedSuperappApi.Translations();

    @NotNull
    private final GeneratedSuperappApi.Orders kdskvkvmocai = new GeneratedSuperappApi.Orders();

    @NotNull
    private final GeneratedSuperappApi.Store kdskvkvmocaj = new GeneratedSuperappApi.Store();

    @NotNull
    private final GeneratedSuperappApi.Verification kdskvkvmocak = new GeneratedSuperappApi.Verification();

    @NotNull
    private final GeneratedSuperappApi.Captcha kdskvkvmocal = new GeneratedSuperappApi.Captcha();

    @NotNull
    private final GeneratedSuperappApi.QrWebToApp kdskvkvmocam = new GeneratedSuperappApi.QrWebToApp();

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Account getAccount() {
        return this.account;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.AccountVerification getAccountVerification() {
        return this.kdskvkvmocad;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Advertisement getAdvertisement() {
        return this.advertisement;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.App getApp() {
        return this.app;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkAuth getAuth() {
        return this.auth;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Birthday getBirthday() {
        return this.birthday;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Captcha getCaptcha() {
        return this.kdskvkvmocal;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Common getCommon() {
        return this.common;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Database getDatabase() {
        return this.database;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Email getEmail() {
        return this.email;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public String getEndpoint() {
        return this.endpoint;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Esia getEsia() {
        return this.kdskvkvmocac;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Friends getFriends() {
        return this.friends;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Geo getGeo() {
        return this.geo;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.GoodsOrders getGoodsOrders() {
        return this.kdskvkvmocag;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Group getGroup() {
        return this.group;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Identity getIdentity() {
        return this.identity;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Messages getMessages() {
        return this.kdskvkvmocab;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Notification getNotification() {
        return this.notification;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Orders getOrders() {
        return this.kdskvkvmocai;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Permission getPermission() {
        return this.permission;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.QrWebToApp getQrWebToApp() {
        return this.kdskvkvmocam;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkRestore getRestore() {
        return this.kdskvkvmocs;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    public long getServerTime() {
        return System.currentTimeMillis();
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Settings getSettings() {
        return this.settings;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Stat getStat() {
        return this.stat;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Storage getStorage() {
        return this.storage;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Store getStore() {
        return this.kdskvkvmocaj;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.SuperApp getSuperApp() {
        return this.superApp;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Translations getTranslations() {
        return this.kdskvkvmocah;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Users getUsers() {
        return this.users;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkUtils getUtils() {
        return this.utils;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Verification getVerification() {
        return this.kdskvkvmocak;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public String getVkApiHost() {
        return this.vkApiHost;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkHealth getVkHealth() {
        return this.kdskvkvmocaf;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkRun getVkRun() {
        return this.vkRun;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkWorkout getVkWorkout() {
        return this.kdskvkvmocae;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkIdMail getVkidmail() {
        return this.kdskvkvmoce;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.VkidOk getVkidok() {
        return this.kdskvkvmocd;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    @NotNull
    public SuperappApi.Widgets getWidgets() {
        return this.widgets;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    public void ignoreAccessToken(@Nullable String accessToken) {
        SuperappApiCore.INSTANCE.ignoreAccessToken(accessToken);
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    public boolean isWhitelistInternetEnabled() {
        return false;
    }

    public void setAccount(@NotNull SuperappApi.Account account) {
        Intrinsics.checkNotNullParameter(account, "<set-?>");
        this.account = account;
    }

    public void setAdvertisement(@NotNull SuperappApi.Advertisement advertisement) {
        Intrinsics.checkNotNullParameter(advertisement, "<set-?>");
        this.advertisement = advertisement;
    }

    public void setApp(@NotNull SuperappApi.App app) {
        Intrinsics.checkNotNullParameter(app, "<set-?>");
        this.app = app;
    }

    public void setAuth(@NotNull SuperappApi.VkAuth vkAuth) {
        Intrinsics.checkNotNullParameter(vkAuth, "<set-?>");
        this.auth = vkAuth;
    }

    public void setBirthday(@NotNull SuperappApi.Birthday birthday) {
        Intrinsics.checkNotNullParameter(birthday, "<set-?>");
        this.birthday = birthday;
    }

    public void setCommon(@NotNull SuperappApi.Common common) {
        Intrinsics.checkNotNullParameter(common, "<set-?>");
        this.common = common;
    }

    public void setDatabase(@NotNull SuperappApi.Database database) {
        Intrinsics.checkNotNullParameter(database, "<set-?>");
        this.database = database;
    }

    public void setEmail(@NotNull SuperappApi.Email email) {
        Intrinsics.checkNotNullParameter(email, "<set-?>");
        this.email = email;
    }

    @Override // com.vk.superapp.bridges.SuperappApiBridge
    public void setEndpoint(@NotNull String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.endpoint = str;
    }

    public void setFriends(@NotNull SuperappApi.Friends friends) {
        Intrinsics.checkNotNullParameter(friends, "<set-?>");
        this.friends = friends;
    }

    public void setGeo(@NotNull SuperappApi.Geo geo) {
        Intrinsics.checkNotNullParameter(geo, "<set-?>");
        this.geo = geo;
    }

    public void setGroup(@NotNull SuperappApi.Group group) {
        Intrinsics.checkNotNullParameter(group, "<set-?>");
        this.group = group;
    }

    public void setIdentity(@NotNull SuperappApi.Identity identity) {
        Intrinsics.checkNotNullParameter(identity, "<set-?>");
        this.identity = identity;
    }

    public void setNotification(@NotNull SuperappApi.Notification notification) {
        Intrinsics.checkNotNullParameter(notification, "<set-?>");
        this.notification = notification;
    }

    public void setPermission(@NotNull SuperappApi.Permission permission) {
        Intrinsics.checkNotNullParameter(permission, "<set-?>");
        this.permission = permission;
    }

    public void setSettings(@NotNull SuperappApi.Settings settings) {
        Intrinsics.checkNotNullParameter(settings, "<set-?>");
        this.settings = settings;
    }

    public void setStat(@NotNull SuperappApi.Stat stat) {
        Intrinsics.checkNotNullParameter(stat, "<set-?>");
        this.stat = stat;
    }

    public void setStorage(@NotNull SuperappApi.Storage storage) {
        Intrinsics.checkNotNullParameter(storage, "<set-?>");
        this.storage = storage;
    }

    public void setSuperApp(@NotNull SuperappApi.SuperApp superApp) {
        Intrinsics.checkNotNullParameter(superApp, "<set-?>");
        this.superApp = superApp;
    }

    public void setUsers(@NotNull SuperappApi.Users users) {
        Intrinsics.checkNotNullParameter(users, "<set-?>");
        this.users = users;
    }

    public void setUtils(@NotNull SuperappApi.VkUtils vkUtils) {
        Intrinsics.checkNotNullParameter(vkUtils, "<set-?>");
        this.utils = vkUtils;
    }

    public void setVkRun(@NotNull SuperappApi.VkRun vkRun) {
        Intrinsics.checkNotNullParameter(vkRun, "<set-?>");
        this.vkRun = vkRun;
    }

    public void setWidgets(@NotNull SuperappApi.Widgets widgets) {
        Intrinsics.checkNotNullParameter(widgets, "<set-?>");
        this.widgets = widgets;
    }
}
