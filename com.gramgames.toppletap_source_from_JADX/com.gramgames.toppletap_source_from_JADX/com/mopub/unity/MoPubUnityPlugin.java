package com.mopub.unity;

import android.app.Activity;
import android.location.Location;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;
import com.applovin.sdk.AppLovinEventParameters;
import com.gramgames.UnityLateEvent;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPub;
import com.mopub.common.MoPub.LocationAwareness;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.MoPubConversionTracker;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubInterstitial;
import com.mopub.mobileads.MoPubInterstitial.InterstitialAdListener;
import com.mopub.mobileads.MoPubRewardedVideoListener;
import com.mopub.mobileads.MoPubRewardedVideoManager.RequestParameters;
import com.mopub.mobileads.MoPubView;
import com.mopub.mobileads.MoPubView.BannerAdListener;
import com.mopub.mobileads.VastIconXmlManager;
import com.unity3d.player.UnityPlayer;
import gs.gram.mopub.BuildConfig;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class MoPubUnityPlugin implements BannerAdListener, InterstitialAdListener, MoPubRewardedVideoListener {
    private static String TAG;
    private static MoPubUnityPlugin sInstance;
    public Activity mActivity;
    private RelativeLayout mLayout;
    private MoPubInterstitial mMoPubInterstitial;
    private MoPubView mMoPubView;

    class 10 implements Runnable {
        10() {
        }

        public void run() {
            MoPub.initializeRewardedVideo(MoPubUnityPlugin.this.getActivity(), new MediationSettings[0]);
            MoPub.setRewardedVideoListener(MoPubUnityPlugin.this);
        }
    }

    class 11 implements Runnable {
        final /* synthetic */ String val$adUnitId;
        final /* synthetic */ String val$json;
        final /* synthetic */ String val$keywords;
        final /* synthetic */ double val$latitude;
        final /* synthetic */ double val$longitude;

        11(double d, double d2, String str, String str2, String str3) {
            this.val$latitude = d;
            this.val$longitude = d2;
            this.val$keywords = str;
            this.val$json = str2;
            this.val$adUnitId = str3;
        }

        public void run() {
            Location location = new Location(BuildConfig.FLAVOR);
            location.setLatitude(this.val$latitude);
            location.setLongitude(this.val$longitude);
            RequestParameters requestParameters = new RequestParameters(this.val$keywords, location);
            if (this.val$json != null) {
                MoPub.loadRewardedVideo(this.val$adUnitId, requestParameters, MoPubUnityPlugin.this.extractMediationSettingsFromJson(this.val$json));
            } else {
                MoPub.loadRewardedVideo(this.val$adUnitId, requestParameters, new MediationSettings[0]);
            }
        }
    }

    class 12 implements Runnable {
        final /* synthetic */ String val$adUnitId;

        12(String str) {
            this.val$adUnitId = str;
        }

        public void run() {
            MoPub.showRewardedVideo(this.val$adUnitId);
        }
    }

    class 1 implements Runnable {
        final /* synthetic */ Runnable val$runner;

        1(Runnable runnable) {
            this.val$runner = runnable;
        }

        public void run() {
            try {
                this.val$runner.run();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ String val$locationAwareness;

        2(String str) {
            this.val$locationAwareness = str;
        }

        public void run() {
            MoPub.setLocationAwareness(LocationAwareness.valueOf(this.val$locationAwareness));
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ String val$adUnitId;
        final /* synthetic */ int val$alignment;

        3(String str, int i) {
            this.val$adUnitId = str;
            this.val$alignment = i;
        }

        public void run() {
            if (MoPubUnityPlugin.this.mMoPubView == null) {
                MoPubUnityPlugin.this.mMoPubView = new MoPubView(MoPubUnityPlugin.this.getActivity());
                MoPubUnityPlugin.this.mMoPubView.setAdUnitId(this.val$adUnitId);
                MoPubUnityPlugin.this.mMoPubView.setBannerAdListener(MoPubUnityPlugin.this);
                MoPubUnityPlugin.this.mMoPubView.loadAd();
                MoPubUnityPlugin.this.prepLayout(this.val$alignment);
                MoPubUnityPlugin.this.mLayout.addView(MoPubUnityPlugin.this.mMoPubView);
                MoPubUnityPlugin.this.getActivity().addContentView(MoPubUnityPlugin.this.mLayout, new LayoutParams(-1, -1));
                MoPubUnityPlugin.this.mLayout.setVisibility(0);
            }
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ boolean val$shouldHide;

        4(boolean z) {
            this.val$shouldHide = z;
        }

        public void run() {
            if (this.val$shouldHide) {
                MoPubUnityPlugin.this.mMoPubView.setVisibility(8);
            } else {
                MoPubUnityPlugin.this.mMoPubView.setVisibility(0);
            }
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ String val$keywords;

        5(String str) {
            this.val$keywords = str;
        }

        public void run() {
            if (MoPubUnityPlugin.this.mMoPubView != null) {
                MoPubUnityPlugin.this.mMoPubView.setKeywords(this.val$keywords);
                MoPubUnityPlugin.this.mMoPubView.loadAd();
            }
        }
    }

    class 6 implements Runnable {
        6() {
        }

        public void run() {
            if (MoPubUnityPlugin.this.mMoPubView != null && MoPubUnityPlugin.this.mLayout != null) {
                MoPubUnityPlugin.this.mLayout.removeAllViews();
                MoPubUnityPlugin.this.mLayout.setVisibility(8);
                MoPubUnityPlugin.this.mMoPubView.destroy();
                MoPubUnityPlugin.this.mMoPubView = null;
            }
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ String val$adUnitId;
        final /* synthetic */ String val$keywords;

        7(String str, String str2) {
            this.val$adUnitId = str;
            this.val$keywords = str2;
        }

        public void run() {
            MoPubUnityPlugin.this.mMoPubInterstitial = new MoPubInterstitial(MoPubUnityPlugin.this.getActivity(), this.val$adUnitId);
            MoPubUnityPlugin.this.mMoPubInterstitial.setInterstitialAdListener(MoPubUnityPlugin.this);
            if (this.val$keywords != null && this.val$keywords.length() > 0) {
                MoPubUnityPlugin.this.mMoPubInterstitial.setKeywords(this.val$keywords);
            }
            MoPubUnityPlugin.this.mMoPubInterstitial.load();
        }
    }

    class 8 implements Runnable {
        8() {
        }

        public void run() {
            MoPubUnityPlugin.this.mMoPubInterstitial.show();
        }
    }

    class 9 implements Runnable {
        9() {
        }

        public void run() {
            new MoPubConversionTracker().reportAppOpen(MoPubUnityPlugin.this.getActivity());
        }
    }

    static {
        TAG = "MoPub";
    }

    public static MoPubUnityPlugin instance() {
        if (sInstance == null) {
            sInstance = new MoPubUnityPlugin();
        }
        return sInstance;
    }

    private Activity getActivity() {
        if (this.mActivity != null) {
            return this.mActivity;
        }
        return UnityPlayer.currentActivity;
    }

    private void UnitySendMessage(String go, String m, String p) {
        if (this.mActivity != null) {
            Log.i(TAG, "UnitySendMessage: " + go + ", " + m + ", " + p);
        } else {
            UnityPlayer.UnitySendMessage(go, m, p);
        }
    }

    private float getScreenDensity() {
        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.density;
    }

    private void prepLayout(int alignment) {
        if (this.mLayout == null) {
            this.mLayout = new RelativeLayout(getActivity());
        } else {
            FrameLayout parentView = (FrameLayout) this.mLayout.getParent();
            if (parentView != null) {
                parentView.removeView(this.mLayout);
            }
        }
        int gravity = 0;
        switch (alignment) {
            case Yylex.YYINITIAL /*0*/:
                gravity = 51;
                break;
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                gravity = 49;
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                gravity = 53;
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                gravity = 17;
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                gravity = 83;
                break;
            case Yytoken.TYPE_COMMA /*5*/:
                gravity = 81;
                break;
            case Yytoken.TYPE_COLON /*6*/:
                gravity = 85;
                break;
        }
        this.mLayout.setGravity(gravity);
    }

    private void runSafelyOnUiThread(Runnable runner) {
        getActivity().runOnUiThread(new 1(runner));
    }

    private static void printExceptionStackTrace(Exception e) {
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        Log.i(TAG, sw.toString());
    }

    public void addFacebookTestDeviceId(String hashedDeviceId) {
        try {
            Class<?> cls = Class.forName("com.facebook.ads.AdSettings");
            cls.getMethod("addTestDevice", new Class[]{String.class}).invoke(cls, new Object[]{hashedDeviceId});
            Log.i(TAG, "successfully added Facebook test device: " + hashedDeviceId);
        } catch (ClassNotFoundException e) {
            Log.i(TAG, "could not find Facebook AdSettings class. Did you add the Audience Network SDK to your Android folder?");
        } catch (NoSuchMethodException e2) {
            Log.i(TAG, "could not find Facebook AdSettings.addTestDevice method. Did you add the Audience Network SDK to your Android folder?");
        } catch (IllegalAccessException e3) {
            e3.printStackTrace();
        } catch (IllegalArgumentException e4) {
            e4.printStackTrace();
        } catch (InvocationTargetException e5) {
            e5.printStackTrace();
        }
    }

    public void addAdMobTestDeviceId(String deviceId) {
    }

    public void setLocationAwareness(String locationAwareness) {
        runSafelyOnUiThread(new 2(locationAwareness));
    }

    public void createBanner(String adUnitId, int alignment) {
        runSafelyOnUiThread(new 3(adUnitId, alignment));
    }

    public void hideBanner(boolean shouldHide) {
        if (this.mMoPubView != null) {
            runSafelyOnUiThread(new 4(shouldHide));
        }
    }

    public void setBannerKeywords(String keywords) {
        runSafelyOnUiThread(new 5(keywords));
    }

    public void destroyBanner() {
        runSafelyOnUiThread(new 6());
    }

    public void requestInterstitialAd(String adUnitId, String keywords) {
        runSafelyOnUiThread(new 7(adUnitId, keywords));
    }

    public void showInterstitialAd() {
        runSafelyOnUiThread(new 8());
    }

    public void reportApplicationOpen() {
        runSafelyOnUiThread(new 9());
    }

    private MediationSettings[] extractMediationSettingsFromJson(String json) {
        ArrayList<MediationSettings> settings = new ArrayList();
        JSONArray jsonArray = new JSONArray(json);
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObj = jsonArray.getJSONObject(i);
            String adVendor = jsonObj.getString("adVendor");
            Log.i(TAG, "adding MediationSettings for ad vendor: " + adVendor);
            String[] strArr;
            if (adVendor.equalsIgnoreCase("chartboost")) {
                if (jsonObj.has("customId")) {
                    try {
                        Constructor<?> mediationSettingsConstructor = Class.forName("com.mopub.mobileads.ChartboostRewardedVideo$ChartboostMediationSettings").getConstructor(new Class[]{String.class});
                        strArr = new Object[1];
                        strArr[0] = jsonObj.getString("customId");
                        settings.add((MediationSettings) mediationSettingsConstructor.newInstance(strArr));
                    } catch (ClassNotFoundException e) {
                        Log.i(TAG, "could not find Chartboost ChartboostMediationSettings class. Did you add the Chartboost Network SDK to your Android folder?");
                        printExceptionStackTrace(e);
                    } catch (InstantiationException e2) {
                        printExceptionStackTrace(e2);
                    } catch (NoSuchMethodException e3) {
                        printExceptionStackTrace(e3);
                    } catch (IllegalAccessException e4) {
                        printExceptionStackTrace(e4);
                    } catch (IllegalArgumentException e5) {
                        printExceptionStackTrace(e5);
                    } catch (InvocationTargetException e6) {
                        printExceptionStackTrace(e6);
                    }
                } else {
                    Log.i(TAG, "No customId key found in the settings object. Aborting adding Chartboost MediationSettings");
                }
            } else {
                if (adVendor.equalsIgnoreCase("vungle")) {
                    try {
                        Class<?> builderClass = Class.forName("com.mopub.mobileads.VungleRewardedVideo$VungleMediationSettings$Builder");
                        Object[] objArr = new Object[0];
                        Object b = builderClass.getConstructor(new Class[0]).newInstance(objArr);
                        String str = "withUserId";
                        Method withUserId = builderClass.getDeclaredMethod(r22, new Class[]{String.class});
                        str = "withCancelDialogBody";
                        Method withCancelDialogBody = builderClass.getDeclaredMethod(r22, new Class[]{String.class});
                        str = "withCancelDialogCloseButton";
                        Method withCancelDialogCloseButton = builderClass.getDeclaredMethod(r22, new Class[]{String.class});
                        str = "withCancelDialogKeepWatchingButton";
                        Method withCancelDialogKeepWatchingButton = builderClass.getDeclaredMethod(r22, new Class[]{String.class});
                        str = "withCancelDialogTitle";
                        Method withCancelDialogTitle = builderClass.getDeclaredMethod(r22, new Class[]{String.class});
                        str = "build";
                        Method build = builderClass.getDeclaredMethod(r22, new Class[0]);
                        if (jsonObj.has("userId")) {
                            strArr = new Object[1];
                            strArr[0] = jsonObj.getString("userId");
                            withUserId.invoke(b, strArr);
                        }
                        if (jsonObj.has("cancelDialogBody")) {
                            strArr = new Object[1];
                            strArr[0] = jsonObj.getString("cancelDialogBody");
                            withCancelDialogBody.invoke(b, strArr);
                        }
                        if (jsonObj.has("cancelDialogCloseButton")) {
                            strArr = new Object[1];
                            strArr[0] = jsonObj.getString("cancelDialogCloseButton");
                            withCancelDialogCloseButton.invoke(b, strArr);
                        }
                        if (jsonObj.has("cancelDialogKeepWatchingButton")) {
                            strArr = new Object[1];
                            strArr[0] = jsonObj.getString("cancelDialogKeepWatchingButton");
                            withCancelDialogKeepWatchingButton.invoke(b, strArr);
                        }
                        if (jsonObj.has("cancelDialogTitle")) {
                            strArr = new Object[1];
                            strArr[0] = jsonObj.getString("cancelDialogTitle");
                            withCancelDialogTitle.invoke(b, strArr);
                        }
                        settings.add((MediationSettings) build.invoke(b, new Object[0]));
                    } catch (ClassNotFoundException e7) {
                        Log.i(TAG, "could not find Vungle VungleMediationSettings class. Did you add the Vungle Network SDK to your Android folder?");
                        printExceptionStackTrace(e7);
                    } catch (InstantiationException e22) {
                        printExceptionStackTrace(e22);
                    } catch (NoSuchMethodException e32) {
                        printExceptionStackTrace(e32);
                    } catch (IllegalAccessException e42) {
                        printExceptionStackTrace(e42);
                    } catch (IllegalArgumentException e52) {
                        printExceptionStackTrace(e52);
                    } catch (InvocationTargetException e62) {
                        printExceptionStackTrace(e62);
                    }
                } else {
                    if (adVendor.equalsIgnoreCase("adcolony")) {
                        if (jsonObj.has("withConfirmationDialog")) {
                            if (jsonObj.has("withResultsDialog")) {
                                boolean withConfirmationDialog = jsonObj.getBoolean("withConfirmationDialog");
                                boolean withResultsDialog = jsonObj.getBoolean("withResultsDialog");
                                try {
                                    settings.add((MediationSettings) Class.forName("com.mopub.mobileads.AdColonyRewardedVideo$AdColonyInstanceMediationSettings").getConstructor(new Class[]{Boolean.TYPE, Boolean.TYPE}).newInstance(new Object[]{Boolean.valueOf(withConfirmationDialog), Boolean.valueOf(withResultsDialog)}));
                                } catch (ClassNotFoundException e72) {
                                    try {
                                        Log.i(TAG, "could not find AdColony AdColonyInstanceMediationSettings class. Did you add the AdColony Network SDK to your Android folder?");
                                        printExceptionStackTrace(e72);
                                    } catch (JSONException e8) {
                                        printExceptionStackTrace(e8);
                                    }
                                } catch (InstantiationException e222) {
                                    printExceptionStackTrace(e222);
                                } catch (NoSuchMethodException e322) {
                                    printExceptionStackTrace(e322);
                                } catch (IllegalAccessException e422) {
                                    printExceptionStackTrace(e422);
                                } catch (IllegalArgumentException e522) {
                                    printExceptionStackTrace(e522);
                                } catch (InvocationTargetException e622) {
                                    printExceptionStackTrace(e622);
                                }
                            } else {
                                continue;
                            }
                        } else {
                            continue;
                        }
                    } else {
                        Log.e(TAG, "adVendor not available for custom mediation settings: [" + adVendor + "]");
                    }
                }
            }
        }
        return (MediationSettings[]) settings.toArray(new MediationSettings[settings.size()]);
    }

    public void initializeRewardedVideo() {
        runSafelyOnUiThread(new 10());
    }

    public void requestRewardedVideo(String adUnitId, String json, String keywords, double latitude, double longitude) {
        runSafelyOnUiThread(new 11(latitude, longitude, keywords, json, adUnitId));
    }

    public void showRewardedVideo(String adUnitId) {
        if (MoPub.hasRewardedVideo(adUnitId)) {
            runSafelyOnUiThread(new 12(adUnitId));
        } else {
            Log.i(TAG, "no rewarded video is available at this time");
        }
    }

    public void onBannerLoaded(MoPubView banner) {
        JSONObject json = getJSONFromBannerView(banner);
        try {
            json.put(VastIconXmlManager.WIDTH, banner.getAdWidth());
            json.put(VastIconXmlManager.HEIGHT, banner.getAdHeight());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UnitySendMessage("MoPubManager", "onAdLoaded", json.toString());
        int height = this.mMoPubView.getAdHeight();
        int width = this.mMoPubView.getAdWidth();
        float density = getScreenDensity();
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) this.mMoPubView.getLayoutParams();
        params.width = (int) (((float) width) * density);
        params.height = (int) (((float) height) * density);
        this.mMoPubView.setLayoutParams(params);
    }

    public void onBannerFailed(MoPubView banner, MoPubErrorCode errorCode) {
        Log.i(TAG, "onAdFailed: " + errorCode);
        JSONObject json = getJSONFromBannerView(banner);
        try {
            json.put("errorString", errorCode.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UnitySendMessage("MoPubManager", "onAdFailed", json.toString());
    }

    public void onBannerClicked(MoPubView banner) {
        UnitySendMessage("MoPubManager", "onAdClicked", getJSONFromBannerView(banner).toString());
    }

    public void onBannerExpanded(MoPubView banner) {
        UnitySendMessage("MoPubManager", "onAdExpanded", getJSONFromBannerView(banner).toString());
    }

    public void onBannerCollapsed(MoPubView banner) {
        UnitySendMessage("MoPubManager", "onAdCollapsed", getJSONFromBannerView(banner).toString());
    }

    public void onInterstitialLoaded(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialLoaded: " + interstitial);
        UnitySendMessage("MoPubManager", "onInterstitialLoaded", getJSONFromBannerView(interstitial.getMoPubInterstitialView()).toString());
    }

    public void onInterstitialFailed(MoPubInterstitial interstitial, MoPubErrorCode errorCode) {
        Log.i(TAG, "onInterstitialFailed: " + errorCode);
        JSONObject json = getJSONFromBannerView(interstitial.getMoPubInterstitialView());
        try {
            json.put("errorString", errorCode.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        UnitySendMessage("MoPubManager", "onInterstitialFailed", json.toString());
    }

    public void onInterstitialShown(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialShown: " + interstitial);
        UnitySendMessage("MoPubManager", "onInterstitialShown", getJSONFromBannerView(interstitial.getMoPubInterstitialView()).toString());
    }

    public void onInterstitialClicked(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialClicked: " + interstitial);
        UnityLateEvent.getInstance().add("MoPubManager", "onInterstitialClicked", getJSONFromBannerView(interstitial.getMoPubInterstitialView()).toString());
    }

    public void onInterstitialDismissed(MoPubInterstitial interstitial) {
        Log.i(TAG, "onInterstitialDismissed: " + interstitial);
        UnitySendMessage("MoPubManager", "onInterstitialDismissed", getJSONFromBannerView(interstitial.getMoPubInterstitialView()).toString());
    }

    public void onRewardedVideoLoadSuccess(String adUnitId) {
        UnitySendMessage("MoPubManager", "onRewardedVideoLoaded", adUnitId);
    }

    public void onRewardedVideoLoadFailure(String adUnitId, MoPubErrorCode errorCode) {
        Log.i(TAG, "onRewardedVideoFailed: " + errorCode);
        UnitySendMessage("MoPubManager", "onRewardedVideoFailed", adUnitId);
    }

    public void onRewardedVideoStarted(String adUnitId) {
        UnitySendMessage("MoPubManager", "onRewardedVideoShown", adUnitId);
    }

    public void onRewardedVideoPlaybackError(String adUnitId, MoPubErrorCode errorCode) {
        UnitySendMessage("MoPubManager", "onRewardedVideoFailedToPlay", adUnitId);
    }

    public void onRewardedVideoClosed(String adUnitId) {
        UnitySendMessage("MoPubManager", "onRewardedVideoClosed", adUnitId);
    }

    public void onRewardedVideoCompleted(Set<String> adUnitIds, MoPubReward reward) {
        if (adUnitIds.size() == 0 || reward == null) {
            Log.i(TAG, "onRewardedVideoCompleted with no adUnitId and/or reward. Bailing out.");
            return;
        }
        try {
            JSONObject json = new JSONObject();
            json.put("adUnitId", adUnitIds.toArray()[0].toString());
            json.put("currencyType", BuildConfig.FLAVOR);
            json.put(AppLovinEventParameters.REVENUE_AMOUNT, reward.getAmount());
            UnitySendMessage("MoPubManager", "onRewardedVideoReceivedReward", json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private JSONObject getJSONFromBannerView(MoPubView bannerView) {
        JSONObject json = new JSONObject();
        try {
            String networkType = bannerView.getNetworkType();
            String adType = bannerView.getAdType();
            json.put("adUnitId", bannerView.getAdUnitId());
            json.put("networkType", networkType != null ? networkType : BuildConfig.FLAVOR);
            json.put("adType", adType != null ? adType : BuildConfig.FLAVOR);
            Log.i(TAG, "Extracting Ad info from View. AdUnitId: " + bannerView.getAdUnitId() + " Network Type: " + networkType + " Ad Type: " + adType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json;
    }
}
