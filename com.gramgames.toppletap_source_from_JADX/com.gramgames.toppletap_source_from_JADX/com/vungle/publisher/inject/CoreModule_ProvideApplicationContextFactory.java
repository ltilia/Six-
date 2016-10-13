package com.vungle.publisher.inject;

import android.content.Context;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;

/* compiled from: vungle */
public final class CoreModule_ProvideApplicationContextFactory implements Factory<Context> {
    static final /* synthetic */ boolean a;
    private final fi b;

    static {
        a = !CoreModule_ProvideApplicationContextFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideApplicationContextFactory(fi module) {
        if (a || module != null) {
            this.b = module;
            return;
        }
        throw new AssertionError();
    }

    public final Context get() {
        return (Context) Preconditions.checkNotNull(this.b.a, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<Context> create(fi module) {
        return new CoreModule_ProvideApplicationContextFactory(module);
    }
}
