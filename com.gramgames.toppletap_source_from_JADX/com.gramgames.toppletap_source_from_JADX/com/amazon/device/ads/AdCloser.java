package com.amazon.device.ads;

import com.amazon.device.ads.SDKEvent.SDKEventType;
import java.util.concurrent.atomic.AtomicBoolean;
import org.json.simple.parser.Yytoken;

class AdCloser {
    private static final String LOGTAG;
    private final AdController adController;
    private final AtomicBoolean isClosing;
    private final MobileAdsLogger logger;

    static {
        LOGTAG = AdCloser.class.getSimpleName();
    }

    public AdCloser(AdController adController) {
        this(adController, new MobileAdsLoggerFactory());
    }

    AdCloser(AdController adController, MobileAdsLoggerFactory loggerFactory) {
        this.isClosing = new AtomicBoolean(false);
        this.adController = adController;
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
    }

    public boolean closeAd() {
        this.logger.d("Ad is attempting to close.");
        boolean isClosed = false;
        if (!(this.adController.getAdState().equals(AdState.READY_TO_LOAD) || this.isClosing.getAndSet(true))) {
            boolean shouldClearAdActivity = false;
            boolean fireCloseEvent = false;
            switch (this.adController.getAdControlCallback().adClosing()) {
                case Yylex.YYINITIAL /*0*/:
                    fireCloseEvent = true;
                    break;
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    shouldClearAdActivity = true;
                    fireCloseEvent = true;
                    break;
            }
            if (fireCloseEvent) {
                this.adController.fireSDKEvent(new SDKEvent(SDKEventType.CLOSED));
                isClosed = true;
            }
            if (shouldClearAdActivity) {
                this.adController.resetToReady();
            }
            this.isClosing.set(false);
        }
        return isClosed;
    }
}
