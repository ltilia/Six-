package gs.gram.mopub.mediation.applovinconnector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import com.applovin.adview.AppLovinAdView;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdService;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import com.mopub.mobileads.MoPubErrorCode;
import java.util.Map;

public class AppLovinInterstitialAdapter extends CustomEventInterstitial implements AppLovinAdLoadListener {
    private AppLovinAdService adService;
    private AppLovinAd lastReceived;
    private CustomEventInterstitialListener mInterstitialListener;
    private Activity parentActivity;

    class 1 implements Runnable {
        final /* synthetic */ AppLovinAd val$adToRender;

        class 1 implements AppLovinAdClickListener {
            1() {
            }

            public void adClicked(AppLovinAd appLovinAd) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onLeaveApplication();
            }
        }

        class 2 implements AppLovinAdDisplayListener {
            2() {
            }

            public void adDisplayed(AppLovinAd appLovinAd) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialShown();
            }

            public void adHidden(AppLovinAd appLovinAd) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialDismissed();
            }
        }

        1(AppLovinAd appLovinAd) {
            this.val$adToRender = appLovinAd;
        }

        public void run() {
            AppLovinAdView adView = new AppLovinAdView(AppLovinAdSize.BANNER, AppLovinInterstitialAdapter.this.parentActivity);
            adView.setAdClickListener(new 1());
            adView.setAdDisplayListener(new 2());
            adView.renderAd(this.val$adToRender);
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialLoaded();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ int val$errorCode;

        3(int i) {
            this.val$errorCode = i;
        }

        public void run() {
            if (this.val$errorCode == AppLovinErrorCodes.NO_FILL) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.NO_FILL);
            } else if (this.val$errorCode >= 500) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.SERVER_ERROR);
            } else if (this.val$errorCode < 0) {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
            } else {
                AppLovinInterstitialAdapter.this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.UNSPECIFIED);
            }
        }
    }

    public void loadInterstitial(Context context, CustomEventInterstitialListener interstitialListener, Map<String, Object> map, Map<String, String> map2) {
        this.mInterstitialListener = interstitialListener;
        if (context instanceof Activity) {
            this.parentActivity = (Activity) context;
            Log.d("AppLovinAdapter", "Request received for new interstitial.");
            this.adService = AppLovinSdk.getInstance(context).getAdService();
            this.adService.loadNextAd(AppLovinAdSize.INTERSTITIAL, this);
            return;
        }
        this.mInterstitialListener.onInterstitialFailed(MoPubErrorCode.INTERNAL_ERROR);
    }

    public void showInterstitial() {
        AppLovinAd adToRender = this.lastReceived;
        if (adToRender != null) {
            Log.d("MoPub", "Showing AppLovin interstitial ad...");
            this.parentActivity.runOnUiThread(new 1(adToRender));
        }
    }

    public void onInvalidate() {
    }

    public void adReceived(AppLovinAd ad) {
        Log.d("MoPub", "AppLovin interstitial loaded successfully.");
        this.lastReceived = ad;
        this.parentActivity.runOnUiThread(new 2());
    }

    public void failedToReceiveAd(int errorCode) {
        this.parentActivity.runOnUiThread(new 3(errorCode));
    }
}
