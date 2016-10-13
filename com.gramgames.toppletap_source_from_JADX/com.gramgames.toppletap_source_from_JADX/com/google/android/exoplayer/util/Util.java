package com.google.android.exoplayer.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.Uri;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.google.android.exoplayer.ExoPlayerLibraryInfo;
import com.google.android.exoplayer.upstream.DataSource;
import com.google.android.exoplayer.upstream.DataSpec;
import com.google.android.gms.nearby.connection.Connections;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Util {
    public static final String DEVICE;
    public static final String MANUFACTURER;
    private static final long MAX_BYTES_TO_DRAIN = 2048;
    public static final String MODEL;
    public static final int SDK_INT;
    public static final int TYPE_DASH = 0;
    public static final int TYPE_HLS = 2;
    public static final int TYPE_OTHER = 3;
    public static final int TYPE_SS = 1;
    private static final Pattern XS_DATE_TIME_PATTERN;
    private static final Pattern XS_DURATION_PATTERN;

    static class 1 implements ThreadFactory {
        final /* synthetic */ String val$threadName;

        1(String str) {
            this.val$threadName = str;
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, this.val$threadName);
        }
    }

    static class 2 implements ThreadFactory {
        final /* synthetic */ String val$threadName;

        2(String str) {
            this.val$threadName = str;
        }

        public Thread newThread(Runnable r) {
            return new Thread(r, this.val$threadName);
        }
    }

    static {
        int i = (VERSION.SDK_INT == 23 && VERSION.CODENAME.charAt(TYPE_DASH) == 'N') ? 24 : VERSION.SDK_INT;
        SDK_INT = i;
        DEVICE = Build.DEVICE;
        MANUFACTURER = Build.MANUFACTURER;
        MODEL = Build.MODEL;
        XS_DATE_TIME_PATTERN = Pattern.compile("(\\d\\d\\d\\d)\\-(\\d\\d)\\-(\\d\\d)[Tt](\\d\\d):(\\d\\d):(\\d\\d)(\\.(\\d+))?([Zz]|((\\+|\\-)(\\d\\d):(\\d\\d)))?");
        XS_DURATION_PATTERN = Pattern.compile("^(-)?P(([0-9]*)Y)?(([0-9]*)M)?(([0-9]*)D)?(T(([0-9]*)H)?(([0-9]*)M)?(([0-9.]*)S)?)?$");
    }

    private Util() {
    }

    @SuppressLint({"InlinedApi"})
    public static boolean isAndroidTv(Context context) {
        return context.getPackageManager().hasSystemFeature("android.software.leanback");
    }

    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[Connections.MAX_RELIABLE_MESSAGE_LEN];
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        while (true) {
            int bytesRead = inputStream.read(buffer);
            if (bytesRead == -1) {
                return outputStream.toByteArray();
            }
            outputStream.write(buffer, TYPE_DASH, bytesRead);
        }
    }

    public static boolean isLocalFileUri(Uri uri) {
        String scheme = uri.getScheme();
        return TextUtils.isEmpty(scheme) || scheme.equals("file");
    }

    public static boolean areEqual(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        } else {
            return o1.equals(o2);
        }
    }

    public static boolean contains(Object[] items, Object item) {
        for (int i = TYPE_DASH; i < items.length; i += TYPE_SS) {
            if (areEqual(items[i], item)) {
                return true;
            }
        }
        return false;
    }

    public static ExecutorService newSingleThreadExecutor(String threadName) {
        return Executors.newSingleThreadExecutor(new 1(threadName));
    }

    public static ScheduledExecutorService newSingleThreadScheduledExecutor(String threadName) {
        return Executors.newSingleThreadScheduledExecutor(new 2(threadName));
    }

    public static void closeQuietly(DataSource dataSource) {
        try {
            dataSource.close();
        } catch (IOException e) {
        }
    }

    public static void closeQuietly(OutputStream outputStream) {
        try {
            outputStream.close();
        } catch (IOException e) {
        }
    }

    public static String toLowerInvariant(String text) {
        return text == null ? null : text.toLowerCase(Locale.US);
    }

    public static int ceilDivide(int numerator, int denominator) {
        return ((numerator + denominator) - 1) / denominator;
    }

    public static long ceilDivide(long numerator, long denominator) {
        return ((numerator + denominator) - 1) / denominator;
    }

    public static int binarySearchFloor(long[] a, long key, boolean inclusive, boolean stayInBounds) {
        int index = Arrays.binarySearch(a, key);
        if (index < 0) {
            index = -(index + TYPE_HLS);
        } else if (!inclusive) {
            index--;
        }
        return stayInBounds ? Math.max(TYPE_DASH, index) : index;
    }

    public static int binarySearchCeil(long[] a, long key, boolean inclusive, boolean stayInBounds) {
        int index = Arrays.binarySearch(a, key);
        if (index < 0) {
            index ^= -1;
        } else if (!inclusive) {
            index += TYPE_SS;
        }
        return stayInBounds ? Math.min(a.length - 1, index) : index;
    }

    public static <T> int binarySearchFloor(List<? extends Comparable<? super T>> list, T key, boolean inclusive, boolean stayInBounds) {
        int index = Collections.binarySearch(list, key);
        if (index < 0) {
            index = -(index + TYPE_HLS);
        } else if (!inclusive) {
            index--;
        }
        return stayInBounds ? Math.max(TYPE_DASH, index) : index;
    }

    public static <T> int binarySearchCeil(List<? extends Comparable<? super T>> list, T key, boolean inclusive, boolean stayInBounds) {
        int index = Collections.binarySearch(list, key);
        if (index < 0) {
            index ^= -1;
        } else if (!inclusive) {
            index += TYPE_SS;
        }
        return stayInBounds ? Math.min(list.size() - 1, index) : index;
    }

    public static int[] firstIntegersArray(int length) {
        int[] firstIntegers = new int[length];
        for (int i = TYPE_DASH; i < length; i += TYPE_SS) {
            firstIntegers[i] = i;
        }
        return firstIntegers;
    }

    public static long parseXsDuration(String value) {
        Matcher matcher = XS_DURATION_PATTERN.matcher(value);
        if (!matcher.matches()) {
            return (long) ((Double.parseDouble(value) * 3600.0d) * 1000.0d);
        }
        boolean negated = !TextUtils.isEmpty(matcher.group(TYPE_SS));
        String years = matcher.group(TYPE_OTHER);
        double durationSeconds = years != null ? Double.parseDouble(years) * 3.1556908E7d : 0.0d;
        String months = matcher.group(5);
        durationSeconds += months != null ? Double.parseDouble(months) * 2629739.0d : 0.0d;
        String days = matcher.group(7);
        durationSeconds += days != null ? Double.parseDouble(days) * 86400.0d : 0.0d;
        String hours = matcher.group(10);
        durationSeconds += hours != null ? Double.parseDouble(hours) * 3600.0d : 0.0d;
        String minutes = matcher.group(12);
        durationSeconds += minutes != null ? Double.parseDouble(minutes) * 60.0d : 0.0d;
        String seconds = matcher.group(14);
        long durationMillis = (long) (1000.0d * (durationSeconds + (seconds != null ? Double.parseDouble(seconds) : 0.0d)));
        if (negated) {
            return -durationMillis;
        }
        return durationMillis;
    }

    public static long parseXsDateTime(String value) throws ParseException {
        Matcher matcher = XS_DATE_TIME_PATTERN.matcher(value);
        if (matcher.matches()) {
            int timezoneShift;
            if (matcher.group(9) == null) {
                timezoneShift = TYPE_DASH;
            } else if (matcher.group(9).equalsIgnoreCase("Z")) {
                timezoneShift = TYPE_DASH;
            } else {
                timezoneShift = (Integer.parseInt(matcher.group(12)) * 60) + Integer.parseInt(matcher.group(13));
                if (matcher.group(11).equals("-")) {
                    timezoneShift *= -1;
                }
            }
            Calendar dateTime = new GregorianCalendar(TimeZone.getTimeZone("GMT"));
            dateTime.clear();
            dateTime.set(Integer.parseInt(matcher.group(TYPE_SS)), Integer.parseInt(matcher.group(TYPE_HLS)) - 1, Integer.parseInt(matcher.group(TYPE_OTHER)), Integer.parseInt(matcher.group(4)), Integer.parseInt(matcher.group(5)), Integer.parseInt(matcher.group(6)));
            if (!TextUtils.isEmpty(matcher.group(8))) {
                dateTime.set(14, new BigDecimal("0." + matcher.group(8)).movePointRight(TYPE_OTHER).intValue());
            }
            long time = dateTime.getTimeInMillis();
            if (timezoneShift != 0) {
                return time - ((long) (Constants.SOCKET_TIMEOUT * timezoneShift));
            }
            return time;
        }
        throw new ParseException("Invalid date/time format: " + value, TYPE_DASH);
    }

    public static long scaleLargeTimestamp(long timestamp, long multiplier, long divisor) {
        if (divisor >= multiplier && divisor % multiplier == 0) {
            return timestamp / (divisor / multiplier);
        }
        if (divisor < multiplier && multiplier % divisor == 0) {
            return timestamp * (multiplier / divisor);
        }
        return (long) (((double) timestamp) * (((double) multiplier) / ((double) divisor)));
    }

    public static long[] scaleLargeTimestamps(List<Long> timestamps, long multiplier, long divisor) {
        long[] scaledTimestamps = new long[timestamps.size()];
        int i;
        if (divisor >= multiplier && divisor % multiplier == 0) {
            long divisionFactor = divisor / multiplier;
            for (i = TYPE_DASH; i < scaledTimestamps.length; i += TYPE_SS) {
                scaledTimestamps[i] = ((Long) timestamps.get(i)).longValue() / divisionFactor;
            }
        } else if (divisor >= multiplier || multiplier % divisor != 0) {
            double multiplicationFactor = ((double) multiplier) / ((double) divisor);
            for (i = TYPE_DASH; i < scaledTimestamps.length; i += TYPE_SS) {
                scaledTimestamps[i] = (long) (((double) ((Long) timestamps.get(i)).longValue()) * multiplicationFactor);
            }
        } else {
            long multiplicationFactor2 = multiplier / divisor;
            for (i = TYPE_DASH; i < scaledTimestamps.length; i += TYPE_SS) {
                scaledTimestamps[i] = ((Long) timestamps.get(i)).longValue() * multiplicationFactor2;
            }
        }
        return scaledTimestamps;
    }

    public static void scaleLargeTimestampsInPlace(long[] timestamps, long multiplier, long divisor) {
        int i;
        if (divisor >= multiplier && divisor % multiplier == 0) {
            long divisionFactor = divisor / multiplier;
            for (i = TYPE_DASH; i < timestamps.length; i += TYPE_SS) {
                timestamps[i] = timestamps[i] / divisionFactor;
            }
        } else if (divisor >= multiplier || multiplier % divisor != 0) {
            double multiplicationFactor = ((double) multiplier) / ((double) divisor);
            for (i = TYPE_DASH; i < timestamps.length; i += TYPE_SS) {
                timestamps[i] = (long) (((double) timestamps[i]) * multiplicationFactor);
            }
        } else {
            long multiplicationFactor2 = multiplier / divisor;
            for (i = TYPE_DASH; i < timestamps.length; i += TYPE_SS) {
                timestamps[i] = timestamps[i] * multiplicationFactor2;
            }
        }
    }

    public static int[] toArray(List<Integer> list) {
        if (list == null) {
            return null;
        }
        int length = list.size();
        int[] intArray = new int[length];
        for (int i = TYPE_DASH; i < length; i += TYPE_SS) {
            intArray[i] = ((Integer) list.get(i)).intValue();
        }
        return intArray;
    }

    public static void maybeTerminateInputStream(HttpURLConnection connection, long bytesRemaining) {
        if (SDK_INT == 19 || SDK_INT == 20) {
            try {
                InputStream inputStream = connection.getInputStream();
                if (bytesRemaining == -1) {
                    if (inputStream.read() == -1) {
                        return;
                    }
                } else if (bytesRemaining <= MAX_BYTES_TO_DRAIN) {
                    return;
                }
                String className = inputStream.getClass().getName();
                if (className.equals("com.android.okhttp.internal.http.HttpTransport$ChunkedInputStream") || className.equals("com.android.okhttp.internal.http.HttpTransport$FixedLengthInputStream")) {
                    Method unexpectedEndOfInput = inputStream.getClass().getSuperclass().getDeclaredMethod("unexpectedEndOfInput", new Class[TYPE_DASH]);
                    unexpectedEndOfInput.setAccessible(true);
                    unexpectedEndOfInput.invoke(inputStream, new Object[TYPE_DASH]);
                }
            } catch (IOException e) {
            } catch (Exception e2) {
            }
        }
    }

    public static DataSpec getRemainderDataSpec(DataSpec dataSpec, int bytesLoaded) {
        long remainingLength = -1;
        if (bytesLoaded == 0) {
            return dataSpec;
        }
        if (dataSpec.length != -1) {
            remainingLength = dataSpec.length - ((long) bytesLoaded);
        }
        return new DataSpec(dataSpec.uri, dataSpec.position + ((long) bytesLoaded), remainingLength, dataSpec.key, dataSpec.flags);
    }

    public static int getIntegerCodeForString(String string) {
        int length = string.length();
        Assertions.checkArgument(length <= 4);
        int result = TYPE_DASH;
        for (int i = TYPE_DASH; i < length; i += TYPE_SS) {
            result = (result << 8) | string.charAt(i);
        }
        return result;
    }

    public static int getTopInt(long value) {
        return (int) (value >>> 32);
    }

    public static int getBottomInt(long value) {
        return (int) value;
    }

    public static long getLong(int topInteger, int bottomInteger) {
        return (((long) topInteger) << 32) | (((long) bottomInteger) & 4294967295L);
    }

    public static String getHexStringFromBytes(byte[] data, int beginIndex, int endIndex) {
        StringBuilder dataStringBuilder = new StringBuilder(endIndex - beginIndex);
        for (int i = beginIndex; i < endIndex; i += TYPE_SS) {
            Object[] objArr = new Object[TYPE_SS];
            objArr[TYPE_DASH] = Byte.valueOf(data[i]);
            dataStringBuilder.append(String.format(Locale.US, "%02X", objArr));
        }
        return dataStringBuilder.toString();
    }

    public static byte[] getBytesFromHexString(String hexString) {
        byte[] data = new byte[(hexString.length() / TYPE_HLS)];
        for (int i = TYPE_DASH; i < data.length; i += TYPE_SS) {
            int stringOffset = i * TYPE_HLS;
            data[i] = (byte) ((Character.digit(hexString.charAt(stringOffset), 16) << 4) + Character.digit(hexString.charAt(stringOffset + TYPE_SS), 16));
        }
        return data;
    }

    public static <T> String getCommaDelimitedSimpleClassNames(T[] objects) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = TYPE_DASH; i < objects.length; i += TYPE_SS) {
            stringBuilder.append(objects[i].getClass().getSimpleName());
            if (i < objects.length - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public static String getUserAgent(Context context, String applicationName) {
        String versionName;
        try {
            versionName = context.getPackageManager().getPackageInfo(context.getPackageName(), TYPE_DASH).versionName;
        } catch (NameNotFoundException e) {
            versionName = "?";
        }
        return applicationName + "/" + versionName + " (Linux;Android " + VERSION.RELEASE + ") " + "ExoPlayerLib/" + ExoPlayerLibraryInfo.VERSION;
    }

    public static byte[] executePost(String url, byte[] data, Map<String, String> requestProperties) throws IOException {
        HttpURLConnection urlConnection = null;
        OutputStream out;
        InputStream inputStream;
        try {
            urlConnection = (HttpURLConnection) new URL(url).openConnection();
            urlConnection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST);
            urlConnection.setDoOutput(data != null);
            urlConnection.setDoInput(true);
            if (requestProperties != null) {
                for (Entry<String, String> requestProperty : requestProperties.entrySet()) {
                    urlConnection.setRequestProperty((String) requestProperty.getKey(), (String) requestProperty.getValue());
                }
            }
            if (data != null) {
                out = urlConnection.getOutputStream();
                out.write(data);
                out.close();
            }
            inputStream = urlConnection.getInputStream();
            byte[] toByteArray = toByteArray(inputStream);
            inputStream.close();
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            return toByteArray;
        } catch (Throwable th) {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
    }

    public static int inferContentType(String fileName) {
        if (fileName == null) {
            return TYPE_OTHER;
        }
        if (fileName.endsWith(".mpd")) {
            return TYPE_DASH;
        }
        if (fileName.endsWith(".ism")) {
            return TYPE_SS;
        }
        if (fileName.endsWith(".m3u8")) {
            return TYPE_HLS;
        }
        return TYPE_OTHER;
    }
}
