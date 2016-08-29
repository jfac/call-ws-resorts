/*
 * Clase que realiza el llamado del webservice.
 * 
 */

package athie.call.resorts.wsdl;

import athie.call.resorts.beans.ResortsCountry;
import athie.call.resorts.beans.ResortsState;
import athie.call.resorts.beans.UnitsAvailable;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.HttpsURLConnection;
import xml.utils.XmlException;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
public class callWsdl implements Serializable{
    
    private final String unit_available
                = "https://www.securevacancy.com/vacancyservices.asmx/UnitsAvailable";
    private final String ResortProfile
                = "https://www.securevacancy.com/vacancyservices.asmx/ResortProfile";
    private final String country
                = "https://www.securevacancy.com/vacancyservices.asmx/ListOfCountriesRequest";
    private final String state
                = "https://www.securevacancy.com/vacancyservices.asmx/ListOfStatesCodesRequest";
    private List<ResortsCountry> lstCountry;
    private List<ResortsState> lstState;
    private List<UnitsAvailable> lstUnitAvailable;
    private final char c = '&';
    private final HttpsConection httpsCon;
    
    public callWsdl(){
        httpsCon = new HttpsConection();
        lstUnitAvailable = new ArrayList<>();
        lstCountry = new ArrayList<>();
        lstState = new ArrayList<>();
    }
    
    /**
     * Llama la lista de todos los paises
     * @return lista de los paises
     */
    public List<ResortsCountry> getCountry(){
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            sb.append(country)
            .append(getLogin());
            callWsdlCountry(sb.toString());
            return lstCountry;
        } catch (XmlException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCountry;
    }
    
    /**
     * Obtiene los estados dependiendo del pais
     * @param country  pais requerido para buscar los estados
     * @return Lista de los estados
     */
    public List<ResortsState> getState(String country){
        StringBuilder sb;
        try {
            sb = new StringBuilder();
            sb.append(state)
                    .append(getLogin()).append(c)
                    .append("Country=").append(country);
            callWsdlState(sb.toString());
            return lstState;
        } catch (XmlException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstState;
    }
    
    /**
     * Devuelve el profile del resort
     * @param ResortID id del resort a buscar
     * @return 
     */
    public UnitsAvailable getResortProfile(int ResortID){
        StringBuilder sb;
        sb = new StringBuilder();
        //Creamos la url con los parametros que requerimos
        sb.append(ResortProfile)
                .append(getLogin())
                .append(c)
                .append("ResortID=").append(ResortID);
        return null;
    }
    public List<UnitsAvailable> getUnitAvailable(String ResortName, String ResortCity, String ResortCountry,
            String TravelDate1, String TravelDate2){
        StringBuilder sb;
        try {
            sb = new StringBuilder();
        //Creamos la url con los parametros que requerimos
            sb.append(unit_available)
                    .append(getLogin()).append(c)
                    .append("ResortName=").append(ResortName).append(c)
                    .append("ResortCity=").append(ResortCity).append(c)
                    .append("ResortCountry=").append(ResortCountry).append(c)
                    .append("TravelDate1=").append(TravelDate1).append(c)
                    .append("TravelDate2=").append(TravelDate2);
            callWsdlUnitsAvailable(sb.toString());
            return lstUnitAvailable;
        } catch (IOException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XmlException ex) {
            Logger.getLogger(callWsdl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstUnitAvailable;
        
    }
    
    private String getLogin(){
        StringBuilder sbLogin = new StringBuilder();
        sbLogin.append("?LoginUser=").append("usuer").append(c)
        .append("LoginPassword=").append("password"); 
        return sbLogin.toString();
    }
    
    /**
     * Realiza el llamado al web service 
     */
    private void callWsdlUnitsAvailable(String url) throws IOException, XmlException {
        HttpsURLConnection con = null;
        con = httpsCon.getHttpsURLConnection(url);
        DataSet set = new DataSet(con.getInputStream());
        lstUnitAvailable = set.getLstUnitsAvailable();
    }
    
    private void callWsdlCountry(String url) throws XmlException, IOException {
        HttpsURLConnection con = null;
        //Creamos la conexion https
        con = httpsCon.getHttpsURLConnection(url);
        DataSet set = new DataSet(con.getInputStream());
        lstCountry = set.getLstCountry();
    }
    
    private void callWsdlState(String url) throws XmlException, IOException {
        HttpsURLConnection con = null;
        //Creamos la conexion https
        con = httpsCon.getHttpsURLConnection(url);
        DataSet set = new DataSet(con.getInputStream());
        lstState = set.getLstState();
    }
    
}
