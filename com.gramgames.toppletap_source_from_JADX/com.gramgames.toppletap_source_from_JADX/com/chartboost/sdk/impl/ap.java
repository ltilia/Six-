package com.chartboost.sdk.impl;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout.LayoutParams;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.e.a;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;

public class ap extends ao {
    private ImageView a;

    public ap(av avVar, Context context) {
        super(context);
        this.a = new ImageView(context);
        addView(this.a, new LayoutParams(-1, -1));
    }

    public void a(a aVar, int i) {
        a a = aVar.a("assets").a(CBUtility.c().a() ? DeviceInfo.ORIENTATION_PORTRAIT : DeviceInfo.ORIENTATION_LANDSCAPE);
        if (a.c()) {
            Bundle bundle = new Bundle();
            bundle.putInt("index", i);
            String str = BuildConfig.FLAVOR;
            if (!(a.e("checksum") == null || a.e("checksum").isEmpty())) {
                str = a.e("checksum");
            }
            bb.a().a(a.e(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY), str, null, this.a, bundle);
        }
    }

    public int a() {
        return CBUtility.a(110, getContext());
    }
}
