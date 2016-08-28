/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts;

import athie.call.resorts.beans.UnitsAvailable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
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
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author oem
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws KeyManagementException {
        try {
            // TODO code application logic here
            new test().callWsdl();
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getCause());
        }
    }
    private TrustManager[ ] get_trust_mgr() {
	     TrustManager[ ] certs = new TrustManager[ ] {
	        new X509TrustManager() {
	           public X509Certificate[ ] getAcceptedIssuers() { return null; }
	           public void checkClientTrusted(X509Certificate[ ] certs, String t) { }
	           public void checkServerTrusted(X509Certificate[ ] certs, String t) { }
	         }
	      };
	      return certs;
	  }
    
    private void callWsdl() throws IOException, Exception{
        StringBuilder sb = new StringBuilder();
        //Creamos la url con los parametros que requerimos
        final char c = '&';
        final String unit_available = 
                "https://www.securevacancy.com/vacancyservices.asmx/UnitsAvailable";
        sb.append(unit_available);
        sb.append("?LoginUser=").append("vbassol").append(c);
        sb.append("LoginPassword=").append("93Kg6N").append(c);
        sb.append("ResortName=").append(c);
        sb.append("ResortCity=").append(c);
        sb.append("ResortCountry=").append("mexico").append(c);
        sb.append("TravelDate1=").append(c);
        sb.append("TravelDate2=");

        //Creamos la coneccion de https
        HttpsURLConnection con = getHttpsURLConnection(sb.toString());
        Document doc = parseXML(con.getInputStream());
        setBean(doc);
        
        con.disconnect();
    }
    /**
     * Regresa la conexion por https
     * @param s_url
     * @return 
     */
    private HttpsURLConnection getHttpsURLConnection(String s_url) {
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
                public boolean verify(String host, SSLSession sess) {
                    if (host.equals("localhost")) {
                        return true;
                    } else {
                        return false;
                    }
                }
            });
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (KeyManagementException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MalformedURLException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    /**
     * Realiza el parse de xml a un objeto
     * @param doc
     * @return 
     */
    private UnitsAvailable jaxbXMLToObject(InputStream inp) {
        try {
            writeDoc(parseXML(inp));
            Schema schema = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(
                            new File(
                                    System.getProperty("user.dir").toString()+"/xml.xsd"
                            )
                    );
            JAXBContext context = JAXBContext.newInstance(UnitsAvailable.class);
            Unmarshaller un = context.createUnmarshaller();
            un.setSchema(schema);
            //System.out.println(doc.getDocumentURI());
            UnitsAvailable emp = (UnitsAvailable) un.unmarshal(inp);
            return emp;
        } catch (JAXBException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
        return null;
    }
    
    /**
     *
     * @param stream
     * @return
     * @throws Exception
     */
    private Document parseXML(InputStream stream) throws Exception {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();
            doc = objDocumentBuilder.parse(stream);
        } catch (Exception ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }

        return doc;
    }
    private void writeDoc(Document doc) {
        try {
            String path = System.getProperty("user.dir").toString()+"/xml.xsd";
            TransformerFactory factory = TransformerFactory.newInstance();
            Transformer transformer = factory.newTransformer();
            Result result = new StreamResult(new File(path));
            Source source = new DOMSource(doc);
            transformer.transform(source, result);
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        } catch (TransformerException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex.getMessage());
        }
    }
    
    private UnitsAvailable setBean(Document doc) {
        UnitsAvailable bean = new UnitsAvailable();
        doc.getDocumentElement().normalize();
        Element docEle = doc.getDocumentElement();
        NodeList nl = docEle.getChildNodes();
        if (nl != null && nl.getLength() > 0) {
            for (int i = 0; i < nl.getLength(); i++) {
                            Node nNode = nl.item(i);
                            System.out.println(nNode.getNodeName());
                if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nl.item(i);
                    if (el.getNodeName().contains("NewDataSet")) {
                        bean.setInvType(el.getElementsByTagName("UnitAvailable").item(0).getTextContent());
                        System.out.println(bean.getInvType());
//                        String phone = el.getElementsByTagName("phone").item(0).getTextContent();
//                        String email = el.getElementsByTagName("email").item(0).getTextContent();
//                        String area = el.getElementsByTagName("area").item(0).getTextContent();
//                        String city = el.getElementsByTagName("city").item(0).getTextContent();
                    }
                }
            }
        }
        //NodeList descNodes = doc.getElementsByTagName("UnitAvailable");
//        for (int i = 0; i < descNodes.getLength(); i++) {
//            System.out.println("Nodos");
//            Node nNode = descNodes.item(i);
//            System.out.println("\n Current Element: " + nNode.getNodeName());
//            System.out.println("\n Current value: " + nNode.getNodeValue());
//            System.out.println("\n Current text content : " + nNode.getTextContent());
//            //if (nNode.getNodeType() == Node.ELEMENT_NODE) {
//                Element eElement = (Element) nNode;
//                String context = eElement.getElementsByTagName("ResortName")
//                        .item(i)
//                        .getTextContent();
//                if (!context.isEmpty()) {
//                    System.out.println("ResortName: " + context);
//                }
//            //} else {
//                System.out.println(nNode.getNodeType());
//            //}
//        }
        return bean;
    }
    
}
