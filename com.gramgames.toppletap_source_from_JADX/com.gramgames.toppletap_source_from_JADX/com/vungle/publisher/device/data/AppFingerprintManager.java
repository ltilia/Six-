package com.vungle.publisher.device.data;

import android.content.pm.PackageInfo;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.LoggedException;
import com.vungle.publisher.device.data.AppFingerprint.Factory;
import com.vungle.publisher.env.SdkConfig;
import com.vungle.publisher.gu;
import com.vungle.publisher.gu.2;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import org.json.JSONObject;

@Singleton
/* compiled from: vungle */
public class AppFingerprintManager {
    @Inject
    public SdkConfig a;
    @Inject
    public Factory b;
    @Inject
    public gu c;
    @Inject
    public ScheduledPriorityExecutor d;
    @Inject
    public LoggedException.Factory e;

    class 1 implements Runnable {
        final /* synthetic */ AppFingerprintManager a;

        1(AppFingerprintManager appFingerprintManager) {
            this.a = appFingerprintManager;
        }

        public final void run() {
            try {
                Logger.d(Logger.DATA_TAG, "creating and sending app fingerprint");
                gu guVar = this.a.c;
                Factory factory = this.a.b;
                List<PackageInfo> installedPackages = factory.b.getPackageManager().getInstalledPackages(0);
                JSONObject jSONObject = new JSONObject();
                for (PackageInfo packageInfo : installedPackages) {
                    if (packageInfo != null) {
                        jSONObject.put(packageInfo.packageName, true);
                    }
                }
                AppFingerprint appFingerprint = (AppFingerprint) factory.c.get();
                appFingerprint.a = jSONObject;
                appFingerprint.b = System.currentTimeMillis();
                appFingerprint.c = factory.a.a();
                appFingerprint.d = factory.a.c();
                appFingerprint.e = factory.a.i();
                guVar.e.a(new 2(guVar, appFingerprint), b.appFingerprint);
            } catch (Throwable e) {
                this.a.e.a(Logger.DATA_TAG, "exception while creating/ sending app fingerprint", e);
            }
        }
    }

    @Inject
    AppFingerprintManager() {
    }

    public final void a() {
        try {
            long j = this.a.g;
            if (j > 0) {
                Logger.v(Logger.DATA_TAG, "app fingerprinting allowed by server");
                if (j + this.a.f < System.currentTimeMillis()) {
                    this.d.a(new 1(this), b.appFingerprint);
                    return;
                } else {
                    Logger.d(Logger.DATA_TAG, "throttled fingerprint request");
                    return;
                }
            }
            Logger.v(Logger.DATA_TAG, "app fingerprinting not allowed by server");
        } catch (Throwable e) {
            this.e.a(Logger.DATA_TAG, "exception while throttling app fingerprint", e);
        }
    }
}
