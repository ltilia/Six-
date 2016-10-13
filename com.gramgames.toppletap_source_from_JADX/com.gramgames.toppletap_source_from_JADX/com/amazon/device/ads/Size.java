package com.amazon.device.ads;

import com.mopub.mobileads.VastIconXmlManager;
import org.json.JSONObject;

class Size {
    private int height;
    private int width;

    public Size(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Size(String screenSize) {
        int width = 0;
        int height = 0;
        if (screenSize != null) {
            String[] dimensions = screenSize.split("x");
            if (dimensions != null && dimensions.length == 2) {
                width = Math.max(parseInt(dimensions[0], 0), 0);
                height = Math.max(parseInt(dimensions[1], 0), 0);
            }
        }
        this.width = width;
        this.height = height;
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

    public String toString() {
        return this.width + "x" + this.height;
    }

    private static int parseInt(String str, int defaultValue) {
        try {
            defaultValue = Integer.parseInt(str);
        } catch (NumberFormatException e) {
        }
        return defaultValue;
    }

    public JSONObject toJSONObject() {
        JSONObject json = new JSONObject();
        JSONUtils.put(json, VastIconXmlManager.WIDTH, this.width);
        JSONUtils.put(json, VastIconXmlManager.HEIGHT, this.height);
        return json;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Size)) {
            return false;
        }
        Size other = (Size) obj;
        if (this.width == other.width && this.height == other.height) {
            return true;
        }
        return false;
    }
}
