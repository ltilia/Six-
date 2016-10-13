package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.internal.NativeProtocol;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzmn;
import java.util.ArrayList;

public final class AppContentUtils {

    private interface AppContentRunner {
        void zzb(ArrayList<DataHolder> arrayList, int i);
    }

    static class 1 implements AppContentRunner {
        final /* synthetic */ ArrayList zzaDR;

        1(ArrayList arrayList) {
            this.zzaDR = arrayList;
        }

        public void zzb(ArrayList<DataHolder> arrayList, int i) {
            this.zzaDR.add(new AppContentActionRef(arrayList, i));
        }
    }

    static class 2 implements AppContentRunner {
        final /* synthetic */ ArrayList zzaDR;

        2(ArrayList arrayList) {
            this.zzaDR = arrayList;
        }

        public void zzb(ArrayList<DataHolder> arrayList, int i) {
            this.zzaDR.add(new AppContentAnnotationRef(arrayList, i));
        }
    }

    static class 3 implements AppContentRunner {
        final /* synthetic */ ArrayList zzaDR;

        3(ArrayList arrayList) {
            this.zzaDR = arrayList;
        }

        public void zzb(ArrayList<DataHolder> arrayList, int i) {
            this.zzaDR.add(new AppContentConditionRef(arrayList, i));
        }
    }

    static class 4 implements AppContentRunner {
        final /* synthetic */ DataHolder zzaDS;
        final /* synthetic */ Bundle zzaDT;

        4(DataHolder dataHolder, Bundle bundle) {
            this.zzaDS = dataHolder;
            this.zzaDT = bundle;
        }

        public void zzb(ArrayList<DataHolder> arrayList, int i) {
            AppContentTuple appContentTupleRef = new AppContentTupleRef(this.zzaDS, i);
            this.zzaDT.putString(appContentTupleRef.getName(), appContentTupleRef.getValue());
        }
    }

    public static ArrayList<AppContentAction> zza(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        ArrayList<AppContentAction> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        zza(dataHolder2, 1, str2, NativeProtocol.BRIDGE_ARG_ACTION_ID_STRING, i, new 1(arrayList2), arrayList);
        return arrayList2;
    }

    private static void zza(DataHolder dataHolder, int i, String str, String str2, int i2, AppContentRunner appContentRunner, ArrayList<DataHolder> arrayList) {
        DataHolder dataHolder2 = (DataHolder) arrayList.get(i);
        Object zzd = dataHolder.zzd(str, i2, dataHolder.zzbH(i2));
        if (!TextUtils.isEmpty(zzd)) {
            int count = dataHolder2.getCount();
            String[] split = zzd.split(",");
            for (int i3 = 0; i3 < count; i3++) {
                CharSequence zzd2 = dataHolder2.zzd(str2, i3, dataHolder2.zzbH(i3));
                if (!TextUtils.isEmpty(zzd2) && zzmn.zzb(split, zzd2)) {
                    appContentRunner.zzb(arrayList, i3);
                }
            }
        }
    }

    public static ArrayList<AppContentAnnotation> zzb(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        ArrayList<AppContentAnnotation> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        zza(dataHolder2, 2, str2, "annotation_id", i, new 2(arrayList2), arrayList);
        return arrayList2;
    }

    public static ArrayList<AppContentCondition> zzc(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        ArrayList<AppContentCondition> arrayList2 = new ArrayList();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        zza(dataHolder2, 4, str2, "condition_id", i, new 3(arrayList2), arrayList);
        return arrayList2;
    }

    public static Bundle zzd(DataHolder dataHolder, ArrayList<DataHolder> arrayList, String str, int i) {
        Bundle bundle = new Bundle();
        DataHolder dataHolder2 = dataHolder;
        String str2 = str;
        zza(dataHolder2, 3, str2, "tuple_id", i, new 4((DataHolder) arrayList.get(3), bundle), arrayList);
        return bundle;
    }
}
