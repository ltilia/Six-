package com.adjust.sdk;

import com.unity3d.ads.android.properties.UnityAdsConstants;
import gs.gram.mopub.BuildConfig;
import java.util.LinkedHashMap;
import java.util.Map;

public class AdjustEvent {
    private static ILogger logger;
    Map<String, String> callbackParameters;
    String currency;
    String eventToken;
    Map<String, String> partnerParameters;
    Double revenue;

    static {
        logger = AdjustFactory.getLogger();
    }

    public AdjustEvent(String eventToken) {
        if (checkEventToken(eventToken, logger)) {
            this.eventToken = eventToken;
        }
    }

    public void setRevenue(double revenue, String currency) {
        if (checkRevenue(Double.valueOf(revenue), currency)) {
            this.revenue = Double.valueOf(revenue);
            this.currency = currency;
        }
    }

    public void addCallbackParameter(String key, String value) {
        if (isValidParameter(key, UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, "Callback") && isValidParameter(value, "value", "Callback")) {
            if (this.callbackParameters == null) {
                this.callbackParameters = new LinkedHashMap();
            }
            if (((String) this.callbackParameters.put(key, value)) != null) {
                logger.warn("key %s was overwritten", key);
            }
        }
    }

    public void addPartnerParameter(String key, String value) {
        if (isValidParameter(key, UnityAdsConstants.UNITY_ADS_REWARD_ITEMKEY_KEY, "Partner") && isValidParameter(value, "value", "Partner")) {
            if (this.partnerParameters == null) {
                this.partnerParameters = new LinkedHashMap();
            }
            if (((String) this.partnerParameters.put(key, value)) != null) {
                logger.warn("key %s was overwritten", key);
            }
        }
    }

    public boolean isValid() {
        return this.eventToken != null;
    }

    private static boolean checkEventToken(String eventToken, ILogger logger) {
        if (eventToken == null) {
            logger.error("Missing Event Token", new Object[0]);
            return false;
        } else if (eventToken.length() == 6) {
            return true;
        } else {
            logger.error("Malformed Event Token '%s'", eventToken);
            return false;
        }
    }

    private boolean checkRevenue(Double revenue, String currency) {
        if (revenue != null) {
            if (revenue.doubleValue() < 0.0d) {
                logger.error("Invalid amount %.5f", revenue);
                return false;
            } else if (currency == null) {
                logger.error("Currency must be set with revenue", new Object[0]);
                return false;
            } else if (currency.equals(BuildConfig.FLAVOR)) {
                logger.error("Currency is empty", new Object[0]);
                return false;
            }
        } else if (currency != null) {
            logger.error("Revenue must be set with currency", new Object[0]);
            return false;
        }
        return true;
    }

    private boolean isValidParameter(String attribute, String attributeType, String parameterName) {
        if (attribute == null) {
            logger.error("%s parameter %s is missing", parameterName, attributeType);
            return false;
        } else if (!attribute.equals(BuildConfig.FLAVOR)) {
            return true;
        } else {
            logger.error("%s parameter %s is empty", parameterName, attributeType);
            return false;
        }
    }
}
