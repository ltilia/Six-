package com.facebook.ads.internal.adapters;

import com.facebook.ads.internal.server.AdPlacementType;

public enum h {
    ANBANNER(i.class, g.AN, AdPlacementType.BANNER),
    ANINTERSTITIAL(j.class, g.AN, AdPlacementType.INTERSTITIAL),
    ANNATIVE(k.class, g.AN, AdPlacementType.NATIVE);
    
    public Class<?> d;
    public String e;
    public g f;
    public AdPlacementType g;

    private h(Class<?> cls, g gVar, AdPlacementType adPlacementType) {
        this.d = cls;
        this.f = gVar;
        this.g = adPlacementType;
    }
}
