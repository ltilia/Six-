import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.Log;
import android.view.ViewGroup.LayoutParams;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdSize;
import com.amazon.device.ads.AdTargetingOptions;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.mobileads.CustomEventBanner;
import com.mopub.mobileads.CustomEventBanner.CustomEventBannerListener;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AmazonEventBanner extends CustomEventBanner {
    private static final String ADVANCED_OPTIONS_KEY = "advOptions";
    private static final String APP_KEY = "appKey";
    private static final String GEOLOCATION_ENABLED_KEY = "geolocationEnabled";
    private static final String LOGGING_ENABLED_KEY = "loggingEnabled";
    private static final String LOG_TAG;
    private static final String MOPUB_AD_HEIGHT_KEY = "com_mopub_ad_height";
    private static final String MOPUB_AD_WIDTH_KEY = "com_mopub_ad_width";
    private static final String PK_KEY = "pk";
    private static final String PK_VALUE = "[AndroidMoPubAdapter-1.0]";
    private static final String SCALING_ENABLED_KEY = "scalingEnabled";
    private static final String SLOT_KEY = "slot";
    private static final String SLOT_VALUE = "MoPubAMZN";
    private static final String TESTING_ENABLED_KEY = "testingEnabled";
    private AdLayout amazonBanner;
    private CustomEventBannerListener mopubBannerListener;

    static {
        LOG_TAG = AmazonEventBanner.class.getSimpleName();
    }

    protected void loadBanner(Context context, CustomEventBannerListener customEventBannerListener, Map<String, Object> localExtras, Map<String, String> serviceExtras) {
        this.mopubBannerListener = customEventBannerListener;
        AdRegistration.setAppKey((String) serviceExtras.get(APP_KEY));
        AdRegistration.enableLogging(Boolean.parseBoolean((String) serviceExtras.get(LOGGING_ENABLED_KEY)));
        AdRegistration.enableTesting(Boolean.parseBoolean((String) serviceExtras.get(TESTING_ENABLED_KEY)));
        AdSize amazonAdSize = convertToAmazonAdSize(((Integer) localExtras.get(MOPUB_AD_WIDTH_KEY)).intValue(), ((Integer) localExtras.get(MOPUB_AD_HEIGHT_KEY)).intValue());
        if (!Boolean.parseBoolean((String) serviceExtras.get(SCALING_ENABLED_KEY))) {
            amazonAdSize = amazonAdSize.disableScaling();
        }
        this.amazonBanner = new AdLayout(context, amazonAdSize);
        this.amazonBanner.setLayoutParams(new LayoutParams(-1, -2));
        this.amazonBanner.setListener(new AmazonEventBanner$AmazonAdBannerListener(null));
        AdTargetingOptions adTargetingOptions = new AdTargetingOptions();
        String advOptions = (String) serviceExtras.get(ADVANCED_OPTIONS_KEY);
        if (!(advOptions == null || advOptions.isEmpty())) {
            try {
                JSONObject advOptionsJson = new JSONObject(advOptions);
                Iterator<String> keysIt = advOptionsJson.keys();
                while (keysIt.hasNext()) {
                    String key = (String) keysIt.next();
                    adTargetingOptions.setAdvancedOption(key, advOptionsJson.getString(key));
                }
            } catch (JSONException e) {
                Log.e(LOG_TAG, "Error converting advOptions JSON.");
            }
        }
        adTargetingOptions.enableGeoLocation(Boolean.parseBoolean((String) serviceExtras.get(GEOLOCATION_ENABLED_KEY)));
        adTargetingOptions.setAdvancedOption(SLOT_KEY, SLOT_VALUE);
        adTargetingOptions.setAdvancedOption(PK_KEY, PK_VALUE);
        this.amazonBanner.loadAd(adTargetingOptions);
    }

    protected void onInvalidate() {
        this.amazonBanner.destroy();
    }

    private AdSize convertToAmazonAdSize(int adWidth, int adHeight) {
        if (adWidth == 320 && adHeight == 50) {
            return AdSize.SIZE_320x50;
        }
        if (adWidth == Strategy.TTL_SECONDS_DEFAULT) {
            if (adHeight == 250) {
                return AdSize.SIZE_300x250;
            }
            if (adHeight == 50) {
                return AdSize.SIZE_320x50;
            }
        }
        if (adWidth == AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT && adHeight == 50) {
            return AdSize.SIZE_1024x50;
        }
        if (adWidth == 600 && adHeight == 90) {
            return AdSize.SIZE_600x90;
        }
        if (adWidth == 728 && adHeight == 90) {
            return AdSize.SIZE_728x90;
        }
        return AdSize.SIZE_AUTO;
    }
}
