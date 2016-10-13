package com.vungle.publisher.db.model;

import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAdReport extends AdReport<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    public Factory p;
    @Inject
    public com.vungle.publisher.db.model.StreamingAdPlay.Factory q;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends BaseFactory<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        public com.vungle.publisher.db.model.StreamingAdPlay.Factory b;
        @Inject
        public com.vungle.publisher.db.model.StreamingAd.Factory d;
        @Inject
        public Provider<StreamingAdReport> e;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
            return this.d;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory b() {
            return this.b;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new StreamingAdReport[i];
        }

        protected final /* synthetic */ cb c_() {
            return (StreamingAdReport) this.e.get();
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

        protected final b c() {
            return b.streaming;
        }

        protected final String e_() {
            return "ad_report";
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

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory a() {
        return this.q;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.p;
    }

    @Inject
    StreamingAdReport() {
    }
}
