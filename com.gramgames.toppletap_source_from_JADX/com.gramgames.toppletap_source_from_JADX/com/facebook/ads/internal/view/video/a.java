package com.facebook.ads.internal.view.video;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Cap;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import com.facebook.ads.AdError;
import com.facebook.ads.InterstitialAdActivity;
import com.facebook.ads.InterstitialAdActivity.Type;
import com.facebook.ads.NativeAdVideoActivity;
import com.facebook.ads.internal.adapters.e;
import com.facebook.ads.internal.util.o;
import com.facebook.ads.internal.util.u;
import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.games.GamesStatusCodes;
import java.util.HashMap;
import java.util.Map;

public class a extends FrameLayout {
    private com.facebook.ads.internal.view.video.support.a a;
    private e b;
    private c c;
    private String d;
    private String e;
    private String f;
    private boolean g;
    private boolean h;
    private int i;
    private Handler j;
    private Handler k;
    private Runnable l;
    private Runnable m;
    private float n;

    class 1 implements com.facebook.ads.internal.view.video.support.b {
        final /* synthetic */ a a;
        final /* synthetic */ a b;

        1(a aVar, a aVar2) {
            this.b = aVar;
            this.a = aVar2;
        }

        public void a(MediaPlayer mediaPlayer) {
            try {
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = new MediaPlayer();
                }
                mediaPlayer.setVolume(this.b.getVolume(), this.b.getVolume());
                mediaPlayer.setLooping(false);
                if (this.a.getAutoplay()) {
                    this.a.c();
                } else {
                    this.a.d();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    class 2 extends com.facebook.ads.internal.adapters.e.a {
        final /* synthetic */ a a;
        final /* synthetic */ a b;

        2(a aVar, a aVar2) {
            this.b = aVar;
            this.a = aVar2;
        }

        public void a() {
            if (this.a.getAutoplay()) {
                this.b.c();
            } else {
                this.b.d();
            }
        }

        public void b() {
            this.b.d();
        }
    }

    class 3 implements OnTouchListener {
        final /* synthetic */ a a;
        final /* synthetic */ a b;

        3(a aVar, a aVar2) {
            this.b = aVar;
            this.a = aVar2;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1 || this.a.getVideoURI() == null) {
                return false;
            }
            this.b.g();
            return true;
        }
    }

    class 4 implements OnClickListener {
        final /* synthetic */ a a;
        final /* synthetic */ a b;

        4(a aVar, a aVar2) {
            this.b = aVar;
            this.a = aVar2;
        }

        public void onClick(View view) {
            if (this.a.getVideoURI() != null) {
                this.b.g();
            }
        }
    }

    class 5 implements OnTouchListener {
        final /* synthetic */ a a;

        5(a aVar) {
            this.a = aVar;
        }

        public boolean onTouch(View view, MotionEvent motionEvent) {
            if (motionEvent.getAction() != 1) {
                return false;
            }
            if (this.a.c.c()) {
                this.a.c();
                return true;
            }
            this.a.d();
            return true;
        }
    }

    class 6 implements OnClickListener {
        final /* synthetic */ a a;

        6(a aVar) {
            this.a = aVar;
        }

        public void onClick(View view) {
            if (this.a.c.c()) {
                this.a.c();
            } else {
                this.a.d();
            }
        }
    }

    private static final class a extends u<a> {
        public a(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null) {
                if (aVar.a.getCurrentPosition() > GamesStatusCodes.STATUS_ACHIEVEMENT_UNLOCK_FAILURE) {
                    new o().execute(new String[]{aVar.getVideoPlayReportURI()});
                    return;
                }
                aVar.j.postDelayed(this, 250);
            }
        }
    }

    private static final class b extends u<a> {
        public b(a aVar) {
            super(aVar);
        }

        public void run() {
            a aVar = (a) a();
            if (aVar != null && aVar != null) {
                int currentPosition = aVar.a.getCurrentPosition();
                if (currentPosition > aVar.i) {
                    aVar.i = currentPosition;
                }
                aVar.k.postDelayed(this, 250);
            }
        }
    }

    class c extends Button {
        final /* synthetic */ a a;
        private Paint b;
        private Path c;
        private Path d;
        private Path e;
        private int f;
        private boolean g;

        class 1 extends Paint {
            final /* synthetic */ c a;

            1(c cVar) {
                this.a = cVar;
                setStyle(Style.FILL_AND_STROKE);
                setStrokeCap(Cap.ROUND);
                setStrokeWidth(3.0f);
                setAntiAlias(true);
                setColor(-1);
            }
        }

        public c(a aVar, Context context) {
            this.a = aVar;
            super(context);
            a();
        }

        private void a() {
            this.f = 60;
            this.c = new Path();
            this.d = new Path();
            this.e = new Path();
            this.b = new 1(this);
            b();
            setClickable(true);
            setBackgroundColor(0);
        }

        private void a(boolean z) {
            this.g = z;
            refreshDrawableState();
            invalidate();
        }

        private void b() {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            LayoutParams layoutParams = new RelativeLayout.LayoutParams((int) (((float) this.f) * displayMetrics.density), (int) (displayMetrics.density * ((float) this.f)));
            layoutParams.addRule(9);
            layoutParams.addRule(12);
            setLayoutParams(layoutParams);
        }

        private boolean c() {
            return this.g;
        }

        protected void onDraw(@NonNull Canvas canvas) {
            if (canvas.isHardwareAccelerated() && VERSION.SDK_INT < 17) {
                setLayerType(1, null);
            }
            float max = ((float) Math.max(canvas.getWidth(), canvas.getHeight())) / 100.0f;
            if (c()) {
                this.e.rewind();
                this.e.moveTo(26.5f * max, 15.5f * max);
                this.e.lineTo(26.5f * max, 84.5f * max);
                this.e.lineTo(82.5f * max, 50.5f * max);
                this.e.lineTo(26.5f * max, max * 15.5f);
                this.e.close();
                canvas.drawPath(this.e, this.b);
            } else {
                this.c.rewind();
                this.c.moveTo(29.0f * max, 21.0f * max);
                this.c.lineTo(29.0f * max, 79.0f * max);
                this.c.lineTo(45.0f * max, 79.0f * max);
                this.c.lineTo(45.0f * max, 21.0f * max);
                this.c.lineTo(29.0f * max, 21.0f * max);
                this.c.close();
                this.d.rewind();
                this.d.moveTo(55.0f * max, 21.0f * max);
                this.d.lineTo(55.0f * max, 79.0f * max);
                this.d.lineTo(71.0f * max, 79.0f * max);
                this.d.lineTo(71.0f * max, 21.0f * max);
                this.d.lineTo(55.0f * max, max * 21.0f);
                this.d.close();
                canvas.drawPath(this.c, this.b);
                canvas.drawPath(this.d, this.b);
            }
            super.onDraw(canvas);
        }
    }

    public a(Context context) {
        super(context);
        e();
    }

    private void a(Context context, Intent intent) {
        Display defaultDisplay = ((WindowManager) context.getSystemService("window")).getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        intent.putExtra("displayRotation", defaultDisplay.getRotation());
        intent.putExtra("displayWidth", displayMetrics.widthPixels);
        intent.putExtra("displayHeight", displayMetrics.heightPixels);
        intent.putExtra("useNativeCloseButton", true);
        intent.putExtra(InterstitialAdActivity.VIEW_TYPE, Type.VIDEO);
        intent.putExtra(InterstitialAdActivity.VIDEO_URL, getVideoURI());
        intent.putExtra(InterstitialAdActivity.VIDEO_PLAY_REPORT_URL, getVideoPlayReportURI());
        intent.putExtra(InterstitialAdActivity.VIDEO_TIME_REPORT_URL, getVideoTimeReportURI());
        intent.putExtra(InterstitialAdActivity.PREDEFINED_ORIENTATION_KEY, 13);
        intent.addFlags(DriveFile.MODE_READ_ONLY);
    }

    private boolean a(Class<?> cls) {
        try {
            Context context = getContext();
            Intent intent = new Intent(context, cls);
            a(context, intent);
            context.startActivity(intent);
            return true;
        } catch (Throwable e) {
            com.facebook.ads.internal.util.c.a(com.facebook.ads.internal.util.b.a(e, "Error occurred while loading fullscreen video activity."));
            return false;
        }
    }

    private void e() {
        this.n = 0.0f;
        View relativeLayout = new RelativeLayout(getContext());
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        relativeLayout.setGravity(17);
        relativeLayout.setLayoutParams(layoutParams);
        setBackgroundColor(0);
        Context context = getContext();
        this.a = new com.facebook.ads.internal.view.video.support.a(context);
        this.a.setBackgroundColor(0);
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(10, -1);
        layoutParams2.addRule(12, -1);
        layoutParams2.addRule(11, -1);
        layoutParams2.addRule(9, -1);
        this.a.setLayoutParams(layoutParams2);
        this.a.setFrameVideoViewListener(new 1(this, this));
        relativeLayout.addView(this.a);
        addView(relativeLayout);
        this.k = new Handler();
        this.l = new b(this);
        this.k.postDelayed(this.l, 250);
        this.j = new Handler();
        this.m = new a(this);
        this.j.postDelayed(this.m, 250);
        this.b = new e(context, this, 50, true, new 2(this, this));
        this.b.a(0);
        this.b.b(250);
        this.b.a();
        setOnTouchListenerInternal(new 3(this, this));
        setOnClickListenerInternal(new 4(this, this));
        this.c = new c(this, context);
        this.c.setLayoutParams(new FrameLayout.LayoutParams(100, 100, 80));
        this.c.setOnTouchListener(new 5(this));
        this.c.setOnClickListener(new 6(this));
        addView(this.c);
    }

    private void f() {
        if (!this.h && getVideoTimeReportURI() != null) {
            Map hashMap = new HashMap();
            hashMap.put("time", Integer.toString(this.i / AdError.NETWORK_ERROR_CODE));
            hashMap.put("inline", AppEventsConstants.EVENT_PARAM_VALUE_YES);
            new o(hashMap).execute(new String[]{getVideoTimeReportURI()});
            this.h = true;
            this.i = 0;
        }
    }

    private void g() {
        if (!a(NativeAdVideoActivity.class)) {
            a(InterstitialAdActivity.class);
        }
    }

    private void setOnClickListenerInternal(OnClickListener onClickListener) {
        super.setOnClickListener(onClickListener);
    }

    private void setOnTouchListenerInternal(OnTouchListener onTouchListener) {
        super.setOnTouchListener(onTouchListener);
    }

    public void a() {
        if (this.i > 0) {
            f();
            this.i = 0;
        }
    }

    public void b() {
        this.d = null;
    }

    public void c() {
        this.c.a(false);
        this.a.a();
    }

    public void d() {
        this.c.a(true);
        this.a.b();
    }

    public boolean getAutoplay() {
        return this.g;
    }

    public String getVideoPlayReportURI() {
        return this.e;
    }

    public String getVideoTimeReportURI() {
        return this.f;
    }

    public String getVideoURI() {
        return this.d;
    }

    float getVolume() {
        return this.n;
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b.a();
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        f();
        this.b.b();
    }

    public void setAutoplay(boolean z) {
        this.g = z;
    }

    public void setOnClickListener(OnClickListener onClickListener) {
    }

    public void setOnTouchListener(OnTouchListener onTouchListener) {
    }

    public void setVideoPlayReportURI(String str) {
        this.e = str;
    }

    public void setVideoTimeReportURI(String str) {
        a();
        this.f = str;
    }

    public void setVideoURI(String str) {
        this.d = str;
        if (str != null) {
            this.a.setup(Uri.parse(str));
            if (this.g) {
                this.a.a();
            }
        }
    }

    void setVolume(float f) {
        this.n = f;
    }
}
