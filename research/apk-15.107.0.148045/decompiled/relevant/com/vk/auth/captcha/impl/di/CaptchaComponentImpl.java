package com.vk.auth.captcha.impl.di;

import com.vk.auth.captcha.api.SakCaptchaHandler;
import com.vk.auth.captcha.api.di.CaptchaComponent;
import com.vk.auth.captcha.impl.SakCaptchaHandlerImpl;
import com.vk.di.component.factory.DiComponentFactory;
import com.vk.di.component.factory.DiComponentProvider;
import com.vk.di.dependency.DiDependency;
import com.vk.di.dependency.DiDependencyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: compiled from: ProGuard */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003R\u001b\u0010\t\u001a\u00020\u00048VX\u0096\u0084\u0002¢\u0006\f\n\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\u000b"}, d2 = {"Lcom/vk/auth/captcha/impl/di/CaptchaComponentImpl;", "Lcom/vk/auth/captcha/api/di/CaptchaComponent;", "<init>", "()V", "Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "lpmiahctpackvmoca", "Lcom/vk/di/dependency/DiDependency;", "getSakCaptchaHandler", "()Lcom/vk/auth/captcha/api/SakCaptchaHandler;", "sakCaptchaHandler", "Factory", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class CaptchaComponentImpl implements CaptchaComponent {
    static final /* synthetic */ KProperty<Object>[] lpmiahctpackvmocb = {Reflection.property1(new PropertyReference1Impl(CaptchaComponentImpl.class, "sakCaptchaHandler", "getSakCaptchaHandler()Lcom/vk/auth/captcha/api/SakCaptchaHandler;", 0))};

    /* JADX INFO: renamed from: lpmiahctpackvmoca, reason: from kotlin metadata */
    @NotNull
    private final DiDependency sakCaptchaHandler = DiDependencyKt.newInstance(this, new Function0() { // from class: com.vk.auth.captcha.impl.di.a
        @Override // kotlin.jvm.functions.Function0
        public final Object invoke() {
            return CaptchaComponentImpl.lpmiahctpackvmoca();
        }
    });

    /* JADX INFO: compiled from: ProGuard */
    /* JADX INFO: loaded from: classes.dex */
    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007¢\u0006\u0004\b\u0003\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/vk/auth/captcha/impl/di/CaptchaComponentImpl$Factory;", "Lcom/vk/di/component/factory/DiComponentFactory;", "Lcom/vk/auth/captcha/api/di/CaptchaComponent;", "<init>", "()V", "createComponent", "provider", "Lcom/vk/di/component/factory/DiComponentProvider;", "impl_release"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Factory implements DiComponentFactory<CaptchaComponent> {
        @Override // com.vk.di.component.factory.DiComponentFactory
        @NotNull
        public CaptchaComponent createComponent(@NotNull DiComponentProvider provider) {
            Intrinsics.checkNotNullParameter(provider, "provider");
            return new CaptchaComponentImpl();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final SakCaptchaHandlerImpl lpmiahctpackvmoca() {
        return new SakCaptchaHandlerImpl();
    }

    @Override // com.vk.auth.captcha.api.di.CaptchaComponent
    @NotNull
    public SakCaptchaHandler getSakCaptchaHandler() {
        return (SakCaptchaHandler) this.sakCaptchaHandler.getValue(this, lpmiahctpackvmocb[0]);
    }
}
