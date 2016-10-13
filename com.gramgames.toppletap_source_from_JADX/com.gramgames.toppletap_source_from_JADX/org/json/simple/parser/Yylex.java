package org.json.simple.parser;

import com.google.android.gms.drive.ExecutionOptions;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsProperties;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

class Yylex {
    public static final int STRING_BEGIN = 2;
    public static final int YYEOF = -1;
    public static final int YYINITIAL = 0;
    private static final int[] ZZ_ACTION;
    private static final String ZZ_ACTION_PACKED_0 = "\u0002\u0000\u0002\u0001\u0001\u0002\u0001\u0003\u0001\u0004\u0003\u0001\u0001\u0005\u0001\u0006\u0001\u0007\u0001\b\u0001\t\u0001\n\u0001\u000b\u0001\f\u0001\r\u0005\u0000\u0001\f\u0001\u000e\u0001\u000f\u0001\u0010\u0001\u0011\u0001\u0012\u0001\u0013\u0001\u0014\u0001\u0000\u0001\u0015\u0001\u0000\u0001\u0015\u0004\u0000\u0001\u0016\u0001\u0017\u0002\u0000\u0001\u0018";
    private static final int[] ZZ_ATTRIBUTE;
    private static final String ZZ_ATTRIBUTE_PACKED_0 = "\u0002\u0000\u0001\t\u0003\u0001\u0001\t\u0003\u0001\u0006\t\u0002\u0001\u0001\t\u0005\u0000\b\t\u0001\u0000\u0001\u0001\u0001\u0000\u0001\u0001\u0004\u0000\u0002\t\u0002\u0000\u0001\t";
    private static final int ZZ_BUFFERSIZE = 16384;
    private static final char[] ZZ_CMAP;
    private static final String ZZ_CMAP_PACKED = "\t\u0000\u0001\u0007\u0001\u0007\u0002\u0000\u0001\u0007\u0012\u0000\u0001\u0007\u0001\u0000\u0001\t\b\u0000\u0001\u0006\u0001\u0019\u0001\u0002\u0001\u0004\u0001\n\n\u0003\u0001\u001a\u0006\u0000\u0004\u0001\u0001\u0005\u0001\u0001\u0014\u0000\u0001\u0017\u0001\b\u0001\u0018\u0003\u0000\u0001\u0012\u0001\u000b\u0002\u0001\u0001\u0011\u0001\f\u0005\u0000\u0001\u0013\u0001\u0000\u0001\r\u0003\u0000\u0001\u000e\u0001\u0014\u0001\u000f\u0001\u0010\u0005\u0000\u0001\u0015\u0001\u0000\u0001\u0016\uff82\u0000";
    private static final String[] ZZ_ERROR_MSG;
    private static final int[] ZZ_LEXSTATE;
    private static final int ZZ_NO_MATCH = 1;
    private static final int ZZ_PUSHBACK_2BIG = 2;
    private static final int[] ZZ_ROWMAP;
    private static final String ZZ_ROWMAP_PACKED_0 = "\u0000\u0000\u0000\u001b\u00006\u0000Q\u0000l\u0000\u0087\u00006\u0000\u00a2\u0000\u00bd\u0000\u00d8\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u00f3\u0000\u010e\u00006\u0000\u0129\u0000\u0144\u0000\u015f\u0000\u017a\u0000\u0195\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u00006\u0000\u01b0\u0000\u01cb\u0000\u01e6\u0000\u01e6\u0000\u0201\u0000\u021c\u0000\u0237\u0000\u0252\u00006\u00006\u0000\u026d\u0000\u0288\u00006";
    private static final int[] ZZ_TRANS;
    private static final int ZZ_UNKNOWN_ERROR = 0;
    private StringBuffer sb;
    private int yychar;
    private int yycolumn;
    private int yyline;
    private boolean zzAtBOL;
    private boolean zzAtEOF;
    private char[] zzBuffer;
    private int zzCurrentPos;
    private int zzEndRead;
    private int zzLexicalState;
    private int zzMarkedPos;
    private Reader zzReader;
    private int zzStartRead;
    private int zzState;

    static {
        ZZ_LEXSTATE = new int[]{YYINITIAL, YYINITIAL, ZZ_NO_MATCH, ZZ_NO_MATCH};
        ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);
        ZZ_ACTION = zzUnpackAction();
        ZZ_ROWMAP = zzUnpackRowMap();
        ZZ_TRANS = new int[]{ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, 3, 4, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, 5, ZZ_PUSHBACK_2BIG, 6, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, 7, 8, ZZ_PUSHBACK_2BIG, 9, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, ZZ_PUSHBACK_2BIG, 10, 11, 12, 13, 14, 15, 16, 16, 16, 16, 16, 16, 16, 16, 17, 18, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 4, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 4, 19, 20, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 20, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 5, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 21, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 22, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 23, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 16, 16, 16, 16, 16, 16, 16, 16, YYEOF, YYEOF, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 24, 25, 26, 27, 28, 29, 30, 31, 32, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 33, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 34, 35, YYEOF, YYEOF, 34, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 36, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 37, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 38, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 39, YYEOF, 39, YYEOF, 39, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 39, 39, YYEOF, YYEOF, YYEOF, YYEOF, 39, 39, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 33, YYEOF, 20, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 20, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 35, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 38, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 40, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 41, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 42, YYEOF, 42, YYEOF, 42, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 42, 42, YYEOF, YYEOF, YYEOF, YYEOF, 42, 42, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 43, YYEOF, 43, YYEOF, 43, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 43, 43, YYEOF, YYEOF, YYEOF, YYEOF, 43, 43, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 44, YYEOF, 44, YYEOF, 44, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, 44, 44, YYEOF, YYEOF, YYEOF, YYEOF, 44, 44, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF, YYEOF};
        ZZ_ERROR_MSG = new String[]{"Unkown internal scanner error", "Error: could not match input", "Error: pushback value was too large"};
        ZZ_ATTRIBUTE = zzUnpackAttribute();
    }

    private static int[] zzUnpackAction() {
        int[] result = new int[45];
        int offset = zzUnpackAction(ZZ_ACTION_PACKED_0, YYINITIAL, result);
        return result;
    }

    private static int zzUnpackAction(String packed, int offset, int[] result) {
        int j = offset;
        int l = packed.length();
        int i = YYINITIAL;
        while (i < l) {
            int j2;
            int i2 = i + ZZ_NO_MATCH;
            int count = packed.charAt(i);
            i = i2 + ZZ_NO_MATCH;
            int value = packed.charAt(i2);
            while (true) {
                j2 = j + ZZ_NO_MATCH;
                result[j] = value;
                count += YYEOF;
                if (count <= 0) {
                    break;
                }
                j = j2;
            }
            j = j2;
        }
        return j;
    }

    private static int[] zzUnpackRowMap() {
        int[] result = new int[45];
        int offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, YYINITIAL, result);
        return result;
    }

    private static int zzUnpackRowMap(String packed, int offset, int[] result) {
        int j = offset;
        int l = packed.length();
        int j2 = j;
        int i = YYINITIAL;
        while (i < l) {
            int i2 = i + ZZ_NO_MATCH;
            int high = packed.charAt(i) << 16;
            j = j2 + ZZ_NO_MATCH;
            i = i2 + ZZ_NO_MATCH;
            result[j2] = packed.charAt(i2) | high;
            j2 = j;
        }
        return j2;
    }

    private static int[] zzUnpackAttribute() {
        int[] result = new int[45];
        int offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, YYINITIAL, result);
        return result;
    }

    private static int zzUnpackAttribute(String packed, int offset, int[] result) {
        int j = offset;
        int l = packed.length();
        int i = YYINITIAL;
        while (i < l) {
            int j2;
            int i2 = i + ZZ_NO_MATCH;
            int count = packed.charAt(i);
            i = i2 + ZZ_NO_MATCH;
            int value = packed.charAt(i2);
            while (true) {
                j2 = j + ZZ_NO_MATCH;
                result[j] = value;
                count += YYEOF;
                if (count <= 0) {
                    break;
                }
                j = j2;
            }
            j = j2;
        }
        return j;
    }

    int getPosition() {
        return this.yychar;
    }

    Yylex(Reader in) {
        this.zzLexicalState = YYINITIAL;
        this.zzBuffer = new char[ZZ_BUFFERSIZE];
        this.zzAtBOL = true;
        this.sb = new StringBuffer();
        this.zzReader = in;
    }

    Yylex(InputStream in) {
        this(new InputStreamReader(in));
    }

    private static char[] zzUnpackCMap(String packed) {
        char[] map = new char[ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH];
        int j = YYINITIAL;
        int i = YYINITIAL;
        while (i < 90) {
            int j2;
            int i2 = i + ZZ_NO_MATCH;
            int count = packed.charAt(i);
            i = i2 + ZZ_NO_MATCH;
            char value = packed.charAt(i2);
            while (true) {
                j2 = j + ZZ_NO_MATCH;
                map[j] = value;
                count += YYEOF;
                if (count <= 0) {
                    break;
                }
                j = j2;
            }
            j = j2;
        }
        return map;
    }

    private boolean zzRefill() throws IOException {
        if (this.zzStartRead > 0) {
            System.arraycopy(this.zzBuffer, this.zzStartRead, this.zzBuffer, YYINITIAL, this.zzEndRead - this.zzStartRead);
            this.zzEndRead -= this.zzStartRead;
            this.zzCurrentPos -= this.zzStartRead;
            this.zzMarkedPos -= this.zzStartRead;
            this.zzStartRead = YYINITIAL;
        }
        if (this.zzCurrentPos >= this.zzBuffer.length) {
            char[] newBuffer = new char[(this.zzCurrentPos * ZZ_PUSHBACK_2BIG)];
            System.arraycopy(this.zzBuffer, YYINITIAL, newBuffer, YYINITIAL, this.zzBuffer.length);
            this.zzBuffer = newBuffer;
        }
        int numRead = this.zzReader.read(this.zzBuffer, this.zzEndRead, this.zzBuffer.length - this.zzEndRead);
        if (numRead > 0) {
            this.zzEndRead += numRead;
            return false;
        } else if (numRead != 0) {
            return true;
        } else {
            int c = this.zzReader.read();
            if (c == YYEOF) {
                return true;
            }
            char[] cArr = this.zzBuffer;
            int i = this.zzEndRead;
            this.zzEndRead = i + ZZ_NO_MATCH;
            cArr[i] = (char) c;
            return false;
        }
    }

    public final void yyclose() throws IOException {
        this.zzAtEOF = true;
        this.zzEndRead = this.zzStartRead;
        if (this.zzReader != null) {
            this.zzReader.close();
        }
    }

    public final void yyreset(Reader reader) {
        this.zzReader = reader;
        this.zzAtBOL = true;
        this.zzAtEOF = false;
        this.zzStartRead = YYINITIAL;
        this.zzEndRead = YYINITIAL;
        this.zzMarkedPos = YYINITIAL;
        this.zzCurrentPos = YYINITIAL;
        this.yycolumn = YYINITIAL;
        this.yychar = YYINITIAL;
        this.yyline = YYINITIAL;
        this.zzLexicalState = YYINITIAL;
    }

    public final int yystate() {
        return this.zzLexicalState;
    }

    public final void yybegin(int newState) {
        this.zzLexicalState = newState;
    }

    public final String yytext() {
        return new String(this.zzBuffer, this.zzStartRead, this.zzMarkedPos - this.zzStartRead);
    }

    public final char yycharat(int pos) {
        return this.zzBuffer[this.zzStartRead + pos];
    }

    public final int yylength() {
        return this.zzMarkedPos - this.zzStartRead;
    }

    private void zzScanError(int errorCode) {
        String message;
        try {
            message = ZZ_ERROR_MSG[errorCode];
        } catch (ArrayIndexOutOfBoundsException e) {
            message = ZZ_ERROR_MSG[YYINITIAL];
        }
        throw new Error(message);
    }

    public void yypushback(int number) {
        if (number > yylength()) {
            zzScanError(ZZ_PUSHBACK_2BIG);
        }
        this.zzMarkedPos -= number;
    }

    public Yytoken yylex() throws IOException, ParseException {
        int zzEndReadL = this.zzEndRead;
        char[] zzBufferL = this.zzBuffer;
        char[] zzCMapL = ZZ_CMAP;
        int[] zzTransL = ZZ_TRANS;
        int[] zzRowMapL = ZZ_ROWMAP;
        int[] zzAttrL = ZZ_ATTRIBUTE;
        while (true) {
            int zzMarkedPosL = this.zzMarkedPos;
            this.yychar += zzMarkedPosL - this.zzStartRead;
            int zzAction = YYEOF;
            this.zzStartRead = zzMarkedPosL;
            this.zzCurrentPos = zzMarkedPosL;
            int zzCurrentPosL = zzMarkedPosL;
            this.zzState = ZZ_LEXSTATE[this.zzLexicalState];
            int zzCurrentPosL2 = zzCurrentPosL;
            while (true) {
                int zzInput;
                if (zzCurrentPosL2 < zzEndReadL) {
                    zzCurrentPosL = zzCurrentPosL2 + ZZ_NO_MATCH;
                    zzInput = zzBufferL[zzCurrentPosL2];
                } else {
                    if (this.zzAtEOF) {
                        zzInput = YYEOF;
                        zzCurrentPosL = zzCurrentPosL2;
                    } else {
                        this.zzCurrentPos = zzCurrentPosL2;
                        this.zzMarkedPos = zzMarkedPosL;
                        boolean eof = zzRefill();
                        zzCurrentPosL = this.zzCurrentPos;
                        zzMarkedPosL = this.zzMarkedPos;
                        zzBufferL = this.zzBuffer;
                        zzEndReadL = this.zzEndRead;
                        if (eof) {
                            zzInput = YYEOF;
                        } else {
                            zzCurrentPosL2 = zzCurrentPosL + ZZ_NO_MATCH;
                            zzInput = zzBufferL[zzCurrentPosL];
                            zzCurrentPosL = zzCurrentPosL2;
                        }
                    }
                    this.zzMarkedPos = zzMarkedPosL;
                    if (zzAction >= 0) {
                        zzAction = ZZ_ACTION[zzAction];
                    }
                    switch (zzAction) {
                        case ZZ_NO_MATCH /*1*/:
                            throw new ParseException(this.yychar, YYINITIAL, new Character(yycharat(YYINITIAL)));
                        case ZZ_PUSHBACK_2BIG /*2*/:
                            return new Yytoken(YYINITIAL, Long.valueOf(yytext()));
                        case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                        case R.styleable.Theme_actionMenuTextAppearance /*25*/:
                        case R.styleable.Theme_actionMenuTextColor /*26*/:
                        case R.styleable.Theme_actionModeStyle /*27*/:
                        case R.styleable.Theme_actionModeCloseButtonStyle /*28*/:
                        case R.styleable.Theme_actionModeBackground /*29*/:
                        case R.styleable.Theme_actionModeSplitBackground /*30*/:
                        case R.styleable.Theme_actionModeCloseDrawable /*31*/:
                        case R.styleable.Theme_actionModeCutDrawable /*32*/:
                        case R.styleable.Theme_actionModeCopyDrawable /*33*/:
                        case R.styleable.Theme_actionModePasteDrawable /*34*/:
                        case R.styleable.Theme_actionModeSelectAllDrawable /*35*/:
                        case R.styleable.Theme_actionModeShareDrawable /*36*/:
                        case R.styleable.Theme_actionModeFindDrawable /*37*/:
                        case R.styleable.Theme_actionModeWebSearchDrawable /*38*/:
                        case R.styleable.Theme_actionModePopupWindowStyle /*39*/:
                        case R.styleable.Theme_textAppearanceLargePopupMenu /*40*/:
                        case R.styleable.Theme_textAppearanceSmallPopupMenu /*41*/:
                        case R.styleable.Theme_dialogTheme /*42*/:
                        case R.styleable.Theme_dialogPreferredPadding /*43*/:
                        case R.styleable.Theme_listDividerAlertDialog /*44*/:
                        case R.styleable.Theme_actionDropDownStyle /*45*/:
                        case R.styleable.Theme_dropdownListPreferredItemHeight /*46*/:
                        case R.styleable.Theme_spinnerDropDownItemStyle /*47*/:
                        case R.styleable.Theme_homeAsUpIndicator /*48*/:
                            break;
                        case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                            this.sb.delete(YYINITIAL, this.sb.length());
                            yybegin(ZZ_PUSHBACK_2BIG);
                            break;
                        case Yytoken.TYPE_COMMA /*5*/:
                            return new Yytoken(ZZ_NO_MATCH, null);
                        case Yytoken.TYPE_COLON /*6*/:
                            return new Yytoken(ZZ_PUSHBACK_2BIG, null);
                        case R.styleable.Toolbar_contentInsetLeft /*7*/:
                            return new Yytoken(3, null);
                        case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                            return new Yytoken(4, null);
                        case R.styleable.Toolbar_popupTheme /*9*/:
                            return new Yytoken(5, null);
                        case R.styleable.Toolbar_titleTextAppearance /*10*/:
                            return new Yytoken(6, null);
                        case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                            this.sb.append(yytext());
                            break;
                        case R.styleable.Toolbar_titleMargins /*12*/:
                            this.sb.append('\\');
                            break;
                        case R.styleable.Toolbar_titleMarginStart /*13*/:
                            yybegin(YYINITIAL);
                            return new Yytoken(YYINITIAL, this.sb.toString());
                        case R.styleable.Toolbar_titleMarginEnd /*14*/:
                            this.sb.append('\"');
                            break;
                        case R.styleable.Toolbar_titleMarginTop /*15*/:
                            this.sb.append('/');
                            break;
                        case R.styleable.Toolbar_titleMarginBottom /*16*/:
                            this.sb.append('\b');
                            break;
                        case R.styleable.Toolbar_maxButtonHeight /*17*/:
                            this.sb.append('\f');
                            break;
                        case R.styleable.Toolbar_collapseIcon /*18*/:
                            this.sb.append('\n');
                            break;
                        case R.styleable.Toolbar_collapseContentDescription /*19*/:
                            this.sb.append('\r');
                            break;
                        case UnityAdsProperties.MAX_BUFFERING_WAIT_SECONDS /*20*/:
                            this.sb.append('\t');
                            break;
                        case R.styleable.Toolbar_navigationContentDescription /*21*/:
                            return new Yytoken(YYINITIAL, Double.valueOf(yytext()));
                        case R.styleable.Toolbar_logoDescription /*22*/:
                            return new Yytoken(YYINITIAL, null);
                        case R.styleable.Toolbar_titleTextColor /*23*/:
                            return new Yytoken(YYINITIAL, Boolean.valueOf(yytext()));
                        case R.styleable.Toolbar_subtitleTextColor /*24*/:
                            try {
                                int ch = Integer.parseInt(yytext().substring(ZZ_PUSHBACK_2BIG), 16);
                                this.sb.append((char) ch);
                                break;
                            } catch (Exception e) {
                                throw new ParseException(this.yychar, ZZ_PUSHBACK_2BIG, e);
                            }
                        default:
                            if (zzInput == YYEOF || this.zzStartRead != this.zzCurrentPos) {
                                zzScanError(ZZ_NO_MATCH);
                                break;
                            }
                            this.zzAtEOF = true;
                            return null;
                    }
                }
                int zzNext = zzTransL[zzRowMapL[this.zzState] + zzCMapL[zzInput]];
                if (zzNext != YYEOF) {
                    this.zzState = zzNext;
                    int zzAttributes = zzAttrL[this.zzState];
                    if ((zzAttributes & ZZ_NO_MATCH) == ZZ_NO_MATCH) {
                        zzAction = this.zzState;
                        zzMarkedPosL = zzCurrentPosL;
                        if ((zzAttributes & 8) != 8) {
                        }
                    }
                    zzCurrentPosL2 = zzCurrentPosL;
                }
                this.zzMarkedPos = zzMarkedPosL;
                if (zzAction >= 0) {
                    zzAction = ZZ_ACTION[zzAction];
                }
                switch (zzAction) {
                    case ZZ_NO_MATCH /*1*/:
                        throw new ParseException(this.yychar, YYINITIAL, new Character(yycharat(YYINITIAL)));
                    case ZZ_PUSHBACK_2BIG /*2*/:
                        return new Yytoken(YYINITIAL, Long.valueOf(yytext()));
                    case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    case R.styleable.Theme_actionMenuTextAppearance /*25*/:
                    case R.styleable.Theme_actionMenuTextColor /*26*/:
                    case R.styleable.Theme_actionModeStyle /*27*/:
                    case R.styleable.Theme_actionModeCloseButtonStyle /*28*/:
                    case R.styleable.Theme_actionModeBackground /*29*/:
                    case R.styleable.Theme_actionModeSplitBackground /*30*/:
                    case R.styleable.Theme_actionModeCloseDrawable /*31*/:
                    case R.styleable.Theme_actionModeCutDrawable /*32*/:
                    case R.styleable.Theme_actionModeCopyDrawable /*33*/:
                    case R.styleable.Theme_actionModePasteDrawable /*34*/:
                    case R.styleable.Theme_actionModeSelectAllDrawable /*35*/:
                    case R.styleable.Theme_actionModeShareDrawable /*36*/:
                    case R.styleable.Theme_actionModeFindDrawable /*37*/:
                    case R.styleable.Theme_actionModeWebSearchDrawable /*38*/:
                    case R.styleable.Theme_actionModePopupWindowStyle /*39*/:
                    case R.styleable.Theme_textAppearanceLargePopupMenu /*40*/:
                    case R.styleable.Theme_textAppearanceSmallPopupMenu /*41*/:
                    case R.styleable.Theme_dialogTheme /*42*/:
                    case R.styleable.Theme_dialogPreferredPadding /*43*/:
                    case R.styleable.Theme_listDividerAlertDialog /*44*/:
                    case R.styleable.Theme_actionDropDownStyle /*45*/:
                    case R.styleable.Theme_dropdownListPreferredItemHeight /*46*/:
                    case R.styleable.Theme_spinnerDropDownItemStyle /*47*/:
                    case R.styleable.Theme_homeAsUpIndicator /*48*/:
                        break;
                    case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                        this.sb.delete(YYINITIAL, this.sb.length());
                        yybegin(ZZ_PUSHBACK_2BIG);
                        break;
                    case Yytoken.TYPE_COMMA /*5*/:
                        return new Yytoken(ZZ_NO_MATCH, null);
                    case Yytoken.TYPE_COLON /*6*/:
                        return new Yytoken(ZZ_PUSHBACK_2BIG, null);
                    case R.styleable.Toolbar_contentInsetLeft /*7*/:
                        return new Yytoken(3, null);
                    case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                        return new Yytoken(4, null);
                    case R.styleable.Toolbar_popupTheme /*9*/:
                        return new Yytoken(5, null);
                    case R.styleable.Toolbar_titleTextAppearance /*10*/:
                        return new Yytoken(6, null);
                    case R.styleable.Toolbar_subtitleTextAppearance /*11*/:
                        this.sb.append(yytext());
                        break;
                    case R.styleable.Toolbar_titleMargins /*12*/:
                        this.sb.append('\\');
                        break;
                    case R.styleable.Toolbar_titleMarginStart /*13*/:
                        yybegin(YYINITIAL);
                        return new Yytoken(YYINITIAL, this.sb.toString());
                    case R.styleable.Toolbar_titleMarginEnd /*14*/:
                        this.sb.append('\"');
                        break;
                    case R.styleable.Toolbar_titleMarginTop /*15*/:
                        this.sb.append('/');
                        break;
                    case R.styleable.Toolbar_titleMarginBottom /*16*/:
                        this.sb.append('\b');
                        break;
                    case R.styleable.Toolbar_maxButtonHeight /*17*/:
                        this.sb.append('\f');
                        break;
                    case R.styleable.Toolbar_collapseIcon /*18*/:
                        this.sb.append('\n');
                        break;
                    case R.styleable.Toolbar_collapseContentDescription /*19*/:
                        this.sb.append('\r');
                        break;
                    case UnityAdsProperties.MAX_BUFFERING_WAIT_SECONDS /*20*/:
                        this.sb.append('\t');
                        break;
                    case R.styleable.Toolbar_navigationContentDescription /*21*/:
                        return new Yytoken(YYINITIAL, Double.valueOf(yytext()));
                    case R.styleable.Toolbar_logoDescription /*22*/:
                        return new Yytoken(YYINITIAL, null);
                    case R.styleable.Toolbar_titleTextColor /*23*/:
                        return new Yytoken(YYINITIAL, Boolean.valueOf(yytext()));
                    case R.styleable.Toolbar_subtitleTextColor /*24*/:
                        int ch2 = Integer.parseInt(yytext().substring(ZZ_PUSHBACK_2BIG), 16);
                        this.sb.append((char) ch2);
                        break;
                    default:
                        if (zzInput == YYEOF) {
                            break;
                        }
                        zzScanError(ZZ_NO_MATCH);
                        break;
                }
            }
        }
    }
}
