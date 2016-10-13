package com.mopub.nativeads;

import android.os.Handler;
import android.support.annotation.NonNull;
import com.mopub.nativeads.MoPubNativeAdPositioning.MoPubClientPositioning;
import com.mopub.nativeads.PositioningSource.PositioningListener;

class ClientPositioningSource implements PositioningSource {
    @NonNull
    private final Handler mHandler;
    @NonNull
    private final MoPubClientPositioning mPositioning;

    class 1 implements Runnable {
        final /* synthetic */ PositioningListener val$listener;

        1(PositioningListener positioningListener) {
            this.val$listener = positioningListener;
        }

        public void run() {
            this.val$listener.onLoad(ClientPositioningSource.this.mPositioning);
        }
    }

    ClientPositioningSource(@NonNull MoPubClientPositioning positioning) {
        this.mHandler = new Handler();
        this.mPositioning = MoPubNativeAdPositioning.clone(positioning);
    }

    public void loadPositions(@NonNull String adUnitId, @NonNull PositioningListener listener) {
        this.mHandler.post(new 1(listener));
    }
}
