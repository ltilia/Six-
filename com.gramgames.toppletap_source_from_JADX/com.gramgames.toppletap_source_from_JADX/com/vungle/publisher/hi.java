package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.InfiniteRetryHttpResponseHandler;
import com.vungle.publisher.protocol.ReportAdHttpRequest;
import com.vungle.publisher.reporting.AdReportManager;
import dagger.Lazy;
import java.io.IOException;
import javax.inject.Inject;
import org.json.JSONException;

/* compiled from: vungle */
public abstract class hi extends InfiniteRetryHttpResponseHandler {
    @Inject
    protected Lazy<AdReportManager> a;

    public void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        ReportAdHttpRequest reportAdHttpRequest = (ReportAdHttpRequest) httpTransaction.a;
        AdReportManager adReportManager = (AdReportManager) this.a.get();
        Logger.i(Logger.REPORT_TAG, "deleting report " + reportAdHttpRequest.f);
        adReportManager.c.a((Object[]) new Integer[]{r2});
        reportAdHttpRequest.e.q();
    }
}
