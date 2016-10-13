package com.facebook.ads;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Rect;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.adapters.o;
import com.facebook.ads.internal.adapters.p;
import com.facebook.ads.internal.dto.d;
import com.facebook.ads.internal.h;
import com.facebook.ads.internal.util.k;
import com.facebook.ads.internal.util.l;
import com.facebook.ads.internal.util.m;
import com.facebook.ads.internal.view.n;
import com.mopub.mobileads.VastIconXmlManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.json.JSONObject;

public class NativeAd implements Ad {
    private static final com.facebook.ads.internal.c a;
    private static final String b;
    private static WeakHashMap<View, WeakReference<NativeAd>> c;
    private final Context d;
    private final String e;
    private AdListener f;
    private ImpressionListener g;
    private h h;
    private volatile boolean i;
    private p j;
    private d k;
    private View l;
    private List<View> m;
    private OnTouchListener n;
    private e o;
    private o p;
    private a q;
    private b r;
    private com.facebook.ads.internal.view.o s;
    private Type t;
    private boolean u;
    private boolean v;
    private boolean w;

    class 1 extends com.facebook.ads.internal.a {
        final /* synthetic */ EnumSet a;
        final /* synthetic */ NativeAd b;

        class 1 implements l {
            final /* synthetic */ p a;
            final /* synthetic */ 1 b;

            1(1 1, p pVar) {
                this.b = 1;
                this.a = pVar;
            }

            public void a() {
                this.b.b.j = this.a;
                this.b.b.g();
                if (this.b.b.f != null) {
                    this.b.b.f.onAdLoaded(this.b.b);
                }
            }
        }

        1(NativeAd nativeAd, EnumSet enumSet) {
            this.b = nativeAd;
            this.a = enumSet;
        }

        public void a() {
            if (this.b.h != null) {
                this.b.h.c();
            }
        }

        public void a(p pVar) {
            if (pVar != null) {
                List arrayList = new ArrayList(2);
                if (this.a.contains(MediaCacheFlag.ICON) && pVar.h() != null) {
                    arrayList.add(pVar.h().getUrl());
                }
                if (this.a.contains(MediaCacheFlag.IMAGE) && pVar.i() != null) {
                    arrayList.add(pVar.i().getUrl());
                }
                m.a(this.b.d, arrayList, new 1(this, pVar));
            }
        }

        public void a(com.facebook.ads.internal.b bVar) {
            if (this.b.f != null) {
                this.b.f.onError(this.b, bVar.b());
            }
        }

        public void b() {
            if (this.b.f != null) {
                this.b.f.onAdClicked(this.b);
            }
        }

        public void c() {
            throw new IllegalStateException("Native ads manager their own impressions.");
        }
    }

    class 2 implements n {
        final /* synthetic */ NativeAd a;

        2(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public void a(int i) {
            if (this.a.j != null) {
                this.a.j.a(i);
            }
        }
    }

    class 3 extends com.facebook.ads.internal.adapters.e.a {
        final /* synthetic */ NativeAd a;

        3(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public void a() {
            this.a.p.a(this.a.l);
            this.a.p.a(this.a.t);
            this.a.p.a(this.a.u);
            this.a.p.b(this.a.v);
            this.a.p.c(this.a.w);
            this.a.p.a();
        }
    }

    class 4 extends com.facebook.ads.internal.adapters.c {
        final /* synthetic */ NativeAd a;

        4(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public boolean a() {
            return true;
        }
    }

    class 5 extends com.facebook.ads.internal.adapters.c {
        final /* synthetic */ String a;
        final /* synthetic */ NativeAd b;

        5(NativeAd nativeAd, String str) {
            this.b = nativeAd;
            this.a = str;
        }

        public boolean b() {
            return true;
        }

        public String c() {
            return this.a;
        }
    }

    public static class Image {
        private final String a;
        private final int b;
        private final int c;

        private Image(String str, int i, int i2) {
            this.a = str;
            this.b = i;
            this.c = i2;
        }

        public static Image fromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            String optString = jSONObject.optString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
            return optString != null ? new Image(optString, jSONObject.optInt(VastIconXmlManager.WIDTH, 0), jSONObject.optInt(VastIconXmlManager.HEIGHT, 0)) : null;
        }

        public int getHeight() {
            return this.c;
        }

        public String getUrl() {
            return this.a;
        }

        public int getWidth() {
            return this.b;
        }
    }

    public enum MediaCacheFlag {
        NONE(0),
        ICON(1),
        IMAGE(2);
        
        public static final EnumSet<MediaCacheFlag> ALL;
        private final long a;

        static {
            ALL = EnumSet.allOf(MediaCacheFlag.class);
        }

        private MediaCacheFlag(long j) {
            this.a = j;
        }

        public long getCacheFlagValue() {
            return this.a;
        }
    }

    public static class Rating {
        private final double a;
        private final double b;

        private Rating(double d, double d2) {
            this.a = d;
            this.b = d2;
        }

        public static Rating fromJSONObject(JSONObject jSONObject) {
            if (jSONObject == null) {
                return null;
            }
            double optDouble = jSONObject.optDouble("value", 0.0d);
            double optDouble2 = jSONObject.optDouble("scale", 0.0d);
            return (optDouble == 0.0d || optDouble2 == 0.0d) ? null : new Rating(optDouble, optDouble2);
        }

        public double getScale() {
            return this.b;
        }

        public double getValue() {
            return this.a;
        }
    }

    private class a implements OnClickListener, OnTouchListener {
        final /* synthetic */ NativeAd a;
        private int b;
        private int c;
        private int d;
        private int e;
        private float f;
        private float g;
        private int h;
        private int i;
        private boolean j;

        private a(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public Map<String, Object> a() {
            Map<String, Object> hashMap = new HashMap();
            hashMap.put("clickX", Integer.valueOf(this.b));
            hashMap.put("clickY", Integer.valueOf(this.c));
            hashMap.put(VastIconXmlManager.WIDTH, Integer.valueOf(this.d));
            hashMap.put(VastIconXmlManager.HEIGHT, Integer.valueOf(this.e));
            hashMap.put("adPositionX", Float.valueOf(this.f));
            hashMap.put("adPositionY", Float.valueOf(this.g));
            hashMap.put("visibleWidth", Integer.valueOf(this.i));
            hashMap.put("visibleHeight", Integer.valueOf(this.h));
            return hashMap;
        }

        public void onClick(View view) {
            if (this.a.f != null) {
                this.a.f.onAdClicked(this.a);
            }
            if (!this.j) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            Map a = a();
            if (this.a.t != null) {
                a.put("nti", String.valueOf(this.a.t.getValue()));
            }
            if (this.a.u) {
                a.put("nhs", String.valueOf(this.a.u));
            }
            this.a.j.b(a);
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 0 && this.a.l != null) {
                this.d = this.a.l.getWidth();
                this.e = this.a.l.getHeight();
                int[] iArr = new int[2];
                this.a.l.getLocationInWindow(iArr);
                this.f = (float) iArr[0];
                this.g = (float) iArr[1];
                Rect rect = new Rect();
                this.a.l.getGlobalVisibleRect(rect);
                this.i = rect.width();
                this.h = rect.height();
                int[] iArr2 = new int[2];
                view.getLocationInWindow(iArr2);
                this.b = (((int) motionEvent.getX()) + iArr2[0]) - iArr[0];
                this.c = (iArr2[1] + ((int) motionEvent.getY())) - iArr[1];
                this.j = true;
            }
            return this.a.n != null && this.a.n.onTouch(view, motionEvent);
        }
    }

    private class b extends BroadcastReceiver {
        final /* synthetic */ NativeAd a;
        private boolean b;

        private b(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public void a() {
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.facebook.ads.native.impression:" + this.a.j.q());
            intentFilter.addAction("com.facebook.ads.native.click:" + this.a.j.q());
            LocalBroadcastManager.getInstance(this.a.d).registerReceiver(this, intentFilter);
            this.b = true;
        }

        public void b() {
            if (this.b) {
                try {
                    LocalBroadcastManager.getInstance(this.a.d).unregisterReceiver(this);
                } catch (Exception e) {
                }
            }
        }

        public void onReceive(Context context, Intent intent) {
            Object obj = intent.getAction().split(":")[0];
            if ("com.facebook.ads.native.impression".equals(obj)) {
                this.a.p.a();
            } else if ("com.facebook.ads.native.click".equals(obj)) {
                Map hashMap = new HashMap();
                hashMap.put("mil", Boolean.valueOf(true));
                this.a.j.b(hashMap);
            }
        }
    }

    private class c extends com.facebook.ads.internal.adapters.c {
        final /* synthetic */ NativeAd a;

        private c(NativeAd nativeAd) {
            this.a = nativeAd;
        }

        public boolean a() {
            return false;
        }

        public void d() {
            if (this.a.g != null) {
                this.a.g.onLoggingImpression(this.a);
            }
            if ((this.a.f instanceof ImpressionListener) && this.a.f != this.a.g) {
                ((ImpressionListener) this.a.f).onLoggingImpression(this.a);
            }
        }

        public void e() {
        }
    }

    static {
        a = com.facebook.ads.internal.c.ADS;
        b = NativeAd.class.getSimpleName();
        c = new WeakHashMap();
    }

    public NativeAd(Context context, p pVar, d dVar) {
        this(context, null);
        this.k = dVar;
        this.i = true;
        this.j = pVar;
    }

    public NativeAd(Context context, String str) {
        this.m = new ArrayList();
        this.d = context;
        this.e = str;
    }

    NativeAd(NativeAd nativeAd) {
        this(nativeAd.d, null);
        this.k = nativeAd.k;
        this.i = true;
        this.j = nativeAd.j;
    }

    private void a(View view) {
        this.m.add(view);
        view.setOnClickListener(this.q);
        view.setOnTouchListener(this.q);
    }

    private void a(List<View> list, View view) {
        list.add(view);
        if ((view instanceof ViewGroup) && !(view instanceof com.facebook.ads.internal.view.video.a)) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                a((List) list, viewGroup.getChildAt(i));
            }
        }
    }

    private int d() {
        return this.k != null ? this.k.e() : this.j != null ? this.j.f() : (this.h == null || this.h.a() == null) ? 0 : this.h.a().f();
    }

    public static void downloadAndDisplayImage(Image image, ImageView imageView) {
        if (image != null && imageView != null) {
            new k(imageView).execute(new String[]{image.getUrl()});
        }
    }

    private int e() {
        return this.k != null ? this.k.g() : this.j != null ? this.j.g() : (this.h == null || this.h.a() == null) ? AdError.NETWORK_ERROR_CODE : this.h.a().g();
    }

    private void f() {
        for (View view : this.m) {
            view.setOnClickListener(null);
            view.setOnTouchListener(null);
        }
        this.m.clear();
    }

    private void g() {
        if (this.j != null && this.j.a()) {
            this.r = new b();
            this.r.a();
            this.p = new o(this.d, new 4(this), this.j);
        }
    }

    private int getMinViewabilityPercentage() {
        return this.k != null ? this.k.e() : (this.h == null || this.h.a() == null) ? 1 : this.h.a().e();
    }

    private void logExternalClick(String str) {
        Map hashMap = new HashMap();
        hashMap.put("eil", Boolean.valueOf(true));
        hashMap.put("eil_source", str);
        this.j.b(hashMap);
    }

    private void logExternalImpression() {
        this.p.a();
    }

    private void registerExternalLogReceiver(String str) {
        this.p = new o(this.d, new 5(this, str), this.j);
    }

    String a() {
        return !isAdLoaded() ? null : this.j.t();
    }

    void a(Type type) {
        this.t = type;
    }

    void a(boolean z) {
        this.u = z;
    }

    String b() {
        return !isAdLoaded() ? null : this.j.u();
    }

    void b(boolean z) {
        this.v = z;
    }

    String c() {
        return !isAdLoaded() ? null : this.j.v();
    }

    public void destroy() {
        if (this.r != null) {
            this.r.b();
            this.r = null;
        }
        if (this.h != null) {
            this.h.d();
            this.h = null;
        }
    }

    public String getAdBody() {
        return !isAdLoaded() ? null : this.j.m();
    }

    public String getAdCallToAction() {
        return !isAdLoaded() ? null : this.j.n();
    }

    public Image getAdChoicesIcon() {
        return !isAdLoaded() ? null : this.j.r();
    }

    public String getAdChoicesLinkUrl() {
        return !isAdLoaded() ? null : this.j.s();
    }

    public Image getAdCoverImage() {
        return !isAdLoaded() ? null : this.j.i();
    }

    public Image getAdIcon() {
        return !isAdLoaded() ? null : this.j.h();
    }

    public String getAdSocialContext() {
        return !isAdLoaded() ? null : this.j.o();
    }

    public Rating getAdStarRating() {
        return !isAdLoaded() ? null : this.j.p();
    }

    public String getAdSubtitle() {
        return !isAdLoaded() ? null : this.j.l();
    }

    public String getAdTitle() {
        return !isAdLoaded() ? null : this.j.k();
    }

    public NativeAdViewAttributes getAdViewAttributes() {
        return !isAdLoaded() ? null : this.j.j();
    }

    public String getId() {
        return !isAdLoaded() ? null : this.j.q();
    }

    public boolean isAdLoaded() {
        return this.j != null;
    }

    public boolean isNativeConfigEnabled() {
        return isAdLoaded() && this.j.c();
    }

    public void loadAd() {
        loadAd(EnumSet.of(MediaCacheFlag.NONE));
    }

    public void loadAd(EnumSet<MediaCacheFlag> enumSet) {
        if (this.i) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.i = true;
        this.h = new h(this.d, this.e, com.facebook.ads.internal.e.NATIVE_UNKNOWN, null, a, 1, true);
        this.h.a(new 1(this, enumSet));
        this.h.b();
    }

    public void registerViewForInteraction(View view) {
        List arrayList = new ArrayList();
        a(arrayList, view);
        registerViewForInteraction(view, arrayList);
    }

    public void registerViewForInteraction(View view, List<View> list) {
        if (view == null) {
            throw new IllegalArgumentException("Must provide a View");
        } else if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        } else if (isAdLoaded()) {
            if (this.l != null) {
                Log.w(b, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                unregisterView();
            }
            if (c.containsKey(view)) {
                Log.w(b, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                ((NativeAd) ((WeakReference) c.get(view)).get()).unregisterView();
            }
            this.q = new a();
            this.l = view;
            if (view instanceof ViewGroup) {
                this.s = new com.facebook.ads.internal.view.o(view.getContext(), new 2(this));
                ((ViewGroup) view).addView(this.s);
            }
            for (View a : list) {
                a(a);
            }
            this.p = new o(this.d, new c(), this.j);
            this.p.a((List) list);
            this.o = new e(this.d, this.l, getMinViewabilityPercentage(), new 3(this));
            this.o.a(d());
            this.o.b(e());
            this.o.a();
            c.put(view, new WeakReference(this));
        } else {
            Log.e(b, "Ad not loaded");
        }
    }

    public void setAdListener(AdListener adListener) {
        this.f = adListener;
    }

    @Deprecated
    public void setImpressionListener(ImpressionListener impressionListener) {
        this.g = impressionListener;
    }

    public void setMediaViewAutoplay(boolean z) {
        this.w = z;
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
        this.n = onTouchListener;
    }

    public void unregisterView() {
        if (this.l != null) {
            if (c.containsKey(this.l) && ((WeakReference) c.get(this.l)).get() == this) {
                if ((this.l instanceof ViewGroup) && this.s != null) {
                    ((ViewGroup) this.l).removeView(this.s);
                    this.s = null;
                }
                c.remove(this.l);
                f();
                this.l = null;
                if (this.o != null) {
                    this.o.b();
                    this.o = null;
                }
                this.p = null;
                return;
            }
            throw new IllegalStateException("View not registered with this NativeAd");
        }
    }
}
