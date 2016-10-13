package com.chartboost.sdk.impl;

import android.content.Context;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;

public final class bn extends LinearLayout {
    private TextView a;
    private bp b;

    public bn(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        setGravity(17);
        this.a = new TextView(getContext());
        this.a.setTextColor(-1);
        this.a.setTextSize(2, 16.0f);
        this.a.setTypeface(null, 1);
        this.a.setText("Loading...");
        this.a.setGravity(17);
        this.b = new bp(getContext());
        addView(this.a);
        addView(this.b);
        a();
    }

    public void a() {
        removeView(this.a);
        removeView(this.b);
        float f = getContext().getResources().getDisplayMetrics().density;
        int round = Math.round(CloseButton.TEXT_SIZE_SP * f);
        setOrientation(1);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(round, round, round, 0);
        addView(this.a, layoutParams);
        layoutParams = new LinearLayout.LayoutParams(-1, Math.round(f * 32.0f));
        layoutParams.setMargins(round, round, round, round);
        addView(this.b, layoutParams);
    }
}
