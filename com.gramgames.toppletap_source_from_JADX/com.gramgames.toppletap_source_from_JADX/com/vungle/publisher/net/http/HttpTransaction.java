package com.vungle.publisher.net.http;

import android.os.SystemClock;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.gp;
import com.vungle.publisher.hb;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.EnumMap;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class HttpTransaction {
    public HttpRequest a;
    public hb b;
    public b c;
    @Inject
    public HttpTransport d;
    private gp e;

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        private static final EnumMap<HttpRequest.b, b> a;
        @Inject
        public Provider<HttpTransaction> c;

        static {
            EnumMap enumMap = new EnumMap(HttpRequest.b.class);
            a = enumMap;
            enumMap.put(HttpRequest.b.download, b.downloadLocalAd);
            a.put(HttpRequest.b.reportAd, b.reportAd);
            a.put(HttpRequest.b.requestConfig, b.requestConfig);
            a.put(HttpRequest.b.requestLocalAd, b.requestLocalAd);
            a.put(HttpRequest.b.requestStreamingAd, b.requestStreamingAd);
            a.put(HttpRequest.b.sessionEnd, b.sessionEnd);
            a.put(HttpRequest.b.sessionStart, b.sessionStart);
            a.put(HttpRequest.b.trackEvent, b.externalNetworkRequest);
            a.put(HttpRequest.b.trackInstall, b.reportInstall);
            a.put(HttpRequest.b.unfilledAd, b.unfilledAd);
            a.put(HttpRequest.b.appFingerprint, b.appFingerprint);
            a.put(HttpRequest.b.reportExceptions, b.reportExceptions);
        }

        public final HttpTransaction a(HttpRequest httpRequest, gp gpVar) {
            return a(httpRequest, gpVar, new hb());
        }

        public final HttpTransaction a(HttpRequest httpRequest, gp gpVar, hb hbVar) {
            HttpTransaction httpTransaction = (HttpTransaction) this.c.get();
            httpTransaction.a = httpRequest;
            httpTransaction.e = gpVar;
            b bVar = (b) a.get(httpRequest.a());
            if (bVar == null) {
                Logger.w(Logger.NETWORK_TAG, "missing mapping for HttpTransaction requestType = " + httpRequest.a().toString());
                bVar = b.otherTask;
            }
            httpTransaction.c = bVar;
            httpTransaction.b = hbVar;
            return httpTransaction;
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    @Inject
    HttpTransaction() {
    }

    public final void a() {
        hb hbVar = this.b;
        if (hbVar.a <= 0) {
            hbVar.a = SystemClock.elapsedRealtime();
        }
        hbVar.b++;
        hbVar.c++;
        this.e.c(this, this.d.a(this.a));
    }

    public String toString() {
        return "{" + this.a + ", " + this.b + "}";
    }
}
