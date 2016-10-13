package com.applovin.sdk.unity;

import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.player.UnityPlayerActivity;

public class AppLovinUnityActivity extends UnityPlayerActivity {
    private boolean autoload;

    public AppLovinUnityActivity() {
        this.autoload = false;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Bundle metaData = getPackageManager().getApplicationInfo(getPackageName(), RadialCountdown.BACKGROUND_ALPHA).metaData;
            if (metaData != null) {
                this.autoload = metaData.getBoolean("applovin.sdk.autoload", false);
            }
        } catch (NameNotFoundException e) {
            Log.e("AppLovin SDK", "Name not found", e);
        }
        if (this.autoload) {
            AppLovinFacade.ShowAd(this);
        }
    }
}
