package com.unity3d.ads.android.cache;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.nearby.connection.Connections;
import com.mopub.mobileads.CustomEventInterstitialAdapter;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.data.UnityAdsDevice;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;

class UnityAdsCacheThreadHandler extends Handler {
    private String _currentDownload;
    private boolean _stopped;

    UnityAdsCacheThreadHandler() {
        this._currentDownload = null;
        this._stopped = false;
    }

    public void handleMessage(Message message) {
        Bundle data = message.getData();
        downloadFile(data.getString(ShareConstants.FEED_SOURCE_PARAM), data.getString("target"));
    }

    public void setStoppedStatus(boolean z) {
        this._stopped = z;
    }

    public String getCurrentDownload() {
        return this._currentDownload;
    }

    private void downloadFile(String str, String str2) {
        if (!this._stopped && str != null && str2 != null) {
            try {
                UnityAdsDeviceLog.debug("Unity Ads cache: start downloading " + str + " to " + str2);
                this._currentDownload = str2;
                long elapsedRealtime = SystemClock.elapsedRealtime();
                URL url = new URL(str);
                if (UnityAdsDevice.isActiveNetworkConnected()) {
                    File file = new File(str2);
                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
                    URLConnection openConnection = url.openConnection();
                    openConnection.setConnectTimeout(CustomEventInterstitialAdapter.DEFAULT_INTERSTITIAL_TIMEOUT_DELAY);
                    openConnection.setReadTimeout(CustomEventInterstitialAdapter.DEFAULT_INTERSTITIAL_TIMEOUT_DELAY);
                    openConnection.connect();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(openConnection.getInputStream());
                    byte[] bArr = new byte[Connections.MAX_RELIABLE_MESSAGE_LEN];
                    long j = 0;
                    while (!this._stopped) {
                        int read = bufferedInputStream.read(bArr);
                        if (read == -1) {
                            break;
                        }
                        j += (long) read;
                        bufferedOutputStream.write(bArr, 0, read);
                    }
                    bufferedOutputStream.flush();
                    bufferedOutputStream.close();
                    bufferedInputStream.close();
                    this._currentDownload = null;
                    if (this._stopped) {
                        UnityAdsDeviceLog.debug("Unity Ads cache: downloading of " + str + " stopped");
                        if (!file.delete()) {
                            UnityAdsDeviceLog.debug("Couldn't delete file: " + file.getName());
                            return;
                        }
                        return;
                    }
                    elapsedRealtime = SystemClock.elapsedRealtime() - elapsedRealtime;
                    UnityAdsDeviceLog.debug("Unity Ads cache: File " + str2 + " of " + j + " bytes downloaded in " + elapsedRealtime + "ms");
                    if (elapsedRealtime > 0 && j > 0) {
                        UnityAdsProperties.CACHING_SPEED = j / elapsedRealtime;
                        return;
                    }
                    return;
                }
                UnityAdsDeviceLog.debug("Unity Ads cache: download cancelled, no internet connection available");
            } catch (Exception e) {
                this._currentDownload = null;
                UnityAdsDeviceLog.debug("Unity Ads cache: Exception when downloading " + str + " to " + str2 + ": " + e.getMessage());
            }
        }
    }
}
