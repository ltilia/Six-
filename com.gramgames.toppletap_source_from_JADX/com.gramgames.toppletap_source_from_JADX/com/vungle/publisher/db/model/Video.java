package com.vungle.publisher.db.model;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import com.mopub.mobileads.VastIconXmlManager;
import com.vungle.publisher.bs;
import com.vungle.publisher.cb;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.db.model.Viewable.BaseFactory;
import com.vungle.publisher.protocol.message.RequestAdResponse;
import com.vungle.publisher.protocol.message.RequestAdResponse.CallToActionOverlay;

/* compiled from: vungle */
public abstract class Video<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends Viewable<A, V, R> {
    public Float e;
    public Integer f;
    public Integer g;
    public Boolean h;
    public Boolean i;
    public Integer j;
    public Integer k;
    public Integer l;
    Integer m;
    public Integer n;

    /* compiled from: vungle */
    public static abstract class Factory<A extends Ad<A, V, R>, V extends Video<A, V, R>, R extends RequestAdResponse> extends BaseFactory<A, V, V, R> {
        protected abstract b b();

        protected /* synthetic */ Viewable b(Ad ad, RequestAdResponse requestAdResponse) {
            return a(ad, requestAdResponse);
        }

        protected Factory() {
        }

        protected V a(A a, R r) {
            Video video = (Video) super.b(a, r);
            if (video != null) {
                a(video, (RequestAdResponse) r);
            }
            return video;
        }

        static V a(V v, RequestAdResponse requestAdResponse) {
            v.g = requestAdResponse.l();
            v.k = requestAdResponse.h();
            v.l = requestAdResponse.i();
            v.m = requestAdResponse.j();
            v.n = requestAdResponse.n();
            CallToActionOverlay d = requestAdResponse.d();
            if (d != null) {
                v.e = d.d();
                v.f = d.h();
                v.h = d.e();
                v.i = d.f();
                v.j = d.g();
            }
            return v;
        }

        protected final V a(String str, boolean z) throws SQLException {
            return (Video) a(str, b(), z);
        }

        protected V a(V v, Cursor cursor, boolean z) {
            super.a((Viewable) v, cursor, z);
            v.e = bs.b(cursor, "cta_clickable_percent");
            v.f = bs.d(cursor, "enable_cta_delay_seconds");
            v.g = bs.d(cursor, VastIconXmlManager.HEIGHT);
            v.h = bs.a(cursor, "is_cta_enabled");
            v.i = bs.a(cursor, "is_cta_shown_on_touch");
            v.j = bs.d(cursor, "show_cta_delay_seconds");
            v.k = bs.d(cursor, "show_close_delay_incentivized_seconds");
            v.l = bs.d(cursor, "show_close_delay_interstitial_seconds");
            v.m = bs.d(cursor, "show_countdown_delay_seconds");
            v.n = bs.d(cursor, VastIconXmlManager.WIDTH);
            return v;
        }
    }

    public abstract Uri i();

    protected Video() {
    }

    protected ContentValues a(boolean z) {
        ContentValues a = super.a(z);
        a.put("cta_clickable_percent", this.e);
        a.put("enable_cta_delay_seconds", this.f);
        a.put(VastIconXmlManager.HEIGHT, this.g);
        a.put("is_cta_enabled", this.h);
        a.put("is_cta_shown_on_touch", this.i);
        a.put("show_cta_delay_seconds", this.j);
        a.put("show_close_delay_incentivized_seconds", this.k);
        a.put("show_close_delay_interstitial_seconds", this.l);
        a.put("show_countdown_delay_seconds", this.m);
        a.put(VastIconXmlManager.WIDTH, this.n);
        return a;
    }

    protected StringBuilder p() {
        StringBuilder p = super.p();
        cb.a(p, "cta_clickable_percent", this.e, false);
        cb.a(p, "enable_cta_delay_seconds", this.f, false);
        cb.a(p, VastIconXmlManager.HEIGHT, this.g, false);
        cb.a(p, "is_cta_enabled", this.h, false);
        cb.a(p, "is_cta_shown_on_touch", this.i, false);
        cb.a(p, "show_cta_delay_seconds", this.j, false);
        cb.a(p, "show_close_delay_incentivized_seconds", this.k, false);
        cb.a(p, "show_close_delay_interstitial_seconds", this.l, false);
        cb.a(p, "show_countdown_delay_seconds", this.m, false);
        cb.a(p, VastIconXmlManager.WIDTH, this.n, false);
        return p;
    }
}
