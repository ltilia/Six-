package com.mopub.common.util;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Streams {
    public static void copyContent(InputStream inputStream, OutputStream outputStream) throws IOException {
        if (inputStream == null || outputStream == null) {
            throw new IOException("Unable to copy from or to a null stream.");
        }
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_COPY];
        while (true) {
            int length = inputStream.read(buffer);
            if (length != -1) {
                outputStream.write(buffer, 0, length);
            } else {
                return;
            }
        }
    }

    public static void copyContent(InputStream inputStream, OutputStream outputStream, long maxBytes) throws IOException {
        if (inputStream == null || outputStream == null) {
            throw new IOException("Unable to copy from or to a null stream.");
        }
        byte[] buffer = new byte[AccessibilityNodeInfoCompat.ACTION_COPY];
        long totalRead = 0;
        while (true) {
            int length = inputStream.read(buffer);
            if (length != -1) {
                totalRead += (long) length;
                if (totalRead >= maxBytes) {
                    break;
                }
                outputStream.write(buffer, 0, length);
            } else {
                return;
            }
        }
        throw new IOException("Error copying content: attempted to copy " + totalRead + " bytes, with " + maxBytes + " maximum.");
    }

    public static void readStream(InputStream inputStream, byte[] buffer) throws IOException {
        int offset = 0;
        int maxBytes = buffer.length;
        do {
            int bytesRead = inputStream.read(buffer, offset, maxBytes);
            if (bytesRead != -1) {
                offset += bytesRead;
                maxBytes -= bytesRead;
            } else {
                return;
            }
        } while (maxBytes > 0);
    }

    public static void closeStream(Closeable stream) {
        if (stream != null) {
            try {
                stream.close();
            } catch (IOException e) {
            }
        }
    }
}
