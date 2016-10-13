package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.mobileads.resource.DrawableConstants.CloseButton;
import com.mopub.volley.DefaultRetryPolicy;

public class an extends u {
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;

    public an(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
        this.c = 30.0f;
        this.d = 2.0f;
        this.e = CloseButton.STROKE_WIDTH;
        this.f = 2.0f;
        this.g = DefaultRetryPolicy.DEFAULT_BACKOFF_MULT;
    }

    protected float a() {
        return this.c * this.g;
    }

    public void a(float f) {
        this.g = f;
    }

    public void a(int i) {
        a(((float) i) / this.c);
    }

    protected float b() {
        return this.e * this.g;
    }

    protected float c() {
        return this.f * this.g;
    }

    protected float d() {
        return a() / 2.0f;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = d();
        Paint paint = new Paint(1);
        paint.setARGB(80, 0, 0, 0);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(-1);
        paint2.setStyle(Style.STROKE);
        paint2.setStrokeWidth(c());
        float b = b();
        float a = a() - b;
        canvas.drawLine(b, b, a, a, paint2);
        canvas.drawLine(b, a, a, b, paint2);
    }
}
