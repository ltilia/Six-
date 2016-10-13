package com.facebook.ads.internal.view;

import android.content.Context;
import android.net.Uri;
import android.net.http.SslError;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.adapters.m;
import com.facebook.ads.internal.ssp.ANAdRenderer.Listener;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.h;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class c extends d {
    private static final String a;
    private final l b;
    private final int c;
    private final Listener d;
    private m e;
    private e f;
    private long g;
    private com.facebook.ads.internal.util.b.a h;

    class 1 extends com.facebook.ads.internal.adapters.c {
        final /* synthetic */ c a;

        1(c cVar) {
            this.a = cVar;
        }

        public void d() {
            this.a.d.onAdImpression();
        }
    }

    class 2 extends com.facebook.ads.internal.adapters.e.a {
        final /* synthetic */ c a;

        2(c cVar) {
            this.a = cVar;
        }

        public void a() {
            this.a.e.a();
        }
    }

    private class a extends WebViewClient {
        final /* synthetic */ c a;

        private a(c cVar) {
            this.a = cVar;
        }

        public void onReceivedSslError(WebView webView, @NonNull SslErrorHandler sslErrorHandler, SslError sslError) {
            if (g.a()) {
                sslErrorHandler.proceed();
            } else {
                sslErrorHandler.cancel();
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            Uri parse = Uri.parse(str);
            if ("fbad".equals(parse.getScheme()) && UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE.equals(parse.getAuthority())) {
                this.a.d.onAdClose();
            } else {
                this.a.d.onAdClick();
                com.facebook.ads.internal.action.a a = com.facebook.ads.internal.action.b.a(this.a.getContext(), parse);
                if (a != null) {
                    try {
                        this.a.h = a.a();
                        this.a.g = System.currentTimeMillis();
                        a.b();
                    } catch (Throwable e) {
                        Log.e(c.a, "Error executing action", e);
                    }
                }
            }
            return true;
        }
    }

    private class b {
        final /* synthetic */ c a;
        private final String b;

        private b(c cVar) {
            this.a = cVar;
            this.b = b.class.getSimpleName();
        }

        @JavascriptInterface
        public void alert(String str) {
            Log.e(this.b, str);
        }

        @JavascriptInterface
        public String getAnalogInfo() {
            return g.a(com.facebook.ads.internal.util.a.a());
        }

        @JavascriptInterface
        public void onPageInitialized() {
            if (!this.a.b()) {
                this.a.d();
                if (this.a.f != null) {
                    this.a.f.a();
                }
            }
        }
    }

    static {
        a = c.class.getSimpleName();
    }

    public c(Context context, l lVar, int i, Listener listener) {
        super(context);
        if (lVar == null || listener == null) {
            throw new IllegalArgumentException();
        }
        this.b = lVar;
        this.c = i;
        this.d = listener;
        c();
    }

    private void c() {
        setWebViewClient(new a());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportZoom(false);
        h.b(this);
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        addJavascriptInterface(new b(), "AdControl");
        this.e = new m(getContext(), this, new 1(this));
        this.e.a(this.b);
        this.f = new e(getContext(), this, this.c, new 2(this));
        this.f.a(this.b.j());
        this.f.b(this.b.k());
        this.f.a();
        loadDataWithBaseURL(h.a(), this.b.d(), WebRequest.CONTENT_TYPE_HTML, "utf-8", null);
    }

    private void d() {
        this.e.c();
    }

    public void destroy() {
        if (this.f != null) {
            this.f.b();
            this.f = null;
        }
        h.a((WebView) this);
        super.destroy();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (i == 0) {
            if (this.g > 0 && this.h != null) {
                com.facebook.ads.internal.util.c.a(com.facebook.ads.internal.util.b.a(this.g, this.h, this.b.i()));
                this.g = 0;
                this.h = null;
            }
            if (this.f != null) {
                this.f.a();
            }
        } else if (i == 8 && this.f != null) {
            this.f.b();
        }
    }
}
