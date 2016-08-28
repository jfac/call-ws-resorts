/*
 * El salto de cetificados por https el codigo original se encuentra en
 * @see <a href="http://www.mkyong.com/webservices/jax-ws/how-to-bypass-certificate-checking-in-a-java-web-service-client/">
 * How to bypass certificate checking in a Java web service client</a> 
 */

package athie.call.resorts.wsdl;


import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 *
 * @author oem
 */
public class HttpsConection {
    
    /**
     * Realiza el certificado para el https
     * @return 
     */
    private TrustManager[] get_trust_mgr() {
        TrustManager[] certs;
        certs = new TrustManager[]{
            new X509TrustManager() {
                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                @Override
                public void checkClientTrusted(X509Certificate[] certs, String t) {
                }

                @Override
                public void checkServerTrusted(X509Certificate[] certs, String t) {
                }
            }
        };
        return certs;
    }
    
    /**
     * Regresa la conexion por https
     * @param s_url
     * @return 
     */
    public HttpsURLConnection getHttpsURLConnection(String s_url) {
        HttpsURLConnection con = null;
        try {
            // Create a context that doesn't check certificates.
            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
            TrustManager[] trust_mgr = get_trust_mgr();
            ssl_ctx.init(null, // key manager
                    trust_mgr, // trust manager
                    new SecureRandom()); // random number generator
            
            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
            
            URL url = new URL(s_url);
            
            con = (HttpsURLConnection) url.openConnection();
            
            // Guard against "bad hostname" errors during handshake.
            con.setHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String host, SSLSession sess) {
                    return host.equals("localhost");
                }
            });
        } catch (MalformedURLException ex) {
            Logger.getLogger(HttpsConection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | KeyManagementException | NoSuchAlgorithmException ex) {
            Logger.getLogger(HttpsConection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
}
