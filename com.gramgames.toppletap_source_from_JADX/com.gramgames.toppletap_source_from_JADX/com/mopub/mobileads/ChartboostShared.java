package com.mopub.mobileads;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.chartboost.sdk.Chartboost;
import com.chartboost.sdk.Chartboost.CBMediation;
import com.chartboost.sdk.ChartboostDelegate;
import com.chartboost.sdk.Model.CBError.CBImpressionError;
import com.mopub.common.MoPub;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import gs.gram.mopub.BuildConfig;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class ChartboostShared {
    public static final String APP_ID_KEY = "appId";
    public static final String APP_SIGNATURE_KEY = "appSignature";
    public static final String LOCATION_DEFAULT = "Default";
    public static final String LOCATION_KEY = "location";
    @Nullable
    private static String mAppId;
    @Nullable
    private static String mAppSignature;
    private static volatile ChartboostSingletonDelegate sDelegate;

    public static class ChartboostSingletonDelegate extends ChartboostDelegate implements CustomEventRewardedVideoListener {
        private static final CustomEventInterstitialListener NULL_LISTENER;
        private Map<String, CustomEventInterstitialListener> mInterstitialListenersForLocation;
        private Set<String> mRewardedVideoLocationsToLoad;

        static class 1 implements CustomEventInterstitialListener {
            1() {
            }

            public void onInterstitialLoaded() {
            }

            public void onInterstitialFailed(MoPubErrorCode errorCode) {
            }

            public void onInterstitialShown() {
            }

            public void onInterstitialClicked() {
            }

            public void onLeaveApplication() {
            }

            public void onInterstitialDismissed() {
            }
        }

        public ChartboostSingletonDelegate() {
            this.mInterstitialListenersForLocation = Collections.synchronizedMap(new TreeMap());
            this.mRewardedVideoLocationsToLoad = Collections.synchronizedSet(new TreeSet());
        }

        static {
            NULL_LISTENER = new 1();
        }

        public void registerInterstitialListener(@NonNull String location, @NonNull CustomEventInterstitialListener interstitialListener) {
            Preconditions.checkNotNull(location);
            Preconditions.checkNotNull(interstitialListener);
            this.mInterstitialListenersForLocation.put(location, interstitialListener);
        }

        public void unregisterInterstitialListener(@NonNull String location) {
            Preconditions.checkNotNull(location);
            this.mInterstitialListenersForLocation.remove(location);
        }

        public void registerRewardedVideoLocation(@NonNull String location) {
            Preconditions.checkNotNull(location);
            this.mRewardedVideoLocationsToLoad.add(location);
        }

        public void unregisterRewardedVideoLocation(@NonNull String location) {
            Preconditions.checkNotNull(location);
            this.mRewardedVideoLocationsToLoad.remove(location);
        }

        @NonNull
        public CustomEventInterstitialListener getInterstitialListener(@NonNull String location) {
            CustomEventInterstitialListener listener = (CustomEventInterstitialListener) this.mInterstitialListenersForLocation.get(location);
            return listener != null ? listener : NULL_LISTENER;
        }

        public boolean hasInterstitialLocation(@NonNull String location) {
            return this.mInterstitialListenersForLocation.containsKey(location);
        }

        public void didCacheInterstitial(String location) {
            MoPubLog.d("Chartboost interstitial loaded successfully.");
            getInterstitialListener(location).onInterstitialLoaded();
        }

        public void didFailToLoadInterstitial(String location, CBImpressionError error) {
            Log.d("MoPub", "Chartboost interstitial ad failed to load." + (error != null ? "Error: " + error.name() : BuildConfig.FLAVOR));
            getInterstitialListener(location).onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
        }

        public void didDismissInterstitial(String location) {
            MoPubLog.d("Chartboost interstitial ad dismissed.");
            getInterstitialListener(location).onInterstitialDismissed();
        }

        public void didCloseInterstitial(String location) {
            MoPubLog.d("Chartboost interstitial ad closed.");
        }

        public void didClickInterstitial(String location) {
            MoPubLog.d("Chartboost interstitial ad clicked.");
            getInterstitialListener(location).onInterstitialClicked();
        }

        public void didDisplayInterstitial(String location) {
            MoPubLog.d("Chartboost interstitial ad shown.");
            getInterstitialListener(location).onInterstitialShown();
        }

        public void didCacheRewardedVideo(String location) {
            super.didCacheRewardedVideo(location);
            if (this.mRewardedVideoLocationsToLoad.contains(location)) {
                MoPubLog.d("Chartboost rewarded video cached for location " + location + ".");
                MoPubRewardedVideoManager.onRewardedVideoLoadSuccess(ChartboostRewardedVideo.class, location);
                this.mRewardedVideoLocationsToLoad.remove(location);
            }
        }

        public void didFailToLoadRewardedVideo(String location, CBImpressionError error) {
            super.didFailToLoadRewardedVideo(location, error);
            String suffix = error != null ? " with error: " + error.name() : BuildConfig.FLAVOR;
            if (this.mRewardedVideoLocationsToLoad.contains(location)) {
                MoPubErrorCode errorCode = MoPubErrorCode.VIDEO_DOWNLOAD_ERROR;
                MoPubLog.d("Chartboost rewarded video cache failed for location " + location + suffix);
                if (CBImpressionError.INVALID_LOCATION.equals(error)) {
                    errorCode = MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR;
                }
                MoPubRewardedVideoManager.onRewardedVideoLoadFailure(ChartboostRewardedVideo.class, location, errorCode);
                this.mRewardedVideoLocationsToLoad.remove(location);
            }
        }

        public void didDismissRewardedVideo(String location) {
            super.didDismissRewardedVideo(location);
            MoPubRewardedVideoManager.onRewardedVideoClosed(ChartboostRewardedVideo.class, location);
            MoPubLog.d("Chartboost rewarded video dismissed for location " + location + ".");
        }

        public void didCloseRewardedVideo(String location) {
            super.didCloseRewardedVideo(location);
            MoPubLog.d("Chartboost rewarded video closed for location " + location + ".");
        }

        public void didClickRewardedVideo(String location) {
            super.didClickRewardedVideo(location);
            MoPubRewardedVideoManager.onRewardedVideoClicked(ChartboostRewardedVideo.class, location);
            MoPubLog.d("Chartboost rewarded video clicked for location " + location + ".");
        }

        public void didCompleteRewardedVideo(String location, int reward) {
            super.didCompleteRewardedVideo(location, reward);
            MoPubLog.d("Chartboost rewarded video completed for location " + location + " with " + "reward amount " + reward);
            MoPubRewardedVideoManager.onRewardedVideoCompleted(ChartboostRewardedVideo.class, location, MoPubReward.success(BuildConfig.FLAVOR, reward));
        }

        public void didDisplayRewardedVideo(String location) {
            super.didDisplayRewardedVideo(location);
            MoPubLog.d("Chartboost rewarded video displayed for location " + location + ".");
            MoPubRewardedVideoManager.onRewardedVideoStarted(ChartboostRewardedVideo.class, location);
        }

        public boolean shouldRequestMoreApps(String location) {
            return false;
        }

        public boolean shouldDisplayMoreApps(String location) {
            return false;
        }
    }

    static {
        sDelegate = new ChartboostSingletonDelegate();
    }

    public static synchronized boolean initializeSdk(@NonNull Activity launcherActivity, @NonNull Map<String, String> serverExtras) {
        boolean z = false;
        synchronized (ChartboostShared.class) {
            Preconditions.checkNotNull(launcherActivity);
            Preconditions.checkNotNull(serverExtras);
            if (!serverExtras.containsKey(APP_ID_KEY)) {
                throw new IllegalStateException("Chartboost rewarded video initialization failed due to missing application ID.");
            } else if (serverExtras.containsKey(APP_SIGNATURE_KEY)) {
                String appId = (String) serverExtras.get(APP_ID_KEY);
                String appSignature = (String) serverExtras.get(APP_SIGNATURE_KEY);
                if (!(appId.equals(mAppId) && appSignature.equals(mAppSignature))) {
                    mAppId = appId;
                    mAppSignature = appSignature;
                    Chartboost.startWithAppId(launcherActivity, mAppId, mAppSignature);
                    Chartboost.setImpressionsUseActivities(false);
                    Chartboost.setMediation(CBMediation.CBMediationMoPub, MoPub.SDK_VERSION);
                    Chartboost.setDelegate(sDelegate);
                    Chartboost.setShouldRequestInterstitialsInFirstSession(true);
                    Chartboost.setAutoCacheAds(false);
                    Chartboost.setShouldDisplayLoadingViewForMoreApps(false);
                    z = true;
                }
            } else {
                throw new IllegalStateException("Chartboost rewarded video initialization failed due to missing application signature.");
            }
        }
        return z;
    }

    @NonNull
    public static ChartboostSingletonDelegate getDelegate() {
        return sDelegate;
    }

    @Deprecated
    @VisibleForTesting
    static void reset() {
        sDelegate = new ChartboostSingletonDelegate();
        mAppId = null;
        mAppSignature = null;
    }
}
