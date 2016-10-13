package com.gramgames.activity;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import java.util.ArrayList;

public class ActivityProxyManager {
    private static final String TAG = "[GG-ActivityProxy]";
    private final Activity m_activity;
    private ArrayList m_proxyClasses;

    public ActivityProxyManager(Activity activity) {
        this.m_proxyClasses = new ArrayList();
        this.m_activity = activity;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void init() {
        /*
        r13 = this;
        r9 = r13.m_activity;	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r9 = r9.getPackageManager();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r10 = r13.m_activity;	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r10 = r10.getPackageName();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r11 = 128; // 0x80 float:1.794E-43 double:6.32E-322;
        r1 = r9.getApplicationInfo(r10, r11);	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r3 = r1.metaData;	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r4 = r3.keySet();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r9 = r4.iterator();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
    L_0x001c:
        r10 = r9.hasNext();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        if (r10 == 0) goto L_0x0088;
    L_0x0022:
        r8 = r9.next();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r8 = (java.lang.String) r8;	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r6 = r3.get(r8);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        if (r6 == 0) goto L_0x001c;
    L_0x002e:
        r10 = r6 instanceof java.lang.String;	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        if (r10 == 0) goto L_0x001c;
    L_0x0032:
        r0 = r6;
        r0 = (java.lang.String) r0;	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r5 = r0;
        r10 = "UnityActivityProxy";
        r10 = r5.equalsIgnoreCase(r10);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        if (r10 == 0) goto L_0x001c;
    L_0x003e:
        r7 = java.lang.Class.forName(r8);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r10 = "[GG-ActivityProxy]";
        r11 = new java.lang.StringBuilder;	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r11.<init>();	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r12 = "Proxy class found in meta-data: ";
        r11 = r11.append(r12);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r11 = r11.append(r8);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r11 = r11.toString();	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        android.util.Log.d(r10, r11);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r10 = r13.m_proxyClasses;	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        r10.add(r7);	 Catch:{ ClassNotFoundException -> 0x0060, Exception -> 0x0089, NameNotFoundException -> 0x007d }
        goto L_0x001c;
    L_0x0060:
        r2 = move-exception;
        r10 = "[GG-ActivityProxy]";
        r11 = new java.lang.StringBuilder;	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r11.<init>();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r12 = "Proxy class not found for entry: ";
        r11 = r11.append(r12);	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r11 = r11.append(r8);	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r11 = r11.toString();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        android.util.Log.d(r10, r11);	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r2.printStackTrace();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        goto L_0x001c;
    L_0x007d:
        r2 = move-exception;
        r9 = "[GG-ActivityProxy]";
        r10 = "Package name not found.";
        android.util.Log.d(r9, r10);
        r2.printStackTrace();
    L_0x0088:
        return;
    L_0x0089:
        r2 = move-exception;
        r10 = "[GG-ActivityProxy]";
        r11 = "There is an unexpected error while probing meta data for proxy classes.";
        android.util.Log.d(r10, r11);	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        r2.printStackTrace();	 Catch:{ NameNotFoundException -> 0x007d, Exception -> 0x0095 }
        goto L_0x001c;
    L_0x0095:
        r2 = move-exception;
        r9 = "[GG-ActivityProxy]";
        r10 = "Got error while getting bundle.";
        android.util.Log.d(r9, r10);
        r2.printStackTrace();
        goto L_0x0088;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.gramgames.activity.ActivityProxyManager.init():void");
    }

    public void invokeMethod(String methodName) {
        int len = this.m_proxyClasses.size();
        for (int i = 0; i < len; i++) {
            Class classObject = (Class) this.m_proxyClasses.get(i);
            try {
                classObject.getMethod(methodName, new Class[0]).invoke(null, new Object[0]);
            } catch (NoSuchMethodException e) {
                Log.d(TAG, "There is not found any method called: " + methodName + " in class " + classObject.getName());
            } catch (Exception e2) {
                Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                e2.printStackTrace();
            }
        }
    }

    public void invokeMethodWithKeyEvent(String methodName, int keyCode, KeyEvent event) {
        int len = this.m_proxyClasses.size();
        for (int i = 0; i < len; i++) {
            Class classObject = (Class) this.m_proxyClasses.get(i);
            try {
                classObject.getMethod(methodName, new Class[]{Integer.TYPE, KeyEvent.class}).invoke(null, new Object[]{Integer.valueOf(keyCode), event});
            } catch (NoSuchMethodException e) {
                Log.d(TAG, "There is not found any method called: " + methodName + " in class " + classObject.getName());
            } catch (Exception e2) {
                Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                e2.printStackTrace();
            }
        }
    }

    public void invokeMethodWithInteger(String methodName, int value) {
        int len = this.m_proxyClasses.size();
        for (int i = 0; i < len; i++) {
            Class classObject = (Class) this.m_proxyClasses.get(i);
            try {
                classObject.getMethod(methodName, new Class[]{Integer.TYPE}).invoke(null, new Object[]{Integer.valueOf(value)});
            } catch (NoSuchMethodException e) {
                Log.d(TAG, "There is not found any method called: " + methodName + " in class " + classObject.getName());
            } catch (Exception e2) {
                Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                e2.printStackTrace();
            }
        }
    }

    public void invokeMethodWithBoolean(String methodName, boolean value) {
        int len = this.m_proxyClasses.size();
        for (int i = 0; i < len; i++) {
            Class classObject = (Class) this.m_proxyClasses.get(i);
            try {
                classObject.getMethod(methodName, new Class[]{Boolean.TYPE}).invoke(null, new Object[]{Boolean.valueOf(value)});
            } catch (NoSuchMethodException e) {
                Log.d(TAG, "There is not found any method called: " + methodName + " in class " + classObject.getName());
            } catch (Exception e2) {
                Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                e2.printStackTrace();
            }
        }
    }

    public <T> void invokeMethodWithObject(String methodName, T value) {
        if (value != null) {
            int len = this.m_proxyClasses.size();
            for (int i = 0; i < len; i++) {
                Class classObject = (Class) this.m_proxyClasses.get(i);
                try {
                    classObject.getMethod(methodName, new Class[]{value.getClass()}).invoke(null, new Object[]{value});
                } catch (NoSuchMethodException e) {
                    Log.d(TAG, "There is not found any method called: " + methodName + " in class " + classObject.getName());
                } catch (Exception e2) {
                    Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                    e2.printStackTrace();
                }
            }
        }
    }

    public void invokeMethodForActivityResult(int request, int response, Intent data) {
        int len = this.m_proxyClasses.size();
        for (int i = 0; i < len; i++) {
            Class classObject = (Class) this.m_proxyClasses.get(i);
            try {
                classObject.getMethod("onActivityResult", new Class[]{Integer.TYPE, Integer.TYPE, Intent.class}).invoke(null, new Object[]{Integer.valueOf(request), Integer.valueOf(response), data});
            } catch (NoSuchMethodException e) {
                Log.d(TAG, "There is not found any method called: onActivityResult in class " + classObject.getName());
            } catch (Exception e2) {
                Log.d(TAG, "Error occurred while getting method from class " + classObject.getName());
                e2.printStackTrace();
            }
        }
    }
}
