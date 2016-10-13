package org.json.simple.parser;

public class Yytoken {
    public static final int TYPE_COLON = 6;
    public static final int TYPE_COMMA = 5;
    public static final int TYPE_EOF = -1;
    public static final int TYPE_LEFT_BRACE = 1;
    public static final int TYPE_LEFT_SQUARE = 3;
    public static final int TYPE_RIGHT_BRACE = 2;
    public static final int TYPE_RIGHT_SQUARE = 4;
    public static final int TYPE_VALUE = 0;
    public int type;
    public Object value;

    public Yytoken(int type, Object value) {
        this.type = 0;
        this.value = null;
        this.type = type;
        this.value = value;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        switch (this.type) {
            case TYPE_EOF /*-1*/:
                sb.append("END OF FILE");
                break;
            case Yylex.YYINITIAL /*0*/:
                sb.append("VALUE(").append(this.value).append(")");
                break;
            case TYPE_LEFT_BRACE /*1*/:
                sb.append("LEFT BRACE({)");
                break;
            case TYPE_RIGHT_BRACE /*2*/:
                sb.append("RIGHT BRACE(})");
                break;
            case TYPE_LEFT_SQUARE /*3*/:
                sb.append("LEFT SQUARE([)");
                break;
            case TYPE_RIGHT_SQUARE /*4*/:
                sb.append("RIGHT SQUARE(])");
                break;
            case TYPE_COMMA /*5*/:
                sb.append("COMMA(,)");
                break;
            case TYPE_COLON /*6*/:
                sb.append("COLON(:)");
                break;
        }
        return sb.toString();
    }
}
