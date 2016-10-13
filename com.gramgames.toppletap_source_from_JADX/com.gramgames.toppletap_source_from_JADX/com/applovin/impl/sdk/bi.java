package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAd;
import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.nativeAds.AppLovinNativeAdPrecacheListener;
import com.applovin.nativeAds.AppLovinNativeAdService;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class bi implements AppLovinNativeAdService {
    private final AppLovinSdkImpl a;
    private final Object b;

    bi(AppLovinSdkImpl appLovinSdkImpl) {
        this.b = new Object();
        this.a = appLovinSdkImpl;
    }

    private List a(AppLovinNativeAd appLovinNativeAd) {
        List arrayList = new ArrayList(1);
        arrayList.add((NativeAdImpl) appLovinNativeAd);
        return arrayList;
    }

    private void a(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isVideoPrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
            return;
        }
        this.a.a().a(new cm(this.a, a(appLovinNativeAd), new bl(this, appLovinNativeAdPrecacheListener)), cw.MAIN);
    }

    private void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, int i) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(i);
            } catch (Throwable e) {
                this.a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    private void a(AppLovinNativeAdLoadListener appLovinNativeAdLoadListener, List list) {
        if (appLovinNativeAdLoadListener != null) {
            try {
                appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
            } catch (Throwable e) {
                this.a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
            }
        }
    }

    private void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, int i, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPrecachingFailed(appLovinNativeAd, i);
                return;
            } catch (Throwable e) {
                this.a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagePrecachingFailed(appLovinNativeAd, i);
    }

    private void a(AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener, AppLovinNativeAd appLovinNativeAd, boolean z) {
        if (appLovinNativeAdPrecacheListener == null) {
            return;
        }
        if (z) {
            try {
                appLovinNativeAdPrecacheListener.onNativeAdVideoPreceached(appLovinNativeAd);
                return;
            } catch (Throwable e) {
                this.a.getLogger().userError("WidgetServiceImpl", "Encountered exception whilst notifying user callback", e);
                return;
            }
        }
        appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
    }

    private void b(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.a().a(new ck(this.a, list, new bo(this, appLovinNativeAdLoadListener)), cw.MAIN);
    }

    private void c(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        this.a.a().a(new cm(this.a, list, new bp(this, appLovinNativeAdLoadListener)), cw.MAIN);
    }

    public void a(List list, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        int intValue = ((Integer) this.a.a(cb.aQ)).intValue();
        if (intValue > 0) {
            list = list;
            int size = list.size();
            if (size != 0) {
                intValue = Math.min(intValue, size);
                List subList = list.subList(0, intValue);
                b(subList, new bm(this, subList, appLovinNativeAdLoadListener, list.subList(intValue, size)));
            } else if (appLovinNativeAdLoadListener != null) {
                appLovinNativeAdLoadListener.onNativeAdsFailedToLoad(AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            }
        } else if (appLovinNativeAdLoadListener != null) {
            appLovinNativeAdLoadListener.onNativeAdsLoaded(list);
        }
    }

    public void loadNativeAds(int i, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        AppLovinNativeAd appLovinNativeAd = null;
        synchronized (this.b) {
            if (i == 1) {
                if (this.a.d().e(NativeAdImpl.SPEC_NATIVE)) {
                    appLovinNativeAd = (AppLovinNativeAd) this.a.d().b(NativeAdImpl.SPEC_NATIVE);
                }
            }
        }
        if (appLovinNativeAd != null) {
            a(appLovinNativeAdLoadListener, Arrays.asList(new AppLovinNativeAd[]{appLovinNativeAd}));
            return;
        }
        this.a.a().a(new ct(this.a, i, new bj(this, appLovinNativeAdLoadListener)), cw.MAIN);
    }

    public void precacheResources(AppLovinNativeAd appLovinNativeAd, AppLovinNativeAdPrecacheListener appLovinNativeAdPrecacheListener) {
        if (appLovinNativeAd.isImagePrecached()) {
            appLovinNativeAdPrecacheListener.onNativeAdImagesPrecached(appLovinNativeAd);
            a(appLovinNativeAd, appLovinNativeAdPrecacheListener);
            return;
        }
        this.a.a().a(new ck(this.a, a(appLovinNativeAd), new bk(this, appLovinNativeAdPrecacheListener)), cw.MAIN);
    }
}
