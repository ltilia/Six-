package com.amazon.device.ads;

import android.view.ViewTreeObserver.OnGlobalLayoutListener;

/* compiled from: ViewabilityObserver */
class AmazonOnGlobalLayoutListenerFactory {

    /* compiled from: ViewabilityObserver */
    private class AmazonOnGlobalLayoutListener implements OnGlobalLayoutListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnGlobalLayoutListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onGlobalLayout() {
            this.viewabilityObserver.addOnScrollChangedListenerIfNeeded();
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }

    AmazonOnGlobalLayoutListenerFactory() {
    }

    public OnGlobalLayoutListener buildAmazonOnGlobalLayoutListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnGlobalLayoutListener(viewabilityObserver);
    }
}
