package com.mopub.mraid;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Rect;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.view.WindowManager;
import android.webkit.ConsoleMessage;
import android.webkit.JsResult;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.mopub.common.AdReport;
import com.mopub.common.CloseableLayout;
import com.mopub.common.CloseableLayout.ClosePosition;
import com.mopub.common.CloseableLayout.OnCloseListener;
import com.mopub.common.Preconditions;
import com.mopub.common.UrlAction;
import com.mopub.common.UrlHandler.Builder;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.DeviceUtils;
import com.mopub.common.util.Dips;
import com.mopub.common.util.Utils;
import com.mopub.common.util.Views;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.mopub.mobileads.util.WebViews;
import com.mopub.mraid.MraidBridge.MraidBridgeListener;
import com.mopub.mraid.MraidBridge.MraidWebView;
import java.lang.ref.WeakReference;
import java.net.URI;

public class MraidController {
    private final AdReport mAdReport;
    private boolean mAllowOrientationChange;
    @NonNull
    private final CloseableLayout mCloseableAdContainer;
    @NonNull
    private final Context mContext;
    @Nullable
    private MraidWebViewDebugListener mDebugListener;
    @NonNull
    private final FrameLayout mDefaultAdContainer;
    private MraidOrientation mForceOrientation;
    private boolean mIsPaused;
    @NonNull
    private final MraidBridge mMraidBridge;
    private final MraidBridgeListener mMraidBridgeListener;
    @Nullable
    private MraidListener mMraidListener;
    private final MraidNativeCommandHandler mMraidNativeCommandHandler;
    @Nullable
    private MraidWebView mMraidWebView;
    @Nullable
    private UseCustomCloseListener mOnCloseButtonListener;
    @NonNull
    private OrientationBroadcastReceiver mOrientationBroadcastReceiver;
    @Nullable
    private Integer mOriginalActivityOrientation;
    @NonNull
    private final PlacementType mPlacementType;
    @Nullable
    private ViewGroup mRootView;
    @NonNull
    private final MraidScreenMetrics mScreenMetrics;
    @NonNull
    private final ScreenMetricsWaiter mScreenMetricsWaiter;
    @NonNull
    private final MraidBridge mTwoPartBridge;
    private final MraidBridgeListener mTwoPartBridgeListener;
    @Nullable
    private MraidWebView mTwoPartWebView;
    @NonNull
    private ViewState mViewState;
    @NonNull
    private final WeakReference<Activity> mWeakActivity;

    public interface MraidListener {
        void onClose();

        void onExpand();

        void onFailedToLoad();

        void onLoaded(View view);

        void onOpen();
    }

    public interface UseCustomCloseListener {
        void useCustomCloseChanged(boolean z);
    }

    class 1 implements OnCloseListener {
        1() {
        }

        public void onClose() {
            MraidController.this.handleClose();
        }
    }

    class 2 implements OnTouchListener {
        2() {
        }

        public boolean onTouch(View v, MotionEvent event) {
            return true;
        }
    }

    class 3 implements MraidBridgeListener {
        3() {
        }

        public void onPageLoaded() {
            MraidController.this.handlePageLoad();
        }

        public void onPageFailedToLoad() {
            if (MraidController.this.mMraidListener != null) {
                MraidController.this.mMraidListener.onFailedToLoad();
            }
        }

        public void onVisibilityChanged(boolean isVisible) {
            if (!MraidController.this.mTwoPartBridge.isAttached()) {
                MraidController.this.mMraidBridge.notifyViewability(isVisible);
            }
        }

        public boolean onJsAlert(@NonNull String message, @NonNull JsResult result) {
            return MraidController.this.handleJsAlert(message, result);
        }

        public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
            return MraidController.this.handleConsoleMessage(consoleMessage);
        }

        public void onClose() {
            MraidController.this.handleClose();
        }

        public void onResize(int width, int height, int offsetX, int offsetY, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
            MraidController.this.handleResize(width, height, offsetX, offsetY, closePosition, allowOffscreen);
        }

        public void onExpand(@Nullable URI uri, boolean shouldUseCustomClose) throws MraidCommandException {
            MraidController.this.handleExpand(uri, shouldUseCustomClose);
        }

        public void onUseCustomClose(boolean shouldUseCustomClose) {
            MraidController.this.handleCustomClose(shouldUseCustomClose);
        }

        public void onSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
            MraidController.this.handleSetOrientationProperties(allowOrientationChange, forceOrientation);
        }

        public void onOpen(@NonNull URI uri) {
            MraidController.this.handleOpen(uri.toString());
        }

        public void onPlayVideo(@NonNull URI uri) {
            MraidController.this.handleShowVideo(uri.toString());
        }
    }

    class 4 implements MraidBridgeListener {
        4() {
        }

        public void onPageLoaded() {
            MraidController.this.handleTwoPartPageLoad();
        }

        public void onPageFailedToLoad() {
        }

        public void onVisibilityChanged(boolean isVisible) {
            MraidController.this.mMraidBridge.notifyViewability(isVisible);
            MraidController.this.mTwoPartBridge.notifyViewability(isVisible);
        }

        public boolean onJsAlert(@NonNull String message, @NonNull JsResult result) {
            return MraidController.this.handleJsAlert(message, result);
        }

        public boolean onConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
            return MraidController.this.handleConsoleMessage(consoleMessage);
        }

        public void onResize(int width, int height, int offsetX, int offsetY, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
            throw new MraidCommandException("Not allowed to resize from an expanded state");
        }

        public void onExpand(@Nullable URI uri, boolean shouldUseCustomClose) {
        }

        public void onClose() {
            MraidController.this.handleClose();
        }

        public void onUseCustomClose(boolean shouldUseCustomClose) {
            MraidController.this.handleCustomClose(shouldUseCustomClose);
        }

        public void onSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
            MraidController.this.handleSetOrientationProperties(allowOrientationChange, forceOrientation);
        }

        public void onOpen(URI uri) {
            MraidController.this.handleOpen(uri.toString());
        }

        public void onPlayVideo(@NonNull URI uri) {
            MraidController.this.handleShowVideo(uri.toString());
        }
    }

    class 5 implements Runnable {
        5() {
        }

        public void run() {
            MraidController.this.mMraidBridge.notifySupports(MraidController.this.mMraidNativeCommandHandler.isSmsAvailable(MraidController.this.mContext), MraidController.this.mMraidNativeCommandHandler.isTelAvailable(MraidController.this.mContext), MraidNativeCommandHandler.isCalendarAvailable(MraidController.this.mContext), MraidNativeCommandHandler.isStorePictureSupported(MraidController.this.mContext), MraidController.this.isInlineVideoAvailable());
            MraidController.this.mMraidBridge.notifyPlacementType(MraidController.this.mPlacementType);
            MraidController.this.mMraidBridge.notifyViewability(MraidController.this.mMraidBridge.isVisible());
            MraidController.this.mMraidBridge.notifyReady();
        }
    }

    class 6 implements Runnable {
        6() {
        }

        public void run() {
            MraidBridge access$100 = MraidController.this.mTwoPartBridge;
            boolean isSmsAvailable = MraidController.this.mMraidNativeCommandHandler.isSmsAvailable(MraidController.this.mContext);
            boolean isTelAvailable = MraidController.this.mMraidNativeCommandHandler.isTelAvailable(MraidController.this.mContext);
            MraidController.this.mMraidNativeCommandHandler;
            boolean isCalendarAvailable = MraidNativeCommandHandler.isCalendarAvailable(MraidController.this.mContext);
            MraidController.this.mMraidNativeCommandHandler;
            access$100.notifySupports(isSmsAvailable, isTelAvailable, isCalendarAvailable, MraidNativeCommandHandler.isStorePictureSupported(MraidController.this.mContext), MraidController.this.isInlineVideoAvailable());
            MraidController.this.mTwoPartBridge.notifyViewState(MraidController.this.mViewState);
            MraidController.this.mTwoPartBridge.notifyPlacementType(MraidController.this.mPlacementType);
            MraidController.this.mTwoPartBridge.notifyViewability(MraidController.this.mTwoPartBridge.isVisible());
            MraidController.this.mTwoPartBridge.notifyReady();
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ View val$currentWebView;
        final /* synthetic */ Runnable val$successRunnable;

        7(View view, Runnable runnable) {
            this.val$currentWebView = view;
            this.val$successRunnable = runnable;
        }

        public void run() {
            DisplayMetrics displayMetrics = MraidController.this.mContext.getResources().getDisplayMetrics();
            MraidController.this.mScreenMetrics.setScreenSize(displayMetrics.widthPixels, displayMetrics.heightPixels);
            int[] location = new int[2];
            View rootView = MraidController.this.getRootView();
            rootView.getLocationOnScreen(location);
            MraidController.this.mScreenMetrics.setRootViewPosition(location[0], location[1], rootView.getWidth(), rootView.getHeight());
            MraidController.this.mDefaultAdContainer.getLocationOnScreen(location);
            MraidController.this.mScreenMetrics.setDefaultAdPosition(location[0], location[1], MraidController.this.mDefaultAdContainer.getWidth(), MraidController.this.mDefaultAdContainer.getHeight());
            this.val$currentWebView.getLocationOnScreen(location);
            MraidController.this.mScreenMetrics.setCurrentAdPosition(location[0], location[1], this.val$currentWebView.getWidth(), this.val$currentWebView.getHeight());
            MraidController.this.mMraidBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
            if (MraidController.this.mTwoPartBridge.isAttached()) {
                MraidController.this.mTwoPartBridge.notifyScreenMetrics(MraidController.this.mScreenMetrics);
            }
            if (this.val$successRunnable != null) {
                this.val$successRunnable.run();
            }
        }
    }

    @VisibleForTesting
    class OrientationBroadcastReceiver extends BroadcastReceiver {
        @Nullable
        private Context mContext;
        private int mLastRotation;

        OrientationBroadcastReceiver() {
            this.mLastRotation = -1;
        }

        public void onReceive(Context context, Intent intent) {
            if (this.mContext != null && "android.intent.action.CONFIGURATION_CHANGED".equals(intent.getAction())) {
                int orientation = MraidController.this.getDisplayRotation();
                if (orientation != this.mLastRotation) {
                    this.mLastRotation = orientation;
                    MraidController.this.handleOrientationChange(this.mLastRotation);
                }
            }
        }

        public void register(@NonNull Context context) {
            Preconditions.checkNotNull(context);
            this.mContext = context.getApplicationContext();
            if (this.mContext != null) {
                this.mContext.registerReceiver(this, new IntentFilter("android.intent.action.CONFIGURATION_CHANGED"));
            }
        }

        public void unregister() {
            if (this.mContext != null) {
                this.mContext.unregisterReceiver(this);
                this.mContext = null;
            }
        }
    }

    @VisibleForTesting
    static class ScreenMetricsWaiter {
        @NonNull
        private final Handler mHandler;
        @Nullable
        private WaitRequest mLastWaitRequest;

        static class WaitRequest {
            @NonNull
            private final Handler mHandler;
            @Nullable
            private Runnable mSuccessRunnable;
            @NonNull
            private final View[] mViews;
            int mWaitCount;
            private final Runnable mWaitingRunnable;

            class 1 implements Runnable {

                class 1 implements OnPreDrawListener {
                    final /* synthetic */ View val$view;

                    1(View view) {
                        this.val$view = view;
                    }

                    public boolean onPreDraw() {
                        this.val$view.getViewTreeObserver().removeOnPreDrawListener(this);
                        WaitRequest.this.countDown();
                        return true;
                    }
                }

                1() {
                }

                public void run() {
                    for (View view : WaitRequest.this.mViews) {
                        if (view.getHeight() > 0 || view.getWidth() > 0) {
                            WaitRequest.this.countDown();
                        } else {
                            view.getViewTreeObserver().addOnPreDrawListener(new 1(view));
                        }
                    }
                }
            }

            private WaitRequest(@NonNull Handler handler, @NonNull View[] views) {
                this.mWaitingRunnable = new 1();
                this.mHandler = handler;
                this.mViews = views;
            }

            private void countDown() {
                this.mWaitCount--;
                if (this.mWaitCount == 0 && this.mSuccessRunnable != null) {
                    this.mSuccessRunnable.run();
                    this.mSuccessRunnable = null;
                }
            }

            void start(@NonNull Runnable successRunnable) {
                this.mSuccessRunnable = successRunnable;
                this.mWaitCount = this.mViews.length;
                this.mHandler.post(this.mWaitingRunnable);
            }

            void cancel() {
                this.mHandler.removeCallbacks(this.mWaitingRunnable);
                this.mSuccessRunnable = null;
            }
        }

        ScreenMetricsWaiter() {
            this.mHandler = new Handler();
        }

        WaitRequest waitFor(@NonNull View... views) {
            this.mLastWaitRequest = new WaitRequest(views, null);
            return this.mLastWaitRequest;
        }

        void cancelLastRequest() {
            if (this.mLastWaitRequest != null) {
                this.mLastWaitRequest.cancel();
                this.mLastWaitRequest = null;
            }
        }
    }

    public MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType) {
        this(context, adReport, placementType, new MraidBridge(adReport, placementType), new MraidBridge(adReport, PlacementType.INTERSTITIAL), new ScreenMetricsWaiter());
    }

    @VisibleForTesting
    MraidController(@NonNull Context context, @Nullable AdReport adReport, @NonNull PlacementType placementType, @NonNull MraidBridge bridge, @NonNull MraidBridge twoPartBridge, @NonNull ScreenMetricsWaiter screenMetricsWaiter) {
        this.mViewState = ViewState.LOADING;
        this.mOrientationBroadcastReceiver = new OrientationBroadcastReceiver();
        this.mAllowOrientationChange = true;
        this.mForceOrientation = MraidOrientation.NONE;
        this.mMraidBridgeListener = new 3();
        this.mTwoPartBridgeListener = new 4();
        this.mContext = context.getApplicationContext();
        Preconditions.checkNotNull(this.mContext);
        this.mAdReport = adReport;
        if (context instanceof Activity) {
            this.mWeakActivity = new WeakReference((Activity) context);
        } else {
            this.mWeakActivity = new WeakReference(null);
        }
        this.mPlacementType = placementType;
        this.mMraidBridge = bridge;
        this.mTwoPartBridge = twoPartBridge;
        this.mScreenMetricsWaiter = screenMetricsWaiter;
        this.mViewState = ViewState.LOADING;
        this.mScreenMetrics = new MraidScreenMetrics(this.mContext, this.mContext.getResources().getDisplayMetrics().density);
        this.mDefaultAdContainer = new FrameLayout(this.mContext);
        this.mCloseableAdContainer = new CloseableLayout(this.mContext);
        this.mCloseableAdContainer.setOnCloseListener(new 1());
        View dimmingView = new View(this.mContext);
        dimmingView.setOnTouchListener(new 2());
        this.mCloseableAdContainer.addView(dimmingView, new LayoutParams(-1, -1));
        this.mOrientationBroadcastReceiver.register(this.mContext);
        this.mMraidBridge.setMraidBridgeListener(this.mMraidBridgeListener);
        this.mTwoPartBridge.setMraidBridgeListener(this.mTwoPartBridgeListener);
        this.mMraidNativeCommandHandler = new MraidNativeCommandHandler();
    }

    public void setMraidListener(@Nullable MraidListener mraidListener) {
        this.mMraidListener = mraidListener;
    }

    public void setUseCustomCloseListener(@Nullable UseCustomCloseListener listener) {
        this.mOnCloseButtonListener = listener;
    }

    public void setDebugListener(@Nullable MraidWebViewDebugListener debugListener) {
        this.mDebugListener = debugListener;
    }

    public void loadContent(@NonNull String htmlData) {
        Preconditions.checkState(this.mMraidWebView == null, "loadContent should only be called once");
        this.mMraidWebView = new MraidWebView(this.mContext);
        this.mMraidBridge.attachView(this.mMraidWebView);
        this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
        this.mMraidBridge.setContentHtml(htmlData);
    }

    private int getDisplayRotation() {
        return ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    }

    @VisibleForTesting
    boolean handleConsoleMessage(@NonNull ConsoleMessage consoleMessage) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onConsoleMessage(consoleMessage);
        }
        return true;
    }

    @VisibleForTesting
    boolean handleJsAlert(@NonNull String message, @NonNull JsResult result) {
        if (this.mDebugListener != null) {
            return this.mDebugListener.onJsAlert(message, result);
        }
        result.confirm();
        return true;
    }

    @Nullable
    private View getCurrentWebView() {
        return this.mTwoPartBridge.isAttached() ? this.mTwoPartWebView : this.mMraidWebView;
    }

    private boolean isInlineVideoAvailable() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || getCurrentWebView() == null) {
            return false;
        }
        return this.mMraidNativeCommandHandler.isInlineVideoAvailable(activity, getCurrentWebView());
    }

    @VisibleForTesting
    void handlePageLoad() {
        setViewState(ViewState.DEFAULT, new 5());
        if (this.mMraidListener != null) {
            this.mMraidListener.onLoaded(this.mDefaultAdContainer);
        }
    }

    @VisibleForTesting
    void handleTwoPartPageLoad() {
        updateScreenMetricsAsync(new 6());
    }

    private void updateScreenMetricsAsync(@Nullable Runnable successRunnable) {
        this.mScreenMetricsWaiter.cancelLastRequest();
        View currentWebView = getCurrentWebView();
        if (currentWebView != null) {
            this.mScreenMetricsWaiter.waitFor(this.mDefaultAdContainer, currentWebView).start(new 7(currentWebView, successRunnable));
        }
    }

    void handleOrientationChange(int currentRotation) {
        updateScreenMetricsAsync(null);
    }

    public void pause(boolean isFinishing) {
        this.mIsPaused = true;
        if (this.mMraidWebView != null) {
            WebViews.onPause(this.mMraidWebView, isFinishing);
        }
        if (this.mTwoPartWebView != null) {
            WebViews.onPause(this.mTwoPartWebView, isFinishing);
        }
    }

    public void resume() {
        this.mIsPaused = false;
        if (this.mMraidWebView != null) {
            WebViews.onResume(this.mMraidWebView);
        }
        if (this.mTwoPartWebView != null) {
            WebViews.onResume(this.mTwoPartWebView);
        }
    }

    public void destroy() {
        this.mScreenMetricsWaiter.cancelLastRequest();
        try {
            this.mOrientationBroadcastReceiver.unregister();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().contains("Receiver not registered")) {
                throw e;
            }
        }
        if (!this.mIsPaused) {
            pause(true);
        }
        Views.removeFromParent(this.mCloseableAdContainer);
        this.mMraidBridge.detach();
        if (this.mMraidWebView != null) {
            this.mMraidWebView.destroy();
            this.mMraidWebView = null;
        }
        this.mTwoPartBridge.detach();
        if (this.mTwoPartWebView != null) {
            this.mTwoPartWebView.destroy();
            this.mTwoPartWebView = null;
        }
    }

    private void setViewState(@NonNull ViewState viewState) {
        setViewState(viewState, null);
    }

    private void setViewState(@NonNull ViewState viewState, @Nullable Runnable successRunnable) {
        MoPubLog.d("MRAID state set to " + viewState);
        this.mViewState = viewState;
        this.mMraidBridge.notifyViewState(viewState);
        if (this.mTwoPartBridge.isLoaded()) {
            this.mTwoPartBridge.notifyViewState(viewState);
        }
        if (this.mMraidListener != null) {
            if (viewState == ViewState.EXPANDED) {
                this.mMraidListener.onExpand();
            } else if (viewState == ViewState.HIDDEN) {
                this.mMraidListener.onClose();
            }
        }
        updateScreenMetricsAsync(successRunnable);
    }

    int clampInt(int min, int target, int max) {
        return Math.max(min, Math.min(target, max));
    }

    @VisibleForTesting
    void handleResize(int widthDips, int heightDips, int offsetXDips, int offsetYDips, @NonNull ClosePosition closePosition, boolean allowOffscreen) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to resize after the WebView is destroyed");
        } else if (this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED) {
                throw new MraidCommandException("Not allowed to resize from an already expanded ad");
            } else if (this.mPlacementType == PlacementType.INTERSTITIAL) {
                throw new MraidCommandException("Not allowed to resize from an interstitial ad");
            } else {
                int width = Dips.dipsToIntPixels((float) widthDips, this.mContext);
                int height = Dips.dipsToIntPixels((float) heightDips, this.mContext);
                int offsetX = Dips.dipsToIntPixels((float) offsetXDips, this.mContext);
                int left = this.mScreenMetrics.getDefaultAdRect().left + offsetX;
                int top = this.mScreenMetrics.getDefaultAdRect().top + Dips.dipsToIntPixels((float) offsetYDips, this.mContext);
                Rect resizeRect = new Rect(left, top, left + width, top + height);
                if (!allowOffscreen) {
                    Rect bounds = this.mScreenMetrics.getRootViewRect();
                    if (resizeRect.width() > bounds.width() || resizeRect.height() > bounds.height()) {
                        throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + heightDips + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that doesn't allow the ad to" + " appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                    }
                    resizeRect.offsetTo(clampInt(bounds.left, resizeRect.left, bounds.right - resizeRect.width()), clampInt(bounds.top, resizeRect.top, bounds.bottom - resizeRect.height()));
                }
                Rect closeRect = new Rect();
                this.mCloseableAdContainer.applyCloseRegionBounds(closePosition, resizeRect, closeRect);
                if (!this.mScreenMetrics.getRootViewRect().contains(closeRect)) {
                    throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + heightDips + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that doesn't allow the close" + " region to appear within the max allowed size (" + this.mScreenMetrics.getRootViewRectDips().width() + ", " + this.mScreenMetrics.getRootViewRectDips().height() + ")");
                } else if (resizeRect.contains(closeRect)) {
                    this.mCloseableAdContainer.setCloseVisible(false);
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    LayoutParams layoutParams = new LayoutParams(resizeRect.width(), resizeRect.height());
                    layoutParams.leftMargin = resizeRect.left - this.mScreenMetrics.getRootViewRect().left;
                    layoutParams.topMargin = resizeRect.top - this.mScreenMetrics.getRootViewRect().top;
                    if (this.mViewState == ViewState.DEFAULT) {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                        getRootView().addView(this.mCloseableAdContainer, layoutParams);
                    } else if (this.mViewState == ViewState.RESIZED) {
                        this.mCloseableAdContainer.setLayoutParams(layoutParams);
                    }
                    this.mCloseableAdContainer.setClosePosition(closePosition);
                    setViewState(ViewState.RESIZED);
                } else {
                    throw new MraidCommandException("resizeProperties specified a size (" + widthDips + ", " + height + ") and offset (" + offsetXDips + ", " + offsetYDips + ") that don't allow the close region to appear " + "within the resized ad.");
                }
            }
        }
    }

    void handleExpand(@Nullable URI uri, boolean shouldUseCustomClose) throws MraidCommandException {
        if (this.mMraidWebView == null) {
            throw new MraidCommandException("Unable to expand after the WebView is destroyed");
        } else if (this.mPlacementType != PlacementType.INTERSTITIAL) {
            if (this.mViewState == ViewState.DEFAULT || this.mViewState == ViewState.RESIZED) {
                applyOrientation();
                boolean isTwoPart = uri != null;
                if (isTwoPart) {
                    this.mTwoPartWebView = new MraidWebView(this.mContext);
                    this.mTwoPartBridge.attachView(this.mTwoPartWebView);
                    this.mTwoPartBridge.setContentUrl(uri.toString());
                }
                LayoutParams layoutParams = new LayoutParams(-1, -1);
                if (this.mViewState == ViewState.DEFAULT) {
                    if (isTwoPart) {
                        this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                    } else {
                        this.mDefaultAdContainer.removeView(this.mMraidWebView);
                        this.mDefaultAdContainer.setVisibility(4);
                        this.mCloseableAdContainer.addView(this.mMraidWebView, layoutParams);
                    }
                    getRootView().addView(this.mCloseableAdContainer, new LayoutParams(-1, -1));
                } else if (this.mViewState == ViewState.RESIZED && isTwoPart) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, layoutParams);
                    this.mDefaultAdContainer.setVisibility(4);
                    this.mCloseableAdContainer.addView(this.mTwoPartWebView, layoutParams);
                }
                this.mCloseableAdContainer.setLayoutParams(layoutParams);
                handleCustomClose(shouldUseCustomClose);
                setViewState(ViewState.EXPANDED);
            }
        }
    }

    @VisibleForTesting
    void handleClose() {
        if (this.mMraidWebView != null && this.mViewState != ViewState.LOADING && this.mViewState != ViewState.HIDDEN) {
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                unApplyOrientation();
            }
            if (this.mViewState == ViewState.RESIZED || this.mViewState == ViewState.EXPANDED) {
                if (!this.mTwoPartBridge.isAttached() || this.mTwoPartWebView == null) {
                    this.mCloseableAdContainer.removeView(this.mMraidWebView);
                    this.mDefaultAdContainer.addView(this.mMraidWebView, new LayoutParams(-1, -1));
                    this.mDefaultAdContainer.setVisibility(0);
                } else {
                    this.mCloseableAdContainer.removeView(this.mTwoPartWebView);
                    this.mTwoPartBridge.detach();
                }
                getRootView().removeView(this.mCloseableAdContainer);
                setViewState(ViewState.DEFAULT);
            } else if (this.mViewState == ViewState.DEFAULT) {
                this.mDefaultAdContainer.setVisibility(4);
                setViewState(ViewState.HIDDEN);
            }
        }
    }

    @TargetApi(19)
    @NonNull
    private ViewGroup getRootView() {
        if (this.mRootView == null) {
            if (VERSION.SDK_INT >= 19) {
                Preconditions.checkState(this.mDefaultAdContainer.isAttachedToWindow());
            }
            this.mRootView = (ViewGroup) this.mDefaultAdContainer.getRootView().findViewById(16908290);
        }
        return this.mRootView;
    }

    @VisibleForTesting
    void handleShowVideo(@NonNull String videoUrl) {
        BaseVideoPlayerActivity.startMraid(this.mContext, videoUrl);
    }

    @VisibleForTesting
    void lockOrientation(int screenOrientation) throws MraidCommandException {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null || !shouldAllowForceOrientation(this.mForceOrientation)) {
            throw new MraidCommandException("Attempted to lock orientation to unsupported value: " + this.mForceOrientation.name());
        }
        if (this.mOriginalActivityOrientation == null) {
            this.mOriginalActivityOrientation = Integer.valueOf(activity.getRequestedOrientation());
        }
        activity.setRequestedOrientation(screenOrientation);
    }

    @VisibleForTesting
    void applyOrientation() throws MraidCommandException {
        if (this.mForceOrientation != MraidOrientation.NONE) {
            lockOrientation(this.mForceOrientation.getActivityInfoOrientation());
        } else if (this.mAllowOrientationChange) {
            unApplyOrientation();
        } else {
            Activity activity = (Activity) this.mWeakActivity.get();
            if (activity == null) {
                throw new MraidCommandException("Unable to set MRAID expand orientation to 'none'; expected passed in Activity Context.");
            }
            lockOrientation(DeviceUtils.getScreenOrientation(activity));
        }
    }

    @VisibleForTesting
    void unApplyOrientation() {
        Activity activity = (Activity) this.mWeakActivity.get();
        if (!(activity == null || this.mOriginalActivityOrientation == null)) {
            activity.setRequestedOrientation(this.mOriginalActivityOrientation.intValue());
        }
        this.mOriginalActivityOrientation = null;
    }

    @TargetApi(13)
    @VisibleForTesting
    boolean shouldAllowForceOrientation(MraidOrientation newOrientation) {
        if (newOrientation == MraidOrientation.NONE) {
            return true;
        }
        Activity activity = (Activity) this.mWeakActivity.get();
        if (activity == null) {
            return false;
        }
        try {
            ActivityInfo activityInfo = activity.getPackageManager().getActivityInfo(new ComponentName(activity, activity.getClass()), 0);
            int activityOrientation = activityInfo.screenOrientation;
            if (activityOrientation == -1) {
                boolean containsNecessaryConfigChanges = Utils.bitMaskContainsFlag(activityInfo.configChanges, RadialCountdown.BACKGROUND_ALPHA);
                if (VERSION.SDK_INT >= 13) {
                    if (containsNecessaryConfigChanges && Utils.bitMaskContainsFlag(activityInfo.configChanges, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT)) {
                        containsNecessaryConfigChanges = true;
                    } else {
                        containsNecessaryConfigChanges = false;
                    }
                }
                return containsNecessaryConfigChanges;
            } else if (activityOrientation != newOrientation.getActivityInfoOrientation()) {
                return false;
            } else {
                return true;
            }
        } catch (NameNotFoundException e) {
            return false;
        }
    }

    @VisibleForTesting
    void handleCustomClose(boolean useCustomClose) {
        boolean wasUsingCustomClose;
        boolean z = true;
        if (this.mCloseableAdContainer.isCloseVisible()) {
            wasUsingCustomClose = false;
        } else {
            wasUsingCustomClose = true;
        }
        if (useCustomClose != wasUsingCustomClose) {
            CloseableLayout closeableLayout = this.mCloseableAdContainer;
            if (useCustomClose) {
                z = false;
            }
            closeableLayout.setCloseVisible(z);
            if (this.mOnCloseButtonListener != null) {
                this.mOnCloseButtonListener.useCustomCloseChanged(useCustomClose);
            }
        }
    }

    @NonNull
    public FrameLayout getAdContainer() {
        return this.mDefaultAdContainer;
    }

    public void loadJavascript(@NonNull String javascript) {
        this.mMraidBridge.injectJavaScript(javascript);
    }

    @NonNull
    public Context getContext() {
        return this.mContext;
    }

    @VisibleForTesting
    void handleSetOrientationProperties(boolean allowOrientationChange, MraidOrientation forceOrientation) throws MraidCommandException {
        if (shouldAllowForceOrientation(forceOrientation)) {
            this.mAllowOrientationChange = allowOrientationChange;
            this.mForceOrientation = forceOrientation;
            if (this.mViewState == ViewState.EXPANDED || this.mPlacementType == PlacementType.INTERSTITIAL) {
                applyOrientation();
                return;
            }
            return;
        }
        throw new MraidCommandException("Unable to force orientation to " + forceOrientation);
    }

    @VisibleForTesting
    void handleOpen(@NonNull String url) {
        if (this.mMraidListener != null) {
            this.mMraidListener.onOpen();
        }
        Builder builder = new Builder();
        if (this.mAdReport != null) {
            builder.withDspCreativeId(this.mAdReport.getDspCreativeId());
        }
        builder.withSupportedUrlActions(UrlAction.IGNORE_ABOUT_SCHEME, UrlAction.OPEN_NATIVE_BROWSER, UrlAction.OPEN_IN_APP_BROWSER, UrlAction.HANDLE_SHARE_TWEET, UrlAction.FOLLOW_DEEP_LINK_WITH_FALLBACK, UrlAction.FOLLOW_DEEP_LINK).build().handleUrl(this.mContext, url);
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    ViewState getViewState() {
        return this.mViewState;
    }

    @Deprecated
    @VisibleForTesting
    void setViewStateForTesting(@NonNull ViewState viewState) {
        this.mViewState = viewState;
    }

    @Deprecated
    @NonNull
    @VisibleForTesting
    CloseableLayout getExpandedAdContainer() {
        return this.mCloseableAdContainer;
    }

    @Deprecated
    @VisibleForTesting
    void setRootView(FrameLayout rootView) {
        this.mRootView = rootView;
    }

    @Deprecated
    @VisibleForTesting
    void setRootViewSize(int width, int height) {
        this.mScreenMetrics.setRootViewPosition(0, 0, width, height);
    }

    @Deprecated
    @VisibleForTesting
    Integer getOriginalActivityOrientation() {
        return this.mOriginalActivityOrientation;
    }

    @Deprecated
    @VisibleForTesting
    boolean getAllowOrientationChange() {
        return this.mAllowOrientationChange;
    }

    @Deprecated
    @VisibleForTesting
    MraidOrientation getForceOrientation() {
        return this.mForceOrientation;
    }

    @Deprecated
    @VisibleForTesting
    void setOrientationBroadcastReceiver(OrientationBroadcastReceiver receiver) {
        this.mOrientationBroadcastReceiver = receiver;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getMraidWebView() {
        return this.mMraidWebView;
    }

    @Deprecated
    @VisibleForTesting
    MraidWebView getTwoPartWebView() {
        return this.mTwoPartWebView;
    }
}
