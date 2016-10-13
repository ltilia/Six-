package com.vungle.publisher.protocol;

import com.vungle.publisher.af;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.MaxRetryAgeHttpResponseHandler;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse;
import com.vungle.publisher.protocol.message.RequestStreamingAdResponse.Factory;
import com.vungle.publisher.w;
import java.io.IOException;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class RequestStreamingAdHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    @Inject
    public EventBus a;
    @Inject
    public Factory b;

    @Inject
    RequestStreamingAdHttpResponseHandler() {
        this.i = 1;
        this.h = 1;
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        this.a.a(new af((RequestStreamingAdResponse) this.b.a(a(httpResponse.a))));
    }

    protected final void b(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        this.a.a(new w());
    }
}
