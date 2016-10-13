package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.FireAndForgetHttpResponseHandler;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONException;

@Singleton
/* compiled from: vungle */
public class SessionEndHttpTransactionFactory extends Factory {
    @Inject
    public SessionEndHttpRequest.Factory a;
    @Inject
    public FireAndForgetHttpResponseHandler b;

    @Inject
    SessionEndHttpTransactionFactory() {
    }

    public final HttpTransaction a(long j, long j2) throws JSONException {
        return super.a(this.a.a(j, j2), this.b);
    }
}
