package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdUpdateListener;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;
import java.util.Collection;
import java.util.HashSet;

class h implements AppLovinAdLoadListener {
    final /* synthetic */ AppLovinAdServiceImpl a;
    private final i b;

    private h(AppLovinAdServiceImpl appLovinAdServiceImpl, i iVar) {
        this.a = appLovinAdServiceImpl;
        this.b = iVar;
    }

    public void adReceived(AppLovinAd appLovinAd) {
        synchronized (this.b.b) {
            if (this.a.a(this.b.a)) {
                long b = this.a.b(this.b.a);
                if (b > 0) {
                    this.b.d = (b * 1000) + System.currentTimeMillis();
                } else if (b == 0) {
                    this.b.d = PtsTimestampAdjuster.DO_NOT_OFFSET;
                }
                this.b.c = appLovinAd;
            } else {
                this.b.c = null;
                this.b.d = 0;
            }
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.b.g);
            this.b.g.clear();
            Collection<AppLovinAdUpdateListener> hashSet2 = new HashSet(this.b.f);
            this.b.e = false;
        }
        this.a.c(this.b.a);
        for (AppLovinAdLoadListener adReceived : hashSet) {
            try {
                adReceived.adReceived(appLovinAd);
            } catch (Throwable th) {
                this.a.b.e("AppLovinAdService", "Unable to notify listener about a newly loaded ad", th);
            }
        }
        for (AppLovinAdUpdateListener adUpdated : hashSet2) {
            try {
                adUpdated.adUpdated(appLovinAd);
            } catch (Throwable th2) {
                this.a.b.e("AppLovinAdService", "Unable to notify listener about an updated loaded ad", th2);
            }
        }
    }

    public void failedToReceiveAd(int i) {
        synchronized (this.b.b) {
            Collection<AppLovinAdLoadListener> hashSet = new HashSet(this.b.g);
            this.b.g.clear();
            this.b.e = false;
        }
        for (AppLovinAdLoadListener failedToReceiveAd : hashSet) {
            try {
                failedToReceiveAd.failedToReceiveAd(i);
            } catch (Throwable th) {
                this.a.b.e("AppLovinAdService", "Unable to notify listener about ad load failure", th);
            }
        }
    }
}
