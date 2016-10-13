package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.DeviceUtils.ForceOrientation;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.mobileads.BaseVideoViewController.BaseVideoViewControllerListener;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.network.TrackingRequest;
import java.io.Serializable;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class VastVideoViewController extends BaseVideoViewController {
    static final String CURRENT_POSITION = "current_position";
    static final int DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON = 5000;
    static final int MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON = 16000;
    static final String RESUMED_VAST_CONFIG = "resumed_vast_config";
    private static final int SEEKER_POSITION_NOT_INITIALIZED = -1;
    static final String VAST_VIDEO_CONFIG = "vast_video_config";
    private static final long VIDEO_COUNTDOWN_UPDATE_INTERVAL = 250;
    private static final long VIDEO_PROGRESS_TIMER_CHECKER_DELAY = 50;
    public static final int WEBVIEW_PADDING = 16;
    @NonNull
    private View mAdsByView;
    @NonNull
    private ImageView mBlurredLastVideoFrameImageView;
    @NonNull
    private VastVideoGradientStripWidget mBottomGradientStripWidget;
    @NonNull
    private final OnTouchListener mClickThroughListener;
    @NonNull
    private VastVideoCloseButtonWidget mCloseButtonWidget;
    @NonNull
    private final VastVideoViewCountdownRunnable mCountdownRunnable;
    @NonNull
    private VastVideoCtaButtonWidget mCtaButtonWidget;
    private int mDuration;
    private boolean mHasSkipOffset;
    private boolean mHasSocialActions;
    @NonNull
    private final View mIconView;
    private boolean mIsCalibrationDone;
    private boolean mIsClosing;
    private boolean mIsVideoFinishedPlaying;
    @NonNull
    private final View mLandscapeCompanionAdView;
    @NonNull
    private final View mPortraitCompanionAdView;
    @NonNull
    private VastVideoProgressBarWidget mProgressBarWidget;
    @NonNull
    private final VastVideoViewProgressRunnable mProgressCheckerRunnable;
    @NonNull
    private VastVideoRadialCountdownWidget mRadialCountdownWidget;
    private int mSeekerPositionOnPause;
    private int mShowCloseButtonDelay;
    private boolean mShowCloseButtonEventFired;
    @NonNull
    private final Map<String, VastCompanionAdConfig> mSocialActionsCompanionAds;
    @NonNull
    private final View mSocialActionsView;
    @NonNull
    private VastVideoGradientStripWidget mTopGradientStripWidget;
    @Nullable
    private VastCompanionAdConfig mVastCompanionAdConfig;
    @Nullable
    private final VastIconConfig mVastIconConfig;
    private final VastVideoConfig mVastVideoConfig;
    private boolean mVideoError;
    @NonNull
    private final VastVideoView mVideoView;

    class 10 extends WebViewClient {
        final /* synthetic */ Context val$context;
        final /* synthetic */ VastCompanionAdConfig val$vastCompanionAdConfig;

        10(VastCompanionAdConfig vastCompanionAdConfig, Context context) {
            this.val$vastCompanionAdConfig = vastCompanionAdConfig;
            this.val$context = context;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            this.val$vastCompanionAdConfig.handleClick(this.val$context, 1, url, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            return true;
        }
    }

    static /* synthetic */ class 11 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation;

        static {
            $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation = new int[ForceOrientation.values().length];
            try {
                $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[ForceOrientation.FORCE_PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[ForceOrientation.FORCE_LANDSCAPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[ForceOrientation.DEVICE_ORIENTATION.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[ForceOrientation.UNDEFINED.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    class 1 implements OnTouchListener {
        final /* synthetic */ Activity val$activity;

        1(Activity activity) {
            this.val$activity = activity;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() == 1 && VastVideoViewController.this.shouldAllowClickThrough()) {
                VastVideoViewController.this.mIsClosing = true;
                VastVideoViewController.this.broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
                VastVideoViewController.this.mVastVideoConfig.handleClickForResult(this.val$activity, VastVideoViewController.this.mIsVideoFinishedPlaying ? VastVideoViewController.this.mDuration : VastVideoViewController.this.getCurrentPosition(), 1);
            }
            return true;
        }
    }

    class 2 implements OnGlobalLayoutListener {
        final /* synthetic */ Activity val$activity;

        2(Activity activity) {
            this.val$activity = activity;
        }

        public void onGlobalLayout() {
            VastVideoViewController.this.mAdsByView = VastVideoViewController.this.createAdsByView(this.val$activity);
            VastVideoViewController.this.mIconView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
        }
    }

    class 3 implements OnPreparedListener {
        final /* synthetic */ VastVideoView val$videoView;

        3(VastVideoView vastVideoView) {
            this.val$videoView = vastVideoView;
        }

        public void onPrepared(MediaPlayer mp) {
            VastVideoViewController.this.mDuration = VastVideoViewController.this.mVideoView.getDuration();
            VastVideoViewController.this.adjustSkipOffset();
            if (VastVideoViewController.this.mVastCompanionAdConfig == null || VastVideoViewController.this.mHasSocialActions) {
                this.val$videoView.prepareBlurredLastVideoFrame(VastVideoViewController.this.mBlurredLastVideoFrameImageView, VastVideoViewController.this.mVastVideoConfig.getDiskMediaFileUrl());
            }
            VastVideoViewController.this.mProgressBarWidget.calibrateAndMakeVisible(VastVideoViewController.this.getDuration(), VastVideoViewController.this.mShowCloseButtonDelay);
            VastVideoViewController.this.mRadialCountdownWidget.calibrateAndMakeVisible(VastVideoViewController.this.mShowCloseButtonDelay);
            VastVideoViewController.this.mIsCalibrationDone = true;
        }
    }

    class 4 implements OnCompletionListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ VastVideoView val$videoView;

        4(VastVideoView vastVideoView, Context context) {
            this.val$videoView = vastVideoView;
            this.val$context = context;
        }

        public void onCompletion(MediaPlayer mp) {
            VastVideoViewController.this.stopRunnables();
            VastVideoViewController.this.makeVideoInteractable();
            VastVideoViewController.this.videoCompleted(false);
            VastVideoViewController.this.mIsVideoFinishedPlaying = true;
            if (VastVideoViewController.this.mVastVideoConfig.isRewardedVideo()) {
                VastVideoViewController.this.broadcastAction(RewardedVideoBroadcastReceiver.ACTION_REWARDED_VIDEO_COMPLETE);
            }
            if (!VastVideoViewController.this.mVideoError && VastVideoViewController.this.mVastVideoConfig.getRemainingProgressTrackerCount() == 0) {
                VastVideoViewController.this.mVastVideoConfig.handleComplete(VastVideoViewController.this.getContext(), VastVideoViewController.this.getCurrentPosition());
            }
            this.val$videoView.setVisibility(4);
            VastVideoViewController.this.mProgressBarWidget.setVisibility(8);
            if (!VastVideoViewController.this.mHasSocialActions) {
                VastVideoViewController.this.mIconView.setVisibility(8);
            } else if (VastVideoViewController.this.mBlurredLastVideoFrameImageView.getDrawable() != null) {
                VastVideoViewController.this.mBlurredLastVideoFrameImageView.setScaleType(ScaleType.CENTER_CROP);
                VastVideoViewController.this.mBlurredLastVideoFrameImageView.setVisibility(0);
            }
            VastVideoViewController.this.mTopGradientStripWidget.notifyVideoComplete();
            VastVideoViewController.this.mBottomGradientStripWidget.notifyVideoComplete();
            VastVideoViewController.this.mCtaButtonWidget.notifyVideoComplete();
            if (VastVideoViewController.this.mVastCompanionAdConfig != null) {
                if (this.val$context.getResources().getConfiguration().orientation == 1) {
                    VastVideoViewController.this.mPortraitCompanionAdView.setVisibility(0);
                } else {
                    VastVideoViewController.this.mLandscapeCompanionAdView.setVisibility(0);
                }
                VastVideoViewController.this.mVastCompanionAdConfig.handleImpression(this.val$context, VastVideoViewController.this.mDuration);
            } else if (VastVideoViewController.this.mBlurredLastVideoFrameImageView.getDrawable() != null) {
                VastVideoViewController.this.mBlurredLastVideoFrameImageView.setVisibility(0);
            }
        }
    }

    class 5 implements OnErrorListener {
        final /* synthetic */ VastVideoView val$videoView;

        5(VastVideoView vastVideoView) {
            this.val$videoView = vastVideoView;
        }

        public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
            if (this.val$videoView.retryMediaPlayer(mediaPlayer, what, extra, VastVideoViewController.this.mVastVideoConfig.getDiskMediaFileUrl())) {
                return true;
            }
            VastVideoViewController.this.stopRunnables();
            VastVideoViewController.this.makeVideoInteractable();
            VastVideoViewController.this.videoError(false);
            VastVideoViewController.this.mVideoError = true;
            VastVideoViewController.this.mVastVideoConfig.handleError(VastVideoViewController.this.getContext(), VastErrorCode.GENERAL_LINEAR_AD_ERROR, VastVideoViewController.this.getCurrentPosition());
            return false;
        }
    }

    class 6 implements OnTouchListener {
        6() {
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            int currentPosition;
            if (VastVideoViewController.this.mIsVideoFinishedPlaying) {
                currentPosition = VastVideoViewController.this.mDuration;
            } else {
                currentPosition = VastVideoViewController.this.getCurrentPosition();
            }
            if (motionEvent.getAction() == 1) {
                VastVideoViewController.this.mIsClosing = true;
                VastVideoViewController.this.mVastVideoConfig.handleClose(VastVideoViewController.this.getContext(), currentPosition);
                VastVideoViewController.this.getBaseVideoViewControllerListener().onFinish();
            }
            return true;
        }
    }

    class 7 implements VastWebViewClickListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ VastIconConfig val$vastIconConfig;

        7(VastIconConfig vastIconConfig, Context context) {
            this.val$vastIconConfig = vastIconConfig;
            this.val$context = context;
        }

        public void onVastWebViewClick() {
            TrackingRequest.makeVastTrackingHttpRequest(this.val$vastIconConfig.getClickTrackingUris(), null, Integer.valueOf(VastVideoViewController.this.getCurrentPosition()), VastVideoViewController.this.getNetworkMediaFileUrl(), this.val$context);
            this.val$vastIconConfig.handleClick(VastVideoViewController.this.getContext(), null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
        }
    }

    class 8 extends WebViewClient {
        final /* synthetic */ VastIconConfig val$vastIconConfig;

        8(VastIconConfig vastIconConfig) {
            this.val$vastIconConfig = vastIconConfig;
        }

        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            this.val$vastIconConfig.handleClick(VastVideoViewController.this.getContext(), url, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
            return true;
        }
    }

    class 9 implements VastWebViewClickListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ VastCompanionAdConfig val$vastCompanionAdConfig;

        9(VastCompanionAdConfig vastCompanionAdConfig, Context context) {
            this.val$vastCompanionAdConfig = vastCompanionAdConfig;
            this.val$context = context;
        }

        public void onVastWebViewClick() {
            VastVideoViewController.this.broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_CLICK);
            TrackingRequest.makeVastTrackingHttpRequest(this.val$vastCompanionAdConfig.getClickTrackers(), null, Integer.valueOf(VastVideoViewController.this.mDuration), null, this.val$context);
            this.val$vastCompanionAdConfig.handleClick(this.val$context, 1, null, VastVideoViewController.this.mVastVideoConfig.getDspCreativeId());
        }
    }

    VastVideoViewController(Activity activity, Bundle intentExtras, @Nullable Bundle savedInstanceState, long broadcastIdentifier, BaseVideoViewControllerListener baseVideoViewControllerListener) throws IllegalStateException {
        super(activity, Long.valueOf(broadcastIdentifier), baseVideoViewControllerListener);
        this.mShowCloseButtonDelay = DEFAULT_VIDEO_DURATION_FOR_CLOSE_BUTTON;
        this.mHasSkipOffset = false;
        this.mIsCalibrationDone = false;
        this.mHasSocialActions = false;
        this.mIsClosing = false;
        this.mSeekerPositionOnPause = SEEKER_POSITION_NOT_INITIALIZED;
        Serializable resumedVastConfiguration = null;
        if (savedInstanceState != null) {
            resumedVastConfiguration = savedInstanceState.getSerializable(RESUMED_VAST_CONFIG);
        }
        Serializable serializable = intentExtras.getSerializable(VAST_VIDEO_CONFIG);
        if (resumedVastConfiguration != null && (resumedVastConfiguration instanceof VastVideoConfig)) {
            this.mVastVideoConfig = (VastVideoConfig) resumedVastConfiguration;
            this.mSeekerPositionOnPause = savedInstanceState.getInt(CURRENT_POSITION, SEEKER_POSITION_NOT_INITIALIZED);
        } else if (serializable == null || !(serializable instanceof VastVideoConfig)) {
            throw new IllegalStateException("VastVideoConfig is invalid");
        } else {
            this.mVastVideoConfig = (VastVideoConfig) serializable;
        }
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(activity.getResources().getConfiguration().orientation);
        this.mSocialActionsCompanionAds = this.mVastVideoConfig.getSocialActionsCompanionAds();
        this.mVastIconConfig = this.mVastVideoConfig.getVastIconConfig();
        this.mClickThroughListener = new 1(activity);
        getLayout().setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        addBlurredLastVideoFrameImageView(activity, 4);
        this.mVideoView = createVideoView(activity, 0);
        this.mVideoView.requestFocus();
        this.mLandscapeCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(2), 4);
        this.mPortraitCompanionAdView = createCompanionAdView(activity, this.mVastVideoConfig.getVastCompanionAd(1), 4);
        addTopGradientStripWidget(activity);
        addProgressBarWidget(activity, 4);
        addBottomGradientStripWidget(activity);
        addRadialCountdownWidget(activity, 4);
        this.mIconView = createIconView(activity, this.mVastIconConfig, 4);
        this.mIconView.getViewTreeObserver().addOnGlobalLayoutListener(new 2(activity));
        addCtaButtonWidget(activity);
        Context context = activity;
        this.mSocialActionsView = createSocialActionsView(context, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.SOCIAL_ACTIONS_AD_SLOT_ID), Dips.dipsToIntPixels(38.0f, activity), 6, this.mCtaButtonWidget, 4, WEBVIEW_PADDING);
        addCloseButtonWidget(activity, 8);
        Handler mainHandler = new Handler(Looper.getMainLooper());
        this.mProgressCheckerRunnable = new VastVideoViewProgressRunnable(this, this.mVastVideoConfig, mainHandler);
        this.mCountdownRunnable = new VastVideoViewCountdownRunnable(this, mainHandler);
    }

    @VisibleForTesting
    View createAdsByView(Activity activity) {
        return createSocialActionsView(activity, (VastCompanionAdConfig) this.mSocialActionsCompanionAds.get(VastXmlManagerAggregator.ADS_BY_AD_SLOT_ID), this.mIconView.getHeight(), 1, this.mIconView, 0, 6);
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSocialActions() {
        return this.mHasSocialActions;
    }

    @Deprecated
    @VisibleForTesting
    View getSocialActionsView() {
        return this.mSocialActionsView;
    }

    protected VideoView getVideoView() {
        return this.mVideoView;
    }

    protected void onCreate() {
        super.onCreate();
        switch (11.$SwitchMap$com$mopub$common$util$DeviceUtils$ForceOrientation[this.mVastVideoConfig.getCustomForceOrientation().ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(1);
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                getBaseVideoViewControllerListener().onSetRequestedOrientation(0);
                break;
        }
        this.mVastVideoConfig.handleImpression(getContext(), getCurrentPosition());
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_SHOW);
    }

    protected void onResume() {
        startRunnables();
        if (this.mSeekerPositionOnPause > 0) {
            this.mVideoView.seekTo(this.mSeekerPositionOnPause);
        }
        if (!this.mIsVideoFinishedPlaying) {
            this.mVideoView.start();
        }
        if (this.mSeekerPositionOnPause != SEEKER_POSITION_NOT_INITIALIZED) {
            this.mVastVideoConfig.handleResume(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onPause() {
        stopRunnables();
        this.mSeekerPositionOnPause = getCurrentPosition();
        this.mVideoView.pause();
        if (!this.mIsVideoFinishedPlaying && !this.mIsClosing) {
            this.mVastVideoConfig.handlePause(getContext(), this.mSeekerPositionOnPause);
        }
    }

    protected void onDestroy() {
        stopRunnables();
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_DISMISS);
        this.mVideoView.onDestroy();
    }

    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt(CURRENT_POSITION, this.mSeekerPositionOnPause);
        outState.putSerializable(RESUMED_VAST_CONFIG, this.mVastVideoConfig);
    }

    protected void onConfigurationChanged(Configuration newConfig) {
        int orientation = getContext().getResources().getConfiguration().orientation;
        this.mVastCompanionAdConfig = this.mVastVideoConfig.getVastCompanionAd(orientation);
        if (this.mLandscapeCompanionAdView.getVisibility() == 0 || this.mPortraitCompanionAdView.getVisibility() == 0) {
            if (orientation == 1) {
                this.mLandscapeCompanionAdView.setVisibility(4);
                this.mPortraitCompanionAdView.setVisibility(0);
            } else {
                this.mPortraitCompanionAdView.setVisibility(4);
                this.mLandscapeCompanionAdView.setVisibility(0);
            }
            if (this.mVastCompanionAdConfig != null) {
                this.mVastCompanionAdConfig.handleImpression(getContext(), this.mDuration);
            }
        }
    }

    protected void onBackPressed() {
    }

    public boolean backButtonEnabled() {
        return this.mShowCloseButtonEventFired;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1 && resultCode == SEEKER_POSITION_NOT_INITIALIZED) {
            getBaseVideoViewControllerListener().onFinish();
        }
    }

    private void adjustSkipOffset() {
        int videoDuration = getDuration();
        if (this.mVastVideoConfig.isRewardedVideo()) {
            this.mShowCloseButtonDelay = videoDuration;
            return;
        }
        if (videoDuration < MAX_VIDEO_DURATION_FOR_CLOSE_BUTTON) {
            this.mShowCloseButtonDelay = videoDuration;
        }
        Integer skipOffsetMillis = this.mVastVideoConfig.getSkipOffsetMillis(videoDuration);
        if (skipOffsetMillis != null) {
            this.mShowCloseButtonDelay = skipOffsetMillis.intValue();
            this.mHasSkipOffset = true;
        }
    }

    private VastVideoView createVideoView(@NonNull Context context, int initialVisibility) {
        if (this.mVastVideoConfig.getDiskMediaFileUrl() == null) {
            throw new IllegalStateException("VastVideoConfig does not have a video disk path");
        }
        VastVideoView videoView = new VastVideoView(context);
        videoView.setId((int) Utils.generateUniqueId());
        videoView.setOnPreparedListener(new 3(videoView));
        videoView.setOnTouchListener(this.mClickThroughListener);
        videoView.setOnCompletionListener(new 4(videoView, context));
        videoView.setOnErrorListener(new 5(videoView));
        videoView.setVideoPath(this.mVastVideoConfig.getDiskMediaFileUrl());
        videoView.setVisibility(initialVisibility);
        return videoView;
    }

    private void addTopGradientStripWidget(@NonNull Context context) {
        boolean hasCompanionAd;
        if (this.mVastCompanionAdConfig != null) {
            hasCompanionAd = true;
        } else {
            hasCompanionAd = false;
        }
        this.mTopGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.TOP_BOTTOM, this.mVastVideoConfig.getCustomForceOrientation(), hasCompanionAd, 0, 6, getLayout().getId());
        getLayout().addView(this.mTopGradientStripWidget);
    }

    private void addBottomGradientStripWidget(@NonNull Context context) {
        this.mBottomGradientStripWidget = new VastVideoGradientStripWidget(context, Orientation.BOTTOM_TOP, this.mVastVideoConfig.getCustomForceOrientation(), this.mVastCompanionAdConfig != null, 8, 2, this.mProgressBarWidget.getId());
        getLayout().addView(this.mBottomGradientStripWidget);
    }

    private void addProgressBarWidget(@NonNull Context context, int initialVisibility) {
        this.mProgressBarWidget = new VastVideoProgressBarWidget(context);
        this.mProgressBarWidget.setAnchorId(this.mVideoView.getId());
        this.mProgressBarWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mProgressBarWidget);
    }

    private void addRadialCountdownWidget(@NonNull Context context, int initialVisibility) {
        this.mRadialCountdownWidget = new VastVideoRadialCountdownWidget(context);
        this.mRadialCountdownWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mRadialCountdownWidget);
    }

    private void addCtaButtonWidget(@NonNull Context context) {
        boolean hasCompanionAd;
        boolean hasClickthroughUrl;
        if (this.mVastCompanionAdConfig != null) {
            hasCompanionAd = true;
        } else {
            hasCompanionAd = false;
        }
        if (TextUtils.isEmpty(this.mVastVideoConfig.getClickThroughUrl())) {
            hasClickthroughUrl = false;
        } else {
            hasClickthroughUrl = true;
        }
        this.mCtaButtonWidget = new VastVideoCtaButtonWidget(context, this.mVideoView.getId(), hasCompanionAd, hasClickthroughUrl);
        getLayout().addView(this.mCtaButtonWidget);
        this.mCtaButtonWidget.setOnTouchListener(this.mClickThroughListener);
        String customCtaText = this.mVastVideoConfig.getCustomCtaText();
        if (customCtaText != null) {
            this.mCtaButtonWidget.updateCtaText(customCtaText);
        }
    }

    private void addCloseButtonWidget(@NonNull Context context, int initialVisibility) {
        this.mCloseButtonWidget = new VastVideoCloseButtonWidget(context);
        this.mCloseButtonWidget.setVisibility(initialVisibility);
        getLayout().addView(this.mCloseButtonWidget);
        this.mCloseButtonWidget.setOnTouchListenerToContent(new 6());
        String customSkipText = this.mVastVideoConfig.getCustomSkipText();
        if (customSkipText != null) {
            this.mCloseButtonWidget.updateCloseButtonText(customSkipText);
        }
        String customCloseIconUrl = this.mVastVideoConfig.getCustomCloseIconUrl();
        if (customCloseIconUrl != null) {
            this.mCloseButtonWidget.updateCloseButtonIcon(customCloseIconUrl);
        }
    }

    private void addBlurredLastVideoFrameImageView(@NonNull Context context, int initialVisibility) {
        this.mBlurredLastVideoFrameImageView = new ImageView(context);
        this.mBlurredLastVideoFrameImageView.setVisibility(initialVisibility);
        getLayout().addView(this.mBlurredLastVideoFrameImageView, new LayoutParams(SEEKER_POSITION_NOT_INITIALIZED, SEEKER_POSITION_NOT_INITIALIZED));
    }

    @NonNull
    @VisibleForTesting
    View createCompanionAdView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int initialVisibility) {
        Preconditions.checkNotNull(context);
        if (vastCompanionAdConfig == null) {
            View emptyView = new View(context);
            emptyView.setVisibility(4);
            return emptyView;
        }
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(17);
        getLayout().addView(relativeLayout, new LayoutParams(SEEKER_POSITION_NOT_INITIALIZED, SEEKER_POSITION_NOT_INITIALIZED));
        View companionView = createCompanionVastWebView(context, vastCompanionAdConfig);
        companionView.setVisibility(initialVisibility);
        LayoutParams companionAdLayout = new LayoutParams(Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getWidth() + WEBVIEW_PADDING), context), Dips.dipsToIntPixels((float) (vastCompanionAdConfig.getHeight() + WEBVIEW_PADDING), context));
        companionAdLayout.addRule(13, SEEKER_POSITION_NOT_INITIALIZED);
        relativeLayout.addView(companionView, companionAdLayout);
        return companionView;
    }

    @NonNull
    @VisibleForTesting
    View createSocialActionsView(@NonNull Context context, @Nullable VastCompanionAdConfig vastCompanionAdConfig, int anchorHeight, int layoutVerb, @NonNull View anchorView, int initialVisibility, int leftMarginDips) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(anchorView);
        if (vastCompanionAdConfig == null) {
            View emptyView = new View(context);
            emptyView.setVisibility(4);
            return emptyView;
        }
        this.mHasSocialActions = true;
        this.mCtaButtonWidget.setHasSocialActions(this.mHasSocialActions);
        View companionView = createCompanionVastWebView(context, vastCompanionAdConfig);
        int width = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getWidth(), context);
        int height = Dips.dipsToIntPixels((float) vastCompanionAdConfig.getHeight(), context);
        int offset = (anchorHeight - height) / 2;
        int leftMargin = Dips.dipsToIntPixels((float) leftMarginDips, context);
        LayoutParams companionAdLayout = new LayoutParams(width, height);
        companionAdLayout.addRule(layoutVerb, anchorView.getId());
        companionAdLayout.addRule(6, anchorView.getId());
        companionAdLayout.setMargins(leftMargin, offset, 0, 0);
        RelativeLayout relativeLayout = new RelativeLayout(context);
        relativeLayout.setGravity(WEBVIEW_PADDING);
        relativeLayout.addView(companionView, new LayoutParams(-2, -2));
        getLayout().addView(relativeLayout, companionAdLayout);
        companionView.setVisibility(initialVisibility);
        return companionView;
    }

    @NonNull
    @VisibleForTesting
    View createIconView(@NonNull Context context, @Nullable VastIconConfig vastIconConfig, int initialVisibility) {
        Preconditions.checkNotNull(context);
        if (vastIconConfig == null) {
            return new View(context);
        }
        View iconView = VastWebView.createView(context, vastIconConfig.getVastResource());
        iconView.setVastWebViewClickListener(new 7(vastIconConfig, context));
        iconView.setWebViewClient(new 8(vastIconConfig));
        iconView.setVisibility(initialVisibility);
        LayoutParams layoutParams = new LayoutParams(Dips.asIntPixels((float) vastIconConfig.getWidth(), context), Dips.asIntPixels((float) vastIconConfig.getHeight(), context));
        layoutParams.setMargins(Dips.dipsToIntPixels(12.0f, context), Dips.dipsToIntPixels(12.0f, context), 0, 0);
        getLayout().addView(iconView, layoutParams);
        return iconView;
    }

    int getDuration() {
        return this.mVideoView.getDuration();
    }

    int getCurrentPosition() {
        return this.mVideoView.getCurrentPosition();
    }

    void makeVideoInteractable() {
        this.mShowCloseButtonEventFired = true;
        this.mRadialCountdownWidget.setVisibility(8);
        this.mCloseButtonWidget.setVisibility(0);
        this.mCtaButtonWidget.notifyVideoSkippable();
        this.mSocialActionsView.setVisibility(0);
    }

    boolean shouldBeInteractable() {
        return !this.mShowCloseButtonEventFired && getCurrentPosition() >= this.mShowCloseButtonDelay;
    }

    void updateCountdown() {
        if (this.mIsCalibrationDone) {
            this.mRadialCountdownWidget.updateCountdownProgress(this.mShowCloseButtonDelay, getCurrentPosition());
        }
    }

    void updateProgressBar() {
        this.mProgressBarWidget.updateProgress(getCurrentPosition());
    }

    String getNetworkMediaFileUrl() {
        if (this.mVastVideoConfig == null) {
            return null;
        }
        return this.mVastVideoConfig.getNetworkMediaFileUrl();
    }

    void handleIconDisplay(int currentPosition) {
        if (this.mVastIconConfig != null && currentPosition >= this.mVastIconConfig.getOffsetMS()) {
            this.mIconView.setVisibility(0);
            this.mVastIconConfig.handleImpression(getContext(), currentPosition, getNetworkMediaFileUrl());
            if (this.mVastIconConfig.getDurationMS() != null && currentPosition >= this.mVastIconConfig.getOffsetMS() + this.mVastIconConfig.getDurationMS().intValue()) {
                this.mIconView.setVisibility(8);
            }
        }
    }

    private boolean shouldAllowClickThrough() {
        return this.mShowCloseButtonEventFired;
    }

    private void startRunnables() {
        this.mProgressCheckerRunnable.startRepeating(VIDEO_PROGRESS_TIMER_CHECKER_DELAY);
        this.mCountdownRunnable.startRepeating(VIDEO_COUNTDOWN_UPDATE_INTERVAL);
    }

    private void stopRunnables() {
        this.mProgressCheckerRunnable.stop();
        this.mCountdownRunnable.stop();
    }

    @NonNull
    private VastWebView createCompanionVastWebView(@NonNull Context context, @NonNull VastCompanionAdConfig vastCompanionAdConfig) {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(vastCompanionAdConfig);
        VastWebView companionView = VastWebView.createView(context, vastCompanionAdConfig.getVastResource());
        companionView.setVastWebViewClickListener(new 9(vastCompanionAdConfig, context));
        companionView.setWebViewClient(new 10(vastCompanionAdConfig, context));
        return companionView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewProgressRunnable getProgressCheckerRunnable() {
        return this.mProgressCheckerRunnable;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoViewCountdownRunnable getCountdownRunnable() {
        return this.mCountdownRunnable;
    }

    @Deprecated
    @VisibleForTesting
    boolean getHasSkipOffset() {
        return this.mHasSkipOffset;
    }

    @Deprecated
    @VisibleForTesting
    int getShowCloseButtonDelay() {
        return this.mShowCloseButtonDelay;
    }

    @Deprecated
    @VisibleForTesting
    boolean isShowCloseButtonEventFired() {
        return this.mShowCloseButtonEventFired;
    }

    @Deprecated
    @VisibleForTesting
    void setCloseButtonVisible(boolean visible) {
        this.mShowCloseButtonEventFired = visible;
    }

    @Deprecated
    @VisibleForTesting
    boolean isVideoFinishedPlaying() {
        return this.mIsVideoFinishedPlaying;
    }

    @Deprecated
    @VisibleForTesting
    boolean isCalibrationDone() {
        return this.mIsCalibrationDone;
    }

    @Deprecated
    @VisibleForTesting
    View getLandscapeCompanionAdView() {
        return this.mLandscapeCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    View getPortraitCompanionAdView() {
        return this.mPortraitCompanionAdView;
    }

    @Deprecated
    @VisibleForTesting
    boolean getVideoError() {
        return this.mVideoError;
    }

    @Deprecated
    @VisibleForTesting
    void setVideoError() {
        this.mVideoError = true;
    }

    @Deprecated
    @VisibleForTesting
    View getIconView() {
        return this.mIconView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getTopGradientStripWidget() {
        return this.mTopGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoGradientStripWidget getBottomGradientStripWidget() {
        return this.mBottomGradientStripWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoProgressBarWidget getProgressBarWidget() {
        return this.mProgressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setProgressBarWidget(@NonNull VastVideoProgressBarWidget progressBarWidget) {
        this.mProgressBarWidget = progressBarWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoRadialCountdownWidget getRadialCountdownWidget() {
        return this.mRadialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    void setRadialCountdownWidget(@NonNull VastVideoRadialCountdownWidget radialCountdownWidget) {
        this.mRadialCountdownWidget = radialCountdownWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCtaButtonWidget getCtaButtonWidget() {
        return this.mCtaButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoCloseButtonWidget getCloseButtonWidget() {
        return this.mCloseButtonWidget;
    }

    @Deprecated
    @VisibleForTesting
    ImageView getBlurredLastVideoFrameImageView() {
        return this.mBlurredLastVideoFrameImageView;
    }

    @Deprecated
    @VisibleForTesting
    VastVideoView getVastVideoView() {
        return this.mVideoView;
    }

    @Deprecated
    @VisibleForTesting
    void setIsClosing(boolean isClosing) {
        this.mIsClosing = isClosing;
    }
}
