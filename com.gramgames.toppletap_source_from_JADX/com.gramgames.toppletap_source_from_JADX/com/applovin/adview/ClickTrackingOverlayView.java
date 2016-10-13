package com.applovin.adview;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.applovin.impl.sdk.cf;
import com.applovin.sdk.AppLovinSdk;
import com.applovin.sdk.AppLovinSdkUtils;

public class ClickTrackingOverlayView extends RelativeLayout {
    public ClickTrackingOverlayView(Context context, AppLovinSdk appLovinSdk) {
        super(context, null, new cf(appLovinSdk).M());
        a(context, appLovinSdk);
    }

    private void a(Context context, AppLovinSdk appLovinSdk) {
        LayoutParams layoutParams;
        cf cfVar = new cf(appLovinSdk);
        View progressBar = new ProgressBar(context);
        progressBar.setIndeterminate(true);
        int L = cfVar.L();
        if (L == -2 || L == -1) {
            layoutParams = new RelativeLayout.LayoutParams(L, L);
        } else {
            L = AppLovinSdkUtils.dpToPx(context, L);
            layoutParams = new RelativeLayout.LayoutParams(L, L);
        }
        layoutParams.addRule(13);
        progressBar.setLayoutParams(layoutParams);
        setBackgroundColor(Color.parseColor(cfVar.K()));
        addView(progressBar);
    }
}
