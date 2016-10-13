package com.vungle.publisher.net.http;

import android.os.Build.VERSION;
import android.os.Bundle;
import android.text.TextUtils;
import com.mopub.mobileads.CustomEventBannerAdapter;
import com.vungle.log.Logger;
import com.vungle.publisher.jh;
import com.vungle.publisher.net.http.HttpRequest.a;
import com.vungle.publisher.net.http.HttpResponse.Factory;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
/* compiled from: vungle */
public class HttpTransport {
    @Inject
    public HttpURLConnectionFactory a;
    @Inject
    public Factory b;
    @Inject
    public HttpRequestChainElement.Factory c;

    static {
        if (VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
    }

    @Inject
    HttpTransport() {
    }

    public final HttpResponse a(HttpRequest httpRequest) {
        HttpURLConnection httpURLConnection;
        HttpResponse httpResponse;
        String str;
        Throwable th;
        HttpURLConnection httpURLConnection2;
        int i = -1;
        List arrayList = new ArrayList();
        try {
            a b = httpRequest.b();
            String str2 = httpRequest.b;
            int i2 = 0;
            HttpURLConnection httpURLConnection3 = null;
            while (i2 < 5) {
                try {
                    Logger.d(Logger.NETWORK_TAG, b + " " + str2);
                    HttpURLConnection a = HttpURLConnectionFactory.a(str2);
                    try {
                        a.setConnectTimeout(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
                        a.setInstanceFollowRedirects(false);
                        a.setReadTimeout(CustomEventBannerAdapter.DEFAULT_BANNER_TIMEOUT_DELAY);
                        a.setUseCaches(false);
                        if (b != null) {
                            a.setRequestMethod(b.toString());
                        }
                        a(a, httpRequest);
                        String str3 = httpRequest.d;
                        if (!TextUtils.isEmpty(str3)) {
                            Logger.d(Logger.NETWORK_TAG, "request body: " + str3);
                            byte[] bytes = str3.getBytes();
                            if ("gzip".equals(httpRequest.c == null ? null : httpRequest.c.getString("Content-Encoding"))) {
                                int length = bytes.length;
                                OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                GZIPOutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
                                gZIPOutputStream.write(bytes);
                                gZIPOutputStream.close();
                                bytes = byteArrayOutputStream.toByteArray();
                                Logger.v(Logger.NETWORK_TAG, "gzipped request from " + length + " bytes down to " + bytes.length + " bytes");
                            }
                            a.setDoOutput(true);
                            a.setFixedLengthStreamingMode(bytes.length);
                            a.getOutputStream().write(bytes);
                        }
                        i = a.getResponseCode();
                        if (a(i2, i)) {
                            String headerField = a.getHeaderField("Location");
                            Logger.i(Logger.NETWORK_TAG, a(b, i2, i, a.getContentType(), httpRequest.b, str2, headerField));
                            Long valueOf = a.getHeaderFieldDate("Date", -1) == -1 ? null : Long.valueOf(a.getHeaderFieldDate("Date", -1));
                            HttpRequestChainElement httpRequestChainElement = (HttpRequestChainElement) this.c.a.get();
                            httpRequestChainElement.b = str2;
                            httpRequestChainElement.c = i;
                            httpRequestChainElement.a = headerField;
                            httpRequestChainElement.d = valueOf;
                            arrayList.add(httpRequestChainElement);
                            i2++;
                            str2 = headerField;
                            httpURLConnection3 = a;
                        } else {
                            if ((i == 200 ? 1 : null) != null) {
                                Logger.d(Logger.NETWORK_TAG, a(b, i2, i, a.getContentType(), httpRequest.b, str2, null));
                                httpURLConnection = a;
                            } else {
                                Logger.i(Logger.NETWORK_TAG, a(b, i2, i, a.getContentType(), httpRequest.b, str2, null));
                                httpURLConnection = a;
                            }
                            httpResponse = (HttpResponse) this.b.a.get();
                            httpResponse.a = httpURLConnection;
                            httpResponse.d = arrayList;
                            httpResponse.b = i;
                            if (httpURLConnection != null) {
                                str = null;
                            } else {
                                str = String.valueOf(httpURLConnection.getURL());
                            }
                            httpResponse.c = str;
                            return httpResponse;
                        }
                    } catch (Throwable e) {
                        th = e;
                        httpURLConnection2 = a;
                    } catch (Throwable e2) {
                        th = e2;
                        httpURLConnection2 = a;
                    } catch (Throwable e22) {
                        th = e22;
                        httpURLConnection2 = a;
                    } catch (Throwable e222) {
                        th = e222;
                        httpURLConnection2 = a;
                    }
                } catch (Throwable e2222) {
                    th = e2222;
                    httpURLConnection2 = httpURLConnection3;
                } catch (Throwable e22222) {
                    th = e22222;
                    httpURLConnection2 = httpURLConnection3;
                } catch (Throwable e222222) {
                    th = e222222;
                    httpURLConnection2 = httpURLConnection3;
                } catch (Throwable e2222222) {
                    th = e2222222;
                    httpURLConnection2 = httpURLConnection3;
                }
            }
            httpURLConnection = httpURLConnection3;
        } catch (Throwable e22222222) {
            th = e22222222;
            httpURLConnection2 = null;
            Logger.w(Logger.NETWORK_TAG, jh.a(th));
            i = 601;
            httpURLConnection = httpURLConnection2;
            httpResponse = (HttpResponse) this.b.a.get();
            httpResponse.a = httpURLConnection;
            httpResponse.d = arrayList;
            httpResponse.b = i;
            if (httpURLConnection != null) {
                str = null;
            } else {
                str = String.valueOf(httpURLConnection.getURL());
            }
            httpResponse.c = str;
            return httpResponse;
        } catch (Throwable e222222222) {
            th = e222222222;
            httpURLConnection2 = null;
            Logger.d(Logger.NETWORK_TAG, jh.a(th));
            i = 602;
            httpURLConnection = httpURLConnection2;
            httpResponse = (HttpResponse) this.b.a.get();
            httpResponse.a = httpURLConnection;
            httpResponse.d = arrayList;
            httpResponse.b = i;
            if (httpURLConnection != null) {
                str = String.valueOf(httpURLConnection.getURL());
            } else {
                str = null;
            }
            httpResponse.c = str;
            return httpResponse;
        } catch (Throwable e2222222222) {
            th = e2222222222;
            httpURLConnection2 = null;
            Logger.d(Logger.NETWORK_TAG, jh.a(th));
            i = 603;
            httpURLConnection = httpURLConnection2;
            httpResponse = (HttpResponse) this.b.a.get();
            httpResponse.a = httpURLConnection;
            httpResponse.d = arrayList;
            httpResponse.b = i;
            if (httpURLConnection != null) {
                str = null;
            } else {
                str = String.valueOf(httpURLConnection.getURL());
            }
            httpResponse.c = str;
            return httpResponse;
        } catch (Throwable e22222222222) {
            th = e22222222222;
            httpURLConnection2 = null;
            Logger.w(Logger.NETWORK_TAG, jh.a(th));
            i = 600;
            httpURLConnection = httpURLConnection2;
            httpResponse = (HttpResponse) this.b.a.get();
            httpResponse.a = httpURLConnection;
            httpResponse.d = arrayList;
            httpResponse.b = i;
            if (httpURLConnection != null) {
                str = String.valueOf(httpURLConnection.getURL());
            } else {
                str = null;
            }
            httpResponse.c = str;
            return httpResponse;
        }
        httpResponse = (HttpResponse) this.b.a.get();
        httpResponse.a = httpURLConnection;
        httpResponse.d = arrayList;
        httpResponse.b = i;
        if (httpURLConnection != null) {
            str = String.valueOf(httpURLConnection.getURL());
        } else {
            str = null;
        }
        httpResponse.c = str;
        return httpResponse;
    }

    private static void a(HttpURLConnection httpURLConnection, HttpRequest httpRequest) {
        Bundle bundle = httpRequest.c;
        if (bundle != null) {
            for (String str : bundle.keySet()) {
                String string = bundle.getString(str);
                if (string == null) {
                    for (String str2 : bundle.getStringArray(str)) {
                        Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + str2);
                        httpURLConnection.addRequestProperty(str, str2);
                    }
                } else {
                    Logger.v(Logger.NETWORK_TAG, "request header: " + str + ": " + string);
                    httpURLConnection.addRequestProperty(str, string);
                }
            }
        }
    }

    private static boolean a(int i, int i2) {
        if (i <= 0) {
            boolean z = i2 == 301 || i2 == 302;
            if (!z) {
                return false;
            }
        }
        return true;
    }

    private static String a(a aVar, int i, int i2, String str, String str2, String str3, String str4) {
        StringBuilder stringBuilder = new StringBuilder("HTTP");
        boolean a = a(i, i2);
        if (a) {
            stringBuilder.append(" redirect count ").append(i).append(',');
        }
        stringBuilder.append(" response code ").append(i2).append(", content-type ").append(str).append(" for ").append(aVar).append(" to");
        if (i > 0) {
            stringBuilder.append(" original URL ").append(str2).append(',');
        }
        stringBuilder.append(" requested URL ").append(str3);
        if (a) {
            stringBuilder.append(", next URL ").append(str4);
        }
        return stringBuilder.toString();
    }
}
