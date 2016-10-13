package com.vungle.publisher;

import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.protocol.TrackInstallHttpResponseHandler;
import dagger.MembersInjector;
import javax.inject.Provider;

public final class if implements MembersInjector<TrackInstallHttpResponseHandler> {
    static final /* synthetic */ boolean a;
    private final Provider<ScheduledPriorityExecutor> b;
    private final Provider<Factory> c;
    private final Provider<SdkState> d;

    static {
        a = !if.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        TrackInstallHttpResponseHandler trackInstallHttpResponseHandler = (TrackInstallHttpResponseHandler) obj;
        if (trackInstallHttpResponseHandler == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        gq.a(trackInstallHttpResponseHandler, this.b);
        gq.b(trackInstallHttpResponseHandler, this.c);
        trackInstallHttpResponseHandler.a = (SdkState) this.d.get();
    }

    private if(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<SdkState> provider3) {
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

    public static MembersInjector<TrackInstallHttpResponseHandler> a(Provider<ScheduledPriorityExecutor> provider, Provider<Factory> provider2, Provider<SdkState> provider3) {
        return new if(provider, provider2, provider3);
    }
}
