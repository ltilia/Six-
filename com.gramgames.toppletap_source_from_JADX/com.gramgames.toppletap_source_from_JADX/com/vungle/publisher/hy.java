package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.RequestStreamingAdHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hy implements MembersInjector<RequestStreamingAdHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<EventBus> d;
    private final Provider<RequestStreamingAdResponse.Factory> e;

    static {
        a = !hy.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        RequestStreamingAdHttpResponseHandler requestStreamingAdHttpResponseHandler = (RequestStreamingAdHttpResponseHandler) obj;
        if (requestStreamingAdHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(requestStreamingAdHttpResponseHandler, this.b);
        gq.b(requestStreamingAdHttpResponseHandler, this.c);
        requestStreamingAdHttpResponseHandler.a = (EventBus) this.d.get();
        requestStreamingAdHttpResponseHandler.b = (RequestStreamingAdResponse.Factory) this.e.get();
    }

    private hy(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<RequestStreamingAdResponse.Factory> provider4) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                if (a || provider3 != null) {
                    this.d = provider3;
                    if (a || provider4 != null) {
                        this.e = provider4;
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

    public static MembersInjector<RequestStreamingAdHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventBus> provider3, Provider<RequestStreamingAdResponse.Factory> provider4) {
        return new hy(provider, provider2, provider3, provider4);
    }
}
