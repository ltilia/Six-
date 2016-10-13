package com.mopub.mobileads;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

public class RewardedVideoBroadcastReceiver extends BaseBroadcastReceiver {
    public static final String ACTION_REWARDED_VIDEO_COMPLETE = "com.mopub.action.rewardedvideo.complete";
    private static IntentFilter sIntentFilter;
    @Nullable
    private CustomEventRewardedVideoInterstitialListener mRewardedVideoListener;

    public RewardedVideoBroadcastReceiver(@Nullable CustomEventRewardedVideoInterstitialListener rewardedVideoListener, long broadcastIdentifier) {
        super(broadcastIdentifier);
        this.mRewardedVideoListener = rewardedVideoListener;
        getIntentFilter();
    }

    @NonNull
    public IntentFilter getIntentFilter() {
        if (sIntentFilter == null) {
            sIntentFilter = new IntentFilter();
            sIntentFilter.addAction(ACTION_REWARDED_VIDEO_COMPLETE);
        }
        return sIntentFilter;
    }

    public void onReceive(@NonNull Context context, @NonNull Intent intent) {
        if (this.mRewardedVideoListener != null && shouldConsumeBroadcast(intent)) {
            if (ACTION_REWARDED_VIDEO_COMPLETE.equals(intent.getAction())) {
                this.mRewardedVideoListener.onVideoComplete();
                unregister(this);
            }
        }
    }
}
