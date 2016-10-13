package com.unity3d.ads.android.cache;

import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.UnityAdsDeviceLog;

class UnityAdsCacheThread extends Thread {
    private static final int MSG_DOWNLOAD = 1;
    private static UnityAdsCacheThreadHandler _handler;
    private static boolean _ready;
    private static final Object _readyLock;

    UnityAdsCacheThread() {
    }

    static {
        _handler = null;
        _ready = false;
        _readyLock = new Object();
    }

    private static void init() {
        UnityAdsCacheThread unityAdsCacheThread = new UnityAdsCacheThread();
        unityAdsCacheThread.setName("UnityAdsCacheThread");
        unityAdsCacheThread.start();
        while (!_ready) {
            try {
                synchronized (_readyLock) {
                    _readyLock.wait();
                }
            } catch (InterruptedException e) {
                UnityAdsDeviceLog.debug("Couldn't synchronize thread");
            }
        }
    }

    public void run() {
        Looper.prepare();
        _handler = new UnityAdsCacheThreadHandler();
        _ready = true;
        synchronized (_readyLock) {
            _readyLock.notify();
        }
        Looper.loop();
    }

    public static synchronized void download(String str, String str2) {
        synchronized (UnityAdsCacheThread.class) {
            if (!_ready) {
                init();
            }
            Bundle bundle = new Bundle();
            bundle.putString(ShareConstants.FEED_SOURCE_PARAM, str);
            bundle.putString("target", str2);
            Message message = new Message();
            message.what = MSG_DOWNLOAD;
            message.setData(bundle);
            _handler.setStoppedStatus(false);
            _handler.sendMessage(message);
        }
    }

    public static String getCurrentDownload() {
        if (_ready) {
            return _handler.getCurrentDownload();
        }
        return null;
    }

    public static void stopAllDownloads() {
        if (_ready) {
            _handler.removeMessages(MSG_DOWNLOAD);
            _handler.setStoppedStatus(true);
        }
    }
}
