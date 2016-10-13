package com.vungle.publisher;

/* compiled from: vungle */
public interface EventListener {
    void onAdEnd(boolean z);

    void onAdPlayableChanged(boolean z);

    void onAdStart();

    void onAdUnavailable(String str);

    void onVideoView(boolean z, int i, int i2);
}
