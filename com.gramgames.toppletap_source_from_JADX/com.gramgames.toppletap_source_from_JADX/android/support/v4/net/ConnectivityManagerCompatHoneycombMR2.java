package android.support.v4.net;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.unity3d.ads.android.R;
import org.json.simple.parser.Yytoken;

class ConnectivityManagerCompatHoneycombMR2 {
    ConnectivityManagerCompatHoneycombMR2() {
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
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
            case R.styleable.Toolbar_popupTheme /*9*/:
                return false;
            default:
                return true;
        }
    }
}
