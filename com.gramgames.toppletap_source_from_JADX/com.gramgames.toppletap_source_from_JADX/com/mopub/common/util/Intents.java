package com.mopub.common.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.Constants;
import com.mopub.common.MoPubBrowser;
import com.mopub.common.Preconditions;
import com.mopub.common.Preconditions.NoThrow;
import com.mopub.common.UrlAction;
import com.mopub.common.logging.MoPubLog;
import com.mopub.exceptions.IntentNotResolvableException;
import com.mopub.exceptions.UrlParseException;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class Intents {
    private Intents() {
    }

    public static void startActivity(@NonNull Context context, @NonNull Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        try {
            context.startActivity(intent);
        } catch (Throwable e) {
            throw new IntentNotResolvableException(e);
        }
    }

    public static Intent getStartActivityIntent(@NonNull Context context, @NonNull Class clazz, @Nullable Bundle extras) {
        Intent intent = new Intent(context, clazz);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        if (extras != null) {
            intent.putExtras(extras);
        }
        return intent;
    }

    public static boolean deviceCanHandleIntent(@NonNull Context context, @NonNull Intent intent) {
        try {
            if (context.getPackageManager().queryIntentActivities(intent, 0).isEmpty()) {
                return false;
            }
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    public static Intent intentForNativeBrowserScheme(@NonNull Uri uri) throws UrlParseException {
        Preconditions.checkNotNull(uri);
        if (!UrlAction.OPEN_NATIVE_BROWSER.shouldTryHandlingUrl(uri)) {
            throw new UrlParseException("URL does not have mopubnativebrowser:// scheme.");
        } else if ("navigate".equals(uri.getHost())) {
            try {
                String urlToOpenInNativeBrowser = uri.getQueryParameter(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
                if (urlToOpenInNativeBrowser == null) {
                    throw new UrlParseException("URL missing 'url' query parameter.");
                }
                return new Intent("android.intent.action.VIEW", Uri.parse(urlToOpenInNativeBrowser));
            } catch (UnsupportedOperationException e) {
                MoPubLog.w("Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        } else {
            throw new UrlParseException("URL missing 'navigate' host parameter.");
        }
    }

    public static Intent intentForShareTweet(@NonNull Uri uri) throws UrlParseException {
        if (UrlAction.HANDLE_SHARE_TWEET.shouldTryHandlingUrl(uri)) {
            try {
                String screenName = uri.getQueryParameter("screen_name");
                String tweetId = uri.getQueryParameter("tweet_id");
                if (TextUtils.isEmpty(screenName)) {
                    throw new UrlParseException("URL missing non-empty 'screen_name' query parameter.");
                } else if (TextUtils.isEmpty(tweetId)) {
                    throw new UrlParseException("URL missing non-empty 'tweet_id' query parameter.");
                } else {
                    String tweetUrl = String.format("https://twitter.com/%s/status/%s", new Object[]{screenName, tweetId});
                    String shareMessage = String.format("Check out @%s's Tweet: %s", new Object[]{screenName, tweetUrl});
                    Intent shareTweetIntent = new Intent("android.intent.action.SEND");
                    shareTweetIntent.setType(WebRequest.CONTENT_TYPE_PLAIN_TEXT);
                    shareTweetIntent.putExtra("android.intent.extra.SUBJECT", shareMessage);
                    shareTweetIntent.putExtra("android.intent.extra.TEXT", shareMessage);
                    return shareTweetIntent;
                }
            } catch (UnsupportedOperationException e) {
                MoPubLog.w("Could not handle url: " + uri);
                throw new UrlParseException("Passed-in URL did not create a hierarchical URI.");
            }
        }
        throw new UrlParseException("URL does not have mopubshare://tweet? format.");
    }

    public static void showMoPubBrowserForUrl(@NonNull Context context, @NonNull Uri uri, @Nullable String dspCreativeId) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        MoPubLog.d("Final URI to show in browser: " + uri);
        Bundle extras = new Bundle();
        extras.putString(MoPubBrowser.DESTINATION_URL_KEY, uri.toString());
        if (!TextUtils.isEmpty(dspCreativeId)) {
            extras.putString(MoPubBrowser.DSP_CREATIVE_ID, dspCreativeId);
        }
        launchIntentForUserClick(context, getStartActivityIntent(context, MoPubBrowser.class, extras), "Could not show MoPubBrowser for url: " + uri + "\n\tPerhaps you " + "forgot to declare com.mopub.common.MoPubBrowser in your Android manifest file.");
    }

    public static void launchIntentForUserClick(@NonNull Context context, @NonNull Intent intent, @Nullable String errorMessage) throws IntentNotResolvableException {
        NoThrow.checkNotNull(context);
        NoThrow.checkNotNull(intent);
        try {
            startActivity(context, intent);
        } catch (IntentNotResolvableException e) {
            throw new IntentNotResolvableException(errorMessage + "\n" + e.getMessage());
        }
    }

    public static void launchApplicationUrl(@NonNull Context context, @NonNull Uri uri) throws IntentNotResolvableException {
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        if (deviceCanHandleIntent(context, intent)) {
            launchApplicationIntent(context, intent);
            return;
        }
        throw new IntentNotResolvableException("Could not handle application specific action: " + uri + "\n\tYou may be running in the emulator or another " + "device which does not have the required application.");
    }

    public static void launchApplicationIntent(@NonNull Context context, @NonNull Intent intent) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(intent);
        if (deviceCanHandleIntent(context, intent)) {
            String errorMessage = "Unable to open intent: " + intent;
            if (!(context instanceof Activity)) {
                intent.addFlags(DriveFile.MODE_READ_ONLY);
            }
            launchIntentForUserClick(context, intent, errorMessage);
            return;
        }
        String fallbackUrl = intent.getStringExtra("browser_fallback_url");
        if (!TextUtils.isEmpty(fallbackUrl)) {
            Uri fallbackUri = Uri.parse(fallbackUrl);
            String fallbackScheme = fallbackUri.getScheme();
            if (Constants.HTTP.equalsIgnoreCase(fallbackScheme) || Constants.HTTPS.equalsIgnoreCase(fallbackScheme)) {
                showMoPubBrowserForUrl(context, fallbackUri, null);
            } else {
                launchApplicationUrl(context, fallbackUri);
            }
        } else if ("market".equalsIgnoreCase(intent.getScheme())) {
            throw new IntentNotResolvableException("Device could not handle neither intent nor market url.\nIntent: " + intent.toString());
        } else {
            launchApplicationUrl(context, getPlayStoreUri(intent));
        }
    }

    @NonNull
    public static Uri getPlayStoreUri(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        return Uri.parse("market://details?id=" + intent.getPackage());
    }

    public static void launchActionViewIntent(@NonNull Context context, @NonNull Uri uri, @Nullable String errorMessage) throws IntentNotResolvableException {
        Preconditions.checkNotNull(context);
        Preconditions.checkNotNull(uri);
        Intent intent = new Intent("android.intent.action.VIEW", uri);
        if (!(context instanceof Activity)) {
            intent.addFlags(DriveFile.MODE_READ_ONLY);
        }
        launchIntentForUserClick(context, intent, errorMessage);
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri) {
        return false;
    }

    @Deprecated
    public static boolean canHandleApplicationUrl(Context context, Uri uri, boolean logError) {
        return false;
    }
}
