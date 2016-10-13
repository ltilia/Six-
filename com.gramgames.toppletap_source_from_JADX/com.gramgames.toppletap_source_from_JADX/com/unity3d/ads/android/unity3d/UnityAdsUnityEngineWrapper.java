package com.unity3d.ads.android.unity3d;

import android.annotation.TargetApi;
import android.app.Activity;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.zone.UnityAdsZoneManager;
import java.util.HashMap;
import java.util.Map;

@TargetApi(9)
public class UnityAdsUnityEngineWrapper implements IUnityAdsListener {
    private static Boolean _initialized;

    class 1 implements Runnable {
        final /* synthetic */ Activity val$activity;
        final /* synthetic */ String val$gameId;
        final /* synthetic */ UnityAdsUnityEngineWrapper val$listener;
        final /* synthetic */ int val$logLevel;
        final /* synthetic */ boolean val$testMode;
        final /* synthetic */ String val$unityVersion;

        1(int i, boolean z, String str, Activity activity, String str2, UnityAdsUnityEngineWrapper unityAdsUnityEngineWrapper) {
            this.val$logLevel = i;
            this.val$testMode = z;
            this.val$unityVersion = str;
            this.val$activity = activity;
            this.val$gameId = str2;
            this.val$listener = unityAdsUnityEngineWrapper;
        }

        public void run() {
            UnityAdsDeviceLog.setLogLevel(this.val$logLevel);
            UnityAds.setTestMode(this.val$testMode);
            if (this.val$unityVersion.length() > 0) {
                UnityAdsProperties.UNITY_VERSION = this.val$unityVersion;
            }
            UnityAds.init(this.val$activity, this.val$gameId, this.val$listener);
        }
    }

    private static native void UnityAdsOnFetchCompleted();

    private static native void UnityAdsOnFetchFailed();

    private static native void UnityAdsOnHide();

    private static native void UnityAdsOnShow();

    private static native void UnityAdsOnVideoCompleted(String str, int i);

    private static native void UnityAdsOnVideoStarted();

    static {
        _initialized = Boolean.valueOf(false);
    }

    public void init(Activity activity, String str, boolean z, int i, String str2) {
        if (!_initialized.booleanValue()) {
            _initialized = Boolean.valueOf(true);
            try {
                UnityAdsUtils.runOnUiThread(new 1(i, z, str2, activity, str, this));
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Error occured while initializing Unity Ads");
            }
        }
    }

    public boolean show(String str, String str2, String str3) {
        if (!UnityAds.canShow()) {
            return false;
        }
        Map map = null;
        if (str3.length() > 0) {
            map = new HashMap();
            for (String split : str3.split(",")) {
                String[] split2 = split.split(":");
                map.put(split2[0], split2[1]);
            }
        }
        if (!canShowAds(str)) {
            return false;
        }
        if (str2.length() > 0) {
            if (!UnityAds.setZone(str, str2)) {
                UnityAdsDeviceLog.debug("Could not set zone");
                return false;
            }
        } else if (str.length() > 0 && !UnityAds.setZone(str)) {
            UnityAdsDeviceLog.debug("Could not set zone");
            return false;
        }
        return UnityAds.show(map);
    }

    public boolean canShowAds(String str) {
        if (str == null || str.length() <= 0) {
            return UnityAds.canShow();
        }
        UnityAdsZoneManager zoneManager = UnityAdsWebData.getZoneManager();
        return (zoneManager == null || zoneManager.getZone(str) == null || !UnityAds.canShow()) ? false : true;
    }

    public void setLogLevel(int i) {
        UnityAdsDeviceLog.setLogLevel(i);
    }

    public void setCampaignDataURL(String str) {
        UnityAds.setCampaignDataURL(str);
    }

    public void onHide() {
        UnityAdsOnHide();
    }

    public void onShow() {
        UnityAdsOnShow();
    }

    public void onVideoStarted() {
        UnityAdsOnVideoStarted();
    }

    public void onVideoCompleted(String str, boolean z) {
        if (str == null || str.isEmpty()) {
            str = "null";
        }
        UnityAdsOnVideoCompleted(str, z ? 1 : 0);
    }

    public void onFetchCompleted() {
        UnityAdsOnFetchCompleted();
    }

    public void onFetchFailed() {
        UnityAdsOnFetchFailed();
    }
}
