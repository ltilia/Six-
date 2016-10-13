package com.applovin.impl.sdk;

import com.applovin.nativeAds.AppLovinNativeAdLoadListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.share.internal.ShareConstants;
import com.mopub.mobileads.BaseVideoPlayerActivity;
import gs.gram.mopub.BuildConfig;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

class db extends ca {
    private final AppLovinNativeAdLoadListener a;
    private final JSONObject b;

    db(JSONObject jSONObject, AppLovinSdkImpl appLovinSdkImpl, AppLovinNativeAdLoadListener appLovinNativeAdLoadListener) {
        super("TaskRenderNativeAd", appLovinSdkImpl);
        this.a = appLovinNativeAdLoadListener;
        this.b = jSONObject;
    }

    private String a(Map map, String str) {
        String str2 = (String) map.get("simp_url");
        if (AppLovinSdkUtils.isValidString(str2)) {
            return str2.replace("{CLCODE}", str);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    private String a(Map map, String str, String str2) {
        String str3 = (String) map.get("click_url");
        if (AppLovinSdkUtils.isValidString(str3)) {
            CharSequence charSequence;
            if (str2 == null) {
                charSequence = BuildConfig.FLAVOR;
            }
            return str3.replace("{CLCODE}", str).replace("{EVENT_ID}", charSequence);
        }
        throw new IllegalArgumentException("No impression URL available");
    }

    private void a(JSONObject jSONObject) {
        List<Map> a = bc.a(jSONObject.getJSONArray("native_ads"));
        Map a2 = bc.a(jSONObject.getJSONObject("native_settings"));
        List arrayList = new ArrayList(a.size());
        for (Map map : a) {
            String str = (String) map.get("clcode");
            NativeAdImpl a3 = new bf().e((String) map.get(ShareConstants.WEB_DIALOG_PARAM_TITLE)).f((String) map.get(ShareConstants.WEB_DIALOG_PARAM_DESCRIPTION)).g((String) map.get(ShareConstants.FEED_CAPTION_PARAM)).p((String) map.get("cta")).a((String) map.get("icon_url")).b((String) map.get("image_url")).d((String) map.get(BaseVideoPlayerActivity.VIDEO_URL)).c((String) map.get("star_rating_url")).h((String) map.get("icon_url")).i((String) map.get("image_url")).j((String) map.get(BaseVideoPlayerActivity.VIDEO_URL)).a(Float.parseFloat((String) map.get("star_rating"))).o(str).k(a(a2, str)).l(a(a2, str, (String) map.get("event_id"))).m(b(a2, str)).n(c(a2, str)).a(Long.parseLong((String) map.get("ad_id"))).a(this.f).a();
            arrayList.add(a3);
            this.f.getLogger().d("TaskRenderNativeAd", "Prepared slot: " + a3.getAdId());
        }
        if (this.a != null) {
            this.a.onNativeAdsLoaded(arrayList);
        }
    }

    private String b(Map map, String str) {
        String str2 = (String) map.get("video_start_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    private String c(Map map, String str) {
        String str2 = (String) map.get("video_end_url");
        return str2 != null ? str2.replace("{CLCODE}", str) : null;
    }

    void a(int i) {
        try {
            if (this.a != null) {
                this.a.onNativeAdsFailedToLoad(i);
            }
        } catch (Throwable e) {
            this.f.getLogger().e("TaskRenderNativeAd", "Unable to notify listener about failure.", e);
        }
    }

    public void run() {
        try {
            if (this.b == null || this.b.length() == 0) {
                a((int) AppLovinErrorCodes.UNABLE_TO_PREPARE_NATIVE_AD);
            } else {
                a(this.b);
            }
        } catch (Throwable e) {
            this.f.getLogger().e("TaskRenderNativeAd", "Unable to render widget.", e);
            a((int) AppLovinErrorCodes.UNABLE_TO_PRECACHE_RESOURCES);
        }
    }
}
