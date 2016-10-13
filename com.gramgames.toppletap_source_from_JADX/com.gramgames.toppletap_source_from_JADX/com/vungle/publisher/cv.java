package com.vungle.publisher;

import com.vungle.publisher.db.model.LocalViewableDelegate;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.net.http.DownloadHttpGateway;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class cv implements MembersInjector<LocalViewableDelegate> {
    static final /* synthetic */ boolean a;
    private final Provider<DownloadHttpGateway> b;
    private final Provider<ek> c;
    private final Provider<SdkState> d;

    static {
        a = !cv.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        LocalViewableDelegate localViewableDelegate = (LocalViewableDelegate) obj;
        if (localViewableDelegate == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        localViewableDelegate.d = (DownloadHttpGateway) this.b.get();
        localViewableDelegate.e = (ek) this.c.get();
        localViewableDelegate.f = (SdkState) this.d.get();
    }

    private cv(Provider<DownloadHttpGateway> provider, Provider<ek> provider2, Provider<SdkState> provider3) {
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

    public static MembersInjector<LocalViewableDelegate> a(Provider<DownloadHttpGateway> provider, Provider<ek> provider2, Provider<SdkState> provider3) {
        return new cv(provider, provider2, provider3);
    }
}
