package com.facebook.ads;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.Transformation;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.facebook.ads.NativeAd.Image;

public class AdChoicesView extends RelativeLayout {
    private final Context a;
    private final NativeAd b;
    private final DisplayMetrics c;
    private boolean d;
    private TextView e;

    class 1 implements OnTouchListener {
        final /* synthetic */ AdChoicesView a;

        1(AdChoicesView adChoicesView) {
            this.a = adChoicesView;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 0) {
                return false;
            }
            if (this.a.d) {
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.addCategory("android.intent.category.BROWSABLE");
                intent.setData(Uri.parse(this.a.b.getAdChoicesLinkUrl()));
                this.a.a.startActivity(intent);
            } else {
                this.a.a();
            }
            return true;
        }
    }

    class 2 extends Animation {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ AdChoicesView c;

        2(AdChoicesView adChoicesView, int i, int i2) {
            this.c = adChoicesView;
            this.a = i;
            this.b = i2;
        }

        protected void applyTransformation(float f, Transformation transformation) {
            int i = (int) (((float) this.a) + (((float) (this.b - this.a)) * f));
            this.c.getLayoutParams().width = i;
            this.c.requestLayout();
            this.c.e.getLayoutParams().width = i - this.a;
            this.c.e.requestLayout();
        }

        public boolean willChangeBounds() {
            return true;
        }
    }

    class 3 implements AnimationListener {
        final /* synthetic */ int a;
        final /* synthetic */ int b;
        final /* synthetic */ AdChoicesView c;

        class 1 implements Runnable {
            final /* synthetic */ 3 a;

            class 1 extends Animation {
                final /* synthetic */ 1 a;

                1(1 1) {
                    this.a = 1;
                }

                protected void applyTransformation(float f, Transformation transformation) {
                    int i = (int) (((float) this.a.a.a) + (((float) (this.a.a.b - this.a.a.a)) * f));
                    this.a.a.c.getLayoutParams().width = i;
                    this.a.a.c.requestLayout();
                    this.a.a.c.e.getLayoutParams().width = i - this.a.a.b;
                    this.a.a.c.e.requestLayout();
                }

                public boolean willChangeBounds() {
                    return true;
                }
            }

            1(3 3) {
                this.a = 3;
            }

            public void run() {
                if (this.a.c.d) {
                    this.a.c.d = false;
                    Animation 1 = new 1(this);
                    1.setDuration(300);
                    1.setFillAfter(true);
                    this.a.c.startAnimation(1);
                }
            }
        }

        3(AdChoicesView adChoicesView, int i, int i2) {
            this.c = adChoicesView;
            this.a = i;
            this.b = i2;
        }

        public void onAnimationEnd(Animation animation) {
            new Handler().postDelayed(new 1(this), 3000);
        }

        public void onAnimationRepeat(Animation animation) {
        }

        public void onAnimationStart(Animation animation) {
        }
    }

    public AdChoicesView(Context context, NativeAd nativeAd) {
        this(context, nativeAd, false);
    }

    public AdChoicesView(Context context, NativeAd nativeAd, boolean z) {
        super(context);
        this.d = false;
        this.a = context;
        this.b = nativeAd;
        this.c = this.a.getResources().getDisplayMetrics();
        Image adChoicesIcon = this.b.getAdChoicesIcon();
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
        setOnTouchListener(new 1(this));
        this.e = new TextView(this.a);
        addView(this.e);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        if (z) {
            layoutParams2.addRule(11, a(adChoicesIcon).getId());
            layoutParams2.width = 0;
            layoutParams.width = Math.round(((float) (adChoicesIcon.getWidth() + 4)) * this.c.density);
            layoutParams.height = Math.round(((float) (adChoicesIcon.getHeight() + 2)) * this.c.density);
            this.d = false;
        } else {
            this.d = true;
        }
        setLayoutParams(layoutParams);
        layoutParams2.addRule(15, -1);
        this.e.setLayoutParams(layoutParams2);
        this.e.setSingleLine();
        this.e.setText("AdChoices");
        this.e.setTextSize(10.0f);
        this.e.setTextColor(-4341303);
    }

    private ImageView a(Image image) {
        View imageView = new ImageView(this.a);
        addView(imageView);
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(Math.round(((float) image.getWidth()) * this.c.density), Math.round(((float) image.getHeight()) * this.c.density));
        layoutParams.addRule(9);
        layoutParams.addRule(15, -1);
        layoutParams.setMargins(Math.round(4.0f * this.c.density), Math.round(this.c.density * 2.0f), Math.round(this.c.density * 2.0f), Math.round(this.c.density * 2.0f));
        imageView.setLayoutParams(layoutParams);
        NativeAd.downloadAndDisplayImage(image, imageView);
        return imageView;
    }

    private void a() {
        Paint paint = new Paint();
        paint.setTextSize(this.e.getTextSize());
        int round = Math.round(paint.measureText("AdChoices") + (4.0f * this.c.density));
        int width = getWidth();
        round += width;
        this.d = true;
        Animation 2 = new 2(this, width, round);
        2.setAnimationListener(new 3(this, round, width));
        2.setDuration(300);
        2.setFillAfter(true);
        startAnimation(2);
    }
}
