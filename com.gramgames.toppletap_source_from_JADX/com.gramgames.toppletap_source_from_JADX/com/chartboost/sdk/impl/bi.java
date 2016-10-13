package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.j;

public class bi extends ImageView {
    protected TextView a;
    private j b;

    public bi(Context context) {
        super(context);
        this.b = null;
        this.a = null;
    }

    public void a(j jVar) {
        if (this.b != jVar) {
            this.b = jVar;
            setImageDrawable(new BitmapDrawable(jVar.f()));
        }
    }

    public void setImageBitmap(Bitmap adImage) {
        this.b = null;
        setImageDrawable(new BitmapDrawable(adImage));
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        a(canvas);
    }

    protected void a(Canvas canvas) {
        if (this.a != null) {
            this.a.layout(0, 0, canvas.getWidth(), canvas.getHeight());
            this.a.setEnabled(isEnabled());
            this.a.setSelected(isSelected());
            if (isFocused()) {
                this.a.requestFocus();
            } else {
                this.a.clearFocus();
            }
            this.a.setPressed(isPressed());
            this.a.draw(canvas);
        }
    }
}
