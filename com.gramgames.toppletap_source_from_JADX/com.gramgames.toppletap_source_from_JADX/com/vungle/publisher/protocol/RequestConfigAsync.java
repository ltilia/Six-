package com.vungle.publisher.protocol;

import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.bo;
import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestConfigAsync extends bo {
    @Inject
    public RequestConfigRunnable b;

    @Singleton
    /* compiled from: vungle */
    public static class RequestConfigRunnable implements Runnable {
        @Inject
        public ProtocolHttpGateway a;

        @Inject
        RequestConfigRunnable() {
        }

        public void run() {
            this.a.a();
        }
    }

    /* compiled from: vungle */
    public final class RequestConfigRunnable_Factory implements Factory<RequestConfigRunnable> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<RequestConfigRunnable> b;

        static {
            a = !RequestConfigRunnable_Factory.class.desiredAssertionStatus();
        }

        public RequestConfigRunnable_Factory(MembersInjector<RequestConfigRunnable> requestConfigRunnableMembersInjector) {
            if (a || requestConfigRunnableMembersInjector != null) {
                this.b = requestConfigRunnableMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final RequestConfigRunnable get() {
            return (RequestConfigRunnable) MembersInjectors.injectMembers(this.b, new RequestConfigRunnable());
        }

        public static Factory<RequestConfigRunnable> create(MembersInjector<RequestConfigRunnable> requestConfigRunnableMembersInjector) {
            return new RequestConfigRunnable_Factory(requestConfigRunnableMembersInjector);
        }
    }

    @Inject
    RequestConfigAsync() {
    }

    protected final b b() {
        return b.requestConfig;
    }

    protected final Runnable a() {
        return this.b;
    }
}
