package com.vungle.publisher.file;

import dagger.MembersInjector;
import dagger.internal.Factory;
import dagger.internal.MembersInjectors;

/* compiled from: vungle */
public final class CacheManager_Factory implements Factory<CacheManager> {
    static final /* synthetic */ boolean a;
    private final MembersInjector<CacheManager> b;

    static {
        a = !CacheManager_Factory.class.desiredAssertionStatus();
    }

    public CacheManager_Factory(MembersInjector<CacheManager> cacheManagerMembersInjector) {
        if (a || cacheManagerMembersInjector != null) {
            this.b = cacheManagerMembersInjector;
            return;
        }
        throw new AssertionError();
    }

    public final CacheManager get() {
        return (CacheManager) MembersInjectors.injectMembers(this.b, new CacheManager());
    }

    public static Factory<CacheManager> create(MembersInjector<CacheManager> cacheManagerMembersInjector) {
        return new CacheManager_Factory(cacheManagerMembersInjector);
    }
}
