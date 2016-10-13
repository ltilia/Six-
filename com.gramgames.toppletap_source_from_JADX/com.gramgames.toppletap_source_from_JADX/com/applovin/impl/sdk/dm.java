package com.applovin.impl.sdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.adjust.sdk.Constants;
import com.applovin.impl.adview.v;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdImpl.Builder;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;
import com.google.android.exoplayer.C;
import com.google.android.exoplayer.extractor.ts.PsExtractor;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.Map;
import java.util.Map.Entry;

public class dm extends AppLovinSdkUtils {
    private static final char[] a;
    private static final char[] b;

    static {
        a = "0123456789abcdef".toCharArray();
        b = "-'".toCharArray();
    }

    public static float a(float f) {
        return 1000.0f * f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static android.graphics.Bitmap a(android.content.Context r10, int r11, int r12) {
        /*
        r1 = 1;
        r0 = 0;
        r2 = 0;
        r3 = 0;
        r4 = new android.graphics.BitmapFactory$Options;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4.<init>();	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r5 = 1;
        r4.inJustDecodeBounds = r5;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r5 = r10.getResources();	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        android.graphics.BitmapFactory.decodeResource(r5, r11);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r5 = r4.outHeight;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        if (r5 > r12) goto L_0x001b;
    L_0x0017:
        r5 = r4.outWidth;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        if (r5 <= r12) goto L_0x003f;
    L_0x001b:
        r6 = 4611686018427387904; // 0x4000000000000000 float:0.0 double:2.0;
        r8 = (double) r12;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r1 = r4.outHeight;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = r4.outWidth;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r1 = java.lang.Math.max(r1, r4);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = (double) r1;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = r8 / r4;
        r4 = java.lang.Math.log(r4);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r8 = 4602678819172646912; // 0x3fe0000000000000 float:0.0 double:0.5;
        r8 = java.lang.Math.log(r8);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = r4 / r8;
        r4 = java.lang.Math.ceil(r4);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r1 = (int) r4;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = (double) r1;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4 = java.lang.Math.pow(r6, r4);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r1 = (int) r4;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
    L_0x003f:
        r4 = new android.graphics.BitmapFactory$Options;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4.<init>();	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r4.inSampleSize = r1;	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r1 = r10.getResources();	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r0 = android.graphics.BitmapFactory.decodeResource(r1, r11);	 Catch:{ Exception -> 0x0055, all -> 0x005f }
        r2.close();	 Catch:{ Exception -> 0x0069 }
        r3.close();	 Catch:{ Exception -> 0x0069 }
    L_0x0054:
        return r0;
    L_0x0055:
        r1 = move-exception;
        r2.close();	 Catch:{ Exception -> 0x005d }
        r3.close();	 Catch:{ Exception -> 0x005d }
        goto L_0x0054;
    L_0x005d:
        r1 = move-exception;
        goto L_0x0054;
    L_0x005f:
        r0 = move-exception;
        r2.close();	 Catch:{ Exception -> 0x0067 }
        r3.close();	 Catch:{ Exception -> 0x0067 }
    L_0x0066:
        throw r0;
    L_0x0067:
        r1 = move-exception;
        goto L_0x0066;
    L_0x0069:
        r1 = move-exception;
        goto L_0x0054;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.dm.a(android.content.Context, int, int):android.graphics.Bitmap");
    }

    public static Bitmap a(File file, int i) {
        FileInputStream fileInputStream;
        FileInputStream fileInputStream2;
        Throwable th;
        int i2 = 1;
        FileInputStream fileInputStream3 = null;
        FileInputStream fileInputStream4;
        try {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            fileInputStream4 = new FileInputStream(file);
            try {
                BitmapFactory.decodeStream(fileInputStream4, null, options);
                fileInputStream4.close();
                if (options.outHeight > i || options.outWidth > i) {
                    i2 = (int) Math.pow(2.0d, (double) ((int) Math.ceil(Math.log(((double) i) / ((double) Math.max(options.outHeight, options.outWidth))) / Math.log(0.5d))));
                }
                Options options2 = new Options();
                options2.inSampleSize = i2;
                InputStream fileInputStream5 = new FileInputStream(file);
                try {
                    Bitmap decodeStream = BitmapFactory.decodeStream(fileInputStream5, null, options2);
                    fileInputStream5.close();
                    try {
                        fileInputStream4.close();
                        fileInputStream5.close();
                        return decodeStream;
                    } catch (Exception e) {
                        return decodeStream;
                    }
                } catch (Exception e2) {
                    InputStream inputStream = fileInputStream5;
                    fileInputStream = fileInputStream4;
                    try {
                        fileInputStream.close();
                        fileInputStream2.close();
                    } catch (Exception e3) {
                    }
                    return null;
                } catch (Throwable th2) {
                    th = th2;
                    InputStream inputStream2 = fileInputStream5;
                    try {
                        fileInputStream4.close();
                        fileInputStream3.close();
                    } catch (Exception e4) {
                    }
                    throw th;
                }
            } catch (Exception e5) {
                fileInputStream2 = null;
                fileInputStream = fileInputStream4;
                fileInputStream.close();
                fileInputStream2.close();
                return null;
            } catch (Throwable th3) {
                th = th3;
                fileInputStream4.close();
                fileInputStream3.close();
                throw th;
            }
        } catch (Exception e6) {
            fileInputStream2 = null;
            fileInputStream = null;
            fileInputStream.close();
            fileInputStream2.close();
            return null;
        } catch (Throwable th4) {
            th = th4;
            fileInputStream4 = null;
            fileInputStream4.close();
            fileInputStream3.close();
            throw th;
        }
    }

    public static AppLovinAdImpl a() {
        return new Builder().setHtml(BuildConfig.FLAVOR).setSize(AppLovinAdSize.BANNER).setType(AppLovinAdType.REGULAR).setVideoFilename(BuildConfig.FLAVOR).setTarget(AdTarget.DEFAULT).setCloseStyle(v.WhiteXOnOpaqueBlack).setVideoCloseDelay(0.0f).setCloseDelay(0.0f).setCountdownLength(0).setCurrentAdIdNumber(-1).setClCode(BuildConfig.FLAVOR).build();
    }

    public static String a(String str) {
        return (str == null || str.length() <= 4) ? "NOKEY" : str.substring(str.length() - 4);
    }

    public static String a(String str, AppLovinSdkImpl appLovinSdkImpl) {
        return a(str, (Integer) appLovinSdkImpl.a(cb.n), (String) appLovinSdkImpl.a(cb.m));
    }

    private static String a(String str, Integer num, String str2) {
        if (str2 == null) {
            throw new IllegalArgumentException("No algorithm specified");
        } else if (str == null || str.length() < 1) {
            return BuildConfig.FLAVOR;
        } else {
            if (str2.length() < 1 || UnityAdsConstants.UNITY_ADS_WEBVIEW_VIEWTYPE_NONE.equals(str2)) {
                return str;
            }
            try {
                MessageDigest instance = MessageDigest.getInstance(str2);
                instance.update(str.getBytes(C.UTF8_NAME));
                str = a(instance.digest());
                return (str == null || num.intValue() <= 0) ? str : str.substring(0, Math.min(num.intValue(), str.length()));
            } catch (Throwable e) {
                throw new RuntimeException("Unknown algorithm \"" + str2 + "\"", e);
            } catch (Throwable e2) {
                throw new RuntimeException("Programming error: UTF-8 is not know encoding", e2);
            }
        }
    }

    public static String a(String str, String str2) {
        if (str == null) {
            str = BuildConfig.FLAVOR;
        }
        return str2.replace("{PLACEMENT}", c(str));
    }

    static String a(Map map) {
        if (map == null || map.isEmpty()) {
            return BuildConfig.FLAVOR;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : map.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append(entry.getKey()).append('=').append(entry.getValue());
        }
        return stringBuilder.toString();
    }

    public static String a(byte[] bArr) {
        if (bArr == null) {
            throw new IllegalArgumentException("No data specified");
        }
        char[] cArr = new char[(bArr.length * 2)];
        for (int i = 0; i < bArr.length; i++) {
            cArr[i * 2] = a[(bArr[i] & PsExtractor.VIDEO_STREAM_MASK) >>> 4];
            cArr[(i * 2) + 1] = a[bArr[i] & 15];
        }
        return new String(cArr);
    }

    public static boolean a(AppLovinSdk appLovinSdk, String str) {
        for (String startsWith : ((String) ((AppLovinSdkImpl) appLovinSdk).a(cb.C)).split(",")) {
            if (str.startsWith(startsWith)) {
                return true;
            }
        }
        return false;
    }

    public static long b(float f) {
        return (long) Math.round(f);
    }

    public static String b(String str) {
        return a(str, Integer.valueOf(-1), Constants.SHA1);
    }

    public static long c(float f) {
        return b(a(f));
    }

    static String c(String str) {
        if (!AppLovinSdkUtils.isValidString(str)) {
            return BuildConfig.FLAVOR;
        }
        try {
            return URLEncoder.encode(str, C.UTF8_NAME);
        } catch (Throwable e) {
            throw new UnsupportedOperationException(e);
        }
    }
}
