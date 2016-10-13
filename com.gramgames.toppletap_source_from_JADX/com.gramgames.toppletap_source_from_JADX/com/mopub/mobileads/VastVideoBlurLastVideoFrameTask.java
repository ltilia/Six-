package com.mopub.mobileads;

import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ImageUtils;

public class VastVideoBlurLastVideoFrameTask extends AsyncTask<String, Void, Boolean> {
    private static final int MICROSECONDS_PER_MILLISECOND = 1000;
    private static final int OFFSET_IN_MICROSECONDS = 200000;
    @Nullable
    private Bitmap mBlurredLastVideoFrame;
    @NonNull
    private final ImageView mBlurredLastVideoFrameImageView;
    @Nullable
    private Bitmap mLastVideoFrame;
    @NonNull
    private final MediaMetadataRetriever mMediaMetadataRetriever;
    private int mVideoDuration;

    public VastVideoBlurLastVideoFrameTask(@NonNull MediaMetadataRetriever mediaMetadataRetriever, @NonNull ImageView blurredLastVideoFrameImageView, int videoDuration) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
        this.mBlurredLastVideoFrameImageView = blurredLastVideoFrameImageView;
        this.mVideoDuration = videoDuration;
    }

    protected Boolean doInBackground(String... videoPaths) {
        if (VERSION.SDK_INT < 10) {
            return Boolean.valueOf(false);
        }
        if (videoPaths == null || videoPaths.length == 0 || videoPaths[0] == null) {
            return Boolean.valueOf(false);
        }
        try {
            this.mMediaMetadataRetriever.setDataSource(videoPaths[0]);
            this.mLastVideoFrame = this.mMediaMetadataRetriever.getFrameAtTime((long) ((this.mVideoDuration * MICROSECONDS_PER_MILLISECOND) - OFFSET_IN_MICROSECONDS), 3);
            if (this.mLastVideoFrame == null) {
                return Boolean.valueOf(false);
            }
            this.mBlurredLastVideoFrame = ImageUtils.applyFastGaussianBlurToBitmap(this.mLastVideoFrame, 4);
            return Boolean.valueOf(true);
        } catch (Exception e) {
            MoPubLog.d("Failed to blur last video frame", e);
            return Boolean.valueOf(false);
        }
    }

    protected void onPostExecute(Boolean success) {
        if (isCancelled()) {
            onCancelled();
        } else if (success != null && success.booleanValue()) {
            this.mBlurredLastVideoFrameImageView.setImageBitmap(this.mBlurredLastVideoFrame);
            ImageUtils.setImageViewAlpha(this.mBlurredLastVideoFrameImageView, 100);
        }
    }

    protected void onCancelled() {
        MoPubLog.d("VastVideoBlurLastVideoFrameTask was cancelled.");
    }

    @Deprecated
    @VisibleForTesting
    Bitmap getBlurredLastVideoFrame() {
        return this.mBlurredLastVideoFrame;
    }
}
