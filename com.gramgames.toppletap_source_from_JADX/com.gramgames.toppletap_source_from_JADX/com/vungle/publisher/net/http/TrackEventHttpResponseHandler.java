package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry;
import com.vungle.publisher.db.model.EventTrackingHttpLogEntry.Factory;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public class TrackEventHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    boolean a;
    @Inject
    public Factory b;

    @Inject
    TrackEventHttpResponseHandler() {
    }

    protected final void c(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        try {
            super.c(httpTransaction, httpResponse);
            if (this.a) {
                e(httpTransaction, httpResponse);
            }
        } catch (Throwable th) {
            if (this.a) {
                e(httpTransaction, httpResponse);
            }
        }
    }

    private void e(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        try {
            TrackEventHttpRequest trackEventHttpRequest = (TrackEventHttpRequest) httpTransaction.a;
            Logger.d(Logger.NETWORK_TAG, "logging reqeust chain for " + trackEventHttpRequest);
            Ad ad = trackEventHttpRequest.e;
            List<HttpRequestChainElement> list = httpResponse.d;
            if (list == null) {
                Logger.d(Logger.NETWORK_TAG, "null request chain for " + trackEventHttpRequest);
                return;
            }
            for (HttpRequestChainElement httpRequestChainElement : list) {
                Factory factory = this.b;
                a aVar = trackEventHttpRequest.f;
                EventTrackingHttpLogEntry b = factory.b();
                b.a = ad.d();
                b.b = ad.h();
                b.c = aVar;
                b.e = Integer.valueOf(httpRequestChainElement.c);
                b.f = Long.valueOf(httpRequestChainElement.d.longValue());
                b.g = httpRequestChainElement.b;
                b.r();
            }
        } catch (Throwable e) {
            this.g.a(Logger.NETWORK_TAG, "error logging call-to-action response", e);
        }
    }
}
