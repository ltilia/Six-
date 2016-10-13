package com.facebook.ads.internal.http;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.exoplayer.C;
import com.google.android.gms.nearby.messages.Strategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.util.EntityUtils;
import org.json.simple.parser.Yytoken;

public class c {
    private Handler a;

    class 1 extends Handler {
        final /* synthetic */ c a;

        1(c cVar) {
            this.a = cVar;
        }

        public void handleMessage(Message message) {
            this.a.a(message);
        }
    }

    public c() {
        if (Looper.myLooper() != null) {
            this.a = new 1(this);
        }
    }

    protected Message a(int i, Object obj) {
        if (this.a != null) {
            return this.a.obtainMessage(i, obj);
        }
        Message obtain = Message.obtain();
        obtain.what = i;
        obtain.obj = obj;
        return obtain;
    }

    public void a() {
    }

    public void a(int i, String str) {
        a(str);
    }

    protected void a(Message message) {
        Object[] objArr;
        switch (message.what) {
            case Yylex.YYINITIAL /*0*/:
                objArr = (Object[]) message.obj;
                c(((Integer) objArr[0]).intValue(), (String) objArr[1]);
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                objArr = (Object[]) message.obj;
                c((Throwable) objArr[0], (String) objArr[1]);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                a();
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                b();
            default:
        }
    }

    public void a(String str) {
    }

    public void a(Throwable th) {
    }

    public void a(Throwable th, String str) {
        a(th);
    }

    void a(HttpResponse httpResponse) {
        String str = null;
        StatusLine statusLine = httpResponse.getStatusLine();
        try {
            HttpEntity entity = httpResponse.getEntity();
            if (entity != null) {
                str = EntityUtils.toString(new BufferedHttpEntity(entity), C.UTF8_NAME);
            }
        } catch (Throwable e) {
            b(e, null);
        }
        if (statusLine.getStatusCode() >= Strategy.TTL_SECONDS_DEFAULT) {
            b(new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase()), str);
        } else {
            b(statusLine.getStatusCode(), str);
        }
    }

    public void b() {
    }

    protected void b(int i, String str) {
        b(a(0, new Object[]{Integer.valueOf(i), str}));
    }

    protected void b(Message message) {
        if (this.a != null) {
            this.a.sendMessage(message);
        } else {
            a(message);
        }
    }

    protected void b(Throwable th, String str) {
        b(a(1, new Object[]{th, str}));
    }

    protected void c() {
        b(a(2, null));
    }

    protected void c(int i, String str) {
        a(i, str);
    }

    protected void c(Throwable th, String str) {
        a(th, str);
    }

    protected void d() {
        b(a(3, null));
    }
}
