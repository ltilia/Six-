package com.vungle.publisher;

import com.vungle.publisher.protocol.message.ExtraInfo;
import com.vungle.publisher.protocol.message.ReportLocalAd.Factory;
import com.vungle.publisher.protocol.message.ReportLocalAd.Factory.PlayFactory;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class im implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<ExtraInfo.Factory> b;
    private final Provider<PlayFactory> c;
    private final Provider<RequestLocalAd.Factory> d;

    static {
        a = !im.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = (ExtraInfo.Factory) this.b.get();
        factory.b = (PlayFactory) this.c.get();
        factory.c = (RequestLocalAd.Factory) this.d.get();
    }

    private im(Provider<ExtraInfo.Factory> provider, Provider<PlayFactory> provider2, Provider<RequestLocalAd.Factory> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<ExtraInfo.Factory> provider, Provider<PlayFactory> provider2, Provider<RequestLocalAd.Factory> provider3) {
        return new im(provider, provider2, provider3);
    }
}
