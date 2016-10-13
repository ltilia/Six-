package com.amazon.device.ads;

import org.json.JSONObject;

class ViewabilityInfo {
    private boolean isAdOnScreen;
    private JSONObject jsonObject;

    public ViewabilityInfo(boolean isAdOnScreen, JSONObject jsonObject) {
        this.jsonObject = jsonObject;
        this.isAdOnScreen = isAdOnScreen;
    }

    public boolean isAdOnScreen() {
        return this.isAdOnScreen;
    }

    public JSONObject getJsonObject() {
        return this.jsonObject;
    }
}
