package com.unity3d.ads.android;

public interface IUnityAdsListener {
    void onFetchCompleted();

    void onFetchFailed();

    void onHide();

    void onShow();

    void onVideoCompleted(String str, boolean z);

    void onVideoStarted();
}
