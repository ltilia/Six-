package com.vungle.publisher;

import java.lang.reflect.Array;

/* compiled from: vungle */
public final class jf {
    public static <T> T[] a(T[]... tArr) {
        Class cls = null;
        int i = 0;
        for (int i2 = 0; i2 < 2; i2++) {
            Object obj = tArr[i2];
            if (obj != null) {
                i += obj.length;
                cls = obj.getClass();
            }
        }
        if (cls == null) {
            return null;
        }
        Object[] objArr = (Object[]) Array.newInstance(cls.getComponentType(), i);
        i = 0;
        for (int i3 = 0; i3 < 2; i3++) {
            Object obj2 = tArr[i3];
            if (obj2 != null) {
                System.arraycopy(obj2, 0, objArr, i, obj2.length);
                i += obj2.length;
            }
        }
        return objArr;
    }
}
