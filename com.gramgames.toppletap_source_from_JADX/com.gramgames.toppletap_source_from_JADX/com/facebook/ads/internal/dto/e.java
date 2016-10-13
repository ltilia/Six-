package com.facebook.ads.internal.dto;

import android.content.Context;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.adapters.f;
import com.facebook.ads.internal.c;
import com.facebook.ads.internal.d;
import com.facebook.ads.internal.server.AdPlacementType;
import com.facebook.ads.internal.util.AdInternalSettings;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.AdType;
import com.mopub.unity.BuildConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.simple.parser.Yytoken;

public class e {
    protected String a;
    protected AdPlacementType b;
    protected b c;
    public Context d;
    public com.facebook.ads.internal.e e;
    public boolean f;
    private c g;
    private int h;
    private AdSize i;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.BANNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[b.NATIVE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public e(Context context, String str, AdSize adSize, com.facebook.ads.internal.e eVar, c cVar, int i, boolean z) {
        this.a = str;
        this.i = adSize;
        this.e = eVar;
        this.c = b.a(eVar);
        this.g = cVar;
        this.h = i;
        this.f = z;
        a(context);
    }

    private void a(Context context) {
        this.d = context;
        f.a(context);
        f();
    }

    private void a(Map<String, String> map, String str, String str2) {
        if (AdInternalSettings.shouldUseLiveRailEndpoint()) {
            map.put("LR_" + str, str2);
        } else {
            map.put(str, str2);
        }
    }

    private static Map<String, String> b(Context context) {
        Map<String, String> hashMap = new HashMap();
        hashMap.put("VIEWABLE", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("SCHEMA", AdType.STATIC_NATIVE);
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", BuildConfig.VERSION_NAME);
        hashMap.put("LOCALE", Locale.getDefault().toString());
        float f = context.getResources().getDisplayMetrics().density;
        int i = context.getResources().getDisplayMetrics().widthPixels;
        int i2 = context.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(f));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int) (((float) i) / f)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int) (((float) i2) / f)));
        hashMap.put("IDFA", f.n);
        hashMap.put("IDFA_FLAG", f.o ? AppEventsConstants.EVENT_PARAM_VALUE_NO : AppEventsConstants.EVENT_PARAM_VALUE_YES);
        hashMap.put("ATTRIBUTION_ID", f.m);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", f.a);
        hashMap.put("BUNDLE", f.d);
        hashMap.put("APPNAME", f.e);
        hashMap.put("APPVERS", f.f);
        hashMap.put("APPBUILD", String.valueOf(f.g));
        hashMap.put("CARRIER", f.h);
        hashMap.put("MAKE", f.b);
        hashMap.put("MODEL", f.c);
        hashMap.put("COPPA", String.valueOf(AdSettings.isChildDirected()));
        hashMap.put("SDK_CAPABILITY", d.b());
        return hashMap;
    }

    private void f() {
        if (this.c == null) {
            this.c = b.UNKNOWN;
        }
        switch (1.a[this.c.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                this.b = AdPlacementType.INTERSTITIAL;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                this.b = AdPlacementType.BANNER;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                this.b = AdPlacementType.NATIVE;
            default:
                this.b = AdPlacementType.UNKNOWN;
        }
    }

    public String a() {
        return this.a;
    }

    public b b() {
        return this.c;
    }

    public AdSize c() {
        return this.i;
    }

    public int d() {
        return this.h;
    }

    public Map<String, String> e() {
        Map<String, String> hashMap = new HashMap();
        a(hashMap, "PLACEMENT_ID", this.a);
        if (this.b != AdPlacementType.UNKNOWN) {
            a(hashMap, "PLACEMENT_TYPE", this.b.toString().toLowerCase());
        }
        for (Entry entry : b(this.d).entrySet()) {
            a(hashMap, (String) entry.getKey(), (String) entry.getValue());
        }
        if (this.i != null) {
            a(hashMap, "WIDTH", String.valueOf(this.i.getWidth()));
            a(hashMap, "HEIGHT", String.valueOf(this.i.getHeight()));
        }
        a(hashMap, "ADAPTERS", f.a(this.b));
        if (this.e != null) {
            a(hashMap, "TEMPLATE_ID", String.valueOf(this.e.a()));
        }
        if (this.g != null) {
            a(hashMap, "REQUEST_TYPE", String.valueOf(this.g.a()));
        }
        if (this.f) {
            a(hashMap, "TEST_MODE", AppEventsConstants.EVENT_PARAM_VALUE_YES);
        }
        if (this.h != 0) {
            a(hashMap, "NUM_ADS_REQUESTED", String.valueOf(this.h));
        }
        a(hashMap, "CLIENT_EVENTS", com.facebook.ads.internal.util.c.a());
        return hashMap;
    }
}
