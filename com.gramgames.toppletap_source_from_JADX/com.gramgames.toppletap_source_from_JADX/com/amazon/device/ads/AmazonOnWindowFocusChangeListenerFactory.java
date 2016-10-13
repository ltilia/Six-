package com.amazon.device.ads;

import android.view.ViewTreeObserver.OnWindowFocusChangeListener;

/* compiled from: ViewabilityObserver */
class AmazonOnWindowFocusChangeListenerFactory {

    /* compiled from: ViewabilityObserver */
    private class AmazonOnWindowFocusChangeListener implements OnWindowFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        AmazonOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onWindowFocusChanged(boolean hasFocus) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }

    AmazonOnWindowFocusChangeListenerFactory() {
    }

    public OnWindowFocusChangeListener buildOnWindowFocusChangeListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnWindowFocusChangeListener(viewabilityObserver);
    }
}
