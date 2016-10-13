package com.google.android.gms.internal;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import gs.gram.mopub.BuildConfig;
import java.util.concurrent.Future;

@zzhb
public final class zzip {

    public interface zzb {
        void zze(Bundle bundle);
    }

    private static abstract class zza extends zzim {
        private zza() {
        }

        public void onStop() {
        }
    }

    static class 1 extends zza {
        final /* synthetic */ boolean zzLP;
        final /* synthetic */ Context zzxh;

        1(Context context, boolean z) {
            this.zzxh = context;
            this.zzLP = z;
            super();
        }

        public void zzbr() {
            Editor edit = zzip.zzw(this.zzxh).edit();
            edit.putBoolean("use_https", this.zzLP);
            edit.apply();
        }
    }

    static class 2 extends zza {
        final /* synthetic */ zzb zzLQ;
        final /* synthetic */ Context zzxh;

        2(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
            this.zzxh = context;
            this.zzLQ = com_google_android_gms_internal_zzip_zzb;
            super();
        }

        public void zzbr() {
            SharedPreferences zzw = zzip.zzw(this.zzxh);
            Bundle bundle = new Bundle();
            bundle.putBoolean("use_https", zzw.getBoolean("use_https", true));
            if (this.zzLQ != null) {
                this.zzLQ.zze(bundle);
            }
        }
    }

    static class 3 extends zza {
        final /* synthetic */ int zzLR;
        final /* synthetic */ Context zzxh;

        3(Context context, int i) {
            this.zzxh = context;
            this.zzLR = i;
            super();
        }

        public void zzbr() {
            Editor edit = zzip.zzw(this.zzxh).edit();
            edit.putInt("webview_cache_version", this.zzLR);
            edit.apply();
        }
    }

    static class 4 extends zza {
        final /* synthetic */ zzb zzLQ;
        final /* synthetic */ Context zzxh;

        4(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
            this.zzxh = context;
            this.zzLQ = com_google_android_gms_internal_zzip_zzb;
            super();
        }

        public void zzbr() {
            SharedPreferences zzw = zzip.zzw(this.zzxh);
            Bundle bundle = new Bundle();
            bundle.putInt("webview_cache_version", zzw.getInt("webview_cache_version", 0));
            if (this.zzLQ != null) {
                this.zzLQ.zze(bundle);
            }
        }
    }

    static class 5 extends zza {
        final /* synthetic */ boolean zzLS;
        final /* synthetic */ Context zzxh;

        5(Context context, boolean z) {
            this.zzxh = context;
            this.zzLS = z;
            super();
        }

        public void zzbr() {
            Editor edit = zzip.zzw(this.zzxh).edit();
            edit.putBoolean("content_url_opted_out", this.zzLS);
            edit.apply();
        }
    }

    static class 6 extends zza {
        final /* synthetic */ zzb zzLQ;
        final /* synthetic */ Context zzxh;

        6(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
            this.zzxh = context;
            this.zzLQ = com_google_android_gms_internal_zzip_zzb;
            super();
        }

        public void zzbr() {
            SharedPreferences zzw = zzip.zzw(this.zzxh);
            Bundle bundle = new Bundle();
            bundle.putBoolean("content_url_opted_out", zzw.getBoolean("content_url_opted_out", true));
            if (this.zzLQ != null) {
                this.zzLQ.zze(bundle);
            }
        }
    }

    static class 7 extends zza {
        final /* synthetic */ String zzLT;
        final /* synthetic */ Context zzxh;

        7(Context context, String str) {
            this.zzxh = context;
            this.zzLT = str;
            super();
        }

        public void zzbr() {
            Editor edit = zzip.zzw(this.zzxh).edit();
            edit.putString("content_url_hashes", this.zzLT);
            edit.apply();
        }
    }

    static class 8 extends zza {
        final /* synthetic */ zzb zzLQ;
        final /* synthetic */ Context zzxh;

        8(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
            this.zzxh = context;
            this.zzLQ = com_google_android_gms_internal_zzip_zzb;
            super();
        }

        public void zzbr() {
            SharedPreferences zzw = zzip.zzw(this.zzxh);
            Bundle bundle = new Bundle();
            bundle.putString("content_url_hashes", zzw.getString("content_url_hashes", BuildConfig.FLAVOR));
            if (this.zzLQ != null) {
                this.zzLQ.zze(bundle);
            }
        }
    }

    public static Future zza(Context context, int i) {
        return new 3(context, i).zzhn();
    }

    public static Future zza(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
        return new 2(context, com_google_android_gms_internal_zzip_zzb).zzhn();
    }

    public static Future zza(Context context, boolean z) {
        return new 1(context, z).zzhn();
    }

    public static Future zzb(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
        return new 4(context, com_google_android_gms_internal_zzip_zzb).zzhn();
    }

    public static Future zzb(Context context, boolean z) {
        return new 5(context, z).zzhn();
    }

    public static Future zzc(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
        return new 6(context, com_google_android_gms_internal_zzip_zzb).zzhn();
    }

    public static Future zzd(Context context, zzb com_google_android_gms_internal_zzip_zzb) {
        return new 8(context, com_google_android_gms_internal_zzip_zzb).zzhn();
    }

    public static Future zzd(Context context, String str) {
        return new 7(context, str).zzhn();
    }

    public static SharedPreferences zzw(Context context) {
        return context.getSharedPreferences("admob", 0);
    }
}
