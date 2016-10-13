package com.amazon.device.ads;

import android.annotation.SuppressLint;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.JSONUtils.JSONUtilities;
import com.amazon.device.ads.WebRequest.HttpMethod;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class AdRequest {
    private static final String LOGTAG;
    private static final AAXParameter<?>[] PARAMETERS;
    private static final AAXParameterGroup[] PARAMETER_GROUPS;
    private Info advertisingIdentifierInfo;
    private final Configuration configuration;
    private final ConnectionInfo connectionInfo;
    private final DebugProperties debugProperties;
    private String instrPixelUrl;
    private final JSONObjectBuilder jsonObjectBuilder;
    private final JSONUtilities jsonUtilities;
    private final MobileAdsLogger logger;
    private final AdTargetingOptions opt;
    private final String orientation;
    protected final Map<Integer, LOISlot> slots;
    private final WebRequestFactory webRequestFactory;

    static class AdRequestBuilder {
        private AdTargetingOptions adTargetingOptions;
        private Info advertisingIdentifierInfo;

        AdRequestBuilder() {
        }

        public AdRequestBuilder withAdTargetingOptions(AdTargetingOptions adTargetingOptions) {
            this.adTargetingOptions = adTargetingOptions;
            return this;
        }

        public AdRequestBuilder withAdvertisingIdentifierInfo(Info advertisingIdentifierInfo) {
            this.advertisingIdentifierInfo = advertisingIdentifierInfo;
            return this;
        }

        public AdRequest build() {
            return new AdRequest(this.adTargetingOptions).setAdvertisingIdentifierInfo(this.advertisingIdentifierInfo);
        }
    }

    static class JSONObjectBuilder {
        private AAXParameterGroup[] aaxParameterGroups;
        private AAXParameter<?>[] aaxParameters;
        private Map<String, String> advancedOptions;
        private final JSONObject json;
        private final MobileAdsLogger logger;
        private ParameterData parameterData;

        JSONObjectBuilder(MobileAdsLogger logger) {
            this(logger, new JSONObject());
        }

        JSONObjectBuilder(MobileAdsLogger logger, JSONObject json) {
            this.logger = logger;
            this.json = json;
        }

        JSONObjectBuilder setAAXParameters(AAXParameter<?>[] aaxParameters) {
            this.aaxParameters = aaxParameters;
            return this;
        }

        JSONObjectBuilder setAAXParameterGroups(AAXParameterGroup[] aaxParameterGroups) {
            this.aaxParameterGroups = aaxParameterGroups;
            return this;
        }

        JSONObjectBuilder setAdvancedOptions(Map<String, String> advancedOptions) {
            this.advancedOptions = advancedOptions;
            return this;
        }

        JSONObjectBuilder setParameterData(ParameterData parameterData) {
            this.parameterData = parameterData;
            return this;
        }

        ParameterData getParameterData() {
            return this.parameterData;
        }

        JSONObject getJSON() {
            return this.json;
        }

        void build() {
            for (AAXParameter parameter : this.aaxParameters) {
                putIntoJSON(parameter, parameter.getValue(this.parameterData));
            }
            if (this.aaxParameterGroups != null) {
                for (AAXParameterGroup aaxParameterGroup : this.aaxParameterGroups) {
                    aaxParameterGroup.evaluate(this.parameterData, this.json);
                }
            }
            if (this.advancedOptions != null) {
                for (Entry<String, String> entry : this.advancedOptions.entrySet()) {
                    if (!StringUtils.isNullOrWhiteSpace((String) entry.getValue())) {
                        putIntoJSON((String) entry.getKey(), entry.getValue());
                    }
                }
            }
        }

        void putIntoJSON(AAXParameter<?> parameter, Object value) {
            putIntoJSON(parameter.getName(), value);
        }

        void putIntoJSON(String key, Object value) {
            if (value != null) {
                try {
                    this.json.put(key, value);
                } catch (JSONException e) {
                    this.logger.d("Could not add parameter to JSON %s: %s", key, value);
                }
            }
        }
    }

    static class LOISlot {
        static final AAXParameter<?>[] PARAMETERS;
        private final AdSlot adSlot;
        private final DebugProperties debugProperties;
        private final JSONObjectBuilder jsonObjectBuilder;
        private final JSONUtilities jsonUtilities;
        private final AdTargetingOptions opt;

        static {
            PARAMETERS = new AAXParameter[]{AAXParameter.SIZE, AAXParameter.PAGE_TYPE, AAXParameter.SLOT, AAXParameter.SLOT_POSITION, AAXParameter.MAX_SIZE, AAXParameter.SLOT_ID, AAXParameter.FLOOR_PRICE, AAXParameter.SUPPORTED_MEDIA_TYPES, AAXParameter.VIDEO_OPTIONS};
        }

        LOISlot(AdSlot adSlot, AdRequest adRequest, MobileAdsLogger logger) {
            this(adSlot, adRequest, logger, new JSONObjectBuilder(logger), DebugProperties.getInstance(), new JSONUtilities());
        }

        LOISlot(AdSlot adSlot, AdRequest adRequest, MobileAdsLogger logger, JSONObjectBuilder jsonObjectBuilder, DebugProperties debugProperties, JSONUtilities jsonUtilites) {
            this.opt = adSlot.getAdTargetingOptions();
            this.adSlot = adSlot;
            this.debugProperties = debugProperties;
            this.jsonUtilities = jsonUtilites;
            Map<String, String> advancedOptions = this.opt.getCopyOfAdvancedOptions();
            if (this.debugProperties.containsDebugProperty(DebugProperties.DEBUG_ADVTARGETING)) {
                JSONObject json = this.debugProperties.getDebugPropertyAsJSONObject(DebugProperties.DEBUG_ADVTARGETING, null);
                if (json != null) {
                    advancedOptions.putAll(this.jsonUtilities.createMapFromJSON(json));
                }
            }
            this.jsonObjectBuilder = jsonObjectBuilder.setAAXParameters(PARAMETERS).setAdvancedOptions(advancedOptions).setParameterData(new ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(advancedOptions).setLOISlot(this).setAdRequest(adRequest));
        }

        AdTargetingOptions getAdTargetingOptions() {
            return this.opt;
        }

        JSONObject getJSON() {
            this.jsonObjectBuilder.build();
            return this.jsonObjectBuilder.getJSON();
        }

        AdSlot getAdSlot() {
            return this.adSlot;
        }
    }

    static {
        LOGTAG = AdRequest.class.getSimpleName();
        PARAMETERS = new AAXParameter[]{AAXParameter.APP_KEY, AAXParameter.CHANNEL, AAXParameter.PUBLISHER_KEYWORDS, AAXParameter.PUBLISHER_ASINS, AAXParameter.USER_AGENT, AAXParameter.SDK_VERSION, AAXParameter.GEOLOCATION, AAXParameter.DEVICE_INFO, AAXParameter.PACKAGE_INFO, AAXParameter.TEST, AAXParameter.OPT_OUT};
        PARAMETER_GROUPS = new AAXParameterGroup[]{AAXParameterGroup.USER_ID};
    }

    public AdRequest(AdTargetingOptions opt) {
        this(opt, new WebRequestFactory(), MobileAdsInfoStore.getInstance(), Configuration.getInstance(), DebugProperties.getInstance(), new MobileAdsLoggerFactory(), new JSONUtilities());
    }

    @SuppressLint({"UseSparseArrays"})
    AdRequest(AdTargetingOptions opt, WebRequestFactory webRequestFactory, MobileAdsInfoStore infoStore, Configuration configuration, DebugProperties debugProperties, MobileAdsLoggerFactory loggerFactory, JSONUtilities jsonUtilities) {
        this.opt = opt;
        this.webRequestFactory = webRequestFactory;
        this.jsonUtilities = jsonUtilities;
        this.slots = new HashMap();
        this.orientation = infoStore.getDeviceInfo().getOrientation();
        this.connectionInfo = new ConnectionInfo(infoStore);
        this.configuration = configuration;
        this.debugProperties = debugProperties;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        Map<String, String> advancedOptions = this.opt.getCopyOfAdvancedOptions();
        if (this.debugProperties.containsDebugProperty(DebugProperties.DEBUG_ADVTARGETING)) {
            JSONObject json = this.debugProperties.getDebugPropertyAsJSONObject(DebugProperties.DEBUG_ADVTARGETING, null);
            if (json != null) {
                advancedOptions.putAll(this.jsonUtilities.createMapFromJSON(json));
            }
        }
        this.jsonObjectBuilder = new JSONObjectBuilder(this.logger).setAAXParameters(PARAMETERS).setAAXParameterGroups(PARAMETER_GROUPS).setAdvancedOptions(advancedOptions).setParameterData(new ParameterData().setAdTargetingOptions(this.opt).setAdvancedOptions(advancedOptions).setAdRequest(this));
    }

    public void setInstrumentationPixelURL(String instrPixelUrl) {
        this.instrPixelUrl = instrPixelUrl;
    }

    public String getInstrumentationPixelURL() {
        return this.instrPixelUrl;
    }

    AdTargetingOptions getAdTargetingOptions() {
        return this.opt;
    }

    String getOrientation() {
        return this.orientation;
    }

    Info getAdvertisingIdentifierInfo() {
        return this.advertisingIdentifierInfo;
    }

    AdRequest setAdvertisingIdentifierInfo(Info advertisingIdentifierInfo) {
        this.advertisingIdentifierInfo = advertisingIdentifierInfo;
        return this;
    }

    public void putSlot(AdSlot adSlot) {
        if (getAdvertisingIdentifierInfo().hasSISDeviceIdentifier()) {
            adSlot.getMetricsCollector().incrementMetric(MetricType.AD_COUNTER_IDENTIFIED_DEVICE);
        }
        adSlot.setConnectionInfo(this.connectionInfo);
        this.slots.put(Integer.valueOf(adSlot.getSlotNumber()), new LOISlot(adSlot, this, this.logger));
    }

    protected JSONArray getSlots() {
        JSONArray array = new JSONArray();
        for (LOISlot slot : this.slots.values()) {
            array.put(slot.getJSON());
        }
        return array;
    }

    public WebRequest getWebRequest() {
        WebRequest request = this.webRequestFactory.createWebRequest();
        boolean z = isSSLRequired() || request.getUseSecure();
        request.setUseSecure(z);
        request.setExternalLogTag(LOGTAG);
        request.setHttpMethod(HttpMethod.POST);
        request.setHost(this.configuration.getString(ConfigOption.AAX_HOSTNAME));
        request.setPath(this.configuration.getString(ConfigOption.AD_RESOURCE_PATH));
        request.enableLog(true);
        request.setContentType(WebRequest.CONTENT_TYPE_JSON);
        request.setDisconnectEnabled(false);
        setParametersInWebRequest(request);
        return request;
    }

    private boolean isSSLRequired() {
        return !Configuration.getInstance().getBoolean(ConfigOption.TRUNCATE_LAT_LON) && Configuration.getInstance().getBoolean(ConfigOption.SEND_GEO) && getAdTargetingOptions().isGeoLocationEnabled();
    }

    protected void setParametersInWebRequest(WebRequest request) {
        this.jsonObjectBuilder.build();
        Object slots = AAXParameter.SLOTS.getValue(this.jsonObjectBuilder.getParameterData());
        if (slots == null) {
            slots = getSlots();
        }
        this.jsonObjectBuilder.putIntoJSON(AAXParameter.SLOTS, slots);
        JSONObject json = this.jsonObjectBuilder.getJSON();
        String additionalTargetingParams = this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_AD_PARAMS, null);
        if (!StringUtils.isNullOrEmpty(additionalTargetingParams)) {
            request.setAdditionalQueryParamsString(additionalTargetingParams);
        }
        setRequestBodyString(request, json);
    }

    protected void setRequestBodyString(WebRequest request, JSONObject json) {
        request.setRequestBodyString(json.toString());
    }
}
