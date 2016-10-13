package com.facebook.unity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.AccessToken;
import com.facebook.AccessToken.AccessTokenRefreshCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.FacebookSdk.InitializeCallback;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.appevents.internal.ActivityLifecycleTracker;
import com.facebook.applinks.AppLinkData;
import com.facebook.applinks.AppLinkData.CompletionHandler;
import com.facebook.internal.BundleJSONConverter;
import com.facebook.internal.InternalSettings;
import com.facebook.internal.Utility;
import com.facebook.login.LoginManager;
import com.facebook.share.widget.ShareDialog.Mode;
import com.facebook.unity.FBUnityLoginActivity.LoginType;
import com.mopub.mobileads.VungleRewardedVideo;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Currency;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;

public class FB {
    static final String FB_UNITY_OBJECT = "UnityFacebookSDKPlugin";
    static Mode ShareDialogMode;
    static final String TAG;
    private static AtomicBoolean activateAppCalled;
    private static AppEventsLogger appEventsLogger;
    private static Intent intent;

    static class 1 implements InitializeCallback {
        final /* synthetic */ String val$appID;

        1(String str) {
            this.val$appID = str;
        }

        public void onInitialized() {
            UnityMessage unityMessage = new UnityMessage("OnInitComplete");
            AccessToken token = AccessToken.getCurrentAccessToken();
            if (token != null) {
                FBLogin.addLoginParametersToMessage(unityMessage, token, null);
            } else {
                unityMessage.put("key_hash", FB.getKeyHash());
            }
            FB.ActivateApp(this.val$appID);
            unityMessage.send();
        }
    }

    static class 2 implements CompletionHandler {
        final /* synthetic */ UnityMessage val$unityMessage;

        2(UnityMessage unityMessage) {
            this.val$unityMessage = unityMessage;
        }

        public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
            FB.addAppLinkToMessage(this.val$unityMessage, appLinkData);
            this.val$unityMessage.send();
        }
    }

    static class 3 implements AccessTokenRefreshCallback {
        final /* synthetic */ UnityMessage val$unityMessage;

        3(UnityMessage unityMessage) {
            this.val$unityMessage = unityMessage;
        }

        public void OnTokenRefreshed(AccessToken accessToken) {
            FBLogin.addLoginParametersToMessage(this.val$unityMessage, accessToken, null);
            this.val$unityMessage.send();
        }

        public void OnTokenRefreshFailed(FacebookException e) {
            this.val$unityMessage.sendError(e.getMessage());
        }
    }

    static class 4 implements CompletionHandler {
        final /* synthetic */ UnityMessage val$unityMessage;

        4(UnityMessage unityMessage) {
            this.val$unityMessage = unityMessage;
        }

        public void onDeferredAppLinkDataFetched(AppLinkData appLinkData) {
            FB.addAppLinkToMessage(this.val$unityMessage, appLinkData);
            this.val$unityMessage.send();
        }
    }

    static class 5 implements Runnable {
        final /* synthetic */ Activity val$unityActivity;

        5(Activity activity) {
            this.val$unityActivity = activity;
        }

        public void run() {
            ActivityLifecycleTracker.onActivityCreated(this.val$unityActivity);
            ActivityLifecycleTracker.onActivityResumed(this.val$unityActivity);
        }
    }

    static {
        TAG = FB.class.getName();
        activateAppCalled = new AtomicBoolean();
        ShareDialogMode = Mode.AUTOMATIC;
    }

    private static AppEventsLogger getAppEventsLogger() {
        if (appEventsLogger == null) {
            appEventsLogger = AppEventsLogger.newLogger(getUnityActivity().getApplicationContext());
        }
        return appEventsLogger;
    }

    public static Activity getUnityActivity() {
        return UnityReflection.GetUnityActivity();
    }

    @UnityCallable
    public static void Init(String params_str) {
        String appID;
        Log.v(TAG, "Init(" + params_str + ")");
        UnityParams unity_params = UnityParams.parse(params_str, "couldn't parse init params: " + params_str);
        if (unity_params.hasString(VungleRewardedVideo.APP_ID_KEY).booleanValue()) {
            appID = unity_params.getString(VungleRewardedVideo.APP_ID_KEY);
        } else {
            appID = Utility.getMetadataApplicationId(getUnityActivity());
        }
        FacebookSdk.setApplicationId(appID);
        FacebookSdk.sdkInitialize(getUnityActivity(), new 1(appID));
    }

    @UnityCallable
    public static void LoginWithReadPermissions(String params_str) {
        Log.v(TAG, "LoginWithReadPermissions(" + params_str + ")");
        Intent intent = new Intent(getUnityActivity(), FBUnityLoginActivity.class);
        intent.putExtra(FBUnityLoginActivity.LOGIN_PARAMS, params_str);
        intent.putExtra(FBUnityLoginActivity.LOGIN_TYPE, LoginType.READ);
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void LoginWithPublishPermissions(String params_str) {
        Log.v(TAG, "LoginWithPublishPermissions(" + params_str + ")");
        Intent intent = new Intent(getUnityActivity(), FBUnityLoginActivity.class);
        intent.putExtra(FBUnityLoginActivity.LOGIN_PARAMS, params_str);
        intent.putExtra(FBUnityLoginActivity.LOGIN_TYPE, LoginType.PUBLISH);
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void Logout(String params_str) {
        Log.v(TAG, "Logout(" + params_str + ")");
        LoginManager.getInstance().logOut();
        UnityMessage message = new UnityMessage("OnLogoutComplete");
        message.put("did_complete", Boolean.valueOf(true));
        message.send();
    }

    @UnityCallable
    public static void AppRequest(String params_str) {
        Log.v(TAG, "AppRequest(" + params_str + ")");
        Intent intent = new Intent(getUnityActivity(), FBUnityGameRequestActivity.class);
        intent.putExtra(FBUnityGameRequestActivity.GAME_REQUEST_PARAMS, UnityParams.parse(params_str).getStringParams());
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void GameGroupCreate(String params_str) {
        Log.v(TAG, "GameGroupCreate(" + params_str + ")");
        Bundle params = UnityParams.parse(params_str).getStringParams();
        Intent intent = new Intent(getUnityActivity(), FBUnityCreateGameGroupActivity.class);
        intent.putExtra(FBUnityCreateGameGroupActivity.CREATE_GAME_GROUP_PARAMS, params);
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void GameGroupJoin(String params_str) {
        Log.v(TAG, "GameGroupJoin(" + params_str + ")");
        Bundle params = UnityParams.parse(params_str).getStringParams();
        Intent intent = new Intent(getUnityActivity(), FBUnityJoinGameGroupActivity.class);
        intent.putExtra(FBUnityJoinGameGroupActivity.JOIN_GAME_GROUP_PARAMS, params);
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void ShareLink(String params_str) {
        Log.v(TAG, "ShareLink(" + params_str + ")");
        Bundle params = UnityParams.parse(params_str).getStringParams();
        Intent intent = new Intent(getUnityActivity(), FBUnityDialogsActivity.class);
        intent.putExtra(FBUnityDialogsActivity.DIALOG_TYPE, ShareDialogMode);
        intent.putExtra(FBUnityDialogsActivity.SHARE_DIALOG_PARAMS, params);
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void FeedShare(String params_str) {
        Log.v(TAG, "FeedShare(" + params_str + ")");
        Bundle params = UnityParams.parse(params_str).getStringParams();
        Intent intent = new Intent(getUnityActivity(), FBUnityDialogsActivity.class);
        intent.putExtra(FBUnityDialogsActivity.DIALOG_TYPE, Mode.FEED);
        intent.putExtra(FBUnityDialogsActivity.FEED_DIALOG_PARAMS, params);
        getUnityActivity().startActivity(intent);
    }

    public static void SetIntent(Intent intent) {
        intent = intent;
    }

    public static void SetLimitEventUsage(String params_str) {
        Log.v(TAG, "SetLimitEventUsage(" + params_str + ")");
        FacebookSdk.setLimitEventAndDataUsage(getUnityActivity().getApplicationContext(), Boolean.valueOf(params_str).booleanValue());
    }

    @UnityCallable
    public static void LogAppEvent(String params_str) {
        Log.v(TAG, "LogAppEvent(" + params_str + ")");
        UnityParams unity_params = UnityParams.parse(params_str);
        Bundle parameters = new Bundle();
        if (unity_params.has("parameters")) {
            parameters = unity_params.getParamsObject("parameters").getStringParams();
        }
        if (unity_params.has("logPurchase")) {
            getAppEventsLogger().logPurchase(new BigDecimal(unity_params.getDouble("logPurchase")), Currency.getInstance(unity_params.getString(AppLovinEventParameters.REVENUE_CURRENCY)), parameters);
        } else if (!unity_params.hasString("logEvent").booleanValue()) {
            Log.e(TAG, "couldn't logPurchase or logEvent params: " + params_str);
        } else if (unity_params.has("valueToSum")) {
            getAppEventsLogger().logEvent(unity_params.getString("logEvent"), unity_params.getDouble("valueToSum"), parameters);
        } else {
            getAppEventsLogger().logEvent(unity_params.getString("logEvent"), parameters);
        }
    }

    @UnityCallable
    public static void SetShareDialogMode(String mode) {
        Log.v(TAG, "SetShareDialogMode(" + mode + ")");
        if (mode.equalsIgnoreCase("NATIVE")) {
            ShareDialogMode = Mode.NATIVE;
        } else if (mode.equalsIgnoreCase("WEB")) {
            ShareDialogMode = Mode.WEB;
        } else if (mode.equalsIgnoreCase("FEED")) {
            ShareDialogMode = Mode.FEED;
        } else {
            ShareDialogMode = Mode.AUTOMATIC;
        }
    }

    @UnityCallable
    public static String GetSdkVersion() {
        return FacebookSdk.getSdkVersion();
    }

    @UnityCallable
    public static void SetUserAgentSuffix(String suffix) {
        Log.v(TAG, "SetUserAgentSuffix(" + suffix + ")");
        InternalSettings.setCustomUserAgent(suffix);
    }

    @UnityCallable
    public static void AppInvite(String paramsStr) {
        Log.v(TAG, "AppInvite(" + paramsStr + ")");
        Intent intent = new Intent(getUnityActivity(), AppInviteDialogActivity.class);
        intent.putExtra(AppInviteDialogActivity.DIALOG_PARAMS, UnityParams.parse(paramsStr).getStringParams());
        getUnityActivity().startActivity(intent);
    }

    @UnityCallable
    public static void FetchDeferredAppLinkData(String paramsStr) {
        LogMethodCall("FetchDeferredAppLinkData", paramsStr);
        UnityParams unityParams = UnityParams.parse(paramsStr);
        UnityMessage unityMessage = new UnityMessage("OnFetchDeferredAppLinkComplete");
        if (unityParams.hasString(Constants.CALLBACK_ID_KEY).booleanValue()) {
            unityMessage.put(Constants.CALLBACK_ID_KEY, unityParams.getString(Constants.CALLBACK_ID_KEY));
        }
        AppLinkData.fetchDeferredAppLinkData(getUnityActivity(), new 2(unityMessage));
    }

    @UnityCallable
    public static void GetAppLink(String paramsStr) {
        Log.v(TAG, "GetAppLink(" + paramsStr + ")");
        UnityMessage unityMessage = UnityMessage.createWithCallbackFromParams("OnGetAppLinkComplete", UnityParams.parse(paramsStr));
        if (intent == null) {
            unityMessage.put("did_complete", Boolean.valueOf(true));
            unityMessage.send();
            return;
        }
        AppLinkData appLinkData = AppLinkData.createFromAlApplinkData(intent);
        if (appLinkData != null) {
            addAppLinkToMessage(unityMessage, appLinkData);
            unityMessage.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, intent.getDataString());
        } else if (intent.getData() != null) {
            unityMessage.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, intent.getDataString());
        } else {
            unityMessage.put("did_complete", Boolean.valueOf(true));
        }
        unityMessage.send();
    }

    @UnityCallable
    public static void RefreshCurrentAccessToken(String paramsStr) {
        LogMethodCall("RefreshCurrentAccessToken", paramsStr);
        UnityParams unityParams = UnityParams.parse(paramsStr);
        UnityMessage unityMessage = new UnityMessage("OnRefreshCurrentAccessTokenComplete");
        if (unityParams.hasString(Constants.CALLBACK_ID_KEY).booleanValue()) {
            unityMessage.put(Constants.CALLBACK_ID_KEY, unityParams.getString(Constants.CALLBACK_ID_KEY));
        }
        AccessToken.refreshCurrentAccessTokenAsync(new 3(unityMessage));
        AppLinkData.fetchDeferredAppLinkData(getUnityActivity(), new 4(unityMessage));
    }

    @TargetApi(8)
    public static String getKeyHash() {
        try {
            Activity activity = getUnityActivity();
            if (activity == null) {
                return BuildConfig.FLAVOR;
            }
            Signature[] signatureArr = activity.getPackageManager().getPackageInfo(activity.getPackageName(), 64).signatures;
            if (0 < signatureArr.length) {
                Signature signature = signatureArr[0];
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                String keyHash = Base64.encodeToString(md.digest(), 0);
                Log.d(TAG, "KeyHash: " + keyHash);
                return keyHash;
            }
            return BuildConfig.FLAVOR;
        } catch (NameNotFoundException e) {
        } catch (NoSuchAlgorithmException e2) {
        }
    }

    private static void ActivateApp(String appId) {
        if (activateAppCalled.compareAndSet(false, true)) {
            Activity unityActivity = getUnityActivity();
            if (appId != null) {
                AppEventsLogger.activateApp(unityActivity.getApplication(), appId);
            } else {
                AppEventsLogger.activateApp(unityActivity.getApplication());
            }
            new Handler(Looper.getMainLooper()).post(new 5(unityActivity));
            return;
        }
        Log.w(TAG, "Activite app only needs to be called once");
    }

    private static void startActivity(Class<?> cls, String paramsStr) {
        Intent intent = new Intent(getUnityActivity(), cls);
        intent.putExtra(BaseActivity.ACTIVITY_PARAMS, UnityParams.parse(paramsStr).getStringParams());
        getUnityActivity().startActivity(intent);
    }

    private static void LogMethodCall(String methodName, String paramsStr) {
        Log.v(TAG, String.format(Locale.ROOT, "%s(%s)", new Object[]{methodName, paramsStr}));
    }

    private static void addAppLinkToMessage(UnityMessage unityMessage, AppLinkData appLinkData) {
        if (appLinkData == null) {
            unityMessage.put("did_complete", Boolean.valueOf(true));
            return;
        }
        unityMessage.put("ref", appLinkData.getRef());
        unityMessage.put("target_url", appLinkData.getTargetUri().toString());
        try {
            if (appLinkData.getArgumentBundle() != null) {
                unityMessage.put(AppLinkData.ARGUMENTS_EXTRAS_KEY, BundleJSONConverter.convertToJSON(appLinkData.getArgumentBundle()).toString());
            }
        } catch (JSONException ex) {
            Log.e(TAG, ex.getLocalizedMessage());
        }
    }
}
