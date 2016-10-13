package com.google.android.exoplayer.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import com.facebook.ads.AdError;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.ts.PsExtractor;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.drive.FileUploadPreferences;
import com.google.android.gms.nearby.connection.Connections;
import com.google.android.gms.nearby.connection.ConnectionsStatusCodes;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.nio.ByteBuffer;

public final class DtsUtil {
    private static final int[] CHANNELS_BY_AMODE;
    private static final int[] SAMPLE_RATE_BY_SFREQ;
    private static final ParsableBitArray SCRATCH_BITS;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE;

    static {
        CHANNELS_BY_AMODE = new int[]{1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8};
        SAMPLE_RATE_BY_SFREQ = new int[]{-1, ConnectionsStatusCodes.STATUS_NETWORK_NOT_CONNECTED, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1};
        TWICE_BITRATE_KBPS_BY_RATE = new int[]{64, 112, RadialCountdown.BACKGROUND_ALPHA, PsExtractor.AUDIO_STREAM, PsExtractor.VIDEO_STREAM, FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED, 384, 448, AdRequest.MAX_CONTENT_URL_LENGTH, 640, 768, 896, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, 1152, 1280, 1536, 1920, AccessibilityNodeInfoCompat.ACTION_PREVIOUS_HTML_ELEMENT, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, Connections.MAX_RELIABLE_MESSAGE_LEN, 6144, 7680};
        SCRATCH_BITS = new ParsableBitArray();
    }

    public static MediaFormat parseDtsFormat(byte[] frame, String trackId, long durationUs, String language) {
        ParsableBitArray frameBits = SCRATCH_BITS;
        frameBits.reset(frame);
        frameBits.skipBits(60);
        int channelCount = CHANNELS_BY_AMODE[frameBits.readBits(6)];
        int sampleRate = SAMPLE_RATE_BY_SFREQ[frameBits.readBits(4)];
        int rate = frameBits.readBits(5);
        int bitrate = rate >= TWICE_BITRATE_KBPS_BY_RATE.length ? -1 : (TWICE_BITRATE_KBPS_BY_RATE[rate] * AdError.NETWORK_ERROR_CODE) / 2;
        frameBits.skipBits(10);
        return MediaFormat.createAudioFormat(trackId, MimeTypes.AUDIO_DTS, bitrate, -1, durationUs, channelCount + (frameBits.readBits(2) > 0 ? 1 : 0), sampleRate, null, language);
    }

    public static int parseDtsAudioSampleCount(byte[] data) {
        return ((((data[4] & 1) << 6) | ((data[5] & 252) >> 2)) + 1) * 32;
    }

    public static int parseDtsAudioSampleCount(ByteBuffer data) {
        int position = data.position();
        return ((((data.get(position + 4) & 1) << 6) | ((data.get(position + 5) & 252) >> 2)) + 1) * 32;
    }

    public static int getDtsFrameSize(byte[] data) {
        return ((((data[5] & 2) << 12) | ((data[6] & RadialCountdown.PROGRESS_ALPHA) << 4)) | ((data[7] & PsExtractor.VIDEO_STREAM_MASK) >> 4)) + 1;
    }

    private DtsUtil() {
    }
}
