package com.vungle.publisher.inject;

import com.vungle.publisher.FullScreenAdActivity;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class CoreModule_ProvideFullScreenAdActivityClassFactory implements Factory<Class> {
    static final /* synthetic */ boolean a;
    private final fi b;

    static {
        a = !CoreModule_ProvideFullScreenAdActivityClassFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideFullScreenAdActivityClassFactory(fi module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final Class get() {
        fi fiVar = this.b;
        return (Class) Preconditions.checkNotNull(fiVar.d == null ? FullScreenAdActivity.class : fiVar.d, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Class> create(fi module) {
        return new CoreModule_ProvideFullScreenAdActivityClassFactory(module);
    }
}
