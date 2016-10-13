package com.google.android.exoplayer;

import com.google.android.exoplayer.MediaCodecUtil.DecoderQueryException;

public interface MediaCodecSelector {
    public static final MediaCodecSelector DEFAULT;

    static class 1 implements MediaCodecSelector {
        private static final String RAW_DECODER_NAME = "OMX.google.raw.decoder";

        1() {
        }

        public DecoderInfo getDecoderInfo(String mimeType, boolean requiresSecureDecoder) throws DecoderQueryException {
            return MediaCodecUtil.getDecoderInfo(mimeType, requiresSecureDecoder);
        }

        public String getPassthroughDecoderName() throws DecoderQueryException {
            return RAW_DECODER_NAME;
        }
    }

    DecoderInfo getDecoderInfo(String str, boolean z) throws DecoderQueryException;

    String getPassthroughDecoderName() throws DecoderQueryException;

    static {
        DEFAULT = new 1();
    }
}
