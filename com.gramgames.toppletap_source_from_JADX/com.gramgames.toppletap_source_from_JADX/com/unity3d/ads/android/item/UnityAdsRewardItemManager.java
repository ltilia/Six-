package com.unity3d.ads.android.item;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;

public class UnityAdsRewardItemManager {
    private UnityAdsRewardItem _currentItem;
    private UnityAdsRewardItem _defaultItem;
    private Map _rewardItems;

    public UnityAdsRewardItemManager(JSONArray jSONArray, String str) {
        this._rewardItems = null;
        this._currentItem = null;
        this._defaultItem = null;
        this._rewardItems = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                UnityAdsRewardItem unityAdsRewardItem = new UnityAdsRewardItem(jSONArray.getJSONObject(i));
                if (unityAdsRewardItem.hasValidData()) {
                    if (unityAdsRewardItem.getKey().equals(str)) {
                        this._currentItem = unityAdsRewardItem;
                        this._defaultItem = unityAdsRewardItem;
                    }
                    this._rewardItems.put(unityAdsRewardItem.getKey(), unityAdsRewardItem);
                }
            } catch (JSONException e) {
                UnityAdsDeviceLog.error("Failed to parse reward item");
            }
        }
    }

    public UnityAdsRewardItem getItem(String str) {
        if (this._rewardItems.containsKey(str)) {
            return (UnityAdsRewardItem) this._rewardItems.get(str);
        }
        return null;
    }

    public UnityAdsRewardItem getCurrentItem() {
        return this._currentItem;
    }

    public UnityAdsRewardItem getDefaultItem() {
        return this._defaultItem;
    }

    public boolean setCurrentItem(String str) {
        if (!this._rewardItems.containsKey(str)) {
            return false;
        }
        this._currentItem = (UnityAdsRewardItem) this._rewardItems.get(str);
        return true;
    }

    public ArrayList allItems() {
        ArrayList arrayList = new ArrayList();
        for (UnityAdsRewardItem add : this._rewardItems.values()) {
            arrayList.add(add);
        }
        return arrayList;
    }

    public int itemCount() {
        return this._rewardItems.size();
    }
}
