package com.vungle.publisher;

import com.vungle.log.Logger;
import java.io.File;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* compiled from: vungle */
public final class fc {
    static final Pattern a;

    static {
        a = Pattern.compile("[|\\\\?*<\":>+'&\\[\\]]");
    }

    public static String a(Object... objArr) {
        StringBuilder stringBuilder = new StringBuilder();
        int i = 0;
        while (i < 2) {
            Object obj = objArr[i];
            if (obj == null) {
                throw new IllegalArgumentException("null path element at index " + i);
            }
            stringBuilder.append(obj);
            int length = stringBuilder.length();
            if (length > 0 && i + 1 < 2 && stringBuilder.charAt(length - 1) != File.separatorChar) {
                stringBuilder.append(File.separatorChar);
            }
            i++;
        }
        return stringBuilder.toString();
    }

    public static boolean a(String str) {
        File file = new File(str);
        boolean c = c(file);
        if (c) {
            Logger.d(Logger.FILE_TAG, "successfully deleted: " + file);
        } else {
            Logger.w(Logger.FILE_TAG, "could not delete: " + file);
        }
        return c;
    }

    private static boolean c(File file) {
        Deque arrayDeque = new ArrayDeque();
        arrayDeque.push(file);
        File file2 = (File) arrayDeque.peek();
        boolean z = true;
        while (file2 != null) {
            boolean z2;
            boolean z3;
            if (file2.isDirectory()) {
                File[] listFiles = file2.listFiles();
                if (listFiles.length > 0) {
                    for (Object push : listFiles) {
                        arrayDeque.push(push);
                    }
                    z2 = z;
                    z3 = z2;
                    file2 = (File) arrayDeque.peek();
                    z = z3;
                }
            }
            arrayDeque.pop();
            z = z && (!file2.exists() || file2.delete());
            z2 = z;
            z3 = z2;
            file2 = (File) arrayDeque.peek();
            z = z3;
        }
        return z;
    }

    public static boolean a(File file) {
        if (file == null) {
            Logger.w(Logger.FILE_TAG, "null directory path");
            return false;
        } else if (file.mkdirs()) {
            Logger.d(Logger.FILE_TAG, "created directory: " + file);
            return true;
        } else if (file.isDirectory()) {
            Logger.v(Logger.FILE_TAG, "directory exists: " + file);
            return true;
        } else {
            Logger.d(Logger.FILE_TAG, "unable to create directory: " + file);
            return false;
        }
    }

    public static boolean b(File file) {
        boolean z = false;
        File parentFile = file.getParentFile();
        if (a(parentFile)) {
            z = parentFile.canWrite();
            if (z) {
                Logger.v(Logger.FILE_TAG, "directory is writeable: " + parentFile);
            } else {
                Logger.d(Logger.FILE_TAG, "directory not writeable: " + parentFile);
            }
        }
        return z;
    }

    public static boolean b(String str) {
        return !a.matcher(str).find();
    }

    public static String c(String str) {
        Matcher matcher = a.matcher(str);
        if (!matcher.find()) {
            return str;
        }
        String replaceAll = matcher.replaceAll("_");
        Logger.i(Logger.FILE_TAG, "Unsafe character(s) found / replaced in filepath: " + str + " --> " + replaceAll);
        return replaceAll;
    }

    public static boolean a(File file, File file2) throws IOException {
        String canonicalPath = file2.getCanonicalPath();
        String canonicalPath2 = file.getCanonicalPath();
        return (canonicalPath == null || canonicalPath.equals(canonicalPath2) || !canonicalPath.startsWith(canonicalPath2)) ? false : true;
    }
}
