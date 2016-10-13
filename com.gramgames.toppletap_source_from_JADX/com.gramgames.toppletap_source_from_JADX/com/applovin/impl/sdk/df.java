package com.applovin.impl.sdk;

import org.json.JSONObject;

class df implements p {
    final /* synthetic */ de a;

    df(de deVar) {
        this.a = deVar;
    }

    public void a(int i) {
        this.a.g.d("TaskReportReward", "Failed to report reward for ad: " + this.a.a.getAdIdNumber() + " - error code: " + i);
    }

    public void a(JSONObject jSONObject, int i) {
        this.a.g.d("TaskReportReward", "Reported reward successfully for ad: " + this.a.a.getAdIdNumber());
    }
}
