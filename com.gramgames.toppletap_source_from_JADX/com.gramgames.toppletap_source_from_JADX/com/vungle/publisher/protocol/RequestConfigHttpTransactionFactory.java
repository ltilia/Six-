package com.vungle.publisher.protocol;

import com.vungle.publisher.hr;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestConfigHttpTransactionFactory extends Factory {
    @Inject
    public RequestConfigHttpRequest.Factory a;
    @Inject
    public hr b;

    @Inject
    RequestConfigHttpTransactionFactory() {
    }

    public final HttpTransaction a() {
        return super.a(this.a.d(), this.b);
    }
}
