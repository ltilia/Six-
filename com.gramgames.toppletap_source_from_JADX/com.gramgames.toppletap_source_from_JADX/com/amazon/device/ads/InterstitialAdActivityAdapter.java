package com.amazon.device.ads;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.res.Configuration;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.ViewGroup;
import com.amazon.device.ads.AdActivity.AdActivityAdapter;
import com.amazon.device.ads.SDKEvent.SDKEventType;

@SuppressLint({"NewApi"})
class InterstitialAdActivityAdapter implements AdActivityAdapter {
    private static final String LOGTAG;
    private Activity activity;
    private AdController adController;
    private final AndroidBuildInfo buildInfo;
    private final MobileAdsLogger logger;

    class InterstitialAdSDKEventListener implements SDKEventListener {
        InterstitialAdSDKEventListener() {
        }

        public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor controller) {
            if (sdkEvent.getEventType().equals(SDKEventType.CLOSED)) {
                InterstitialAdActivityAdapter.this.finishActivity();
            }
        }
    }

    InterstitialAdActivityAdapter() {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.buildInfo = new AndroidBuildInfo();
        this.activity = null;
    }

    static {
        LOGTAG = InterstitialAdActivityAdapter.class.getSimpleName();
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
        this.activity.getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        AndroidTargetUtils.hideActionAndStatusBars(this.buildInfo, this.activity);
    }

    public void onCreate() {
        AndroidTargetUtils.enableHardwareAcceleration(this.buildInfo, this.activity.getWindow());
        this.adController = getAdController();
        if (this.adController == null) {
            this.logger.e("Failed to show interstitial ad due to an error in the Activity.");
            InterstitialAd.resetIsAdShowing();
            this.activity.finish();
            return;
        }
        this.adController.setAdActivity(this.activity);
        this.adController.addSDKEventListener(new InterstitialAdSDKEventListener());
        ViewGroup parent = (ViewGroup) this.adController.getView().getParent();
        if (parent != null) {
            parent.removeView(this.adController.getView());
        }
        this.activity.setContentView(this.adController.getView());
        this.adController.adShown();
    }

    AdController getAdController() {
        return AdControllerFactory.getCachedAdController();
    }

    public void onPause() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
        }
    }

    public void onResume() {
    }

    public void onStop() {
        if (this.activity.isFinishing() && this.adController != null) {
            this.adController.fireViewableEvent();
            this.adController.closeAd();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        if (this.adController != null) {
            return this.adController.onBackButtonPress();
        }
        return false;
    }

    public void onWindowFocusChanged() {
        if (this.adController != null) {
            this.adController.fireViewableEvent();
        }
    }

    Activity getActivity() {
        return this.activity;
    }

    private void finishActivity() {
        if (!this.activity.isFinishing()) {
            this.adController = null;
            this.activity.finish();
        }
    }
}
