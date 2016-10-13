package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.DisplayDimension;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class is implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<AdConfig> b;
    private final Provider<ek> c;
    private final Provider<DisplayDimension.Factory> d;
    private final Provider<fw> e;
    private final Provider<em> f;

    static {
        a = !is.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (AdConfig) this.b.get();
        factory.b = (ek) this.c.get();
        factory.c = (DisplayDimension.Factory) this.d.get();
        factory.d = (fw) this.e.get();
        factory.e = (em) this.f.get();
    }

    private is(Provider<AdConfig> provider, Provider<ek> provider2, Provider<DisplayDimension.Factory> provider3, Provider<fw> provider4, Provider<em> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<AdConfig> provider, Provider<ek> provider2, Provider<DisplayDimension.Factory> provider3, Provider<fw> provider4, Provider<em> provider5) {
        return new is(provider, provider2, provider3, provider4, provider5);
    }
}
