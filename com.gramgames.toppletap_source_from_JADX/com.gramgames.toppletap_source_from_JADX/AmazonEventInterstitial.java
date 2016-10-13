import android.content.Context;
import android.util.Log;
import com.amazon.device.ads.AdRegistration;
import com.amazon.device.ads.AdTargetingOptions;
import com.amazon.device.ads.InterstitialAd;
import com.mopub.mobileads.CustomEventInterstitial;
import com.mopub.mobileads.CustomEventInterstitial.CustomEventInterstitialListener;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class AmazonEventInterstitial extends CustomEventInterstitial {
    private static final String ADVANCED_OPTIONS_KEY = "advOptions";
    private static final String APP_KEY = "appKey";
    private static final String GEOLOCATION_ENABLED_KEY = "geolocationEnabled";
    private static final String LOGGING_ENABLED_KEY = "loggingEnabled";
    private static final String LOG_TAG;
    private static final String PK_KEY = "pk";
    private static final String PK_VALUE = "[AndroidMoPubAdapter-1.0]";
    private static final String SLOT_KEY = "slot";
    private static final String SLOT_VALUE = "MoPubAMZN";
    private static final String TESTING_ENABLED_KEY = "testingEnabled";
    private InterstitialAd amazonInterstitial;
    private CustomEventInterstitialListener mopubInterstitialListener;

    static {
        LOG_TAG = AmazonEventInterstitial.class.getSimpleName();
    }

    protected void loadInterstitial(Context context, CustomEventInterstitialListener customEventInterstitialListener, Map<String, Object> map, Map<String, String> serviceExtras) {
        this.mopubInterstitialListener = customEventInterstitialListener;
        AdRegistration.setAppKey((String) serviceExtras.get(APP_KEY));
        AdRegistration.enableLogging(Boolean.parseBoolean((String) serviceExtras.get(LOGGING_ENABLED_KEY)));
        AdRegistration.enableTesting(Boolean.parseBoolean((String) serviceExtras.get(TESTING_ENABLED_KEY)));
        this.amazonInterstitial = new InterstitialAd(context);
        this.amazonInterstitial.setListener(new AmazonEventInterstitial$InterstitialAdListener());
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
                Log.d(LOG_TAG, "Error converting advOptions JSON.");
            }
        }
        adTargetingOptions.enableGeoLocation(Boolean.parseBoolean((String) serviceExtras.get(GEOLOCATION_ENABLED_KEY)));
        adTargetingOptions.setAdvancedOption(SLOT_KEY, SLOT_VALUE);
        adTargetingOptions.setAdvancedOption(PK_KEY, PK_VALUE);
        this.amazonInterstitial.loadAd(adTargetingOptions);
    }

    protected void showInterstitial() {
        this.amazonInterstitial.showAd();
    }

    protected void onInvalidate() {
    }
}
