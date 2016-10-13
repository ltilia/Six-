package com.vungle.publisher.display.view;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class WebViewFactory_Factory implements Factory<WebViewFactory> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<WebViewFactory> b;

    static {
        a = !WebViewFactory_Factory.class.desiredAssertionStatus();
    }

    public WebViewFactory_Factory(MembersInjector<WebViewFactory> webViewFactoryMembersInjector) {
        if (a || webViewFactoryMembersInjector != null) {
            this.b = webViewFactoryMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final WebViewFactory get() {
        return (WebViewFactory) MembersInjectors.injectMembers(this.b, new WebViewFactory());
    }

    public static Factory<WebViewFactory> create(MembersInjector<WebViewFactory> webViewFactoryMembersInjector) {
        return new WebViewFactory_Factory(webViewFactoryMembersInjector);
    }
}
