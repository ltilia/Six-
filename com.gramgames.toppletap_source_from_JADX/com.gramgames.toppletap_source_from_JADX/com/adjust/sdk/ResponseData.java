package com.adjust.sdk;

import java.util.Locale;
import org.json.JSONObject;
import org.json.simple.parser.Yytoken;

public abstract class ResponseData {
    public ActivityKind activityKind;
    public String adid;
    public AdjustAttribution attribution;
    public JSONObject jsonResponse;
    public String message;
    public boolean success;
    public String timestamp;
    public boolean willRetry;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$adjust$sdk$ActivityKind;

        static {
            $SwitchMap$com$adjust$sdk$ActivityKind = new int[ActivityKind.values().length];
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.SESSION.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.ATTRIBUTION.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.EVENT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$com$adjust$sdk$ActivityKind[ActivityKind.CLICK.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public static ResponseData buildResponseData(ActivityPackage activityPackage) {
        ResponseData responseData;
        ActivityKind activityKind = activityPackage.getActivityKind();
        switch (1.$SwitchMap$com$adjust$sdk$ActivityKind[activityKind.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                responseData = new SessionResponseData();
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                responseData = new AttributionResponseData();
                break;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                responseData = new EventResponseData(activityPackage);
                break;
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                responseData = new ClickResponseData();
                break;
            default:
                responseData = new UnknownResponseData();
                break;
        }
        responseData.activityKind = activityKind;
        return responseData;
    }

    public String toString() {
        return String.format(Locale.US, "message:%s timestamp:%s json:%s", new Object[]{this.message, this.timestamp, this.jsonResponse});
    }
}
