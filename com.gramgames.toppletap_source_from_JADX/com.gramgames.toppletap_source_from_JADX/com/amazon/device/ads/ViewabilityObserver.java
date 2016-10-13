package com.amazon.device.ads;

import android.annotation.TargetApi;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.ViewTreeObserver.OnWindowFocusChangeListener;
import com.amazon.device.ads.Configuration.ConfigOption;
import com.amazon.device.ads.SDKEvent.SDKEventType;
import com.facebook.internal.ServerProtocol;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import org.json.JSONObject;

class ViewabilityObserver {
    public static final String IS_VIEWABLE_KEY = "IS_VIEWABLE";
    private static final String LOGTAG;
    private static long VIEWABLE_INTERVAL = 0;
    public static final String VIEWABLE_PARAMS_KEY = "VIEWABLE_PARAMS";
    private final AdController adController;
    private final Configuration configuration;
    private final DebugProperties debugProperties;
    private boolean firedOnlyOnce;
    private final AtomicBoolean isScrollListenerAdded;
    private long lastTimeViewableEventFired;
    private final MobileAdsLogger logger;
    private boolean observersAdded;
    private final OnGlobalFocusChangeListener onGlobalFocusChangeListener;
    private final OnGlobalLayoutListener onGlobalLayoutListener;
    private final OnScrollChangedListener onScrollChangedListener;
    private OnWindowFocusChangeListener onWindowFocusChangeListener;
    private ViewTreeObserver viewTreeObserver;
    private final ViewUtils viewUtils;
    private final ViewabilityChecker viewabilityChecker;
    private final AtomicInteger viewabilityInterestCount;

    static {
        LOGTAG = ViewabilityObserver.class.getSimpleName();
        VIEWABLE_INTERVAL = 200;
    }

    public ViewabilityObserver(AdController adController) {
        this(adController, new ViewabilityCheckerFactory(), new MobileAdsLoggerFactory(), new AmazonOnGlobalFocusChangeListenerFactory(), new AmazonOnGlobalLayoutListenerFactory(), new AmazonOnScrollChangedListenerFactory(), new AmazonOnWindowFocusChangeListenerFactory(), new AtomicInteger(0), new AtomicBoolean(false), new ViewUtils(), DebugProperties.getInstance(), Configuration.getInstance());
    }

    ViewabilityObserver(AdController adController, ViewabilityCheckerFactory viewabilityCheckerFactory, MobileAdsLoggerFactory mobileAdsLoggerFactory, AmazonOnGlobalFocusChangeListenerFactory amazonOnGlobalFocusChangeListenerFactory, AmazonOnGlobalLayoutListenerFactory amazonOnGlobalLayoutListenerFactory, AmazonOnScrollChangedListenerFactory amazonOnScrollChangedListenerFactory, AmazonOnWindowFocusChangeListenerFactory amazonOnWindowFocusChangeListenerFactory, AtomicInteger viewabilityInterestCount, AtomicBoolean isScrollListenerAdded, ViewUtils viewUtils, DebugProperties debugProperties, Configuration configuration) {
        this.firedOnlyOnce = false;
        this.observersAdded = false;
        this.lastTimeViewableEventFired = 0;
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        this.viewabilityChecker = viewabilityCheckerFactory.buildViewabilityChecker(this.adController);
        this.onGlobalFocusChangeListener = amazonOnGlobalFocusChangeListenerFactory.buildAmazonOnGlobalFocusChangedListener(this);
        this.onGlobalLayoutListener = amazonOnGlobalLayoutListenerFactory.buildAmazonOnGlobalLayoutListener(this);
        this.onScrollChangedListener = amazonOnScrollChangedListenerFactory.buildAmazonOnScrollChangedListenerFactory(this);
        if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
            this.onWindowFocusChangeListener = amazonOnWindowFocusChangeListenerFactory.buildOnWindowFocusChangeListener(this);
        }
        this.viewabilityInterestCount = viewabilityInterestCount;
        this.isScrollListenerAdded = isScrollListenerAdded;
        this.viewUtils = viewUtils;
        this.debugProperties = debugProperties;
        this.configuration = configuration;
        VIEWABLE_INTERVAL = this.debugProperties.getDebugPropertyAsLong(DebugProperties.DEBUG_VIEWABLE_INTERVAL, Long.valueOf(this.configuration.getLongWithDefault(ConfigOption.VIEWABLE_INTERVAL, 200))).longValue();
    }

    public void registerViewabilityInterest() {
        int count = this.viewabilityInterestCount.incrementAndGet();
        this.logger.d("Viewability Interest Registered. Current number of objects interested in viewability: %d", Integer.valueOf(count));
        synchronized (this) {
            addObserversIfNeeded();
        }
    }

    @TargetApi(18)
    private void addObserversIfNeeded() {
        if (this.viewTreeObserver == null) {
            this.viewTreeObserver = this.adController.getAdContainer().getViewTreeObserver();
        }
        if (isViewTreeObserverAlive() && !this.observersAdded) {
            this.viewTreeObserver.addOnGlobalLayoutListener(this.onGlobalLayoutListener);
            this.viewTreeObserver.addOnGlobalFocusChangeListener(this.onGlobalFocusChangeListener);
            if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
                this.viewTreeObserver.addOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
            }
            this.observersAdded = true;
            fireViewableEvent(false);
        }
    }

    protected void addOnScrollChangedListenerIfNeeded() {
        if (!this.isScrollListenerAdded.get()) {
            if (this.viewTreeObserver == null || !this.viewTreeObserver.isAlive()) {
                this.viewTreeObserver = this.adController.getAdContainer().getViewTreeObserver();
            }
            this.viewTreeObserver.addOnScrollChangedListener(this.onScrollChangedListener);
            this.isScrollListenerAdded.set(true);
        }
    }

    public void deregisterViewabilityInterest() {
        synchronized (this) {
            int listenerCount = this.viewabilityInterestCount.decrementAndGet();
            if (listenerCount < 0) {
                this.logger.w("No Viewability Interest was previously registered. Ignoring request to deregister.");
                this.viewabilityInterestCount.incrementAndGet();
                return;
            }
            this.logger.d("Viewability Interest Deregistered. Current number of objects interested in viewability: %d", Integer.valueOf(listenerCount));
            if (listenerCount == 0) {
                removeObservers();
            }
        }
    }

    @TargetApi(18)
    private void removeObservers() {
        if (this.viewTreeObserver == null) {
            this.logger.w("Root view tree observer is null");
        } else if (this.viewUtils.removeOnGlobalLayoutListener(this.viewTreeObserver, this.onGlobalLayoutListener)) {
            this.viewTreeObserver.removeOnScrollChangedListener(this.onScrollChangedListener);
            this.viewTreeObserver.removeOnGlobalFocusChangeListener(this.onGlobalFocusChangeListener);
            if (AndroidTargetUtils.isAtLeastAndroidAPI(18)) {
                this.viewTreeObserver.removeOnWindowFocusChangeListener(this.onWindowFocusChangeListener);
            }
            this.observersAdded = false;
            this.isScrollListenerAdded.set(false);
        } else {
            this.logger.w("Root view tree observer is not alive");
        }
    }

    public void fireViewableEvent(boolean isScrollEvent) {
        long currentTime = System.currentTimeMillis();
        if (!isScrollEvent || currentTime - this.lastTimeViewableEventFired >= VIEWABLE_INTERVAL) {
            this.lastTimeViewableEventFired = currentTime;
            ViewabilityInfo viewabilityInfo = this.viewabilityChecker.getViewabilityInfo();
            if (viewabilityInfo == null) {
                this.logger.w("Viewable info is null");
                return;
            }
            JSONObject viewableParams = viewabilityInfo.getJsonObject();
            boolean isAdOnScreen = viewabilityInfo.isAdOnScreen();
            SDKEvent viewableSDKEvent = new SDKEvent(SDKEventType.VIEWABLE);
            viewableSDKEvent.setParameter(VIEWABLE_PARAMS_KEY, viewableParams.toString());
            viewableSDKEvent.setParameter(IS_VIEWABLE_KEY, isAdOnScreen ? ServerProtocol.DIALOG_RETURN_SCOPES_TRUE : " false");
            if (isAdOnScreen) {
                this.adController.fireSDKEvent(viewableSDKEvent);
                this.firedOnlyOnce = false;
            } else if (!this.firedOnlyOnce) {
                this.adController.fireSDKEvent(viewableSDKEvent);
                this.firedOnlyOnce = true;
            }
        }
    }

    private boolean isViewTreeObserverAlive() {
        if (this.viewTreeObserver.isAlive()) {
            return true;
        }
        this.logger.w("Root view tree observer is not alive");
        return false;
    }

    public boolean isViewable() {
        ViewabilityInfo viewabilityInfo = this.viewabilityChecker.getViewabilityInfo();
        if (viewabilityInfo != null) {
            return viewabilityInfo.isAdOnScreen();
        }
        this.logger.w("Viewable info is null");
        return false;
    }
}
