package com.vungle.publisher.audio;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Handler;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.event.VolumeChangeEvent;
import com.vungle.publisher.ad.event.VolumeChangeEvent.Factory;
import com.vungle.publisher.device.AudioHelper;
import com.vungle.publisher.event.EventBus;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class VolumeChangeContentObserver extends ContentObserver {
    private static final Handler g;
    public volatile int a;
    public boolean b;
    @Inject
    public AudioHelper c;
    @Inject
    public Factory d;
    @Inject
    public EventBus e;
    @Inject
    public Context f;

    static {
        g = new Handler();
    }

    @Inject
    VolumeChangeContentObserver() {
        super(g);
        this.b = false;
    }

    public void onChange(boolean selfChange) {
        try {
            super.onChange(selfChange);
            int i = this.a;
            int b = this.c.b();
            this.a = b;
            if (b != i) {
                Logger.v(Logger.DEVICE_TAG, "volume changed " + i + " --> " + b);
                EventBus eventBus = this.e;
                Factory factory = this.d;
                VolumeChangeEvent volumeChangeEvent = new VolumeChangeEvent();
                volumeChangeEvent.b = factory.a.b();
                volumeChangeEvent.d = factory.a.c();
                volumeChangeEvent.a = i;
                volumeChangeEvent.c = factory.a.a((float) i);
                eventBus.a(volumeChangeEvent);
            }
        } catch (Throwable e) {
            Logger.e(Logger.DEVICE_TAG, e);
        }
    }
}
