package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.AdAvailabilityEventListener;
import com.vungle.publisher.ad.AdManager.PlayAdEventListener;
import com.vungle.publisher.ad.AdManager.PrepareStreamingAdEventListener;
import com.vungle.publisher.ad.AdPreparer;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LocalAd.Factory;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.Viewable;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import dagger.MembersInjector;
import dagger.internal.DoubleCheckLazy;
import javax.inject.Provider;

public final class e implements MembersInjector<AdManager> {
    static final /* synthetic */ boolean a;
    private final Provider<AdPreparer> b;
    private final Provider<Context> c;
    private final Provider<ek> d;
    private final Provider<EventBus> e;
    private final Provider<Class> f;
    private final Provider<ScheduledPriorityExecutor> g;
    private final Provider<Factory> h;
    private final Provider<fw> i;
    private final Provider<PlayAdEventListener> j;
    private final Provider<AdAvailabilityEventListener> k;
    private final Provider<PrepareStreamingAdEventListener> l;
    private final Provider<ProtocolHttpGateway> m;
    private final Provider<SdkConfig> n;
    private final Provider<StreamingAd.Factory> o;
    private final Provider<Viewable.Factory> p;
    private final Provider<SdkState> q;
    private final Provider<LoggedException.Factory> r;

    static {
        a = !e.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        AdManager adManager = (AdManager) obj;
        if (adManager == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        adManager.a = (AdPreparer) this.b.get();
        adManager.b = (Context) this.c.get();
        adManager.c = (ek) this.d.get();
        adManager.d = (EventBus) this.e.get();
        adManager.e = (Class) this.f.get();
        adManager.f = (ScheduledPriorityExecutor) this.g.get();
        adManager.g = (Factory) this.h.get();
        adManager.h = (fw) this.i.get();
        adManager.i = DoubleCheckLazy.create(this.j);
        adManager.j = DoubleCheckLazy.create(this.k);
        adManager.k = this.l;
        adManager.l = (ProtocolHttpGateway) this.m.get();
        adManager.m = (SdkConfig) this.n.get();
        adManager.n = (StreamingAd.Factory) this.o.get();
        adManager.o = (Viewable.Factory) this.p.get();
        adManager.p = DoubleCheckLazy.create(this.q);
        adManager.q = (LoggedException.Factory) this.r.get();
    }

    private e(Provider<AdPreparer> provider, Provider<Context> provider2, Provider<ek> provider3, Provider<EventBus> provider4, Provider<Class> provider5, Provider<ScheduledPriorityExecutor> provider6, Provider<Factory> provider7, Provider<fw> provider8, Provider<PlayAdEventListener> provider9, Provider<AdAvailabilityEventListener> provider10, Provider<PrepareStreamingAdEventListener> provider11, Provider<ProtocolHttpGateway> provider12, Provider<SdkConfig> provider13, Provider<StreamingAd.Factory> provider14, Provider<Viewable.Factory> provider15, Provider<SdkState> provider16, Provider<LoggedException.Factory> provider17) {
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
                                            if (a || provider10 != null) {
                                                this.k = provider10;
                                                if (a || provider11 != null) {
                                                    this.l = provider11;
                                                    if (a || provider12 != null) {
                                                        this.m = provider12;
                                                        if (a || provider13 != null) {
                                                            this.n = provider13;
                                                            if (a || provider14 != null) {
                                                                this.o = provider14;
                                                                if (a || provider15 != null) {
                                                                    this.p = provider15;
                                                                    if (a || provider16 != null) {
                                                                        this.q = provider16;
                                                                        if (a || provider17 != null) {
                                                                            this.r = provider17;
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

    public static MembersInjector<AdManager> a(Provider<AdPreparer> provider, Provider<Context> provider2, Provider<ek> provider3, Provider<EventBus> provider4, Provider<Class> provider5, Provider<ScheduledPriorityExecutor> provider6, Provider<Factory> provider7, Provider<fw> provider8, Provider<PlayAdEventListener> provider9, Provider<AdAvailabilityEventListener> provider10, Provider<PrepareStreamingAdEventListener> provider11, Provider<ProtocolHttpGateway> provider12, Provider<SdkConfig> provider13, Provider<StreamingAd.Factory> provider14, Provider<Viewable.Factory> provider15, Provider<SdkState> provider16, Provider<LoggedException.Factory> provider17) {
        return new e(provider, provider2, provider3, provider4, provider5, provider6, provider7, provider8, provider9, provider10, provider11, provider12, provider13, provider14, provider15, provider16, provider17);
    }
}
