package com.unity3d.ads.android.webapp;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.webkit.ValueCallback;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings.RenderPriority;
import android.webkit.WebStorage.QuotaUpdater;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.io.File;
import java.util.Locale;
import org.json.JSONObject;

@TargetApi(9)
public class UnityAdsWebView extends WebView {
    private IUnityAdsWebViewListener _listener;
    private String _url;
    private boolean _webAppLoaded;
    private UnityAdsWebBridge _webBridge;

    class 1 implements OnLongClickListener {
        1() {
        }

        public boolean onLongClick(View view) {
            return true;
        }
    }

    class UnityAdsJavascriptRunner implements Runnable {
        private String _jsString;
        private WebView _webView;

        public UnityAdsJavascriptRunner(String str, WebView webView) {
            this._jsString = null;
            this._webView = null;
            this._jsString = str;
            this._webView = webView;
        }

        public void run() {
            if (this._jsString != null) {
                try {
                    if (VERSION.SDK_INT >= 19) {
                        try {
                            WebView.class.getMethod("evaluateJavascript", new Class[]{String.class, ValueCallback.class}).invoke(this._webView, new Object[]{this._jsString, null});
                            return;
                        } catch (Exception e) {
                            UnityAdsDeviceLog.error("Could not invoke evaluateJavascript");
                            return;
                        }
                    }
                    UnityAdsWebView.this.loadUrl(this._jsString);
                    return;
                } catch (Exception e2) {
                    UnityAdsDeviceLog.error("Error while processing JavaScriptString!");
                    return;
                }
            }
            UnityAdsDeviceLog.error("Could not process JavaScript, the string is NULL");
        }
    }

    class UnityAdsViewChromeClient extends WebChromeClient {
        private UnityAdsViewChromeClient() {
        }

        public void onConsoleMessage(String str, int i, String str2) {
            File file;
            try {
                file = new File(str2);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Could not handle sourceId: " + e.getMessage());
                file = null;
            }
            if (file != null) {
                str2 = file.getName();
            }
            UnityAdsDeviceLog.debug("JavaScript (sourceId=" + str2 + ", line=" + i + "): " + str);
        }

        public void onReachedMaxAppCacheSize(long j, long j2, QuotaUpdater quotaUpdater) {
            quotaUpdater.updateQuota(2 * j);
        }
    }

    class UnityAdsViewClient extends WebViewClient {
        private UnityAdsViewClient() {
        }

        public void onPageFinished(WebView webView, String str) {
            super.onPageFinished(webView, str);
            UnityAdsDeviceLog.debug("Finished url: " + str);
            if (UnityAdsWebView.this._listener != null && !UnityAdsWebView.this._webAppLoaded) {
                UnityAdsWebView.this._webAppLoaded = true;
                UnityAdsWebView.this._listener.onWebAppLoaded();
            }
        }

        public boolean shouldOverrideUrlLoading(WebView webView, String str) {
            UnityAdsDeviceLog.debug("Trying to load url: " + str);
            return false;
        }

        public void onReceivedError(WebView webView, int i, String str, String str2) {
            UnityAdsDeviceLog.error(i + " (" + str2 + ") " + str);
            super.onReceivedError(webView, i, str, str2);
        }
    }

    public UnityAdsWebView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._url = null;
        this._listener = null;
        this._webAppLoaded = false;
        this._webBridge = null;
    }

    public UnityAdsWebView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this._url = null;
        this._listener = null;
        this._webAppLoaded = false;
        this._webBridge = null;
    }

    public UnityAdsWebView(Context context) {
        super(context);
        this._url = null;
        this._listener = null;
        this._webAppLoaded = false;
        this._webBridge = null;
    }

    public UnityAdsWebView(Context context, IUnityAdsWebViewListener iUnityAdsWebViewListener, UnityAdsWebBridge unityAdsWebBridge) {
        super(context);
        this._url = null;
        this._listener = null;
        this._webAppLoaded = false;
        this._webBridge = null;
        UnityAdsDeviceLog.debug("Loading WebView from URL: " + UnityAdsProperties.WEBVIEW_BASE_URL);
        init(UnityAdsProperties.WEBVIEW_BASE_URL, iUnityAdsWebViewListener, unityAdsWebBridge);
    }

    private boolean isWebAppLoaded() {
        return this._webAppLoaded;
    }

    public void setWebViewCurrentView(String str, JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            String str2 = "{}";
            if (jSONObject != null) {
                str2 = jSONObject.toString();
            }
            str2 = String.format(Locale.US, "%s%s(\"%s\", %s);", new Object[]{UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_CHANGE_VIEW, str, str2});
            UnityAdsUtils.runOnUiThread(new UnityAdsJavascriptRunner(str2, this));
            UnityAdsDeviceLog.debug("Send change view to WebApp: " + str2);
            if (jSONObject != null) {
                str2 = UnityAdsConstants.UNITY_ADS_INIT_QUERYPARAM_TEST_KEY;
                try {
                    str2 = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY);
                } catch (Exception e) {
                    UnityAdsDeviceLog.debug("Couldn't get API action key: " + e.getMessage());
                }
                UnityAdsDeviceLog.debug("dataHasApiActionKey=" + jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY));
                UnityAdsDeviceLog.debug("actionEqualsWebViewApiOpen=" + str2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN));
                UnityAdsDeviceLog.debug("isDebuggable=" + UnityAdsUtils.isDebuggable());
                UnityAdsDeviceLog.debug("runWebViewTests=" + UnityAdsProperties.RUN_WEBVIEW_TESTS);
                UnityAdsDeviceLog.debug("testJavaScriptContents=" + UnityAdsProperties.TEST_JAVASCRIPT);
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY) && str2.equals(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN) && UnityAdsUtils.isDebuggable() && UnityAdsProperties.RUN_WEBVIEW_TESTS.booleanValue() && UnityAdsProperties.TEST_JAVASCRIPT != null) {
                    UnityAdsDeviceLog.debug("Running test-javascript: " + UnityAdsProperties.TEST_JAVASCRIPT);
                    UnityAdsUtils.runOnUiThread(new UnityAdsJavascriptRunner(UnityAdsProperties.TEST_JAVASCRIPT, this));
                    UnityAdsProperties.RUN_WEBVIEW_TESTS = Boolean.valueOf(false);
                }
            }
        }
    }

    public void sendNativeEventToWebApp(String str, JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            String str2 = "{}";
            if (jSONObject != null) {
                str2 = jSONObject.toString();
            }
            str2 = String.format(Locale.US, "%s%s(\"%s\", %s);", new Object[]{UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_HANDLE_NATIVE_EVENT, str, str2});
            UnityAdsDeviceLog.debug("Send native event to WebApp: " + str2);
            UnityAdsUtils.runOnUiThread(new UnityAdsJavascriptRunner(str2, this));
        }
    }

    public void initWebApp(JSONObject jSONObject) {
        if (isWebAppLoaded()) {
            JSONObject jSONObject2 = new JSONObject();
            try {
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_CAMPAIGNDATA_KEY, jSONObject);
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "android");
                String advertisingTrackingId = UnityAdsDevice.getAdvertisingTrackingId();
                if (advertisingTrackingId != null) {
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_TRACKINGENABLED_KEY, UnityAdsDevice.isLimitAdTrackingEnabled() ? 0 : 1);
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ADVERTISINGTRACKINGID_KEY, UnityAdsUtils.Md5(advertisingTrackingId).toLowerCase(Locale.US));
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWADVERTISINGTRACKINGID_KEY, advertisingTrackingId);
                } else if (!UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN.equals(UnityAdsDevice.getAndroidId(false))) {
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_ANDROIDID_KEY, UnityAdsDevice.getAndroidId(true));
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_RAWANDROIDID_KEY, UnityAdsDevice.getAndroidId(false));
                }
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDKVERSION_KEY, UnityAdsConstants.UNITY_ADS_VERSION);
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY, UnityAdsProperties.UNITY_ADS_GAME_ID);
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENDENSITY_KEY, UnityAdsDevice.getScreenDensity());
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SCREENSIZE_KEY, UnityAdsDevice.getScreenSize());
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_ZONES_KEY, UnityAdsWebData.getZoneManager().getZonesJson());
                if (UnityAdsProperties.UNITY_VERSION != null && UnityAdsProperties.UNITY_VERSION.length() > 0) {
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_UNITYVERSION_KEY, UnityAdsProperties.UNITY_VERSION);
                }
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SOFTWAREVERSION_KEY, UnityAdsDevice.getSoftwareVersion());
                jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_DEVICETYPE_KEY, UnityAdsDevice.getDeviceType());
                String format = String.format(Locale.US, "%s%s(%s);", new Object[]{UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_PREFIX, UnityAdsConstants.UNITY_ADS_WEBVIEW_JS_INIT, jSONObject2.toString()});
                UnityAdsDeviceLog.debug("Initializing WebView with JS call: " + format);
                UnityAdsUtils.runOnUiThread(new UnityAdsJavascriptRunner(format, this));
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Error creating webview init params: " + e.getMessage());
            }
        }
    }

    public void setWebBridgeListener(IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        if (this._webBridge != null) {
            this._webBridge.setListener(iUnityAdsWebBridgeListener);
        }
    }

    private void init(String str, IUnityAdsWebViewListener iUnityAdsWebViewListener, UnityAdsWebBridge unityAdsWebBridge) {
        this._listener = iUnityAdsWebViewListener;
        this._url = str;
        this._webBridge = unityAdsWebBridge;
        this._webAppLoaded = false;
        setupUnityAdsView();
        loadUrl(this._url);
        if (VERSION.SDK_INT > 8) {
            setOnLongClickListener(new 1());
            setLongClickable(false);
        }
    }

    @SuppressLint({"SetJavaScriptEnabled", "AddJavascriptInterface"})
    private void setupUnityAdsView() {
        String str;
        getSettings().setJavaScriptEnabled(true);
        if (this._url != null && this._url.contains("_raw.html")) {
            getSettings().setCacheMode(2);
            UnityAdsDeviceLog.debug("LOAD_NO_CACHE");
        } else if (VERSION.SDK_INT < 17) {
            getSettings().setCacheMode(0);
        } else {
            getSettings().setCacheMode(-1);
        }
        if (getContext() == null || getContext().getCacheDir() == null) {
            str = null;
        } else {
            str = getContext().getCacheDir().toString();
        }
        getSettings().setSupportZoom(false);
        getSettings().setBuiltInZoomControls(false);
        getSettings().setLightTouchEnabled(false);
        getSettings().setRenderPriority(RenderPriority.HIGH);
        getSettings().setSupportMultipleWindows(false);
        getSettings().setAllowFileAccess(false);
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        setClickable(true);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setInitialScale(0);
        setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        setBackgroundDrawable(null);
        setBackgroundResource(0);
        setWebViewClient(new UnityAdsViewClient());
        setWebChromeClient(new UnityAdsViewChromeClient());
        if (str != null) {
            boolean z;
            if (VERSION.SDK_INT <= 7) {
                z = false;
            } else {
                z = true;
            }
            getSettings().setAppCacheEnabled(z);
            getSettings().setDomStorageEnabled(true);
            getSettings().setAppCacheMaxSize(10485760);
            getSettings().setAppCachePath(str);
        }
        UnityAdsDeviceLog.debug("Adding javascript interface");
        addJavascriptInterface(this._webBridge, "unityadsnativebridge");
    }
}
