package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class n implements OnErrorListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    n(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.v.post(new o(this, i, i2));
        return true;
    }
}
