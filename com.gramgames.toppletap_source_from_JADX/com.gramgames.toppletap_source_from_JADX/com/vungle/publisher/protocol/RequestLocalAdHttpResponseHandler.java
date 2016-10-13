package com.vungle.publisher.protocol;

import android.os.SystemClock;
import com.facebook.ads.AdError;
import com.vungle.log.Logger;
import com.vungle.publisher.aa;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.ad.AdManager.3;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.Ad.a;
import com.vungle.publisher.db.model.LocalAd;
import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.ha;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse.Factory;
import com.vungle.publisher.reporting.AdServiceReportingHandler;
import com.vungle.publisher.x;
import com.vungle.publisher.y;
import dagger.Lazy;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.simple.parser.Yytoken;

@Singleton
/* compiled from: vungle */
public class RequestLocalAdHttpResponseHandler extends InfiniteRetryHttpResponseHandler {
    @Inject
    public AdServiceReportingHandler a;
    @Inject
    public EventBus b;
    @Inject
    public EventTrackingHttpLogEntryDeleteDelegate c;
    @Inject
    public Lazy<AdManager> d;
    @Inject
    public Lazy<SdkState> e;
    @Inject
    public Factory k;
    @Inject
    public ScheduledPriorityExecutor l;
    @Inject
    public ProtocolHttpGateway m;

    @Inject
    RequestLocalAdHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        String a = a(httpResponse.a);
        SdkState sdkState = (SdkState) this.e.get();
        sdkState.a(0);
        RequestLocalAdResponse requestLocalAdResponse = (RequestLocalAdResponse) this.k.a(a);
        Integer valueOf = requestLocalAdResponse.r == null ? null : Integer.valueOf(requestLocalAdResponse.r.intValue() * AdError.NETWORK_ERROR_CODE);
        AdServiceReportingHandler adServiceReportingHandler = this.a;
        adServiceReportingHandler.b = SystemClock.elapsedRealtime() - adServiceReportingHandler.a;
        this.c.a(((RequestLocalAdHttpRequest) httpTransaction.a).e.g);
        if (valueOf == null) {
            if ((requestLocalAdResponse.s.longValue() * 1000 < System.currentTimeMillis() ? 1 : null) != null) {
                Logger.i(Logger.NETWORK_TAG, "received expired ad from server, tossing it and getting a new one");
                this.b.a(new aa(httpTransaction.b));
                return;
            }
            Logger.v(Logger.NETWORK_TAG, "received a valid ad, continue processing");
            AdManager adManager = (AdManager) this.d.get();
            String f = requestLocalAdResponse.f();
            LocalAd localAd = (LocalAd) adManager.g.a((Object) f);
            if (localAd != null) {
                try {
                    adManager.g.a(localAd, requestLocalAdResponse);
                } catch (Throwable e) {
                    Logger.w(Logger.PREPARE_TAG, "error updating ad " + f, e);
                }
                a i = localAd.i();
                switch (3.a[i.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        Logger.i(Logger.PREPARE_TAG, "received " + localAd.x() + " in status " + i);
                        adManager.a.a(f);
                        break;
                    default:
                        Logger.w(Logger.PREPARE_TAG, "received " + localAd.x() + " in status " + i + " - ignoring");
                        break;
                }
            }
            try {
                adManager.g.i();
                localAd = adManager.g.a(requestLocalAdResponse);
                Logger.i(Logger.PREPARE_TAG, "received new " + localAd.x());
                localAd.l();
                adManager.a.a(f);
            } catch (Throwable e2) {
                Logger.w(Logger.PREPARE_TAG, "error preparing ad " + f, e2);
                adManager.d.a(new y());
            }
            sdkState = (SdkState) this.e.get();
            Integer a2 = requestLocalAdResponse.a();
            if (a2 == null) {
                Logger.v(Logger.AD_TAG, "ignoring set null min ad delay seconds");
                return;
            }
            int intValue = a2.intValue();
            Logger.d(Logger.AD_TAG, "setting min ad delay seconds: " + intValue);
            sdkState.o.edit().putInt("VgAdDelayDuration", intValue).apply();
            return;
        }
        Logger.d(Logger.NETWORK_TAG, "received sleep from server: " + valueOf);
        sdkState.a((long) valueOf.intValue());
        this.l.a(new ha(httpTransaction), httpTransaction.c, (long) valueOf.intValue());
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse, Exception exception) {
        this.b.a(new x(httpTransaction.b));
    }
}
