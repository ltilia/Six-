package com.vungle.publisher;

import android.content.Context;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.file.CacheManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class VunglePubBase_MembersInjector implements MembersInjector<VunglePubBase> {
    static final /* synthetic */ boolean a;
    private final Provider<AdManager> b;
    private final Provider<InitializationEventListener> c;
    private final Provider<CacheManager> d;
    private final Provider<DatabaseHelper> e;
    private final Provider<Demographic> f;
    private final Provider<ek> g;
    private final Provider<EventBus> h;
    private final Provider<AdConfig> i;
    private final Provider<SafeBundleAdConfigFactory> j;
    private final Provider<SdkConfig> k;
    private final Provider<SdkState> l;
    private final Provider<Context> m;

    static {
        a = !VunglePubBase_MembersInjector.class.desiredAssertionStatus();
    }

    public VunglePubBase_MembersInjector(Provider<AdManager> adManagerProvider, Provider<InitializationEventListener> initializationEventListenerProvider, Provider<CacheManager> cacheManagerProvider, Provider<DatabaseHelper> databaseHelperProvider, Provider<Demographic> demographicProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<AdConfig> globalAdConfigProvider, Provider<SafeBundleAdConfigFactory> safeBundleAdConfigFactoryProvider, Provider<SdkConfig> sdkConfigProvider, Provider<SdkState> sdkStateProvider, Provider<Context> contextProvider) {
        if (a || adManagerProvider != null) {
            this.b = adManagerProvider;
            if (a || initializationEventListenerProvider != null) {
                this.c = initializationEventListenerProvider;
                if (a || cacheManagerProvider != null) {
                    this.d = cacheManagerProvider;
                    if (a || databaseHelperProvider != null) {
                        this.e = databaseHelperProvider;
                        if (a || demographicProvider != null) {
                            this.f = demographicProvider;
                            if (a || deviceProvider != null) {
                                this.g = deviceProvider;
                                if (a || eventBusProvider != null) {
                                    this.h = eventBusProvider;
                                    if (a || globalAdConfigProvider != null) {
                                        this.i = globalAdConfigProvider;
                                        if (a || safeBundleAdConfigFactoryProvider != null) {
                                            this.j = safeBundleAdConfigFactoryProvider;
                                            if (a || sdkConfigProvider != null) {
                                                this.k = sdkConfigProvider;
                                                if (a || sdkStateProvider != null) {
                                                    this.l = sdkStateProvider;
                                                    if (a || contextProvider != null) {
                                                        this.m = contextProvider;
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

    public static MembersInjector<VunglePubBase> create(Provider<AdManager> adManagerProvider, Provider<InitializationEventListener> initializationEventListenerProvider, Provider<CacheManager> cacheManagerProvider, Provider<DatabaseHelper> databaseHelperProvider, Provider<Demographic> demographicProvider, Provider<ek> deviceProvider, Provider<EventBus> eventBusProvider, Provider<AdConfig> globalAdConfigProvider, Provider<SafeBundleAdConfigFactory> safeBundleAdConfigFactoryProvider, Provider<SdkConfig> sdkConfigProvider, Provider<SdkState> sdkStateProvider, Provider<Context> contextProvider) {
        return new VunglePubBase_MembersInjector(adManagerProvider, initializationEventListenerProvider, cacheManagerProvider, databaseHelperProvider, demographicProvider, deviceProvider, eventBusProvider, globalAdConfigProvider, safeBundleAdConfigFactoryProvider, sdkConfigProvider, sdkStateProvider, contextProvider);
    }

    public final void injectMembers(VunglePubBase instance) {
        if (instance == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        instance.a = (AdManager) this.b.get();
        instance.b = (InitializationEventListener) this.c.get();
        instance.c = (CacheManager) this.d.get();
        instance.d = (DatabaseHelper) this.e.get();
        instance.e = (Demographic) this.f.get();
        instance.f = (ek) this.g.get();
        instance.g = (EventBus) this.h.get();
        instance.h = (AdConfig) this.i.get();
        instance.i = (SafeBundleAdConfigFactory) this.j.get();
        instance.j = (SdkConfig) this.k.get();
        instance.k = (SdkState) this.l.get();
        instance.l = (Context) this.m.get();
    }

    public static void injectAdManager(VunglePubBase instance, Provider<AdManager> adManagerProvider) {
        instance.a = (AdManager) adManagerProvider.get();
    }

    public static void injectInitializationEventListener(VunglePubBase instance, Provider<InitializationEventListener> initializationEventListenerProvider) {
        instance.b = (InitializationEventListener) initializationEventListenerProvider.get();
    }

    public static void injectCacheManager(VunglePubBase instance, Provider<CacheManager> cacheManagerProvider) {
        instance.c = (CacheManager) cacheManagerProvider.get();
    }

    public static void injectDatabaseHelper(VunglePubBase instance, Provider<DatabaseHelper> databaseHelperProvider) {
        instance.d = (DatabaseHelper) databaseHelperProvider.get();
    }

    public static void injectDemographic(VunglePubBase instance, Provider<Demographic> demographicProvider) {
        instance.e = (Demographic) demographicProvider.get();
    }

    public static void injectDevice(VunglePubBase instance, Provider<ek> deviceProvider) {
        instance.f = (ek) deviceProvider.get();
    }

    public static void injectEventBus(VunglePubBase instance, Provider<EventBus> eventBusProvider) {
        instance.g = (EventBus) eventBusProvider.get();
    }

    public static void injectGlobalAdConfig(VunglePubBase instance, Provider<AdConfig> globalAdConfigProvider) {
        instance.h = (AdConfig) globalAdConfigProvider.get();
    }

    public static void injectSafeBundleAdConfigFactory(VunglePubBase instance, Provider<SafeBundleAdConfigFactory> safeBundleAdConfigFactoryProvider) {
        instance.i = (SafeBundleAdConfigFactory) safeBundleAdConfigFactoryProvider.get();
    }

    public static void injectSdkConfig(VunglePubBase instance, Provider<SdkConfig> sdkConfigProvider) {
        instance.j = (SdkConfig) sdkConfigProvider.get();
    }

    public static void injectSdkState(VunglePubBase instance, Provider<SdkState> sdkStateProvider) {
        instance.k = (SdkState) sdkStateProvider.get();
    }

    public static void injectContext(VunglePubBase instance, Provider<Context> contextProvider) {
        instance.l = (Context) contextProvider.get();
    }
}
