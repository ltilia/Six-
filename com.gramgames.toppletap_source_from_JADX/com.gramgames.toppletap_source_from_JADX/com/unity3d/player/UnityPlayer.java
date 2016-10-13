package com.unity3d.player;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.NativeActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Size;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.InputEvent;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ProgressBar;
import com.adjust.sdk.Constants;
import com.google.android.gms.drive.FileUploadPreferences;
import com.mopub.mobileads.resource.DrawableConstants.RadialCountdown;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

public class UnityPlayer extends FrameLayout implements a {
    private static Lock D;
    public static Activity currentActivity;
    private static boolean p;
    private ProgressBar A;
    private Runnable B;
    private Runnable C;
    b a;
    s b;
    private boolean c;
    private boolean d;
    private boolean e;
    private final j f;
    private final t g;
    private boolean h;
    private v i;
    private final ConcurrentLinkedQueue j;
    private BroadcastReceiver k;
    private boolean l;
    private ContextWrapper m;
    private SurfaceView n;
    private WindowManager o;
    private boolean q;
    private boolean r;
    private int s;
    private int t;
    private final r u;
    private String v;
    private NetworkInfo w;
    private Bundle x;
    private List y;
    private w z;

    private abstract class c implements Runnable {
        final /* synthetic */ UnityPlayer f;

        private c(UnityPlayer unityPlayer) {
            this.f = unityPlayer;
        }

        public abstract void a();

        public final void run() {
            if (!this.f.isFinishing()) {
                a();
            }
        }
    }

    class 10 extends c {
        final /* synthetic */ boolean a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ UnityPlayer d;

        10(UnityPlayer unityPlayer, boolean z, String str, int i) {
            this.d = unityPlayer;
            this.a = z;
            this.b = str;
            this.c = i;
            super((byte) 0);
        }

        public final void a() {
            if (this.a) {
                this.d.nativeSetInputCanceled(true);
            } else if (this.b != null) {
                this.d.nativeSetInputString(this.b);
            }
            if (this.c == 1) {
                this.d.nativeSoftInputClosed();
            }
        }
    }

    class 11 extends c {
        final /* synthetic */ int a;
        final /* synthetic */ byte[] b;
        final /* synthetic */ Size c;
        final /* synthetic */ a d;
        final /* synthetic */ UnityPlayer e;

        11(UnityPlayer unityPlayer, int i, byte[] bArr, Size size, a aVar) {
            this.e = unityPlayer;
            this.a = i;
            this.b = bArr;
            this.c = size;
            this.d = aVar;
            super((byte) 0);
        }

        public final void a() {
            this.e.nativeVideoFrameCallback(this.a, this.b, this.c.width, this.c.height);
            this.d.a(this.b);
        }
    }

    class 12 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ int b;
        final /* synthetic */ int c;
        final /* synthetic */ int d;
        final /* synthetic */ boolean e;
        final /* synthetic */ int f;
        final /* synthetic */ int g;
        final /* synthetic */ UnityPlayer h;

        12(UnityPlayer unityPlayer, String str, int i, int i2, int i3, boolean z, int i4, int i5) {
            this.h = unityPlayer;
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = z;
            this.f = i4;
            this.g = i5;
        }

        public final void run() {
            if (this.h.z == null) {
                this.h.pause();
                this.h.z = new w(this.h, this.h.m, this.a, this.b, this.c, this.d, this.e, (long) this.f, (long) this.g);
                this.h.addView(this.h.z);
                this.h.z.requestFocus();
                this.h.g.d(this.h.n);
            }
        }
    }

    class 13 implements Callback {
        final /* synthetic */ UnityPlayer a;

        13(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i2, int i3) {
            this.a.a(0, surfaceHolder.getSurface());
        }

        public final void surfaceCreated(SurfaceHolder surfaceHolder) {
            this.a.a(0, surfaceHolder.getSurface());
        }

        public final void surfaceDestroyed(SurfaceHolder surfaceHolder) {
            this.a.a(0, null);
        }
    }

    class 14 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        14(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            if (this.a.z != null) {
                this.a.g.c(this.a.n);
                this.a.removeView(this.a.z);
                this.a.z = null;
                this.a.resume();
            }
        }
    }

    class 15 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        class 1 implements Runnable {
            final /* synthetic */ 15 a;

            1(15 15) {
                this.a = 15;
            }

            public final void run() {
                this.a.a.i.d();
                this.a.a.g();
            }
        }

        15(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            this.a.b(new 1(this));
        }
    }

    class 16 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        16(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            if (this.a.c) {
                this.a.g.d(this.a.n);
            } else {
                this.a.g.c(this.a.n);
            }
        }
    }

    class 17 implements Runnable {
        final /* synthetic */ Semaphore a;
        final /* synthetic */ UnityPlayer b;

        17(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.b = unityPlayer;
            this.a = semaphore;
        }

        public final void run() {
            this.b.f();
            this.a.release();
        }
    }

    class 18 implements Runnable {
        final /* synthetic */ Semaphore a;
        final /* synthetic */ UnityPlayer b;

        18(UnityPlayer unityPlayer, Semaphore semaphore) {
            this.b = unityPlayer;
            this.a = semaphore;
        }

        public final void run() {
            if (this.b.nativePause()) {
                this.b.q = true;
                this.b.f();
                this.a.release(2);
                return;
            }
            this.a.release();
        }
    }

    class 19 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        19(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            this.a.nativeResume();
        }
    }

    class 1 implements OnClickListener {
        final /* synthetic */ UnityPlayer a;

        1(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void onClick(DialogInterface dialogInterface, int i) {
            this.a.b();
        }
    }

    class 20 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ UnityPlayer b;

        20(UnityPlayer unityPlayer, boolean z) {
            this.b = unityPlayer;
            this.a = z;
        }

        public final void run() {
            this.b.nativeFocusChanged(this.a);
        }
    }

    class 2 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        2(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            int l = this.a.nativeActivityIndicatorStyle();
            if (l >= 0) {
                if (this.a.A == null) {
                    this.a.A = new ProgressBar(this.a.m, null, new int[]{16842874, 16843401, 16842873, 16843400}[l]);
                    this.a.A.setIndeterminate(true);
                    this.a.A.setLayoutParams(new LayoutParams(-2, -2, 51));
                    this.a.addView(this.a.A);
                }
                this.a.A.setVisibility(0);
                this.a.bringChildToFront(this.a.A);
            }
        }
    }

    class 3 extends BroadcastReceiver {
        final /* synthetic */ UnityPlayer a;

        public void onReceive(Context context, Intent intent) {
            this.a.b();
        }
    }

    class 4 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        4(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            if (this.a.A != null) {
                this.a.A.setVisibility(8);
                this.a.removeView(this.a.A);
                this.a.A = null;
            }
        }
    }

    class 5 implements Runnable {
        final /* synthetic */ boolean a;
        final /* synthetic */ UnityPlayer b;

        5(UnityPlayer unityPlayer, boolean z) {
            this.b = unityPlayer;
            this.a = z;
        }

        public final void run() {
            q.i.a(this.b, this.a);
        }
    }

    class 6 implements Runnable {
        final /* synthetic */ UnityPlayer a;
        final /* synthetic */ String b;
        final /* synthetic */ int c;
        final /* synthetic */ boolean d;
        final /* synthetic */ boolean e;
        final /* synthetic */ boolean f;
        final /* synthetic */ boolean g;
        final /* synthetic */ String h;
        final /* synthetic */ UnityPlayer i;

        6(UnityPlayer unityPlayer, UnityPlayer unityPlayer2, String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
            this.i = unityPlayer;
            this.a = unityPlayer2;
            this.b = str;
            this.c = i;
            this.d = z;
            this.e = z2;
            this.f = z3;
            this.g = z4;
            this.h = str2;
        }

        public final void run() {
            this.i.b = new s(this.i.m, this.a, this.b, this.c, this.d, this.e, this.f, this.h);
            this.i.b.show();
        }
    }

    class 7 implements Runnable {
        final /* synthetic */ UnityPlayer a;

        7(UnityPlayer unityPlayer) {
            this.a = unityPlayer;
        }

        public final void run() {
            if (this.a.b != null) {
                this.a.b.dismiss();
                this.a.b = null;
            }
        }
    }

    class 8 extends c {
        final /* synthetic */ Runnable a;
        final /* synthetic */ UnityPlayer b;

        8(UnityPlayer unityPlayer, Runnable runnable) {
            this.b = unityPlayer;
            this.a = runnable;
            super((byte) 0);
        }

        public final void a() {
            this.b.b(this.a);
        }
    }

    class 9 implements Runnable {
        final /* synthetic */ String a;
        final /* synthetic */ UnityPlayer b;

        9(UnityPlayer unityPlayer, String str) {
            this.b = unityPlayer;
            this.a = str;
        }

        public final void run() {
            if (this.b.b != null && this.a != null) {
                this.b.b.a(this.a);
            }
        }
    }

    enum a {
        PAUSE,
        RESUME,
        QUIT,
        FOCUS_GAINED,
        FOCUS_LOST
    }

    private class b extends Thread {
        ArrayBlockingQueue a;
        boolean b;
        final /* synthetic */ UnityPlayer c;

        b(UnityPlayer unityPlayer) {
            this.c = unityPlayer;
            this.b = false;
            this.a = new ArrayBlockingQueue(32);
        }

        private void a(a aVar) {
            try {
                this.a.put(aVar);
            } catch (InterruptedException e) {
                interrupt();
            }
        }

        public final void a() {
            a(a.QUIT);
        }

        public final void a(boolean z) {
            a(z ? a.FOCUS_GAINED : a.FOCUS_LOST);
        }

        public final void b() {
            a(a.RESUME);
        }

        public final void c() {
            a(a.PAUSE);
        }

        public final void run() {
            setName("UnityMain");
            while (true) {
                try {
                    a aVar = (a) this.a.take();
                    if (aVar != a.QUIT) {
                        if (aVar == a.RESUME) {
                            this.b = true;
                        } else if (aVar == a.PAUSE) {
                            this.b = false;
                            this.c.executeGLThreadJobs();
                        } else if (aVar == a.FOCUS_LOST && !this.b) {
                            this.c.executeGLThreadJobs();
                        }
                        if (this.b) {
                            do {
                                this.c.executeGLThreadJobs();
                                if (this.a.peek() != null) {
                                    break;
                                } else if (!(this.c.isFinishing() || this.c.nativeRender())) {
                                    this.c.b();
                                }
                            } while (!interrupted());
                        }
                    } else {
                        return;
                    }
                } catch (InterruptedException e) {
                    return;
                }
            }
        }
    }

    static {
        currentActivity = null;
        new u().a();
        p = false;
        p = loadLibraryStatic("main");
        D = new ReentrantLock();
    }

    public UnityPlayer(ContextWrapper contextWrapper) {
        super(contextWrapper);
        this.c = false;
        this.d = false;
        this.e = true;
        this.h = false;
        this.i = new v();
        this.j = new ConcurrentLinkedQueue();
        this.k = null;
        this.l = false;
        this.a = new b(this);
        this.r = true;
        this.s = 0;
        this.t = 0;
        this.v = null;
        this.w = null;
        this.x = new Bundle();
        this.y = new ArrayList();
        this.b = null;
        this.A = null;
        this.B = new 2(this);
        this.C = new 4(this);
        if (contextWrapper instanceof Activity) {
            currentActivity = (Activity) contextWrapper;
        }
        this.g = new t(this);
        this.m = contextWrapper;
        this.f = contextWrapper instanceof Activity ? new p(contextWrapper) : null;
        this.u = new r(contextWrapper, this);
        a();
        if (q.a) {
            q.i.a((View) this);
        }
        setFullscreen(true);
        a(this.m.getApplicationInfo());
        if (v.c()) {
            initJni(contextWrapper);
            nativeFile(this.m.getPackageCodePath());
            j();
            this.n = new SurfaceView(contextWrapper);
            this.n.getHolder().setFormat(2);
            this.n.getHolder().addCallback(new 13(this));
            this.n.setFocusable(true);
            this.n.setFocusableInTouchMode(true);
            this.g.c(this.n);
            this.q = false;
            c();
            nativeInitWWW(WWW.class);
            nativeInitWebRequest(UnityWebRequest.class);
            if (q.e) {
                q.l.a(this, this.m);
            }
            if (q.h) {
                if (currentActivity != null) {
                    q.m.a(currentActivity, new 15(this));
                } else {
                    this.i.d();
                }
            }
            if (q.d) {
                q.k.a(this);
            }
            this.o = (WindowManager) this.m.getSystemService("window");
            k();
            this.a.start();
            return;
        }
        AlertDialog create = new Builder(this.m).setTitle("Failure to initialize!").setPositiveButton("OK", new 1(this)).setMessage("Your hardware does not support this application, sorry!").create();
        create.setCancelable(false);
        create.show();
    }

    public static void UnitySendMessage(String str, String str2, String str3) {
        if (v.c()) {
            nativeUnitySendMessage(str, str2, str3);
        } else {
            m.Log(5, "Native libraries not loaded - dropping message for " + str + "." + str2);
        }
    }

    private static String a(String str) {
        byte[] digest;
        int i = 0;
        try {
            MessageDigest instance = MessageDigest.getInstance(Constants.MD5);
            FileInputStream fileInputStream = new FileInputStream(str);
            long length = new File(str).length();
            fileInputStream.skip(length - Math.min(length, 65558));
            byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
            for (int i2 = 0; i2 != -1; i2 = fileInputStream.read(bArr)) {
                instance.update(bArr, 0, i2);
            }
            digest = instance.digest();
        } catch (FileNotFoundException e) {
            digest = null;
        } catch (IOException e2) {
            digest = null;
        } catch (NoSuchAlgorithmException e3) {
            digest = null;
        }
        if (digest == null) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        while (i < digest.length) {
            stringBuffer.append(Integer.toString((digest[i] & RadialCountdown.PROGRESS_ALPHA) + FileUploadPreferences.BATTERY_USAGE_UNRESTRICTED, 16).substring(1));
            i++;
        }
        return stringBuffer.toString();
    }

    private void a() {
        try {
            File file = new File(this.m.getPackageCodePath(), "assets/bin/Data/settings.xml");
            InputStream fileInputStream = file.exists() ? new FileInputStream(file) : this.m.getAssets().open("bin/Data/settings.xml");
            XmlPullParserFactory newInstance = XmlPullParserFactory.newInstance();
            newInstance.setNamespaceAware(true);
            XmlPullParser newPullParser = newInstance.newPullParser();
            newPullParser.setInput(fileInputStream, null);
            String str = null;
            String str2 = null;
            for (int eventType = newPullParser.getEventType(); eventType != 1; eventType = newPullParser.next()) {
                if (eventType == 2) {
                    str2 = newPullParser.getName();
                    String str3 = str;
                    for (int i = 0; i < newPullParser.getAttributeCount(); i++) {
                        if (newPullParser.getAttributeName(i).equalsIgnoreCase(UnityAdsConstants.UNITY_ADS_ZONE_NAME_KEY)) {
                            str3 = newPullParser.getAttributeValue(i);
                        }
                    }
                    str = str3;
                } else if (eventType == 3) {
                    str2 = null;
                } else if (eventType == 4 && str != null) {
                    if (str2.equalsIgnoreCase("integer")) {
                        this.x.putInt(str, Integer.parseInt(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("string")) {
                        this.x.putString(str, newPullParser.getText());
                    } else if (str2.equalsIgnoreCase("bool")) {
                        this.x.putBoolean(str, Boolean.parseBoolean(newPullParser.getText()));
                    } else if (str2.equalsIgnoreCase("float")) {
                        this.x.putFloat(str, Float.parseFloat(newPullParser.getText()));
                    }
                    str = null;
                }
            }
        } catch (Exception e) {
            m.Log(6, "Unable to locate player settings. " + e.getLocalizedMessage());
            b();
        }
    }

    private void a(int i, Surface surface) {
        if (!this.c) {
            b(0, surface);
        }
    }

    private static void a(ApplicationInfo applicationInfo) {
        if (p && NativeLoader.load(applicationInfo.nativeLibraryDir)) {
            v.a();
        }
    }

    private void a(c cVar) {
        if (!isFinishing()) {
            c((Runnable) cVar);
        }
    }

    static void a(Runnable runnable) {
        new Thread(runnable).start();
    }

    private static String[] a(Context context) {
        String packageName = context.getPackageName();
        Vector vector = new Vector();
        try {
            int i = context.getPackageManager().getPackageInfo(packageName, 0).versionCode;
            if (Environment.getExternalStorageState().equals("mounted")) {
                File file = new File(Environment.getExternalStorageDirectory().toString() + "/Android/obb/" + packageName);
                if (file.exists()) {
                    if (i > 0) {
                        String str = file + File.separator + "main." + i + "." + packageName + ".obb";
                        if (new File(str).isFile()) {
                            vector.add(str);
                        }
                    }
                    if (i > 0) {
                        packageName = file + File.separator + "patch." + i + "." + packageName + ".obb";
                        if (new File(packageName).isFile()) {
                            vector.add(packageName);
                        }
                    }
                }
            }
            String[] strArr = new String[vector.size()];
            vector.toArray(strArr);
            return strArr;
        } catch (NameNotFoundException e) {
            return new String[0];
        }
    }

    private void b() {
        if ((this.m instanceof Activity) && !((Activity) this.m).isFinishing()) {
            ((Activity) this.m).finish();
        }
    }

    private boolean b(int i, Surface surface) {
        if (!v.c()) {
            return false;
        }
        nativeRecreateGfxState(i, surface);
        return true;
    }

    private void c() {
        if (this.m instanceof NativeActivity) {
            boolean a = new o((Activity) this.m).a();
            this.l = a;
            nativeForwardEventsToDalvik(a);
        }
    }

    private void c(Runnable runnable) {
        if (!v.c()) {
            return;
        }
        if (Thread.currentThread() == this.a) {
            runnable.run();
        } else {
            this.j.add(runnable);
        }
    }

    private void d() {
        for (a c : this.y) {
            c.c();
        }
    }

    private void e() {
        for (a aVar : this.y) {
            try {
                aVar.a((a) this);
            } catch (Exception e) {
                m.Log(6, "Unable to initialize camera: " + e.getMessage());
                aVar.c();
            }
        }
    }

    private void f() {
        nativeDone();
    }

    private void g() {
        if (!this.i.f()) {
            return;
        }
        if (this.z != null) {
            this.z.onResume();
            return;
        }
        if (q.d) {
            q.k.c();
        }
        this.i.c(true);
        e();
        this.u.e();
        this.v = null;
        this.w = null;
        if (v.c()) {
            j();
        }
        c(new 19(this));
        this.a.b();
    }

    private static void h() {
        if (v.c()) {
            lockNativeAccess();
            if (NativeLoader.unload()) {
                v.b();
                unlockNativeAccess();
                return;
            }
            unlockNativeAccess();
            throw new UnsatisfiedLinkError("Unable to unload libraries from libmain.so");
        }
    }

    private boolean i() {
        return this.m.getPackageManager().hasSystemFeature("android.hardware.camera") || this.m.getPackageManager().hasSystemFeature("android.hardware.camera.front");
    }

    private final native void initJni(Context context);

    private void j() {
        if (this.x.getBoolean("useObb")) {
            for (String str : a(this.m)) {
                String a = a(str);
                if (this.x.getBoolean(a)) {
                    nativeFile(str);
                }
                this.x.remove(a);
            }
        }
    }

    private void k() {
        if (this.m instanceof Activity) {
            ((Activity) this.m).getWindow().setFlags(AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT, AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT);
        }
    }

    protected static boolean loadLibraryStatic(String str) {
        try {
            System.loadLibrary(str);
            return true;
        } catch (UnsatisfiedLinkError e) {
            m.Log(6, "Unable to find " + str);
            return false;
        } catch (Exception e2) {
            m.Log(6, "Unknown error " + e2);
            return false;
        }
    }

    protected static void lockNativeAccess() {
        D.lock();
    }

    private final native int nativeActivityIndicatorStyle();

    private final native void nativeDone();

    private final native void nativeFile(String str);

    private final native void nativeFocusChanged(boolean z);

    private final native void nativeInitWWW(Class cls);

    private final native void nativeInitWebRequest(Class cls);

    private final native boolean nativeInjectEvent(InputEvent inputEvent);

    private final native boolean nativePause();

    private final native void nativeRecreateGfxState(int i, Surface surface);

    private final native boolean nativeRender();

    private final native void nativeResume();

    private final native void nativeSetExtras(Bundle bundle);

    private final native void nativeSetInputCanceled(boolean z);

    private final native void nativeSetInputString(String str);

    private final native void nativeSetTouchDeltaY(float f);

    private final native void nativeSoftInputClosed();

    private static native void nativeUnitySendMessage(String str, String str2, String str3);

    private final native void nativeVideoFrameCallback(int i, byte[] bArr, int i2, int i3);

    protected static void unlockNativeAccess() {
        D.unlock();
    }

    protected boolean Location_IsServiceEnabledByUser() {
        return this.u.a();
    }

    protected void Location_SetDesiredAccuracy(float f) {
        this.u.b(f);
    }

    protected void Location_SetDistanceFilter(float f) {
        this.u.a(f);
    }

    protected void Location_StartUpdatingLocation() {
        this.u.b();
    }

    protected void Location_StopUpdatingLocation() {
        this.u.c();
    }

    final void b(Runnable runnable) {
        if (this.m instanceof Activity) {
            ((Activity) this.m).runOnUiThread(runnable);
        } else {
            m.Log(5, "Not running Unity from an Activity; ignored...");
        }
    }

    protected void closeCamera(int i) {
        for (a aVar : this.y) {
            if (aVar.a() == i) {
                aVar.c();
                this.y.remove(aVar);
                return;
            }
        }
    }

    public void configurationChanged(Configuration configuration) {
        if (this.n instanceof SurfaceView) {
            this.n.getHolder().setSizeFromLayout();
        }
        if (this.z != null) {
            this.z.updateVideoLayout();
        }
    }

    protected void disableLogger() {
        m.a = true;
    }

    public boolean displayChanged(int i, Surface surface) {
        if (i == 0) {
            this.c = surface != null;
            b(new 16(this));
        }
        return b(i, surface);
    }

    protected void executeGLThreadJobs() {
        while (true) {
            Runnable runnable = (Runnable) this.j.poll();
            if (runnable != null) {
                runnable.run();
            } else {
                return;
            }
        }
    }

    protected void forwardMotionEventToDalvik(long j, long j2, int i, int i2, int[] iArr, float[] fArr, int i3, float f, float f2, int i4, int i5, int i6, int i7, int i8, long[] jArr, float[] fArr2) {
        this.f.a(j, j2, i, i2, iArr, fArr, i3, f, f2, i4, i5, i6, i7, i8, jArr, fArr2);
    }

    protected int getCameraOrientation(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.orientation;
    }

    protected int getNumCameras() {
        return !i() ? 0 : Camera.getNumberOfCameras();
    }

    public Bundle getSettings() {
        return this.x;
    }

    protected int getSplashMode() {
        return this.x.getInt("splash_mode");
    }

    public View getView() {
        return this;
    }

    protected void hideSoftInput() {
        Runnable 7 = new 7(this);
        if (q.g) {
            a(new 8(this, 7));
        } else {
            b(7);
        }
    }

    protected void hideVideoPlayer() {
        b(new 14(this));
    }

    public void init(int i, boolean z) {
    }

    protected int[] initCamera(int i, int i2, int i3, int i4) {
        a aVar = new a(i, i2, i3, i4);
        try {
            aVar.a((a) this);
            this.y.add(aVar);
            Size b = aVar.b();
            return new int[]{b.width, b.height};
        } catch (Exception e) {
            m.Log(6, "Unable to initialize camera: " + e.getMessage());
            aVar.c();
            return null;
        }
    }

    public boolean injectEvent(InputEvent inputEvent) {
        return nativeInjectEvent(inputEvent);
    }

    protected boolean installPresentationDisplay(int i) {
        return q.e ? q.l.a(this, this.m, i) : false;
    }

    protected boolean isCameraFrontFacing(int i) {
        CameraInfo cameraInfo = new CameraInfo();
        Camera.getCameraInfo(i, cameraInfo);
        return cameraInfo.facing == 1;
    }

    protected boolean isFinishing() {
        if (!this.q) {
            boolean z = (this.m instanceof Activity) && ((Activity) this.m).isFinishing();
            this.q = z;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    protected void kill() {
        Process.killProcess(Process.myPid());
    }

    protected boolean loadLibrary(String str) {
        return loadLibraryStatic(str);
    }

    protected final native void nativeAddVSyncTime(long j);

    final native void nativeForwardEventsToDalvik(boolean z);

    protected native void nativeSetLocation(float f, float f2, float f3, float f4, double d, float f5);

    protected native void nativeSetLocationStatus(int i);

    public void onCameraFrame(a aVar, byte[] bArr) {
        a(new 11(this, aVar.a(), bArr, aVar.b(), aVar));
    }

    public boolean onGenericMotionEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyMultiple(int i, int i2, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onKeyUp(int i, KeyEvent keyEvent) {
        return injectEvent(keyEvent);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        return injectEvent(motionEvent);
    }

    public void pause() {
        if (this.z != null) {
            this.z.onPause();
            return;
        }
        reportSoftInputStr(null, 1, true);
        if (this.i.g()) {
            if (v.c()) {
                Semaphore semaphore = new Semaphore(0);
                if (isFinishing()) {
                    c(new 17(this, semaphore));
                } else {
                    c(new 18(this, semaphore));
                }
                try {
                    if (!semaphore.tryAcquire(4, TimeUnit.SECONDS)) {
                        m.Log(5, "Timeout while trying to pause the Unity Engine.");
                    }
                } catch (InterruptedException e) {
                    m.Log(5, "UI thread got interrupted while trying to pause the Unity Engine.");
                }
                if (semaphore.drainPermits() > 0) {
                    quit();
                }
            }
            this.i.c(false);
            this.i.b(true);
            d();
            this.a.c();
            this.u.d();
            if (q.d) {
                q.k.b();
            }
        }
    }

    public void quit() {
        this.q = true;
        if (!this.i.e()) {
            pause();
        }
        this.a.a();
        try {
            this.a.join(4000);
        } catch (InterruptedException e) {
            this.a.interrupt();
        }
        if (this.k != null) {
            this.m.unregisterReceiver(this.k);
        }
        this.k = null;
        if (v.c()) {
            removeAllViews();
        }
        if (q.e) {
            q.l.a(this.m);
        }
        if (q.d) {
            q.k.a();
        }
        kill();
        h();
    }

    protected void reportSoftInputStr(String str, int i, boolean z) {
        if (i == 1) {
            hideSoftInput();
        }
        a(new 10(this, z, str, i));
    }

    public void resume() {
        if (q.a) {
            q.i.b(this);
        }
        this.i.b(false);
        g();
    }

    protected void setFullscreen(boolean z) {
        this.e = z;
        if (q.a) {
            b(new 5(this, z));
        }
    }

    protected void setSoftInputStr(String str) {
        b(new 9(this, str));
    }

    protected void showSoftInput(String str, int i, boolean z, boolean z2, boolean z3, boolean z4, String str2) {
        b(new 6(this, this, str, i, z, z2, z3, z4, str2));
    }

    protected void showVideoPlayer(String str, int i, int i2, int i3, boolean z, int i4, int i5) {
        b(new 12(this, str, i, i2, i3, z, i4, i5));
    }

    protected void startActivityIndicator() {
        b(this.B);
    }

    protected void stopActivityIndicator() {
        b(this.C);
    }

    public void windowFocusChanged(boolean z) {
        this.i.a(z);
        if (z && this.b != null) {
            reportSoftInputStr(null, 1, false);
        }
        if (q.a && z) {
            q.i.b(this);
        }
        c(new 20(this, z));
        this.a.a(z);
        g();
    }
}
