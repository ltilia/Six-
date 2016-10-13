package com.vungle.publisher;

import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.protocol.ReportStreamingAdHttpRequest.Factory;
import com.vungle.publisher.protocol.message.ReportStreamingAd;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hm implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<ft> c;
    private final Provider<em> d;
    private final Provider<String> e;
    private final Provider<WrapperFramework> f;
    private final Provider<String> g;
    private final Provider<ReportStreamingAd.Factory> h;

    static {
        a = !hm.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gm.a(factory, this.b);
        factory.a = (ft) this.c.get();
        factory.c = (em) this.d.get();
        factory.d = (String) this.e.get();
        factory.e = (WrapperFramework) this.f.get();
        factory.f = (String) this.g.get();
        factory.g = (ReportStreamingAd.Factory) this.h.get();
    }

    private hm(Provider<ek> provider, Provider<ft> provider2, Provider<em> provider3, Provider<String> provider4, Provider<WrapperFramework> provider5, Provider<String> provider6, Provider<ReportStreamingAd.Factory> provider7) {
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

    public static MembersInjector<Factory> a(Provider<ek> provider, Provider<ft> provider2, Provider<em> provider3, Provider<String> provider4, Provider<WrapperFramework> provider5, Provider<String> provider6, Provider<ReportStreamingAd.Factory> provider7) {
        return new hm(provider, provider2, provider3, provider4, provider5, provider6, provider7);
    }
}
