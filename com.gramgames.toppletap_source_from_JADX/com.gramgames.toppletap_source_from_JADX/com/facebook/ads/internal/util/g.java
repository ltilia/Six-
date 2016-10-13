package com.facebook.ads.internal.util;

import android.app.KeyguardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.PowerManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.facebook.ads.AdSettings;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.e;
import com.google.android.exoplayer.C;
import com.google.android.gms.nearby.connection.Connections;
import com.mopub.common.Constants;
import com.mopub.mobileads.CustomEventInterstitialAdapter;
import gs.gram.mopub.BuildConfig;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.security.KeyStore;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class g {
    private static final Uri a;
    private static final String b;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AdSize.values().length];
            try {
                a[AdSize.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AdSize.RECTANGLE_HEIGHT_250.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AdSize.BANNER_HEIGHT_90.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[AdSize.BANNER_HEIGHT_50.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static class a {
        public String a;
        public String b;
        public boolean c;

        public a(String str, String str2, boolean z) {
            this.a = str;
            this.b = str2;
            this.c = z;
        }
    }

    static {
        a = Uri.parse("content://com.facebook.katana.provider.AttributionIdProvider");
        b = g.class.getSimpleName();
    }

    public static e a(AdSize adSize) {
        switch (1.a[adSize.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return e.WEBVIEW_INTERSTITIAL_UNKNOWN;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return e.WEBVIEW_BANNER_250;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return e.WEBVIEW_BANNER_90;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return e.WEBVIEW_BANNER_50;
            default:
                return e.WEBVIEW_BANNER_LEGACY;
        }
    }

    public static a a(ContentResolver contentResolver) {
        a aVar;
        Throwable th;
        Cursor query;
        try {
            ContentResolver contentResolver2 = contentResolver;
            query = contentResolver2.query(a, new String[]{"aid", "androidid", "limit_tracking"}, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        aVar = new a(query.getString(query.getColumnIndex("aid")), query.getString(query.getColumnIndex("androidid")), Boolean.valueOf(query.getString(query.getColumnIndex("limit_tracking"))).booleanValue());
                        if (query != null) {
                            query.close();
                        }
                        return aVar;
                    }
                } catch (Exception e) {
                    try {
                        aVar = new a(null, null, false);
                        if (query != null) {
                            query.close();
                        }
                        return aVar;
                    } catch (Throwable th2) {
                        th = th2;
                        if (query != null) {
                            query.close();
                        }
                        throw th;
                    }
                }
            }
            aVar = new a(null, null, false);
            if (query != null) {
                query.close();
            }
        } catch (Exception e2) {
            query = null;
            aVar = new a(null, null, false);
            if (query != null) {
                query.close();
            }
            return aVar;
        } catch (Throwable th3) {
            th = th3;
            query = null;
            if (query != null) {
                query.close();
            }
            throw th;
        }
        return aVar;
    }

    public static Object a(Object obj, Method method, Object... objArr) {
        try {
            return method.invoke(obj, objArr);
        } catch (Exception e) {
            return null;
        }
    }

    public static String a(InputStream inputStream) {
        StringWriter stringWriter = new StringWriter();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        char[] cArr = new char[Connections.MAX_RELIABLE_MESSAGE_LEN];
        while (true) {
            int read = inputStreamReader.read(cArr);
            if (read != -1) {
                stringWriter.write(cArr, 0, read);
            } else {
                String stringWriter2 = stringWriter.toString();
                stringWriter.close();
                inputStreamReader.close();
                return stringWriter2;
            }
        }
    }

    public static String a(Map<String, Object> map) {
        JSONObject jSONObject = new JSONObject();
        for (Entry entry : map.entrySet()) {
            try {
                jSONObject.put((String) entry.getKey(), entry.getValue());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jSONObject.toString();
    }

    public static String a(byte[] bArr) {
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            InputStream gZIPInputStream = new GZIPInputStream(byteArrayInputStream);
            String a = a(gZIPInputStream);
            gZIPInputStream.close();
            byteArrayInputStream.close();
            return a;
        } catch (Throwable e) {
            c.a(b.a(e, "Error decompressing data"));
            e.printStackTrace();
            return BuildConfig.FLAVOR;
        }
    }

    public static Method a(Class<?> cls, String str, Class<?>... clsArr) {
        try {
            return cls.getMethod(str, clsArr);
        } catch (NoSuchMethodException e) {
            return null;
        }
    }

    public static Method a(String str, String str2, Class<?>... clsArr) {
        try {
            return a(Class.forName(str), str2, (Class[]) clsArr);
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

    public static void a(Context context, String str) {
        if (AdSettings.isTestMode(context)) {
            Log.d("FBAudienceNetworkLog", str + " (displayed for test ads only)");
        }
    }

    public static void a(DisplayMetrics displayMetrics, View view, AdSize adSize) {
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(((int) (((float) displayMetrics.widthPixels) / displayMetrics.density)) >= adSize.getWidth() ? displayMetrics.widthPixels : (int) Math.ceil((double) (((float) adSize.getWidth()) * displayMetrics.density)), (int) Math.ceil((double) (((float) adSize.getHeight()) * displayMetrics.density)));
        layoutParams.addRule(14, -1);
        view.setLayoutParams(layoutParams);
    }

    public static void a(View view, boolean z, String str) {
    }

    public static boolean a() {
        String urlPrefix = AdSettings.getUrlPrefix();
        return !s.a(urlPrefix) && urlPrefix.endsWith(".sb");
    }

    public static boolean a(Context context) {
        try {
            return !((PowerManager) context.getSystemService("power")).isScreenOn() ? false : !((KeyguardManager) context.getSystemService("keyguard")).inKeyguardRestrictedInputMode();
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean a(Context context, View view, int i) {
        if (view == null) {
            a(view, false, "adView is null.");
            return false;
        } else if (view.getParent() == null) {
            a(view, false, "adView has no parent.");
            return false;
        } else if (view.getVisibility() != 0) {
            a(view, false, "adView is not set to VISIBLE.");
            return false;
        } else if (VERSION.SDK_INT < 11 || view.getAlpha() >= 0.9f) {
            int width = view.getWidth();
            int height = view.getHeight();
            int[] iArr = new int[2];
            try {
                view.getLocationOnScreen(iArr);
                DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
                if (iArr[0] < 0 || displayMetrics.widthPixels - iArr[0] < width) {
                    a(view, false, "adView is not fully on screen horizontally.");
                    return false;
                }
                width = (int) ((((double) height) * (100.0d - ((double) i))) / 100.0d);
                if (iArr[1] < 0 && Math.abs(iArr[1]) > width) {
                    a(view, false, "adView is not visible from the top.");
                    return false;
                } else if ((height + iArr[1]) - displayMetrics.heightPixels > width) {
                    a(view, false, "adView is not visible from the bottom.");
                    return false;
                } else {
                    a(view, true, "adView is visible.");
                    return a(context);
                }
            } catch (NullPointerException e) {
                a(view, false, "Cannot get location on screen.");
                return false;
            }
        } else {
            a(view, false, "adView is too transparent.");
            return false;
        }
    }

    public static byte[] a(String str) {
        try {
            OutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length());
            GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
            gZIPOutputStream.write(str.getBytes());
            gZIPOutputStream.close();
            byte[] toByteArray = byteArrayOutputStream.toByteArray();
            byteArrayOutputStream.close();
            return toByteArray;
        } catch (Throwable e) {
            c.a(b.a(e, "Error compressing data"));
            e.printStackTrace();
            return new byte[0];
        }
    }

    public static HttpClient b() {
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        HttpParams params = defaultHttpClient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, CustomEventInterstitialAdapter.DEFAULT_INTERSTITIAL_TIMEOUT_DELAY);
        HttpConnectionParams.setSoTimeout(params, CustomEventInterstitialAdapter.DEFAULT_INTERSTITIAL_TIMEOUT_DELAY);
        if (a()) {
            try {
                KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
                instance.load(null, null);
                SocketFactory qVar = new q(instance);
                qVar.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params, C.UTF8_NAME);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme(Constants.HTTP, PlainSocketFactory.getSocketFactory(), 80));
                schemeRegistry.register(new Scheme(Constants.HTTPS, qVar, 443));
                return new DefaultHttpClient(new ThreadSafeClientConnManager(params, schemeRegistry), params);
            } catch (Exception e) {
            }
        }
        return defaultHttpClient;
    }
}
