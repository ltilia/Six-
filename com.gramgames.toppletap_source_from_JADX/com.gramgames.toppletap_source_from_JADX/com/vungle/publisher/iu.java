package com.vungle.publisher;

import com.vungle.publisher.protocol.message.RequestConfig;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class iu implements MembersInjector<RequestConfig> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<em> c;

    static {
        a = !iu.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestConfig requestConfig = (RequestConfig) obj;
        if (requestConfig == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        requestConfig.a = (ek) this.b.get();
        requestConfig.b = (em) this.c.get();
    }

    private iu(Provider<ek> provider, Provider<em> provider2) {
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

    public static MembersInjector<RequestConfig> a(Provider<ek> provider, Provider<em> provider2) {
        return new iu(provider, provider2);
    }
}
