package com.google.android.gms.games.internal.constants;

import org.json.simple.parser.Yytoken;

public final class RequestUpdateResultOutcome {
    private RequestUpdateResultOutcome() {
    }

    public static boolean isValid(int outcome) {
        switch (outcome) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return true;
            default:
                return false;
        }
    }
}
