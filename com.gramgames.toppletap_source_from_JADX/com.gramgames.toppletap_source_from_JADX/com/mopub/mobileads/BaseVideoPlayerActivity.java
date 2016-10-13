package com.mopub.mobileads;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import com.facebook.internal.AnalyticsEvents;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.drive.DriveFile;
import com.mopub.common.AdType;
import com.mopub.common.DataKeys;
import com.mopub.common.FullAdType;
import com.mopub.common.logging.MoPubLog;
import com.mopub.nativeads.NativeVideoViewController;

public class BaseVideoPlayerActivity extends Activity {
    public static final String VIDEO_CLASS_EXTRAS_KEY = "video_view_class_name";
    public static final String VIDEO_URL = "video_url";

    public static void startMraid(Context context, String videoUrl) {
        try {
            context.startActivity(createIntentMraid(context, videoUrl));
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentMraid(Context context, String videoUrl) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(DriveFile.MODE_READ_ONLY);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, AdType.MRAID);
        intentVideoPlayerActivity.putExtra(VIDEO_URL, videoUrl);
        return intentVideoPlayerActivity;
    }

    static void startVast(Context context, VastVideoConfig vastVideoConfig, long broadcastIdentifier) {
        try {
            context.startActivity(createIntentVast(context, vastVideoConfig, broadcastIdentifier));
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    static Intent createIntentVast(Context context, VastVideoConfig vastVideoConfig, long broadcastIdentifier) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(DriveFile.MODE_READ_ONLY);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, FullAdType.VAST);
        intentVideoPlayerActivity.putExtra("vast_video_config", vastVideoConfig);
        intentVideoPlayerActivity.putExtra(DataKeys.BROADCAST_IDENTIFIER_KEY, broadcastIdentifier);
        return intentVideoPlayerActivity;
    }

    public static void startNativeVideo(Context context, long nativeVideoId, VastVideoConfig vastVideoConfig) {
        try {
            context.startActivity(createIntentNativeVideo(context, nativeVideoId, vastVideoConfig));
        } catch (ActivityNotFoundException e) {
            MoPubLog.d("Activity MraidVideoPlayerActivity not found. Did you declare it in your AndroidManifest.xml?");
        }
    }

    public static Intent createIntentNativeVideo(Context context, long nativeVideoId, VastVideoConfig vastVideoConfig) {
        Intent intentVideoPlayerActivity = new Intent(context, MraidVideoPlayerActivity.class);
        intentVideoPlayerActivity.setFlags(DriveFile.MODE_READ_ONLY);
        intentVideoPlayerActivity.putExtra(VIDEO_CLASS_EXTRAS_KEY, AnalyticsEvents.PARAMETER_SHARE_DIALOG_SHOW_NATIVE);
        intentVideoPlayerActivity.putExtra(NativeVideoViewController.NATIVE_VIDEO_ID, nativeVideoId);
        intentVideoPlayerActivity.putExtra(NativeVideoViewController.NATIVE_VAST_VIDEO_CONFIG, vastVideoConfig);
        return intentVideoPlayerActivity;
    }

    protected void onDestroy() {
        super.onDestroy();
        AudioManager am = (AudioManager) getSystemService(MimeTypes.BASE_TYPE_AUDIO);
        if (am != null) {
            am.abandonAudioFocus(null);
        }
    }
}
