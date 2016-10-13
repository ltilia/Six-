package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import android.util.Base64;
import com.applovin.sdk.AppLovinEventParameters;
import com.applovin.sdk.AppLovinEventTypes;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.e;
import com.chartboost.sdk.Libraries.e.a;
import com.chartboost.sdk.Libraries.g;
import com.chartboost.sdk.Libraries.h;
import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.impl.ay;
import com.chartboost.sdk.impl.ay.c;
import com.chartboost.sdk.impl.az;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.games.Games;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.util.EnumMap;
import java.util.Locale;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class CBAnalytics {

    static class 1 implements c {
        final /* synthetic */ String a;
        final /* synthetic */ CBIAPType b;

        1(String str, CBIAPType cBIAPType) {
            this.a = str;
            this.b = cBIAPType;
        }

        public void a(a aVar, ay ayVar) {
        }

        public void a(a aVar, ay ayVar, CBError cBError) {
            try {
                if (this.a.equals(AppLovinEventTypes.USER_COMPLETED_IN_APP_PURCHASE) && aVar.c() && aVar.f(Games.EXTRA_STATUS) == 400 && this.b == CBIAPType.GOOGLE_PLAY) {
                    CBLogging.a("CBPostInstallTracker", this.a + " 400 response from server!!");
                    az a = az.a(com.chartboost.sdk.c.y());
                    h h = a.h();
                    ConcurrentHashMap g = a.g();
                    if (h != null && g != null) {
                        h.e((File) g.get(ayVar));
                        g.remove(ayVar);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public enum CBIAPPurchaseInfo {
        PRODUCT_ID,
        PRODUCT_TITLE,
        PRODUCT_DESCRIPTION,
        PRODUCT_PRICE,
        PRODUCT_CURRENCY_CODE,
        GOOGLE_PURCHASE_DATA,
        GOOGLE_PURCHASE_SIGNATURE,
        AMAZON_PURCHASE_TOKEN,
        AMAZON_USER_ID
    }

    public enum CBIAPType {
        GOOGLE_PLAY,
        AMAZON
    }

    public enum CBLevelType {
        HIGHEST_LEVEL_REACHED(1),
        CURRENT_AREA(2),
        CHARACTER_LEVEL(3),
        OTHER_SEQUENTIAL(4),
        OTHER_NONSEQUENTIAL(5);
        
        private int a;

        private CBLevelType(int value) {
            this.a = value;
        }

        public int getLevelType() {
            return this.a;
        }
    }

    private CBAnalytics() {
    }

    public synchronized void trackInAppPurchaseEvent(EnumMap<CBIAPPurchaseInfo, String> map, CBIAPType iapType) {
        if (!(map == null || iapType == null)) {
            if (!(TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_ID)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE)) || TextUtils.isEmpty((CharSequence) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE)))) {
                a((String) map.get(CBIAPPurchaseInfo.PRODUCT_ID), (String) map.get(CBIAPPurchaseInfo.PRODUCT_TITLE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_DESCRIPTION), (String) map.get(CBIAPPurchaseInfo.PRODUCT_PRICE), (String) map.get(CBIAPPurchaseInfo.PRODUCT_CURRENCY_CODE), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_DATA), (String) map.get(CBIAPPurchaseInfo.GOOGLE_PURCHASE_SIGNATURE), (String) map.get(CBIAPPurchaseInfo.AMAZON_USER_ID), (String) map.get(CBIAPPurchaseInfo.AMAZON_PURCHASE_TOKEN), iapType);
            }
        }
        CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
    }

    private static synchronized void a(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            if (com.chartboost.sdk.c.y() == null) {
                CBLogging.b("CBPostInstallTracker", "You need call Chartboost.init() before calling any public API's");
            } else if (!com.chartboost.sdk.c.q()) {
                CBLogging.b("CBPostInstallTracker", "You need call Chartboost.OnStart() before tracking in-app purchases");
            } else if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str5)) {
                CBLogging.b("CBPostInstallTracker", "Null object is passed. Please pass a valid value object");
            } else {
                try {
                    Matcher matcher = Pattern.compile("(\\d+\\.\\d+)|(\\d+)").matcher(str4);
                    matcher.find();
                    Object group = matcher.group();
                    if (TextUtils.isEmpty(group)) {
                        CBLogging.b("CBPostInstallTracker", "Invalid price object");
                    } else {
                        float parseFloat = Float.parseFloat(group);
                        a aVar = null;
                        if (cBIAPType == CBIAPType.GOOGLE_PLAY) {
                            if (TextUtils.isEmpty(str6) || TextUtils.isEmpty(str7)) {
                                CBLogging.b("CBPostInstallTracker", "Null object is passed for for purchase data or purchase signature");
                            } else {
                                aVar = e.a(e.a("purchaseData", str6), e.a("purchaseSignature", str7), e.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, Integer.valueOf(CBIAPType.GOOGLE_PLAY.ordinal())));
                            }
                        } else if (cBIAPType == CBIAPType.AMAZON) {
                            if (TextUtils.isEmpty(str8) || TextUtils.isEmpty(str9)) {
                                CBLogging.b("CBPostInstallTracker", "Null object is passed for for amazon user id or amazon purchase token");
                            } else {
                                aVar = e.a(e.a("userID", str8), e.a("purchaseToken", str9), e.a(UnityAdsConstants.UNITY_ADS_ANALYTICS_QUERYPARAM_EVENTTYPE_KEY, Integer.valueOf(CBIAPType.AMAZON.ordinal())));
                            }
                        }
                        if (aVar == null) {
                            CBLogging.b("CBPostInstallTracker", "Error while parsing the receipt to a JSON Object, ");
                        } else {
                            String encodeToString = Base64.encodeToString(aVar.toString().getBytes(), 2);
                            a(e.a(e.a("localized-title", str2), e.a("localized-description", str3), e.a("price", Float.valueOf(parseFloat)), e.a(AppLovinEventParameters.REVENUE_CURRENCY, str5), e.a("productID", str), e.a("receipt", encodeToString)), AppLovinEventTypes.USER_COMPLETED_IN_APP_PURCHASE, cBIAPType);
                        }
                    }
                } catch (IllegalStateException e) {
                    CBLogging.b("CBPostInstallTracker", "Invalid price object");
                }
            }
        }
    }

    public static synchronized void trackInAppGooglePlayPurchaseEvent(String title, String description, String price, String currency, String productID, String purchaseData, String purchaseSignature) {
        synchronized (CBAnalytics.class) {
            a(productID, title, description, price, currency, purchaseData, purchaseSignature, null, null, CBIAPType.GOOGLE_PLAY);
        }
    }

    public static synchronized void trackInAppAmazonStorePurchaseEvent(String title, String description, String price, String currency, String productID, String userID, String purchaseToken) {
        synchronized (CBAnalytics.class) {
            a(productID, title, description, price, currency, null, null, userID, purchaseToken, CBIAPType.AMAZON);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, String description) {
        synchronized (CBAnalytics.class) {
            trackLevelInfo(eventLabel, type, mainLevel, 0, description);
        }
    }

    public static synchronized void trackLevelInfo(String eventLabel, CBLevelType type, int mainLevel, int subLevel, String description) {
        synchronized (CBAnalytics.class) {
            if (TextUtils.isEmpty(eventLabel)) {
                CBLogging.b("CBPostInstallTracker", "Invalid value: event label cannot be empty or null");
            } else {
                if (type != null) {
                    if (type instanceof CBLevelType) {
                        if (mainLevel < 0 || subLevel < 0) {
                            CBLogging.b("CBPostInstallTracker", "Invalid value: Level number should be > 0");
                        } else if (description.isEmpty()) {
                            CBLogging.b("CBPostInstallTracker", "Invalid value: description cannot be empty or null");
                        } else {
                            a a = e.a(e.a("event_label", eventLabel), e.a("event_field", Integer.valueOf(type.getLevelType())), e.a("main_level", Integer.valueOf(mainLevel)), e.a("sub_level", Integer.valueOf(subLevel)), e.a(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION, description), e.a("timestamp", Long.valueOf(System.currentTimeMillis())), e.a("data_type", "level_info"));
                            JSONArray jSONArray = new JSONArray();
                            jSONArray.put(a.e());
                            a(jSONArray, "tracking");
                        }
                    }
                }
                CBLogging.b("CBPostInstallTracker", "Invalid value: level type cannot be empty or null, please choose from one of the CBLevelType enum values");
            }
        }
    }

    private static synchronized void a(a aVar, String str, CBIAPType cBIAPType) {
        synchronized (CBAnalytics.class) {
            ay ayVar = new ay(String.format(Locale.US, "%s%s", new Object[]{"/post-install-event/", str}));
            ayVar.a(str, (Object) aVar);
            ayVar.a(g.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a)));
            ayVar.b(str);
            ayVar.a(true);
            ayVar.a(new 1(str, cBIAPType));
        }
    }

    private static synchronized void a(JSONArray jSONArray, String str) {
        synchronized (CBAnalytics.class) {
            ay ayVar = new ay("/post-install-event/".concat("tracking"));
            ayVar.a("track_info", (Object) jSONArray);
            ayVar.a(g.a(g.a(Games.EXTRA_STATUS, com.chartboost.sdk.Libraries.a.a)));
            ayVar.b(str);
            ayVar.a(true);
            ayVar.t();
        }
    }
}
