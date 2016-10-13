package com.vungle.publisher;

import android.text.TextUtils;
import com.facebook.internal.Utility;
import com.vungle.log.Logger;
import com.vungle.publisher.async.ScheduledPriorityExecutor;
import com.vungle.publisher.db.model.LoggedException.Factory;
import com.vungle.publisher.net.http.HttpResponse;
import com.vungle.publisher.net.http.HttpTransaction;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.inject.Inject;
import org.json.JSONException;

/* compiled from: vungle */
public abstract class gp {
    private static final Pattern a;
    @Inject
    public ScheduledPriorityExecutor f;
    @Inject
    public Factory g;

    static {
        a = Pattern.compile("\\bcharset=([\\w\\-]+)\\b");
    }

    public void c(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        try {
            if (a(httpResponse.b)) {
                try {
                    a(httpTransaction, httpResponse);
                    return;
                } catch (Throwable e) {
                    Logger.d(Logger.NETWORK_TAG, e);
                    httpResponse.b = 603;
                } catch (Throwable e2) {
                    this.g.b(Logger.NETWORK_TAG, "IOException while handling response: " + httpResponse, e2);
                    httpResponse.b = 600;
                } catch (Throwable e22) {
                    this.g.b(Logger.NETWORK_TAG, "JSONException while handling response: " + httpResponse, e22);
                    httpResponse.b = 604;
                }
            }
            d(httpTransaction, httpResponse);
        } catch (Exception e3) {
            a(httpTransaction, httpResponse, e3);
        }
    }

    public void d(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        b(httpTransaction, httpResponse);
    }

    public void a(HttpTransaction httpTransaction, HttpResponse httpResponse) throws IOException, JSONException {
    }

    public void a(HttpTransaction httpTransaction, HttpResponse httpResponse, Exception exception) {
        this.g.a(Logger.NETWORK_TAG, "error while handling response: " + httpResponse, exception);
        b(httpTransaction, httpResponse);
    }

    public void b(HttpTransaction httpTransaction, HttpResponse httpResponse) {
        Logger.w(Logger.NETWORK_TAG, httpTransaction.a + " failed permanently with response code " + httpResponse.b);
    }

    public static boolean a(int i) {
        return i == 200;
    }

    public final String a(HttpURLConnection httpURLConnection) throws IOException {
        Throwable th;
        Reader reader = null;
        Object obj = 1;
        try {
            String group;
            InputStream inputStream;
            Reader inputStreamReader;
            StringBuilder stringBuilder;
            char[] cArr;
            int read;
            int responseCode = httpURLConnection.getResponseCode();
            CharSequence contentType = httpURLConnection.getContentType();
            if (contentType != null) {
                Matcher matcher = a.matcher(contentType);
                if (matcher.find()) {
                    group = matcher.group(1);
                    Logger.v(Logger.NETWORK_TAG, "response character set: " + group);
                    if (responseCode / 100 > 3) {
                        obj = null;
                    }
                    inputStream = obj == null ? httpURLConnection.getInputStream() : httpURLConnection.getErrorStream();
                    if (TextUtils.isEmpty(group)) {
                        group = "ISO-8859-1";
                    }
                    inputStreamReader = new InputStreamReader(inputStream, group);
                    stringBuilder = new StringBuilder();
                    cArr = new char[Utility.DEFAULT_STREAM_BUFFER_SIZE];
                    while (true) {
                        read = inputStreamReader.read(cArr);
                        if (read > 0) {
                            break;
                        }
                        stringBuilder.append(cArr, 0, read);
                    }
                    group = stringBuilder.toString();
                    Logger.d(Logger.NETWORK_TAG, "response body: " + group);
                    try {
                        inputStreamReader.close();
                    } catch (Throwable e) {
                        this.g.b(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e);
                    }
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                    return group;
                }
            }
            group = null;
            Logger.v(Logger.NETWORK_TAG, "response character set: " + group);
            if (responseCode / 100 > 3) {
                obj = null;
            }
            if (obj == null) {
            }
            if (TextUtils.isEmpty(group)) {
                group = "ISO-8859-1";
            }
            inputStreamReader = new InputStreamReader(inputStream, group);
            try {
                stringBuilder = new StringBuilder();
                cArr = new char[Utility.DEFAULT_STREAM_BUFFER_SIZE];
                while (true) {
                    read = inputStreamReader.read(cArr);
                    if (read > 0) {
                        break;
                        group = stringBuilder.toString();
                        Logger.d(Logger.NETWORK_TAG, "response body: " + group);
                        inputStreamReader.close();
                        if (httpURLConnection != null) {
                            httpURLConnection.disconnect();
                        }
                        return group;
                    }
                    stringBuilder.append(cArr, 0, read);
                }
            } catch (Throwable th2) {
                th = th2;
                reader = inputStreamReader;
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (Throwable e2) {
                        this.g.b(Logger.NETWORK_TAG, "error closing input stream " + httpURLConnection.getURL(), e2);
                    }
                }
                if (httpURLConnection != null) {
                    httpURLConnection.disconnect();
                }
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            if (reader != null) {
                reader.close();
            }
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
            throw th;
        }
    }
}
