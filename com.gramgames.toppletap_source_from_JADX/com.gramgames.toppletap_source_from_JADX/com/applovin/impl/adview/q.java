package com.applovin.impl.adview;

import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;

class q implements OnLongClickListener {
    final /* synthetic */ o a;

    q(o oVar) {
        this.a = oVar;
    }

    public boolean onLongClick(View view) {
        Log.d("AdWebView", "Received a LongClick event.");
        return true;
    }
}
