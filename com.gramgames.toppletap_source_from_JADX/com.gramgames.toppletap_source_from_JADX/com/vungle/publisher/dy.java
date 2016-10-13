package com.vungle.publisher;

import com.vungle.publisher.display.controller.AdWebChromeClient;
import com.vungle.publisher.display.controller.AdWebViewClient;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.WebViewFactory;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class dy implements MembersInjector<PostRollFragment> {
    static final /* synthetic */ boolean a;
    private final Provider<ek> b;
    private final Provider<AdWebViewClient> c;
    private final Provider<WebViewFactory> d;
    private final Provider<EventBus> e;
    private final Provider<AdWebChromeClient> f;

    static {
        a = !dy.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PostRollFragment postRollFragment = (PostRollFragment) obj;
        if (postRollFragment == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        postRollFragment.e = (ek) this.b.get();
        postRollFragment.f = (AdWebViewClient) this.c.get();
        postRollFragment.g = (WebViewFactory) this.d.get();
        postRollFragment.a = (EventBus) this.e.get();
        postRollFragment.b = (AdWebChromeClient) this.f.get();
    }

    private dy(Provider<ek> provider, Provider<AdWebViewClient> provider2, Provider<WebViewFactory> provider3, Provider<EventBus> provider4, Provider<AdWebChromeClient> provider5) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
                        if (a || provider5 != null) {
                            this.f = provider5;
                            return;
                        }
                        throw new AssertionError();
                    }
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<PostRollFragment> a(Provider<ek> provider, Provider<AdWebViewClient> provider2, Provider<WebViewFactory> provider3, Provider<EventBus> provider4, Provider<AdWebChromeClient> provider5) {
        return new dy(provider, provider2, provider3, provider4, provider5);
    }
}
