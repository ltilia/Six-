package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.text.TextUtils;
import com.mopub.common.AdFormat;
import com.mopub.common.VisibleForTesting;
import com.mopub.common.logging.MoPubLog;
import com.mopub.mobileads.factories.CustomEventInterstitialAdapterFactory;
import java.util.Map;
import org.json.simple.parser.Yytoken;

public class MoPubInterstitial implements CustomEventInterstitialAdapterListener {
    private Activity mActivity;
    private String mAdUnitId;
    private InterstitialState mCurrentInterstitialState;
    private CustomEventInterstitialAdapter mCustomEventInterstitialAdapter;
    private InterstitialAdListener mInterstitialAdListener;
    private MoPubInterstitialView mInterstitialView;
    private boolean mIsDestroyed;

    public interface InterstitialAdListener {
        void onInterstitialClicked(MoPubInterstitial moPubInterstitial);

        void onInterstitialDismissed(MoPubInterstitial moPubInterstitial);

        void onInterstitialFailed(MoPubInterstitial moPubInterstitial, MoPubErrorCode moPubErrorCode);

        void onInterstitialLoaded(MoPubInterstitial moPubInterstitial);

        void onInterstitialShown(MoPubInterstitial moPubInterstitial);
    }

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState;

        static {
            $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState = new int[InterstitialState.values().length];
            try {
                $SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[InterstitialState.CUSTOM_EVENT_AD_READY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
        }
    }

    private enum InterstitialState {
        CUSTOM_EVENT_AD_READY,
        NOT_READY;

        boolean isReady() {
            return this != NOT_READY;
        }
    }

    public class MoPubInterstitialView extends MoPubView {
        public MoPubInterstitialView(Context context) {
            super(context);
            setAutorefreshEnabled(false);
        }

        public AdFormat getAdFormat() {
            return AdFormat.INTERSTITIAL;
        }

        protected void loadCustomEvent(String customEventClassName, Map<String, String> serverExtras) {
            if (this.mAdViewController != null) {
                if (TextUtils.isEmpty(customEventClassName)) {
                    MoPubLog.d("Couldn't invoke custom event because the server did not specify one.");
                    loadFailUrl(MoPubErrorCode.ADAPTER_NOT_FOUND);
                    return;
                }
                if (MoPubInterstitial.this.mCustomEventInterstitialAdapter != null) {
                    MoPubInterstitial.this.mCustomEventInterstitialAdapter.invalidate();
                }
                MoPubLog.d("Loading custom event interstitial adapter.");
                MoPubInterstitial.this.mCustomEventInterstitialAdapter = CustomEventInterstitialAdapterFactory.create(MoPubInterstitial.this, customEventClassName, serverExtras, this.mAdViewController.getBroadcastIdentifier(), this.mAdViewController.getAdReport());
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.setAdapterListener(MoPubInterstitial.this);
                MoPubInterstitial.this.mCustomEventInterstitialAdapter.loadInterstitial();
            }
        }

        protected void trackImpression() {
            MoPubLog.d("Tracking impression for interstitial.");
            if (this.mAdViewController != null) {
                this.mAdViewController.trackImpression();
            }
        }

        protected void adFailed(MoPubErrorCode errorCode) {
            if (MoPubInterstitial.this.mInterstitialAdListener != null) {
                MoPubInterstitial.this.mInterstitialAdListener.onInterstitialFailed(MoPubInterstitial.this, errorCode);
            }
        }
    }

    public MoPubInterstitial(Activity activity, String id) {
        this.mActivity = activity;
        this.mAdUnitId = id;
        this.mInterstitialView = new MoPubInterstitialView(this.mActivity);
        this.mInterstitialView.setAdUnitId(this.mAdUnitId);
        this.mCurrentInterstitialState = InterstitialState.NOT_READY;
    }

    public void load() {
        resetCurrentInterstitial();
        this.mInterstitialView.loadAd();
    }

    public void forceRefresh() {
        resetCurrentInterstitial();
        this.mInterstitialView.forceRefresh();
    }

    private void resetCurrentInterstitial() {
        this.mCurrentInterstitialState = InterstitialState.NOT_READY;
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
        this.mIsDestroyed = false;
    }

    public boolean isReady() {
        return this.mCurrentInterstitialState.isReady();
    }

    boolean isDestroyed() {
        return this.mIsDestroyed;
    }

    public boolean show() {
        switch (1.$SwitchMap$com$mopub$mobileads$MoPubInterstitial$InterstitialState[this.mCurrentInterstitialState.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                showCustomEventInterstitial();
                return true;
            default:
                return false;
        }
    }

    private void showCustomEventInterstitial() {
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.showInterstitial();
        }
    }

    Integer getAdTimeoutDelay() {
        return this.mInterstitialView.getAdTimeoutDelay();
    }

    public MoPubInterstitialView getMoPubInterstitialView() {
        return this.mInterstitialView;
    }

    public void setKeywords(String keywords) {
        this.mInterstitialView.setKeywords(keywords);
    }

    public String getKeywords() {
        return this.mInterstitialView.getKeywords();
    }

    public Activity getActivity() {
        return this.mActivity;
    }

    public Location getLocation() {
        return this.mInterstitialView.getLocation();
    }

    public void destroy() {
        this.mIsDestroyed = true;
        if (this.mCustomEventInterstitialAdapter != null) {
            this.mCustomEventInterstitialAdapter.invalidate();
            this.mCustomEventInterstitialAdapter = null;
        }
        this.mInterstitialView.setBannerAdListener(null);
        this.mInterstitialView.destroy();
    }

    public void setInterstitialAdListener(InterstitialAdListener listener) {
        this.mInterstitialAdListener = listener;
    }

    public InterstitialAdListener getInterstitialAdListener() {
        return this.mInterstitialAdListener;
    }

    public void setTesting(boolean testing) {
        this.mInterstitialView.setTesting(testing);
    }

    public boolean getTesting() {
        return this.mInterstitialView.getTesting();
    }

    public void setLocalExtras(Map<String, Object> extras) {
        this.mInterstitialView.setLocalExtras(extras);
    }

    public Map<String, Object> getLocalExtras() {
        return this.mInterstitialView.getLocalExtras();
    }

    public void onCustomEventInterstitialLoaded() {
        if (!this.mIsDestroyed) {
            this.mCurrentInterstitialState = InterstitialState.CUSTOM_EVENT_AD_READY;
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialLoaded(this);
            }
        }
    }

    public void onCustomEventInterstitialFailed(MoPubErrorCode errorCode) {
        if (!isDestroyed()) {
            this.mCurrentInterstitialState = InterstitialState.NOT_READY;
            this.mInterstitialView.loadFailUrl(errorCode);
        }
    }

    public void onCustomEventInterstitialShown() {
        if (!isDestroyed()) {
            this.mInterstitialView.trackImpression();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialShown(this);
            }
        }
    }

    public void onCustomEventInterstitialClicked() {
        if (!isDestroyed()) {
            this.mInterstitialView.registerClick();
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialClicked(this);
            }
        }
    }

    public void onCustomEventInterstitialDismissed() {
        if (!isDestroyed()) {
            this.mCurrentInterstitialState = InterstitialState.NOT_READY;
            if (this.mInterstitialAdListener != null) {
                this.mInterstitialAdListener.onInterstitialDismissed(this);
            }
        }
    }

    @Deprecated
    @VisibleForTesting
    void setInterstitialView(MoPubInterstitialView interstitialView) {
        this.mInterstitialView = interstitialView;
    }
}
