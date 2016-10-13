package com.google.android.exoplayer.text.eia608;

import android.support.v4.media.TransportMediator;
import com.applovin.sdk.AppLovinErrorCodes;
import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.extractor.ts.PsExtractor;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.exoplayer.util.ParsableBitArray;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.gms.drive.MetadataChangeSet;
import com.unity3d.ads.android.R;
import java.util.ArrayList;

public final class Eia608Parser {
    private static final int[] BASIC_CHARACTER_SET;
    private static final int COUNTRY_CODE = 181;
    private static final int PAYLOAD_TYPE_CC = 4;
    private static final int PROVIDER_CODE = 49;
    private static final int[] SPECIAL_CHARACTER_SET;
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET;
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET;
    private static final int USER_DATA_TYPE_CODE = 3;
    private static final int USER_ID = 1195456820;
    private final ArrayList<ClosedCaption> captions;
    private final ParsableBitArray seiBuffer;
    private final StringBuilder stringBuilder;

    static {
        BASIC_CHARACTER_SET = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 225, 43, 44, 45, 46, 47, 48, PROVIDER_CODE, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, R.styleable.Theme_buttonStyleSmall, R.styleable.Theme_checkboxStyle, R.styleable.Theme_checkedTextViewStyle, R.styleable.Theme_editTextStyle, R.styleable.Theme_radioButtonStyle, R.styleable.Theme_ratingBarStyle, R.styleable.Theme_seekBarStyle, R.styleable.Theme_spinnerStyle, R.styleable.Theme_switchStyle, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, 209, 241, 9632};
        SPECIAL_CHARACTER_SET = new int[]{174, 176, PsExtractor.PRIVATE_STREAM_1, 191, 8482, 162, 163, 9834, PsExtractor.VIDEO_STREAM, 32, 232, 226, 234, 238, 244, 251};
        SPECIAL_ES_FR_CHARACTER_SET = new int[]{193, 201, 211, 218, 220, 252, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, PsExtractor.AUDIO_STREAM, 194, 199, 200, 202, 203, 235, 206, 207, 239, 212, 217, 249, 219, 171, 187};
        SPECIAL_PT_DE_CHARACTER_SET = new int[]{195, 227, 205, AppLovinErrorCodes.NO_FILL, 236, 210, 242, 213, 245, 123, 125, 92, 94, 95, MetadataChangeSet.CUSTOM_PROPERTY_SIZE_LIMIT_BYTES, TransportMediator.KEYCODE_MEDIA_PLAY, 196, 228, 214, 246, 223, 165, 164, 9474, 197, 229, 216, 248, 9484, 9488, 9492, 9496};
    }

    Eia608Parser() {
        this.seiBuffer = new ParsableBitArray();
        this.stringBuilder = new StringBuilder();
        this.captions = new ArrayList();
    }

    boolean canParse(String mimeType) {
        return mimeType.equals(MimeTypes.APPLICATION_EIA608);
    }

    ClosedCaptionList parse(SampleHolder sampleHolder) {
        if (sampleHolder.size < 10) {
            return null;
        }
        this.captions.clear();
        this.stringBuilder.setLength(0);
        this.seiBuffer.reset(sampleHolder.data.array());
        this.seiBuffer.skipBits(67);
        int ccCount = this.seiBuffer.readBits(5);
        this.seiBuffer.skipBits(8);
        for (int i = 0; i < ccCount; i++) {
            this.seiBuffer.skipBits(5);
            if (!this.seiBuffer.readBit()) {
                this.seiBuffer.skipBits(18);
            } else if (this.seiBuffer.readBits(2) != 0) {
                this.seiBuffer.skipBits(16);
            } else {
                this.seiBuffer.skipBits(1);
                byte ccData1 = (byte) this.seiBuffer.readBits(7);
                this.seiBuffer.skipBits(1);
                byte ccData2 = (byte) this.seiBuffer.readBits(7);
                if (ccData1 != null || ccData2 != null) {
                    if ((ccData1 == 17 || ccData1 == 25) && (ccData2 & 112) == 48) {
                        this.stringBuilder.append(getSpecialChar(ccData2));
                    } else if ((ccData1 == (byte) 18 || ccData1 == 26) && (ccData2 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedEsFrChar(ccData2));
                    } else if ((ccData1 == 19 || ccData1 == 27) && (ccData2 & 96) == 32) {
                        backspace();
                        this.stringBuilder.append(getExtendedPtDeChar(ccData2));
                    } else if (ccData1 < ClosedCaptionCtrl.RESUME_CAPTION_LOADING) {
                        addCtrl(ccData1, ccData2);
                    } else {
                        this.stringBuilder.append(getChar(ccData1));
                        if (ccData2 >= ClosedCaptionCtrl.RESUME_CAPTION_LOADING) {
                            this.stringBuilder.append(getChar(ccData2));
                        }
                    }
                }
            }
        }
        addBufferedText();
        if (this.captions.isEmpty()) {
            return null;
        }
        ClosedCaption[] captionArray = new ClosedCaption[this.captions.size()];
        this.captions.toArray(captionArray);
        return new ClosedCaptionList(sampleHolder.timeUs, sampleHolder.isDecodeOnly(), captionArray);
    }

    private static char getChar(byte ccData) {
        return (char) BASIC_CHARACTER_SET[(ccData & TransportMediator.KEYCODE_MEDIA_PAUSE) - 32];
    }

    private static char getSpecialChar(byte ccData) {
        return (char) SPECIAL_CHARACTER_SET[ccData & 15];
    }

    private static char getExtendedEsFrChar(byte ccData) {
        return (char) SPECIAL_ES_FR_CHARACTER_SET[ccData & 31];
    }

    private static char getExtendedPtDeChar(byte ccData) {
        return (char) SPECIAL_PT_DE_CHARACTER_SET[ccData & 31];
    }

    private void addBufferedText() {
        if (this.stringBuilder.length() > 0) {
            this.captions.add(new ClosedCaptionText(this.stringBuilder.toString()));
            this.stringBuilder.setLength(0);
        }
    }

    private void addCtrl(byte ccData1, byte ccData2) {
        addBufferedText();
        this.captions.add(new ClosedCaptionCtrl(ccData1, ccData2));
    }

    private void backspace() {
        addCtrl(ClosedCaptionCtrl.MISC_CHAN_1, ClosedCaptionCtrl.BACKSPACE);
    }

    public static boolean isSeiMessageEia608(int payloadType, int payloadLength, ParsableByteArray payload) {
        if (payloadType != PAYLOAD_TYPE_CC || payloadLength < 8) {
            return false;
        }
        int startPosition = payload.getPosition();
        int countryCode = payload.readUnsignedByte();
        int providerCode = payload.readUnsignedShort();
        int userIdentifier = payload.readInt();
        int userDataTypeCode = payload.readUnsignedByte();
        payload.setPosition(startPosition);
        if (countryCode == COUNTRY_CODE && providerCode == PROVIDER_CODE && userIdentifier == USER_ID && userDataTypeCode == USER_DATA_TYPE_CODE) {
            return true;
        }
        return false;
    }
}
