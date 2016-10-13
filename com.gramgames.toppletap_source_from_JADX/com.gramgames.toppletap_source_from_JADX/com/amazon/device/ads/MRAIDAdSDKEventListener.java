package com.amazon.device.ads;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.amazon.device.ads.SDKEvent.SDKEventType;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.UnityAdsDeviceLog;
import org.json.simple.parser.Yytoken;

class MRAIDAdSDKEventListener implements SDKEventListener {
    private static final String LOGTAG;
    private final MobileAdsLogger logger;
    private MRAIDAdSDKBridge mraidAdSDKBridge;

    class 1 implements OnGlobalLayoutListener {
        final /* synthetic */ AdControlAccessor val$adControlAccessor;

        1(AdControlAccessor adControlAccessor) {
            this.val$adControlAccessor = adControlAccessor;
        }

        public void onGlobalLayout() {
            Position currentPosition = this.val$adControlAccessor.getCurrentPosition();
            if (currentPosition != null) {
                this.val$adControlAccessor.removeOnGlobalLayoutListener(this);
                MRAIDAdSDKEventListener.this.mraidAdSDKBridge.updateDefaultPosition(currentPosition.getSize().getWidth(), currentPosition.getSize().getHeight(), currentPosition.getX(), currentPosition.getY());
                MRAIDAdSDKEventListener.this.mraidAdSDKBridge.orientationPropertyChange();
            }
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$AdState;
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType;

        static {
            $SwitchMap$com$amazon$device$ads$AdState = new int[AdState.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.EXPANDED.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.SHOWING.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$AdState[AdState.RENDERED.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType = new int[SDKEventType.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.RENDERED.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.PLACED.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.VISIBLE.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.CLOSED.ordinal()] = 4;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.RESIZED.ordinal()] = 5;
            } catch (NoSuchFieldError e8) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.HIDDEN.ordinal()] = 6;
            } catch (NoSuchFieldError e9) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.DESTROYED.ordinal()] = 7;
            } catch (NoSuchFieldError e10) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.BRIDGE_ADDED.ordinal()] = 8;
            } catch (NoSuchFieldError e11) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[SDKEventType.VIEWABLE.ordinal()] = 9;
            } catch (NoSuchFieldError e12) {
            }
        }
    }

    static {
        LOGTAG = MRAIDAdSDKEventListener.class.getSimpleName();
    }

    MRAIDAdSDKEventListener(MRAIDAdSDKBridge adBridge) {
        this.logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
        this.mraidAdSDKBridge = adBridge;
    }

    public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        this.logger.d(sdkEvent.getEventType().toString());
        switch (2.$SwitchMap$com$amazon$device$ads$SDKEvent$SDKEventType[sdkEvent.getEventType().ordinal()]) {
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                handleDefaultEvent(adControlAccessor);
                handleReadyEvent(adControlAccessor);
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                handleShowingEvent(adControlAccessor);
                handleDefaultEvent(adControlAccessor);
                handleReadyEvent(adControlAccessor);
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                handleClosedEvent(adControlAccessor);
            case Yytoken.TYPE_COMMA /*5*/:
                this.mraidAdSDKBridge.reportSizeChangeEvent();
            case Yytoken.TYPE_COLON /*6*/:
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
            case UnityAdsDeviceLog.LOGLEVEL_DEBUG /*8*/:
                handleBridgeAddedEvent(sdkEvent, adControlAccessor);
            case R.styleable.Toolbar_popupTheme /*9*/:
                handleViewableEvent(sdkEvent, adControlAccessor);
            default:
        }
    }

    private void handleViewableEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.viewableChange(" + sdkEvent.getParameter(ViewabilityObserver.IS_VIEWABLE_KEY) + ");");
    }

    private void handleBridgeAddedEvent(SDKEvent sdkEvent, AdControlAccessor adControlAccessor) {
        String bridgeName = sdkEvent.getParameter(SDKEvent.BRIDGE_NAME);
        if (bridgeName != null && bridgeName.equals(this.mraidAdSDKBridge.getName())) {
            switch (2.$SwitchMap$com$amazon$device$ads$AdState[adControlAccessor.getAdState().ordinal()]) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    handleShowingEvent(adControlAccessor);
                    handleDefaultEvent(adControlAccessor);
                    handleReadyEvent(adControlAccessor);
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                    if (!adControlAccessor.isModal()) {
                        handleDefaultEvent(adControlAccessor);
                        handleReadyEvent(adControlAccessor);
                    }
                default:
            }
        }
    }

    private void handleReadyEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.ready();");
    }

    private void handleShowingEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.addOnGlobalLayoutListener(new 1(adControlAccessor));
    }

    private void handleDefaultEvent(AdControlAccessor adControlAccessor) {
        adControlAccessor.injectJavascript("mraidBridge.stateChange('default');");
    }

    private void handleClosedEvent(AdControlAccessor adControlAccessor) {
        if (adControlAccessor.getAdState().equals(AdState.EXPANDED)) {
            this.mraidAdSDKBridge.collapseExpandedAd(adControlAccessor);
        } else if (adControlAccessor.getAdState().equals(AdState.SHOWING)) {
            adControlAccessor.injectJavascript("mraidBridge.stateChange('hidden');");
            adControlAccessor.injectJavascript("mraidBridge.viewableChange('false');");
        }
    }
}
