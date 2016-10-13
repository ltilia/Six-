package com.chartboost.sdk.impl;

import com.applovin.sdk.AppLovinErrorCodes;
import com.mopub.common.Constants;
import com.mopub.volley.toolbox.HttpClientStack.HttpPatch;
import com.unity3d.ads.android.R;
import com.unity3d.ads.android.properties.UnityAdsConstants;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.json.simple.parser.Yytoken;

public class y implements x {
    private final a a;
    private final SSLSocketFactory b;

    public interface a {
        String a(String str);
    }

    public y() {
        this(null);
    }

    public y(a aVar) {
        this(aVar, null);
    }

    public y(a aVar, SSLSocketFactory sSLSocketFactory) {
        this.a = aVar;
        this.b = sSLSocketFactory;
    }

    public HttpResponse a(l<?> lVar, Map<String, String> map) throws IOException, a {
        String a;
        String d = lVar.d();
        HashMap hashMap = new HashMap();
        hashMap.putAll(lVar.i());
        hashMap.putAll(map);
        if (this.a != null) {
            a = this.a.a(d);
            if (a == null) {
                throw new IOException("URL blocked by rewriter: " + d);
            }
        }
        a = d;
        HttpURLConnection a2 = a(new URL(a), (l) lVar);
        for (String a3 : hashMap.keySet()) {
            a2.addRequestProperty(a3, (String) hashMap.get(a3));
        }
        a(a2, (l) lVar);
        ProtocolVersion protocolVersion = new ProtocolVersion("HTTP", 1, 1);
        if (a2.getResponseCode() == -1) {
            throw new IOException("Could not retrieve response code from HttpUrlConnection.");
        }
        StatusLine basicStatusLine = new BasicStatusLine(protocolVersion, a2.getResponseCode(), a2.getResponseMessage());
        HttpResponse basicHttpResponse = new BasicHttpResponse(basicStatusLine);
        if (a(lVar.a(), basicStatusLine.getStatusCode())) {
            basicHttpResponse.setEntity(a(a2));
        }
        for (Entry entry : a2.getHeaderFields().entrySet()) {
            if (entry.getKey() != null) {
                basicHttpResponse.addHeader(new BasicHeader((String) entry.getKey(), (String) ((List) entry.getValue()).get(0)));
            }
        }
        return basicHttpResponse;
    }

    private static boolean a(int i, int i2) {
        return (i == 4 || ((100 <= i2 && i2 < 200) || i2 == AppLovinErrorCodes.NO_FILL || i2 == 304)) ? false : true;
    }

    private static HttpEntity a(HttpURLConnection httpURLConnection) {
        InputStream inputStream;
        HttpEntity basicHttpEntity = new BasicHttpEntity();
        try {
            inputStream = httpURLConnection.getInputStream();
        } catch (IOException e) {
            inputStream = httpURLConnection.getErrorStream();
        }
        basicHttpEntity.setContent(inputStream);
        basicHttpEntity.setContentLength((long) httpURLConnection.getContentLength());
        basicHttpEntity.setContentEncoding(httpURLConnection.getContentEncoding());
        basicHttpEntity.setContentType(httpURLConnection.getContentType());
        return basicHttpEntity;
    }

    protected HttpURLConnection a(URL url) throws IOException {
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
        return httpURLConnection;
    }

    private HttpURLConnection a(URL url, l<?> lVar) throws IOException {
        HttpURLConnection a = a(url);
        int t = lVar.t();
        a.setConnectTimeout(t);
        a.setReadTimeout(t);
        a.setUseCaches(false);
        a.setDoInput(true);
        if (Constants.HTTPS.equals(url.getProtocol()) && this.b != null) {
            ((HttpsURLConnection) a).setSSLSocketFactory(this.b);
        }
        return a;
    }

    static void a(HttpURLConnection httpURLConnection, l<?> lVar) throws IOException, a {
        switch (lVar.a()) {
            case Yytoken.TYPE_EOF /*-1*/:
                byte[] m = lVar.m();
                if (m != null) {
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST);
                    httpURLConnection.addRequestProperty("Content-Type", lVar.l());
                    DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
                    dataOutputStream.write(m);
                    dataOutputStream.close();
                }
            case Yylex.YYINITIAL /*0*/:
                httpURLConnection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_GET);
            case Yytoken.TYPE_LEFT_BRACE /*1*/:
                httpURLConnection.setRequestMethod(UnityAdsConstants.UNITY_ADS_REQUEST_METHOD_POST);
                b(httpURLConnection, lVar);
            case Yytoken.TYPE_RIGHT_BRACE /*2*/:
                httpURLConnection.setRequestMethod("PUT");
                b(httpURLConnection, lVar);
            case Yytoken.TYPE_LEFT_SQUARE /*3*/:
                httpURLConnection.setRequestMethod("DELETE");
            case Yytoken.TYPE_RIGHT_SQUARE /*4*/:
                httpURLConnection.setRequestMethod("HEAD");
            case Yytoken.TYPE_COMMA /*5*/:
                httpURLConnection.setRequestMethod("OPTIONS");
            case Yytoken.TYPE_COLON /*6*/:
                httpURLConnection.setRequestMethod("TRACE");
            case R.styleable.Toolbar_contentInsetLeft /*7*/:
                httpURLConnection.setRequestMethod(HttpPatch.METHOD_NAME);
                b(httpURLConnection, lVar);
            default:
                throw new IllegalStateException("Unknown method type.");
        }
    }

    private static void b(HttpURLConnection httpURLConnection, l<?> lVar) throws IOException, a {
        byte[] q = lVar.q();
        if (q != null) {
            httpURLConnection.setDoOutput(true);
            httpURLConnection.addRequestProperty("Content-Type", lVar.p());
            DataOutputStream dataOutputStream = new DataOutputStream(httpURLConnection.getOutputStream());
            dataOutputStream.write(q);
            dataOutputStream.close();
        }
    }
}
