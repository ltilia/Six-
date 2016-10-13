package com.applovin.impl.adview;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.applovin.adview.AppLovinInterstitialActivity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.impl.sdk.AppLovinAdImpl;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinSdkImpl;
import com.applovin.impl.sdk.n;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ah implements AppLovinInterstitialAdDialog {
    public static volatile boolean a;
    public static volatile boolean b;
    private static final Map c;
    private static volatile boolean o;
    private final String d;
    private final AppLovinSdkImpl e;
    private final WeakReference f;
    private volatile AppLovinAdLoadListener g;
    private volatile AppLovinAdDisplayListener h;
    private volatile AppLovinAdVideoPlaybackListener i;
    private volatile AppLovinAdClickListener j;
    private volatile AppLovinAdImpl k;
    private volatile AdTarget l;
    private volatile w m;
    private volatile String n;

    static {
        c = Collections.synchronizedMap(new HashMap());
        a = false;
        b = false;
    }

    ah(AppLovinSdk appLovinSdk, Activity activity) {
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.e = (AppLovinSdkImpl) appLovinSdk;
            this.d = UUID.randomUUID().toString();
            a = true;
            b = false;
            this.f = new WeakReference(activity);
        }
    }

    public static ah a(String str) {
        return (ah) c.get(str);
    }

    private void a(int i) {
        Activity i2 = i();
        if (i2 != null) {
            i2.runOnUiThread(new al(this, i));
        } else {
            this.e.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    private void a(Activity activity) {
        Object xVar = new x(this.e, activity);
        xVar.a(this);
        this.m = xVar;
        xVar.a(this.k, this.n);
    }

    private void a(AppLovinAd appLovinAd) {
        if (this.h != null) {
            this.h.adHidden(appLovinAd);
        }
    }

    private void b(Activity activity) {
        Intent intent = new Intent(activity, AppLovinInterstitialActivity.class);
        intent.putExtra(AppLovinInterstitialActivity.KEY_WRAPPER_ID, this.d);
        AppLovinInterstitialActivity.lastKnownWrapper = this;
        activity.startActivity(intent);
        a(true);
    }

    private void b(AppLovinAd appLovinAd) {
        Activity i = i();
        if (i != null) {
            i.runOnUiThread(new ak(this, appLovinAd));
        } else {
            this.e.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        }
    }

    private Activity i() {
        return this.f != null ? (Activity) this.f.get() : null;
    }

    public AppLovinSdk a() {
        return this.e;
    }

    public void a(w wVar) {
        this.m = wVar;
    }

    public void a(boolean z) {
        o = z;
    }

    public AppLovinAd b() {
        return this.k;
    }

    public AppLovinAdVideoPlaybackListener c() {
        return this.i;
    }

    public AppLovinAdDisplayListener d() {
        return this.h;
    }

    public void dismiss() {
        Activity i = i();
        if (i == null) {
            this.e.getLogger().userError("InterstitialAdDialogWrapper", "Failed to notify load listener: activity reference is stale");
        } else if (this.m != null) {
            i.runOnUiThread(new am(this));
        }
    }

    public AppLovinAdClickListener e() {
        return this.j;
    }

    public AdTarget f() {
        return this.l;
    }

    public String g() {
        return this.n;
    }

    public void h() {
        a = false;
        b = true;
        c.remove(this.d);
    }

    public boolean isAdReadyToDisplay() {
        return this.e.getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
    }

    public boolean isShowing() {
        return o;
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        this.j = appLovinAdClickListener;
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.h = appLovinAdDisplayListener;
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        this.g = appLovinAdLoadListener;
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.i = appLovinAdVideoPlaybackListener;
    }

    public void show() {
        show(null);
    }

    public void show(String str) {
        this.e.getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new ai(this, str));
    }

    public void showAndRender(AppLovinAd appLovinAd) {
        showAndRender(appLovinAd, null);
    }

    public void showAndRender(AppLovinAd appLovinAd, String str) {
        if (isShowing()) {
            this.e.getLogger().userError("AppLovinInterstitialAdDialog", "Attempted to show an interstitial while one is already displayed; ignoring.");
            return;
        }
        c.put(this.d, this);
        this.k = (AppLovinAdImpl) appLovinAd;
        this.n = str;
        this.l = this.k != null ? this.k.getTarget() : AdTarget.DEFAULT;
        Context i = i();
        if (i == null) {
            this.e.getLogger().e("InterstitialAdDialogWrapper", "Failed to show interstitial: stale activity reference provided");
            a(appLovinAd);
        } else if (!AppLovinSdkUtils.isValidString(this.k.getVideoFilename()) || this.e.getFileManager().a(this.k.getVideoFilename(), i)) {
            boolean a = n.a(AppLovinInterstitialActivity.class, i);
            boolean z = this.l == AdTarget.ACTIVITY_LANDSCAPE || this.l == AdTarget.ACTIVITY_PORTRAIT;
            i.runOnUiThread(new aj(this, a, z, i));
        } else {
            a(appLovinAd);
        }
    }
}
