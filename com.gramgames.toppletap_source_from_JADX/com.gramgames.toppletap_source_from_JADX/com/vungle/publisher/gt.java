package com.vungle.publisher;

import com.vungle.publisher.net.http.HttpRequestChainElement;
import com.vungle.publisher.net.http.HttpResponse.Factory;
import com.vungle.publisher.net.http.HttpTransport;
import com.vungle.publisher.net.http.HttpURLConnectionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class gt implements MembersInjector<HttpTransport> {
    static final /* synthetic */ boolean a;
    private final Provider<HttpURLConnectionFactory> b;
    private final Provider<Factory> c;
    private final Provider<HttpRequestChainElement.Factory> d;

    static {
        a = !gt.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        HttpTransport httpTransport = (HttpTransport) obj;
        if (httpTransport == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        httpTransport.a = (HttpURLConnectionFactory) this.b.get();
        httpTransport.b = (Factory) this.c.get();
        httpTransport.c = (HttpRequestChainElement.Factory) this.d.get();
    }

    private gt(Provider<HttpURLConnectionFactory> provider, Provider<Factory> provider2, Provider<HttpRequestChainElement.Factory> provider3) {
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

    public static MembersInjector<HttpTransport> a(Provider<HttpURLConnectionFactory> provider, Provider<Factory> provider2, Provider<HttpRequestChainElement.Factory> provider3) {
        return new gt(provider, provider2, provider3);
    }
}
