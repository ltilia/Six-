package com.chartboost.sdk.impl;

import android.support.v4.media.session.PlaybackStateCompat;
import com.google.android.exoplayer.C;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigInteger;
import java.nio.charset.Charset;

public class bt {
    public static final BigInteger a;
    public static final BigInteger b;
    public static final BigInteger c;
    public static final BigInteger d;
    public static final BigInteger e;
    public static final BigInteger f;
    public static final BigInteger g;
    public static final BigInteger h;
    public static final File[] i;
    private static final Charset j;

    static {
        a = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID);
        b = a.multiply(a);
        c = a.multiply(b);
        d = a.multiply(c);
        e = a.multiply(d);
        f = a.multiply(e);
        g = BigInteger.valueOf(PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID).multiply(BigInteger.valueOf(1152921504606846976L));
        h = a.multiply(g);
        i = new File[0];
        j = Charset.forName(C.UTF8_NAME);
    }

    public static FileInputStream a(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File '" + file + "' does not exist");
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (file.canRead()) {
            return new FileInputStream(file);
        } else {
            throw new IOException("File '" + file + "' cannot be read");
        }
    }

    public static FileOutputStream a(File file, boolean z) throws IOException {
        if (!file.exists()) {
            File parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.mkdirs() || parentFile.isDirectory())) {
                throw new IOException("Directory '" + parentFile + "' could not be created");
            }
        } else if (file.isDirectory()) {
            throw new IOException("File '" + file + "' exists but is a directory");
        } else if (!file.canWrite()) {
            throw new IOException("File '" + file + "' cannot be written to");
        }
        return new FileOutputStream(file, z);
    }

    public static byte[] b(File file) throws IOException {
        InputStream inputStream = null;
        try {
            inputStream = a(file);
            byte[] a = bu.a(inputStream, file.length());
            return a;
        } finally {
            bu.a(inputStream);
        }
    }

    public static void a(File file, byte[] bArr) throws IOException {
        a(file, bArr, false);
    }

    public static void a(File file, byte[] bArr, boolean z) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = a(file, z);
            outputStream.write(bArr);
            outputStream.close();
        } finally {
            bu.a(outputStream);
        }
    }
}
