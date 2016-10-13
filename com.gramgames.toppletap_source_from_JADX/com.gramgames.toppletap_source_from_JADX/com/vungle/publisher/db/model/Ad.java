package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.text.TextUtils;
import com.google.android.gms.games.Games;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cg;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.jj;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.q;
import com.vungle.publisher.z;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

/* compiled from: vungle */
public abstract class Ad<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cb<String> {
    protected static final String a;
    protected static final String b;
    protected static final String c;
    protected String d;
    protected String e;
    protected String f;
    protected String g;
    protected Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> h;
    protected long i;
    protected a j;
    protected b k;
    protected long l;
    protected long m;
    protected V n;
    String o;
    protected boolean p;
    protected boolean q;

    /* compiled from: vungle */
    public static abstract class Factory<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends com.vungle.publisher.cb.a<A, String> {
        @Inject
        public com.vungle.publisher.db.model.EventTracking.Factory a;
        @Inject
        public EventBus b;

        protected abstract b b();

        protected abstract com.vungle.publisher.db.model.Video.Factory<A, V, R> b_();

        protected Factory() {
        }

        protected A a(R r) {
            Ad ad = (Ad) c_();
            String f = r.f();
            ad.s = f;
            ad.k = b();
            ad.h = this.a.a(f, r.k());
            ad.n = b_().a(ad, (RequestAdResponse) r);
            b(ad, r);
            return ad;
        }

        public int a(A a, R r) {
            b(a, r);
            com.vungle.publisher.db.model.Video.Factory.a(a.k(), (RequestAdResponse) r).m();
            com.vungle.publisher.db.model.EventTracking.Factory factory = this.a;
            String f = r.f();
            factory.a(f);
            Map a2 = factory.a(f, r.k());
            com.vungle.publisher.db.model.EventTracking.Factory.a(a2);
            a.h = a2;
            return a.m();
        }

        protected final int c() {
            Logger.d(Logger.DATABASE_TAG, "deleting " + b() + " records without pending reports in status " + a.deleting);
            return this.c.getWritableDatabase().delete("ad", Ad.b + " AND status = ?", new String[]{r0.toString()});
        }

        public boolean a(Ad<?, ?, ?> ad) {
            if (!a("id = ? AND " + Ad.b + " AND ((expiration_timestamp_seconds IS NULL OR expiration_timestamp_seconds <= ?) OR status != ?)", new String[]{ad.d(), Long.toString(System.currentTimeMillis() / 1000), a.ready.toString()})) {
                return false;
            }
            Logger.d(Logger.DATABASE_TAG, "deleting ad after successful report");
            if (ad.n() > 0) {
                return true;
            }
            return false;
        }

        protected final A a(b bVar, String str) {
            return (Ad) super.a((Object) str, "type = ?", new String[]{bVar.toString()});
        }

        protected final int a(List<? extends Ad<?, ?, ?>> list, a aVar) {
            int size = list.size();
            Object[] objArr = new String[size];
            int i = 0;
            for (Ad ad : list) {
                objArr[0] = ad.d();
                a i2 = ad.i();
                int i3 = (aVar == a.ready || i2 != a.ready) ? (aVar != a.ready || i2 == a.ready) ? 0 : 1 : -1;
                i3 += i;
                ad.a(aVar);
                i = i3;
            }
            ContentValues contentValues = new ContentValues();
            contentValues.put(Games.EXTRA_STATUS, aVar.toString());
            String str = "id IN (" + bs.a(size) + ")";
            Logger.d(Logger.DATABASE_TAG, "updating status of ads " + jj.a(objArr) + " to " + aVar);
            int updateWithOnConflict = this.c.getWritableDatabase().updateWithOnConflict(e_(), contentValues, str, objArr, 3);
            if (updateWithOnConflict > 0) {
                if (i > 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability increased by " + i);
                    this.b.a(new z());
                } else if (i < 0) {
                    Logger.d(Logger.DATABASE_TAG, "ad availability decreased by " + i);
                    this.b.a(new q());
                }
            }
            return updateWithOnConflict;
        }

        private static A b(A a, R r) {
            a.d = r.b();
            Object c = r.c();
            String e = r.e();
            if (TextUtils.isEmpty(c)) {
                a.e = e;
            } else {
                a.e = c;
                a.f = e;
            }
            a.g = r.g();
            return a;
        }

        protected A a(A a, Cursor cursor, boolean z) {
            a.d = bs.f(cursor, "advertising_app_vungle_id");
            a.e = bs.f(cursor, "call_to_action_final_url");
            a.f = bs.f(cursor, "call_to_action_url");
            a.g = bs.f(cursor, "delivery_id");
            a.s = bs.f(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            a.i = bs.e(cursor, "insert_timestamp_millis").longValue();
            a.j = (a) bs.a(cursor, Games.EXTRA_STATUS, a.class);
            a.k = (b) bs.a(cursor, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, b.class);
            a.l = bs.e(cursor, "update_timestamp_millis").longValue();
            a.m = bs.e(cursor, "failed_timestamp_millis").longValue();
            if (z) {
                b(a);
                a((Ad) a, z);
            }
            return a;
        }

        protected final Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> b(A a) {
            if (a.p) {
                return a.h;
            }
            Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> b = this.a.b((String) a.s);
            a.h = b;
            a.p = true;
            return b;
        }

        protected final V a(A a, boolean z) {
            if (a.q) {
                return a.n;
            }
            V a2 = b_().a((String) a.s, z);
            a.n = a2;
            a.q = true;
            return a2;
        }
    }

    public enum a {
        aware,
        failed,
        invalid,
        preparing,
        ready,
        viewed,
        deleting
    }

    public enum b {
        local,
        streaming
    }

    protected abstract Factory<A, V, R> a();

    public /* synthetic */ Object r() {
        return l();
    }

    public final /* bridge */ /* synthetic */ Object s() {
        return (String) this.s;
    }

    static {
        a = "(SELECT DISTINCT ad_id FROM ad_report WHERE status IN ('" + com.vungle.publisher.db.model.AdReport.a.reportable + "', '" + com.vungle.publisher.db.model.AdReport.a.playing + "'))";
        b = "id NOT IN " + a;
        c = "id IN " + a;
    }

    protected Ad() {
        this.r = String.class;
    }

    protected final String b() {
        return "ad";
    }

    protected final boolean f_() {
        return false;
    }

    public final String d() {
        return (String) this.s;
    }

    public final String e() {
        return this.d;
    }

    public final String f() {
        return this.e;
    }

    public final String g() {
        return this.f;
    }

    public final String h() {
        return this.g;
    }

    public final String[] a(com.vungle.publisher.db.model.EventTracking.a aVar) {
        if (t() != null) {
            List<EventTracking> list = (List) t().get(aVar);
            if (list != null) {
                int size = list.size();
                if (size > 0) {
                    String[] strArr = new String[size];
                    int i = 0;
                    for (EventTracking eventTracking : list) {
                        size = i + 1;
                        strArr[i] = eventTracking.c;
                        i = size;
                    }
                    return strArr;
                }
            }
        }
        return null;
    }

    private Map<com.vungle.publisher.db.model.EventTracking.a, List<EventTracking>> t() {
        return a().b(this);
    }

    public final a i() {
        return this.j;
    }

    public void a(a aVar) {
        Logger.v(Logger.PREPARE_TAG, "setting status from " + this.j + " to " + aVar + " for " + x());
        this.j = aVar;
        if (aVar == a.failed) {
            this.m = System.currentTimeMillis();
        }
    }

    public final void b(a aVar) {
        a().a(Arrays.asList(new Ad[]{this}), aVar);
    }

    public final long j() {
        return this.m;
    }

    public final V k() {
        return a().a(this, false);
    }

    public <W extends cg<A, V, R>> W a(com.vungle.publisher.cg.b bVar) {
        throw new IllegalArgumentException("unknown viewable type: " + bVar);
    }

    public String l() throws SQLException {
        String str = (String) super.r();
        if (this.h != null) {
            for (List<EventTracking> it : this.h.values()) {
                for (EventTracking r : it) {
                    r.r();
                }
            }
        }
        if (this.n != null) {
            this.n.r();
        }
        return str;
    }

    public int m() {
        int m = super.m();
        if (m == 1 && this.n != null) {
            this.n.m();
        }
        return m;
    }

    public int n() {
        return super.n();
    }

    protected ContentValues a(boolean z) {
        long currentTimeMillis = System.currentTimeMillis();
        this.l = currentTimeMillis;
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, (String) this.s);
            this.i = currentTimeMillis;
            contentValues.put("insert_timestamp_millis", Long.valueOf(currentTimeMillis));
            contentValues.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.k.toString());
        }
        contentValues.put("advertising_app_vungle_id", this.d);
        contentValues.put("call_to_action_final_url", this.e);
        contentValues.put("call_to_action_url", this.f);
        contentValues.put("delivery_id", this.g);
        contentValues.put(Games.EXTRA_STATUS, this.j.toString());
        contentValues.put("update_timestamp_millis", Long.valueOf(currentTimeMillis));
        contentValues.put("failed_timestamp_millis", Long.valueOf(this.m));
        return contentValues;
    }

    protected final StringBuilder o() {
        StringBuilder o = super.o();
        cb.a(o, UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, this.k, false);
        return o;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "advertising_app_vungle_id", this.d, false);
        cb.a(p, "call_to_action_final_url", this.e, false);
        cb.a(p, "call_to_action_url", this.f, false);
        cb.a(p, "delivery_id", this.g, false);
        cb.a(p, "insert_timestamp_millis", Long.valueOf(this.i), false);
        cb.a(p, Games.EXTRA_STATUS, this.j, false);
        cb.a(p, "update_timestamp_millis", Long.valueOf(this.l), false);
        cb.a(p, "failed_timestamp_millis", Long.valueOf(this.m), false);
        cb.a(p, "eventTrackings", this.h == null ? null : Integer.valueOf(this.h.size()), false);
        return p;
    }

    public boolean equals(Object ad) {
        return (ad instanceof Ad) && a((Ad) ad);
    }

    public final boolean a(Ad<?, ?, ?> ad) {
        return (ad == null || ad.s == null || !((String) ad.s).equals(this.s)) ? false : true;
    }

    public int hashCode() {
        return this.s == null ? super.hashCode() : ((String) this.s).hashCode();
    }

    public final boolean q() {
        return a().a(this);
    }
}
