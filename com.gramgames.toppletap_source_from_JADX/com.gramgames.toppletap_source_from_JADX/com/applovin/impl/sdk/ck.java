package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.List;

public class ck extends cl {
    public ck(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdLoadListener);
    }

    public ck(AppLovinSdkImpl appLovinSdkImpl, List list, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        super("TaskCacheNativeAdImages", appLovinSdkImpl, list, appLovinNativeAdPrecacheListener);
    }

    private boolean b(NativeAdImpl nativeAdImpl) {
        this.g.w("TaskCacheNativeAdImages", "Unable to cache image resource");
        a(nativeAdImpl, !q.a(this.h) ? AppLovinErrorCodes.NO_NETWORK : AppLovinErrorCodes.UNABLE_TO_PRECACHE_IMAGE_RESOURCES);
        return false;
    }

    protected void a(NativeAdImpl nativeAdImpl) {
        if (this.b != null) {
            this.b.onNativeAdImagesPrecached(nativeAdImpl);
        }
    }

    protected void a(NativeAdImpl nativeAdImpl, int i) {
        if (this.b != null) {
            this.b.onNativeAdImagePrecachingFailed(nativeAdImpl, i);
        }
    }

    protected boolean a(NativeAdImpl nativeAdImpl, y yVar) {
        this.f.getLogger().d("TaskCacheNativeAdImages", "Beginning slot image caching for ad " + nativeAdImpl.getAdId());
        if (((Boolean) this.f.a(cb.B)).booleanValue()) {
            String a = a(nativeAdImpl.getSourceIconUrl(), yVar);
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setIconUrl(a);
            a = a(nativeAdImpl.getSourceImageUrl(), yVar);
            if (a == null) {
                return b(nativeAdImpl);
            }
            nativeAdImpl.setImageUrl(a);
        } else {
            this.f.getLogger().d("TaskCacheNativeAdImages", "Resource caching is disabled, skipping...");
        }
        return true;
    }

    public /* bridge */ /* synthetic */ void run() {
        super.run();
    }
}
