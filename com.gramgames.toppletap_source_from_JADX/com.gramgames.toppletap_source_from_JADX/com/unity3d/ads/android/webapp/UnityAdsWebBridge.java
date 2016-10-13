package com.unity3d.ads.android.webapp;

import android.content.Intent;
import android.net.Uri;
import android.webkit.JavascriptInterface;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class UnityAdsWebBridge {
    private IUnityAdsWebBridgeListener _listener;

    /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent;

        static {
            $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent = new int[UnityAdsWebEvent.values().length];
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.PlayVideo.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.PauseVideo.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.CloseView.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.LoadComplete.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.InitComplete.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.Orientation.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.PlayStore.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.NavigateTo.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[UnityAdsWebEvent.LaunchIntent.ordinal()] = 9;
            } catch (NoSuchFieldError e9) {
            }
        }
    }

    enum UnityAdsWebEvent {
        PlayVideo,
        PauseVideo,
        CloseView,
        LoadComplete,
        InitComplete,
        Orientation,
        PlayStore,
        NavigateTo,
        LaunchIntent;

        public final String toString() {
            switch (1.$SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYVIDEO;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return "pauseVideo";
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_LOADCOMPLETE;
                case Yytoken.TYPE_COMMA /*5*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_INITCOMPLETE;
                case Yytoken.TYPE_COLON /*6*/:
                    return "orientation";
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_PLAYSTORE;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_NAVIGATETO;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    return UnityAdsConstants.UNITY_ADS_WEBVIEW_API_LAUNCHINTENT;
                default:
                    return null;
            }
        }
    }

    private UnityAdsWebEvent getEventType(String str) {
        for (UnityAdsWebEvent unityAdsWebEvent : UnityAdsWebEvent.values()) {
            if (unityAdsWebEvent.toString().equals(str)) {
                return unityAdsWebEvent;
            }
        }
        return null;
    }

    public UnityAdsWebBridge(IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        this._listener = null;
        this._listener = iUnityAdsWebBridgeListener;
    }

    public void setListener(IUnityAdsWebBridgeListener iUnityAdsWebBridgeListener) {
        this._listener = iUnityAdsWebBridgeListener;
    }

    @JavascriptInterface
    public boolean handleWebEvent(String str, String str2) {
        JSONObject jSONObject;
        Exception e;
        JSONObject jSONObject2 = null;
        boolean z = false;
        UnityAdsDeviceLog.debug(str + ", " + str2);
        if (this._listener == null || str2 == null) {
            return z;
        }
        try {
            jSONObject = new JSONObject(str2);
            try {
                jSONObject2 = jSONObject.getJSONObject(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
            } catch (Exception e2) {
                e = e2;
                UnityAdsDeviceLog.error("Error while parsing parameters: " + e.getMessage());
                return jSONObject != null ? z : z;
            }
        } catch (Exception e3) {
            e = e3;
            jSONObject = jSONObject2;
            UnityAdsDeviceLog.error("Error while parsing parameters: " + e.getMessage());
            if (jSONObject != null) {
            }
        }
        if (jSONObject != null && str != null) {
            UnityAdsWebEvent eventType = getEventType(str);
            if (eventType == null) {
                return z;
            }
            switch (1.$SwitchMap$com$unity3d$ads$android$webapp$UnityAdsWebBridge$UnityAdsWebEvent[eventType.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this._listener.onPlayVideo(jSONObject2);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this._listener.onPauseVideo(jSONObject2);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    this._listener.onCloseAdsView(jSONObject2);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    this._listener.onWebAppLoadComplete(jSONObject2);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    this._listener.onWebAppInitComplete(jSONObject2);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    this._listener.onOrientationRequest(jSONObject2);
                    break;
                case R.styleable.Toolbar_contentInsetLeft /*7*/:
                    this._listener.onOpenPlayStore(jSONObject2);
                    break;
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    if (jSONObject2 != null && jSONObject2.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY)) {
                        try {
                            String string = jSONObject2.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY);
                            if (string != null) {
                                try {
                                    Intent intent = new Intent("android.intent.action.VIEW");
                                    intent.setData(Uri.parse(string));
                                    UnityAdsProperties.getCurrentActivity().startActivity(intent);
                                    break;
                                } catch (Exception e4) {
                                    UnityAdsDeviceLog.error("Could not start activity for opening URL: " + string + ", maybe malformed URL?");
                                    break;
                                }
                            }
                        } catch (Exception e5) {
                            UnityAdsDeviceLog.error("Error fetching clickUrl");
                            return z;
                        }
                    }
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    this._listener.onLaunchIntent(jSONObject2);
                    break;
            }
            return true;
        }
    }
}
