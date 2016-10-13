package com.vungle.publisher.net.http;

import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.message.ReportExceptions;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;

/* compiled from: vungle */
public final class ReportExceptionsHttpRequest extends IngestHttpRequest {

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends com.vungle.publisher.net.http.IngestHttpRequest.Factory<ReportExceptionsHttpRequest> {
        @Inject
        public com.vungle.publisher.protocol.message.ReportExceptions.Factory a;
        @Inject
        public Provider<ReportExceptionsHttpRequest> d;

        protected final /* synthetic */ HttpRequest b() {
            return (ReportExceptionsHttpRequest) this.d.get();
        }

        @Inject
        Factory() {
        }

        public final ReportExceptionsHttpRequest a(List<LoggedException> list) throws JSONException {
            ReportExceptionsHttpRequest reportExceptionsHttpRequest = (ReportExceptionsHttpRequest) d();
            ReportExceptions reportExceptions = (ReportExceptions) this.a.a.get();
            reportExceptions.a = list;
            reportExceptionsHttpRequest.d = reportExceptions.c();
            return reportExceptionsHttpRequest;
        }

        protected final String a() {
            return "api/v1/sdkErrors";
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

    @Inject
    ReportExceptionsHttpRequest() {
    }

    protected final b a() {
        return b.reportExceptions;
    }
}
