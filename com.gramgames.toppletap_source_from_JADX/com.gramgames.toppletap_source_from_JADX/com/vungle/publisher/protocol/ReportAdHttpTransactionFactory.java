package com.vungle.publisher.protocol;

import com.vungle.publisher.db.model.LocalAdReport;
import com.vungle.publisher.db.model.StreamingAdReport;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class ReportAdHttpTransactionFactory extends Factory {
    @Inject
    public ReportLocalAdHttpRequest.Factory a;
    @Inject
    public ReportLocalAdHttpResponseHandler b;
    @Inject
    public ReportStreamingAdHttpRequest.Factory d;
    @Inject
    public ReportStreamingAdHttpResponseHandler e;

    @Inject
    ReportAdHttpTransactionFactory() {
    }

    public final HttpTransaction a(LocalAdReport localAdReport) throws JSONException {
        return super.a(this.a.a(localAdReport), this.b);
    }

    public final HttpTransaction a(StreamingAdReport streamingAdReport) throws JSONException {
        return super.a(this.d.a(streamingAdReport), this.e);
    }
}
