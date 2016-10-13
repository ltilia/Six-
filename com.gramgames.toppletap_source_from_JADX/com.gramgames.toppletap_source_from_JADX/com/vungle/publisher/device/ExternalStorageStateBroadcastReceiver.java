package com.vungle.publisher.device;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.vungle.publisher.do;
import com.vungle.publisher.dp;
import com.vungle.publisher.event.EventBus;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class ExternalStorageStateBroadcastReceiver extends BroadcastReceiver {
    @Inject
    public Context a;
    @Inject
    public EventBus b;

    @Inject
    ExternalStorageStateBroadcastReceiver() {
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("android.intent.action.MEDIA_MOUNTED".equals(action)) {
            this.b.a(new do());
        } else if ("android.intent.action.MEDIA_UNMOUNTED".equals(action)) {
            this.b.a(new dp());
        }
    }
}
