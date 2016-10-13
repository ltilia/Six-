package com.adjust.sdk;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.util.DisplayMetrics;
import com.unity3d.ads.android.R;
import gs.gram.mopub.BuildConfig;
import java.util.Locale;
import java.util.Map;
import org.json.simple.parser.Yytoken;

class DeviceInfo {
    String androidId;
    String apiLevel;
    String appVersion;
    String clientSdk;
    String country;
    String deviceManufacturer;
    String deviceName;
    String deviceType;
    String displayHeight;
    String displayWidth;
    String fbAttributionId;
    String language;
    String macSha1;
    String macShortMd5;
    String osName;
    String osVersion;
    String packageName;
    Map<String, String> pluginKeys;
    String screenDensity;
    String screenFormat;
    String screenSize;

    DeviceInfo(Context context, String sdkPrefix) {
        Resources resources = context.getResources();
        DisplayMetrics displayMetrics = resources.getDisplayMetrics();
        Configuration configuration = resources.getConfiguration();
        Locale locale = configuration.locale;
        int screenLayout = configuration.screenLayout;
        boolean isGooglePlayServicesAvailable = Util.getPlayAdId(context) != null;
        String macAddress = getMacAddress(context, isGooglePlayServicesAvailable);
        this.packageName = getPackageName(context);
        this.appVersion = getAppVersion(context);
        this.deviceType = getDeviceType(screenLayout);
        this.deviceName = getDeviceName();
        this.deviceManufacturer = getDeviceManufacturer();
        this.osName = getOsName();
        this.osVersion = getOsVersion();
        this.apiLevel = getApiLevel();
        this.language = getLanguage(locale);
        this.country = getCountry(locale);
        this.screenSize = getScreenSize(screenLayout);
        this.screenFormat = getScreenFormat(screenLayout);
        this.screenDensity = getScreenDensity(displayMetrics);
        this.displayWidth = getDisplayWidth(displayMetrics);
        this.displayHeight = getDisplayHeight(displayMetrics);
        this.clientSdk = getClientSdk(sdkPrefix);
        this.androidId = getAndroidId(context, isGooglePlayServicesAvailable);
        this.fbAttributionId = getFacebookAttributionId(context);
        this.pluginKeys = Util.getPluginKeys(context);
        this.macSha1 = getMacSha1(macAddress);
        this.macShortMd5 = getMacShortMd5(macAddress);
    }

    private String getMacAddress(Context context, boolean isGooglePlayServicesAvailable) {
        if (isGooglePlayServicesAvailable) {
            return null;
        }
        if (!Util.checkPermission(context, "android.permission.ACCESS_WIFI_STATE")) {
            AdjustFactory.getLogger().warn("Missing permission: ACCESS_WIFI_STATE", new Object[0]);
        }
        return Util.getMacAddress(context);
    }

    private String getPackageName(Context context) {
        return context.getPackageName();
    }

    private String getAppVersion(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (NameNotFoundException e) {
            return null;
        }
    }

    private String getDeviceType(int screenLayout) {
        switch (screenLayout & 15) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "phone";
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return "tablet";
            default:
                return null;
        }
    }

    private String getDeviceName() {
        return Build.MODEL;
    }

    private String getDeviceManufacturer() {
        return Build.MANUFACTURER;
    }

    private String getOsName() {
        return "android";
    }

    private String getOsVersion() {
        return VERSION.RELEASE;
    }

    private String getApiLevel() {
        return BuildConfig.FLAVOR + VERSION.SDK_INT;
    }

    private String getLanguage(Locale locale) {
        return locale.getLanguage();
    }

    private String getCountry(Locale locale) {
        return locale.getCountry();
    }

    private String getScreenSize(int screenLayout) {
        switch (screenLayout & 15) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return Constants.SMALL;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return Constants.NORMAL;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return Constants.LARGE;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return Constants.XLARGE;
            default:
                return null;
        }
    }

    private String getScreenFormat(int screenLayout) {
        switch (screenLayout & 48) {
            case R.styleable.Toolbar_titleMarginBottom /*16*/:
                return Constants.NORMAL;
            case R.styleable.Theme_actionModeCutDrawable /*32*/:
                return Constants.LONG;
            default:
                return null;
        }
    }

    private String getScreenDensity(DisplayMetrics displayMetrics) {
        int density = displayMetrics.densityDpi;
        if (density == 0) {
            return null;
        }
        if (density < 140) {
            return Constants.LOW;
        }
        if (density > 200) {
            return Constants.HIGH;
        }
        return Constants.MEDIUM;
    }

    private String getDisplayWidth(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.widthPixels);
    }

    private String getDisplayHeight(DisplayMetrics displayMetrics) {
        return String.valueOf(displayMetrics.heightPixels);
    }

    private String getClientSdk(String sdkPrefix) {
        if (sdkPrefix == null) {
            return Constants.CLIENT_SDK;
        }
        return String.format(Locale.US, "%s@%s", new Object[]{sdkPrefix, Constants.CLIENT_SDK});
    }

    private String getMacSha1(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        return Util.sha1(macAddress);
    }

    private String getMacShortMd5(String macAddress) {
        if (macAddress == null) {
            return null;
        }
        return Util.md5(macAddress.replaceAll(":", BuildConfig.FLAVOR));
    }

    private String getAndroidId(Context context, boolean isGooglePlayServicesAvailable) {
        if (isGooglePlayServicesAvailable) {
            return null;
        }
        return Util.getAndroidId(context);
    }

    private String getFacebookAttributionId(Context context) {
        try {
            String columnName = "aid";
            Cursor cursor = context.getContentResolver().query(Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider"), new String[]{"aid"}, null, null, null);
            if (cursor == null) {
                return null;
            }
            if (cursor.moveToFirst()) {
                String attributionId = cursor.getString(cursor.getColumnIndex("aid"));
                cursor.close();
                return attributionId;
            }
            cursor.close();
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
