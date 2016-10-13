package com.amazon.device.ads;

import com.vungle.publisher.FullScreenAdActivity;
import gs.gram.mopub.BuildConfig;
import org.json.JSONObject;

/* compiled from: SISRequests */
class SISGenerateDIDRequest extends SISDeviceRequest {
    private static final MetricType CALL_METRIC_TYPE;
    private static final String LOGTAG;
    private static final String PATH = "/generate_did";

    static {
        LOGTAG = SISGenerateDIDRequest.class.getSimpleName();
        CALL_METRIC_TYPE = MetricType.SIS_LATENCY_REGISTER;
    }

    public SISGenerateDIDRequest() {
        setCallMetricType(CALL_METRIC_TYPE);
        setLogTag(LOGTAG);
        setPath(PATH);
    }

    public void onResponseReceived(JSONObject payload) {
        String adId = JSONUtils.getStringFromJSON(payload, FullScreenAdActivity.AD_ID_EXTRA_KEY, BuildConfig.FLAVOR);
        if (adId.length() > 0) {
            MobileAdsInfoStore.getInstance().getRegistrationInfo().putAdId(adId, getAdvertisingIdentifierInfo());
        }
    }
}
