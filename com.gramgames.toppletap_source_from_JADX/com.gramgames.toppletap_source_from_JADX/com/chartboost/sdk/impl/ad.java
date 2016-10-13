package com.chartboost.sdk.impl;

import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.Model.a;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.c;
import com.chartboost.sdk.e;
import com.chartboost.sdk.h;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.common.AdType;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ad extends e {
    private static final String d;
    private static ad e;

    class 1 implements a {
        final /* synthetic */ ad a;

        1(ad adVar) {
            this.a = adVar;
        }

        public void a(a aVar) {
            if (c.h() != null) {
                c.h().didClickInterstitial(aVar.e);
            }
        }

        public void b(a aVar) {
            if (c.h() != null) {
                c.h().didCloseInterstitial(aVar.e);
            }
        }

        public void c(a aVar) {
            if (c.h() != null) {
                c.h().didDismissInterstitial(aVar.e);
            }
        }

        public void d(a aVar) {
            if (c.h() != null) {
                c.h().didCacheInterstitial(aVar.e);
            }
        }

        public void a(a aVar, CBImpressionError cBImpressionError) {
            if (c.h() != null) {
                c.h().didFailToLoadInterstitial(aVar.e, cBImpressionError);
            }
        }

        public void e(a aVar) {
            if (c.h() != null) {
                c.h().didDisplayInterstitial(aVar.e);
            }
        }

        public boolean f(a aVar) {
            if (c.h() != null) {
                return c.h().shouldDisplayInterstitial(aVar.e);
            }
            return true;
        }

        public boolean g(a aVar) {
            if (c.h() != null) {
                return c.h().shouldRequestInterstitial(aVar.e);
            }
            return true;
        }

        public boolean h(a aVar) {
            if (c.h() != null) {
                return c.v();
            }
            return true;
        }
    }

    static {
        d = ad.class.getSimpleName();
    }

    protected ad() {
    }

    public static ad i() {
        if (e == null) {
            e = new ad();
        }
        return e;
    }

    protected boolean b(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        return aVar2.a("media-type") != null && aVar2.a("media-type").equals(MimeTypes.BASE_TYPE_VIDEO);
    }

    protected void g(a aVar) {
        super.g(aVar);
    }

    protected void a(a aVar, com.chartboost.sdk.Libraries.e.a aVar2) {
        if (aVar.a != b.NATIVE) {
            aVar.a(aVar2);
            if (aVar2.c() && aVar2.a("webview").c()) {
                Object e = aVar2.a("webview").e("template");
                if (!TextUtils.isEmpty(e)) {
                    aVar.i = e;
                }
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(aVar2.a("webview"));
                Object jSONObject = new JSONObject();
                try {
                    jSONObject.put("templates", jSONArray);
                    if (aVar.j) {
                        com.chartboost.sdk.b.a.put(aVar, com.chartboost.sdk.b.e.Medium);
                        com.chartboost.sdk.b.a(com.chartboost.sdk.b.e.Medium, com.chartboost.sdk.Libraries.e.a.a(jSONObject));
                        return;
                    }
                    com.chartboost.sdk.b.a.put(aVar, com.chartboost.sdk.b.e.High);
                    com.chartboost.sdk.b.a(com.chartboost.sdk.b.e.High, com.chartboost.sdk.Libraries.e.a.a(jSONObject));
                    return;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                    CBLogging.b(d, "Error while trying to create a template object from the response");
                    a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
                    return;
                }
            }
            CBLogging.b(d, "Response got from the server is empty");
            a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
        } else if (!b(aVar, aVar2) || h.c(aVar2)) {
            super.a(aVar, aVar2);
        } else {
            CBLogging.b(d, "Video Media unavailable for the cached impression");
            a(aVar, CBImpressionError.VIDEO_UNAVAILABLE);
        }
    }

    public void q(a aVar) {
        com.chartboost.sdk.Libraries.e.a A = aVar.A();
        if (A.c() && A.a("webview").c()) {
            String e = A.a("webview").e("template");
            if (A.j("prefetch_required")) {
                com.chartboost.sdk.b.b();
            }
            if (com.chartboost.sdk.b.d().containsKey(e)) {
                aVar.i = e;
                this.b.put(aVar.e, aVar);
                if (aVar.j) {
                    b().d(aVar);
                    aVar.c = a.e.CACHED;
                    n(aVar);
                    return;
                }
                super.a(aVar, aVar.A());
                return;
            }
            CBLogging.b(d, "Cannot able to find the html file for some reason due to some error");
            a(aVar, CBImpressionError.ERROR_LOADING_WEB_VIEW);
        }
    }

    protected a a(String str, boolean z) {
        return new a(a.a.INTERSTITIAL, z, str, false, g());
    }

    protected ay e(a aVar) {
        ay bcVar;
        if (c.H().booleanValue()) {
            aVar.a = b.WEB;
            com.chartboost.sdk.Libraries.e.a c = com.chartboost.sdk.b.c();
            bcVar = new bc(c.A());
            bcVar.a("cache_assets", c, bc.a.AD);
            bcVar.a(l.a.HIGH);
            bcVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e, bc.a.AD);
            if (aVar.j) {
                bcVar.a("cache", Boolean.valueOf(true), bc.a.AD);
                bcVar.b(true);
            } else {
                bcVar.a("cache", Boolean.valueOf(false), bc.a.AD);
            }
            bcVar.a(com.chartboost.sdk.Model.b.f);
        } else {
            aVar.a = b.NATIVE;
            bcVar = new ay(c.A());
            bcVar.a("local-videos", j());
            bcVar.a(l.a.HIGH);
            bcVar.a(com.chartboost.sdk.Model.b.f);
            bcVar.a(GooglePlayServicesInterstitial.LOCATION_KEY, aVar.e);
            if (aVar.j) {
                bcVar.a("cache", AppEventsConstants.EVENT_PARAM_VALUE_YES);
                bcVar.b(true);
            }
        }
        return bcVar;
    }

    protected void i(a aVar) {
        if (aVar.f != a.c.INTERSTITIAL_VIDEO && aVar.a != b.WEB) {
            super.i(aVar);
        }
    }

    protected a c() {
        return new 1(this);
    }

    protected ay l(a aVar) {
        return new ay("/interstitial/show");
    }

    public JSONArray j() {
        JSONArray jSONArray = new JSONArray();
        String[] c = com.chartboost.sdk.Libraries.h.c();
        if (c != null) {
            for (String str : c) {
                if (!str.contains("nomedia")) {
                    jSONArray.put(str);
                }
            }
        }
        return jSONArray;
    }

    public String f() {
        return String.format("%s-%s", new Object[]{AdType.INTERSTITIAL, h()});
    }
}
