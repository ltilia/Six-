package org.json.simple;

import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONValue {
    public static Object parse(Reader in) {
        try {
            return new JSONParser().parse(in);
        } catch (Exception e) {
            return null;
        }
    }

    public static Object parse(String s) {
        return parse(new StringReader(s));
    }

    public static Object parseWithException(Reader in) throws IOException, ParseException {
        return new JSONParser().parse(in);
    }

    public static Object parseWithException(String s) throws ParseException {
        return new JSONParser().parse(s);
    }

    public static void writeJSONString(Object value, Writer out) throws IOException {
        if (value == null) {
            out.write("null");
        } else if (value instanceof String) {
            out.write(34);
            out.write(escape((String) value));
            out.write(34);
        } else if (value instanceof Double) {
            if (((Double) value).isInfinite() || ((Double) value).isNaN()) {
                out.write("null");
            } else {
                out.write(value.toString());
            }
        } else if (value instanceof Float) {
            if (((Float) value).isInfinite() || ((Float) value).isNaN()) {
                out.write("null");
            } else {
                out.write(value.toString());
            }
        } else if (value instanceof Number) {
            out.write(value.toString());
        } else if (value instanceof Boolean) {
            out.write(value.toString());
        } else if (value instanceof JSONStreamAware) {
            ((JSONStreamAware) value).writeJSONString(out);
        } else if (value instanceof JSONAware) {
            out.write(((JSONAware) value).toJSONString());
        } else if (value instanceof Map) {
            JSONObject.writeJSONString((Map) value, out);
        } else if (value instanceof List) {
            JSONArray.writeJSONString((List) value, out);
        } else {
            out.write(value.toString());
        }
    }

    public static String toJSONString(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return new StringBuffer().append("\"").append(escape((String) value)).append("\"").toString();
        }
        if (value instanceof Double) {
            if (((Double) value).isInfinite() || ((Double) value).isNaN()) {
                return "null";
            }
            return value.toString();
        } else if (value instanceof Float) {
            if (((Float) value).isInfinite() || ((Float) value).isNaN()) {
                return "null";
            }
            return value.toString();
        } else if (value instanceof Number) {
            return value.toString();
        } else {
            if (value instanceof Boolean) {
                return value.toString();
            }
            if (value instanceof JSONAware) {
                return ((JSONAware) value).toJSONString();
            }
            if (value instanceof Map) {
                return JSONObject.toJSONString((Map) value);
            }
            if (value instanceof List) {
                return JSONArray.toJSONString((List) value);
            }
            return value.toString();
        }
    }

    public static String escape(String s) {
        if (s == null) {
            return null;
        }
        StringBuffer sb = new StringBuffer();
        escape(s, sb);
        return sb.toString();
    }

    static void escape(String s, StringBuffer sb) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                    sb.append("\\b");
                    break;
                case R.styleable.Toolbar_popupTheme /*9*/:
                    sb.append("\\t");
                    break;
                case R.styleable.Toolbar_titleTextAppearance /*10*/:
                    sb.append("\\n");
                    break;
                case R.styleable.Toolbar_titleMargins /*12*/:
                    sb.append("\\f");
                    break;
                case R.styleable.Toolbar_titleMarginStart /*13*/:
                    sb.append("\\r");
                    break;
                case R.styleable.Theme_actionModePasteDrawable /*34*/:
                    sb.append("\\\"");
                    break;
                case R.styleable.Theme_spinnerDropDownItemStyle /*47*/:
                    sb.append("\\/");
                    break;
                case R.styleable.Theme_alertDialogButtonGroupStyle /*92*/:
                    sb.append("\\\\");
                    break;
                default:
                    if ((ch >= '\u0000' && ch <= '\u001f') || ((ch >= '\u007f' && ch <= '\u009f') || (ch >= '\u2000' && ch <= '\u20ff'))) {
                        String ss = Integer.toHexString(ch);
                        sb.append("\\u");
                        for (int k = 0; k < 4 - ss.length(); k++) {
                            sb.append('0');
                        }
                        sb.append(ss.toUpperCase());
                        break;
                    }
                    sb.append(ch);
                    break;
            }
        }
    }
}
