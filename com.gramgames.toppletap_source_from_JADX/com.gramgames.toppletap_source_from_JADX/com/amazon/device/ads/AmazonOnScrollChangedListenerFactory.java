package com.amazon.device.ads;

import android.view.ViewTreeObserver.OnScrollChangedListener;

/* compiled from: ViewabilityObserver */
class AmazonOnScrollChangedListenerFactory {

    /* compiled from: ViewabilityObserver */
    private class AmazonOnScrollChangedListener implements OnScrollChangedListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnScrollChangedListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onScrollChanged() {
            this.viewabilityObserver.fireViewableEvent(true);
        }
    }

    AmazonOnScrollChangedListenerFactory() {
    }

    public OnScrollChangedListener buildAmazonOnScrollChangedListenerFactory(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnScrollChangedListener(viewabilityObserver);
    }
}
