package com.applovin.impl.sdk;

import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventService;
import com.applovin.sdk.AppLovinEventTypes;
import com.applovin.sdk.AppLovinSdk;
import com.prime31.util.IabHelper;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.UUID;

public class EventServiceImpl implements AppLovinEventService {
    private final AppLovinSdkImpl a;
    private final List b;

    public EventServiceImpl(AppLovinSdk appLovinSdk) {
        this.a = (AppLovinSdkImpl) appLovinSdk;
        this.b = Arrays.asList(((String) ((AppLovinSdkImpl) appLovinSdk).a(cb.bc)).split(","));
    }

    private Uri a(bx bxVar, s sVar) {
        r dataCollector = this.a.getDataCollector();
        u a = dataCollector.a();
        t b = dataCollector.b();
        boolean contains = this.b.contains(bxVar.a());
        Builder appendQueryParameter = Uri.parse((String) this.a.a(cb.bb)).buildUpon().appendQueryParameter(NotificationCompatApi21.CATEGORY_EVENT, contains ? bxVar.a() : "postinstall").appendQueryParameter("ts", Long.toString(bxVar.c())).appendQueryParameter(UnityAdsConstants.UNITY_ADS_WEBVIEW_DATAPARAM_PLATFORM_KEY, "Android").appendQueryParameter("model", a.a).appendQueryParameter("package_name", b.c).appendQueryParameter("sdk_key", this.a.getSdkKey()).appendQueryParameter("idfa", sVar.b).appendQueryParameter("dnt", Boolean.toString(sVar.a)).appendQueryParameter("ia", Long.toString(b.d)).appendQueryParameter("api_did", (String) this.a.a(cb.c)).appendQueryParameter("brand", a.c).appendQueryParameter("model", a.a).appendQueryParameter("revision", a.d).appendQueryParameter("sdk_version", AppLovinSdk.VERSION).appendQueryParameter("os", a.b).appendQueryParameter("orientation_lock", a.i).appendQueryParameter("app_version", this.a.getDataCollector().b().b).appendQueryParameter("country_code", a.f).appendQueryParameter("carrier", a.g).appendQueryParameter("tz_offset", String.valueOf(a.j));
        if (!contains) {
            appendQueryParameter = appendQueryParameter.appendQueryParameter("sub_event", bxVar.a());
        }
        return appendQueryParameter.build();
    }

    private Map a(Map map) {
        Map hashMap = new HashMap();
        if (map != null) {
            for (Entry entry : map.entrySet()) {
                Object key = entry.getKey();
                Object value = entry.getValue();
                if ((key instanceof String) && (value instanceof String)) {
                    hashMap.put((String) key, (String) value);
                } else {
                    this.a.getLogger().w("EventServiceImpl", "Unexpected class type in trackEvent(); all keys and values passed as parameters must be String. Encountered " + key.getClass().getCanonicalName() + "/" + value.getClass().getCanonicalName() + "; will use toString() value instead, which may be unexpected...");
                    hashMap.put(key.toString(), value.toString());
                }
            }
        }
        return hashMap;
    }

    private void a(bx bxVar) {
        if (((Boolean) this.a.a(cb.bd)).booleanValue()) {
            this.a.getLogger().d("EventServiceImpl", "Tracking event: " + bxVar);
            a(new v(this, bxVar));
        }
    }

    private void a(co coVar) {
        this.a.a().a(new cn(this.a, coVar), cw.BACKGROUND);
    }

    public void trackCheckout(String str, Map map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap(1);
        hashMap.put(AppLovinEventParameters.CHECKOUT_TRANSACTION_IDENTIFIER, str);
        trackEvent(AppLovinEventTypes.USER_COMPLETED_CHECKOUT, hashMap);
    }

    public void trackEvent(String str) {
        trackEvent(str, new HashMap());
    }

    public void trackEvent(String str, Map map) {
        a(new bx(str, a(map), System.currentTimeMillis(), dm.b(UUID.randomUUID().toString())));
    }

    public void trackInAppPurchase(Intent intent, Map map) {
        Map hashMap = map != null ? new HashMap(map) : new HashMap();
        try {
            hashMap.put(AppLovinEventParameters.IN_APP_PURCHASE_DATA, intent.getStringExtra(IabHelper.RESPONSE_INAPP_PURCHASE_DATA));
            hashMap.put(AppLovinEventParameters.IN_APP_DATA_SIGNATURE, intent.getStringExtra(IabHelper.RESPONSE_INAPP_SIGNATURE));
        } catch (Throwable e) {
            this.a.getLogger().userError("EventServiceImpl", "Unable to track in app purchase; invalid purchanse intent", e);
        }
        trackEvent(AppLovinEventTypes.USER_COMPLETED_IN_APP_PURCHASE, hashMap);
    }
}
