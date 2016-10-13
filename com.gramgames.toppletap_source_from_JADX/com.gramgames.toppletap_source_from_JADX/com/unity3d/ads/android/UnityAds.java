package com.unity3d.ads.android;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Build.VERSION;
import com.google.android.gms.drive.DriveFile;
import com.unity3d.ads.android.UnityAdsDeviceLog.UnityAdsShowMsg;
import com.unity3d.ads.android.cache.UnityAdsCache;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.data.UnityAdsAdvertisingId;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.item.UnityAdsRewardItem;
import com.unity3d.ads.android.item.UnityAdsRewardItemManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.view.UnityAdsFullscreenActivity;
import com.unity3d.ads.android.view.UnityAdsMainView;
import com.unity3d.ads.android.webapp.IUnityAdsWebDataListener;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.zone.UnityAdsIncentivizedZone;
import com.unity3d.ads.android.zone.UnityAdsZone;
import com.unity3d.ads.android.zone.UnityAdsZoneManager;
import java.lang.annotation.Annotation;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONObject;

@TargetApi(9)
public class UnityAds implements IUnityAdsWebDataListener {
    public static final String UNITY_ADS_OPTION_GAMERSID_KEY = "sid";
    public static final String UNITY_ADS_OPTION_MUTE_VIDEO_SOUNDS = "muteVideoSounds";
    public static final String UNITY_ADS_OPTION_NOOFFERSCREEN_KEY = "noOfferScreen";
    public static final String UNITY_ADS_OPTION_OPENANIMATED_KEY = "openAnimated";
    public static final String UNITY_ADS_OPTION_VIDEO_USES_DEVICE_ORIENTATION = "useDeviceOrientationForVideo";
    public static final String UNITY_ADS_REWARDITEM_NAME_KEY = "name";
    public static final String UNITY_ADS_REWARDITEM_PICTURE_KEY = "picture";
    private static IUnityAdsListener _adsListener;
    private static boolean _initialized;
    private static UnityAds _instance;

    class 1 implements OnClickListener {
        final /* synthetic */ AlertDialog val$alertDialog;

        1(AlertDialog alertDialog) {
            this.val$alertDialog = alertDialog;
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            this.val$alertDialog.dismiss();
        }
    }

    final class 2 implements Runnable {
        final /* synthetic */ Activity val$activity;

        2(Activity activity) {
            this.val$activity = activity;
        }

        public final void run() {
            UnityAdsAdvertisingId.init(this.val$activity);
            if (UnityAdsWebData.initCampaigns()) {
                UnityAds._initialized = true;
            }
        }
    }

    final class 3 implements Runnable {
        3() {
        }

        public final void run() {
            UnityAdsMainView.initWebView();
        }
    }

    static {
        _initialized = false;
        _adsListener = null;
        _instance = null;
    }

    private UnityAds() {
    }

    public static boolean isSupported() {
        return VERSION.SDK_INT >= 9;
    }

    public static void setDebugMode(boolean z) {
        if (z) {
            UnityAdsDeviceLog.setLogLevel(8);
        } else {
            UnityAdsDeviceLog.setLogLevel(4);
        }
    }

    public static void setTestMode(boolean z) {
        UnityAdsProperties.TESTMODE_ENABLED = Boolean.valueOf(z);
    }

    public static void setTestDeveloperId(String str) {
        UnityAdsProperties.TEST_DEVELOPER_ID = str;
    }

    public static void setTestOptionsId(String str) {
        UnityAdsProperties.TEST_OPTIONS_ID = str;
    }

    public static String getSDKVersion() {
        return UnityAdsConstants.UNITY_ADS_VERSION;
    }

    public static void setCampaignDataURL(String str) {
        UnityAdsProperties.CAMPAIGN_DATA_URL = str;
    }

    public static void enableUnityDeveloperInternalTestMode() {
        UnityAdsProperties.CAMPAIGN_DATA_URL = "https://impact.staging.applifier.com/mobile/campaigns";
        UnityAdsProperties.UNITY_DEVELOPER_INTERNAL_TEST = Boolean.valueOf(true);
    }

    public static void setListener(IUnityAdsListener iUnityAdsListener) {
        _adsListener = iUnityAdsListener;
    }

    public static IUnityAdsListener getListener() {
        return _adsListener;
    }

    public static void changeActivity(Activity activity) {
        if (activity == null) {
            UnityAdsDeviceLog.debug("changeActivity: null, ignoring");
            return;
        }
        UnityAdsDeviceLog.debug("changeActivity: " + activity.getClass().getName());
        UnityAdsProperties.CURRENT_ACTIVITY = new WeakReference(activity);
        if (!(activity instanceof UnityAdsFullscreenActivity)) {
            UnityAdsProperties.BASE_ACTIVITY = new WeakReference(activity);
        }
    }

    public static boolean hide() {
        if (!(UnityAdsProperties.CURRENT_ACTIVITY.get() instanceof UnityAdsFullscreenActivity)) {
            return false;
        }
        ((Activity) UnityAdsProperties.CURRENT_ACTIVITY.get()).finish();
        return true;
    }

    public static boolean setZone(String str) {
        if (isShowingAds()) {
            return false;
        }
        if (UnityAdsWebData.getZoneManager() != null) {
            return UnityAdsWebData.getZoneManager().setCurrentZone(str);
        }
        throw new IllegalStateException("Unable to set zone before campaigns are available");
    }

    public static boolean setZone(String str, String str2) {
        if (!isShowingAds() && setZone(str)) {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            if (currentZone.isIncentivized()) {
                return ((UnityAdsIncentivizedZone) currentZone).itemManager().setCurrentItem(str2);
            }
        }
        return false;
    }

    public static String getZone() {
        UnityAdsZoneManager zoneManager = UnityAdsWebData.getZoneManager();
        if (zoneManager != null) {
            UnityAdsZone currentZone = zoneManager.getCurrentZone();
            if (currentZone != null) {
                return currentZone.getZoneId();
            }
        }
        return null;
    }

    public static boolean show(Map map) {
        if (canShow()) {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            if (currentZone != null) {
                UnityAdsCache.stopAllDownloads();
                currentZone.mergeOptions(map);
                if (currentZone.noOfferScreen()) {
                    ArrayList viewableVideoPlanCampaigns = UnityAdsWebData.getViewableVideoPlanCampaigns();
                    if (viewableVideoPlanCampaigns.size() > 0) {
                        UnityAdsProperties.SELECTED_CAMPAIGN = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(0);
                    }
                }
                UnityAdsDeviceLog.info("Launching ad from \"" + currentZone.getZoneName() + "\", options: " + currentZone.getZoneOptions().toString());
                UnityAdsProperties.SELECTED_CAMPAIGN_CACHED = Boolean.valueOf(false);
                startFullscreenActivity();
                return true;
            }
            UnityAdsDeviceLog.error("Unity Ads current zone is null");
        } else {
            UnityAdsDeviceLog.error("Unity Ads not ready to show ads");
        }
        return false;
    }

    public static boolean show() {
        return show(null);
    }

    private static void startFullscreenActivity() {
        Intent intent = new Intent(UnityAdsProperties.getCurrentActivity(), UnityAdsFullscreenActivity.class);
        int i = 268500992;
        if (UnityAdsWebData.getZoneManager().getCurrentZone().openAnimated()) {
            i = DriveFile.MODE_READ_ONLY;
        }
        intent.addFlags(i);
        Activity baseActivity = UnityAdsProperties.getBaseActivity();
        if (baseActivity != null) {
            try {
                baseActivity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                UnityAdsDeviceLog.error("Could not find UnityAdsFullScreenActivity (failed Android manifest merging?): " + e.getMessage());
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Weird error: " + e2.getMessage());
            }
        }
    }

    @Deprecated
    public static boolean canShowAds() {
        return canShow();
    }

    private static boolean isShowingAds() {
        return UnityAdsProperties.isShowingAds();
    }

    public static boolean canShow() {
        if (!UnityAdsProperties.isAdsReadySent()) {
            UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.WEBAPP_NOT_INITIALIZED);
            return false;
        } else if (isShowingAds()) {
            UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.SHOWING_ADS);
            return false;
        } else if (!UnityAdsDevice.isActiveNetworkConnected()) {
            UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.NO_INTERNET);
            return false;
        } else if (UnityAdsWebData.initInProgress()) {
            return false;
        } else {
            ArrayList viewableVideoPlanCampaigns = UnityAdsWebData.getViewableVideoPlanCampaigns();
            if (viewableVideoPlanCampaigns == null) {
                UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.NO_ADS);
                return false;
            } else if (viewableVideoPlanCampaigns.size() == 0) {
                UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.ZERO_ADS);
                return false;
            } else {
                UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(0);
                if (unityAdsCampaign.allowStreamingVideo().booleanValue() || UnityAdsCache.isCampaignCached(unityAdsCampaign)) {
                    UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.READY);
                    return true;
                }
                UnityAdsDeviceLog.logShowStatus(UnityAdsShowMsg.VIDEO_NOT_CACHED);
                return false;
            }
        }
    }

    @Deprecated
    public static boolean hasMultipleRewardItems() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return false;
        }
        if (((UnityAdsIncentivizedZone) currentZone).itemManager().itemCount() > 1) {
            return true;
        }
        return false;
    }

    @Deprecated
    public static ArrayList getRewardItemKeys() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        ArrayList allItems = ((UnityAdsIncentivizedZone) currentZone).itemManager().allItems();
        ArrayList arrayList = new ArrayList();
        Iterator it = allItems.iterator();
        while (it.hasNext()) {
            arrayList.add(((UnityAdsRewardItem) it.next()).getKey());
        }
        return arrayList;
    }

    @Deprecated
    public static String getDefaultRewardItemKey() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        return ((UnityAdsIncentivizedZone) currentZone).itemManager().getDefaultItem().getKey();
    }

    @Deprecated
    public static String getCurrentRewardItemKey() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            return null;
        }
        return ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey();
    }

    @Deprecated
    public static boolean setRewardItemKey(String str) {
        if (canShow()) {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            if (currentZone != null && currentZone.isIncentivized()) {
                return ((UnityAdsIncentivizedZone) currentZone).itemManager().setCurrentItem(str);
            }
        }
        return false;
    }

    @Deprecated
    public static void setDefaultRewardItemAsRewardItem() {
        if (canShow()) {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            if (currentZone != null && currentZone.isIncentivized()) {
                UnityAdsRewardItemManager itemManager = ((UnityAdsIncentivizedZone) currentZone).itemManager();
                itemManager.setCurrentItem(itemManager.getDefaultItem().getKey());
            }
        }
    }

    @Deprecated
    public static Map getRewardItemDetailsWithKey(String str) {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone != null && currentZone.isIncentivized()) {
            UnityAdsRewardItem item = ((UnityAdsIncentivizedZone) currentZone).itemManager().getItem(str);
            if (item != null) {
                return item.getDetails();
            }
            UnityAdsDeviceLog.info("Could not fetch reward item: " + str);
        }
        return null;
    }

    public void onWebDataCompleted() {
        boolean z = true;
        UnityAdsDeviceLog.entered();
        JSONObject jSONObject = null;
        boolean z2 = false;
        if (UnityAdsWebData.getData() != null && UnityAdsWebData.getData().has(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY)) {
            try {
                jSONObject = UnityAdsWebData.getData().getJSONObject(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY);
            } catch (Exception e) {
                z2 = z;
            }
            if (!z2) {
                UnityAdsWebData.setupCampaignRefreshTimer();
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDK_IS_CURRENT_KEY)) {
                    try {
                        z = jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_SDK_IS_CURRENT_KEY);
                    } catch (Exception e2) {
                        z2 = z;
                    }
                }
            }
        }
        if (!(z2 || r0 || UnityAdsProperties.getCurrentActivity() == null || !UnityAdsUtils.isDebuggable())) {
            AlertDialog create = new Builder(UnityAdsProperties.getCurrentActivity()).create();
            create.setTitle("Unity Ads");
            create.setMessage("You are not running the latest version of Unity Ads android. Please update your version (this dialog won't appear in release builds).");
            create.setButton("OK", new 1(create));
            create.show();
        }
        setup();
    }

    public void onWebDataFailed() {
        if (getListener() != null && !UnityAdsProperties.UNITY_ADS_READY_SENT.booleanValue()) {
            getListener().onFetchFailed();
            UnityAdsProperties.UNITY_ADS_READY_SENT = Boolean.valueOf(true);
        }
    }

    public static void init(Activity activity, String str, IUnityAdsListener iUnityAdsListener) {
        Object obj = null;
        if (_instance == null && !_initialized) {
            if (str == null || str.length() == 0) {
                UnityAdsDeviceLog.error("Unity Ads init failed: gameId is empty");
                return;
            }
            try {
                if (Integer.parseInt(str) <= 0) {
                    UnityAdsDeviceLog.error("Unity Ads init failed: gameId is invalid");
                    return;
                }
                if (UnityAdsProperties.UNITY_VERSION == null || UnityAdsProperties.UNITY_VERSION.length() <= 0) {
                    UnityAdsDeviceLog.info("Initializing Unity Ads version 1506 with gameId " + str);
                } else {
                    UnityAdsDeviceLog.info("Initializing Unity Ads version 1506 (Unity + " + UnityAdsProperties.UNITY_VERSION + ") with gameId " + str);
                }
                if (activity.getResources().getIdentifier("unityads_view_video_play", TtmlNode.TAG_LAYOUT, activity.getPackageName()) == 0) {
                    UnityAdsDeviceLog.error("Unity Ads layout resources not found, check that you have properly merged Unity Ads resource files in your project");
                    return;
                }
                UnityAdsDeviceLog.debug("Unity Ads layout resources ok");
                try {
                    Method method = Class.forName("com.unity3d.ads.android.webapp.UnityAdsWebBridge").getMethod("handleWebEvent", new Class[]{String.class, String.class});
                    if (VERSION.SDK_INT >= 17) {
                        Annotation[] annotations = method.getAnnotations();
                        if (annotations != null) {
                            for (Annotation annotationType : annotations) {
                                Class annotationType2 = annotationType.annotationType();
                                if (annotationType2 != null && annotationType2.isAnnotation() && annotationType2.getName().equals("android.webkit.JavascriptInterface")) {
                                    break;
                                }
                            }
                        }
                        int i = 1;
                        if (obj != null) {
                            UnityAdsDeviceLog.error("UnityAds ProGuard check fail: com.unity3d.ads.android.webapp.handleWebEvent lacks android.webkit.JavascriptInterface annotation");
                            return;
                        }
                    }
                    UnityAdsDeviceLog.debug("UnityAds ProGuard check OK");
                } catch (ClassNotFoundException e) {
                    UnityAdsDeviceLog.error("UnityAds ProGuard check fail: com.unity3d.ads.android.webapp.UnityAdsWebBridge class not found, check ProGuard settings");
                    return;
                } catch (NoSuchMethodException e2) {
                    UnityAdsDeviceLog.error("UnityAds ProGuard check fail: com.unity3d.ads.android.webapp.handleWebEvent method not found, check ProGuard settings");
                    return;
                } catch (Exception e3) {
                    UnityAdsDeviceLog.debug("UnityAds ProGuard check: Unknown exception: " + e3);
                }
                if (_instance == null) {
                    _instance = new UnityAds();
                }
                setListener(iUnityAdsListener);
                UnityAdsProperties.UNITY_ADS_GAME_ID = str;
                UnityAdsProperties.BASE_ACTIVITY = new WeakReference(activity);
                UnityAdsProperties.APPLICATION_CONTEXT = activity.getApplicationContext();
                UnityAdsProperties.CURRENT_ACTIVITY = new WeakReference(activity);
                UnityAdsDeviceLog.debug("Is debuggable=" + UnityAdsUtils.isDebuggable());
                UnityAdsWebData.setWebDataListener(_instance);
                new Thread(new 2(activity)).start();
            } catch (NumberFormatException e4) {
                UnityAdsDeviceLog.error("Unity Ads init failed: gameId does not parse as an integer");
            }
        }
    }

    private static void setup() {
        initCache();
        UnityAdsUtils.runOnUiThread(new 3());
    }

    private static void initCache() {
        UnityAdsDeviceLog.entered();
        if (_initialized) {
            UnityAdsCache.initialize(UnityAdsWebData.getVideoPlanCampaigns());
        }
    }
}
