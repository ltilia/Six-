package com.vungle.publisher;

import java.io.File;

/* compiled from: vungle */
public final class ff {

    public interface a {
        void a(File file, long j);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void a(java.io.File r10, java.io.File r11, com.vungle.publisher.ff.a... r12) throws java.io.IOException {
        /*
        r1 = 0;
        r0 = "VungleFile";
        r2 = new java.lang.StringBuilder;
        r3 = "extracting ";
        r2.<init>(r3);
        r2 = r2.append(r10);
        r3 = " to ";
        r2 = r2.append(r3);
        r2 = r2.append(r11);
        r2 = r2.toString();
        com.vungle.log.Logger.d(r0, r2);
        r0 = r11.isDirectory();
        if (r0 != 0) goto L_0x0040;
    L_0x0025:
        r0 = r11.mkdirs();
        if (r0 != 0) goto L_0x0040;
    L_0x002b:
        r0 = new java.io.IOException;
        r1 = new java.lang.StringBuilder;
        r2 = "failed to create directories ";
        r1.<init>(r2);
        r1 = r1.append(r11);
        r1 = r1.toString();
        r0.<init>(r1);
        throw r0;
    L_0x0040:
        r4 = new java.util.zip.ZipInputStream;
        r0 = new java.io.BufferedInputStream;
        r2 = new java.io.FileInputStream;
        r2.<init>(r10);
        r0.<init>(r2);
        r4.<init>(r0);
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r5 = new byte[r0];	 Catch:{ all -> 0x00c4 }
    L_0x0053:
        r0 = r4.getNextEntry();	 Catch:{ all -> 0x00c4 }
        if (r0 == 0) goto L_0x013a;
    L_0x0059:
        r0 = r0.getName();	 Catch:{ all -> 0x00c4 }
        r2 = com.vungle.publisher.fc.b(r0);	 Catch:{ all -> 0x00c4 }
        if (r2 == 0) goto L_0x0125;
    L_0x0063:
        r2 = new java.io.File;	 Catch:{ all -> 0x00c4 }
        r2.<init>(r11, r0);	 Catch:{ all -> 0x00c4 }
        r6 = r2.getCanonicalFile();	 Catch:{ all -> 0x00c4 }
        r2 = com.vungle.publisher.fc.a(r11, r6);	 Catch:{ all -> 0x00c4 }
        if (r2 == 0) goto L_0x0106;
    L_0x0072:
        r0 = "VungleFile";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "verified ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r6);	 Catch:{ all -> 0x00c4 }
        r3 = " is nested within ";
        r2 = r2.append(r3);	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r11);	 Catch:{ all -> 0x00c4 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c4 }
        com.vungle.log.Logger.v(r0, r2);	 Catch:{ all -> 0x00c4 }
        r0 = r6.getParentFile();	 Catch:{ all -> 0x00c4 }
        com.vungle.publisher.fc.a(r0);	 Catch:{ all -> 0x00c4 }
        r0 = "VungleFile";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "extracting ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r6);	 Catch:{ all -> 0x00c4 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c4 }
        com.vungle.log.Logger.v(r0, r2);	 Catch:{ all -> 0x00c4 }
        r7 = new java.io.FileOutputStream;	 Catch:{ all -> 0x00c4 }
        r7.<init>(r6);	 Catch:{ all -> 0x00c4 }
        r2 = 0;
    L_0x00b2:
        r0 = r4.read(r5);	 Catch:{ all -> 0x00bf }
        if (r0 <= 0) goto L_0x00c9;
    L_0x00b8:
        r8 = (long) r0;	 Catch:{ all -> 0x00bf }
        r2 = r2 + r8;
        r8 = 0;
        r7.write(r5, r8, r0);	 Catch:{ all -> 0x00bf }
        goto L_0x00b2;
    L_0x00bf:
        r0 = move-exception;
        r7.close();	 Catch:{ IOException -> 0x00f0 }
    L_0x00c3:
        throw r0;	 Catch:{ all -> 0x00c4 }
    L_0x00c4:
        r0 = move-exception;
        r4.close();	 Catch:{ IOException -> 0x0154 }
    L_0x00c8:
        throw r0;
    L_0x00c9:
        r0 = r1;
    L_0x00ca:
        if (r0 > 0) goto L_0x00d4;
    L_0x00cc:
        r8 = r12[r0];	 Catch:{ all -> 0x00bf }
        r8.a(r6, r2);	 Catch:{ all -> 0x00bf }
        r0 = r0 + 1;
        goto L_0x00ca;
    L_0x00d4:
        r7.close();	 Catch:{ IOException -> 0x00d9 }
        goto L_0x0053;
    L_0x00d9:
        r0 = move-exception;
        r0 = "VungleFile";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "error closing file output stream ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r11);	 Catch:{ all -> 0x00c4 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c4 }
        com.vungle.log.Logger.w(r0, r2);	 Catch:{ all -> 0x00c4 }
        goto L_0x0053;
    L_0x00f0:
        r1 = move-exception;
        r1 = "VungleFile";
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "error closing file output stream ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r2 = r2.append(r11);	 Catch:{ all -> 0x00c4 }
        r2 = r2.toString();	 Catch:{ all -> 0x00c4 }
        com.vungle.log.Logger.w(r1, r2);	 Catch:{ all -> 0x00c4 }
        goto L_0x00c3;
    L_0x0106:
        r1 = new com.vungle.publisher.fe;	 Catch:{ all -> 0x00c4 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "aborting zip extraction - child ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00c4 }
        r2 = " escapes destination directory ";
        r0 = r0.append(r2);	 Catch:{ all -> 0x00c4 }
        r0 = r0.append(r11);	 Catch:{ all -> 0x00c4 }
        r0 = r0.toString();	 Catch:{ all -> 0x00c4 }
        r1.<init>(r0);	 Catch:{ all -> 0x00c4 }
        throw r1;	 Catch:{ all -> 0x00c4 }
    L_0x0125:
        r1 = new com.vungle.publisher.fd;	 Catch:{ all -> 0x00c4 }
        r2 = new java.lang.StringBuilder;	 Catch:{ all -> 0x00c4 }
        r3 = "Unsafe path ";
        r2.<init>(r3);	 Catch:{ all -> 0x00c4 }
        r0 = r2.append(r0);	 Catch:{ all -> 0x00c4 }
        r0 = r0.toString();	 Catch:{ all -> 0x00c4 }
        r1.<init>(r0);	 Catch:{ all -> 0x00c4 }
        throw r1;	 Catch:{ all -> 0x00c4 }
    L_0x013a:
        r4.close();	 Catch:{ IOException -> 0x013e }
    L_0x013d:
        return;
    L_0x013e:
        r0 = move-exception;
        r0 = "VungleFile";
        r1 = new java.lang.StringBuilder;
        r2 = "error closing zip input stream ";
        r1.<init>(r2);
        r1 = r1.append(r10);
        r1 = r1.toString();
        com.vungle.log.Logger.w(r0, r1);
        goto L_0x013d;
    L_0x0154:
        r1 = move-exception;
        r1 = "VungleFile";
        r2 = new java.lang.StringBuilder;
        r3 = "error closing zip input stream ";
        r2.<init>(r3);
        r2 = r2.append(r10);
        r2 = r2.toString();
        com.vungle.log.Logger.w(r1, r2);
        goto L_0x00c8;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vungle.publisher.ff.a(java.io.File, java.io.File, com.vungle.publisher.ff$a[]):void");
    }
}
