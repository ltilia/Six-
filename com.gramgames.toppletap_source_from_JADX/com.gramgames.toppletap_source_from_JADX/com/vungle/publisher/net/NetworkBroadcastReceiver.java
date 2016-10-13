package com.vungle.publisher.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import com.vungle.log.Logger;
import com.vungle.publisher.dq;
import com.vungle.publisher.dr;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.fw;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class NetworkBroadcastReceiver extends BroadcastReceiver {
    public static final IntentFilter a;
    @Inject
    public Context b;
    @Inject
    public fw c;
    @Inject
    public EventBus d;
    private final AtomicBoolean e;

    static {
        a = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    }

    @Inject
    NetworkBroadcastReceiver() {
        this.e = new AtomicBoolean(false);
    }

    public void onReceive(Context context, Intent intent) {
        if (!"android.net.conn.CONNECTIVITY_CHANGE".equals(intent.getAction())) {
            return;
        }
        if (intent.getBooleanExtra("noConnectivity", false)) {
            if (this.e.compareAndSet(true, false)) {
                Logger.d(Logger.NETWORK_TAG, "lost connectivity");
                this.d.a(new dr());
            }
        } else if (intent.getBooleanExtra("isFailover", false)) {
            Logger.d(Logger.NETWORK_TAG, "connectivity failover");
        } else {
            Logger.d(Logger.NETWORK_TAG, "connectivity established");
            synchronized (this) {
                notifyAll();
            }
            if (this.e.compareAndSet(false, true)) {
                this.d.a(new dq());
            }
        }
    }
}
