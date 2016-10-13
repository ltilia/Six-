package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils.JSONUtilities;
import java.util.Locale;
import org.json.JSONObject;

class OrientationProperties {
    private Boolean allowOrientationChange;
    private ForceOrientation forceOrientation;
    private final JSONUtilities jsonUtils;

    public OrientationProperties() {
        this(new JSONUtilities());
    }

    OrientationProperties(JSONUtilities jsonUtils) {
        this.allowOrientationChange = Boolean.valueOf(true);
        this.forceOrientation = ForceOrientation.NONE;
        this.jsonUtils = jsonUtils;
    }

    public Boolean isAllowOrientationChange() {
        return this.allowOrientationChange;
    }

    public void setAllowOrientationChange(Boolean allowOrientationChange) {
        this.allowOrientationChange = allowOrientationChange;
    }

    public ForceOrientation getForceOrientation() {
        return this.forceOrientation;
    }

    public void setForceOrientation(ForceOrientation forceOrientation) {
        this.forceOrientation = forceOrientation;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        this.jsonUtils.put(json, "forceOrientation", this.forceOrientation.toString());
        this.jsonUtils.put(json, "allowOrientationChange", this.allowOrientationChange.booleanValue());
        return json;
    }

    public void fromJSONObject(JSONObject json) {
        this.allowOrientationChange = Boolean.valueOf(this.jsonUtils.getBooleanFromJSON(json, "allowOrientationChange", this.allowOrientationChange.booleanValue()));
        this.forceOrientation = ForceOrientation.valueOf(this.jsonUtils.getStringFromJSON(json, "forceOrientation", this.forceOrientation.toString()).toUpperCase(Locale.US));
    }
}
