package com.mopub.network;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.LruCache;
import android.webkit.WebView;
import com.mopub.common.ClientMetadata;
import com.mopub.common.Constants;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.util.DeviceUtils;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.mopub.volley.Network;
import com.mopub.volley.toolbox.BasicNetwork;
import com.mopub.volley.toolbox.DiskBasedCache;
import com.mopub.volley.toolbox.ImageLoader;
import com.mopub.volley.toolbox.ImageLoader.ImageCache;
import java.io.File;

public class Networking {
    @VisibleForTesting
    static final String CACHE_DIRECTORY_NAME = "mopub-volley-cache";
    private static final String DEFAULT_USER_AGENT;
    private static volatile MaxWidthImageLoader sMaxWidthImageLoader;
    private static volatile MoPubRequestQueue sRequestQueue;
    private static boolean sUseHttps;
    private static volatile String sUserAgent;

    static class 1 extends LruCache<String, Bitmap> {
        1(int x0) {
            super(x0);
        }

        protected int sizeOf(String key, Bitmap value) {
            if (value != null) {
                return value.getRowBytes() * value.getHeight();
            }
            return super.sizeOf(key, value);
        }
    }

    static class 2 implements ImageCache {
        final /* synthetic */ LruCache val$imageCache;

        2(LruCache lruCache) {
            this.val$imageCache = lruCache;
        }

        public Bitmap getBitmap(String key) {
            return (Bitmap) this.val$imageCache.get(key);
        }

        public void putBitmap(String key, Bitmap bitmap) {
            this.val$imageCache.put(key, bitmap);
        }
    }

    static {
        DEFAULT_USER_AGENT = System.getProperty("http.agent");
        sUseHttps = false;
    }

    @Nullable
    public static MoPubRequestQueue getRequestQueue() {
        return sRequestQueue;
    }

    @NonNull
    public static MoPubRequestQueue getRequestQueue(@NonNull Context context) {
        MoPubRequestQueue requestQueue = sRequestQueue;
        if (requestQueue == null) {
            synchronized (Networking.class) {
                try {
                    requestQueue = sRequestQueue;
                    if (requestQueue == null) {
                        Network network = new BasicNetwork(new RequestQueueHttpStack(getUserAgent(context.getApplicationContext()), new PlayServicesUrlRewriter(ClientMetadata.getInstance(context).getDeviceId(), context), CustomSSLSocketFactory.getDefault(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY)));
                        File volleyCacheDir = new File(context.getCacheDir().getPath() + File.separator + CACHE_DIRECTORY_NAME);
                        MoPubRequestQueue requestQueue2 = new MoPubRequestQueue(new DiskBasedCache(volleyCacheDir, (int) DeviceUtils.diskCacheSizeBytes(volleyCacheDir, 10485760)), network);
                        try {
                            sRequestQueue = requestQueue2;
                            requestQueue2.start();
                            requestQueue = requestQueue2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            requestQueue = requestQueue2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return requestQueue;
    }

    @NonNull
    public static ImageLoader getImageLoader(@NonNull Context context) {
        MaxWidthImageLoader imageLoader = sMaxWidthImageLoader;
        if (imageLoader == null) {
            synchronized (Networking.class) {
                try {
                    imageLoader = sMaxWidthImageLoader;
                    if (imageLoader == null) {
                        MaxWidthImageLoader imageLoader2 = new MaxWidthImageLoader(getRequestQueue(context), context, new 2(new 1(DeviceUtils.memoryCacheSizeBytes(context))));
                        try {
                            sMaxWidthImageLoader = imageLoader2;
                            imageLoader = imageLoader2;
                        } catch (Throwable th) {
                            Throwable th2 = th;
                            imageLoader = imageLoader2;
                            throw th2;
                        }
                    }
                } catch (Throwable th3) {
                    th2 = th3;
                    throw th2;
                }
            }
        }
        return imageLoader;
    }

    @NonNull
    public static String getUserAgent(@NonNull Context context) {
        Preconditions.checkNotNull(context);
        String userAgent = sUserAgent;
        if (userAgent == null) {
            synchronized (Networking.class) {
                userAgent = sUserAgent;
                if (userAgent == null) {
                    if (Looper.myLooper() == Looper.getMainLooper()) {
                        try {
                            userAgent = new WebView(context).getSettings().getUserAgentString();
                        } catch (Exception e) {
                            userAgent = DEFAULT_USER_AGENT;
                        }
                    } else {
                        userAgent = DEFAULT_USER_AGENT;
                    }
                    sUserAgent = userAgent;
                }
            }
        }
        return userAgent;
    }

    @NonNull
    public static String getCachedUserAgent() {
        String userAgent = sUserAgent;
        if (userAgent == null) {
            return DEFAULT_USER_AGENT;
        }
        return userAgent;
    }

    @VisibleForTesting
    public static synchronized void clearForTesting() {
        synchronized (Networking.class) {
            sRequestQueue = null;
            sMaxWidthImageLoader = null;
            sUserAgent = null;
        }
    }

    @VisibleForTesting
    public static synchronized void setRequestQueueForTesting(MoPubRequestQueue queue) {
        synchronized (Networking.class) {
            sRequestQueue = queue;
        }
    }

    @VisibleForTesting
    public static synchronized void setImageLoaderForTesting(MaxWidthImageLoader imageLoader) {
        synchronized (Networking.class) {
            sMaxWidthImageLoader = imageLoader;
        }
    }

    @VisibleForTesting
    public static synchronized void setUserAgentForTesting(String userAgent) {
        synchronized (Networking.class) {
            sUserAgent = userAgent;
        }
    }

    public static void useHttps(boolean useHttps) {
        sUseHttps = useHttps;
    }

    public static boolean useHttps() {
        return sUseHttps;
    }

    public static String getScheme() {
        return useHttps() ? Constants.HTTPS : Constants.HTTP;
    }

    public static String getBaseUrlScheme() {
        return Constants.HTTP;
    }
}
