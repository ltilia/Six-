package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class AppVisibleCustomProperties implements SafeParcelable, Iterable<CustomProperty> {
    public static final Creator<AppVisibleCustomProperties> CREATOR;
    public static final AppVisibleCustomProperties zzasK;
    final int mVersionCode;
    final List<CustomProperty> zzasL;

    public static class zza {
        private final Map<CustomPropertyKey, CustomProperty> zzasM;

        public zza() {
            this.zzasM = new HashMap();
        }

        public zza zza(CustomPropertyKey customPropertyKey, String str) {
            zzx.zzb((Object) customPropertyKey, UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
            this.zzasM.put(customPropertyKey, new CustomProperty(customPropertyKey, str));
            return this;
        }

        public zza zza(CustomProperty customProperty) {
            zzx.zzb((Object) customProperty, (Object) "property");
            this.zzasM.put(customProperty.zztB(), customProperty);
            return this;
        }

        public AppVisibleCustomProperties zztA() {
            return new AppVisibleCustomProperties(null);
        }
    }

    static {
        CREATOR = new zza();
        zzasK = new zza().zztA();
    }

    AppVisibleCustomProperties(int versionCode, Collection<CustomProperty> properties) {
        this.mVersionCode = versionCode;
        zzx.zzz(properties);
        this.zzasL = new ArrayList(properties);
    }

    private AppVisibleCustomProperties(Collection<CustomProperty> properties) {
        this(1, (Collection) properties);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        return (o == null || o.getClass() != getClass()) ? false : zztz().equals(((AppVisibleCustomProperties) o).zztz());
    }

    public int hashCode() {
        return zzw.hashCode(this.zzasL);
    }

    public Iterator<CustomProperty> iterator() {
        return this.zzasL.iterator();
    }

    public void writeToParcel(Parcel dest, int flags) {
        zza.zza(this, dest, flags);
    }

    public Map<CustomPropertyKey, String> zztz() {
        Map hashMap = new HashMap(this.zzasL.size());
        for (CustomProperty customProperty : this.zzasL) {
            hashMap.put(customProperty.zztB(), customProperty.getValue());
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
