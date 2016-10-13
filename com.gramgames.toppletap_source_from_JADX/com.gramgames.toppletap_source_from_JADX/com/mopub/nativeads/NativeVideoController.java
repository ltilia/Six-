package com.mopub.nativeads;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Surface;
import android.view.TextureView;
import com.facebook.ads.AdError;
import com.google.android.exoplayer.ExoPlaybackException;
import com.google.android.exoplayer.ExoPlayer;
import com.google.android.exoplayer.ExoPlayer.Factory;
import com.google.android.exoplayer.MediaCodecAudioTrackRenderer;
import com.google.android.exoplayer.MediaCodecSelector;
import com.google.android.exoplayer.MediaCodecVideoTrackRenderer;
import com.google.android.exoplayer.TrackRenderer;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorSampleSource;
import com.google.android.exoplayer.extractor.mp4.Mp4Extractor;
import com.google.android.exoplayer.upstream.Allocator;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DefaultAllocator;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.event.BaseEvent.Category;
import com.mopub.common.event.BaseEvent.Name;
import com.mopub.common.event.BaseEvent.SamplingRate;
import com.mopub.common.event.Event;
import com.mopub.common.event.EventDetails;
import com.mopub.common.event.MoPubEvents;
import com.mopub.mobileads.RepeatingHandlerRunnable;
import com.mopub.mobileads.VastTracker;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.network.TrackingRequest;
import com.mopub.volley.DefaultRetryPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@TargetApi(16)
public class NativeVideoController implements com.google.android.exoplayer.ExoPlayer.Listener, OnAudioFocusChangeListener {
    private static final int BUFFER_SEGMENT_COUNT = 32;
    private static final int BUFFER_SEGMENT_SIZE = 65536;
    public static final long RESUME_FINISHED_THRESHOLD = 750;
    public static final int STATE_BUFFERING = 3;
    public static final int STATE_CLEARED = 6;
    public static final int STATE_ENDED = 5;
    public static final int STATE_IDLE = 1;
    public static final int STATE_PREPARING = 2;
    public static final int STATE_READY = 4;
    @NonNull
    private static final Map<Long, NativeVideoController> sManagerMap;
    private boolean mAppAudioEnabled;
    private boolean mAudioEnabled;
    @NonNull
    private AudioManager mAudioManager;
    @Nullable
    private MediaCodecAudioTrackRenderer mAudioTrackRenderer;
    @NonNull
    private final Context mContext;
    @Nullable
    private EventDetails mEventDetails;
    @Nullable
    private volatile ExoPlayer mExoPlayer;
    @NonNull
    private final ExoPlayerFactory mExoPlayerFactory;
    private boolean mExoPlayerStateStartedFromIdle;
    @Nullable
    private BitmapDrawable mFinalFrame;
    @NonNull
    private final Handler mHandler;
    @Nullable
    private Listener mListener;
    @NonNull
    private NativeVideoProgressRunnable mNativeVideoProgressRunnable;
    @Nullable
    private OnAudioFocusChangeListener mOnAudioFocusChangeListener;
    @Nullable
    private WeakReference<Object> mOwnerRef;
    private boolean mPlayWhenReady;
    private int mPreviousExoPlayerState;
    @Nullable
    private Surface mSurface;
    @Nullable
    private TextureView mTextureView;
    @NonNull
    private VastVideoConfig mVastVideoConfig;
    @Nullable
    private MediaCodecVideoTrackRenderer mVideoTrackRenderer;

    public interface Listener {
        void onError(Exception exception);

        void onStateChanged(boolean z, int i);
    }

    @VisibleForTesting
    static class ExoPlayerFactory {
        ExoPlayerFactory() {
        }

        public ExoPlayer newInstance(int rendererCount, int minBufferMs, int minRebufferMs) {
            return Factory.newInstance(rendererCount, minBufferMs, minRebufferMs);
        }
    }

    static class NativeVideoProgressRunnable extends RepeatingHandlerRunnable {
        @NonNull
        private final Context mContext;
        private long mCurrentPosition;
        private long mDuration;
        @Nullable
        private ExoPlayer mExoPlayer;
        @Nullable
        private ProgressListener mProgressListener;
        private boolean mStopRequested;
        @Nullable
        private TextureView mTextureView;
        @NonNull
        private final VastVideoConfig mVastVideoConfig;
        @NonNull
        private final VisibilityChecker mVisibilityChecker;
        @NonNull
        private final List<VisibilityTrackingEvent> mVisibilityTrackingEvents;

        public interface ProgressListener {
            void updateProgress(int i);
        }

        NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig) {
            this(context, handler, visibilityTrackingEvents, new VisibilityChecker(), vastVideoConfig);
        }

        @VisibleForTesting
        NativeVideoProgressRunnable(@NonNull Context context, @NonNull Handler handler, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VisibilityChecker visibilityChecker, @NonNull VastVideoConfig vastVideoConfig) {
            super(handler);
            Preconditions.checkNotNull(context);
            Preconditions.checkNotNull(handler);
            Preconditions.checkNotNull(visibilityTrackingEvents);
            Preconditions.checkNotNull(vastVideoConfig);
            this.mContext = context.getApplicationContext();
            this.mVisibilityTrackingEvents = visibilityTrackingEvents;
            this.mVisibilityChecker = visibilityChecker;
            this.mVastVideoConfig = vastVideoConfig;
            this.mDuration = -1;
            this.mStopRequested = false;
        }

        void setExoPlayer(@Nullable ExoPlayer exoPlayer) {
            this.mExoPlayer = exoPlayer;
        }

        void setTextureView(@Nullable TextureView textureView) {
            this.mTextureView = textureView;
        }

        void setProgressListener(@Nullable ProgressListener progressListener) {
            this.mProgressListener = progressListener;
        }

        void seekTo(long currentPosition) {
            this.mCurrentPosition = currentPosition;
        }

        long getCurrentPosition() {
            return this.mCurrentPosition;
        }

        long getDuration() {
            return this.mDuration;
        }

        void requestStop() {
            this.mStopRequested = true;
        }

        void checkImpressionTrackers(boolean forceTrigger) {
            int trackedCount = 0;
            for (VisibilityTrackingEvent event : this.mVisibilityTrackingEvents) {
                if (event.isTracked) {
                    trackedCount += NativeVideoController.STATE_IDLE;
                } else if (forceTrigger || this.mVisibilityChecker.isVisible(this.mTextureView, this.mTextureView, event.minimumPercentageVisible)) {
                    event.totalQualifiedPlayCounter = (int) (((long) event.totalQualifiedPlayCounter) + this.mUpdateIntervalMillis);
                    if (forceTrigger || event.totalQualifiedPlayCounter >= event.totalRequiredPlayTimeMs) {
                        event.strategy.execute();
                        event.isTracked = true;
                        trackedCount += NativeVideoController.STATE_IDLE;
                    }
                }
            }
            if (trackedCount == this.mVisibilityTrackingEvents.size() && this.mStopRequested) {
                stop();
            }
        }

        public void doWork() {
            if (this.mExoPlayer != null && this.mExoPlayer.getPlayWhenReady()) {
                this.mCurrentPosition = this.mExoPlayer.getCurrentPosition();
                this.mDuration = this.mExoPlayer.getDuration();
                checkImpressionTrackers(false);
                if (this.mProgressListener != null) {
                    this.mProgressListener.updateProgress((int) ((((float) this.mCurrentPosition) / ((float) this.mDuration)) * 1000.0f));
                }
                List<VastTracker> trackers = this.mVastVideoConfig.getUntriggeredTrackersBefore((int) this.mCurrentPosition, (int) this.mDuration);
                if (!trackers.isEmpty()) {
                    Iterable trackingUrls = new ArrayList();
                    for (VastTracker tracker : trackers) {
                        if (!tracker.isTracked()) {
                            trackingUrls.add(tracker.getTrackingUrl());
                            tracker.setTracked();
                        }
                    }
                    TrackingRequest.makeTrackingHttpRequest(trackingUrls, this.mContext);
                }
            }
        }

        @Deprecated
        @VisibleForTesting
        void setUpdateIntervalMillis(long updateIntervalMillis) {
            this.mUpdateIntervalMillis = updateIntervalMillis;
        }
    }

    static class VisibilityTrackingEvent {
        boolean isTracked;
        int minimumPercentageVisible;
        OnTrackedStrategy strategy;
        int totalQualifiedPlayCounter;
        int totalRequiredPlayTimeMs;

        interface OnTrackedStrategy {
            void execute();
        }

        VisibilityTrackingEvent() {
        }
    }

    static {
        sManagerMap = new HashMap(STATE_READY);
    }

    @NonNull
    public static NativeVideoController createForId(long id, @NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
        NativeVideoController nvc = new NativeVideoController(context, visibilityTrackingEvents, vastVideoConfig, eventDetails);
        sManagerMap.put(Long.valueOf(id), nvc);
        return nvc;
    }

    @NonNull
    @VisibleForTesting
    public static NativeVideoController createForId(long id, @NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull ExoPlayerFactory exoPlayerFactory, @Nullable EventDetails eventDetails, @NonNull AudioManager audioManager) {
        NativeVideoController nvc = new NativeVideoController(context, vastVideoConfig, nativeVideoProgressRunnable, exoPlayerFactory, eventDetails, audioManager);
        sManagerMap.put(Long.valueOf(id), nvc);
        return nvc;
    }

    @VisibleForTesting
    static void setForId(long id, @NonNull NativeVideoController nativeVideoController) {
        sManagerMap.put(Long.valueOf(id), nativeVideoController);
    }

    @Nullable
    public static NativeVideoController getForId(long id) {
        return (NativeVideoController) sManagerMap.get(Long.valueOf(id));
    }

    @Nullable
    public static NativeVideoController remove(long id) {
        return (NativeVideoController) sManagerMap.remove(Long.valueOf(id));
    }

    private NativeVideoController(@NonNull Context context, @NonNull List<VisibilityTrackingEvent> visibilityTrackingEvents, @NonNull VastVideoConfig vastVideoConfig, @Nullable EventDetails eventDetails) {
        this(context, vastVideoConfig, new NativeVideoProgressRunnable(context, new Handler(Looper.getMainLooper()), visibilityTrackingEvents, vastVideoConfig), new ExoPlayerFactory(), eventDetails, (AudioManager) context.getSystemService(MimeTypes.BASE_TYPE_AUDIO));
    }

    private NativeVideoController(@NonNull Context context, @NonNull VastVideoConfig vastVideoConfig, @NonNull NativeVideoProgressRunnable nativeVideoProgressRunnable, @NonNull ExoPlayerFactory exoPlayerFactory, @Nullable EventDetails eventDetails, @NonNull AudioManager audioManager) {
        this.mPreviousExoPlayerState = STATE_IDLE;
        this.mExoPlayerStateStartedFromIdle = true;
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastVideoConfig);
        Preconditions.checkNotNull(exoPlayerFactory);
        Preconditions.checkNotNull(audioManager);
        this.mContext = context.getApplicationContext();
        this.mHandler = new Handler(Looper.getMainLooper());
        this.mVastVideoConfig = vastVideoConfig;
        this.mNativeVideoProgressRunnable = nativeVideoProgressRunnable;
        this.mExoPlayerFactory = exoPlayerFactory;
        this.mEventDetails = eventDetails;
        this.mAudioManager = audioManager;
    }

    public void setListener(@Nullable Listener listener) {
        this.mListener = listener;
    }

    public void setProgressListener(@Nullable ProgressListener progressListener) {
        this.mNativeVideoProgressRunnable.setProgressListener(progressListener);
    }

    public void setOnAudioFocusChangeListener(@Nullable OnAudioFocusChangeListener onAudioFocusChangeListener) {
        this.mOnAudioFocusChangeListener = onAudioFocusChangeListener;
    }

    public void setPlayWhenReady(boolean playWhenReady) {
        if (this.mPlayWhenReady != playWhenReady) {
            this.mPlayWhenReady = playWhenReady;
            setExoPlayWhenReady();
        }
    }

    public int getPlaybackState() {
        if (this.mExoPlayer == null) {
            return STATE_CLEARED;
        }
        return this.mExoPlayer.getPlaybackState();
    }

    public void setAudioEnabled(boolean audioEnabled) {
        this.mAudioEnabled = audioEnabled;
        setExoAudio();
    }

    public void setAppAudioEnabled(boolean audioEnabled) {
        if (this.mAppAudioEnabled != audioEnabled) {
            this.mAppAudioEnabled = audioEnabled;
            if (this.mAppAudioEnabled) {
                this.mAudioManager.requestAudioFocus(this, STATE_BUFFERING, STATE_IDLE);
            } else {
                this.mAudioManager.abandonAudioFocus(this);
            }
        }
    }

    public void setAudioVolume(float volume) {
        if (this.mAudioEnabled) {
            setExoAudio(volume);
        }
    }

    public void onAudioFocusChange(int focusChange) {
        if (this.mOnAudioFocusChangeListener != null) {
            this.mOnAudioFocusChangeListener.onAudioFocusChange(focusChange);
        }
    }

    public void setTextureView(@NonNull TextureView textureView) {
        Preconditions.checkNotNull(textureView);
        this.mSurface = new Surface(textureView.getSurfaceTexture());
        this.mTextureView = textureView;
        this.mNativeVideoProgressRunnable.setTextureView(this.mTextureView);
        setExoSurface(this.mSurface);
    }

    public void prepare(@NonNull Object owner) {
        Preconditions.checkNotNull(owner);
        this.mOwnerRef = new WeakReference(owner);
        clearExistingPlayer();
        preparePlayer();
        setExoSurface(this.mSurface);
    }

    public void clear() {
        setPlayWhenReady(false);
        this.mSurface = null;
        clearExistingPlayer();
    }

    public void release(@NonNull Object owner) {
        Preconditions.checkNotNull(owner);
        if ((this.mOwnerRef == null ? null : this.mOwnerRef.get()) == owner) {
            clearExistingPlayer();
        }
    }

    public void onPlayerStateChanged(boolean playWhenReady, int newState) {
        if (newState == STATE_ENDED && this.mFinalFrame == null) {
            this.mFinalFrame = new BitmapDrawable(this.mContext.getResources(), this.mTextureView.getBitmap());
            this.mNativeVideoProgressRunnable.requestStop();
        }
        if (this.mPreviousExoPlayerState == STATE_READY && newState == STATE_BUFFERING) {
            MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_BUFFERING, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
        }
        if (this.mExoPlayerStateStartedFromIdle && this.mPreviousExoPlayerState == STATE_BUFFERING && newState == STATE_READY) {
            MoPubEvents.log(Event.createEventFromDetails(Name.DOWNLOAD_VIDEO_READY, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
        }
        this.mPreviousExoPlayerState = newState;
        if (newState == STATE_READY) {
            this.mExoPlayerStateStartedFromIdle = false;
        } else if (newState == STATE_IDLE) {
            this.mExoPlayerStateStartedFromIdle = true;
        }
        if (this.mListener != null) {
            this.mListener.onStateChanged(playWhenReady, newState);
        }
    }

    public void seekTo(long ms) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.seekTo(ms);
            this.mNativeVideoProgressRunnable.seekTo(ms);
        }
    }

    public long getCurrentPosition() {
        return this.mNativeVideoProgressRunnable.getCurrentPosition();
    }

    public long getDuration() {
        return this.mNativeVideoProgressRunnable.getDuration();
    }

    public void onPlayWhenReadyCommitted() {
    }

    public void onPlayerError(ExoPlaybackException e) {
        if (this.mListener != null) {
            MoPubEvents.log(Event.createEventFromDetails(Name.ERROR_DURING_PLAYBACK, Category.NATIVE_VIDEO, SamplingRate.NATIVE_VIDEO, this.mEventDetails));
            this.mListener.onError(e);
            this.mNativeVideoProgressRunnable.requestStop();
        }
    }

    public void handleCtaClick(@NonNull Context context) {
        triggerImpressionTrackers();
        this.mVastVideoConfig.handleClickWithoutResult(context, 0);
    }

    public boolean hasFinalFrame() {
        return this.mFinalFrame != null;
    }

    @Nullable
    public Drawable getFinalFrame() {
        return this.mFinalFrame;
    }

    void triggerImpressionTrackers() {
        this.mNativeVideoProgressRunnable.checkImpressionTrackers(true);
    }

    private void clearExistingPlayer() {
        if (this.mExoPlayer != null) {
            setExoSurface(null);
            this.mExoPlayer.stop();
            this.mExoPlayer.release();
            this.mExoPlayer = null;
            this.mNativeVideoProgressRunnable.stop();
            this.mNativeVideoProgressRunnable.setExoPlayer(null);
        }
    }

    private void preparePlayer() {
        if (this.mExoPlayer == null) {
            this.mExoPlayer = this.mExoPlayerFactory.newInstance(STATE_PREPARING, AdError.NETWORK_ERROR_CODE, Factory.DEFAULT_MIN_REBUFFER_MS);
            this.mNativeVideoProgressRunnable.setExoPlayer(this.mExoPlayer);
            this.mExoPlayer.addListener(this);
            Allocator allocator = new DefaultAllocator(BUFFER_SEGMENT_SIZE);
            Extractor extractor = new Mp4Extractor();
            DataSource httpSource = new HttpDiskCompositeDataSource(this.mContext, "exo_demo", this.mEventDetails);
            Uri parse = Uri.parse(this.mVastVideoConfig.getNetworkMediaFileUrl());
            Extractor[] extractorArr = new Extractor[STATE_IDLE];
            extractorArr[0] = extractor;
            ExtractorSampleSource sampleSource = new ExtractorSampleSource(parse, httpSource, allocator, AccessibilityNodeInfoCompat.ACTION_SET_TEXT, extractorArr);
            this.mVideoTrackRenderer = new MediaCodecVideoTrackRenderer(this.mContext, sampleSource, MediaCodecSelector.DEFAULT, STATE_PREPARING, 0, this.mHandler, null, 10);
            this.mAudioTrackRenderer = new MediaCodecAudioTrackRenderer(sampleSource, MediaCodecSelector.DEFAULT);
            ExoPlayer exoPlayer = this.mExoPlayer;
            TrackRenderer[] trackRendererArr = new TrackRenderer[STATE_PREPARING];
            trackRendererArr[0] = this.mAudioTrackRenderer;
            trackRendererArr[STATE_IDLE] = this.mVideoTrackRenderer;
            exoPlayer.prepare(trackRendererArr);
            this.mNativeVideoProgressRunnable.startRepeating(50);
        }
        setExoAudio();
        setExoPlayWhenReady();
    }

    private void setExoPlayWhenReady() {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.setPlayWhenReady(this.mPlayWhenReady);
        }
    }

    private void setExoAudio() {
        setExoAudio(this.mAudioEnabled ? DefaultRetryPolicy.DEFAULT_BACKOFF_MULT : 0.0f);
    }

    private void setExoAudio(float volume) {
        boolean z = volume >= 0.0f && volume <= DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        Preconditions.checkArgument(z);
        if (this.mExoPlayer != null) {
            this.mExoPlayer.sendMessage(this.mAudioTrackRenderer, STATE_IDLE, Float.valueOf(volume));
        }
    }

    private void setExoSurface(@Nullable Surface surface) {
        if (this.mExoPlayer != null) {
            this.mExoPlayer.sendMessage(this.mVideoTrackRenderer, STATE_IDLE, surface);
        }
    }
}
