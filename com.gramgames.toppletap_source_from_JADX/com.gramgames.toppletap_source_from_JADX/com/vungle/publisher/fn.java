package com.vungle.publisher;

import com.vungle.publisher.location.AndroidLocation;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fn implements MembersInjector<AndroidLocation> {
    static final /* synthetic */ boolean a;
    private final Provider<fo> b;

    static {
        a = !fn.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AndroidLocation androidLocation = (AndroidLocation) obj;
        if (androidLocation == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        androidLocation.a = (fo) this.b.get();
    }

    private fn(Provider<fo> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AndroidLocation> a(Provider<fo> provider) {
        return new fn(provider);
    }
}
