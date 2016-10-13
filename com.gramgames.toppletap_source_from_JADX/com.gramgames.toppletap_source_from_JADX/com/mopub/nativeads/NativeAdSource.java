package com.mopub.nativeads;

import android.app.Activity;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.adjust.sdk.Constants;
import com.facebook.ads.AdError;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.exoplayer.chunk.FormatEvaluator.AdaptiveEvaluator;
import com.google.android.gms.games.GamesStatusCodes;
import com.mopub.common.VisibleForTesting;
import com.mopub.nativeads.MoPubNative.MoPubNativeNetworkListener;
import java.util.ArrayList;
import java.util.List;

class NativeAdSource {
    private static final int CACHE_LIMIT = 1;
    private static final int EXPIRATION_TIME_MILLISECONDS = 900000;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    @VisibleForTesting
    static final int[] RETRY_TIME_ARRAY_MILLISECONDS;
    @NonNull
    private final AdRendererRegistry mAdRendererRegistry;
    @Nullable
    private AdSourceListener mAdSourceListener;
    @VisibleForTesting
    int mCurrentRetries;
    @Nullable
    private MoPubNative mMoPubNative;
    @NonNull
    private final MoPubNativeNetworkListener mMoPubNativeNetworkListener;
    @NonNull
    private final List<TimestampWrapper<NativeAd>> mNativeAdCache;
    @NonNull
    private final Handler mReplenishCacheHandler;
    @NonNull
    private final Runnable mReplenishCacheRunnable;
    @VisibleForTesting
    boolean mRequestInFlight;
    @Nullable
    private RequestParameters mRequestParameters;
    @VisibleForTesting
    boolean mRetryInFlight;
    @VisibleForTesting
    int mSequenceNumber;

    interface AdSourceListener {
        void onAdsAvailable();
    }

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            NativeAdSource.this.mRetryInFlight = false;
            NativeAdSource.this.replenishCache();
        }
    }

    class 2 implements MoPubNativeNetworkListener {
        2() {
        }

        public void onNativeLoad(@NonNull NativeAd nativeAd) {
            if (NativeAdSource.this.mMoPubNative != null) {
                NativeAdSource.this.mRequestInFlight = false;
                NativeAdSource nativeAdSource = NativeAdSource.this;
                nativeAdSource.mSequenceNumber += NativeAdSource.CACHE_LIMIT;
                NativeAdSource.this.resetRetryTime();
                NativeAdSource.this.mNativeAdCache.add(new TimestampWrapper(nativeAd));
                if (NativeAdSource.this.mNativeAdCache.size() == NativeAdSource.CACHE_LIMIT && NativeAdSource.this.mAdSourceListener != null) {
                    NativeAdSource.this.mAdSourceListener.onAdsAvailable();
                }
                NativeAdSource.this.replenishCache();
            }
        }

        public void onNativeFail(NativeErrorCode errorCode) {
            NativeAdSource.this.mRequestInFlight = false;
            if (NativeAdSource.this.mCurrentRetries >= NativeAdSource.RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
                NativeAdSource.this.resetRetryTime();
                return;
            }
            NativeAdSource.this.updateRetryTime();
            NativeAdSource.this.mRetryInFlight = true;
            NativeAdSource.this.mReplenishCacheHandler.postDelayed(NativeAdSource.this.mReplenishCacheRunnable, (long) NativeAdSource.this.getRetryTime());
        }
    }

    static {
        RETRY_TIME_ARRAY_MILLISECONDS = new int[]{AdError.NETWORK_ERROR_CODE, GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE, Factory.DEFAULT_MIN_REBUFFER_MS, AdaptiveEvaluator.DEFAULT_MIN_DURATION_TO_RETAIN_AFTER_DISCARD_MS, Constants.SOCKET_TIMEOUT, MAXIMUM_RETRY_TIME_MILLISECONDS};
    }

    NativeAdSource() {
        this(new ArrayList(CACHE_LIMIT), new Handler(), new AdRendererRegistry());
    }

    @VisibleForTesting
    NativeAdSource(@NonNull List<TimestampWrapper<NativeAd>> nativeAdCache, @NonNull Handler replenishCacheHandler, @NonNull AdRendererRegistry adRendererRegistry) {
        this.mNativeAdCache = nativeAdCache;
        this.mReplenishCacheHandler = replenishCacheHandler;
        this.mReplenishCacheRunnable = new 1();
        this.mAdRendererRegistry = adRendererRegistry;
        this.mMoPubNativeNetworkListener = new 2();
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    int getAdRendererCount() {
        return this.mAdRendererRegistry.getAdRendererCount();
    }

    public int getViewTypeForAd(@NonNull NativeAd nativeAd) {
        return this.mAdRendererRegistry.getViewTypeForAd(nativeAd);
    }

    void registerAdRenderer(@NonNull MoPubAdRenderer moPubNativeAdRenderer) {
        this.mAdRendererRegistry.registerAdRenderer(moPubNativeAdRenderer);
        if (this.mMoPubNative != null) {
            this.mMoPubNative.registerAdRenderer(moPubNativeAdRenderer);
        }
    }

    @Nullable
    public MoPubAdRenderer getAdRendererForViewType(int viewType) {
        return this.mAdRendererRegistry.getRendererForViewType(viewType);
    }

    void setAdSourceListener(@Nullable AdSourceListener adSourceListener) {
        this.mAdSourceListener = adSourceListener;
    }

    void loadAds(@NonNull Activity activity, @NonNull String adUnitId, RequestParameters requestParameters) {
        loadAds(requestParameters, new MoPubNative(activity, adUnitId, this.mMoPubNativeNetworkListener));
    }

    @VisibleForTesting
    void loadAds(RequestParameters requestParameters, MoPubNative moPubNative) {
        clear();
        for (MoPubAdRenderer renderer : this.mAdRendererRegistry.getRendererIterable()) {
            moPubNative.registerAdRenderer(renderer);
        }
        this.mRequestParameters = requestParameters;
        this.mMoPubNative = moPubNative;
        replenishCache();
    }

    void clear() {
        if (this.mMoPubNative != null) {
            this.mMoPubNative.destroy();
            this.mMoPubNative = null;
        }
        this.mRequestParameters = null;
        for (TimestampWrapper<NativeAd> timestampWrapper : this.mNativeAdCache) {
            ((NativeAd) timestampWrapper.mInstance).destroy();
        }
        this.mNativeAdCache.clear();
        this.mReplenishCacheHandler.removeMessages(0);
        this.mRequestInFlight = false;
        this.mSequenceNumber = 0;
        resetRetryTime();
    }

    @Nullable
    NativeAd dequeueAd() {
        long now = SystemClock.uptimeMillis();
        if (!(this.mRequestInFlight || this.mRetryInFlight)) {
            this.mReplenishCacheHandler.post(this.mReplenishCacheRunnable);
        }
        while (!this.mNativeAdCache.isEmpty()) {
            TimestampWrapper<NativeAd> responseWrapper = (TimestampWrapper) this.mNativeAdCache.remove(0);
            if (now - responseWrapper.mCreatedTimestamp < 900000) {
                return (NativeAd) responseWrapper.mInstance;
            }
        }
        return null;
    }

    @VisibleForTesting
    void updateRetryTime() {
        if (this.mCurrentRetries < RETRY_TIME_ARRAY_MILLISECONDS.length - 1) {
            this.mCurrentRetries += CACHE_LIMIT;
        }
    }

    @VisibleForTesting
    void resetRetryTime() {
        this.mCurrentRetries = 0;
    }

    @VisibleForTesting
    int getRetryTime() {
        if (this.mCurrentRetries >= RETRY_TIME_ARRAY_MILLISECONDS.length) {
            this.mCurrentRetries = RETRY_TIME_ARRAY_MILLISECONDS.length - 1;
        }
        return RETRY_TIME_ARRAY_MILLISECONDS[this.mCurrentRetries];
    }

    @VisibleForTesting
    void replenishCache() {
        if (!this.mRequestInFlight && this.mMoPubNative != null && this.mNativeAdCache.size() < CACHE_LIMIT) {
            this.mRequestInFlight = true;
            this.mMoPubNative.makeRequest(this.mRequestParameters, Integer.valueOf(this.mSequenceNumber));
        }
    }

    @Deprecated
    @VisibleForTesting
    void setMoPubNative(MoPubNative moPubNative) {
        this.mMoPubNative = moPubNative;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    MoPubNativeNetworkListener getMoPubNativeNetworkListener() {
        return this.mMoPubNativeNetworkListener;
    }
}
