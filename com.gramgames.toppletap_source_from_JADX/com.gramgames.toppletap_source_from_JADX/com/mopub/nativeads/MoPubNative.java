package com.mopub.nativeads;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.AdFormat;
import com.mopub.common.Constants;
import com.mopub.common.GpsHelper;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.ManifestUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.Networking;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.TreeMap;
import org.json.simple.parser.Yytoken;

public class MoPubNative {
    static final MoPubNativeNetworkListener EMPTY_NETWORK_LISTENER;
    @NonNull
    private final WeakReference<Activity> mActivity;
    @NonNull
    AdRendererRegistry mAdRendererRegistry;
    @NonNull
    private final String mAdUnitId;
    @NonNull
    private Map<String, Object> mLocalExtras;
    @NonNull
    private MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @Nullable
    private AdRequest mNativeRequest;
    @NonNull
    private final Listener mVolleyListener;

    public interface MoPubNativeNetworkListener {
        void onNativeFail(NativeErrorCode nativeErrorCode);

        void onNativeLoad(NativeAd nativeAd);
    }

    static class 1 implements MoPubNativeNetworkListener {
        1() {
        }

        public void onNativeLoad(@NonNull NativeAd nativeAd) {
            nativeAd.destroy();
        }

        public void onNativeFail(NativeErrorCode errorCode) {
        }
    }

    class 2 implements Listener {
        2() {
        }

        public void onSuccess(@NonNull AdResponse response) {
            MoPubNative.this.onAdLoad(response);
        }

        public void onErrorResponse(@NonNull VolleyError volleyError) {
            MoPubNative.this.onAdError(volleyError);
        }
    }

    class 3 implements CustomEventNativeListener {
        final /* synthetic */ AdResponse val$response;

        3(AdResponse adResponse) {
            this.val$response = adResponse;
        }

        public void onNativeAdLoaded(@NonNull BaseNativeAd nativeAd) {
            Activity activity = MoPubNative.this.getActivityOrDestroy();
            if (activity != null) {
                MoPubAdRenderer renderer = MoPubNative.this.mAdRendererRegistry.getRendererForAd(nativeAd);
                if (renderer == null) {
                    onNativeAdFailed(NativeErrorCode.NATIVE_RENDERER_CONFIGURATION_ERROR);
                } else {
                    MoPubNative.this.mMoPubNativeNetworkListener.onNativeLoad(new NativeAd(activity, this.val$response.getImpressionTrackingUrl(), this.val$response.getClickTrackingUrl(), MoPubNative.this.mAdUnitId, nativeAd, renderer));
                }
            }
        }

        public void onNativeAdFailed(NativeErrorCode errorCode) {
            MoPubLog.v(String.format("Native Ad failed to load with error: %s.", new Object[]{errorCode}));
            MoPubNative.this.requestNativeAd(this.val$response.getFailoverUrl());
        }
    }

    static /* synthetic */ class 4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        static {
            $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = new int[Reason.values().length];
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.BAD_BODY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.BAD_HEADER_DATA.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.WARMING_UP.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.NO_FILL.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.UNSPECIFIED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
        }
    }

    static {
        EMPTY_NETWORK_LISTENER = new 1();
    }

    public MoPubNative(@NonNull Activity activity, @NonNull String adUnitId, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this(activity, adUnitId, new AdRendererRegistry(), moPubNativeNetworkListener);
    }

    @VisibleForTesting
    public MoPubNative(@NonNull Activity activity, @NonNull String adUnitId, @NonNull AdRendererRegistry adRendererRegistry, @NonNull MoPubNativeNetworkListener moPubNativeNetworkListener) {
        this.mLocalExtras = new TreeMap();
        Preconditions.checkNotNull(activity, "Activity may not be null.");
        Preconditions.checkNotNull(adUnitId, "AdUnitId may not be null.");
        Preconditions.checkNotNull(adRendererRegistry, "AdRendererRegistry may not be null.");
        Preconditions.checkNotNull(moPubNativeNetworkListener, "MoPubNativeNetworkListener may not be null.");
        ManifestUtils.checkNativeActivitiesDeclared(activity);
        this.mActivity = new WeakReference(activity);
        this.mAdUnitId = adUnitId;
        this.mMoPubNativeNetworkListener = moPubNativeNetworkListener;
        this.mAdRendererRegistry = adRendererRegistry;
        this.mVolleyListener = new 2();
        GpsHelper.fetchAdvertisingInfoAsync(activity, null);
    }

    public void registerAdRenderer(MoPubAdRenderer moPubAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubAdRenderer);
    }

    public void destroy() {
        this.mActivity.clear();
        if (this.mNativeRequest != null) {
            this.mNativeRequest.cancel();
            this.mNativeRequest = null;
        }
        this.mMoPubNativeNetworkListener = EMPTY_NETWORK_LISTENER;
    }

    public void setLocalExtras(@Nullable Map<String, Object> localExtras) {
        if (localExtras == null) {
            this.mLocalExtras = new TreeMap();
        } else {
            this.mLocalExtras = new TreeMap(localExtras);
        }
    }

    public void makeRequest() {
        makeRequest((RequestParameters) null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters) {
        makeRequest(requestParameters, null);
    }

    public void makeRequest(@Nullable RequestParameters requestParameters, @Nullable Integer sequenceNumber) {
        Activity activity = getActivityOrDestroy();
        if (activity != null) {
            if (DeviceUtils.isNetworkAvailable(activity)) {
                loadNativeAd(requestParameters, sequenceNumber);
            } else {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
            }
        }
    }

    private void loadNativeAd(@Nullable RequestParameters requestParameters, @Nullable Integer sequenceNumber) {
        Activity activity = getActivityOrDestroy();
        if (activity != null) {
            NativeUrlGenerator generator = new NativeUrlGenerator(activity).withAdUnitId(this.mAdUnitId).withRequest(requestParameters);
            if (sequenceNumber != null) {
                generator.withSequenceNumber(sequenceNumber.intValue());
            }
            String endpointUrl = generator.generateUrlString(Constants.HOST);
            if (endpointUrl != null) {
                MoPubLog.d("Loading ad from: " + endpointUrl);
            }
            requestNativeAd(endpointUrl);
        }
    }

    void requestNativeAd(@Nullable String endpointUrl) {
        Activity activity = getActivityOrDestroy();
        if (activity != null) {
            if (endpointUrl == null) {
                this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_REQUEST_URL);
                return;
            }
            this.mNativeRequest = new AdRequest(endpointUrl, AdFormat.NATIVE, this.mAdUnitId, activity, this.mVolleyListener);
            Networking.getRequestQueue(activity).add(this.mNativeRequest);
        }
    }

    private void onAdLoad(@NonNull AdResponse response) {
        Activity activity = getActivityOrDestroy();
        if (activity != null) {
            CustomEventNativeAdapter.loadNativeAd(activity, this.mLocalExtras, response, new 3(response));
        }
    }

    @VisibleForTesting
    void onAdError(@NonNull VolleyError volleyError) {
        MoPubLog.d("Native ad request failed.", volleyError);
        if (volleyError instanceof MoPubNetworkError) {
            switch (4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.INVALID_RESPONSE);
                    return;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    MoPubLog.c(MoPubErrorCode.WARMUP.toString());
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.EMPTY_AD_RESPONSE);
                    return;
                default:
                    this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
                    return;
            }
        }
        NetworkResponse response = volleyError.networkResponse;
        if (response != null && response.statusCode >= 500 && response.statusCode < 600) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.SERVER_ERROR_RESPONSE_CODE);
        } else if (response != null || DeviceUtils.isNetworkAvailable((Context) this.mActivity.get())) {
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.UNSPECIFIED);
        } else {
            MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
            this.mMoPubNativeNetworkListener.onNativeFail(NativeErrorCode.CONNECTION_ERROR);
        }
    }

    Activity getActivityOrDestroy() {
        Activity activity = (Activity) this.mActivity.get();
        if (activity == null) {
            destroy();
            MoPubLog.d("Weak reference to Activity in MoPubNative became null. This instance of MoPubNative is destroyed and No more requests will be processed.");
        }
        return activity;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
