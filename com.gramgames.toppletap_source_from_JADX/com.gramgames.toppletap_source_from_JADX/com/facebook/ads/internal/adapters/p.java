package com.facebook.ads.internal.adapters;

import android.content.Context;
import com.facebook.ads.NativeAd.Image;
import com.facebook.ads.NativeAd.Rating;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.server.AdPlacementType;
import java.util.Map;

public abstract class p implements AdAdapter {
    public abstract void a(int i);

    public abstract void a(Context context, q qVar, Map<String, Object> map);

    public abstract void a(Map<String, Object> map);

    public abstract boolean a();

    public abstract void b(Map<String, Object> map);

    public abstract boolean b();

    public abstract boolean c();

    public abstract boolean d();

    public abstract int e();

    public abstract int f();

    public abstract int g();

    public final AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }

    public abstract Image h();

    public abstract Image i();

    public abstract NativeAdViewAttributes j();

    public abstract String k();

    public abstract String l();

    public abstract String m();

    public abstract String n();

    public abstract String o();

    public abstract Rating p();

    public abstract String q();

    public abstract Image r();

    public abstract String s();

    public abstract String t();

    public abstract String u();

    public abstract String v();

    public abstract boolean w();
}
