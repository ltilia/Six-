package com.applovin.adview;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import org.json.simple.parser.Yytoken;

public class AppLovinTouchToClickListener implements OnTouchListener {
    private long a;
    private float b;
    private float c;
    private Context d;
    private OnClickListener e;

    public AppLovinTouchToClickListener(Context context, OnClickListener onClickListener) {
        this.d = context;
        this.e = onClickListener;
    }

    private float a(float f) {
        return f / this.d.getResources().getDisplayMetrics().density;
    }

    private float a(float f, float f2, float f3, float f4) {
        float f5 = f - f3;
        float f6 = f2 - f4;
        return a((float) Math.sqrt((double) ((f5 * f5) + (f6 * f6))));
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case Yylex.YYINITIAL /*0*/:
                this.a = System.currentTimeMillis();
                this.b = motionEvent.getX();
                this.c = motionEvent.getY();
                break;
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                if (System.currentTimeMillis() - this.a < 1000 && a(this.b, this.c, motionEvent.getX(), motionEvent.getY()) < 10.0f) {
                    this.e.onClick(view);
                    break;
                }
        }
        return true;
    }
}
