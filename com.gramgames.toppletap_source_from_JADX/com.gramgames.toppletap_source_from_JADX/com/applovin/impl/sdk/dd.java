package com.applovin.impl.sdk;

import android.util.Log;
import com.applovin.sdk.AppLovinErrorCodes;
import org.json.JSONObject;

class dd implements p {
    final /* synthetic */ AppLovinSdkImpl a;
    final /* synthetic */ String b;
    final /* synthetic */ dc c;

    dd(dc dcVar, AppLovinSdkImpl appLovinSdkImpl, String str) {
        this.c = dcVar;
        this.a = appLovinSdkImpl;
        this.b = str;
    }

    public void a(int i) {
        Object obj = 1;
        Object obj2 = (i < 200 || i >= 500) ? 1 : null;
        if (i == AppLovinErrorCodes.NO_NETWORK) {
            obj = null;
        }
        if (obj2 == null || r0 == null || this.c.a <= 0) {
            this.c.a(i);
            return;
        }
        long longValue = this.c.b < 0 ? ((Long) this.a.a(cb.l)).longValue() : this.c.b;
        Log.w(this.b, "Unable to send request due to server failure (code " + i + "). " + this.c.a + " attempts left, retrying in " + (((double) longValue) / 1000.0d) + " seconds...");
        this.c.a = this.c.a - 1;
        if (this.c.a == 0) {
            this.c.c();
        }
        this.a.a().a(this.c, cw.BACKGROUND, longValue);
    }

    public void a(JSONObject jSONObject, int i) {
        this.c.a = 0;
        this.c.a(jSONObject, i);
    }
}
