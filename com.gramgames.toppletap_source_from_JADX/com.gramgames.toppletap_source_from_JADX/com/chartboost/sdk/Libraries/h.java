package com.chartboost.sdk.Libraries;

import android.content.Context;
import android.os.Environment;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.text.TextUtils;
import com.chartboost.sdk.c;
import com.chartboost.sdk.impl.bt;
import com.google.android.gms.games.request.Requests;
import com.mopub.common.AdType;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class h {
    private static File a;
    private static File b;
    private static File c;
    private static File d;
    private static File e;
    private static File f;
    private static File g;
    private static File h;
    private static File i;
    private static File j;
    private static File k;
    private static File l;
    private static File m;
    private static File n;
    private static File o;
    private static File p;
    private static File q;
    private static File r;
    private static File s;
    private static Context t;
    private static File u;
    private static File v;
    private static File w;
    private static String[] x;
    private boolean y;

    public enum a {
        TemplateMetaData("templates"),
        Videos("videos"),
        Images("images"),
        StyleSheets("css"),
        Javascript("js"),
        Html(AdType.HTML),
        VideoCompletion("videoCompletionEvents"),
        Session("session"),
        Track("track"),
        RequestManager(Requests.EXTRA_REQUESTS);
        
        private final String k;

        private a(String str) {
            this.k = str;
        }

        public String toString() {
            return this.k;
        }
    }

    public h(boolean z) {
        this.y = false;
        t = c.y();
        if (t == null) {
            CBLogging.b("CBFileCache", "RunTime error: Cannot find context object");
            return;
        }
        this.y = z;
        a(z);
    }

    private File a(boolean z) {
        if (m() != null) {
            return a;
        }
        File file = new File(t.getCacheDir(), ".chartboost");
        s = file;
        c = file;
        file = new File(Environment.getExternalStorageDirectory(), ".chartboost");
        r = file;
        b = file;
        if (!c.exists()) {
            c.mkdirs();
        }
        if (!b.exists()) {
            b.mkdirs();
        }
        if (c.a()) {
            a = c;
        } else {
            a = b;
        }
        if (!z) {
            a = c;
        }
        k = new File(c, a.TemplateMetaData.toString());
        if (!k.exists()) {
            k.mkdir();
        }
        j = new File(b, a.TemplateMetaData.toString());
        if (!j.exists()) {
            j.mkdir();
        }
        e = new File(c, a.Videos.toString());
        if (!e.exists()) {
            e.mkdir();
        }
        d = new File(b, a.Videos.toString());
        if (!d.exists()) {
            d.mkdir();
        }
        g = new File(c, a.StyleSheets.toString());
        if (!g.exists()) {
            g.mkdir();
        }
        f = new File(b, a.StyleSheets.toString());
        if (!f.exists()) {
            f.mkdir();
        }
        i = new File(c, a.Javascript.toString());
        if (!i.exists()) {
            i.mkdir();
        }
        h = new File(b, a.Javascript.toString());
        if (!h.exists()) {
            h.mkdir();
        }
        m = new File(c, a.Html.toString());
        if (!m.exists()) {
            m.mkdir();
        }
        l = new File(b, a.Html.toString());
        if (!l.exists()) {
            l.mkdir();
        }
        q = new File(c, a.Images.toString());
        if (!q.exists()) {
            q.mkdir();
        }
        p = new File(b, a.Images.toString());
        if (!p.exists()) {
            p.mkdir();
        }
        n = new File(b, ".adId");
        o = new File(c, ".adId");
        u = new File(s, a.RequestManager.toString());
        w = new File(s, a.Track.toString());
        v = new File(s, a.Session.toString());
        return a;
    }

    public static File a() {
        if (e()) {
            return r;
        }
        return s;
    }

    public synchronized File a(File file, String str, com.chartboost.sdk.Libraries.e.a aVar) {
        File file2;
        file2 = null;
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            file2 = a(file, file2, aVar);
        }
        return file2;
    }

    public synchronized File a(File file, File file2, com.chartboost.sdk.Libraries.e.a aVar) {
        File file3;
        if (file == null) {
            file3 = null;
        } else {
            if (file2 == null) {
                file3 = new File(file.getPath(), Long.toString(System.nanoTime()));
            } else {
                file3 = file2;
            }
            try {
                bt.a(file3, aVar.toString().getBytes());
            } catch (Throwable e) {
                CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
            }
        }
        return file3;
    }

    public synchronized void a(File file, String str, byte[] bArr) {
        if (file != null) {
            File file2 = null;
            if (!TextUtils.isEmpty(str)) {
                file2 = new File(file.getPath(), str);
            }
            a(file, file2, bArr);
        }
    }

    public synchronized void a(File file, File file2, byte[] bArr) {
        if (!(file == null || bArr == null)) {
            if (file2 == null) {
                file2 = new File(file.getPath(), Long.toString(System.nanoTime()));
            }
            try {
                bt.a(file2, bArr);
            } catch (Throwable e) {
                CBLogging.b("CBFileCache", "IOException attempting to write cache to disk", e);
            }
        }
    }

    public synchronized com.chartboost.sdk.Libraries.e.a a(File file, String str) {
        com.chartboost.sdk.Libraries.e.a aVar;
        if (m() == null || str == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a;
        } else {
            File file2 = new File(file, str);
            if (file2.exists()) {
                aVar = a(file2);
            } else {
                aVar = com.chartboost.sdk.Libraries.e.a.a;
            }
        }
        return aVar;
    }

    public synchronized com.chartboost.sdk.Libraries.e.a a(File file) {
        com.chartboost.sdk.Libraries.e.a aVar;
        if (m() == null) {
            aVar = com.chartboost.sdk.Libraries.e.a.a;
        } else {
            String str;
            try {
                str = new String(bt.b(file));
            } catch (Throwable e) {
                CBLogging.b("CBFileCache", "Error loading cache from disk", e);
                str = null;
            }
            aVar = com.chartboost.sdk.Libraries.e.a.k(str);
        }
        return aVar;
    }

    public synchronized byte[] b(File file) {
        byte[] bArr = null;
        synchronized (this) {
            if (file != null) {
                try {
                    bArr = bt.b(file);
                } catch (Throwable e) {
                    CBLogging.b("CBFileCache", "Error loading cache from disk", e);
                }
            }
        }
        return bArr;
    }

    public synchronized String[] c(File file) {
        String[] strArr;
        if (file == null) {
            strArr = null;
        } else {
            strArr = file.list();
        }
        return strArr;
    }

    public static synchronized HashMap<String, File> b() {
        HashMap<String, File> hashMap;
        synchronized (h.class) {
            hashMap = new HashMap();
            if (a != null) {
                File file;
                if (e()) {
                    file = r;
                } else {
                    file = s;
                }
                String[] list = file.list();
                if (list != null && list.length > 0) {
                    for (String str : list) {
                        if (!(str.equalsIgnoreCase(a.TemplateMetaData.toString()) || str.equalsIgnoreCase(a.RequestManager.toString()) || str.equalsIgnoreCase(a.Track.toString()) || str.equalsIgnoreCase(a.Session.toString()) || str.equalsIgnoreCase(a.VideoCompletion.toString()) || str.contains("."))) {
                            File file2 = new File(file, str);
                            if (file2 != null) {
                                String[] list2 = file2.list();
                                if (list2 != null && list2.length > 0) {
                                    for (String str2 : list2) {
                                        if (!str2.equals(".nomedia")) {
                                            hashMap.put(str2, new File(file2, str2));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return hashMap;
    }

    public static synchronized String[] c() {
        String[] strArr = null;
        synchronized (h.class) {
            if (a != null) {
                File file;
                if (e()) {
                    file = d;
                } else {
                    file = e;
                }
                if (file != null) {
                    x = file.list();
                }
                if (!(x == null || x.length == 0)) {
                    strArr = x;
                }
            }
        }
        return strArr;
    }

    public static String a(String str) {
        if (a == null) {
            return null;
        }
        File file;
        if (e()) {
            file = d;
        } else {
            file = e;
        }
        File file2 = new File(file, str);
        if (file2.exists()) {
            return file2.getPath();
        }
        return null;
    }

    public static synchronized void a(ArrayList<String> arrayList, File file, boolean z) {
        ObjectOutputStream objectOutputStream;
        IOException e;
        FileOutputStream fileOutputStream = null;
        synchronized (h.class) {
            if (!(file == null || arrayList == null)) {
                if (z) {
                    ArrayList<String> d = d(file);
                    if (d.size() > 0) {
                        Iterator it = arrayList.iterator();
                        while (it.hasNext()) {
                            String str = (String) it.next();
                            if (!d.contains(str)) {
                                d.add(str);
                            }
                        }
                        arrayList = d;
                    }
                }
                try {
                    OutputStream fileOutputStream2 = new FileOutputStream(file);
                    try {
                        objectOutputStream = new ObjectOutputStream(fileOutputStream2);
                        try {
                            objectOutputStream.writeObject(arrayList);
                            objectOutputStream.close();
                            fileOutputStream2.close();
                        } catch (IOException e2) {
                            e = e2;
                            fileOutputStream = fileOutputStream2;
                            if (objectOutputStream != null) {
                                try {
                                    objectOutputStream.close();
                                } catch (IOException e3) {
                                    e3.printStackTrace();
                                    e.printStackTrace();
                                }
                            }
                            if (fileOutputStream != null) {
                                fileOutputStream.close();
                            }
                            e.printStackTrace();
                        }
                    } catch (IOException e4) {
                        e = e4;
                        objectOutputStream = null;
                        OutputStream outputStream = fileOutputStream2;
                        if (objectOutputStream != null) {
                            objectOutputStream.close();
                        }
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        e.printStackTrace();
                    }
                } catch (IOException e5) {
                    e = e5;
                    objectOutputStream = null;
                    if (objectOutputStream != null) {
                        objectOutputStream.close();
                    }
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized java.util.ArrayList<java.lang.String> d(java.io.File r7) {
        /*
        r3 = 0;
        r5 = com.chartboost.sdk.Libraries.h.class;
        monitor-enter(r5);
        r1 = new java.util.ArrayList;	 Catch:{ all -> 0x003b }
        r1.<init>();	 Catch:{ all -> 0x003b }
        if (r7 == 0) goto L_0x0011;
    L_0x000b:
        r0 = r7.exists();	 Catch:{ all -> 0x003b }
        if (r0 != 0) goto L_0x0014;
    L_0x0011:
        r0 = r1;
    L_0x0012:
        monitor-exit(r5);
        return r0;
    L_0x0014:
        r4 = new java.io.FileInputStream;	 Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x0043 }
        r4.<init>(r7);	 Catch:{ IOException -> 0x004d, ClassNotFoundException -> 0x0043 }
        r2 = new java.io.ObjectInputStream;	 Catch:{ IOException -> 0x0053, ClassNotFoundException -> 0x0043 }
        r2.<init>(r4);	 Catch:{ IOException -> 0x0053, ClassNotFoundException -> 0x0043 }
        r0 = r2.readObject();	 Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0043 }
        r0 = (java.util.ArrayList) r0;	 Catch:{ IOException -> 0x005a, ClassNotFoundException -> 0x0043 }
        r2.close();	 Catch:{ IOException -> 0x002b, ClassNotFoundException -> 0x004b }
        r4.close();	 Catch:{ IOException -> 0x002b, ClassNotFoundException -> 0x004b }
        goto L_0x0012;
    L_0x002b:
        r1 = move-exception;
        r3 = r4;
    L_0x002d:
        if (r2 == 0) goto L_0x0032;
    L_0x002f:
        r2.close();	 Catch:{ IOException -> 0x003e }
    L_0x0032:
        if (r3 == 0) goto L_0x0037;
    L_0x0034:
        r3.close();	 Catch:{ IOException -> 0x003e }
    L_0x0037:
        r1.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0012;
    L_0x003b:
        r0 = move-exception;
        monitor-exit(r5);
        throw r0;
    L_0x003e:
        r2 = move-exception;
        r2.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0037;
    L_0x0043:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x0047:
        r1.printStackTrace();	 Catch:{ all -> 0x003b }
        goto L_0x0012;
    L_0x004b:
        r1 = move-exception;
        goto L_0x0047;
    L_0x004d:
        r0 = move-exception;
        r2 = r3;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
    L_0x0053:
        r0 = move-exception;
        r2 = r3;
        r3 = r4;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
    L_0x005a:
        r0 = move-exception;
        r3 = r4;
        r6 = r0;
        r0 = r1;
        r1 = r6;
        goto L_0x002d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.chartboost.sdk.Libraries.h.d(java.io.File):java.util.ArrayList<java.lang.String>");
    }

    public synchronized void e(File file) {
        if (file != null) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

    public synchronized void b(File file, String str) {
        if (file != null) {
            if (!TextUtils.isEmpty(str)) {
                e(c(file, str));
            }
        }
    }

    public synchronized void d() {
        int i = 0;
        synchronized (this) {
            if (a != null) {
                try {
                    if (b != null) {
                        File[] listFiles = b.listFiles();
                        if (listFiles != null) {
                            for (File delete : listFiles) {
                                delete.delete();
                            }
                        }
                    }
                    if (c != null) {
                        File[] listFiles2 = c.listFiles();
                        if (listFiles2 != null) {
                            int length = listFiles2.length;
                            while (i < length) {
                                listFiles2[i].delete();
                                i++;
                            }
                        }
                    }
                } catch (Exception e) {
                    CBLogging.b("CBFileCache", "Error while clearing the file cache");
                }
            }
        }
    }

    public static boolean e() {
        if (Environment.getExternalStorageState().equals("mounted") && !c.a()) {
            return true;
        }
        CBLogging.e("CBFileCache", "External Storage unavailable");
        return false;
    }

    public boolean b(String str) {
        if (i() == null || str == null) {
            return false;
        }
        return new File(i(), str).exists();
    }

    public boolean c(String str) {
        if (f() == null || str == null) {
            return false;
        }
        return new File(f(), str).exists();
    }

    public File c(File file, String str) {
        if (file == null) {
            return null;
        }
        return new File(file.getPath(), str);
    }

    public File f() {
        if (this.y && e()) {
            return d;
        }
        return e;
    }

    public File d(String str) {
        if (this.y && e()) {
            return new File(r, str);
        }
        return new File(s, str);
    }

    public boolean e(String str) {
        File file;
        if (this.y && e()) {
            file = r;
        } else {
            file = s;
        }
        if (file == null) {
            return false;
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.mkdir();
        }
        return true;
    }

    public File g() {
        return u;
    }

    public File h() {
        return v;
    }

    public File i() {
        if (this.y && e()) {
            return p;
        }
        return q;
    }

    public File j() {
        if (this.y && e()) {
            return j;
        }
        return k;
    }

    public File k() {
        return o;
    }

    public File l() {
        return n;
    }

    public File m() {
        if (a == null) {
            return null;
        }
        if (this.y && e()) {
            a = b;
        } else {
            a = c;
        }
        return a;
    }

    public static long f(File file) {
        long j = 0;
        if (file != null) {
            try {
                if (file.isDirectory()) {
                    File[] listFiles = file.listFiles();
                    if (listFiles != null) {
                        int i = 0;
                        while (i < listFiles.length) {
                            long f = f(listFiles[i]) + j;
                            i++;
                            j = f;
                        }
                    }
                    return j;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        j = file.length();
        return j;
    }

    public static com.chartboost.sdk.Libraries.e.a n() {
        File file;
        com.chartboost.sdk.Libraries.e.a a = com.chartboost.sdk.Libraries.e.a.a();
        a.a(".chartboost-external-folder-size", Long.valueOf(f(r)));
        a.a(".chartboost-internal-folder-size", Long.valueOf(f(s)));
        if (e()) {
            file = r;
        } else {
            file = s;
        }
        String[] list = file.list();
        if (list != null && list.length > 0) {
            for (String file2 : list) {
                File file3 = new File(file, file2);
                if (file3 != null) {
                    com.chartboost.sdk.Libraries.e.a a2 = com.chartboost.sdk.Libraries.e.a.a();
                    a2.a(file3.getName() + "-size", Long.valueOf(f(file3)));
                    String[] list2 = file3.list();
                    if (list2 != null) {
                        a2.a("count", Integer.valueOf(list2.length));
                    }
                    a.a(file3.getName(), a2);
                }
            }
        }
        return a;
    }

    public static boolean a(File file, int i) {
        if (file == null || !file.exists()) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.add(6, -i);
        Date date = new Date(file.lastModified());
        CBLogging.a("CBFileCache", "### File last modified" + date.toString());
        if (!date.before(instance.getTime())) {
            return false;
        }
        CBLogging.a("CBFileCache", "### File is expired and is past " + i + " days");
        return true;
    }

    public void o() {
        File file = new File(c, "asset_log");
        File file2 = new File(b, "asset_log");
        if (!(this.y && e())) {
            file2 = file;
        }
        if (file2 != null && file2.exists()) {
            File[] listFiles = file2.listFiles();
            if (listFiles != null && listFiles.length > 0) {
                for (File file22 : listFiles) {
                    try {
                        CBLogging.a("CBFileCache", "Copying the template meta data files from asset_log folder to template folder");
                        a(file22, j());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void a(File file, File file2) throws IOException {
        int i = 0;
        if (!file.isDirectory()) {
            File parentFile = file2.getParentFile();
            if (parentFile == null || parentFile.exists() || parentFile.mkdirs()) {
                InputStream fileInputStream = new FileInputStream(file);
                OutputStream fileOutputStream = new FileOutputStream(file2);
                byte[] bArr = new byte[AccessibilityNodeInfoCompat.ACTION_NEXT_HTML_ELEMENT];
                while (true) {
                    int read = fileInputStream.read(bArr);
                    if (read > 0) {
                        fileOutputStream.write(bArr, 0, read);
                    } else {
                        fileInputStream.close();
                        fileOutputStream.close();
                        return;
                    }
                }
            }
            throw new IOException("Cannot create dir " + parentFile.getAbsolutePath());
        } else if (file2.exists() || file2.mkdirs()) {
            String[] list = file.list();
            while (i < list.length) {
                a(new File(file, list[i]), new File(file2, list[i]));
                i++;
            }
        } else {
            throw new IOException("Cannot create dir " + file2.getAbsolutePath());
        }
    }
}
