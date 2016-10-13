package com.chartboost.sdk.Libraries;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.Display;
import android.view.WindowManager;
import com.chartboost.sdk.Chartboost.CBFramework;
import com.chartboost.sdk.Model.a.b;
import com.chartboost.sdk.c;
import com.google.android.exoplayer.C;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import org.json.simple.parser.Yytoken;

public final class CBUtility {
    private static Handler a;

    private CBUtility() {
    }

    public static SharedPreferences a() {
        if (c.y() != null) {
            return c.y().getSharedPreferences("cbPrefs", 0);
        }
        CBLogging.b("CBUtility", "The context must be set through the Chartboost method onCreate() before modifying or accessing preferences.");
        return null;
    }

    public static boolean b() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public static String a(Map<String, Object> map) {
        if (map == null) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!map.keySet().isEmpty()) {
            stringBuilder.append("?");
        }
        for (String str : map.keySet()) {
            String str2;
            if (stringBuilder.length() > 1) {
                stringBuilder.append("&");
            }
            String obj = map.get(str2).toString();
            if (str2 != null) {
                try {
                    str2 = URLEncoder.encode(str2, C.UTF8_NAME);
                } catch (Throwable e) {
                    CBLogging.b("CBUtility", "This method requires UTF-8 encoding support", e);
                    return null;
                }
            }
            str2 = BuildConfig.FLAVOR;
            stringBuilder.append(str2);
            stringBuilder.append("=");
            stringBuilder.append(obj != null ? URLEncoder.encode(obj, C.UTF8_NAME) : BuildConfig.FLAVOR);
        }
        return stringBuilder.toString();
    }

    public static float a(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    public static int a(int i, Context context) {
        return Math.round(((float) i) * a(context));
    }

    public static float a(float f, Context context) {
        return a(context) * f;
    }

    public static f c() {
        int i;
        Context y = c.y();
        Display defaultDisplay = ((WindowManager) y.getSystemService("window")).getDefaultDisplay();
        int i2 = y.getResources().getConfiguration().orientation;
        int rotation = defaultDisplay.getRotation();
        if (defaultDisplay.getWidth() == defaultDisplay.getHeight()) {
            Object obj = 3;
        } else if (defaultDisplay.getWidth() < defaultDisplay.getHeight()) {
            i = 1;
        } else {
            i = 2;
        }
        if (obj == 1) {
            obj = 1;
        } else if (obj == 2) {
            obj = null;
        } else {
            if (obj == 3) {
                if (i2 == 1) {
                    i = 1;
                } else if (i2 == 2) {
                    obj = null;
                }
            }
            i = 1;
        }
        if (!(rotation == 0 || rotation == 2)) {
            if (obj == null) {
                i = 1;
            } else {
                obj = null;
            }
        }
        if (obj != null) {
            switch (rotation) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    return f.g;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    return f.PORTRAIT_REVERSE;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    return f.h;
                default:
                    return f.PORTRAIT;
            }
        }
        switch (rotation) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return f.e;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return f.LANDSCAPE_REVERSE;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return f.f;
            default:
                return f.LANDSCAPE;
        }
    }

    public static void throwProguardError(Exception ex) {
        if (ex instanceof NoSuchMethodException) {
            CBLogging.b("CBUtility", "Chartboost library error! Have you used proguard on your application? Make sure to add the line '-keep class com.chartboost.sdk.** { *; }' to your proguard config file.");
        } else if (ex == null || ex.getMessage() == null) {
            CBLogging.b("CBUtility", "Unknown Proguard error");
        } else {
            CBLogging.b("CBUtility", ex.getMessage());
        }
    }

    public static String d() {
        String str = "%s %s %s";
        Object[] objArr = new Object[3];
        objArr[0] = "Chartboost-Android-SDK";
        objArr[1] = c.b() == null ? BuildConfig.FLAVOR : c.b();
        objArr[2] = "6.4.1";
        return String.format(str, objArr);
    }

    public static Handler e() {
        if (a == null) {
            a = new Handler(Looper.getMainLooper());
        }
        return a;
    }

    public static void a(Handler handler) {
        a = handler;
    }

    public static boolean f() {
        return h() || i() || j();
    }

    public static String g() {
        SimpleDateFormat simpleDateFormat;
        if (VERSION.SDK_INT >= 18) {
            simpleDateFormat = new SimpleDateFormat("ZZZZ", Locale.US);
        } else {
            simpleDateFormat = new SimpleDateFormat("'GMT'ZZZZ", Locale.US);
        }
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(new Date());
    }

    private static boolean h() {
        String str = Build.TAGS;
        return str != null && str.contains("test-keys");
    }

    private static boolean i() {
        return new File("/system/app/Superuser.apk").exists();
    }

    private static boolean j() {
        for (String file : new String[]{"/sbin/su", "/system/bin/su", "/system/xbin/su", "/data/local/xbin/su", "/data/local/bin/su", "/system/sd/xbin/su", "/system/bin/failsafe/su", "/data/local/su"}) {
            if (new File(file).exists()) {
                return true;
            }
        }
        return false;
    }

    public static void a(Activity activity, b bVar) {
        if (activity != null) {
            if ((bVar == b.WEB && c.H().booleanValue() && c.M()) || (bVar == b.NATIVE && c.N() && c.S())) {
                f c = c();
                if (c == f.PORTRAIT) {
                    activity.setRequestedOrientation(1);
                } else if (c == f.PORTRAIT_REVERSE) {
                    activity.setRequestedOrientation(9);
                } else if (c == f.LANDSCAPE) {
                    activity.setRequestedOrientation(0);
                } else {
                    activity.setRequestedOrientation(8);
                }
            }
        }
    }

    public static void b(Activity activity, b bVar) {
        if (activity != null) {
            if ((bVar == b.WEB && c.H().booleanValue() && c.M()) || (bVar == b.NATIVE && c.N() && c.S())) {
                activity.setRequestedOrientation(-1);
            }
        }
    }

    public static void a(Activity activity) {
        if (activity == null || !c.c) {
            if ((activity.getWindow().getAttributes().flags & AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT) != 0) {
                CBLogging.d("CBUtility", "Attempting to show Status and Navigation bars on a fullscreen activity. Please change your Chartboost activity theme to: \"@android:style/Theme.Translucent\"` in your Manifest file");
            }
        } else if (VERSION.SDK_INT >= 11) {
            activity.getWindow().getDecorView().setSystemUiVisibility(5894);
        }
    }

    public static boolean a(CBFramework cBFramework) {
        return c.b() != null && c.b() == cBFramework;
    }
}
