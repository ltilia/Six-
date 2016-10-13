package com.applovin.adview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.applovin.impl.adview.AdViewControllerImpl;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdClickListener;
import com.applovin.sdk.AppLovinAdDisplayListener;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdVideoPlaybackListener;
import com.applovin.sdk.AppLovinLogger;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class AppLovinAdView extends RelativeLayout {
    public static final String NAMESPACE = "http://schemas.applovin.com/android/1.0";
    private AdViewController a;

    public AppLovinAdView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public AppLovinAdView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(null, null, context, attributeSet);
    }

    public AppLovinAdView(AppLovinAdSize appLovinAdSize, Activity activity) {
        super(activity);
        Log.d(AppLovinLogger.SDK_TAG, "Created new AdView");
        a(appLovinAdSize, null, activity, null);
    }

    public AppLovinAdView(AppLovinSdk appLovinSdk, AppLovinAdSize appLovinAdSize, Activity activity) {
        super(activity);
        a(appLovinAdSize, appLovinSdk, activity, null);
    }

    private void a(AttributeSet attributeSet, Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int i = displayMetrics.widthPixels;
        int applyDimension = (int) TypedValue.applyDimension(1, 50.0f, displayMetrics);
        View textView = new TextView(context);
        textView.setBackgroundColor(Color.rgb(220, 220, 220));
        textView.setTextColor(CtaButton.BACKGROUND_COLOR);
        textView.setText("AppLovin Ad");
        textView.setGravity(17);
        addView(textView, i, applyDimension);
    }

    private void a(AppLovinAdSize appLovinAdSize, AppLovinSdk appLovinSdk, Context context, AttributeSet attributeSet) {
        if (isInEditMode()) {
            a(attributeSet, context);
            return;
        }
        AdViewController adViewControllerImpl = new AdViewControllerImpl();
        adViewControllerImpl.initializeAdView(this, context, appLovinAdSize, appLovinSdk, attributeSet);
        this.a = adViewControllerImpl;
    }

    public void destroy() {
        if (this.a != null) {
            this.a.destroy();
        }
    }

    public AdViewController getAdViewController() {
        return this.a;
    }

    public AppLovinAdSize getSize() {
        return this.a != null ? this.a.getSize() : null;
    }

    public void loadNextAd() {
        if (this.a != null) {
            this.a.loadNextAd();
        } else {
            Log.i(AppLovinLogger.SDK_TAG, "Unable to load next ad: AppLovinAdView is not initialized.");
        }
    }

    protected void onDetachedFromWindow() {
        if (this.a != null) {
            this.a.onDetachedFromWindow();
        }
        super.onDetachedFromWindow();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (this.a != null) {
            this.a.onVisibilityChanged(i);
        }
    }

    public void pause() {
        if (this.a != null) {
            this.a.pause();
        }
    }

    public void renderAd(AppLovinAd appLovinAd) {
        renderAd(appLovinAd, null);
    }

    public void renderAd(AppLovinAd appLovinAd, String str) {
        if (this.a != null) {
            this.a.renderAd(appLovinAd, str);
        }
    }

    public void resume() {
        if (this.a != null) {
            this.a.resume();
        }
    }

    public void setAdClickListener(AppLovinAdClickListener appLovinAdClickListener) {
        if (this.a != null) {
            this.a.setAdClickListener(appLovinAdClickListener);
        }
    }

    public void setAdDisplayListener(AppLovinAdDisplayListener appLovinAdDisplayListener) {
        if (this.a != null) {
            this.a.setAdDisplayListener(appLovinAdDisplayListener);
        }
    }

    public void setAdLoadListener(AppLovinAdLoadListener appLovinAdLoadListener) {
        if (this.a != null) {
            this.a.setAdLoadListener(appLovinAdLoadListener);
        }
    }

    public void setAdVideoPlaybackListener(AppLovinAdVideoPlaybackListener appLovinAdVideoPlaybackListener) {
        if (this.a != null) {
            this.a.setAdVideoPlaybackListener(appLovinAdVideoPlaybackListener);
        }
    }

    public void setAutoDestroy(boolean z) {
        if (this.a != null) {
            this.a.setAutoDestroy(z);
        }
    }
}
