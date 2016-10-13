package com.google.android.exoplayer.extractor.ts;

import android.util.Log;
import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.NalUnitUtil;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.volley.DefaultRetryPolicy;
import java.util.Collections;

final class H265Reader extends ElementaryStreamReader {
    private static final int BLA_W_LP = 16;
    private static final int CRA_NUT = 21;
    private static final int PPS_NUT = 34;
    private static final int PREFIX_SEI_NUT = 39;
    private static final int RASL_R = 9;
    private static final int SPS_NUT = 33;
    private static final int SUFFIX_SEI_NUT = 40;
    private static final String TAG = "H265Reader";
    private static final int VPS_NUT = 32;
    private boolean hasOutputFormat;
    private long pesTimeUs;
    private final NalUnitTargetBuffer pps;
    private final boolean[] prefixFlags;
    private final NalUnitTargetBuffer prefixSei;
    private final SampleReader sampleReader;
    private final SeiReader seiReader;
    private final ParsableByteArray seiWrapper;
    private final NalUnitTargetBuffer sps;
    private final NalUnitTargetBuffer suffixSei;
    private long totalBytesWritten;
    private final NalUnitTargetBuffer vps;

    private static final class SampleReader {
        private static final int FIRST_SLICE_FLAG_OFFSET = 2;
        private boolean firstSliceFlag;
        private boolean lookingForFirstSliceFlag;
        private int nalUnitBytesRead;
        private boolean nalUnitHasKeyframeData;
        private long nalUnitStartPosition;
        private long nalUnitTimeUs;
        private final TrackOutput output;
        private boolean readingSample;
        private boolean sampleIsKeyframe;
        private long samplePosition;
        private long sampleTimeUs;

        public SampleReader(TrackOutput output) {
            this.output = output;
        }

        public void reset() {
            this.lookingForFirstSliceFlag = false;
            this.firstSliceFlag = false;
            this.readingSample = false;
        }

        public void startNalUnit(long position, int offset, int nalUnitType, long pesTimeUs) {
            boolean z;
            boolean z2 = false;
            this.firstSliceFlag = false;
            this.nalUnitTimeUs = pesTimeUs;
            this.nalUnitBytesRead = 0;
            this.nalUnitStartPosition = position;
            if (nalUnitType >= H265Reader.VPS_NUT && this.readingSample) {
                outputSample(offset);
                this.readingSample = false;
            }
            if (nalUnitType < H265Reader.BLA_W_LP || nalUnitType > H265Reader.CRA_NUT) {
                z = false;
            } else {
                z = true;
            }
            this.nalUnitHasKeyframeData = z;
            if (this.nalUnitHasKeyframeData || nalUnitType <= H265Reader.RASL_R) {
                z2 = true;
            }
            this.lookingForFirstSliceFlag = z2;
        }

        public void readNalUnitData(byte[] data, int offset, int limit) {
            if (this.lookingForFirstSliceFlag) {
                int headerOffset = (offset + FIRST_SLICE_FLAG_OFFSET) - this.nalUnitBytesRead;
                if (headerOffset < limit) {
                    boolean z;
                    if ((data[headerOffset] & RadialCountdown.BACKGROUND_ALPHA) != 0) {
                        z = true;
                    } else {
                        z = false;
                    }
                    this.firstSliceFlag = z;
                    this.lookingForFirstSliceFlag = false;
                    return;
                }
                this.nalUnitBytesRead += limit - offset;
            }
        }

        public void endNalUnit(long position, int offset) {
            if (this.firstSliceFlag) {
                if (this.readingSample) {
                    outputSample(offset + ((int) (position - this.nalUnitStartPosition)));
                }
                this.samplePosition = this.nalUnitStartPosition;
                this.sampleTimeUs = this.nalUnitTimeUs;
                this.readingSample = true;
                this.sampleIsKeyframe = this.nalUnitHasKeyframeData;
            }
        }

        private void outputSample(int offset) {
            this.output.sampleMetadata(this.sampleTimeUs, this.sampleIsKeyframe ? 1 : 0, (int) (this.nalUnitStartPosition - this.samplePosition), offset, null);
        }
    }

    public H265Reader(TrackOutput output, SeiReader seiReader) {
        super(output);
        this.seiReader = seiReader;
        this.prefixFlags = new boolean[3];
        this.vps = new NalUnitTargetBuffer(VPS_NUT, RadialCountdown.BACKGROUND_ALPHA);
        this.sps = new NalUnitTargetBuffer(SPS_NUT, RadialCountdown.BACKGROUND_ALPHA);
        this.pps = new NalUnitTargetBuffer(PPS_NUT, RadialCountdown.BACKGROUND_ALPHA);
        this.prefixSei = new NalUnitTargetBuffer(PREFIX_SEI_NUT, RadialCountdown.BACKGROUND_ALPHA);
        this.suffixSei = new NalUnitTargetBuffer(SUFFIX_SEI_NUT, RadialCountdown.BACKGROUND_ALPHA);
        this.sampleReader = new SampleReader(output);
        this.seiWrapper = new ParsableByteArray();
    }

    public void seek() {
        NalUnitUtil.clearPrefixFlags(this.prefixFlags);
        this.vps.reset();
        this.sps.reset();
        this.pps.reset();
        this.prefixSei.reset();
        this.suffixSei.reset();
        this.sampleReader.reset();
        this.totalBytesWritten = 0;
    }

    public void packetStarted(long pesTimeUs, boolean dataAlignmentIndicator) {
        this.pesTimeUs = pesTimeUs;
    }

    public void consume(ParsableByteArray data) {
        while (data.bytesLeft() > 0) {
            int offset = data.getPosition();
            int limit = data.limit();
            byte[] dataArray = data.data;
            this.totalBytesWritten += (long) data.bytesLeft();
            this.output.sampleData(data, data.bytesLeft());
            while (offset < limit) {
                int nalUnitOffset = NalUnitUtil.findNalUnit(dataArray, offset, limit, this.prefixFlags);
                if (nalUnitOffset == limit) {
                    nalUnitData(dataArray, offset, limit);
                    return;
                }
                int nalUnitType = NalUnitUtil.getH265NalUnitType(dataArray, nalUnitOffset);
                int lengthToNalUnit = nalUnitOffset - offset;
                if (lengthToNalUnit > 0) {
                    nalUnitData(dataArray, offset, nalUnitOffset);
                }
                int bytesWrittenPastPosition = limit - nalUnitOffset;
                long absolutePosition = this.totalBytesWritten - ((long) bytesWrittenPastPosition);
                nalUnitEnd(absolutePosition, bytesWrittenPastPosition, lengthToNalUnit < 0 ? -lengthToNalUnit : 0, this.pesTimeUs);
                startNalUnit(absolutePosition, bytesWrittenPastPosition, nalUnitType, this.pesTimeUs);
                offset = nalUnitOffset + 3;
            }
        }
    }

    public void packetFinished() {
    }

    private void startNalUnit(long position, int offset, int nalUnitType, long pesTimeUs) {
        if (!this.hasOutputFormat) {
            this.vps.startNalUnit(nalUnitType);
            this.sps.startNalUnit(nalUnitType);
            this.pps.startNalUnit(nalUnitType);
        }
        this.prefixSei.startNalUnit(nalUnitType);
        this.suffixSei.startNalUnit(nalUnitType);
        this.sampleReader.startNalUnit(position, offset, nalUnitType, pesTimeUs);
    }

    private void nalUnitData(byte[] dataArray, int offset, int limit) {
        if (this.hasOutputFormat) {
            this.sampleReader.readNalUnitData(dataArray, offset, limit);
        } else {
            this.vps.appendToNalUnit(dataArray, offset, limit);
            this.sps.appendToNalUnit(dataArray, offset, limit);
            this.pps.appendToNalUnit(dataArray, offset, limit);
        }
        this.prefixSei.appendToNalUnit(dataArray, offset, limit);
        this.suffixSei.appendToNalUnit(dataArray, offset, limit);
    }

    private void nalUnitEnd(long position, int offset, int discardPadding, long pesTimeUs) {
        if (this.hasOutputFormat) {
            this.sampleReader.endNalUnit(position, offset);
        } else {
            this.vps.endNalUnit(discardPadding);
            this.sps.endNalUnit(discardPadding);
            this.pps.endNalUnit(discardPadding);
            if (this.vps.isCompleted() && this.sps.isCompleted() && this.pps.isCompleted()) {
                this.output.format(parseMediaFormat(this.vps, this.sps, this.pps));
                this.hasOutputFormat = true;
            }
        }
        if (this.prefixSei.endNalUnit(discardPadding)) {
            this.seiWrapper.reset(this.prefixSei.nalData, NalUnitUtil.unescapeStream(this.prefixSei.nalData, this.prefixSei.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(pesTimeUs, this.seiWrapper);
        }
        if (this.suffixSei.endNalUnit(discardPadding)) {
            this.seiWrapper.reset(this.suffixSei.nalData, NalUnitUtil.unescapeStream(this.suffixSei.nalData, this.suffixSei.nalLength));
            this.seiWrapper.skipBytes(5);
            this.seiReader.consume(pesTimeUs, this.seiWrapper);
        }
    }

    private static MediaFormat parseMediaFormat(NalUnitTargetBuffer vps, NalUnitTargetBuffer sps, NalUnitTargetBuffer pps) {
        int i;
        Object csd = new byte[((vps.nalLength + sps.nalLength) + pps.nalLength)];
        System.arraycopy(vps.nalData, 0, csd, 0, vps.nalLength);
        System.arraycopy(sps.nalData, 0, csd, vps.nalLength, sps.nalLength);
        System.arraycopy(pps.nalData, 0, csd, vps.nalLength + sps.nalLength, pps.nalLength);
        NalUnitUtil.unescapeStream(sps.nalData, sps.nalLength);
        ParsableBitArray bitArray = new ParsableBitArray(sps.nalData);
        bitArray.skipBits(44);
        int maxSubLayersMinus1 = bitArray.readBits(3);
        bitArray.skipBits(1);
        bitArray.skipBits(88);
        bitArray.skipBits(8);
        int toSkip = 0;
        for (i = 0; i < maxSubLayersMinus1; i++) {
            if (bitArray.readBits(1) == 1) {
                toSkip += 89;
            }
            if (bitArray.readBits(1) == 1) {
                toSkip += 8;
            }
        }
        bitArray.skipBits(toSkip);
        if (maxSubLayersMinus1 > 0) {
            bitArray.skipBits((8 - maxSubLayersMinus1) * 2);
        }
        bitArray.readUnsignedExpGolombCodedInt();
        int chromaFormatIdc = bitArray.readUnsignedExpGolombCodedInt();
        if (chromaFormatIdc == 3) {
            bitArray.skipBits(1);
        }
        int picWidthInLumaSamples = bitArray.readUnsignedExpGolombCodedInt();
        int picHeightInLumaSamples = bitArray.readUnsignedExpGolombCodedInt();
        if (bitArray.readBit()) {
            int confWinLeftOffset = bitArray.readUnsignedExpGolombCodedInt();
            int confWinRightOffset = bitArray.readUnsignedExpGolombCodedInt();
            int confWinTopOffset = bitArray.readUnsignedExpGolombCodedInt();
            int confWinBottomOffset = bitArray.readUnsignedExpGolombCodedInt();
            int subWidthC = (chromaFormatIdc == 1 || chromaFormatIdc == 2) ? 2 : 1;
            picWidthInLumaSamples -= (confWinLeftOffset + confWinRightOffset) * subWidthC;
            picHeightInLumaSamples -= (confWinTopOffset + confWinBottomOffset) * (chromaFormatIdc == 1 ? 2 : 1);
        }
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        int log2MaxPicOrderCntLsbMinus4 = bitArray.readUnsignedExpGolombCodedInt();
        i = bitArray.readBit() ? 0 : maxSubLayersMinus1;
        while (i <= maxSubLayersMinus1) {
            bitArray.readUnsignedExpGolombCodedInt();
            bitArray.readUnsignedExpGolombCodedInt();
            bitArray.readUnsignedExpGolombCodedInt();
            i++;
        }
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        bitArray.readUnsignedExpGolombCodedInt();
        if (bitArray.readBit() && bitArray.readBit()) {
            skipScalingList(bitArray);
        }
        bitArray.skipBits(2);
        if (bitArray.readBit()) {
            bitArray.skipBits(8);
            bitArray.readUnsignedExpGolombCodedInt();
            bitArray.readUnsignedExpGolombCodedInt();
            bitArray.skipBits(1);
        }
        skipShortTermRefPicSets(bitArray);
        if (bitArray.readBit()) {
            for (i = 0; i < bitArray.readUnsignedExpGolombCodedInt(); i++) {
                bitArray.skipBits((log2MaxPicOrderCntLsbMinus4 + 4) + 1);
            }
        }
        bitArray.skipBits(2);
        float pixelWidthHeightRatio = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
        if (bitArray.readBit() && bitArray.readBit()) {
            int aspectRatioIdc = bitArray.readBits(8);
            if (aspectRatioIdc == RadialCountdown.PROGRESS_ALPHA) {
                int sarWidth = bitArray.readBits(BLA_W_LP);
                int sarHeight = bitArray.readBits(BLA_W_LP);
                if (!(sarWidth == 0 || sarHeight == 0)) {
                    pixelWidthHeightRatio = ((float) sarWidth) / ((float) sarHeight);
                }
            } else if (aspectRatioIdc < NalUnitUtil.ASPECT_RATIO_IDC_VALUES.length) {
                pixelWidthHeightRatio = NalUnitUtil.ASPECT_RATIO_IDC_VALUES[aspectRatioIdc];
            } else {
                Log.w(TAG, "Unexpected aspect_ratio_idc value: " + aspectRatioIdc);
            }
        }
        return MediaFormat.createVideoFormat(null, MimeTypes.VIDEO_H265, -1, -1, -1, picWidthInLumaSamples, picHeightInLumaSamples, Collections.singletonList(csd), -1, pixelWidthHeightRatio);
    }

    private static void skipScalingList(ParsableBitArray bitArray) {
        for (int sizeId = 0; sizeId < 4; sizeId++) {
            int matrixId = 0;
            while (matrixId < 6) {
                int i;
                if (bitArray.readBit()) {
                    int coefNum = Math.min(64, 1 << ((sizeId << 1) + 4));
                    if (sizeId > 1) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                    for (int i2 = 0; i2 < coefNum; i2++) {
                        bitArray.readSignedExpGolombCodedInt();
                    }
                } else {
                    bitArray.readUnsignedExpGolombCodedInt();
                }
                if (sizeId == 3) {
                    i = 3;
                } else {
                    i = 1;
                }
                matrixId += i;
            }
        }
    }

    private static void skipShortTermRefPicSets(ParsableBitArray bitArray) {
        int numShortTermRefPicSets = bitArray.readUnsignedExpGolombCodedInt();
        boolean interRefPicSetPredictionFlag = false;
        int previousNumDeltaPocs = 0;
        for (int stRpsIdx = 0; stRpsIdx < numShortTermRefPicSets; stRpsIdx++) {
            if (stRpsIdx != 0) {
                interRefPicSetPredictionFlag = bitArray.readBit();
            }
            if (interRefPicSetPredictionFlag) {
                bitArray.skipBits(1);
                bitArray.readUnsignedExpGolombCodedInt();
                for (int j = 0; j <= previousNumDeltaPocs; j++) {
                    if (bitArray.readBit()) {
                        bitArray.skipBits(1);
                    }
                }
            } else {
                int i;
                int numNegativePics = bitArray.readUnsignedExpGolombCodedInt();
                int numPositivePics = bitArray.readUnsignedExpGolombCodedInt();
                previousNumDeltaPocs = numNegativePics + numPositivePics;
                for (i = 0; i < numNegativePics; i++) {
                    bitArray.readUnsignedExpGolombCodedInt();
                    bitArray.skipBits(1);
                }
                for (i = 0; i < numPositivePics; i++) {
                    bitArray.readUnsignedExpGolombCodedInt();
                    bitArray.skipBits(1);
                }
            }
        }
    }
}
