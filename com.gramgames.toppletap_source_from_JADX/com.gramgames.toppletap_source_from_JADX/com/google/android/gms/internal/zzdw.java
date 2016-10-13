package com.google.android.gms.internal;

import android.os.Handler;
import android.os.RemoteException;
import com.google.android.gms.ads.internal.util.client.zzb;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzr;
import java.util.LinkedList;
import java.util.List;

@zzhb
class zzdw {
    private final List<zza> zzpH;

    interface zza {
        void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException;
    }

    class 1 extends com.google.android.gms.ads.internal.client.zzq.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ 1 zzAd;

            1(1 1) {
                this.zzAd = 1;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdClosed();
                }
                zzr.zzbN().zzee();
            }
        }

        class 2 implements zza {
            final /* synthetic */ 1 zzAd;
            final /* synthetic */ int zzAe;

            2(1 1, int i) {
                this.zzAd = 1;
                this.zzAe = i;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdFailedToLoad(this.zzAe);
                }
            }
        }

        class 3 implements zza {
            final /* synthetic */ 1 zzAd;

            3(1 1) {
                this.zzAd = 1;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdLeftApplication();
                }
            }
        }

        class 4 implements zza {
            final /* synthetic */ 1 zzAd;

            4(1 1) {
                this.zzAd = 1;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdLoaded();
                }
            }
        }

        class 5 implements zza {
            final /* synthetic */ 1 zzAd;

            5(1 1) {
                this.zzAd = 1;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzpK != null) {
                    com_google_android_gms_internal_zzdx.zzpK.onAdOpened();
                }
            }
        }

        1(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAdClosed() throws RemoteException {
            this.zzAc.zzpH.add(new 1(this));
        }

        public void onAdFailedToLoad(int errorCode) throws RemoteException {
            this.zzAc.zzpH.add(new 2(this, errorCode));
            zzin.v("Pooled interstitial failed to load.");
        }

        public void onAdLeftApplication() throws RemoteException {
            this.zzAc.zzpH.add(new 3(this));
        }

        public void onAdLoaded() throws RemoteException {
            this.zzAc.zzpH.add(new 4(this));
            zzin.v("Pooled interstitial loaded.");
        }

        public void onAdOpened() throws RemoteException {
            this.zzAc.zzpH.add(new 5(this));
        }
    }

    class 2 extends com.google.android.gms.ads.internal.client.zzw.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ String val$name;
            final /* synthetic */ String zzAf;
            final /* synthetic */ 2 zzAg;

            1(2 2, String str, String str2) {
                this.zzAg = 2;
                this.val$name = str;
                this.zzAf = str2;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAq != null) {
                    com_google_android_gms_internal_zzdx.zzAq.onAppEvent(this.val$name, this.zzAf);
                }
            }
        }

        2(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAppEvent(String name, String info) throws RemoteException {
            this.zzAc.zzpH.add(new 1(this, name, info));
        }
    }

    class 3 extends com.google.android.gms.internal.zzgd.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ zzgc zzAh;
            final /* synthetic */ 3 zzAi;

            1(3 3, zzgc com_google_android_gms_internal_zzgc) {
                this.zzAi = 3;
                this.zzAh = com_google_android_gms_internal_zzgc;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAr != null) {
                    com_google_android_gms_internal_zzdx.zzAr.zza(this.zzAh);
                }
            }
        }

        3(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void zza(zzgc com_google_android_gms_internal_zzgc) throws RemoteException {
            this.zzAc.zzpH.add(new 1(this, com_google_android_gms_internal_zzgc));
        }
    }

    class 4 extends com.google.android.gms.internal.zzcf.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ zzce zzAj;
            final /* synthetic */ 4 zzAk;

            1(4 4, zzce com_google_android_gms_internal_zzce) {
                this.zzAk = 4;
                this.zzAj = com_google_android_gms_internal_zzce;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAs != null) {
                    com_google_android_gms_internal_zzdx.zzAs.zza(this.zzAj);
                }
            }
        }

        4(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void zza(zzce com_google_android_gms_internal_zzce) throws RemoteException {
            this.zzAc.zzpH.add(new 1(this, com_google_android_gms_internal_zzce));
        }
    }

    class 5 extends com.google.android.gms.ads.internal.client.zzp.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ 5 zzAl;

            1(5 5) {
                this.zzAl = 5;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAt != null) {
                    com_google_android_gms_internal_zzdx.zzAt.onAdClicked();
                }
            }
        }

        5(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onAdClicked() throws RemoteException {
            this.zzAc.zzpH.add(new 1(this));
        }
    }

    class 6 extends com.google.android.gms.ads.internal.reward.client.zzd.zza {
        final /* synthetic */ zzdw zzAc;

        class 1 implements zza {
            final /* synthetic */ 6 zzAm;

            1(6 6) {
                this.zzAm = 6;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdLoaded();
                }
            }
        }

        class 2 implements zza {
            final /* synthetic */ 6 zzAm;

            2(6 6) {
                this.zzAm = 6;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdOpened();
                }
            }
        }

        class 3 implements zza {
            final /* synthetic */ 6 zzAm;

            3(6 6) {
                this.zzAm = 6;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoStarted();
                }
            }
        }

        class 4 implements zza {
            final /* synthetic */ 6 zzAm;

            4(6 6) {
                this.zzAm = 6;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdClosed();
                }
            }
        }

        class 5 implements zza {
            final /* synthetic */ 6 zzAm;
            final /* synthetic */ com.google.android.gms.ads.internal.reward.client.zza zzAn;

            5(6 6, com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) {
                this.zzAm = 6;
                this.zzAn = com_google_android_gms_ads_internal_reward_client_zza;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.zza(this.zzAn);
                }
            }
        }

        class 6 implements zza {
            final /* synthetic */ 6 zzAm;

            6(6 6) {
                this.zzAm = 6;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdLeftApplication();
                }
            }
        }

        class 7 implements zza {
            final /* synthetic */ int zzAe;
            final /* synthetic */ 6 zzAm;

            7(6 6, int i) {
                this.zzAm = 6;
                this.zzAe = i;
            }

            public void zzb(zzdx com_google_android_gms_internal_zzdx) throws RemoteException {
                if (com_google_android_gms_internal_zzdx.zzAu != null) {
                    com_google_android_gms_internal_zzdx.zzAu.onRewardedVideoAdFailedToLoad(this.zzAe);
                }
            }
        }

        6(zzdw com_google_android_gms_internal_zzdw) {
            this.zzAc = com_google_android_gms_internal_zzdw;
        }

        public void onRewardedVideoAdClosed() throws RemoteException {
            this.zzAc.zzpH.add(new 4(this));
        }

        public void onRewardedVideoAdFailedToLoad(int errorCode) throws RemoteException {
            this.zzAc.zzpH.add(new 7(this, errorCode));
        }

        public void onRewardedVideoAdLeftApplication() throws RemoteException {
            this.zzAc.zzpH.add(new 6(this));
        }

        public void onRewardedVideoAdLoaded() throws RemoteException {
            this.zzAc.zzpH.add(new 1(this));
        }

        public void onRewardedVideoAdOpened() throws RemoteException {
            this.zzAc.zzpH.add(new 2(this));
        }

        public void onRewardedVideoStarted() throws RemoteException {
            this.zzAc.zzpH.add(new 3(this));
        }

        public void zza(com.google.android.gms.ads.internal.reward.client.zza com_google_android_gms_ads_internal_reward_client_zza) throws RemoteException {
            this.zzAc.zzpH.add(new 5(this, com_google_android_gms_ads_internal_reward_client_zza));
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ zzdw zzAc;
        final /* synthetic */ zza zzAo;
        final /* synthetic */ zzdx zzAp;

        7(zzdw com_google_android_gms_internal_zzdw, zza com_google_android_gms_internal_zzdw_zza, zzdx com_google_android_gms_internal_zzdx) {
            this.zzAc = com_google_android_gms_internal_zzdw;
            this.zzAo = com_google_android_gms_internal_zzdw_zza;
            this.zzAp = com_google_android_gms_internal_zzdx;
        }

        public void run() {
            try {
                this.zzAo.zzb(this.zzAp);
            } catch (Throwable e) {
                zzb.zzd("Could not propagate interstitial ad event.", e);
            }
        }
    }

    zzdw() {
        this.zzpH = new LinkedList();
    }

    void zza(zzdx com_google_android_gms_internal_zzdx) {
        Handler handler = zzir.zzMc;
        for (zza 7 : this.zzpH) {
            handler.post(new 7(this, 7, com_google_android_gms_internal_zzdx));
        }
    }

    void zzc(zzk com_google_android_gms_ads_internal_zzk) {
        com_google_android_gms_ads_internal_zzk.zza(new 1(this));
        com_google_android_gms_ads_internal_zzk.zza(new 2(this));
        com_google_android_gms_ads_internal_zzk.zza(new 3(this));
        com_google_android_gms_ads_internal_zzk.zza(new 4(this));
        com_google_android_gms_ads_internal_zzk.zza(new 5(this));
        com_google_android_gms_ads_internal_zzk.zza(new 6(this));
    }
}
