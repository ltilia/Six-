package com.amazon.device.ads;

/* compiled from: AAXParameterGroupParameter */
class SISDeviceIdentifierAAXParameter extends AAXParameterGroupParameter {
    SISDeviceIdentifierAAXParameter() {
        this(DebugProperties.getInstance(), new MobileAdsLoggerFactory());
    }

    SISDeviceIdentifierAAXParameter(DebugProperties debugProperties, MobileAdsLoggerFactory mobileAdsLoggerFactory) {
        super(debugProperties, "ad-id", DebugProperties.DEBUG_ADID, mobileAdsLoggerFactory);
    }

    protected String getDerivedValue(ParameterData parameterData) {
        return parameterData.getAdRequest().getAdvertisingIdentifierInfo().getSISDeviceIdentifier();
    }
}
