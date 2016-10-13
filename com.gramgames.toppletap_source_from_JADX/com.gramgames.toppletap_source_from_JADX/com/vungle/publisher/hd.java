package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.TrackEventHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class hd implements MembersInjector<TrackEventHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<EventTrackingHttpLogEntry.Factory> d;

    static {
        a = !hd.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        TrackEventHttpResponseHandler trackEventHttpResponseHandler = (TrackEventHttpResponseHandler) obj;
        if (trackEventHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        trackEventHttpResponseHandler.f = (ScheduledPriorityExecutor) this.b.get();
        trackEventHttpResponseHandler.g = (Factory) this.c.get();
        trackEventHttpResponseHandler.b = (EventTrackingHttpLogEntry.Factory) this.d.get();
    }

    private hd(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventTrackingHttpLogEntry.Factory> provider3) {
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

    public static MembersInjector<TrackEventHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<EventTrackingHttpLogEntry.Factory> provider3) {
        return new hd(provider, provider2, provider3);
    }
}
