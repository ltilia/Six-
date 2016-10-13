package com.amazon.device.ads;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.amazon.device.ads.AdActivity.AdActivityAdapter;
import com.amazon.device.ads.AdVideoPlayer.AdVideoPlayerListener;
import com.unity3d.ads.android.properties.UnityAdsConstants;

class VideoActionHandler implements AdActivityAdapter {
    private Activity activity;
    private RelativeLayout layout;
    private AdVideoPlayer player;

    class 1 implements AdVideoPlayerListener {
        1() {
        }

        public void onError() {
            VideoActionHandler.this.activity.finish();
        }

        public void onComplete() {
            VideoActionHandler.this.activity.finish();
        }
    }

    VideoActionHandler() {
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public void onCreate() {
        Bundle data = this.activity.getIntent().getExtras();
        this.layout = new RelativeLayout(this.activity);
        this.layout.setLayoutParams(new LayoutParams(-1, -1));
        this.activity.setContentView(this.layout);
        initPlayer(data);
        this.player.playVideo();
    }

    private void setPlayerListener(AdVideoPlayer player) {
        player.setListener(new 1());
    }

    private void initPlayer(Bundle data) {
        this.player = new AdVideoPlayer(this.activity);
        this.player.setPlayData(data.getString(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY));
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(-1, -1);
        lp.addRule(13);
        this.player.setLayoutParams(lp);
        this.player.setViewGroup(this.layout);
        setPlayerListener(this.player);
    }

    public void onPause() {
    }

    public void onResume() {
    }

    public void onStop() {
        this.player.releasePlayer();
        this.player = null;
        this.activity.finish();
    }

    public void preOnCreate() {
        this.activity.requestWindowFeature(1);
    }

    public void onConfigurationChanged(Configuration configuration) {
    }

    public boolean onBackPressed() {
        return false;
    }

    public void onWindowFocusChanged() {
    }
}
