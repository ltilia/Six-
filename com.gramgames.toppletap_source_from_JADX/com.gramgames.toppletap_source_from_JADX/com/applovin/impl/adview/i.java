package com.applovin.impl.adview;

import com.applovin.adview.AppLovinInterstitialAdDialog;

class i implements Runnable {
    final /* synthetic */ AdViewControllerImpl a;

    private i(AdViewControllerImpl adViewControllerImpl) {
        this.a = adViewControllerImpl;
    }

    public void run() {
        if (this.a.n != null) {
            try {
                this.a.d.d("AppLovinAdView", "Rendering interstitial ad for #" + this.a.n.getAdIdNumber() + " over placement: \"" + this.a.f + "\"...");
                AppLovinInterstitialAdDialog createInterstitialAdDialog = new InterstitialAdDialogCreatorImpl().createInterstitialAdDialog(this.a.b, this.a.a);
                createInterstitialAdDialog.setAdDisplayListener(new d(this.a));
                createInterstitialAdDialog.setAdVideoPlaybackListener(new e(this.a));
                createInterstitialAdDialog.setAdClickListener(new c(this.a));
                createInterstitialAdDialog.showAndRender(this.a.n, this.a.f);
            } catch (Throwable th) {
            }
        }
    }
}
