package com.facebook.ads.internal.action;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.util.Log;
import com.applovin.sdk.AppLovinEventParameters;
import com.facebook.ads.internal.util.b.a;
import com.facebook.ads.internal.util.f;
import com.facebook.ads.internal.util.i;
import com.facebook.ads.internal.util.s;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.ExecutionOptions;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class c extends a {
    private static final String a;
    private final Context b;
    private final Uri c;

    static {
        a = c.class.getSimpleName();
    }

    public c(Context context, Uri uri) {
        this.b = context;
        this.c = uri;
    }

    private Intent a(i iVar) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        if (!(s.a(iVar.a()) || s.a(iVar.b()))) {
            intent.setComponent(new ComponentName(iVar.a(), iVar.b()));
        }
        if (!s.a(iVar.c())) {
            intent.setData(Uri.parse(iVar.c()));
        }
        return intent;
    }

    private Intent b(i iVar) {
        if (s.a(iVar.a())) {
            return null;
        }
        if (!f.a(this.b, iVar.a())) {
            return null;
        }
        String c = iVar.c();
        if (!s.a(c) && (c.startsWith("tel:") || c.startsWith("telprompt:"))) {
            return new Intent("android.intent.action.CALL", Uri.parse(c));
        }
        PackageManager packageManager = this.b.getPackageManager();
        if (s.a(iVar.b()) && s.a(c)) {
            return packageManager.getLaunchIntentForPackage(iVar.a());
        }
        Intent a = a(iVar);
        List<ResolveInfo> queryIntentActivities = packageManager.queryIntentActivities(a, ExecutionOptions.MAX_TRACKING_TAG_STRING_LENGTH);
        if (a.getComponent() == null) {
            for (ResolveInfo resolveInfo : queryIntentActivities) {
                if (resolveInfo.activityInfo.packageName.equals(iVar.a())) {
                    a.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
                    break;
                }
            }
        }
        return (queryIntentActivities.isEmpty() || a.getComponent() == null) ? null : a;
    }

    private List<i> f() {
        String queryParameter = this.c.getQueryParameter("appsite_data");
        if (s.a(queryParameter) || "[]".equals(queryParameter)) {
            return null;
        }
        List<i> arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(queryParameter).optJSONArray("android");
            if (optJSONArray == null) {
                return arrayList;
            }
            for (int i = 0; i < optJSONArray.length(); i++) {
                i a = i.a(optJSONArray.optJSONObject(i));
                if (a != null) {
                    arrayList.add(a);
                }
            }
            return arrayList;
        } catch (Throwable e) {
            Log.w(a, "Error parsing appsite_data", e);
            return arrayList;
        }
    }

    public a a() {
        return a.OPEN_STORE;
    }

    public void b() {
        a(this.b, this.c);
        List<Intent> d = d();
        if (d != null) {
            for (Intent startActivity : d) {
                try {
                    this.b.startActivity(startActivity);
                    return;
                } catch (Throwable e) {
                    Log.d(a, "Failed to open app intent, falling back", e);
                }
            }
        }
        e();
    }

    protected Uri c() {
        String queryParameter = this.c.getQueryParameter("store_url");
        if (!s.a(queryParameter)) {
            return Uri.parse(queryParameter);
        }
        queryParameter = this.c.getQueryParameter(AppLovinEventParameters.IN_APP_PURCHASE_TRANSACTION_IDENTIFIER);
        return Uri.parse(String.format("market://details?id=%s", new Object[]{queryParameter}));
    }

    protected List<Intent> d() {
        List<i> f = f();
        List<Intent> arrayList = new ArrayList();
        if (f != null) {
            for (i b : f) {
                Intent b2 = b(b);
                if (b2 != null) {
                    arrayList.add(b2);
                }
            }
        }
        return arrayList;
    }

    public void e() {
        Intent intent = new Intent("android.intent.action.VIEW", c());
        intent.addFlags(DriveFile.MODE_READ_ONLY);
        try {
            this.b.startActivity(intent);
        } catch (Throwable e) {
            Log.d(a, "Failed to open market url: " + this.c.toString(), e);
            String queryParameter = this.c.getQueryParameter("store_url_web_fallback");
            if (queryParameter != null && queryParameter.length() > 0) {
                Intent intent2 = new Intent("android.intent.action.VIEW", Uri.parse(queryParameter));
                intent2.addFlags(DriveFile.MODE_READ_ONLY);
                try {
                    this.b.startActivity(intent2);
                } catch (Throwable e2) {
                    Log.d(a, "Failed to open fallback url: " + queryParameter, e2);
                }
            }
        }
    }
}
