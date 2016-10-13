package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.BaseLifecycleListener;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.logging.MoPubLog;
import com.vungle.publisher.AdConfig;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;
import gs.gram.mopub.BuildConfig;
import java.util.Locale;
import java.util.Map;

public class VungleRewardedVideo extends CustomEventRewardedVideo {
    public static final String APP_ID_KEY = "appId";
    private static final String DEFAULT_VUNGLE_APP_ID = "YOUR_DEFAULT_VUNGLE_APP_ID";
    public static final String VUNGLE_AD_NETWORK_CONSTANT = "vngl_id";
    private static boolean sInitialized;
    private static final LifecycleListener sLifecycleListener;
    private static VungleRewardedVideoListener sVungleListener;
    private static VunglePub sVunglePub;
    private String mAdUnitId;
    private final Handler mHandler;
    private boolean mIsLoading;

    static class 1 extends BaseLifecycleListener {
        1() {
        }

        public void onPause(@NonNull Activity activity) {
            super.onPause(activity);
            VungleRewardedVideo.sVunglePub.onPause();
        }

        public void onResume(@NonNull Activity activity) {
            super.onResume(activity);
            VungleRewardedVideo.sVunglePub.onResume();
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT);
        }
    }

    public static class VungleMediationSettings implements MediationSettings {
        @Nullable
        private final String body;
        @Nullable
        private final String closeButtonText;
        @Nullable
        private final String keepWatchingButtonText;
        @Nullable
        private final String title;
        @Nullable
        private final String userId;

        public static class Builder {
            @Nullable
            private String body;
            @Nullable
            private String closeButtonText;
            @Nullable
            private String keepWatchingButtonText;
            @Nullable
            private String title;
            @Nullable
            private String userId;

            public Builder withUserId(@NonNull String userId) {
                this.userId = userId;
                return this;
            }

            public Builder withCancelDialogTitle(@NonNull String title) {
                this.title = title;
                return this;
            }

            public Builder withCancelDialogBody(@NonNull String body) {
                this.body = body;
                return this;
            }

            public Builder withCancelDialogCloseButton(@NonNull String buttonText) {
                this.closeButtonText = buttonText;
                return this;
            }

            public Builder withCancelDialogKeepWatchingButton(@NonNull String buttonText) {
                this.keepWatchingButtonText = buttonText;
                return this;
            }

            public VungleMediationSettings build() {
                return new VungleMediationSettings();
            }
        }

        private VungleMediationSettings(@NonNull Builder builder) {
            this.userId = builder.userId;
            this.title = builder.title;
            this.body = builder.body;
            this.closeButtonText = builder.closeButtonText;
            this.keepWatchingButtonText = builder.keepWatchingButtonText;
        }
    }

    private class VungleRewardedVideoListener implements EventListener, CustomEventRewardedVideoListener {
        private VungleRewardedVideoListener() {
        }

        public void onAdEnd(boolean wasCallToActionClicked) {
            if (wasCallToActionClicked) {
                MoPubRewardedVideoManager.onRewardedVideoClicked(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT);
            }
            MoPubRewardedVideoManager.onRewardedVideoClosed(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT);
        }

        public void onAdStart() {
            MoPubRewardedVideoManager.onRewardedVideoStarted(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT);
        }

        public void onAdUnavailable(String s) {
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT, MoPubErrorCode.NETWORK_NO_FILL);
        }

        public void onAdPlayableChanged(boolean playable) {
            if (VungleRewardedVideo.this.mIsLoading && playable) {
                VungleRewardedVideo.this.notifyAdAvailable();
            }
        }

        public void onVideoView(boolean isCompletedView, int watchedMillis, int videoMillis) {
            MoPubLog.d(String.format(Locale.US, "%.1f%% of Vungle video watched.", new Object[]{Double.valueOf((((double) watchedMillis) / ((double) videoMillis)) * 100.0d)}));
            if (isCompletedView) {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(VungleRewardedVideo.class, VungleRewardedVideo.VUNGLE_AD_NETWORK_CONSTANT, MoPubReward.success(BuildConfig.FLAVOR, MoPubReward.NO_REWARD_AMOUNT));
            }
        }
    }

    static {
        sLifecycleListener = new 1();
    }

    public VungleRewardedVideo() {
        sVungleListener = new VungleRewardedVideoListener();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mIsLoading = false;
    }

    @Nullable
    public CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return sVungleListener;
    }

    @Nullable
    public LifecycleListener getLifecycleListener() {
        return sLifecycleListener;
    }

    @NonNull
    protected String getAdNetworkId() {
        return VUNGLE_AD_NETWORK_CONSTANT;
    }

    protected boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> map2) throws Exception {
        boolean z = true;
        synchronized (VungleRewardedVideo.class) {
            if (sInitialized) {
                z = false;
            } else {
                sVunglePub = VunglePub.getInstance();
                sInitialized = true;
            }
        }
        return z;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        sVunglePub.init(activity, serverExtras.containsKey(APP_ID_KEY) ? (String) serverExtras.get(APP_ID_KEY) : DEFAULT_VUNGLE_APP_ID);
        sVunglePub.setEventListeners(sVungleListener);
        Object adUnitObject = localExtras.get(DataKeys.AD_UNIT_ID_KEY);
        if (adUnitObject instanceof String) {
            this.mAdUnitId = (String) adUnitObject;
        }
        if (sVunglePub.isAdPlayable()) {
            notifyAdAvailable();
        } else {
            this.mIsLoading = true;
        }
    }

    protected boolean hasVideoAvailable() {
        return sVunglePub.isAdPlayable();
    }

    protected void showVideo() {
        AdConfig adConfig = new AdConfig();
        adConfig.setIncentivized(true);
        setUpMediationSettingsForRequest(adConfig);
        sVunglePub.playAd(adConfig);
    }

    private void setUpMediationSettingsForRequest(AdConfig adConfig) {
        VungleMediationSettings globalMediationSettings = (VungleMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(VungleMediationSettings.class);
        VungleMediationSettings instanceMediationSettings = (VungleMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(VungleMediationSettings.class, this.mAdUnitId);
        if (instanceMediationSettings != null) {
            modifyAdConfig(adConfig, instanceMediationSettings);
        } else if (globalMediationSettings != null) {
            modifyAdConfig(adConfig, globalMediationSettings);
        }
    }

    private void modifyAdConfig(AdConfig adConfig, VungleMediationSettings mediationSettings) {
        if (!TextUtils.isEmpty(mediationSettings.body)) {
            adConfig.setIncentivizedCancelDialogBodyText(mediationSettings.body);
        }
        if (!TextUtils.isEmpty(mediationSettings.closeButtonText)) {
            adConfig.setIncentivizedCancelDialogCloseButtonText(mediationSettings.closeButtonText);
        }
        if (!TextUtils.isEmpty(mediationSettings.keepWatchingButtonText)) {
            adConfig.setIncentivizedCancelDialogKeepWatchingButtonText(mediationSettings.keepWatchingButtonText);
        }
        if (!TextUtils.isEmpty(mediationSettings.title)) {
            adConfig.setIncentivizedCancelDialogTitle(mediationSettings.title);
        }
        if (!TextUtils.isEmpty(mediationSettings.userId)) {
            adConfig.setIncentivizedUserId(mediationSettings.userId);
        }
    }

    private void notifyAdAvailable() {
        MoPubLog.d("Vungle rewarded video ad successfully loaded.");
        this.mIsLoading = false;
        this.mHandler.post(new 2());
    }

    protected void onInvalidate() {
        this.mIsLoading = false;
    }
}
