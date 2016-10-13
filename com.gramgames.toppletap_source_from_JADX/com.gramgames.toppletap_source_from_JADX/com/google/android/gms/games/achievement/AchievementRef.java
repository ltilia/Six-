package com.google.android.gms.games.achievement;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import com.facebook.internal.ServerProtocol;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerRef;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public final class AchievementRef extends zzc implements Achievement {
    AchievementRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public int describeContents() {
        return 0;
    }

    public Achievement freeze() {
        return new AchievementEntity(this);
    }

    public String getAchievementId() {
        return getString("external_achievement_id");
    }

    public int getCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        return getInteger("current_steps");
    }

    public String getDescription() {
        return getString(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION);
    }

    public void getDescription(CharArrayBuffer dataOut) {
        zza(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, dataOut);
    }

    public String getFormattedCurrentSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        return getString("formatted_current_steps");
    }

    public void getFormattedCurrentSteps(CharArrayBuffer dataOut) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        zza("formatted_current_steps", dataOut);
    }

    public String getFormattedTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        return getString("formatted_total_steps");
    }

    public void getFormattedTotalSteps(CharArrayBuffer dataOut) {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        zza("formatted_total_steps", dataOut);
    }

    public long getLastUpdatedTimestamp() {
        return getLong("last_updated_timestamp");
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

    public Uri getRevealedImageUri() {
        return zzcA("revealed_icon_image_uri");
    }

    public String getRevealedImageUrl() {
        return getString("revealed_icon_image_url");
    }

    public int getState() {
        return getInteger(ServerProtocol.DIALOG_PARAM_STATE);
    }

    public int getTotalSteps() {
        boolean z = true;
        if (getType() != 1) {
            z = false;
        }
        zzb.zzab(z);
        return getInteger("total_steps");
    }

    public int getType() {
        return getInteger(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
    }

    public Uri getUnlockedImageUri() {
        return zzcA("unlocked_icon_image_uri");
    }

    public String getUnlockedImageUrl() {
        return getString("unlocked_icon_image_url");
    }

    public long getXpValue() {
        return (!zzcz("instance_xp_value") || zzcB("instance_xp_value")) ? getLong("definition_xp_value") : getLong("instance_xp_value");
    }

    public String toString() {
        return AchievementEntity.zzb(this);
    }

    public void writeToParcel(Parcel dest, int flags) {
        ((AchievementEntity) freeze()).writeToParcel(dest, flags);
    }
}
