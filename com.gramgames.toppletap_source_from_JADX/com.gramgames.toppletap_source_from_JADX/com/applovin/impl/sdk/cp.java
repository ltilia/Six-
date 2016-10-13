package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class cp extends ca {
    private final String a;
    private final Map b;
    private final AppLovinPostbackListener c;
    private int d;
    private int i;
    private int j;

    public cp(AppLovinSdkImpl appLovinSdkImpl, String str, Map map, AppLovinPostbackListener appLovinPostbackListener) {
        super("TaskDispatchPostback", appLovinSdkImpl);
        this.j = -1;
        this.a = str;
        this.c = appLovinPostbackListener;
        this.b = map;
    }

    public void a(int i) {
        this.d = i;
    }

    public void b(int i) {
        this.i = i;
    }

    public void c(int i) {
        this.j = i;
    }

    public void run() {
        if (AppLovinSdkUtils.isValidString(this.a)) {
            dc cqVar = new cq(this, "RepeatTaskDispatchPostback", this.d < 0 ? ((Integer) this.f.a(cb.ay)).intValue() : this.d, this.f);
            cqVar.a((long) this.i);
            cqVar.run();
            return;
        }
        this.f.getLogger().i("TaskDispatchPostback", "Requested URL is not valid; nothing to do...");
        this.c.onPostbackFailure(this.a, AppLovinErrorCodes.INVALID_URL);
    }
}
