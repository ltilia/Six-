package com.area730.localnotif;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.support.v4.app.NotificationCompat.Builder;

public class NotificationReciever extends BroadcastReceiver {
    public static final String ALERT_ONCE_KEY = "alertOnce";
    public static final String AUTOCANCEL_KEY = "autocancel";
    public static final String BIG_ICON_KEY = "big_icon";
    public static final String BODY_KEY = "body";
    public static final String COLOR_KEY = "color";
    public static final String DEFAULTS_KEY = "defaults";
    public static final String DEFAULT_ICON = "app_icon";
    public static final String DRAWABLE_TYPE = "drawable";
    public static final String ID_KEY = "id";
    public static final String NUMBER_KEY = "numberKey";
    public static final String RAW_TYPE = "raw";
    public static final String SMALL_ICON_KEY = "small_icon";
    public static final String SOUND_KEY = "soundKey";
    public static final String TICKER_KEY = "ticker";
    public static final String TITLE_KEY = "title";
    public static final String UNITY_CLASS = "com.unity3d.player.UnityPlayerNativeActivity";
    public static final String VIBRO_KEY = "vibro";
    public static final String WHEN_KEY = "when";
    private final String DEFAULT_BODY;
    private final String DEFAULT_TITLE;

    public NotificationReciever() {
        this.DEFAULT_TITLE = "Default title";
        this.DEFAULT_BODY = "Default body";
    }

    public void onReceive(Context context, Intent intent) {
        Logger.LogInfo("NotificationReciever.onReceive(), package: " + context.getPackageName());
        String title = intent.getStringExtra(TITLE_KEY);
        String body = intent.getStringExtra(BODY_KEY);
        String smallIcon = intent.getStringExtra(SMALL_ICON_KEY);
        String bigIcon = intent.getStringExtra(BIG_ICON_KEY);
        String ticker = intent.getStringExtra(TICKER_KEY);
        int id = intent.getIntExtra(ID_KEY, 1);
        boolean autocancel = intent.getBooleanExtra(AUTOCANCEL_KEY, false);
        String sound = intent.getStringExtra(SOUND_KEY);
        long when = intent.getLongExtra(WHEN_KEY, 0);
        long[] vibroPattern = intent.getLongArrayExtra(VIBRO_KEY);
        int defaults = intent.getIntExtra(DEFAULTS_KEY, 0);
        int number = intent.getIntExtra(NUMBER_KEY, 0);
        boolean alertOnce = intent.getBooleanExtra(ALERT_ONCE_KEY, false);
        String hexColor = intent.getStringExtra(COLOR_KEY);
        Class<?> unityClassActivity = null;
        try {
            unityClassActivity = Class.forName(UNITY_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Resources res = context.getResources();
        Builder mBuilder = new Builder(context);
        PendingIntent resultPendingIntent = PendingIntent.getActivity(context, 0, new Intent(context, unityClassActivity), 0);
        int defaultIconID = res.getIdentifier(DEFAULT_ICON, DRAWABLE_TYPE, context.getPackageName());
        if (nullOrEmpty(title)) {
            title = "Default title";
            Logger.LogWarning("Notification title is null or empty, Using default instead");
        }
        if (nullOrEmpty(body)) {
            body = "Default body";
            Logger.LogWarning("Notification body is null or empty, Using default instead");
        }
        if (nullOrEmpty(smallIcon)) {
            smallIcon = DEFAULT_ICON;
            Logger.LogWarning("Notification small icon name is null or empty, Using default instead");
        }
        int smallIconRes = res.getIdentifier(smallIcon, DRAWABLE_TYPE, context.getPackageName());
        if (smallIconRes == 0) {
            smallIconRes = defaultIconID;
            Logger.LogWarning("Small icon (" + smallIcon + ") can't be found in " + DRAWABLE_TYPE + " folders. Using default icon instead");
        }
        mBuilder.setContentTitle(title).setContentText(body).setSmallIcon(smallIconRes).setAutoCancel(autocancel).setOnlyAlertOnce(alertOnce).setContentIntent(resultPendingIntent);
        if (!nullOrEmpty(ticker)) {
            mBuilder.setTicker(ticker);
        }
        if (vibroPattern != null) {
            mBuilder.setVibrate(vibroPattern);
        }
        if (when != 0) {
            mBuilder.setWhen(when);
        }
        if (defaults != 0) {
            mBuilder.setDefaults(defaults);
        }
        if (number != 0) {
            mBuilder.setNumber(number);
        }
        if (!nullOrEmpty(bigIcon)) {
            int bigIconRes = res.getIdentifier(bigIcon, DRAWABLE_TYPE, context.getPackageName());
            if (bigIconRes == 0) {
                Logger.LogError("Large icon (" + bigIcon + ") can't be found in " + DRAWABLE_TYPE + " folders.");
            } else {
                mBuilder.setLargeIcon(BitmapFactory.decodeResource(res, bigIconRes));
            }
        }
        if (!nullOrEmpty(sound)) {
            int soundId = res.getIdentifier(sound, RAW_TYPE, context.getPackageName());
            if (soundId == 0) {
                Logger.LogError("Sound resource (" + sound + ") can't be found in " + RAW_TYPE + " folder");
            } else {
                mBuilder.setSound(Uri.parse("android.resource://" + context.getPackageName() + "/" + soundId));
            }
        }
        if (!nullOrEmpty(hexColor)) {
            try {
                mBuilder.setColor(Color.parseColor(hexColor));
            } catch (IllegalArgumentException e2) {
                Logger.LogError("Wrong color format");
            }
        }
        ((NotificationManager) context.getSystemService("notification")).notify(id, mBuilder.build());
    }

    private boolean nullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }
}
