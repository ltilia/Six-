package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.event.EventBus;
import javax.inject.Inject;

/* compiled from: vungle */
public class et implements ey {
    private boolean a;
    @Inject
    protected EventBus h;

    public void register() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.h.b(this);
        this.a = true;
    }

    public void registerSticky() {
        if (this.a) {
            Logger.w(Logger.EVENT_TAG, getClass().getName() + " already listening sticky");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening sticky");
        this.h.a.a((Object) this, "onEvent", true);
        this.a = true;
    }

    public void unregister() {
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " unregistered");
        this.h.a.a((Object) this);
        this.a = false;
    }

    public void registerOnce() {
        if (this.a) {
            Logger.v(Logger.EVENT_TAG, getClass().getName() + " already listening");
            return;
        }
        Logger.d(Logger.EVENT_TAG, getClass().getName() + " listening");
        this.h.b(this);
        this.a = true;
    }
}
