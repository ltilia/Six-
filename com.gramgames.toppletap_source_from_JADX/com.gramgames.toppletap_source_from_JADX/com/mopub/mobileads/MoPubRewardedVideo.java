package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import gs.gram.mopub.BuildConfig;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class MoPubRewardedVideo extends CustomEventRewardedVideo {
    @NonNull
    private static final String MOPUB_REWARDED_VIDEO_ID = "mopub_rewarded_video_id";
    private boolean mIsLoaded;
    @NonNull
    private RewardedVastVideoInterstitial mRewardedVastVideoInterstitial;
    private int mRewardedVideoCurrencyAmount;
    @Nullable
    private String mRewardedVideoCurrencyName;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubErrorCode;

        static {
            $SwitchMap$com$mopub$mobileads$MoPubErrorCode = new int[MoPubErrorCode.values().length];
            try {
                $SwitchMap$com$mopub$mobileads$MoPubErrorCode[MoPubErrorCode.VIDEO_PLAYBACK_ERROR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private class MoPubRewardedVideoListener implements CustomEventInterstitialListener, CustomEventRewardedVideoInterstitialListener {
        private MoPubRewardedVideoListener() {
        }

        public void onInterstitialLoaded() {
            MoPubRewardedVideo.this.mIsLoaded = true;
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID);
        }

        public void onInterstitialFailed(MoPubErrorCode errorCode) {
            switch (1.$SwitchMap$com$mopub$mobileads$MoPubErrorCode[errorCode.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    MoPubRewardedVideoManager.onRewardedVideoPlaybackError(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID, errorCode);
                default:
                    MoPubRewardedVideoManager.onRewardedVideoLoadFailure(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID, errorCode);
            }
        }

        public void onInterstitialShown() {
            MoPubRewardedVideoManager.onRewardedVideoStarted(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID);
        }

        public void onInterstitialClicked() {
            MoPubRewardedVideoManager.onRewardedVideoClicked(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID);
        }

        public void onLeaveApplication() {
        }

        public void onInterstitialDismissed() {
            MoPubRewardedVideoManager.onRewardedVideoClosed(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID);
        }

        public void onVideoComplete() {
            if (MoPubRewardedVideo.this.mRewardedVideoCurrencyName == null) {
                MoPubLog.d("No rewarded video was loaded, so no reward is possible");
            } else {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(MoPubRewardedVideo.class, MoPubRewardedVideo.MOPUB_REWARDED_VIDEO_ID, MoPubReward.success(MoPubRewardedVideo.this.mRewardedVideoCurrencyName, MoPubRewardedVideo.this.mRewardedVideoCurrencyAmount));
            }
        }
    }

    public MoPubRewardedVideo() {
        this.mRewardedVastVideoInterstitial = new RewardedVastVideoInterstitial();
    }

    @Nullable
    protected CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return null;
    }

    @Nullable
    protected LifecycleListener getLifecycleListener() {
        return null;
    }

    @NonNull
    protected String getAdNetworkId() {
        return MOPUB_REWARDED_VIDEO_ID;
    }

    protected void onInvalidate() {
        this.mRewardedVastVideoInterstitial.onInvalidate();
        this.mIsLoaded = false;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        return false;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        Preconditions.checkNotNull(activity, "activity cannot be null");
        Preconditions.checkNotNull(localExtras, "localExtras cannot be null");
        Preconditions.checkNotNull(serverExtras, "serverExtras cannot be null");
        Object rewardedVideoCurrencyName = localExtras.get(DataKeys.REWARDED_VIDEO_CURRENCY_NAME_KEY);
        if (rewardedVideoCurrencyName instanceof String) {
            this.mRewardedVideoCurrencyName = (String) rewardedVideoCurrencyName;
        } else {
            MoPubLog.d("No currency name specified for rewarded video. Using the default name.");
            this.mRewardedVideoCurrencyName = BuildConfig.FLAVOR;
        }
        Object rewardedVideoCurrencyAmount = localExtras.get(DataKeys.REWARDED_VIDEO_CURRENCY_AMOUNT_STRING_KEY);
        if (rewardedVideoCurrencyAmount instanceof String) {
            try {
                this.mRewardedVideoCurrencyAmount = Integer.parseInt((String) rewardedVideoCurrencyAmount);
            } catch (NumberFormatException e) {
                MoPubLog.d("Unable to convert currency amount: " + rewardedVideoCurrencyAmount + ". Using the default reward amount: " + 0);
                this.mRewardedVideoCurrencyAmount = 0;
            }
        } else {
            MoPubLog.d("No currency amount specified for rewarded video. Using the default reward amount: 0");
            this.mRewardedVideoCurrencyAmount = 0;
        }
        if (this.mRewardedVideoCurrencyAmount < 0) {
            MoPubLog.d("Negative currency amount specified for rewarded video. Using the default reward amount: 0");
            this.mRewardedVideoCurrencyAmount = 0;
        }
        this.mRewardedVastVideoInterstitial.loadInterstitial(activity, new MoPubRewardedVideoListener(), localExtras, serverExtras);
    }

    protected boolean hasVideoAvailable() {
        return this.mIsLoaded;
    }

    protected void showVideo() {
        if (hasVideoAvailable()) {
            MoPubLog.d("Showing MoPub rewarded video.");
            this.mRewardedVastVideoInterstitial.showInterstitial();
            return;
        }
        MoPubLog.d("Unable to show MoPub rewarded video");
    }

    @Deprecated
    @VisibleForTesting
    void setRewardedVastVideoInterstitial(@NonNull RewardedVastVideoInterstitial rewardedVastVideoInterstitial) {
        this.mRewardedVastVideoInterstitial = rewardedVastVideoInterstitial;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    String getRewardedVideoCurrencyName() {
        return this.mRewardedVideoCurrencyName;
    }

    @Deprecated
    @VisibleForTesting
    int getRewardedVideoCurrencyAmount() {
        return this.mRewardedVideoCurrencyAmount;
    }

    @Deprecated
    @VisibleForTesting
    void setIsLoaded(boolean isLoaded) {
        this.mIsLoaded = isLoaded;
    }
}
