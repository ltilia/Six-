package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.RequestConfigAsync;
import com.vungle.publisher.protocol.message.RequestConfigResponse;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hs implements MembersInjector<hr> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<RequestConfigResponse.Factory> d;
    private final Provider<SdkConfig> e;
    private final Provider<EventBus> f;
    private final Provider<RequestConfigAsync> g;

    static {
        a = !hs.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        hr hrVar = (hr) obj;
        if (hrVar == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(hrVar, this.b);
        gq.b(hrVar, this.c);
        hrVar.a = (RequestConfigResponse.Factory) this.d.get();
        hrVar.b = (SdkConfig) this.e.get();
        hrVar.c = (EventBus) this.f.get();
        hrVar.d = this.g;
    }

    private hs(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<RequestConfigResponse.Factory> provider3, Provider<SdkConfig> provider4, Provider<EventBus> provider5, Provider<RequestConfigAsync> provider6) {
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

    public static MembersInjector<hr> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<RequestConfigResponse.Factory> provider3, Provider<SdkConfig> provider4, Provider<EventBus> provider5, Provider<RequestConfigAsync> provider6) {
        return new hs(provider, provider2, provider3, provider4, provider5, provider6);
    }
}
