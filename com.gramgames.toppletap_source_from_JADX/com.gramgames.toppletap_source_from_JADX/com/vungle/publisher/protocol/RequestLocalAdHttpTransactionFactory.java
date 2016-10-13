package com.vungle.publisher.protocol;

import com.vungle.publisher.gp;
import com.vungle.publisher.hb;
import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import dagger.Lazy;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class RequestLocalAdHttpTransactionFactory extends Factory {
    @Inject
    public RequestLocalAdHttpRequest.Factory a;
    @Inject
    public Lazy<RequestLocalAdHttpResponseHandler> b;

    @Inject
    RequestLocalAdHttpTransactionFactory() {
    }

    public final HttpTransaction a(hb hbVar) {
        return super.a(this.a.d(), (gp) this.b.get(), hbVar);
    }
}
