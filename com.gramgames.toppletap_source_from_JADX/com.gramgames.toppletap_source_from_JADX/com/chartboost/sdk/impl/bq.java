package com.chartboost.sdk.impl;

import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.webkit.ConsoleMessage;
import android.webkit.JsPromptResult;
import android.webkit.WebChromeClient;
import android.webkit.WebChromeClient.CustomViewCallback;
import android.webkit.WebView;
import android.widget.FrameLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.impl.bs.b;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.VastIconXmlManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import org.json.JSONException;
import org.json.JSONObject;

public class bq extends WebChromeClient {
    private static final String a;
    private View b;
    private ViewGroup c;
    private View d;
    private br e;
    private boolean f;
    private FrameLayout g;
    private CustomViewCallback h;
    private a i;
    private bs j;
    private Handler k;

    class 10 implements Runnable {
        final /* synthetic */ bq a;

        10(bq bqVar) {
            this.a = bqVar;
        }

        public void run() {
            this.a.j.r();
        }
    }

    class 11 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        11(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.a.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
                if (!TextUtils.isEmpty(string)) {
                    this.b.j.l = string;
                }
                this.b.j.p();
            } catch (Exception e) {
                CBLogging.b(bq.a, "Cannot find video file name");
                this.b.j.e("Parsing exception unknown field for video replay");
            }
        }
    }

    class 12 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        12(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.a.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
                if (!TextUtils.isEmpty(string)) {
                    this.b.j.l = string;
                }
            } catch (Exception e) {
                CBLogging.b(bq.a, "Cannot find video file name");
                this.b.j.e("Parsing exception unknown field for video pause");
            }
            this.b.j.a(b.PAUSED);
        }
    }

    class 13 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        13(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                Object string = this.a.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
                if (!TextUtils.isEmpty(string)) {
                    this.b.j.l = string;
                }
            } catch (Exception e) {
                CBLogging.b(bq.a, "Cannot find video file name");
                this.b.j.e("Parsing exception unknown field for video play");
            }
            this.b.j.a(b.PLAYING);
        }
    }

    class 1 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        1(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                String string = this.a.getString(NotificationCompatApi21.CATEGORY_EVENT);
                this.b.j.b(string);
                Log.d(br.class.getName(), "JS->Native Track VAST event message: " + string);
            } catch (Exception e) {
                CBLogging.b(bq.a, "Exception occured while parsing the message for webview tracking VAST events");
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        2(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                float f = (float) this.a.getDouble(VastIconXmlManager.DURATION);
                CBLogging.a(bq.a, "######### JS->Native Video current player duration" + (f * 1000.0f));
                this.b.j.a(f * 1000.0f);
            } catch (Exception e) {
                this.b.j.e("Parsing exception unknown field for current player duration");
                CBLogging.b(bq.a, "Cannot find duration parameter for the video");
            }
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        3(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                float f = (float) this.a.getDouble(VastIconXmlManager.DURATION);
                CBLogging.a(bq.a, "######### JS->Native Video total player duration" + (f * 1000.0f));
                this.b.j.b(f * 1000.0f);
            } catch (Exception e) {
                this.b.j.e("Parsing exception unknown field for total player duration");
                CBLogging.b(bq.a, "Cannot find duration parameter for the video");
            }
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ bq a;

        4(bq bqVar) {
            this.a = bqVar;
        }

        public void run() {
            this.a.j.a(null, null);
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ bq a;

        5(bq bqVar) {
            this.a = bqVar;
        }

        public void run() {
            this.a.j.h();
        }
    }

    class 6 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        6(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                String string = this.a.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                Log.d(br.class.getName(), "JS->Native Debug message: " + string);
                this.b.j.c(string);
            } catch (Exception e) {
                CBLogging.b(bq.a, "Exception occured while parsing the message for webview debug track event");
                this.b.j.c("Exception occured while parsing the message for webview debug track event");
            }
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ bq a;

        7(bq bqVar) {
            this.a = bqVar;
        }

        public void run() {
            if (this.a != null) {
                this.a.onHideCustomView();
            }
            this.a.j.a(b.IDLE);
            this.a.j.o();
        }
    }

    class 8 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        8(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                String string = this.a.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                Log.d(br.class.getName(), "JS->Native Error message: " + string);
                this.b.j.d(string);
            } catch (Exception e) {
                CBLogging.b(bq.a, "Error message is empty");
                this.b.j.d(BuildConfig.FLAVOR);
            }
        }
    }

    class 9 implements Runnable {
        final /* synthetic */ JSONObject a;
        final /* synthetic */ bq b;

        9(bq bqVar, JSONObject jSONObject) {
            this.b = bqVar;
            this.a = jSONObject;
        }

        public void run() {
            try {
                String string = this.a.getString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE);
                Log.d(br.class.getName(), "JS->Native Warning message: " + string);
                this.b.j.e(string);
            } catch (Exception e) {
                CBLogging.b(bq.a, "Warning message is empty");
                this.b.j.e(BuildConfig.FLAVOR);
            }
        }
    }

    public interface a {
        void a(boolean z);
    }

    static {
        a = bq.class.getSimpleName();
    }

    public bq() {
        this.k = CBUtility.e();
    }

    public bq(View view, ViewGroup viewGroup, View view2, br brVar, bs bsVar) {
        this.k = CBUtility.e();
        this.b = view;
        this.c = viewGroup;
        this.d = view2;
        this.e = brVar;
        this.f = false;
        this.j = bsVar;
    }

    public boolean onConsoleMessage(ConsoleMessage cm) {
        Log.d(bq.class.getSimpleName(), "Chartboost Webview:" + cm.message() + " -- From line " + cm.lineNumber() + " of " + cm.sourceId());
        return true;
    }

    public boolean onJsPrompt(WebView view, String url, String message, String defaultValue, JsPromptResult result) {
        try {
            JSONObject jSONObject = new JSONObject(message);
            result.confirm(a(jSONObject.getJSONObject("eventArgs"), jSONObject.getString("eventType")));
        } catch (JSONException e) {
            CBLogging.b(a, "Exception caught parsing the function name from js to native");
        }
        return true;
    }

    public String a(JSONObject jSONObject, String str) {
        if (str.equals("click")) {
            l(jSONObject);
        } else if (str.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE)) {
            Log.d(a, "JavaScript to native close callback triggered");
            m(jSONObject);
        } else if (str.equals(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED)) {
            Log.d(a, "JavaScript to native video complete callback triggered");
            c(jSONObject);
        } else if (str.equals("videoPlaying")) {
            Log.d(a, "JavaScript to native video playing callback triggered");
            i(jSONObject);
        } else if (str.equals("videoPaused")) {
            Log.d(a, "JavaScript to native video pause callback triggered");
            h(jSONObject);
        } else if (str.equals("videoReplay")) {
            Log.d(a, "JavaScript to native video replay callback triggered");
            g(jSONObject);
        } else if (str.equals("currentVideoDuration")) {
            j(jSONObject);
        } else if (str.equals("totalVideoDuration")) {
            Log.d(a, "JavaScript to native total duration callback triggered");
            k(jSONObject);
        } else if (str.equals("show")) {
            Log.d(a, "JavaScript to native show callback triggered");
            f(jSONObject);
        } else if (str.equals(NativeProtocol.BRIDGE_ARG_ERROR_BUNDLE)) {
            Log.d(a, "JavaScript to native error callback triggered");
            d(jSONObject);
        } else if (str.equals("warning")) {
            Log.d(a, "JavaScript to native warning callback triggered");
            e(jSONObject);
        } else if (str.equals(BuildConfig.BUILD_TYPE)) {
            Log.d(a, "JavaScript to native webview debug event callback triggered");
            b(jSONObject);
        } else if (!str.equals("tracking")) {
            return "Function name not recognized.";
        } else {
            Log.d(a, "JavaScript to native webview vast tracking event callback triggered");
            a(jSONObject);
        }
        return "Native function successfully called.";
    }

    public void a(JSONObject jSONObject) {
        this.k.post(new 1(this, jSONObject));
    }

    public void b(JSONObject jSONObject) {
        this.k.post(new 6(this, jSONObject));
    }

    public void c(JSONObject jSONObject) {
        Log.d(br.class.getName(), "Video is Completed");
        this.k.post(new 7(this));
    }

    public void d(JSONObject jSONObject) {
        Log.d(br.class.getName(), "Javascript Error occured");
        this.k.post(new 8(this, jSONObject));
    }

    public void e(JSONObject jSONObject) {
        Log.d(br.class.getName(), "Javascript warning occurred");
        this.k.post(new 9(this, jSONObject));
    }

    public void f(JSONObject jSONObject) {
        this.k.post(new 10(this));
    }

    public void g(JSONObject jSONObject) {
        this.k.post(new 11(this, jSONObject));
    }

    public void h(JSONObject jSONObject) {
        this.k.post(new 12(this, jSONObject));
    }

    public void i(JSONObject jSONObject) {
        this.k.post(new 13(this, jSONObject));
    }

    public void j(JSONObject jSONObject) {
        this.k.post(new 2(this, jSONObject));
    }

    public void k(JSONObject jSONObject) {
        this.k.post(new 3(this, jSONObject));
    }

    public void l(JSONObject jSONObject) {
        this.k.post(new 4(this));
    }

    public void m(JSONObject jSONObject) {
        this.k.post(new 5(this));
    }

    public void onShowCustomView(View view, CustomViewCallback callback) {
        if (view instanceof FrameLayout) {
            FrameLayout frameLayout = (FrameLayout) view;
            frameLayout.getFocusedChild();
            this.f = true;
            this.g = frameLayout;
            this.h = callback;
            this.b.setVisibility(4);
            this.c.addView(this.g, new LayoutParams(-1, -1));
            this.c.setVisibility(0);
            if (this.i != null) {
                this.i.a(true);
            }
        }
    }

    public void onShowCustomView(View view, int requestedOrientation, CustomViewCallback callback) {
        onShowCustomView(view, callback);
    }

    public void onHideCustomView() {
        if (this.f) {
            this.c.setVisibility(4);
            this.c.removeView(this.g);
            this.b.setVisibility(0);
            if (!(this.h == null || this.h.getClass().getName().contains(".chromium."))) {
                this.h.onCustomViewHidden();
            }
            this.f = false;
            this.g = null;
            this.h = null;
            if (this.i != null) {
                this.i.a(false);
            }
        }
    }
}
