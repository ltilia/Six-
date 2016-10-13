package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.display.view.DisplayUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class du implements MembersInjector<DisplayUtils> {
    static final /* synthetic */ boolean a;
    private final Provider<Context> b;

    static {
        a = !du.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        DisplayUtils displayUtils = (DisplayUtils) obj;
        if (displayUtils == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        displayUtils.a = (Context) this.b.get();
    }

    private du(Provider<Context> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<DisplayUtils> a(Provider<Context> provider) {
        return new du(provider);
    }
}
