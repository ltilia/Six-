package com.vungle.publisher;

import com.vungle.publisher.net.http.DownloadHttpResponseHandler;
import com.vungle.publisher.net.http.DownloadHttpResponseHandler.Factory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ge implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DownloadHttpResponseHandler> b;

    static {
        a = !ge.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
    }

    private ge(Provider<DownloadHttpResponseHandler> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<DownloadHttpResponseHandler> provider) {
        return new ge(provider);
    }
}
