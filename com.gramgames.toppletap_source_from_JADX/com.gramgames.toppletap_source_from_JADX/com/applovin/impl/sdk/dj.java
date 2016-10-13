package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinAd;
import com.applovin.sdk.AppLovinAdRewardListener;
import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinSdkUtils;
import com.facebook.AccessToken;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

class dj extends by {
    private final AppLovinAdImpl a;
    private final AppLovinAdRewardListener b;
    private final Object c;
    private volatile boolean d;

    public dj(AppLovinSdkImpl appLovinSdkImpl, AppLovinAd appLovinAd, AppLovinAdRewardListener appLovinAdRewardListener) {
        super("TaskValidateReward", appLovinSdkImpl);
        this.c = new Object();
        this.d = false;
        this.a = (AppLovinAdImpl) appLovinAd;
        this.b = appLovinAdRewardListener;
    }

    private void a(int i) {
        if (!c()) {
            String str = "network_timeout";
            if (i < 400 || i > 500) {
                this.b.validationRequestFailed(this.a, i);
            } else {
                this.b.userRewardRejected(this.a, new HashMap(0));
                str = "rejected";
            }
            bq.a().a(this.a, str);
        }
    }

    private void a(String str, Map map) {
        if (!c()) {
            bq a = bq.a();
            a.a(this.a, str);
            a.a(this.a, map);
            if (str.equals("accepted")) {
                this.b.userRewardVerified(this.a, map);
            } else if (str.equals("quota_exceeded")) {
                this.b.userOverQuota(this.a, map);
            } else if (str.equals("rejected")) {
                this.b.userRewardRejected(this.a, map);
            } else {
                this.b.validationRequestFailed(this.a, AppLovinErrorCodes.INCENTIVIZED_UNKNOWN_SERVER_ERROR);
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(org.json.JSONObject r5) {
        /*
        r4 = this;
        r0 = r4.c();
        if (r0 == 0) goto L_0x0007;
    L_0x0006:
        return;
    L_0x0007:
        r2 = com.applovin.impl.sdk.q.a(r5);	 Catch:{ JSONException -> 0x0027 }
        r0 = r4.f;	 Catch:{ JSONException -> 0x0027 }
        com.applovin.impl.sdk.q.a(r2, r0);	 Catch:{ JSONException -> 0x0027 }
        r0 = "params";
        r0 = r2.get(r0);	 Catch:{ Throwable -> 0x0032 }
        r0 = (org.json.JSONObject) r0;	 Catch:{ Throwable -> 0x0032 }
        r0 = com.applovin.impl.sdk.bc.a(r0);	 Catch:{ Throwable -> 0x0032 }
        r1 = r0;
    L_0x001d:
        r0 = "result";
        r0 = r2.getString(r0);	 Catch:{ Throwable -> 0x003b }
    L_0x0023:
        r4.a(r0, r1);	 Catch:{ JSONException -> 0x0027 }
        goto L_0x0006;
    L_0x0027:
        r0 = move-exception;
        r1 = r4.g;
        r2 = r4.e;
        r3 = "Unable to parse API response";
        r1.e(r2, r3, r0);
        goto L_0x0006;
    L_0x0032:
        r0 = move-exception;
        r0 = new java.util.HashMap;	 Catch:{ JSONException -> 0x0027 }
        r1 = 0;
        r0.<init>(r1);	 Catch:{ JSONException -> 0x0027 }
        r1 = r0;
        goto L_0x001d;
    L_0x003b:
        r0 = move-exception;
        r0 = "network_timeout";
        goto L_0x0023;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.applovin.impl.sdk.dj.a(org.json.JSONObject):void");
    }

    public void a(boolean z) {
        synchronized (this.c) {
            this.d = z;
        }
    }

    boolean c() {
        boolean z;
        synchronized (this.c) {
            z = this.d;
        }
        return z;
    }

    public void run() {
        String b = z.b();
        String clCode = this.a.getClCode();
        Map hashMap = new HashMap(2);
        if (AppLovinSdkUtils.isValidString(clCode)) {
            hashMap.put("clcode", clCode);
        } else {
            hashMap.put("clcode", "NO_CLCODE");
        }
        if (b != null) {
            hashMap.put(AccessToken.USER_ID_KEY, b);
        }
        a("vr", new JSONObject(hashMap), new dk(this));
    }
}
