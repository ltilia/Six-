package com.amazon.device.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalFocusChangeListener;

/* compiled from: ViewabilityObserver */
class AmazonOnGlobalFocusChangeListenerFactory {

    /* compiled from: ViewabilityObserver */
    private class AmazonOnGlobalFocusChangeListener implements OnGlobalFocusChangeListener {
        private final ViewabilityObserver viewabilityObserver;

        public AmazonOnGlobalFocusChangeListener(ViewabilityObserver viewabilityObserver) {
            this.viewabilityObserver = viewabilityObserver;
        }

        public void onGlobalFocusChanged(View oldFocus, View newFocus) {
            this.viewabilityObserver.fireViewableEvent(false);
        }
    }

    AmazonOnGlobalFocusChangeListenerFactory() {
    }

    public OnGlobalFocusChangeListener buildAmazonOnGlobalFocusChangedListener(ViewabilityObserver viewabilityObserver) {
        return new AmazonOnGlobalFocusChangeListener(viewabilityObserver);
    }
}
