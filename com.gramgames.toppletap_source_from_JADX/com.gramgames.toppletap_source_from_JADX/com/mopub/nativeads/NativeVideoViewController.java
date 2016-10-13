package com.mopub.nativeads;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.SurfaceTexture;
import android.media.AudioManager.OnAudioFocusChangeListener;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.TextureView.SurfaceTextureListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.VideoView;
import com.facebook.ads.AdError;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.BaseVideoViewController;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mobileads.VastVideoConfig;
import com.mopub.nativeads.NativeFullScreenVideoView.Mode;
import com.mopub.nativeads.NativeVideoController.Listener;
import com.mopub.nativeads.NativeVideoController.NativeVideoProgressRunnable.ProgressListener;
import com.mopub.volley.DefaultRetryPolicy;
import org.json.simple.parser.Yytoken;

@TargetApi(16)
public class NativeVideoViewController extends BaseVideoViewController implements SurfaceTextureListener, Listener, OnAudioFocusChangeListener {
    @NonNull
    public static final String NATIVE_VAST_VIDEO_CONFIG = "native_vast_video_config";
    @NonNull
    public static final String NATIVE_VIDEO_ID = "native_video_id";
    @Nullable
    private Bitmap mCachedVideoFrame;
    private boolean mEnded;
    private boolean mError;
    @NonNull
    private final NativeFullScreenVideoView mFullScreenVideoView;
    private int mLatestVideoControllerState;
    @NonNull
    private final NativeVideoController mNativeVideoController;
    @NonNull
    private VastVideoConfig mVastVideoConfig;
    @NonNull
    private VideoState mVideoState;

    class 1 implements OnClickListener {
        1() {
        }

        public void onClick(View v) {
            if (NativeVideoViewController.this.mEnded) {
                NativeVideoViewController.this.mEnded = false;
                NativeVideoViewController.this.mFullScreenVideoView.resetProgress();
                NativeVideoViewController.this.mNativeVideoController.seekTo(0);
            }
            NativeVideoViewController.this.applyState(VideoState.PLAYING);
        }
    }

    class 2 implements OnClickListener {
        2() {
        }

        public void onClick(View v) {
            NativeVideoViewController.this.applyState(VideoState.PAUSED, true);
            NativeVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
        }
    }

    class 3 implements OnClickListener {
        3() {
        }

        public void onClick(View v) {
            NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
            NativeVideoViewController.this.mCachedVideoFrame = NativeVideoViewController.this.mFullScreenVideoView.getTextureView().getBitmap();
            NativeVideoViewController.this.mNativeVideoController.handleCtaClick((Activity) NativeVideoViewController.this.getContext());
        }
    }

    class 4 implements OnClickListener {
        4() {
        }

        public void onClick(View v) {
            NativeVideoViewController.this.mNativeVideoController.setPlayWhenReady(false);
            NativeVideoViewController.this.mCachedVideoFrame = NativeVideoViewController.this.mFullScreenVideoView.getTextureView().getBitmap();
            new Builder().withSupportedUrlActions(UrlAction.OPEN_IN_APP_BROWSER, new UrlAction[0]).build().handleUrl(NativeVideoViewController.this.getContext(), "https://www.mopub.com/optout/");
        }
    }

    class 5 implements ProgressListener {
        5() {
        }

        public void updateProgress(int progressTenthPercent) {
            NativeVideoViewController.this.mFullScreenVideoView.updateProgress(progressTenthPercent);
        }
    }

    static /* synthetic */ class 6 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState;

        static {
            $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState = new int[VideoState.values().length];
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.FAILED_LOAD.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.LOADING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.BUFFERING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.PLAYING.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.PAUSED.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[VideoState.ENDED.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
        }
    }

    enum VideoState {
        NONE,
        LOADING,
        BUFFERING,
        PAUSED,
        PLAYING,
        ENDED,
        FAILED_LOAD
    }

    public NativeVideoViewController(@NonNull Context context, @NonNull Bundle intentExtras, @NonNull Bundle savedInstanceState, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener) {
        this(context, intentExtras, savedInstanceState, baseVideoViewControllerListener, new NativeFullScreenVideoView(context, context.getResources().getConfiguration().orientation, ((VastVideoConfig) intentExtras.get(NATIVE_VAST_VIDEO_CONFIG)).getCustomCtaText()));
    }

    @VisibleForTesting
    NativeVideoViewController(@NonNull Context context, @NonNull Bundle intentExtras, @NonNull Bundle savedInstanceState, @NonNull BaseVideoViewControllerListener baseVideoViewControllerListener, @NonNull NativeFullScreenVideoView fullScreenVideoView) {
        super(context, null, baseVideoViewControllerListener);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intentExtras);
        Preconditions.checkNotNull(baseVideoViewControllerListener);
        Preconditions.checkNotNull(fullScreenVideoView);
        this.mVideoState = VideoState.NONE;
        this.mVastVideoConfig = (VastVideoConfig) intentExtras.get(NATIVE_VAST_VIDEO_CONFIG);
        this.mFullScreenVideoView = fullScreenVideoView;
        this.mNativeVideoController = NativeVideoController.getForId(((Long) intentExtras.get(NATIVE_VIDEO_ID)).longValue());
        Preconditions.checkNotNull(this.mVastVideoConfig);
        Preconditions.checkNotNull(this.mNativeVideoController);
    }

    protected VideoView getVideoView() {
        return null;
    }

    protected void onCreate() {
        this.mFullScreenVideoView.setSurfaceTextureListener(this);
        this.mFullScreenVideoView.setMode(Mode.LOADING);
        this.mFullScreenVideoView.setPlayControlClickListener(new 1());
        this.mFullScreenVideoView.setCloseControlListener(new 2());
        this.mFullScreenVideoView.setCtaClickListener(new 3());
        this.mFullScreenVideoView.setPrivacyInformationClickListener(new 4());
        this.mFullScreenVideoView.setLayoutParams(new LayoutParams(-1, -1));
        getBaseVideoViewControllerListener().onSetContentView(this.mFullScreenVideoView);
        this.mNativeVideoController.setProgressListener(new 5());
    }

    protected void onResume() {
        if (this.mCachedVideoFrame != null) {
            this.mFullScreenVideoView.setCachedVideoFrame(this.mCachedVideoFrame);
        }
        this.mNativeVideoController.prepare(this);
        this.mNativeVideoController.setListener(this);
        this.mNativeVideoController.setOnAudioFocusChangeListener(this);
    }

    protected void onPause() {
    }

    protected void onDestroy() {
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
    }

    protected void onConfigurationChanged(Configuration configuration) {
        this.mFullScreenVideoView.setOrientation(configuration.orientation);
    }

    protected void onBackPressed() {
        applyState(VideoState.PAUSED, true);
    }

    public void onSurfaceTextureAvailable(SurfaceTexture surface, int width, int height) {
        this.mNativeVideoController.setTextureView(this.mFullScreenVideoView.getTextureView());
        if (!this.mEnded) {
            this.mNativeVideoController.seekTo(this.mNativeVideoController.getCurrentPosition());
        }
        this.mNativeVideoController.setPlayWhenReady(!this.mEnded);
        if (this.mNativeVideoController.getDuration() - this.mNativeVideoController.getCurrentPosition() < 750) {
            this.mEnded = true;
            maybeChangeState();
        }
    }

    public void onSurfaceTextureSizeChanged(SurfaceTexture surface, int width, int height) {
    }

    public boolean onSurfaceTextureDestroyed(SurfaceTexture surface) {
        this.mNativeVideoController.release(this);
        applyState(VideoState.PAUSED);
        return true;
    }

    public void onSurfaceTextureUpdated(SurfaceTexture surface) {
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

    public void onAudioFocusChange(int focusChange) {
        if (focusChange == -1 || focusChange == -2) {
            applyState(VideoState.PAUSED);
        } else if (focusChange == -3) {
            this.mNativeVideoController.setAudioVolume(0.3f);
        } else if (focusChange == 1) {
            this.mNativeVideoController.setAudioVolume(DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            maybeChangeState();
        }
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
        } else if (this.mLatestVideoControllerState == 4) {
            newState = VideoState.PLAYING;
        } else if (this.mLatestVideoControllerState == 5 || this.mLatestVideoControllerState == 6) {
            newState = VideoState.ENDED;
        }
        applyState(newState);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState) {
        applyState(videoState, false);
    }

    @VisibleForTesting
    void applyState(@NonNull VideoState videoState, boolean transitionToInline) {
        Preconditions.checkNotNull(videoState);
        if (this.mVideoState != videoState) {
            switch (6.$SwitchMap$com$mopub$nativeads$NativeVideoViewController$VideoState[videoState.ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mNativeVideoController.setAudioEnabled(false);
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    this.mVastVideoConfig.handleError(getContext(), null, 0);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mFullScreenVideoView.setMode(Mode.LOADING);
                    break;
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    this.mNativeVideoController.setPlayWhenReady(true);
                    this.mNativeVideoController.setAudioEnabled(true);
                    this.mNativeVideoController.setAppAudioEnabled(true);
                    this.mFullScreenVideoView.setMode(Mode.PLAYING);
                    break;
                case Yytoken.TYPE_COMMA /*5*/:
                    if (!transitionToInline) {
                        this.mNativeVideoController.setAppAudioEnabled(false);
                    }
                    this.mNativeVideoController.setPlayWhenReady(false);
                    this.mFullScreenVideoView.setMode(Mode.PAUSED);
                    break;
                case Yytoken.TYPE_COLON /*6*/:
                    this.mEnded = true;
                    this.mNativeVideoController.setAppAudioEnabled(false);
                    this.mFullScreenVideoView.updateProgress(AdError.NETWORK_ERROR_CODE);
                    this.mFullScreenVideoView.setMode(Mode.FINISHED);
                    this.mVastVideoConfig.handleComplete(getContext(), 0);
                    break;
            }
            this.mVideoState = videoState;
        }
    }

    @Deprecated
    @VisibleForTesting
    NativeFullScreenVideoView getNativeFullScreenVideoView() {
        return this.mFullScreenVideoView;
    }

    @Deprecated
    @VisibleForTesting
    VideoState getVideoState() {
        return this.mVideoState;
    }
}
