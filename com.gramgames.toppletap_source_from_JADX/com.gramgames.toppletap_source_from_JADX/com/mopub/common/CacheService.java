package com.mopub.common;

import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.mopub.common.DiskLruCache.Editor;
import com.mopub.common.DiskLruCache.Snapshot;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class CacheService {
    private static final int APP_VERSION = 1;
    private static final int DISK_CACHE_INDEX = 0;
    static final String UNIQUE_CACHE_NAME = "mopub-cache";
    private static final int VALUE_COUNT = 1;
    private static DiskLruCache sDiskLruCache;

    public interface DiskLruCacheGetListener {
        void onComplete(String str, byte[] bArr);
    }

    private static class DiskLruCacheGetTask extends AsyncTask<Void, Void, byte[]> {
        private final DiskLruCacheGetListener mDiskLruCacheGetListener;
        private final String mKey;

        DiskLruCacheGetTask(String key, DiskLruCacheGetListener diskLruCacheGetListener) {
            this.mDiskLruCacheGetListener = diskLruCacheGetListener;
            this.mKey = key;
        }

        protected byte[] doInBackground(Void... voids) {
            return CacheService.getFromDiskCache(this.mKey);
        }

        protected void onPostExecute(byte[] bytes) {
            if (isCancelled()) {
                onCancelled();
            } else if (this.mDiskLruCacheGetListener != null) {
                this.mDiskLruCacheGetListener.onComplete(this.mKey, bytes);
            }
        }

        protected void onCancelled() {
            if (this.mDiskLruCacheGetListener != null) {
                this.mDiskLruCacheGetListener.onComplete(this.mKey, null);
            }
        }
    }

    private static class DiskLruCachePutTask extends AsyncTask<Void, Void, Void> {
        private final byte[] mContent;
        private final String mKey;

        DiskLruCachePutTask(String key, byte[] content) {
            this.mKey = key;
            this.mContent = content;
        }

        protected Void doInBackground(Void... voids) {
            CacheService.putToDiskCache(this.mKey, this.mContent);
            return null;
        }
    }

    public static boolean initializeDiskCache(Context context) {
        if (context == null) {
            return false;
        }
        if (sDiskLruCache == null) {
            File cacheDirectory = getDiskCacheDirectory(context);
            if (cacheDirectory == null) {
                return false;
            }
            try {
                sDiskLruCache = DiskLruCache.open(cacheDirectory, VALUE_COUNT, VALUE_COUNT, DeviceUtils.diskCacheSizeBytes(cacheDirectory));
            } catch (IOException e) {
                MoPubLog.d("Unable to create DiskLruCache", e);
                return false;
            }
        }
        return true;
    }

    public static void initialize(Context context) {
        initializeDiskCache(context);
    }

    public static String createValidDiskCacheKey(String key) {
        return Utils.sha1(key);
    }

    @Nullable
    public static File getDiskCacheDirectory(@NonNull Context context) {
        File cacheDir = context.getCacheDir();
        if (cacheDir == null) {
            return null;
        }
        return new File(cacheDir.getPath() + File.separator + UNIQUE_CACHE_NAME);
    }

    public static boolean containsKeyDiskCache(String key) {
        if (sDiskLruCache == null) {
            return false;
        }
        try {
            if (sDiskLruCache.get(createValidDiskCacheKey(key)) != null) {
                return true;
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFilePathDiskCache(String key) {
        if (sDiskLruCache == null) {
            return null;
        }
        return sDiskLruCache.getDirectory() + File.separator + createValidDiskCacheKey(key) + "." + DISK_CACHE_INDEX;
    }

    public static byte[] getFromDiskCache(String key) {
        if (sDiskLruCache == null) {
            return null;
        }
        byte[] bytes = null;
        Snapshot snapshot = null;
        BufferedInputStream buffIn;
        try {
            snapshot = sDiskLruCache.get(createValidDiskCacheKey(key));
            if (snapshot == null) {
                if (snapshot != null) {
                    snapshot.close();
                }
                return null;
            }
            InputStream in = snapshot.getInputStream(DISK_CACHE_INDEX);
            if (in != null) {
                bytes = new byte[((int) snapshot.getLength(DISK_CACHE_INDEX))];
                buffIn = new BufferedInputStream(in);
                Streams.readStream(buffIn, bytes);
                Streams.closeStream(buffIn);
            }
            if (snapshot == null) {
                return bytes;
            }
            snapshot.close();
            return bytes;
        } catch (Exception e) {
            try {
                MoPubLog.d("Unable to get from DiskLruCache", e);
                if (snapshot == null) {
                    return bytes;
                }
                snapshot.close();
                return bytes;
            } catch (Throwable th) {
                if (snapshot != null) {
                    snapshot.close();
                }
            }
        } catch (Throwable th2) {
            Streams.closeStream(buffIn);
        }
    }

    public static void getFromDiskCacheAsync(String key, DiskLruCacheGetListener diskLruCacheGetListener) {
        new DiskLruCacheGetTask(key, diskLruCacheGetListener).execute(new Void[DISK_CACHE_INDEX]);
    }

    public static boolean putToDiskCache(String key, byte[] content) {
        return putToDiskCache(key, new ByteArrayInputStream(content));
    }

    public static boolean putToDiskCache(String key, InputStream content) {
        if (sDiskLruCache == null) {
            return false;
        }
        Editor editor = null;
        try {
            editor = sDiskLruCache.edit(createValidDiskCacheKey(key));
            if (editor == null) {
                return false;
            }
            OutputStream outputStream = new BufferedOutputStream(editor.newOutputStream(DISK_CACHE_INDEX));
            Streams.copyContent(content, outputStream);
            outputStream.flush();
            outputStream.close();
            sDiskLruCache.flush();
            editor.commit();
            return true;
        } catch (Exception e) {
            MoPubLog.d("Unable to put to DiskLruCache", e);
            if (editor == null) {
                return false;
            }
            try {
                editor.abort();
                return false;
            } catch (IOException e2) {
                return false;
            }
        }
    }

    public static void putToDiskCacheAsync(String key, byte[] content) {
        new DiskLruCachePutTask(key, content).execute(new Void[DISK_CACHE_INDEX]);
    }

    @Deprecated
    @VisibleForTesting
    public static void clearAndNullCaches() {
        if (sDiskLruCache != null) {
            try {
                sDiskLruCache.delete();
                sDiskLruCache = null;
            } catch (IOException e) {
                sDiskLruCache = null;
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    public static DiskLruCache getDiskLruCache() {
        return sDiskLruCache;
    }
}
