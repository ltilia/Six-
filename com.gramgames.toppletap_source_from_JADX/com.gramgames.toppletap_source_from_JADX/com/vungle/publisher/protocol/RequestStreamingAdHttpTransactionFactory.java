package com.vungle.publisher.protocol;

import com.vungle.publisher.ad.SafeBundleAdConfig;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class RequestStreamingAdHttpTransactionFactory extends Factory {
    @Inject
    public RequestStreamingAdHttpRequest.Factory a;
    @Inject
    public RequestStreamingAdHttpResponseHandler b;

    @Inject
    RequestStreamingAdHttpTransactionFactory() {
    }

    public final HttpTransaction a(String str, SafeBundleAdConfig safeBundleAdConfig) throws JSONException {
        return super.a(this.a.a(str, safeBundleAdConfig), this.b);
    }
}
