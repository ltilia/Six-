package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Build.VERSION;
import android.text.TextUtils;
import android.view.ViewGroup.LayoutParams;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.chartboost.sdk.f;
import com.chartboost.sdk.g;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;

public final class bs extends g {
    private static boolean v;
    public String l;
    protected int m;
    private String n;
    private String o;
    private com.chartboost.sdk.Libraries.e.a p;
    private float q;
    private float r;
    private boolean s;
    private long t;
    private long u;
    private b w;

    class 1 implements com.chartboost.sdk.impl.n.b<String> {
        final /* synthetic */ bs a;

        1(bs bsVar) {
            this.a = bsVar;
        }

        public void a(String str) {
        }
    }

    class 2 implements com.chartboost.sdk.impl.n.a {
        final /* synthetic */ bs a;

        2(bs bsVar) {
            this.a = bsVar;
        }

        public void a(s sVar) {
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ c a;
        final /* synthetic */ bs b;

        3(bs bsVar, c cVar) {
            this.b = bsVar;
            this.a = cVar;
        }

        public void run() {
            String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"onForeground\", \"\")";
            CBLogging.a("CBWebViewProtocol", "Calling native to javascript: " + str);
            this.a.b.loadUrl(str);
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ c a;
        final /* synthetic */ bs b;

        4(bs bsVar, c cVar) {
            this.b = bsVar;
            this.a = cVar;
        }

        public void run() {
            String str = "javascript:Chartboost.EventHandler.handleNativeEvent(\"onBackground\", \"\")";
            CBLogging.a("CBWebViewProtocol", "Calling native to javascript: " + str);
            this.a.b.loadUrl(str);
        }
    }

    private class a extends WebViewClient {
        final /* synthetic */ bs a;

        private a(bs bsVar) {
            this.a = bsVar;
        }

        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            bs.v = true;
            this.a.u = System.currentTimeMillis();
            CBLogging.a("CBWebViewProtocol", "Total web view load response time " + ((this.a.u - this.a.t) / 1000));
            f a = f.a();
            if (a != null) {
                a.a(this.a.g);
                return;
            }
            CBLogging.a("CBWebViewProtocol", "#### Error happened loading webview");
            this.a.a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        }

        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            this.a.a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            bs.v = true;
            f a = f.a();
            CBLogging.a("CBWebViewProtocol", "#### Error happened loading webview");
            if (a != null) {
                a.d(this.a.g);
            }
            String str = "Webview seems to have some issues loading html, onRecievedError callback triggered";
            CBLogging.a("CBWebViewProtocol", str);
            com.chartboost.sdk.Tracking.a.a(this.a.g.u().f(), this.a.g.e, this.a.g.t(), str, true);
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            return false;
        }
    }

    public enum b {
        NONE,
        IDLE,
        PLAYING,
        PAUSED
    }

    public class c extends com.chartboost.sdk.g.a {
        public br b;
        public bq c;
        public RelativeLayout d;
        public RelativeLayout e;
        final /* synthetic */ bs f;

        class 1 implements Runnable {
            final /* synthetic */ bs a;
            final /* synthetic */ c b;

            1(c cVar, bs bsVar) {
                this.b = cVar;
                this.a = bsVar;
            }

            public void run() {
                if (!bs.v) {
                    String str = "Webview seems to be taking more time loading the html content, so closing the view.";
                    CBLogging.a("CBWebViewProtocol", str);
                    com.chartboost.sdk.Tracking.a.a(this.b.f.g.u().f(), this.b.f.g.e, this.b.f.g.t(), str, true);
                    this.b.f.a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
                    f a = f.a();
                    if (a != null) {
                        a.d(this.b.f.g);
                    }
                }
            }
        }

        public c(bs bsVar, Context context, String str) {
            this.f = bsVar;
            super(bsVar, context);
            setFocusable(false);
            this.d = new RelativeLayout(context);
            this.e = new RelativeLayout(context);
            this.b = new br(context);
            this.b.setWebViewClient(new a(null));
            this.c = new bq(this.d, this.e, null, this.b, bsVar);
            this.b.setWebChromeClient(this.c);
            if (VERSION.SDK_INT >= 19) {
                br brVar = this.b;
                br.setWebContentsDebuggingEnabled(true);
            }
            this.b.loadDataWithBaseURL(bsVar.o, str, WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
            this.d.addView(this.b);
            this.b.getSettings().setSupportZoom(false);
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            this.d.setLayoutParams(layoutParams);
            this.b.setLayoutParams(layoutParams);
            this.b.setBackgroundColor(0);
            this.e.setVisibility(8);
            this.e.setLayoutParams(layoutParams);
            addView(this.d);
            addView(this.e);
            bsVar.t = System.currentTimeMillis();
            CBUtility.e().postDelayed(new 1(this, bsVar), 3000);
        }

        protected void a(int i, int i2) {
        }
    }

    public /* synthetic */ com.chartboost.sdk.g.a e() {
        return q();
    }

    static {
        v = false;
    }

    public bs(com.chartboost.sdk.Model.a aVar) {
        super(aVar);
        this.l = "UNKNOWN";
        this.n = null;
        this.o = null;
        this.m = 1;
        this.q = 0.0f;
        this.r = 0.0f;
        this.s = false;
        this.t = 0;
        this.u = 0;
        this.w = b.NONE;
    }

    protected com.chartboost.sdk.g.a b(Context context) {
        return new c(this, context, this.n);
    }

    public boolean a(com.chartboost.sdk.Libraries.e.a aVar) {
        File a = h.a();
        this.p = aVar.a("events");
        if (a == null) {
            CBLogging.b("CBWebViewProtocol", "External Storage path is unavailable or media not mounted");
            a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.o = "file://" + a.getAbsolutePath() + "/";
        if (TextUtils.isEmpty(this.g.i)) {
            CBLogging.b("CBWebViewProtocol", "Invalid adId being passed in th response");
            a(CBImpressionError.ERROR_DISPLAYING_VIEW);
            return false;
        }
        ConcurrentHashMap d = com.chartboost.sdk.b.d();
        if (d == null || d.isEmpty() || !d.containsKey(this.g.i)) {
            CBLogging.b("CBWebViewProtocol", "No html data found in memory");
            a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
            return false;
        }
        this.n = (String) d.get(this.g.i);
        b();
        return true;
    }

    public void h() {
        super.h();
    }

    public void b(String str) {
        if (this.p != null && this.p.c() && !TextUtils.isEmpty(str)) {
            ArrayList arrayList = (ArrayList) this.p.a(str).h();
            m a = az.a(com.chartboost.sdk.c.y()).a();
            if (arrayList != null && arrayList.size() > 0) {
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    String str2 = (String) it.next();
                    l acVar = new ac(1, str2, new 1(this), new 2(this));
                    CBLogging.a("CBWebViewProtocol", "###### Sending VAST Tracking Event: " + str2);
                    a.a(acVar);
                }
            }
        }
    }

    public void c(String str) {
        com.chartboost.sdk.Tracking.a.a(this.g.u().f(), this.g.e, this.g.t(), str);
    }

    public void d(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "Unknown Webview error";
        }
        com.chartboost.sdk.Tracking.a.a(this.g.u().f(), this.g.e, this.g.t(), str, true);
        CBLogging.b("CBWebViewProtocol", "Webview error occurred closing the webview" + str);
        a(CBImpressionError.ERROR_LOADING_WEB_VIEW);
        h();
    }

    public void e(String str) {
        if (TextUtils.isEmpty(str)) {
            str = "Unknown Webview warning message";
        }
        com.chartboost.sdk.Tracking.a.b(this.g.u().f(), this.g.e, this.g.t(), str);
        CBLogging.d("CBWebViewProtocol", "Webview warning occurred closing the webview" + str);
    }

    public boolean l() {
        if (this.w != b.PLAYING) {
            c q = q();
            if (q != null) {
                q.c.onHideCustomView();
            }
            h();
        }
        return true;
    }

    public void m() {
        super.m();
        c q = q();
        if (q != null) {
            CBUtility.e().post(new 3(this, q));
            com.chartboost.sdk.Tracking.a.d(this.l, this.g.t());
        }
    }

    public void n() {
        super.n();
        c q = q();
        if (q != null) {
            CBUtility.e().post(new 4(this, q));
            com.chartboost.sdk.Tracking.a.e(this.l, this.g.t());
        }
    }

    public void o() {
        if (this.m <= 1) {
            this.g.g();
            this.m++;
            com.chartboost.sdk.Tracking.a.b(this.l, this.g.t());
        }
    }

    public void d() {
        super.d();
    }

    public void p() {
        com.chartboost.sdk.Tracking.a.c(this.l, this.g.t());
    }

    public void a(b bVar) {
        this.w = bVar;
    }

    public c q() {
        return (c) super.e();
    }

    public void a(float f) {
        this.r = f;
    }

    public void b(float f) {
        this.q = f;
    }

    public float j() {
        return this.q;
    }

    public float k() {
        return this.r;
    }

    public void r() {
        if (!this.s) {
            com.chartboost.sdk.Tracking.a.d(BuildConfig.FLAVOR, this.g.t());
            this.g.v();
            this.s = true;
        }
    }
}
