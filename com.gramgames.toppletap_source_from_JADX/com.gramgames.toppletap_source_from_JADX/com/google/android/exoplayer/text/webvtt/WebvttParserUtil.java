package com.google.android.exoplayer.text.webvtt;

import com.google.android.exoplayer.ParserException;
import com.google.android.exoplayer.util.ParsableByteArray;
import java.util.regex.Pattern;

public final class WebvttParserUtil {
    private static final Pattern HEADER;

    static {
        HEADER = Pattern.compile("^\ufeff?WEBVTT(( |\t).*)?$");
    }

    private WebvttParserUtil() {
    }

    public static void validateWebvttHeaderLine(ParsableByteArray input) throws ParserException {
        String line = input.readLine();
        if (line == null || !HEADER.matcher(line).matches()) {
            throw new ParserException("Expected WEBVTT. Got " + line);
        }
    }

    public static long parseTimestampUs(String timestamp) throws NumberFormatException {
        long value = 0;
        String[] parts = timestamp.split("\\.", 2);
        for (String parseLong : parts[0].split(":")) {
            value = (60 * value) + Long.parseLong(parseLong);
        }
        return ((value * 1000) + Long.parseLong(parts[1])) * 1000;
    }

    public static float parsePercentage(String s) throws NumberFormatException {
        if (s.endsWith("%")) {
            return Float.parseFloat(s.substring(0, s.length() - 1)) / 100.0f;
        }
        throw new NumberFormatException("Percentages must end with %");
    }
}
