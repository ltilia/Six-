package com.applovin.impl.adview;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class p implements OnTouchListener {
    final /* synthetic */ o a;

    p(o oVar) {
        this.a = oVar;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (!view.hasFocus()) {
            view.requestFocus();
        }
        return false;
    }
}
