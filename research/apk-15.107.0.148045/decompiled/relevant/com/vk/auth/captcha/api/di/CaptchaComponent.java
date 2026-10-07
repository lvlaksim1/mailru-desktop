package com.vk.auth.captcha.api.di;

import com.vk.auth.captcha.api.SakCaptchaHandler;
import com.vk.di.component.DiUnscopedComponent;
import com.vk.di.component.factory.DiComponentFactory;
import com.vk.di.component.factory.DiComponentProvider;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Lcom/vk/auth/captcha/api/di/CaptchaComponent;", "Lcom/vk/di/component/DiUnscopedComponent;", "sakCaptchaHandler", "Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "getSakCaptchaHandler", "()Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "Companion", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public interface CaptchaComponent extends DiUnscopedComponent {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    @NotNull
    public static final Companion INSTANCE = Companion.ipaahctpackvmoca;

    /* JADX INFO: compiled from: ProGuard */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/vk/auth/captcha/api/di/CaptchaComponent$Companion;", "", "<init>", "()V", "Lcom/vk/di/component/factory/DiComponentFactory;", "Lcom/vk/auth/captcha/api/di/CaptchaComponent;", "getSTUB_FACTORY", "()Lcom/vk/di/component/factory/DiComponentFactory;", "STUB_FACTORY", "api_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion ipaahctpackvmoca = new Companion();

        private Companion() {
        }

        public static final CaptchaComponent access$getSTUB(Companion companion) {
            companion.getClass();
            return new CaptchaComponent() { // from class: com.vk.auth.captcha.api.di.CaptchaComponent$Companion$STUB$1

                /* JADX INFO: renamed from: ipaahctpackvmoca, reason: from kotlin metadata */
                private final SakCaptchaHandler sakCaptchaHandler = SakCaptchaHandler.INSTANCE.getSTUB$api_release();

                @Override // com.vk.auth.captcha.api.di.CaptchaComponent
                public SakCaptchaHandler getSakCaptchaHandler() {
                    return this.sakCaptchaHandler;
                }
            };
        }

        @NotNull
        public final DiComponentFactory<CaptchaComponent> getSTUB_FACTORY() {
            return new DiComponentFactory<CaptchaComponent>() { // from class: com.vk.auth.captcha.api.di.CaptchaComponent$Companion$STUB_FACTORY$1
                @Override // com.vk.di.component.factory.DiComponentFactory
                public CaptchaComponent createComponent(DiComponentProvider provider) {
                    Intrinsics.checkNotNullParameter(provider, "provider");
                    return CaptchaComponent.Companion.access$getSTUB(CaptchaComponent.Companion.ipaahctpackvmoca);
                }
            };
        }
    }

    @NotNull
    SakCaptchaHandler getSakCaptchaHandler();
}
