package com.mopub.nativeads;

import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.Constants;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.MoPubErrorCode;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.Networking;
import com.mopub.volley.Response.ErrorListener;
import com.mopub.volley.Response.Listener;
import com.mopub.volley.VolleyError;

class ServerPositioningSource implements PositioningSource {
    private static final double DEFAULT_RETRY_TIME_MILLISECONDS = 1000.0d;
    private static final double EXPONENTIAL_BACKOFF_FACTOR = 2.0d;
    private static final int MAXIMUM_RETRY_TIME_MILLISECONDS = 300000;
    @NonNull
    private final Context mContext;
    private final ErrorListener mErrorListener;
    @Nullable
    private PositioningListener mListener;
    private int mMaximumRetryTimeMillis;
    private final Listener<MoPubClientPositioning> mPositioningListener;
    @Nullable
    private PositioningRequest mRequest;
    private int mRetryCount;
    @NonNull
    private final Handler mRetryHandler;
    @NonNull
    private final Runnable mRetryRunnable;
    @Nullable
    private String mRetryUrl;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            ServerPositioningSource.this.requestPositioningInternal();
        }
    }

    class 2 implements Listener<MoPubClientPositioning> {
        2() {
        }

        public void onResponse(MoPubClientPositioning clientPositioning) {
            ServerPositioningSource.this.handleSuccess(clientPositioning);
        }
    }

    class 3 implements ErrorListener {
        3() {
        }

        public void onErrorResponse(VolleyError error) {
            if (!(error instanceof MoPubNetworkError) || ((MoPubNetworkError) error).getReason().equals(Reason.WARMING_UP)) {
                MoPubLog.e("Failed to load positioning data", error);
                if (error.networkResponse == null && !DeviceUtils.isNetworkAvailable(ServerPositioningSource.this.mContext)) {
                    MoPubLog.c(String.valueOf(MoPubErrorCode.NO_CONNECTION.toString()));
                }
            }
            ServerPositioningSource.this.handleFailure();
        }
    }

    ServerPositioningSource(@NonNull Context context) {
        this.mMaximumRetryTimeMillis = MAXIMUM_RETRY_TIME_MILLISECONDS;
        this.mContext = context.getApplicationContext();
        this.mRetryHandler = new Handler();
        this.mRetryRunnable = new 1();
        this.mPositioningListener = new 2();
        this.mErrorListener = new 3();
    }

    public void loadPositions(@NonNull String adUnitId, @NonNull PositioningListener listener) {
        if (this.mRequest != null) {
            this.mRequest.cancel();
            this.mRequest = null;
        }
        if (this.mRetryCount > 0) {
            this.mRetryHandler.removeCallbacks(this.mRetryRunnable);
            this.mRetryCount = 0;
        }
        this.mListener = listener;
        this.mRetryUrl = new PositioningUrlGenerator(this.mContext).withAdUnitId(adUnitId).generateUrlString(Constants.HOST);
        requestPositioningInternal();
    }

    private void requestPositioningInternal() {
        MoPubLog.d("Loading positioning from: " + this.mRetryUrl);
        this.mRequest = new PositioningRequest(this.mRetryUrl, this.mPositioningListener, this.mErrorListener);
        Networking.getRequestQueue(this.mContext).add(this.mRequest);
    }

    private void handleSuccess(@NonNull MoPubClientPositioning positioning) {
        if (this.mListener != null) {
            this.mListener.onLoad(positioning);
        }
        this.mListener = null;
        this.mRetryCount = 0;
    }

    private void handleFailure() {
        int delay = (int) (DEFAULT_RETRY_TIME_MILLISECONDS * Math.pow(EXPONENTIAL_BACKOFF_FACTOR, (double) (this.mRetryCount + 1)));
        if (delay >= this.mMaximumRetryTimeMillis) {
            MoPubLog.d("Error downloading positioning information");
            if (this.mListener != null) {
                this.mListener.onFailed();
            }
            this.mListener = null;
            return;
        }
        this.mRetryCount++;
        this.mRetryHandler.postDelayed(this.mRetryRunnable, (long) delay);
    }

    @Deprecated
    @VisibleForTesting
    void setMaximumRetryTimeMilliseconds(int millis) {
        this.mMaximumRetryTimeMillis = millis;
    }
}
