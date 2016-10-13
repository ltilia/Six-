package com.amazon.device.ads;

import com.amazon.device.ads.JSONUtils.JSONUtilities;
import com.mopub.mobileads.VastIconXmlManager;
import org.json.JSONObject;

class ResizeProperties {
    private static final boolean ALLOW_OFFSCREEN_DEFAULT = true;
    private static final String CUSTOM_CLOSE_POSITION_DEFAULT = "top-right";
    public static final int DIMENSION_NOT_SET = -1;
    private boolean allowOffscreen;
    private String customClosePosition;
    private int height;
    private final JSONUtilities jsonUtils;
    private int offsetX;
    private int offsetY;
    private int width;

    public ResizeProperties() {
        this(new JSONUtilities());
    }

    ResizeProperties(JSONUtilities jsonUtils) {
        this.width = DIMENSION_NOT_SET;
        this.height = DIMENSION_NOT_SET;
        this.offsetX = DIMENSION_NOT_SET;
        this.offsetY = DIMENSION_NOT_SET;
        this.customClosePosition = CUSTOM_CLOSE_POSITION_DEFAULT;
        this.allowOffscreen = ALLOW_OFFSCREEN_DEFAULT;
        this.jsonUtils = jsonUtils;
    }

    public void reset() {
        this.width = DIMENSION_NOT_SET;
        this.height = DIMENSION_NOT_SET;
        this.offsetX = DIMENSION_NOT_SET;
        this.offsetY = DIMENSION_NOT_SET;
        this.customClosePosition = CUSTOM_CLOSE_POSITION_DEFAULT;
        this.allowOffscreen = ALLOW_OFFSCREEN_DEFAULT;
    }

    public boolean areResizePropertiesSet() {
        return (this.width == DIMENSION_NOT_SET || this.height == DIMENSION_NOT_SET || this.offsetX == DIMENSION_NOT_SET || this.offsetY == DIMENSION_NOT_SET) ? false : ALLOW_OFFSCREEN_DEFAULT;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public int getOffsetX() {
        return this.offsetX;
    }

    public int getOffsetY() {
        return this.offsetY;
    }

    public String getCustomClosePosition() {
        return this.customClosePosition;
    }

    public boolean getAllowOffscreen() {
        return this.allowOffscreen;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        setDimensionIfSet(json, VastIconXmlManager.WIDTH, this.width);
        setDimensionIfSet(json, VastIconXmlManager.HEIGHT, this.height);
        setDimensionIfSet(json, "offsetX", this.offsetX);
        setDimensionIfSet(json, "offsetY", this.offsetY);
        this.jsonUtils.put(json, "customClosePosition", this.customClosePosition);
        this.jsonUtils.put(json, "allowOffscreen", this.allowOffscreen);
        return json;
    }

    private void setDimensionIfSet(JSONObject json, String parameter, int value) {
        if (value != DIMENSION_NOT_SET) {
            this.jsonUtils.put(json, parameter, value);
        }
    }

    public boolean fromJSONObject(JSONObject json) {
        this.width = this.jsonUtils.getIntegerFromJSON(json, VastIconXmlManager.WIDTH, this.width);
        this.height = this.jsonUtils.getIntegerFromJSON(json, VastIconXmlManager.HEIGHT, this.height);
        this.offsetX = this.jsonUtils.getIntegerFromJSON(json, "offsetX", this.offsetX);
        this.offsetY = this.jsonUtils.getIntegerFromJSON(json, "offsetY", this.offsetY);
        this.customClosePosition = this.jsonUtils.getStringFromJSON(json, "customClosePosition", this.customClosePosition);
        this.allowOffscreen = this.jsonUtils.getBooleanFromJSON(json, "allowOffscreen", this.allowOffscreen);
        if (areResizePropertiesSet()) {
            return ALLOW_OFFSCREEN_DEFAULT;
        }
        reset();
        return false;
    }
}
