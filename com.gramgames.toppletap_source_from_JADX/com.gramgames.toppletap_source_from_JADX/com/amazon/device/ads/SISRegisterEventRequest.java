package com.amazon.device.ads;

import com.amazon.device.ads.Configuration.ConfigOption;
import com.mopub.mobileads.VungleRewardedVideo;
import com.vungle.publisher.FullScreenAdActivity;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

/* compiled from: SISRequests */
class SISRegisterEventRequest implements SISRequest {
    private static final MetricType CALL_METRIC_TYPE;
    private static final String LOGTAG = "SISRegisterEventRequest";
    private static final String PATH = "/register_event";
    private final Info advertisingIdentifierInfo;
    private final AppEventRegistrationHandler appEventRegistrationHandler;
    private final JSONArray appEvents;
    private final Configuration configuration;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType;

        static {
            $SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType = new int[SISRequestType.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType[SISRequestType.GENERATE_DID.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType[SISRequestType.UPDATE_DEVICE_INFO.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType[SISRequestType.REGISTER_EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    /* compiled from: SISRequests */
    static class SISRequestFactory {
        SISRequestFactory() {
        }

        public SISDeviceRequest createDeviceRequest(SISRequestType requestType) {
            switch (1.$SwitchMap$com$amazon$device$ads$SISRegisterEventRequest$SISRequestType[requestType.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return new SISGenerateDIDRequest();
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return new SISUpdateDeviceInfoRequest();
                default:
                    throw new IllegalArgumentException("SISRequestType " + requestType + " is not a SISDeviceRequest");
            }
        }

        public SISRegisterEventRequest createRegisterEventRequest(Info advertisingIdentifierInfo, JSONArray appEvents) {
            return new SISRegisterEventRequest(advertisingIdentifierInfo, appEvents);
        }
    }

    /* compiled from: SISRequests */
    enum SISRequestType {
        GENERATE_DID,
        UPDATE_DEVICE_INFO,
        REGISTER_EVENT
    }

    static {
        CALL_METRIC_TYPE = MetricType.SIS_LATENCY_REGISTER_EVENT;
    }

    public SISRegisterEventRequest(Info advertisingIdentifierInfo, JSONArray appEvents) {
        this(advertisingIdentifierInfo, appEvents, MobileAdsInfoStore.getInstance(), Configuration.getInstance(), AppEventRegistrationHandler.getInstance());
    }

    SISRegisterEventRequest(Info advertisingIdentifierInfo, JSONArray appEvents, MobileAdsInfoStore infoStore, Configuration configuration, AppEventRegistrationHandler appEventRegistrationHandler) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.advertisingIdentifierInfo = advertisingIdentifierInfo;
        this.appEvents = appEvents;
        this.infoStore = infoStore;
        this.configuration = configuration;
        this.appEventRegistrationHandler = appEventRegistrationHandler;
    }

    public String getLogTag() {
        return LOGTAG;
    }

    public MetricType getCallMetricType() {
        return CALL_METRIC_TYPE;
    }

    public String getPath() {
        return PATH;
    }

    public QueryStringParameters getQueryParameters() {
        QueryStringParameters queryStringParameters = new QueryStringParameters();
        queryStringParameters.putUrlEncoded(FullScreenAdActivity.AD_ID_EXTRA_KEY, this.advertisingIdentifierInfo.getSISDeviceIdentifier());
        queryStringParameters.putUrlEncoded("dt", this.infoStore.getDeviceInfo().getDeviceType());
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        queryStringParameters.putUrlEncoded("app", registrationInfo.getAppName());
        queryStringParameters.putUrlEncoded(VungleRewardedVideo.APP_ID_KEY, registrationInfo.getAppKey());
        queryStringParameters.putUrlEncoded("aud", this.configuration.getString(ConfigOption.SIS_DOMAIN));
        queryStringParameters.putUnencoded("pkg", this.infoStore.getAppInfo().getPackageInfoJSONString());
        return queryStringParameters;
    }

    public HashMap<String, String> getPostParameters() {
        HashMap<String, String> eventsMap = new HashMap();
        eventsMap.put("events", this.appEvents.toString());
        return eventsMap;
    }

    public void onResponseReceived(JSONObject payload) {
        int statusCode = JSONUtils.getIntegerFromJSON(payload, "rcode", 0);
        if (statusCode == 1) {
            this.logger.d("Application events registered successfully.");
            this.appEventRegistrationHandler.onAppEventsRegistered();
            return;
        }
        this.logger.d("Application events not registered. rcode:" + statusCode);
    }

    public MobileAdsLogger getLogger() {
        return this.logger;
    }
}
