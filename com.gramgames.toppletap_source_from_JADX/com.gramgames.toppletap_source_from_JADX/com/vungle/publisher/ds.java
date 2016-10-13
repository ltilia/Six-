package com.vungle.publisher;

import com.vungle.publisher.display.controller.AdWebChromeClient;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ds implements MembersInjector<AdWebChromeClient> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;

    static {
        a = !ds.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdWebChromeClient adWebChromeClient = (AdWebChromeClient) obj;
        if (adWebChromeClient == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adWebChromeClient.a = (EventBus) this.b.get();
    }

    private ds(Provider<EventBus> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<AdWebChromeClient> a(Provider<EventBus> provider) {
        return new ds(provider);
    }
}
