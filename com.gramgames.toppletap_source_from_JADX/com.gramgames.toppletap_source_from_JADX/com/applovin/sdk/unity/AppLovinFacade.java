package com.applovin.sdk.unity;

import android.app.Activity;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.Display;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.applovin.adview.AppLovinAdView;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.adview.AppLovinInterstitialAd;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkSettings;
import com.applovin.sdk.AppLovinTargetingData;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.player.UnityPlayer;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class AppLovinFacade {
    public static final float AD_POSITION_BOTTOM = -50000.0f;
    public static final float AD_POSITION_CENTER = -10000.0f;
    public static final float AD_POSITION_LEFT = -20000.0f;
    public static final float AD_POSITION_RIGHT = -30000.0f;
    public static final float AD_POSITION_TOP = -40000.0f;
    public static final String SERIALIZED_KEY_VALUE_PAIR_SEPARATOR;
    public static final String SERIALIZED_KEY_VALUE_SEPARATOR;
    public static final String TAG = "AppLovinFacade-Unity";
    public static final String UNITY_PLUGIN_BUILD = "3501";
    public static final String UNITY_PLUGIN_VERSION = "3.5.0";
    private static final Map<Activity, AppLovinFacade> facades;
    private static String gameObjectToNotify;
    private static String sdkKey;
    private static AppLovinSdkSettings sdkSettings;
    private AppLovinAdView adView;
    private int adWidth;
    private final DisplayMetrics displayMetric;
    private boolean firstLoad;
    private float horizontalPosition;
    private AppLovinIncentivizedInterstitial incentivizedInterstitial;
    private volatile boolean interCurrentlyShowing;
    private volatile boolean isImmersive;
    private volatile boolean isIncentReady;
    private volatile AppLovinAd lastInterstitial;
    private final AppLovinLogger logger;
    private FrameLayout mainLayout;
    private final Activity parentActivity;
    private int screenHeight;
    private int screenWidth;
    private final AppLovinSdk sdk;
    private float verticalPosition;

    class 10 implements Runnable {
        10() {
        }

        public void run() {
            int gravity = 17;
            if (AppLovinFacade.this.horizontalPosition == AppLovinFacade.AD_POSITION_LEFT) {
                gravity = 3;
            } else if (AppLovinFacade.this.horizontalPosition == AppLovinFacade.AD_POSITION_CENTER) {
                gravity = 17;
            } else if (AppLovinFacade.this.horizontalPosition == AppLovinFacade.AD_POSITION_RIGHT) {
                gravity = 5;
            }
            if (AppLovinFacade.this.verticalPosition == AppLovinFacade.AD_POSITION_TOP) {
                gravity |= 48;
            } else if (AppLovinFacade.this.verticalPosition == AppLovinFacade.AD_POSITION_BOTTOM) {
                gravity |= 80;
            }
            AppLovinFacade.this.updatePadding();
            AppLovinFacade.this.mainLayout.setLayoutParams(new LayoutParams(-2, -2, gravity));
        }
    }

    class 11 implements UnityExtendedLoadListener {
        final /* synthetic */ UnityListenerWrapper val$listenerWrapper;

        11(UnityListenerWrapper unityListenerWrapper) {
            this.val$listenerWrapper = unityListenerWrapper;
        }

        public void onAdReceived(AppLovinAd ad) {
            AppLovinFacade.this.lastInterstitial = ad;
            this.val$listenerWrapper.onAdReceived(ad);
        }

        public void onAdLoadFailed(AppLovinAdSize size, AppLovinAdType type, int errorCode) {
            this.val$listenerWrapper.onAdLoadFailed(size, type, errorCode);
        }
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            AppLovinFacade.this.adView = new AppLovinAdView(AppLovinFacade.this.sdk, AppLovinAdSize.BANNER, AppLovinFacade.this.parentActivity);
            AppLovinFacade.this.adView.setVisibility(8);
            AppLovinFacade.this.setAdViewListeners();
            AppLovinFacade.this.mainLayout = new FrameLayout(AppLovinFacade.this.parentActivity);
            AppLovinFacade.this.mainLayout.addView(AppLovinFacade.this.adView, new LayoutParams(-2, -2, 51));
            AppLovinFacade.this.parentActivity.addContentView(AppLovinFacade.this.mainLayout, new ViewGroup.LayoutParams(-1, -1));
            AppLovinFacade.this.updateAdPosition();
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            AppLovinFacade.this.parentActivity.getWindow().getDecorView().setSystemUiVisibility(3846);
        }
    }

    class 3 implements Runnable {
        3() {
        }

        public void run() {
            AppLovinFacade.this.parentActivity.getWindow().getDecorView().setSystemUiVisibility(1792);
        }
    }

    class 4 implements Runnable {
        4() {
        }

        public void run() {
            AppLovinFacade.this.adView.setVisibility(0);
            AppLovinFacade.this.mainLayout.setVisibility(0);
        }
    }

    class 5 implements Runnable {
        5() {
        }

        public void run() {
            AppLovinFacade.this.mainLayout.setVisibility(8);
        }
    }

    class 6 implements Runnable {
        6() {
        }

        public void run() {
            AppLovinFacade.this.setAdViewListeners();
            AppLovinFacade.this.adView.loadNextAd();
        }
    }

    class 7 implements Runnable {
        7() {
        }

        public void run() {
            AppLovinFacade.this.updatePadding();
        }
    }

    class 8 implements Runnable {
        final /* synthetic */ String val$placement;

        8(String str) {
            this.val$placement = str;
        }

        public void run() {
            AppLovinInterstitialAdDialog adDialog = AppLovinInterstitialAd.create(AppLovinFacade.getAppropriateSdkInstance(AppLovinFacade.this.parentActivity), AppLovinFacade.this.parentActivity);
            UnityListenerWrapper listenerWrapper = new UnityListenerWrapper(null);
            adDialog.setAdLoadListener(new TypeRememberingLoadListener(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, listenerWrapper));
            adDialog.setAdDisplayListener(listenerWrapper);
            adDialog.setAdVideoPlaybackListener(listenerWrapper);
            adDialog.setAdClickListener(listenerWrapper);
            if (AppLovinFacade.this.lastInterstitial != null) {
                adDialog.showAndRender(AppLovinFacade.this.lastInterstitial, this.val$placement);
            } else {
                adDialog.show(this.val$placement);
            }
        }
    }

    class 9 implements Runnable {
        final /* synthetic */ String val$placement;

        9(String str) {
            this.val$placement = str;
        }

        public void run() {
            UnityListenerWrapper listenerWrapper = new UnityListenerWrapper(null);
            if (AppLovinFacade.this.incentivizedInterstitial != null) {
                AppLovinFacade.this.incentivizedInterstitial.show(AppLovinFacade.this.parentActivity, this.val$placement, listenerWrapper, listenerWrapper, listenerWrapper, listenerWrapper);
            } else {
                AppLovinFacade.this.sendToCSharp("LOADFAILED");
            }
        }
    }

    private class UnityListenerWrapper implements UnityExtendedLoadListener, AppLovinAdDisplayListener, AppLovinAdClickListener, AppLovinAdVideoPlaybackListener, AppLovinAdRewardListener {
        private UnityListenerWrapper() {
        }

        public void adDisplayed(AppLovinAd ad) {
            AppLovinFacade.this.sendToCSharp("DISPLAYED" + (ad.getType().equals(AppLovinAdType.INCENTIVIZED) ? "REWARDED" : ad.getSize().getLabel()));
            if (ad.getSize().equals(AppLovinAdSize.INTERSTITIAL)) {
                AppLovinFacade.this.interCurrentlyShowing = true;
            }
        }

        public void adHidden(AppLovinAd ad) {
            AppLovinFacade.this.sendToCSharp("HIDDEN" + (ad.getType().equals(AppLovinAdType.INCENTIVIZED) ? "REWARDED" : ad.getSize().getLabel()));
            if (ad.getSize().equals(AppLovinAdSize.INTERSTITIAL)) {
                AppLovinFacade.this.interCurrentlyShowing = false;
                AppLovinFacade.this.lastInterstitial = null;
            }
            if (ad.getType().equals(AppLovinAdType.INCENTIVIZED)) {
                AppLovinFacade.this.isIncentReady = false;
            }
        }

        public void onAdReceived(AppLovinAd ad) {
            if (ad.getType().equals(AppLovinAdType.INCENTIVIZED)) {
                AppLovinFacade.this.sendToCSharp("LOADEDREWARDED");
                AppLovinFacade.this.isIncentReady = true;
                return;
            }
            AppLovinFacade.this.sendToCSharp("LOADED" + ad.getSize().getLabel());
        }

        public void onAdLoadFailed(AppLovinAdSize size, AppLovinAdType type, int errorCode) {
            AppLovinFacade.this.sendToCSharp("LOADFAILED");
            if (type.equals(AppLovinAdType.INCENTIVIZED)) {
                AppLovinFacade.this.sendToCSharp("LOADREWARDEDFAILED");
            } else {
                AppLovinFacade.this.sendToCSharp("LOAD" + size.getLabel().toUpperCase() + "FAILED");
            }
        }

        public void userRewardVerified(AppLovinAd ad, Map response) {
            AppLovinFacade.this.sendToCSharp("REWARDAPPROVED");
            String amountStr = (String) response.get(AppLovinEventParameters.REVENUE_AMOUNT);
            String currencyName = (String) response.get(AppLovinEventParameters.REVENUE_CURRENCY);
            if (amountStr != null && currencyName != null) {
                AppLovinFacade.this.sendToCSharp("REWARDAPPROVEDINFO|" + amountStr + "|" + currencyName);
            }
        }

        public void userOverQuota(AppLovinAd ad, Map response) {
            AppLovinFacade.this.sendToCSharp("REWARDOVERQUOTA");
        }

        public void userRewardRejected(AppLovinAd ad, Map response) {
            AppLovinFacade.this.sendToCSharp("REWARDREJECTED");
        }

        public void validationRequestFailed(AppLovinAd ad, int errorCode) {
            if (errorCode == AppLovinErrorCodes.INCENTIVIZED_USER_CLOSED_VIDEO) {
                AppLovinFacade.this.sendToCSharp("USERCLOSEDEARLY");
            } else {
                AppLovinFacade.this.sendToCSharp("REWARDTIMEOUT");
            }
        }

        public void userDeclinedToViewAd(AppLovinAd ad) {
            AppLovinFacade.this.sendToCSharp("USERDECLINED");
        }

        public void videoPlaybackBegan(AppLovinAd ad) {
            AppLovinFacade.this.sendToCSharp("VIDEOBEGAN");
        }

        public void videoPlaybackEnded(AppLovinAd ad, double percentViewed, boolean fullyWatched) {
            AppLovinFacade.this.sendToCSharp("VIDEOSTOPPED");
        }

        public void adClicked(AppLovinAd ad) {
            AppLovinFacade.this.sendToCSharp("CLICKED");
        }
    }

    static {
        SERIALIZED_KEY_VALUE_PAIR_SEPARATOR = String.valueOf('\u001c');
        SERIALIZED_KEY_VALUE_SEPARATOR = String.valueOf('\u001d');
        facades = new WeakHashMap();
    }

    private static AppLovinSdk getAppropriateSdkInstance(Activity currentActivity) {
        if (sdkSettings == null) {
            sdkSettings = new AppLovinSdkSettings();
        }
        if (sdkKey != null) {
            return AppLovinSdk.getInstance(sdkKey, sdkSettings, currentActivity);
        }
        return AppLovinSdk.getInstance(currentActivity);
    }

    public static AppLovinFacade getAppLovinFacade(Activity activity) {
        AppLovinFacade facade;
        synchronized (facades) {
            facade = (AppLovinFacade) facades.get(activity);
            if (facade == null) {
                facade = new AppLovinFacade(activity);
                facades.put(activity, facade);
            }
        }
        return facade;
    }

    public static void SetAdPosition(Activity currentActivity, String horizontal, String vertical) {
        getAppLovinFacade(currentActivity).setAdPosition(stringCompatibility(horizontal), stringCompatibility(vertical));
    }

    public static void SetAdPosition(Activity currentActivity, float x, float y) {
        getAppLovinFacade(currentActivity).setAdPosition(x, y);
    }

    public static void ShowAd(Activity currentActivity) {
        getAppLovinFacade(currentActivity).showAd();
    }

    public static void HideAd(Activity currentActivity) {
        getAppLovinFacade(currentActivity).hideAd();
    }

    public static void LoadNextAd(Activity currentActivity) {
        getAppLovinFacade(currentActivity).loadNextAd();
    }

    public static void SetAdWidth(Activity currentActivity, int width) {
        getAppLovinFacade(currentActivity).setAdWidth(width);
    }

    public static void ShowInterstitial(Activity currentActivity, String placement) {
        getAppLovinFacade(currentActivity).showInterstitial(placement);
    }

    public static void LoadIncentInterstitial(Activity currentActivity) {
        getAppLovinFacade(currentActivity).preloadIncentInterstitial();
    }

    public static void ShowIncentInterstitial(Activity currentActivity, String placement) {
        getAppLovinFacade(currentActivity).showIncentInterstitial(placement);
    }

    public static void SetGender(Activity currentActivity, String gender) {
        if (sdkInitialized(currentActivity)) {
            char genderChar;
            if (gender.equalsIgnoreCase("m") || gender.equalsIgnoreCase("male")) {
                genderChar = AppLovinTargetingData.GENDER_MALE;
            } else if (gender.equalsIgnoreCase("f") || gender.equalsIgnoreCase("female")) {
                genderChar = AppLovinTargetingData.GENDER_FEMALE;
            } else {
                return;
            }
            getAppropriateSdkInstance(currentActivity).getTargetingData().setGender(genderChar);
        }
    }

    public static void SetBirthYear(Activity currentActivity, int birthYear) {
        if (sdkInitialized(currentActivity)) {
            getAppropriateSdkInstance(currentActivity).getTargetingData().setBirthYear(birthYear);
        }
    }

    @Deprecated
    public static void SetCarrier(Activity currentActivity, String carrier) {
        Log.e(AppLovinLogger.SDK_TAG, "Explicitly setting `carrier` targeting data is deprecated.");
    }

    @Deprecated
    public static void SetCountry(Activity currentActivity, String country) {
        Log.e(AppLovinLogger.SDK_TAG, "Explicitly setting `country` targeting data is deprecated.");
    }

    private static boolean sdkInitialized(Activity currentActivity) {
        if (currentActivity == null || getAppropriateSdkInstance(currentActivity) == null) {
            return false;
        }
        return true;
    }

    public static void InitializeSdk(Activity currentActivity) {
        if (!sdkInitialized(currentActivity)) {
            AppLovinSdk.initializeSdk(currentActivity);
        }
    }

    public static void SetInterests(Activity currentActivity, String... interests) {
        if (sdkInitialized(currentActivity)) {
            getAppropriateSdkInstance(currentActivity).getTargetingData().setInterests(interests);
        }
    }

    public static void SetKeywords(Activity currentActivity, String... keywords) {
        if (sdkInitialized(currentActivity)) {
            getAppropriateSdkInstance(currentActivity).getTargetingData().setKeywords(keywords);
        }
    }

    public static void SetLanguage(Activity currentActivity, String language) {
        if (sdkInitialized(currentActivity)) {
            getAppropriateSdkInstance(currentActivity).getTargetingData().setLanguage(language);
        }
    }

    public static void PutExtra(Activity currentActivity, String key, String value) {
        if (sdkInitialized(currentActivity)) {
            getAppropriateSdkInstance(currentActivity).getTargetingData().putExtra(key, value);
        }
    }

    public static void SetSdkKey(Activity currentActivity, String key) {
        sdkKey = key;
    }

    public static void SetVerboseLoggingOn(String isVerboseLogging) {
        if (sdkSettings == null) {
            sdkSettings = new AppLovinSdkSettings();
        }
        sdkSettings.setVerboseLogging(Boolean.parseBoolean(isVerboseLogging));
    }

    public static void SetMuted(String muted) {
        if (sdkSettings == null) {
            sdkSettings = new AppLovinSdkSettings();
        }
        sdkSettings.setMuted(Boolean.parseBoolean(muted));
    }

    public static String IsMuted() {
        if (sdkSettings == null) {
            sdkSettings = new AppLovinSdkSettings();
        }
        return Boolean.toString(sdkSettings.isMuted());
    }

    public static void PreloadInterstitial(Activity currentActivity) {
        getAppLovinFacade(currentActivity).preloadInterstitial();
    }

    public static String IsInterstitialReady(Activity currentActivity) {
        return getAppLovinFacade(currentActivity).isInterstitialReady(currentActivity);
    }

    public static String IsInterstitialShowing(Activity currentActivity) {
        return getAppLovinFacade(currentActivity).isInterstitialShowing();
    }

    public static String IsIncentReady(Activity currentActivity) {
        return getAppLovinFacade(currentActivity).isIncentReady();
    }

    public static String IsCurrentInterstitialVideo(Activity currentActivity) {
        return getAppLovinFacade(currentActivity).isCurrentInterstitialVideo();
    }

    public static void TrackEvent(Activity currentActivity, String eventType, String serializedParameters) {
        getAppLovinFacade(currentActivity).trackEvent(currentActivity, eventType, serializedParameters);
    }

    public static void EnableImmersiveMode(Activity currentActivity) {
        getAppLovinFacade(currentActivity).enableImmersiveMode(currentActivity);
    }

    public AppLovinFacade(Activity currentActivity) {
        this.firstLoad = false;
        this.horizontalPosition = AD_POSITION_CENTER;
        this.verticalPosition = AD_POSITION_CENTER;
        this.interCurrentlyShowing = false;
        this.isIncentReady = false;
        this.isImmersive = false;
        if (currentActivity == null) {
            throw new IllegalArgumentException("No activity specified");
        }
        this.parentActivity = currentActivity;
        this.sdk = getAppropriateSdkInstance(currentActivity);
        this.logger = this.sdk.getLogger();
        try {
            this.sdk.setPluginVersion("unity-3.5.0");
        } catch (Throwable th) {
            this.logger.userError(AppLovinLogger.SDK_TAG, "AppLovin SDK may be out of date. Please consider updating to the latest version.");
        }
        Display display = this.parentActivity.getWindowManager().getDefaultDisplay();
        this.displayMetric = this.parentActivity.getResources().getDisplayMetrics();
        if (VERSION.SDK_INT > 13) {
            Point size = new Point();
            display.getSize(size);
            this.screenWidth = size.x;
            this.screenHeight = size.y;
        } else {
            this.screenWidth = display.getWidth();
            this.screenHeight = display.getHeight();
        }
        if (this.screenWidth > this.screenHeight) {
            this.adWidth = (int) TypedValue.applyDimension(1, 320.0f, this.displayMetric);
        } else {
            this.adWidth = this.screenWidth;
        }
        try {
            Bundle metaData = this.parentActivity.getPackageManager().getApplicationInfo(this.parentActivity.getPackageName(), RadialCountdown.BACKGROUND_ALPHA).metaData;
            if (metaData != null) {
                int userAdWidth = metaData.getInt("applovin.sdk.ad.width", 0);
                if (userAdWidth > 0) {
                    setAdWidth(userAdWidth);
                }
                if (metaData.getString("applovin.sdk.ad.position.vertical") != null) {
                    this.verticalPosition = stringCompatibility(metaData.getString("applovin.sdk.ad.position.vertical"));
                }
                if (metaData.getString("applovin.sdk.ad.position.horizontal") != null) {
                    this.horizontalPosition = stringCompatibility(metaData.getString("applovin.sdk.ad.position.horizontal"));
                }
            }
        } catch (NameNotFoundException e) {
            Log.e(AppLovinLogger.SDK_TAG, "Name not found", e);
        }
        this.parentActivity.runOnUiThread(new 1());
    }

    private String isIncentReady() {
        return Boolean.toString(this.isIncentReady);
    }

    private String isCurrentInterstitialVideo() {
        boolean z = this.lastInterstitial != null && this.lastInterstitial.isVideoAd();
        return Boolean.toString(z);
    }

    private void setAdViewListeners() {
        UnityListenerWrapper listenerWrapper = new UnityListenerWrapper();
        this.adView.setAdLoadListener(new TypeRememberingLoadListener(AppLovinAdSize.BANNER, AppLovinAdType.REGULAR, listenerWrapper));
        this.adView.setAdDisplayListener(listenerWrapper);
        this.adView.setAdVideoPlaybackListener(listenerWrapper);
        this.adView.setAdClickListener(listenerWrapper);
    }

    private void enableImmersiveMode(Activity currentActivity) {
        if (VERSION.SDK_INT >= 19) {
            this.isImmersive = true;
            this.parentActivity.runOnUiThread(new 2());
        }
    }

    private void disableImmersiveMode() {
        if (VERSION.SDK_INT >= 19) {
            this.parentActivity.runOnUiThread(new 3());
        }
    }

    public static float stringCompatibility(String val) {
        if (val.equals(TtmlNode.LEFT)) {
            return AD_POSITION_LEFT;
        }
        if (val.equals(TtmlNode.RIGHT)) {
            return AD_POSITION_RIGHT;
        }
        if (val.equals("top")) {
            return AD_POSITION_TOP;
        }
        if (val.equals("bottom")) {
            return AD_POSITION_BOTTOM;
        }
        return (val.equals(TtmlNode.CENTER) || !val.equals("middle")) ? AD_POSITION_CENTER : AD_POSITION_CENTER;
    }

    public void showAd() {
        this.logger.d(AppLovinLogger.SDK_TAG, "Show AppLovin Ad");
        if (!this.firstLoad) {
            loadNextAd();
            this.firstLoad = true;
        }
        this.parentActivity.runOnUiThread(new 4());
    }

    public void hideAd() {
        this.logger.d(AppLovinLogger.SDK_TAG, "Hide AppLovin Ad");
        this.parentActivity.runOnUiThread(new 5());
    }

    public void loadNextAd() {
        this.logger.d(AppLovinLogger.SDK_TAG, "AppLovin Load Next Ad");
        this.firstLoad = true;
        this.parentActivity.runOnUiThread(new 6());
    }

    public void setAdWidth(int width) {
        this.adWidth = (int) TypedValue.applyDimension(1, (float) width, this.displayMetric);
        this.parentActivity.runOnUiThread(new 7());
    }

    public void showInterstitial(String placement) {
        if (this.isImmersive) {
            disableImmersiveMode();
        }
        this.parentActivity.runOnUiThread(new 8(placement));
    }

    public void preloadIncentInterstitial() {
        this.incentivizedInterstitial = AppLovinIncentivizedInterstitial.create(getAppropriateSdkInstance(this.parentActivity));
        this.incentivizedInterstitial.preload(new TypeRememberingLoadListener(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.INCENTIVIZED, new UnityListenerWrapper()));
    }

    public void showIncentInterstitial(String placement) {
        this.parentActivity.runOnUiThread(new 9(placement));
    }

    private void updatePadding() {
        int difference = this.screenWidth - this.adWidth;
        if (this.horizontalPosition == AD_POSITION_LEFT) {
            this.adView.setPadding(0, 0, difference, 0);
        } else if (this.horizontalPosition == AD_POSITION_CENTER) {
            this.adView.setPadding(difference / 2, 0, difference / 2, 0);
        } else if (this.horizontalPosition == AD_POSITION_RIGHT) {
            this.adView.setPadding(difference, 0, 0, 0);
        }
    }

    public void updateAdPosition() {
        if (this.horizontalPosition == 0.0f) {
            throw new NullPointerException("No horizontal position specifed");
        } else if (this.verticalPosition == 0.0f) {
            throw new NullPointerException("No vertical position specifed");
        } else {
            this.parentActivity.runOnUiThread(new 10());
        }
    }

    public void setAdPosition(float horizontal, float vertical) {
        if (horizontal == 0.0f) {
            throw new NullPointerException("No horizontal position specified");
        } else if (vertical == 0.0f) {
            throw new NullPointerException("No vertical position specified");
        } else {
            this.horizontalPosition = horizontal;
            this.verticalPosition = vertical;
            updateAdPosition();
        }
    }

    public void preloadInterstitial() {
        getAppropriateSdkInstance(this.parentActivity).getAdService().loadNextAd(AppLovinAdSize.INTERSTITIAL, new TypeRememberingLoadListener(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR, new 11(new UnityListenerWrapper())));
    }

    public static void SetIncentivizedUsername(Activity currentActivity, String username) {
        new AppLovinIncentivizedInterstitial(getAppropriateSdkInstance(currentActivity)).setUserIdentifier(username);
    }

    public String isInterstitialReady(Activity activity) {
        boolean hasLastInter;
        boolean z = false;
        if (this.lastInterstitial != null) {
            hasLastInter = true;
        } else {
            hasLastInter = false;
        }
        boolean hasPreloadedInter = getAppropriateSdkInstance(activity).getAdService().hasPreloadedAd(AppLovinAdSize.INTERSTITIAL);
        if (hasLastInter || hasPreloadedInter) {
            z = true;
        }
        return Boolean.toString(z);
    }

    public String isInterstitialShowing() {
        return Boolean.toString(this.interCurrentlyShowing);
    }

    public static void SetUnityAdListener(String gameObjectToNotify) {
        gameObjectToNotify = gameObjectToNotify;
    }

    public void sendToCSharp(String message) {
        this.logger.d(TAG, "Sending message to Unity/C#: " + message);
        if (gameObjectToNotify != null) {
            UnityPlayer.UnitySendMessage(gameObjectToNotify, "onAppLovinEventReceived", message);
        } else {
            this.logger.d(TAG, "Skipping sending message to Unity/C#: No GameObject provided.");
        }
    }

    public void trackEvent(Activity activity, String eventType, String serializedParameters) {
        this.logger.d(TAG, "Tracking event of type " + eventType + "; parameters: " + serializedParameters);
        if (serializedParameters == null) {
            getAppropriateSdkInstance(activity).getEventService().trackEvent(eventType);
            return;
        }
        getAppropriateSdkInstance(activity).getEventService().trackEvent(eventType, deserializeParameters(serializedParameters));
    }

    private static Map<String, String> deserializeParameters(String serializedParameters) {
        Map<String, String> deserialized = new HashMap();
        for (String keyValuePair : serializedParameters.split(SERIALIZED_KEY_VALUE_PAIR_SEPARATOR)) {
            if (keyValuePair.length() > 0) {
                String[] splitPair = keyValuePair.split(SERIALIZED_KEY_VALUE_SEPARATOR);
                if (splitPair.length > 1) {
                    deserialized.put(splitPair[0], splitPair[1]);
                }
            }
        }
        return deserialized;
    }
}
