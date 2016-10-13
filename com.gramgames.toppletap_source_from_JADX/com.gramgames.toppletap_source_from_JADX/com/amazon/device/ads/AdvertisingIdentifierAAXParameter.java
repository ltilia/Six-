package com.amazon.device.ads;

/* compiled from: AAXParameterGroupParameter */
class AdvertisingIdentifierAAXParameter extends AAXParameterGroupParameter {
    AdvertisingIdentifierAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory());
    }

    AdvertisingIdentifierAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(debugProperties, "idfa", DebugProperties.DEBUG_IDFA, mobileAdsLoggerFactory);
    }

    protected String getDerivedValue(ParameterData parameterData) {
        return parameterData.getAdRequest().getAdvertisingIdentifierInfo().getAdvertisingIdentifier();
    }
}
