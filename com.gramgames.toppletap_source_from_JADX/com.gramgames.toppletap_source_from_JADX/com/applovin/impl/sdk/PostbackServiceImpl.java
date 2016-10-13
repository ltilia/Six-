package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinPostbackListener;
import com.applovin.sdk.AppLovinPostbackService;
import com.applovin.sdk.AppLovinSdkUtils;
import java.util.Map;

public class PostbackServiceImpl implements AppLovinPostbackService {
    private final AppLovinSdkImpl a;

    PostbackServiceImpl(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
    }

    public void dispatchPostbackAsync(String str, AppLovinPostbackListener appLovinPostbackListener) {
        dispatchPostbackAsync(str, null, appLovinPostbackListener);
    }

    public void dispatchPostbackAsync(String str, Map map, int i, int i2, int i3, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.isValidString(str)) {
            ca cpVar = new cp(this.a, str, map, appLovinPostbackListener);
            cpVar.b(i2);
            cpVar.a(i);
            cpVar.c(i3);
            this.a.a().a(cpVar, cw.POSTBACKS);
            return;
        }
        this.a.getLogger().e("PostbackService", "Requested a postback dispatch for an empty URL; nothing to do...");
        if (appLovinPostbackListener != null) {
            appLovinPostbackListener.onPostbackFailure(str, AppLovinErrorCodes.INVALID_URL);
        }
    }

    public void dispatchPostbackAsync(String str, Map map, AppLovinPostbackListener appLovinPostbackListener) {
        if (AppLovinSdkUtils.isValidString(str)) {
            this.a.a().a(new cp(this.a, str, map, new bu(this, appLovinPostbackListener)), cw.POSTBACKS);
            return;
        }
        this.a.getLogger().d("PostbackService", "Ignoring enqueued postback request to invalid URL");
    }
}
