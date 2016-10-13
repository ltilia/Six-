package com.adjust.sdk;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.simple.parser.Yytoken;

public enum ActivityKind {
    UNKNOWN,
    SESSION,
    EVENT,
    CLICK,
    ATTRIBUTION,
    REVENUE,
    REATTRIBUTION;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$adjust$sdk$ActivityKind;

        static {
            $SwitchMap$com$adjust$sdk$ActivityKind = new int[ActivityKind.values().length];
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.EVENT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.CLICK.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.ATTRIBUTION.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static ActivityKind fromString(String string) {
        if ("session".equals(string)) {
            return SESSION;
        }
        if (NotificationCompatApi21.CATEGORY_EVENT.equals(string)) {
            return EVENT;
        }
        if ("click".equals(string)) {
            return CLICK;
        }
        if ("attribution".equals(string)) {
            return ATTRIBUTION;
        }
        return UNKNOWN;
    }

    public String toString() {
        switch (1.$SwitchMap$com$adjust$sdk$ActivityKind[ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return "session";
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return NotificationCompatApi21.CATEGORY_EVENT;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                return "click";
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return "attribution";
            default:
                return UnityAdsConstants.UNITY_ADS_DEVICEID_UNKNOWN;
        }
    }
}
