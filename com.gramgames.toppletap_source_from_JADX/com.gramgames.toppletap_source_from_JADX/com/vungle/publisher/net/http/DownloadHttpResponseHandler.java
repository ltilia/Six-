package com.vungle.publisher.net.http;

import com.facebook.internal.Utility;
import com.vungle.log.Logger;
import com.vungle.publisher.ad.AdManager;
import com.vungle.publisher.cg.a;
import com.vungle.publisher.cg.b;
import com.vungle.publisher.ct;
import com.vungle.publisher.event.EventBus;
import com.vungle.publisher.fc;
import com.vungle.publisher.r;
import com.vungle.publisher.s;
import dagger.MembersInjector;
import dagger.internal.MembersInjectors;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import org.json.JSONException;
import org.json.simple.parser.Yytoken;

/* compiled from: vungle */
public class DownloadHttpResponseHandler extends MaxRetryAgeHttpResponseHandler {
    String a;
    String b;
    b c;
    @Inject
    public EventBus d;
    @Inject
    public AdManager e;

    static /* synthetic */ class 1 {
        static final /* synthetic */ int[] a;

        static {
            a = new int[b.values().length];
            try {
                a[b.preRoll.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[b.postRoll.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    @Singleton
    /* compiled from: vungle */
    public static class Factory {
        @Inject
        public Provider<DownloadHttpResponseHandler> a;

        @Inject
        Factory() {
        }
    }

    /* compiled from: vungle */
    public final class Factory_Factory implements dagger.internal.Factory<Factory> {
        static final /* synthetic */ boolean a;
        private final MembersInjector<Factory> b;

        static {
            a = !Factory_Factory.class.desiredAssertionStatus();
        }

        public Factory_Factory(MembersInjector<Factory> factoryMembersInjector) {
            if (a || factoryMembersInjector != null) {
                this.b = factoryMembersInjector;
                return;
            }
            throw new AssertionError();
        }

        public final Factory get() {
            return (Factory) MembersInjectors.injectMembers(this.b, new Factory());
        }

        public static dagger.internal.Factory<Factory> create(MembersInjector<Factory> factoryMembersInjector) {
            return new Factory_Factory(factoryMembersInjector);
        }
    }

    @Inject
    DownloadHttpResponseHandler() {
    }

    protected final void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
        OutputStream fileOutputStream;
        Throwable th;
        HttpRequest httpRequest;
        OutputStream outputStream = null;
        InputStream inputStream;
        try {
            HttpURLConnection httpURLConnection = httpResponse.a;
            inputStream = httpURLConnection.getInputStream();
            try {
                File file = new File(this.b);
                if (fc.b(file)) {
                    int read;
                    Logger.d(Logger.NETWORK_TAG, "downloading to: " + this.b);
                    byte[] bArr = new byte[Utility.DEFAULT_STREAM_BUFFER_SIZE];
                    fileOutputStream = new FileOutputStream(file);
                    int i = 0;
                    while (true) {
                        try {
                            read = inputStream.read(bArr);
                            if (read <= 0) {
                                break;
                            }
                            i += read;
                            fileOutputStream.write(bArr, 0, read);
                        } catch (Throwable th2) {
                            Throwable th3 = th2;
                            outputStream = fileOutputStream;
                            th = th3;
                        }
                    }
                    fileOutputStream.flush();
                    read = httpURLConnection.getContentLength();
                    if (read < 0 || read == i) {
                        Logger.d(Logger.NETWORK_TAG, "download complete: " + this.b + ", size: " + i);
                        ct b = this.e.a(this.a).b(this.c);
                        switch (1.a[this.c.ordinal()]) {
                            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                                b.a(Integer.valueOf(i));
                                break;
                        }
                        Logger.i(Logger.NETWORK_TAG, this.c + " downloaded for ad " + this.a);
                        b.a(a.downloaded);
                        b.m();
                        this.d.a(new s(b, httpTransaction.b));
                    } else {
                        Logger.w(Logger.NETWORK_TAG, "download size mismatch: " + this.b + ", expected size: " + read + ", actual size: " + i);
                        b(httpTransaction, httpResponse);
                    }
                } else {
                    Logger.w(Logger.NETWORK_TAG, "could not create or directory not writeable: " + file);
                    b(httpTransaction, httpResponse);
                    fileOutputStream = null;
                }
                HttpRequest httpRequest2 = httpTransaction.a;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest2 + ": error closing input stream", e);
                    }
                }
                if (fileOutputStream != null) {
                    try {
                        fileOutputStream.close();
                    } catch (Throwable th4) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest2 + ": error closing output stream", th4);
                    }
                }
            } catch (Throwable th5) {
                th4 = th5;
                httpRequest = httpTransaction.a;
                if (inputStream != null) {
                    try {
                        inputStream.close();
                    } catch (Throwable e2) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest + ": error closing input stream", e2);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (Throwable th22) {
                        Logger.d(Logger.NETWORK_TAG, httpRequest + ": error closing output stream", th22);
                    }
                }
                throw th4;
            }
        } catch (Throwable th6) {
            th4 = th6;
            inputStream = null;
            httpRequest = httpTransaction.a;
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
            throw th4;
        }
    }

    protected final void b(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        this.d.a(new r(httpTransaction.b));
    }
}
