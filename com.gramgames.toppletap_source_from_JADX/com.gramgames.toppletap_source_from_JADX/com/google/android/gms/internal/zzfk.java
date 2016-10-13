package com.google.android.gms.internal;

import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.util.client.zzb;

@zzhb
public final class zzfk<NETWORK_EXTRAS extends NetworkExtras, SERVER_PARAMETERS extends MediationServerParameters> implements MediationBannerListener, MediationInterstitialListener {
    private final zzez zzCK;

    class 10 implements Runnable {
        final /* synthetic */ zzfk zzCQ;
        final /* synthetic */ ErrorCode zzCR;

        10(zzfk com_google_android_gms_internal_zzfk, ErrorCode errorCode) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
            this.zzCR = errorCode;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdFailedToLoad(zzfl.zza(this.zzCR));
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    class 11 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        11(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdLeftApplication();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
            }
        }
    }

    class 1 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        1(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdClicked();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClicked.", e);
            }
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        2(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdOpened();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
            }
        }
    }

    class 3 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        3(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdLoaded();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
            }
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        4(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdClosed();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
            }
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ zzfk zzCQ;
        final /* synthetic */ ErrorCode zzCR;

        5(zzfk com_google_android_gms_internal_zzfk, ErrorCode errorCode) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
            this.zzCR = errorCode;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdFailedToLoad(zzfl.zza(this.zzCR));
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
            }
        }
    }

    class 6 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        6(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdLeftApplication();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
            }
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        7(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdOpened();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
            }
        }
    }

    class 8 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        8(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdLoaded();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
            }
        }
    }

    class 9 implements Runnable {
        final /* synthetic */ zzfk zzCQ;

        9(zzfk com_google_android_gms_internal_zzfk) {
            this.zzCQ = com_google_android_gms_internal_zzfk;
        }

        public void run() {
            try {
                this.zzCQ.zzCK.onAdClosed();
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
            }
        }
    }

    public zzfk(zzez com_google_android_gms_internal_zzez) {
        this.zzCK = com_google_android_gms_internal_zzez;
    }

    public void onClick(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzaI("Adapter called onClick.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdClicked();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClicked.", e);
                return;
            }
        }
        zzb.zzaK("onClick must be called on the main UI thread.");
        zza.zzMS.post(new 1(this));
    }

    public void onDismissScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzaI("Adapter called onDismissScreen.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzaK("onDismissScreen must be called on the main UI thread.");
        zza.zzMS.post(new 4(this));
    }

    public void onDismissScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzaI("Adapter called onDismissScreen.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdClosed();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdClosed.", e);
                return;
            }
        }
        zzb.zzaK("onDismissScreen must be called on the main UI thread.");
        zza.zzMS.post(new 9(this));
    }

    public void onFailedToReceiveAd(MediationBannerAdapter<?, ?> mediationBannerAdapter, ErrorCode errorCode) {
        zzb.zzaI("Adapter called onFailedToReceiveAd with error. " + errorCode);
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdFailedToLoad(zzfl.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzaK("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzMS.post(new 5(this, errorCode));
    }

    public void onFailedToReceiveAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter, ErrorCode errorCode) {
        zzb.zzaI("Adapter called onFailedToReceiveAd with error " + errorCode + ".");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdFailedToLoad(zzfl.zza(errorCode));
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdFailedToLoad.", e);
                return;
            }
        }
        zzb.zzaK("onFailedToReceiveAd must be called on the main UI thread.");
        zza.zzMS.post(new 10(this, errorCode));
    }

    public void onLeaveApplication(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzaI("Adapter called onLeaveApplication.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzaK("onLeaveApplication must be called on the main UI thread.");
        zza.zzMS.post(new 6(this));
    }

    public void onLeaveApplication(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzaI("Adapter called onLeaveApplication.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdLeftApplication();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLeftApplication.", e);
                return;
            }
        }
        zzb.zzaK("onLeaveApplication must be called on the main UI thread.");
        zza.zzMS.post(new 11(this));
    }

    public void onPresentScreen(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzaI("Adapter called onPresentScreen.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzaK("onPresentScreen must be called on the main UI thread.");
        zza.zzMS.post(new 7(this));
    }

    public void onPresentScreen(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzaI("Adapter called onPresentScreen.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdOpened();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdOpened.", e);
                return;
            }
        }
        zzb.zzaK("onPresentScreen must be called on the main UI thread.");
        zza.zzMS.post(new 2(this));
    }

    public void onReceivedAd(MediationBannerAdapter<?, ?> mediationBannerAdapter) {
        zzb.zzaI("Adapter called onReceivedAd.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzaK("onReceivedAd must be called on the main UI thread.");
        zza.zzMS.post(new 8(this));
    }

    public void onReceivedAd(MediationInterstitialAdapter<?, ?> mediationInterstitialAdapter) {
        zzb.zzaI("Adapter called onReceivedAd.");
        if (zzn.zzcS().zzhJ()) {
            try {
                this.zzCK.onAdLoaded();
                return;
            } catch (Throwable e) {
                zzb.zzd("Could not call onAdLoaded.", e);
                return;
            }
        }
        zzb.zzaK("onReceivedAd must be called on the main UI thread.");
        zza.zzMS.post(new 3(this));
    }
}
