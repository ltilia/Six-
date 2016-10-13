package com.vungle.publisher.net.http;

import com.vungle.log.Logger;
import com.vungle.publisher.ct;
import com.vungle.publisher.gk;
import com.vungle.publisher.hb;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DownloadHttpGateway extends gk {
    @Inject
    public DownloadHttpTransactionFactory a;

    public class 1 implements Runnable {
        final /* synthetic */ ct a;
        final /* synthetic */ hb b;
        final /* synthetic */ DownloadHttpGateway c;

        public 1(DownloadHttpGateway downloadHttpGateway, ct ctVar, hb hbVar) {
            this.c = downloadHttpGateway;
            this.a = ctVar;
            this.b = hbVar;
        }

        public final void run() {
            try {
                this.c.a.a(this.a.d(), this.a.f(), this.a.g(), this.a.k(), this.b).a();
            } catch (Throwable e) {
                this.c.d.a(Logger.NETWORK_TAG, "error requesting streaming ad", e);
            }
        }
    }

    @Inject
    DownloadHttpGateway() {
    }
}
