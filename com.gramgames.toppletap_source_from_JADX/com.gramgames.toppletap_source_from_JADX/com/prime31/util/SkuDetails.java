package com.prime31.util;

import com.facebook.share.internal.ShareConstants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import org.json.JSONException;
import org.json.JSONObject;

public class SkuDetails {
    String _description;
    String _itemType;
    String _json;
    String _price;
    String _price_amount_micros;
    String _price_currency_code;
    String _sku;
    String _title;
    String _type;

    public SkuDetails(String jsonSkuDetails) throws JSONException {
        this(IabHelper.ITEM_TYPE_INAPP, jsonSkuDetails);
    }

    public SkuDetails(String itemType, String jsonSkuDetails) throws JSONException {
        this._itemType = itemType;
        JSONObject o = new JSONObject(jsonSkuDetails);
        this._sku = o.optString("productId");
        this._type = o.optString(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY);
        this._price = o.optString("price");
        this._title = o.optString(ShareConstants.WEB_DIALOG_PARAM_TITLE);
        this._description = o.optString(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION);
        this._price_currency_code = o.optString("price_currency_code");
        this._price_amount_micros = o.optString("price_amount_micros");
        o.put("itemType", itemType);
        this._json = o.toString();
    }

    public String getSku() {
        return this._sku;
    }

    public String getType() {
        return this._type;
    }

    public String getItemType() {
        return this._itemType;
    }

    public String getPrice() {
        return this._price;
    }

    public String getPriceMicros() {
        return this._price_amount_micros;
    }

    public String getCurrencyCode() {
        return this._price_currency_code;
    }

    public String getTitle() {
        return this._title;
    }

    public String getDescription() {
        return this._description;
    }

    public String toString() {
        return "SkuDetails:" + this._json;
    }

    public String toJson() {
        return this._json;
    }
}
