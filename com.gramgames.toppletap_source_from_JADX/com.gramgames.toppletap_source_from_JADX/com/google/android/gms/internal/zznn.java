package com.google.android.gms.internal;

import android.os.Bundle;
import android.support.v4.util.LongSparseArray;
import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.drive.metadata.internal.AppVisibleCustomProperties;
import com.google.android.gms.drive.metadata.internal.CustomProperty;
import com.google.android.gms.drive.metadata.internal.zze.zza;
import com.google.android.gms.drive.metadata.internal.zzk;
import java.util.Arrays;

public class zznn extends zzk<AppVisibleCustomProperties> {
    public static final zza zzatL;

    static class 1 implements zza {
        1() {
        }

        public void zzb(DataHolder dataHolder) {
            zznn.zzd(dataHolder);
        }

        public String zztD() {
            return "customPropertiesExtraHolder";
        }
    }

    static {
        zzatL = new 1();
    }

    public zznn(int i) {
        super("customProperties", Arrays.asList(new String[]{"hasCustomProperties", "sqlId"}), Arrays.asList(new String[]{"customPropertiesExtra", "customPropertiesExtraHolder"}), i);
    }

    private static void zzc(DataHolder dataHolder) {
        synchronized (dataHolder) {
            DataHolder dataHolder2 = (DataHolder) dataHolder.zzpZ().getParcelable("customPropertiesExtraHolder");
            if (dataHolder2 == null) {
                return;
            }
            try {
                LongSparseArray zzf = zzf(dataHolder2);
                SparseArray sparseArray = new SparseArray();
                for (int i = 0; i < dataHolder.getCount(); i++) {
                    AppVisibleCustomProperties.zza com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza = (AppVisibleCustomProperties.zza) zzf.get(dataHolder.zzb("sqlId", i, dataHolder.zzbH(i)));
                    if (com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza != null) {
                        sparseArray.append(i, com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza.zztA());
                    }
                }
                dataHolder.zzpZ().putSparseParcelableArray("customPropertiesExtra", sparseArray);
            } finally {
                dataHolder2.close();
                dataHolder.zzpZ().remove("customPropertiesExtraHolder");
            }
        }
    }

    private static void zzd(DataHolder dataHolder) {
        Bundle zzpZ = dataHolder.zzpZ();
        if (zzpZ != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) zzpZ.getParcelable("customPropertiesExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    zzpZ.remove("customPropertiesExtraHolder");
                }
            }
        }
    }

    private static LongSparseArray<AppVisibleCustomProperties.zza> zzf(DataHolder dataHolder) {
        Bundle zzpZ = dataHolder.zzpZ();
        String string = zzpZ.getString("entryIdColumn");
        String string2 = zzpZ.getString("keyColumn");
        String string3 = zzpZ.getString("visibilityColumn");
        String string4 = zzpZ.getString("valueColumn");
        LongSparseArray<AppVisibleCustomProperties.zza> longSparseArray = new LongSparseArray();
        for (int i = 0; i < dataHolder.getCount(); i++) {
            int zzbH = dataHolder.zzbH(i);
            long zzb = dataHolder.zzb(string, i, zzbH);
            String zzd = dataHolder.zzd(string2, i, zzbH);
            int zzc = dataHolder.zzc(string3, i, zzbH);
            CustomProperty customProperty = new CustomProperty(new CustomPropertyKey(zzd, zzc), dataHolder.zzd(string4, i, zzbH));
            AppVisibleCustomProperties.zza com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza = (AppVisibleCustomProperties.zza) longSparseArray.get(zzb);
            if (com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza == null) {
                com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza = new AppVisibleCustomProperties.zza();
                longSparseArray.put(zzb, com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza);
            }
            com_google_android_gms_drive_metadata_internal_AppVisibleCustomProperties_zza.zza(customProperty);
        }
        return longSparseArray;
    }

    protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzl(dataHolder, i, i2);
    }

    protected AppVisibleCustomProperties zzl(DataHolder dataHolder, int i, int i2) {
        Bundle zzpZ = dataHolder.zzpZ();
        SparseArray sparseParcelableArray = zzpZ.getSparseParcelableArray("customPropertiesExtra");
        if (sparseParcelableArray == null) {
            if (zzpZ.getParcelable("customPropertiesExtraHolder") != null) {
                zzc(dataHolder);
                sparseParcelableArray = zzpZ.getSparseParcelableArray("customPropertiesExtra");
            }
            if (sparseParcelableArray == null) {
                return AppVisibleCustomProperties.zzasK;
            }
        }
        return (AppVisibleCustomProperties) sparseParcelableArray.get(i, AppVisibleCustomProperties.zzasK);
    }
}
