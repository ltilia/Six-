package com.applovin.impl.adview;

import android.view.View;
import android.view.View.OnClickListener;

class ad implements OnClickListener {
    final /* synthetic */ x a;

    ad(x xVar) {
        this.a = xVar;
    }

    public void onClick(View view) {
        if (this.a.g.isClickable()) {
            this.a.g.performClick();
        }
    }
}
