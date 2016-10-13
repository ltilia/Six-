package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.db.model.AdReport.BaseFactory;
import com.vungle.publisher.protocol.message.RequestLocalAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;

/* compiled from: vungle */
public class LocalAdReport extends AdReport<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
    Long p;
    @Inject
    public Factory q;
    @Inject
    public com.vungle.publisher.db.model.LocalAdPlay.Factory u;

    @Singleton
    /* compiled from: vungle */
    public static class Factory extends BaseFactory<LocalAdReport, LocalAdPlay, LocalAdReportEvent, LocalAd, LocalVideo, RequestLocalAdResponse> {
        @Inject
        public com.vungle.publisher.db.model.LocalAd.Factory b;
        @Inject
        public com.vungle.publisher.db.model.LocalAdPlay.Factory d;
        @Inject
        public Provider<LocalAdReport> e;

        public final /* bridge */ /* synthetic */ int a(List list) {
            return super.a(list);
        }

        protected final /* bridge */ /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            return a((LocalAdReport) cbVar, cursor, false);
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.Ad.Factory a() {
            return this.b;
        }

        protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory b() {
            return this.d;
        }

        protected final /* bridge */ /* synthetic */ Object[] b(int i) {
            return new Integer[i];
        }

        protected final /* bridge */ /* synthetic */ cb[] c(int i) {
            return new LocalAdReport[i];
        }

        protected final /* synthetic */ cb c_() {
            return (LocalAdReport) this.e.get();
        }

        public final /* bridge */ /* synthetic */ List d() {
            return super.d();
        }

        public final /* bridge */ /* synthetic */ List d(int i) {
            return super.d(i);
        }

        @Inject
        Factory() {
        }

        protected final b c() {
            return b.local;
        }

        private LocalAdReport a(LocalAdReport localAdReport, Cursor cursor, boolean z) {
            super.a((AdReport) localAdReport, cursor, z);
            localAdReport.p = bs.e(cursor, "download_end_millis");
            return localAdReport;
        }

        protected final String e_() {
            return "ad_report";
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

    protected final /* bridge */ /* synthetic */ com.vungle.publisher.db.model.AdPlay.Factory a() {
        return this.u;
    }

    protected final /* bridge */ /* synthetic */ a a_() {
        return this.q;
    }

    @Inject
    LocalAdReport() {
    }

    public final int z() {
        if (this.p == null) {
            Logger.w(Logger.REPORT_TAG, "download end millis null for " + x());
            return -1;
        } else if (this.p.longValue() < 0) {
            return 0;
        } else {
            if (this.d != null) {
                return (int) (this.p.longValue() - this.d.longValue());
            }
            Logger.w(Logger.REPORT_TAG, "insert timestamp millis null for " + x());
            return -1;
        }
    }

    public final void d(Long l) {
        this.p = l;
        Logger.d(Logger.REPORT_TAG, "setting ad download end millis " + l + " (duration " + z() + " ms) for " + x());
        w();
    }

    protected final ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("download_end_millis", this.p);
        return a;
    }

    public final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "download_end_millis", this.p, false);
        return p;
    }
}
