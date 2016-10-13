package com.unity3d.ads.android.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import com.applovin.sdk.AppLovinErrorCodes;
import com.facebook.applinks.AppLinkData;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.cache.UnityAdsCache;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.campaign.UnityAdsCampaign.UnityAdsCampaignStatus;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.video.IUnityAdsVideoPlayerListener;
import com.unity3d.ads.android.view.UnityAdsMainView.UnityAdsMainViewState;
import com.unity3d.ads.android.webapp.IUnityAdsWebBridgeListener;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.webapp.UnityAdsWebData.UnityAdsVideoPosition;
import com.unity3d.ads.android.zone.UnityAdsIncentivizedZone;
import com.unity3d.ads.android.zone.UnityAdsZone;
import gs.gram.mopub.BuildConfig;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

@TargetApi(9)
public class UnityAdsFullscreenActivity extends Activity implements IUnityAdsVideoPlayerListener, IUnityAdsWebBridgeListener {
    private boolean _finishOperationsDone;
    private UnityAdsMainView _mainView;
    private int _pausedPosition;
    private Boolean _preventVideoDoubleStart;
    private boolean _rewatch;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            UnityAdsFullscreenActivity.this.finish();
        }
    }

    class UnityAdsPlayVideoRunner implements Runnable {
        private IUnityAdsVideoPlayerListener _listener;

        private UnityAdsPlayVideoRunner() {
            this._listener = null;
        }

        public void setVideoPlayerListener(IUnityAdsVideoPlayerListener iUnityAdsVideoPlayerListener) {
            this._listener = iUnityAdsVideoPlayerListener;
        }

        public void run() {
            UnityAdsDeviceLog.entered();
            if (UnityAdsProperties.SELECTED_CAMPAIGN != null) {
                UnityAdsDeviceLog.debug("Selected campaign found");
                try {
                    String str;
                    new JSONObject().put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_BUFFERING);
                    if (UnityAdsCache.isCampaignCached(UnityAdsProperties.SELECTED_CAMPAIGN)) {
                        str = UnityAdsCache.getCacheDirectory() + "/" + UnityAdsProperties.SELECTED_CAMPAIGN.getVideoFilename();
                        UnityAdsProperties.SELECTED_CAMPAIGN_CACHED = Boolean.valueOf(true);
                    } else {
                        str = UnityAdsProperties.SELECTED_CAMPAIGN.getVideoStreamUrl();
                        UnityAdsProperties.SELECTED_CAMPAIGN_CACHED = Boolean.valueOf(false);
                    }
                    UnityAdsFullscreenActivity.this.getMainView().setViewState(UnityAdsMainViewState.VideoPlayer);
                    UnityAdsFullscreenActivity.this.getMainView().videoplayerview.setListener(this._listener);
                    UnityAdsDeviceLog.debug("Start videoplayback with: " + str);
                    UnityAdsFullscreenActivity.this.getMainView().videoplayerview.playVideo(str, UnityAdsProperties.SELECTED_CAMPAIGN_CACHED.booleanValue());
                    return;
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Couldn't create data JSON");
                    return;
                }
            }
            UnityAdsDeviceLog.error("Campaign is null");
        }
    }

    public UnityAdsFullscreenActivity() {
        this._preventVideoDoubleStart = Boolean.valueOf(false);
        this._mainView = null;
        this._pausedPosition = 0;
        this._rewatch = false;
        this._finishOperationsDone = false;
    }

    private UnityAdsMainView getMainView() {
        return this._mainView;
    }

    private void setupViews() {
        if (getMainView() != null) {
            UnityAdsDeviceLog.debug("View was not destroyed, trying to destroy it");
            this._mainView = null;
        }
        if (getMainView() == null) {
            this._mainView = new UnityAdsMainView((Context) this, (IUnityAdsWebBridgeListener) this);
        }
    }

    private void finishActivity() {
        if (!this._finishOperationsDone) {
            this._finishOperationsDone = true;
            UnityAdsDeviceLog.debug("Running finish operations on Unity Ads activity");
            if (!(UnityAdsWebData.getZoneManager() == null || UnityAdsWebData.getZoneManager().getCurrentZone().openAnimated())) {
                overridePendingTransition(0, 0);
            }
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY, UnityAdsConstants.UNITY_ADS_WEBVIEW_API_CLOSE);
                if (!(getMainView() == null || UnityAdsMainView.webview == null)) {
                    UnityAdsMainView.webview.setWebViewCurrentView(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE, jSONObject);
                }
                UnityAdsViewUtils.removeViewFromParent(UnityAdsMainView.webview);
                if (getMainView() != null) {
                    UnityAdsViewUtils.removeViewFromParent(getMainView());
                    if (getMainView().videoplayerview != null) {
                        getMainView().videoplayerview.clearVideoPlayer();
                    }
                    UnityAdsViewUtils.removeViewFromParent(getMainView().videoplayerview);
                    UnityAdsProperties.SELECTED_CAMPAIGN = null;
                    getMainView().videoplayerview = null;
                }
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onHide();
                }
                if (!UnityAdsWebData.refreshCampaignsIfNeeded()) {
                    ArrayList viewableVideoPlanCampaigns = UnityAdsWebData.getViewableVideoPlanCampaigns();
                    if (viewableVideoPlanCampaigns != null && viewableVideoPlanCampaigns.size() > 0) {
                        UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(0);
                        if (!UnityAdsCache.isCampaignCached(unityAdsCampaign) && unityAdsCampaign.allowCacheVideo().booleanValue()) {
                            UnityAdsCache.cacheCampaign(unityAdsCampaign);
                        }
                    }
                }
            } catch (Exception e) {
            }
        }
    }

    private void changeOrientation() {
        int i = 6;
        if (UnityAdsWebData.getZoneManager() == null) {
            UnityAdsDeviceLog.error("Static state lost, finishing activity");
            finish();
            return;
        }
        if (UnityAdsWebData.getZoneManager().getCurrentZone().useDeviceOrientationForVideo()) {
            i = -1;
        }
        setRequestedOrientation(i);
    }

    public void onCreate(Bundle bundle) {
        UnityAdsDeviceLog.entered();
        super.onCreate(bundle);
        UnityAdsProperties.APPLICATION_CONTEXT = getApplicationContext();
        UnityAds.changeActivity(this);
        if (UnityAdsMainView.webview == null) {
            UnityAdsMainView.initWebView();
        }
        setupViews();
        setContentView(getMainView());
        changeOrientation();
        create();
        this._preventVideoDoubleStart = Boolean.valueOf(false);
    }

    private void create() {
        Boolean bool;
        Boolean valueOf = Boolean.valueOf(true);
        JSONObject jSONObject = new JSONObject();
        try {
            UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY, UnityAdsConstants.UNITY_ADS_WEBVIEW_API_OPEN);
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ZONE_KEY, currentZone.getZoneId());
            if (currentZone.isIncentivized()) {
                jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_REWARD_ITEM_KEY, ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey());
            }
            bool = valueOf;
        } catch (Exception e) {
            bool = Boolean.valueOf(false);
        }
        if (bool.booleanValue()) {
            UnityAdsDeviceLog.debug("Setting up WebView with view:none and data:" + jSONObject.toString());
            if (getMainView() != null) {
                UnityAdsMainView.webview.setWebViewCurrentView(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE, jSONObject);
                getMainView().setViewState(UnityAdsMainViewState.WebView);
                if (UnityAdsWebData.getZoneManager().getCurrentZone().noOfferScreen()) {
                    playVideo(false);
                }
                if (UnityAds.getListener() != null) {
                    UnityAds.getListener().onShow();
                    return;
                }
                return;
            }
            UnityAdsDeviceLog.error("mainview null after open, closing");
            finish();
        }
    }

    public void onStart() {
        UnityAdsDeviceLog.entered();
        super.onStart();
    }

    public void onRestart() {
        UnityAdsDeviceLog.entered();
        super.onRestart();
    }

    public void onResume() {
        super.onResume();
        UnityAdsDeviceLog.entered();
        resumeVideo();
    }

    private void resumeVideo() {
        if (this._pausedPosition > 0) {
            try {
                getMainView().videoplayerview.seekTo(this._pausedPosition);
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Unexpected error while seeking video");
            }
            this._pausedPosition = 0;
        }
    }

    public void onPause() {
        UnityAdsDeviceLog.entered();
        pauseVideo();
        if (isFinishing()) {
            finishActivity();
        }
        super.onPause();
    }

    public void onStop() {
        UnityAdsDeviceLog.entered();
        super.onStop();
    }

    protected void onDestroy() {
        UnityAdsDeviceLog.entered();
        if (isFinishing()) {
            finishActivity();
        }
        super.onDestroy();
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        switch (i) {
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                UnityAdsDeviceLog.entered();
                if (getMainView().videoplayerview != null) {
                    UnityAdsDeviceLog.debug("Seconds: " + getMainView().videoplayerview.getSecondsUntilBackButtonAllowed());
                }
                if (UnityAdsProperties.SELECTED_CAMPAIGN != null && UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
                    finish();
                } else if (getMainView().getViewState() != UnityAdsMainViewState.VideoPlayer) {
                    finish();
                } else if (getMainView().videoplayerview != null && getMainView().videoplayerview.getSecondsUntilBackButtonAllowed() == 0) {
                    finish();
                } else if (UnityAdsWebData.getZoneManager().getCurrentZone().disableBackButtonForSeconds() == 0) {
                    finish();
                } else {
                    UnityAdsDeviceLog.debug("Prevented back-button");
                }
                return true;
            default:
                return false;
        }
    }

    public void onPlayVideo(JSONObject jSONObject) {
        UnityAdsDeviceLog.entered();
        if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY)) {
            String str = null;
            try {
                str = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Could not get campaignId");
            }
            if (str != null) {
                if (UnityAdsWebData.getCampaignById(str) != null) {
                    UnityAdsProperties.SELECTED_CAMPAIGN = UnityAdsWebData.getCampaignById(str);
                }
                if (UnityAdsProperties.SELECTED_CAMPAIGN != null && UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId() != null && UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId().equals(str)) {
                    Boolean valueOf = Boolean.valueOf(false);
                    try {
                        valueOf = Boolean.valueOf(jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_REWATCH_KEY));
                    } catch (Exception e2) {
                        UnityAdsDeviceLog.debug("Couldn't get rewatch property");
                    }
                    if (valueOf.booleanValue()) {
                        this._pausedPosition = 0;
                    }
                    UnityAdsDeviceLog.debug("Selected campaign=" + UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId() + " isViewed: " + UnityAdsProperties.SELECTED_CAMPAIGN.isViewed());
                    if (UnityAdsProperties.SELECTED_CAMPAIGN == null) {
                        return;
                    }
                    if (valueOf.booleanValue() || !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
                        if (valueOf.booleanValue()) {
                            this._preventVideoDoubleStart = Boolean.valueOf(false);
                        }
                        playVideo(valueOf.booleanValue());
                    }
                }
            }
        }
    }

    public void onPauseVideo(JSONObject jSONObject) {
        String str = "WebView requested PauseVideo";
        if (jSONObject != null) {
            str = str + " " + jSONObject.toString();
        }
        UnityAdsDeviceLog.debug(str);
        pauseVideo();
    }

    private void pauseVideo() {
        if (getMainView() != null && getMainView().videoplayerview != null && getMainView().videoplayerview.isPlaying()) {
            this._pausedPosition = getMainView().videoplayerview.getCurrentPosition() + AppLovinErrorCodes.INCENTIVIZED_SERVER_TIMEOUT;
            if (this._pausedPosition < 0) {
                this._pausedPosition = 0;
            }
            getMainView().videoplayerview.pauseVideo();
        }
    }

    private void resetPausedPosition() {
        this._pausedPosition = 0;
    }

    public void onCloseAdsView(JSONObject jSONObject) {
        String str = "WebView requested CloseAdsView";
        if (jSONObject != null) {
            str = str + " " + jSONObject.toString();
        }
        UnityAdsDeviceLog.debug(str);
        UnityAdsUtils.runOnUiThread(new 1());
    }

    public void onWebAppLoadComplete(JSONObject jSONObject) {
        String str = "WebView reported WebAppLoadComplete";
        if (jSONObject != null) {
            str = str + " " + jSONObject.toString();
        }
        UnityAdsDeviceLog.debug(str);
        UnityAdsDeviceLog.entered();
    }

    public void onWebAppInitComplete(JSONObject jSONObject) {
        UnityAdsDeviceLog.entered();
    }

    public void onOrientationRequest(JSONObject jSONObject) {
        setRequestedOrientation(jSONObject.optInt("orientation", -1));
    }

    public void onLaunchIntent(JSONObject jSONObject) {
        try {
            Intent parseLaunchIntent = parseLaunchIntent(jSONObject);
            if (parseLaunchIntent == null) {
                UnityAdsDeviceLog.error("No suitable intent to launch");
                UnityAdsDeviceLog.debug("Intent JSON: " + jSONObject.toString());
                return;
            }
            startActivity(parseLaunchIntent);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Failed to launch intent: " + e.getMessage());
        }
    }

    private Intent parseLaunchIntent(JSONObject jSONObject) {
        try {
            if (!jSONObject.has("packageName") || jSONObject.has("className") || jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY) || jSONObject.has("mimeType")) {
                JSONArray jSONArray;
                int i;
                Intent intent = new Intent();
                if (jSONObject.has("className") && jSONObject.has("packageName")) {
                    intent.setClassName(jSONObject.getString("packageName"), jSONObject.getString("className"));
                }
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY)) {
                    intent.setAction(jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_API_ACTION_KEY));
                }
                if (jSONObject.has(ShareConstants.MEDIA_URI)) {
                    intent.setData(Uri.parse(jSONObject.getString(ShareConstants.MEDIA_URI)));
                }
                if (jSONObject.has("mimeType")) {
                    intent.setType(jSONObject.getString("mimeType"));
                }
                if (jSONObject.has("categories")) {
                    jSONArray = jSONObject.getJSONArray("categories");
                    if (jSONArray.length() > 0) {
                        for (i = 0; i < jSONArray.length(); i++) {
                            intent.addCategory(jSONArray.getString(i));
                        }
                    }
                }
                if (jSONObject.has("flags")) {
                    intent.setFlags(jSONObject.getInt("flags"));
                }
                if (jSONObject.has(AppLinkData.ARGUMENTS_EXTRAS_KEY)) {
                    jSONArray = jSONObject.getJSONArray(AppLinkData.ARGUMENTS_EXTRAS_KEY);
                    for (i = 0; i < jSONArray.length(); i++) {
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String string = jSONObject2.getString(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
                        Object obj = jSONObject2.get("value");
                        if (obj instanceof String) {
                            intent.putExtra(string, (String) obj);
                        } else if (obj instanceof Integer) {
                            intent.putExtra(string, ((Integer) obj).intValue());
                        } else if (obj instanceof Double) {
                            intent.putExtra(string, ((Double) obj).doubleValue());
                        } else if (obj instanceof Boolean) {
                            intent.putExtra(string, ((Boolean) obj).booleanValue());
                        } else {
                            UnityAdsDeviceLog.error("Unable to parse launch intent extra " + string);
                        }
                    }
                }
                return intent;
            }
            Intent launchIntentForPackage = getPackageManager().getLaunchIntentForPackage(jSONObject.getString("packageName"));
            if (launchIntentForPackage == null || !jSONObject.has("flags")) {
                return launchIntentForPackage;
            }
            launchIntentForPackage.addFlags(jSONObject.getInt("flags"));
            return launchIntentForPackage;
        } catch (JSONException e) {
            UnityAdsDeviceLog.error("Exception while parsing intent json: " + e.getMessage());
            return null;
        }
    }

    public void onOpenPlayStore(JSONObject jSONObject) {
        String str = null;
        UnityAdsDeviceLog.entered();
        if (jSONObject != null) {
            String string;
            UnityAdsDeviceLog.debug(jSONObject.toString());
            Boolean valueOf = Boolean.valueOf(false);
            if (jSONObject.has(UnityAdsConstants.UNITY_ADS_PLAYSTORE_ITUNESID_KEY)) {
                try {
                    string = jSONObject.getString(UnityAdsConstants.UNITY_ADS_PLAYSTORE_ITUNESID_KEY);
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Could not fetch playStoreId");
                }
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY)) {
                    try {
                        str = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY);
                    } catch (Exception e2) {
                        UnityAdsDeviceLog.error("Could not fetch clickUrl");
                    }
                }
                if (jSONObject.has(UnityAdsConstants.UNITY_ADS_PLAYSTORE_BYPASSAPPSHEET_KEY)) {
                    try {
                        valueOf = Boolean.valueOf(jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_PLAYSTORE_BYPASSAPPSHEET_KEY));
                    } catch (Exception e3) {
                        UnityAdsDeviceLog.error("Could not fetch bypassAppSheet");
                    }
                }
                if (string == null && !r2.booleanValue()) {
                    openPlayStoreAsIntent(string);
                    return;
                } else if (str != null) {
                    openPlayStoreInBrowser(str);
                }
            }
            string = str;
            if (jSONObject.has(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY)) {
                str = jSONObject.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY);
            }
            if (jSONObject.has(UnityAdsConstants.UNITY_ADS_PLAYSTORE_BYPASSAPPSHEET_KEY)) {
                valueOf = Boolean.valueOf(jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_PLAYSTORE_BYPASSAPPSHEET_KEY));
            }
            if (string == null) {
            }
            if (str != null) {
                openPlayStoreInBrowser(str);
            }
        }
    }

    private void openPlayStoreAsIntent(String str) {
        UnityAdsDeviceLog.debug("Opening playstore activity with storeId: " + str);
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=" + str)));
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Couldn't create PlayStore intent!");
        }
    }

    private void openPlayStoreInBrowser(String str) {
        UnityAdsDeviceLog.debug("Opening playStore in browser: " + str);
        try {
            startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Couldn't create browser intent!");
        }
    }

    private void playVideo(boolean z) {
        if (this._preventVideoDoubleStart.booleanValue()) {
            UnityAdsDeviceLog.debug("Prevent double create of video playback");
            return;
        }
        this._preventVideoDoubleStart = Boolean.valueOf(true);
        this._rewatch = z;
        UnityAdsDeviceLog.debug("Running threaded");
        Runnable unityAdsPlayVideoRunner = new UnityAdsPlayVideoRunner();
        unityAdsPlayVideoRunner.setVideoPlayerListener(this);
        UnityAdsUtils.runOnUiThread(unityAdsPlayVideoRunner);
    }

    private void finishPlayback() {
        if (getMainView().videoplayerview != null) {
            getMainView().videoplayerview.setKeepScreenOn(false);
        }
        this._pausedPosition = 0;
        getMainView().destroyVideoPlayerView();
        getMainView().setViewState(UnityAdsMainViewState.WebView);
        setRequestedOrientation(-1);
    }

    public void onVideoPlaybackStarted() {
        UnityAdsDeviceLog.entered();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        if (!(UnityAds.getListener() == null || this._rewatch)) {
            UnityAds.getListener().onVideoStarted();
        }
        ArrayList viewableVideoPlanCampaigns = UnityAdsWebData.getViewableVideoPlanCampaigns();
        if (viewableVideoPlanCampaigns.size() > 1) {
            UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) viewableVideoPlanCampaigns.get(1);
            if (UnityAdsCache.isCampaignCached(UnityAdsProperties.SELECTED_CAMPAIGN) && !UnityAdsCache.isCampaignCached(unityAdsCampaign) && unityAdsCampaign.allowCacheVideo().booleanValue()) {
                UnityAdsCache.cacheCampaign(unityAdsCampaign);
            }
        }
        getMainView().bringChildToFront(getMainView().videoplayerview);
        changeOrientation();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e2) {
            UnityAdsDeviceLog.debug("Could not set campaign");
        }
        if (UnityAdsMainView.webview != null) {
            UnityAdsMainView.webview.setWebViewCurrentView(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_COMPLETED, jSONObject);
        }
    }

    public void onEventPositionReached(UnityAdsVideoPosition unityAdsVideoPosition) {
        if (UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignStatus().equals(UnityAdsCampaignStatus.VIEWED) && !UnityAdsWebData.sendCampaignViewProgress(UnityAdsProperties.SELECTED_CAMPAIGN, unityAdsVideoPosition)) {
            UnityAdsDeviceLog.debug("Sending campaign view progress failed!");
        }
    }

    public void onCompletion(MediaPlayer mediaPlayer) {
        UnityAdsDeviceLog.entered();
        finishPlayback();
        onEventPositionReached(UnityAdsVideoPosition.End);
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        UnityAdsMainView.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject);
        UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT++;
        if (UnityAds.getListener() != null && UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
            UnityAdsDeviceLog.info("Unity Ads video completed");
            UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaignStatus.VIEWED);
            UnityAds.getListener().onVideoCompleted(getRewardItemKey(), false);
        }
    }

    public void onVideoPlaybackError() {
        finishPlayback();
        UnityAdsDeviceLog.entered();
        UnityAdsWebData.sendAnalyticsRequest(UnityAdsConstants.UNITY_ADS_ANALYTICS_EVENTTYPE_VIDEOERROR, UnityAdsProperties.SELECTED_CAMPAIGN);
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_VIDEOPLAYBACKERROR);
            jSONObject2.put(UnityAdsConstants.UNITY_ADS_TEXTKEY_KEY, UnityAdsConstants.UNITY_ADS_TEXTKEY_BUFFERING);
            jSONObject3.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        if (UnityAdsMainView.webview != null) {
            UnityAdsMainView.webview.setWebViewCurrentView(UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_COMPLETED, jSONObject3);
            UnityAdsMainView.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_SHOWERROR, jSONObject);
            UnityAdsMainView.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject3);
        }
        if (UnityAdsProperties.SELECTED_CAMPAIGN != null) {
            UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaignStatus.VIEWED);
            UnityAdsProperties.SELECTED_CAMPAIGN = null;
        }
    }

    public void onVideoSkip() {
        finishPlayback();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CAMPAIGNID_KEY, UnityAdsProperties.SELECTED_CAMPAIGN.getCampaignId());
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not create JSON");
        }
        UnityAdsMainView.webview.sendNativeEventToWebApp(UnityAdsConstants.UNITY_ADS_NATIVEEVENT_VIDEOCOMPLETED, jSONObject);
        UnityAdsProperties.CAMPAIGN_REFRESH_VIEWS_COUNT++;
        if (UnityAds.getListener() != null && UnityAdsProperties.SELECTED_CAMPAIGN != null && !UnityAdsProperties.SELECTED_CAMPAIGN.isViewed().booleanValue()) {
            UnityAdsDeviceLog.info("Unity Ads video skipped");
            UnityAdsProperties.SELECTED_CAMPAIGN.setCampaignStatus(UnityAdsCampaignStatus.VIEWED);
            UnityAds.getListener().onVideoCompleted(getRewardItemKey(), true);
        }
    }

    private static String getRewardItemKey() {
        String str;
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone == null || !currentZone.isIncentivized()) {
            str = null;
        } else {
            str = ((UnityAdsIncentivizedZone) currentZone).itemManager().getCurrentItem().getKey();
        }
        return str == null ? BuildConfig.FLAVOR : str;
    }
}
