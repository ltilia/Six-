package com.vungle.publisher.protocol;

import com.vungle.publisher.net.http.HttpTransaction;
import com.vungle.publisher.net.http.HttpTransaction.Factory;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class TrackInstallHttpTransactionFactory extends Factory {
    @Inject
    public TrackInstallHttpRequest.Factory a;
    @Inject
    public TrackInstallHttpResponseHandler b;

    @Inject
    TrackInstallHttpTransactionFactory() {
    }

    public final HttpTransaction a() {
        return super.a(this.a.d(), this.b);
    }
}
