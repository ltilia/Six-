package com.unity3d.ads.android.item;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class UnityAdsRewardItem {
    private String _key;
    private String _name;
    private String _pictureURL;
    private final String[] _requiredKeys;
    private JSONObject _rewardItemJSON;

    public UnityAdsRewardItem(JSONObject jSONObject) {
        this._key = null;
        this._name = null;
        this._pictureURL = null;
        this._rewardItemJSON = null;
        this._requiredKeys = new String[]{UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY};
        this._rewardItemJSON = jSONObject;
        parseValues();
    }

    public String getKey() {
        return this._key;
    }

    public String getName() {
        return this._name;
    }

    public String getPictureUrl() {
        return this._pictureURL;
    }

    public boolean hasValidData() {
        return checkDataIntegrity();
    }

    public Map getDetails() {
        Map hashMap = new HashMap();
        hashMap.put(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY, getName());
        hashMap.put(UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY, getPictureUrl());
        return hashMap;
    }

    private void parseValues() {
        try {
            this._key = this._rewardItemJSON.getString(UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY);
            this._name = this._rewardItemJSON.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
            this._pictureURL = this._rewardItemJSON.getString(UnityAdsConstants.UNITY_ADS_REWARD_PICTURE_KEY);
        } catch (Exception e) {
            UnityAdsDeviceLog.error("Problem parsing campaign values");
        }
    }

    private boolean checkDataIntegrity() {
        if (this._rewardItemJSON == null) {
            return false;
        }
        for (String has : this._requiredKeys) {
            if (!this._rewardItemJSON.has(has)) {
                return false;
            }
        }
        return true;
    }
}
