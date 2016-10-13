package com.facebook.ads;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.facebook.ads.internal.g;
import com.facebook.ads.internal.view.f;
import com.facebook.ads.internal.view.h;
import com.facebook.ads.internal.view.h.a;
import com.facebook.ads.internal.view.m;
import com.mopub.mobileads.resource.DrawableConstants.CtaButton;
import org.json.simple.parser.Yytoken;

public class InterstitialAdActivity extends Activity {
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String VIDEO_PLAY_REPORT_URL = "videoPlayReportURL";
    public static final String VIDEO_TIME_REPORT_URL = "videoTimeReportURL";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    private RelativeLayout a;
    private g b;
    private int c;
    private int d;
    private int e;
    private boolean f;
    private String g;
    private h h;
    private int i;

    class 1 implements OnClickListener {
        final /* synthetic */ InterstitialAdActivity a;

        1(InterstitialAdActivity interstitialAdActivity) {
            this.a = interstitialAdActivity;
        }

        public void onClick(View view) {
            this.a.finish();
        }
    }

    class 2 implements a {
        final /* synthetic */ InterstitialAdActivity a;

        2(InterstitialAdActivity interstitialAdActivity) {
            this.a = interstitialAdActivity;
        }

        public void a(View view) {
            this.a.a.addView(view);
            if (this.a.b != null) {
                this.a.a.addView(this.a.b);
            }
        }

        public void a(String str) {
            this.a.a(str);
        }
    }

    class 3 implements a {
        final /* synthetic */ InterstitialAdActivity a;

        3(InterstitialAdActivity interstitialAdActivity) {
            this.a = interstitialAdActivity;
        }

        public void a(View view) {
            this.a.a.addView(view);
            if (this.a.b != null) {
                this.a.a.addView(this.a.b);
            }
        }

        public void a(String str) {
            this.a.a(str);
        }
    }

    static /* synthetic */ class 4 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[Type.values().length];
            try {
                a[Type.VIDEO.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[Type.DISPLAY.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public enum Type {
        DISPLAY,
        VIDEO
    }

    public InterstitialAdActivity() {
        this.f = false;
        this.i = -1;
    }

    private void a(int i, int i2) {
        int i3 = i2 >= i ? 1 : 0;
        int rotation = ((WindowManager) getSystemService("window")).getDefaultDisplay().getRotation();
        if (i3 != 0) {
            switch (rotation) {
                case Yytoken.TYPE_LEFT_BRACE /*1*/:
                case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                    setRequestedOrientation(9);
                    return;
                default:
                    setRequestedOrientation(1);
                    return;
            }
        }
        switch (rotation) {
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                setRequestedOrientation(8);
            default:
                setRequestedOrientation(0);
        }
    }

    private void a(Intent intent, Bundle bundle) {
        if (bundle != null) {
            this.c = bundle.getInt("lastRequestedOrientation", -1);
            this.g = bundle.getString("adInterstitialUniqueId");
            this.h.a(intent, bundle);
            this.f = true;
            return;
        }
        this.d = intent.getIntExtra("displayWidth", 0);
        this.e = intent.getIntExtra("displayHeight", 0);
        this.g = intent.getStringExtra("adInterstitialUniqueId");
        this.h.a(intent, bundle);
    }

    private void a(String str) {
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(str + ":" + this.g));
    }

    public void finish() {
        this.a.removeAllViews();
        this.h.c();
        a("com.facebook.ads.interstitial.dismissed");
        super.finish();
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        requestWindowFeature(1);
        getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        this.a = new RelativeLayout(this);
        this.a.setBackgroundColor(CtaButton.BACKGROUND_COLOR);
        setContentView(this.a, new LayoutParams(-1, -1));
        Intent intent = getIntent();
        if (intent.getBooleanExtra("useNativeCloseButton", false)) {
            this.b = new g(this);
            this.b.setId(100002);
            this.b.setOnClickListener(new 1(this));
        }
        switch (4.a[((Type) intent.getSerializableExtra(VIEW_TYPE)).ordinal()]) {
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                this.h = new m(this, new 2(this));
                break;
            default:
                this.h = new f(this, new 3(this));
                break;
        }
        this.i = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
        a(intent, bundle);
        a("com.facebook.ads.interstitial.displayed");
    }

    public void onPause() {
        super.onPause();
        this.h.a();
    }

    public void onRestart() {
        super.onRestart();
        this.f = true;
    }

    public void onResume() {
        super.onResume();
        this.h.b();
    }

    public void onSaveInstanceState(@NonNull Bundle bundle) {
        super.onSaveInstanceState(bundle);
        this.h.a(bundle);
        bundle.putInt("lastRequestedOrientation", this.c);
        bundle.putString("adInterstitialUniqueId", this.g);
    }

    public void onStart() {
        super.onStart();
        if (this.i != -1) {
            setRequestedOrientation(this.i);
        } else if (!(this.d == 0 || this.e == 0)) {
            if (!this.f) {
                a(this.d, this.e);
            } else if (this.c >= 0) {
                setRequestedOrientation(this.c);
                this.c = -1;
            }
        }
        this.f = false;
    }

    public void setRequestedOrientation(int i) {
        this.c = i;
        super.setRequestedOrientation(i);
    }
}
