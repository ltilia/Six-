package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.item.UnityAdsRewardItem;
import com.unity3d.ads.android.item.UnityAdsRewardItemManager;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.JSONObject;

public class UnityAdsIncentivizedZone extends UnityAdsZone {
    private UnityAdsRewardItemManager _rewardItems;

    public UnityAdsIncentivizedZone(JSONObject jSONObject) {
        super(jSONObject);
        this._rewardItems = null;
        this._rewardItems = new UnityAdsRewardItemManager(jSONObject.getJSONArray(UnityAdsConstants.UNITY_ADS_ZONE_REWARD_ITEMS_KEY), new UnityAdsRewardItem(jSONObject.getJSONObject(UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_REWARD_ITEM_KEY)).getKey());
    }

    public boolean isIncentivized() {
        return true;
    }

    public UnityAdsRewardItemManager itemManager() {
        return this._rewardItems;
    }
}
