package com.facebook.ads.internal.view;

import android.content.Context;
import android.net.http.SslError;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.util.g;
import com.facebook.ads.internal.util.h;

public class a extends d {
    private final a a;
    private e b;

    public interface a {
        void a();

        void a(int i);

        void a(String str);

        void b();
    }

    class 1 extends com.facebook.ads.internal.adapters.e.a {
        final /* synthetic */ a a;
        final /* synthetic */ a b;

        1(a aVar, a aVar2) {
            this.b = aVar;
            this.a = aVar2;
        }

        public void a() {
            this.a.b();
        }
    }

    private class b extends WebViewClient {
        final /* synthetic */ a a;

        private b(a aVar) {
            this.a = aVar;
        }

        public void onReceivedSslError(WebView webView, @NonNull SslErrorHandler sslErrorHandler, SslError sslError) {
            if (g.a()) {
                sslErrorHandler.proceed();
            } else {
                sslErrorHandler.cancel();
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            this.a.a.a(str);
            return true;
        }
    }

    public class c {
        final /* synthetic */ a a;
        private final String b;

        public c(a aVar) {
            this.a = aVar;
            this.b = c.class.getSimpleName();
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
                this.a.a.a();
                if (this.a.b != null) {
                    this.a.b.a();
                }
            }
        }
    }

    public a(Context context, a aVar, int i) {
        super(context);
        this.a = aVar;
        setWebViewClient(new b());
        getSettings().setJavaScriptEnabled(true);
        getSettings().setSupportZoom(false);
        h.b(this);
        setHorizontalScrollBarEnabled(false);
        setHorizontalScrollbarOverlay(false);
        setVerticalScrollBarEnabled(false);
        setVerticalScrollbarOverlay(false);
        addJavascriptInterface(new c(this), "AdControl");
        this.b = new e(getContext(), this, i, new 1(this, aVar));
    }

    public void a(int i, int i2) {
        this.b.a(i);
        this.b.b(i2);
    }

    public void destroy() {
        if (this.b != null) {
            this.b.b();
            this.b = null;
        }
        h.a((WebView) this);
        super.destroy();
    }

    protected void onWindowVisibilityChanged(int i) {
        super.onWindowVisibilityChanged(i);
        if (this.a != null) {
            this.a.a(i);
        }
        if (i == 0) {
            if (this.b != null) {
                this.b.a();
            }
        } else if (i == 8 && this.b != null) {
            this.b.b();
        }
    }
}
