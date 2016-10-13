import android.util.Log;
import com.amazon.device.ads.Ad;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdError.ErrorCode;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import com.mopub.mobileads.MoPubErrorCode;

class AmazonEventBanner$AmazonAdBannerListener implements AdListener {
    final /* synthetic */ AmazonEventBanner this$0;

    private AmazonEventBanner$AmazonAdBannerListener(AmazonEventBanner amazonEventBanner) {
        this.this$0 = amazonEventBanner;
    }

    public void onAdLoaded(Ad ad, AdProperties adProperties) {
        this.this$0.mopubBannerListener.onBannerLoaded(this.this$0.amazonBanner);
    }

    public void onAdFailedToLoad(Ad ad, AdError adError) {
        this.this$0.mopubBannerListener.onBannerFailed(convertToMoPubErrorCode(adError));
    }

    public void onAdExpanded(Ad ad) {
        this.this$0.mopubBannerListener.onBannerExpanded();
    }

    public void onAdCollapsed(Ad ad) {
        this.this$0.mopubBannerListener.onBannerCollapsed();
    }

    public void onAdDismissed(Ad ad) {
        Log.i(AmazonEventBanner.LOG_TAG, "Amazon Banner Ad dismissed.");
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
