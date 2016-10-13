package com.google.android.gms.games.internal.constants;

import org.json.simple.parser.Yytoken;

public final class PlatformType {
    private PlatformType() {
    }

    public static String zzgw(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
                return "ANDROID";
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "IOS";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "WEB_APP";
            default:
                throw new IllegalArgumentException("Unknown platform type: " + i);
        }
    }
}
