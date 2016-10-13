package com.applovin.impl.sdk;

import org.json.JSONObject;

class bz extends dc {
    final /* synthetic */ String a;
    final /* synthetic */ JSONObject b;
    final /* synthetic */ p c;
    final /* synthetic */ by d;

    bz(by byVar, String str, cd cdVar, AppLovinSdkImpl appLovinSdkImpl, String str2, JSONObject jSONObject, p pVar) {
        this.d = byVar;
        this.a = str2;
        this.b = jSONObject;
        this.c = pVar;
        super(str, cdVar, appLovinSdkImpl);
    }

    public void a(int i) {
        this.c.a(i);
    }

    protected void a(o oVar, p pVar) {
        oVar.a(q.a(q.a(this.a, this.f)), this.b, pVar);
    }

    public void a(JSONObject jSONObject, int i) {
        this.c.a(jSONObject, i);
    }
}
