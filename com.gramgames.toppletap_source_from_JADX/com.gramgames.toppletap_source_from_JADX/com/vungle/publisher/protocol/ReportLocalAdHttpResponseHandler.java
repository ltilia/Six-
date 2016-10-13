package com.vungle.publisher.protocol;

import com.vungle.publisher.hi;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.protocol.message.ReportLocalAd;
import com.vungle.publisher.protocol.message.RequestLocalAd;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class ReportLocalAdHttpResponseHandler extends hi {
    @Inject
    public EventTrackingHttpLogEntryDeleteDelegate b;

    @Inject
    ReportLocalAdHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        super.a(httpTransaction, httpResponse);
        this.b.a(((RequestLocalAd) ((ReportLocalAd) ((ReportLocalAdHttpRequest) httpTransaction.a).g).d()).g);
    }
}
