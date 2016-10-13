package com.vungle.publisher;

import com.vungle.publisher.display.controller.AdWebViewClient;
import com.vungle.publisher.display.controller.WebViewConfig;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dt implements MembersInjector<AdWebViewClient> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<WebViewConfig> c;

    static {
        a = !dt.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdWebViewClient adWebViewClient = (AdWebViewClient) obj;
        if (adWebViewClient == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adWebViewClient.a = (EventBus) this.b.get();
        adWebViewClient.b = (WebViewConfig) this.c.get();
    }

    private dt(Provider<EventBus> provider, Provider<WebViewConfig> provider2) {
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

    public static MembersInjector<AdWebViewClient> a(Provider<EventBus> provider, Provider<WebViewConfig> provider2) {
        return new dt(provider, provider2);
    }
}
