package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.device.data.AppFingerprint;
import com.vungle.publisher.net.http.AppFingerprintHttpTransactionFactory;
import com.vungle.publisher.net.http.ReportExceptionsHttpTransactionFactory;
import java.util.List;
import javax.inject.Inject;

/* compiled from: vungle */
public final class gu extends gk {
    @Inject
    ReportExceptionsHttpTransactionFactory a;
    @Inject
    AppFingerprintHttpTransactionFactory b;
    @Inject
    Factory f;

    public class 1 implements Runnable {
        final /* synthetic */ List a;
        final /* synthetic */ gu b;

        public 1(gu guVar, List list) {
            this.b = guVar;
            this.a = list;
        }

        public final void run() {
            try {
                this.b.a.a(this.a).a();
            } catch (Throwable e) {
                Logger.w(Logger.DATA_TAG, "error sending logged exceptions", e);
            }
        }
    }

    public class 2 implements Runnable {
        final /* synthetic */ AppFingerprint a;
        final /* synthetic */ gu b;

        public 2(gu guVar, AppFingerprint appFingerprint) {
            this.b = guVar;
            this.a = appFingerprint;
        }

        public final void run() {
            try {
                this.b.b.a(this.a).a();
            } catch (Throwable e) {
                this.b.f.a(Logger.DATA_TAG, "error sending app fingerprint chunk", e);
            }
        }
    }
}
