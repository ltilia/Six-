package com.google.android.gms.common.data;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public class zzd<T extends SafeParcelable> extends AbstractDataBuffer<T> {
    private static final String[] zzajg;
    private final Creator<T> zzajh;

    static {
        zzajg = new String[]{UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY};
    }

    public zzd(DataHolder dataHolder, Creator<T> creator) {
        super(dataHolder);
        this.zzajh = creator;
    }

    public /* synthetic */ Object get(int i) {
        return zzbG(i);
    }

    public T zzbG(int i) {
        byte[] zzg = this.zzahi.zzg(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, i, this.zzahi.zzbH(i));
        Parcel obtain = Parcel.obtain();
        obtain.unmarshall(zzg, 0, zzg.length);
        obtain.setDataPosition(0);
        SafeParcelable safeParcelable = (SafeParcelable) this.zzajh.createFromParcel(obtain);
        obtain.recycle();
        return safeParcelable;
    }
}
