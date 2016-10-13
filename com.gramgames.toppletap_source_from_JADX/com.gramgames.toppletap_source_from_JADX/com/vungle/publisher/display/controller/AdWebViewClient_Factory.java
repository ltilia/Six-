package com.vungle.publisher.display.controller;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class AdWebViewClient_Factory implements Factory<AdWebViewClient> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<AdWebViewClient> b;

    static {
        a = !AdWebViewClient_Factory.class.desiredAssertionStatus();
    }

    public AdWebViewClient_Factory(MembersInjector<AdWebViewClient> adWebViewClientMembersInjector) {
        if (a || adWebViewClientMembersInjector != null) {
            this.b = adWebViewClientMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final AdWebViewClient get() {
        return (AdWebViewClient) MembersInjectors.injectMembers(this.b, new AdWebViewClient());
    }

    public static Factory<AdWebViewClient> create(MembersInjector<AdWebViewClient> adWebViewClientMembersInjector) {
        return new AdWebViewClient_Factory(adWebViewClientMembersInjector);
    }
}
