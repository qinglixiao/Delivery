package com.std.network.dns;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Description:
 * Author: lixiao
 * Create on: 2020-01-01.
 */
public class STSSLSocketManager {

    public static javax.net.ssl.SSLSocketFactory getSSLSocketFactory() {
        try {
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{getX509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static X509TrustManager getX509TrustManager() {
        return new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[]{};
            }

            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain,
                                           String authType) throws java.security.cert.CertificateException {
            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain,
                                           String authType) throws java.security.cert.CertificateException {
            }
        };
    }

}
