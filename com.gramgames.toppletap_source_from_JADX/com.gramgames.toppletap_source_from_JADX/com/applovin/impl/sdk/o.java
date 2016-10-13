package com.applovin.impl.sdk;

import com.applovin.sdk.AppLovinErrorCodes;
import com.applovin.sdk.AppLovinLogger;
import com.google.android.exoplayer.C;
import com.google.android.gms.nearby.messages.Strategy;
import com.mopub.common.Constants;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

class o {
    private final AppLovinSdkImpl a;
    private final AppLovinLogger b;

    o(AppLovinSdkImpl appLovinSdkImpl) {
        this.a = appLovinSdkImpl;
        this.b = appLovinSdkImpl.getLogger();
    }

    private int a(Throwable th) {
        if (th instanceof UnknownHostException) {
            return AppLovinErrorCodes.NO_NETWORK;
        }
        if (th instanceof SocketTimeoutException) {
            return AppLovinErrorCodes.FETCH_AD_TIMEOUT;
        }
        if (!(th instanceof IOException)) {
            return th instanceof JSONException ? -104 : -1;
        } else {
            String message = th.getMessage();
            return (message == null || !message.toLowerCase(Locale.ENGLISH).contains("authentication challenge")) ? -100 : 401;
        }
    }

    private HttpURLConnection a(String str, String str2, int i) {
        HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str).openConnection();
        httpURLConnection.setRequestMethod(str2);
        httpURLConnection.setConnectTimeout(i < 0 ? ((Integer) this.a.a(cb.o)).intValue() : i);
        if (i < 0) {
            i = ((Integer) this.a.a(cb.q)).intValue();
        }
        httpURLConnection.setReadTimeout(i);
        httpURLConnection.setDefaultUseCaches(false);
        httpURLConnection.setAllowUserInteraction(false);
        httpURLConnection.setUseCaches(false);
        httpURLConnection.setInstanceFollowRedirects(true);
        httpURLConnection.setDoInput(true);
        return httpURLConnection;
    }

    private static void a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (Exception e) {
            }
        }
    }

    private void a(String str, int i, String str2, p pVar) {
        this.b.d("ConnectionManager", i + " received from from \"" + str2 + "\": " + str);
        if (i < 200 || i >= Strategy.TTL_SECONDS_DEFAULT) {
            this.b.e("ConnectionManager", i + " error received from \"" + str2 + "\"");
            pVar.a(i);
            return;
        }
        JSONObject jSONObject = new JSONObject();
        if (!(i == AppLovinErrorCodes.NO_FILL || str == null || str.length() <= 2)) {
            jSONObject = new JSONObject(str);
        }
        pVar.a(jSONObject, i);
    }

    private void a(String str, String str2, int i, long j) {
        this.b.i("ConnectionManager", "Successful " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s" + " over " + q.a(this.a) + " to \"" + str2 + "\"");
    }

    private void a(String str, String str2, int i, long j, Throwable th) {
        this.b.e("ConnectionManager", "Failed " + str + " returned " + i + " in " + (((float) (System.currentTimeMillis() - j)) / 1000.0f) + " s" + " over " + q.a(this.a) + " to \"" + str2 + "\"", th);
    }

    private static void a(HttpURLConnection httpURLConnection) {
        if (httpURLConnection != null) {
            try {
                httpURLConnection.disconnect();
            } catch (Exception e) {
            }
        }
    }

    void a(String str, int i, p pVar) {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, i, null, true, pVar);
    }

    void a(String str, int i, JSONObject jSONObject, boolean z, p pVar) {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, i, jSONObject, z, pVar);
    }

    void a(String str, int i, boolean z, p pVar) {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET, i, null, z, pVar);
    }

    void a(String str, String str2, int i, JSONObject jSONObject, boolean z, p pVar) {
        String str3;
        HttpURLConnection a;
        Throwable th;
        int a2;
        Throwable th2;
        InputStream inputStream;
        if (str == null) {
            throw new IllegalArgumentException("No endpoint specified");
        } else if (str2 == null) {
            throw new IllegalArgumentException("No method specified");
        } else if (pVar == null) {
            throw new IllegalArgumentException("No callback specified");
        } else if (str.toLowerCase().startsWith(Constants.HTTP)) {
            if (!((Boolean) this.a.a(cb.be)).booleanValue() || str.contains("https://")) {
                str3 = str;
            } else {
                this.a.getLogger().w("ConnectionManager", "Plaintext HTTP operation requested; upgrading to HTTPS due to universal SSL setting...");
                str3 = str.replace("http://", "https://");
            }
            long currentTimeMillis = System.currentTimeMillis();
            InputStream inputStream2 = null;
            try {
                this.b.i("ConnectionManager", "Sending " + str2 + " request to \"" + str3 + "\"...");
                a = a(str3, str2, i);
                if (jSONObject != null) {
                    try {
                        String jSONObject2 = jSONObject.toString();
                        this.b.d("ConnectionManager", "Request to \"" + str3 + "\" is " + jSONObject2);
                        a.setRequestProperty("Content-Type", "application/json; charset=utf-8");
                        a.setDoOutput(true);
                        a.setFixedLengthStreamingMode(jSONObject2.getBytes(Charset.forName(C.UTF8_NAME)).length);
                        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(a.getOutputStream(), "UTF8"));
                        printWriter.print(jSONObject2);
                        printWriter.close();
                    } catch (Throwable th3) {
                        th = th3;
                        try {
                            a2 = a(th);
                            a(str2, str3, a2, currentTimeMillis, th);
                            pVar.a(a2);
                            a(inputStream2);
                            a(a);
                        } catch (Throwable th4) {
                            th2 = th4;
                            a(inputStream2);
                            a(a);
                            throw th2;
                        }
                    }
                }
                try {
                    a2 = a.getResponseCode();
                    if (a2 > 0) {
                        a(str2, str3, a2, currentTimeMillis);
                        if (z) {
                            inputStream = a.getInputStream();
                            try {
                                a(q.a(inputStream), a.getResponseCode(), str3, pVar);
                            } catch (MalformedURLException e) {
                                if (z) {
                                    try {
                                        pVar.a(-901);
                                        a(inputStream);
                                        a(a);
                                    } catch (Throwable th5) {
                                        inputStream2 = inputStream;
                                        th2 = th5;
                                        a(inputStream2);
                                        a(a);
                                        throw th2;
                                    }
                                }
                                pVar.a(new JSONObject(), -901);
                                a(inputStream);
                                a(a);
                            }
                        }
                        pVar.a(new JSONObject(), a2);
                        inputStream = null;
                    } else {
                        a(str2, str3, a2, currentTimeMillis, null);
                        pVar.a(a2);
                        inputStream = null;
                    }
                } catch (MalformedURLException e2) {
                    inputStream = null;
                    if (z) {
                        pVar.a(-901);
                        a(inputStream);
                        a(a);
                    }
                    pVar.a(new JSONObject(), -901);
                    a(inputStream);
                    a(a);
                }
                a(inputStream);
                a(a);
            } catch (Throwable th52) {
                a = null;
                th2 = th52;
                a(inputStream2);
                a(a);
                throw th2;
            }
        } else {
            this.b.userError("ConnectionManager", "Requested postback submission to non HTTP endpoint " + str + "; skipping...");
            pVar.a(AppLovinErrorCodes.INVALID_URL);
        }
    }

    void a(String str, JSONObject jSONObject, p pVar) {
        a(str, UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST, -1, jSONObject, true, pVar);
    }
}
