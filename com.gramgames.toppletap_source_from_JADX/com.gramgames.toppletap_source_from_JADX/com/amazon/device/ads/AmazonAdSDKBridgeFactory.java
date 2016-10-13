package com.amazon.device.ads;

class AmazonAdSDKBridgeFactory implements AdSDKBridgeFactory {
    AmazonAdSDKBridgeFactory() {
    }

    public AdSDKBridge createAdSDKBridge(AdControlAccessor accessor) {
        return new AmazonAdSDKBridge(accessor, new JavascriptInteractor());
    }
}
