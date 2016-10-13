package com.vungle.publisher;

import com.vungle.publisher.db.model.LocalViewableDelegate;
import com.vungle.publisher.db.model.LocalViewableDelegate.Factory;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cu implements MembersInjector<Factory> {
    static final /* synthetic */ boolean a;
    private final Provider<LocalViewableDelegate> b;
    private final Provider<DownloadHttpGateway> c;

    static {
        a = !cu.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        Factory factory = (Factory) obj;
        if (factory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        factory.a = this.b;
        factory.b = (DownloadHttpGateway) this.c.get();
    }

    private cu(Provider<LocalViewableDelegate> provider, Provider<DownloadHttpGateway> provider2) {
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

    public static MembersInjector<Factory> a(Provider<LocalViewableDelegate> provider, Provider<DownloadHttpGateway> provider2) {
        return new cu(provider, provider2);
    }
}
