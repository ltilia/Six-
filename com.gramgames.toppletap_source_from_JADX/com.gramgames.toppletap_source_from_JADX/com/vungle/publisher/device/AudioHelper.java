package com.vungle.publisher.device;

import android.media.AudioManager;
import com.google.android.gms.games.stats.PlayerStats;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AudioHelper {
    @Inject
    public AudioManager a;

    public final int a() {
        return this.a.getStreamMaxVolume(3);
    }

    public final int b() {
        return this.a.getStreamVolume(3);
    }

    public final float c() {
        return a((float) b());
    }

    public final float a(float f) {
        int a = a();
        return a == 0 ? PlayerStats.UNSET_VALUE : f / ((float) a);
    }

    public final void a(boolean z) {
        this.a.setStreamMute(3, !z);
    }
}
