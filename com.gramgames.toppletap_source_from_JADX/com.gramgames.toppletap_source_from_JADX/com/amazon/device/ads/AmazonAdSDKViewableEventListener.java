package com.amazon.device.ads;

import com.amazon.device.ads.SDKEvent.SDKEventType;
import org.json.simple.parser.Yytoken;

class AmazonAdSDKViewableEventListener implements SDKEventListener {
    private static final String LOGTAG;
    private final MobileAdsLogger logger;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType;

        static {
            $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType = new int[SDKEventType.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.VIEWABLE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    static {
        LOGTAG = AmazonAdSDKViewableEventListener.class.getSimpleName();
    }

    public AmazonAdSDKViewableEventListener() {
        this(new MobileAdsLoggerFactory());
    }

    AmazonAdSDKViewableEventListener(MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        this.logger.d(sdkEvent.getEventType().toString());
        switch (1.$SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[sdkEvent.getEventType().ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                handleViewableEvent(adControlAccessor, sdkEvent);
            default:
        }
    }

    public void handleViewableEvent(AdControlAccessor adControlAccessor, SDKEvent sdkEvent) {
        adControlAccessor.injectJavascript("viewableBridge.viewabilityChange('" + sdkEvent.getParameter(ViewabilityObserver.VIEWABLE_PARAMS_KEY) + "');");
    }
}
