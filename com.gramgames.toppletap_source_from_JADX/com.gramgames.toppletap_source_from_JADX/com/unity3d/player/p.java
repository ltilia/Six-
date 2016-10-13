package com.unity3d.player;

import android.app.Activity;
import android.content.ContextWrapper;
import android.view.MotionEvent;
import android.view.MotionEvent.PointerCoords;
import android.view.View;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.simple.parser.Yytoken;

public final class p implements j {
    private final Queue a;
    private final Activity b;
    private Runnable c;

    class 1 implements Runnable {
        final /* synthetic */ p a;

        1(p pVar) {
            this.a = pVar;
        }

        private static void a(View view, MotionEvent motionEvent) {
            if (q.b) {
                q.j.a(view, motionEvent);
            }
        }

        public final void run() {
            while (true) {
                MotionEvent motionEvent = (MotionEvent) this.a.a.poll();
                if (motionEvent != null) {
                    View decorView = this.a.b.getWindow().getDecorView();
                    int source = motionEvent.getSource();
                    if ((source & 2) != 0) {
                        switch (motionEvent.getAction() & RadialCountdown.PROGRESS_ALPHA) {
                            case Yylex.YYINITIAL /*0*/:
                            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                            case Yytoken.TYPE_COMMA /*5*/:
                            case Yytoken.TYPE_COLON /*6*/:
                                decorView.dispatchTouchEvent(motionEvent);
                                break;
                            default:
                                a(decorView, motionEvent);
                                break;
                        }
                    } else if ((source & 4) != 0) {
                        decorView.dispatchTrackballEvent(motionEvent);
                    } else {
                        a(decorView, motionEvent);
                    }
                } else {
                    return;
                }
            }
        }
    }

    public p(ContextWrapper contextWrapper) {
        this.a = new ConcurrentLinkedQueue();
        this.c = new 1(this);
        this.b = (Activity) contextWrapper;
    }

    private static int a(PointerCoords[] pointerCoordsArr, float[] fArr, int i) {
        for (int i2 = 0; i2 < pointerCoordsArr.length; i2++) {
            PointerCoords pointerCoords = new PointerCoords();
            pointerCoordsArr[i2] = pointerCoords;
            int i3 = i + 1;
            pointerCoords.orientation = fArr[i];
            int i4 = i3 + 1;
            pointerCoords.pressure = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.size = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.toolMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.toolMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.touchMajor = fArr[i3];
            i3 = i4 + 1;
            pointerCoords.touchMinor = fArr[i4];
            i4 = i3 + 1;
            pointerCoords.x = fArr[i3];
            i = i4 + 1;
            pointerCoords.y = fArr[i4];
        }
        return i;
    }

    private static PointerCoords[] a(int i, float[] fArr) {
        PointerCoords[] pointerCoordsArr = new PointerCoords[i];
        a(pointerCoordsArr, fArr, 0);
        return pointerCoordsArr;
    }

    public final void a(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        if (this.b != null) {
            MotionEvent obtain = MotionEvent.obtain(j, j2, i, i2, iArr, a(i2, fArr), i3, f, f2, i4, i5, i6, i7);
            int i9 = 0;
            for (int i10 = 0; i10 < i8; i10++) {
                PointerCoords[] pointerCoordsArr = new PointerCoords[i2];
                i9 = a(pointerCoordsArr, fArr2, i9);
                obtain.addBatch(jArr[i10], pointerCoordsArr, i3);
            }
            this.a.add(obtain);
            this.b.runOnUiThread(this.c);
        }
    }
}
