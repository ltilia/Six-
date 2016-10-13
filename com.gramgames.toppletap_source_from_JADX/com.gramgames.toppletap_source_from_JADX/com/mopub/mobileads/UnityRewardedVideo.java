package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.BaseLifecycleListener;
import com.mopub.common.DataKeys;
import com.mopub.common.LifecycleListener;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.unity3d.ads.android.IUnityAdsListener;
import com.unity3d.ads.android.UnityAds;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UnityRewardedVideo extends CustomEventRewardedVideo {
    private static final String DEFAULT_ZONE_ID = "";
    private static final String GAME_ID_KEY = "gameId";
    private static final String ZONE_ID_KEY = "zoneId";
    private static boolean sInitialized;
    private static final LifecycleListener sLifecycleListener;
    private static final UnityAdsListener sUnityAdsListener;
    @NonNull
    private static String sZoneId;
    @Nullable
    private UnityMediationSettings mMediationSettings;

    private static class UnityAdsListener implements IUnityAdsListener, CustomEventRewardedVideoListener {
        private UnityAdsListener() {
        }

        public void onFetchCompleted() {
            MoPubLog.d("Unity rewarded video cached for zone " + UnityAds.getZone() + ".");
            UnityRewardedVideo.loadRewardedVideo();
        }

        public void onFetchFailed() {
            MoPubLog.d("Unity rewarded video cache failed for zone " + UnityAds.getZone() + ".");
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(UnityRewardedVideo.class, UnityAds.getZone(), MoPubErrorCode.NETWORK_NO_FILL);
        }

        public void onShow() {
            MoPubLog.d("Unity rewarded video displayed for zone " + UnityAds.getZone() + ".");
        }

        public void onHide() {
            MoPubRewardedVideoManager.onRewardedVideoClosed(UnityRewardedVideo.class, UnityAds.getZone());
            MoPubLog.d("Unity rewarded video dismissed for zone " + UnityAds.getZone() + ".");
        }

        public void onVideoStarted() {
            MoPubRewardedVideoManager.onRewardedVideoStarted(UnityRewardedVideo.class, UnityAds.getZone());
            MoPubLog.d("Unity rewarded video started for zone " + UnityAds.getZone() + ".");
        }

        public void onVideoCompleted(String itemKey, boolean skipped) {
            if (skipped) {
                MoPubRewardedVideoManager.onRewardedVideoCompleted(UnityRewardedVideo.class, UnityAds.getZone(), MoPubReward.failure());
                MoPubLog.d("Unity rewarded video skipped for zone " + UnityAds.getZone() + " with " + "reward item key " + itemKey);
                return;
            }
            MoPubRewardedVideoManager.onRewardedVideoCompleted(UnityRewardedVideo.class, UnityAds.getZone(), MoPubReward.success(itemKey, MoPubReward.NO_REWARD_AMOUNT));
            MoPubLog.d("Unity rewarded video completed for zone " + UnityAds.getZone() + " with reward item key " + itemKey);
        }
    }

    private static final class UnityLifecycleListener extends BaseLifecycleListener {
        private UnityLifecycleListener() {
        }

        public void onCreate(@NonNull Activity activity) {
            super.onCreate(activity);
            UnityAds.changeActivity(activity);
        }

        public void onResume(@NonNull Activity activity) {
            super.onResume(activity);
            UnityAds.changeActivity(activity);
        }
    }

    public static final class UnityMediationSettings implements MediationSettings {
        @NonNull
        private final HashMap<String, Object> mProperties;

        public UnityMediationSettings(@NonNull String gamerId) {
            this.mProperties = new HashMap();
            this.mProperties.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_GAMERSID_KEY, gamerId);
        }

        @NonNull
        public Map<String, Object> getPropertiesMap() {
            return this.mProperties;
        }
    }

    static {
        sLifecycleListener = new UnityLifecycleListener();
        sUnityAdsListener = new UnityAdsListener();
        sInitialized = false;
        sZoneId = DEFAULT_ZONE_ID;
    }

    @NonNull
    public CustomEventRewardedVideoListener getVideoListenerForSdk() {
        return sUnityAdsListener;
    }

    @NonNull
    public LifecycleListener getLifecycleListener() {
        return sLifecycleListener;
    }

    @NonNull
    public String getAdNetworkId() {
        return sZoneId;
    }

    public boolean checkAndInitializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, Object> map, @NonNull Map<String, String> serverExtras) throws Exception {
        if (sInitialized) {
            return false;
        }
        if (serverExtras.containsKey(GAME_ID_KEY)) {
            String gameId = (String) serverExtras.get(GAME_ID_KEY);
            if (TextUtils.isEmpty(gameId)) {
                throw new IllegalStateException("Unity rewarded video initialization failed due to empty gameId");
            }
            UnityAds.init(launcherActivity, gameId, sUnityAdsListener);
            sInitialized = true;
            return true;
        }
        throw new IllegalStateException("Unity rewarded video initialization failed due to missing gameId");
    }

    protected void loadWithSdkInitialized(@NonNull Activity activity, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) throws Exception {
        if (serverExtras.containsKey(ZONE_ID_KEY)) {
            String zoneId = (String) serverExtras.get(ZONE_ID_KEY);
            if (TextUtils.isEmpty(zoneId)) {
                zoneId = sZoneId;
            }
            sZoneId = zoneId;
        }
        try {
            setUpMediationSettingsForRequest((String) localExtras.get(DataKeys.AD_UNIT_ID_KEY));
        } catch (ClassCastException e) {
            MoPubLog.e("Failed to set up Unity mediation settings due to invalid ad unit id", e);
        }
        loadRewardedVideo();
    }

    public boolean hasVideoAvailable() {
        return UnityAds.canShow();
    }

    public void showVideo() {
        if (hasVideoAvailable()) {
            UnityAds.show(getUnityProperties());
        } else {
            MoPubLog.d("Attempted to show Unity rewarded video before it was available.");
        }
    }

    protected void onInvalidate() {
        UnityAds.setListener(null);
    }

    private void setUpMediationSettingsForRequest(@Nullable String moPubId) {
        this.mMediationSettings = (UnityMediationSettings) MoPubRewardedVideoManager.getGlobalMediationSettings(UnityMediationSettings.class);
        if (moPubId != null) {
            UnityMediationSettings instanceSettings = (UnityMediationSettings) MoPubRewardedVideoManager.getInstanceMediationSettings(UnityMediationSettings.class, moPubId);
            if (instanceSettings != null) {
                this.mMediationSettings = instanceSettings;
            }
        }
    }

    @NonNull
    private Map<String, Object> getUnityProperties() {
        if (this.mMediationSettings == null) {
            return Collections.emptyMap();
        }
        return this.mMediationSettings.getPropertiesMap();
    }

    private static void loadRewardedVideo() {
        UnityAds.setZone(sZoneId);
        MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(UnityRewardedVideo.class, UnityAds.getZone());
    }

    @VisibleForTesting
    void reset() {
        sInitialized = false;
        sZoneId = DEFAULT_ZONE_ID;
    }
}
