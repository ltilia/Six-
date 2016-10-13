package com.mopub.mobileads;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.webkit.WebViewDatabase;
import android.widget.FrameLayout;
import com.mopub.common.AdFormat;
import com.mopub.common.logging.MoPubLog;
import com.mopub.common.util.ManifestUtils;
import com.mopub.common.util.Visibility;
import com.mopub.mobileads.factories.AdViewControllerFactory;
import com.mopub.mobileads.factories.CustomEventBannerAdapterFactory;
import java.util.Map;
import java.util.TreeMap;

public class MoPubView extends FrameLayout {
    @Nullable
    protected AdViewController mAdViewController;
    private BannerAdListener mBannerAdListener;
    private Context mContext;
    protected CustomEventBannerAdapter mCustomEventBannerAdapter;
    private BroadcastReceiver mScreenStateReceiver;
    private int mScreenVisibility;

    public interface BannerAdListener {
        void onBannerClicked(MoPubView moPubView);

        void onBannerCollapsed(MoPubView moPubView);

        void onBannerExpanded(MoPubView moPubView);

        void onBannerFailed(MoPubView moPubView, MoPubErrorCode moPubErrorCode);

        void onBannerLoaded(MoPubView moPubView);
    }

    class 1 extends BroadcastReceiver {
        1() {
        }

        public void onReceive(Context context, Intent intent) {
            if (Visibility.isScreenVisible(MoPubView.this.mScreenVisibility) && intent != null) {
                String action = intent.getAction();
                if ("android.intent.action.USER_PRESENT".equals(action)) {
                    MoPubView.this.setAdVisibility(0);
                } else if ("android.intent.action.SCREEN_OFF".equals(action)) {
                    MoPubView.this.setAdVisibility(8);
                }
            }
        }
    }

    public MoPubView(Context context) {
        this(context, null);
    }

    public MoPubView(Context context, AttributeSet attrs) {
        super(context, attrs);
        ManifestUtils.checkWebViewActivitiesDeclared(context);
        this.mContext = context;
        this.mScreenVisibility = getVisibility();
        setHorizontalScrollBarEnabled(false);
        setVerticalScrollBarEnabled(false);
        if (WebViewDatabase.getInstance(context) == null) {
            MoPubLog.e("Disabling MoPub. Local cache file is inaccessible so MoPub will fail if we try to create a WebView. Details of this Android bug found at:https://code.google.com/p/android/issues/detail?id=10789");
            return;
        }
        this.mAdViewController = AdViewControllerFactory.create(context, this);
        registerScreenStateBroadcastReceiver();
    }

    private void registerScreenStateBroadcastReceiver() {
        this.mScreenStateReceiver = new 1();
        IntentFilter filter = new IntentFilter("android.intent.action.SCREEN_OFF");
        filter.addAction("android.intent.action.USER_PRESENT");
        this.mContext.registerReceiver(this.mScreenStateReceiver, filter);
    }

    private void unregisterScreenStateBroadcastReceiver() {
        try {
            this.mContext.unregisterReceiver(this.mScreenStateReceiver);
        } catch (Exception e) {
            MoPubLog.d("Failed to unregister screen state broadcast receiver (never registered).");
        }
    }

    public void loadAd() {
        if (this.mAdViewController != null) {
            this.mAdViewController.loadAd();
        }
    }

    public void destroy() {
        unregisterScreenStateBroadcastReceiver();
        removeAllViews();
        if (this.mAdViewController != null) {
            this.mAdViewController.cleanup();
            this.mAdViewController = null;
        }
        if (this.mCustomEventBannerAdapter != null) {
            this.mCustomEventBannerAdapter.invalidate();
            this.mCustomEventBannerAdapter = null;
        }
    }

    Integer getAdTimeoutDelay() {
        return this.mAdViewController != null ? this.mAdViewController.getAdTimeoutDelay() : null;
    }

    protected void loadFailUrl(MoPubErrorCode errorCode) {
        if (this.mAdViewController != null) {
            this.mAdViewController.loadFailUrl(errorCode);
        }
    }

    protected void loadCustomEvent(String customEventClassName, Map<String, String> serverExtras) {
        if (this.mAdViewController != null) {
            if (TextUtils.isEmpty(customEventClassName)) {
                MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                return;
            }
            if (this.mCustomEventBannerAdapter != null) {
                this.mCustomEventBannerAdapter.invalidate();
            }
            MoPubLog.d("Loading custom event adapter.");
            this.mCustomEventBannerAdapter = CustomEventBannerAdapterFactory.create(this, customEventClassName, serverExtras, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport());
            this.mCustomEventBannerAdapter.loadAd();
        }
    }

    protected void registerClick() {
        if (this.mAdViewController != null) {
            this.mAdViewController.registerClick();
            adClicked();
        }
    }

    protected void trackNativeImpression() {
        MoPubLog.d("Tracking impression for native adapter.");
        if (this.mAdViewController != null) {
            this.mAdViewController.trackImpression();
        }
    }

    protected void onWindowVisibilityChanged(int visibility) {
        if (Visibility.hasScreenVisibilityChanged(this.mScreenVisibility, visibility)) {
            this.mScreenVisibility = visibility;
            setAdVisibility(this.mScreenVisibility);
        }
    }

    private void setAdVisibility(int visibility) {
        if (this.mAdViewController != null) {
            if (Visibility.isScreenVisible(visibility)) {
                this.mAdViewController.unpauseRefresh();
            } else {
                this.mAdViewController.pauseRefresh();
            }
        }
    }

    protected void adLoaded() {
        MoPubLog.d("adLoaded");
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerLoaded(this);
        }
    }

    protected void adFailed(MoPubErrorCode errorCode) {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerFailed(this, errorCode);
        }
    }

    protected void adPresentedOverlay() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerExpanded(this);
        }
    }

    protected void adClosed() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerCollapsed(this);
        }
    }

    protected void adClicked() {
        if (this.mBannerAdListener != null) {
            this.mBannerAdListener.onBannerClicked(this);
        }
    }

    protected void nativeAdLoaded() {
        if (this.mAdViewController != null) {
            this.mAdViewController.scheduleRefreshTimerIfEnabled();
        }
        adLoaded();
    }

    public void setAdUnitId(String adUnitId) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdUnitId(adUnitId);
        }
    }

    public String getAdUnitId() {
        return this.mAdViewController != null ? this.mAdViewController.getAdUnitId() : null;
    }

    public void setKeywords(String keywords) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setKeywords(keywords);
        }
    }

    public String getKeywords() {
        return this.mAdViewController != null ? this.mAdViewController.getKeywords() : null;
    }

    public void setLocation(Location location) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocation(location);
        }
    }

    public Location getLocation() {
        return this.mAdViewController != null ? this.mAdViewController.getLocation() : null;
    }

    public int getAdWidth() {
        return this.mAdViewController != null ? this.mAdViewController.getAdWidth() : 0;
    }

    public int getAdHeight() {
        return this.mAdViewController != null ? this.mAdViewController.getAdHeight() : 0;
    }

    public String getNetworkType() {
        return this.mAdViewController != null ? this.mAdViewController.getNetworkType() : null;
    }

    public String getAdType() {
        return this.mAdViewController != null ? this.mAdViewController.getAdType() : null;
    }

    public Activity getActivity() {
        return (Activity) this.mContext;
    }

    public void setBannerAdListener(BannerAdListener listener) {
        this.mBannerAdListener = listener;
    }

    public BannerAdListener getBannerAdListener() {
        return this.mBannerAdListener;
    }

    public void setLocalExtras(Map<String, Object> localExtras) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setLocalExtras(localExtras);
        }
    }

    public Map<String, Object> getLocalExtras() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getLocalExtras();
        }
        return new TreeMap();
    }

    public void setAutorefreshEnabled(boolean enabled) {
        if (this.mAdViewController != null) {
            this.mAdViewController.forceSetAutorefreshEnabled(enabled);
        }
    }

    public boolean getAutorefreshEnabled() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getAutorefreshEnabled();
        }
        MoPubLog.d("Can't get autorefresh status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void setAdContentView(View view) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setAdContentView(view);
        }
    }

    public void setTesting(boolean testing) {
        if (this.mAdViewController != null) {
            this.mAdViewController.setTesting(testing);
        }
    }

    public boolean getTesting() {
        if (this.mAdViewController != null) {
            return this.mAdViewController.getTesting();
        }
        MoPubLog.d("Can't get testing status for destroyed MoPubView. Returning false.");
        return false;
    }

    public void forceRefresh() {
        if (this.mCustomEventBannerAdapter != null) {
            this.mCustomEventBannerAdapter.invalidate();
            this.mCustomEventBannerAdapter = null;
        }
        if (this.mAdViewController != null) {
            this.mAdViewController.forceRefresh();
        }
    }

    AdViewController getAdViewController() {
        return this.mAdViewController;
    }

    public AdFormat getAdFormat() {
        return AdFormat.BANNER;
    }

    @Deprecated
    public void setTimeout(int milliseconds) {
    }

    @Deprecated
    public String getResponseString() {
        return null;
    }

    @Deprecated
    public String getClickTrackingUrl() {
        return null;
    }
}
