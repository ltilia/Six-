package com.vungle.publisher;

import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.protocol.ProtocolHttpGateway;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class InitializationEventListener_MembersInjector implements MembersInjector<InitializationEventListener> {
    static final /* synthetic */ boolean a;
    private final Provider<EventBus> b;
    private final Provider<AdManager> c;
    private final Provider<ScheduledPriorityExecutor> d;
    private final Provider<ProtocolHttpGateway> e;
    private final Provider<AdReportManager> f;
    private final Provider<InitialConfigUpdatedEventListener> g;
    private final Provider<GlobalEventListener> h;
    private final Provider<SdkState> i;

    static {
        a = !InitializationEventListener_MembersInjector.class.desiredAssertionStatus();
    }

    public InitializationEventListener_MembersInjector(Provider<EventBus> eventBusProvider, Provider<AdManager> adManagerProvider, Provider<ScheduledPriorityExecutor> executorProvider, Provider<ProtocolHttpGateway> protocolHttpGatewayProvider, Provider<AdReportManager> reportManagerProvider, Provider<InitialConfigUpdatedEventListener> initialConfigUpdatedEventListenerProvider, Provider<GlobalEventListener> globalEventListenerProvider, Provider<SdkState> sdkStateProvider) {
        if (a || eventBusProvider != null) {
            this.b = eventBusProvider;
            if (a || adManagerProvider != null) {
                this.c = adManagerProvider;
                if (a || executorProvider != null) {
                    this.d = executorProvider;
                    if (a || protocolHttpGatewayProvider != null) {
                        this.e = protocolHttpGatewayProvider;
                        if (a || reportManagerProvider != null) {
                            this.f = reportManagerProvider;
                            if (a || initialConfigUpdatedEventListenerProvider != null) {
                                this.g = initialConfigUpdatedEventListenerProvider;
                                if (a || globalEventListenerProvider != null) {
                                    this.h = globalEventListenerProvider;
                                    if (a || sdkStateProvider != null) {
                                        this.i = sdkStateProvider;
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

    public static MembersInjector<InitializationEventListener> create(Provider<EventBus> eventBusProvider, Provider<AdManager> adManagerProvider, Provider<ScheduledPriorityExecutor> executorProvider, Provider<ProtocolHttpGateway> protocolHttpGatewayProvider, Provider<AdReportManager> reportManagerProvider, Provider<InitialConfigUpdatedEventListener> initialConfigUpdatedEventListenerProvider, Provider<GlobalEventListener> globalEventListenerProvider, Provider<SdkState> sdkStateProvider) {
        return new InitializationEventListener_MembersInjector(eventBusProvider, adManagerProvider, executorProvider, protocolHttpGatewayProvider, reportManagerProvider, initialConfigUpdatedEventListenerProvider, globalEventListenerProvider, sdkStateProvider);
    }

    public final void injectMembers(InitializationEventListener instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        eu.a(instance, this.b);
        instance.a = (AdManager) this.c.get();
        instance.b = (ScheduledPriorityExecutor) this.d.get();
        instance.c = (ProtocolHttpGateway) this.e.get();
        instance.d = (AdReportManager) this.f.get();
        instance.e = (InitialConfigUpdatedEventListener) this.g.get();
        instance.f = (GlobalEventListener) this.h.get();
        instance.g = (SdkState) this.i.get();
    }

    public static void injectAdManager(InitializationEventListener instance, Provider<AdManager> adManagerProvider) {
        instance.a = (AdManager) adManagerProvider.get();
    }

    public static void injectExecutor(InitializationEventListener instance, Provider<ScheduledPriorityExecutor> executorProvider) {
        instance.b = (ScheduledPriorityExecutor) executorProvider.get();
    }

    public static void injectProtocolHttpGateway(InitializationEventListener instance, Provider<ProtocolHttpGateway> protocolHttpGatewayProvider) {
        instance.c = (ProtocolHttpGateway) protocolHttpGatewayProvider.get();
    }

    public static void injectReportManager(InitializationEventListener instance, Provider<AdReportManager> reportManagerProvider) {
        instance.d = (AdReportManager) reportManagerProvider.get();
    }

    public static void injectInitialConfigUpdatedEventListener(InitializationEventListener instance, Provider<InitialConfigUpdatedEventListener> initialConfigUpdatedEventListenerProvider) {
        instance.e = (InitialConfigUpdatedEventListener) initialConfigUpdatedEventListenerProvider.get();
    }

    public static void injectGlobalEventListener(InitializationEventListener instance, Provider<GlobalEventListener> globalEventListenerProvider) {
        instance.f = (GlobalEventListener) globalEventListenerProvider.get();
    }

    public static void injectSdkState(InitializationEventListener instance, Provider<SdkState> sdkStateProvider) {
        instance.g = (SdkState) sdkStateProvider.get();
    }
}
