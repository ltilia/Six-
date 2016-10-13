package com.facebook.ads.internal.ssp;

import android.content.Context;
import android.view.View;
import com.facebook.ads.AdSize;
import com.facebook.ads.internal.adapters.l;
import com.facebook.ads.internal.d;
import com.facebook.ads.internal.e;
import com.facebook.ads.internal.view.c;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public class ANAdRenderer {

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[AdSize.values().length];
            try {
                a[AdSize.INTERSTITIAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[AdSize.RECTANGLE_HEIGHT_250.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[AdSize.BANNER_HEIGHT_90.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[AdSize.BANNER_HEIGHT_50.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public interface Listener {
        void onAdClick();

        void onAdClose();

        void onAdError(Throwable th);

        void onAdImpression();
    }

    public static String getSupportedCapabilities() {
        return d.c();
    }

    public static int getTemplateID(int i, int i2) {
        AdSize fromWidthAndHeight = AdSize.fromWidthAndHeight(i, i2);
        if (fromWidthAndHeight == null) {
            return e.UNKNOWN.a();
        }
        switch (1.a[fromWidthAndHeight.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return e.WEBVIEW_INTERSTITIAL_UNKNOWN.a();
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return e.WEBVIEW_BANNER_250.a();
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return e.WEBVIEW_BANNER_90.a();
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return e.WEBVIEW_BANNER_50.a();
            default:
                return e.WEBVIEW_BANNER_LEGACY.a();
        }
    }

    public static View renderAd(Context context, JSONObject jSONObject, int i, int i2, int i3, Listener listener) {
        try {
            return new c(context, l.a(jSONObject), i3, listener);
        } catch (Throwable th) {
            listener.onAdError(th);
            return null;
        }
    }
}
