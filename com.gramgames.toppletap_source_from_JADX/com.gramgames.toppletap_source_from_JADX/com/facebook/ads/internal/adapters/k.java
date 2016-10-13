package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.AdError;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAd.Rating;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.util.b;
import com.facebook.ads.internal.util.f;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Map;
import org.json.JSONObject;

public class k extends p {
    private Context a;
    private n b;

    public void a(int i) {
        if (this.b != null) {
            this.b.a(i);
        }
    }

    public void a(Context context, q qVar, Map<String, Object> map) {
        this.b = n.a((JSONObject) map.get(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY));
        this.a = context;
        if (this.b == null || f.a(context, this.b)) {
            qVar.a(this, AdError.NO_FILL);
            return;
        }
        if (qVar != null) {
            qVar.a(this);
        }
        b.a = this.b.v();
    }

    public void a(Map<String, Object> map) {
        if (this.b != null) {
            this.b.a((Map) map);
        }
    }

    public boolean a() {
        return w() && this.b.o();
    }

    public void b(Map<String, Object> map) {
        if (this.b != null) {
            this.b.a(this.a, (Map) map);
        }
    }

    public boolean b() {
        return this.b.p();
    }

    public boolean c() {
        return this.b.x();
    }

    public boolean d() {
        return this.b.q();
    }

    public int e() {
        return this.b.r();
    }

    public int f() {
        return this.b.y();
    }

    public int g() {
        return this.b.z();
    }

    public Image h() {
        return !w() ? null : this.b.d();
    }

    public Image i() {
        return !w() ? null : this.b.e();
    }

    public NativeAdViewAttributes j() {
        return !w() ? null : this.b.w();
    }

    public String k() {
        return !w() ? null : this.b.f();
    }

    public String l() {
        return !w() ? null : this.b.g();
    }

    public String m() {
        return !w() ? null : this.b.h();
    }

    public String n() {
        return !w() ? null : this.b.i();
    }

    public String o() {
        return !w() ? null : this.b.j();
    }

    public void onDestroy() {
    }

    public Rating p() {
        return !w() ? null : this.b.k();
    }

    public String q() {
        return !w() ? null : this.b.s();
    }

    public Image r() {
        return !w() ? null : this.b.t();
    }

    public String s() {
        return !w() ? null : this.b.u();
    }

    public String t() {
        return !w() ? null : this.b.l();
    }

    public String u() {
        return !w() ? null : this.b.m();
    }

    public String v() {
        return !w() ? null : this.b.n();
    }

    public boolean w() {
        return this.b != null;
    }
}
