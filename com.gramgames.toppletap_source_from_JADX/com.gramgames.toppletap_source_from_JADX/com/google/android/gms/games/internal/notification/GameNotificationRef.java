package com.google.android.gms.games.internal.notification;

import com.area730.localnotif.NotificationReciever;
import com.facebook.share.internal.ShareConstants;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.zzw;
import com.unity3d.ads.android.properties.UnityAdsConstants;

public final class GameNotificationRef extends zzc implements GameNotification {
    GameNotificationRef(DataHolder holder, int dataRow) {
        super(holder, dataRow);
    }

    public long getId() {
        return getLong("_id");
    }

    public String getText() {
        return getString(MimeTypes.BASE_TYPE_TEXT);
    }

    public String getTitle() {
        return getString(ShareConstants.WEB_DIALOG_PARAM_TITLE);
    }

    public int getType() {
        return getInteger(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
    }

    public String toString() {
        return zzw.zzy(this).zzg("Id", Long.valueOf(getId())).zzg("NotificationId", zzxt()).zzg("Type", Integer.valueOf(getType())).zzg("Title", getTitle()).zzg("Ticker", zzxu()).zzg("Text", getText()).zzg("CoalescedText", zzxv()).zzg("isAcknowledged", Boolean.valueOf(zzxw())).zzg("isSilent", Boolean.valueOf(zzxx())).toString();
    }

    public String zzxt() {
        return getString("notification_id");
    }

    public String zzxu() {
        return getString(NotificationReciever.TICKER_KEY);
    }

    public String zzxv() {
        return getString("coalesced_text");
    }

    public boolean zzxw() {
        return getInteger("acknowledged") > 0;
    }

    public boolean zzxx() {
        return getInteger("alert_level") == 0;
    }
}
