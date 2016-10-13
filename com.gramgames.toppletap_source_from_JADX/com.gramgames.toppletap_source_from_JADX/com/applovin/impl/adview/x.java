package com.applovin.impl.adview;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.applovin.adview.AppLovinAdView;
import com.applovin.impl.sdk.cf;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.internal.WebDialog;
import java.lang.ref.WeakReference;

class x extends Dialog implements w {
    private final Activity a;
    private final AppLovinSdk b;
    private final AppLovinLogger c;
    private RelativeLayout d;
    private AppLovinAdView e;
    private Runnable f;
    private u g;
    private Handler h;
    private ah i;
    private volatile boolean j;
    private volatile boolean k;

    x(AppLovinSdk appLovinSdk, Activity activity) {
        super(activity, WebDialog.DEFAULT_THEME);
        this.i = null;
        this.j = false;
        this.k = false;
        if (appLovinSdk == null) {
            throw new IllegalArgumentException("No sdk specified");
        } else if (activity == null) {
            throw new IllegalArgumentException("No activity specified");
        } else {
            this.b = appLovinSdk;
            this.c = appLovinSdk.getLogger();
            this.a = activity;
            this.f = new ag();
            this.h = new Handler();
            this.e = new AppLovinAdView(appLovinSdk, AppLovinAdSize.INTERSTITIAL, activity);
            this.e.setAutoDestroy(false);
            ((AdViewControllerImpl) this.e.getAdViewController()).setParentDialog(new WeakReference(this));
            requestWindowFeature(1);
            try {
                getWindow().setFlags(activity.getWindow().getAttributes().flags, activity.getWindow().getAttributes().flags);
            } catch (Throwable e) {
                this.c.e("InterstitialAdDialog", "Setting window flags failed.", e);
            }
        }
    }

    private int a(int i) {
        return AppLovinSdkUtils.dpToPx(this.a, i);
    }

    private void a() {
        this.a.runOnUiThread(new af(this));
    }

    private void a(long j) {
        this.h.postDelayed(new ae(this), j);
    }

    private void a(v vVar) {
        int i = 9;
        this.g = u.a(this.b, getContext(), vVar);
        this.g.setVisibility(8);
        this.g.setOnClickListener(new ac(this));
        this.g.setClickable(false);
        cf cfVar = new cf(this.b);
        int a = a(cfVar.l());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(a, a);
        layoutParams.addRule(10);
        layoutParams.addRule(cfVar.y() ? 9 : 11);
        this.g.a(a);
        int a2 = a(cfVar.n());
        int a3 = a(cfVar.p());
        layoutParams.setMargins(a3, a2, a3, a2);
        this.e.addView(this.g, layoutParams);
        this.g.bringToFront();
        int a4 = a(new cf(this.b).r());
        View view = new View(this.a);
        view.setBackgroundColor(0);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(a + a4, a + a4);
        layoutParams2.addRule(10);
        if (!cfVar.x()) {
            i = 11;
        }
        layoutParams2.addRule(i);
        layoutParams2.setMargins(0, a2 - a(5), a3 - a(5), 0);
        view.setOnClickListener(new ad(this));
        this.e.addView(view, layoutParams2);
        view.bringToFront();
    }

    private void b() {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams.addRule(13);
        this.e.setLayoutParams(layoutParams);
        this.d = new RelativeLayout(this.a);
        this.d.setLayoutParams(new RelativeLayout.LayoutParams(-1, -1));
        this.d.setBackgroundColor(-1157627904);
        this.d.addView(this.e);
        setContentView(this.d);
    }

    public void a(ah ahVar) {
        this.e.setAdDisplayListener(new y(this, ahVar));
        this.e.setAdClickListener(new z(this, ahVar));
        this.e.setAdVideoPlaybackListener(new aa(this, ahVar));
        this.i = ahVar;
        ahVar.a(true);
    }

    public void a(AppLovinAd appLovinAd, String str) {
        this.a.runOnUiThread(new ab(this, appLovinAd, str));
    }

    public void dismiss() {
        if (this.i != null) {
            this.i.h();
        }
        if (this.e != null) {
            this.e.destroy();
        }
        this.i = null;
        this.e = null;
        super.dismiss();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        b();
    }
}
