package com.google.android.gms.games.internal.constants;

import com.google.android.gms.games.internal.GamesLog;
import org.json.simple.parser.Yytoken;

public final class RequestType {
    private RequestType() {
    }

    public static String zzgw(int i) {
        switch (i) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "GIFT";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return "WISH";
            default:
                GamesLog.zzA("RequestType", "Unknown request type: " + i);
                return "UNKNOWN_TYPE";
        }
    }
}
