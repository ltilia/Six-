package com.vungle.publisher;

import com.vungle.publisher.protocol.message.ReportStreamingAd.Factory.PlayFactory;
import com.vungle.publisher.protocol.message.ReportStreamingAd.Factory.PlayFactory.UserActionFactory;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class in implements MembersInjector<PlayFactory> {
    static final /* synthetic */ boolean a;
    private final Provider<UserActionFactory> b;

    static {
        a = !in.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        PlayFactory playFactory = (PlayFactory) obj;
        if (playFactory == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        playFactory.a = (UserActionFactory) this.b.get();
    }

    private in(Provider<UserActionFactory> provider) {
        if (a || provider != null) {
            this.b = provider;
            return;
        }
        throw new AssertionError();
    }

    public static MembersInjector<PlayFactory> a(Provider<UserActionFactory> provider) {
        return new in(provider);
    }
}
