package com.applovin.impl.sdk;

import com.applovin.impl.adview.v;
import com.applovin.impl.sdk.AppLovinAdImpl.AdTarget;
import com.applovin.impl.sdk.AppLovinAdImpl.Builder;
import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdLoadListener;
import com.applovin.sdk.AppLovinAdSize;
import com.applovin.sdk.AppLovinAdType;
import com.google.android.exoplayer.util.MimeTypes;
import com.google.android.gms.games.stats.PlayerStats;
import com.mopub.common.AdType;
import gs.gram.mopub.BuildConfig;
import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class da extends ca implements dl {
    private final Collection a;
    private final JSONObject b;
    private final AppLovinAdLoadListener c;
    private final y d;
    private boolean i;
    private c j;

    da(JSONObject jSONObject, AppLovinAdLoadListener appLovinAdLoadListener, AppLovinSdkImpl appLovinSdkImpl) {
        super("RenderAd", appLovinSdkImpl);
        this.j = new c(AppLovinAdSize.INTERSTITIAL, AppLovinAdType.REGULAR);
        this.b = jSONObject;
        this.c = appLovinAdLoadListener;
        this.a = d();
        this.d = appLovinSdkImpl.getFileManager();
    }

    private float a(String str, AppLovinAdType appLovinAdType, float f) {
        return appLovinAdType.equals(AppLovinAdType.INCENTIVIZED) ? 0.5f : (appLovinAdType.equals(AppLovinAdType.REGULAR) && str != null && f == PlayerStats.UNSET_VALUE) ? 0.5f : 0.0f;
    }

    private v a(int i) {
        return i == 1 ? v.WhiteXOnTransparentGrey : v.WhiteXOnOpaqueBlack;
    }

    private v a(String str) {
        return str != null ? v.WhiteXOnTransparentGrey : v.WhiteXOnOpaqueBlack;
    }

    private String a(String str, String str2) {
        File a = this.d.a(str2.replace("/", "_"), this.f.getApplicationContext(), true);
        if (a == null) {
            return null;
        }
        if (a.exists()) {
            this.g.d(this.e, "Loaded " + str2 + " from cache: file://" + a.getAbsolutePath());
            return "file://" + a.getAbsolutePath();
        }
        return this.d.a(a, new StringBuilder().append(str).append(str2).toString()) ? "file://" + a.getAbsolutePath() : null;
    }

    private void a(JSONObject jSONObject) {
        AppLovinAdSize fromString;
        String string = jSONObject.getString(AdType.HTML);
        if (jSONObject.has("size")) {
            fromString = AppLovinAdSize.fromString(jSONObject.getString("size"));
        } else {
            fromString = AppLovinAdSize.BANNER;
        }
        String str = null;
        if (string == null || string.length() <= 0) {
            this.g.e(this.e, "No HTML received for requested ad");
            c();
            return;
        }
        AdTarget valueOf;
        AppLovinAdType fromString2;
        String string2;
        int i;
        double d;
        float f;
        float f2;
        v a;
        String b = b(string);
        if (jSONObject.has("ad_target")) {
            valueOf = AdTarget.valueOf(jSONObject.getString("ad_target").toUpperCase(Locale.ENGLISH));
        } else {
            valueOf = AdTarget.DEFAULT;
        }
        if (jSONObject.has("ad_type")) {
            fromString2 = AppLovinAdType.fromString(jSONObject.getString("ad_type").toUpperCase(Locale.ENGLISH));
        } else {
            fromString2 = AppLovinAdType.REGULAR;
        }
        this.j = new c(fromString, fromString2);
        if (jSONObject.has(MimeTypes.BASE_TYPE_VIDEO)) {
            string2 = jSONObject.getString(MimeTypes.BASE_TYPE_VIDEO);
            if (string2 == null || string2.isEmpty()) {
                string2 = null;
            } else {
                try {
                    str = this.d.a(this.h, string2);
                    this.i = true;
                    string2 = str;
                } catch (Exception e) {
                    string2 = str;
                }
            }
            if (string2 == null) {
                c();
                return;
            }
            str = string2;
        }
        long j = -1;
        if (jSONObject.has("ad_id")) {
            j = jSONObject.getLong("ad_id");
        }
        if (jSONObject.has("countdown_length")) {
            try {
                i = jSONObject.getInt("countdown_length");
            } catch (JSONException e2) {
                i = 0;
            }
        } else {
            i = 0;
        }
        float f3 = 0.0f;
        if (jSONObject.has("close_delay")) {
            try {
                d = jSONObject.getDouble("close_delay");
                if (-3.4028234663852886E38d < d && d < 3.4028234663852886E38d) {
                    f3 = (float) d;
                }
                f = f3;
            } catch (JSONException e3) {
                f = 0.0f;
            }
        } else {
            f = 0.0f;
        }
        f3 = a(str, fromString2, f);
        if (jSONObject.has("close_delay_graphic")) {
            try {
                d = jSONObject.getDouble("close_delay_graphic");
                if (-1.401298464324817E-45d < d && d < 3.4028234663852886E38d) {
                    f3 = (float) d;
                }
                f2 = f3;
            } catch (JSONException e4) {
                f2 = f3;
            }
        } else {
            f2 = f3;
        }
        if (jSONObject.has("close_style")) {
            try {
                a = a(jSONObject.getInt("close_style"));
            } catch (JSONException e5) {
                a = a(str);
            }
        } else {
            a = a(str);
        }
        String str2 = BuildConfig.FLAVOR;
        if (jSONObject.has("clcodes")) {
            try {
                string2 = ((JSONArray) jSONObject.get("clcodes")).getString(0);
            } catch (JSONException e6) {
                string2 = str2;
            }
        } else {
            string2 = str2;
        }
        str2 = BuildConfig.FLAVOR;
        if (jSONObject.has("video_end_url")) {
            try {
                str2 = jSONObject.getString("video_end_url");
            } catch (Exception e7) {
            }
        }
        String str3 = BuildConfig.FLAVOR;
        if (jSONObject.has("mute_image")) {
            try {
                str3 = this.f.getFileManager().a(this.h, jSONObject.getString("mute_image"), false);
            } catch (Exception e8) {
            }
        }
        String str4 = BuildConfig.FLAVOR;
        if (jSONObject.has("unmute_image")) {
            try {
                str4 = this.f.getFileManager().a(this.h, jSONObject.getString("unmute_image"), false);
            } catch (Exception e9) {
            }
        }
        String str5 = BuildConfig.FLAVOR;
        if (jSONObject.has("click_tracking_url")) {
            try {
                str5 = jSONObject.getString("click_tracking_url");
            } catch (Exception e10) {
            }
        }
        boolean z = false;
        if (jSONObject.has("dismiss_on_skip")) {
            try {
                z = jSONObject.getBoolean("dismiss_on_skip");
            } catch (Exception e11) {
            }
        }
        boolean z2 = false;
        if (jSONObject.has("video_clickable")) {
            try {
                z2 = jSONObject.getBoolean("video_clickable");
            } catch (Exception e12) {
            }
        }
        String str6 = BuildConfig.FLAVOR;
        if (jSONObject.has("click_url")) {
            try {
                str6 = jSONObject.getString("click_url");
            } catch (Exception e13) {
            }
        }
        a(new Builder().setHtml(b).setSize(fromString).setType(fromString2).setVideoFilename(str).setTarget(valueOf).setCloseStyle(a).setVideoCloseDelay(f).setCloseDelay(f2).setCountdownLength(i).setCurrentAdIdNumber(j).setClCode(string2).setCompletionUrl(str2).setSupplementalClickTrackingUrl(str5).setMuteImageFilename(str3).setUnmuteImageFilename(str4).setDismissOnSkip(z).setVideoClickableDuringPlayback(z2).setClickDestinationUrl(str6).build());
    }

    private String b(String str) {
        return ((Boolean) this.f.a(cb.B)).booleanValue() ? c(str) : str;
    }

    private String c(String str) {
        StringBuilder stringBuilder = new StringBuilder(str);
        for (String str2 : ((String) this.f.a(cb.C)).split(",")) {
            int i = 0;
            int i2 = 0;
            while (i2 < stringBuilder.length()) {
                i2 = stringBuilder.indexOf(str2, i);
                if (i2 == -1) {
                    break;
                }
                int length = stringBuilder.length();
                i = i2;
                while (!this.a.contains(Character.valueOf(stringBuilder.charAt(i))) && i < length) {
                    i++;
                }
                if (i <= i2 || i == length) {
                    this.g.d(this.e, "Unable to cache resource; ad HTML is invalid.");
                } else {
                    String a = a(str2, stringBuilder.substring(str2.length() + i2, i));
                    if (a != null) {
                        stringBuilder.replace(i2, i, a);
                    }
                }
            }
        }
        return stringBuilder.toString();
    }

    private Collection d() {
        Collection hashSet = new HashSet();
        for (char valueOf : ((String) this.f.a(cb.ah)).toCharArray()) {
            hashSet.add(Character.valueOf(valueOf));
        }
        hashSet.add(Character.valueOf('\"'));
        return hashSet;
    }

    void a(AppLovinAd appLovinAd) {
        this.f.getLogger().d(a(), "Rendered new ad:" + appLovinAd);
        if (this.c != null) {
            this.c.adReceived(appLovinAd);
        }
    }

    void c() {
        try {
            if (this.c == null) {
                return;
            }
            if (this.c instanceof w) {
                ((w) this.c).a(this.j, -6);
            } else {
                this.c.failedToReceiveAd(-6);
            }
        } catch (Throwable th) {
            this.g.e(this.e, "Unable process a failure to receive an ad", th);
        }
    }

    public String e() {
        return "tRA";
    }

    public boolean f() {
        return this.i;
    }

    public void run() {
        this.g.d(this.e, "Rendering ad...");
        try {
            a(this.b);
        } catch (Throwable e) {
            this.g.e(this.e, "Unable to parse ad service response", e);
            c();
        } catch (Throwable e2) {
            this.g.e(this.e, "Ad response is not valid", e2);
            c();
        } catch (Throwable e22) {
            this.g.e(this.e, "Unable to render ad", e22);
            c();
        }
    }
}
