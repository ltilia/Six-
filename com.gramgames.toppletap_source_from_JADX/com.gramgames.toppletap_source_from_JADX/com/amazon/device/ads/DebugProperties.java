package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils.JSONUtilities;
import java.util.Properties;
import org.json.JSONObject;

class DebugProperties {
    public static final String DEBUG_AAX_AD_PARAMS = "debug.aaxAdParams";
    public static final String DEBUG_AAX_CONFIG_HOSTNAME = "debug.aaxConfigHostname";
    public static final String DEBUG_AAX_CONFIG_PARAMS = "debug.aaxConfigParams";
    public static final String DEBUG_AAX_CONFIG_USE_SECURE = "debug.aaxConfigUseSecure";
    public static final String DEBUG_ADID = "debug.adid";
    public static final String DEBUG_ADVTARGETING = "debug.advTargeting";
    public static final String DEBUG_APPID = "debug.appid";
    public static final String DEBUG_CAN_TIMEOUT = "debug.canTimeout";
    public static final String DEBUG_CHANNEL = "debug.channel";
    public static final String DEBUG_CONFIG_FEATURE_USE_GPS_ADVERTISING_ID = "debug.fUseGPSAID";
    public static final String DEBUG_DINFO = "debug.dinfo";
    public static final String DEBUG_DIRECTEDID = "debug.directedId";
    public static final String DEBUG_ECPM = "debug.ec";
    public static final String DEBUG_GEOLOC = "debug.geoloc";
    public static final String DEBUG_IDFA = "debug.idfa";
    public static final String DEBUG_LOGGING = "debug.logging";
    public static final String DEBUG_MADS_USE_SECURE = "debug.madsUseSecure";
    public static final String DEBUG_MD5UDID = "debug.md5udid";
    public static final String DEBUG_MXSZ = "debug.mxsz";
    public static final String DEBUG_NORETRYTTL = "debug.noRetryTTL";
    public static final String DEBUG_NORETRYTTL_MAX = "debug.noRetryTTLMax";
    public static final String DEBUG_OPT_OUT = "debug.optOut";
    public static final String DEBUG_PA = "debug.pa";
    public static final String DEBUG_PK = "debug.pk";
    public static final String DEBUG_PKG = "debug.pkg";
    public static final String DEBUG_PT = "debug.pt";
    public static final String DEBUG_SHA1UDID = "debug.sha1udid";
    public static final String DEBUG_SHOULD_FETCH_CONFIG = "debug.shouldFetchConfig";
    public static final String DEBUG_SHOULD_IDENTIFY_USER = "debug.shouldIdentifyUser";
    public static final String DEBUG_SHOULD_REGISTER_SIS = "debug.shouldRegisterSIS";
    public static final String DEBUG_SIS_CHECKIN_INTERVAL = "debug.sisCheckinInterval";
    public static final String DEBUG_SIZE = "debug.size";
    public static final String DEBUG_SLOT = "debug.slot";
    public static final String DEBUG_SLOTS = "debug.slots";
    public static final String DEBUG_SLOT_ID = "debug.slotId";
    public static final String DEBUG_SP = "debug.sp";
    public static final String DEBUG_SUPPORTED_MEDIA_TYPES = "debug.supportedMediaTypes";
    public static final String DEBUG_TEST = "debug.test";
    public static final String DEBUG_UA = "debug.ua";
    public static final String DEBUG_USESECURE = "debug.useSecure";
    public static final String DEBUG_VER = "debug.ver";
    public static final String DEBUG_VIDEO_OPTIONS = "debug.videoOptions";
    public static final String DEBUG_VIEWABLE_INTERVAL = "debug.viewableInterval";
    public static final String DEBUG_WEBVIEWS = "debug.webViews";
    private static final String LOGTAG;
    private static final DebugProperties instance;
    private final Properties debugProperties;
    private final JSONUtilities jsonUtilities;
    private final MobileAdsLogger logger;

    static {
        instance = new DebugProperties();
        LOGTAG = DebugProperties.class.getSimpleName();
    }

    public DebugProperties() {
        this(new JSONUtilities(), new MobileAdsLoggerFactory());
    }

    DebugProperties(JSONUtilities jsonUtilities, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.debugProperties = new Properties();
        this.jsonUtilities = jsonUtilities;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public static DebugProperties getInstance() {
        return instance;
    }

    public String getDebugPropertyAsString(String property, String defaultValue) {
        return this.debugProperties.getProperty(property, defaultValue);
    }

    public Integer getDebugPropertyAsInteger(String property, Integer defaultValue) {
        String propertyValue = this.debugProperties.getProperty(property);
        if (propertyValue != null) {
            try {
                defaultValue = Integer.valueOf(Integer.parseInt(propertyValue));
            } catch (NumberFormatException e) {
                this.logger.e("Unable to parse integer debug property - property: %s, value: %s", property, propertyValue);
            }
        }
        return defaultValue;
    }

    public Boolean getDebugPropertyAsBoolean(String property, Boolean defaultValue) {
        String propertyValue = this.debugProperties.getProperty(property);
        if (propertyValue != null) {
            try {
                defaultValue = Boolean.valueOf(Boolean.parseBoolean(propertyValue));
            } catch (NumberFormatException e) {
                this.logger.e("Unable to parse boolean debug property - property: %s, value: %s", property, propertyValue);
            }
        }
        return defaultValue;
    }

    public Long getDebugPropertyAsLong(String property, Long defaultValue) {
        String propertyValue = this.debugProperties.getProperty(property);
        if (propertyValue != null) {
            try {
                defaultValue = Long.valueOf(Long.parseLong(propertyValue));
            } catch (NumberFormatException e) {
                this.logger.e("Unable to parse long debug property - property: %s, value: %s", property, propertyValue);
            }
        }
        return defaultValue;
    }

    public JSONObject getDebugPropertyAsJSONObject(String property, JSONObject defaultValue) {
        String propertyValue = this.debugProperties.getProperty(property);
        return propertyValue == null ? defaultValue : this.jsonUtilities.getJSONObjectFromString(propertyValue);
    }

    public boolean containsDebugProperty(String property) {
        return this.debugProperties.containsKey(property);
    }

    public void setDebugProperty(String property, String value) {
        this.debugProperties.put(property, value);
    }

    public void clearDebugProperties() {
        this.debugProperties.clear();
    }

    public void overwriteDebugProperties(JSONObject json) {
        clearDebugProperties();
        this.debugProperties.putAll(this.jsonUtilities.createMapFromJSON(json));
    }
}
