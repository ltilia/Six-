package com.vungle.publisher;

import com.vungle.publisher.ad.SafeBundleAdConfig;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class SafeBundleAdConfigFactory {
    @Inject
    AdConfig a;

    @Inject
    SafeBundleAdConfigFactory() {
    }

    public SafeBundleAdConfig get() {
        return new SafeBundleAdConfig(this.a.a);
    }

    public SafeBundleAdConfig merge(AdConfig... adConfigs) {
        int i = 0;
        h[] hVarArr = null;
        if (adConfigs != null) {
            h[] hVarArr2 = new h[adConfigs.length];
            int length = adConfigs.length;
            int i2 = 0;
            while (i2 < length) {
                int i3;
                AdConfig adConfig = adConfigs[i2];
                if (adConfig != null) {
                    i3 = i + 1;
                    hVarArr2[i] = adConfig.a;
                } else {
                    i3 = i;
                }
                i2++;
                i = i3;
            }
            hVarArr = hVarArr2;
        }
        return new SafeBundleAdConfig(hVarArr);
    }
}
