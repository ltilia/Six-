package com.vungle.publisher.db.model;

import com.vungle.publisher.cb;
import com.vungle.publisher.cg;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public class StreamingAd extends Ad<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    public Factory u;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.streamingVideo.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.Ad.Factory<StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        public Provider<StreamingAd> d;
        @Inject
        public com.vungle.publisher.db.model.StreamingVideo.Factory e;

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new String[i];
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Video.Factory b_() {
            return this.e;
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new StreamingAd[i];
        }

        protected final /* synthetic */ cb c_() {
            return (StreamingAd) this.d.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public final /* bridge */ /* synthetic */ List d(int i) {
            return super.d(i);
        }

        @Inject
        Factory() {
        }

        public final StreamingAd a(RequestStreamingAdResponse requestStreamingAdResponse) {
            StreamingAd streamingAd = (StreamingAd) super.a((RequestAdResponse) requestStreamingAdResponse);
            streamingAd.a(a.ready);
            return streamingAd;
        }

        protected final Ad.b b() {
            return Ad.b.streaming;
        }

        public final StreamingAd a(String str) {
            return (StreamingAd) super.a(Ad.b.streaming, str);
        }

        protected final String e_() {
            return "ad";
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    public final /* synthetic */ cg a(b bVar) {
        switch (1.a[bVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return (StreamingVideo) k();
            default:
                super.a(bVar);
                return null;
        }
    }

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
        return this.u;
    }

    protected final /* bridge */ /* synthetic */ cb.a a_() {
        return this.u;
    }

    @Inject
    StreamingAd() {
    }
}
