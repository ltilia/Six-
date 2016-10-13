package com.mopub.mobileads;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.vungle.publisher.EventListener;
import com.vungle.publisher.VunglePub;
import java.util.Map;

public class VungleInterstitial extends CustomEventInterstitial implements EventListener {
    public static final String APP_ID_KEY = "appId";
    private static final String DEFAULT_VUNGLE_APP_ID = "YOUR_DEFAULT_VUNGLE_APP_ID";
    private CustomEventInterstitialListener mCustomEventInterstitialListener;
    private final Handler mHandler;
    private boolean mIsLoading;
    private final VunglePub mVunglePub;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialLoaded();
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            Log.d("MoPub", "Showing Vungle interstitial ad.");
            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialShown();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ boolean val$wasCallToActionClicked;

        3(boolean z) {
            this.val$wasCallToActionClicked = z;
        }

        public void run() {
            Log.d("MoPub", "Vungle interstitial ad dismissed.");
            VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialDismissed();
            if (this.val$wasCallToActionClicked) {
                VungleInterstitial.this.mCustomEventInterstitialListener.onInterstitialClicked();
            }
        }
    }

    public VungleInterstitial() {
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mVunglePub = VunglePub.getInstance();
        this.mIsLoading = false;
    }

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> serverExtras) {
        this.mCustomEventInterstitialListener = customEventInterstitialListener;
        if (context == null) {
            this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_INVALID_STATE);
            return;
        }
        String appId;
        if (extrasAreValid(serverExtras)) {
            appId = (String) serverExtras.get(APP_ID_KEY);
        } else {
            appId = DEFAULT_VUNGLE_APP_ID;
        }
        this.mVunglePub.init(context, appId);
        this.mVunglePub.setEventListeners(this);
        if (this.mVunglePub.isAdPlayable()) {
            notifyAdAvailable();
        } else {
            this.mIsLoading = true;
        }
    }

    protected void showInterstitial() {
        if (this.mVunglePub.isAdPlayable()) {
            this.mVunglePub.playAd();
        } else {
            Log.d("MoPub", "Tried to show a Vungle interstitial ad before it finished loading. Please try again.");
        }
    }

    protected void onInvalidate() {
        this.mVunglePub.clearEventListeners();
        this.mIsLoading = false;
    }

    private boolean extrasAreValid(Map<String, String> serverExtras) {
        return serverExtras.containsKey(APP_ID_KEY);
    }

    private void notifyAdAvailable() {
        Log.d("MoPub", "Vungle interstitial ad successfully loaded.");
        this.mIsLoading = false;
        this.mHandler.post(new 1());
    }

    public void onVideoView(boolean isCompletedView, int watchedMillis, int videoDurationMillis) {
        double watchedPercent = (((double) watchedMillis) / ((double) videoDurationMillis)) * 100.0d;
        Log.d("MoPub", String.format("%.1f%% of Vungle video watched.", new Object[]{Double.valueOf(watchedPercent)}));
    }

    public void onAdStart() {
        this.mHandler.post(new 2());
    }

    public void onAdEnd(boolean wasCallToActionClicked) {
        this.mHandler.post(new 3(wasCallToActionClicked));
    }

    public void onAdUnavailable(String s) {
        this.mCustomEventInterstitialListener.onInterstitialFailed(MoPubErrorCode.NETWORK_NO_FILL);
    }

    public void onAdPlayableChanged(boolean playable) {
        if (this.mIsLoading && playable) {
            notifyAdAvailable();
        }
    }
}
