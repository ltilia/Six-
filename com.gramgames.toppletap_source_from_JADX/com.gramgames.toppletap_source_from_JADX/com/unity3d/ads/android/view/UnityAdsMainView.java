package com.unity3d.ads.android.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.video.UnityAdsVideoPlayView;
import com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener;
import com.unity3d.ads.android.webapp.IUnityAdsWebViewListener;
import com.unity3d.ads.android.webapp.UnityAdsWebBridge;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.webapp.UnityAdsWebView;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

@TargetApi(9)
public class UnityAdsMainView extends RelativeLayout {
    private static final int FILL_PARENT = -1;
    public static UnityAdsWebView webview;
    private UnityAdsMainViewState _currentState;
    private IUnityAdsWebBridgeListener _webBridgeListener;
    public UnityAdsVideoPlayView videoplayerview;

    final class 1 implements IUnityAdsWebViewListener {
        1() {
        }

        public final void onWebAppLoaded() {
            UnityAdsMainView.webview.initWebApp(UnityAdsWebData.getData());
        }
    }

    final class 2 implements IUnityAdsWebBridgeListener {

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                if (!UnityAdsProperties.isAdsReadySent() && UnityAds.getListener() != null) {
                    UnityAdsDeviceLog.debug("Unity Ads ready.");
                    UnityAdsProperties.setAdsReadySent(true);
                    UnityAds.getListener().onFetchCompleted();
                }
            }
        }

        2() {
        }

        public final void onPlayVideo(JSONObject jSONObject) {
        }

        public final void onPauseVideo(JSONObject jSONObject) {
        }

        public final void onCloseAdsView(JSONObject jSONObject) {
        }

        public final void onWebAppLoadComplete(JSONObject jSONObject) {
        }

        public final void onWebAppInitComplete(JSONObject jSONObject) {
            String str = "WebView reported WebAppInitComplete";
            if (jSONObject != null) {
                str = str + " " + jSONObject.toString();
            }
            UnityAdsDeviceLog.debug(str);
            Boolean valueOf = Boolean.valueOf(true);
            if (UnityAdsWebData.hasViewableAds()) {
                JSONObject jSONObject2 = new JSONObject();
                try {
                    jSONObject2.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY, UnityAdsConstants.UNITY_ADS_WEBVIEW_API_INITCOMPLETE);
                } catch (Exception e) {
                    valueOf = Boolean.valueOf(false);
                }
                if (valueOf.booleanValue()) {
                    UnityAdsMainView.webview.setWebViewCurrentView(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE, jSONObject2);
                    UnityAdsUtils.runOnUiThread(new 1());
                }
            }
        }

        public final void onOrientationRequest(JSONObject jSONObject) {
        }

        public final void onOpenPlayStore(JSONObject jSONObject) {
        }

        public final void onLaunchIntent(JSONObject jSONObject) {
        }
    }

    class 3 implements Runnable {
        3() {
        }

        public void run() {
            UnityAdsMainView.this.placeWebView();
        }
    }

    /* synthetic */ class 4 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$view$UnityAdsMainView$UnityAdsMainViewState;

        static {
            $SwitchMap$com$unity3d$ads$android$view$UnityAdsMainView$UnityAdsMainViewState = new int[UnityAdsMainViewState.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$view$UnityAdsMainView$UnityAdsMainViewState[UnityAdsMainViewState.WebView.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$view$UnityAdsMainView$UnityAdsMainViewState[UnityAdsMainViewState.VideoPlayer.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum UnityAdsMainViewState {
        WebView,
        VideoPlayer
    }

    static {
        webview = null;
    }

    public UnityAdsMainView(Context context, IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        super(context);
        this.videoplayerview = null;
        this._webBridgeListener = null;
        this._currentState = UnityAdsMainViewState.WebView;
        this._webBridgeListener = iUnityAdsWebBridgeListener;
        init();
    }

    public UnityAdsMainView(Context context) {
        super(context);
        this.videoplayerview = null;
        this._webBridgeListener = null;
        this._currentState = UnityAdsMainViewState.WebView;
        init();
    }

    public UnityAdsMainView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.videoplayerview = null;
        this._webBridgeListener = null;
        this._currentState = UnityAdsMainViewState.WebView;
        init();
    }

    public UnityAdsMainView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.videoplayerview = null;
        this._webBridgeListener = null;
        this._currentState = UnityAdsMainViewState.WebView;
        init();
    }

    public void setViewState(UnityAdsMainViewState unityAdsMainViewState) {
        if (!this._currentState.equals(unityAdsMainViewState)) {
            this._currentState = unityAdsMainViewState;
            switch (4.$SwitchMap$com$unity3d$ads$android$view$UnityAdsMainView$UnityAdsMainViewState[unityAdsMainViewState.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    UnityAdsViewUtils.removeViewFromParent(webview);
                    addView(webview, new LayoutParams(FILL_PARENT, FILL_PARENT));
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    if (this.videoplayerview == null) {
                        createVideoPlayerView();
                        bringChildToFront(webview);
                    }
                default:
            }
        }
    }

    public UnityAdsMainViewState getViewState() {
        return this._currentState;
    }

    public static void initWebView() {
        if (webview != null) {
            UnityAdsViewUtils.removeViewFromParent(webview);
            webview.destroy();
            webview = null;
        }
        UnityAdsDeviceLog.debug("Initing WebView");
        webview = new UnityAdsWebView(UnityAdsProperties.APPLICATION_CONTEXT, new 1(), new UnityAdsWebBridge(new 2()));
    }

    private void init() {
        UnityAdsDeviceLog.entered();
        webview.setWebBridgeListener(this._webBridgeListener);
        post(new 3());
    }

    public void destroyVideoPlayerView() {
        UnityAdsDeviceLog.entered();
        if (this.videoplayerview != null) {
            this.videoplayerview.clearVideoPlayer();
        }
        UnityAdsViewUtils.removeViewFromParent(this.videoplayerview);
        this.videoplayerview = null;
    }

    private void createVideoPlayerView() {
        this.videoplayerview = new UnityAdsVideoPlayView(getContext());
        this.videoplayerview.setLayoutParams(new LayoutParams(FILL_PARENT, FILL_PARENT));
        addView(this.videoplayerview);
    }

    private void placeWebView() {
        if (webview != null) {
            if (webview.getParent() != null) {
                UnityAdsViewUtils.removeViewFromParent(webview);
            }
            addView(webview, new LayoutParams(FILL_PARENT, FILL_PARENT));
        }
    }
}
