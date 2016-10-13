package com.vungle.publisher;

import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.StreamingAd.Factory;
import com.vungle.publisher.db.model.StreamingVideo;
import dagger.MembersInjector;
import javax.inject.Provider;

/* compiled from: vungle */
public final class di implements MembersInjector<StreamingVideo> {
    static final /* synthetic */ boolean a;
    private final Provider<DatabaseHelper> b;
    private final Provider<Factory> c;
    private final Provider<StreamingVideo.Factory> d;

    static {
        a = !di.class.desiredAssertionStatus();
    }

    public final /* synthetic */ void injectMembers(Object obj) {
        StreamingVideo streamingVideo = (StreamingVideo) obj;
        if (streamingVideo == null) {
            throw new NullPointerException("Cannot inject members into a null reference");
        }
        streamingVideo.t = (DatabaseHelper) this.b.get();
        streamingVideo.a = (Factory) this.c.get();
        streamingVideo.b = (StreamingVideo.Factory) this.d.get();
    }

    private di(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingVideo.Factory> provider3) {
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

    public static MembersInjector<StreamingVideo> a(Provider<DatabaseHelper> provider, Provider<Factory> provider2, Provider<StreamingVideo.Factory> provider3) {
        return new di(provider, provider2, provider3);
    }
}
