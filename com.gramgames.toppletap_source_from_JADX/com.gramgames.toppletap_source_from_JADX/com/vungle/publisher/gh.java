package com.vungle.publisher;

import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.async.ScheduledPriorityExecutor.b;
import com.vungle.publisher.db.model.Ad;
import com.vungle.publisher.db.model.EventTracking.a;
import com.vungle.publisher.net.http.TrackEventHttpTransactionFactory;
import gs.gram.mopub.BuildConfig;
import java.util.Map;
import java.util.Map.Entry;
import javax.inject.Inject;

/* compiled from: vungle */
public final class gh extends gk {
    @Inject
    TrackEventHttpTransactionFactory a;
    @Inject
    ScheduledPriorityExecutor b;

    class 1 implements Runnable {
        final /* synthetic */ Ad a;
        final /* synthetic */ a b;
        final /* synthetic */ String c;
        final /* synthetic */ Map d;
        final /* synthetic */ gh e;

        1(gh ghVar, Ad ad, a aVar, String str, Map map) {
            this.e = ghVar;
            this.a = ad;
            this.b = aVar;
            this.c = str;
            this.d = map;
        }

        public final void run() {
            try {
                TrackEventHttpTransactionFactory trackEventHttpTransactionFactory = this.e.a;
                Ad ad = this.a;
                a aVar = this.b;
                String str = this.c;
                Map map = this.d;
                if (map != null) {
                    StringBuilder stringBuilder = new StringBuilder(str);
                    for (Entry entry : map.entrySet()) {
                        str = (String) entry.getKey();
                        int i = -1;
                        while (true) {
                            int indexOf = stringBuilder.indexOf(str, i);
                            if (indexOf > 0) {
                                stringBuilder.replace(indexOf, str.length() + indexOf, entry.getValue() == null ? BuildConfig.FLAVOR : (String) entry.getValue());
                                i = indexOf;
                            }
                        }
                    }
                    str = stringBuilder.toString();
                }
                trackEventHttpTransactionFactory.a(ad, aVar, str).a();
            } catch (Throwable e) {
                Logger.w(Logger.NETWORK_TAG, "error sending " + this.b + " event", e);
            }
        }
    }

    public final void a(Ad<?, ?, ?> ad, a aVar, Map<String, String> map, String... strArr) {
        if (strArr != null) {
            for (String str : strArr) {
                if (str != null) {
                    this.b.a(new 1(this, ad, aVar, str, map), b.externalNetworkRequest);
                }
            }
        }
    }
}
