package com.amazon.device.ads;

import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.ThreadUtils.ExecutionStyle;
import com.amazon.device.ads.ThreadUtils.ExecutionThread;
import com.amazon.device.ads.ThreadUtils.ThreadRunner;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;

class ViewabilityJavascriptFetcher {
    private static final String CDN_URL = "https://dwxjayoxbnyrr.cloudfront.net/amazon-ads.viewablejs";
    private static final String LOGTAG;
    private static ViewabilityJavascriptFetcher instance;
    private int currentJSVersion;
    private final DebugProperties debugProperties;
    private final MobileAdsInfoStore infoStore;
    private final MobileAdsLogger logger;
    private final Metrics metrics;
    private final PermissionChecker permissionChecker;
    private final Settings settings;
    private final ThreadRunner threadRunner;
    private final WebRequestFactory webRequestFactory;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            ViewabilityJavascriptFetcher.this.fetchJavascriptFromURLOnBackgroundThread();
        }
    }

    static {
        LOGTAG = ViewabilityJavascriptFetcher.class.getSimpleName();
        instance = new ViewabilityJavascriptFetcher();
    }

    protected ViewabilityJavascriptFetcher() {
        this(new MobileAdsLoggerFactory(), new PermissionChecker(), DebugProperties.getInstance(), Settings.getInstance(), new WebRequestFactory(), Metrics.getInstance(), ThreadUtils.getThreadRunner(), MobileAdsInfoStore.getInstance());
    }

    ViewabilityJavascriptFetcher(MobileAdsLoggerFactory mobileAdsLoggerFactory, PermissionChecker permissionChecker, DebugProperties debugProperties, Settings settings, WebRequestFactory webRequestFactory, Metrics metrics, ThreadRunner threadRunner, MobileAdsInfoStore mobileAdsInfoStore) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.permissionChecker = permissionChecker;
        this.debugProperties = debugProperties;
        this.settings = settings;
        this.webRequestFactory = webRequestFactory;
        this.metrics = metrics;
        this.threadRunner = threadRunner;
        this.infoStore = mobileAdsInfoStore;
    }

    private boolean shouldFetch() {
        this.currentJSVersion = this.settings.getInt(ConfigOption.VIEWABLE_JS_VERSION_CONFIG.getSettingsName(), 0);
        if (this.settings.getInt("viewableJSVersionStored", -1) >= this.currentJSVersion && !StringUtils.isNullOrEmpty(this.settings.getString("viewableJSSettingsNameAmazonAdSDK", null))) {
            return false;
        }
        return true;
    }

    public void fetchJavascript() {
        if (shouldFetch()) {
            beginFetch();
        }
    }

    protected void beginFetch() {
        this.threadRunner.execute(new 1(), ExecutionStyle.SCHEDULE, ExecutionThread.BACKGROUND_THREAD);
    }

    public void fetchJavascriptFromURLOnBackgroundThread() {
        this.logger.d("In ViewabilityJavascriptFetcher background thread");
        if (this.permissionChecker.hasInternetPermission(this.infoStore.getApplicationContext())) {
            WebRequest webRequest = createWebRequest();
            if (webRequest == null) {
                onFetchFailure();
                return;
            }
            try {
                this.settings.putString("viewableJSSettingsNameAmazonAdSDK", webRequest.makeCall().getResponseReader().readAsString());
                this.settings.putInt("viewableJSVersionStored", this.currentJSVersion);
                this.logger.d("Viewability Javascript fetched and saved");
                return;
            } catch (WebRequestException e) {
                onFetchFailure();
                return;
            }
        }
        this.logger.e("Network task cannot commence because the INTERNET permission is missing from the app's manifest.");
        onFetchFailure();
    }

    protected WebRequest createWebRequest() {
        WebRequest webRequest = this.webRequestFactory.createWebRequest();
        webRequest.setExternalLogTag(LOGTAG);
        webRequest.enableLog(true);
        webRequest.setUrlString(this.settings.getString(ConfigOption.VIEWABLE_JAVASCRIPT_URL.getSettingsName(), CDN_URL));
        webRequest.setMetricsCollector(this.metrics.getMetricsCollector());
        webRequest.setServiceCallLatencyMetric(MetricType.CDN_JAVASCRIPT_DOWLOAD_LATENCY);
        webRequest.setUseSecure(this.debugProperties.getDebugPropertyAsBoolean(DebugProperties.DEBUG_AAX_CONFIG_USE_SECURE, Boolean.valueOf(true)).booleanValue());
        return webRequest;
    }

    private void onFetchFailure() {
        this.metrics.getMetricsCollector().incrementMetric(MetricType.CDN_JAVASCRIPT_DOWNLOAD_FAILED);
        this.logger.w("Viewability Javascript fetch failed");
    }

    public static final ViewabilityJavascriptFetcher getInstance() {
        return instance;
    }
}
