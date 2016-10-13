import android.util.Log;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.mopub.mobileads.MoPubErrorCode;

class AmazonEventInterstitial$InterstitialAdListener implements AdListener {
    final /* synthetic */ AmazonEventInterstitial this$0;

    private AmazonEventInterstitial$InterstitialAdListener(AmazonEventInterstitial amazonEventInterstitial) {
        this.this$0 = amazonEventInterstitial;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        this.this$0.mopubInterstitialListener.onInterstitialLoaded();
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        this.this$0.mopubInterstitialListener.onInterstitialFailed(convertToMoPubErrorCode(adError));
    }

    public void onAdExpanded(Ad ad) {
        Log.i(AmazonEventInterstitial.LOG_TAG, "Amazon Interstitial Ad Expanded.");
    }

    public void onAdCollapsed(Ad ad) {
        Log.i(AmazonEventInterstitial.LOG_TAG, "Amazon Interstitial Ad Collapsed.");
    }

    public void onAdDismissed(Ad ad) {
        this.this$0.mopubInterstitialListener.onInterstitialDismissed();
    }

    private MoPubErrorCode convertToMoPubErrorCode(AdError adError) {
        ErrorCode errorCode = adError.getCode();
        if (errorCode.equals(ErrorCode.NO_FILL)) {
            return MoPubErrorCode.NETWORK_NO_FILL;
        }
        if (errorCode.equals(ErrorCode.NETWORK_ERROR)) {
            return MoPubErrorCode.NETWORK_INVALID_STATE;
        }
        if (errorCode.equals(ErrorCode.NETWORK_TIMEOUT)) {
            return MoPubErrorCode.NETWORK_TIMEOUT;
        }
        if (errorCode.equals(ErrorCode.INTERNAL_ERROR)) {
            return MoPubErrorCode.INTERNAL_ERROR;
        }
        return MoPubErrorCode.UNSPECIFIED;
    }
}
