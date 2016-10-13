package com.area730.localnotif;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.C;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import com.unity3d.player.UnityPlayer;

public class NotificationHandler {
    private static Handler UIHandler;

    static class 1 implements Runnable {
        final /* synthetic */ String val$toastText;

        1(String str) {
            this.val$toastText = str;
        }

        public void run() {
            Toast.makeText(UnityPlayer.currentActivity, this.val$toastText, 0).show();
        }
    }

    static {
        UIHandler = new Handler(Looper.getMainLooper());
    }

    public static float getVersion() {
        return 1.3f;
    }

    public static void showToast(String toastText) {
        Logger.LogInfo("NotificationHandler.showToast() call");
        UIHandler.post(new 1(toastText));
    }

    public static void scheduleNotification(long delayMillis, int id, String title, String body, String ticker, String smallIcon, String bigIcon, int defaults, boolean autocancel, String sound, long[] vibroPattern, long when, boolean isRepeating, long interval, int number, boolean alertOnce, String hexColor) {
        Activity currentActivity = UnityPlayer.currentActivity;
        AlarmManager am = (AlarmManager) currentActivity.getSystemService(NotificationCompatApi21.CATEGORY_ALARM);
        Intent intent = new Intent(currentActivity, NotificationReciever.class);
        intent.putExtra(ShareConstants.WEB_DIALOG_PARAM_TITLE, title);
        intent.putExtra(UnityAdsConstants.UNITY_ADS_FAILED_URL_BODY_KEY, body);
        intent.putExtra(NotificationReciever.TICKER_KEY, ticker);
        intent.putExtra(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, id);
        intent.putExtra(NotificationReciever.SMALL_ICON_KEY, smallIcon);
        intent.putExtra(NotificationReciever.BIG_ICON_KEY, bigIcon);
        intent.putExtra(NotificationReciever.DEFAULTS_KEY, defaults);
        intent.putExtra(NotificationReciever.AUTOCANCEL_KEY, autocancel);
        intent.putExtra(NotificationReciever.SOUND_KEY, sound);
        intent.putExtra(NotificationReciever.VIBRO_KEY, vibroPattern);
        intent.putExtra(NotificationReciever.WHEN_KEY, when);
        intent.putExtra(NotificationReciever.NUMBER_KEY, number);
        intent.putExtra(NotificationReciever.ALERT_ONCE_KEY, alertOnce);
        intent.putExtra(TtmlNode.ATTR_TTS_COLOR, hexColor);
        if (!isRepeating) {
            am.set(0, System.currentTimeMillis() + delayMillis, PendingIntent.getBroadcast(currentActivity, id, intent, C.SAMPLE_FLAG_DECODE_ONLY));
            Logger.LogInfo("One time notification scheduled");
        } else if (interval != 0) {
            am.setRepeating(0, System.currentTimeMillis() + delayMillis, interval, PendingIntent.getBroadcast(currentActivity, id, intent, C.SAMPLE_FLAG_DECODE_ONLY));
            Logger.LogInfo("Repeating notification scheduled");
        } else {
            Logger.LogError("Notification not scheduled. You must specify the interval for repeating notification ( > 0)");
        }
    }

    public static void cancelNotifications(int id) {
        Activity currentActivity = UnityPlayer.currentActivity;
        ((AlarmManager) currentActivity.getSystemService(NotificationCompatApi21.CATEGORY_ALARM)).cancel(PendingIntent.getBroadcast(currentActivity, id, new Intent(currentActivity, NotificationReciever.class), C.SAMPLE_FLAG_DECODE_ONLY));
        Logger.LogInfo("Notification with id " + id + " cancelled");
    }

    public static void clear(int id) {
        ((NotificationManager) UnityPlayer.currentActivity.getSystemService("notification")).cancel(id);
        Logger.LogInfo("Notification with id " + id + " cleared");
    }

    public static void clearAll() {
        ((NotificationManager) UnityPlayer.currentActivity.getSystemService("notification")).cancelAll();
        Logger.LogInfo("All notifications cleared");
    }
}
