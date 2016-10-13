package com.applovin.impl.sdk;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class as implements OnClickListener {
    final /* synthetic */ ar a;

    as(ar arVar) {
        this.a = arVar;
    }

    public void onClick(DialogInterface dialogInterface, int i) {
        dialogInterface.dismiss();
        this.a.a.h.schedule(new at(this), 200);
    }
}
