package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class SessionStartHttpTransactionFactory extends Factory {
    @Inject
    public SessionStartHttpRequest.Factory a;
    @Inject
    public FireAndForgetHttpResponseHandler b;

    @Inject
    SessionStartHttpTransactionFactory() {
    }

    public final HttpTransaction a(long j) throws JSONException {
        return super.a(this.a.a(j), this.b);
    }
}
