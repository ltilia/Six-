package com.vungle.publisher;

import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.image.BitmapFactory;
import com.vungle.publisher.util.ViewUtils;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class jl implements MembersInjector<ViewUtils> {
    static final /* synthetic */ boolean a;
    private final Provider<BitmapFactory> b;
    private final Provider<Factory> c;

    static {
        a = !jl.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        ViewUtils viewUtils = (ViewUtils) obj;
        if (viewUtils == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        viewUtils.a = (BitmapFactory) this.b.get();
        viewUtils.b = (Factory) this.c.get();
    }

    private jl(Provider<BitmapFactory> provider, Provider<Factory> provider2) {
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

    public static MembersInjector<ViewUtils> a(Provider<BitmapFactory> provider, Provider<Factory> provider2) {
        return new jl(provider, provider2);
    }
}
