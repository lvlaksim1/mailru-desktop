package com.vk.auth.captcha.impl.sound;

import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import com.vk.auth.captcha.impl.base.BaseCaptchaPresenter;
import com.vk.auth.captcha.impl.base.CaptchaStatus;
import com.vk.auth.captcha.impl.utils.SoundCaptchaUtilsKt;
import com.vk.auth.captcha.impl.utils.UriUtilsKt;
import com.vk.core.extensions.UriExtKt;
import com.vk.superapp.core.utils.VKCLogger;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import java.io.FileDescriptor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u00012\u00020\u0002B!\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\r\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\nH\u0016¢\u0006\u0004\b\r\u0010\u000eJ\u000f\u0010\u000f\u001a\u00020\fH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u000f\u0010\u0011\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0011\u0010\u0010J\u000f\u0010\u0012\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0012\u0010\u0010J\u000f\u0010\u0013\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0013\u0010\u0010J\u000f\u0010\u0014\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0014\u0010\u0010¨\u0006\u0015"}, d2 = {"Lcom/vk/auth/captcha/impl/sound/SoundCaptchaPresenter;", "Lcom/vk/auth/captcha/impl/base/BaseCaptchaPresenter;", "Lcom/vk/auth/captcha/impl/sound/SoundCaptchaContract$Presenter;", "Landroid/media/AudioManager;", "audioManager", "", "soundCaptchaUri", "token", "<init>", "(Landroid/media/AudioManager;Ljava/lang/String;Ljava/lang/String;)V", "", "addSwapType", "", "activate", "(Z)V", "deactivate", "()V", "refresh", "retry", "play", "pause", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SoundCaptchaPresenter extends BaseCaptchaPresenter implements SoundCaptchaContract.Presenter {

    @NotNull
    private final AudioManager lpmiahctpackvmoce;

    @NotNull
    private final String lpmiahctpackvmocf;

    @Nullable
    private final String lpmiahctpackvmocg;

    @Nullable
    private Disposable lpmiahctpackvmoch;

    @NotNull
    private final MediaPlayer lpmiahctpackvmoci;

    @Nullable
    private final AudioFocusRequest lpmiahctpackvmocj;

    @NotNull
    private final AudioManager.OnAudioFocusChangeListener lpmiahctpackvmock;

    /* JADX INFO: compiled from: ProGuard */
    static final /* synthetic */ class lpmiahctpackvmoca extends FunctionReferenceImpl implements Function1<Throwable, Unit> {
        lpmiahctpackvmoca(SoundCaptchaPresenter soundCaptchaPresenter) {
            super(1, soundCaptchaPresenter, SoundCaptchaPresenter.class, "loadingError", "loadingError(Ljava/lang/Throwable;)V", 0);
        }

        @Override // kotlin.jvm.functions.Function1
        public final Unit invoke(Throwable th2) {
            Throwable p10 = th2;
            Intrinsics.checkNotNullParameter(p10, "p0");
            SoundCaptchaPresenter.access$loadingError((SoundCaptchaPresenter) this.receiver, p10);
            return Unit.INSTANCE;
        }
    }

    public SoundCaptchaPresenter(@NotNull AudioManager audioManager, @NotNull String soundCaptchaUri, @Nullable String str) {
        Intrinsics.checkNotNullParameter(audioManager, "audioManager");
        Intrinsics.checkNotNullParameter(soundCaptchaUri, "soundCaptchaUri");
        this.lpmiahctpackvmoce = audioManager;
        this.lpmiahctpackvmocf = soundCaptchaUri;
        this.lpmiahctpackvmocg = str;
        MediaPlayer mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(new AudioAttributes.Builder().setContentType(1).setUsage(1).build());
        mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener() { // from class: com.vk.auth.captcha.impl.sound.a
            @Override // android.media.MediaPlayer.OnErrorListener
            public final boolean onError(MediaPlayer mediaPlayer2, int i10, int i11) {
                return SoundCaptchaPresenter.lpmiahctpackvmoca(this.f40205a, mediaPlayer2, i10, i11);
            }
        });
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.vk.auth.captcha.impl.sound.b
            @Override // android.media.MediaPlayer.OnPreparedListener
            public final void onPrepared(MediaPlayer mediaPlayer2) {
                SoundCaptchaPresenter.lpmiahctpackvmoca(this.f40206a, mediaPlayer2);
            }
        });
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() { // from class: com.vk.auth.captcha.impl.sound.c
            @Override // android.media.MediaPlayer.OnCompletionListener
            public final void onCompletion(MediaPlayer mediaPlayer2) {
                SoundCaptchaPresenter.lpmiahctpackvmocb(this.f40207a, mediaPlayer2);
            }
        });
        this.lpmiahctpackvmoci = mediaPlayer;
        this.lpmiahctpackvmocj = new AudioFocusRequest.Builder(3).build();
        this.lpmiahctpackvmock = new AudioManager.OnAudioFocusChangeListener() { // from class: com.vk.auth.captcha.impl.sound.d
            @Override // android.media.AudioManager.OnAudioFocusChangeListener
            public final void onAudioFocusChange(int i10) {
                SoundCaptchaPresenter.lpmiahctpackvmoca(this.f40208a, i10);
            }
        };
    }

    public static final void access$loadingError(SoundCaptchaPresenter soundCaptchaPresenter, Throwable th2) {
        soundCaptchaPresenter.getClass();
        VKCLogger.INSTANCE.e(th2);
        soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.LoadingError(soundCaptchaPresenter.getRefreshCountdown()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean lpmiahctpackvmoca(SoundCaptchaPresenter soundCaptchaPresenter, MediaPlayer mediaPlayer, int i10, int i11) {
        soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.LoadingError(soundCaptchaPresenter.getRefreshCountdown()));
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocb(SoundCaptchaPresenter soundCaptchaPresenter, MediaPlayer mediaPlayer) {
        soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.Ready(false, soundCaptchaPresenter.getRefreshCountdown()));
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void activate(boolean addSwapType) {
        Uri.Builder builderBuildUpon = UriExtKt.toUri(this.lpmiahctpackvmocf).buildUpon();
        if (addSwapType) {
            Intrinsics.checkNotNull(builderBuildUpon);
            UriUtilsKt.querySwapType(builderBuildUpon);
        } else {
            Intrinsics.checkNotNull(builderBuildUpon);
            UriUtilsKt.queryFirst(builderBuildUpon);
        }
        Uri uriBuild = builderBuildUpon.build();
        Intrinsics.checkNotNullExpressionValue(uriBuild, "build(...)");
        lpmiahctpackvmoca(uriBuild, addSwapType);
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void deactivate() {
        this.lpmiahctpackvmoci.stop();
        this.lpmiahctpackvmoci.reset();
        Disposable disposable = this.lpmiahctpackvmoch;
        if (disposable != null) {
            disposable.dispose();
        }
        getRefreshTimer().cancel();
        AudioFocusRequest audioFocusRequest = this.lpmiahctpackvmocj;
        if (audioFocusRequest != null) {
            this.lpmiahctpackvmoce.abandonAudioFocusRequest(audioFocusRequest);
        }
        setCaptchaStatus(new CaptchaStatus.Inactive(getRefreshCountdown()));
    }

    @Override // com.vk.auth.captcha.impl.sound.SoundCaptchaContract.Presenter
    public void pause() {
        if (this.lpmiahctpackvmoci.isPlaying()) {
            this.lpmiahctpackvmoci.pause();
            this.lpmiahctpackvmoci.seekTo(0);
            setCaptchaStatus(new CaptchaStatus.Ready(false, getRefreshCountdown()));
        }
    }

    @Override // com.vk.auth.captcha.impl.sound.SoundCaptchaContract.Presenter
    public void play() {
        AudioFocusRequest audioFocusRequest = this.lpmiahctpackvmocj;
        if (audioFocusRequest != null) {
            this.lpmiahctpackvmoce.requestAudioFocus(audioFocusRequest);
        }
        setCaptchaStatus(new CaptchaStatus.Ready(true, getRefreshCountdown()));
        this.lpmiahctpackvmoci.start();
    }

    @Override // com.vk.auth.captcha.impl.base.CaptchaContract.Presenter
    public void refresh() {
        Uri.Builder builderBuildUpon = UriExtKt.toUri(this.lpmiahctpackvmocf).buildUpon();
        Intrinsics.checkNotNullExpressionValue(builderBuildUpon, "buildUpon(...)");
        Uri uriBuild = UriUtilsKt.queryRefresh(builderBuildUpon).build();
        Intrinsics.checkNotNull(uriBuild);
        lpmiahctpackvmoca(uriBuild, true);
    }

    @Override // com.vk.auth.captcha.impl.sound.SoundCaptchaContract.Presenter
    public void retry() {
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SoundCaptchaPresenter soundCaptchaPresenter, MediaPlayer mediaPlayer) {
        soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.Ready(false, soundCaptchaPresenter.getRefreshCountdown()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmocb(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(SoundCaptchaPresenter soundCaptchaPresenter, int i10) {
        if (i10 == -3 || i10 == -2 || i10 == -1) {
            soundCaptchaPresenter.lpmiahctpackvmoci.pause();
            soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.Ready(false, soundCaptchaPresenter.getRefreshCountdown()));
        } else {
            if (i10 != 1) {
                return;
            }
            soundCaptchaPresenter.lpmiahctpackvmoci.setVolume(1.0f, 1.0f);
        }
    }

    private final void lpmiahctpackvmoca(Uri uri, boolean z10) {
        setCaptchaStatus(new CaptchaStatus.Loading(getRefreshCountdown()));
        this.lpmiahctpackvmoci.reset();
        Disposable disposable = this.lpmiahctpackvmoch;
        if (disposable != null) {
            disposable.dispose();
        }
        String string = uri.toString();
        Intrinsics.checkNotNullExpressionValue(string, "toString(...)");
        Observable<FileDescriptor> observableLoadCaptchaFromUrlWithToken = SoundCaptchaUtilsKt.loadCaptchaFromUrlWithToken(string, this.lpmiahctpackvmocg);
        final Function1 function1 = new Function1() { // from class: com.vk.auth.captcha.impl.sound.e
            @Override // kotlin.jvm.functions.Function1
            public final Object invoke(Object obj) {
                return SoundCaptchaPresenter.lpmiahctpackvmoca(this.f40209a, (FileDescriptor) obj);
            }
        };
        Consumer<? super FileDescriptor> consumer = new Consumer() { // from class: com.vk.auth.captcha.impl.sound.f
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SoundCaptchaPresenter.lpmiahctpackvmoca(function1, obj);
            }
        };
        final lpmiahctpackvmoca lpmiahctpackvmocaVar = new lpmiahctpackvmoca(this);
        this.lpmiahctpackvmoch = observableLoadCaptchaFromUrlWithToken.subscribe(consumer, new Consumer() { // from class: com.vk.auth.captcha.impl.sound.g
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                SoundCaptchaPresenter.lpmiahctpackvmocb(lpmiahctpackvmocaVar, obj);
            }
        });
        if (z10) {
            getRefreshTimer().start();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void lpmiahctpackvmoca(Function1 function1, Object obj) {
        function1.invoke(obj);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit lpmiahctpackvmoca(SoundCaptchaPresenter soundCaptchaPresenter, FileDescriptor fileDescriptor) {
        try {
            soundCaptchaPresenter.lpmiahctpackvmoci.setDataSource(fileDescriptor);
            soundCaptchaPresenter.lpmiahctpackvmoci.prepareAsync();
        } catch (Exception e10) {
            soundCaptchaPresenter.getClass();
            VKCLogger.INSTANCE.e(e10);
            soundCaptchaPresenter.setCaptchaStatus(new CaptchaStatus.LoadingError(soundCaptchaPresenter.getRefreshCountdown()));
        }
        return Unit.INSTANCE;
    }
}
