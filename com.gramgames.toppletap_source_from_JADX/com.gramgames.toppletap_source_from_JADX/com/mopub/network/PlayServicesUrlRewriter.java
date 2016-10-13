package com.mopub.network;

import android.content.Context;
import android.net.Uri;
import com.facebook.appevents.AppEventsConstants;
import com.mopub.common.GpsHelper;
import com.mopub.common.GpsHelper.AdvertisingInfo;
import com.mopub.volley.toolbox.HurlStack.UrlRewriter;
import gs.gram.mopub.BuildConfig;

public class PlayServicesUrlRewriter implements UrlRewriter {
    public static final String DO_NOT_TRACK_TEMPLATE = "mp_tmpl_do_not_track";
    private static final String IFA_PREFIX = "ifa:";
    public static final String UDID_TEMPLATE = "mp_tmpl_advertising_id";
    private final Context applicationContext;
    private final String deviceIdentifier;

    public PlayServicesUrlRewriter(String deviceId, Context context) {
        this.deviceIdentifier = deviceId;
        this.applicationContext = context.getApplicationContext();
    }

    public String rewriteUrl(String url) {
        if (!url.contains(UDID_TEMPLATE) && !url.contains(DO_NOT_TRACK_TEMPLATE)) {
            return url;
        }
        String prefix = BuildConfig.FLAVOR;
        AdvertisingInfo advertisingInfo = new AdvertisingInfo(this.deviceIdentifier, false);
        if (GpsHelper.isPlayServicesAvailable(this.applicationContext)) {
            AdvertisingInfo playServicesAdInfo = GpsHelper.fetchAdvertisingInfoSync(this.applicationContext);
            if (playServicesAdInfo != null) {
                prefix = IFA_PREFIX;
                advertisingInfo = playServicesAdInfo;
            }
        }
        return url.replace(UDID_TEMPLATE, Uri.encode(prefix + advertisingInfo.advertisingId)).replace(DO_NOT_TRACK_TEMPLATE, advertisingInfo.limitAdTracking ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
    }
}
