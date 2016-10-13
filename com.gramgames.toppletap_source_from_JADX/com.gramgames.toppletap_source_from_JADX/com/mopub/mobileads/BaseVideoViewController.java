package com.mopub.mobileads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.mopub.common.logging.MoPubLog;

public abstract class BaseVideoViewController {
    private final BaseVideoViewControllerListener mBaseVideoViewControllerListener;
    @Nullable
    private Long mBroadcastIdentifier;
    private final Context mContext;
    private final RelativeLayout mLayout;

    public interface BaseVideoViewControllerListener {
        void onFinish();

        void onSetContentView(View view);

        void onSetRequestedOrientation(int i);

        void onStartActivityForResult(Class<? extends Activity> cls, int i, Bundle bundle);
    }

    protected abstract VideoView getVideoView();

    protected abstract void onBackPressed();

    protected abstract void onConfigurationChanged(Configuration configuration);

    protected abstract void onDestroy();

    protected abstract void onPause();

    protected abstract void onResume();

    protected abstract void onSaveInstanceState(@NonNull Bundle bundle);

    protected BaseVideoViewController(Context context, @Nullable Long broadcastIdentifier, BaseVideoViewControllerListener baseVideoViewControllerListener) {
        this.mContext = context;
        this.mBroadcastIdentifier = broadcastIdentifier;
        this.mBaseVideoViewControllerListener = baseVideoViewControllerListener;
        this.mLayout = new RelativeLayout(this.mContext);
    }

    protected void onCreate() {
        LayoutParams adViewLayout = new LayoutParams(-1, -2);
        adViewLayout.addRule(13);
        this.mLayout.addView(getVideoView(), 0, adViewLayout);
        this.mBaseVideoViewControllerListener.onSetContentView(this.mLayout);
    }

    public boolean backButtonEnabled() {
        return true;
    }

    void onActivityResult(int requestCode, int resultCode, Intent data) {
    }

    protected BaseVideoViewControllerListener getBaseVideoViewControllerListener() {
        return this.mBaseVideoViewControllerListener;
    }

    protected Context getContext() {
        return this.mContext;
    }

    public ViewGroup getLayout() {
        return this.mLayout;
    }

    protected void videoError(boolean shouldFinish) {
        MoPubLog.e("Video cannot be played.");
        broadcastAction(EventForwardingBroadcastReceiver.ACTION_INTERSTITIAL_FAIL);
        if (shouldFinish) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    protected void videoCompleted(boolean shouldFinish) {
        if (shouldFinish) {
            this.mBaseVideoViewControllerListener.onFinish();
        }
    }

    void broadcastAction(String action) {
        if (this.mBroadcastIdentifier != null) {
            BaseBroadcastReceiver.broadcastAction(this.mContext, this.mBroadcastIdentifier.longValue(), action);
        } else {
            MoPubLog.w("Tried to broadcast a video event without a broadcast identifier to send to.");
        }
    }
}
