package com.unity3d.ads.android.video;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import com.unity3d.ads.android.R;

public class UnityAdsVideoPausedView extends RelativeLayout {
    public UnityAdsVideoPausedView(Context context) {
        super(context);
        createView();
    }

    public UnityAdsVideoPausedView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        createView();
    }

    public UnityAdsVideoPausedView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        createView();
    }

    private void createView() {
        LayoutInflater.from(getContext()).inflate(R.layout.unityads_view_video_paused, this);
    }
}
