package com.mopub.mobileads;

import android.content.Context;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mraid.MraidNativeCommandHandler;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NetworkResponse;
import com.mopub.volley.VolleyError;
import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;
import org.json.simple.parser.Yytoken;

public class AdViewController {
    static final double BACKOFF_FACTOR = 1.5d;
    static final int DEFAULT_REFRESH_TIME_MILLISECONDS = 60000;
    static final int MAX_REFRESH_TIME_MILLISECONDS = 600000;
    private static final LayoutParams WRAP_AND_CENTER_LAYOUT_PARAMS;
    private static final WeakHashMap<View, Boolean> sViewShouldHonorServerDimensions;
    @Nullable
    private AdRequest mActiveRequest;
    @NonNull
    private final Listener mAdListener;
    @Nullable
    private AdResponse mAdResponse;
    @Nullable
    private String mAdUnitId;
    private boolean mAdWasLoaded;
    private boolean mAutoRefreshEnabled;
    @VisibleForTesting
    int mBackoffPower;
    private final long mBroadcastIdentifier;
    @Nullable
    private Context mContext;
    private Handler mHandler;
    private boolean mIsDestroyed;
    private boolean mIsLoading;
    private boolean mIsTesting;
    private String mKeywords;
    private Map<String, Object> mLocalExtras;
    private Location mLocation;
    @Nullable
    private MoPubView mMoPubView;
    private boolean mPreviousAutoRefreshSetting;
    private final Runnable mRefreshRunnable;
    @Nullable
    private Integer mRefreshTimeMillis;
    private int mTimeoutMilliseconds;
    private String mUrl;
    @Nullable
    private WebViewAdUrlGenerator mUrlGenerator;

    class 1 implements Listener {
        1() {
        }

        public void onSuccess(AdResponse response) {
            AdViewController.this.onAdLoadSuccess(response);
        }

        public void onErrorResponse(VolleyError volleyError) {
            AdViewController.this.onAdLoadError(volleyError);
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            AdViewController.this.internalLoadAd();
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ View val$view;

        3(View view) {
            this.val$view = view;
        }

        public void run() {
            MoPubView moPubView = AdViewController.this.getMoPubView();
            if (moPubView != null) {
                moPubView.removeAllViews();
                moPubView.addView(this.val$view, AdViewController.this.getAdLayoutParams(this.val$view));
            }
        }
    }

    static /* synthetic */ class 4 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        static {
            $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = new int[Reason.values().length];
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.WARMING_UP.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.NO_FILL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    static {
        WRAP_AND_CENTER_LAYOUT_PARAMS = new LayoutParams(-2, -2, 17);
        sViewShouldHonorServerDimensions = new WeakHashMap();
    }

    public static void setShouldHonorServerDimensions(View view) {
        sViewShouldHonorServerDimensions.put(view, Boolean.valueOf(true));
    }

    private static boolean getShouldHonorServerDimensions(View view) {
        return sViewShouldHonorServerDimensions.get(view) != null;
    }

    public AdViewController(@NonNull Context context, @NonNull MoPubView view) {
        this.mBackoffPower = 1;
        this.mLocalExtras = new HashMap();
        this.mAutoRefreshEnabled = true;
        this.mPreviousAutoRefreshSetting = true;
        this.mContext = context;
        this.mMoPubView = view;
        this.mTimeoutMilliseconds = -1;
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mUrlGenerator = new WebViewAdUrlGenerator(this.mContext.getApplicationContext(), MraidNativeCommandHandler.isStorePictureSupported(this.mContext));
        this.mAdListener = new 1();
        this.mRefreshRunnable = new 2();
        this.mRefreshTimeMillis = Integer.valueOf(DEFAULT_REFRESH_TIME_MILLISECONDS);
        this.mHandler = new Handler();
    }

    @VisibleForTesting
    void onAdLoadSuccess(@NonNull AdResponse adResponse) {
        int i;
        this.mBackoffPower = 1;
        this.mAdResponse = adResponse;
        if (this.mAdResponse.getAdTimeoutMillis() == null) {
            i = this.mTimeoutMilliseconds;
        } else {
            i = this.mAdResponse.getAdTimeoutMillis().intValue();
        }
        this.mTimeoutMilliseconds = i;
        this.mRefreshTimeMillis = this.mAdResponse.getRefreshTimeMillis();
        setNotLoading();
        loadCustomEvent(this.mMoPubView, adResponse.getCustomEventClassName(), adResponse.getServerExtras());
        scheduleRefreshTimerIfEnabled();
    }

    @VisibleForTesting
    void onAdLoadError(VolleyError error) {
        if (error instanceof MoPubNetworkError) {
            MoPubNetworkError moPubNetworkError = (MoPubNetworkError) error;
            if (moPubNetworkError.getRefreshTimeMillis() != null) {
                this.mRefreshTimeMillis = moPubNetworkError.getRefreshTimeMillis();
            }
        }
        MoPubErrorCode errorCode = getErrorCodeFromVolleyError(error, this.mContext);
        if (errorCode == MoPubErrorCode.SERVER_ERROR) {
            this.mBackoffPower++;
        }
        setNotLoading();
        adDidFail(errorCode);
    }

    @VisibleForTesting
    void loadCustomEvent(@Nullable MoPubView moPubView, @Nullable String customEventClassName, @NonNull Map<String, String> serverExtras) {
        Preconditions.checkNotNull(serverExtras);
        if (moPubView == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
        } else {
            moPubView.loadCustomEvent(customEventClassName, serverExtras);
        }
    }

    @NonNull
    @VisibleForTesting
    static MoPubErrorCode getErrorCodeFromVolleyError(@NonNull VolleyError error, @Nullable Context context) {
        NetworkResponse networkResponse = error.networkResponse;
        if (error instanceof MoPubNetworkError) {
            switch (4.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) error).getReason().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return MoPubErrorCode.WARMUP;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return MoPubErrorCode.NO_FILL;
                default:
                    return MoPubErrorCode.UNSPECIFIED;
            }
        } else if (networkResponse == null) {
            if (DeviceUtils.isNetworkAvailable(context)) {
                return MoPubErrorCode.UNSPECIFIED;
            }
            return MoPubErrorCode.NO_CONNECTION;
        } else if (error.networkResponse.statusCode >= 400) {
            return MoPubErrorCode.SERVER_ERROR;
        } else {
            return MoPubErrorCode.UNSPECIFIED;
        }
    }

    @Nullable
    public MoPubView getMoPubView() {
        return this.mMoPubView;
    }

    public void loadAd() {
        this.mBackoffPower = 1;
        internalLoadAd();
    }

    private void internalLoadAd() {
        this.mAdWasLoaded = true;
        if (TextUtils.isEmpty(this.mAdUnitId)) {
            MoPubLog.d("Can't load an ad in this ad view because the ad unit ID is not set. Did you forget to call setAdUnitId()?");
        } else if (isNetworkAvailable()) {
            loadNonJavascript(generateAdUrl());
        } else {
            MoPubLog.d("Can't load an ad because there is no network connectivity.");
            scheduleRefreshTimerIfEnabled();
        }
    }

    void loadNonJavascript(String url) {
        if (url != null) {
            MoPubLog.d("Loading url: " + url);
            if (!this.mIsLoading) {
                this.mUrl = url;
                this.mIsLoading = true;
                fetchAd(this.mUrl);
            } else if (!TextUtils.isEmpty(this.mAdUnitId)) {
                MoPubLog.i("Already loading an ad for " + this.mAdUnitId + ", wait to finish.");
            }
        }
    }

    public void reload() {
        MoPubLog.d("Reload ad: " + this.mUrl);
        loadNonJavascript(this.mUrl);
    }

    void loadFailUrl(MoPubErrorCode errorCode) {
        this.mIsLoading = false;
        Log.v("MoPub", "MoPubErrorCode: " + (errorCode == null ? BuildConfig.FLAVOR : errorCode.toString()));
        String failUrl = this.mAdResponse == null ? BuildConfig.FLAVOR : this.mAdResponse.getFailoverUrl();
        if (TextUtils.isEmpty(failUrl)) {
            adDidFail(MoPubErrorCode.NO_FILL);
            return;
        }
        MoPubLog.d("Loading failover url: " + failUrl);
        loadNonJavascript(failUrl);
    }

    void setNotLoading() {
        this.mIsLoading = false;
        if (this.mActiveRequest != null) {
            if (!this.mActiveRequest.isCanceled()) {
                this.mActiveRequest.cancel();
            }
            this.mActiveRequest = null;
        }
    }

    public String getKeywords() {
        return this.mKeywords;
    }

    public void setKeywords(String keywords) {
        this.mKeywords = keywords;
    }

    public Location getLocation() {
        return this.mLocation;
    }

    public void setLocation(Location location) {
        this.mLocation = location;
    }

    public String getAdUnitId() {
        return this.mAdUnitId;
    }

    public void setAdUnitId(@NonNull String adUnitId) {
        this.mAdUnitId = adUnitId;
    }

    public long getBroadcastIdentifier() {
        return this.mBroadcastIdentifier;
    }

    public String getNetworkType() {
        return this.mAdResponse == null ? null : this.mAdResponse.getNetworkType();
    }

    public String getAdType() {
        return this.mAdResponse == null ? null : this.mAdResponse.getAdType();
    }

    public int getAdWidth() {
        if (this.mAdResponse == null || this.mAdResponse.getWidth() == null) {
            return 0;
        }
        return this.mAdResponse.getWidth().intValue();
    }

    public int getAdHeight() {
        if (this.mAdResponse == null || this.mAdResponse.getHeight() == null) {
            return 0;
        }
        return this.mAdResponse.getHeight().intValue();
    }

    public boolean getAutorefreshEnabled() {
        return this.mAutoRefreshEnabled;
    }

    void pauseRefresh() {
        this.mPreviousAutoRefreshSetting = this.mAutoRefreshEnabled;
        setAutorefreshEnabled(false);
    }

    void unpauseRefresh() {
        setAutorefreshEnabled(this.mPreviousAutoRefreshSetting);
    }

    void forceSetAutorefreshEnabled(boolean enabled) {
        this.mPreviousAutoRefreshSetting = enabled;
        setAutorefreshEnabled(enabled);
    }

    private void setAutorefreshEnabled(boolean enabled) {
        boolean autorefreshChanged = this.mAdWasLoaded && this.mAutoRefreshEnabled != enabled;
        if (autorefreshChanged) {
            MoPubLog.d("Refresh " + (enabled ? "enabled" : "disabled") + " for ad unit (" + this.mAdUnitId + ").");
        }
        this.mAutoRefreshEnabled = enabled;
        if (this.mAdWasLoaded && this.mAutoRefreshEnabled) {
            scheduleRefreshTimerIfEnabled();
        } else if (!this.mAutoRefreshEnabled) {
            cancelRefreshTimer();
        }
    }

    @Nullable
    public AdReport getAdReport() {
        if (this.mAdUnitId == null || this.mAdResponse == null) {
            return null;
        }
        return new AdReport(this.mAdUnitId, ClientMetadata.getInstance(this.mContext), this.mAdResponse);
    }

    public boolean getTesting() {
        return this.mIsTesting;
    }

    public void setTesting(boolean enabled) {
        this.mIsTesting = enabled;
    }

    boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    void cleanup() {
        if (!this.mIsDestroyed) {
            if (this.mActiveRequest != null) {
                this.mActiveRequest.cancel();
                this.mActiveRequest = null;
            }
            setAutorefreshEnabled(false);
            cancelRefreshTimer();
            this.mMoPubView = null;
            this.mContext = null;
            this.mUrlGenerator = null;
            this.mIsDestroyed = true;
        }
    }

    Integer getAdTimeoutDelay() {
        return Integer.valueOf(this.mTimeoutMilliseconds);
    }

    void trackImpression() {
        if (this.mAdResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(this.mAdResponse.getImpressionTrackingUrl(), this.mContext, Name.IMPRESSION_REQUEST);
        }
    }

    void registerClick() {
        if (this.mAdResponse != null) {
            TrackingRequest.makeTrackingHttpRequest(this.mAdResponse.getClickTrackingUrl(), this.mContext, Name.CLICK_REQUEST);
        }
    }

    void fetchAd(String url) {
        MoPubView moPubView = getMoPubView();
        if (moPubView == null || this.mContext == null) {
            MoPubLog.d("Can't load an ad in this ad view because it was destroyed.");
            setNotLoading();
            return;
        }
        AdRequest adRequest = new AdRequest(url, moPubView.getAdFormat(), this.mAdUnitId, this.mContext, this.mAdListener);
        Networking.getRequestQueue(this.mContext).add(adRequest);
        this.mActiveRequest = adRequest;
    }

    void forceRefresh() {
        setNotLoading();
        loadAd();
    }

    @Nullable
    String generateAdUrl() {
        if (this.mUrlGenerator == null) {
            return null;
        }
        return this.mUrlGenerator.withAdUnitId(this.mAdUnitId).withKeywords(this.mKeywords).withLocation(this.mLocation).generateUrlString(Constants.HOST);
    }

    void adDidFail(MoPubErrorCode errorCode) {
        MoPubLog.i("Ad failed to load.");
        setNotLoading();
        MoPubView moPubView = getMoPubView();
        if (moPubView != null) {
            scheduleRefreshTimerIfEnabled();
            moPubView.adFailed(errorCode);
        }
    }

    void scheduleRefreshTimerIfEnabled() {
        cancelRefreshTimer();
        if (this.mAutoRefreshEnabled && this.mRefreshTimeMillis != null && this.mRefreshTimeMillis.intValue() > 0) {
            this.mHandler.postDelayed(this.mRefreshRunnable, Math.min(600000, ((long) this.mRefreshTimeMillis.intValue()) * ((long) Math.pow(BACKOFF_FACTOR, (double) this.mBackoffPower))));
        }
    }

    void setLocalExtras(Map<String, Object> localExtras) {
        this.mLocalExtras = localExtras != null ? new TreeMap(localExtras) : new TreeMap();
    }

    Map<String, Object> getLocalExtras() {
        return this.mLocalExtras != null ? new TreeMap(this.mLocalExtras) : new TreeMap();
    }

    private void cancelRefreshTimer() {
        this.mHandler.removeCallbacks(this.mRefreshRunnable);
    }

    private boolean isNetworkAvailable() {
        if (this.mContext == null) {
            return false;
        }
        if (!DeviceUtils.isPermissionGranted(this.mContext, "android.permission.ACCESS_NETWORK_STATE")) {
            return true;
        }
        NetworkInfo networkInfo = ((ConnectivityManager) this.mContext.getSystemService("connectivity")).getActiveNetworkInfo();
        if (networkInfo == null || !networkInfo.isConnected()) {
            return false;
        }
        return true;
    }

    void setAdContentView(View view) {
        this.mHandler.post(new 3(view));
    }

    private LayoutParams getAdLayoutParams(View view) {
        Integer width = null;
        Integer height = null;
        if (this.mAdResponse != null) {
            width = this.mAdResponse.getWidth();
            height = this.mAdResponse.getHeight();
        }
        if (width == null || height == null || !getShouldHonorServerDimensions(view) || width.intValue() <= 0 || height.intValue() <= 0) {
            return WRAP_AND_CENTER_LAYOUT_PARAMS;
        }
        return new LayoutParams(Dips.asIntPixels((float) width.intValue(), this.mContext), Dips.asIntPixels((float) height.intValue(), this.mContext), 17);
    }

    @Deprecated
    @VisibleForTesting
    Integer getRefreshTimeMillis() {
        return this.mRefreshTimeMillis;
    }

    @Deprecated
    @VisibleForTesting
    void setRefreshTimeMillis(@Nullable Integer refreshTimeMillis) {
        this.mRefreshTimeMillis = refreshTimeMillis;
    }
}
