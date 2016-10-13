package com.mopub.mraid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.media.MediaScannerConnection.MediaScannerConnectionClient;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Toast;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.MoPubHttpUrlConnection;
import com.mopub.common.Preconditions;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.AsyncTasks;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Intents;
import com.mopub.common.util.ResponseHeader;
import com.mopub.common.util.Streams;
import com.mopub.common.util.Utils;
import com.mopub.common.util.VersionCode;
import com.mopub.mobileads.GooglePlayServicesInterstitial;
import gs.gram.mopub.BuildConfig;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class MraidNativeCommandHandler {
    public static final String ANDROID_CALENDAR_CONTENT_TYPE = "vnd.android.cursor.item/event";
    private static final String[] DATE_FORMATS;
    private static final int MAX_NUMBER_DAYS_IN_MONTH = 31;
    @VisibleForTesting
    static final String MIME_TYPE_HEADER = "Content-Type";

    interface MraidCommandFailureListener {
        void onFailure(MraidCommandException mraidCommandException);
    }

    class 1 implements DownloadImageAsyncTaskListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ MraidCommandFailureListener val$failureListener;

        1(Context context, MraidCommandFailureListener mraidCommandFailureListener) {
            this.val$context = context;
            this.val$failureListener = mraidCommandFailureListener;
        }

        public void onSuccess() {
            MoPubLog.d("Image successfully saved.");
        }

        public void onFailure() {
            Toast.makeText(this.val$context, "Image failed to download.", 0).show();
            MoPubLog.d("Error downloading and saving image file.");
            this.val$failureListener.onFailure(new MraidCommandException("Error downloading and saving image file."));
        }
    }

    class 2 implements OnClickListener {
        final /* synthetic */ Context val$context;
        final /* synthetic */ MraidCommandFailureListener val$failureListener;
        final /* synthetic */ String val$imageUrl;

        2(Context context, String str, MraidCommandFailureListener mraidCommandFailureListener) {
            this.val$context = context;
            this.val$imageUrl = str;
            this.val$failureListener = mraidCommandFailureListener;
        }

        public void onClick(DialogInterface dialog, int which) {
            MraidNativeCommandHandler.this.downloadImage(this.val$context, this.val$imageUrl, this.val$failureListener);
        }
    }

    @VisibleForTesting
    static class DownloadImageAsyncTask extends AsyncTask<String, Void, Boolean> {
        private final Context mContext;
        private final DownloadImageAsyncTaskListener mListener;

        interface DownloadImageAsyncTaskListener {
            void onFailure();

            void onSuccess();
        }

        public DownloadImageAsyncTask(@NonNull Context context, @NonNull DownloadImageAsyncTaskListener listener) {
            this.mContext = context.getApplicationContext();
            this.mListener = listener;
        }

        protected Boolean doInBackground(@NonNull String[] params) {
            OutputStream pictureOutputStream;
            Boolean valueOf;
            Throwable th;
            if (params == null || params.length == 0 || params[0] == null) {
                return Boolean.valueOf(false);
            }
            File pictureStoragePath = getPictureStoragePath();
            pictureStoragePath.mkdirs();
            String uriString = params[0];
            URI uri = URI.create(uriString);
            InputStream pictureInputStream = null;
            OutputStream pictureOutputStream2 = null;
            try {
                File pictureFile;
                HttpURLConnection urlConnection = MoPubHttpUrlConnection.getHttpUrlConnection(uriString);
                InputStream pictureInputStream2 = new BufferedInputStream(urlConnection.getInputStream());
                try {
                    String redirectLocation = urlConnection.getHeaderField(ResponseHeader.LOCATION.getKey());
                    if (!TextUtils.isEmpty(redirectLocation)) {
                        uri = URI.create(redirectLocation);
                    }
                    pictureFile = new File(pictureStoragePath, getFileNameForUriAndHeaders(uri, urlConnection.getHeaderFields()));
                    pictureOutputStream = new FileOutputStream(pictureFile);
                } catch (Exception e) {
                    pictureInputStream = pictureInputStream2;
                    try {
                        valueOf = Boolean.valueOf(false);
                        Streams.closeStream(pictureInputStream);
                        Streams.closeStream(pictureOutputStream2);
                        return valueOf;
                    } catch (Throwable th2) {
                        th = th2;
                        Streams.closeStream(pictureInputStream);
                        Streams.closeStream(pictureOutputStream2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    pictureInputStream = pictureInputStream2;
                    Streams.closeStream(pictureInputStream);
                    Streams.closeStream(pictureOutputStream2);
                    throw th;
                }
                try {
                    Streams.copyContent(pictureInputStream2, pictureOutputStream);
                    loadPictureIntoGalleryApp(pictureFile.toString());
                    valueOf = Boolean.valueOf(true);
                    Streams.closeStream(pictureInputStream2);
                    Streams.closeStream(pictureOutputStream);
                    return valueOf;
                } catch (Exception e2) {
                    pictureOutputStream2 = pictureOutputStream;
                    pictureInputStream = pictureInputStream2;
                    valueOf = Boolean.valueOf(false);
                    Streams.closeStream(pictureInputStream);
                    Streams.closeStream(pictureOutputStream2);
                    return valueOf;
                } catch (Throwable th4) {
                    th = th4;
                    pictureOutputStream2 = pictureOutputStream;
                    pictureInputStream = pictureInputStream2;
                    Streams.closeStream(pictureInputStream);
                    Streams.closeStream(pictureOutputStream2);
                    throw th;
                }
            } catch (Exception e3) {
                valueOf = Boolean.valueOf(false);
                Streams.closeStream(pictureInputStream);
                Streams.closeStream(pictureOutputStream2);
                return valueOf;
            }
        }

        protected void onPostExecute(Boolean success) {
            if (success == null || !success.booleanValue()) {
                this.mListener.onFailure();
            } else {
                this.mListener.onSuccess();
            }
        }

        @Nullable
        private String getFileNameForUriAndHeaders(@NonNull URI uri, @Nullable Map<String, List<String>> headers) {
            Preconditions.checkNotNull(uri);
            String path = uri.getPath();
            if (path == null || headers == null) {
                return null;
            }
            String filename = new File(path).getName();
            List<String> mimeTypeHeaders = (List) headers.get(MraidNativeCommandHandler.MIME_TYPE_HEADER);
            if (mimeTypeHeaders == null || mimeTypeHeaders.isEmpty() || mimeTypeHeaders.get(0) == null) {
                return filename;
            }
            for (String field : ((String) mimeTypeHeaders.get(0)).split(";")) {
                if (field.contains("image/")) {
                    String extension = "." + field.split("/")[1];
                    if (filename.endsWith(extension)) {
                        return filename;
                    }
                    return filename + extension;
                }
            }
            return filename;
        }

        private File getPictureStoragePath() {
            return new File(Environment.getExternalStorageDirectory(), "Pictures");
        }

        private void loadPictureIntoGalleryApp(String filename) {
            MoPubMediaScannerConnectionClient mediaScannerConnectionClient = new MoPubMediaScannerConnectionClient(null, null);
            MediaScannerConnection mediaScannerConnection = new MediaScannerConnection(this.mContext, mediaScannerConnectionClient);
            mediaScannerConnectionClient.setMediaScannerConnection(mediaScannerConnection);
            mediaScannerConnection.connect();
        }

        @Deprecated
        @VisibleForTesting
        DownloadImageAsyncTaskListener getListener() {
            return this.mListener;
        }
    }

    private static class MoPubMediaScannerConnectionClient implements MediaScannerConnectionClient {
        private final String mFilename;
        private MediaScannerConnection mMediaScannerConnection;
        private final String mMimeType;

        private MoPubMediaScannerConnectionClient(String filename, String mimeType) {
            this.mFilename = filename;
            this.mMimeType = mimeType;
        }

        private void setMediaScannerConnection(MediaScannerConnection connection) {
            this.mMediaScannerConnection = connection;
        }

        public void onMediaScannerConnected() {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.scanFile(this.mFilename, this.mMimeType);
            }
        }

        public void onScanCompleted(String path, Uri uri) {
            if (this.mMediaScannerConnection != null) {
                this.mMediaScannerConnection.disconnect();
            }
        }
    }

    static {
        DATE_FORMATS = new String[]{"yyyy-MM-dd'T'HH:mm:ssZZZZZ", CalendarEventParameters.DATE_FORMAT};
    }

    void createCalendarEvent(Context context, Map<String, String> params) throws MraidCommandException {
        if (isCalendarAvailable(context)) {
            try {
                Map<String, Object> calendarParams = translateJSParamsToAndroidCalendarEventMapping(params);
                Intent intent = new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE);
                for (String key : calendarParams.keySet()) {
                    Object value = calendarParams.get(key);
                    if (value instanceof Long) {
                        intent.putExtra(key, ((Long) value).longValue());
                    } else if (value instanceof Integer) {
                        intent.putExtra(key, ((Integer) value).intValue());
                    } else {
                        intent.putExtra(key, (String) value);
                    }
                }
                intent.setFlags(DriveFile.MODE_READ_ONLY);
                context.startActivity(intent);
                return;
            } catch (ActivityNotFoundException e) {
                MoPubLog.d("no calendar app installed");
                throw new MraidCommandException("Action is unsupported on this device - no calendar app installed");
            } catch (Throwable e2) {
                MoPubLog.d("create calendar: invalid parameters " + e2.getMessage());
                throw new MraidCommandException(e2);
            } catch (Throwable e22) {
                MoPubLog.d("could not create calendar event");
                throw new MraidCommandException(e22);
            }
        }
        MoPubLog.d("unsupported action createCalendarEvent for devices pre-ICS");
        throw new MraidCommandException("Action is unsupported on this device (need Android version Ice Cream Sandwich or above)");
    }

    void storePicture(@NonNull Context context, @NonNull String imageUrl, @NonNull MraidCommandFailureListener failureListener) throws MraidCommandException {
        if (!isStorePictureSupported(context)) {
            MoPubLog.d("Error downloading file - the device does not have an SD card mounted, or the Android permission is not granted.");
            throw new MraidCommandException("Error downloading file  - the device does not have an SD card mounted, or the Android permission is not granted.");
        } else if (context instanceof Activity) {
            showUserDialog(context, imageUrl, failureListener);
        } else {
            Toast.makeText(context, "Downloading image to Picture gallery...", 0).show();
            downloadImage(context, imageUrl, failureListener);
        }
    }

    boolean isTelAvailable(Context context) {
        Intent telIntent = new Intent("android.intent.action.DIAL");
        telIntent.setData(Uri.parse("tel:"));
        return Intents.deviceCanHandleIntent(context, telIntent);
    }

    boolean isSmsAvailable(Context context) {
        Intent smsIntent = new Intent("android.intent.action.VIEW");
        smsIntent.setData(Uri.parse("sms:"));
        return Intents.deviceCanHandleIntent(context, smsIntent);
    }

    public static boolean isStorePictureSupported(Context context) {
        return "mounted".equals(Environment.getExternalStorageState()) && DeviceUtils.isPermissionGranted(context, "android.permission.WRITE_EXTERNAL_STORAGE");
    }

    static boolean isCalendarAvailable(Context context) {
        return VersionCode.currentApiLevel().isAtLeast(VersionCode.ICE_CREAM_SANDWICH) && Intents.deviceCanHandleIntent(context, new Intent("android.intent.action.INSERT").setType(ANDROID_CALENDAR_CONTENT_TYPE));
    }

    @TargetApi(11)
    boolean isInlineVideoAvailable(@NonNull Activity activity, @NonNull View view) {
        if (VersionCode.currentApiLevel().isBelow(VersionCode.HONEYCOMB_MR1)) {
            return false;
        }
        View tempView = view;
        while (tempView.isHardwareAccelerated() && !Utils.bitMaskContainsFlag(tempView.getLayerType(), 1)) {
            if (tempView.getParent() instanceof View) {
                tempView = (View) tempView.getParent();
            } else {
                Window window = activity.getWindow();
                if (window == null || !Utils.bitMaskContainsFlag(window.getAttributes().flags, ViewCompat.MEASURED_STATE_TOO_SMALL)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    @TargetApi(14)
    private Map<String, Object> translateJSParamsToAndroidCalendarEventMapping(Map<String, String> params) {
        Map<String, Object> validatedParamsMapping = new HashMap();
        if (params.containsKey(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION) && params.containsKey(TtmlNode.START)) {
            validatedParamsMapping.put(ShareConstants.WEB_DIALOG_PARAM_TITLE, params.get(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION));
            if (!params.containsKey(TtmlNode.START) || params.get(TtmlNode.START) == null) {
                throw new IllegalArgumentException("Invalid calendar event: start is null.");
            }
            Date startDateTime = parseDate((String) params.get(TtmlNode.START));
            if (startDateTime != null) {
                validatedParamsMapping.put("beginTime", Long.valueOf(startDateTime.getTime()));
                if (params.containsKey(TtmlNode.END) && params.get(TtmlNode.END) != null) {
                    Date endDateTime = parseDate((String) params.get(TtmlNode.END));
                    if (endDateTime != null) {
                        validatedParamsMapping.put("endTime", Long.valueOf(endDateTime.getTime()));
                    } else {
                        throw new IllegalArgumentException("Invalid calendar event: end time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
                    }
                }
                if (params.containsKey(GooglePlayServicesInterstitial.LOCATION_KEY)) {
                    validatedParamsMapping.put("eventLocation", params.get(GooglePlayServicesInterstitial.LOCATION_KEY));
                }
                if (params.containsKey("summary")) {
                    validatedParamsMapping.put(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, params.get("summary"));
                }
                if (params.containsKey("transparency")) {
                    validatedParamsMapping.put("availability", Integer.valueOf(((String) params.get("transparency")).equals("transparent") ? 1 : 0));
                }
                validatedParamsMapping.put("rrule", parseRecurrenceRule(params));
                return validatedParamsMapping;
            }
            throw new IllegalArgumentException("Invalid calendar event: start time is malformed. Date format expecting (yyyy-MM-DDTHH:MM:SS-xx:xx) or (yyyy-MM-DDTHH:MM-xx:xx) i.e. 2013-08-14T09:00:01-08:00");
        }
        throw new IllegalArgumentException("Missing start and description fields");
    }

    private Date parseDate(String dateTime) {
        Date result = null;
        String[] strArr = DATE_FORMATS;
        int length = strArr.length;
        int i = 0;
        while (i < length) {
            try {
                result = new SimpleDateFormat(strArr[i], Locale.US).parse(dateTime);
                if (result != null) {
                    break;
                }
                i++;
            } catch (ParseException e) {
            }
        }
        return result;
    }

    private String parseRecurrenceRule(Map<String, String> params) throws IllegalArgumentException {
        StringBuilder rule = new StringBuilder();
        if (params.containsKey("frequency")) {
            String frequency = (String) params.get("frequency");
            int interval = -1;
            if (params.containsKey("interval")) {
                interval = Integer.parseInt((String) params.get("interval"));
            }
            if ("daily".equals(frequency)) {
                rule.append("FREQ=DAILY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
            } else if ("weekly".equals(frequency)) {
                rule.append("FREQ=WEEKLY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
                if (params.containsKey("daysInWeek")) {
                    String weekdays = translateWeekIntegersToDays((String) params.get("daysInWeek"));
                    if (weekdays == null) {
                        throw new IllegalArgumentException("invalid ");
                    }
                    rule.append("BYDAY=" + weekdays + ";");
                }
            } else if ("monthly".equals(frequency)) {
                rule.append("FREQ=MONTHLY;");
                if (interval != -1) {
                    rule.append("INTERVAL=" + interval + ";");
                }
                if (params.containsKey("daysInMonth")) {
                    String monthDays = translateMonthIntegersToDays((String) params.get("daysInMonth"));
                    if (monthDays == null) {
                        throw new IllegalArgumentException();
                    }
                    rule.append("BYMONTHDAY=" + monthDays + ";");
                }
            } else {
                throw new IllegalArgumentException("frequency is only supported for daily, weekly, and monthly.");
            }
        }
        return rule.toString();
    }

    private String translateWeekIntegersToDays(String expression) throws IllegalArgumentException {
        StringBuilder daysResult = new StringBuilder();
        boolean[] daysAlreadyCounted = new boolean[7];
        String[] days = expression.split(",");
        for (String day : days) {
            int dayNumber = Integer.parseInt(day);
            if (dayNumber == 7) {
                dayNumber = 0;
            }
            if (!daysAlreadyCounted[dayNumber]) {
                daysResult.append(dayNumberToDayOfWeekString(dayNumber) + ",");
                daysAlreadyCounted[dayNumber] = true;
            }
        }
        if (days.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the week if specifying repeating weekly");
        }
        daysResult.deleteCharAt(daysResult.length() - 1);
        return daysResult.toString();
    }

    private String translateMonthIntegersToDays(String expression) throws IllegalArgumentException {
        StringBuilder daysResult = new StringBuilder();
        boolean[] daysAlreadyCounted = new boolean[63];
        String[] days = expression.split(",");
        for (String day : days) {
            int dayNumber = Integer.parseInt(day);
            if (!daysAlreadyCounted[dayNumber + MAX_NUMBER_DAYS_IN_MONTH]) {
                daysResult.append(dayNumberToDayOfMonthString(dayNumber) + ",");
                daysAlreadyCounted[dayNumber + MAX_NUMBER_DAYS_IN_MONTH] = true;
            }
        }
        if (days.length == 0) {
            throw new IllegalArgumentException("must have at least 1 day of the month if specifying repeating weekly");
        }
        daysResult.deleteCharAt(daysResult.length() - 1);
        return daysResult.toString();
    }

    private String dayNumberToDayOfWeekString(int number) throws IllegalArgumentException {
        switch (number) {
            case Yylex.YYINITIAL /*0*/:
                return "SU";
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "MO";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "TU";
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return "WE";
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return "TH";
            case Yytoken.TYPE_COMMA /*5*/:
                return "FR";
            case Yytoken.TYPE_COLON /*6*/:
                return "SA";
            default:
                throw new IllegalArgumentException("invalid day of week " + number);
        }
    }

    private String dayNumberToDayOfMonthString(int number) throws IllegalArgumentException {
        if (number != 0 && number >= -31 && number <= MAX_NUMBER_DAYS_IN_MONTH) {
            return BuildConfig.FLAVOR + number;
        }
        throw new IllegalArgumentException("invalid day of month " + number);
    }

    void downloadImage(Context context, String uriString, MraidCommandFailureListener failureListener) {
        AsyncTasks.safeExecuteOnExecutor(new DownloadImageAsyncTask(context, new 1(context, failureListener)), uriString);
    }

    private void showUserDialog(Context context, String imageUrl, MraidCommandFailureListener failureListener) {
        new Builder(context).setTitle("Save Image").setMessage("Download image to Picture gallery?").setNegativeButton("Cancel", null).setPositiveButton("Okay", new 2(context, imageUrl, failureListener)).setCancelable(true).show();
    }
}
