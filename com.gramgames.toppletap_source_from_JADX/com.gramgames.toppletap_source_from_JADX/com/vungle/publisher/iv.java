package com.vungle.publisher;

import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.protocol.message.RequestAd.Demographic;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo;
import com.vungle.publisher.protocol.message.RequestLocalAd.Factory;
import com.vungle.publisher.protocol.message.RequestLocalAd.HttpLogEntry;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iv implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<Demographic.Factory> b;
    private final Provider<ek> c;
    private final Provider<DeviceInfo.Factory> d;
    private final Provider<em> e;
    private final Provider<EventTrackingHttpLogEntry.Factory> f;
    private final Provider<HttpLogEntry.Factory> g;
    private final Provider<SdkState> h;

    static {
        a = !iv.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (Demographic.Factory) this.b.get();
        factory.b = (ek) this.c.get();
        factory.c = (DeviceInfo.Factory) this.d.get();
        factory.d = (em) this.e.get();
        factory.e = (EventTrackingHttpLogEntry.Factory) this.f.get();
        factory.f = (HttpLogEntry.Factory) this.g.get();
        factory.g = DoubleCheckLazy.create(this.h);
    }

    private iv(Provider<Demographic.Factory> provider, Provider<ek> provider2, Provider<DeviceInfo.Factory> provider3, Provider<em> provider4, Provider<EventTrackingHttpLogEntry.Factory> provider5, Provider<HttpLogEntry.Factory> provider6, Provider<SdkState> provider7) {
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
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
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
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<Demographic.Factory> provider, Provider<ek> provider2, Provider<DeviceInfo.Factory> provider3, Provider<em> provider4, Provider<EventTrackingHttpLogEntry.Factory> provider5, Provider<HttpLogEntry.Factory> provider6, Provider<SdkState> provider7) {
        return new iv(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
