package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.db.model.LocalAdPlay;
import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.LocalAdReportEvent;
import com.vungle.publisher.db.model.LocalVideo;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.protocol.message.ReportLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import javax.inject.Inject;
import javax.inject.Singleton;

/* compiled from: vungle */
public final class ReportLocalAdHttpRequest extends ReportAdHttpRequest<RequestLocalAd, ReportLocalAd, LocalAdReport> {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.protocol.ReportAdHttpRequest.Factory<RequestLocalAd, RequestLocalAdResponse, ReportLocalAd, ReportLocalAdHttpRequest, LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo> {
        @Inject
        public com.vungle.publisher.protocol.message.ReportLocalAd.Factory g;

        protected final /* synthetic */ HttpRequest b() {
            return new ReportLocalAdHttpRequest();
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.protocol.message.ReportAd.Factory d() {
            return this.g;
        }

        protected final /* synthetic */ ReportAdHttpRequest e() {
            return new ReportLocalAdHttpRequest();
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
