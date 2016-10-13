package com.amazon.device.ads;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import org.json.JSONObject;

class AppInfo {
    private final JSONObject packageInfoUrlJSON;
    private final PackageManager packageManager;
    private final String packageName;

    public AppInfo(Context context) {
        this(context, Metrics.getInstance().getMetricsCollector(), new JSONObject());
    }

    AppInfo(Context context, MetricsCollector metricsCollector, JSONObject packageInfoUrlJSON) {
        String versionCode = null;
        this.packageInfoUrlJSON = packageInfoUrlJSON;
        this.packageName = context.getPackageName();
        JSONUtils.put(packageInfoUrlJSON, "pn", this.packageName);
        this.packageManager = context.getPackageManager();
        try {
            String charSequence;
            CharSequence applicationLabel = this.packageManager.getApplicationLabel(context.getApplicationInfo());
            String str = "lbl";
            if (applicationLabel != null) {
                charSequence = applicationLabel.toString();
            } else {
                charSequence = null;
            }
            JSONUtils.put(packageInfoUrlJSON, str, charSequence);
        } catch (ArrayIndexOutOfBoundsException e) {
            metricsCollector.incrementMetric(MetricType.APP_INFO_LABEL_INDEX_OUT_OF_BOUNDS);
        }
        try {
            String versionName;
            PackageInfo packageInfo = this.packageManager.getPackageInfo(this.packageName, 0);
            if (packageInfo != null) {
                versionName = packageInfo.versionName;
            } else {
                versionName = null;
            }
            JSONUtils.put(packageInfoUrlJSON, "vn", versionName);
            if (packageInfo != null) {
                versionCode = Integer.toString(packageInfo.versionCode);
            }
            JSONUtils.put(packageInfoUrlJSON, "v", versionCode);
        } catch (NameNotFoundException e2) {
        }
    }

    protected AppInfo() {
        this.packageName = null;
        this.packageInfoUrlJSON = null;
        this.packageManager = null;
    }

    public JSONObject getPackageInfoJSON() {
        return this.packageInfoUrlJSON;
    }

    public String getPackageInfoJSONString() {
        if (this.packageInfoUrlJSON != null) {
            return this.packageInfoUrlJSON.toString();
        }
        return null;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public PackageManager getPackageManager() {
        return this.packageManager;
    }
}
