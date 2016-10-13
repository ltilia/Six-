package com.mopub.nativeads;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.widget.ImageView;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.logging.MoPubLog;
import com.mopub.network.Networking;
import com.mopub.volley.VolleyError;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageContainer;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class NativeImageHelper {

    public interface ImageListener {
        void onImagesCached();

        void onImagesFailedToCache(NativeErrorCode nativeErrorCode);
    }

    static class 1 implements com.mopub.volley.toolbox.ImageLoader.ImageListener {
        final /* synthetic */ AtomicBoolean val$anyFailures;
        final /* synthetic */ AtomicInteger val$imageCounter;
        final /* synthetic */ ImageListener val$imageListener;

        1(AtomicInteger atomicInteger, AtomicBoolean atomicBoolean, ImageListener imageListener) {
            this.val$imageCounter = atomicInteger;
            this.val$anyFailures = atomicBoolean;
            this.val$imageListener = imageListener;
        }

        public void onResponse(ImageContainer imageContainer, boolean isImmediate) {
            if (imageContainer.getBitmap() != null && this.val$imageCounter.decrementAndGet() == 0 && !this.val$anyFailures.get()) {
                this.val$imageListener.onImagesCached();
            }
        }

        public void onErrorResponse(VolleyError volleyError) {
            MoPubLog.d("Failed to download a native ads image:", volleyError);
            boolean anyPreviousErrors = this.val$anyFailures.getAndSet(true);
            this.val$imageCounter.decrementAndGet();
            if (!anyPreviousErrors) {
                this.val$imageListener.onImagesFailedToCache(NativeErrorCode.IMAGE_DOWNLOAD_FAILURE);
            }
        }
    }

    static class 2 implements com.mopub.volley.toolbox.ImageLoader.ImageListener {
        final /* synthetic */ ImageView val$imageView;

        2(ImageView imageView) {
            this.val$imageView = imageView;
        }

        public void onResponse(ImageContainer imageContainer, boolean isImmediate) {
            if (!isImmediate) {
                MoPubLog.d("Image was not loaded immediately into your ad view. You should call preCacheImages as part of your custom event loading process.");
            }
            this.val$imageView.setImageBitmap(imageContainer.getBitmap());
        }

        public void onErrorResponse(VolleyError volleyError) {
            MoPubLog.d("Failed to load image.", volleyError);
            this.val$imageView.setImageDrawable(null);
        }
    }

    public static void preCacheImages(@NonNull Context context, @NonNull List<String> imageUrls, @NonNull ImageListener imageListener) {
        ImageLoader imageLoader = Networking.getImageLoader(context);
        AtomicInteger imageCounter = new AtomicInteger(imageUrls.size());
        AtomicBoolean anyFailures = new AtomicBoolean(false);
        com.mopub.volley.toolbox.ImageLoader.ImageListener volleyImageListener = new 1(imageCounter, anyFailures, imageListener);
        for (String url : imageUrls) {
            if (TextUtils.isEmpty(url)) {
                anyFailures.set(true);
                imageListener.onImagesFailedToCache(NativeErrorCode.IMAGE_DOWNLOAD_FAILURE);
                return;
            }
            imageLoader.get(url, volleyImageListener);
        }
    }

    public static void loadImageView(@Nullable String url, @Nullable ImageView imageView) {
        if (!NoThrow.checkNotNull(imageView, "Cannot load image into null ImageView")) {
            return;
        }
        if (NoThrow.checkNotNull(url, "Cannot load image with null url")) {
            Networking.getImageLoader(imageView.getContext()).get(url, new 2(imageView));
        } else {
            imageView.setImageDrawable(null);
        }
    }
}
