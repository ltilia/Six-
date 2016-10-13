package com.vungle.publisher.display.controller;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdWebChromeClient_Factory implements Factory<AdWebChromeClient> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdWebChromeClient> b;

    static {
        a = !AdWebChromeClient_Factory.class.desiredAssertionStatus();
    }

    public AdWebChromeClient_Factory(MembersInjector<AdWebChromeClient> adWebChromeClientMembersInjector) {
        if (a || adWebChromeClientMembersInjector != null) {
            this.b = adWebChromeClientMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdWebChromeClient get() {
        return (AdWebChromeClient) MembersInjectors.injectMembers(this.b, new AdWebChromeClient());
    }

    public static Factory<AdWebChromeClient> create(MembersInjector<AdWebChromeClient> adWebChromeClientMembersInjector) {
        return new AdWebChromeClient_Factory(adWebChromeClientMembersInjector);
    }
}
