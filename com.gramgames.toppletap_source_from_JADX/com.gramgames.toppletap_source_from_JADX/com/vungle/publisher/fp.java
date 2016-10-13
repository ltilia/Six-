package com.vungle.publisher;

import android.content.Context;
import android.location.Location;
import com.vungle.log.Logger;
import com.vungle.publisher.inject.Injector;
import com.vungle.publisher.location.GoogleLocationClientDetailedLocationProvider;
import com.vungle.publisher.location.GoogleLocationServicesDetailedLocationProvider;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;

/* compiled from: vungle */
public final class fp implements fo {
    boolean a;
    boolean b;
    @Inject
    Context c;
    private final AtomicBoolean d;
    private Location e;
    private final fo f;
    private final fo g;

    @Inject
    public fp() {
        fo googleLocationClientDetailedLocationProvider;
        fo googleLocationServicesDetailedLocationProvider;
        this.a = true;
        this.b = true;
        this.d = new AtomicBoolean(false);
        Injector.b().a(this);
        try {
            googleLocationClientDetailedLocationProvider = new GoogleLocationClientDetailedLocationProvider(this.c);
        } catch (NoClassDefFoundError e) {
            Logger.i(Logger.LOCATION_TAG, "GoogleLocationClientDetailedLocationProvider not found: " + e);
            googleLocationClientDetailedLocationProvider = null;
        }
        try {
            googleLocationServicesDetailedLocationProvider = new GoogleLocationServicesDetailedLocationProvider(this.c);
        } catch (NoClassDefFoundError e2) {
            Logger.i(Logger.LOCATION_TAG, "GoogleLocationServicesDetailedLocationProvider not found: " + e2);
            googleLocationServicesDetailedLocationProvider = null;
        }
        this.f = googleLocationClientDetailedLocationProvider;
        this.g = googleLocationServicesDetailedLocationProvider;
    }

    public final Location b() {
        if (this.d.compareAndSet(false, true)) {
            Object obj = null;
            try {
                if (this.b) {
                    obj = this.g;
                    if (obj != null) {
                        this.e = obj.b();
                    }
                }
            } catch (Throwable e) {
                Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, e);
                this.b = false;
            } catch (Throwable e2) {
                Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, e2);
            }
            if (this.a && this.e == null) {
                try {
                    fo foVar = this.f;
                    if (foVar != null) {
                        this.e = foVar.b();
                    }
                } catch (Throwable e22) {
                    Logger.i(Logger.LOCATION_TAG, "permanent error obtaining detailed location " + obj, e22);
                    this.a = false;
                } catch (Throwable e222) {
                    Logger.i(Logger.LOCATION_TAG, "error obtaining detailed location " + obj, e222);
                }
            }
        }
        return this.e;
    }
}
