package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils.JSONUtilities;
import com.mopub.mobileads.VastIconXmlManager;
import org.json.JSONObject;

class ExpandProperties {
    private int height;
    private final boolean isModal;
    private final JSONUtilities jsonUtils;
    private boolean useCustomClose;
    private int width;

    public ExpandProperties() {
        this(new JSONUtilities());
    }

    ExpandProperties(JSONUtilities jsonUtils) {
        this.width = -1;
        this.height = -1;
        this.useCustomClose = false;
        this.isModal = true;
        this.jsonUtils = jsonUtils;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Boolean getUseCustomClose() {
        return Boolean.valueOf(this.useCustomClose);
    }

    public void setUseCustomClose(Boolean useCustomClose) {
        this.useCustomClose = useCustomClose.booleanValue();
    }

    public Boolean getIsModal() {
        return Boolean.valueOf(true);
    }

    public ExpandProperties toClone() {
        ExpandProperties clone = new ExpandProperties();
        clone.width = this.width;
        clone.height = this.height;
        clone.useCustomClose = this.useCustomClose;
        return clone;
    }

    public String toString() {
        return toJSONObject().toString();
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        this.jsonUtils.put(json, VastIconXmlManager.WIDTH, this.width);
        this.jsonUtils.put(json, VastIconXmlManager.HEIGHT, this.height);
        this.jsonUtils.put(json, "useCustomClose", this.useCustomClose);
        getClass();
        this.jsonUtils.put(json, "isModal", true);
        return json;
    }

    public void fromJSONObject(JSONObject json) {
        this.width = this.jsonUtils.getIntegerFromJSON(json, VastIconXmlManager.WIDTH, this.width);
        this.height = this.jsonUtils.getIntegerFromJSON(json, VastIconXmlManager.HEIGHT, this.height);
        this.useCustomClose = this.jsonUtils.getBooleanFromJSON(json, "useCustomClose", this.useCustomClose);
    }
}
