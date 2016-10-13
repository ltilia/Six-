package com.facebook.ads;

import android.content.Context;
import android.view.View;
import com.facebook.ads.internal.view.b;
import com.google.android.gms.nearby.messages.Strategy;
import com.unity3d.ads.android.R;

public class NativeAdView {

    public enum Type {
        HEIGHT_100(-1, 100),
        HEIGHT_120(-1, 120),
        HEIGHT_300(-1, Strategy.TTL_SECONDS_DEFAULT),
        HEIGHT_400(-1, 400);
        
        private final int a;
        private final int b;

        private Type(int i, int i2) {
            this.a = i;
            this.b = i2;
        }

        public int getHeight() {
            return this.b;
        }

        public int getValue() {
            switch (this.b) {
                case R.styleable.Theme_buttonStyle /*100*/:
                    return 1;
                case 120:
                    return 2;
                case Strategy.TTL_SECONDS_DEFAULT /*300*/:
                    return 3;
                case 400:
                    return 4;
                default:
                    return -1;
            }
        }

        public int getWidth() {
            return this.a;
        }
    }

    public static View render(Context context, NativeAd nativeAd, Type type) {
        return render(context, nativeAd, type, null);
    }

    public static View render(Context context, NativeAd nativeAd, Type type, NativeAdViewAttributes nativeAdViewAttributes) {
        if (nativeAd.isNativeConfigEnabled()) {
            nativeAdViewAttributes = nativeAd.getAdViewAttributes();
        } else if (nativeAdViewAttributes == null) {
            nativeAdViewAttributes = new NativeAdViewAttributes();
        }
        nativeAd.a(type);
        return new b(context, nativeAd, type, nativeAdViewAttributes);
    }
}
