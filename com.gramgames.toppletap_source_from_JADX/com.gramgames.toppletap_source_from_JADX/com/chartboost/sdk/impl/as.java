package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.google.android.exoplayer.C;
import com.mopub.common.AdType;

public class as extends ao {
    private WebView a;
    private OnClickListener b;

    class 1 extends WebViewClient {
        final /* synthetic */ as a;

        1(as asVar) {
            this.a = asVar;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url == null) {
                return false;
            }
            if (url.contains("chartboost") && url.contains("click") && this.a.b != null) {
                this.a.b.onClick(this.a);
            }
            return true;
        }
    }

    public as(av avVar, Context context) {
        super(context);
        this.b = null;
        this.a = new WebView(context);
        addView(this.a, new LayoutParams(-1, -1));
        this.a.setBackgroundColor(0);
        this.a.setWebViewClient(new 1(this));
    }

    public void setOnClickListener(OnClickListener clickListener) {
        super.setOnClickListener(clickListener);
        this.b = clickListener;
    }

    public void a(a aVar, int i) {
        String e = aVar.e(AdType.HTML);
        if (e != null) {
            try {
                this.a.loadDataWithBaseURL("file:///android_res/", e, WebRequest.CONTENT_TYPE_HTML, C.UTF8_NAME, null);
            } catch (Throwable e2) {
                CBLogging.b("AppCellWebView", "Exception raised loading data into webview", e2);
            }
        }
    }

    public int a() {
        return CBUtility.a(100, getContext());
    }
}
