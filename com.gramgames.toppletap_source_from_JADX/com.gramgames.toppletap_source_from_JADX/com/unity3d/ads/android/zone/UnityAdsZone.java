package com.unity3d.ads.android.zone;

import com.unity3d.ads.android.UnityAdsDeviceLog;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.ArrayList;
import java.util.Map;
import java.util.Map.Entry;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class UnityAdsZone {
    private final ArrayList _allowClientOverrides;
    private boolean _default;
    private String _gamerSid;
    private JSONObject _initialOptions;
    private JSONObject _options;
    private String _zoneId;
    private String _zoneName;

    public UnityAdsZone(JSONObject jSONObject) {
        int i = 0;
        this._initialOptions = null;
        this._options = null;
        this._zoneId = null;
        this._zoneName = null;
        this._default = false;
        this._gamerSid = null;
        this._allowClientOverrides = new ArrayList();
        this._initialOptions = new JSONObject(jSONObject.toString());
        this._options = jSONObject;
        this._zoneId = jSONObject.getString(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY);
        this._zoneName = jSONObject.getString(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY);
        this._default = jSONObject.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_DEFAULT_KEY, true);
        JSONArray optJSONArray = jSONObject.optJSONArray(UnityAdsConstants.UNITY_ADS_ZONE_ALLOW_CLIENT_OVERRIDES_KEY);
        if (optJSONArray != null) {
            while (i < optJSONArray.length()) {
                this._allowClientOverrides.add(optJSONArray.getString(i));
                i++;
            }
        }
    }

    public String getZoneId() {
        return this._zoneId;
    }

    public String getZoneName() {
        return this._zoneName;
    }

    public JSONObject getZoneOptions() {
        return this._options;
    }

    public boolean isDefault() {
        return this._default;
    }

    public boolean isIncentivized() {
        return false;
    }

    public boolean muteVideoSounds() {
        return this._options.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_MUTE_VIDEO_SOUNDS_KEY, false);
    }

    public boolean noOfferScreen() {
        return this._options.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_NO_OFFER_SCREEN_KEY, true);
    }

    public boolean openAnimated() {
        return this._options.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_OPEN_ANIMATED_KEY, false);
    }

    public boolean useDeviceOrientationForVideo() {
        return this._options.optBoolean(UnityAdsConstants.UNITY_ADS_ZONE_USE_DEVICE_ORIENTATION_FOR_VIDEO_KEY, false);
    }

    public long allowVideoSkipInSeconds() {
        return this._options.optLong(UnityAdsConstants.UNITY_ADS_ZONE_ALLOW_VIDEO_SKIP_IN_SECONDS_KEY, 0);
    }

    public long disableBackButtonForSeconds() {
        return this._options.optLong(UnityAdsConstants.UNITY_ADS_ZONE_DISABLE_BACK_BUTTON_FOR_SECONDS, 0);
    }

    public String getGamerSid() {
        return this._gamerSid;
    }

    public void setGamerSid(String str) {
        this._gamerSid = str;
    }

    public void mergeOptions(Map map) {
        try {
            this._options = new JSONObject(this._initialOptions.toString());
            setGamerSid(null);
        } catch (JSONException e) {
            UnityAdsDeviceLog.debug("Could not set Gamer SID");
        }
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                if (allowsOverride((String) entry.getKey())) {
                    try {
                        this._options.put((String) entry.getKey(), entry.getValue());
                    } catch (JSONException e2) {
                        UnityAdsDeviceLog.error("Unable to set JSON value");
                    }
                }
            }
            if (map.containsKey(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_GAMERSID_KEY)) {
                setGamerSid((String) map.get(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_GAMERSID_KEY));
            }
        }
    }

    private boolean allowsOverride(String str) {
        return this._allowClientOverrides.contains(str);
    }
}
