package com.unity3d.ads.android.campaign;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import org.json.JSONObject;

public class UnityAdsCampaign {
    private JSONObject _campaignJson;
    private UnityAdsCampaignStatus _campaignStatus;
    private final String[] _requiredKeys;

    public enum UnityAdsCampaignStatus {
        READY,
        VIEWED;

        public final String toString() {
            return name();
        }
    }

    public UnityAdsCampaign(JSONObject jSONObject) {
        this._campaignJson = null;
        this._requiredKeys = new String[]{UnityAdsConstants.UNITY_ADS_CAMPAIGN_ENDSCREEN_KEY, UnityAdsConstants.UNITY_ADS_WEBVIEW_EVENTDATA_CLICKURL_KEY, UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_STREAMING_KEY, UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_GAME_NAME_KEY, UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, UnityAdsConstants.UNITY_ADS_CAMPAIGN_TAGLINE_KEY};
        this._campaignStatus = UnityAdsCampaignStatus.READY;
        this._campaignJson = jSONObject;
    }

    public String toString() {
        return "ID: " + getCampaignId() + ", STATUS: " + getCampaignStatus().toString() + ", URL: " + getVideoUrl();
    }

    public Boolean forceCacheVideo() {
        if (checkDataIntegrity()) {
            try {
                return Boolean.valueOf(this._campaignJson.getBoolean(UnityAdsConstants.UNITY_ADS_CAMPAIGN_CACHE_VIDEO_KEY));
            } catch (Exception e) {
                UnityAdsDeviceLog.warning("Key not found for campaign: " + getCampaignId());
            }
        }
        return Boolean.valueOf(false);
    }

    public Boolean allowCacheVideo() {
        if (checkDataIntegrity()) {
            try {
                return Boolean.valueOf(this._campaignJson.getBoolean(UnityAdsConstants.UNITY_ADS_CAMPAIGN_ALLOW_CACHE_KEY));
            } catch (Exception e) {
                UnityAdsDeviceLog.warning("Key not found for campaign: " + getCampaignId());
            }
        }
        return Boolean.valueOf(false);
    }

    public Boolean allowStreamingVideo() {
        if (checkDataIntegrity()) {
            try {
                return Boolean.valueOf(this._campaignJson.getBoolean(UnityAdsConstants.UNITY_ADS_CAMPAIGN_ALLOW_STREAMING_KEY));
            } catch (Exception e) {
                UnityAdsDeviceLog.debug("Could not get streaming video status");
            }
        }
        return Boolean.valueOf(true);
    }

    public String getCampaignId() {
        if (checkDataIntegrity()) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getGameId() {
        if (checkDataIntegrity()) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_GAMEID_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoUrl() {
        if (checkDataIntegrity()) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoStreamUrl() {
        if (checkDataIntegrity()) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_STREAMING_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public String getVideoFilename() {
        if (checkDataIntegrity()) {
            try {
                return new StringBuilder(UnityAdsConstants.UNITY_ADS_LOCALFILE_PREFIX).append(getCampaignId()).append("-").append(new File(this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_DOWNLOADABLE_KEY)).getName()).toString();
            } catch (Exception e) {
                UnityAdsDeviceLog.error("This should not happen!");
            }
        }
        return null;
    }

    public long getVideoFileExpectedSize() {
        long j = -1;
        if (checkDataIntegrity()) {
            try {
                try {
                    j = Long.parseLong(this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_TRAILER_SIZE_KEY));
                } catch (Exception e) {
                    UnityAdsDeviceLog.error("Could not parse size: " + e.getMessage());
                }
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Not found, returning -1");
            }
        }
        return j;
    }

    public String getStoreId() {
        if (this._campaignJson.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY)) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY);
            } catch (Exception e) {
                UnityAdsDeviceLog.error("Was supposed to use UnityAdsConstants.UNITY_ADS_CAMPAIGN_STOREID_KEY but " + e.getMessage() + " occured");
            }
        }
        if (this._campaignJson.has(UnityAdsConstants.UNITY_ADS_PLAYSTORE_ITUNESID_KEY)) {
            try {
                return this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_PLAYSTORE_ITUNESID_KEY);
            } catch (Exception e2) {
                UnityAdsDeviceLog.error("Was supposed to use UnityAdsConstants.UNITY_ADS_CAMPAIGN_ITUNESID_KEY but " + e2.getMessage() + " occured");
            }
        }
        return null;
    }

    public String getFilterMode() {
        String str = null;
        try {
            if (checkDataIntegrity() && this._campaignJson.has(UnityAdsConstants.UNITY_ADS_CAMPAIGN_FILTER_MODE)) {
                str = this._campaignJson.getString(UnityAdsConstants.UNITY_ADS_CAMPAIGN_FILTER_MODE);
            }
        } catch (Exception e) {
        }
        return str;
    }

    public UnityAdsCampaignStatus getCampaignStatus() {
        return this._campaignStatus;
    }

    public void setCampaignStatus(UnityAdsCampaignStatus unityAdsCampaignStatus) {
        this._campaignStatus = unityAdsCampaignStatus;
    }

    public Boolean isViewed() {
        return Boolean.valueOf(this._campaignStatus == UnityAdsCampaignStatus.VIEWED);
    }

    public boolean hasValidData() {
        return checkDataIntegrity();
    }

    private boolean checkDataIntegrity() {
        if (this._campaignJson == null) {
            return false;
        }
        for (String has : this._requiredKeys) {
            if (!this._campaignJson.has(has)) {
                return false;
            }
        }
        return true;
    }
}
