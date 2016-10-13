package com.amazon.device.ads;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import com.amazon.device.ads.WebRequest.WebRequestException;
import com.amazon.device.ads.WebRequest.WebRequestFactory;
import com.google.android.exoplayer.C;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.Constants;
import gs.gram.mopub.BuildConfig;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Locale;

class WebUtils {
    private static final String LOGTAG;
    private static final MobileAdsLogger logger;

    static class 1 implements Runnable {
        final /* synthetic */ boolean val$disconnectEnabled;
        final /* synthetic */ String val$url;

        1(String str, boolean z) {
            this.val$url = str;
            this.val$disconnectEnabled = z;
        }

        public void run() {
            WebRequest request = new WebRequestFactory().createWebRequest();
            request.enableLog(true);
            request.setUrlString(this.val$url);
            request.setDisconnectEnabled(this.val$disconnectEnabled);
            try {
                request.makeCall();
            } catch (WebRequestException e) {
            }
        }
    }

    WebUtils() {
    }

    static {
        LOGTAG = WebUtils.class.getSimpleName();
        logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    }

    public static boolean launchActivityForIntentLink(String url, Context context) {
        if (url == null || url.equals(BuildConfig.FLAVOR)) {
            url = "about:blank";
        }
        logger.d("Launch Intent: " + url);
        Intent actionIntent = new Intent();
        if (url.startsWith("intent:")) {
            try {
                actionIntent = Intent.parseUri(url, 1);
            } catch (URISyntaxException e) {
                return false;
            }
        }
        actionIntent.setData(Uri.parse(url));
        actionIntent.setAction("android.intent.action.VIEW");
        actionIntent.setFlags(DriveFile.MODE_READ_ONLY);
        try {
            context.startActivity(actionIntent);
            return true;
        } catch (ActivityNotFoundException e2) {
            String action = actionIntent.getAction();
            logger.w("Could not handle " + (action.startsWith("market://") ? "market" : Constants.INTENT_SCHEME) + " action: " + action);
            return false;
        }
    }

    public static final String getURLEncodedString(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLEncoder.encode(s, C.UTF8_NAME).replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLEncodedString threw: %s", e);
            return s;
        }
    }

    public static final String getURLDecodedString(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLDecoder.decode(s, C.UTF8_NAME);
        } catch (UnsupportedEncodingException e) {
            logger.d("getURLDecodedString threw: %s", e);
            return s;
        }
    }

    public static final String getScheme(String url) {
        String scheme = Uri.parse(url).getScheme();
        if (scheme != null) {
            return scheme.toLowerCase(Locale.US);
        }
        return scheme;
    }

    public static final String encloseHtml(String html, boolean isHTML5) {
        if (html == null) {
            return html;
        }
        if (html.indexOf("<html>") == -1) {
            html = "<html>" + html + "</html>";
        }
        if (isHTML5 && html.indexOf("<!DOCTYPE html>") == -1) {
            return "<!DOCTYPE html>" + html;
        }
        return html;
    }

    public static final void executeWebRequestInThread(String url, boolean disconnectEnabled) {
        ThreadUtils.scheduleRunnable(new 1(url, disconnectEnabled));
    }
}
