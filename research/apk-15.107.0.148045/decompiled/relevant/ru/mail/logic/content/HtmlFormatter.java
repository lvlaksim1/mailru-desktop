package ru.mail.logic.content;

import android.content.Context;
import android.net.Uri;
import android.text.BidiFormatter;
import android.text.Html;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import com.bumptech.glide.load.engine.bitmap_recycle.ArrayPool;
import com.fasterxml.jackson.core.base.GeneratorBase;
import com.google.i18n.phonenumbers.PhoneNumberMatch;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.vk.auth.enterphone.choosecountry.Country;
import com.vk.superapp.api.dto.geo.GeoServicesConstants;
import com.vk.superapp.api.dto.story.WebStoryAttachment;
import github.ankushsachdeva.emojicon.StickersReplacer;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.jvm.internal.CharCompanionObject;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.parser.Tag;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import ru.mail.analytics.MailAppAnalytics;
import ru.mail.analytics.MailAppDependencies;
import ru.mail.android_utils.WebViewUtils;
import ru.mail.calendar.presentation.navigation.CalendarFeature;
import ru.mail.config.ConfigRetriever;
import ru.mail.config.Configuration;
import ru.mail.config.ConfigurationRepository;
import ru.mail.config.ConfigurationWithRawData;
import ru.mail.config.section.MessageContentUrlReplacementRule;
import ru.mail.core.di.AppCoreModuleEntryPoint;
import ru.mail.data.cmd.imap.Formatter;
import ru.mail.data.entities.Attach;
import ru.mail.data.entities.MailThreadRepresentation;
import ru.mail.logic.navigation.segue.ClickerLinkConstructor;
import ru.mail.mailapp.DTOConfiguration;
import ru.mail.mlkit.api.MLKitProvider;
import ru.mail.portal.app.adapter.di.Portal;
import ru.mail.r7editor.impl.presentation.webview.R7WebViewConfigInjector;
import ru.mail.ui.fragments.mailbox.newmail.HtmlBodyFactory;
import ru.mail.ui.fragments.mailbox.newmail.MessageContentEntity;
import ru.mail.util.log.Formats;
import ru.mail.util.log.Log;
import ru.mail.util.log.LogFilter;
import ru.mail.util.url.MessageContentUrlResolver;
import ru.mail.util.url.MessageContentUrlResolverRegistry;
import ru.mail.util.url.UrlReplacementRule;
import ru.mail.util.url.UrlRewriter;
import ru.mail.utils.StringEscapeUtils;
import ru.mail.utils.feature.Features;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes15.dex */
public class HtmlFormatter {
    public static final String APPEND_QUERY_PARAMS_WITH_RULE_PARAM = "mail-app-append-query-params-with-rule";
    private static final String ATTR_BACKGROUND = "background";
    private static final String BLOCKQUOTE = "blockquote";
    private static final String BLOCKQUOTE_ID = "mail-app-auto-quote";
    private static final String BODY_CLOSE = "</body>";
    private static final String BODY_OPEN = "<body>";
    public static final String COLLAPSE_ONLY_MODE = "collapse_only";
    private static final String DATA_ATTR_GEO_ANALYTICS_NAME = "data-map-type";
    private static final String DATA_ATTR_LATITUDE = "data-map-latitude";
    private static final String DATA_ATTR_LONGITUDE = "data-map-longitude";
    public static final String DATA_SIG_REPLACED_ATTR = "mail-app-replaced-attr-sig";
    public static final int DECODING_WEBVIEW_VERSION = 65;
    private static final String DOM_PURIFY = "<script> document.write(HtmlParser.transform('%s', {mode: '%2s', styleConfig: false , dompurifyConfig: { ALLOWED_TAGS: ['a', 'area', 'b', 'blockquote', 'br', 'button', 'center', 'colgroup', 'col', 'dd', 'del', 'div', 'dl', 'dt', 'em', 'fieldset', 'font', 'form', 'h1', 'h2', 'h3', 'h4', 'h5', 'h6', 'hr', 'i', 'img', 'input', 'ins', 'legend', 'li', 'map', 'mark', 'ol', 'option', 'p', 'pre', 's', 'select', 'small', 'span', 'strike', 'strong', 'sub', 'sup', 'table', 'tbody', 'td', 'textarea', 'th', 'tr', 'tt', 'u', 'ul', 'style'], ALLOW_UNKNOWN_PROTOCOLS: true, ADD_ATTR: ['%3$s', '%4$s', 'data-src']}}).parsedHtml)</script>";
    public static final int FETCH_PRIORITY_WEBVIEW_VERSION = 102;
    public static final String GEO_ANALYTICS_NAME_SUFFIX = "type=";
    public static final String GEO_URL_PREFIX = "geo:";
    private static final String INLINE_IMAGE_URL_PATH = "/cgi-bin/readmsg";
    private static final int MAX_BLOCKQUOTE_NESTING_LEVEL = 3;
    public static final String ORIGINAL_URL_PARAMETER = "original-url";
    private static final String POSTFIX = "</span></span></body></html>";
    private static final String PREFIX = "<!DOCTYPE html><html id='root'><head><style type=\"text/css\">body {font-family:helvetica; font-size: 11.5pt;} img {display:inline;} pre {white-space: pre-wrap;} * {word-wrap: break-word;}</style><meta charset='utf-8' /><meta id='viewport' name='viewport' content='width=device-width, user-scalable=yes, minimum-scale=1, maximum-scale=10' /></head>";
    private static final String PRE_TAG = "pre";
    public static final String PURIFY_ONLY_MODE = "purify_only";
    private static final String SUBSCRIPTION_ID_CUSTOM = "mail-app-auto-signature";
    private static final String SUBSCRIPTION_ID_DEFAULT = "mail-app-auto-default-signature";
    private final ConfigRetriever mConfigRetriever;
    private final ConfigurationRepository mConfigurationRepository;
    private final Context mContext;
    private final FormatterDelegate mFormatterDelegate;
    private final MessageContentUrlResolverRegistry mMessageContentUrlResolverRegistry;
    private final FormatterParams mParams;
    private final String mTopEmailSection;
    private final DefaultSubscriptAppNameDelegate subscriptAppNameDelegate;
    private static final Log LOG = Log.getLog("HtmlFormatter");
    private static final Pattern PAIR_PART_PATTERN = Pattern.compile("&#(\\d+);");
    private static final LogFilter sLogFilter = new LogFilter(Formats.newUrlFormat("token"), Formats.newUrlFormat(ClickerLinkConstructor.AUTOGEN_TOKEN), Formats.newUrlFormat("refresh_token"), Formats.newUrlFormat("access_token"));
    private static final String TAG_A = "a";
    private static final String ATTR_HREF = "href";
    private static final String ATTR_FORMACTION = "formaction";
    private static final String ATTR_SRC = "src";
    private static final Map<String, List<String>> PRE_PROCESS_URL_ATTRS = b0.a(new Map.Entry[]{a0.a(TAG_A, androidx.camera.core.j.a(new Object[]{ATTR_HREF})), a0.a("area", androidx.camera.core.j.a(new Object[]{ATTR_HREF})), a0.a("link", androidx.camera.core.j.a(new Object[]{ATTR_HREF})), a0.a("form", androidx.camera.core.j.a(new Object[]{"action"})), a0.a("button", androidx.camera.core.j.a(new Object[]{ATTR_FORMACTION})), a0.a("input", androidx.camera.core.j.a(new Object[]{ATTR_FORMACTION, ATTR_SRC}))});
    private static final String IMG = "img";
    private static final Map<String, List<String>> POST_PROCESS_URL_ATTRS = b0.a(new Map.Entry[]{a0.a(IMG, androidx.camera.core.j.a(new Object[]{ATTR_SRC, "data-src"})), a0.a("source", androidx.camera.core.j.a(new Object[]{ATTR_SRC})), a0.a(WebStoryAttachment.WEB_ATTACHMENT_TYPE_AUDIO, androidx.camera.core.j.a(new Object[]{ATTR_SRC})), a0.a("video", androidx.camera.core.j.a(new Object[]{ATTR_SRC, "poster"})), a0.a("track", androidx.camera.core.j.a(new Object[]{ATTR_SRC})), a0.a("embed", androidx.camera.core.j.a(new Object[]{ATTR_SRC})), a0.a("iframe", androidx.camera.core.j.a(new Object[]{ATTR_SRC})), a0.a("object", androidx.camera.core.j.a(new Object[]{"data"})), a0.a("body", androidx.camera.core.j.a(new Object[]{"background"})), a0.a("table", androidx.camera.core.j.a(new Object[]{"background"})), a0.a("tr", androidx.camera.core.j.a(new Object[]{"background"})), a0.a("td", androidx.camera.core.j.a(new Object[]{"background"})), a0.a("th", androidx.camera.core.j.a(new Object[]{"background"}))});

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    public static class FormatResult {

        @NonNull
        private final String mFormattedHtml;
        private final boolean mHasImages;
        private final boolean mHasInlineAttaches;

        public FormatResult(@NonNull String str, boolean z10, boolean z11) {
            this.mFormattedHtml = str;
            this.mHasInlineAttaches = z10;
            this.mHasImages = z11;
        }

        @NonNull
        public String getFormattedHtml() {
            return this.mFormattedHtml;
        }

        public boolean hasImages() {
            return this.mHasImages;
        }

        public boolean hasInlineAttaches() {
            return this.mHasInlineAttaches;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    @VisibleForTesting(otherwise = 2)
    static class FormatterNodeVisitor implements NodeVisitor {
        private static final String BLANK_IMAGE = "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAQAAAC1HAwCAAAAC0lEQVR42mNgYAAAAAMAASsJTYQAAAAASUVORK5CYII=";
        private final List<Configuration.AppendingQueryParamsRule> mAppendingQueryParamsRules;
        private final int mImagesToLoadImmediately;
        private final boolean mIsFormatterHyphenEnabled;
        private final boolean mIsFormatterNbspEnabled;
        private final boolean mIsLazyInlineImagesEnabled;
        private final boolean mIsSequentialInlineImagesEnabled;
        private final List<Configuration.LinksReplacementRule> mLinksReplacementRules;
        private final MailAppAnalytics mMailAppAnalytics;

        @Nullable
        private final MessageContentUrlResolver mMessageContentUrlResolver;
        private boolean mUrlReplacementLogged;
        private final List<? extends UrlReplacementRule> mUrlReplacementRules;
        private final int mWebViewVersion;
        private final AtomicInteger mBlockQuoteLevel = new AtomicInteger(0);
        private final AtomicInteger mPreTagLevel = new AtomicInteger(0);
        private final AtomicInteger mInlineImagesCounter = new AtomicInteger(0);
        private final WordWrapFormatter mWrapFormatter = new WordWrapFormatter();

        FormatterNodeVisitor(ConfigurationRepository configurationRepository, ConfigRetriever configRetriever, MessageContentUrlResolverRegistry messageContentUrlResolverRegistry, MailAppAnalytics mailAppAnalytics) {
            ConfigurationWithRawData configuration = configurationRepository.getConfiguration();
            this.mLinksReplacementRules = configuration.getLinksReplacementRules();
            this.mAppendingQueryParamsRules = configuration.getAppendingQueryParamsRules();
            this.mUrlReplacementRules = MessageContentUrlReplacementRule.read(configRetriever);
            this.mMessageContentUrlResolver = messageContentUrlResolverRegistry.newSnapshot();
            this.mIsFormatterHyphenEnabled = configuration.isFormatterHyphenEnabled();
            this.mIsFormatterNbspEnabled = configuration.isFormatterNbspDisabled();
            this.mIsLazyInlineImagesEnabled = configuration.isMailViewInlineImagesLazyLoading();
            this.mIsSequentialInlineImagesEnabled = configuration.isMailViewInlineImagesSequentialLoading();
            this.mMailAppAnalytics = mailAppAnalytics;
            this.mImagesToLoadImmediately = configuration.getMailViewInlineImagesToLoadImmediate();
            this.mWebViewVersion = WebViewUtils.getWebViewVersionCode(-1);
        }

        private void addDecodingAttr(Node node) {
            if (this.mWebViewVersion >= 65) {
                node.attr("decoding", "async");
            }
        }

        private void addFetchPriorityAttr(Node node, String str) {
            if (this.mWebViewVersion >= 102) {
                node.attr("fetchPriority", str);
            }
        }

        private void applyReplacement(Node node, Configuration.LinksReplacementRule linksReplacementRule) {
            Uri.Builder builderBuildUpon = Uri.parse(linksReplacementRule.getUrl()).buildUpon();
            for (Map.Entry<String, String> entry : linksReplacementRule.getAttrsMapping().entrySet()) {
                builderBuildUpon.appendQueryParameter(entry.getValue(), node.attr(entry.getKey()));
            }
            String str = node.attributes().get(HtmlFormatter.ATTR_HREF);
            if (!str.isEmpty()) {
                try {
                    builderBuildUpon.appendQueryParameter(HtmlFormatter.ORIGINAL_URL_PARAMETER, URLEncoder.encode(str, R7WebViewConfigInjector.UTF_8));
                } catch (UnsupportedEncodingException e10) {
                    HtmlFormatter.LOG.e("Failed to encode original URL: " + HtmlFormatter.sLogFilter.filter(str), e10);
                }
            }
            node.attr(HtmlFormatter.ATTR_HREF, builderBuildUpon.build().toString());
            node.attr(HtmlFormatter.DATA_SIG_REPLACED_ATTR, "true");
        }

        private boolean hasAllAttributes(@NonNull Node node, @NonNull Set<String> set) {
            return hasAllAttributes(node, (String[]) set.toArray(new String[0]));
        }

        private void markURLRequiredAppendingParams(Node node, Configuration.AppendingQueryParamsRule appendingQueryParamsRule) {
            node.attr(HtmlFormatter.ATTR_HREF, Uri.parse(node.attr(appendingQueryParamsRule.getTargetUrlLocation())).buildUpon().appendQueryParameter(HtmlFormatter.APPEND_QUERY_PARAMS_WITH_RULE_PARAM, appendingQueryParamsRule.getName()).build().toString());
            this.mMailAppAnalytics.sendAppendingQueryParamsHandledAnalytics(appendingQueryParamsRule.getName());
        }

        private void performLinkReplacementIfNeeded(Node node) {
            for (Configuration.LinksReplacementRule linksReplacementRule : this.mLinksReplacementRules) {
                if (hasAllAttributes(node, linksReplacementRule.getAttrsMapping().keySet())) {
                    applyReplacement(node, linksReplacementRule);
                    this.mMailAppAnalytics.sendLinkHasBeenReplacedAnalytics(linksReplacementRule.getName());
                    Log log = HtmlFormatter.LOG;
                    StringBuilder sb2 = new StringBuilder();
                    sb2.append("Replacing URL has finished! Result: ");
                    sb2.append(HtmlFormatter.sLogFilter.filter("" + node));
                    log.d(sb2.toString());
                    return;
                }
            }
        }

        private void processBlockquote(Node node, int i10) {
            String strAttr = node.attr(GeoServicesConstants.STYLE);
            String str = "margin-right: 0px";
            if (!TextUtils.isEmpty(strAttr)) {
                str = strAttr + "margin-right: 0px";
            }
            node.attr(GeoServicesConstants.STYLE, str);
            if (i10 >= 3) {
                node.attr(GeoServicesConstants.STYLE, "margin: 0px; padding: 0px; border: 0px");
            }
        }

        private void processElement(@NonNull Element element) {
            String strNodeName = element.nodeName();
            if (strNodeName.equals(HtmlFormatter.BLOCKQUOTE)) {
                processBlockquote(element, this.mBlockQuoteLevel.getAndIncrement());
            } else if (strNodeName.equals(HtmlFormatter.IMG)) {
                processImg(element);
            } else if (strNodeName.equals(HtmlFormatter.TAG_A)) {
                processLink(element);
            }
        }

        private void processGeoLink(Node node) {
            if (hasAllAttributes(node, HtmlFormatter.DATA_ATTR_LATITUDE, HtmlFormatter.DATA_ATTR_LONGITUDE)) {
                String strAttr = node.attr(HtmlFormatter.DATA_ATTR_LATITUDE);
                String strAttr2 = node.attr(HtmlFormatter.DATA_ATTR_LONGITUDE);
                node.attr(HtmlFormatter.ATTR_HREF, String.format("%s%s,%s?q=%s,%s&%s%s", HtmlFormatter.GEO_URL_PREFIX, strAttr, strAttr2, strAttr, strAttr2, HtmlFormatter.GEO_ANALYTICS_NAME_SUFFIX, node.hasAttr(HtmlFormatter.DATA_ATTR_GEO_ANALYTICS_NAME) ? node.attr(HtmlFormatter.DATA_ATTR_GEO_ANALYTICS_NAME) : "default"));
            }
        }

        private void processImg(Node node) {
            String strAttr = node.attr(HtmlFormatter.ATTR_SRC);
            if (strAttr.contains(HtmlFormatter.INLINE_IMAGE_URL_PATH)) {
                int iIncrementAndGet = this.mInlineImagesCounter.incrementAndGet();
                if (this.mIsLazyInlineImagesEnabled) {
                    if (iIncrementAndGet <= this.mImagesToLoadImmediately) {
                        addFetchPriorityAttr(node, "high");
                    } else {
                        addFetchPriorityAttr(node, "low");
                        addDecodingAttr(node);
                    }
                }
                if (this.mIsSequentialInlineImagesEnabled) {
                    node.attr(HtmlFormatter.ATTR_SRC, BLANK_IMAGE);
                    node.attr("data-src", strAttr);
                }
            }
            if (node.hasAttr(StickersReplacer.getAttrOriginUrl())) {
                node.attr(HtmlFormatter.ATTR_SRC, node.attr(StickersReplacer.getAttrOriginUrl()));
            }
        }

        private void processLink(Node node) {
            performLinkReplacementIfNeeded(node);
            processQueryParams(node);
            processGeoLink(node);
        }

        private void processQueryParams(Node node) {
            for (Configuration.AppendingQueryParamsRule appendingQueryParamsRule : this.mAppendingQueryParamsRules) {
                if (node.hasAttr(appendingQueryParamsRule.getTargetUrlLocation())) {
                    if (appendingQueryParamsRule.getUrlValidationPattern().matcher(node.attr(appendingQueryParamsRule.getTargetUrlLocation())).matches() && hasAllAttributes(node, appendingQueryParamsRule.getRequiredAttributes())) {
                        markURLRequiredAppendingParams(node, appendingQueryParamsRule);
                        Log log = HtmlFormatter.LOG;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append("Rule ");
                        sb2.append(appendingQueryParamsRule.getName());
                        sb2.append(" has been applied to link ");
                        sb2.append(HtmlFormatter.sLogFilter.filter("" + node));
                        log.d(sb2.toString());
                        return;
                    }
                }
            }
        }

        private void processTextNode(TextNode textNode) {
            if (textNode.isBlank()) {
                return;
            }
            String wholeText = textNode.getWholeText();
            if (this.mIsFormatterNbspEnabled) {
                wholeText = wholeText.replaceAll(WordWrapFormatter.NBSP_STR, "\u200b ");
            }
            textNode.text(this.mWrapFormatter.format(wholeText, this.mIsFormatterHyphenEnabled));
        }

        private String resolveUrl(String str) {
            String strRewriteOrNull = UrlRewriter.rewriteOrNull(str, this.mUrlReplacementRules);
            if (strRewriteOrNull != null) {
                return strRewriteOrNull;
            }
            MessageContentUrlResolver messageContentUrlResolver = this.mMessageContentUrlResolver;
            return messageContentUrlResolver == null ? str : messageContentUrlResolver.resolve(str);
        }

        private void rewriteUrlAttrs(Node node, Map<String, List<String>> map) {
            List<String> list;
            if ((this.mUrlReplacementRules.isEmpty() && this.mMessageContentUrlResolver == null) || (list = map.get(node.nodeName())) == null) {
                return;
            }
            for (String str : list) {
                String strAttr = node.attr(str);
                if (!strAttr.isEmpty()) {
                    String strResolveUrl = resolveUrl(strAttr);
                    if (!strResolveUrl.equals(strAttr)) {
                        node.attr(str, strResolveUrl);
                        if (!this.mUrlReplacementLogged) {
                            this.mUrlReplacementLogged = true;
                            HtmlFormatter.LOG.d("Message content URL replacement applied");
                        }
                    }
                }
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i10) {
            boolean z10 = node instanceof Element;
            if (z10 && node.nodeName().equals(HtmlFormatter.PRE_TAG)) {
                this.mPreTagLevel.incrementAndGet();
            }
            if (z10) {
                rewriteUrlAttrs(node, HtmlFormatter.PRE_PROCESS_URL_ATTRS);
            }
            if (this.mPreTagLevel.get() == 0) {
                if (node instanceof TextNode) {
                    processTextNode((TextNode) node);
                } else if (z10) {
                    processElement((Element) node);
                }
            }
            if (z10) {
                rewriteUrlAttrs(node, HtmlFormatter.POST_PROCESS_URL_ATTRS);
            }
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i10) {
            if (node instanceof Element) {
                String strNodeName = node.nodeName();
                if (strNodeName.equals(HtmlFormatter.BLOCKQUOTE)) {
                    this.mBlockQuoteLevel.decrementAndGet();
                } else if (strNodeName.equals(HtmlFormatter.PRE_TAG)) {
                    this.mPreTagLevel.decrementAndGet();
                }
            }
        }

        private boolean hasAllAttributes(@NonNull Node node, @NonNull String... strArr) {
            for (String str : strArr) {
                if (!node.hasAttr(str)) {
                    return false;
                }
            }
            return true;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    public static class FormatterParams implements Serializable {
        private final int mContentHorizontalPadding;
        private final int mContentVerticalPadding;
        private final int mDeviceHeight;
        private final int mDeviceWidth;
        private final boolean mNeedParseQuotes;
        private final boolean mNeedTopEmailSection;
        private final boolean mShouldSanitizeHtml;

        public FormatterParams() {
            this(0, 0);
        }

        public int getContentHorizontalPadding() {
            return this.mContentHorizontalPadding;
        }

        public int getContentVerticalPadding() {
            return this.mContentVerticalPadding;
        }

        public int getDeviceHeight() {
            return this.mDeviceHeight;
        }

        public int getDeviceWidth() {
            return this.mDeviceWidth;
        }

        public boolean isNeedParseQuotes() {
            return this.mNeedParseQuotes;
        }

        public boolean isNeedTopEmailSection() {
            return this.mNeedTopEmailSection;
        }

        public boolean isShouldSanitizeHtml() {
            return this.mShouldSanitizeHtml;
        }

        @NonNull
        public String toString() {
            return "FormatterParams{mDeviceWidth=" + this.mDeviceWidth + ", mDeviceHeight=" + this.mDeviceHeight + ", mContentHorizontalPadding=" + this.mContentHorizontalPadding + ", mContentVerticalPadding=" + this.mContentVerticalPadding + ", mShouldSanitizeHtml=" + this.mShouldSanitizeHtml + ", mNeedTopEmailSection=" + this.mNeedTopEmailSection + AbstractJsonLexerKt.END_OBJ;
        }

        public FormatterParams(int i10, int i11) {
            this(i10, i11, 0, 0);
        }

        public FormatterParams(int i10, int i11, int i12, int i13) {
            this.mDeviceWidth = i10;
            this.mDeviceHeight = i11;
            this.mContentHorizontalPadding = i12;
            this.mContentVerticalPadding = i13;
            this.mShouldSanitizeHtml = false;
            this.mNeedTopEmailSection = true;
            this.mNeedParseQuotes = true;
        }

        public FormatterParams(Context context) {
            this(context, true);
        }

        public FormatterParams(Context context, boolean z10) {
            this(context, false, z10);
        }

        public FormatterParams(Context context, boolean z10, boolean z11) {
            this(context, z10, z11, true);
        }

        public FormatterParams(Context context, boolean z10, boolean z11, boolean z12) {
            DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
            this.mDeviceWidth = (int) Math.ceil(displayMetrics.widthPixels / displayMetrics.scaledDensity);
            this.mDeviceHeight = (int) Math.ceil(displayMetrics.heightPixels / displayMetrics.scaledDensity);
            this.mContentHorizontalPadding = (int) (context.getResources().getDimensionPixelSize(ru.mail.mails.R.dimen.mail_view_header_horizontal_padding) / displayMetrics.density);
            this.mContentVerticalPadding = (int) (context.getResources().getDimensionPixelSize(ru.mail.mails.R.dimen.mail_view_header_vertical_padding) / displayMetrics.density);
            this.mShouldSanitizeHtml = z10;
            this.mNeedTopEmailSection = z11;
            this.mNeedParseQuotes = z12;
        }

        public FormatterParams(int i10, int i11, int i12, int i13, boolean z10, boolean z11) {
            this.mDeviceWidth = i10;
            this.mDeviceHeight = i11;
            this.mContentHorizontalPadding = i12;
            this.mContentVerticalPadding = i13;
            this.mShouldSanitizeHtml = z10;
            this.mNeedTopEmailSection = z11;
            this.mNeedParseQuotes = true;
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    private static class PhoneNumberToLinkFormatter implements LinkFormatter {
        private TextNode processPhoneNumbers(TextNode textNode, Iterator<PhoneNumberMatch> it) {
            ArrayList<PhoneNumberMatch> arrayList = new ArrayList();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
            int i10 = 0;
            for (PhoneNumberMatch phoneNumberMatch : arrayList) {
                int iStart = phoneNumberMatch.start() - i10;
                int iEnd = phoneNumberMatch.end() - i10;
                TextNode textNodeSplitText = textNode.splitText(iStart);
                int i11 = iEnd - iStart;
                if (textNodeSplitText.text().length() != i11) {
                    textNode = textNodeSplitText.splitText(i11);
                }
                Phonenumber.PhoneNumber phoneNumberNumber = phoneNumberMatch.number();
                Element element = new Element(Tag.valueOf(HtmlFormatter.TAG_A), "");
                element.text(textNodeSplitText.text());
                element.attr(HtmlFormatter.ATTR_HREF, "tel:+" + phoneNumberNumber.getCountryCode() + phoneNumberNumber.getNationalNumber());
                textNodeSplitText.replaceWith(element);
                i10 += iEnd;
            }
            return textNode;
        }

        @Override // ru.mail.logic.content.LinkFormatter
        public int findAndFormat(TextNode textNode, Context context) {
            PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
            String strText = textNode.text();
            Iterator<PhoneNumberMatch> it = phoneNumberUtil.findNumbers(strText, Locale.getDefault().getCountry()).iterator();
            int i10 = 0;
            try {
                if (it.hasNext()) {
                    textNode = processPhoneNumbers(textNode, it);
                    strText = textNode.text();
                    i10 = 1;
                }
                Iterator<PhoneNumberMatch> it2 = phoneNumberUtil.findNumbers(strText, Country.RUSSIA_ISO_CODE).iterator();
                if (it2.hasNext()) {
                    textNode = processPhoneNumbers(textNode, it2);
                    strText = textNode.text();
                    i10++;
                }
                Iterator<PhoneNumberMatch> it3 = phoneNumberUtil.findNumbers(strText, null).iterator();
                if (!it3.hasNext()) {
                    return i10;
                }
                processPhoneNumbers(textNode, it3);
                return i10 + 1;
            } catch (NullPointerException e10) {
                HtmlFormatter.LOG.e("PhoneNumberToLinkFormatterError", e10);
                new SensitiveDataAssertSender(context).sendAssertion(e10, "PhoneNumberToLinkFormatterError", strText, "NPE com.google.i18n.phonenumbers.PhoneNumberMatcher");
                return 0;
            }
        }

        private PhoneNumberToLinkFormatter() {
        }
    }

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes10.dex */
    static class WordWrapFormatter {
        private static final int MAX_LENGTH = 20;
        private static final int MIN_SEPARATED_CHARS = 2;
        private static final char NBSP = 160;
        private static final String NBSP_STR = String.valueOf((char) 160);
        private static final char SOFT_HYPHEN = 173;
        private static final char ZERO_WIDTH_SPACE = 8203;
        private static final String ZERO_WIDTH_SPACE_WITH_SPACE = "\u200b ";

        WordWrapFormatter() {
        }

        private int charsFromEndToSpace(String str, int i10, int i11) {
            int i12 = 0;
            while (i10 < str.length() && i12 < i11) {
                char cCharAt = str.charAt(i10);
                if (Character.isWhitespace(cCharAt)) {
                    break;
                }
                if (cCharAt != 160) {
                    int i13 = i10 + 1;
                    if (isSurrogate(str, i10, i13)) {
                        i10 = i13;
                    }
                    i12++;
                }
                i10++;
            }
            return i12;
        }

        private int charsFromSpaceToEnd(String str, int i10, int i11) {
            int i12 = i10 - 1;
            int i13 = 0;
            while (i12 >= i10 - 20 && i13 < i11) {
                char cCharAt = str.charAt(i12);
                if (Character.isWhitespace(cCharAt)) {
                    break;
                }
                if (cCharAt != 160) {
                    if (isSurrogate(str, i12 - 1, i12)) {
                        i12--;
                    }
                    i13++;
                }
                i12--;
            }
            return i13;
        }

        private boolean isSurrogate(String str, int i10, int i11) {
            return i10 < str.length() && i11 < str.length() && Character.isSurrogatePair(str.charAt(i10), str.charAt(i11));
        }

        public String format(String str, boolean z10) {
            if (str.length() <= 20) {
                return str;
            }
            StringBuilder sb2 = new StringBuilder();
            int i10 = 0;
            while (i10 < str.length()) {
                int i11 = i10 + 20;
                if (i11 >= str.length()) {
                    sb2.append(str.substring(i10, str.length()));
                    break;
                }
                char cCharAt = str.charAt(i10 + 19);
                if (Character.isHighSurrogate(cCharAt)) {
                    i11 = i10 + 19;
                    cCharAt = str.charAt(i10 + 18);
                }
                if (!z10) {
                    sb2.append(str.substring(i10, i11));
                } else if (charsFromSpaceToEnd(str, i11, 2) < 2 || charsFromEndToSpace(str, i11, 2) < 2) {
                    sb2.append(str.substring(i10, i11));
                } else if (cCharAt == 160) {
                    sb2.append(str.substring(i10, i11 - 1));
                    sb2.append(StringUtils.SPACE);
                } else if (str.charAt(i11) == 160) {
                    sb2.append(str.substring(i10, i11));
                    sb2.append(StringUtils.SPACE);
                    i11++;
                } else {
                    sb2.append(str.substring(i10, i11));
                    sb2.append(SOFT_HYPHEN);
                }
                i10 = i11;
            }
            return sb2.toString();
        }
    }

    public HtmlFormatter(Context context, FormatterParams formatterParams) {
        this(context, formatterParams, "");
    }

    private String escapeHtml(String str) {
        StringBuilder sb2 = new StringBuilder(str);
        StickersReplacer.toBBFromHtml(sb2);
        String html = Html.toHtml(new SpannableString(sb2.toString()));
        sb2.setLength(0);
        sb2.append(html);
        StickersReplacer.toHtmlFromBB(sb2);
        return sb2.toString();
    }

    @NotNull
    private String escapeSurrogatePair(char c10, char c11) {
        return "&#" + (((c10 - GeneratorBase.SURR1_FIRST) << 10) | ArrayPool.STANDARD_BUFFER_SIZE_BYTES | (c11 - CharCompanionObject.MIN_LOW_SURROGATE)) + MailThreadRepresentation.PAYLOAD_DELIM_CHAR;
    }

    private Element formatChildren(Element element, @Nullable String str) {
        formatLinksInChildren(element);
        formatPhoneInChildren(element);
        formatDateInChildren(element, str);
        element.traverse((NodeVisitor) new FormatterNodeVisitor(this.mConfigurationRepository, this.mConfigRetriever, this.mMessageContentUrlResolverRegistry, MailAppDependencies.analytics(this.mContext)));
        return element;
    }

    private void formatDateInChildren(Node node, @Nullable String str) {
        DTOConfiguration.Config.MlKit mlKitConfig = this.mConfigurationRepository.getConfiguration().getMlKitConfig();
        if (mlKitConfig.getEnabled() && mlKitConfig.getMessageDateLinkWrap().getEnabled()) {
            MLKitProvider mLKitProvider = (MLKitProvider) Features.getProvider().provideOptional(MLKitProvider.class);
            CalendarFeature calendarFeature = (CalendarFeature) Portal.featureProvider().provide(CalendarFeature.class);
            if (mLKitProvider == null || calendarFeature == null) {
                return;
            }
            CalendarDeeplinkCreatorProvider lazyFactoryHolder = CalendarDeeplinkCreatorProvider.INSTANCE.getInstance();
            int withFormatter = this.mFormatterDelegate.formatWithFormatter(node, new DateToLinkFormatter(mLKitProvider.provideMLExtractor(this.mContext), lazyFactoryHolder != null ? lazyFactoryHolder.provide(this.mContext) : new CalendarDeeplinkCreatorStub(), str, mlKitConfig.getMessageDateLinkWrap(), mLKitProvider.provideMLKitTracker(this.mContext)), this.mContext);
            if (withFormatter > 0) {
                MailAppDependencies.analyticsKt(this.mContext).onFormattingMessageDate(withFormatter, str);
            }
        }
    }

    private void formatLinksInChildren(Node node) {
        this.mFormatterDelegate.formatWithFormatter(node, new LinkTextToLinkFormatter(), this.mContext);
    }

    private void formatPhoneInChildren(Node node) {
        this.mFormatterDelegate.formatWithFormatter(node, new PhoneNumberToLinkFormatter(), this.mContext);
    }

    public static boolean hasHTMLTags(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return Pattern.compile("<(\"[^\"]*\"|'[^']*'|[^'\">])*>").matcher(str).find();
    }

    private boolean hasImages(Elements elements) {
        return elements.size() > 0;
    }

    private boolean hasInlineAttaches(Elements elements) {
        return elements.select("[src~=/cgi-bin/readmsg]").size() > 0 || elements.select("[data-src~=/cgi-bin/readmsg]").size() > 0;
    }

    private boolean isFirstPartOfSurrogatePair(char c10) {
        return c10 >= 55296 && c10 < 56320;
    }

    private boolean isSecondPartOfSurrogatePair(char c10) {
        return c10 >= 56320 && c10 <= 57343;
    }

    private Character lookForFirstPartOfPair(Matcher matcher) {
        try {
            char c10 = (char) Integer.parseInt(matcher.group(1));
            if (isFirstPartOfSurrogatePair(c10)) {
                return Character.valueOf(c10);
            }
        } catch (NumberFormatException unused) {
        }
        return null;
    }

    private Character lookForwardForSecondPartOfPair(Matcher matcher, String str) {
        Matcher matcher2 = PAIR_PART_PATTERN.matcher(str.substring(matcher.end()));
        if (matcher2.find() && matcher2.start() == 0) {
            try {
                char c10 = (char) Integer.parseInt(matcher2.group(1));
                if (isSecondPartOfSurrogatePair(c10)) {
                    return Character.valueOf(c10);
                }
            } catch (NumberFormatException unused) {
            }
        }
        return null;
    }

    public static String removeSoftHyphens(String str) {
        return str.indexOf(173) != -1 ? str.replace(String.valueOf((char) 173), "") : str;
    }

    public static String replaceAttachesWithCidLinks(String str, List<Attach> list) {
        Document bodyFragment = Jsoup.parseBodyFragment(str);
        for (Attach attach : list) {
            Iterator<Element> it = bodyFragment.select(String.format("img[src~=%s(\\D|$)]", attach.getPartId())).iterator();
            while (it.hasNext()) {
                it.next().attr(ATTR_SRC, attach.getCid());
            }
        }
        return bodyFragment.body().html();
    }

    public static String replaceSrc(String str, String str2, Formatter formatter) {
        Document bodyFragment = Jsoup.parseBodyFragment(str);
        for (Element element : bodyFragment.select("img[src~=" + str2 + "]")) {
            element.attr(ATTR_SRC, formatter.format(element.attr(ATTR_SRC)));
        }
        return bodyFragment.body().html();
    }

    private void sanitizeDirty(StringBuilder sb2, int i10, int i11) {
        if (this.mConfigurationRepository.getConfiguration().isBackendQuotationParserDisabled() && this.mParams.isNeedParseQuotes()) {
            LOG.d("Sanitizing dirty body with quotation parser");
            sb2.replace(i10, i11, String.format(DOM_PURIFY, StringEscapeUtils.escapeString(sb2.substring(i10, i11)), COLLAPSE_ONLY_MODE, StickersReplacer.getAttrOriginUrl(), DATA_SIG_REPLACED_ATTR));
        } else {
            LOG.d("Sanitizing dirty body");
            sb2.replace(i10, i11, String.format(DOM_PURIFY, StringEscapeUtils.escapeString(sb2.substring(i10, i11)), PURIFY_ONLY_MODE, StickersReplacer.getAttrOriginUrl(), DATA_SIG_REPLACED_ATTR));
        }
    }

    private Elements selectImageElemenets(Document document) {
        return document.select(IMG);
    }

    @NonNull
    private String wrap(StringBuilder sb2) {
        String str = String.format("<body style='padding: %1$dpx %2$dpx!important; margin: 0px!important;height:auto!important;width:auto!important;'>", Integer.valueOf(this.mParams.getContentVerticalPadding()), Integer.valueOf(this.mParams.getContentHorizontalPadding())) + "<span style=\"display:block; overflow:hidden;\" id=\"mail-padding-wrapper\"><span id=\"mail-scale-wrapper\">";
        int iIndexOf = sb2.indexOf(BODY_OPEN);
        sb2.delete(iIndexOf, iIndexOf + 6);
        String str2 = PREFIX + str;
        sb2.insert(iIndexOf, str2);
        int length = iIndexOf + str2.length();
        if (this.mParams.isNeedTopEmailSection()) {
            sb2.insert(length, this.mTopEmailSection);
        }
        int iLastIndexOf = sb2.lastIndexOf(BODY_CLOSE);
        sb2.replace(iLastIndexOf, iLastIndexOf + 28, POSTFIX);
        if (this.mParams.isShouldSanitizeHtml()) {
            sanitizeDirty(sb2, length, iLastIndexOf);
        }
        return sb2.toString();
    }

    public String addBlockquote(String str, MessageContentEntity messageContentEntity) {
        String html = Html.toHtml(new SpannableString(messageContentEntity.getBodyPlain()));
        if (!str.contains(html)) {
            return str;
        }
        StringBuilder sb2 = new StringBuilder(str);
        int iLastIndexOf = str.lastIndexOf(html);
        HashMap map = new HashMap();
        map.put("cite", messageContentEntity.getId());
        sb2.replace(iLastIndexOf, html.length() + iLastIndexOf, wrapBlockquote(html, map));
        return sb2.toString();
    }

    public String deleteIndentInFirstLine(String str, Locale locale) {
        return str.replaceFirst("<p.*?>", "<p style='margin-top: 0px;' dir=" + (BidiFormatter.getInstance(locale).isRtlContext() ? "\"rtl\"" : "\"ltr\"") + SimpleComparison.GREATER_THAN_OPERATION);
    }

    public FormatResult format(String str, @Nullable String str2) {
        return format(Jsoup.parseBodyFragment(str), str2);
    }

    public String replaceEscapedSurrogateUtf16Pairs(String str) {
        Matcher matcher = PAIR_PART_PATTERN.matcher(str);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            Character chLookForFirstPartOfPair = lookForFirstPartOfPair(matcher);
            Character chLookForwardForSecondPartOfPair = lookForwardForSecondPartOfPair(matcher, str);
            if (chLookForFirstPartOfPair != null && chLookForwardForSecondPartOfPair != null) {
                matcher.appendReplacement(stringBuffer, escapeSurrogatePair(chLookForFirstPartOfPair.charValue(), chLookForwardForSecondPartOfPair.charValue()));
                matcher.find();
                matcher.appendReplacement(stringBuffer, "");
            }
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }

    public String toHtml(HtmlBodyFactory.BodyPlain bodyPlain) {
        return escapeHtml((String) bodyPlain.getData());
    }

    public String wrapBlockquote(String str, Map<String, String> map) {
        StringBuilder sb2 = new StringBuilder("<blockquote id=\"%s\"");
        if (map != null) {
            for (int i10 = 0; i10 < map.size(); i10++) {
                sb2.append(" %s");
            }
        }
        sb2.append(">%s</blockquote>");
        ArrayList arrayList = new ArrayList();
        arrayList.add(BLOCKQUOTE_ID);
        if (map != null) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                arrayList.add(entry.getKey() + "=\"" + entry.getValue() + "\"");
            }
        }
        arrayList.add(str);
        return String.format(sb2.toString(), arrayList.toArray());
    }

    public String wrapSubscription(String str, boolean z10) {
        Document document = Jsoup.parse(str);
        Element elementLast = document.select("p").last();
        if (elementLast == null) {
            return str;
        }
        elementLast.wrap(String.format("<div id=\"%s\"></div>", z10 ? SUBSCRIPTION_ID_DEFAULT : SUBSCRIPTION_ID_CUSTOM));
        if (z10) {
            String subscriptAppName = this.subscriptAppNameDelegate.getSubscriptAppName();
            elementLast.html(elementLast.html().replaceAll(subscriptAppName, "<a href=" + this.mContext.getString(ru.mail.mails.R.string.mail_link) + SimpleComparison.GREATER_THAN_OPERATION + subscriptAppName + "</a>"));
        }
        return document.body().html();
    }

    public HtmlFormatter(Context context, FormatterParams formatterParams, @NonNull String str) {
        this.mFormatterDelegate = new FormatterDelegate();
        this.mContext = context;
        this.mParams = formatterParams;
        this.mTopEmailSection = str;
        this.subscriptAppNameDelegate = new DefaultSubscriptAppNameDelegate(context);
        this.mConfigurationRepository = ConfigurationRepository.from(context);
        this.mConfigRetriever = AppCoreModuleEntryPoint.configRetriever(context);
        this.mMessageContentUrlResolverRegistry = AppCoreModuleEntryPoint.messageContentUrlResolverRegistry(context);
    }

    public FormatResult format(Document document, @Nullable String str) {
        document.outputSettings().prettyPrint(false);
        Element children = formatChildren(document.body(), str);
        Elements elementsSelectImageElemenets = selectImageElemenets(document);
        return new FormatResult(wrap(new StringBuilder(children.outerHtml())), hasInlineAttaches(elementsSelectImageElemenets), hasImages(elementsSelectImageElemenets));
    }
}
