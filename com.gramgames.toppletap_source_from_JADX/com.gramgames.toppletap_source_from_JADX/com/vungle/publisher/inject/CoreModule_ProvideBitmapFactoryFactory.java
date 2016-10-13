package com.vungle.publisher.inject;

import com.vungle.publisher.fi;
import com.vungle.publisher.image.AssetBitmapFactory;
import com.vungle.publisher.image.BitmapFactory;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import javax.inject.Provider;

/* compiled from: vungle */
public final class CoreModule_ProvideBitmapFactoryFactory implements Factory<BitmapFactory> {
    static final /* synthetic */ boolean a;
    private final fi b;
    private final Provider<AssetBitmapFactory> c;

    static {
        a = !CoreModule_ProvideBitmapFactoryFactory.class.desiredAssertionStatus();
    }

    public CoreModule_ProvideBitmapFactoryFactory(fi module, Provider<AssetBitmapFactory> assetBitmapFactoryProvider) {
        if (a || module != null) {
            this.b = module;
            if (a || assetBitmapFactoryProvider != null) {
                this.c = assetBitmapFactoryProvider;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public final BitmapFactory get() {
        fi fiVar = this.b;
        Object obj = (AssetBitmapFactory) this.c.get();
        if (fiVar.c != null) {
            obj = fiVar.c;
        }
        return (BitmapFactory) Preconditions.checkNotNull(obj, "Cannot return null from a non-@Nullable @Provides method");
    }

    public static Factory<BitmapFactory> create(fi module, Provider<AssetBitmapFactory> assetBitmapFactoryProvider) {
        return new CoreModule_ProvideBitmapFactoryFactory(module, assetBitmapFactoryProvider);
    }
}
