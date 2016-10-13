package com.amazon.device.ads;

/* compiled from: AAXParameterGroupParameter */
class SHA1UDIDAAXParameter extends AAXParameterGroupParameter {
    private MobileAdsInfoStore mobileAdsInfoStore;

    SHA1UDIDAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory(), MobileAdsInfoStore.getInstance());
    }

    SHA1UDIDAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory, MobileAdsInfoStore mobileAdsInfoStore) {
        super(debugProperties, "sha1_udid", DebugProperties.DEBUG_SHA1UDID, mobileAdsLoggerFactory);
        this.mobileAdsInfoStore = mobileAdsInfoStore;
    }

    protected String getDerivedValue(ParameterData parameterData) {
        return this.mobileAdsInfoStore.getDeviceInfo().getUdidSha1();
    }
}
