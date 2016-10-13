package com.google.android.exoplayer.extractor.ts;

import android.util.Pair;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.Arrays;
import java.util.Collections;
import org.json.simple.parser.Yytoken;

final class H262Reader extends ElementaryStreamReader {
    private static final double[] FRAME_RATE_VALUES;
    private static final int START_EXTENSION = 181;
    private static final int START_GROUP = 184;
    private static final int START_PICTURE = 0;
    private static final int START_SEQUENCE_HEADER = 179;
    private final CsdBuffer csdBuffer;
    private boolean foundFirstFrameInGroup;
    private boolean foundFirstFrameInPacket;
    private long frameDurationUs;
    private long framePosition;
    private long frameTimeUs;
    private boolean hasOutputFormat;
    private boolean isKeyframe;
    private long pesTimeUs;
    private final boolean[] prefixFlags;
    private long totalBytesWritten;

    private static final class CsdBuffer {
        public byte[] data;
        private boolean isFilling;
        public int length;
        public int sequenceExtensionPosition;

        public CsdBuffer(int initialCapacity) {
            this.data = new byte[initialCapacity];
        }

        public void reset() {
            this.isFilling = false;
            this.length = H262Reader.START_PICTURE;
            this.sequenceExtensionPosition = H262Reader.START_PICTURE;
        }

        public boolean onStartCode(int startCodeValue, int bytesAlreadyPassed) {
            if (this.isFilling) {
                if (this.sequenceExtensionPosition == 0 && startCodeValue == H262Reader.START_EXTENSION) {
                    this.sequenceExtensionPosition = this.length;
                } else {
                    this.length -= bytesAlreadyPassed;
                    this.isFilling = false;
                    return true;
                }
            } else if (startCodeValue == H262Reader.START_SEQUENCE_HEADER) {
                this.isFilling = true;
            }
            return false;
        }

        public void onData(byte[] newData, int offset, int limit) {
            if (this.isFilling) {
                int readLength = limit - offset;
                if (this.data.length < this.length + readLength) {
                    this.data = Arrays.copyOf(this.data, (this.length + readLength) * 2);
                }
                System.arraycopy(newData, offset, this.data, this.length, readLength);
                this.length += readLength;
            }
        }
    }

    static {
        FRAME_RATE_VALUES = new double[]{23.976023976023978d, 24.0d, 25.0d, 29.97002997002997d, 30.0d, 50.0d, 59.94005994005994d, 60.0d};
    }

    public H262Reader(TrackOutput output) {
        super(output);
        this.prefixFlags = new boolean[4];
        this.csdBuffer = new CsdBuffer(RadialCountdown.BACKGROUND_ALPHA);
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.csdBuffer.reset();
        this.foundFirstFrameInPacket = false;
        this.foundFirstFrameInGroup = false;
        this.totalBytesWritten = 0;
    }

    public void packetStarted(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.pesTimeUs = pesTimeUs;
        this.foundFirstFrameInPacket = false;
    }

    public void consume(ParsableByteArray data) {
        if (data.bytesLeft() > 0) {
            int offset = data.getPosition();
            int limit = data.limit();
            byte[] dataArray = data.data;
            this.totalBytesWritten += (long) data.bytesLeft();
            this.output.sampleData(data, data.bytesLeft());
            int searchOffset = offset;
            while (true) {
                int startCodeOffset = NalUnitUtil.findNalUnit(dataArray, searchOffset, limit, this.prefixFlags);
                if (startCodeOffset == limit) {
                    break;
                }
                int startCodeValue = data.data[startCodeOffset + 3] & RadialCountdown.PROGRESS_ALPHA;
                if (!this.hasOutputFormat) {
                    int lengthToStartCode = startCodeOffset - offset;
                    if (lengthToStartCode > 0) {
                        this.csdBuffer.onData(dataArray, offset, startCodeOffset);
                    }
                    if (this.csdBuffer.onStartCode(startCodeValue, lengthToStartCode < 0 ? -lengthToStartCode : START_PICTURE)) {
                        Pair<MediaFormat, Long> result = parseCsdBuffer(this.csdBuffer);
                        this.output.format((MediaFormat) result.first);
                        this.frameDurationUs = ((Long) result.second).longValue();
                        this.hasOutputFormat = true;
                    }
                }
                if (this.hasOutputFormat && (startCodeValue == START_GROUP || startCodeValue == 0)) {
                    int bytesWrittenPastStartCode = limit - startCodeOffset;
                    if (this.foundFirstFrameInGroup) {
                        this.output.sampleMetadata(this.frameTimeUs, this.isKeyframe ? 1 : START_PICTURE, ((int) (this.totalBytesWritten - this.framePosition)) - bytesWrittenPastStartCode, bytesWrittenPastStartCode, null);
                        this.isKeyframe = false;
                    }
                    if (startCodeValue == START_GROUP) {
                        this.foundFirstFrameInGroup = false;
                        this.isKeyframe = true;
                    } else {
                        this.frameTimeUs = !this.foundFirstFrameInPacket ? this.pesTimeUs : this.frameTimeUs + this.frameDurationUs;
                        this.framePosition = this.totalBytesWritten - ((long) bytesWrittenPastStartCode);
                        this.foundFirstFrameInPacket = true;
                        this.foundFirstFrameInGroup = true;
                    }
                }
                offset = startCodeOffset;
                searchOffset = offset + 3;
            }
            if (!this.hasOutputFormat) {
                this.csdBuffer.onData(dataArray, offset, limit);
            }
        }
    }

    public void packetFinished() {
    }

    private static Pair<MediaFormat, Long> parseCsdBuffer(CsdBuffer csdBuffer) {
        byte[] csdData = Arrays.copyOf(csdBuffer.data, csdBuffer.length);
        int secondByte = csdData[5] & RadialCountdown.PROGRESS_ALPHA;
        int width = ((csdData[4] & RadialCountdown.PROGRESS_ALPHA) << 4) | (secondByte >> 4);
        int i = (secondByte & 15) << 8;
        int height = i | (csdData[6] & RadialCountdown.PROGRESS_ALPHA);
        float pixelWidthHeightRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        switch ((csdData[7] & PsExtractor.VIDEO_STREAM_MASK) >> 4) {
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                pixelWidthHeightRatio = ((float) (height * 4)) / ((float) (width * 3));
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                pixelWidthHeightRatio = ((float) (height * 16)) / ((float) (width * 9));
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                pixelWidthHeightRatio = ((float) (height * 121)) / ((float) (width * 100));
                break;
        }
        MediaFormat format = MediaFormat.createVideoFormat(null, MimeTypes.VIDEO_MPEG2, -1, -1, -1, width, height, Collections.singletonList(csdData), -1, pixelWidthHeightRatio);
        long frameDurationUs = 0;
        int frameRateCodeMinusOne = (csdData[7] & 15) - 1;
        if (frameRateCodeMinusOne >= 0 && frameRateCodeMinusOne < FRAME_RATE_VALUES.length) {
            double frameRate = FRAME_RATE_VALUES[frameRateCodeMinusOne];
            int sequenceExtensionPosition = csdBuffer.sequenceExtensionPosition;
            int frameRateExtensionN = (csdData[sequenceExtensionPosition + 9] & 96) >> 5;
            int frameRateExtensionD = csdData[sequenceExtensionPosition + 9] & 31;
            if (frameRateExtensionN != frameRateExtensionD) {
                frameRate *= (((double) frameRateExtensionN) + 1.0d) / ((double) (frameRateExtensionD + 1));
            }
            frameDurationUs = (long) (1000000.0d / frameRate);
        }
        return Pair.create(format, Long.valueOf(frameDurationUs));
    }
}
