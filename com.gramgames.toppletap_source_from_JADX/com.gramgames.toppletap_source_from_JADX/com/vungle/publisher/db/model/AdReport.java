package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import com.google.android.gms.games.Games;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.db.DatabaseHelper;
import com.vungle.publisher.db.model.Ad.b;
import com.vungle.publisher.jj;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public abstract class AdReport<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cb<Integer> {
    protected A a;
    protected String b;
    protected String c;
    protected Long d;
    protected boolean e;
    protected String f;
    protected a g;
    protected Long h;
    protected Integer i;
    protected Long j;
    protected Long k;
    protected Map<String, AdReportExtra> l;
    protected List<P> m;
    protected boolean n;
    @Inject
    public com.vungle.publisher.db.model.AdReportExtra.Factory o;

    /* compiled from: vungle */
    public static abstract class BaseFactory<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends com.vungle.publisher.cb.a<T, Integer> {
        @Inject
        public com.vungle.publisher.db.model.AdReportExtra.Factory a;

        protected abstract com.vungle.publisher.db.model.Ad.Factory<A, V, R> a();

        protected abstract com.vungle.publisher.db.model.AdPlay.Factory<T, P, E, A, V, R> b();

        protected abstract b c();

        protected /* bridge */ /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            return a((AdReport) cbVar, cursor, false);
        }

        protected BaseFactory() {
        }

        protected final List<T> d_() {
            List<T> a = a("status = ? AND ad_id IN (SELECT id FROM ad WHERE type = ?)", new String[]{a.reportable.toString(), c().toString()}, "insert_timestamp_millis ASC");
            for (T a2 : a) {
                a((AdReport) a2, null, true);
            }
            return a;
        }

        public final T a(A a) {
            T b = b(a);
            if (b != null) {
                return b;
            }
            AdReport adReport = (AdReport) c_();
            adReport.a = a;
            adReport.g = a.open;
            Logger.d(Logger.DATABASE_TAG, "creating new " + adReport.x());
            adReport.u();
            return adReport;
        }

        public final T b(A a) {
            String[] strArr = new String[]{a.open.toString()};
            String str = "status = ?";
            if (a == null) {
                throw new IllegalArgumentException("null ad");
            }
            String d = a.d();
            if (d == null) {
                throw new IllegalArgumentException("null ad_id");
            }
            Object[] objArr = new String[2];
            objArr[0] = d;
            for (int i = 0; i <= 0; i++) {
                objArr[1] = strArr[0];
            }
            List a2 = a("ad_id = ? AND " + str, (String[]) objArr, "insert_timestamp_millis DESC");
            str = "ad_id = ? AND " + str + ", with params: " + jj.a(objArr);
            int size = a2.size();
            switch (size) {
                case Yylex.YYINITIAL /*0*/:
                    return null;
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    T a3 = a((AdReport) a2.get(0), (Ad) a, false);
                    Logger.d(Logger.DATABASE_TAG, "fetched " + a3.x());
                    return a3;
                default:
                    Logger.w(Logger.DATABASE_TAG, size + " ad_report records for " + str);
                    return null;
            }
        }

        protected T a(T t, Cursor cursor, boolean z) {
            t.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            t.a(bs.f(cursor, "ad_id"));
            t.c = bs.f(cursor, "incentivized_publisher_app_user_id");
            t.e = bs.a(cursor, "is_incentivized").booleanValue();
            t.d = bs.e(cursor, "insert_timestamp_millis");
            t.f = bs.f(cursor, "placement");
            t.g = (a) bs.a(cursor, Games.EXTRA_STATUS, a.class);
            t.h = bs.e(cursor, "update_timestamp_millis");
            t.i = bs.d(cursor, "video_duration_millis");
            t.j = bs.e(cursor, "view_end_millis");
            t.k = bs.e(cursor, "view_start_millis");
            return t;
        }

        private T a(T t, A a, boolean z) {
            if (a == null) {
                t.a = (Ad) a().a((Object) t.d());
            } else {
                t.a = a;
            }
            if (z) {
                t.m = b().b(t);
                t.l = this.a.b((Integer) t.s);
            }
            return t;
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public DatabaseHelper a;
        @Inject
        public com.vungle.publisher.db.model.LocalAdReport.Factory b;
        @Inject
        public com.vungle.publisher.db.model.StreamingAdReport.Factory c;

        @Inject
        Factory() {
        }

        public final List<AdReport<?, ?, ?, ?, ?, ?>> a() {
            List<AdReport<?, ?, ?, ?, ?, ?>> arrayList = new ArrayList();
            for (LocalAdReport add : this.b.d_()) {
                arrayList.add(add);
            }
            for (StreamingAdReport add2 : this.c.d_()) {
                arrayList.add(add2);
            }
            return arrayList;
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

    public enum a {
        open,
        failed,
        playing,
        reportable
    }

    protected abstract com.vungle.publisher.db.model.AdPlay.Factory<T, P, E, A, V, R> a();

    public final /* synthetic */ Object r() {
        return u();
    }

    protected AdReport() {
    }

    protected final String b() {
        return "ad_report";
    }

    protected final String d() {
        return this.a == null ? this.b : this.a.d();
    }

    protected final void a(String str) {
        this.b = str;
    }

    public final A e() {
        return this.a;
    }

    public final void a(Map<String, String> map) {
        Map map2;
        com.vungle.publisher.db.model.AdReportExtra.Factory factory = this.o;
        Integer num = (Integer) this.s;
        if (map != null) {
            Map hashMap = new HashMap();
            for (Entry entry : map.entrySet()) {
                String str = (String) entry.getKey();
                String str2 = (String) entry.getValue();
                AdReportExtra b = factory.b();
                b.a = num;
                b.b = str;
                b.c = str2;
                hashMap.put(str, b);
            }
            map2 = hashMap;
        } else {
            map2 = null;
        }
        this.l = map2;
        int size = map2 == null ? 0 : map2.size();
        if (size <= 0) {
            Logger.d(Logger.DATABASE_TAG, "no new extras for " + x());
            return;
        }
        Logger.d(Logger.DATABASE_TAG, size + " new extras for " + x());
        this.n = true;
        A();
    }

    public final Map<String, AdReportExtra> f() {
        Map<String, AdReportExtra> map = this.l;
        if (map != null) {
            return map;
        }
        map = this.o.b((Integer) this.s);
        this.l = map;
        return map;
    }

    public final boolean g() {
        return this.e;
    }

    public final void b(boolean z) {
        this.e = z;
    }

    public final String h() {
        return this.c;
    }

    public final void b(String str) {
        this.c = str;
    }

    public final void c(String str) {
        this.f = str;
    }

    public final String i() {
        return this.f;
    }

    public final void a(a aVar) {
        Logger.d(Logger.REPORT_TAG, "setting ad_report status " + aVar + " for " + x());
        this.g = aVar;
    }

    public final Integer j() {
        return this.i;
    }

    public final void a(Integer num) {
        Logger.d(Logger.REPORT_TAG, "setting video duration millis " + num + " for " + x());
        this.i = num;
        m();
    }

    public final int k() {
        if (this.k == null) {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view start millis was null");
            return -1;
        } else if (this.j != null) {
            return (int) (this.j.longValue() - this.k.longValue());
        } else {
            Logger.w(Logger.DATABASE_TAG, "unable to calculate ad duration because view end millis was null");
            return -1;
        }
    }

    public final void a(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad end millis " + l + " for " + x());
        this.j = l;
    }

    public final void b(Long l) {
        a(l);
        m();
    }

    public final Long l() {
        return this.k;
    }

    public final void c(Long l) {
        Logger.d(Logger.REPORT_TAG, "setting ad start millis " + l + " for " + x());
        this.k = l;
    }

    public final P q() {
        List z = z();
        P a = a().a(this);
        a.r();
        z.add(a);
        return a;
    }

    public final P[] t() {
        List z = z();
        return (AdPlay[]) z.toArray(a().c(z.size()));
    }

    private List<P> z() {
        List<P> list = this.m;
        if (list != null) {
            return list;
        }
        list = a().b(this);
        this.m = list;
        return list;
    }

    public final Integer u() throws SQLException {
        Integer num = (Integer) super.r();
        A();
        return num;
    }

    private void A() {
        if (this.n) {
            Map map = this.l;
            if (this.s == null) {
                Logger.d(Logger.DATABASE_TAG, "delaying inserting extras for uninserted " + x());
                return;
            }
            Logger.d(Logger.DATABASE_TAG, "replacing extras for " + x());
            this.o.a((Integer) this.s);
            if (!(map == null || map.isEmpty())) {
                com.vungle.publisher.cb.a.a((cb[]) map.values().toArray(com.vungle.publisher.db.model.AdReportExtra.Factory.a(map.size())));
            }
            this.n = false;
            return;
        }
        Logger.v(Logger.DATABASE_TAG, "no new extras to insert for " + x());
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("ad_id", d());
            Long valueOf = Long.valueOf(currentTimeMillis);
            this.d = valueOf;
            contentValues.put("insert_timestamp_millis", valueOf);
        }
        contentValues.put("incentivized_publisher_app_user_id", this.c);
        contentValues.put("is_incentivized", Boolean.valueOf(this.e));
        contentValues.put("placement", this.f);
        contentValues.put(Games.EXTRA_STATUS, jj.a(this.g));
        Long valueOf2 = Long.valueOf(currentTimeMillis);
        this.h = valueOf2;
        contentValues.put("update_timestamp_millis", valueOf2);
        contentValues.put("video_duration_millis", this.i);
        contentValues.put("view_end_millis", this.j);
        contentValues.put("view_start_millis", this.k);
        return contentValues;
    }

    public StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "ad_id", d(), false);
        cb.a(p, "insert_timestamp_millis", this.d, false);
        cb.a(p, "incentivized_publisher_app_user_id", this.c, false);
        cb.a(p, "is_incentivized", Boolean.valueOf(this.e), false);
        cb.a(p, "placement", this.f, false);
        cb.a(p, Games.EXTRA_STATUS, this.g, false);
        cb.a(p, "update_timestamp_millis", this.h, false);
        cb.a(p, "video_duration_millis", this.i, false);
        cb.a(p, "view_end_millis", this.j, false);
        cb.a(p, "view_start_millis", this.k, false);
        cb.a(p, "plays", this.m == null ? "not fetched" : Integer.valueOf(this.m.size()), false);
        return p;
    }
}
