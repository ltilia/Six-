package com.vungle.sdk;

import com.vungle.publisher.FullScreenAdActivity.AdEventListener.Factory;
import com.vungle.publisher.FullScreenAdActivity_MembersInjector;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.display.view.PostRollFragment;
import com.vungle.publisher.display.view.VideoFragment;
import com.vungle.publisher.ek;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.env.WrapperFramework;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.reporting.AdReportEventListener;
import com.vungle.publisher.util.IntentFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VungleAdvert_MembersInjector implements MembersInjector<VungleAdvert> {
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
        a = !VungleAdvert_MembersInjector.class.desiredAssertionStatus();
    }

    public VungleAdvert_MembersInjector(Provider<Factory> adEventListenerFactoryProvider, Provider<AdManager> adManagerProvider, Provider<AdReportEventListener.Factory> adReportEventListenerFactoryProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<VideoFragment.Factory> videoFragmentFactoryProvider, Provider<PostRollFragment.Factory> postRollFragmentFactoryProvider, Provider<SdkState> sdkStateProvider, Provider<IntentFactory> intentFactoryProvider, Provider<LoggedException.Factory> loggedExceptionFactoryProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
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

    public static MembersInjector<VungleAdvert> create(Provider<Factory> adEventListenerFactoryProvider, Provider<AdManager> adManagerProvider, Provider<AdReportEventListener.Factory> adReportEventListenerFactoryProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<VideoFragment.Factory> videoFragmentFactoryProvider, Provider<PostRollFragment.Factory> postRollFragmentFactoryProvider, Provider<SdkState> sdkStateProvider, Provider<IntentFactory> intentFactoryProvider, Provider<LoggedException.Factory> loggedExceptionFactoryProvider, Provider<WrapperFramework> wrapperFrameworkProvider) {
        return new VungleAdvert_MembersInjector(adEventListenerFactoryProvider, adManagerProvider, adReportEventListenerFactoryProvider, deviceProvider, eventBusProvider, videoFragmentFactoryProvider, postRollFragmentFactoryProvider, sdkStateProvider, intentFactoryProvider, loggedExceptionFactoryProvider, wrapperFrameworkProvider);
    }

    public final void injectMembers(VungleAdvert instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        FullScreenAdActivity_MembersInjector.injectAdEventListenerFactory(instance, this.b);
        FullScreenAdActivity_MembersInjector.injectAdManager(instance, this.c);
        FullScreenAdActivity_MembersInjector.injectAdReportEventListenerFactory(instance, this.d);
        FullScreenAdActivity_MembersInjector.injectDevice(instance, this.e);
        FullScreenAdActivity_MembersInjector.injectEventBus(instance, this.f);
        FullScreenAdActivity_MembersInjector.injectVideoFragmentFactory(instance, this.g);
        FullScreenAdActivity_MembersInjector.injectPostRollFragmentFactory(instance, this.h);
        FullScreenAdActivity_MembersInjector.injectSdkState(instance, this.i);
        FullScreenAdActivity_MembersInjector.injectIntentFactory(instance, this.j);
        FullScreenAdActivity_MembersInjector.injectLoggedExceptionFactory(instance, this.k);
        FullScreenAdActivity_MembersInjector.injectWrapperFramework(instance, this.l);
    }
}
