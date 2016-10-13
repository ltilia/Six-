package com.applovin.impl.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.SoftReference;

public class z {
    private static String l;
    private final AppLovinSdkImpl a;
    private final AppLovinAdServiceImpl b;
    private AppLovinAdImpl c;
    private String d;
    private SoftReference e;
    private final Handler f;
    private final Object g;
    private volatile String h;
    private dj i;
    private volatile boolean j;
    private SoftReference k;

    static {
        l = null;
    }

    public z(AppLovinSdk appLovinSdk) {
        this.g = new Object();
        this.j = false;
        this.a = (AppLovinSdkImpl) appLovinSdk;
        this.b = (AppLovinAdServiceImpl) appLovinSdk.getAdService();
        this.f = new Handler(Looper.getMainLooper());
    }

    public static void a(String str) {
        l = str;
    }

    public static String b() {
        return l;
    }

    private void e() {
        if (this.e != null) {
            AppLovinAdLoadListener appLovinAdLoadListener = (AppLovinAdLoadListener) this.e.get();
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.failedToReceiveAd(AppLovinErrorCodes.INCENTIVIZED_NO_AD_PRELOADED);
            }
        }
    }

    private AppLovinAdRewardListener f() {
        return new aa(this);
    }

    void a(Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        if (a()) {
            AppLovinAd appLovinAd = this.c;
            if (appLovinAd.getType().equals(AppLovinAdType.INCENTIVIZED)) {
                AppLovinInterstitialAdDialog create = AppLovinInterstitialAd.create(this.a, activity);
                AppLovinAdRewardListener aeVar = new ae(activity, appLovinAdRewardListener, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener, null);
                create.setAdDisplayListener(aeVar);
                create.setAdVideoPlaybackListener(aeVar);
                create.setAdClickListener(aeVar);
                create.showAndRender(appLovinAd, this.d);
                this.k = new SoftReference(create);
                a(appLovinAd, aeVar);
                return;
            }
            this.a.getLogger().e("IncentivizedAdController", "Attempted to render an ad of type " + this.c.getType() + " in an Incentivized Ad interstitial.");
            appLovinAdVideoPlaybackListener.videoPlaybackEnded(this.c, 0.0d, false);
            return;
        }
        this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
        e();
    }

    public void a(Activity activity, String str, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        AppLovinAdRewardListener f = appLovinAdRewardListener == null ? f() : appLovinAdRewardListener;
        if (!a()) {
            this.a.getLogger().userError("IncentivizedAdController", "Skipping incentivized video playback: user attempted to play an incentivized video before one was preloaded.");
            e();
        } else if (!AppLovinSdkUtils.isValidString(this.c.getVideoFilename()) || this.a.getFileManager().a(this.c.getVideoFilename(), (Context) activity)) {
            this.d = str;
            if (((Boolean) this.a.a(cb.Z)).booleanValue()) {
                aq aqVar = new aq(this.a, this);
                aqVar.a(activity);
                aqVar.a(appLovinAdDisplayListener);
                aqVar.a(appLovinAdClickListener);
                aqVar.a(appLovinAdVideoPlaybackListener);
                aqVar.a(f);
                aqVar.a();
                return;
            }
            a(activity, f, appLovinAdVideoPlaybackListener, appLovinAdDisplayListener, appLovinAdClickListener);
        }
    }

    void a(AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        this.i = new dj(this.a, appLovinAd, appLovinAdRewardListener);
        this.a.a().a(this.i, cw.BACKGROUND);
    }

    public void a(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.a.getLogger().d("IncentivizedAdController", "User requested preload of incentivized ad...");
        this.e = new SoftReference(appLovinAdLoadListener);
        if (a()) {
            this.a.getLogger().userError("IncentivizedAdController", "Attempted to call preloadAndNotify: while an ad was already loaded or currently being played. Do not call preloadAndNotify: again until the last ad has been closed (adHidden).");
            if (appLovinAdLoadListener != null) {
                appLovinAdLoadListener.adReceived(this.c);
                return;
            }
            return;
        }
        this.b.loadNextAd(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, new ab(this, appLovinAdLoadListener));
    }

    void a(AppLovinAdRewardListener appLovinAdRewardListener) {
        appLovinAdRewardListener.userDeclinedToViewAd(this.c);
    }

    void a(String str, Activity activity) {
        if (str != null && ((Boolean) this.a.a(cb.aa)).booleanValue()) {
            new ao(this.a, activity, str).a();
        }
    }

    public boolean a() {
        return this.c != null;
    }

    void b(String str) {
        synchronized (this.g) {
            this.h = str;
        }
    }

    String c() {
        String str;
        synchronized (this.g) {
            str = this.h;
        }
        return str;
    }

    public void d() {
        if (this.k != null) {
            AppLovinInterstitialAdDialog appLovinInterstitialAdDialog = (AppLovinInterstitialAdDialog) this.k.get();
            if (appLovinInterstitialAdDialog != null) {
                appLovinInterstitialAdDialog.dismiss();
            }
        }
    }
}
