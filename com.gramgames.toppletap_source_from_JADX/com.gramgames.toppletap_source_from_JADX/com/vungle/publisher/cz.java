package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.EventTracking;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.StreamingAd.Factory;
import com.vungle.publisher.db.model.StreamingVideo;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cz implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<EventTracking.Factory> c;
    private final Provider<EventBus> d;
    private final Provider<StreamingAd> e;
    private final Provider<StreamingVideo.Factory> f;

    static {
        a = !cz.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.c = (DatabaseHelper) this.b.get();
        factory.a = (EventTracking.Factory) this.c.get();
        factory.b = (EventBus) this.d.get();
        factory.d = this.e;
        factory.e = (StreamingVideo.Factory) this.f.get();
    }

    private cz(Provider<DatabaseHelper> provider, Provider<EventTracking.Factory> provider2, Provider<EventBus> provider3, Provider<StreamingAd> provider4, Provider<StreamingVideo.Factory> provider5) {
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

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<EventTracking.Factory> provider2, Provider<EventBus> provider3, Provider<StreamingAd> provider4, Provider<StreamingVideo.Factory> provider5) {
        return new cz(provider, provider2, provider3, provider4, provider5);
    }
}
