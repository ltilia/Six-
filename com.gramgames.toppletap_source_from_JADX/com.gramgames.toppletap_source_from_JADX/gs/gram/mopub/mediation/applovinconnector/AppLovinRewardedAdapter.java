package gs.gram.mopub.mediation.applovinconnector;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.applovin.adview.AppLovinIncentivizedInterstitial;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinEventParameters;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.mopub.mobileads.CustomEventRewardedVideo;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.mobileads.MoPubRewardedVideoManager;
import gs.gram.mopub.BuildConfig;
import java.util.Map;

public class AppLovinRewardedAdapter extends CustomEventRewardedVideo implements AppLovinAdLoadListener, AppLovinAdClickListener, AppLovinAdDisplayListener, AppLovinAdRewardListener {
    private static volatile boolean adReady = false;
    private static volatile boolean initialized = false;
    private static final boolean loggingEnabled = true;
    private AppLovinIncentivizedInterstitial mIncent;
    private Activity parentActivity;

    static {
        adReady = false;
        initialized = false;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        if (initialized) {
            return false;
        }
        ALLog("Initializing AppLovin SDK for rewarded video.");
        initialized = loggingEnabled;
        return loggingEnabled;
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    @NonNull
    protected String getAdNetworkId() {
        return BuildConfig.FLAVOR;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        ALLog("Loading rewarded video.");
        this.parentActivity = activity;
        this.mIncent = AppLovinIncentivizedInterstitial.create((Context) activity);
        this.mIncent.preload(this);
    }

    protected void onInvalidate() {
    }

    @Nullable
    protected CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return null;
    }

    protected boolean hasVideoAvailable() {
        return adReady;
    }

    protected void showVideo() {
        if (hasVideoAvailable()) {
            ALLog("Showing rewarded video.");
            this.mIncent.show(this.parentActivity, this, null, this, this);
            adReady = false;
            return;
        }
        ALLog("No rewarded video available to show.");
        MoPubRewardedVideoManager.onRewardedVideoPlaybackError(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, MoPubErrorCode.VIDEO_PLAYBACK_ERROR);
    }

    public void adReceived(AppLovinAd ad) {
        ALLog("Rewarded video loaded.");
        adReady = loggingEnabled;
        MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR);
    }

    public void failedToReceiveAd(int errorCode) {
        MoPubErrorCode error;
        ALLog("Rewarded video failed to load: " + errorCode);
        switch (errorCode) {
            case AppLovinErrorCodes.NO_FILL /*204*/:
                error = MoPubErrorCode.NO_FILL;
                break;
            default:
                error = MoPubErrorCode.INTERNAL_ERROR;
                break;
        }
        MoPubRewardedVideoManager.onRewardedVideoLoadFailure(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, error);
    }

    public void adClicked(AppLovinAd ad) {
        ALLog("Rewarded video clicked.");
        MoPubRewardedVideoManager.onRewardedVideoClicked(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR);
    }

    public void adHidden(AppLovinAd ad) {
        ALLog("Ad hidden.");
        MoPubRewardedVideoManager.onRewardedVideoClosed(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR);
    }

    public void adDisplayed(AppLovinAd ad) {
        ALLog("Ad displayed.");
        MoPubRewardedVideoManager.onRewardedVideoStarted(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR);
    }

    public void userRewardVerified(AppLovinAd appLovinAd, Map map) {
        ALLog("Granting reward.");
        MoPubRewardedVideoManager.onRewardedVideoCompleted(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, MoPubReward.success((String) map.get(AppLovinEventParameters.REVENUE_CURRENCY), (int) Double.parseDouble((String) map.get(AppLovinEventParameters.REVENUE_AMOUNT))));
    }

    public void userOverQuota(AppLovinAd appLovinAd, Map map) {
        ALLog("User over quota.");
        MoPubRewardedVideoManager.onRewardedVideoCompleted(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, MoPubReward.failure());
    }

    public void userRewardRejected(AppLovinAd appLovinAd, Map map) {
        ALLog("Reward rejected by AppLovin.");
        MoPubRewardedVideoManager.onRewardedVideoCompleted(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, MoPubReward.failure());
    }

    public void validationRequestFailed(AppLovinAd appLovinAd, int i) {
        ALLog("Validation request to server failed or user closed ad early.");
        MoPubRewardedVideoManager.onRewardedVideoCompleted(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR, MoPubReward.failure());
    }

    public void userDeclinedToViewAd(AppLovinAd appLovinAd) {
        ALLog("User declined to view ad.");
        adReady = loggingEnabled;
        MoPubRewardedVideoManager.onRewardedVideoClosed(AppLovinRewardedAdapter.class, BuildConfig.FLAVOR);
    }

    private void ALLog(String logMessage) {
        Log.d("AppLovinAdapter", logMessage);
    }
}
