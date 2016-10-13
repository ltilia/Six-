package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnityAdsZoneManager {
    private UnityAdsZone _currentZone;
    private Map _zones;

    public UnityAdsZoneManager(JSONArray jSONArray) {
        this._zones = null;
        this._currentZone = null;
        this._zones = new HashMap();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                UnityAdsZone unityAdsIncentivizedZone;
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                if (jSONObject.getBoolean(UnityAdsConstants.UNITY_ADS_ZONE_INCENTIVIZED_KEY)) {
                    unityAdsIncentivizedZone = new UnityAdsIncentivizedZone(jSONObject);
                } else {
                    unityAdsIncentivizedZone = new UnityAdsZone(jSONObject);
                }
                if (this._currentZone == null && unityAdsIncentivizedZone.isDefault()) {
                    this._currentZone = unityAdsIncentivizedZone;
                }
                this._zones.put(unityAdsIncentivizedZone.getZoneId(), unityAdsIncentivizedZone);
            } catch (JSONException e) {
                UnityAdsDeviceLog.error("Failed to parse zone");
            }
        }
    }

    public UnityAdsZone getZone(String str) {
        if (this._zones.containsKey(str)) {
            return (UnityAdsZone) this._zones.get(str);
        }
        return null;
    }

    public UnityAdsZone getCurrentZone() {
        return this._currentZone;
    }

    public Map getZonesMap() {
        return this._zones;
    }

    public boolean setCurrentZone(String str) {
        if (this._zones.containsKey(str)) {
            this._currentZone = (UnityAdsZone) this._zones.get(str);
            return true;
        }
        this._currentZone = null;
        return false;
    }

    public int zoneCount() {
        return this._zones != null ? this._zones.size() : 0;
    }

    public JSONArray getZonesJson() {
        JSONArray jSONArray = new JSONArray();
        for (UnityAdsZone zoneOptions : this._zones.values()) {
            jSONArray.put(zoneOptions.getZoneOptions());
        }
        return jSONArray;
    }

    public void clear() {
        this._currentZone = null;
        this._zones.clear();
        this._zones = null;
    }
}
