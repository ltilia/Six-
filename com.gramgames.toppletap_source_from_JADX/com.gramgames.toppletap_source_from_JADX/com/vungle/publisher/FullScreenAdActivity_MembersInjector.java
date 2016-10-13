package com.vungle.publisher;

import com.vungle.publisher.FullScreenAdActivity.AdEventListener.Factory;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.util.IntentFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class FullScreenAdActivity_MembersInjector implements MembersInjector<FullScreenAdActivity> {
    static final /* synthetic */ boolean a;
    private final Provider<Factory> b;
    private final Provider<AdManager> c;
    private final Provider<AdReportEventListener.Factory> d;
    private final Provider<ek> e;
    private final Provider<EventBus> f;
    private final Provider<VideoFragment.Factory> g;
    private final Provider<PostRollFragment.Factory> h;
    private final Provider<SdkState> i;
    private final Provider<IntentFactory> j;
    private final Provider<LoggedException.Factory> k;
    private final Provider<WrapperFramework> l;

    static {
        a = !FullScreenAdActivity_MembersInjector.class.desiredAssertionStatus();
    }

    public FullScreenAdActivity_MembersInjector(Provider<Factory> adEventListenerFactoryProvider, Provider<AdManager> adManagerProvider, Provider<AdReportEventListener.Factory> adReportEventListenerFactoryProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<VideoFragment.Factory> videoFragmentFactoryProvider, Provider<PostRollFragment.Factory> postRollFragmentFactoryProvider, Provider<SdkState> sdkStateProvider, Provider<IntentFactory> intentFactoryProvider, Provider<LoggedException.Factory> loggedExceptionFactoryProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
        if (a || adEventListenerFactoryProvider != null) {
            this.b = adEventListenerFactoryProvider;
            if (a || adManagerProvider != null) {
                this.c = adManagerProvider;
                if (a || adReportEventListenerFactoryProvider != null) {
                    this.d = adReportEventListenerFactoryProvider;
                    if (a || deviceProvider != null) {
                        this.e = deviceProvider;
                        if (a || eventBusProvider != null) {
                            this.f = eventBusProvider;
                            if (a || videoFragmentFactoryProvider != null) {
                                this.g = videoFragmentFactoryProvider;
                                if (a || postRollFragmentFactoryProvider != null) {
                                    this.h = postRollFragmentFactoryProvider;
                                    if (a || sdkStateProvider != null) {
                                        this.i = sdkStateProvider;
                                        if (a || intentFactoryProvider != null) {
                                            this.j = intentFactoryProvider;
                                            if (a || loggedExceptionFactoryProvider != null) {
                                                this.k = loggedExceptionFactoryProvider;
                                                if (a || wrapperFrameworkProvider != null) {
                                                    this.l = wrapperFrameworkProvider;
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

    public static MembersInjector<FullScreenAdActivity> create(Provider<Factory> adEventListenerFactoryProvider, Provider<AdManager> adManagerProvider, Provider<AdReportEventListener.Factory> adReportEventListenerFactoryProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<VideoFragment.Factory> videoFragmentFactoryProvider, Provider<PostRollFragment.Factory> postRollFragmentFactoryProvider, Provider<SdkState> sdkStateProvider, Provider<IntentFactory> intentFactoryProvider, Provider<LoggedException.Factory> loggedExceptionFactoryProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
        return new FullScreenAdActivity_MembersInjector(adEventListenerFactoryProvider, adManagerProvider, adReportEventListenerFactoryProvider, deviceProvider, eventBusProvider, videoFragmentFactoryProvider, postRollFragmentFactoryProvider, sdkStateProvider, intentFactoryProvider, loggedExceptionFactoryProvider, wrapperFrameworkProvider);
    }

    public final void injectMembers(FullScreenAdActivity instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.c = (Factory) this.b.get();
        instance.d = (AdManager) this.c.get();
        instance.e = (AdReportEventListener.Factory) this.d.get();
        instance.f = (ek) this.e.get();
        instance.g = (EventBus) this.f.get();
        instance.h = (VideoFragment.Factory) this.g.get();
        instance.i = (PostRollFragment.Factory) this.h.get();
        instance.j = (SdkState) this.i.get();
        instance.k = (IntentFactory) this.j.get();
        instance.l = (LoggedException.Factory) this.k.get();
        instance.m = (WrapperFramework) this.l.get();
    }

    public static void injectAdEventListenerFactory(FullScreenAdActivity instance, Provider<Factory> adEventListenerFactoryProvider) {
        instance.c = (Factory) adEventListenerFactoryProvider.get();
    }

    public static void injectAdManager(FullScreenAdActivity instance, Provider<AdManager> adManagerProvider) {
        instance.d = (AdManager) adManagerProvider.get();
    }

    public static void injectAdReportEventListenerFactory(FullScreenAdActivity instance, Provider<AdReportEventListener.Factory> adReportEventListenerFactoryProvider) {
        instance.e = (AdReportEventListener.Factory) adReportEventListenerFactoryProvider.get();
    }

    public static void injectDevice(FullScreenAdActivity instance, Provider<ek> deviceProvider) {
        instance.f = (ek) deviceProvider.get();
    }

    public static void injectEventBus(FullScreenAdActivity instance, Provider<EventBus> eventBusProvider) {
        instance.g = (EventBus) eventBusProvider.get();
    }

    public static void injectVideoFragmentFactory(FullScreenAdActivity instance, Provider<VideoFragment.Factory> videoFragmentFactoryProvider) {
        instance.h = (VideoFragment.Factory) videoFragmentFactoryProvider.get();
    }

    public static void injectPostRollFragmentFactory(FullScreenAdActivity instance, Provider<PostRollFragment.Factory> postRollFragmentFactoryProvider) {
        instance.i = (PostRollFragment.Factory) postRollFragmentFactoryProvider.get();
    }

    public static void injectSdkState(FullScreenAdActivity instance, Provider<SdkState> sdkStateProvider) {
        instance.j = (SdkState) sdkStateProvider.get();
    }

    public static void injectIntentFactory(FullScreenAdActivity instance, Provider<IntentFactory> intentFactoryProvider) {
        instance.k = (IntentFactory) intentFactoryProvider.get();
    }

    public static void injectLoggedExceptionFactory(FullScreenAdActivity instance, Provider<LoggedException.Factory> loggedExceptionFactoryProvider) {
        instance.l = (LoggedException.Factory) loggedExceptionFactoryProvider.get();
    }

    public static void injectWrapperFramework(FullScreenAdActivity instance, Provider<WrapperFramework> wrapperFrameworkProvider) {
        instance.m = (WrapperFramework) wrapperFrameworkProvider.get();
    }
}
