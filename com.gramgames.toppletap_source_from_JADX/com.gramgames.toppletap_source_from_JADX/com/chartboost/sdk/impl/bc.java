package com.chartboost.sdk.impl;

import com.applovin.sdk.AppLovinEventParameters;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.c;
import com.facebook.internal.ServerProtocol;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import gs.gram.mopub.BuildConfig;
import org.json.simple.parser.Yytoken;

public final class bc extends ay {
    private com.chartboost.sdk.Libraries.e.a c;
    private com.chartboost.sdk.Libraries.e.a d;
    private com.chartboost.sdk.Libraries.e.a e;
    private com.chartboost.sdk.Libraries.e.a f;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[a.values().length];
            try {
                a[a.AD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    public enum a {
        AD
    }

    public bc(String str) {
        super(str);
        this.c = com.chartboost.sdk.Libraries.e.a.a();
        this.d = com.chartboost.sdk.Libraries.e.a.a();
        this.e = com.chartboost.sdk.Libraries.e.a.a();
        this.f = com.chartboost.sdk.Libraries.e.a.a();
    }

    protected void d() {
        int i = 1;
        this.d.a("app", b.o);
        this.d.a("bundle", b.e);
        this.d.a("bundle_id", b.f);
        this.d.a("custom_id", c.p());
        this.d.a("session_id", BuildConfig.FLAVOR);
        this.d.a("ui", Integer.valueOf(-1));
        this.d.a("test_mode", Boolean.valueOf(false));
        this.a.a("app", this.d);
        this.e.a("carrier", e.a(e.a("carrier_name", b.q.e("carrier-name")), e.a("mobile_country_code", b.q.e("mobile-country-code")), e.a("mobile_network_code", b.q.e("mobile-network-code")), e.a("iso_country_code", b.q.e("iso-country-code")), e.a("phone_type", Integer.valueOf(b.q.f("phone-type")))));
        this.e.a("model", b.a);
        this.e.a("device_type", b.p);
        this.e.a("os", b.b);
        this.e.a("country", b.c);
        this.e.a("language", b.d);
        this.e.a("timestamp", b.m);
        this.e.a("reachability", Integer.valueOf(ax.a().b()));
        this.e.a("scale", b.n);
        com.chartboost.sdk.Libraries.e.a aVar = this.e;
        String str = "is_portrait";
        if (!CBUtility.c().a()) {
            i = 0;
        }
        aVar.a(str, Integer.valueOf(i));
        this.e.a("rooted_device", Boolean.valueOf(b.r));
        this.e.a("timezone", b.s);
        this.e.a("mobile_network", b.t);
        this.e.a("dw", b.j);
        this.e.a("dh", b.k);
        this.e.a("dpi", b.l);
        this.e.a("w", b.h);
        this.e.a("h", b.i);
        this.e.a("device_family", BuildConfig.FLAVOR);
        this.e.a("retina", Boolean.valueOf(false));
        this.e.a("identity", com.chartboost.sdk.Libraries.c.b());
        com.chartboost.sdk.Libraries.c.a c = com.chartboost.sdk.Libraries.c.c();
        if (c.b()) {
            this.e.a("tracking", Integer.valueOf(c.a()));
        }
        this.a.a("device", this.e);
        this.c.a("framework", BuildConfig.FLAVOR);
        this.c.a(ServerProtocol.DIALOG_PARAM_SDK_VERSION, b.g);
        this.c.a("framework_version", c.d());
        this.c.a("wrapper_version", c.c());
        this.c.a("mediation", c.e());
        this.a.a(ServerProtocol.DIALOG_PARAM_SDK_VERSION, this.c);
        this.f.a("session", Integer.valueOf(CBUtility.a().getInt("cbPrefSessionCount", 0)));
        if (this.f.a("cache").b()) {
            this.f.a("cache", Boolean.valueOf(false));
        }
        if (this.f.a(AppLovinEventParameters.REVENUE_AMOUNT).b()) {
            this.f.a(AppLovinEventParameters.REVENUE_AMOUNT, Integer.valueOf(0));
        }
        if (this.f.a("retry_count").b()) {
            this.f.a("retry_count", Integer.valueOf(0));
        }
        if (this.f.a(GooglePlayServicesInterstitial.LOCATION_KEY).b()) {
            this.f.a(GooglePlayServicesInterstitial.LOCATION_KEY, BuildConfig.FLAVOR);
        }
        this.a.a("ad", this.f);
    }

    public void a(String str, Object obj, a aVar) {
        if (this.a == null) {
            this.a = com.chartboost.sdk.Libraries.e.a.a();
        }
        switch (1.a[aVar.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                this.f.a(str, obj);
                this.a.a("ad", this.f);
            default:
        }
    }
}
