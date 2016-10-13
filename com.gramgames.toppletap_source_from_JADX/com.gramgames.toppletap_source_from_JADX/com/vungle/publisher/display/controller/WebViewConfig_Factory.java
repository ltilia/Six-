package com.vungle.publisher.display.controller;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class WebViewConfig_Factory implements Factory<WebViewConfig> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<WebViewConfig> b;

    static {
        a = !WebViewConfig_Factory.class.desiredAssertionStatus();
    }

    public WebViewConfig_Factory(MembersInjector<WebViewConfig> webViewConfigMembersInjector) {
        if (a || webViewConfigMembersInjector != null) {
            this.b = webViewConfigMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final WebViewConfig get() {
        return (WebViewConfig) MembersInjectors.injectMembers(this.b, new WebViewConfig());
    }

    public static Factory<WebViewConfig> create(MembersInjector<WebViewConfig> webViewConfigMembersInjector) {
        return new WebViewConfig_Factory(webViewConfigMembersInjector);
    }
}
