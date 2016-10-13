package com.unity3d.ads.android.cache;

import android.annotation.TargetApi;
import android.os.Build.VERSION;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.campaign.UnityAdsCampaign;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

@TargetApi(9)
public class UnityAdsCache {
    private static File _cacheDirectory;

    final class 1 implements FilenameFilter {
        1() {
        }

        public final boolean accept(File file, String str) {
            boolean startsWith = str.startsWith(UnityAdsConstants.UNITY_ADS_LOCALFILE_PREFIX);
            UnityAdsDeviceLog.debug("Unity Ads cache: filtering result for file: " + str + ", " + startsWith);
            return startsWith;
        }
    }

    static {
        _cacheDirectory = null;
    }

    public static void initialize(ArrayList arrayList) {
        if (arrayList != null && arrayList.size() != 0) {
            UnityAdsDeviceLog.debug("Unity Ads cache: initializing cache with " + arrayList.size() + " campaigns");
            stopAllDownloads();
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            Iterator it = arrayList.iterator();
            Object obj = 1;
            while (it.hasNext()) {
                UnityAdsCampaign unityAdsCampaign = (UnityAdsCampaign) it.next();
                if (unityAdsCampaign.forceCacheVideo().booleanValue() || (unityAdsCampaign.allowCacheVideo().booleanValue() && r1 != null)) {
                    String videoFilename = unityAdsCampaign.getVideoFilename();
                    if (isFileCached(videoFilename, unityAdsCampaign.getVideoFileExpectedSize())) {
                        UnityAdsDeviceLog.debug("Unity Ads cache: not downloading " + videoFilename + ", already in cache");
                    } else {
                        UnityAdsDeviceLog.debug("Unity Ads cache: queuing " + videoFilename + " for download");
                        hashMap.put(unityAdsCampaign.getVideoUrl(), videoFilename);
                    }
                }
                hashMap2.put(unityAdsCampaign.getVideoFilename(), Long.valueOf(unityAdsCampaign.getVideoFileExpectedSize()));
                obj = null;
            }
            initializeCacheDirectory(hashMap2);
            for (Entry entry : hashMap.entrySet()) {
                UnityAdsCacheThread.download((String) entry.getKey(), getFullFilename((String) entry.getValue()));
            }
        }
    }

    public static void cacheCampaign(UnityAdsCampaign unityAdsCampaign) {
        String videoFilename = unityAdsCampaign.getVideoFilename();
        if (!isFileCached(videoFilename, unityAdsCampaign.getVideoFileExpectedSize())) {
            String currentDownload = UnityAdsCacheThread.getCurrentDownload();
            if (currentDownload == null || !currentDownload.equals(getFullFilename(videoFilename))) {
                UnityAdsCacheThread.download(unityAdsCampaign.getVideoUrl(), getFullFilename(videoFilename));
            }
        }
    }

    public static boolean isCampaignCached(UnityAdsCampaign unityAdsCampaign) {
        return isFileCached(unityAdsCampaign.getVideoFilename(), unityAdsCampaign.getVideoFileExpectedSize());
    }

    public static void stopAllDownloads() {
        UnityAdsCacheThread.stopAllDownloads();
    }

    private static void initializeCacheDirectory(HashMap hashMap) {
        _cacheDirectory = new File(UnityAdsProperties.APPLICATION_CONTEXT.getFilesDir().getPath());
        if (VERSION.SDK_INT > 18) {
            File externalCacheDir = UnityAdsProperties.APPLICATION_CONTEXT.getExternalCacheDir();
            if (externalCacheDir != null) {
                File file = new File(externalCacheDir.getAbsolutePath(), UnityAdsConstants.CACHE_DIR_NAME);
                _cacheDirectory = file;
                if (file.mkdirs()) {
                    UnityAdsDeviceLog.debug("Successfully created cache");
                }
            }
        }
        UnityAdsDeviceLog.debug("Unity Ads cache: using " + _cacheDirectory.getAbsolutePath() + " as cache");
        if (_cacheDirectory.isDirectory()) {
            hashMap.put(UnityAdsConstants.PENDING_REQUESTS_FILENAME, Long.valueOf(-1));
            File[] listFiles;
            if (_cacheDirectory.getAbsolutePath().endsWith(UnityAdsConstants.CACHE_DIR_NAME)) {
                UnityAdsDeviceLog.debug("Unity Ads cache: checking cache directory " + _cacheDirectory.getAbsolutePath());
                listFiles = _cacheDirectory.listFiles();
            } else {
                UnityAdsDeviceLog.debug("Unity Ads cache: checking app directory for Unity Ads cached files");
                listFiles = _cacheDirectory.listFiles(new 1());
            }
            for (File file2 : r1) {
                String name = file2.getName();
                if (hashMap.containsKey(name)) {
                    long longValue = ((Long) hashMap.get(name)).longValue();
                    if (longValue != -1) {
                        if (file2.length() != longValue) {
                            UnityAdsDeviceLog.debug("Unity Ads cache: " + name + " file size mismatch, deleting from cache");
                            if (!file2.delete()) {
                                UnityAdsDeviceLog.debug("Unity Ads cache: Couldn't delete file: " + file2.getAbsolutePath());
                            }
                        } else {
                            UnityAdsDeviceLog.debug("Unity Ads cache: " + name + " found, keeping");
                        }
                    }
                } else {
                    UnityAdsDeviceLog.debug("Unity Ads cache: " + name + " not found in ad plan, deleting from cache");
                    if (!file2.delete()) {
                        UnityAdsDeviceLog.debug("Unity Ads cache: Couldn't delete file: " + file2.getAbsolutePath());
                    }
                }
            }
            return;
        }
        UnityAdsDeviceLog.error("Unity Ads cache: Creating cache dir failed");
    }

    public static String getCacheDirectory() {
        return _cacheDirectory != null ? _cacheDirectory.getAbsolutePath() : null;
    }

    private static String getFullFilename(String str) {
        return getCacheDirectory() + "/" + str;
    }

    private static boolean isFileCached(String str, long j) {
        File file = new File(getCacheDirectory() + "/" + str);
        if (file.exists() && (j == -1 || file.length() == j)) {
            return true;
        }
        return false;
    }
}
