package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.env.SdkConfig;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class AppFingerprintHttpResponseHandler extends FireAndForgetHttpResponseHandler {
    @Inject
    public SdkConfig a;

    @Inject
    AppFingerprintHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        super.a(httpTransaction, httpResponse);
        long currentTimeMillis = System.currentTimeMillis();
        Logger.d(Logger.DATA_TAG, "sent fingerprint at time: " + currentTimeMillis);
        this.a.a(currentTimeMillis);
    }
}
