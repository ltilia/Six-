package com.amazon.device.ads;

import org.json.JSONException;
import org.json.JSONObject;

abstract class AAXParameterGroupParameter {
    static final AdvertisingIdentifierAAXParameter ADVERTISING_IDENTIFIER;
    static final DirectedIdAAXParameter DIRECTED_ID;
    private static final String LOG_TAG;
    static final SHA1UDIDAAXParameter SHA1_UDID;
    static final SISDeviceIdentifierAAXParameter SIS_DEVICE_IDENTIFIER;
    private final String debugName;
    protected final DebugProperties debugProperties;
    private final String key;
    private final MobileAdsLogger logger;

    protected abstract String getDerivedValue(ParameterData parameterData);

    static {
        LOG_TAG = AAXParameterGroupParameter.class.getSimpleName();
        ADVERTISING_IDENTIFIER = new AdvertisingIdentifierAAXParameter();
        SIS_DEVICE_IDENTIFIER = new SISDeviceIdentifierAAXParameter();
        SHA1_UDID = new SHA1UDIDAAXParameter();
        DIRECTED_ID = new DirectedIdAAXParameter();
    }

    AAXParameterGroupParameter(DebugProperties debugProperties, String key, String debugName, MobileAdsLoggerFactory loggerFactory) {
        this.debugProperties = debugProperties;
        this.key = key;
        this.debugName = debugName;
        this.logger = loggerFactory.createMobileAdsLogger(LOG_TAG);
    }

    boolean evaluate(ParameterData parameterData, JSONObject jsonObject) {
        return putIntoJSON(jsonObject, this.key, this.debugProperties.getDebugPropertyAsString(this.debugName, getDerivedValue(parameterData)));
    }

    protected boolean putIntoJSON(JSONObject json, String key, String value) {
        if (!StringUtils.isNullOrEmpty(value)) {
            try {
                json.put(key, value);
                return true;
            } catch (JSONException e) {
                this.logger.d("Could not add parameter to JSON %s: %s", key, value);
            }
        }
        return false;
    }
}
