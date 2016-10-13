package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.AdPlay;
import com.vungle.publisher.db.model.AdReport;
import com.vungle.publisher.db.model.AdReportEvent;
import com.vungle.publisher.db.model.Video;
import com.vungle.publisher.fl;
import com.vungle.publisher.net.http.HttpRequest;
import com.vungle.publisher.net.http.HttpRequest.b;
import com.vungle.publisher.protocol.ProtocolHttpRequest.a;
import com.vungle.publisher.protocol.message.ReportAd;
import com.vungle.publisher.protocol.message.RequestAd;
import com.vungle.publisher.protocol.message.RequestAdResponse;

/* compiled from: vungle */
public abstract class ReportAdHttpRequest<Q extends RequestAd<Q>, O extends ReportAd<Q, O>, T extends AdReport<?, ?, ?, ?, ?, ?>> extends ProtocolHttpRequest {
    public Ad<?, ?, ?> e;
    public Integer f;
    O g;

    /* compiled from: vungle */
    public static abstract class Factory<Q extends RequestAd<Q>, R extends RequestAdResponse, O extends ReportAd<Q, O>, H extends ReportAdHttpRequest<Q, O, T>, T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>> extends a<H> {
        protected abstract com.vungle.publisher.protocol.message.ReportAd.Factory<Q, R, O, T, P, E, A, V> d();

        protected abstract H e();

        protected Factory() {
        }

        protected /* synthetic */ HttpRequest b() {
            return e();
        }

        public final H a(T t) throws fl {
            try {
                ReportAdHttpRequest reportAdHttpRequest = (ReportAdHttpRequest) a();
                reportAdHttpRequest.e = t == null ? null : t.e();
                reportAdHttpRequest.b = this.d + "reportAd";
                reportAdHttpRequest.c.putString("Content-Type", WebRequest.CONTENT_TYPE_JSON);
                reportAdHttpRequest.f = (Integer) t.s();
                reportAdHttpRequest.b = this.d + "reportAd";
                ReportAd a = d().a(t);
                reportAdHttpRequest.g = a;
                if (a != null) {
                    reportAdHttpRequest.d = a.c();
                }
                return reportAdHttpRequest;
            } catch (Throwable e) {
                throw new fl(e);
            }
        }
    }

    protected ReportAdHttpRequest() {
    }

    protected final b a() {
        return b.reportAd;
    }

    protected final HttpRequest.a b() {
        return HttpRequest.a.POST;
    }
}
