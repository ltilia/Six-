package com.amazon.device.ads;

public class AmazonViewableAdSDKBridgeFactory implements AdSDKBridgeFactory {
    public AdSDKBridge createAdSDKBridge(AdControlAccessor accessor) {
        return new AmazonViewableAdSDKBridge(accessor, new JavascriptInteractor());
    }
}
