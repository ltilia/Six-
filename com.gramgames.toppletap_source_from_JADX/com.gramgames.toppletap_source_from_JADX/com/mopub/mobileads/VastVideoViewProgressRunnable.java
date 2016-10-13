package com.mopub.mobileads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.common.Preconditions;
import com.mopub.network.TrackingRequest;
import java.util.ArrayList;
import java.util.List;

public class VastVideoViewProgressRunnable extends RepeatingHandlerRunnable {
    @NonNull
    private final VastVideoConfig mVastVideoConfig;
    @NonNull
    private final VastVideoViewController mVideoViewController;

    public VastVideoViewProgressRunnable(@NonNull VastVideoViewController videoViewController, @NonNull VastVideoConfig vastVideoConfig, @NonNull Handler handler) {
        super(handler);
        Preconditions.checkNotNull(videoViewController);
        Preconditions.checkNotNull(vastVideoConfig);
        this.mVideoViewController = videoViewController;
        this.mVastVideoConfig = vastVideoConfig;
    }

    public void doWork() {
        int videoLength = this.mVideoViewController.getDuration();
        int currentPosition = this.mVideoViewController.getCurrentPosition();
        this.mVideoViewController.updateProgressBar();
        if (videoLength > 0) {
            List<VastTracker> trackersToTrack = this.mVastVideoConfig.getUntriggeredTrackersBefore(currentPosition, videoLength);
            if (!trackersToTrack.isEmpty()) {
                List<String> trackUrls = new ArrayList();
                for (VastTracker tracker : trackersToTrack) {
                    trackUrls.add(tracker.getTrackingUrl());
                    tracker.setTracked();
                }
                TrackingRequest.makeTrackingHttpRequest(new VastMacroHelper(trackUrls).withAssetUri(this.mVideoViewController.getNetworkMediaFileUrl()).withContentPlayHead(Integer.valueOf(currentPosition)).getUris(), this.mVideoViewController.getContext());
            }
            this.mVideoViewController.handleIconDisplay(currentPosition);
        }
    }
}
