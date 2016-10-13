package com.amazon.device.ads;

public class ViewabilityCheckerFactory {
    public ViewabilityChecker buildViewabilityChecker(AdController adcontroller) {
        return new ViewabilityChecker(adcontroller);
    }
}
