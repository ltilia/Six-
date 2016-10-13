package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.EventTracking;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAd.Factory;
import com.vungle.publisher.db.model.LocalArchive;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.db.model.Viewable;
import com.vungle.publisher.event.EventBus;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class ch implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<EventTracking.Factory> c;
    private final Provider<EventBus> d;
    private final Provider<String> e;
    private final Provider<LocalArchive.Factory> f;
    private final Provider<Viewable.Factory> g;
    private final Provider<LocalAd> h;
    private final Provider<LocalVideo.Factory> i;
    private final Provider<ScheduledPriorityExecutor> j;

    static {
        a = !ch.class.desiredAssertionStatus();
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
        factory.e = (LocalArchive.Factory) this.f.get();
        factory.f = (Viewable.Factory) this.g.get();
        factory.g = this.h;
        factory.h = (LocalVideo.Factory) this.i.get();
        factory.i = (ScheduledPriorityExecutor) this.j.get();
    }

    private ch(Provider<DatabaseHelper> provider, Provider<EventTracking.Factory> provider2, Provider<EventBus> provider3, Provider<String> provider4, Provider<LocalArchive.Factory> provider5, Provider<Viewable.Factory> provider6, Provider<LocalAd> provider7, Provider<LocalVideo.Factory> provider8, Provider<ScheduledPriorityExecutor> provider9) {
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
                            if (a || provider6 != null) {
                                this.g = provider6;
                                if (a || provider7 != null) {
                                    this.h = provider7;
                                    if (a || provider8 != null) {
                                        this.i = provider8;
                                        if (a || provider9 != null) {
                                            this.j = provider9;
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
                    throw new AssertionError();
                }
                throw new AssertionError();
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<Factory> a(Provider<DatabaseHelper> provider, Provider<EventTracking.Factory> provider2, Provider<EventBus> provider3, Provider<String> provider4, Provider<LocalArchive.Factory> provider5, Provider<Viewable.Factory> provider6, Provider<LocalAd> provider7, Provider<LocalVideo.Factory> provider8, Provider<ScheduledPriorityExecutor> provider9) {
        return new ch(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9);
    }
}
