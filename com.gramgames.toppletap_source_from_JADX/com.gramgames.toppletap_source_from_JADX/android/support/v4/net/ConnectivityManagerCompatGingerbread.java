package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import org.json.simple.parser.Yytoken;

class ConnectivityManagerCompatGingerbread {
    ConnectivityManagerCompatGingerbread() {
    }

    public static boolean isActiveNetworkMetered(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info == null) {
            return true;
        }
        switch (info.getType()) {
            case Yylex.YYINITIAL /*0*/:
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
            case Yytoken.TYPE_COMMA /*5*/:
            case Yytoken.TYPE_COLON /*6*/:
                return true;
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return false;
            default:
                return true;
        }
    }
}
