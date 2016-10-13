package com.vungle.publisher.db;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vungle.log.Logger;
import com.vungle.publisher.em;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class DatabaseBroadcastReceiver extends BroadcastReceiver {
    @Inject
    public Context a;
    @Inject
    public DatabaseHelper b;
    @Inject
    public em c;

    @Inject
    DatabaseBroadcastReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        try {
            if ("com.vungle.publisher.db.DUMP_TABLES".equals(intent.getAction())) {
                Logger.d(Logger.DATABASE_DUMP_TAG, this.c.b() + " received dump tables request");
                this.b.a(intent.getStringArrayExtra("tables"));
            }
        } catch (Throwable e) {
            Logger.w(Logger.DATABASE_DUMP_TAG, "error dumping database", e);
        }
    }
}
