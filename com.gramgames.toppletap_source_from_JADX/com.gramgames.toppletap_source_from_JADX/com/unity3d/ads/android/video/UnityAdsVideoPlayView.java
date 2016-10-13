package com.unity3d.ads.android.video;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.support.v4.internal.view.SupportMenu;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.AdError;
import com.google.android.exoplayer.hls.HlsChunkSource;
import com.google.android.exoplayer.util.MimeTypes;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.UnityAdsUtils;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import com.unity3d.ads.android.view.UnityAdsMuteVideoButton;
import com.unity3d.ads.android.view.UnityAdsMuteVideoButton.UnityAdsMuteVideoButtonState;
import com.unity3d.ads.android.view.UnityAdsViewUtils;
import com.unity3d.ads.android.webapp.UnityAdsWebData;
import com.unity3d.ads.android.webapp.UnityAdsWebData.UnityAdsVideoPosition;
import com.unity3d.ads.android.zone.UnityAdsZone;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

@TargetApi(9)
public class UnityAdsVideoPlayView extends RelativeLayout {
    private long _bufferingStartedMillis;
    private TextView _bufferingText;
    private LinearLayout _countDownText;
    private RelativeLayout _layout;
    private IUnityAdsVideoPlayerListener _listener;
    private MediaPlayer _mediaPlayer;
    private UnityAdsMuteVideoButton _muteButton;
    private boolean _muted;
    private UnityAdsVideoPausedView _pausedView;
    private final Map _sentPositionEvents;
    private TextView _skipTextView;
    private TextView _timeLeftInSecondsText;
    private Timer _videoPausedTimer;
    private boolean _videoPlaybackErrors;
    private boolean _videoPlaybackStartedSent;
    private boolean _videoPlayheadPrepared;
    private long _videoStartedPlayingMillis;
    private UnityAdsVideoView _videoView;
    private float _volumeBeforeMute;

    class 1 implements OnErrorListener {
        1() {
        }

        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            UnityAdsDeviceLog.error("For some reason the device failed to play the video (error: " + i + ", " + i2 + "), a crash was prevented.");
            UnityAdsVideoPlayView.this.videoPlaybackFailed();
            return true;
        }
    }

    class 2 implements Runnable {
        2() {
        }

        public void run() {
            UnityAdsVideoPlayView.this._videoView.pause();
            UnityAdsVideoPlayView.this.setKeepScreenOn(false);
            UnityAdsVideoPlayView.this.createAndAddPausedView();
        }
    }

    class 3 implements Runnable {
        3() {
        }

        public void run() {
            UnityAdsVideoPlayView.this._videoView.start();
            UnityAdsVideoPlayView.this.setKeepScreenOn(true);
        }
    }

    class 4 implements OnCompletionListener {
        4() {
        }

        public void onCompletion(MediaPlayer mediaPlayer) {
            UnityAdsVideoPlayView.this._listener.onCompletion(mediaPlayer);
        }
    }

    class 5 implements OnPreparedListener {
        5() {
        }

        public void onPrepared(MediaPlayer mediaPlayer) {
            UnityAdsDeviceLog.entered();
            UnityAdsVideoPlayView.this._mediaPlayer = mediaPlayer;
            if (UnityAdsVideoPlayView.this._muted) {
                UnityAdsVideoPlayView.this.storeVolume();
                UnityAdsVideoPlayView.this._mediaPlayer.setVolume(0.0f, 0.0f);
            }
            UnityAdsVideoPlayView.this._videoPlayheadPrepared = true;
        }
    }

    class 6 implements OnClickListener {
        6() {
        }

        public void onClick(View view) {
            if (!UnityAdsVideoPlayView.this._videoPlayheadPrepared || !UnityAdsVideoPlayView.this._videoPlaybackStartedSent) {
                return;
            }
            if (UnityAdsVideoPlayView.this._muted) {
                UnityAdsVideoPlayView.this._muted = false;
                UnityAdsVideoPlayView.this._muteButton.setState(UnityAdsMuteVideoButtonState.UnMuted);
                UnityAdsVideoPlayView.this._mediaPlayer.setVolume(UnityAdsVideoPlayView.this._volumeBeforeMute, UnityAdsVideoPlayView.this._volumeBeforeMute);
                return;
            }
            UnityAdsVideoPlayView.this._muted = true;
            UnityAdsVideoPlayView.this._muteButton.setState(UnityAdsMuteVideoButtonState.Muted);
            UnityAdsVideoPlayView.this.storeVolume();
            UnityAdsVideoPlayView.this._mediaPlayer.setVolume(0.0f, 0.0f);
        }
    }

    class 7 implements OnClickListener {
        7() {
        }

        public void onClick(View view) {
            if (!UnityAdsVideoPlayView.this._videoView.isPlaying()) {
                UnityAdsVideoPlayView.this.hideVideoPausedView();
                UnityAdsVideoPlayView.this.startVideo();
            }
        }
    }

    class 8 implements OnClickListener {
        8() {
        }

        public void onClick(View view) {
            UnityAdsVideoPlayView.this._listener.onVideoSkip();
        }
    }

    class 9 implements Runnable {
        final /* synthetic */ boolean val$canSkip;
        final /* synthetic */ boolean val$hasSkip;
        final /* synthetic */ int val$visibility;

        9(int i, boolean z, boolean z2) {
            this.val$visibility = i;
            this.val$hasSkip = z;
            this.val$canSkip = z2;
        }

        public void run() {
            if (UnityAdsVideoPlayView.this._bufferingText != null) {
                UnityAdsVideoPlayView.this._bufferingText.setVisibility(this.val$visibility);
            }
            if (this.val$visibility == 0) {
                if (UnityAdsVideoPlayView.this._skipTextView == null) {
                    UnityAdsVideoPlayView.this.updateSkipText(UnityAdsVideoPlayView.this.getSkipDuration());
                }
                UnityAdsVideoPlayView.this.enableSkippingFromSkipText();
            } else if (!this.val$hasSkip) {
                UnityAdsVideoPlayView.this.hideSkipText();
            } else if (this.val$canSkip) {
                UnityAdsVideoPlayView.this.enableSkippingFromSkipText();
            } else {
                UnityAdsVideoPlayView.this.disableSkippingFromSkipText();
            }
        }
    }

    class VideoStateChecker extends TimerTask {
        private Float _curPos;
        private int _duration;
        private Float _oldPos;
        private boolean _playHeadHasMoved;
        private Float _skipTimeLeft;
        private boolean _videoHasStalled;

        class 1 implements Runnable {
            1() {
            }

            public void run() {
                UnityAdsVideoPlayView.this.updateTimeLeftText();
            }
        }

        class 2 implements Runnable {
            2() {
            }

            public void run() {
                UnityAdsVideoPlayView.this.enableSkippingFromSkipText();
            }
        }

        class 3 implements Runnable {
            3() {
            }

            public void run() {
                if (UnityAdsVideoPlayView.this._skipTextView != null && !VideoStateChecker.this._videoHasStalled) {
                    UnityAdsVideoPlayView.this._skipTextView.setVisibility(0);
                    UnityAdsVideoPlayView.this.updateSkipText(Math.round(Math.ceil((double) ((((float) (UnityAdsVideoPlayView.this.getSkipDuration() * 1000)) - VideoStateChecker.this._curPos.floatValue()) / 1000.0f))));
                }
            }
        }

        class 4 implements Runnable {
            4() {
            }

            public void run() {
                UnityAdsVideoPlayView.this.hideSkipText();
            }
        }

        class 5 implements Runnable {
            5() {
            }

            public void run() {
                UnityAdsDeviceLog.error("Buffering taking too long.. cancelling video play");
                UnityAdsVideoPlayView.this.videoPlaybackFailed();
            }
        }

        class 6 implements Runnable {
            6() {
            }

            public void run() {
                if (!UnityAdsVideoPlayView.this._videoPlaybackStartedSent) {
                    if (UnityAdsVideoPlayView.this._listener != null) {
                        UnityAdsVideoPlayView.this._videoPlaybackStartedSent = true;
                        UnityAdsVideoPlayView.this._listener.onVideoPlaybackStarted();
                        UnityAdsVideoPlayView.this._videoStartedPlayingMillis = System.currentTimeMillis();
                    }
                    if (!UnityAdsVideoPlayView.this._sentPositionEvents.containsKey(UnityAdsVideoPosition.Start)) {
                        UnityAdsVideoPlayView.this._sentPositionEvents.put(UnityAdsVideoPosition.Start, Boolean.valueOf(true));
                        UnityAdsVideoPlayView.this._listener.onEventPositionReached(UnityAdsVideoPosition.Start);
                    }
                }
            }
        }

        private VideoStateChecker() {
            this._curPos = Float.valueOf(0.0f);
            this._oldPos = Float.valueOf(0.0f);
            this._skipTimeLeft = Float.valueOf(0.01f);
            this._duration = 1;
            this._playHeadHasMoved = false;
            this._videoHasStalled = false;
        }

        public void run() {
            if (UnityAdsVideoPlayView.this._videoView == null || UnityAdsVideoPlayView.this._timeLeftInSecondsText == null) {
                UnityAdsVideoPlayView.this.purgeVideoPausedTimer();
                return;
            }
            int duration;
            this._oldPos = this._curPos;
            try {
                this._curPos = Float.valueOf((float) UnityAdsVideoPlayView.this._videoView.getCurrentPosition());
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Could not get videoView currentPosition");
                if (this._oldPos.floatValue() > 0.0f) {
                    this._curPos = this._oldPos;
                } else {
                    this._curPos = Float.valueOf(0.01f);
                }
            }
            Boolean valueOf = Boolean.valueOf(true);
            try {
                duration = UnityAdsVideoPlayView.this._videoView.getDuration();
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Could not get videoView duration");
                valueOf = Boolean.valueOf(false);
                duration = 1;
            }
            if (valueOf.booleanValue()) {
                this._duration = duration;
            }
            Float valueOf2 = Float.valueOf(this._curPos.floatValue() / ((float) this._duration));
            if (this._curPos.floatValue() > this._oldPos.floatValue()) {
                this._playHeadHasMoved = true;
                this._videoHasStalled = false;
                UnityAdsVideoPlayView.this.setBufferingTextVisibility(4, UnityAdsVideoPlayView.this.hasSkipDuration(), this._skipTimeLeft.floatValue() <= 0.0f);
            } else {
                this._videoHasStalled = true;
                UnityAdsVideoPlayView.this.setBufferingTextVisibility(0, true, true);
            }
            UnityAdsUtils.runOnUiThread(new 1());
            if (UnityAdsVideoPlayView.this.hasSkipDuration() && UnityAdsVideoPlayView.this.getSkipDuration() > 0 && this._skipTimeLeft.floatValue() > 0.0f && ((long) (this._duration / AdError.NETWORK_ERROR_CODE)) > UnityAdsVideoPlayView.this.getSkipDuration()) {
                this._skipTimeLeft = Float.valueOf(((float) (UnityAdsVideoPlayView.this.getSkipDuration() * 1000)) - this._curPos.floatValue());
                if (this._skipTimeLeft.floatValue() < 0.0f) {
                    this._skipTimeLeft = Float.valueOf(0.0f);
                }
                if (this._skipTimeLeft.floatValue() == 0.0f) {
                    UnityAdsUtils.runOnUiThread(new 2());
                } else {
                    UnityAdsUtils.runOnUiThread(new 3());
                }
            } else if (this._playHeadHasMoved && ((long) (this._duration / AdError.NETWORK_ERROR_CODE)) <= UnityAdsVideoPlayView.this.getSkipDuration()) {
                UnityAdsUtils.runOnUiThread(new 4());
            }
            if (((double) valueOf2.floatValue()) > 0.25d && !UnityAdsVideoPlayView.this._sentPositionEvents.containsKey(UnityAdsVideoPosition.FirstQuartile)) {
                UnityAdsVideoPlayView.this._listener.onEventPositionReached(UnityAdsVideoPosition.FirstQuartile);
                UnityAdsVideoPlayView.this._sentPositionEvents.put(UnityAdsVideoPosition.FirstQuartile, Boolean.valueOf(true));
            }
            if (((double) valueOf2.floatValue()) > 0.5d && !UnityAdsVideoPlayView.this._sentPositionEvents.containsKey(UnityAdsVideoPosition.MidPoint)) {
                UnityAdsVideoPlayView.this._listener.onEventPositionReached(UnityAdsVideoPosition.MidPoint);
                UnityAdsVideoPlayView.this._sentPositionEvents.put(UnityAdsVideoPosition.MidPoint, Boolean.valueOf(true));
            }
            if (((double) valueOf2.floatValue()) > 0.75d && !UnityAdsVideoPlayView.this._sentPositionEvents.containsKey(UnityAdsVideoPosition.ThirdQuartile)) {
                UnityAdsVideoPlayView.this._listener.onEventPositionReached(UnityAdsVideoPosition.ThirdQuartile);
                UnityAdsVideoPlayView.this._sentPositionEvents.put(UnityAdsVideoPosition.ThirdQuartile, Boolean.valueOf(true));
            }
            if (!this._playHeadHasMoved && UnityAdsVideoPlayView.this._bufferingStartedMillis > 0 && System.currentTimeMillis() - UnityAdsVideoPlayView.this._bufferingStartedMillis > HlsChunkSource.DEFAULT_MAX_BUFFER_TO_SWITCH_DOWN_MS) {
                cancel();
                UnityAdsUtils.runOnUiThread(new 5());
            }
            if (UnityAdsVideoPlayView.this._videoPlayheadPrepared && this._playHeadHasMoved) {
                UnityAdsUtils.runOnUiThread(new 6());
            }
        }
    }

    public UnityAdsVideoPlayView(Context context) {
        super(context);
        this._timeLeftInSecondsText = null;
        this._bufferingStartedMillis = 0;
        this._videoStartedPlayingMillis = 0;
        this._volumeBeforeMute = 0.5f;
        this._sentPositionEvents = new HashMap();
        this._pausedView = null;
        this._muteButton = null;
        this._countDownText = null;
        this._videoPausedTimer = null;
        this._mediaPlayer = null;
        this._videoPlaybackErrors = false;
        this._muted = false;
        this._videoPlaybackStartedSent = false;
        this._videoPlayheadPrepared = false;
        this._layout = null;
        this._videoView = null;
        this._skipTextView = null;
        this._bufferingText = null;
        createView();
    }

    public UnityAdsVideoPlayView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this._timeLeftInSecondsText = null;
        this._bufferingStartedMillis = 0;
        this._videoStartedPlayingMillis = 0;
        this._volumeBeforeMute = 0.5f;
        this._sentPositionEvents = new HashMap();
        this._pausedView = null;
        this._muteButton = null;
        this._countDownText = null;
        this._videoPausedTimer = null;
        this._mediaPlayer = null;
        this._videoPlaybackErrors = false;
        this._muted = false;
        this._videoPlaybackStartedSent = false;
        this._videoPlayheadPrepared = false;
        this._layout = null;
        this._videoView = null;
        this._skipTextView = null;
        this._bufferingText = null;
        createView();
    }

    public UnityAdsVideoPlayView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this._timeLeftInSecondsText = null;
        this._bufferingStartedMillis = 0;
        this._videoStartedPlayingMillis = 0;
        this._volumeBeforeMute = 0.5f;
        this._sentPositionEvents = new HashMap();
        this._pausedView = null;
        this._muteButton = null;
        this._countDownText = null;
        this._videoPausedTimer = null;
        this._mediaPlayer = null;
        this._videoPlaybackErrors = false;
        this._muted = false;
        this._videoPlaybackStartedSent = false;
        this._videoPlayheadPrepared = false;
        this._layout = null;
        this._videoView = null;
        this._skipTextView = null;
        this._bufferingText = null;
        createView();
    }

    public void setListener(IUnityAdsVideoPlayerListener iUnityAdsVideoPlayerListener) {
        this._listener = iUnityAdsVideoPlayerListener;
    }

    public void playVideo(String str, boolean z) {
        if (str != null) {
            this._videoPlayheadPrepared = false;
            UnityAdsDeviceLog.debug("Playing video from: " + str);
            this._videoView.setOnErrorListener(new 1());
            if (z && !new File(str).setReadable(true, false)) {
                UnityAdsDeviceLog.debug("COULD NOT SET FILE READABLE");
            }
            try {
                this._videoView.setVideoPath(str);
                if (!this._videoPlaybackErrors) {
                    updateTimeLeftText();
                    this._bufferingStartedMillis = System.currentTimeMillis();
                    startVideo();
                }
            } catch (Exception e) {
                UnityAdsDeviceLog.error("For some reason the device failed to play the video, a crash was prevented.");
                videoPlaybackFailed();
            }
        }
    }

    public int getCurrentPosition() {
        if (this._videoView != null) {
            return this._videoView.getCurrentPosition();
        }
        return 0;
    }

    public void seekTo(int i) {
        if (this._videoView != null) {
            this._videoView.seekTo(i);
        }
    }

    public void pauseVideo() {
        purgeVideoPausedTimer();
        if (this._videoView != null && this._videoView.isPlaying()) {
            UnityAdsUtils.runOnUiThread(new 2());
        }
    }

    public boolean isPlaying() {
        return this._videoView != null && this._videoView.isPlaying();
    }

    public void clearVideoPlayer() {
        UnityAdsDeviceLog.entered();
        setKeepScreenOn(false);
        setOnClickListener(null);
        setOnFocusChangeListener(null);
        hideSkipText();
        UnityAdsViewUtils.removeViewFromParent(this._countDownText);
        hideVideoPausedView();
        purgeVideoPausedTimer();
        this._videoView.stopPlayback();
        this._videoView.setOnCompletionListener(null);
        this._videoView.setOnPreparedListener(null);
        this._videoView.setOnErrorListener(null);
        removeAllViews();
    }

    public int getSecondsUntilBackButtonAllowed() {
        UnityAdsZone currentZone = UnityAdsWebData.getZoneManager().getCurrentZone();
        if (currentZone.disableBackButtonForSeconds() > 0 && this._videoStartedPlayingMillis > 0) {
            int round = Math.round((float) ((currentZone.disableBackButtonForSeconds() * 1000) - (System.currentTimeMillis() - this._videoStartedPlayingMillis)));
            if (round < 0) {
                return 0;
            }
            return round;
        } else if (currentZone.allowVideoSkipInSeconds() <= 0 || this._videoStartedPlayingMillis > 0) {
            return 0;
        } else {
            return 1;
        }
    }

    private void storeVolume() {
        AudioManager audioManager = (AudioManager) UnityAdsProperties.APPLICATION_CONTEXT.getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (audioManager != null) {
            int streamVolume = audioManager.getStreamVolume(3);
            int streamMaxVolume = audioManager.getStreamMaxVolume(3);
            float f = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT / ((float) streamMaxVolume);
            this._volumeBeforeMute = ((float) streamVolume) * f;
            UnityAdsDeviceLog.debug("Storing volume: " + streamVolume + ", " + streamMaxVolume + ", " + f + ", " + this._volumeBeforeMute);
        }
    }

    private void videoPlaybackFailed() {
        this._videoPlaybackErrors = true;
        purgeVideoPausedTimer();
        if (this._listener != null) {
            this._listener.onVideoPlaybackError();
        }
    }

    private void startVideo() {
        UnityAdsUtils.runOnUiThread(new 3());
        if (this._videoPausedTimer == null) {
            this._videoPausedTimer = new Timer();
            this._videoPausedTimer.scheduleAtFixedRate(new VideoStateChecker(), 500, 500);
        }
    }

    private void purgeVideoPausedTimer() {
        if (this._videoPausedTimer != null) {
            this._videoPausedTimer.cancel();
            this._videoPausedTimer.purge();
            this._videoPausedTimer = null;
        }
    }

    private void createView() {
        this._layout = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.unityads_view_video_play, this);
        if (UnityAdsWebData.getZoneManager().getCurrentZone().muteVideoSounds()) {
            this._muted = true;
        }
        this._videoView = (UnityAdsVideoView) this._layout.findViewById(R.id.unityAdsVideoView);
        this._videoView.setClickable(true);
        this._videoView.setOnCompletionListener(new 4());
        this._videoView.setOnPreparedListener(new 5());
        this._bufferingText = (TextView) this._layout.findViewById(R.id.unityAdsVideoBufferingText);
        this._countDownText = (LinearLayout) this._layout.findViewById(R.id.unityAdsVideoCountDown);
        this._timeLeftInSecondsText = (TextView) this._layout.findViewById(R.id.unityAdsVideoTimeLeftText);
        this._timeLeftInSecondsText.setText(R.string.unityads_default_video_length_text);
        this._skipTextView = (TextView) this._layout.findViewById(R.id.unityAdsVideoSkipText);
        this._muteButton = new UnityAdsMuteVideoButton(getContext());
        this._muteButton.setLayout((RelativeLayout) this._layout.findViewById(R.id.unityAdsAudioToggleView));
        if (this._muted) {
            this._muteButton.setState(UnityAdsMuteVideoButtonState.Muted);
        }
        this._layout.findViewById(R.id.unityAdsAudioToggleView).setOnClickListener(new 6());
        if (UnityAdsProperties.UNITY_DEVELOPER_INTERNAL_TEST.booleanValue()) {
            View relativeLayout = new RelativeLayout(getContext());
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
            layoutParams.addRule(14);
            layoutParams.addRule(15);
            relativeLayout.setLayoutParams(layoutParams);
            View textView = new TextView(getContext());
            textView.setTextColor(SupportMenu.CATEGORY_MASK);
            textView.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
            textView.setText("INTERNAL UNITY TEST BUILD\nDO NOT USE IN PRODUCTION");
            relativeLayout.addView(textView);
            addView(relativeLayout);
        }
        if (hasSkipDuration()) {
            updateSkipText(getSkipDuration());
        }
        setOnClickListener(new 7());
    }

    private void enableSkippingFromSkipText() {
        if (this._skipTextView == null) {
            this._skipTextView = (TextView) this._layout.findViewById(R.id.unityAdsVideoSkipText);
        }
        if (this._skipTextView != null) {
            this._skipTextView.setText(R.string.unityads_skip_video_text);
        }
        if (this._skipTextView != null) {
            this._skipTextView.setClickable(true);
            this._skipTextView.setOnClickListener(new 8());
        }
    }

    private void updateSkipText(long j) {
        if (this._skipTextView == null) {
            this._skipTextView = (TextView) this._layout.findViewById(R.id.unityAdsVideoSkipText);
        }
        this._skipTextView.setText(getResources().getString(R.string.unityads_skip_video_prefix) + " " + j + " " + getResources().getString(R.string.unityads_skip_video_suffix));
    }

    private void updateTimeLeftText() {
        if (this._timeLeftInSecondsText == null) {
            this._timeLeftInSecondsText = (TextView) this._layout.findViewById(R.id.unityAdsVideoTimeLeftText);
        }
        this._timeLeftInSecondsText.setText(Math.round(Math.ceil((double) ((this._videoView.getDuration() - this._videoView.getCurrentPosition()) / AdError.NETWORK_ERROR_CODE))));
    }

    private void createAndAddPausedView() {
        if (this._pausedView == null) {
            this._pausedView = new UnityAdsVideoPausedView(getContext());
        }
        if (this._pausedView.getParent() == null) {
            LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
            layoutParams.addRule(13);
            addView(this._pausedView, layoutParams);
        }
    }

    private boolean hasSkipDuration() {
        return UnityAdsWebData.getZoneManager().getCurrentZone().allowVideoSkipInSeconds() > 0;
    }

    private long getSkipDuration() {
        if (hasSkipDuration()) {
            return UnityAdsWebData.getZoneManager().getCurrentZone().allowVideoSkipInSeconds();
        }
        return 0;
    }

    private void disableSkippingFromSkipText() {
        if (this._skipTextView != null) {
            this._skipTextView.setClickable(false);
        }
    }

    private void hideTimeRemainingLabel() {
        UnityAdsViewUtils.removeViewFromParent(this._countDownText);
    }

    private void hideVideoPausedView() {
        if (this._pausedView != null && this._pausedView.getParent() != null) {
            removeView(this._pausedView);
        }
    }

    private void hideSkipText() {
        if (this._skipTextView != null && this._skipTextView.getParent() != null) {
            disableSkippingFromSkipText();
            this._skipTextView.setVisibility(4);
        }
    }

    private void setBufferingTextVisibility(int i, boolean z, boolean z2) {
        UnityAdsUtils.runOnUiThread(new 9(i, z, z2));
    }
}
