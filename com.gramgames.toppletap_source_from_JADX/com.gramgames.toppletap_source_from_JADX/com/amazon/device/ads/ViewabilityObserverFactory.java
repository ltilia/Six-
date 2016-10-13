package com.amazon.device.ads;

public class ViewabilityObserverFactory {
    public ViewabilityObserver buildViewabilityObserver(AdController adcontroller) {
        return new ViewabilityObserver(adcontroller);
    }
}
