package com.applovin.impl.adview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import com.applovin.sdk.AppLovinSdk;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import com.mopub.volley.DefaultRetryPolicy;

public class ao extends u {
    private float c;
    private float d;
    private float e;
    private float f;
    private float g;

    public ao(AppLovinSdk appLovinSdk, Context context) {
        super(appLovinSdk, context);
        this.c = 30.0f;
        this.d = 2.0f;
        this.e = 10.0f;
        this.f = 3.0f;
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

    protected float e() {
        return this.d * this.g;
    }

    protected float f() {
        return d() - e();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float d = d();
        Paint paint = new Paint(1);
        paint.setColor(-1);
        canvas.drawCircle(d, d, d, paint);
        Paint paint2 = new Paint(1);
        paint2.setColor(CtaButton.BACKGROUND_COLOR);
        canvas.drawCircle(d, d, f(), paint2);
        Paint paint3 = new Paint(paint);
        paint3.setStyle(Style.STROKE);
        paint3.setStrokeWidth(c());
        float b = b();
        float a = a() - b;
        canvas.drawLine(b, b, a, a, paint3);
        canvas.drawLine(b, a, a, b, paint3);
    }
}
