package com.applovin.impl.adview;

class b implements Runnable {
    final /* synthetic */ int a;
    final /* synthetic */ AdViewControllerImpl b;

    b(AdViewControllerImpl adViewControllerImpl, int i) {
        this.b = adViewControllerImpl;
        this.a = i;
    }

    public void run() {
        try {
            if (this.b.v != null) {
                this.b.v.failedToReceiveAd(this.a);
            }
        } catch (Throwable th) {
            this.b.d.userError("AppLovinAdView", "Exception while running app load  callback", th);
        }
    }
}
