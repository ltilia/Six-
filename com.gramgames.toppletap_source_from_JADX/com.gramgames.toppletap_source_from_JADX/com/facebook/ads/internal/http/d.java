package com.facebook.ads.internal.http;

import com.google.android.exoplayer.C;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;

public class d {
    private static String d;
    protected ConcurrentHashMap<String, String> a;
    protected ConcurrentHashMap<String, a> b;
    protected ConcurrentHashMap<String, ArrayList<String>> c;

    private static class a {
        public InputStream a;
        public String b;
        public String c;

        public String a() {
            return this.b != null ? this.b : "nofilename";
        }
    }

    static {
        d = C.UTF8_NAME;
    }

    public d() {
        c();
    }

    public d(Map<String, String> map) {
        c();
        for (Entry entry : map.entrySet()) {
            a((String) entry.getKey(), (String) entry.getValue());
        }
    }

    private void c() {
        this.a = new ConcurrentHashMap();
        this.b = new ConcurrentHashMap();
        this.c = new ConcurrentHashMap();
    }

    public HttpEntity a() {
        if (this.b.isEmpty()) {
            try {
                return new UrlEncodedFormEntity(b(), d);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return null;
            }
        }
        HttpEntity eVar = new e();
        for (Entry entry : this.a.entrySet()) {
            eVar.a((String) entry.getKey(), (String) entry.getValue());
        }
        int size = this.b.entrySet().size() - 1;
        int i = 0;
        for (Entry entry2 : this.b.entrySet()) {
            a aVar = (a) entry2.getValue();
            if (aVar.a != null) {
                boolean z = i == size;
                if (aVar.c != null) {
                    eVar.a((String) entry2.getKey(), aVar.a(), aVar.a, aVar.c, z);
                } else {
                    eVar.a((String) entry2.getKey(), aVar.a(), aVar.a, z);
                }
            }
            i++;
        }
        for (Entry entry22 : this.c.entrySet()) {
            Iterator it = ((ArrayList) entry22.getValue()).iterator();
            while (it.hasNext()) {
                eVar.a((String) entry22.getKey(), (String) it.next());
            }
        }
        return eVar;
    }

    public void a(String str, String str2) {
        if (str != null && str2 != null) {
            this.a.put(str, str2);
        }
    }

    protected List<BasicNameValuePair> b() {
        List<BasicNameValuePair> linkedList = new LinkedList();
        for (Entry entry : this.a.entrySet()) {
            linkedList.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
        }
        for (Entry entry2 : this.c.entrySet()) {
            Iterator it = ((ArrayList) entry2.getValue()).iterator();
            while (it.hasNext()) {
                linkedList.add(new BasicNameValuePair((String) entry2.getKey(), (String) it.next()));
            }
        }
        return linkedList;
    }

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Entry entry : this.a.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry.getKey());
            stringBuilder.append("=");
            stringBuilder.append((String) entry.getValue());
        }
        for (Entry entry2 : this.b.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            stringBuilder.append((String) entry2.getKey());
            stringBuilder.append("=");
            stringBuilder.append("FILE");
        }
        for (Entry entry22 : this.c.entrySet()) {
            if (stringBuilder.length() > 0) {
                stringBuilder.append("&");
            }
            ArrayList arrayList = (ArrayList) entry22.getValue();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                String str = (String) it.next();
                if (arrayList.indexOf(str) != 0) {
                    stringBuilder.append("&");
                }
                stringBuilder.append((String) entry22.getKey());
                stringBuilder.append("=");
                stringBuilder.append(str);
            }
        }
        return stringBuilder.toString();
    }
}
