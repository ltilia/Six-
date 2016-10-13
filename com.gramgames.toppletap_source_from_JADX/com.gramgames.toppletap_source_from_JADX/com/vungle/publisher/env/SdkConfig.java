package com.vungle.publisher.env;

import android.content.Context;
import android.content.SharedPreferences;
import com.vungle.log.Logger;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.event.ClientEventListenerAdapter;
import com.vungle.publisher.event.ClientEventListenerAdapter.Factory;
import com.vungle.publisher.ey;
import com.vungle.publisher.fv;
import com.vungle.publisher.inject.annotations.EnvSharedPreferences;
import com.vungle.publisher.jj;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class SdkConfig {
    public final Map<EventListener, ey> a;
    public boolean b;
    public final Set<fv> c;
    public int d;
    public String e;
    public long f;
    public long g;
    @Inject
    public Factory h;
    @Inject
    public Context i;
    @Inject
    @EnvSharedPreferences
    public SharedPreferences j;

    @Inject
    SdkConfig() {
        this.a = new HashMap();
        this.c = EnumSet.noneOf(fv.class);
        this.e = "isExceptionReportingEnabled";
    }

    public final void a(EventListener... eventListenerArr) {
        if (eventListenerArr != null) {
            for (EventListener eventListener : eventListenerArr) {
                if (eventListener == null) {
                    Logger.d(Logger.EVENT_TAG, "ignoring add null event listener");
                } else {
                    if ((!this.a.containsKey(eventListener) ? 1 : null) != null) {
                        Logger.d(Logger.EVENT_TAG, "adding event listener " + eventListener);
                        ClientEventListenerAdapter clientEventListenerAdapter = (ClientEventListenerAdapter) this.h.a.get();
                        clientEventListenerAdapter.a = eventListener;
                        this.a.put(eventListener, clientEventListenerAdapter);
                        clientEventListenerAdapter.registerSticky();
                    } else {
                        Logger.d(Logger.EVENT_TAG, "already added event listener " + eventListener);
                    }
                }
            }
        }
    }

    public final void a() {
        for (ey unregister : this.a.values()) {
            unregister.unregister();
        }
        this.a.clear();
    }

    public final void a(fv... fvVarArr) {
        Logger.d(Logger.CONFIG_TAG, "setting ad streaming connectivity types " + jj.b(fvVarArr));
        this.c.clear();
        if (fvVarArr != null) {
            for (Object obj : fvVarArr) {
                if (obj != null) {
                    this.c.add(obj);
                }
            }
        }
    }

    public final boolean b() {
        Logger.d(Logger.CONFIG_TAG, "isExceptionReportingEnabled: " + this.j.getBoolean(this.e, false));
        return this.j.getBoolean(this.e, false);
    }

    public final void a(long j) {
        Logger.d(Logger.CONFIG_TAG, "setting last app fingerprint timestamp to " + j);
        this.f = j;
    }
}
