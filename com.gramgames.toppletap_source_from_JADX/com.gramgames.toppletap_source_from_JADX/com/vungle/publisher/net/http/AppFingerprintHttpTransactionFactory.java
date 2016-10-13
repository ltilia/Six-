package com.vungle.publisher.net.http;

import com.vungle.publisher.device.data.AppFingerprint;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class AppFingerprintHttpTransactionFactory extends Factory {
    @Inject
    public AppFingerprintHttpRequest.Factory a;
    @Inject
    public AppFingerprintHttpResponseHandler b;

    @Inject
    AppFingerprintHttpTransactionFactory() {
    }

    public final HttpTransaction a(AppFingerprint appFingerprint) throws JSONException {
        return super.a(this.a.a(appFingerprint), this.b);
    }
}
