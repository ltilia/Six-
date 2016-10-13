package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class av implements OnClickListener {
    final /* synthetic */ ar a;

    av(ar arVar) {
        this.a = arVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a.b.a(this.a.a.g);
    }
}
