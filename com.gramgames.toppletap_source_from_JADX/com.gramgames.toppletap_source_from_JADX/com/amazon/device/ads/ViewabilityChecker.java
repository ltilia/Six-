package com.amazon.device.ads;

import android.graphics.Rect;
import android.view.View;
import com.amazon.device.ads.MobileAdsLogger.Level;
import org.json.JSONException;
import org.json.JSONObject;

class ViewabilityChecker {
    static final String HEIGHT_AD = "height";
    static final String INSTRUMENTATION_URL = "instrumentationPixelUrl";
    static final String IS_AD_ONSCREEN = "isAdOnScreen";
    private static final String LOGTAG;
    static final String VIEWABLE_PERCENTAGE = "viewablePercentage";
    static final String WIDTH_AD = "width";
    static final String X_POSITION_AD = "x";
    static final String Y_POSITION_AD = "y";
    private final AdController adController;
    private float adTotalArea;
    private View adView;
    private final MobileAdsLogger logger;
    private ViewabilityOverlapCalculator viewabilityOverlapCalculator;

    static {
        LOGTAG = ViewabilityChecker.class.getSimpleName();
    }

    public ViewabilityChecker(AdController adController) {
        this(adController, new MobileAdsLoggerFactory(), new ViewabilityOverlapCalculator(adController));
    }

    ViewabilityChecker(AdController adController, MobileAdsLoggerFactory mobileAdsLoggerFactory, ViewabilityOverlapCalculator viewabilityOverlapCalculator) {
        this.adController = adController;
        this.logger = mobileAdsLoggerFactory.createMobileAdsLogger(LOGTAG);
        if (this.adController == null) {
            throw new IllegalArgumentException("AdController is null");
        }
        this.viewabilityOverlapCalculator = viewabilityOverlapCalculator;
    }

    public ViewabilityInfo getViewabilityInfo() {
        float visibleArea = 0.0f;
        Rect visibleAdRect = new Rect();
        this.adView = this.adController.getAdContainer().getCurrentAdView();
        if (this.adView == null) {
            this.adTotalArea = 0.0f;
        } else {
            this.adTotalArea = (float) (this.adView.getWidth() * this.adView.getHeight());
        }
        if (((double) this.adTotalArea) == 0.0d) {
            this.logger.w("AdView width and height not set");
            return null;
        }
        boolean isAdVisible = this.adView.getGlobalVisibleRect(visibleAdRect);
        boolean isAdShown = this.adView.isShown();
        boolean windowHasFocus = hasWindowFocus();
        boolean isAdTransparent = AndroidTargetUtils.isAdTransparent(this.adController.getAdContainer());
        if (isAdTransparent) {
            this.logger.forceLog(Level.WARN, "This ad view is transparent therefore it will not be considered viewable. Please ensure the ad view is completely opaque.", new Object[0]);
        }
        this.logger.d("IsAdVisible: %s, IsAdShown: %s, windowHasFocus: %s, IsAdTransparent: %s", Boolean.valueOf(isAdVisible), Boolean.valueOf(isAdShown), Boolean.valueOf(windowHasFocus), Boolean.valueOf(isAdTransparent));
        boolean isAdOnScreen = isAdVisible && isAdShown && windowHasFocus && !isAdTransparent;
        if (isAdOnScreen) {
            if (this.adController.isModal()) {
                visibleArea = 100.0f;
            } else {
                long beforeTime = System.currentTimeMillis();
                visibleArea = this.viewabilityOverlapCalculator.calculateViewablePercentage(this.adView, visibleAdRect);
                long afterTime = System.currentTimeMillis();
                this.logger.d("Total computation time: %d", Long.valueOf(afterTime - beforeTime));
            }
        }
        if (visibleArea == 0.0f) {
            isAdOnScreen = false;
        }
        return new ViewabilityInfo(isAdOnScreen, getJSONObject(visibleArea, isAdOnScreen, this.adView));
    }

    private JSONObject getJSONObject(float visibleArea, boolean isAdOnScreen, View adView) {
        JSONObject jsonObject = new JSONObject();
        int[] location = new int[2];
        try {
            jsonObject.put(VIEWABLE_PERCENTAGE, (double) visibleArea);
            jsonObject.put(WIDTH_AD, adView.getWidth());
            jsonObject.put(HEIGHT_AD, adView.getHeight());
            if (isAdOnScreen) {
                this.adView.getLocationOnScreen(location);
            }
            jsonObject.put(X_POSITION_AD, location[0]);
            jsonObject.put(Y_POSITION_AD, location[1]);
            return jsonObject;
        } catch (JSONException e) {
            this.logger.w("JSON Error occured %s", e.getMessage());
            return null;
        }
    }

    private boolean hasWindowFocus() {
        View rootView = this.adController.getRootView();
        if (rootView == null) {
            return false;
        }
        return rootView.hasWindowFocus();
    }
}
