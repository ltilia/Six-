package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.publisher.ej;
import com.vungle.publisher.em;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvidePublisherAppFactory implements Factory<em> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;
    private final Provider<WrapperFramework> d;

    static {
        a = !CoreModule_ProvidePublisherAppFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvidePublisherAppFactory(fi module, Provider<Context> contextProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || contextProvider != null) {
                this.c = contextProvider;
                if (a || wrapperFrameworkProvider != null) {
                    this.d = wrapperFrameworkProvider;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final em get() {
        return (em) Preconditions.checkNotNull(new ej(((Context) this.c.get()).getPackageName(), this.b.b, (WrapperFramework) this.d.get()), "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<em> create(fi module, Provider<Context> contextProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
        return new CoreModule_ProvidePublisherAppFactory(module, contextProvider, wrapperFrameworkProvider);
    }
}
