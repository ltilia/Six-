package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.view.View;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdReport;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DateAndTime;
import com.mopub.common.util.Streams;
import gs.gram.mopub.BuildConfig;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class AdAlertReporter {
    private static final String BODY_SEPARATOR = "\n=================\n";
    private static final String DATE_FORMAT_PATTERN = "M/d/yy hh:mm:ss a z";
    private static final String EMAIL_RECIPIENT = "creative-review@mopub.com";
    private static final String EMAIL_SCHEME = "mailto:";
    private static final int IMAGE_QUALITY = 25;
    private static final String MARKUP_FILENAME = "mp_adalert_markup.html";
    private static final String PARAMETERS_FILENAME = "mp_adalert_parameters.txt";
    private static final String SCREEN_SHOT_FILENAME = "mp_adalert_screenshot.png";
    private final Context mContext;
    private final String mDateString;
    private ArrayList<Uri> mEmailAttachments;
    private Intent mEmailIntent;
    private String mParameters;
    private String mResponse;
    private final View mView;

    public AdAlertReporter(Context context, View view, @Nullable AdReport adReport) {
        this.mView = view;
        this.mContext = context;
        this.mEmailAttachments = new ArrayList();
        this.mDateString = new SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.US).format(DateAndTime.now());
        initEmailIntent();
        Bitmap screenShot = takeScreenShot();
        String screenShotString = convertBitmapInWEBPToBase64EncodedString(screenShot);
        this.mParameters = BuildConfig.FLAVOR;
        this.mResponse = BuildConfig.FLAVOR;
        if (adReport != null) {
            this.mParameters = adReport.toString();
            this.mResponse = adReport.getResponseString();
        }
        addEmailSubject();
        addEmailBody(this.mParameters, this.mResponse, screenShotString);
        addTextAttachment(PARAMETERS_FILENAME, this.mParameters);
        addTextAttachment(MARKUP_FILENAME, this.mResponse);
        addImageAttachment(SCREEN_SHOT_FILENAME, screenShot);
    }

    public void send() {
        this.mEmailIntent.putParcelableArrayListExtra("android.intent.extra.STREAM", this.mEmailAttachments);
        Intent chooserIntent = Intent.createChooser(this.mEmailIntent, "Send Email...");
        chooserIntent.addFlags(DriveFile.MODE_READ_ONLY);
        this.mContext.startActivity(chooserIntent);
    }

    private void initEmailIntent() {
        Uri emailScheme = Uri.parse(EMAIL_SCHEME);
        this.mEmailIntent = new Intent("android.intent.action.SEND_MULTIPLE");
        this.mEmailIntent.setDataAndType(emailScheme, "plain/text");
        this.mEmailIntent.putExtra("android.intent.extra.EMAIL", new String[]{EMAIL_RECIPIENT});
    }

    private Bitmap takeScreenShot() {
        if (this.mView == null || this.mView.getRootView() == null) {
            return null;
        }
        View rootView = this.mView.getRootView();
        boolean wasDrawingCacheEnabled = rootView.isDrawingCacheEnabled();
        rootView.setDrawingCacheEnabled(true);
        Bitmap drawingCache = rootView.getDrawingCache();
        if (drawingCache == null) {
            return null;
        }
        Bitmap bitmap = Bitmap.createBitmap(drawingCache);
        rootView.setDrawingCacheEnabled(wasDrawingCacheEnabled);
        return bitmap;
    }

    private String convertBitmapInWEBPToBase64EncodedString(Bitmap bitmap) {
        String result = null;
        if (bitmap != null) {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(CompressFormat.JPEG, IMAGE_QUALITY, byteArrayOutputStream);
                result = Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
            } catch (Exception e) {
            }
        }
        return result;
    }

    private void addEmailSubject() {
        this.mEmailIntent.putExtra("android.intent.extra.SUBJECT", "New creative violation report - " + this.mDateString);
    }

    private void addEmailBody(String... data) {
        StringBuilder body = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            body.append(data[i]);
            if (i != data.length - 1) {
                body.append(BODY_SEPARATOR);
            }
        }
        this.mEmailIntent.putExtra("android.intent.extra.TEXT", body.toString());
    }

    private void addImageAttachment(String fileName, Bitmap bitmap) {
        FileOutputStream fileOutputStream = null;
        if (fileName != null && bitmap != null) {
            try {
                fileOutputStream = this.mContext.openFileOutput(fileName, 1);
                bitmap.compress(CompressFormat.PNG, IMAGE_QUALITY, fileOutputStream);
                this.mEmailAttachments.add(Uri.fromFile(new File(this.mContext.getFilesDir() + File.separator + fileName)));
            } catch (Exception e) {
                MoPubLog.d("Unable to write text attachment to file: " + fileName);
            } finally {
                Streams.closeStream(fileOutputStream);
            }
        }
    }

    private void addTextAttachment(String fileName, String body) {
        FileOutputStream fileOutputStream = null;
        if (fileName != null && body != null) {
            try {
                fileOutputStream = this.mContext.openFileOutput(fileName, 1);
                fileOutputStream.write(body.getBytes());
                this.mEmailAttachments.add(Uri.fromFile(new File(this.mContext.getFilesDir() + File.separator + fileName)));
            } catch (Exception e) {
                MoPubLog.d("Unable to write text attachment to file: " + fileName);
            } finally {
                Streams.closeStream(fileOutputStream);
            }
        }
    }

    @Deprecated
    Intent getEmailIntent() {
        return this.mEmailIntent;
    }

    @Deprecated
    ArrayList<Uri> getEmailAttachments() {
        return this.mEmailAttachments;
    }

    @Deprecated
    String getParameters() {
        return this.mParameters;
    }

    @Deprecated
    String getResponse() {
        return this.mResponse;
    }
}
