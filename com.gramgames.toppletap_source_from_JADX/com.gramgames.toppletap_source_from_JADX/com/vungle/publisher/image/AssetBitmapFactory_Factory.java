package com.vungle.publisher.image;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AssetBitmapFactory_Factory implements Factory<AssetBitmapFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AssetBitmapFactory> b;

    static {
        a = !AssetBitmapFactory_Factory.class.desiredAssertionStatus();
    }

    public AssetBitmapFactory_Factory(MembersInjector<AssetBitmapFactory> assetBitmapFactoryMembersInjector) {
        if (a || assetBitmapFactoryMembersInjector != null) {
            this.b = assetBitmapFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AssetBitmapFactory get() {
        return (AssetBitmapFactory) MembersInjectors.injectMembers(this.b, new AssetBitmapFactory());
    }

    public static Factory<AssetBitmapFactory> create(MembersInjector<AssetBitmapFactory> assetBitmapFactoryMembersInjector) {
        return new AssetBitmapFactory_Factory(assetBitmapFactoryMembersInjector);
    }
}
