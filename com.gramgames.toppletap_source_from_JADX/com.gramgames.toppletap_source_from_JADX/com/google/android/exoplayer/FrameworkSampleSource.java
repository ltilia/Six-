package com.google.android.exoplayer;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.net.Uri;
import com.google.android.exoplayer.SampleSource.SampleSourceReader;
import com.google.android.exoplayer.drm.DrmInitData;
import com.google.android.exoplayer.drm.DrmInitData.Mapped;
import com.google.android.exoplayer.drm.DrmInitData.SchemeInitData;
import com.google.android.exoplayer.extractor.mp4.PsshAtomUtil;
import com.google.android.exoplayer.extractor.ts.PtsTimestampAdjuster;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.Util;
import com.google.android.gms.games.stats.PlayerStats;
import com.mopub.mobileads.VastIconXmlManager;
import java.io.FileDescriptor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

@TargetApi(16)
@Deprecated
public final class FrameworkSampleSource implements SampleSource, SampleSourceReader {
    private static final int ALLOWED_FLAGS_MASK = 3;
    private static final int TRACK_STATE_DISABLED = 0;
    private static final int TRACK_STATE_ENABLED = 1;
    private static final int TRACK_STATE_FORMAT_SENT = 2;
    private final Context context;
    private MediaExtractor extractor;
    private final FileDescriptor fileDescriptor;
    private final long fileDescriptorLength;
    private final long fileDescriptorOffset;
    private final Map<String, String> headers;
    private long lastSeekPositionUs;
    private boolean[] pendingDiscontinuities;
    private long pendingSeekPositionUs;
    private IOException preparationError;
    private boolean prepared;
    private int remainingReleaseCount;
    private MediaFormat[] trackFormats;
    private int[] trackStates;
    private final Uri uri;

    public FrameworkSampleSource(Context context, Uri uri, Map<String, String> headers) {
        Assertions.checkState(Util.SDK_INT >= 16);
        this.context = (Context) Assertions.checkNotNull(context);
        this.uri = (Uri) Assertions.checkNotNull(uri);
        this.headers = headers;
        this.fileDescriptor = null;
        this.fileDescriptorOffset = 0;
        this.fileDescriptorLength = 0;
    }

    public FrameworkSampleSource(FileDescriptor fileDescriptor, long fileDescriptorOffset, long fileDescriptorLength) {
        Assertions.checkState(Util.SDK_INT >= 16);
        this.fileDescriptor = (FileDescriptor) Assertions.checkNotNull(fileDescriptor);
        this.fileDescriptorOffset = fileDescriptorOffset;
        this.fileDescriptorLength = fileDescriptorLength;
        this.context = null;
        this.uri = null;
        this.headers = null;
    }

    public SampleSourceReader register() {
        this.remainingReleaseCount += TRACK_STATE_ENABLED;
        return this;
    }

    public boolean prepare(long positionUs) {
        if (!this.prepared) {
            if (this.preparationError != null) {
                return false;
            }
            this.extractor = new MediaExtractor();
            try {
                if (this.context != null) {
                    this.extractor.setDataSource(this.context, this.uri, this.headers);
                } else {
                    this.extractor.setDataSource(this.fileDescriptor, this.fileDescriptorOffset, this.fileDescriptorLength);
                }
                this.trackStates = new int[this.extractor.getTrackCount()];
                this.pendingDiscontinuities = new boolean[this.trackStates.length];
                this.trackFormats = new MediaFormat[this.trackStates.length];
                for (int i = TRACK_STATE_DISABLED; i < this.trackStates.length; i += TRACK_STATE_ENABLED) {
                    this.trackFormats[i] = createMediaFormat(this.extractor.getTrackFormat(i));
                }
                this.prepared = true;
            } catch (IOException e) {
                this.preparationError = e;
                return false;
            }
        }
        return true;
    }

    public int getTrackCount() {
        Assertions.checkState(this.prepared);
        return this.trackStates.length;
    }

    public MediaFormat getFormat(int track) {
        Assertions.checkState(this.prepared);
        return this.trackFormats[track];
    }

    public void enable(int track, long positionUs) {
        boolean z;
        boolean z2 = true;
        Assertions.checkState(this.prepared);
        if (this.trackStates[track] == 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.trackStates[track] = TRACK_STATE_ENABLED;
        this.extractor.selectTrack(track);
        if (positionUs == 0) {
            z2 = false;
        }
        seekToUsInternal(positionUs, z2);
    }

    public boolean continueBuffering(int track, long positionUs) {
        return true;
    }

    public long readDiscontinuity(int track) {
        if (!this.pendingDiscontinuities[track]) {
            return Long.MIN_VALUE;
        }
        this.pendingDiscontinuities[track] = false;
        return this.lastSeekPositionUs;
    }

    public int readData(int track, long positionUs, MediaFormatHolder formatHolder, SampleHolder sampleHolder) {
        Assertions.checkState(this.prepared);
        Assertions.checkState(this.trackStates[track] != 0);
        if (this.pendingDiscontinuities[track]) {
            return -2;
        }
        if (this.trackStates[track] != TRACK_STATE_FORMAT_SENT) {
            formatHolder.format = this.trackFormats[track];
            formatHolder.drmInitData = Util.SDK_INT >= 18 ? getDrmInitDataV18() : null;
            this.trackStates[track] = TRACK_STATE_FORMAT_SENT;
            return -4;
        }
        int extractorTrackIndex = this.extractor.getSampleTrackIndex();
        if (extractorTrackIndex == track) {
            if (sampleHolder.data != null) {
                int offset = sampleHolder.data.position();
                sampleHolder.size = this.extractor.readSampleData(sampleHolder.data, offset);
                sampleHolder.data.position(sampleHolder.size + offset);
            } else {
                sampleHolder.size = TRACK_STATE_DISABLED;
            }
            sampleHolder.timeUs = this.extractor.getSampleTime();
            sampleHolder.flags = this.extractor.getSampleFlags() & ALLOWED_FLAGS_MASK;
            if (sampleHolder.isEncrypted()) {
                sampleHolder.cryptoInfo.setFromExtractorV16(this.extractor);
            }
            this.pendingSeekPositionUs = -1;
            this.extractor.advance();
            return -3;
        }
        return extractorTrackIndex < 0 ? -1 : -2;
    }

    public void disable(int track) {
        boolean z;
        Assertions.checkState(this.prepared);
        if (this.trackStates[track] != 0) {
            z = true;
        } else {
            z = false;
        }
        Assertions.checkState(z);
        this.extractor.unselectTrack(track);
        this.pendingDiscontinuities[track] = false;
        this.trackStates[track] = TRACK_STATE_DISABLED;
    }

    public void maybeThrowError() throws IOException {
        if (this.preparationError != null) {
            throw this.preparationError;
        }
    }

    public void seekToUs(long positionUs) {
        Assertions.checkState(this.prepared);
        seekToUsInternal(positionUs, false);
    }

    public long getBufferedPositionUs() {
        Assertions.checkState(this.prepared);
        long bufferedDurationUs = this.extractor.getCachedDuration();
        if (bufferedDurationUs == -1) {
            return -1;
        }
        long sampleTime = this.extractor.getSampleTime();
        return sampleTime == -1 ? -3 : sampleTime + bufferedDurationUs;
    }

    public void release() {
        Assertions.checkState(this.remainingReleaseCount > 0);
        int i = this.remainingReleaseCount - 1;
        this.remainingReleaseCount = i;
        if (i == 0 && this.extractor != null) {
            this.extractor.release();
            this.extractor = null;
        }
    }

    @TargetApi(18)
    private DrmInitData getDrmInitDataV18() {
        Map<UUID, byte[]> psshInfo = this.extractor.getPsshInfo();
        if (psshInfo == null || psshInfo.isEmpty()) {
            return null;
        }
        DrmInitData drmInitData = new Mapped();
        for (UUID uuid : psshInfo.keySet()) {
            drmInitData.put(uuid, new SchemeInitData(MimeTypes.VIDEO_MP4, PsshAtomUtil.buildPsshAtom(uuid, (byte[]) psshInfo.get(uuid))));
        }
        return drmInitData;
    }

    private void seekToUsInternal(long positionUs, boolean force) {
        if (force || this.pendingSeekPositionUs != positionUs) {
            this.lastSeekPositionUs = positionUs;
            this.pendingSeekPositionUs = positionUs;
            this.extractor.seekTo(positionUs, TRACK_STATE_DISABLED);
            for (int i = TRACK_STATE_DISABLED; i < this.trackStates.length; i += TRACK_STATE_ENABLED) {
                if (this.trackStates[i] != 0) {
                    this.pendingDiscontinuities[i] = true;
                }
            }
        }
    }

    @SuppressLint({"InlinedApi"})
    private static MediaFormat createMediaFormat(MediaFormat format) {
        long durationUs;
        String mimeType = format.getString("mime");
        String language = getOptionalStringV16(format, "language");
        int maxInputSize = getOptionalIntegerV16(format, "max-input-size");
        int width = getOptionalIntegerV16(format, VastIconXmlManager.WIDTH);
        int height = getOptionalIntegerV16(format, VastIconXmlManager.HEIGHT);
        int rotationDegrees = getOptionalIntegerV16(format, "rotation-degrees");
        int channelCount = getOptionalIntegerV16(format, "channel-count");
        int sampleRate = getOptionalIntegerV16(format, "sample-rate");
        int encoderDelay = getOptionalIntegerV16(format, "encoder-delay");
        int encoderPadding = getOptionalIntegerV16(format, "encoder-padding");
        ArrayList<byte[]> initializationData = new ArrayList();
        int i = TRACK_STATE_DISABLED;
        while (true) {
            if (!format.containsKey("csd-" + i)) {
                break;
            }
            ByteBuffer buffer = format.getByteBuffer("csd-" + i);
            Object data = new byte[buffer.limit()];
            buffer.get(data);
            initializationData.add(data);
            buffer.flip();
            i += TRACK_STATE_ENABLED;
        }
        if (format.containsKey("durationUs")) {
            durationUs = format.getLong("durationUs");
        } else {
            durationUs = -1;
        }
        MediaFormat mediaFormat = new MediaFormat(null, mimeType, -1, maxInputSize, durationUs, width, height, rotationDegrees, PlayerStats.UNSET_VALUE, channelCount, sampleRate, language, PtsTimestampAdjuster.DO_NOT_OFFSET, initializationData, false, -1, -1, encoderDelay, encoderPadding);
        mediaFormat.setFrameworkFormatV16(format);
        return mediaFormat;
    }

    @TargetApi(16)
    private static final String getOptionalStringV16(MediaFormat format, String key) {
        return format.containsKey(key) ? format.getString(key) : null;
    }

    @TargetApi(16)
    private static final int getOptionalIntegerV16(MediaFormat format, String key) {
        return format.containsKey(key) ? format.getInteger(key) : -1;
    }
}
