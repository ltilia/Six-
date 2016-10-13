package com.vungle.publisher.location;

import android.location.Location;
import com.vungle.log.Logger;
import com.vungle.publisher.fo;
import com.vungle.publisher.ft;
import com.vungle.publisher.inject.Injector;
import gs.gram.mopub.BuildConfig;
import java.util.Locale;
import java.util.TimeZone;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class AndroidLocation implements ft {
    @Inject
    public fo a;

    @Inject
    public AndroidLocation() {
        Injector.b().a(this);
    }

    public final String a() {
        String str = BuildConfig.FLAVOR;
        try {
            str = Locale.getDefault().getISO3Language();
        } catch (Throwable e) {
            Logger.w(Logger.LOCATION_TAG, "error getting ISO 3-letter language code", e);
        }
        return str;
    }

    public final Location b() {
        Location location = null;
        if (this.a == null) {
            Logger.d(Logger.LOCATION_TAG, "cannot provide detailed location - null detailed location provider");
        } else {
            synchronized (this) {
                location = this.a.b();
            }
        }
        return location;
    }

    public final String c() {
        return TimeZone.getDefault().getID();
    }
}
