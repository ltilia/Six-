package com.vungle.publisher;

import com.vungle.publisher.file.CacheManager;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class fb implements MembersInjector<CacheManager> {
    static final /* synthetic */ boolean a;
    private final Provider<String> b;
    private final Provider<String> c;

    static {
        a = !fb.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        CacheManager cacheManager = (CacheManager) obj;
        if (cacheManager == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        cacheManager.a = this.b;
        cacheManager.b = this.c;
    }

    private fb(Provider<String> provider, Provider<String> provider2) {
        if (a || provider != null) {
            this.b = provider;
            if (a || provider2 != null) {
                this.c = provider2;
                return;
            }
            throw new AssertionError();
        }
        throw new AssertionError();
    }

    public static MembersInjector<CacheManager> a(Provider<String> provider, Provider<String> provider2) {
        return new fb(provider, provider2);
    }
}
