package com.vungle.publisher.inject;

import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class CoreModule_ProvideWrapperFrameworkFactory implements Factory<WrapperFramework> {
    static final /* synthetic */ boolean a;
    private final fi b;

    static {
        a = !CoreModule_ProvideWrapperFrameworkFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideWrapperFrameworkFactory(fi module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final WrapperFramework get() {
        fi fiVar = this.b;
        return (WrapperFramework) Preconditions.checkNotNull(fiVar.e == null ? WrapperFramework.none : fiVar.e, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WrapperFramework> create(fi module) {
        return new CoreModule_ProvideWrapperFrameworkFactory(module);
    }
}
