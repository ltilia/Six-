package com.chartboost.sdk;

import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import com.chartboost.sdk.Libraries.CBLogging;
import com.chartboost.sdk.Libraries.CBUtility;
import com.chartboost.sdk.Libraries.k;
import com.chartboost.sdk.impl.az;

public class CBImpressionActivity extends Activity {
    protected static final String a;
    private Activity b;
    private PhoneStateListener c;

    class 1 extends PhoneStateListener {
        final /* synthetic */ CBImpressionActivity a;

        1(CBImpressionActivity cBImpressionActivity) {
            this.a = cBImpressionActivity;
        }

        public void onCallStateChanged(int state, String incomingNumber) {
            if (state == 1) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: Ringing");
                CBLogging.a(CBImpressionActivity.a, "##### Pausing the impression");
                this.a.onPause();
            } else if (state == 0) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: Idle");
                CBLogging.a(CBImpressionActivity.a, "##### Resuming the impression");
                this.a.onResume();
            } else if (state == 2) {
                CBLogging.a(CBImpressionActivity.a, "##### Phone call State: OffHook");
                CBLogging.a(CBImpressionActivity.a, "##### Pausing the impression");
                this.a.onPause();
            }
            super.onCallStateChanged(state, incomingNumber);
        }
    }

    static class 2 implements Runnable {
        final /* synthetic */ Activity a;

        2(Activity activity) {
            this.a = activity;
        }

        public void run() {
            CBLogging.e("VideoInit", "preparing activity for video surface");
            this.a.addContentView(new SurfaceView(this.a), new LayoutParams(0, 0));
        }
    }

    public CBImpressionActivity() {
        this.b = null;
        this.c = new 1(this);
    }

    static {
        a = CBImpressionActivity.class.getSimpleName();
    }

    public boolean dispatchTouchEvent(MotionEvent event) {
        if (this.b != null) {
            return this.b.dispatchTouchEvent(event);
        }
        return super.dispatchTouchEvent(event);
    }

    public void forwardTouchEvents(Activity host) {
        this.b = host;
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(ViewCompat.MEASURED_STATE_TOO_SMALL, ViewCompat.MEASURED_STATE_TOO_SMALL);
        requestWindowFeature(1);
        getWindow().setWindowAnimations(0);
        Chartboost.a(this);
        setContentView(new RelativeLayout(this));
        a(this);
        az.a((Context) this).d();
        CBLogging.a(CBImpressionActivity.class.getName(), "Impression Activity onCreate() called");
    }

    protected void onStart() {
        super.onStart();
        if (!Chartboost.i()) {
            Chartboost.a((Activity) this);
        }
        try {
            TelephonyManager telephonyManager = (TelephonyManager) getSystemService("phone");
            if (telephonyManager != null) {
                telephonyManager.listen(this.c, 32);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onResume() {
        super.onResume();
        if (!Chartboost.i()) {
            Chartboost.a(k.a((Activity) this));
        }
        CBUtility.a((Activity) this);
    }

    protected void onPause() {
        super.onPause();
        if (!Chartboost.i()) {
            Chartboost.b(k.a((Activity) this));
        }
    }

    protected void onStop() {
        super.onStop();
        if (!Chartboost.i()) {
            Chartboost.c(k.a((Activity) this));
        }
        try {
            ((TelephonyManager) getSystemService("phone")).listen(this.c, 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        if (!Chartboost.i()) {
            Chartboost.b((Activity) this);
        }
    }

    public void onBackPressed() {
        if (!Chartboost.d()) {
            super.onBackPressed();
        }
    }

    protected static void a(Activity activity) {
        if (VERSION.SDK_INT < 14) {
            CBUtility.e().post(new 2(activity));
        }
    }
}
