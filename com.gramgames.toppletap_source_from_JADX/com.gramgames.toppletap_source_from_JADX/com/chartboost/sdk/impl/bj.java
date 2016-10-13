package com.chartboost.sdk.impl;

import android.content.Context;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView.ScaleType;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.chartboost.sdk.Libraries.j;
import org.json.simple.parser.Yytoken;

public abstract class bj extends RelativeLayout {
    private static Rect b;
    private a a;
    protected boolean c;
    protected Button d;
    private boolean e;

    class 1 implements OnTouchListener {
        final /* synthetic */ bj a;

        1(bj bjVar) {
            this.a = bjVar;
        }

        public boolean onTouch(View v, MotionEvent event) {
            boolean a = bj.b(v, event);
            switch (event.getActionMasked()) {
                case Yylex.YYINITIAL /*0*/:
                    this.a.a.a(a);
                    return a;
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                    if (this.a.getVisibility() == 0 && this.a.isEnabled() && a) {
                        this.a.a(event);
                    }
                    this.a.a.a(false);
                    break;
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    this.a.a.a(a);
                    break;
                case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                    this.a.a.a(false);
                    break;
            }
            return true;
        }
    }

    class 2 implements OnClickListener {
        final /* synthetic */ bj a;

        2(bj bjVar) {
            this.a = bjVar;
        }

        public void onClick(View v) {
            this.a.a(null);
        }
    }

    public class a extends bi {
        final /* synthetic */ bj b;

        public a(bj bjVar, Context context) {
            this.b = bjVar;
            super(context);
            bjVar.c = false;
        }

        protected void a(boolean z) {
            if (this.b.e && z) {
                if (!this.b.c) {
                    if (getDrawable() != null) {
                        getDrawable().setColorFilter(1996488704, Mode.SRC_ATOP);
                    } else if (getBackground() != null) {
                        getBackground().setColorFilter(1996488704, Mode.SRC_ATOP);
                    }
                    invalidate();
                    this.b.c = true;
                }
            } else if (this.b.c) {
                if (getDrawable() != null) {
                    getDrawable().clearColorFilter();
                } else if (getBackground() != null) {
                    getBackground().clearColorFilter();
                }
                invalidate();
                this.b.c = false;
            }
        }

        public void a(j jVar, LayoutParams layoutParams) {
            a(jVar);
            layoutParams.width = jVar.h();
            layoutParams.height = jVar.i();
        }
    }

    protected abstract void a(MotionEvent motionEvent);

    static {
        b = new Rect();
    }

    public bj(Context context) {
        super(context);
        this.c = false;
        this.d = null;
        this.e = true;
        b();
    }

    private void b() {
        this.a = new a(this, getContext());
        this.a.setOnTouchListener(new 1(this));
        addView(this.a, new RelativeLayout.LayoutParams(-1, -1));
    }

    private static boolean b(View view, MotionEvent motionEvent) {
        view.getLocalVisibleRect(b);
        Rect rect = b;
        rect.left += view.getPaddingLeft();
        rect = b;
        rect.top += view.getPaddingTop();
        rect = b;
        rect.right -= view.getPaddingRight();
        rect = b;
        rect.bottom -= view.getPaddingBottom();
        return b.contains(Math.round(motionEvent.getX()), Math.round(motionEvent.getY()));
    }

    public void a(String str) {
        if (str != null) {
            a().setText(str);
            addView(a(), new RelativeLayout.LayoutParams(-1, -1));
            this.a.setVisibility(8);
            a(false);
            this.d.setOnClickListener(new 2(this));
        } else if (this.d != null) {
            removeView(a());
            this.d = null;
            this.a.setVisibility(0);
            a(true);
        }
    }

    public TextView a() {
        if (this.d == null) {
            this.d = new Button(getContext());
            this.d.setGravity(17);
        }
        this.d.postInvalidate();
        return this.d;
    }

    public void a(j jVar) {
        this.a.a(jVar);
        a(null);
    }

    public void a(j jVar, RelativeLayout.LayoutParams layoutParams) {
        this.a.a(jVar, layoutParams);
        a(null);
    }

    public void a(ScaleType scaleType) {
        this.a.setScaleType(scaleType);
    }

    public void a(boolean z) {
        this.e = z;
    }
}
