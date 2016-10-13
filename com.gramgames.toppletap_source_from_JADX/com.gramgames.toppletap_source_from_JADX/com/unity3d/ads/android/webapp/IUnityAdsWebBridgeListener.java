package com.unity3d.ads.android.webapp;

import org.json.JSONObject;

public interface IUnityAdsWebBridgeListener {
    void onCloseAdsView(JSONObject jSONObject);

    void onLaunchIntent(JSONObject jSONObject);

    void onOpenPlayStore(JSONObject jSONObject);

    void onOrientationRequest(JSONObject jSONObject);

    void onPauseVideo(JSONObject jSONObject);

    void onPlayVideo(JSONObject jSONObject);

    void onWebAppInitComplete(JSONObject jSONObject);

    void onWebAppLoadComplete(JSONObject jSONObject);
}
