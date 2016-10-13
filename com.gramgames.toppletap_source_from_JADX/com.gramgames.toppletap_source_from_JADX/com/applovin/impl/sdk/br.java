package com.applovin.impl.sdk;

import android.content.SharedPreferences;
import com.applovin.sdk.AppLovinLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class br {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;
    private ArrayList c;
    private ArrayList d;
    private final Object e;
    private final SharedPreferences f;

    br(AppLovinSdkImpl appLovinSdkImpl) {
        if (appLovinSdkImpl == null) {
            throw new IllegalArgumentException("No sdk specified");
        }
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
        this.f = appLovinSdkImpl.getApplicationContext().getSharedPreferences("com.applovin.sdk.impl.postbackQueue.domain", 0);
        this.e = new Object();
        this.c = c();
        this.d = new ArrayList();
    }

    private bt a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return new bt(this, jSONObject.getString("targetUrl"), bc.a(jSONObject.getJSONObject("requestBody")), jSONObject.getInt("attemptNumber"));
        } catch (Throwable e) {
            this.b.w("PersistentPostbackManager", "Unable to inflate postback request from JSON.", e);
            return null;
        }
    }

    private void a(bt btVar) {
        synchronized (this.e) {
            b(btVar);
            c(btVar);
        }
    }

    private void b(bt btVar) {
        synchronized (this.e) {
            if (this.c.size() < ((Integer) this.a.a(cb.bf)).intValue()) {
                this.c.add(btVar);
                d();
                this.b.d("PersistentPostbackManager", "Enqueued postback: " + btVar);
            } else {
                this.b.w("PersistentPostbackManager", "Persistent queue has reached maximum size; postback retried in memory only." + btVar);
            }
        }
    }

    private ArrayList c() {
        if (n.b()) {
            Set<String> stringSet = this.f.getStringSet("com.applovin.sdk.impl.postbackQueue.key", new LinkedHashSet(0));
            ArrayList arrayList = new ArrayList(Math.max(1, stringSet.size()));
            int intValue = ((Integer) this.a.a(cb.bg)).intValue();
            this.b.d("PersistentPostbackManager", "Deserializing " + stringSet.size() + " postback(s).");
            for (String str : stringSet) {
                bt a = a(str);
                if (a == null) {
                    this.b.e("PersistentPostbackManager", "Unable to deserialize postback json: " + str);
                } else if (a.a() > intValue) {
                    arrayList.add(a);
                } else {
                    this.b.d("PersistentPostbackManager", "Skipping deserialization because maximum attempt count exceeded for postback: " + a);
                }
            }
            this.b.d("PersistentPostbackManager", "Successfully loaded postback queue with " + arrayList.size() + " postback(s).");
            return arrayList;
        }
        this.b.d("PersistentPostbackManager", "Loading new postback queue due to old Android version...");
        return new ArrayList();
    }

    private void c(bt btVar) {
        this.b.d("PersistentPostbackManager", "Preparing to submit postback..." + btVar);
        synchronized (this.e) {
            btVar.a(btVar.a() + 1);
            d();
        }
        int intValue = ((Integer) this.a.a(cb.bg)).intValue();
        if (btVar.a() > intValue) {
            this.b.w("PersistentPostbackManager", "Exceeded maximum persisted attempt count of " + intValue + ". Dequeuing postback: " + btVar);
            d(btVar);
            return;
        }
        this.a.getPostbackService().dispatchPostbackAsync(btVar.b(), btVar.c(), new bs(this, btVar));
    }

    private void d() {
        if (n.b()) {
            Set linkedHashSet = new LinkedHashSet(this.c.size());
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                String f = f((bt) it.next());
                if (f != null) {
                    linkedHashSet.add(f);
                }
            }
            this.f.edit().putStringSet("com.applovin.sdk.impl.postbackQueue.key", linkedHashSet).commit();
            this.b.d("PersistentPostbackManager", "Wrote updated postback queue to disk.");
            return;
        }
        this.b.d("PersistentPostbackManager", "Skipping writing postback queue to disk due to old Android version...");
    }

    private void d(bt btVar) {
        synchronized (this.e) {
            this.c.remove(btVar);
            d();
        }
        this.b.d("PersistentPostbackManager", "Dequeued successfully transmitted postback: " + btVar);
    }

    private void e(bt btVar) {
        synchronized (this.e) {
            this.d.add(btVar);
        }
    }

    private String f(bt btVar) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("attemptNumber", btVar.a()).put("targetUrl", btVar.b());
            Map c = btVar.c();
            if (c != null) {
                jSONObject.put("requestBody", new JSONObject(c));
            }
            return jSONObject.toString();
        } catch (Throwable e) {
            this.b.w("PersistentPostbackManager", "Unable to serialize postback request to JSON.", e);
            return null;
        }
    }

    public void a() {
        synchronized (this.e) {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                c((bt) it.next());
            }
        }
    }

    public void a(String str, Map map) {
        a(new bt(this, str, map));
    }

    public void b() {
        synchronized (this.e) {
            Iterator it = this.d.iterator();
            while (it.hasNext()) {
                c((bt) it.next());
            }
            this.d.clear();
        }
    }
}
