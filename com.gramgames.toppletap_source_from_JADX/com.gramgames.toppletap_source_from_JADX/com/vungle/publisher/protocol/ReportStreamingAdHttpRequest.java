package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.StreamingAd;
import com.vungle.publisher.db.model.StreamingAdPlay;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.db.model.StreamingAdReportEvent;
import com.vungle.publisher.db.model.StreamingVideo;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.protocol.message.ReportStreamingAd;
import com.vungle.publisher.protocol.message.RequestStreamingAd;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ReportStreamingAdHttpRequest extends ReportAdHttpRequest<RequestStreamingAd, ReportStreamingAd, StreamingAdReport> {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.ReportAdHttpRequest.Factory<RequestStreamingAd, RequestStreamingAdResponse, ReportStreamingAd, ReportStreamingAdHttpRequest, StreamingAdReport, StreamingAdPlay, StreamingAdReportEvent, StreamingAd, StreamingVideo> {
        @Inject
        public com.vungle.publisher.protocol.message.ReportStreamingAd.Factory g;

        protected final /* synthetic */ HttpRequest b() {
            return new ReportStreamingAdHttpRequest();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.ReportAd.Factory d() {
            return this.g;
        }

        protected final /* synthetic */ ReportAdHttpRequest e() {
            return new ReportStreamingAdHttpRequest();
        }

        @Inject
        Factory() {
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
}
