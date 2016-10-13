package com.unity3d.ads.android.video;

import android.media.MediaPlayer.OnCompletionListener;
import com.unity3d.ads.android.webapp.UnityAdsWebData.UnityAdsVideoPosition;

public interface IUnityAdsVideoPlayerListener extends OnCompletionListener {
    void onEventPositionReached(UnityAdsVideoPosition unityAdsVideoPosition);

    void onVideoPlaybackError();

    void onVideoPlaybackStarted();

    void onVideoSkip();
}
