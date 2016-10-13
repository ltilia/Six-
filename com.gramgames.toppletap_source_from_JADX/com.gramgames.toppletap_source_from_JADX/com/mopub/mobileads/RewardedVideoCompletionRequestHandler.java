package com.mopub.mobileads;

import android.content.Context;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.mopub.common.MoPub;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.RewardedVideoCompletionRequest.RewardedVideoCompletionRequestListener;
import com.mopub.network.Networking;
import com.mopub.volley.DefaultRetryPolicy;
import com.mopub.volley.RequestQueue;
import com.mopub.volley.VolleyError;
import com.prime31.util.IabHelper;
import gs.gram.mopub.BuildConfig;

public class RewardedVideoCompletionRequestHandler implements RewardedVideoCompletionRequestListener {
    private static final String API_VERSION_KEY = "&v=";
    private static final String CUSTOMER_ID_KEY = "&customer_id=";
    static final int MAX_RETRIES = 17;
    static final int REQUEST_TIMEOUT_DELAY = 1000;
    static final int[] RETRY_TIMES;
    private static final String SDK_VERSION_KEY = "&nv=";
    @NonNull
    private final Handler mHandler;
    @NonNull
    private final RequestQueue mRequestQueue;
    private int mRetryCount;
    private volatile boolean mShouldStop;
    @NonNull
    private final String mUrl;

    class 1 implements Runnable {
        1() {
        }

        public void run() {
            RewardedVideoCompletionRequestHandler.this.makeRewardedVideoCompletionRequest();
        }
    }

    static {
        RETRY_TIMES = new int[]{Factory.DEFAULT_MIN_REBUFFER_MS, CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY, WebRequest.DEFAULT_TIMEOUT, 40000, Constants.SOCKET_TIMEOUT};
    }

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String url, @Nullable String customerId) {
        this(context, url, customerId, new Handler());
    }

    RewardedVideoCompletionRequestHandler(@NonNull Context context, @NonNull String url, @Nullable String customerId, @NonNull Handler handler) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(url);
        this.mUrl = appendParameters(url, customerId);
        this.mRetryCount = 0;
        this.mHandler = handler;
        this.mRequestQueue = Networking.getRequestQueue(context);
    }

    void makeRewardedVideoCompletionRequest() {
        if (this.mShouldStop) {
            this.mRequestQueue.cancelAll(this.mUrl);
            return;
        }
        RewardedVideoCompletionRequest rewardedVideoCompletionRequest = new RewardedVideoCompletionRequest(this.mUrl, new DefaultRetryPolicy(getTimeout(this.mRetryCount) + IabHelper.IABHELPER_ERROR_BASE, 0, 0.0f), this);
        rewardedVideoCompletionRequest.setTag(this.mUrl);
        this.mRequestQueue.add(rewardedVideoCompletionRequest);
        if (this.mRetryCount >= MAX_RETRIES) {
            MoPubLog.d("Exceeded number of retries for rewarded video completion request.");
            return;
        }
        this.mHandler.postDelayed(new 1(), (long) getTimeout(this.mRetryCount));
        this.mRetryCount++;
    }

    public void onResponse(Integer response) {
        if (response == null) {
            return;
        }
        if (response.intValue() < 500 || response.intValue() >= 600) {
            this.mShouldStop = true;
        }
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (volleyError != null && volleyError.networkResponse != null) {
            if (volleyError.networkResponse.statusCode < 500 || volleyError.networkResponse.statusCode >= 600) {
                this.mShouldStop = true;
            }
        }
    }

    public static void makeRewardedVideoCompletionRequest(@Nullable Context context, @Nullable String url, @Nullable String customerId) {
        if (!TextUtils.isEmpty(url) && context != null) {
            new RewardedVideoCompletionRequestHandler(context, url, customerId).makeRewardedVideoCompletionRequest();
        }
    }

    static int getTimeout(int retryCount) {
        if (retryCount < 0 || retryCount >= RETRY_TIMES.length) {
            return RETRY_TIMES[RETRY_TIMES.length - 1];
        }
        return RETRY_TIMES[retryCount];
    }

    private static String appendParameters(@NonNull String url, @Nullable String customerId) {
        String str;
        Preconditions.checkNotNull(url);
        StringBuilder append = new StringBuilder().append(url).append(CUSTOMER_ID_KEY);
        if (customerId == null) {
            str = BuildConfig.FLAVOR;
        } else {
            str = Uri.encode(customerId);
        }
        return append.append(str).append(SDK_VERSION_KEY).append(Uri.encode(MoPub.SDK_VERSION)).append(API_VERSION_KEY).append(1).toString();
    }

    @Deprecated
    @VisibleForTesting
    boolean getShouldStop() {
        return this.mShouldStop;
    }

    @Deprecated
    @VisibleForTesting
    int getRetryCount() {
        return this.mRetryCount;
    }

    @Deprecated
    @VisibleForTesting
    void setRetryCount(int retryCount) {
        this.mRetryCount = retryCount;
    }
}
