package com.vungle.publisher.db.model;

import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class StreamingAdReportEvent extends AdReportEvent<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
    @Inject
    public Factory e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.db.model.AdReportEvent.Factory<StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo, RequestStreamingAdResponse> {
        @Inject
        public Provider<StreamingAdReportEvent> a;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new StreamingAdReportEvent[i];
        }

        protected final /* synthetic */ cb c_() {
            return (StreamingAdReportEvent) this.a.get();
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

        protected final String e_() {
            return "ad_report_event";
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

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.e;
    }

    @Inject
    StreamingAdReportEvent() {
    }
}
