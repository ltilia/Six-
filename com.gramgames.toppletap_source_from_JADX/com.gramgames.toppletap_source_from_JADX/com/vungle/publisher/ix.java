package com.vungle.publisher;

import com.vungle.publisher.protocol.message.ExtraInfo;
import com.vungle.publisher.protocol.message.RequestAd.Demographic;
import com.vungle.publisher.protocol.message.RequestAd.DeviceInfo;
import com.vungle.publisher.protocol.message.RequestStreamingAd.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ix implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<Demographic.Factory> b;
    private final Provider<ek> c;
    private final Provider<DeviceInfo.Factory> d;
    private final Provider<em> e;
    private final Provider<ExtraInfo.Factory> f;

    static {
        a = !ix.class.desiredAssertionStatus();
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
        factory.e = (ExtraInfo.Factory) this.f.get();
    }

    private ix(Provider<Demographic.Factory> provider, Provider<ek> provider2, Provider<DeviceInfo.Factory> provider3, Provider<em> provider4, Provider<ExtraInfo.Factory> provider5) {
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

    public static MembersInjector<Factory> a(Provider<Demographic.Factory> provider, Provider<ek> provider2, Provider<DeviceInfo.Factory> provider3, Provider<em> provider4, Provider<ExtraInfo.Factory> provider5) {
        return new ix(provider, provider2, provider3, provider4, provider5);
    }
}
