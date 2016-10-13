package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import java.util.Timer;

class aq {
    private final AppLovinSdkImpl a;
    private final z b;
    private Activity c;
    private AppLovinAdDisplayListener d;
    private AppLovinAdVideoPlaybackListener e;
    private AppLovinAdClickListener f;
    private AppLovinAdRewardListener g;
    private final Timer h;

    aq(AppLovinSdkImpl appLovinSdkImpl, z zVar) {
        this.h = new Timer("IncentivizedAdLauncher");
        this.a = appLovinSdkImpl;
        this.b = zVar;
    }

    private void b() {
        this.b.a(this.c, this.g, this.e, this.d, this.f);
    }

    void a() {
        this.c.runOnUiThread(new ar(this));
    }

    public void a(Activity activity) {
        this.c = activity;
    }

    public void a(AppLovinAdClickListener appLovinAdClickListener) {
        this.f = appLovinAdClickListener;
    }

    public void a(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        this.d = appLovinAdDisplayListener;
    }

    public void a(AppLovinAdRewardListener appLovinAdRewardListener) {
        this.g = appLovinAdRewardListener;
    }

    public void a(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        this.e = appLovinAdVideoPlaybackListener;
    }
}
