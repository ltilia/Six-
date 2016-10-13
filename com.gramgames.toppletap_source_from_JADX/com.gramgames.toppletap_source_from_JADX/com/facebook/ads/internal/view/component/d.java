package com.facebook.ads.internal.view.component;

import android.content.Context;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.view.i;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;

public class d extends LinearLayout {
    public d(Context context, NativeAd nativeAd, NativeAdViewAttributes nativeAdViewAttributes) {
        super(context);
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        View linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setGravity(17);
        linearLayout.setVerticalGravity(16);
        LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
        layoutParams.setMargins(Math.round(displayMetrics.density * CtaButton.TEXT_SIZE_SP), Math.round(displayMetrics.density * CtaButton.TEXT_SIZE_SP), Math.round(displayMetrics.density * CtaButton.TEXT_SIZE_SP), Math.round(displayMetrics.density * CtaButton.TEXT_SIZE_SP));
        linearLayout.setLayoutParams(layoutParams);
        addView(linearLayout);
        View textView = new TextView(getContext());
        textView.setText(nativeAd.getAdSubtitle());
        i.a(textView, nativeAdViewAttributes);
        textView.setEllipsize(TruncateAt.END);
        textView.setSingleLine(true);
        linearLayout.addView(textView);
        textView = new TextView(getContext());
        textView.setText(nativeAd.getAdBody());
        i.b(textView, nativeAdViewAttributes);
        textView.setEllipsize(TruncateAt.END);
        textView.setMaxLines(2);
        linearLayout.addView(textView);
    }
}
