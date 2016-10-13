package com.google.android.gms.games.internal.constants;

import org.json.simple.parser.Yytoken;

public final class TimeSpan {
    private TimeSpan() {
        throw new AssertionError("Uninstantiable");
    }

    public static String zzgw(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
                return "DAILY";
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "WEEKLY";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "ALL_TIME";
            default:
                throw new IllegalArgumentException("Unknown time span " + i);
        }
    }
}
