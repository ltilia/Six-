package com.amazon.device.ads;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.widget.RelativeLayout.LayoutParams;
import com.amazon.device.ads.AdActivity.AdActivityAdapter;
import com.amazon.device.ads.AdEvent.AdEventType;
import com.amazon.device.ads.JSONUtils.JSONUtilities;
import com.amazon.device.ads.SDKEvent.SDKEventType;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.simple.parser.Yytoken;

class ModalAdActivityAdapter implements AdActivityAdapter {
    private static final String CONTENT_DESCRIPTION_AD_CONTAINER_VIEW = "adContainerView";
    private static final String CONTENT_DESCRIPTION_EXPANSION_VIEW = "expansionView";
    private static final String LOGTAG;
    private Activity activity;
    private ViewGroup adContainerView;
    private AdControlAccessor adControlAccessor;
    private final AdUtils2 adUtils;
    private final AndroidBuildInfo buildInfo;
    private final ExpandProperties expandProperties;
    private ViewGroup expansionView;
    private final JSONUtilities jsonUtils;
    private Size lastReportedSize;
    private final LayoutFactory layoutFactory;
    private final MobileAdsLogger logger;
    private final OrientationProperties orientationProperties;
    private String url;
    private final ViewUtils viewUtils;

    class 1 implements OnGlobalLayoutListener {
        1() {
        }

        public void onGlobalLayout() {
            ModalAdActivityAdapter.this.viewUtils.removeOnGlobalLayoutListener(ModalAdActivityAdapter.this.expansionView.getViewTreeObserver(), this);
            Position currentPosition = ModalAdActivityAdapter.this.adControlAccessor.getCurrentPosition();
            if (currentPosition != null) {
                Size currentSize = currentPosition.getSize();
                if (currentSize != null && !currentSize.equals(ModalAdActivityAdapter.this.lastReportedSize)) {
                    ModalAdActivityAdapter.this.lastReportedSize = currentSize;
                    ModalAdActivityAdapter.this.adControlAccessor.injectJavascript("mraidBridge.sizeChange(" + currentSize.getWidth() + "," + currentSize.getHeight() + ");");
                }
            }
        }
    }

    static /* synthetic */ class 2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$device$ads$ForceOrientation;

        static {
            $SwitchMap$com$amazon$device$ads$ForceOrientation = new int[ForceOrientation.values().length];
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.PORTRAIT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.LANDSCAPE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$com$amazon$device$ads$ForceOrientation[ForceOrientation.NONE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    private class ModalAdSDKEventListener implements SDKEventListener {
        private ModalAdSDKEventListener() {
        }

        public void onSDKEvent(SDKEvent sdkEvent, AdControlAccessor controller) {
            if (sdkEvent.getEventType().equals(SDKEventType.CLOSED)) {
                ModalAdActivityAdapter.this.finishActivity();
            }
        }
    }

    static {
        LOGTAG = ModalAdActivityAdapter.class.getSimpleName();
    }

    public ModalAdActivityAdapter() {
        this(new MobileAdsLoggerFactory(), new AdUtils2(), new JSONUtilities(), new ExpandProperties(), new OrientationProperties(), new AndroidBuildInfo(), new LayoutFactory(), new ViewUtils());
    }

    ModalAdActivityAdapter(MobileAdsLoggerFactory loggerFactory, AdUtils2 adUtils, JSONUtilities jsonUtils, ExpandProperties expandProperties, OrientationProperties orientationProperties, AndroidBuildInfo buildInfo, LayoutFactory layoutFactory, ViewUtils viewUtils) {
        this.logger = loggerFactory.createMobileAdsLogger(LOGTAG);
        this.adUtils = adUtils;
        this.jsonUtils = jsonUtils;
        this.expandProperties = expandProperties;
        this.orientationProperties = orientationProperties;
        this.buildInfo = buildInfo;
        this.layoutFactory = layoutFactory;
        this.viewUtils = viewUtils;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
        this.activity.getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        AndroidTargetUtils.hideActionAndStatusBars(this.buildInfo, this.activity);
    }

    public void onCreate() {
        Intent intent = this.activity.getIntent();
        String url = intent.getStringExtra(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY);
        if (!StringUtils.isNullOrWhiteSpace(url)) {
            this.url = url;
        }
        this.expandProperties.fromJSONObject(this.jsonUtils.getJSONObjectFromString(intent.getStringExtra("expandProperties")));
        if (this.url != null) {
            this.expandProperties.setWidth(-1);
            this.expandProperties.setHeight(-1);
        }
        this.orientationProperties.fromJSONObject(this.jsonUtils.getJSONObjectFromString(intent.getStringExtra("orientationProperties")));
        AndroidTargetUtils.enableHardwareAcceleration(this.buildInfo, this.activity.getWindow());
        this.adControlAccessor = AdControllerFactory.getCachedAdControlAccessor();
        if (this.adControlAccessor == null) {
            this.logger.e("Failed to show expanded ad due to an error in the Activity.");
            this.activity.finish();
            return;
        }
        this.adControlAccessor.setAdActivity(this.activity);
        this.adControlAccessor.addSDKEventListener(new ModalAdSDKEventListener());
        expandAd();
        orientationPropertyChange();
        this.adControlAccessor.fireAdEvent(new AdEvent(AdEventType.EXPANDED));
        this.adControlAccessor.injectJavascript("mraidBridge.stateChange('expanded');");
        reportSizeChangeEvent();
    }

    private void reportSizeChangeEvent() {
        this.expansionView.getViewTreeObserver().addOnGlobalLayoutListener(new 1());
    }

    private void orientationPropertyChange() {
        if (!this.adControlAccessor.isVisible() || !this.adControlAccessor.isModal()) {
            return;
        }
        if (this.activity == null) {
            this.logger.e("unable to handle orientation property change because the context did not contain an activity");
            return;
        }
        int originalOrientation = this.activity.getRequestedOrientation();
        this.logger.d("Current Orientation: " + originalOrientation);
        switch (2.$SwitchMap$com$amazon$device$ads$ForceOrientation[this.orientationProperties.getForceOrientation().ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                this.activity.setRequestedOrientation(7);
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                this.activity.setRequestedOrientation(6);
                break;
        }
        if (ForceOrientation.NONE.equals(this.orientationProperties.getForceOrientation())) {
            if (this.orientationProperties.isAllowOrientationChange().booleanValue()) {
                this.activity.setRequestedOrientation(-1);
            } else {
                this.activity.setRequestedOrientation(DisplayUtils.determineCanonicalScreenOrientation(this.activity, this.buildInfo));
            }
        }
        int newOrientation = this.activity.getRequestedOrientation();
        this.logger.d("New Orientation: " + newOrientation);
        if (newOrientation != originalOrientation) {
            reportSizeChangeEvent();
        }
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
        if (this.activity.isFinishing() && this.adControlAccessor != null) {
            this.adControlAccessor.closeAd();
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        reportSizeChangeEvent();
    }

    public boolean onBackPressed() {
        if (this.adControlAccessor != null) {
            return this.adControlAccessor.onBackButtonPress();
        }
        return false;
    }

    public void onWindowFocusChanged() {
    }

    private void expandAd() {
        boolean z = true;
        if (this.url != null) {
            this.adControlAccessor.stashView();
        }
        Size expandSize = computeExpandedSizeInPixels(this.expandProperties);
        createExpandedView();
        this.adControlAccessor.moveViewToViewGroup(this.adContainerView, new LayoutParams(-1, -1), true);
        LayoutParams lp = new LayoutParams(expandSize.getWidth(), expandSize.getHeight());
        lp.addRule(13);
        this.expansionView.addView(this.adContainerView, lp);
        this.activity.setContentView(this.expansionView, new LayoutParams(-1, -1));
        AdControlAccessor adControlAccessor = this.adControlAccessor;
        if (this.expandProperties.getUseCustomClose().booleanValue()) {
            z = false;
        }
        adControlAccessor.enableCloseButton(z);
    }

    private void createExpandedView() {
        this.expansionView = this.layoutFactory.createLayout(this.activity, LayoutType.RELATIVE_LAYOUT, CONTENT_DESCRIPTION_EXPANSION_VIEW);
        this.adContainerView = this.layoutFactory.createLayout(this.activity, LayoutType.FRAME_LAYOUT, CONTENT_DESCRIPTION_AD_CONTAINER_VIEW);
    }

    private Size computeExpandedSizeInPixels(ExpandProperties expandProperties) {
        this.logger.d("Expanding Ad to " + expandProperties.getWidth() + "x" + expandProperties.getHeight());
        return new Size(this.adUtils.deviceIndependentPixelToPixel(expandProperties.getWidth()), this.adUtils.deviceIndependentPixelToPixel(expandProperties.getHeight()));
    }

    private void finishActivity() {
        if (!this.activity.isFinishing()) {
            this.adControlAccessor = null;
            this.activity.finish();
        }
    }
}
