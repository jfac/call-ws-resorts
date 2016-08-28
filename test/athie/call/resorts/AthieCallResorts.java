/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athie.call.resorts;

import athie.call.resorts.beans.ResortsBean;
import athie.call.resorts.beans.UnitsAvailable;
import java.awt.List;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author oem
 */
public class AthieCallResorts {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            //new AthieCallResorts().testIt();
            
            JAXBContext jc = JAXBContext.newInstance(ResortsBean.class);
            
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            File xml = new File("/home/oem/Documentos/javaxb/xml.xsd");
            ResortsBean dataSet = (ResortsBean) unmarshaller.unmarshal(xml);
            
            Marshaller marshaller = jc.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(dataSet, System.out);
        } catch (JAXBException ex) {
            Logger.getLogger(AthieCallResorts.class.getName()).log(Level.SEVERE, null, ex);
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

	  private void testIt(){
              StringBuilder sb = new StringBuilder();
              final char c= '&';
	     final String https_url = "https://www.securevacancy.com/vacancyservices.asmx/ResortProfile?LoginUser=vbassol&LoginPassword=93Kg6N&ResortID=2257";
             final String unit_available = "https://www.securevacancy.com/vacancyservices.asmx/UnitsAvailable";
             sb.append(unit_available);
             sb.append("?LoginUser=").append("vbassol").append(c);
             sb.append("LoginPassword=").append("93Kg6N").append(c);
             sb.append("ResortName=").append(c);
             sb.append("ResortCity=").append(c);
             sb.append("ResortCountry=").append("mexico").append(c);
             sb.append("TravelDate1=").append(c);
             sb.append("TravelDate2=");
	     URL url;
	     try {

		    // Create a context that doesn't check certificates.
	            SSLContext ssl_ctx = SSLContext.getInstance("TLS");
	            TrustManager[ ] trust_mgr = get_trust_mgr();
	            ssl_ctx.init(null,                // key manager
	                         trust_mgr,           // trust manager
	                         new SecureRandom()); // random number generator
	            HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx.getSocketFactory());
                    System.out.println(sb.toString());
		    url = new URL(sb.toString());
		    HttpsURLConnection con = (HttpsURLConnection)url.openConnection();

		    // Guard against "bad hostname" errors during handshake.
	            con.setHostnameVerifier(new HostnameVerifier() {
	                public boolean verify(String host, SSLSession sess) {
	                    if (host.equals("localhost")) return true;
	                    else return false;
	                }
	            });

		    //dumpl all cert info
		    print_https_cert(con);
		    
		    //XML
		    print_xml(con);

		    //dump all the content
		    print_content(con);
                    
                    con.disconnect();

		 } catch (MalformedURLException e) {
                     e.printStackTrace();
		 } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
			e.printStackTrace();
		 }
	   }

	  private void print_https_cert(HttpsURLConnection con){
	     if(con!=null){

	     try {

		System.out.println("Response Code : " + con.getResponseCode());
		System.out.println("Cipher Suite : " + con.getCipherSuite());
		System.out.println("\n");

		Certificate[] certs = con.getServerCertificates();
		for(Certificate cert : certs){
		  System.out.println("Cert Type : " + cert.getType());
		  System.out.println("Cert Hash Code : " + cert.hashCode());
		  System.out.println("Cert Public Key Algorithm : " + cert.getPublicKey().getAlgorithm());
		  System.out.println("Cert Public Key Format : " + cert.getPublicKey().getFormat());
		  System.out.println("\n");
		}


	     } catch (SSLPeerUnverifiedException e) {
		  e.printStackTrace();
	     } catch (IOException e){
		  e.printStackTrace();
	     }
	   }
	  }

	  private void print_content(HttpsURLConnection con){
	    if(con!=null){

	    try {

		System.out.println("****** Content of the URL ********");
		BufferedReader br =
			new BufferedReader(
				new InputStreamReader(con.getInputStream()));

		String input;
		

		while ((input = br.readLine()) != null){
		   System.out.println(input);
		}
		br.close();

	     } catch (IOException e) {
		e.printStackTrace();
	     }
	   }
	  }
	  
          /**
           * Obtiene el dato en especifico del SOAP
           * @param con 
           */
	  private java.util.List print_xml(HttpsURLConnection con){
		  Document doc;
		try {
			doc = parseXML(con.getInputStream());
			NodeList descNodes = doc.getElementsByTagName("NewDataSet");
			for(int i=0; i<descNodes.getLength(); i++){
                            //System.out.println(descNodes.item(i).getNodeValue().toString());
                            
                            //System.out.println(descNodes.item(i).getTextContent());
                                
                                //resort.setId(Integer.valueOf(descNodes.item(i).getTextContent()));
                                //for(int i=0; i<descNodes.getLength(); i++){
                            System.out.println("Nodos");
                            Node nNode = descNodes.item(i);
                            System.out.println("\n Current Element: "+nNode.getNodeName());
                            if(nNode.getNodeType() == Node.ELEMENT_NODE ){
                                Element eElement = (Element) nNode;
                                String context = eElement.getElementsByTagName("UnitAvailable")
                                        .item(i)
                                        .getTextContent();
                                if(!context.isEmpty()){
                                 System.out.println("UnitAvailable: "+  context );
                                }
                            }
                            else
                            {
                                System.out.println(nNode.getNodeType());
                            }
			//}
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
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
	  private Document parseXML(InputStream stream) throws Exception
          {
              DocumentBuilderFactory objDocumentBuilderFactory = null;
              DocumentBuilder objDocumentBuilder = null;
              Document doc = null;
              try {
                  objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
                  objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

                  doc = objDocumentBuilder.parse(stream);
              } catch (Exception ex) {
                  throw ex;
              }

              return doc;
          }
    
}
