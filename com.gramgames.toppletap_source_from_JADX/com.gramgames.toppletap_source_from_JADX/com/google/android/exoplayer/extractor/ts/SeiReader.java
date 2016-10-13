package com.google.android.exoplayer.extractor.ts;

import com.google.android.exoplayer.MediaFormat;
import com.google.android.exoplayer.extractor.TrackOutput;
import com.google.android.exoplayer.text.eia608.Eia608Parser;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;

final class SeiReader {
    private final TrackOutput output;

    public SeiReader(TrackOutput output) {
        this.output = output;
        output.format(MediaFormat.createTextFormat(null, MimeTypes.APPLICATION_EIA608, -1, -1, null));
    }

    public void consume(long pesTimeUs, ParsableByteArray seiBuffer) {
        while (seiBuffer.bytesLeft() > 1) {
            int b;
            int payloadType = 0;
            do {
                b = seiBuffer.readUnsignedByte();
                payloadType += b;
            } while (b == RadialCountdown.PROGRESS_ALPHA);
            int payloadSize = 0;
            do {
                b = seiBuffer.readUnsignedByte();
                payloadSize += b;
            } while (b == RadialCountdown.PROGRESS_ALPHA);
            if (Eia608Parser.isSeiMessageEia608(payloadType, payloadSize, seiBuffer)) {
                this.output.sampleData(seiBuffer, payloadSize);
                this.output.sampleMetadata(pesTimeUs, 1, payloadSize, 0, null);
            } else {
                seiBuffer.skipBytes(payloadSize);
            }
        }
    }
}
