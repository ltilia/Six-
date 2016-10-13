package com.applovin.impl.sdk;

import android.content.Intent;
import com.applovin.adview.AppLovinConfirmationActivity;

class ap implements Runnable {
    final /* synthetic */ ao a;

    ap(ao aoVar) {
        this.a = aoVar;
    }

    public void run() {
        String str = (String) this.a.a.a(cb.O);
        String b = this.a.b();
        String str2 = (String) this.a.a.a(cb.T);
        if (n.a(AppLovinConfirmationActivity.class, this.a.c)) {
            try {
                Intent intent = new Intent(this.a.c, AppLovinConfirmationActivity.class);
                intent.putExtra("dialog_title", str);
                intent.putExtra("dialog_body", b);
                intent.putExtra("dialog_button_text", str2);
                this.a.c.startActivity(intent);
                return;
            } catch (Throwable th) {
                this.a.a(b, th);
                return;
            }
        }
        this.a.a(b, null);
    }
}
