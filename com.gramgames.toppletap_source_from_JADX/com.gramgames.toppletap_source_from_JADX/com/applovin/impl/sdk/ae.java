package com.applovin.impl.sdk;

import android.app.Activity;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import java.util.Map;

class ae implements AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener, AppLovinAdVideoPlaybackListener {
    final /* synthetic */ z a;
    private final Activity b;
    private final AppLovinAdDisplayListener c;
    private final AppLovinAdClickListener d;
    private final AppLovinAdVideoPlaybackListener e;
    private final AppLovinAdRewardListener f;

    private ae(z zVar, Activity activity, AppLovinAdRewardListener appLovinAdRewardListener, AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener, AppLovinAdDisplayListener appLovinAdDisplayListener, AppLovinAdClickListener appLovinAdClickListener) {
        this.a = zVar;
        this.c = appLovinAdDisplayListener;
        this.d = appLovinAdClickListener;
        this.e = appLovinAdVideoPlaybackListener;
        this.f = appLovinAdRewardListener;
        this.b = activity;
    }

    public void adClicked(AppLovinAd appLovinAd) {
        if (this.d != null) {
            this.a.f.post(new ah(this, appLovinAd));
        }
    }

    public void adDisplayed(AppLovinAd appLovinAd) {
        if (this.c != null) {
            this.c.adDisplayed(appLovinAd);
        }
    }

    public void adHidden(AppLovinAd appLovinAd) {
        String c = this.a.c();
        if (c == null || !this.a.j) {
            String str;
            int i;
            this.a.i.a(true);
            if (this.a.j) {
                str = "network_timeout";
                i = AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
            } else {
                str = "user_closed_video";
                i = AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO;
            }
            bq.a().a(appLovinAd, str);
            if (this.a.j) {
                this.a.a(c, this.b);
            }
            this.a.f.post(new af(this, appLovinAd, i));
        } else {
            this.a.a(c, this.b);
        }
        if (this.c != null) {
            this.a.f.post(new ag(this, appLovinAd));
        }
        this.a.a.a().a(new de(this.a.a, appLovinAd), cw.BACKGROUND);
        this.a.c = null;
        this.a.d = null;
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        this.a.b("quota_exceeded");
        if (this.f != null) {
            this.a.f.post(new al(this, appLovinAd, map));
        }
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        this.a.b("rejected");
        if (this.f != null) {
            this.a.f.post(new am(this, appLovinAd, map));
        }
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        this.a.b("accepted");
        if (this.f != null) {
            this.a.f.post(new ak(this, appLovinAd, map));
        }
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        this.a.b("network_timeout");
        if (this.f != null) {
            this.a.f.post(new an(this, appLovinAd, i));
        }
    }

    public void videoPlaybackBegan(AppLovinAd appLovinAd) {
        if (this.e != null) {
            this.a.f.post(new ai(this, appLovinAd));
        }
    }

    public void videoPlaybackEnded(AppLovinAd appLovinAd, double d, boolean z) {
        if (this.e != null) {
            this.a.f.post(new aj(this, appLovinAd, d, z));
        }
        this.a.j = z;
    }
}
