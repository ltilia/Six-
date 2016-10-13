package com.facebook.ads.internal.view;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.AdChoicesView;
import com.facebook.ads.MediaView;
import com.facebook.ads.NativeAd;
import com.facebook.ads.NativeAdView.Type;
import com.facebook.ads.NativeAdViewAttributes;
import com.facebook.ads.internal.view.component.d;
import java.util.Arrays;
import org.json.simple.parser.Yytoken;

public class b extends RelativeLayout {
    private final NativeAdViewAttributes a;
    private final NativeAd b;
    private final DisplayMetrics c;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Type.values().length];
            try {
                a[Type.HEIGHT_400.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Type.HEIGHT_300.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[Type.HEIGHT_100.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                a[Type.HEIGHT_120.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
        }
    }

    public b(Context context, NativeAd nativeAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        super(context);
        setBackgroundColor(nativeAdViewAttributes.getBackgroundColor());
        this.a = nativeAdViewAttributes;
        this.b = nativeAd;
        this.c = context.getResources().getDisplayMetrics();
        setLayoutParams(new LayoutParams(-1, Math.round(((float) type.getHeight()) * this.c.density)));
        View lVar = new l(context);
        lVar.setMinWidth(Math.round(280.0f * this.c.density));
        lVar.setMaxWidth(Math.round(375.0f * this.c.density));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, -1);
        layoutParams.addRule(13, -1);
        lVar.setLayoutParams(layoutParams);
        addView(lVar);
        ViewGroup linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams(new LayoutParams(-1, -1));
        lVar.addView(linearLayout);
        switch (1.a[type.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                b(linearLayout);
                break;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                break;
        }
        a(linearLayout);
        a(linearLayout, type);
        View adChoicesView = new AdChoicesView(getContext(), nativeAd);
        LayoutParams layoutParams2 = (LayoutParams) adChoicesView.getLayoutParams();
        layoutParams2.addRule(11);
        layoutParams2.setMargins(Math.round(this.c.density * 4.0f), Math.round(this.c.density * 4.0f), Math.round(this.c.density * 4.0f), Math.round(this.c.density * 4.0f));
        lVar.addView(adChoicesView);
    }

    private void a(ViewGroup viewGroup) {
        View relativeLayout = new RelativeLayout(getContext());
        relativeLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(this.c.density * 180.0f)));
        relativeLayout.setBackgroundColor(this.a.getBackgroundColor());
        View mediaView = new MediaView(getContext());
        relativeLayout.addView(mediaView);
        ViewGroup.LayoutParams layoutParams = new LayoutParams(-1, (int) (this.c.density * 180.0f));
        layoutParams.addRule(13, -1);
        mediaView.setLayoutParams(layoutParams);
        mediaView.setAutoplay(this.a.getAutoplay());
        mediaView.setNativeAd(this.b);
        viewGroup.addView(relativeLayout);
    }

    private void a(ViewGroup viewGroup, Type type) {
        View bVar = new com.facebook.ads.internal.view.component.b(getContext(), this.b, this.a, a(type), b(type));
        bVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(((float) b(type)) * this.c.density)));
        viewGroup.addView(bVar);
        this.b.registerViewForInteraction(this, Arrays.asList(new View[]{bVar.getIconView(), bVar.getCallToActionView()}));
    }

    private boolean a(Type type) {
        return type == Type.HEIGHT_300 || type == Type.HEIGHT_120;
    }

    private int b(Type type) {
        switch (1.a[type.ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                return (type.getHeight() - 180) / 2;
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                return type.getHeight() - 180;
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                return type.getHeight();
            default:
                return 0;
        }
    }

    private void b(ViewGroup viewGroup) {
        View dVar = new d(getContext(), this.b, this.a);
        dVar.setLayoutParams(new LinearLayout.LayoutParams(-1, Math.round(110.0f * this.c.density)));
        viewGroup.addView(dVar);
    }
}
