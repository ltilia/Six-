package android.support.v7.graphics.drawable;

import android.graphics.PorterDuff.Mode;
import android.os.Build.VERSION;
import com.unity3d.ads.android.R;
import org.json.simple.parser.Yytoken;

public class DrawableUtils {
    public static Mode parseTintMode(int value, Mode defaultMode) {
        switch (value) {
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return Mode.SRC_OVER;
            case Yytoken.TYPE_COMMA /*5*/:
                return Mode.SRC_IN;
            case R.styleable.Toolbar_popupTheme /*9*/:
                return Mode.SRC_ATOP;
            case R.styleable.Toolbar_titleMarginEnd /*14*/:
                return Mode.MULTIPLY;
            case R.styleable.Toolbar_titleMarginTop /*15*/:
                return Mode.SCREEN;
            case R.styleable.Toolbar_titleMarginBottom /*16*/:
                if (VERSION.SDK_INT >= 11) {
                    return Mode.valueOf("ADD");
                }
                return defaultMode;
            default:
                return defaultMode;
        }
    }
}
