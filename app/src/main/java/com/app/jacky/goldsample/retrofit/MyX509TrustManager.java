package com.app.jacky.goldsample.retrofit;

import android.content.Context;
import android.util.Log;

import com.app.jacky.goldsample.R;
import com.app.jacky.goldsample.logger.LoggerUtil;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

class MyX509TrustManager implements X509TrustManager {

    private Context mContext;
    public MyX509TrustManager(Context mContext) {
        LoggerUtil.d("New MyX509TrustManager.");
        this.mContext = mContext;
    }

    @Override
    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

    }

    @Override
    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        if (chain == null) {
            throw new CertificateException("checkServerTrusted: X509Certificate array is null");
        }
        if (chain.length < 1) {
            throw new CertificateException("checkServerTrusted: X509Certificate is empty");
        }
        if (!(null != authType && authType.equals("ECDHE_RSA"))) {
            throw new CertificateException("checkServerTrusted: AuthType is not ECDHE_RSA");
        }

        //检查所有证书
        try {
            TrustManagerFactory factory = TrustManagerFactory.getInstance("X509");
            factory.init((KeyStore) null);
            for (TrustManager trustManager : factory.getTrustManagers()) {
                ((X509TrustManager) trustManager).checkServerTrusted(chain, authType);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        //获取本地证书中的信息
        String clientEncoded = "";
        String clientSubject = "";
        String clientIssUser = "";

        CertificateFactory certificateFactory = CertificateFactory.getInstance("X.509");
        InputStream inputStream = mContext.getResources().openRawResource(R.raw.baidu);
        X509Certificate clientCertificate = (X509Certificate) certificateFactory.generateCertificate(inputStream);
        clientEncoded = new BigInteger(1, clientCertificate.getPublicKey().getEncoded()).toString(16);
        clientSubject = clientCertificate.getSubjectDN().getName();
        clientIssUser = clientCertificate.getIssuerDN().getName();

        Log.d("MyX509TrustManager","Client SSL:" + " " + clientEncoded + " " + clientSubject + " " + clientIssUser);


        //获取网络中的证书信息
        X509Certificate certificate = chain[0];
        PublicKey publicKey = certificate.getPublicKey();
        String serverEncoded = new BigInteger(1, publicKey.getEncoded()).toString(16);

        if (!clientEncoded.equals(serverEncoded)) {
            throw new CertificateException("server's PublicKey is not equals to client's PublicKey");
        }
        String subject = certificate.getSubjectDN().getName();
        if (!clientSubject.equals(subject)) {
            throw new CertificateException("server's subject is not equals to client's subject");
        }
        String issuser = certificate.getIssuerDN().getName();
        if (!clientIssUser.equals(issuser)) {
            throw new CertificateException("server's issuser is not equals to client's issuser");
        }
    }

    @Override
    public X509Certificate[] getAcceptedIssuers() {
        return new X509Certificate[0];
    }
}
