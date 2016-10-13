package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinPostbackListener;

class bu implements AppLovinPostbackListener {
    final /* synthetic */ AppLovinPostbackListener a;
    final /* synthetic */ PostbackServiceImpl b;

    bu(PostbackServiceImpl postbackServiceImpl, AppLovinPostbackListener appLovinPostbackListener) {
        this.b = postbackServiceImpl;
        this.a = appLovinPostbackListener;
    }

    public void onPostbackFailure(String str, int i) {
        this.b.a.getLogger().e("PostbackService", "Failed to dispatch postback to URL " + str + ": " + i);
        if (this.a != null) {
            this.a.onPostbackFailure(str, i);
        }
    }

    public void onPostbackSuccess(String str) {
        this.b.a.getLogger().d("PostbackService", "Successfully dispatched postback to URL " + str);
        if (this.a != null) {
            this.a.onPostbackSuccess(str);
        }
    }
}
