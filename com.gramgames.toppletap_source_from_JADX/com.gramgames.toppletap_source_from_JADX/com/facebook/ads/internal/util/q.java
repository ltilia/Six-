package com.facebook.ads.internal.util;

import java.net.Socket;
import java.security.KeyStore;
import java.security.cert.X509Certificate;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.conn.ssl.SSLSocketFactory;

public class q extends SSLSocketFactory {
    SSLContext a;

    class 1 implements X509TrustManager {
        final /* synthetic */ q a;

        1(q qVar) {
            this.a = qVar;
        }

        public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    }

    public q(KeyStore keyStore) {
        super(keyStore);
        this.a = SSLContext.getInstance("TLS");
        1 1 = new 1(this);
        this.a.init(null, new TrustManager[]{1}, null);
    }

    public Socket createSocket() {
        return this.a.getSocketFactory().createSocket();
    }

    public Socket createSocket(Socket socket, String str, int i, boolean z) {
        return this.a.getSocketFactory().createSocket(socket, str, i, z);
    }
}
