package com.google.android.gms.games.internal.constants;

import org.json.simple.parser.Yytoken;

public final class LeaderboardCollection {
    private LeaderboardCollection() {
    }

    public static String zzgw(int i) {
        switch (i) {
            case Yylex.YYINITIAL /*0*/:
                return "PUBLIC";
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "SOCIAL";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "SOCIAL_1P";
            default:
                throw new IllegalArgumentException("Unknown leaderboard collection: " + i);
        }
    }
}
