package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;

class m implements OnCompletionListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    m(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        this.a.m = true;
        this.a.w();
    }
}
