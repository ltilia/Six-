package com.adjust.sdk;

import android.text.TextUtils;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.internal.NativeProtocol;
import com.facebook.share.internal.ShareConstants;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONObject;

class PackageBuilder {
    private static ILogger logger;
    private ActivityState activityState;
    private AdjustConfig adjustConfig;
    AdjustAttribution attribution;
    private long createdAt;
    private DeviceInfo deviceInfo;
    Map<String, String> extraParameters;
    String referrer;
    String reftag;

    static {
        logger = AdjustFactory.getLogger();
    }

    public PackageBuilder(AdjustConfig adjustConfig, DeviceInfo deviceInfo, ActivityState activityState, long createdAt) {
        this.adjustConfig = adjustConfig;
        this.deviceInfo = deviceInfo;
        this.activityState = activityState == null ? null : activityState.shallowCopy();
        this.createdAt = createdAt;
    }

    public ActivityPackage buildSessionPackage() {
        Map<String, String> parameters = getDefaultParameters();
        addDuration(parameters, "last_interval", this.activityState.lastInterval);
        addString(parameters, "default_tracker", this.adjustConfig.defaultTracker);
        ActivityPackage sessionPackage = getDefaultActivityPackage(ActivityKind.SESSION);
        sessionPackage.setPath("/session");
        sessionPackage.setSuffix(BuildConfig.FLAVOR);
        sessionPackage.setParameters(parameters);
        return sessionPackage;
    }

    public ActivityPackage buildEventPackage(AdjustEvent event) {
        Map<String, String> parameters = getDefaultParameters();
        addInt(parameters, "event_count", (long) this.activityState.eventCount);
        addString(parameters, "event_token", event.eventToken);
        addDouble(parameters, "revenue", event.revenue);
        addString(parameters, AppLovinEventParameters.REVENUE_CURRENCY, event.currency);
        addMapJson(parameters, "callback_params", event.callbackParameters);
        addMapJson(parameters, "partner_params", event.partnerParameters);
        ActivityPackage eventPackage = getDefaultActivityPackage(ActivityKind.EVENT);
        eventPackage.setPath("/event");
        eventPackage.setSuffix(getEventSuffix(event));
        eventPackage.setParameters(parameters);
        return eventPackage;
    }

    public ActivityPackage buildClickPackage(String source, long clickTime) {
        Map<String, String> parameters = getIdsParameters();
        addString(parameters, ShareConstants.FEED_SOURCE_PARAM, source);
        addDate(parameters, "click_time", clickTime);
        addString(parameters, Constants.REFTAG, this.reftag);
        addMapJson(parameters, NativeProtocol.WEB_DIALOG_PARAMS, this.extraParameters);
        addString(parameters, Constants.REFERRER, this.referrer);
        injectAttribution(parameters);
        ActivityPackage clickPackage = getDefaultActivityPackage(ActivityKind.CLICK);
        clickPackage.setPath("/sdk_click");
        clickPackage.setSuffix(BuildConfig.FLAVOR);
        clickPackage.setParameters(parameters);
        return clickPackage;
    }

    public ActivityPackage buildAttributionPackage() {
        Map<String, String> parameters = getIdsParameters();
        ActivityPackage attributionPackage = getDefaultActivityPackage(ActivityKind.ATTRIBUTION);
        attributionPackage.setPath("attribution");
        attributionPackage.setSuffix(BuildConfig.FLAVOR);
        attributionPackage.setParameters(parameters);
        return attributionPackage;
    }

    private ActivityPackage getDefaultActivityPackage(ActivityKind activityKind) {
        ActivityPackage activityPackage = new ActivityPackage(activityKind);
        activityPackage.setClientSdk(this.deviceInfo.clientSdk);
        return activityPackage;
    }

    private Map<String, String> getDefaultParameters() {
        Map<String, String> parameters = new HashMap();
        injectDeviceInfo(parameters);
        injectConfig(parameters);
        injectActivityState(parameters);
        injectCreatedAt(parameters);
        checkDeviceIds(parameters);
        return parameters;
    }

    private Map<String, String> getIdsParameters() {
        Map<String, String> parameters = new HashMap();
        injectDeviceInfoIds(parameters);
        injectConfig(parameters);
        injectCreatedAt(parameters);
        checkDeviceIds(parameters);
        return parameters;
    }

    private void injectDeviceInfo(Map<String, String> parameters) {
        injectDeviceInfoIds(parameters);
        addString(parameters, "fb_id", this.deviceInfo.fbAttributionId);
        addString(parameters, "package_name", this.deviceInfo.packageName);
        addString(parameters, "app_version", this.deviceInfo.appVersion);
        addString(parameters, "device_type", this.deviceInfo.deviceType);
        addString(parameters, "device_name", this.deviceInfo.deviceName);
        addString(parameters, "device_manufacturer", this.deviceInfo.deviceManufacturer);
        addString(parameters, "os_name", this.deviceInfo.osName);
        addString(parameters, "os_version", this.deviceInfo.osVersion);
        addString(parameters, "api_level", this.deviceInfo.apiLevel);
        addString(parameters, "language", this.deviceInfo.language);
        addString(parameters, "country", this.deviceInfo.country);
        addString(parameters, "screen_size", this.deviceInfo.screenSize);
        addString(parameters, "screen_format", this.deviceInfo.screenFormat);
        addString(parameters, "screen_density", this.deviceInfo.screenDensity);
        addString(parameters, "display_width", this.deviceInfo.displayWidth);
        addString(parameters, "display_height", this.deviceInfo.displayHeight);
        fillPluginKeys(parameters);
    }

    private void injectDeviceInfoIds(Map<String, String> parameters) {
        addString(parameters, "mac_sha1", this.deviceInfo.macSha1);
        addString(parameters, "mac_md5", this.deviceInfo.macShortMd5);
        addString(parameters, "android_id", this.deviceInfo.androidId);
    }

    private void injectConfig(Map<String, String> parameters) {
        addString(parameters, "app_token", this.adjustConfig.appToken);
        addString(parameters, "environment", this.adjustConfig.environment);
        addBoolean(parameters, "device_known", this.adjustConfig.deviceKnown);
        addBoolean(parameters, "needs_response_details", Boolean.valueOf(this.adjustConfig.hasListener()));
        addString(parameters, "gps_adid", Util.getPlayAdId(this.adjustConfig.context));
        addBoolean(parameters, "tracking_enabled", Util.isPlayTrackingEnabled(this.adjustConfig.context));
    }

    private void injectActivityState(Map<String, String> parameters) {
        addString(parameters, "android_uuid", this.activityState.uuid);
        addInt(parameters, "session_count", (long) this.activityState.sessionCount);
        addInt(parameters, "subsession_count", (long) this.activityState.subsessionCount);
        addDuration(parameters, "session_length", this.activityState.sessionLength);
        addDuration(parameters, "time_spent", this.activityState.timeSpent);
    }

    private void injectCreatedAt(Map<String, String> parameters) {
        addDate(parameters, "created_at", this.createdAt);
    }

    private void injectAttribution(Map<String, String> parameters) {
        if (this.attribution != null) {
            addString(parameters, "tracker", this.attribution.trackerName);
            addString(parameters, "campaign", this.attribution.campaign);
            addString(parameters, "adgroup", this.attribution.adgroup);
            addString(parameters, "creative", this.attribution.creative);
        }
    }

    private void checkDeviceIds(Map<String, String> parameters) {
        if (!parameters.containsKey("mac_sha1") && !parameters.containsKey("mac_md5") && !parameters.containsKey("android_id") && !parameters.containsKey("gps_adid")) {
            logger.error("Missing device id's. Please check if Proguard is correctly set with Adjust SDK", new Object[0]);
        }
    }

    private void fillPluginKeys(Map<String, String> parameters) {
        if (this.deviceInfo.pluginKeys != null) {
            for (Entry<String, String> entry : this.deviceInfo.pluginKeys.entrySet()) {
                addString(parameters, (String) entry.getKey(), (String) entry.getValue());
            }
        }
    }

    private String getEventSuffix(AdjustEvent event) {
        if (event.revenue == null) {
            return String.format(Locale.US, "'%s'", new Object[]{event.eventToken});
        }
        return String.format(Locale.US, "(%.5f %s, '%s')", new Object[]{event.revenue, event.currency, event.eventToken});
    }

    private void addString(Map<String, String> parameters, String key, String value) {
        if (!TextUtils.isEmpty(value)) {
            parameters.put(key, value);
        }
    }

    private void addInt(Map<String, String> parameters, String key, long value) {
        if (value >= 0) {
            addString(parameters, key, Long.toString(value));
        }
    }

    private void addDate(Map<String, String> parameters, String key, long value) {
        if (value >= 0) {
            addString(parameters, key, Util.dateFormat(value));
        }
    }

    private void addDuration(Map<String, String> parameters, String key, long durationInMilliSeconds) {
        if (durationInMilliSeconds >= 0) {
            addInt(parameters, key, (500 + durationInMilliSeconds) / 1000);
        }
    }

    private void addMapJson(Map<String, String> parameters, String key, Map<String, String> map) {
        if (map != null && map.size() != 0) {
            addString(parameters, key, new JSONObject(map).toString());
        }
    }

    private void addBoolean(Map<String, String> parameters, String key, Boolean value) {
        if (value != null) {
            addInt(parameters, key, (long) (value.booleanValue() ? 1 : 0));
        }
    }

    private void addDouble(Map<String, String> parameters, String key, Double value) {
        if (value != null) {
            addString(parameters, key, String.format(Locale.US, "%.5f", new Object[]{value}));
        }
    }
}
