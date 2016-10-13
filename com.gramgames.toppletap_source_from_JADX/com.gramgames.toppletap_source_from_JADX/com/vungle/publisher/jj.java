package com.vungle.publisher;

import java.util.Arrays;

/* compiled from: vungle */
public final class jj {
    private static final char[] a;

    static {
        a = "0123456789abcdef".toCharArray();
    }

    public static <T> String a(T... tArr) {
        return a(", ", tArr == null ? null : Arrays.asList(tArr));
    }

    public static String a(Iterable<?> iterable) {
        return a(", ", iterable);
    }

    public static String a(String str, Iterable<?> iterable) {
        if (iterable == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        Object obj = 1;
        for (Object next : iterable) {
            if (obj != null) {
                obj = null;
            } else {
                stringBuilder.append(str);
            }
            stringBuilder.append(next);
        }
        return stringBuilder.toString();
    }

    public static String a(Enum<?> enumR) {
        return enumR == null ? null : enumR.name();
    }

    public static String b(Object[] objArr) {
        return "[" + a(objArr) + "]";
    }
}
