package com.facebook.internal;

import com.facebook.FacebookException;
import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.JSONArray;
import org.json.JSONObject;

public class GraphUtil {
    private static final String[] dateFormats;

    static {
        dateFormats = new String[]{"yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd"};
    }

    public static JSONObject createOpenGraphActionForPost(String type) {
        JSONObject action = new JSONObject();
        if (type != null) {
            try {
                action.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, type);
            } catch (Throwable e) {
                throw new FacebookException("An error occurred while setting up the open graph action", e);
            }
        }
        return action;
    }

    public static JSONObject createOpenGraphObjectForPost(String type) {
        return createOpenGraphObjectForPost(type, null, null, null, null, null, null);
    }

    public static JSONObject createOpenGraphObjectForPost(String type, String title, String imageUrl, String url, String description, JSONObject objectProperties, String id) {
        JSONObject openGraphObject = new JSONObject();
        if (type != null) {
            try {
                openGraphObject.put(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, type);
            } catch (Throwable e) {
                throw new FacebookException("An error occurred while setting up the graph object", e);
            }
        }
        openGraphObject.put(ShareConstants.WEB_DIALOG_PARAM_TITLE, title);
        if (imageUrl != null) {
            JSONObject imageUrlObject = new JSONObject();
            imageUrlObject.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, imageUrl);
            JSONArray imageUrls = new JSONArray();
            imageUrls.put(imageUrlObject);
            openGraphObject.put("image", imageUrls);
        }
        openGraphObject.put(UnityAdsConstants.UNITY_ADS_FAILED_URL_URL_KEY, url);
        openGraphObject.put(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, description);
        openGraphObject.put(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY, true);
        if (objectProperties != null) {
            openGraphObject.put(UnityAdsConstants.UNITY_ADS_JSON_DATA_ROOTKEY, objectProperties);
        }
        if (id != null) {
            openGraphObject.put(UnityAdsConstants.UNITY_ADS_ZONE_ID_KEY, id);
        }
        return openGraphObject;
    }

    public static boolean isOpenGraphObjectForPost(JSONObject object) {
        return object != null ? object.optBoolean(NativeProtocol.OPEN_GRAPH_CREATE_OBJECT_KEY) : false;
    }
}
