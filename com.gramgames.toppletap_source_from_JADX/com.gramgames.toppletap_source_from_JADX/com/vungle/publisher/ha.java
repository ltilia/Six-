package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.net.http.HttpTransaction;

/* compiled from: vungle */
public final class ha implements Runnable {
    private final HttpTransaction a;

    public ha(HttpTransaction httpTransaction) {
        this.a = httpTransaction;
    }

    public final void run() {
        try {
            Logger.d(Logger.NETWORK_TAG, "executing " + this.a);
            this.a.a();
        } catch (Throwable e) {
            Logger.e(Logger.NETWORK_TAG, "error processing transaction: " + this.a, e);
        }
    }
}
