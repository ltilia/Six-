package com.google.android.gms.games.event;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public final class EventRef extends zzc implements Event {
    EventRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        return EventEntity.zza(this, obj);
    }

    public Event freeze() {
        return new EventEntity(this);
    }

    public String getDescription() {
        return getString(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION);
    }

    public void getDescription(CharArrayBuffer dataOut) {
        zza(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, dataOut);
    }

    public String getEventId() {
        return getString("external_event_id");
    }

    public String getFormattedValue() {
        return getString("formatted_value");
    }

    public void getFormattedValue(CharArrayBuffer dataOut) {
        zza("formatted_value", dataOut);
    }

    public Uri getIconImageUri() {
        return zzcA("icon_image_uri");
    }

    public String getIconImageUrl() {
        return getString("icon_image_url");
    }

    public String getName() {
        return getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
    }

    public void getName(CharArrayBuffer dataOut) {
        zza(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, dataOut);
    }

    public Player getPlayer() {
        return new PlayerRef(this.zzahi, this.zzaje);
    }

    public long getValue() {
        return getLong("value");
    }

    public int hashCode() {
        return EventEntity.zza(this);
    }

    public boolean isVisible() {
        return getBoolean("visibility");
    }

    public String toString() {
        return EventEntity.zzb(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((EventEntity) freeze()).writeToParcel(dest, flags);
    }
}
