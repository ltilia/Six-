package com.google.android.gms.games.internal.constants;

import org.json.simple.parser.Yytoken;

public final class MatchResult {
    public static boolean isValid(int result) {
        switch (result) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case Yytoken.TYPE_COMMA /*5*/:
                return true;
            default:
                return false;
        }
    }
}
