package com.applovin.impl.sdk;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppLovinAdServiceImpl implements AppLovinAdService {
    public static String URI_NO_OP;
    public static String URI_TRACK_CLICK_IMMEDIATELY;
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private Handler c;
    private final Map d;

    static {
        URI_NO_OP = "/adservice/no_op";
        URI_TRACK_CLICK_IMMEDIATELY = "/adservice/track_click_now";
    }

    AppLovinAdServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.c = new Handler(Looper.getMainLooper());
        this.d = new HashMap(2);
        for (AppLovinAdType put : AppLovinAdType.allTypes()) {
            this.d.put(put, new HashMap());
        }
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.BANNER, new i(null));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.MREC, new i(null));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.INTERSTITIAL, new i(null));
        ((Map) this.d.get(AppLovinAdType.REGULAR)).put(AppLovinAdSize.LEADER, new i(null));
        ((Map) this.d.get(AppLovinAdType.INCENTIVIZED)).put(AppLovinAdSize.INTERSTITIAL, new i(null));
    }

    private void a(Uri uri, AppLovinAdImpl appLovinAdImpl, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl) {
        adViewControllerImpl.removeClickTrackingOverlay();
        expireAdLoadState(appLovinAdImpl);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.a);
        adViewControllerImpl.dismissInterstitialIfRequired();
    }

    private void a(AppLovinAdImpl appLovinAdImpl, String str) {
        String supplementalClickTrackingUrl = appLovinAdImpl.getSupplementalClickTrackingUrl(str);
        if (AppLovinSdkUtils.isValidString(supplementalClickTrackingUrl)) {
            this.a.getPersistentPostbackManager().a(supplementalClickTrackingUrl, null);
        }
    }

    private void a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        c cVar = new c(appLovinAdSize, appLovinAdType);
        AppLovinAd appLovinAd = (AppLovinAd) this.a.c().b(cVar);
        if (appLovinAd != null) {
            this.b.d("AppLovinAdService", "Using pre-loaded ad: " + appLovinAd + " for size " + appLovinAdSize + " and type " + appLovinAdType);
            appLovinAdLoadListener.adReceived(appLovinAd);
        } else {
            this.a.a().a(new cr(appLovinAdSize, appLovinAdType, appLovinAdLoadListener, this.a), cw.MAIN);
        }
        this.a.c().f(cVar);
    }

    private boolean a() {
        return ((PowerManager) this.a.getApplicationContext().getSystemService("power")).isScreenOn();
    }

    private boolean a(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.BANNER ? ((Boolean) this.a.a(cb.t)).booleanValue() : appLovinAdSize == AppLovinAdSize.MREC ? ((Boolean) this.a.a(cb.v)).booleanValue() : appLovinAdSize == AppLovinAdSize.LEADER ? ((Boolean) this.a.a(cb.x)).booleanValue() : false;
    }

    private boolean a(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        return !((Boolean) this.a.a(cb.A)).booleanValue() ? false : !b(appLovinAdSize, appLovinAdType) ? false : appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.a.a(cb.av)).booleanValue() : appLovinAdSize.equals(AppLovinAdSize.INTERSTITIAL) ? ((Boolean) this.a.a(cb.aw)).booleanValue() : false;
    }

    private long b(AppLovinAdSize appLovinAdSize) {
        return appLovinAdSize == AppLovinAdSize.BANNER ? ((Long) this.a.a(cb.u)).longValue() : appLovinAdSize == AppLovinAdSize.MREC ? ((Long) this.a.a(cb.w)).longValue() : appLovinAdSize == AppLovinAdSize.LEADER ? ((Long) this.a.a(cb.y)).longValue() : 0;
    }

    private boolean b(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType) {
        try {
            return appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) ? ((Boolean) this.a.a(cb.E)).booleanValue() : ((String) this.a.a(cb.D)).toUpperCase(Locale.ENGLISH).contains(appLovinAdSize.getLabel());
        } catch (Throwable e) {
            this.a.getLogger().e("AppLovinAdService", "Unable to safely test preload merge capability", e);
            return false;
        }
    }

    private void c(AppLovinAdSize appLovinAdSize) {
        long b = b(appLovinAdSize);
        if (b > 0) {
            this.a.a().a(new j(this, appLovinAdSize), cw.MAIN, (b + 2) * 1000);
        }
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener) {
        addAdUpdateListener(appLovinAdUpdateListener, AppLovinAdSize.BANNER);
    }

    public void addAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener == null) {
            throw new IllegalArgumentException("No ad listener specified");
        }
        Object obj;
        i iVar = (i) ((Map) this.d.get(AppLovinAdType.REGULAR)).get(appLovinAdSize);
        synchronized (iVar.b) {
            if (iVar.f.contains(appLovinAdUpdateListener)) {
                obj = null;
            } else {
                iVar.f.add(appLovinAdUpdateListener);
                obj = 1;
                this.b.d("AppLovinAdService", "Added update listener: " + appLovinAdUpdateListener);
            }
        }
        if (obj != null) {
            this.a.a().a(new j(this, appLovinAdSize), cw.MAIN);
        }
    }

    public void expireAdLoadState(AppLovinAd appLovinAd) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        i iVar = (i) ((Map) this.d.get(appLovinAdImpl.getType())).get(appLovinAdImpl.getSize());
        synchronized (iVar.b) {
            iVar.c = null;
            iVar.d = 0;
        }
    }

    public boolean hasPreloadedAd(AppLovinAdSize appLovinAdSize) {
        return this.a.c().e(new c(appLovinAdSize, AppLovinAdType.REGULAR));
    }

    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdLoadListener appLovinAdLoadListener) {
        loadNextAd(appLovinAdSize, AppLovinAdType.REGULAR, appLovinAdLoadListener);
    }

    public void loadNextAd(AppLovinAdSize appLovinAdSize, AppLovinAdType appLovinAdType, AppLovinAdLoadListener appLovinAdLoadListener) {
        Object obj = 1;
        if (appLovinAdSize == null) {
            throw new IllegalArgumentException("No ad size specified");
        } else if (appLovinAdLoadListener == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (appLovinAdType == null) {
            throw new IllegalArgumentException("No ad type specificed");
        } else {
            AppLovinAd appLovinAd;
            this.a.getLogger().d("AppLovinAdService", "Loading next ad of size " + appLovinAdSize.getLabel() + " and type " + appLovinAdType.getLabel());
            if (appLovinAdSize.equals(AppLovinAdSize.BANNER) || appLovinAdSize.equals(AppLovinAdSize.MREC) || appLovinAdSize.equals(AppLovinAdSize.LEADER)) {
                this.a.getLogger().userError("AppLovinAdService", "Banners, MRecs and Leaderboards are deprecated and will be removed in a future SDK version!");
            }
            i iVar = (i) ((Map) this.d.get(appLovinAdType)).get(appLovinAdSize);
            synchronized (iVar.b) {
                if (System.currentTimeMillis() <= iVar.d) {
                    obj = null;
                }
                if (iVar.c == null || r2 != null) {
                    this.b.d("AppLovinAdService", "Loading next ad...");
                    iVar.g.add(appLovinAdLoadListener);
                    if (!iVar.e) {
                        iVar.e = true;
                        obj = new h(this, (i) ((Map) this.d.get(appLovinAdType)).get(appLovinAdSize), null);
                        if (!a(appLovinAdSize, appLovinAdType)) {
                            this.b.d("AppLovinAdService", "Task merge not necessary.");
                            a(appLovinAdSize, appLovinAdType, obj);
                        } else if (this.a.c().a(new c(appLovinAdSize, appLovinAdType), obj)) {
                            this.b.d("AppLovinAdService", "Attaching load listener to initial preload task...");
                            appLovinAd = null;
                        } else {
                            this.b.d("AppLovinAdService", "Skipped attach of initial preload callback.");
                            a(appLovinAdSize, appLovinAdType, obj);
                            appLovinAd = null;
                        }
                    }
                    appLovinAd = null;
                } else {
                    appLovinAd = iVar.c;
                }
            }
            if (appLovinAd != null) {
                appLovinAdLoadListener.adReceived(appLovinAd);
            }
        }
    }

    public void preloadAd(AppLovinAdSize appLovinAdSize) {
        this.a.c().f(new c(appLovinAdSize, AppLovinAdType.REGULAR));
    }

    public void removeAdUpdateListener(AppLovinAdUpdateListener appLovinAdUpdateListener, AppLovinAdSize appLovinAdSize) {
        if (appLovinAdUpdateListener != null) {
            i iVar = (i) ((Map) this.d.get(AppLovinAdType.REGULAR)).get(appLovinAdSize);
            synchronized (iVar.b) {
                iVar.f.remove(appLovinAdUpdateListener);
            }
            this.b.d("AppLovinAdService", "Removed update listener: " + appLovinAdUpdateListener);
        }
    }

    public void trackAndLaunchClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        a(appLovinAdImpl, str);
        a(uri, appLovinAdImpl, appLovinAdView, adViewControllerImpl);
    }

    public void trackAndLaunchForegroundClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, AdViewControllerImpl adViewControllerImpl, Uri uri) {
        if (appLovinAd == null) {
            throw new IllegalArgumentException("No ad specified");
        }
        AppLovinAdImpl appLovinAdImpl = (AppLovinAdImpl) appLovinAd;
        this.b.d("AppLovinAdService", "Tracking foreground click on an ad...");
        int intValue = ((Integer) this.a.a(cb.bj)).intValue();
        int intValue2 = ((Integer) this.a.a(cb.bk)).intValue();
        int intValue3 = ((Integer) this.a.a(cb.bl)).intValue();
        this.a.getPostbackService().dispatchPostbackAsync(((AppLovinAdImpl) appLovinAd).getSupplementalClickTrackingUrl(str), null, intValue, intValue2, intValue3, new e(this, adViewControllerImpl, uri, appLovinAdImpl, appLovinAdView));
    }

    public void trackAndLaunchVideoClick(AppLovinAd appLovinAd, String str, AppLovinAdView appLovinAdView, Uri uri) {
        a((AppLovinAdImpl) appLovinAd, str);
        AppLovinSdkUtils.openUri(appLovinAdView.getContext(), uri, this.a);
    }
}
