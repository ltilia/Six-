package com.unity3d.ads.android.unity3d;

import android.app.Activity;
import com.facebook.internal.ServerProtocol;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.zone.UnityAdsZoneManager;
import gs.gram.mopub.BuildConfig;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

public class UnityAdsUnityWrapper implements IUnityAdsListener {
    private static Boolean _constructed;
    private static Boolean _initialized;
    private String _gameId;
    private String _gameObject;
    private Method _sendMessageMethod;
    private Activity _startupActivity;
    private boolean _testMode;

    class 1 implements Runnable {
        final /* synthetic */ UnityAdsUnityWrapper val$listener;
        final /* synthetic */ int val$logLevel;
        final /* synthetic */ String val$unityVersion;

        1(int i, String str, UnityAdsUnityWrapper unityAdsUnityWrapper) {
            this.val$logLevel = i;
            this.val$unityVersion = str;
            this.val$listener = unityAdsUnityWrapper;
        }

        public void run() {
            UnityAdsDeviceLog.setLogLevel(this.val$logLevel);
            UnityAds.setTestMode(UnityAdsUnityWrapper.this._testMode);
            if (this.val$unityVersion.length() > 0) {
                UnityAdsProperties.UNITY_VERSION = this.val$unityVersion;
            }
            UnityAds.init(UnityAdsUnityWrapper.this._startupActivity, UnityAdsUnityWrapper.this._gameId, this.val$listener);
        }
    }

    static {
        _constructed = Boolean.valueOf(false);
        _initialized = Boolean.valueOf(false);
    }

    public UnityAdsUnityWrapper() {
        this._startupActivity = null;
        this._gameObject = null;
        this._gameId = null;
        this._sendMessageMethod = null;
        this._testMode = false;
        if (!_constructed.booleanValue()) {
            _constructed = Boolean.valueOf(true);
            try {
                this._sendMessageMethod = Class.forName("com.unity3d.player.UnityPlayer").getDeclaredMethod("UnitySendMessage", new Class[]{String.class, String.class, String.class});
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Error getting class or method of com.unity3d.player.UnityPlayer, method UnitySendMessage(string, string, string). " + e.getLocalizedMessage());
            }
        }
    }

    public boolean isSupported() {
        return UnityAds.isSupported();
    }

    public String getSDKVersion() {
        return UnityAds.getSDKVersion();
    }

    public void init(String str, Activity activity, boolean z, int i, String str2, String str3) {
        if (!_initialized.booleanValue()) {
            _initialized = Boolean.valueOf(true);
            this._gameId = str;
            this._gameObject = str2;
            this._testMode = z;
            if (this._startupActivity == null) {
                this._startupActivity = activity;
            }
            try {
                UnityAdsUtils.runOnUiThread(new 1(i, str3, this));
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Error occured while initializing Unity Ads");
            }
        }
    }

    public boolean show(String str, String str2, String str3) {
        if (!canShowZone(str)) {
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
        if (str2.length() > 0) {
            if (str != null && str.length() > 0) {
                UnityAds.setZone(str, str2);
            }
        } else if (str != null && str.length() > 0) {
            UnityAds.setZone(str);
        }
        return UnityAds.show(map);
    }

    public void hide() {
        if (!UnityAds.hide()) {
            UnityAdsDeviceLog.debug("Problems hiding UnityAds");
        }
    }

    public boolean canShow() {
        return UnityAds.canShow();
    }

    public boolean canShowZone(String str) {
        if (str == null || str.length() <= 0) {
            return UnityAds.canShow();
        }
        UnityAdsZoneManager zoneManager = UnityAdsWebData.getZoneManager();
        return (zoneManager == null || zoneManager.getZone(str) == null || !UnityAds.canShow()) ? false : true;
    }

    public boolean hasMultipleRewardItems() {
        return UnityAds.hasMultipleRewardItems();
    }

    public String getRewardItemKeys() {
        String str = null;
        ArrayList rewardItemKeys = UnityAds.getRewardItemKeys();
        if (rewardItemKeys != null && rewardItemKeys.size() > 0) {
            str = BuildConfig.FLAVOR;
            Iterator it = rewardItemKeys.iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                if (rewardItemKeys.indexOf(str2) > 0) {
                    str = str + ";";
                }
                str = str + str2;
            }
        }
        return str;
    }

    public String getDefaultRewardItemKey() {
        return UnityAds.getDefaultRewardItemKey();
    }

    public String getCurrentRewardItemKey() {
        return UnityAds.getCurrentRewardItemKey();
    }

    public boolean setRewardItemKey(String str) {
        return UnityAds.setRewardItemKey(str);
    }

    public void setDefaultRewardItemAsRewardItem() {
        UnityAds.setDefaultRewardItemAsRewardItem();
    }

    public String getRewardItemDetailsWithKey(String str) {
        if (UnityAds.getRewardItemDetailsWithKey(str) != null) {
            UnityAdsDeviceLog.debug("Fetching reward data");
            HashMap hashMap = (HashMap) UnityAds.getRewardItemDetailsWithKey(str);
            if (hashMap != null) {
                return ((String) hashMap.get(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY)) + ";" + ((String) hashMap.get(UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY));
            }
            UnityAdsDeviceLog.debug("Problems getting reward item details");
        } else {
            UnityAdsDeviceLog.debug("Could not find reward item details");
        }
        return BuildConfig.FLAVOR;
    }

    public String getRewardItemDetailsKeys() {
        return String.format(Locale.US, "%s;%s", new Object[]{UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY});
    }

    public void setLogLevel(int i) {
        UnityAdsDeviceLog.setLogLevel(i);
    }

    public void enableUnityDeveloperInternalTestMode() {
        UnityAds.enableUnityDeveloperInternalTestMode();
    }

    public void setCampaignDataURL(String str) {
        UnityAds.setCampaignDataURL(str);
    }

    public void onHide() {
        sendMessageToUnity3D("onHide", null);
    }

    public void onShow() {
        sendMessageToUnity3D("onShow", null);
    }

    public void onVideoStarted() {
        sendMessageToUnity3D("onVideoStarted", null);
    }

    public void onVideoCompleted(String str, boolean z) {
        sendMessageToUnity3D("onVideoCompleted", str + ";" + (z ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : "false"));
    }

    public void onFetchCompleted() {
        sendMessageToUnity3D("onFetchCompleted", null);
    }

    public void onFetchFailed() {
        sendMessageToUnity3D("onFetchFailed", null);
    }

    private void sendMessageToUnity3D(String str, String str2) {
        if (str2 == null) {
            str2 = BuildConfig.FLAVOR;
        }
        if (this._sendMessageMethod == null) {
            UnityAdsDeviceLog.error("Cannot send message to Unity3D. Method is null");
            return;
        }
        try {
            UnityAdsDeviceLog.debug("Sending message (" + str + ", " + str2 + ") to Unity3D");
            this._sendMessageMethod.invoke(null, new Object[]{this._gameObject, str, str2});
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Can't invoke UnitySendMessage method. Error = " + e.getLocalizedMessage());
        }
    }
}
