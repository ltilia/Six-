package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestAdResponse.CallToActionOverlay;
import com.vungle.publisher.protocol.message.RequestAdResponse.ThirdPartyAdTracking;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iw implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<CallToActionOverlay.Factory> b;
    private final Provider<ThirdPartyAdTracking.Factory> c;

    static {
        a = !iw.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (CallToActionOverlay.Factory) this.b.get();
        factory.b = (ThirdPartyAdTracking.Factory) this.c.get();
    }

    private iw(Provider<CallToActionOverlay.Factory> provider, Provider<ThirdPartyAdTracking.Factory> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<CallToActionOverlay.Factory> provider, Provider<ThirdPartyAdTracking.Factory> provider2) {
        return new iw(provider, provider2);
    }
}
