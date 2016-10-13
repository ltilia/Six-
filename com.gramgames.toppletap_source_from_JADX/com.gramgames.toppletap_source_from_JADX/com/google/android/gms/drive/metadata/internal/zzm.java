package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.metadata.SearchableCollectionMetadataField;
import com.google.android.gms.drive.metadata.internal.zze.zza;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class zzm extends zzj<DriveId> implements SearchableCollectionMetadataField<DriveId> {
    public static final zza zzasT;

    static class 1 implements zza {
        1() {
        }

        public void zzb(DataHolder dataHolder) {
            zzm.zzd(dataHolder);
        }

        public String zztD() {
            return "parentsExtraHolder";
        }
    }

    static {
        zzasT = new 1();
    }

    public zzm(int i) {
        super("parents", Collections.emptySet(), Arrays.asList(new String[]{"parentsExtra", "dbInstanceId", "parentsExtraHolder"}), i);
    }

    private void zzc(DataHolder dataHolder) {
        synchronized (dataHolder) {
            DataHolder dataHolder2 = (DataHolder) dataHolder.zzpZ().getParcelable("parentsExtraHolder");
            if (dataHolder2 == null) {
                return;
            }
            try {
                int i;
                int count = dataHolder.getCount();
                ArrayList arrayList = new ArrayList(count);
                Map hashMap = new HashMap(count);
                for (i = 0; i < count; i++) {
                    int zzbH = dataHolder.zzbH(i);
                    ParentDriveIdSet parentDriveIdSet = new ParentDriveIdSet();
                    arrayList.add(parentDriveIdSet);
                    hashMap.put(Long.valueOf(dataHolder.zzb("sqlId", i, zzbH)), parentDriveIdSet);
                }
                Bundle zzpZ = dataHolder2.zzpZ();
                String string = zzpZ.getString("childSqlIdColumn");
                String string2 = zzpZ.getString("parentSqlIdColumn");
                String string3 = zzpZ.getString("parentResIdColumn");
                int count2 = dataHolder2.getCount();
                for (i = 0; i < count2; i++) {
                    int zzbH2 = dataHolder2.zzbH(i);
                    ((ParentDriveIdSet) hashMap.get(Long.valueOf(dataHolder2.zzb(string, i, zzbH2)))).zza(new PartialDriveId(dataHolder2.zzd(string3, i, zzbH2), dataHolder2.zzb(string2, i, zzbH2), 1));
                }
                dataHolder.zzpZ().putParcelableArrayList("parentsExtra", arrayList);
            } finally {
                dataHolder2.close();
                dataHolder.zzpZ().remove("parentsExtraHolder");
            }
        }
    }

    private static void zzd(DataHolder dataHolder) {
        Bundle zzpZ = dataHolder.zzpZ();
        if (zzpZ != null) {
            synchronized (dataHolder) {
                DataHolder dataHolder2 = (DataHolder) zzpZ.getParcelable("parentsExtraHolder");
                if (dataHolder2 != null) {
                    dataHolder2.close();
                    zzpZ.remove("parentsExtraHolder");
                }
            }
        }
    }

    protected /* synthetic */ Object zzc(DataHolder dataHolder, int i, int i2) {
        return zzd(dataHolder, i, i2);
    }

    protected Collection<DriveId> zzd(DataHolder dataHolder, int i, int i2) {
        Bundle zzpZ = dataHolder.zzpZ();
        List parcelableArrayList = zzpZ.getParcelableArrayList("parentsExtra");
        if (parcelableArrayList == null) {
            if (zzpZ.getParcelable("parentsExtraHolder") != null) {
                zzc(dataHolder);
                parcelableArrayList = zzpZ.getParcelableArrayList("parentsExtra");
            }
            if (parcelableArrayList == null) {
                return null;
            }
        }
        return ((ParentDriveIdSet) parcelableArrayList.get(i)).zzD(zzpZ.getLong("dbInstanceId"));
    }

    protected /* synthetic */ Object zzn(Bundle bundle) {
        return zzs(bundle);
    }

    protected Collection<DriveId> zzs(Bundle bundle) {
        Collection zzs = super.zzs(bundle);
        return zzs == null ? null : new HashSet(zzs);
    }
}
