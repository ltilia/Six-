package com.mopub.mobileads;

import android.app.Activity;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.chartboost.sdk.Chartboost;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.logging.MoPubLog;
import java.util.Map;

public class ChartboostRewardedVideo extends CustomEventRewardedVideo {
    @NonNull
    private static final LifecycleListener sLifecycleListener;
    @NonNull
    private final Handler mHandler;
    @NonNull
    private String mLocation;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            if (Chartboost.hasRewardedVideo(ChartboostRewardedVideo.this.mLocation)) {
                ChartboostShared.getDelegate().didCacheRewardedVideo(ChartboostRewardedVideo.this.mLocation);
            } else {
                Chartboost.cacheRewardedVideo(ChartboostRewardedVideo.this.mLocation);
            }
        }
    }

    private static final class ChartboostLifecycleListener implements LifecycleListener {
        private ChartboostLifecycleListener() {
        }

        public void onCreate(@NonNull Activity activity) {
            Chartboost.onCreate(activity);
        }

        public void onStart(@NonNull Activity activity) {
            Chartboost.onStart(activity);
        }

        public void onPause(@NonNull Activity activity) {
            Chartboost.onPause(activity);
        }

        public void onResume(@NonNull Activity activity) {
            Chartboost.onResume(activity);
        }

        public void onRestart(@NonNull Activity activity) {
        }

        public void onStop(@NonNull Activity activity) {
            Chartboost.onStop(activity);
        }

        public void onDestroy(@NonNull Activity activity) {
            Chartboost.onDestroy(activity);
        }

        public void onBackPressed(@NonNull Activity activity) {
            Chartboost.onBackPressed();
        }
    }

    public static final class ChartboostMediationSettings implements MediationSettings {
        @NonNull
        private final String mCustomId;

        public ChartboostMediationSettings(@NonNull String customId) {
            this.mCustomId = customId;
        }

        @NonNull
        public String getCustomId() {
            return this.mCustomId;
        }
    }

    static {
        sLifecycleListener = new ChartboostLifecycleListener();
    }

    public ChartboostRewardedVideo() {
        this.mLocation = ChartboostShared.LOCATION_DEFAULT;
        this.mHandler = new Handler();
    }

    @NonNull
    public CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return ChartboostShared.getDelegate();
    }

    @NonNull
    public LifecycleListener getLifecycleListener() {
        return sLifecycleListener;
    }

    @NonNull
    public String getAdNetworkId() {
        return this.mLocation;
    }

    public boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> serverExtras) throws Exception {
        ChartboostShared.initializeSdk(launcherActivity, serverExtras);
        return true;
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        if (serverExtras.containsKey(GooglePlayServicesInterstitial.LOCATION_KEY)) {
            String location = (String) serverExtras.get(GooglePlayServicesInterstitial.LOCATION_KEY);
            if (TextUtils.isEmpty(location)) {
                location = this.mLocation;
            }
            this.mLocation = location;
        }
        ChartboostShared.getDelegate().registerRewardedVideoLocation(this.mLocation);
        setUpMediationSettingsForRequest((String) localExtras.get(DataKeys.AD_UNIT_ID_KEY));
        this.mHandler.post(new 1());
    }

    private void setUpMediationSettingsForRequest(String moPubId) {
        ChartboostMediationSettings globalSettings = (ChartboostMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(ChartboostMediationSettings.class);
        ChartboostMediationSettings instanceSettings = (ChartboostMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(ChartboostMediationSettings.class, moPubId);
        if (instanceSettings != null) {
            Chartboost.setCustomId(instanceSettings.getCustomId());
        } else if (globalSettings != null) {
            Chartboost.setCustomId(globalSettings.getCustomId());
        }
    }

    public boolean hasVideoAvailable() {
        return Chartboost.hasRewardedVideo(this.mLocation);
    }

    public void showVideo() {
        if (hasVideoAvailable()) {
            Chartboost.showRewardedVideo(this.mLocation);
        } else {
            MoPubLog.d("Attempted to show Chartboost rewarded video before it was available.");
        }
    }

    protected void onInvalidate() {
        ChartboostShared.getDelegate().unregisterRewardedVideoLocation(this.mLocation);
    }
}
