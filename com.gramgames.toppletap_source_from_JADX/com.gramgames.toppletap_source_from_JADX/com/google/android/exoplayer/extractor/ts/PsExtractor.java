package com.google.android.exoplayer.extractor.ts;

import android.support.v4.view.InputDeviceCompat;
import android.util.SparseArray;
import com.google.android.exoplayer.extractor.Extractor;
import com.google.android.exoplayer.extractor.ExtractorInput;
import com.google.android.exoplayer.extractor.ExtractorOutput;
import com.google.android.exoplayer.extractor.PositionHolder;
import com.google.android.exoplayer.extractor.SeekMap;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.gms.nearby.connection.Connections;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.io.IOException;

public final class PsExtractor implements Extractor {
    public static final int AUDIO_STREAM = 192;
    public static final int AUDIO_STREAM_MASK = 224;
    private static final long MAX_SEARCH_LENGTH = 1048576;
    private static final int MPEG_PROGRAM_END_CODE = 441;
    private static final int PACKET_START_CODE_PREFIX = 1;
    private static final int PACK_START_CODE = 442;
    public static final int PRIVATE_STREAM_1 = 189;
    private static final int SYSTEM_HEADER_START_CODE = 443;
    public static final int VIDEO_STREAM = 224;
    public static final int VIDEO_STREAM_MASK = 240;
    private boolean foundAllTracks;
    private boolean foundAudioTrack;
    private boolean foundVideoTrack;
    private ExtractorOutput output;
    private final ParsableByteArray psPacketBuffer;
    private final SparseArray<PesReader> psPayloadReaders;
    private final PtsTimestampAdjuster ptsTimestampAdjuster;

    private static final class PesReader {
        private static final int PES_SCRATCH_SIZE = 64;
        private boolean dtsFlag;
        private int extendedHeaderLength;
        private final ElementaryStreamReader pesPayloadReader;
        private final ParsableBitArray pesScratch;
        private boolean ptsFlag;
        private final PtsTimestampAdjuster ptsTimestampAdjuster;
        private boolean seenFirstDts;
        private long timeUs;

        public PesReader(ElementaryStreamReader pesPayloadReader, PtsTimestampAdjuster ptsTimestampAdjuster) {
            this.pesPayloadReader = pesPayloadReader;
            this.ptsTimestampAdjuster = ptsTimestampAdjuster;
            this.pesScratch = new ParsableBitArray(new byte[PES_SCRATCH_SIZE]);
        }

        public void seek() {
            this.seenFirstDts = false;
            this.pesPayloadReader.seek();
        }

        public void consume(ParsableByteArray data, ExtractorOutput output) {
            data.readBytes(this.pesScratch.data, 0, 3);
            this.pesScratch.setPosition(0);
            parseHeader();
            data.readBytes(this.pesScratch.data, 0, this.extendedHeaderLength);
            this.pesScratch.setPosition(0);
            parseHeaderExtension();
            this.pesPayloadReader.packetStarted(this.timeUs, true);
            this.pesPayloadReader.consume(data);
            this.pesPayloadReader.packetFinished();
        }

        private void parseHeader() {
            this.pesScratch.skipBits(8);
            this.ptsFlag = this.pesScratch.readBit();
            this.dtsFlag = this.pesScratch.readBit();
            this.pesScratch.skipBits(6);
            this.extendedHeaderLength = this.pesScratch.readBits(8);
        }

        private void parseHeaderExtension() {
            this.timeUs = 0;
            if (this.ptsFlag) {
                this.pesScratch.skipBits(4);
                long pts = ((long) this.pesScratch.readBits(3)) << 30;
                this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                pts |= (long) (this.pesScratch.readBits(15) << 15);
                this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                pts |= (long) this.pesScratch.readBits(15);
                this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                if (!this.seenFirstDts && this.dtsFlag) {
                    this.pesScratch.skipBits(4);
                    long dts = ((long) this.pesScratch.readBits(3)) << 30;
                    this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                    dts |= (long) (this.pesScratch.readBits(15) << 15);
                    this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                    dts |= (long) this.pesScratch.readBits(15);
                    this.pesScratch.skipBits(PsExtractor.PACKET_START_CODE_PREFIX);
                    this.ptsTimestampAdjuster.adjustTimestamp(dts);
                    this.seenFirstDts = true;
                }
                this.timeUs = this.ptsTimestampAdjuster.adjustTimestamp(pts);
            }
        }
    }

    public PsExtractor() {
        this(new PtsTimestampAdjuster(0));
    }

    public PsExtractor(PtsTimestampAdjuster ptsTimestampAdjuster) {
        this.ptsTimestampAdjuster = ptsTimestampAdjuster;
        this.psPacketBuffer = new ParsableByteArray((int) Connections.MAX_RELIABLE_MESSAGE_LEN);
        this.psPayloadReaders = new SparseArray();
    }

    public boolean sniff(ExtractorInput input) throws IOException, InterruptedException {
        boolean z = true;
        byte[] scratch = new byte[14];
        input.peekFully(scratch, 0, 14);
        if (PACK_START_CODE != (((((scratch[0] & RadialCountdown.PROGRESS_ALPHA) << 24) | ((scratch[PACKET_START_CODE_PREFIX] & RadialCountdown.PROGRESS_ALPHA) << 16)) | ((scratch[2] & RadialCountdown.PROGRESS_ALPHA) << 8)) | (scratch[3] & RadialCountdown.PROGRESS_ALPHA)) || (scratch[4] & 196) != 68 || (scratch[6] & 4) != 4 || (scratch[8] & 4) != 4 || (scratch[9] & PACKET_START_CODE_PREFIX) != PACKET_START_CODE_PREFIX || (scratch[12] & 3) != 3) {
            return false;
        }
        input.advancePeekPosition(scratch[13] & 7);
        input.peekFully(scratch, 0, 3);
        if (PACKET_START_CODE_PREFIX != ((((scratch[0] & RadialCountdown.PROGRESS_ALPHA) << 16) | ((scratch[PACKET_START_CODE_PREFIX] & RadialCountdown.PROGRESS_ALPHA) << 8)) | (scratch[2] & RadialCountdown.PROGRESS_ALPHA))) {
            z = false;
        }
        return z;
    }

    public void init(ExtractorOutput output) {
        this.output = output;
        output.seekMap(SeekMap.UNSEEKABLE);
    }

    public void seek() {
        this.ptsTimestampAdjuster.reset();
        for (int i = 0; i < this.psPayloadReaders.size(); i += PACKET_START_CODE_PREFIX) {
            ((PesReader) this.psPayloadReaders.valueAt(i)).seek();
        }
    }

    public int read(ExtractorInput input, PositionHolder seekPosition) throws IOException, InterruptedException {
        if (!input.peekFully(this.psPacketBuffer.data, 0, 4, true)) {
            return -1;
        }
        this.psPacketBuffer.setPosition(0);
        int nextStartCode = this.psPacketBuffer.readInt();
        if (nextStartCode == MPEG_PROGRAM_END_CODE) {
            return -1;
        }
        if (nextStartCode == PACK_START_CODE) {
            input.peekFully(this.psPacketBuffer.data, 0, 10);
            this.psPacketBuffer.setPosition(0);
            this.psPacketBuffer.skipBytes(9);
            input.skipFully((this.psPacketBuffer.readUnsignedByte() & 7) + 14);
            return 0;
        } else if (nextStartCode == SYSTEM_HEADER_START_CODE) {
            input.peekFully(this.psPacketBuffer.data, 0, 2);
            this.psPacketBuffer.setPosition(0);
            input.skipFully(this.psPacketBuffer.readUnsignedShort() + 6);
            return 0;
        } else if (((nextStartCode & InputDeviceCompat.SOURCE_ANY) >> 8) != PACKET_START_CODE_PREFIX) {
            input.skipFully(PACKET_START_CODE_PREFIX);
            return 0;
        } else {
            int streamId = nextStartCode & RadialCountdown.PROGRESS_ALPHA;
            PesReader payloadReader = (PesReader) this.psPayloadReaders.get(streamId);
            if (!this.foundAllTracks) {
                if (payloadReader == null) {
                    ElementaryStreamReader elementaryStreamReader = null;
                    if (!this.foundAudioTrack && streamId == PRIVATE_STREAM_1) {
                        elementaryStreamReader = new Ac3Reader(this.output.track(streamId), false);
                        this.foundAudioTrack = true;
                    } else if (!this.foundAudioTrack && (streamId & VIDEO_STREAM) == AUDIO_STREAM) {
                        elementaryStreamReader = new MpegAudioReader(this.output.track(streamId));
                        this.foundAudioTrack = true;
                    } else if (!this.foundVideoTrack && (streamId & VIDEO_STREAM_MASK) == VIDEO_STREAM) {
                        elementaryStreamReader = new H262Reader(this.output.track(streamId));
                        this.foundVideoTrack = true;
                    }
                    if (elementaryStreamReader != null) {
                        payloadReader = new PesReader(elementaryStreamReader, this.ptsTimestampAdjuster);
                        this.psPayloadReaders.put(streamId, payloadReader);
                    }
                }
                if ((this.foundAudioTrack && this.foundVideoTrack) || input.getPosition() > MAX_SEARCH_LENGTH) {
                    this.foundAllTracks = true;
                    this.output.endTracks();
                }
            }
            input.peekFully(this.psPacketBuffer.data, 0, 2);
            this.psPacketBuffer.setPosition(0);
            int pesLength = this.psPacketBuffer.readUnsignedShort() + 6;
            if (payloadReader == null) {
                input.skipFully(pesLength);
            } else {
                if (this.psPacketBuffer.capacity() < pesLength) {
                    this.psPacketBuffer.reset(new byte[pesLength], pesLength);
                }
                input.readFully(this.psPacketBuffer.data, 0, pesLength);
                this.psPacketBuffer.setPosition(6);
                this.psPacketBuffer.setLimit(pesLength);
                payloadReader.consume(this.psPacketBuffer, this.output);
                this.psPacketBuffer.setLimit(this.psPacketBuffer.capacity());
            }
            return 0;
        }
    }
}
