package com.amazon.device.ads;

import com.amazon.device.ads.PreferredMarketplaceRetriever.NullPreferredMarketplaceRetriever;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.applovin.impl.sdk.NativeAdImpl;
import com.facebook.internal.ServerProtocol;
import com.mopub.mobileads.VungleRewardedVideo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.JSONException;
import org.json.JSONObject;

class Configuration {
    private static final String AAX_MSDK_CONFIG_ENDPOINT = "/msdk/getConfig";
    private static final String AAX_PROD_US_HOSTNAME = "mads.amazon-adsystem.com";
    protected static final String CONFIG_APP_DEFINED_MARKETPLACE = "config-appDefinedMarketplace";
    protected static final String CONFIG_LASTFETCHTIME = "config-lastFetchTime";
    protected static final String CONFIG_TTL = "config-ttl";
    protected static final String CONFIG_VERSION_NAME = "configVersion";
    protected static final int CURRENT_CONFIG_VERSION = 4;
    private static final String LOGTAG;
    protected static final int MAX_CONFIG_TTL = 172800000;
    protected static final int MAX_NO_RETRY_TTL = 300000;
    private static Configuration instance;
    private String appDefinedMarketplace;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private boolean isAppDefinedMarketplaceSet;
    private final AtomicBoolean isFetching;
    private boolean isFirstParty;
    private Boolean lastTestModeValue;
    private final List<ConfigurationListener> listeners;
    private final MobileAdsLogger logger;
    private final Metrics metrics;
    private final PermissionChecker permissionChecker;
    private PreferredMarketplaceRetriever pfmRetriever;
    private final Settings settings;
    private final SystemTime systemTime;
    private final ThreadRunner threadRunner;
    private final ViewabilityJavascriptFetcher viewabilityJavascriptFetcher;
    private final WebRequestFactory webRequestFactory;
    private final WebRequestUserId webRequestUserId;

    interface ConfigurationListener {
        void onConfigurationFailure();

        void onConfigurationReady();
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            Configuration.this.fetchConfigurationOnBackgroundThread();
        }
    }

    public static class ConfigOption {
        public static final ConfigOption AAX_HOSTNAME;
        public static final ConfigOption AD_PREF_URL;
        public static final ConfigOption AD_RESOURCE_PATH;
        public static final ConfigOption BASE_URL;
        public static final ConfigOption DEBUG_PROPERTIES;
        public static final ConfigOption IDENTIFY_USER_INTERVAL;
        public static final ConfigOption MADS_HOSTNAME;
        public static final ConfigOption SEND_GEO;
        public static final ConfigOption SIS_DOMAIN;
        public static final ConfigOption SIS_URL;
        public static final ConfigOption TRUNCATE_LAT_LON;
        public static final ConfigOption VIEWABLE_INTERVAL;
        public static final ConfigOption VIEWABLE_JAVASCRIPT_URL;
        public static final ConfigOption VIEWABLE_JS_VERSION_CONFIG;
        public static final ConfigOption WHITELISTED_CUSTOMER;
        public static final ConfigOption[] configOptions;
        private final boolean allowEmpty;
        private final Class<?> dataType;
        private final String responseKey;
        private final String settingsName;

        static {
            AAX_HOSTNAME = new ConfigOption("config-aaxHostname", String.class, "aaxHostname");
            AD_RESOURCE_PATH = new ConfigOption("config-adResourcePath", String.class, "adResourcePath");
            SIS_URL = new ConfigOption("config-sisURL", String.class, "sisURL");
            AD_PREF_URL = new ConfigOption("config-adPrefURL", String.class, "adPrefURL");
            MADS_HOSTNAME = new ConfigOption("config-madsHostname", String.class, "madsHostname", true);
            SIS_DOMAIN = new ConfigOption("config-sisDomain", String.class, "sisDomain");
            SEND_GEO = new ConfigOption("config-sendGeo", Boolean.class, "sendGeo");
            TRUNCATE_LAT_LON = new ConfigOption("config-truncateLatLon", Boolean.class, "truncateLatLon");
            WHITELISTED_CUSTOMER = new ConfigOption("config-whitelistedCustomer", Boolean.class, "whitelistedCustomer");
            IDENTIFY_USER_INTERVAL = new ConfigOption("config-identifyUserInterval", Long.class, "identifyUserInterval");
            VIEWABLE_JAVASCRIPT_URL = new ConfigOption("config-viewableJavascriptCDNURL", String.class, "viewableJavascriptCDNURL");
            VIEWABLE_JS_VERSION_CONFIG = new ConfigOption("config-viewableJSVersionConfig", Integer.class, "viewableJSVersion");
            VIEWABLE_INTERVAL = new ConfigOption("config-viewableInterval", Long.class, "viewableInterval", true);
            DEBUG_PROPERTIES = new ConfigOption("config-debugProperties", JSONObject.class, "debugProperties", true);
            BASE_URL = new ConfigOption("config-baseURL", String.class, "baseURL", true);
            configOptions = new ConfigOption[]{AAX_HOSTNAME, AD_RESOURCE_PATH, SIS_URL, AD_PREF_URL, MADS_HOSTNAME, SIS_DOMAIN, SEND_GEO, TRUNCATE_LAT_LON, WHITELISTED_CUSTOMER, IDENTIFY_USER_INTERVAL, VIEWABLE_JAVASCRIPT_URL, VIEWABLE_JS_VERSION_CONFIG, DEBUG_PROPERTIES, VIEWABLE_INTERVAL, BASE_URL};
        }

        protected ConfigOption(String settingsName, Class<?> dataType, String responseKey) {
            this(settingsName, dataType, responseKey, false);
        }

        protected ConfigOption(String settingsName, Class<?> dataType, String responseKey, boolean allowEmpty) {
            this.settingsName = settingsName;
            this.responseKey = responseKey;
            this.dataType = dataType;
            this.allowEmpty = allowEmpty;
        }

        String getSettingsName() {
            return this.settingsName;
        }

        String getResponseKey() {
            return this.responseKey;
        }

        Class<?> getDataType() {
            return this.dataType;
        }

        boolean getAllowEmpty() {
            return this.allowEmpty;
        }
    }

    static {
        LOGTAG = Configuration.class.getSimpleName();
        instance = new Configuration();
    }

    protected Configuration() {
        this(new MobileAdsLoggerFactory(), new PermissionChecker(), new WebRequestFactory(), DebugProperties.getInstance(), Settings.getInstance(), MobileAdsInfoStore.getInstance(), new SystemTime(), Metrics.getInstance(), ThreadUtils.getThreadRunner(), new WebRequestUserId());
    }

    Configuration(MobileAdsLoggerFactory loggerFactory, PermissionChecker permissionChecker, WebRequestFactory webRequestFactory, DebugProperties debugProperties, Settings settings, MobileAdsInfoStore infoStore, SystemTime systemTime, Metrics metrics, ThreadRunner threadRunner, WebRequestUserId webRequestUserId) {
        this(loggerFactory, permissionChecker, webRequestFactory, debugProperties, settings, infoStore, systemTime, metrics, threadRunner, webRequestUserId, new ViewabilityJavascriptFetcher());
    }

    Configuration(MobileAdsLoggerFactory loggerFactory, PermissionChecker permissionChecker, WebRequestFactory webRequestFactory, DebugProperties debugProperties, Settings settings, MobileAdsInfoStore infoStore, SystemTime systemTime, Metrics metrics, ThreadRunner threadRunner, WebRequestUserId webRequestUserId, ViewabilityJavascriptFetcher viewabilityJavascriptFetcher) {
        this.appDefinedMarketplace = null;
        this.isAppDefinedMarketplaceSet = false;
        this.listeners = new ArrayList(5);
        this.isFetching = new AtomicBoolean(false);
        this.lastTestModeValue = null;
        this.isFirstParty = false;
        this.pfmRetriever = new NullPreferredMarketplaceRetriever();
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.webRequestFactory = webRequestFactory;
        this.debugProperties = debugProperties;
        this.settings = settings;
        this.infoStore = infoStore;
        this.systemTime = systemTime;
        this.metrics = metrics;
        this.threadRunner = threadRunner;
        this.webRequestUserId = webRequestUserId;
        this.viewabilityJavascriptFetcher = viewabilityJavascriptFetcher;
    }

    public static final Configuration getInstance() {
        return instance;
    }

    public String getAppDefinedMarketplace() {
        return this.appDefinedMarketplace;
    }

    public void setAppDefinedMarketplace(String appDefinedMarketplace) {
        this.appDefinedMarketplace = appDefinedMarketplace;
        this.isAppDefinedMarketplaceSet = true;
    }

    public void setIsFirstParty(boolean isFirstParty) {
        this.isFirstParty = isFirstParty;
    }

    boolean isFirstParty() {
        return this.isFirstParty;
    }

    public boolean hasValue(ConfigOption configOption) {
        return !StringUtils.isNullOrWhiteSpace(getString(configOption));
    }

    public String getString(ConfigOption configOption) {
        return this.settings.getString(configOption.getSettingsName(), null);
    }

    public String getStringWithDefault(ConfigOption configOption, String defaultValue) {
        return this.settings.getString(configOption.getSettingsName(), defaultValue);
    }

    public boolean getBoolean(ConfigOption configOption) {
        return getBooleanWithDefault(configOption, false);
    }

    public boolean getBooleanWithDefault(ConfigOption configOption, boolean defaultValue) {
        return this.settings.getBoolean(configOption.getSettingsName(), defaultValue);
    }

    public int getInt(ConfigOption configOption) {
        return getIntWithDefault(configOption, 0);
    }

    public int getIntWithDefault(ConfigOption configOption, int defaultValue) {
        return this.settings.getInt(configOption.getSettingsName(), defaultValue);
    }

    public long getLong(ConfigOption configOption) {
        return getLongWithDefault(configOption, 0);
    }

    public long getLongWithDefault(ConfigOption configOption, long defaultValue) {
        return this.settings.getLong(configOption.getSettingsName(), defaultValue);
    }

    public JSONObject getJSONObject(ConfigOption configOption) {
        return getJSONObjectWithDefault(configOption, null);
    }

    public JSONObject getJSONObjectWithDefault(ConfigOption configOption, JSONObject defaultValue) {
        return this.settings.getJSONObject(configOption.getSettingsName(), defaultValue);
    }

    protected boolean shouldFetch() {
        if (hasAppDefinedMarketplaceChanged()) {
            return true;
        }
        if (this.settings.getInt(CONFIG_VERSION_NAME, 0) != CURRENT_CONFIG_VERSION) {
            return true;
        }
        long lastFetchTime = this.settings.getLong(CONFIG_LASTFETCHTIME, 0);
        if (lastFetchTime == 0) {
            this.logger.d("No configuration found. A new configuration will be retrieved.");
            return true;
        }
        long currentTime = this.systemTime.currentTimeMillis();
        if (currentTime - lastFetchTime > this.settings.getLong(CONFIG_TTL, 172800000)) {
            this.logger.d("The configuration has expired. A new configuration will be retrieved.");
            return true;
        } else if (this.settings.getWrittenLong("amzn-ad-iu-last-checkin", 0) - lastFetchTime > 0) {
            this.logger.d("A new user has been identified. A new configuration will be retrieved.");
            return true;
        } else if (this.lastTestModeValue != null && this.lastTestModeValue.booleanValue() != this.settings.getBoolean("testingEnabled", false)) {
            this.logger.d("The testing mode has changed. A new configuration will be retrieved.");
            return true;
        } else if (this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_SHOULD_FETCH_CONFIG, Boolean.valueOf(false)).booleanValue()) {
            return true;
        } else {
            return false;
        }
    }

    private boolean hasAppDefinedMarketplaceChanged() {
        String storedAppDefinedMarketplace = this.settings.getString(CONFIG_APP_DEFINED_MARKETPLACE, null);
        if (!this.isAppDefinedMarketplaceSet) {
            return false;
        }
        this.isAppDefinedMarketplaceSet = false;
        if (this.appDefinedMarketplace != null && !this.appDefinedMarketplace.equals(storedAppDefinedMarketplace)) {
            this.settings.putLongWithNoFlush(CONFIG_LASTFETCHTIME, 0);
            this.settings.putStringWithNoFlush(CONFIG_APP_DEFINED_MARKETPLACE, this.appDefinedMarketplace);
            this.settings.flush();
            this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
            this.logger.d("New application-defined marketplace set. A new configuration will be retrieved.");
            return true;
        } else if (storedAppDefinedMarketplace == null || this.appDefinedMarketplace != null) {
            return false;
        } else {
            this.settings.remove(CONFIG_APP_DEFINED_MARKETPLACE);
            this.infoStore.getRegistrationInfo().requestNewSISDeviceIdentifier();
            this.logger.d("Application-defined marketplace removed. A new configuration will be retrieved.");
            return true;
        }
    }

    protected boolean isFetching() {
        return this.isFetching.get();
    }

    protected void setIsFetching(boolean isFetching) {
        this.isFetching.set(isFetching);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener listener) {
        queueConfigurationListener(listener, true);
    }

    public synchronized void queueConfigurationListener(ConfigurationListener listener, boolean allowFetch) {
        if (isFetching()) {
            this.listeners.add(listener);
        } else if (shouldFetch()) {
            this.listeners.add(listener);
            if (allowFetch) {
                this.logger.d("Starting configuration fetching...");
                setIsFetching(true);
                beginFetch();
            }
        } else {
            listener.onConfigurationReady();
        }
    }

    protected void beginFetch() {
        this.threadRunner.execute(new 1(), ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
    }

    protected synchronized void onFetchSuccess() {
        this.viewabilityJavascriptFetcher.fetchJavascript();
        setIsFetching(false);
        for (ConfigurationListener listener : getAndClearListeners()) {
            listener.onConfigurationReady();
        }
    }

    protected synchronized void onFetchFailure() {
        this.metrics.getMetricsCollector().incrementMetric(MetricType.AAX_CONFIG_DOWNLOAD_FAILED);
        setIsFetching(false);
        for (ConfigurationListener listener : getAndClearListeners()) {
            listener.onConfigurationFailure();
        }
    }

    protected synchronized ConfigurationListener[] getAndClearListeners() {
        ConfigurationListener[] toCall;
        toCall = (ConfigurationListener[]) this.listeners.toArray(new ConfigurationListener[this.listeners.size()]);
        this.listeners.clear();
        return toCall;
    }

    protected ConfigOption[] getConfigOptions() {
        return ConfigOption.configOptions;
    }

    protected void setLastTestModeValue(boolean testMode) {
        this.lastTestModeValue = Boolean.valueOf(testMode);
    }

    protected void fetchConfigurationOnBackgroundThread() {
        this.logger.d("In configuration fetcher background thread.");
        if (this.permissionChecker.hasInternetPermission(this.infoStore.getApplicationContext())) {
            WebRequest webRequest = createWebRequest();
            if (webRequest == null) {
                onFetchFailure();
                return;
            }
            try {
                JSONObject json = webRequest.makeCall().getResponseReader().readAsJSON();
                try {
                    for (ConfigOption configOption : getConfigOptions()) {
                        if (!json.isNull(configOption.getResponseKey())) {
                            writeSettingFromConfigOption(configOption, json);
                        } else if (configOption.getAllowEmpty()) {
                            this.settings.removeWithNoFlush(configOption.getSettingsName());
                        } else {
                            throw new Exception("The configuration value for " + configOption.getResponseKey() + " must be present and not null.");
                        }
                    }
                    if (json.isNull(ConfigOption.DEBUG_PROPERTIES.getResponseKey())) {
                        this.settings.removeWithNoFlush(ConfigOption.DEBUG_PROPERTIES.getSettingsName());
                        this.debugProperties.clearDebugProperties();
                    } else {
                        this.debugProperties.overwriteDebugProperties(json.getJSONObject(ConfigOption.DEBUG_PROPERTIES.getResponseKey()));
                    }
                    if (json.isNull("ttl")) {
                        throw new Exception("The configuration value must be present and not null.");
                    }
                    long ttl = NumberUtils.convertToMillisecondsFromSeconds((long) json.getInt("ttl"));
                    if (ttl > 172800000) {
                        ttl = 172800000;
                    }
                    this.settings.putLongWithNoFlush(CONFIG_TTL, ttl);
                    this.settings.putLongWithNoFlush(CONFIG_LASTFETCHTIME, this.systemTime.currentTimeMillis());
                    this.settings.putIntWithNoFlush(CONFIG_VERSION_NAME, CURRENT_CONFIG_VERSION);
                    this.settings.flush();
                    this.logger.d("Configuration fetched and saved.");
                    onFetchSuccess();
                    return;
                } catch (JSONException e) {
                    this.logger.e("Unable to parse JSON response: %s", e.getMessage());
                    onFetchFailure();
                    return;
                } catch (Exception e2) {
                    this.logger.e("Unexpected error during parsing: %s", e2.getMessage());
                    onFetchFailure();
                    return;
                }
            } catch (WebRequestException e3) {
                onFetchFailure();
                return;
            }
        }
        this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
        onFetchFailure();
    }

    private void writeSettingFromConfigOption(ConfigOption configOption, JSONObject json) throws Exception {
        if (configOption.getDataType().equals(String.class)) {
            String value = json.getString(configOption.getResponseKey());
            if (configOption.getAllowEmpty() || !StringUtils.isNullOrWhiteSpace(value)) {
                this.settings.putStringWithNoFlush(configOption.getSettingsName(), value);
                return;
            }
            throw new IllegalArgumentException("The configuration value must not be empty or contain only white spaces.");
        } else if (configOption.getDataType().equals(Boolean.class)) {
            this.settings.putBooleanWithNoFlush(configOption.getSettingsName(), json.getBoolean(configOption.getResponseKey()));
        } else if (configOption.getDataType().equals(Integer.class)) {
            this.settings.putIntWithNoFlush(configOption.getSettingsName(), json.getInt(configOption.getResponseKey()));
        } else if (configOption.getDataType().equals(Long.class)) {
            this.settings.putLongWithNoFlush(configOption.getSettingsName(), json.getLong(configOption.getResponseKey()));
        } else if (configOption.getDataType().equals(JSONObject.class)) {
            this.settings.putJSONObjectWithNoFlush(configOption.getSettingsName(), json.getJSONObject(configOption.getResponseKey()));
        } else {
            throw new IllegalArgumentException("Undefined configuration option type.");
        }
    }

    protected WebRequest createWebRequest() {
        WebRequest request = this.webRequestFactory.createJSONGetWebRequest();
        request.setExternalLogTag(LOGTAG);
        request.enableLog(true);
        request.setHost(this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_CONFIG_HOSTNAME, AAX_PROD_US_HOSTNAME));
        request.setPath(AAX_MSDK_CONFIG_ENDPOINT);
        request.setMetricsCollector(this.metrics.getMetricsCollector());
        request.setServiceCallLatencyMetric(MetricType.AAX_CONFIG_DOWNLOAD_LATENCY);
        request.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_AAX_CONFIG_USE_SECURE, Boolean.valueOf(true)).booleanValue());
        RegistrationInfo registrationInfo = this.infoStore.getRegistrationInfo();
        DeviceInfo deviceInfo = this.infoStore.getDeviceInfo();
        request.putUnencodedQueryParameter(VungleRewardedVideo.APP_ID_KEY, registrationInfo.getAppKey());
        request.putUnencodedQueryParameter("dinfo", deviceInfo.getDInfoProperty().toString());
        request.putUnencodedQueryParameter("sdkVer", Version.getSDKVersion());
        request.putUnencodedQueryParameter(NativeAdImpl.QUERY_PARAM_IS_FIRST_PLAY, Boolean.toString(this.isFirstParty));
        request.putUnencodedQueryParameter("mkt", this.settings.getString(CONFIG_APP_DEFINED_MARKETPLACE, null));
        request.putUnencodedQueryParameter("pfm", getPreferredMarketplace());
        boolean testingEnabled = this.settings.getBoolean("testingEnabled", false);
        setLastTestModeValue(testingEnabled);
        if (testingEnabled) {
            request.putUnencodedQueryParameter("testMode", ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
        }
        request.setAdditionalQueryParamsString(this.debugProperties.getDebugPropertyAsString(DebugProperties.DEBUG_AAX_CONFIG_PARAMS, null));
        if (this.webRequestUserId.populateWebRequestUserId(request)) {
            return request;
        }
        return null;
    }

    public void setPreferredMarketplaceRetriever(PreferredMarketplaceRetriever pfmRetriever) {
        this.pfmRetriever = pfmRetriever;
    }

    PreferredMarketplaceRetriever getPreferredMarketplaceRetriever() {
        return this.pfmRetriever;
    }

    private String getPreferredMarketplace() {
        return this.pfmRetriever.retrievePreferredMarketplace(MobileAdsInfoStore.getInstance().getApplicationContext());
    }
}
