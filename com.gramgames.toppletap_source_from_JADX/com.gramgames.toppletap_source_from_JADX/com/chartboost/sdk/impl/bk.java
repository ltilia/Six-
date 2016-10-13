package com.chartboost.sdk.impl;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnShowListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.ArrayList;

public class bk {
    private static final String[] a;
    private b b;

    public static abstract class b {
        public abstract void a(bk bkVar, int i);

        public void a(bk bkVar) {
        }
    }

    class 1 implements OnShowListener {
        final /* synthetic */ AlertDialog a;
        final /* synthetic */ int b;
        final /* synthetic */ ArrayList c;
        final /* synthetic */ int d;
        final /* synthetic */ bk e;

        class 1 implements OnClickListener {
            final /* synthetic */ int a;
            final /* synthetic */ 1 b;

            1(1 1, int i) {
                this.b = 1;
                this.a = i;
            }

            public void onClick(View v) {
                if (this.b.e.b != null) {
                    this.b.e.b.a(this.b.e, this.a);
                }
                this.b.a.dismiss();
            }
        }

        1(bk bkVar, AlertDialog alertDialog, int i, ArrayList arrayList, int i2) {
            this.e = bkVar;
            this.a = alertDialog;
            this.b = i;
            this.c = arrayList;
            this.d = i2;
        }

        public void onShow(DialogInterface d) {
            Button[] a = bk.b(this.a);
            for (int i = 0; i < this.b; i++) {
                CharSequence charSequence = (CharSequence) this.c.get(i);
                Button button = a[i];
                if (this.d == i) {
                    button.setTypeface(null, 1);
                }
                button.setText(charSequence);
                button.setOnClickListener(new 1(this, i));
            }
        }
    }

    class 2 implements OnCancelListener {
        final /* synthetic */ bk a;

        2(bk bkVar) {
            this.a = bkVar;
        }

        public void onCancel(DialogInterface arg0) {
            this.a.b.a(this.a);
        }
    }

    public static class a {
        private final Bundle a;

        public a() {
            this.a = new Bundle();
        }

        public a a(String str) {
            this.a.putString("arg:title", str);
            return this;
        }

        public a b(String str) {
            this.a.putString("arg:message", str);
            return this;
        }

        public a c(String str) {
            this.a.putString("arg:left", str);
            return this;
        }

        public a d(String str) {
            this.a.putString("arg:right", str);
            return this;
        }

        public bk a(Context context, b bVar) {
            return new bk(context, this.a, bVar);
        }
    }

    static {
        a = new String[]{"arg:left", "arg:center", "arg:right"};
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public bk(android.content.Context r10, android.os.Bundle r11, com.chartboost.sdk.impl.bk.b r12) {
        /*
        r9 = this;
        r1 = 0;
        r8 = 0;
        r9.<init>();
        r9.b = r12;
        r0 = "arg:title";
        r2 = r11.getString(r0);
        r0 = "arg:message";
        r3 = r11.getString(r0);
        r0 = "arg:default";
        r4 = -1;
        r5 = r11.getInt(r0, r4);
        r4 = new java.util.ArrayList;
        r4.<init>();
        r0 = r1;
    L_0x0020:
        r6 = 3;
        if (r0 >= r6) goto L_0x0037;
    L_0x0023:
        r6 = a;
        r6 = r6[r0];
        r6 = r11.getString(r6);
        r7 = android.text.TextUtils.isEmpty(r6);
        if (r7 != 0) goto L_0x0034;
    L_0x0031:
        r4.add(r6);
    L_0x0034:
        r0 = r0 + 1;
        goto L_0x0020;
    L_0x0037:
        r0 = new android.app.AlertDialog$Builder;
        r0.<init>(r10);
        r0 = r0.setTitle(r2);
        r2 = r0.setMessage(r3);
        r3 = r4.size();
        switch(r3) {
            case 1: goto L_0x0078;
            case 2: goto L_0x006e;
            case 3: goto L_0x0064;
            default: goto L_0x004b;
        };
    L_0x004b:
        r2 = r2.create();
        r0 = new com.chartboost.sdk.impl.bk$1;
        r1 = r9;
        r0.<init>(r1, r2, r3, r4, r5);
        r2.setOnShowListener(r0);
        r0 = new com.chartboost.sdk.impl.bk$2;
        r0.<init>(r9);
        r2.setOnCancelListener(r0);
        r2.show();
        return;
    L_0x0064:
        r0 = 2;
        r0 = r4.get(r0);
        r0 = (java.lang.CharSequence) r0;
        r2.setNegativeButton(r0, r8);
    L_0x006e:
        r0 = 1;
        r0 = r4.get(r0);
        r0 = (java.lang.CharSequence) r0;
        r2.setNeutralButton(r0, r8);
    L_0x0078:
        r0 = r4.get(r1);
        r0 = (java.lang.CharSequence) r0;
        r2.setPositiveButton(r0, r8);
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.impl.bk.<init>(android.content.Context, android.os.Bundle, com.chartboost.sdk.impl.bk$b):void");
    }

    private static final Button[] b(AlertDialog alertDialog) {
        ViewGroup viewGroup = (ViewGroup) alertDialog.getButton(-1).getParent();
        int childCount = viewGroup.getChildCount();
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < childCount; i++) {
            View childAt = viewGroup.getChildAt(i);
            if (childAt.getVisibility() == 0 && (childAt instanceof Button)) {
                arrayList.add((Button) childAt);
            }
        }
        Button[] buttonArr = new Button[arrayList.size()];
        arrayList.toArray(buttonArr);
        return buttonArr;
    }
}
