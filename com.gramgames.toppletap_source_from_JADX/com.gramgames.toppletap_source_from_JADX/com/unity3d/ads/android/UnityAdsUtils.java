package com.unity3d.ads.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import com.adjust.sdk.Constants;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import gs.gram.mopub.BuildConfig;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.security.auth.x500.X500Principal;

@TargetApi(9)
public class UnityAdsUtils {
    private static final X500Principal DEBUG_DN;

    static {
        DEBUG_DN = new X500Principal("CN=Android Debug,O=Android,C=US");
    }

    @SuppressLint({"PackageManagerGetSignatures"})
    public static boolean isDebuggable() {
        boolean z = true;
        if (UnityAdsProperties.APPLICATION_CONTEXT == null) {
            return false;
        }
        boolean z2;
        PackageManager packageManager = UnityAdsProperties.APPLICATION_CONTEXT.getPackageManager();
        String packageName = UnityAdsProperties.APPLICATION_CONTEXT.getPackageName();
        try {
            ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, 0);
            int i = applicationInfo.flags & 2;
            applicationInfo.flags = i;
            if (i == 0) {
                z = false;
            }
            z2 = false;
        } catch (NameNotFoundException e) {
            UnityAdsDeviceLog.debug("Could not find name");
            z2 = true;
            z = false;
        }
        if (!z2) {
            return z;
        }
        try {
            Signature[] signatureArr = packageManager.getPackageInfo(packageName, 64).signatures;
            int length = signatureArr.length;
            int i2 = 0;
            boolean z3 = z;
            while (i2 < length) {
                try {
                    z = ((X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(signatureArr[i2].toByteArray()))).getSubjectX500Principal().equals(DEBUG_DN);
                    if (z) {
                        return z;
                    }
                    i2++;
                    z3 = z;
                } catch (NameNotFoundException e2) {
                    z = z3;
                } catch (CertificateException e3) {
                    z = z3;
                }
            }
            return z3;
        } catch (NameNotFoundException e4) {
            UnityAdsDeviceLog.debug("Could not find name");
            return z;
        } catch (CertificateException e5) {
            UnityAdsDeviceLog.debug("Certificate exception");
            return z;
        }
    }

    @SuppressLint({"DefaultLocale"})
    public static String Md5(String str) {
        if (str == null) {
            return null;
        }
        MessageDigest instance;
        try {
            instance = MessageDigest.getInstance(Constants.MD5);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            instance = null;
        }
        if (instance == null) {
            return null;
        }
        instance.update(str.getBytes(), 0, str.length());
        byte[] digest = instance.digest();
        String str2 = BuildConfig.FLAVOR;
        int length = digest.length;
        int i = 0;
        while (i < length) {
            int i2 = digest[i] & RadialCountdown.PROGRESS_ALPHA;
            if (i2 <= 15) {
                str2 = str2 + AppEventsConstants.EVENT_PARAM_VALUE_NO;
            }
            i++;
            str2 = str2 + Integer.toHexString(i2);
        }
        return str2.toUpperCase(Locale.US);
    }

    public static String readFile(File file, boolean z) {
        String str = BuildConfig.FLAVOR;
        if (file.exists() && file.canRead()) {
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    String readLine = bufferedReader.readLine();
                    if (readLine != null) {
                        str = str.concat(readLine);
                        if (z) {
                            str = str.concat("\n");
                        }
                    } else {
                        try {
                            bufferedReader.close();
                            return str;
                        } catch (Exception e) {
                            UnityAdsDeviceLog.error("Problem closing reader: " + e.getMessage());
                            return str;
                        }
                    }
                }
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Problem reading file: " + e2.getMessage());
                return null;
            }
        }
        UnityAdsDeviceLog.error("File did not exist or couldn't be read");
        return null;
    }

    public static boolean writeFile(File file, String str) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            fileOutputStream.write(str.getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
            UnityAdsDeviceLog.debug("Wrote file: " + file.getAbsolutePath());
            return true;
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Could not write file: " + e.getMessage());
            return false;
        }
    }

    public static boolean canUseExternalStorage() {
        return Environment.getExternalStorageState().equals("mounted");
    }

    public static void runOnUiThread(Runnable runnable) {
        runOnUiThread(runnable, 0);
    }

    private static void runOnUiThread(Runnable runnable, long j) {
        Handler handler = new Handler(Looper.getMainLooper());
        if (j > 0) {
            handler.postDelayed(runnable, j);
        } else {
            handler.post(runnable);
        }
    }
}
