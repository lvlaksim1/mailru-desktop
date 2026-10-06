package com.vk.api.generated.apps.dto;

import a.detarenegipakvmocb;
import a.detarenegipakvmocj;
import android.os.Parcel;
import android.os.Parcelable;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.google.api.client.googleapis.media.MediaHttpDownloader;
import com.google.api.client.googleapis.media.MediaHttpUploader;
import com.google.gson.annotations.SerializedName;
import com.vk.dto.common.id.UserId;
import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.parcelize.Parcelize;
import okhttp3.internal.http2.Http2Connection;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.sqlite.database.sqlite.SQLiteDatabase;
import ru.mail.cloud.app.downloader.RemoteFilesRepository;
import ru.mail.data.entities.Collector;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes2.dex */
@Parcelize
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b0\n\u0002\u0010\u0000\n\u0002\bT\b\u0087\b\u0018\u00002\u00020\u0001:\u000e±\u0001²\u0001³\u0001´\u0001µ\u0001¶\u0001·\u0001Bû\u0002\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u0017\u0012\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u0019\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d\u0012\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u0004\u0012\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&\u0012\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u0002\u0012\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b,\u0010-J\r\u0010.\u001a\u00020\u0004¢\u0006\u0004\b.\u0010/J\u001d\u00104\u001a\u0002032\u0006\u00101\u001a\u0002002\u0006\u00102\u001a\u00020\u0004¢\u0006\u0004\b4\u00105J\u0012\u00106\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b6\u00107J\u0012\u00108\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b8\u00109J\u0012\u0010:\u001a\u0004\u0018\u00010\u0006HÆ\u0003¢\u0006\u0004\b:\u0010;J\u0012\u0010<\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b<\u00109J\u0012\u0010=\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\b=\u00109J\u0012\u0010>\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b>\u00107J\u0012\u0010?\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b?\u00107J\u0012\u0010@\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b@\u00107J\u0012\u0010A\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bA\u00109J\u0012\u0010B\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\bB\u0010CJ\u0012\u0010D\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bD\u00107J\u0012\u0010E\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bE\u00107J\u0012\u0010F\u001a\u0004\u0018\u00010\u000eHÆ\u0003¢\u0006\u0004\bF\u0010CJ\u0012\u0010G\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bG\u00107J\u0012\u0010H\u001a\u0004\u0018\u00010\u0014HÆ\u0003¢\u0006\u0004\bH\u0010IJ\u0012\u0010J\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bJ\u00109J\u0012\u0010K\u001a\u0004\u0018\u00010\u0017HÆ\u0003¢\u0006\u0004\bK\u0010LJ\u0012\u0010M\u001a\u0004\u0018\u00010\u0019HÆ\u0003¢\u0006\u0004\bM\u0010NJ\u0012\u0010O\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bO\u00107J\u0012\u0010P\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bP\u00109J\u0012\u0010Q\u001a\u0004\u0018\u00010\u001dHÆ\u0003¢\u0006\u0004\bQ\u0010RJ\u0012\u0010S\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bS\u00107J\u0012\u0010T\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bT\u00107J\u0012\u0010U\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bU\u00109J\u0012\u0010V\u001a\u0004\u0018\u00010\"HÆ\u0003¢\u0006\u0004\bV\u0010WJ\u0012\u0010X\u001a\u0004\u0018\u00010\u0004HÆ\u0003¢\u0006\u0004\bX\u00109J\u0012\u0010Y\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\bY\u00107J\u0012\u0010Z\u001a\u0004\u0018\u00010&HÆ\u0003¢\u0006\u0004\bZ\u0010[J\u0012\u0010\\\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b\\\u00107J\u0012\u0010]\u001a\u0004\u0018\u00010)HÆ\u0003¢\u0006\u0004\b]\u0010^J\u0012\u0010_\u001a\u0004\u0018\u00010\u0002HÆ\u0003¢\u0006\u0004\b_\u00107J\u0084\u0003\u0010`\u001a\u00020\u00002\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u00142\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u0018\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u00192\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010\u001c\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010 \u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010#\u001a\u0004\u0018\u00010\"2\n\b\u0002\u0010$\u001a\u0004\u0018\u00010\u00042\n\b\u0002\u0010%\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010'\u001a\u0004\u0018\u00010&2\n\b\u0002\u0010(\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010*\u001a\u0004\u0018\u00010)2\n\b\u0002\u0010+\u001a\u0004\u0018\u00010\u0002HÆ\u0001¢\u0006\u0004\b`\u0010aJ\u0010\u0010b\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\bb\u00107J\u0010\u0010c\u001a\u00020\u0004HÖ\u0001¢\u0006\u0004\bc\u0010/J\u001a\u0010f\u001a\u00020)2\b\u0010e\u001a\u0004\u0018\u00010dHÖ\u0003¢\u0006\u0004\bf\u0010gR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bh\u0010i\u001a\u0004\bj\u00107R\u001c\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bk\u0010l\u001a\u0004\bm\u00109R\u001c\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006X\u0087\u0004¢\u0006\f\n\u0004\bn\u0010o\u001a\u0004\bp\u0010;R\u001c\u0010\b\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bq\u0010l\u001a\u0004\br\u00109R\u001c\u0010\t\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\bs\u0010l\u001a\u0004\bt\u00109R\u001c\u0010\n\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bu\u0010i\u001a\u0004\bv\u00107R\u001c\u0010\u000b\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\bw\u0010i\u001a\u0004\bx\u00107R\u001c\u0010\f\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\f\n\u0004\by\u0010i\u001a\u0004\bz\u00107R\u001c\u0010\r\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\f\n\u0004\b{\u0010l\u001a\u0004\b|\u00109R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\f\n\u0004\b}\u0010~\u001a\u0004\b\u007f\u0010CR\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0080\u0001\u0010i\u001a\u0005\b\u0081\u0001\u00107R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0082\u0001\u0010i\u001a\u0005\b\u0083\u0001\u00107R\u001e\u0010\u0012\u001a\u0004\u0018\u00010\u000e8\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0084\u0001\u0010~\u001a\u0005\b\u0085\u0001\u0010CR\u001e\u0010\u0013\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0086\u0001\u0010i\u001a\u0005\b\u0087\u0001\u00107R\u001f\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b\u0088\u0001\u0010\u0089\u0001\u001a\u0005\b\u008a\u0001\u0010IR\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u008b\u0001\u0010l\u001a\u0005\b\u008c\u0001\u00109R\u001f\u0010\u0018\u001a\u0004\u0018\u00010\u00178\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b\u008d\u0001\u0010\u008e\u0001\u001a\u0005\b\u008f\u0001\u0010LR\u001f\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b\u0090\u0001\u0010\u0091\u0001\u001a\u0005\b\u0092\u0001\u0010NR\u001e\u0010\u001b\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0093\u0001\u0010i\u001a\u0005\b\u0094\u0001\u00107R\u001e\u0010\u001c\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u0095\u0001\u0010l\u001a\u0005\b\u0096\u0001\u00109R\u001f\u0010\u001e\u001a\u0004\u0018\u00010\u001d8\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b\u0097\u0001\u0010\u0098\u0001\u001a\u0005\b\u0099\u0001\u0010RR\u001e\u0010\u001f\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u009a\u0001\u0010i\u001a\u0005\b\u009b\u0001\u00107R\u001e\u0010 \u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u009c\u0001\u0010i\u001a\u0005\b\u009d\u0001\u00107R\u001e\u0010!\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b\u009e\u0001\u0010l\u001a\u0005\b\u009f\u0001\u00109R\u001f\u0010#\u001a\u0004\u0018\u00010\"8\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b \u0001\u0010¡\u0001\u001a\u0005\b¢\u0001\u0010WR\u001e\u0010$\u001a\u0004\u0018\u00010\u00048\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b£\u0001\u0010l\u001a\u0005\b¤\u0001\u00109R\u001e\u0010%\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b¥\u0001\u0010i\u001a\u0005\b¦\u0001\u00107R\u001f\u0010'\u001a\u0004\u0018\u00010&8\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b§\u0001\u0010¨\u0001\u001a\u0005\b©\u0001\u0010[R\u001e\u0010(\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\bª\u0001\u0010i\u001a\u0005\b«\u0001\u00107R\u001f\u0010*\u001a\u0004\u0018\u00010)8\u0006X\u0087\u0004¢\u0006\u000f\n\u0006\b¬\u0001\u0010\u00ad\u0001\u001a\u0005\b®\u0001\u0010^R\u001e\u0010+\u001a\u0004\u0018\u00010\u00028\u0006X\u0087\u0004¢\u0006\u000e\n\u0005\b¯\u0001\u0010i\u001a\u0005\b°\u0001\u00107¨\u0006¸\u0001"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto;", "Landroid/os/Parcelable;", "", "vkAccessTokenSettings", "", "vkAppId", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;", "vkAreNotificationsEnabled", "vkIsAppUser", "vkIsFavorite", "vkLanguage", "vkPlatform", "vkRef", "vkTs", "Lcom/vk/dto/common/id/UserId;", "vkUserId", "sign", "vkViewerGroupRole", "vkGroupId", "vkExperiment", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;", "vkHasProfileButton", "vkProfileId", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;", "vkIsRecommended", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;", "vkIsEmployee", "vkMode", "vkSeg", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;", "vkH3", "vkClient", "vkRestrictions", "vkTestingGroupId", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;", "vkIsWidescreen", "vkRequestId", "vkRequestKey", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;", "vkIsPlayMachine", "appHash", "", "vkIsUnauth", "vkOkUserId", "<init>", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;Ljava/lang/Integer;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)V", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "component1", "()Ljava/lang/String;", "component2", "()Ljava/lang/Integer;", "component3", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;", "component4", "component5", "component6", "component7", "component8", "component9", "component10", "()Lcom/vk/dto/common/id/UserId;", "component11", "component12", "component13", "component14", "component15", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;", "component16", "component17", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;", "component18", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;", "component19", "component20", "component21", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;", "component22", "component23", "component24", "component25", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;", "component26", "component27", "component28", "()Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;", "component29", "component30", "()Ljava/lang/Boolean;", "component31", "copy", "(Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Ljava/lang/String;Lcom/vk/dto/common/id/UserId;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Integer;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;Ljava/lang/Integer;Ljava/lang/String;Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;)Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto;", "toString", "hashCode", "", "other", "equals", "(Ljava/lang/Object;)Z", "detarenegipakvmoca", "Ljava/lang/String;", "getVkAccessTokenSettings", "detarenegipakvmocb", "Ljava/lang/Integer;", "getVkAppId", "detarenegipakvmocc", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;", "getVkAreNotificationsEnabled", "detarenegipakvmocd", "getVkIsAppUser", "detarenegipakvmoce", "getVkIsFavorite", "detarenegipakvmocf", "getVkLanguage", "detarenegipakvmocg", "getVkPlatform", "detarenegipakvmoch", "getVkRef", "detarenegipakvmoci", "getVkTs", "detarenegipakvmocj", "Lcom/vk/dto/common/id/UserId;", "getVkUserId", "detarenegipakvmock", "getSign", "detarenegipakvmocl", "getVkViewerGroupRole", "detarenegipakvmocm", "getVkGroupId", "detarenegipakvmocn", "getVkExperiment", "detarenegipakvmoco", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;", "getVkHasProfileButton", "detarenegipakvmocp", "getVkProfileId", "detarenegipakvmocq", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;", "getVkIsRecommended", "detarenegipakvmocr", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;", "getVkIsEmployee", "detarenegipakvmocs", "getVkMode", "detarenegipakvmoct", "getVkSeg", "detarenegipakvmocu", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;", "getVkH3", "detarenegipakvmocv", "getVkClient", "detarenegipakvmocw", "getVkRestrictions", "detarenegipakvmocx", "getVkTestingGroupId", "detarenegipakvmocy", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;", "getVkIsWidescreen", "detarenegipakvmocz", "getVkRequestId", "detarenegipakvmocaa", "getVkRequestKey", "detarenegipakvmocab", "Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;", "getVkIsPlayMachine", "detarenegipakvmocac", "getAppHash", "detarenegipakvmocad", "Ljava/lang/Boolean;", "getVkIsUnauth", "detarenegipakvmocae", "getVkOkUserId", "VkAreNotificationsEnabledDto", "VkHasProfileButtonDto", "VkIsRecommendedDto", "VkIsEmployeeDto", "VkH3Dto", "VkIsWidescreenDto", "VkIsPlayMachineDto", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class AppsGetAppLaunchParamsResponseDto implements Parcelable {

    @NotNull
    public static final Parcelable.Creator<AppsGetAppLaunchParamsResponseDto> CREATOR = new Creator();

    /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
    @SerializedName("vk_access_token_settings")
    @Nullable
    private final String vkAccessTokenSettings;

    /* JADX INFO: renamed from: detarenegipakvmocaa, reason: from kotlin metadata */
    @SerializedName("vk_request_key")
    @Nullable
    private final String vkRequestKey;

    /* JADX INFO: renamed from: detarenegipakvmocab, reason: from kotlin metadata */
    @SerializedName("vk_is_play_machine")
    @Nullable
    private final VkIsPlayMachineDto vkIsPlayMachine;

    /* JADX INFO: renamed from: detarenegipakvmocac, reason: from kotlin metadata */
    @SerializedName("app_hash")
    @Nullable
    private final String appHash;

    /* JADX INFO: renamed from: detarenegipakvmocad, reason: from kotlin metadata */
    @SerializedName("vk_is_unauth")
    @Nullable
    private final Boolean vkIsUnauth;

    /* JADX INFO: renamed from: detarenegipakvmocae, reason: from kotlin metadata */
    @SerializedName("vk_ok_user_id")
    @Nullable
    private final String vkOkUserId;

    /* JADX INFO: renamed from: detarenegipakvmocb, reason: from kotlin metadata */
    @SerializedName("vk_app_id")
    @Nullable
    private final Integer vkAppId;

    /* JADX INFO: renamed from: detarenegipakvmocc, reason: from kotlin metadata */
    @SerializedName("vk_are_notifications_enabled")
    @Nullable
    private final VkAreNotificationsEnabledDto vkAreNotificationsEnabled;

    /* JADX INFO: renamed from: detarenegipakvmocd, reason: from kotlin metadata */
    @SerializedName("vk_is_app_user")
    @Nullable
    private final Integer vkIsAppUser;

    /* JADX INFO: renamed from: detarenegipakvmoce, reason: from kotlin metadata */
    @SerializedName("vk_is_favorite")
    @Nullable
    private final Integer vkIsFavorite;

    /* JADX INFO: renamed from: detarenegipakvmocf, reason: from kotlin metadata */
    @SerializedName("vk_language")
    @Nullable
    private final String vkLanguage;

    /* JADX INFO: renamed from: detarenegipakvmocg, reason: from kotlin metadata */
    @SerializedName("vk_platform")
    @Nullable
    private final String vkPlatform;

    /* JADX INFO: renamed from: detarenegipakvmoch, reason: from kotlin metadata */
    @SerializedName("vk_ref")
    @Nullable
    private final String vkRef;

    /* JADX INFO: renamed from: detarenegipakvmoci, reason: from kotlin metadata */
    @SerializedName("vk_ts")
    @Nullable
    private final Integer vkTs;

    /* JADX INFO: renamed from: detarenegipakvmocj, reason: from kotlin metadata */
    @SerializedName("vk_user_id")
    @Nullable
    private final UserId vkUserId;

    /* JADX INFO: renamed from: detarenegipakvmock, reason: from kotlin metadata */
    @SerializedName("sign")
    @Nullable
    private final String sign;

    /* JADX INFO: renamed from: detarenegipakvmocl, reason: from kotlin metadata */
    @SerializedName("vk_viewer_group_role")
    @Nullable
    private final String vkViewerGroupRole;

    /* JADX INFO: renamed from: detarenegipakvmocm, reason: from kotlin metadata */
    @SerializedName("vk_group_id")
    @Nullable
    private final UserId vkGroupId;

    /* JADX INFO: renamed from: detarenegipakvmocn, reason: from kotlin metadata */
    @SerializedName("vk_experiment")
    @Nullable
    private final String vkExperiment;

    /* JADX INFO: renamed from: detarenegipakvmoco, reason: from kotlin metadata */
    @SerializedName("vk_has_profile_button")
    @Nullable
    private final VkHasProfileButtonDto vkHasProfileButton;

    /* JADX INFO: renamed from: detarenegipakvmocp, reason: from kotlin metadata */
    @SerializedName("vk_profile_id")
    @Nullable
    private final Integer vkProfileId;

    /* JADX INFO: renamed from: detarenegipakvmocq, reason: from kotlin metadata */
    @SerializedName("vk_is_recommended")
    @Nullable
    private final VkIsRecommendedDto vkIsRecommended;

    /* JADX INFO: renamed from: detarenegipakvmocr, reason: from kotlin metadata */
    @SerializedName("vk_is_employee")
    @Nullable
    private final VkIsEmployeeDto vkIsEmployee;

    /* JADX INFO: renamed from: detarenegipakvmocs, reason: from kotlin metadata */
    @SerializedName("vk_mode")
    @Nullable
    private final String vkMode;

    /* JADX INFO: renamed from: detarenegipakvmoct, reason: from kotlin metadata */
    @SerializedName("vk_seg")
    @Nullable
    private final Integer vkSeg;

    /* JADX INFO: renamed from: detarenegipakvmocu, reason: from kotlin metadata */
    @SerializedName("vk_h3")
    @Nullable
    private final VkH3Dto vkH3;

    /* JADX INFO: renamed from: detarenegipakvmocv, reason: from kotlin metadata */
    @SerializedName("vk_client")
    @Nullable
    private final String vkClient;

    /* JADX INFO: renamed from: detarenegipakvmocw, reason: from kotlin metadata */
    @SerializedName("vk_restrictions")
    @Nullable
    private final String vkRestrictions;

    /* JADX INFO: renamed from: detarenegipakvmocx, reason: from kotlin metadata */
    @SerializedName("vk_testing_group_id")
    @Nullable
    private final Integer vkTestingGroupId;

    /* JADX INFO: renamed from: detarenegipakvmocy, reason: from kotlin metadata */
    @SerializedName("vk_is_widescreen")
    @Nullable
    private final VkIsWidescreenDto vkIsWidescreen;

    /* JADX INFO: renamed from: detarenegipakvmocz, reason: from kotlin metadata */
    @SerializedName("vk_request_id")
    @Nullable
    private final Integer vkRequestId;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final class Creator implements Parcelable.Creator<AppsGetAppLaunchParamsResponseDto> {
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsGetAppLaunchParamsResponseDto createFromParcel(Parcel parcel) {
            Boolean boolValueOf;
            Intrinsics.checkNotNullParameter(parcel, "parcel");
            String string = parcel.readString();
            Integer numValueOf = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            VkAreNotificationsEnabledDto vkAreNotificationsEnabledDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkAreNotificationsEnabledDto.CREATOR.createFromParcel(parcel);
            Integer numValueOf2 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            Integer numValueOf3 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String string2 = parcel.readString();
            String string3 = parcel.readString();
            String string4 = parcel.readString();
            Integer numValueOf4 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            UserId userId = (UserId) parcel.readParcelable(AppsGetAppLaunchParamsResponseDto.class.getClassLoader());
            String string5 = parcel.readString();
            String string6 = parcel.readString();
            UserId userId2 = (UserId) parcel.readParcelable(AppsGetAppLaunchParamsResponseDto.class.getClassLoader());
            String string7 = parcel.readString();
            VkHasProfileButtonDto vkHasProfileButtonDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkHasProfileButtonDto.CREATOR.createFromParcel(parcel);
            Integer numValueOf5 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            VkIsRecommendedDto vkIsRecommendedDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkIsRecommendedDto.CREATOR.createFromParcel(parcel);
            VkIsEmployeeDto vkIsEmployeeDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkIsEmployeeDto.CREATOR.createFromParcel(parcel);
            String string8 = parcel.readString();
            Integer numValueOf6 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            VkH3Dto vkH3DtoCreateFromParcel = parcel.readInt() == 0 ? null : VkH3Dto.CREATOR.createFromParcel(parcel);
            String string9 = parcel.readString();
            String string10 = parcel.readString();
            Integer numValueOf7 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            VkIsWidescreenDto vkIsWidescreenDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkIsWidescreenDto.CREATOR.createFromParcel(parcel);
            Integer numValueOf8 = parcel.readInt() == 0 ? null : Integer.valueOf(parcel.readInt());
            String string11 = parcel.readString();
            VkIsPlayMachineDto vkIsPlayMachineDtoCreateFromParcel = parcel.readInt() == 0 ? null : VkIsPlayMachineDto.CREATOR.createFromParcel(parcel);
            String string12 = parcel.readString();
            if (parcel.readInt() == 0) {
                boolValueOf = null;
            } else {
                boolValueOf = Boolean.valueOf(parcel.readInt() != 0);
            }
            return new AppsGetAppLaunchParamsResponseDto(string, numValueOf, vkAreNotificationsEnabledDtoCreateFromParcel, numValueOf2, numValueOf3, string2, string3, string4, numValueOf4, userId, string5, string6, userId2, string7, vkHasProfileButtonDtoCreateFromParcel, numValueOf5, vkIsRecommendedDtoCreateFromParcel, vkIsEmployeeDtoCreateFromParcel, string8, numValueOf6, vkH3DtoCreateFromParcel, string9, string10, numValueOf7, vkIsWidescreenDtoCreateFromParcel, numValueOf8, string11, vkIsPlayMachineDtoCreateFromParcel, string12, boolValueOf, parcel.readString());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public final AppsGetAppLaunchParamsResponseDto[] newArray(int i10) {
            return new AppsGetAppLaunchParamsResponseDto[i10];
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto[]) from 0x001a: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u000f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkAreNotificationsEnabledDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "detarenegipakvmoca", "I", "getValue", "value", "TYPE_0", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkAreNotificationsEnabledDto implements Parcelable {
        TYPE_0(0),
        TYPE_1(1);


        @NotNull
        public static final Parcelable.Creator<VkAreNotificationsEnabledDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        private final int value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkAreNotificationsEnabledDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkAreNotificationsEnabledDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkAreNotificationsEnabledDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkAreNotificationsEnabledDto[] newArray(int i10) {
                return new VkAreNotificationsEnabledDto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new VkAreNotificationsEnabledDto[]{r0, r1});
        }

        private VkAreNotificationsEnabledDto(int i10) {
            super(str, i);
            this.value = i10;
        }

        @NotNull
        public static EnumEntries<VkAreNotificationsEnabledDto> getEntries() {
            return detarenegipakvmocc;
        }

        public static VkAreNotificationsEnabledDto valueOf(String str) {
            return (VkAreNotificationsEnabledDto) Enum.valueOf(VkAreNotificationsEnabledDto.class, str);
        }

        public static VkAreNotificationsEnabledDto[] values() {
            return (VkAreNotificationsEnabledDto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkH3Dto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkH3Dto[]) from 0x001a: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkH3Dto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u000f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkH3Dto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "detarenegipakvmoca", "I", "getValue", "value", "TYPE_0", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkH3Dto implements Parcelable {
        TYPE_0(0),
        TYPE_1(1);


        @NotNull
        public static final Parcelable.Creator<VkH3Dto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        private final int value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkH3Dto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkH3Dto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkH3Dto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkH3Dto[] newArray(int i10) {
                return new VkH3Dto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new VkH3Dto[]{r0, r1});
        }

        private VkH3Dto(int i10) {
            super(str, i);
            this.value = i10;
        }

        @NotNull
        public static EnumEntries<VkH3Dto> getEntries() {
            return detarenegipakvmocc;
        }

        public static VkH3Dto valueOf(String str) {
            return (VkH3Dto) Enum.valueOf(VkH3Dto.class, str);
        }

        public static VkH3Dto[] values() {
            return (VkH3Dto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto[]) from 0x000d: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:14)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkHasProfileButtonDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "value", "I", "getValue", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkHasProfileButtonDto implements Parcelable {
        TYPE_1;


        @NotNull
        public static final Parcelable.Creator<VkHasProfileButtonDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocb;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkHasProfileButtonDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkHasProfileButtonDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkHasProfileButtonDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkHasProfileButtonDto[] newArray(int i10) {
                return new VkHasProfileButtonDto[i10];
            }
        }

        static {
            detarenegipakvmocb = EnumEntriesKt.enumEntries(new VkHasProfileButtonDto[]{r0});
        }

        private VkHasProfileButtonDto() {
            super("TYPE_1", 0);
        }

        @NotNull
        public static EnumEntries<VkHasProfileButtonDto> getEntries() {
            return detarenegipakvmocb;
        }

        public static VkHasProfileButtonDto valueOf(String str) {
            return (VkHasProfileButtonDto) Enum.valueOf(VkHasProfileButtonDto.class, str);
        }

        public static VkHasProfileButtonDto[] values() {
            return (VkHasProfileButtonDto[]) detarenegipakvmoca.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return 1;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto[]) from 0x001a: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u000f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsEmployeeDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "detarenegipakvmoca", "I", "getValue", "value", "TYPE_0", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkIsEmployeeDto implements Parcelable {
        TYPE_0(0),
        TYPE_1(1);


        @NotNull
        public static final Parcelable.Creator<VkIsEmployeeDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        private final int value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkIsEmployeeDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsEmployeeDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkIsEmployeeDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsEmployeeDto[] newArray(int i10) {
                return new VkIsEmployeeDto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new VkIsEmployeeDto[]{r0, r1});
        }

        private VkIsEmployeeDto(int i10) {
            super(str, i);
            this.value = i10;
        }

        @NotNull
        public static EnumEntries<VkIsEmployeeDto> getEntries() {
            return detarenegipakvmocc;
        }

        public static VkIsEmployeeDto valueOf(String str) {
            return (VkIsEmployeeDto) Enum.valueOf(VkIsEmployeeDto.class, str);
        }

        public static VkIsEmployeeDto[] values() {
            return (VkIsEmployeeDto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto[]) from 0x001a: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u000f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsPlayMachineDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "detarenegipakvmoca", "I", "getValue", "value", "TYPE_0", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkIsPlayMachineDto implements Parcelable {
        TYPE_0(0),
        TYPE_1(1);


        @NotNull
        public static final Parcelable.Creator<VkIsPlayMachineDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        private final int value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkIsPlayMachineDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsPlayMachineDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkIsPlayMachineDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsPlayMachineDto[] newArray(int i10) {
                return new VkIsPlayMachineDto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new VkIsPlayMachineDto[]{r0, r1});
        }

        private VkIsPlayMachineDto(int i10) {
            super(str, i);
            this.value = i10;
        }

        @NotNull
        public static EnumEntries<VkIsPlayMachineDto> getEntries() {
            return detarenegipakvmocc;
        }

        public static VkIsPlayMachineDto valueOf(String str) {
            return (VkIsPlayMachineDto) Enum.valueOf(VkIsPlayMachineDto.class, str);
        }

        public static VkIsPlayMachineDto[] values() {
            return (VkIsPlayMachineDto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto[]) from 0x000d: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:14)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0007\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsRecommendedDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "value", "I", "getValue", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkIsRecommendedDto implements Parcelable {
        TYPE_1;


        @NotNull
        public static final Parcelable.Creator<VkIsRecommendedDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocb;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkIsRecommendedDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsRecommendedDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkIsRecommendedDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsRecommendedDto[] newArray(int i10) {
                return new VkIsRecommendedDto[i10];
            }
        }

        static {
            detarenegipakvmocb = EnumEntriesKt.enumEntries(new VkIsRecommendedDto[]{r0});
        }

        private VkIsRecommendedDto() {
            super("TYPE_1", 0);
        }

        @NotNull
        public static EnumEntries<VkIsRecommendedDto> getEntries() {
            return detarenegipakvmocb;
        }

        public static VkIsRecommendedDto valueOf(String str) {
            return (VkIsRecommendedDto) Enum.valueOf(VkIsRecommendedDto.class, str);
        }

        public static VkIsRecommendedDto[] values() {
            return (VkIsRecommendedDto[]) detarenegipakvmoca.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return 1;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    /* JADX WARN: Enum visitor error
    jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto[], still in use, count: 1, list:
      (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto[]) from 0x001a: INVOKE (r0v1 com.vk.api.generated.apps.dto.AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto[]) STATIC call: kotlin.enums.EnumEntriesKt.enumEntries(java.lang.Enum[]):kotlin.enums.EnumEntries A[MD:<E extends java.lang.Enum<E>>:(E extends java.lang.Enum<E>[]):kotlin.enums.EnumEntries<E extends java.lang.Enum<E>> (m), WRAPPED] (LINE:27)
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
    @Parcelize
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0087\u0081\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002J\r\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bR\u0017\u0010\u000f\u001a\u00020\u00038\u0006¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u0005j\u0002\b\u0010j\u0002\b\u0011¨\u0006\u0012"}, d2 = {"Lcom/vk/api/generated/apps/dto/AppsGetAppLaunchParamsResponseDto$VkIsWidescreenDto;", "Landroid/os/Parcelable;", "", "", "describeContents", "()I", "Landroid/os/Parcel;", "dest", Collector.FLAGS, "", "writeToParcel", "(Landroid/os/Parcel;I)V", "detarenegipakvmoca", "I", "getValue", "value", "TYPE_0", "TYPE_1", "api-generated_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class VkIsWidescreenDto implements Parcelable {
        TYPE_0(0),
        TYPE_1(1);


        @NotNull
        public static final Parcelable.Creator<VkIsWidescreenDto> CREATOR = new Creator();
        private static final /* synthetic */ EnumEntries detarenegipakvmocc;

        /* JADX INFO: renamed from: detarenegipakvmoca, reason: from kotlin metadata */
        private final int value;

        /* JADX INFO: compiled from: ProGuard */
        @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
        public static final class Creator implements Parcelable.Creator<VkIsWidescreenDto> {
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsWidescreenDto createFromParcel(Parcel parcel) {
                Intrinsics.checkNotNullParameter(parcel, "parcel");
                return VkIsWidescreenDto.valueOf(parcel.readString());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public final VkIsWidescreenDto[] newArray(int i10) {
                return new VkIsWidescreenDto[i10];
            }
        }

        static {
            detarenegipakvmocc = EnumEntriesKt.enumEntries(new VkIsWidescreenDto[]{r0, r1});
        }

        private VkIsWidescreenDto(int i10) {
            super(str, i);
            this.value = i10;
        }

        @NotNull
        public static EnumEntries<VkIsWidescreenDto> getEntries() {
            return detarenegipakvmocc;
        }

        public static VkIsWidescreenDto valueOf(String str) {
            return (VkIsWidescreenDto) Enum.valueOf(VkIsWidescreenDto.class, str);
        }

        public static VkIsWidescreenDto[] values() {
            return (VkIsWidescreenDto[]) detarenegipakvmocb.clone();
        }

        @Override // android.os.Parcelable
        public final int describeContents() {
            return 0;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // android.os.Parcelable
        public final void writeToParcel(@NotNull Parcel dest, int flags) {
            Intrinsics.checkNotNullParameter(dest, "dest");
            dest.writeString(name());
        }
    }

    public AppsGetAppLaunchParamsResponseDto() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, Integer.MAX_VALUE, null);
    }

    public static /* synthetic */ AppsGetAppLaunchParamsResponseDto copy$default(AppsGetAppLaunchParamsResponseDto appsGetAppLaunchParamsResponseDto, String str, Integer num, VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto, Integer num2, Integer num3, String str2, String str3, String str4, Integer num4, UserId userId, String str5, String str6, UserId userId2, String str7, VkHasProfileButtonDto vkHasProfileButtonDto, Integer num5, VkIsRecommendedDto vkIsRecommendedDto, VkIsEmployeeDto vkIsEmployeeDto, String str8, Integer num6, VkH3Dto vkH3Dto, String str9, String str10, Integer num7, VkIsWidescreenDto vkIsWidescreenDto, Integer num8, String str11, VkIsPlayMachineDto vkIsPlayMachineDto, String str12, Boolean bool, String str13, int i10, Object obj) {
        String str14;
        Boolean bool2;
        String str15 = (i10 & 1) != 0 ? appsGetAppLaunchParamsResponseDto.vkAccessTokenSettings : str;
        Integer num9 = (i10 & 2) != 0 ? appsGetAppLaunchParamsResponseDto.vkAppId : num;
        VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto2 = (i10 & 4) != 0 ? appsGetAppLaunchParamsResponseDto.vkAreNotificationsEnabled : vkAreNotificationsEnabledDto;
        Integer num10 = (i10 & 8) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsAppUser : num2;
        Integer num11 = (i10 & 16) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsFavorite : num3;
        String str16 = (i10 & 32) != 0 ? appsGetAppLaunchParamsResponseDto.vkLanguage : str2;
        String str17 = (i10 & 64) != 0 ? appsGetAppLaunchParamsResponseDto.vkPlatform : str3;
        String str18 = (i10 & 128) != 0 ? appsGetAppLaunchParamsResponseDto.vkRef : str4;
        Integer num12 = (i10 & 256) != 0 ? appsGetAppLaunchParamsResponseDto.vkTs : num4;
        UserId userId3 = (i10 & 512) != 0 ? appsGetAppLaunchParamsResponseDto.vkUserId : userId;
        String str19 = (i10 & 1024) != 0 ? appsGetAppLaunchParamsResponseDto.sign : str5;
        String str20 = (i10 & 2048) != 0 ? appsGetAppLaunchParamsResponseDto.vkViewerGroupRole : str6;
        UserId userId4 = (i10 & 4096) != 0 ? appsGetAppLaunchParamsResponseDto.vkGroupId : userId2;
        String str21 = (i10 & 8192) != 0 ? appsGetAppLaunchParamsResponseDto.vkExperiment : str7;
        String str22 = str15;
        VkHasProfileButtonDto vkHasProfileButtonDto2 = (i10 & 16384) != 0 ? appsGetAppLaunchParamsResponseDto.vkHasProfileButton : vkHasProfileButtonDto;
        Integer num13 = (i10 & 32768) != 0 ? appsGetAppLaunchParamsResponseDto.vkProfileId : num5;
        VkIsRecommendedDto vkIsRecommendedDto2 = (i10 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsRecommended : vkIsRecommendedDto;
        VkIsEmployeeDto vkIsEmployeeDto2 = (i10 & 131072) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsEmployee : vkIsEmployeeDto;
        String str23 = (i10 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? appsGetAppLaunchParamsResponseDto.vkMode : str8;
        Integer num14 = (i10 & 524288) != 0 ? appsGetAppLaunchParamsResponseDto.vkSeg : num6;
        VkH3Dto vkH3Dto2 = (i10 & 1048576) != 0 ? appsGetAppLaunchParamsResponseDto.vkH3 : vkH3Dto;
        String str24 = (i10 & 2097152) != 0 ? appsGetAppLaunchParamsResponseDto.vkClient : str9;
        String str25 = (i10 & 4194304) != 0 ? appsGetAppLaunchParamsResponseDto.vkRestrictions : str10;
        Integer num15 = (i10 & RemoteFilesRepository.BYTE_ARRAY_SIZE) != 0 ? appsGetAppLaunchParamsResponseDto.vkTestingGroupId : num7;
        VkIsWidescreenDto vkIsWidescreenDto2 = (i10 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsWidescreen : vkIsWidescreenDto;
        Integer num16 = (i10 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? appsGetAppLaunchParamsResponseDto.vkRequestId : num8;
        String str26 = (i10 & 67108864) != 0 ? appsGetAppLaunchParamsResponseDto.vkRequestKey : str11;
        VkIsPlayMachineDto vkIsPlayMachineDto2 = (i10 & 134217728) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsPlayMachine : vkIsPlayMachineDto;
        String str27 = (i10 & SQLiteDatabase.CREATE_IF_NECESSARY) != 0 ? appsGetAppLaunchParamsResponseDto.appHash : str12;
        Boolean bool3 = (i10 & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? appsGetAppLaunchParamsResponseDto.vkIsUnauth : bool;
        if ((i10 & 1073741824) != 0) {
            bool2 = bool3;
            str14 = appsGetAppLaunchParamsResponseDto.vkOkUserId;
        } else {
            str14 = str13;
            bool2 = bool3;
        }
        return appsGetAppLaunchParamsResponseDto.copy(str22, num9, vkAreNotificationsEnabledDto2, num10, num11, str16, str17, str18, num12, userId3, str19, str20, userId4, str21, vkHasProfileButtonDto2, num13, vkIsRecommendedDto2, vkIsEmployeeDto2, str23, num14, vkH3Dto2, str24, str25, num15, vkIsWidescreenDto2, num16, str26, vkIsPlayMachineDto2, str27, bool2, str14);
    }

    @Nullable
    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getVkAccessTokenSettings() {
        return this.vkAccessTokenSettings;
    }

    @Nullable
    /* JADX INFO: renamed from: component10, reason: from getter */
    public final UserId getVkUserId() {
        return this.vkUserId;
    }

    @Nullable
    /* JADX INFO: renamed from: component11, reason: from getter */
    public final String getSign() {
        return this.sign;
    }

    @Nullable
    /* JADX INFO: renamed from: component12, reason: from getter */
    public final String getVkViewerGroupRole() {
        return this.vkViewerGroupRole;
    }

    @Nullable
    /* JADX INFO: renamed from: component13, reason: from getter */
    public final UserId getVkGroupId() {
        return this.vkGroupId;
    }

    @Nullable
    /* JADX INFO: renamed from: component14, reason: from getter */
    public final String getVkExperiment() {
        return this.vkExperiment;
    }

    @Nullable
    /* JADX INFO: renamed from: component15, reason: from getter */
    public final VkHasProfileButtonDto getVkHasProfileButton() {
        return this.vkHasProfileButton;
    }

    @Nullable
    /* JADX INFO: renamed from: component16, reason: from getter */
    public final Integer getVkProfileId() {
        return this.vkProfileId;
    }

    @Nullable
    /* JADX INFO: renamed from: component17, reason: from getter */
    public final VkIsRecommendedDto getVkIsRecommended() {
        return this.vkIsRecommended;
    }

    @Nullable
    /* JADX INFO: renamed from: component18, reason: from getter */
    public final VkIsEmployeeDto getVkIsEmployee() {
        return this.vkIsEmployee;
    }

    @Nullable
    /* JADX INFO: renamed from: component19, reason: from getter */
    public final String getVkMode() {
        return this.vkMode;
    }

    @Nullable
    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Integer getVkAppId() {
        return this.vkAppId;
    }

    @Nullable
    /* JADX INFO: renamed from: component20, reason: from getter */
    public final Integer getVkSeg() {
        return this.vkSeg;
    }

    @Nullable
    /* JADX INFO: renamed from: component21, reason: from getter */
    public final VkH3Dto getVkH3() {
        return this.vkH3;
    }

    @Nullable
    /* JADX INFO: renamed from: component22, reason: from getter */
    public final String getVkClient() {
        return this.vkClient;
    }

    @Nullable
    /* JADX INFO: renamed from: component23, reason: from getter */
    public final String getVkRestrictions() {
        return this.vkRestrictions;
    }

    @Nullable
    /* JADX INFO: renamed from: component24, reason: from getter */
    public final Integer getVkTestingGroupId() {
        return this.vkTestingGroupId;
    }

    @Nullable
    /* JADX INFO: renamed from: component25, reason: from getter */
    public final VkIsWidescreenDto getVkIsWidescreen() {
        return this.vkIsWidescreen;
    }

    @Nullable
    /* JADX INFO: renamed from: component26, reason: from getter */
    public final Integer getVkRequestId() {
        return this.vkRequestId;
    }

    @Nullable
    /* JADX INFO: renamed from: component27, reason: from getter */
    public final String getVkRequestKey() {
        return this.vkRequestKey;
    }

    @Nullable
    /* JADX INFO: renamed from: component28, reason: from getter */
    public final VkIsPlayMachineDto getVkIsPlayMachine() {
        return this.vkIsPlayMachine;
    }

    @Nullable
    /* JADX INFO: renamed from: component29, reason: from getter */
    public final String getAppHash() {
        return this.appHash;
    }

    @Nullable
    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VkAreNotificationsEnabledDto getVkAreNotificationsEnabled() {
        return this.vkAreNotificationsEnabled;
    }

    @Nullable
    /* JADX INFO: renamed from: component30, reason: from getter */
    public final Boolean getVkIsUnauth() {
        return this.vkIsUnauth;
    }

    @Nullable
    /* JADX INFO: renamed from: component31, reason: from getter */
    public final String getVkOkUserId() {
        return this.vkOkUserId;
    }

    @Nullable
    /* JADX INFO: renamed from: component4, reason: from getter */
    public final Integer getVkIsAppUser() {
        return this.vkIsAppUser;
    }

    @Nullable
    /* JADX INFO: renamed from: component5, reason: from getter */
    public final Integer getVkIsFavorite() {
        return this.vkIsFavorite;
    }

    @Nullable
    /* JADX INFO: renamed from: component6, reason: from getter */
    public final String getVkLanguage() {
        return this.vkLanguage;
    }

    @Nullable
    /* JADX INFO: renamed from: component7, reason: from getter */
    public final String getVkPlatform() {
        return this.vkPlatform;
    }

    @Nullable
    /* JADX INFO: renamed from: component8, reason: from getter */
    public final String getVkRef() {
        return this.vkRef;
    }

    @Nullable
    /* JADX INFO: renamed from: component9, reason: from getter */
    public final Integer getVkTs() {
        return this.vkTs;
    }

    @NotNull
    public final AppsGetAppLaunchParamsResponseDto copy(@Nullable String vkAccessTokenSettings, @Nullable Integer vkAppId, @Nullable VkAreNotificationsEnabledDto vkAreNotificationsEnabled, @Nullable Integer vkIsAppUser, @Nullable Integer vkIsFavorite, @Nullable String vkLanguage, @Nullable String vkPlatform, @Nullable String vkRef, @Nullable Integer vkTs, @Nullable UserId vkUserId, @Nullable String sign, @Nullable String vkViewerGroupRole, @Nullable UserId vkGroupId, @Nullable String vkExperiment, @Nullable VkHasProfileButtonDto vkHasProfileButton, @Nullable Integer vkProfileId, @Nullable VkIsRecommendedDto vkIsRecommended, @Nullable VkIsEmployeeDto vkIsEmployee, @Nullable String vkMode, @Nullable Integer vkSeg, @Nullable VkH3Dto vkH3, @Nullable String vkClient, @Nullable String vkRestrictions, @Nullable Integer vkTestingGroupId, @Nullable VkIsWidescreenDto vkIsWidescreen, @Nullable Integer vkRequestId, @Nullable String vkRequestKey, @Nullable VkIsPlayMachineDto vkIsPlayMachine, @Nullable String appHash, @Nullable Boolean vkIsUnauth, @Nullable String vkOkUserId) {
        return new AppsGetAppLaunchParamsResponseDto(vkAccessTokenSettings, vkAppId, vkAreNotificationsEnabled, vkIsAppUser, vkIsFavorite, vkLanguage, vkPlatform, vkRef, vkTs, vkUserId, sign, vkViewerGroupRole, vkGroupId, vkExperiment, vkHasProfileButton, vkProfileId, vkIsRecommended, vkIsEmployee, vkMode, vkSeg, vkH3, vkClient, vkRestrictions, vkTestingGroupId, vkIsWidescreen, vkRequestId, vkRequestKey, vkIsPlayMachine, appHash, vkIsUnauth, vkOkUserId);
    }

    @Override // android.os.Parcelable
    public final int describeContents() {
        return 0;
    }

    public boolean equals(@Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof AppsGetAppLaunchParamsResponseDto)) {
            return false;
        }
        AppsGetAppLaunchParamsResponseDto appsGetAppLaunchParamsResponseDto = (AppsGetAppLaunchParamsResponseDto) other;
        return Intrinsics.areEqual(this.vkAccessTokenSettings, appsGetAppLaunchParamsResponseDto.vkAccessTokenSettings) && Intrinsics.areEqual(this.vkAppId, appsGetAppLaunchParamsResponseDto.vkAppId) && this.vkAreNotificationsEnabled == appsGetAppLaunchParamsResponseDto.vkAreNotificationsEnabled && Intrinsics.areEqual(this.vkIsAppUser, appsGetAppLaunchParamsResponseDto.vkIsAppUser) && Intrinsics.areEqual(this.vkIsFavorite, appsGetAppLaunchParamsResponseDto.vkIsFavorite) && Intrinsics.areEqual(this.vkLanguage, appsGetAppLaunchParamsResponseDto.vkLanguage) && Intrinsics.areEqual(this.vkPlatform, appsGetAppLaunchParamsResponseDto.vkPlatform) && Intrinsics.areEqual(this.vkRef, appsGetAppLaunchParamsResponseDto.vkRef) && Intrinsics.areEqual(this.vkTs, appsGetAppLaunchParamsResponseDto.vkTs) && Intrinsics.areEqual(this.vkUserId, appsGetAppLaunchParamsResponseDto.vkUserId) && Intrinsics.areEqual(this.sign, appsGetAppLaunchParamsResponseDto.sign) && Intrinsics.areEqual(this.vkViewerGroupRole, appsGetAppLaunchParamsResponseDto.vkViewerGroupRole) && Intrinsics.areEqual(this.vkGroupId, appsGetAppLaunchParamsResponseDto.vkGroupId) && Intrinsics.areEqual(this.vkExperiment, appsGetAppLaunchParamsResponseDto.vkExperiment) && this.vkHasProfileButton == appsGetAppLaunchParamsResponseDto.vkHasProfileButton && Intrinsics.areEqual(this.vkProfileId, appsGetAppLaunchParamsResponseDto.vkProfileId) && this.vkIsRecommended == appsGetAppLaunchParamsResponseDto.vkIsRecommended && this.vkIsEmployee == appsGetAppLaunchParamsResponseDto.vkIsEmployee && Intrinsics.areEqual(this.vkMode, appsGetAppLaunchParamsResponseDto.vkMode) && Intrinsics.areEqual(this.vkSeg, appsGetAppLaunchParamsResponseDto.vkSeg) && this.vkH3 == appsGetAppLaunchParamsResponseDto.vkH3 && Intrinsics.areEqual(this.vkClient, appsGetAppLaunchParamsResponseDto.vkClient) && Intrinsics.areEqual(this.vkRestrictions, appsGetAppLaunchParamsResponseDto.vkRestrictions) && Intrinsics.areEqual(this.vkTestingGroupId, appsGetAppLaunchParamsResponseDto.vkTestingGroupId) && this.vkIsWidescreen == appsGetAppLaunchParamsResponseDto.vkIsWidescreen && Intrinsics.areEqual(this.vkRequestId, appsGetAppLaunchParamsResponseDto.vkRequestId) && Intrinsics.areEqual(this.vkRequestKey, appsGetAppLaunchParamsResponseDto.vkRequestKey) && this.vkIsPlayMachine == appsGetAppLaunchParamsResponseDto.vkIsPlayMachine && Intrinsics.areEqual(this.appHash, appsGetAppLaunchParamsResponseDto.appHash) && Intrinsics.areEqual(this.vkIsUnauth, appsGetAppLaunchParamsResponseDto.vkIsUnauth) && Intrinsics.areEqual(this.vkOkUserId, appsGetAppLaunchParamsResponseDto.vkOkUserId);
    }

    @Nullable
    public final String getAppHash() {
        return this.appHash;
    }

    @Nullable
    public final String getSign() {
        return this.sign;
    }

    @Nullable
    public final String getVkAccessTokenSettings() {
        return this.vkAccessTokenSettings;
    }

    @Nullable
    public final Integer getVkAppId() {
        return this.vkAppId;
    }

    @Nullable
    public final VkAreNotificationsEnabledDto getVkAreNotificationsEnabled() {
        return this.vkAreNotificationsEnabled;
    }

    @Nullable
    public final String getVkClient() {
        return this.vkClient;
    }

    @Nullable
    public final String getVkExperiment() {
        return this.vkExperiment;
    }

    @Nullable
    public final UserId getVkGroupId() {
        return this.vkGroupId;
    }

    @Nullable
    public final VkH3Dto getVkH3() {
        return this.vkH3;
    }

    @Nullable
    public final VkHasProfileButtonDto getVkHasProfileButton() {
        return this.vkHasProfileButton;
    }

    @Nullable
    public final Integer getVkIsAppUser() {
        return this.vkIsAppUser;
    }

    @Nullable
    public final VkIsEmployeeDto getVkIsEmployee() {
        return this.vkIsEmployee;
    }

    @Nullable
    public final Integer getVkIsFavorite() {
        return this.vkIsFavorite;
    }

    @Nullable
    public final VkIsPlayMachineDto getVkIsPlayMachine() {
        return this.vkIsPlayMachine;
    }

    @Nullable
    public final VkIsRecommendedDto getVkIsRecommended() {
        return this.vkIsRecommended;
    }

    @Nullable
    public final Boolean getVkIsUnauth() {
        return this.vkIsUnauth;
    }

    @Nullable
    public final VkIsWidescreenDto getVkIsWidescreen() {
        return this.vkIsWidescreen;
    }

    @Nullable
    public final String getVkLanguage() {
        return this.vkLanguage;
    }

    @Nullable
    public final String getVkMode() {
        return this.vkMode;
    }

    @Nullable
    public final String getVkOkUserId() {
        return this.vkOkUserId;
    }

    @Nullable
    public final String getVkPlatform() {
        return this.vkPlatform;
    }

    @Nullable
    public final Integer getVkProfileId() {
        return this.vkProfileId;
    }

    @Nullable
    public final String getVkRef() {
        return this.vkRef;
    }

    @Nullable
    public final Integer getVkRequestId() {
        return this.vkRequestId;
    }

    @Nullable
    public final String getVkRequestKey() {
        return this.vkRequestKey;
    }

    @Nullable
    public final String getVkRestrictions() {
        return this.vkRestrictions;
    }

    @Nullable
    public final Integer getVkSeg() {
        return this.vkSeg;
    }

    @Nullable
    public final Integer getVkTestingGroupId() {
        return this.vkTestingGroupId;
    }

    @Nullable
    public final Integer getVkTs() {
        return this.vkTs;
    }

    @Nullable
    public final UserId getVkUserId() {
        return this.vkUserId;
    }

    @Nullable
    public final String getVkViewerGroupRole() {
        return this.vkViewerGroupRole;
    }

    public int hashCode() {
        String str = this.vkAccessTokenSettings;
        int iHashCode = (str == null ? 0 : str.hashCode()) * 31;
        Integer num = this.vkAppId;
        int iHashCode2 = (iHashCode + (num == null ? 0 : num.hashCode())) * 31;
        VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto = this.vkAreNotificationsEnabled;
        int iHashCode3 = (iHashCode2 + (vkAreNotificationsEnabledDto == null ? 0 : vkAreNotificationsEnabledDto.hashCode())) * 31;
        Integer num2 = this.vkIsAppUser;
        int iHashCode4 = (iHashCode3 + (num2 == null ? 0 : num2.hashCode())) * 31;
        Integer num3 = this.vkIsFavorite;
        int iHashCode5 = (iHashCode4 + (num3 == null ? 0 : num3.hashCode())) * 31;
        String str2 = this.vkLanguage;
        int iHashCode6 = (iHashCode5 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.vkPlatform;
        int iHashCode7 = (iHashCode6 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.vkRef;
        int iHashCode8 = (iHashCode7 + (str4 == null ? 0 : str4.hashCode())) * 31;
        Integer num4 = this.vkTs;
        int iHashCode9 = (iHashCode8 + (num4 == null ? 0 : num4.hashCode())) * 31;
        UserId userId = this.vkUserId;
        int iHashCode10 = (iHashCode9 + (userId == null ? 0 : userId.hashCode())) * 31;
        String str5 = this.sign;
        int iHashCode11 = (iHashCode10 + (str5 == null ? 0 : str5.hashCode())) * 31;
        String str6 = this.vkViewerGroupRole;
        int iHashCode12 = (iHashCode11 + (str6 == null ? 0 : str6.hashCode())) * 31;
        UserId userId2 = this.vkGroupId;
        int iHashCode13 = (iHashCode12 + (userId2 == null ? 0 : userId2.hashCode())) * 31;
        String str7 = this.vkExperiment;
        int iHashCode14 = (iHashCode13 + (str7 == null ? 0 : str7.hashCode())) * 31;
        VkHasProfileButtonDto vkHasProfileButtonDto = this.vkHasProfileButton;
        int iHashCode15 = (iHashCode14 + (vkHasProfileButtonDto == null ? 0 : vkHasProfileButtonDto.hashCode())) * 31;
        Integer num5 = this.vkProfileId;
        int iHashCode16 = (iHashCode15 + (num5 == null ? 0 : num5.hashCode())) * 31;
        VkIsRecommendedDto vkIsRecommendedDto = this.vkIsRecommended;
        int iHashCode17 = (iHashCode16 + (vkIsRecommendedDto == null ? 0 : vkIsRecommendedDto.hashCode())) * 31;
        VkIsEmployeeDto vkIsEmployeeDto = this.vkIsEmployee;
        int iHashCode18 = (iHashCode17 + (vkIsEmployeeDto == null ? 0 : vkIsEmployeeDto.hashCode())) * 31;
        String str8 = this.vkMode;
        int iHashCode19 = (iHashCode18 + (str8 == null ? 0 : str8.hashCode())) * 31;
        Integer num6 = this.vkSeg;
        int iHashCode20 = (iHashCode19 + (num6 == null ? 0 : num6.hashCode())) * 31;
        VkH3Dto vkH3Dto = this.vkH3;
        int iHashCode21 = (iHashCode20 + (vkH3Dto == null ? 0 : vkH3Dto.hashCode())) * 31;
        String str9 = this.vkClient;
        int iHashCode22 = (iHashCode21 + (str9 == null ? 0 : str9.hashCode())) * 31;
        String str10 = this.vkRestrictions;
        int iHashCode23 = (iHashCode22 + (str10 == null ? 0 : str10.hashCode())) * 31;
        Integer num7 = this.vkTestingGroupId;
        int iHashCode24 = (iHashCode23 + (num7 == null ? 0 : num7.hashCode())) * 31;
        VkIsWidescreenDto vkIsWidescreenDto = this.vkIsWidescreen;
        int iHashCode25 = (iHashCode24 + (vkIsWidescreenDto == null ? 0 : vkIsWidescreenDto.hashCode())) * 31;
        Integer num8 = this.vkRequestId;
        int iHashCode26 = (iHashCode25 + (num8 == null ? 0 : num8.hashCode())) * 31;
        String str11 = this.vkRequestKey;
        int iHashCode27 = (iHashCode26 + (str11 == null ? 0 : str11.hashCode())) * 31;
        VkIsPlayMachineDto vkIsPlayMachineDto = this.vkIsPlayMachine;
        int iHashCode28 = (iHashCode27 + (vkIsPlayMachineDto == null ? 0 : vkIsPlayMachineDto.hashCode())) * 31;
        String str12 = this.appHash;
        int iHashCode29 = (iHashCode28 + (str12 == null ? 0 : str12.hashCode())) * 31;
        Boolean bool = this.vkIsUnauth;
        int iHashCode30 = (iHashCode29 + (bool == null ? 0 : bool.hashCode())) * 31;
        String str13 = this.vkOkUserId;
        return iHashCode30 + (str13 != null ? str13.hashCode() : 0);
    }

    @NotNull
    public String toString() {
        return "AppsGetAppLaunchParamsResponseDto(vkAccessTokenSettings=" + this.vkAccessTokenSettings + ", vkAppId=" + this.vkAppId + ", vkAreNotificationsEnabled=" + this.vkAreNotificationsEnabled + ", vkIsAppUser=" + this.vkIsAppUser + ", vkIsFavorite=" + this.vkIsFavorite + ", vkLanguage=" + this.vkLanguage + ", vkPlatform=" + this.vkPlatform + ", vkRef=" + this.vkRef + ", vkTs=" + this.vkTs + ", vkUserId=" + this.vkUserId + ", sign=" + this.sign + ", vkViewerGroupRole=" + this.vkViewerGroupRole + ", vkGroupId=" + this.vkGroupId + ", vkExperiment=" + this.vkExperiment + ", vkHasProfileButton=" + this.vkHasProfileButton + ", vkProfileId=" + this.vkProfileId + ", vkIsRecommended=" + this.vkIsRecommended + ", vkIsEmployee=" + this.vkIsEmployee + ", vkMode=" + this.vkMode + ", vkSeg=" + this.vkSeg + ", vkH3=" + this.vkH3 + ", vkClient=" + this.vkClient + ", vkRestrictions=" + this.vkRestrictions + ", vkTestingGroupId=" + this.vkTestingGroupId + ", vkIsWidescreen=" + this.vkIsWidescreen + ", vkRequestId=" + this.vkRequestId + ", vkRequestKey=" + this.vkRequestKey + ", vkIsPlayMachine=" + this.vkIsPlayMachine + ", appHash=" + this.appHash + ", vkIsUnauth=" + this.vkIsUnauth + ", vkOkUserId=" + this.vkOkUserId + ')';
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(@NotNull Parcel dest, int flags) {
        Intrinsics.checkNotNullParameter(dest, "dest");
        dest.writeString(this.vkAccessTokenSettings);
        Integer num = this.vkAppId;
        if (num == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num);
        }
        VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto = this.vkAreNotificationsEnabled;
        if (vkAreNotificationsEnabledDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkAreNotificationsEnabledDto.writeToParcel(dest, flags);
        }
        Integer num2 = this.vkIsAppUser;
        if (num2 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num2);
        }
        Integer num3 = this.vkIsFavorite;
        if (num3 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num3);
        }
        dest.writeString(this.vkLanguage);
        dest.writeString(this.vkPlatform);
        dest.writeString(this.vkRef);
        Integer num4 = this.vkTs;
        if (num4 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num4);
        }
        dest.writeParcelable(this.vkUserId, flags);
        dest.writeString(this.sign);
        dest.writeString(this.vkViewerGroupRole);
        dest.writeParcelable(this.vkGroupId, flags);
        dest.writeString(this.vkExperiment);
        VkHasProfileButtonDto vkHasProfileButtonDto = this.vkHasProfileButton;
        if (vkHasProfileButtonDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkHasProfileButtonDto.writeToParcel(dest, flags);
        }
        Integer num5 = this.vkProfileId;
        if (num5 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num5);
        }
        VkIsRecommendedDto vkIsRecommendedDto = this.vkIsRecommended;
        if (vkIsRecommendedDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkIsRecommendedDto.writeToParcel(dest, flags);
        }
        VkIsEmployeeDto vkIsEmployeeDto = this.vkIsEmployee;
        if (vkIsEmployeeDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkIsEmployeeDto.writeToParcel(dest, flags);
        }
        dest.writeString(this.vkMode);
        Integer num6 = this.vkSeg;
        if (num6 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num6);
        }
        VkH3Dto vkH3Dto = this.vkH3;
        if (vkH3Dto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkH3Dto.writeToParcel(dest, flags);
        }
        dest.writeString(this.vkClient);
        dest.writeString(this.vkRestrictions);
        Integer num7 = this.vkTestingGroupId;
        if (num7 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num7);
        }
        VkIsWidescreenDto vkIsWidescreenDto = this.vkIsWidescreen;
        if (vkIsWidescreenDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkIsWidescreenDto.writeToParcel(dest, flags);
        }
        Integer num8 = this.vkRequestId;
        if (num8 == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocb.detarenegipakvmoca(dest, 1, num8);
        }
        dest.writeString(this.vkRequestKey);
        VkIsPlayMachineDto vkIsPlayMachineDto = this.vkIsPlayMachine;
        if (vkIsPlayMachineDto == null) {
            dest.writeInt(0);
        } else {
            dest.writeInt(1);
            vkIsPlayMachineDto.writeToParcel(dest, flags);
        }
        dest.writeString(this.appHash);
        Boolean bool = this.vkIsUnauth;
        if (bool == null) {
            dest.writeInt(0);
        } else {
            detarenegipakvmocj.detarenegipakvmoca(dest, 1, bool);
        }
        dest.writeString(this.vkOkUserId);
    }

    public AppsGetAppLaunchParamsResponseDto(@Nullable String str, @Nullable Integer num, @Nullable VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto, @Nullable Integer num2, @Nullable Integer num3, @Nullable String str2, @Nullable String str3, @Nullable String str4, @Nullable Integer num4, @Nullable UserId userId, @Nullable String str5, @Nullable String str6, @Nullable UserId userId2, @Nullable String str7, @Nullable VkHasProfileButtonDto vkHasProfileButtonDto, @Nullable Integer num5, @Nullable VkIsRecommendedDto vkIsRecommendedDto, @Nullable VkIsEmployeeDto vkIsEmployeeDto, @Nullable String str8, @Nullable Integer num6, @Nullable VkH3Dto vkH3Dto, @Nullable String str9, @Nullable String str10, @Nullable Integer num7, @Nullable VkIsWidescreenDto vkIsWidescreenDto, @Nullable Integer num8, @Nullable String str11, @Nullable VkIsPlayMachineDto vkIsPlayMachineDto, @Nullable String str12, @Nullable Boolean bool, @Nullable String str13) {
        this.vkAccessTokenSettings = str;
        this.vkAppId = num;
        this.vkAreNotificationsEnabled = vkAreNotificationsEnabledDto;
        this.vkIsAppUser = num2;
        this.vkIsFavorite = num3;
        this.vkLanguage = str2;
        this.vkPlatform = str3;
        this.vkRef = str4;
        this.vkTs = num4;
        this.vkUserId = userId;
        this.sign = str5;
        this.vkViewerGroupRole = str6;
        this.vkGroupId = userId2;
        this.vkExperiment = str7;
        this.vkHasProfileButton = vkHasProfileButtonDto;
        this.vkProfileId = num5;
        this.vkIsRecommended = vkIsRecommendedDto;
        this.vkIsEmployee = vkIsEmployeeDto;
        this.vkMode = str8;
        this.vkSeg = num6;
        this.vkH3 = vkH3Dto;
        this.vkClient = str9;
        this.vkRestrictions = str10;
        this.vkTestingGroupId = num7;
        this.vkIsWidescreen = vkIsWidescreenDto;
        this.vkRequestId = num8;
        this.vkRequestKey = str11;
        this.vkIsPlayMachine = vkIsPlayMachineDto;
        this.appHash = str12;
        this.vkIsUnauth = bool;
        this.vkOkUserId = str13;
    }

    public /* synthetic */ AppsGetAppLaunchParamsResponseDto(String str, Integer num, VkAreNotificationsEnabledDto vkAreNotificationsEnabledDto, Integer num2, Integer num3, String str2, String str3, String str4, Integer num4, UserId userId, String str5, String str6, UserId userId2, String str7, VkHasProfileButtonDto vkHasProfileButtonDto, Integer num5, VkIsRecommendedDto vkIsRecommendedDto, VkIsEmployeeDto vkIsEmployeeDto, String str8, Integer num6, VkH3Dto vkH3Dto, String str9, String str10, Integer num7, VkIsWidescreenDto vkIsWidescreenDto, Integer num8, String str11, VkIsPlayMachineDto vkIsPlayMachineDto, String str12, Boolean bool, String str13, int i10, DefaultConstructorMarker defaultConstructorMarker) {
        this((i10 & 1) != 0 ? null : str, (i10 & 2) != 0 ? null : num, (i10 & 4) != 0 ? null : vkAreNotificationsEnabledDto, (i10 & 8) != 0 ? null : num2, (i10 & 16) != 0 ? null : num3, (i10 & 32) != 0 ? null : str2, (i10 & 64) != 0 ? null : str3, (i10 & 128) != 0 ? null : str4, (i10 & 256) != 0 ? null : num4, (i10 & 512) != 0 ? null : userId, (i10 & 1024) != 0 ? null : str5, (i10 & 2048) != 0 ? null : str6, (i10 & 4096) != 0 ? null : userId2, (i10 & 8192) != 0 ? null : str7, (i10 & 16384) != 0 ? null : vkHasProfileButtonDto, (i10 & 32768) != 0 ? null : num5, (i10 & ArrayPool.STANDARD_BUFFER_SIZE_BYTES) != 0 ? null : vkIsRecommendedDto, (i10 & 131072) != 0 ? null : vkIsEmployeeDto, (i10 & MediaHttpUploader.MINIMUM_CHUNK_SIZE) != 0 ? null : str8, (i10 & 524288) != 0 ? null : num6, (i10 & 1048576) != 0 ? null : vkH3Dto, (i10 & 2097152) != 0 ? null : str9, (i10 & 4194304) != 0 ? null : str10, (i10 & RemoteFilesRepository.BYTE_ARRAY_SIZE) != 0 ? null : num7, (i10 & Http2Connection.OKHTTP_CLIENT_WINDOW_SIZE) != 0 ? null : vkIsWidescreenDto, (i10 & MediaHttpDownloader.MAXIMUM_CHUNK_SIZE) != 0 ? null : num8, (i10 & 67108864) != 0 ? null : str11, (i10 & 134217728) != 0 ? null : vkIsPlayMachineDto, (i10 & SQLiteDatabase.CREATE_IF_NECESSARY) != 0 ? null : str12, (i10 & SQLiteDatabase.ENABLE_WRITE_AHEAD_LOGGING) != 0 ? null : bool, (i10 & 1073741824) != 0 ? null : str13);
    }
}
