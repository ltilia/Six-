package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.AdReport;
import com.mopub.common.AdUrlGenerator;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.DataKeys;
import com.mopub.common.MediationSettings;
import com.mopub.common.MoPubReward;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.MoPubCollections;
import com.mopub.common.util.Reflection;
import com.mopub.common.util.Utils;
import com.mopub.network.AdRequest;
import com.mopub.network.AdRequest.Listener;
import com.mopub.network.AdResponse;
import com.mopub.network.MoPubNetworkError;
import com.mopub.network.MoPubNetworkError.Reason;
import com.mopub.network.Networking;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.NoConnectionError;
import com.mopub.volley.VolleyError;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import org.json.simple.parser.Yytoken;

public class MoPubRewardedVideoManager {
    public static final int API_VERSION = 1;
    private static final int DEFAULT_LOAD_TIMEOUT = 30000;
    private static MoPubRewardedVideoManager sInstance;
    @NonNull
    private final AdRequestStatusMapping mAdRequestStatus;
    private final long mBroadcastIdentifier;
    @NonNull
    private final Handler mCallbackHandler;
    @NonNull
    private final Context mContext;
    @NonNull
    private final Handler mCustomEventTimeoutHandler;
    @NonNull
    private final Set<MediationSettings> mGlobalMediationSettings;
    @NonNull
    private final Map<String, Set<MediationSettings>> mInstanceMediationSettings;
    @NonNull
    private WeakReference<Activity> mMainActivity;
    @NonNull
    private final RewardedVideoData mRewardedVideoData;
    @NonNull
    private final Map<String, Runnable> mTimeoutMap;
    @Nullable
    private MoPubRewardedVideoListener mVideoListener;

    private static abstract class ForEachMoPubIdRunnable implements Runnable {
        @NonNull
        private final Class<? extends CustomEventRewardedVideo> mCustomEventClass;
        @NonNull
        private final String mThirdPartyId;

        protected abstract void forEach(@NonNull String str);

        ForEachMoPubIdRunnable(@NonNull Class<? extends CustomEventRewardedVideo> customEventClass, @NonNull String thirdPartyId) {
            Preconditions.checkNotNull(customEventClass);
            Preconditions.checkNotNull(thirdPartyId);
            this.mCustomEventClass = customEventClass;
            this.mThirdPartyId = thirdPartyId;
        }

        public void run() {
            for (String moPubId : MoPubRewardedVideoManager.sInstance.mRewardedVideoData.getMoPubIdsForAdNetwork(this.mCustomEventClass, this.mThirdPartyId)) {
                forEach(moPubId);
            }
        }
    }

    static class 10 extends ForEachMoPubIdRunnable {
        10(Class customEventClass, String thirdPartyId) {
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.onRewardedVideoClosedAction(moPubId);
        }
    }

    static class 11 implements Runnable {
        final /* synthetic */ String val$currentAdUnitId;

        11(String str) {
            this.val$currentAdUnitId = str;
        }

        public void run() {
            MoPubRewardedVideoManager.onRewardedVideoClosedAction(this.val$currentAdUnitId);
        }
    }

    static class 12 implements Runnable {
        final /* synthetic */ Class val$customEventClass;
        final /* synthetic */ MoPubReward val$moPubReward;
        final /* synthetic */ String val$thirdPartyId;

        12(Class cls, MoPubReward moPubReward, String str) {
            this.val$customEventClass = cls;
            this.val$moPubReward = moPubReward;
            this.val$thirdPartyId = str;
        }

        public void run() {
            MoPubReward chosenReward = MoPubRewardedVideoManager.chooseReward(MoPubRewardedVideoManager.sInstance.mRewardedVideoData.getLastShownMoPubReward(this.val$customEventClass), this.val$moPubReward);
            Set<String> rewarded = new HashSet(MoPubRewardedVideoManager.sInstance.mRewardedVideoData.getMoPubIdsForAdNetwork(this.val$customEventClass, this.val$thirdPartyId));
            if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoCompleted(rewarded, chosenReward);
            }
        }
    }

    static class 13 implements Runnable {
        final /* synthetic */ String val$serverCompletionUrl;

        13(String str) {
            this.val$serverCompletionUrl = str;
        }

        public void run() {
            RewardedVideoCompletionRequestHandler.makeRewardedVideoCompletionRequest(MoPubRewardedVideoManager.sInstance.mContext, this.val$serverCompletionUrl, MoPubRewardedVideoManager.sInstance.mRewardedVideoData.getCustomerId());
        }
    }

    static /* synthetic */ class 14 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$network$MoPubNetworkError$Reason;

        static {
            $SwitchMap$com$mopub$network$MoPubNetworkError$Reason = new int[Reason.values().length];
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.NO_FILL.ordinal()] = MoPubRewardedVideoManager.API_VERSION;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.WARMING_UP.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.BAD_BODY.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$network$MoPubNetworkError$Reason[Reason.BAD_HEADER_DATA.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    class 1 implements Runnable {
        final /* synthetic */ CustomEventRewardedVideo val$customEvent;

        1(CustomEventRewardedVideo customEventRewardedVideo) {
            this.val$customEvent = customEventRewardedVideo;
        }

        public void run() {
            MoPubLog.d("Custom Event failed to load rewarded video in a timely fashion.");
            MoPubRewardedVideoManager.onRewardedVideoLoadFailure(this.val$customEvent.getClass(), this.val$customEvent.getAdNetworkId(), MoPubErrorCode.NETWORK_TIMEOUT);
            this.val$customEvent.onInvalidate();
        }
    }

    static class 2 extends ForEachMoPubIdRunnable {
        2(Class customEventClass, String thirdPartyId) {
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.sInstance.cancelTimeouts(moPubId);
            if (MoPubRewardedVideoManager.sInstance.mVideoListener != null) {
                MoPubRewardedVideoManager.sInstance.mVideoListener.onRewardedVideoLoadSuccess(moPubId);
            }
        }
    }

    static class 3 extends ForEachMoPubIdRunnable {
        final /* synthetic */ MoPubErrorCode val$errorCode;

        3(Class customEventClass, String thirdPartyId, MoPubErrorCode moPubErrorCode) {
            this.val$errorCode = moPubErrorCode;
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.sInstance.cancelTimeouts(moPubId);
            MoPubRewardedVideoManager.sInstance.failover(moPubId, this.val$errorCode);
        }
    }

    static class 4 extends ForEachMoPubIdRunnable {
        4(Class customEventClass, String thirdPartyId) {
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.onRewardedVideoStartedAction(moPubId);
        }
    }

    static class 5 implements Runnable {
        final /* synthetic */ String val$currentAdUnitId;

        5(String str) {
            this.val$currentAdUnitId = str;
        }

        public void run() {
            MoPubRewardedVideoManager.onRewardedVideoStartedAction(this.val$currentAdUnitId);
        }
    }

    static class 6 extends ForEachMoPubIdRunnable {
        final /* synthetic */ MoPubErrorCode val$errorCode;

        6(Class customEventClass, String thirdPartyId, MoPubErrorCode moPubErrorCode) {
            this.val$errorCode = moPubErrorCode;
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(moPubId, this.val$errorCode);
        }
    }

    static class 7 implements Runnable {
        final /* synthetic */ String val$currentAdUnitId;
        final /* synthetic */ MoPubErrorCode val$errorCode;

        7(String str, MoPubErrorCode moPubErrorCode) {
            this.val$currentAdUnitId = str;
            this.val$errorCode = moPubErrorCode;
        }

        public void run() {
            MoPubRewardedVideoManager.onRewardedVideoPlaybackErrorAction(this.val$currentAdUnitId, this.val$errorCode);
        }
    }

    static class 8 extends ForEachMoPubIdRunnable {
        8(Class customEventClass, String thirdPartyId) {
            super(customEventClass, thirdPartyId);
        }

        protected void forEach(@NonNull String moPubId) {
            MoPubRewardedVideoManager.onRewardedVideoClickedAction(moPubId);
        }
    }

    static class 9 implements Runnable {
        final /* synthetic */ String val$currentAdUnitId;

        9(String str) {
            this.val$currentAdUnitId = str;
        }

        public void run() {
            MoPubRewardedVideoManager.onRewardedVideoClickedAction(this.val$currentAdUnitId);
        }
    }

    public static final class RequestParameters {
        @Nullable
        public final String mCustomerId;
        @Nullable
        public final String mKeywords;
        @Nullable
        public final Location mLocation;

        public RequestParameters(@Nullable String keywords) {
            this(keywords, null);
        }

        public RequestParameters(@Nullable String keywords, @Nullable Location location) {
            this(keywords, location, null);
        }

        public RequestParameters(@Nullable String keywords, @Nullable Location location, @Nullable String customerId) {
            this.mKeywords = keywords;
            this.mLocation = location;
            this.mCustomerId = customerId;
        }
    }

    public static class RewardedVideoRequestListener implements Listener {
        public final String adUnitId;
        private final MoPubRewardedVideoManager mVideoManager;

        public RewardedVideoRequestListener(MoPubRewardedVideoManager videoManager, String adUnitId) {
            this.adUnitId = adUnitId;
            this.mVideoManager = videoManager;
        }

        public void onSuccess(AdResponse response) {
            this.mVideoManager.onAdSuccess(response, this.adUnitId);
        }

        public void onErrorResponse(VolleyError volleyError) {
            this.mVideoManager.onAdError(volleyError, this.adUnitId);
        }
    }

    private MoPubRewardedVideoManager(@NonNull Activity mainActivity, MediationSettings... mediationSettings) {
        this.mMainActivity = new WeakReference(mainActivity);
        this.mContext = mainActivity.getApplicationContext();
        this.mRewardedVideoData = new RewardedVideoData();
        this.mCallbackHandler = new Handler(Looper.getMainLooper());
        this.mGlobalMediationSettings = new HashSet();
        MoPubCollections.addAllNonNull(this.mGlobalMediationSettings, mediationSettings);
        this.mInstanceMediationSettings = new HashMap();
        this.mCustomEventTimeoutHandler = new Handler();
        this.mTimeoutMap = new HashMap();
        this.mBroadcastIdentifier = Utils.generateUniqueId();
        this.mAdRequestStatus = new AdRequestStatusMapping();
    }

    public static synchronized void init(@NonNull Activity mainActivity, MediationSettings... mediationSettings) {
        synchronized (MoPubRewardedVideoManager.class) {
            if (sInstance == null) {
                sInstance = new MoPubRewardedVideoManager(mainActivity, mediationSettings);
            } else {
                MoPubLog.e("Tried to call initializeRewardedVideo more than once. Only the first initialization call has any effect.");
            }
        }
    }

    public static void updateActivity(@NonNull Activity activity) {
        if (sInstance != null) {
            sInstance.mMainActivity = new WeakReference(activity);
            return;
        }
        logErrorNotInitialized();
    }

    @Nullable
    public static <T extends MediationSettings> T getGlobalMediationSettings(@NonNull Class<T> clazz) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        for (MediationSettings mediationSettings : sInstance.mGlobalMediationSettings) {
            if (clazz.equals(mediationSettings.getClass())) {
                return (MediationSettings) clazz.cast(mediationSettings);
            }
        }
        return null;
    }

    @Nullable
    public static <T extends MediationSettings> T getInstanceMediationSettings(@NonNull Class<T> clazz, @NonNull String adUnitId) {
        if (sInstance == null) {
            logErrorNotInitialized();
            return null;
        }
        Set<MediationSettings> instanceMediationSettings = (Set) sInstance.mInstanceMediationSettings.get(adUnitId);
        if (instanceMediationSettings == null) {
            return null;
        }
        for (MediationSettings mediationSettings : instanceMediationSettings) {
            if (clazz.equals(mediationSettings.getClass())) {
                return (MediationSettings) clazz.cast(mediationSettings);
            }
        }
        return null;
    }

    public static void setVideoListener(@Nullable MoPubRewardedVideoListener listener) {
        if (sInstance != null) {
            sInstance.mVideoListener = listener;
        } else {
            logErrorNotInitialized();
        }
    }

    public static void loadVideo(@NonNull String adUnitId, @Nullable RequestParameters requestParameters, @Nullable MediationSettings... mediationSettings) {
        Location location = null;
        if (sInstance == null) {
            logErrorNotInitialized();
            return;
        }
        Set<MediationSettings> newInstanceMediationSettings = new HashSet();
        MoPubCollections.addAllNonNull(newInstanceMediationSettings, mediationSettings);
        sInstance.mInstanceMediationSettings.put(adUnitId, newInstanceMediationSettings);
        String customerId = requestParameters == null ? null : requestParameters.mCustomerId;
        if (!TextUtils.isEmpty(customerId)) {
            sInstance.mRewardedVideoData.setCustomerId(customerId);
        }
        AdUrlGenerator withKeywords = new WebViewAdUrlGenerator(sInstance.mContext, false).withAdUnitId(adUnitId).withKeywords(requestParameters == null ? null : requestParameters.mKeywords);
        if (requestParameters != null) {
            location = requestParameters.mLocation;
        }
        loadVideo(adUnitId, withKeywords.withLocation(location).generateUrlString(Constants.HOST));
    }

    private static void loadVideo(@NonNull String adUnitId, @NonNull String adUrlString) {
        if (sInstance == null) {
            logErrorNotInitialized();
        } else if (sInstance.mAdRequestStatus.isLoading(adUnitId)) {
            Object[] objArr = new Object[API_VERSION];
            objArr[0] = adUnitId;
            MoPubLog.d(String.format(Locale.US, "Did not queue rewarded video request for ad unit %s. A request is already pending.", objArr));
        } else {
            Networking.getRequestQueue(sInstance.mContext).add(new AdRequest(adUrlString, AdFormat.REWARDED_VIDEO, adUnitId, sInstance.mContext, new RewardedVideoRequestListener(sInstance, adUnitId)));
            sInstance.mAdRequestStatus.markLoading(adUnitId);
        }
    }

    public static boolean hasVideo(@NonNull String adUnitId) {
        if (sInstance != null) {
            return isPlayable(adUnitId, sInstance.mRewardedVideoData.getCustomEvent(adUnitId));
        }
        logErrorNotInitialized();
        return false;
    }

    public static void showVideo(@NonNull String adUnitId) {
        if (sInstance != null) {
            CustomEventRewardedVideo customEvent = sInstance.mRewardedVideoData.getCustomEvent(adUnitId);
            if (isPlayable(adUnitId, customEvent)) {
                sInstance.mRewardedVideoData.updateCustomEventLastShownRewardMapping(customEvent.getClass(), sInstance.mRewardedVideoData.getMoPubReward(adUnitId));
                sInstance.mRewardedVideoData.setCurrentAdUnitId(adUnitId);
                sInstance.mAdRequestStatus.markPlayed(adUnitId);
                customEvent.showVideo();
                return;
            }
            sInstance.failover(adUnitId, MoPubErrorCode.VIDEO_NOT_AVAILABLE);
            return;
        }
        logErrorNotInitialized();
    }

    private static boolean isPlayable(String adUnitId, @Nullable CustomEventRewardedVideo customEvent) {
        return sInstance != null && sInstance.mAdRequestStatus.canPlay(adUnitId) && customEvent != null && customEvent.hasVideoAvailable();
    }

    private void onAdSuccess(AdResponse adResponse, String adUnitId) {
        this.mAdRequestStatus.markLoaded(adUnitId, adResponse.getFailoverUrl(), adResponse.getImpressionTrackingUrl(), adResponse.getClickTrackingUrl());
        Integer timeoutMillis = adResponse.getAdTimeoutMillis();
        if (timeoutMillis == null || timeoutMillis.intValue() <= 0) {
            timeoutMillis = Integer.valueOf(DEFAULT_LOAD_TIMEOUT);
        }
        String customEventClassName = adResponse.getCustomEventClassName();
        if (customEventClassName == null) {
            MoPubLog.e("Couldn't create custom event, class name was null.");
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
            return;
        }
        CustomEventRewardedVideo oldRewardedVideo = this.mRewardedVideoData.getCustomEvent(adUnitId);
        if (oldRewardedVideo != null) {
            oldRewardedVideo.onInvalidate();
        }
        try {
            CustomEventRewardedVideo customEvent = (CustomEventRewardedVideo) Reflection.instantiateClassWithEmptyConstructor(customEventClassName, CustomEventRewardedVideo.class);
            Map<String, Object> localExtras = new TreeMap();
            localExtras.put(DataKeys.AD_UNIT_ID_KEY, adUnitId);
            localExtras.put(DataKeys.REWARDED_VIDEO_CURRENCY_NAME_KEY, adResponse.getRewardedVideoCurrencyName());
            localExtras.put(DataKeys.REWARDED_VIDEO_CURRENCY_AMOUNT_STRING_KEY, adResponse.getRewardedVideoCurrencyAmount());
            localExtras.put(DataKeys.AD_REPORT_KEY, new AdReport(adUnitId, ClientMetadata.getInstance(this.mContext), adResponse));
            localExtras.put(DataKeys.BROADCAST_IDENTIFIER_KEY, Long.valueOf(this.mBroadcastIdentifier));
            localExtras.put(DataKeys.REWARDED_VIDEO_CUSTOMER_ID, this.mRewardedVideoData.getCustomerId());
            this.mRewardedVideoData.updateAdUnitRewardMapping(adUnitId, adResponse.getRewardedVideoCurrencyName(), adResponse.getRewardedVideoCurrencyAmount());
            this.mRewardedVideoData.updateAdUnitToServerCompletionUrlMapping(adUnitId, adResponse.getRewardedVideoCompletionUrl());
            Activity mainActivity = (Activity) this.mMainActivity.get();
            if (mainActivity == null) {
                MoPubLog.d("Could not load custom event because Activity reference was null. Call MoPub#updateActivity before requesting more rewarded videos.");
                this.mAdRequestStatus.markFail(adUnitId);
                return;
            }
            Runnable timeout = new 1(customEvent);
            this.mCustomEventTimeoutHandler.postDelayed(timeout, (long) timeoutMillis.intValue());
            this.mTimeoutMap.put(adUnitId, timeout);
            customEvent.loadCustomEvent(mainActivity, localExtras, adResponse.getServerExtras());
            this.mRewardedVideoData.updateAdUnitCustomEventMapping(adUnitId, customEvent, customEvent.getVideoListenerForSdk(), customEvent.getAdNetworkId());
        } catch (Exception e) {
            Object[] objArr = new Object[API_VERSION];
            objArr[0] = customEventClassName;
            MoPubLog.e(String.format(Locale.US, "Couldn't create custom event with class name %s", objArr));
            failover(adUnitId, MoPubErrorCode.ADAPTER_CONFIGURATION_ERROR);
        }
    }

    private void onAdError(@NonNull VolleyError volleyError, @NonNull String adUnitId) {
        MoPubErrorCode errorCode = MoPubErrorCode.INTERNAL_ERROR;
        if (volleyError instanceof MoPubNetworkError) {
            switch (14.$SwitchMap$com$mopub$network$MoPubNetworkError$Reason[((MoPubNetworkError) volleyError).getReason().ordinal()]) {
                case API_VERSION /*1*/:
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    errorCode = MoPubErrorCode.NO_FILL;
                    break;
                default:
                    errorCode = MoPubErrorCode.INTERNAL_ERROR;
                    break;
            }
        }
        if (volleyError instanceof NoConnectionError) {
            errorCode = MoPubErrorCode.NO_CONNECTION;
        }
        failover(adUnitId, errorCode);
    }

    private void failover(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        String failoverUrl = this.mAdRequestStatus.getFailoverUrl(adUnitId);
        this.mAdRequestStatus.markFail(adUnitId);
        if (failoverUrl != null) {
            loadVideo(adUnitId, failoverUrl);
        } else if (this.mVideoListener != null) {
            this.mVideoListener.onRewardedVideoLoadFailure(adUnitId, errorCode);
        }
    }

    private void cancelTimeouts(@NonNull String moPubId) {
        Runnable runnable = (Runnable) this.mTimeoutMap.remove(moPubId);
        if (runnable != null) {
            this.mCustomEventTimeoutHandler.removeCallbacks(runnable);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoLoadSuccess(@NonNull Class<T> customEventClass, @NonNull String thirdPartyId) {
        postToInstance(new 2(customEventClass, thirdPartyId));
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoLoadFailure(@NonNull Class<T> customEventClass, String thirdPartyId, MoPubErrorCode errorCode) {
        postToInstance(new 3(customEventClass, thirdPartyId, errorCode));
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoStarted(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 4(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 5(currentAdUnitId));
        }
    }

    private static void onRewardedVideoStartedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoStarted(adUnitId);
        }
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getImpressionTrackerUrlString(adUnitId), sInstance.mContext);
        sInstance.mAdRequestStatus.clearImpressionUrl(adUnitId);
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoPlaybackError(@NonNull Class<T> customEventClass, String thirdPartyId, MoPubErrorCode errorCode) {
        String currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 6(customEventClass, thirdPartyId, errorCode));
        } else {
            postToInstance(new 7(currentAdUnitId, errorCode));
        }
    }

    private static void onRewardedVideoPlaybackErrorAction(@NonNull String adUnitId, @NonNull MoPubErrorCode errorCode) {
        Preconditions.checkNotNull(adUnitId);
        Preconditions.checkNotNull(errorCode);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoPlaybackError(adUnitId, errorCode);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoClicked(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 8(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 9(currentAdUnitId));
        }
    }

    private static void onRewardedVideoClickedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        TrackingRequest.makeTrackingHttpRequest(sInstance.mAdRequestStatus.getClickTrackerUrlString(adUnitId), sInstance.mContext);
        sInstance.mAdRequestStatus.clearClickUrl(adUnitId);
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoClosed(@NonNull Class<T> customEventClass, String thirdPartyId) {
        String currentAdUnitId = sInstance.mRewardedVideoData.getCurrentAdUnitId();
        if (TextUtils.isEmpty(currentAdUnitId)) {
            postToInstance(new 10(customEventClass, thirdPartyId));
        } else {
            postToInstance(new 11(currentAdUnitId));
        }
    }

    private static void onRewardedVideoClosedAction(@NonNull String adUnitId) {
        Preconditions.checkNotNull(adUnitId);
        if (sInstance.mVideoListener != null) {
            sInstance.mVideoListener.onRewardedVideoClosed(adUnitId);
        }
    }

    public static <T extends CustomEventRewardedVideo> void onRewardedVideoCompleted(@NonNull Class<T> customEventClass, String thirdPartyId, @NonNull MoPubReward moPubReward) {
        String serverCompletionUrl = sInstance.mRewardedVideoData.getServerCompletionUrl(sInstance.mRewardedVideoData.getCurrentAdUnitId());
        if (TextUtils.isEmpty(serverCompletionUrl)) {
            postToInstance(new 12(customEventClass, moPubReward, thirdPartyId));
        } else {
            postToInstance(new 13(serverCompletionUrl));
        }
    }

    @VisibleForTesting
    static MoPubReward chooseReward(@Nullable MoPubReward moPubReward, @NonNull MoPubReward networkReward) {
        if (!networkReward.isSuccessful()) {
            return networkReward;
        }
        if (moPubReward == null) {
            moPubReward = networkReward;
        }
        return moPubReward;
    }

    private static void postToInstance(@NonNull Runnable runnable) {
        if (sInstance != null) {
            sInstance.mCallbackHandler.post(runnable);
        }
    }

    private static void logErrorNotInitialized() {
        MoPubLog.e("MoPub rewarded video was not initialized. You must call MoPub.initializeRewardedVideo() before loading or attempting to play video ads.");
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    static RewardedVideoData getRewardedVideoData() {
        if (sInstance != null) {
            return sInstance.mRewardedVideoData;
        }
        return null;
    }
}
