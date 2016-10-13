package com.amazon.device.ads;

import gs.gram.mopub.BuildConfig;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class JSONUtils {

    public static class JSONUtilities {
        public boolean getBooleanFromJSON(JSONObject json, String key, boolean defaultValue) {
            return JSONUtils.getBooleanFromJSON(json, key, defaultValue);
        }

        public String getStringFromJSON(JSONObject json, String key, String defaultValue) {
            return JSONUtils.getStringFromJSON(json, key, defaultValue);
        }

        public int getIntegerFromJSON(JSONObject json, String key, int defaultValue) {
            return JSONUtils.getIntegerFromJSON(json, key, defaultValue);
        }

        public long getLongFromJSON(JSONObject json, String key, long defaultValue) {
            return JSONUtils.getLongFromJSON(json, key, defaultValue);
        }

        public JSONArray getJSONArrayFromJSON(JSONObject json, String key) {
            return JSONUtils.getJSONArrayFromJSON(json, key);
        }

        public int getIntegerFromJSONArray(JSONArray jsonArray, int index, int defaultValue) {
            return JSONUtils.getIntegerFromJSONArray(jsonArray, index, defaultValue);
        }

        public JSONObject getJSONObjectFromJSONArray(JSONArray jsonArray, int index) {
            return JSONUtils.getJSONObjectFromJSONArray(jsonArray, index);
        }

        public Map<String, String> createMapFromJSON(JSONObject json) {
            return JSONUtils.createMapFromJSON(json);
        }

        public void put(JSONObject json, String key, String value) {
            JSONUtils.put(json, key, value);
        }

        public void put(JSONObject json, String key, int value) {
            JSONUtils.put(json, key, value);
        }

        public void put(JSONObject json, String key, long value) {
            JSONUtils.put(json, key, value);
        }

        public void put(JSONObject json, String key, boolean value) {
            JSONUtils.put(json, key, value);
        }

        public JSONObject getJSONObjectFromString(String str) {
            return JSONUtils.getJSONObjectFromString(str);
        }
    }

    JSONUtils() {
    }

    public static boolean getBooleanFromJSON(JSONObject json, String key, boolean defaultValue) {
        if (json.isNull(key)) {
            return defaultValue;
        }
        return json.optBoolean(key, defaultValue);
    }

    public static String getStringFromJSON(JSONObject json, String key, String defaultValue) {
        if (json.isNull(key)) {
            return defaultValue;
        }
        return json.optString(key, defaultValue);
    }

    public static int getIntegerFromJSON(JSONObject json, String key, int defaultValue) {
        if (json.isNull(key)) {
            return defaultValue;
        }
        return json.optInt(key, defaultValue);
    }

    public static long getLongFromJSON(JSONObject json, String key, long defaultValue) {
        if (json.isNull(key)) {
            return defaultValue;
        }
        return json.optLong(key, defaultValue);
    }

    public static JSONArray getJSONArrayFromJSON(JSONObject json, String key) {
        if (json.isNull(key)) {
            return null;
        }
        return json.optJSONArray(key);
    }

    public static int getIntegerFromJSONArray(JSONArray jsonArray, int index, int defaultValue) {
        if (jsonArray.isNull(index)) {
            return defaultValue;
        }
        return jsonArray.optInt(index, defaultValue);
    }

    public static JSONObject getJSONObjectFromJSONArray(JSONArray jsonArray, int index) {
        JSONObject jSONObject = null;
        if (!jsonArray.isNull(index)) {
            try {
                jSONObject = jsonArray.getJSONObject(index);
            } catch (JSONException e) {
            }
        }
        return jSONObject;
    }

    public static void put(JSONObject json, String key, String value) {
        if (value != null && !value.equals(BuildConfig.FLAVOR)) {
            try {
                json.put(key, value);
            } catch (JSONException e) {
            }
        }
    }

    public static void put(JSONObject json, String key, int value) {
        try {
            json.put(key, value);
        } catch (JSONException e) {
        }
    }

    public static void put(JSONObject json, String key, long value) {
        try {
            json.put(key, value);
        } catch (JSONException e) {
        }
    }

    public static void put(JSONObject json, String key, boolean value) {
        try {
            json.put(key, value);
        } catch (JSONException e) {
        }
    }

    public static JSONObject getJSONObjectFromString(String str) {
        try {
            return new JSONObject(str);
        } catch (JSONException e) {
            return null;
        }
    }

    public static Map<String, String> createMapFromJSON(JSONObject json) {
        Map<String, String> map = new HashMap();
        Iterator<String> keysItr = json.keys();
        while (keysItr.hasNext()) {
            String key = (String) keysItr.next();
            String value = getStringFromJSON(json, key, null);
            if (value != null) {
                map.put(key, value);
            }
        }
        return map;
    }
}
