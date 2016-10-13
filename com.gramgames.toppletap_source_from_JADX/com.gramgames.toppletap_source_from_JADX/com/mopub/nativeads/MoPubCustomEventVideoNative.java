package com.mopub.nativeads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.SurfaceTexture;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import com.facebook.ads.AdError;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.common.DataKeys;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.mopub.mobileads.VastManager;
import com.mopub.mobileads.VastManager.VastManagerListener;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.mobileads.VideoViewabilityTracker;
import com.mopub.mobileads.factories.VastManagerFactory;
import com.mopub.nativeads.CustomEventNative.CustomEventNativeListener;
import com.mopub.nativeads.MediaLayout.Mode;
import com.mopub.nativeads.MediaLayout.MuteState;
import com.mopub.nativeads.NativeImageHelper.ImageListener;
import com.mopub.nativeads.NativeVideoController.NativeVideoProgressRunnable.ProgressListener;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

@TargetApi(16)
public class MoPubCustomEventVideoNative extends CustomEventNative {

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter;
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState;

        static {
            $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState = new int[VideoState.values().length];
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.FAILED_LOAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.CREATED.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.LOADING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.BUFFERING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.PLAYING.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.PLAYING_MUTED.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[VideoState.ENDED.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
            $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter = new int[Parameter.values().length];
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.IMPRESSION_TRACKER.ordinal()] = 1;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.TITLE.ordinal()] = 2;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.TEXT.ordinal()] = 3;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.IMAGE_URL.ordinal()] = 4;
            } catch (NoSuchFieldError e12) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.ICON_URL.ordinal()] = 5;
            } catch (NoSuchFieldError e13) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.CLICK_DESTINATION.ordinal()] = 6;
            } catch (NoSuchFieldError e14) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.CLICK_TRACKER.ordinal()] = 7;
            } catch (NoSuchFieldError e15) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.CALL_TO_ACTION.ordinal()] = 8;
            } catch (NoSuchFieldError e16) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[Parameter.VAST_VIDEO.ordinal()] = 9;
            } catch (NoSuchFieldError e17) {
            }
        }
    }

    @TargetApi(16)
    @VisibleForTesting
    static class HeaderVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final WeakReference<MoPubVideoNativeAd> mMoPubVideoNativeAd;

        HeaderVisibilityStrategy(@NonNull MoPubVideoNativeAd moPubVideoNativeAd) {
            this.mMoPubVideoNativeAd = new WeakReference(moPubVideoNativeAd);
        }

        public void execute() {
            MoPubVideoNativeAd moPubVideoNativeAd = (MoPubVideoNativeAd) this.mMoPubVideoNativeAd.get();
            if (moPubVideoNativeAd != null) {
                moPubVideoNativeAd.notifyAdImpressed();
            }
        }
    }

    @TargetApi(16)
    public static class MoPubVideoNativeAd extends VideoNativeAd implements VastManagerListener, ProgressListener, OnAudioFocusChangeListener {
        static final String PRIVACY_INFORMATION_CLICKTHROUGH_URL = "https://www.mopub.com/optout/";
        @NonNull
        private final Context mContext;
        @NonNull
        private final CustomEventNativeListener mCustomEventNativeListener;
        private boolean mEnded;
        private boolean mError;
        @Nullable
        private final EventDetails mEventDetails;
        private final long mId;
        @NonNull
        private final JSONObject mJsonObject;
        private int mLatestVideoControllerState;
        private boolean mLatestVisibility;
        @Nullable
        private MediaLayout mMediaLayout;
        @NonNull
        private final String mMoPubClickTrackingUrl;
        private boolean mMuted;
        @Nullable
        private NativeVideoController mNativeVideoController;
        @NonNull
        private final NativeVideoControllerFactory mNativeVideoControllerFactory;
        private boolean mNeedsPrepare;
        private boolean mNeedsSeek;
        private boolean mPauseCanBeTracked;
        private boolean mResumeCanBeTracked;
        @Nullable
        private View mRootView;
        @NonNull
        private final VastManager mVastManager;
        @Nullable
        VastVideoConfig mVastVideoConfig;
        @NonNull
        private final VideoResponseHeaders mVideoResponseHeaders;
        @NonNull
        private VideoState mVideoState;
        @NonNull
        private final VisibilityTracker mVideoVisibleTracking;

        class 1 implements VisibilityTrackerListener {
            1() {
            }

            public void onVisibilityChanged(List<View> visibleViews, List<View> invisibleViews) {
                if (!visibleViews.isEmpty() && !MoPubVideoNativeAd.this.mLatestVisibility) {
                    MoPubVideoNativeAd.this.mLatestVisibility = true;
                    MoPubVideoNativeAd.this.maybeChangeState();
                } else if (!invisibleViews.isEmpty() && MoPubVideoNativeAd.this.mLatestVisibility) {
                    MoPubVideoNativeAd.this.mLatestVisibility = false;
                    MoPubVideoNativeAd.this.maybeChangeState();
                }
            }
        }

        class 2 implements ImageListener {
            2() {
            }

            public void onImagesCached() {
                MoPubVideoNativeAd.this.mVastManager.prepareVastVideoConfiguration(MoPubVideoNativeAd.this.getVastVideo(), MoPubVideoNativeAd.this, MoPubVideoNativeAd.this.mEventDetails == null ? null : MoPubVideoNativeAd.this.mEventDetails.getDspCreativeId(), MoPubVideoNativeAd.this.mContext);
            }

            public void onImagesFailedToCache(NativeErrorCode errorCode) {
                MoPubVideoNativeAd.this.mCustomEventNativeListener.onNativeAdFailed(errorCode);
            }
        }

        class 3 implements SurfaceTextureListener {
            3() {
            }

            public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
                MoPubVideoNativeAd.this.mNativeVideoController.setListener(MoPubVideoNativeAd.this);
                MoPubVideoNativeAd.this.mNativeVideoController.setOnAudioFocusChangeListener(MoPubVideoNativeAd.this);
                MoPubVideoNativeAd.this.mNativeVideoController.setProgressListener(MoPubVideoNativeAd.this);
                MoPubVideoNativeAd.this.mNativeVideoController.setTextureView(MoPubVideoNativeAd.this.mMediaLayout.getTextureView());
                MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                long duration = MoPubVideoNativeAd.this.mNativeVideoController.getDuration();
                long currentPosition = MoPubVideoNativeAd.this.mNativeVideoController.getCurrentPosition();
                if (MoPubVideoNativeAd.this.mLatestVideoControllerState == 5 || (duration > 0 && duration - currentPosition < 750)) {
                    MoPubVideoNativeAd.this.mEnded = true;
                }
                if (MoPubVideoNativeAd.this.mNeedsPrepare) {
                    MoPubVideoNativeAd.this.mNeedsPrepare = false;
                    MoPubVideoNativeAd.this.mNativeVideoController.prepare(MoPubVideoNativeAd.this);
                }
                MoPubVideoNativeAd.this.mNeedsSeek = true;
                MoPubVideoNativeAd.this.maybeChangeState();
            }

            public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
            }

            public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
                MoPubVideoNativeAd.this.mNeedsPrepare = true;
                MoPubVideoNativeAd.this.mNativeVideoController.release(MoPubVideoNativeAd.this);
                MoPubVideoNativeAd.this.applyState(VideoState.PAUSED);
                return true;
            }

            public void onSurfaceTextureUpdated(SurfaceTexture surface) {
            }
        }

        class 4 implements OnClickListener {
            4() {
            }

            public void onClick(View v) {
                MoPubVideoNativeAd.this.mMediaLayout.resetProgress();
                MoPubVideoNativeAd.this.mNativeVideoController.seekTo(0);
                MoPubVideoNativeAd.this.mEnded = false;
                MoPubVideoNativeAd.this.mNeedsSeek = false;
            }
        }

        class 5 implements OnClickListener {
            5() {
            }

            public void onClick(View v) {
                MoPubVideoNativeAd.this.mMuted = !MoPubVideoNativeAd.this.mMuted;
                MoPubVideoNativeAd.this.maybeChangeState();
            }
        }

        class 6 implements OnClickListener {
            6() {
            }

            public void onClick(View v) {
                MoPubVideoNativeAd.this.prepareToLeaveView();
                MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                BaseVideoPlayerActivity.startNativeVideo(MoPubVideoNativeAd.this.mContext, MoPubVideoNativeAd.this.mId, MoPubVideoNativeAd.this.mVastVideoConfig);
            }
        }

        class 7 implements OnClickListener {
            7() {
            }

            public void onClick(View v) {
                MoPubVideoNativeAd.this.prepareToLeaveView();
                MoPubVideoNativeAd.this.mNativeVideoController.triggerImpressionTrackers();
                MoPubVideoNativeAd.this.mNativeVideoController.handleCtaClick(MoPubVideoNativeAd.this.mContext);
            }
        }

        enum Parameter {
            IMPRESSION_TRACKER("imptracker", true),
            CLICK_TRACKER("clktracker", true),
            TITLE(ShareConstants.WEB_DIALOG_PARAM_TITLE, false),
            TEXT(MimeTypes.BASE_TYPE_TEXT, false),
            IMAGE_URL("mainimage", false),
            ICON_URL("iconimage", false),
            CLICK_DESTINATION("clk", false),
            FALLBACK("fallback", false),
            CALL_TO_ACTION("ctatext", false),
            VAST_VIDEO(MimeTypes.BASE_TYPE_VIDEO, false);
            
            @NonNull
            @VisibleForTesting
            static final Set<String> requiredKeys;
            @NonNull
            final String mName;
            final boolean mRequired;

            static {
                requiredKeys = new HashSet();
                Parameter[] values = values();
                int length = values.length;
                int i;
                while (i < length) {
                    Parameter parameter = values[i];
                    if (parameter.mRequired) {
                        requiredKeys.add(parameter.mName);
                    }
                    i++;
                }
            }

            private Parameter(@NonNull String name, boolean required) {
                Preconditions.checkNotNull(name);
                this.mName = name;
                this.mRequired = required;
            }

            @Nullable
            static Parameter from(@NonNull String name) {
                Preconditions.checkNotNull(name);
                for (Parameter parameter : values()) {
                    if (parameter.mName.equals(name)) {
                        return parameter;
                    }
                }
                return null;
            }
        }

        public enum VideoState {
            CREATED,
            LOADING,
            BUFFERING,
            PAUSED,
            PLAYING,
            PLAYING_MUTED,
            ENDED,
            FAILED_LOAD
        }

        public MoPubVideoNativeAd(@NonNull Activity activity, @NonNull JSONObject jsonObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @Nullable EventDetails eventDetails, @NonNull String clickTrackingUrl) {
            this(activity, jsonObject, customEventNativeListener, videoResponseHeaders, new VisibilityTracker(activity), new NativeVideoControllerFactory(), eventDetails, clickTrackingUrl, VastManagerFactory.create(activity.getApplicationContext(), false));
        }

        @VisibleForTesting
        MoPubVideoNativeAd(@NonNull Activity activity, @NonNull JSONObject jsonObject, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull VideoResponseHeaders videoResponseHeaders, @NonNull VisibilityTracker visibilityTracker, @NonNull NativeVideoControllerFactory nativeVideoControllerFactory, @Nullable EventDetails eventDetails, @NonNull String clickTrackingUrl, @NonNull VastManager vastManager) {
            this.mPauseCanBeTracked = false;
            this.mResumeCanBeTracked = false;
            Preconditions.checkNotNull(activity);
            Preconditions.checkNotNull(jsonObject);
            Preconditions.checkNotNull(customEventNativeListener);
            Preconditions.checkNotNull(videoResponseHeaders);
            Preconditions.checkNotNull(visibilityTracker);
            Preconditions.checkNotNull(nativeVideoControllerFactory);
            Preconditions.checkNotNull(clickTrackingUrl);
            Preconditions.checkNotNull(vastManager);
            this.mContext = activity.getApplicationContext();
            this.mJsonObject = jsonObject;
            this.mCustomEventNativeListener = customEventNativeListener;
            this.mVideoResponseHeaders = videoResponseHeaders;
            this.mNativeVideoControllerFactory = nativeVideoControllerFactory;
            this.mMoPubClickTrackingUrl = clickTrackingUrl;
            this.mEventDetails = eventDetails;
            this.mId = Utils.generateUniqueId();
            this.mNeedsSeek = true;
            this.mVideoState = VideoState.CREATED;
            this.mNeedsPrepare = true;
            this.mLatestVideoControllerState = 1;
            this.mMuted = true;
            this.mVideoVisibleTracking = visibilityTracker;
            this.mVideoVisibleTracking.setVisibilityTrackerListener(new 1());
            this.mVastManager = vastManager;
        }

        void loadAd() throws IllegalArgumentException {
            if (containsRequiredKeys(this.mJsonObject)) {
                Iterator<String> keys = this.mJsonObject.keys();
                while (keys.hasNext()) {
                    String key = (String) keys.next();
                    Parameter parameter = Parameter.from(key);
                    if (parameter != null) {
                        try {
                            addInstanceVariable(parameter, this.mJsonObject.opt(key));
                        } catch (ClassCastException e) {
                            throw new IllegalArgumentException("JSONObject key (" + key + ") contained unexpected value.");
                        }
                    }
                    addExtra(key, this.mJsonObject.opt(key));
                }
                setPrivacyInformationIconClickThroughUrl(PRIVACY_INFORMATION_CLICKTHROUGH_URL);
                NativeImageHelper.preCacheImages(this.mContext, getAllImageUrls(), new 2());
                return;
            }
            throw new IllegalArgumentException("JSONObject did not contain required keys.");
        }

        public void onVastVideoConfigurationPrepared(@Nullable VastVideoConfig vastVideoConfig) {
            if (vastVideoConfig == null) {
                this.mCustomEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
                return;
            }
            List<VisibilityTrackingEvent> visibilityTrackingEvents = new ArrayList();
            VisibilityTrackingEvent visibilityTrackingEvent = new VisibilityTrackingEvent();
            visibilityTrackingEvent.strategy = new HeaderVisibilityStrategy(this);
            visibilityTrackingEvent.minimumPercentageVisible = this.mVideoResponseHeaders.getImpressionMinVisiblePercent();
            visibilityTrackingEvent.totalRequiredPlayTimeMs = this.mVideoResponseHeaders.getImpressionVisibleMs();
            visibilityTrackingEvents.add(visibilityTrackingEvent);
            this.mVastVideoConfig = vastVideoConfig;
            VideoViewabilityTracker vastVideoViewabilityTracker = this.mVastVideoConfig.getVideoViewabilityTracker();
            if (vastVideoViewabilityTracker != null) {
                VisibilityTrackingEvent vastVisibilityTrackingEvent = new VisibilityTrackingEvent();
                vastVisibilityTrackingEvent.strategy = new PayloadVisibilityStrategy(this.mContext, vastVideoViewabilityTracker.getTrackingUrl());
                vastVisibilityTrackingEvent.minimumPercentageVisible = vastVideoViewabilityTracker.getPercentViewable();
                vastVisibilityTrackingEvent.totalRequiredPlayTimeMs = vastVideoViewabilityTracker.getViewablePlaytimeMS();
                visibilityTrackingEvents.add(vastVisibilityTrackingEvent);
            }
            Set<String> clickTrackers = new HashSet();
            clickTrackers.add(this.mMoPubClickTrackingUrl);
            clickTrackers.addAll(getClickTrackers());
            ArrayList<VastTracker> vastClickTrackers = new ArrayList();
            for (String clickTrackingUrl : clickTrackers) {
                vastClickTrackers.add(new VastTracker(clickTrackingUrl, false));
            }
            this.mVastVideoConfig.addClickTrackers(vastClickTrackers);
            this.mVastVideoConfig.setClickThroughUrl(getClickDestinationUrl());
            this.mNativeVideoController = this.mNativeVideoControllerFactory.createForId(this.mId, this.mContext, visibilityTrackingEvents, this.mVastVideoConfig, this.mEventDetails);
            this.mCustomEventNativeListener.onNativeAdLoaded(this);
        }

        private boolean containsRequiredKeys(@NonNull JSONObject jsonObject) {
            Preconditions.checkNotNull(jsonObject);
            Set<String> keys = new HashSet();
            Iterator<String> jsonKeys = jsonObject.keys();
            while (jsonKeys.hasNext()) {
                keys.add(jsonKeys.next());
            }
            return keys.containsAll(Parameter.requiredKeys);
        }

        private void addInstanceVariable(@NonNull Parameter key, @Nullable Object value) throws ClassCastException {
            Preconditions.checkNotNull(key);
            Preconditions.checkNotNull(value);
            try {
                switch (1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$Parameter[key.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        addImpressionTrackers(value);
                        return;
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                        setTitle((String) value);
                        return;
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        setText((String) value);
                        return;
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        setMainImageUrl((String) value);
                        return;
                    case Yytoken.TYPE_COMMA /*5*/:
                        setIconImageUrl((String) value);
                        return;
                    case Yytoken.TYPE_COLON /*6*/:
                        setClickDestinationUrl((String) value);
                        return;
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        parseClickTrackers(value);
                        return;
                    case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                        setCallToAction((String) value);
                        return;
                    case R.styleable.Toolbar_popupTheme /*9*/:
                        setVastVideo((String) value);
                        return;
                    default:
                        MoPubLog.d("Unable to add JSON key to internal mapping: " + key.mName);
                        return;
                }
            } catch (ClassCastException e) {
                if (key.mRequired) {
                    throw e;
                }
                MoPubLog.d("Ignoring class cast exception for optional key: " + key.mName);
                return;
            }
            if (key.mRequired) {
                MoPubLog.d("Ignoring class cast exception for optional key: " + key.mName);
                return;
            }
            throw e;
        }

        private void parseClickTrackers(@NonNull Object clickTrackers) {
            if (clickTrackers instanceof JSONArray) {
                addClickTrackers(clickTrackers);
            } else {
                addClickTracker((String) clickTrackers);
            }
        }

        public void render(@NonNull MediaLayout mediaLayout) {
            Preconditions.checkNotNull(mediaLayout);
            this.mVideoVisibleTracking.addView(this.mRootView, mediaLayout, this.mVideoResponseHeaders.getPlayVisiblePercent(), this.mVideoResponseHeaders.getPauseVisiblePercent());
            this.mMediaLayout = mediaLayout;
            this.mMediaLayout.initForVideo();
            this.mMediaLayout.setSurfaceTextureListener(new 3());
            this.mMediaLayout.setPlayButtonClickListener(new 4());
            this.mMediaLayout.setMuteControlClickListener(new 5());
            this.mMediaLayout.setOnClickListener(new 6());
            if (this.mNativeVideoController.getPlaybackState() == 6) {
                this.mNativeVideoController.prepare(this);
            }
            applyState(VideoState.PAUSED);
        }

        public void prepare(@NonNull View view) {
            Preconditions.checkNotNull(view);
            this.mRootView = view;
            this.mRootView.setOnClickListener(new 7());
        }

        public void clear(@NonNull View view) {
            Preconditions.checkNotNull(view);
            this.mNativeVideoController.clear();
            cleanUpMediaLayout();
        }

        public void destroy() {
            cleanUpMediaLayout();
            this.mNativeVideoController.setPlayWhenReady(false);
            this.mNativeVideoController.release(this);
            NativeVideoController.remove(this.mId);
            this.mVideoVisibleTracking.destroy();
        }

        public void onStateChanged(boolean playWhenReady, int playbackState) {
            this.mLatestVideoControllerState = playbackState;
            maybeChangeState();
        }

        public void onError(Exception e) {
            MoPubLog.w("Error playing back video.", e);
            this.mError = true;
            maybeChangeState();
        }

        public void updateProgress(int progressTenthPercent) {
            this.mMediaLayout.updateProgress(progressTenthPercent);
        }

        public void onAudioFocusChange(int focusChange) {
            if (focusChange == -1 || focusChange == -2) {
                this.mMuted = true;
                maybeChangeState();
            } else if (focusChange == -3) {
                this.mNativeVideoController.setAudioVolume(0.3f);
            } else if (focusChange == 1) {
                this.mNativeVideoController.setAudioVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
                maybeChangeState();
            }
        }

        private void cleanUpMediaLayout() {
            if (this.mMediaLayout != null) {
                this.mMediaLayout.setMode(Mode.IMAGE);
                this.mMediaLayout.setSurfaceTextureListener(null);
                this.mMediaLayout.setPlayButtonClickListener(null);
                this.mMediaLayout.setMuteControlClickListener(null);
                this.mMediaLayout.setOnClickListener(null);
                this.mVideoVisibleTracking.removeView(this.mMediaLayout);
                this.mMediaLayout = null;
            }
        }

        private void prepareToLeaveView() {
            this.mNeedsSeek = true;
            this.mNeedsPrepare = true;
            this.mNativeVideoController.setListener(null);
            this.mNativeVideoController.setOnAudioFocusChangeListener(null);
            this.mNativeVideoController.setProgressListener(null);
            this.mNativeVideoController.clear();
            applyState(VideoState.PAUSED, true);
        }

        private void maybeChangeState() {
            VideoState newState = this.mVideoState;
            if (this.mError) {
                newState = VideoState.FAILED_LOAD;
            } else if (this.mEnded) {
                newState = VideoState.ENDED;
            } else if (this.mLatestVideoControllerState == 2 || this.mLatestVideoControllerState == 1) {
                newState = VideoState.LOADING;
            } else if (this.mLatestVideoControllerState == 3) {
                newState = VideoState.BUFFERING;
            } else if (this.mLatestVideoControllerState == 5) {
                this.mEnded = true;
                newState = VideoState.ENDED;
            } else if (this.mLatestVideoControllerState == 4) {
                if (this.mLatestVisibility) {
                    newState = this.mMuted ? VideoState.PLAYING_MUTED : VideoState.PLAYING;
                } else {
                    newState = VideoState.PAUSED;
                }
            }
            applyState(newState);
        }

        @VisibleForTesting
        void applyState(@NonNull VideoState videoState) {
            applyState(videoState, false);
        }

        @VisibleForTesting
        void applyState(@NonNull VideoState videoState, boolean transitionToFullScreen) {
            Preconditions.checkNotNull(videoState);
            if (this.mVastVideoConfig != null && this.mNativeVideoController != null && this.mMediaLayout != null && this.mVideoState != videoState) {
                VideoState previousState = this.mVideoState;
                this.mVideoState = videoState;
                switch (1.$SwitchMap$com$mopub$nativeads$MoPubCustomEventVideoNative$MoPubVideoNativeAd$VideoState[videoState.ordinal()]) {
                    case Yytoken.TYPE_LEFT_BRACE /*1*/:
                        this.mVastVideoConfig.handleError(this.mContext, null, 0);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(Mode.IMAGE);
                        if (previousState != VideoState.PLAYING && previousState != VideoState.PLAYING_MUTED) {
                            MoPubEvents.log(Event.createEventFromDetails(Name.ERROR_FAILED_TO_PLAY, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
                        }
                    case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mMediaLayout.setMode(Mode.LOADING);
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mMediaLayout.setMode(Mode.BUFFERING);
                    case Yytoken.TYPE_COMMA /*5*/:
                        if (transitionToFullScreen) {
                            this.mResumeCanBeTracked = false;
                        }
                        if (!transitionToFullScreen) {
                            this.mNativeVideoController.setAppAudioEnabled(false);
                            if (this.mPauseCanBeTracked) {
                                TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getPauseTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
                                this.mPauseCanBeTracked = false;
                                this.mResumeCanBeTracked = true;
                            }
                        }
                        this.mNativeVideoController.setPlayWhenReady(false);
                        this.mMediaLayout.setMode(Mode.PAUSED);
                    case Yytoken.TYPE_COLON /*6*/:
                        handleResumeTrackersAndSeek(previousState);
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mNativeVideoController.setAudioEnabled(true);
                        this.mNativeVideoController.setAppAudioEnabled(true);
                        this.mMediaLayout.setMode(Mode.PLAYING);
                        this.mMediaLayout.setMuteState(MuteState.UNMUTED);
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        handleResumeTrackersAndSeek(previousState);
                        this.mNativeVideoController.setPlayWhenReady(true);
                        this.mNativeVideoController.setAudioEnabled(false);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(Mode.PLAYING);
                        this.mMediaLayout.setMuteState(MuteState.MUTED);
                    case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                        if (this.mNativeVideoController.hasFinalFrame()) {
                            this.mMediaLayout.setMainImageDrawable(this.mNativeVideoController.getFinalFrame());
                        }
                        this.mPauseCanBeTracked = false;
                        this.mResumeCanBeTracked = false;
                        this.mVastVideoConfig.handleComplete(this.mContext, 0);
                        this.mNativeVideoController.setAppAudioEnabled(false);
                        this.mMediaLayout.setMode(Mode.FINISHED);
                        this.mMediaLayout.updateProgress(AdError.NETWORK_ERROR_CODE);
                    default:
                }
            }
        }

        private void handleResumeTrackersAndSeek(VideoState previousState) {
            if (!(!this.mResumeCanBeTracked || previousState == VideoState.PLAYING || previousState == VideoState.PLAYING_MUTED)) {
                TrackingRequest.makeVastTrackingHttpRequest(this.mVastVideoConfig.getResumeTrackers(), null, Integer.valueOf((int) this.mNativeVideoController.getCurrentPosition()), null, this.mContext);
                this.mResumeCanBeTracked = false;
            }
            this.mPauseCanBeTracked = true;
            if (this.mNeedsSeek) {
                this.mNeedsSeek = false;
                this.mNativeVideoController.seekTo(this.mNativeVideoController.getCurrentPosition());
            }
        }

        private boolean isImageKey(@Nullable String name) {
            return name != null && name.toLowerCase(Locale.US).endsWith("image");
        }

        @NonNull
        private List<String> getExtrasImageUrls() {
            List<String> extrasBitmapUrls = new ArrayList(getExtras().size());
            for (Entry<String, Object> entry : getExtras().entrySet()) {
                if (isImageKey((String) entry.getKey()) && (entry.getValue() instanceof String)) {
                    extrasBitmapUrls.add((String) entry.getValue());
                }
            }
            return extrasBitmapUrls;
        }

        @NonNull
        private List<String> getAllImageUrls() {
            List<String> imageUrls = new ArrayList();
            if (getMainImageUrl() != null) {
                imageUrls.add(getMainImageUrl());
            }
            if (getIconImageUrl() != null) {
                imageUrls.add(getIconImageUrl());
            }
            imageUrls.addAll(getExtrasImageUrls());
            return imageUrls;
        }

        @Deprecated
        @VisibleForTesting
        boolean needsPrepare() {
            return this.mNeedsPrepare;
        }

        @Deprecated
        @VisibleForTesting
        boolean hasEnded() {
            return this.mEnded;
        }

        @Deprecated
        @VisibleForTesting
        boolean needsSeek() {
            return this.mNeedsSeek;
        }

        @Deprecated
        @VisibleForTesting
        boolean isMuted() {
            return this.mMuted;
        }

        @Deprecated
        @VisibleForTesting
        long getId() {
            return this.mId;
        }

        @Deprecated
        @VisibleForTesting
        VideoState getVideoState() {
            return this.mVideoState;
        }

        @Deprecated
        @VisibleForTesting
        void setLatestVisibility(boolean latestVisibility) {
            this.mLatestVisibility = latestVisibility;
        }

        @Deprecated
        @VisibleForTesting
        void setMuted(boolean muted) {
            this.mMuted = muted;
        }

        @Deprecated
        @VisibleForTesting
        MediaLayout getMediaLayout() {
            return this.mMediaLayout;
        }
    }

    @TargetApi(16)
    @VisibleForTesting
    static class NativeVideoControllerFactory {
        NativeVideoControllerFactory() {
        }

        public NativeVideoController createForId(long id, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
            return NativeVideoController.createForId(id, context, visibilityTrackingEvents, vastVideoConfig, eventDetails);
        }
    }

    @TargetApi(16)
    @VisibleForTesting
    static class PayloadVisibilityStrategy implements OnTrackedStrategy {
        @NonNull
        private final Context mContext;
        @NonNull
        private final String mUrl;

        PayloadVisibilityStrategy(@NonNull Context context, @NonNull String url) {
            this.mContext = context.getApplicationContext();
            this.mUrl = url;
        }

        public void execute() {
            TrackingRequest.makeTrackingHttpRequest(this.mUrl, this.mContext);
        }
    }

    @TargetApi(16)
    @VisibleForTesting
    static class VideoResponseHeaders {
        private boolean mHeadersAreValid;
        private int mImpressionMinVisiblePercent;
        private int mImpressionVisibleMs;
        private int mMaxBufferMs;
        private int mPauseVisiblePercent;
        private int mPlayVisiblePercent;

        VideoResponseHeaders(@NonNull Map<String, String> serverExtras) {
            try {
                this.mPlayVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.PLAY_VISIBLE_PERCENT));
                this.mPauseVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.PAUSE_VISIBLE_PERCENT));
                this.mImpressionMinVisiblePercent = Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_MIN_VISIBLE_PERCENT));
                this.mImpressionVisibleMs = Integer.parseInt((String) serverExtras.get(DataKeys.IMPRESSION_VISIBLE_MS));
                this.mMaxBufferMs = Integer.parseInt((String) serverExtras.get(DataKeys.MAX_BUFFER_MS));
                this.mHeadersAreValid = true;
            } catch (NumberFormatException e) {
                this.mHeadersAreValid = false;
            }
        }

        boolean hasValidHeaders() {
            return this.mHeadersAreValid;
        }

        int getPlayVisiblePercent() {
            return this.mPlayVisiblePercent;
        }

        int getPauseVisiblePercent() {
            return this.mPauseVisiblePercent;
        }

        int getImpressionMinVisiblePercent() {
            return this.mImpressionMinVisiblePercent;
        }

        int getImpressionVisibleMs() {
            return this.mImpressionVisibleMs;
        }

        int getMaxBufferMs() {
            return this.mMaxBufferMs;
        }
    }

    protected void loadNativeAd(@NonNull Activity activity, @NonNull CustomEventNativeListener customEventNativeListener, @NonNull Map<String, Object> localExtras, @NonNull Map<String, String> serverExtras) {
        Object json = localExtras.get(DataKeys.JSON_BODY_KEY);
        if (json instanceof JSONObject) {
            Object eventDetailsObject = localExtras.get(DataKeys.EVENT_DETAILS);
            EventDetails eventDetails = eventDetailsObject instanceof EventDetails ? (EventDetails) eventDetailsObject : null;
            VideoResponseHeaders videoResponseHeaders = new VideoResponseHeaders(serverExtras);
            if (videoResponseHeaders.hasValidHeaders()) {
                String clickTrackingUrlFromHeaderObject = localExtras.get(DataKeys.CLICK_TRACKING_URL_KEY);
                if (!(clickTrackingUrlFromHeaderObject instanceof String) || TextUtils.isEmpty(clickTrackingUrlFromHeaderObject)) {
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    return;
                }
                try {
                    new MoPubVideoNativeAd(activity, (JSONObject) json, customEventNativeListener, videoResponseHeaders, eventDetails, clickTrackingUrlFromHeaderObject).loadAd();
                    return;
                } catch (IllegalArgumentException e) {
                    customEventNativeListener.onNativeAdFailed(NativeErrorCode.UNSPECIFIED);
                    return;
                }
            }
            customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
            return;
        }
        customEventNativeListener.onNativeAdFailed(NativeErrorCode.INVALID_RESPONSE);
    }
}
