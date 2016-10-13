package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.vungle.log.Logger;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cb.a;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import gs.gram.mopub.BuildConfig;
import java.util.List;

/* compiled from: vungle */
public abstract class AdPlay<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends cb<Integer> {
    public T a;
    public Integer b;
    public Long c;
    List<E> d;

    /* compiled from: vungle */
    public static abstract class Factory<T extends AdReport<T, P, E, A, V, R>, P extends AdPlay<T, P, E, A, V, R>, E extends AdReportEvent<T, P, E, A, V, R>, A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends a<P, Integer> {
        protected final /* synthetic */ cb a(cb cbVar, Cursor cursor) {
            AdPlay adPlay = (AdPlay) cbVar;
            adPlay.s = bs.d(cursor, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            adPlay.b = bs.d(cursor, "watched_millis");
            adPlay.c = bs.e(cursor, "start_millis");
            return adPlay;
        }

        protected Factory() {
        }

        protected final P a(T t) {
            AdPlay adPlay = (AdPlay) c_();
            adPlay.a = t;
            return adPlay;
        }

        protected final List<P> b(T t) {
            if (t == null) {
                throw new IllegalArgumentException("null ad_report");
            }
            if (((Integer) t.s()) == null) {
                throw new IllegalArgumentException("null report_id");
            }
            List<P> a = a("report_id = ?", new String[]{((Integer) t.s()).toString()}, "start_millis ASC", null);
            for (P p : a) {
                p.a = t;
            }
            return a;
        }
    }

    protected abstract com.vungle.publisher.db.model.AdReportEvent.Factory<T, P, E, A, V, R> a();

    protected AdPlay() {
    }

    public final E a(AdReportEvent.a aVar, Object obj) {
        List e = e();
        Logger.d(Logger.REPORT_TAG, "adding report event " + aVar + (obj == null ? BuildConfig.FLAVOR : ", value " + obj + " for " + x()));
        E a = a().a(this, aVar, obj);
        a.r();
        e.add(a);
        return a;
    }

    public final E[] d() {
        List e = e();
        return (AdReportEvent[]) e.toArray(a().c(e.size()));
    }

    private List<E> e() {
        List<E> list = this.d;
        if (list != null) {
            return list;
        }
        list = a().a(this);
        this.d = list;
        return list;
    }

    protected final String b() {
        return "ad_play";
    }

    private Integer f() {
        return this.a == null ? null : (Integer) this.a.s();
    }

    protected final ContentValues a(boolean z) {
        ContentValues contentValues = new ContentValues();
        if (z) {
            contentValues.put("report_id", f());
            contentValues.put("start_millis", this.c);
        } else {
            contentValues.put("watched_millis", this.b);
        }
        return contentValues;
    }

    protected final StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "report_id", f(), false);
        cb.a(p, "start_millis", this.c, false);
        cb.a(p, "watched_millis", this.b, false);
        return p;
    }
}
