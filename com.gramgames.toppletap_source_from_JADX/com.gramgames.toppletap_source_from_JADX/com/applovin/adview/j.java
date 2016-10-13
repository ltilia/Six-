package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

class j implements OnPreparedListener {
    final /* synthetic */ AppLovinInterstitialActivity a;

    j(AppLovinInterstitialActivity appLovinInterstitialActivity) {
        this.a = appLovinInterstitialActivity;
    }

    public void onPrepared(MediaPlayer mediaPlayer) {
        this.a.F = new WeakReference(mediaPlayer);
        int i = this.a.e() ? 0 : 1;
        mediaPlayer.setVolume((float) i, (float) i);
        i = mediaPlayer.getVideoWidth();
        int videoHeight = mediaPlayer.getVideoHeight();
        this.a.t = (int) TimeUnit.MILLISECONDS.toSeconds((long) mediaPlayer.getDuration());
        this.a.x.setVideoSize(i, videoHeight);
        mediaPlayer.setDisplay(this.a.x.getHolder());
        mediaPlayer.setOnErrorListener(new k(this));
        this.a.p();
        this.a.j();
        this.a.r();
        this.a.h();
    }
}
