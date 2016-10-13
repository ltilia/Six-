package com.vungle.publisher.inject;

import android.content.Context;
import android.view.WindowManager;
import com.vungle.log.Logger;
import com.vungle.publisher.fi;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideWindowManagerFactory implements Factory<WindowManager> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<Context> c;

    static {
        a = !CoreModule_ProvideWindowManagerFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideWindowManagerFactory(fi module, Provider<Context> contextProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || contextProvider != null) {
                this.c = contextProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final WindowManager get() {
        WindowManager windowManager = (WindowManager) ((Context) this.c.get()).getSystemService("window");
        if (windowManager == null) {
            Logger.d(Logger.DEVICE_TAG, "WindowManager not available");
        }
        return (WindowManager) Preconditions.checkNotNull(windowManager, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<WindowManager> create(fi module, Provider<Context> contextProvider) {
        return new CoreModule_ProvideWindowManagerFactory(module, contextProvider);
    }
}
