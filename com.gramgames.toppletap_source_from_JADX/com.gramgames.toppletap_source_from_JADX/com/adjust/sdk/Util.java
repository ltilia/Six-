package com.adjust.sdk;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Looper;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.C;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream.GetField;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;
import java.util.regex.Pattern;
import javax.net.ssl.HttpsURLConnection;
import org.json.JSONException;
import org.json.JSONObject;

public class Util {
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'Z";
    private static SimpleDateFormat dateFormat = null;
    private static final String fieldReadErrorMessage = "Unable to read '%s' field in migration device with message (%s)";

    static class 1 extends AsyncTask<Context, Void, String> {
        final /* synthetic */ OnDeviceIdsRead val$onDeviceIdRead;

        1(OnDeviceIdsRead onDeviceIdsRead) {
            this.val$onDeviceIdRead = onDeviceIdsRead;
        }

        protected String doInBackground(Context... params) {
            ILogger logger = AdjustFactory.getLogger();
            String innerResult = Util.getPlayAdId(params[0]);
            logger.debug("GoogleAdId read " + innerResult, new Object[0]);
            return innerResult;
        }

        protected void onPostExecute(String playAdiId) {
            ILogger logger = AdjustFactory.getLogger();
            this.val$onDeviceIdRead.onGoogleAdIdRead(playAdiId);
        }
    }

    private static ILogger getLogger() {
        return AdjustFactory.getLogger();
    }

    protected static String createUuid() {
        return UUID.randomUUID().toString();
    }

    public static String quote(String string) {
        if (string == null) {
            return null;
        }
        if (!Pattern.compile("\\s").matcher(string).find()) {
            return string;
        }
        return String.format(Locale.US, "'%s'", new Object[]{string});
    }

    public static String dateFormat(long date) {
        if (dateFormat == null) {
            dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.US);
        }
        return dateFormat.format(Long.valueOf(date));
    }

    public static String getPlayAdId(Context context) {
        return Reflection.getPlayAdId(context);
    }

    public static void getGoogleAdId(Context context, OnDeviceIdsRead onDeviceIdRead) {
        ILogger logger = AdjustFactory.getLogger();
        if (Looper.myLooper() != Looper.getMainLooper()) {
            logger.debug("GoogleAdId being read in the background", new Object[0]);
            String GoogleAdId = getPlayAdId(context);
            logger.debug("GoogleAdId read " + GoogleAdId, new Object[0]);
            onDeviceIdRead.onGoogleAdIdRead(GoogleAdId);
            return;
        }
        logger.debug("GoogleAdId being read in the foreground", new Object[0]);
        new 1(onDeviceIdRead).execute(new Context[]{context});
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        return Reflection.isPlayTrackingEnabled(context);
    }

    public static String getMacAddress(Context context) {
        return Reflection.getMacAddress(context);
    }

    public static Map<String, String> getPluginKeys(Context context) {
        return Reflection.getPluginKeys(context);
    }

    public static String getAndroidId(Context context) {
        return Reflection.getAndroidId(context);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static <T> T readObject(android.content.Context r11, java.lang.String r12, java.lang.String r13, java.lang.Class<T> r14) {
        /*
        r1 = 0;
        r4 = 0;
        r3 = r11.openFileInput(r12);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r1 = r3;
        r0 = new java.io.BufferedInputStream;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r0.<init>(r3);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r1 = r0;
        r5 = new java.io.ObjectInputStream;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r5.<init>(r0);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r1 = r5;
        r6 = r5.readObject();	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r4 = r14.cast(r6);	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r6 = getLogger();	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r7 = "Read %s: %s";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r9 = 0;
        r8[r9] = r13;	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r9 = 1;
        r8[r9] = r4;	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
        r6.debug(r7, r8);	 Catch:{ ClassNotFoundException -> 0x0033, ClassCastException -> 0x005c, Exception -> 0x0088, FileNotFoundException -> 0x004b }
    L_0x002d:
        if (r1 == 0) goto L_0x0032;
    L_0x002f:
        r1.close();	 Catch:{ Exception -> 0x00a0 }
    L_0x0032:
        return r4;
    L_0x0033:
        r2 = move-exception;
        r6 = getLogger();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r7 = "Failed to find %s class (%s)";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 0;
        r8[r9] = r13;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 1;
        r10 = r2.getMessage();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r8[r9] = r10;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r6.error(r7, r8);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        goto L_0x002d;
    L_0x004b:
        r2 = move-exception;
        r6 = getLogger();
        r7 = "%s file not found";
        r8 = 1;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r13;
        r6.verbose(r7, r8);
        goto L_0x002d;
    L_0x005c:
        r2 = move-exception;
        r6 = getLogger();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r7 = "Failed to cast %s object (%s)";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 0;
        r8[r9] = r13;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 1;
        r10 = r2.getMessage();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r8[r9] = r10;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r6.error(r7, r8);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        goto L_0x002d;
    L_0x0074:
        r2 = move-exception;
        r6 = getLogger();
        r7 = "Failed to open %s file for reading (%s)";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r13;
        r9 = 1;
        r8[r9] = r2;
        r6.error(r7, r8);
        goto L_0x002d;
    L_0x0088:
        r2 = move-exception;
        r6 = getLogger();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r7 = "Failed to read %s object (%s)";
        r8 = 2;
        r8 = new java.lang.Object[r8];	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 0;
        r8[r9] = r13;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r9 = 1;
        r10 = r2.getMessage();	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r8[r9] = r10;	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        r6.error(r7, r8);	 Catch:{ FileNotFoundException -> 0x004b, Exception -> 0x0074 }
        goto L_0x002d;
    L_0x00a0:
        r2 = move-exception;
        r6 = getLogger();
        r7 = "Failed to close %s file for reading (%s)";
        r8 = 2;
        r8 = new java.lang.Object[r8];
        r9 = 0;
        r8[r9] = r13;
        r9 = 1;
        r8[r9] = r2;
        r6.error(r7, r8);
        goto L_0x0032;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.adjust.sdk.Util.readObject(android.content.Context, java.lang.String, java.lang.String, java.lang.Class):T");
    }

    public static <T> void writeObject(T object, Context context, String filename, String objectName) {
        Closeable closable = null;
        try {
            FileOutputStream outputStream = context.openFileOutput(filename, 0);
            FileOutputStream closable2 = outputStream;
            BufferedOutputStream bufferedStream = new BufferedOutputStream(outputStream);
            closable = bufferedStream;
            ObjectOutputStream objectStream = new ObjectOutputStream(bufferedStream);
            closable = objectStream;
            try {
                objectStream.writeObject(object);
                getLogger().debug("Wrote %s: %s", objectName, object);
            } catch (NotSerializableException e) {
                getLogger().error("Failed to serialize %s", objectName);
            }
        } catch (Exception e2) {
            getLogger().error("Failed to open %s for writing (%s)", objectName, e2);
        }
        if (closable != null) {
            try {
                closable.close();
            } catch (Exception e22) {
                getLogger().error("Failed to close %s file for writing (%s)", objectName, e22);
            }
        }
    }

    public static ResponseData readHttpResponse(HttpsURLConnection connection, ActivityPackage activityPackage) throws Exception {
        String message;
        StringBuffer sb = new StringBuffer();
        ILogger logger = getLogger();
        try {
            InputStream inputStream;
            Integer responseCode = Integer.valueOf(connection.getResponseCode());
            if (responseCode.intValue() >= 400) {
                inputStream = connection.getErrorStream();
            } else {
                inputStream = connection.getInputStream();
            }
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while (true) {
                String line = bufferedReader.readLine();
                if (line == null) {
                    break;
                }
                sb.append(line);
            }
            if (connection != null) {
                connection.disconnect();
            }
            ResponseData responseData = ResponseData.buildResponseData(activityPackage);
            String stringResponse = sb.toString();
            logger.verbose("Response: %s", stringResponse);
            if (!(stringResponse == null || stringResponse.length() == 0)) {
                JSONObject jsonResponse = null;
                try {
                    jsonResponse = new JSONObject(stringResponse);
                } catch (JSONException e) {
                    message = String.format("Failed to parse json response. (%s)", new Object[]{e.getMessage()});
                    logger.error(message, new Object[0]);
                    responseData.message = message;
                }
                if (jsonResponse != null) {
                    responseData.jsonResponse = jsonResponse;
                    message = jsonResponse.optString(ShareConstants.WEB_DIALOG_PARAM_MESSAGE, null);
                    responseData.message = message;
                    responseData.timestamp = jsonResponse.optString("timestamp", null);
                    responseData.adid = jsonResponse.optString("adid", null);
                    if (message == null) {
                        message = "No message found";
                    }
                    if (responseCode == null || responseCode.intValue() != 200) {
                        logger.error("%s", message);
                    } else {
                        logger.info("%s", message);
                        responseData.success = true;
                    }
                }
            }
            return responseData;
        } catch (Exception e2) {
            logger.error("Failed to read response. (%s)", e2.getMessage());
            throw e2;
        } catch (Throwable th) {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    public static HttpsURLConnection createGETHttpsURLConnection(String urlString, String clientSdk) throws IOException {
        HttpsURLConnection connection = createHttpsURLConnection(urlString, clientSdk);
        connection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET);
        return connection;
    }

    public static HttpsURLConnection createPOSTHttpsURLConnection(String urlString, String clientSdk, Map<String, String> parameters) throws IOException {
        HttpsURLConnection connection = createHttpsURLConnection(urlString, clientSdk);
        connection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST);
        connection.setUseCaches(false);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
        wr.writeBytes(getPostDataString(parameters));
        wr.flush();
        wr.close();
        return connection;
    }

    private static String getPostDataString(Map<String, String> body) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        for (Entry<String, String> entry : body.entrySet()) {
            String encodedName = URLEncoder.encode((String) entry.getKey(), C.UTF8_NAME);
            String value = (String) entry.getValue();
            String encodedValue = value != null ? URLEncoder.encode(value, C.UTF8_NAME) : BuildConfig.FLAVOR;
            if (result.length() > 0) {
                result.append("&");
            }
            result.append(encodedName);
            result.append("=");
            result.append(encodedValue);
        }
        String dateString = dateFormat(System.currentTimeMillis());
        result.append("&");
        result.append(URLEncoder.encode("sent_at", C.UTF8_NAME));
        result.append("=");
        result.append(URLEncoder.encode(dateString, C.UTF8_NAME));
        return result.toString();
    }

    public static HttpsURLConnection createHttpsURLConnection(String urlString, String clientSdk) throws IOException {
        HttpsURLConnection connection = AdjustFactory.getHttpsURLConnection(new URL(urlString));
        connection.setRequestProperty("Client-SDK", clientSdk);
        connection.setConnectTimeout(Constants.SOCKET_TIMEOUT);
        connection.setReadTimeout(Constants.SOCKET_TIMEOUT);
        return connection;
    }

    public static boolean checkPermission(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static String readStringField(GetField fields, String name, String defaultValue) {
        return (String) readObjectField(fields, name, defaultValue);
    }

    public static <T> T readObjectField(GetField fields, String name, T defaultValue) {
        try {
            defaultValue = fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
        }
        return defaultValue;
    }

    public static boolean readBooleanField(GetField fields, String name, boolean defaultValue) {
        try {
            defaultValue = fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
        }
        return defaultValue;
    }

    public static int readIntField(GetField fields, String name, int defaultValue) {
        try {
            defaultValue = fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
        }
        return defaultValue;
    }

    public static long readLongField(GetField fields, String name, long defaultValue) {
        try {
            defaultValue = fields.get(name, defaultValue);
        } catch (Exception e) {
            getLogger().debug(fieldReadErrorMessage, name, e.getMessage());
        }
        return defaultValue;
    }

    public static boolean equalObject(Object first, Object second) {
        if (first == null || second == null) {
            return first == null && second == null;
        } else {
            return first.equals(second);
        }
    }

    public static boolean equalsMap(Map first, Map second) {
        if (first == null || second == null) {
            return first == null && second == null;
        } else {
            return first.entrySet().equals(second.entrySet());
        }
    }

    public static boolean equalsDouble(Double first, Double second) {
        if (first == null || second == null) {
            if (first == null && second == null) {
                return true;
            }
            return false;
        } else if (Double.doubleToLongBits(first.doubleValue()) != Double.doubleToLongBits(second.doubleValue())) {
            return false;
        } else {
            return true;
        }
    }

    public static boolean equalString(String first, String second) {
        return equalObject(first, second);
    }

    public static boolean equalEnum(Enum first, Enum second) {
        return equalObject(first, second);
    }

    public static boolean equalLong(Long first, Long second) {
        return equalObject(first, second);
    }

    public static boolean equalInt(Integer first, Integer second) {
        return equalObject(first, second);
    }

    public static boolean equalBoolean(Boolean first, Boolean second) {
        return equalObject(first, second);
    }

    public static int hashBoolean(Boolean value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashLong(Long value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashString(String value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashEnum(Enum value) {
        if (value == null) {
            return 0;
        }
        return value.hashCode();
    }

    public static int hashMap(Map value) {
        if (value == null) {
            return 0;
        }
        return value.entrySet().hashCode();
    }

    public static String sha1(String text) {
        return hash(text, Constants.SHA1);
    }

    public static String md5(String text) {
        return hash(text, Constants.MD5);
    }

    public static String hash(String text, String method) {
        String hashString = null;
        try {
            byte[] bytes = text.getBytes(C.UTF8_NAME);
            MessageDigest mesd = MessageDigest.getInstance(method);
            mesd.update(bytes, 0, bytes.length);
            hashString = convertToHex(mesd.digest());
        } catch (Exception e) {
        }
        return hashString;
    }

    public static String convertToHex(byte[] bytes) {
        BigInteger bigInt = new BigInteger(1, bytes);
        String formatString = "%0" + (bytes.length << 1) + "x";
        return String.format(Locale.US, formatString, new Object[]{bigInt});
    }
}
