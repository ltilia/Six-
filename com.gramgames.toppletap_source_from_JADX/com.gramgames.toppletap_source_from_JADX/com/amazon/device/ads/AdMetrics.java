package com.amazon.device.ads;

import com.amazon.device.ads.WebRequest.WebRequestFactory;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Map.Entry;
import org.json.JSONObject;

class AdMetrics {
    public static final String LOGTAG;
    private MetricsCollector globalMetrics;
    private final MobileAdsLogger logger;
    private final MobileAdsInfoStore mobileAdsInfoStore;
    private final MetricsSubmitter submitter;
    private final WebRequestFactory webRequestFactory;

    static {
        LOGTAG = AdMetrics.class.getSimpleName();
    }

    public AdMetrics(MetricsSubmitter submitter) {
        this(submitter, MobileAdsInfoStore.getInstance());
    }

    AdMetrics(MetricsSubmitter submitter, MobileAdsInfoStore mobileAdsInfoStore) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.webRequestFactory = new WebRequestFactory();
        this.submitter = submitter;
        this.mobileAdsInfoStore = mobileAdsInfoStore;
    }

    private String getAaxUrlAndResetAdMetrics() {
        String url = this.submitter.getInstrumentationPixelUrl() + WebUtils.getURLEncodedString(getAaxJson());
        this.submitter.resetMetricsCollector();
        return url;
    }

    public WebRequest getAaxWebRequestAndResetAdMetrics() {
        WebRequest webRequest = this.webRequestFactory.createWebRequest();
        webRequest.setUrlString(getAaxUrlAndResetAdMetrics());
        return webRequest;
    }

    public boolean canSubmit() {
        String pixelUrl = this.submitter.getInstrumentationPixelUrl();
        if (pixelUrl == null || pixelUrl.equals(BuildConfig.FLAVOR)) {
            return false;
        }
        if (this.mobileAdsInfoStore.getRegistrationInfo().getAppKey() != null) {
            return true;
        }
        this.logger.d("Not submitting metrics because the AppKey is not set.");
        return false;
    }

    public void addGlobalMetrics(MetricsCollector globalMetrics) {
        this.globalMetrics = globalMetrics;
    }

    protected String getAaxJson() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, "c", "msdk");
        JSONUtils.put(json, "v", Version.getRawSDKVersion());
        addMetricsToJSON(json, this.submitter.getMetricsCollector());
        addMetricsToJSON(json, this.globalMetrics);
        String jsonString = json.toString();
        return jsonString.substring(1, jsonString.length() - 1);
    }

    protected static void addMetricsToJSON(JSONObject json, MetricsCollector metricsCollector) {
        if (metricsCollector != null) {
            String metricName;
            HashMap<MetricType, Long> startedMetricHits = new HashMap();
            HashMap<MetricType, Integer> incrementedMetricHits = new HashMap();
            String adTypeMetricTag = metricsCollector.getAdTypeMetricTag();
            if (adTypeMetricTag != null) {
                adTypeMetricTag = adTypeMetricTag + "_";
            }
            for (MetricHit metricHit : (MetricHit[]) metricsCollector.getMetricHits().toArray(new MetricHit[metricsCollector.getMetricHits().size()])) {
                metricName = metricHit.metric.getAaxName();
                if (adTypeMetricTag != null) {
                    if (metricHit.metric.isAdTypeSpecific()) {
                        metricName = adTypeMetricTag + metricName;
                    }
                }
                if (metricHit instanceof MetricHitStartTime) {
                    startedMetricHits.put(metricHit.metric, Long.valueOf(((MetricHitStartTime) metricHit).startTime));
                } else if (metricHit instanceof MetricHitStopTime) {
                    MetricHitStopTime metricHitStopTime = (MetricHitStopTime) metricHit;
                    Long startTime = (Long) startedMetricHits.remove(metricHit.metric);
                    if (startTime != null) {
                        JSONUtils.put(json, metricName, (metricHitStopTime.stopTime + JSONUtils.getLongFromJSON(json, metricName, 0)) - startTime.longValue());
                    }
                } else if (metricHit instanceof MetricHitTotalTime) {
                    JSONUtils.put(json, metricName, ((MetricHitTotalTime) metricHit).totalTime);
                } else if (metricHit instanceof MetricHitIncrement) {
                    int i;
                    MetricHitIncrement metricHitIncrement = (MetricHitIncrement) metricHit;
                    Integer increment = (Integer) incrementedMetricHits.get(metricHit.metric);
                    if (increment == null) {
                        i = metricHitIncrement.increment;
                    } else {
                        i = increment.intValue() + metricHitIncrement.increment;
                    }
                    incrementedMetricHits.put(metricHit.metric, Integer.valueOf(i));
                } else if (metricHit instanceof MetricHitString) {
                    JSONUtils.put(json, metricName, ((MetricHitString) metricHit).text);
                }
            }
            for (Entry<MetricType, Integer> incrementedMetric : incrementedMetricHits.entrySet()) {
                metricName = ((MetricType) incrementedMetric.getKey()).getAaxName();
                if (adTypeMetricTag != null && ((MetricType) incrementedMetric.getKey()).isAdTypeSpecific()) {
                    metricName = adTypeMetricTag + metricName;
                }
                JSONUtils.put(json, metricName, ((Integer) incrementedMetric.getValue()).intValue());
            }
        }
    }
}
