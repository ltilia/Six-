package com.vungle.publisher.protocol;

import com.vungle.publisher.env.SdkState;
import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class TrackInstallHttpResponseHandler extends FireAndForgetHttpResponseHandler {
    @Inject
    public SdkState a;

    @Inject
    TrackInstallHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        super.a(httpTransaction, httpResponse);
        this.a.o.edit().putBoolean("IsVgAppInstalled", true).apply();
    }
}
