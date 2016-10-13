package com.mopub.mobileads;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.AsyncTask.Status;
import android.os.Build.VERSION;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.ImageView;
import android.widget.VideoView;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Streams;
import java.io.File;
import java.io.FileInputStream;

public class VastVideoView extends VideoView {
    private static final int MAX_VIDEO_RETRIES = 1;
    private static final int VIDEO_VIEW_FILE_PERMISSION_ERROR = Integer.MIN_VALUE;
    @Nullable
    private VastVideoBlurLastVideoFrameTask mBlurLastVideoFrameTask;
    @Nullable
    private MediaMetadataRetriever mMediaMetadataRetriever;
    private int mVideoRetries;

    public VastVideoView(@NonNull Context context) {
        super(context);
        Preconditions.checkNotNull(context, "context cannot be null");
        this.mMediaMetadataRetriever = createMediaMetadataRetriever();
    }

    public void prepareBlurredLastVideoFrame(@NonNull ImageView blurredLastVideoFrameImageView, @NonNull String diskMediaFileUrl) {
        if (this.mMediaMetadataRetriever != null) {
            this.mBlurLastVideoFrameTask = new VastVideoBlurLastVideoFrameTask(this.mMediaMetadataRetriever, blurredLastVideoFrameImageView, getDuration());
            try {
                AsyncTask asyncTask = this.mBlurLastVideoFrameTask;
                String[] strArr = new String[MAX_VIDEO_RETRIES];
                strArr[0] = diskMediaFileUrl;
                AsyncTasks.safeExecuteOnExecutor(asyncTask, strArr);
            } catch (Exception e) {
                MoPubLog.d("Failed to blur last video frame", e);
            }
        }
    }

    public void onDestroy() {
        if (this.mBlurLastVideoFrameTask != null && this.mBlurLastVideoFrameTask.getStatus() != Status.FINISHED) {
            this.mBlurLastVideoFrameTask.cancel(true);
        }
    }

    boolean retryMediaPlayer(MediaPlayer mediaPlayer, int what, int extra, @NonNull String diskMediaFileUrl) {
        Throwable th;
        if (VERSION.SDK_INT >= 16 || what != MAX_VIDEO_RETRIES || extra != VIDEO_VIEW_FILE_PERMISSION_ERROR || this.mVideoRetries >= MAX_VIDEO_RETRIES) {
            return false;
        }
        FileInputStream inputStream = null;
        try {
            mediaPlayer.reset();
            FileInputStream inputStream2 = new FileInputStream(new File(diskMediaFileUrl));
            try {
                mediaPlayer.setDataSource(inputStream2.getFD());
                mediaPlayer.prepareAsync();
                start();
                Streams.closeStream(inputStream2);
                this.mVideoRetries += MAX_VIDEO_RETRIES;
                return true;
            } catch (Exception e) {
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                this.mVideoRetries += MAX_VIDEO_RETRIES;
                return false;
            } catch (Throwable th2) {
                th = th2;
                inputStream = inputStream2;
                Streams.closeStream(inputStream);
                this.mVideoRetries += MAX_VIDEO_RETRIES;
                throw th;
            }
        } catch (Exception e2) {
            Streams.closeStream(inputStream);
            this.mVideoRetries += MAX_VIDEO_RETRIES;
            return false;
        } catch (Throwable th3) {
            th = th3;
            Streams.closeStream(inputStream);
            this.mVideoRetries += MAX_VIDEO_RETRIES;
            throw th;
        }
    }

    public void onResume() {
        this.mVideoRetries = 0;
    }

    @Nullable
    @VisibleForTesting
    MediaMetadataRetriever createMediaMetadataRetriever() {
        if (VERSION.SDK_INT >= 10) {
            return new MediaMetadataRetriever();
        }
        return null;
    }

    @Deprecated
    @VisibleForTesting
    void setMediaMetadataRetriever(@NonNull MediaMetadataRetriever mediaMetadataRetriever) {
        this.mMediaMetadataRetriever = mediaMetadataRetriever;
    }

    @Nullable
    @Deprecated
    @VisibleForTesting
    VastVideoBlurLastVideoFrameTask getBlurLastVideoFrameTask() {
        return this.mBlurLastVideoFrameTask;
    }

    @Deprecated
    @VisibleForTesting
    void setBlurLastVideoFrameTask(@NonNull VastVideoBlurLastVideoFrameTask blurLastVideoFrameTask) {
        this.mBlurLastVideoFrameTask = blurLastVideoFrameTask;
    }

    @Deprecated
    @VisibleForTesting
    int getVideoRetries() {
        return this.mVideoRetries;
    }
}
