package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.display.view.WebViewFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ef implements MembersInjector<WebViewFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;

    static {
        a = !ef.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        WebViewFactory webViewFactory = (WebViewFactory) obj;
        if (webViewFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        webViewFactory.a = (Context) this.b.get();
    }

    private ef(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<WebViewFactory> a(Provider<Context> provider) {
        return new ef(provider);
    }
}
