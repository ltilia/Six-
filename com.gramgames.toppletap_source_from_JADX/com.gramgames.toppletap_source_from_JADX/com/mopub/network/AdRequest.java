package com.mopub.network;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.AdType;
import com.mopub.common.FullAdType;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Builder;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ResponseHeader;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.Request;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.toolbox.HttpHeaderParser;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

public class AdRequest extends Request<AdResponse> {
    @NonNull
    private final AdFormat mAdFormat;
    @Nullable
    private final String mAdUnitId;
    @NonNull
    private final Context mContext;
    @NonNull
    private final Listener mListener;

    public interface Listener extends ErrorListener {
        void onSuccess(AdResponse adResponse);
    }

    public AdRequest(@NonNull String url, @NonNull AdFormat adFormat, @Nullable String adUnitId, @NonNull Context context, @NonNull Listener listener) {
        super(0, url, listener);
        Preconditions.checkNotNull(adFormat);
        Preconditions.checkNotNull(listener);
        this.mAdUnitId = adUnitId;
        this.mListener = listener;
        this.mAdFormat = adFormat;
        this.mContext = context.getApplicationContext();
        setRetryPolicy(new DefaultRetryPolicy(DefaultRetryPolicy.DEFAULT_TIMEOUT_MS, 1, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        setShouldCache(false);
    }

    @NonNull
    public Listener getListener() {
        return this.mListener;
    }

    public Map<String, String> getHeaders() {
        TreeMap<String, String> headers = new TreeMap();
        String languageCode = Locale.getDefault().getLanguage();
        Locale userLocale = this.mContext.getResources().getConfiguration().locale;
        if (!(userLocale == null || userLocale.getLanguage().trim().isEmpty())) {
            languageCode = userLocale.getLanguage().trim();
        }
        if (!languageCode.isEmpty()) {
            headers.put(ResponseHeader.ACCEPT_LANGUAGE.getKey(), languageCode);
        }
        return headers;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.mopub.volley.Response<com.mopub.network.AdResponse> parseNetworkResponse(com.mopub.volley.NetworkResponse r35) {
        /*
        r34 = this;
        r0 = r35;
        r15 = r0.headers;
        r30 = com.mopub.common.util.ResponseHeader.WARMUP;
        r31 = 0;
        r0 = r30;
        r1 = r31;
        r30 = com.mopub.network.HeaderUtils.extractBooleanHeader(r15, r0, r1);
        if (r30 == 0) goto L_0x0020;
    L_0x0012:
        r30 = new com.mopub.network.MoPubNetworkError;
        r31 = "Ad Unit is warming up.";
        r32 = com.mopub.network.MoPubNetworkError.Reason.WARMING_UP;
        r30.<init>(r31, r32);
        r30 = com.mopub.volley.Response.error(r30);
    L_0x001f:
        return r30;
    L_0x0020:
        r0 = r34;
        r0 = r0.mContext;
        r30 = r0;
        r31 = com.mopub.common.MoPub.getLocationPrecision();
        r32 = com.mopub.common.MoPub.getLocationAwareness();
        r18 = com.mopub.common.LocationService.getLastKnownLocation(r30, r31, r32);
        r7 = new com.mopub.network.AdResponse$Builder;
        r7.<init>();
        r0 = r34;
        r0 = r0.mAdUnitId;
        r30 = r0;
        r0 = r30;
        r7.setAdUnitId(r0);
        r30 = com.mopub.common.util.ResponseHeader.AD_TYPE;
        r0 = r30;
        r6 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r30 = com.mopub.common.util.ResponseHeader.FULL_AD_TYPE;
        r0 = r30;
        r14 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r7.setAdType(r6);
        r7.setFullAdType(r14);
        r30 = com.mopub.common.util.ResponseHeader.REFRESH_TIME;
        r0 = r30;
        r22 = com.mopub.network.HeaderUtils.extractIntegerHeader(r15, r0);
        if (r22 != 0) goto L_0x0096;
    L_0x0062:
        r21 = 0;
    L_0x0064:
        r0 = r21;
        r7.setRefreshTimeMilliseconds(r0);
        r30 = "clear";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 == 0) goto L_0x00a5;
    L_0x0073:
        r4 = r7.build();
        r0 = r34;
        r1 = r35;
        r2 = r18;
        r0.logScribeEvent(r4, r1, r2);
        r30 = new com.mopub.network.MoPubNetworkError;
        r31 = "No ads found for ad unit.";
        r32 = com.mopub.network.MoPubNetworkError.Reason.NO_FILL;
        r0 = r30;
        r1 = r31;
        r2 = r32;
        r3 = r21;
        r0.<init>(r1, r2, r3);
        r30 = com.mopub.volley.Response.error(r30);
        goto L_0x001f;
    L_0x0096:
        r30 = r22.intValue();
        r0 = r30;
        r0 = r0 * 1000;
        r30 = r0;
        r21 = java.lang.Integer.valueOf(r30);
        goto L_0x0064;
    L_0x00a5:
        r30 = com.mopub.common.util.ResponseHeader.DSP_CREATIVE_ID;
        r0 = r30;
        r11 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r7.setDspCreativeId(r11);
        r30 = com.mopub.common.util.ResponseHeader.NETWORK_TYPE;
        r0 = r30;
        r19 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r19;
        r7.setNetworkType(r0);
        r30 = com.mopub.common.util.ResponseHeader.REDIRECT_URL;
        r0 = r30;
        r20 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r20;
        r7.setRedirectUrl(r0);
        r30 = com.mopub.common.util.ResponseHeader.CLICK_TRACKING_URL;
        r0 = r30;
        r8 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r7.setClickTrackingUrl(r8);
        r30 = com.mopub.common.util.ResponseHeader.IMPRESSION_URL;
        r0 = r30;
        r30 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r30;
        r7.setImpressionTrackingUrl(r0);
        r30 = com.mopub.common.util.ResponseHeader.FAIL_URL;
        r0 = r30;
        r13 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r7.setFailoverUrl(r13);
        r0 = r34;
        r23 = r0.getRequestId(r13);
        r0 = r23;
        r7.setRequestId(r0);
        r30 = com.mopub.common.util.ResponseHeader.SCROLLABLE;
        r31 = 0;
        r0 = r30;
        r1 = r31;
        r17 = com.mopub.network.HeaderUtils.extractBooleanHeader(r15, r0, r1);
        r30 = java.lang.Boolean.valueOf(r17);
        r0 = r30;
        r7.setScrollable(r0);
        r30 = com.mopub.common.util.ResponseHeader.WIDTH;
        r0 = r30;
        r29 = com.mopub.network.HeaderUtils.extractIntegerHeader(r15, r0);
        r30 = com.mopub.common.util.ResponseHeader.HEIGHT;
        r0 = r30;
        r16 = com.mopub.network.HeaderUtils.extractIntegerHeader(r15, r0);
        r0 = r29;
        r1 = r16;
        r7.setDimensions(r0, r1);
        r30 = com.mopub.common.util.ResponseHeader.AD_TIMEOUT;
        r0 = r30;
        r5 = com.mopub.network.HeaderUtils.extractIntegerHeader(r15, r0);
        if (r5 != 0) goto L_0x01f9;
    L_0x012e:
        r30 = 0;
    L_0x0130:
        r0 = r30;
        r7.setAdTimeoutDelayMilliseconds(r0);
        r24 = r34.parseStringBody(r35);
        r0 = r24;
        r7.setResponseBody(r0);
        r30 = "json";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 != 0) goto L_0x0152;
    L_0x0148:
        r30 = "json_video";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 == 0) goto L_0x0160;
    L_0x0152:
        r30 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0209 }
        r0 = r30;
        r1 = r24;
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0209 }
        r0 = r30;
        r7.setJsonBody(r0);	 Catch:{ JSONException -> 0x0209 }
    L_0x0160:
        r0 = r34;
        r0 = r0.mAdFormat;
        r30 = r0;
        r0 = r30;
        r9 = com.mopub.mobileads.AdTypeTranslator.getCustomEventName(r0, r6, r14, r15);
        r7.setCustomEventClassName(r9);
        r30 = com.mopub.common.util.ResponseHeader.CUSTOM_EVENT_DATA;
        r0 = r30;
        r10 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r30 = android.text.TextUtils.isEmpty(r10);
        if (r30 == 0) goto L_0x0185;
    L_0x017d:
        r30 = com.mopub.common.util.ResponseHeader.NATIVE_PARAMS;
        r0 = r30;
        r10 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
    L_0x0185:
        r28 = com.mopub.common.util.Json.jsonStringToMap(r10);	 Catch:{ JSONException -> 0x021f }
        if (r20 == 0) goto L_0x0196;
    L_0x018b:
        r30 = "Redirect-Url";
        r0 = r28;
        r1 = r30;
        r2 = r20;
        r0.put(r1, r2);
    L_0x0196:
        if (r8 == 0) goto L_0x01a1;
    L_0x0198:
        r30 = "Clickthrough-Url";
        r0 = r28;
        r1 = r30;
        r0.put(r1, r8);
    L_0x01a1:
        r0 = r34;
        r30 = r0.eventDataIsInResponseBody(r6, r14);
        if (r30 == 0) goto L_0x01d6;
    L_0x01a9:
        r30 = "Html-Response-Body";
        r0 = r28;
        r1 = r30;
        r2 = r24;
        r0.put(r1, r2);
        r30 = "Scrollable";
        r31 = java.lang.Boolean.toString(r17);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = "com_mopub_orientation";
        r31 = com.mopub.common.util.ResponseHeader.ORIENTATION;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
    L_0x01d6:
        r30 = "json_video";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 == 0) goto L_0x0320;
    L_0x01e0:
        r30 = android.os.Build.VERSION.SDK_INT;
        r31 = 16;
        r0 = r30;
        r1 = r31;
        if (r0 >= r1) goto L_0x0235;
    L_0x01ea:
        r30 = new com.mopub.network.MoPubNetworkError;
        r31 = "Native Video ads are only supported for Android API Level 16 (JellyBean) and above.";
        r32 = com.mopub.network.MoPubNetworkError.Reason.UNSPECIFIED;
        r30.<init>(r31, r32);
        r30 = com.mopub.volley.Response.error(r30);
        goto L_0x001f;
    L_0x01f9:
        r30 = r5.intValue();
        r0 = r30;
        r0 = r0 * 1000;
        r30 = r0;
        r30 = java.lang.Integer.valueOf(r30);
        goto L_0x0130;
    L_0x0209:
        r12 = move-exception;
        r30 = new com.mopub.network.MoPubNetworkError;
        r31 = "Failed to decode body JSON for native ad format";
        r32 = com.mopub.network.MoPubNetworkError.Reason.BAD_BODY;
        r0 = r30;
        r1 = r31;
        r2 = r32;
        r0.<init>(r1, r12, r2);
        r30 = com.mopub.volley.Response.error(r30);
        goto L_0x001f;
    L_0x021f:
        r12 = move-exception;
        r30 = new com.mopub.network.MoPubNetworkError;
        r31 = "Failed to decode server extras for custom event data.";
        r32 = com.mopub.network.MoPubNetworkError.Reason.BAD_HEADER_DATA;
        r0 = r30;
        r1 = r31;
        r2 = r32;
        r0.<init>(r1, r12, r2);
        r30 = com.mopub.volley.Response.error(r30);
        goto L_0x001f;
    L_0x0235:
        r30 = "Play-Visible-Percent";
        r31 = com.mopub.common.util.ResponseHeader.PLAY_VISIBLE_PERCENT;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractPercentHeaderString(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = "Pause-Visible-Percent";
        r31 = com.mopub.common.util.ResponseHeader.PAUSE_VISIBLE_PERCENT;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractPercentHeaderString(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = "Impression-Min-Visible-Percent";
        r31 = com.mopub.common.util.ResponseHeader.IMPRESSION_MIN_VISIBLE_PERCENT;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractPercentHeaderString(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = "Impression-Visible-Ms";
        r31 = com.mopub.common.util.ResponseHeader.IMPRESSION_VISIBLE_MS;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = "Max-Buffer-Ms";
        r31 = com.mopub.common.util.ResponseHeader.MAX_BUFFER_MS;
        r0 = r31;
        r31 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r28;
        r1 = r30;
        r2 = r31;
        r0.put(r1, r2);
        r30 = new com.mopub.common.event.EventDetails$Builder;
        r30.<init>();
        r0 = r34;
        r0 = r0.mAdUnitId;
        r31 = r0;
        r30 = r30.adUnitId(r31);
        r0 = r30;
        r30 = r0.adType(r6);
        r0 = r30;
        r1 = r19;
        r30 = r0.adNetworkType(r1);
        r0 = r30;
        r1 = r29;
        r30 = r0.adWidthPx(r1);
        r0 = r30;
        r1 = r16;
        r30 = r0.adHeightPx(r1);
        r0 = r30;
        r31 = r0.dspCreativeId(r11);
        if (r18 != 0) goto L_0x037b;
    L_0x02c9:
        r30 = 0;
    L_0x02cb:
        r0 = r31;
        r1 = r30;
        r31 = r0.geoLatitude(r1);
        if (r18 != 0) goto L_0x0385;
    L_0x02d5:
        r30 = 0;
    L_0x02d7:
        r0 = r31;
        r1 = r30;
        r31 = r0.geoLongitude(r1);
        if (r18 != 0) goto L_0x038f;
    L_0x02e1:
        r30 = 0;
    L_0x02e3:
        r0 = r31;
        r1 = r30;
        r30 = r0.geoAccuracy(r1);
        r0 = r35;
        r0 = r0.networkTimeMs;
        r32 = r0;
        r31 = java.lang.Long.valueOf(r32);
        r30 = r30.performanceDurationMs(r31);
        r0 = r30;
        r1 = r23;
        r30 = r0.requestId(r1);
        r0 = r35;
        r0 = r0.statusCode;
        r31 = r0;
        r31 = java.lang.Integer.valueOf(r31);
        r30 = r30.requestStatusCode(r31);
        r31 = r34.getUrl();
        r30 = r30.requestUri(r31);
        r30 = r30.build();
        r0 = r30;
        r7.setEventDetails(r0);
    L_0x0320:
        r0 = r28;
        r7.setServerExtras(r0);
        r30 = "rewarded_video";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 != 0) goto L_0x0339;
    L_0x032f:
        r30 = "custom";
        r0 = r30;
        r30 = r0.equals(r6);
        if (r30 == 0) goto L_0x0360;
    L_0x0339:
        r30 = com.mopub.common.util.ResponseHeader.REWARDED_VIDEO_CURRENCY_NAME;
        r0 = r30;
        r27 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r30 = com.mopub.common.util.ResponseHeader.REWARDED_VIDEO_CURRENCY_AMOUNT;
        r0 = r30;
        r26 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r30 = com.mopub.common.util.ResponseHeader.REWARDED_VIDEO_COMPLETION_URL;
        r0 = r30;
        r25 = com.mopub.network.HeaderUtils.extractHeader(r15, r0);
        r0 = r27;
        r7.setRewardedVideoCurrencyName(r0);
        r0 = r26;
        r7.setRewardedVideoCurrencyAmount(r0);
        r0 = r25;
        r7.setRewardedVideoCompletionUrl(r0);
    L_0x0360:
        r4 = r7.build();
        r0 = r34;
        r1 = r35;
        r2 = r18;
        r0.logScribeEvent(r4, r1, r2);
        r30 = r7.build();
        r31 = com.mopub.volley.toolbox.HttpHeaderParser.parseCacheHeaders(r35);
        r30 = com.mopub.volley.Response.success(r30, r31);
        goto L_0x001f;
    L_0x037b:
        r32 = r18.getLatitude();
        r30 = java.lang.Double.valueOf(r32);
        goto L_0x02cb;
    L_0x0385:
        r32 = r18.getLongitude();
        r30 = java.lang.Double.valueOf(r32);
        goto L_0x02d7;
    L_0x038f:
        r30 = r18.getAccuracy();
        r30 = java.lang.Float.valueOf(r30);
        goto L_0x02e3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mopub.network.AdRequest.parseNetworkResponse(com.mopub.volley.NetworkResponse):com.mopub.volley.Response<com.mopub.network.AdResponse>");
    }

    private boolean eventDataIsInResponseBody(@Nullable String adType, @Nullable String fullAdType) {
        return AdType.MRAID.equals(adType) || AdType.HTML.equals(adType) || ((AdType.INTERSTITIAL.equals(adType) && FullAdType.VAST.equals(fullAdType)) || (AdType.REWARDED_VIDEO.equals(adType) && FullAdType.VAST.equals(fullAdType)));
    }

    protected String parseStringBody(NetworkResponse response) {
        try {
            return new String(response.data, HttpHeaderParser.parseCharset(response.headers));
        } catch (UnsupportedEncodingException e) {
            return new String(response.data);
        }
    }

    protected void deliverResponse(AdResponse adResponse) {
        this.mListener.onSuccess(adResponse);
    }

    @Nullable
    @VisibleForTesting
    String getRequestId(@Nullable String failUrl) {
        if (failUrl == null) {
            return null;
        }
        String requestId = null;
        try {
            return Uri.parse(failUrl).getQueryParameter("request_id");
        } catch (UnsupportedOperationException e) {
            MoPubLog.d("Unable to obtain request id from fail url.");
            return requestId;
        }
    }

    @VisibleForTesting
    void logScribeEvent(@NonNull AdResponse adResponse, @NonNull NetworkResponse networkResponse, @Nullable Location location) {
        Double valueOf;
        Double d = null;
        Preconditions.checkNotNull(adResponse);
        Preconditions.checkNotNull(networkResponse);
        Builder withAdWidthPx = new Event.Builder(Name.AD_REQUEST, Category.REQUESTS, SamplingRate.AD_REQUEST.getSamplingRate()).withAdUnitId(this.mAdUnitId).withDspCreativeId(adResponse.getDspCreativeId()).withAdType(adResponse.getAdType()).withAdNetworkType(adResponse.getNetworkType()).withAdWidthPx(adResponse.getWidth() != null ? Double.valueOf(adResponse.getWidth().doubleValue()) : null);
        if (adResponse.getHeight() != null) {
            valueOf = Double.valueOf(adResponse.getHeight().doubleValue());
        } else {
            valueOf = null;
        }
        withAdWidthPx = withAdWidthPx.withAdHeightPx(valueOf);
        if (location != null) {
            valueOf = Double.valueOf(location.getLatitude());
        } else {
            valueOf = null;
        }
        withAdWidthPx = withAdWidthPx.withGeoLat(valueOf);
        if (location != null) {
            valueOf = Double.valueOf(location.getLongitude());
        } else {
            valueOf = null;
        }
        Builder withGeoLon = withAdWidthPx.withGeoLon(valueOf);
        if (location != null) {
            d = Double.valueOf((double) location.getAccuracy());
        }
        MoPubEvents.log(withGeoLon.withGeoAccuracy(d).withPerformanceDurationMs(Double.valueOf((double) networkResponse.networkTimeMs)).withRequestId(adResponse.getRequestId()).withRequestStatusCode(Integer.valueOf(networkResponse.statusCode)).withRequestUri(getUrl()).build());
    }
}
