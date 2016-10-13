package com.applovin.adview;

import android.media.MediaPlayer;
import android.media.MediaPlayer.OnErrorListener;

class k implements OnErrorListener {
    final /* synthetic */ j a;

    k(j jVar) {
        this.a = jVar;
    }

    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.a.a.v.post(new l(this, i, i2));
        return true;
    }
}
