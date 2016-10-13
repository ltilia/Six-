package com.facebook.ads.internal.view;

import android.content.Context;
import android.widget.RelativeLayout;

public class l extends RelativeLayout {
    private int a;
    private int b;

    public l(Context context) {
        super(context);
        this.a = 0;
        this.b = 0;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.b > 0 && getMeasuredWidth() > this.b) {
            setMeasuredDimension(this.b, getMeasuredHeight());
        } else if (getMeasuredWidth() < this.a) {
            setMeasuredDimension(this.a, getMeasuredHeight());
        }
    }

    protected void setMaxWidth(int i) {
        this.b = i;
    }

    public void setMinWidth(int i) {
        this.a = i;
    }
}
