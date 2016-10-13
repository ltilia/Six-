package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.LocalAd.Factory;
import com.vungle.publisher.db.model.LocalVideo;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cs implements MembersInjector<LocalVideo> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<LocalVideo.Factory> d;

    static {
        a = !cs.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalVideo localVideo = (LocalVideo) obj;
        if (localVideo == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localVideo.t = (DatabaseHelper) this.b.get();
        localVideo.c = (Factory) this.c.get();
        localVideo.d = (LocalVideo.Factory) this.d.get();
    }

    private cs(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalVideo.Factory> provider3) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    return;
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<LocalVideo> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<LocalVideo.Factory> provider3) {
        return new cs(provider, provider2, provider3);
    }
}
