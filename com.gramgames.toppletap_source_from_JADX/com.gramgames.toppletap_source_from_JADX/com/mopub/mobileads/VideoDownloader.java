package com.mopub.mobileads;

import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.common.CacheService;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.Streams;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.util.ArrayDeque;
import java.util.Deque;

public class VideoDownloader {
    private static final int MAX_VIDEO_SIZE = 26214400;
    private static final Deque<WeakReference<VideoDownloaderTask>> sDownloaderTasks;

    interface VideoDownloaderListener {
        void onComplete(boolean z);
    }

    @VisibleForTesting
    static class VideoDownloaderTask extends AsyncTask<String, Void, Boolean> {
        @NonNull
        private final VideoDownloaderListener mListener;
        @NonNull
        private final WeakReference<VideoDownloaderTask> mWeakSelf;

        @VisibleForTesting
        VideoDownloaderTask(@NonNull VideoDownloaderListener listener) {
            this.mListener = listener;
            this.mWeakSelf = new WeakReference(this);
            VideoDownloader.sDownloaderTasks.add(this.mWeakSelf);
        }

        protected Boolean doInBackground(String... params) {
            Exception e;
            Throwable th;
            if (params == null || params.length == 0 || params[0] == null) {
                MoPubLog.d("VideoDownloader task tried to execute null or empty url.");
                return Boolean.valueOf(false);
            }
            String videoUrl = params[0];
            HttpURLConnection urlConnection = null;
            InputStream inputStream = null;
            Boolean valueOf;
            try {
                urlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(videoUrl);
                InputStream inputStream2 = new BufferedInputStream(urlConnection.getInputStream());
                try {
                    int statusCode = urlConnection.getResponseCode();
                    if (statusCode < 200 || statusCode >= Strategy.TTL_SECONDS_DEFAULT) {
                        MoPubLog.d("VideoDownloader encountered unexpected statusCode: " + statusCode);
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(inputStream2);
                        if (urlConnection == null) {
                            return valueOf;
                        }
                        urlConnection.disconnect();
                        return valueOf;
                    }
                    if (urlConnection.getContentLength() > VideoDownloader.MAX_VIDEO_SIZE) {
                        MoPubLog.d(String.format("VideoDownloader encountered video larger than disk cap. (%d bytes / %d maximum).", new Object[]{Integer.valueOf(urlConnection.getContentLength()), Integer.valueOf(VideoDownloader.MAX_VIDEO_SIZE)}));
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(inputStream2);
                        if (urlConnection == null) {
                            return valueOf;
                        }
                        urlConnection.disconnect();
                        return valueOf;
                    }
                    valueOf = Boolean.valueOf(CacheService.putToDiskCache(videoUrl, inputStream2));
                    Streams.closeStream(inputStream2);
                    if (urlConnection == null) {
                        return valueOf;
                    }
                    urlConnection.disconnect();
                    return valueOf;
                } catch (Exception e2) {
                    e = e2;
                    inputStream = inputStream2;
                    try {
                        MoPubLog.d("VideoDownloader task threw an internal exception.", e);
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(inputStream);
                        if (urlConnection != null) {
                            return valueOf;
                        }
                        urlConnection.disconnect();
                        return valueOf;
                    } catch (Throwable th2) {
                        th = th2;
                        Streams.closeStream(inputStream);
                        if (urlConnection != null) {
                            urlConnection.disconnect();
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    inputStream = inputStream2;
                    Streams.closeStream(inputStream);
                    if (urlConnection != null) {
                        urlConnection.disconnect();
                    }
                    throw th;
                }
            } catch (Exception e3) {
                e = e3;
                MoPubLog.d("VideoDownloader task threw an internal exception.", e);
                valueOf = Boolean.valueOf(false);
                Streams.closeStream(inputStream);
                if (urlConnection != null) {
                    return valueOf;
                }
                urlConnection.disconnect();
                return valueOf;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (isCancelled()) {
                onCancelled();
                return;
            }
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            if (success == null) {
                this.mListener.onComplete(false);
            } else {
                this.mListener.onComplete(success.booleanValue());
            }
        }

        protected void onCancelled() {
            MoPubLog.d("VideoDownloader task was cancelled.");
            VideoDownloader.sDownloaderTasks.remove(this.mWeakSelf);
            this.mListener.onComplete(false);
        }
    }

    static {
        sDownloaderTasks = new ArrayDeque();
    }

    private VideoDownloader() {
    }

    public static void cache(@Nullable String url, @NonNull VideoDownloaderListener listener) {
        Preconditions.checkNotNull(listener);
        if (url == null) {
            MoPubLog.d("VideoDownloader attempted to cache video with null url.");
            listener.onComplete(false);
            return;
        }
        try {
            AsyncTasks.safeExecuteOnExecutor(new VideoDownloaderTask(listener), url);
        } catch (Exception e) {
            listener.onComplete(false);
        }
    }

    public static void cancelAllDownloaderTasks() {
        for (WeakReference<VideoDownloaderTask> weakDownloaderTask : sDownloaderTasks) {
            cancelOneTask(weakDownloaderTask);
        }
        sDownloaderTasks.clear();
    }

    public static void cancelLastDownloadTask() {
        if (!sDownloaderTasks.isEmpty()) {
            cancelOneTask((WeakReference) sDownloaderTasks.peekLast());
            sDownloaderTasks.removeLast();
        }
    }

    private static boolean cancelOneTask(@Nullable WeakReference<VideoDownloaderTask> weakDownloaderTask) {
        if (weakDownloaderTask == null) {
            return false;
        }
        VideoDownloaderTask downloaderTask = (VideoDownloaderTask) weakDownloaderTask.get();
        if (downloaderTask != null) {
            return downloaderTask.cancel(true);
        }
        return false;
    }

    @Deprecated
    @VisibleForTesting
    public static Deque<WeakReference<VideoDownloaderTask>> getDownloaderTasks() {
        return sDownloaderTasks;
    }

    @Deprecated
    @VisibleForTesting
    public static void clearDownloaderTasks() {
        sDownloaderTasks.clear();
    }
}
