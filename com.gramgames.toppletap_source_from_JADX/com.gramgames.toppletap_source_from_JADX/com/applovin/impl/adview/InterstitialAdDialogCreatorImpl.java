package com.applovin.impl.adview;

import android.app.Activity;
import com.applovin.adview.AppLovinInterstitialAdDialog;
import com.applovin.adview.InterstitialAdDialogCreator;
import com.applovin.sdk.AppLovinSdk;
import java.lang.ref.WeakReference;

public class InterstitialAdDialogCreatorImpl implements InterstitialAdDialogCreator {
    private static final Object a;
    private static WeakReference b;
    private static WeakReference c;

    static {
        a = new Object();
        b = new WeakReference(null);
        c = new WeakReference(null);
    }

    public AppLovinInterstitialAdDialog createInterstitialAdDialog(AppLovinSdk appLovinSdk, Activity activity) {
        AppLovinInterstitialAdDialog appLovinInterstitialAdDialog;
        if (appLovinSdk == null) {
            appLovinSdk = AppLovinSdk.getInstance(activity);
        }
        synchronized (a) {
            appLovinInterstitialAdDialog = (ah) b.get();
            if (appLovinInterstitialAdDialog != null && appLovinInterstitialAdDialog.isShowing() && c.get() == activity) {
                appLovinSdk.getLogger().w("InterstitialAdDialogCreator", "An interstitial dialog is already showing, returning it");
            } else {
                appLovinInterstitialAdDialog = new ah(appLovinSdk, activity);
                b = new WeakReference(appLovinInterstitialAdDialog);
                c = new WeakReference(activity);
            }
        }
        return appLovinInterstitialAdDialog;
    }
}
