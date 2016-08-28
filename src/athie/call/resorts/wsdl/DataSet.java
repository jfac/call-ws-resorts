/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts.wsdl;

import athie.call.resorts.beans.ResortsCountry;
import athie.call.resorts.beans.ResortsState;
import athie.call.resorts.beans.UnitsAvailable;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xml.utils.XmlDocument;
import xml.utils.XmlException;
import xml.utils.XmlNode;
import xml.utils.ResortsXml;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
public class DataSet implements Serializable{
    private final List<UnitsAvailable> lstResortXml;
    private final XmlNode root;
    List<ResortsCountry> lstCountrys;
    List<ResortsState> lstState;
    
    /**
     * Carga la clase mediante un File
     * @param xml Ruta del xml
     * @throws XmlException 
     */
    public DataSet(File xml) throws XmlException {
        lstResortXml = new ArrayList<>();
        lstCountrys = new ArrayList<>();
        lstState = new ArrayList<>();
        XmlDocument document = new XmlDocument();
        root = document.parse(xml);
    }
    
    /**
     * Carga la clase mediante el stream
     * @param wsdl stream del xml
     * @throws XmlException 
     */
    public DataSet(InputStream wsdl) throws XmlException {
        lstResortXml= new ArrayList<>();
        lstCountrys = new ArrayList<>();
        lstState = new ArrayList<>();
        XmlDocument document = new XmlDocument();
        root = document.parse(wsdl);
    }
    
    /**
     * Obtiene las listas de las unidades disponibles.
     * @return lista de unidades disponibles.
     */
    public List<UnitsAvailable> getLstUnitsAvailable(){
        try {
            loadFromXmlUnitsAvailable();
            return lstResortXml;
        } catch (XmlException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstResortXml;
    }
    
    /**
     * Metodo publico que obtiene las unidades disponibles-
     * @return regresa el bean de las unidades disponibles-
     * @throws XmlException 
     */
    public UnitsAvailable getBeanUnitsAvailable() throws XmlException{
        UnitsAvailable resort = new UnitsAvailable();
        XmlNode ratesLevelNode = root.getNodesByTagName("NewDataSet").get(0);
        List<XmlNode> rates = ratesLevelNode.getNodesByTagName("UnitAvailable");
        for (XmlNode xmlNode : rates) {
            if (!xmlNode.getNodeValue().toString().isEmpty()) {
                resort= fillXmlBean(xmlNode);
            }
        }
        return resort;
    }
    
    /**
     * Metodo publico para obtener los paises
     * @return Lista de los paises disponibles
     */
    public List<ResortsCountry> getLstCountry(){
        try {
            System.out.println(lstCountrys.isEmpty());
            loadFromXmlCountry();
            return lstCountrys;
        } catch (XmlException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstCountrys;
    }
    
    /**
     * Metodo publico para obtener los estados dependiendo del pais
     * @return de los estados del pais
     */
    public List<ResortsState> getLstState(){
        try {
            loadFromXmlState();
            return lstState;
        } catch (XmlException ex) {
            Logger.getLogger(DataSet.class.getName()).log(Level.SEVERE, null, ex);
        }
        return lstState;
    }
    
    public void displayLst() {
        //System.out.println("WeekNumber: " + UnitAvailable);
        System.out.println("ResortXml");
        for (UnitsAvailable elBean : lstResortXml) {
            System.out.println(elBean.getInvType());
            System.out.println(elBean.getResortId());
            System.out.println(elBean.getResortName());
            System.out.println(elBean.getResortHighLights());
            System.out.println(elBean.getResortAddress1());
            System.out.println(elBean.getResortAddress2());
            System.out.println(elBean.getResortCity());
            System.out.println(elBean.getResortRegion());
            System.out.println(elBean.getResortPhone());
            System.out.println(elBean.getUnitID());
            System.out.println(elBean.getUnitType());
            System.out.println(elBean.getMaxOccupancy());
            System.out.println(elBean.getPrivacyOccupancy());
            System.out.println(elBean.getInventoryID());
            System.out.println(elBean.getCheckInDate());
            System.out.println(elBean.getCheckOutDate());
            System.out.println(elBean.getPrice());
            System.out.println(elBean.getAllInclusive());
            System.out.println(elBean.getCheckInDate());
            System.out.println(elBean.getNights());
            System.out.println(elBean.getImagePath());
            System.out.println(elBean.getImages());
        }
        System.out.println("X-------------------------------------------------X");
//        System.out.println("Level B");
//        for (String rate : employeeRatesLevelB) {
//            System.out.println("\tRate: " + rate);
//        }
    }
    
    public void displayBean(UnitsAvailable elBean) {
        //System.out.println("WeekNumber: " + UnitAvailable);
        System.out.println("ResortXml");
        //for (xmlBean elBean : lstResortXml) {
            System.out.println(elBean.getInvType());
            System.out.println(elBean.getResortId());
            System.out.println(elBean.getResortName());
            System.out.println(elBean.getResortHighLights());
            System.out.println(elBean.getResortAddress1());
            System.out.println(elBean.getResortAddress2());
            System.out.println(elBean.getResortCity());
            System.out.println(elBean.getResortRegion());
            System.out.println(elBean.getResortPhone());
            System.out.println(elBean.getUnitID());
            System.out.println(elBean.getUnitType());
            System.out.println(elBean.getMaxOccupancy());
            System.out.println(elBean.getPrivacyOccupancy());
            System.out.println(elBean.getInventoryID());
            System.out.println(elBean.getCheckInDate());
            System.out.println(elBean.getCheckOutDate());
            System.out.println(elBean.getPrice());
            System.out.println(elBean.getAllInclusive());
            System.out.println(elBean.getCheckInDate());
            System.out.println(elBean.getNights());
            System.out.println(elBean.getImagePath());
            System.out.println(elBean.getImages());
        //}
        System.out.println("X-------------------------------------------------X");
    }
    
    /**
     * 
     */
    public void displayCountryAndStates(){
        System.out.println("Pais");
        for (ResortsCountry country : lstCountrys) {
            System.out.println(country.getCountry());
            for(ResortsState state : lstState){
                if(state.getCountry().toString().equals(country.getCountry())){
                    System.out.println("States");
                    System.out.println(state.getStateCode());
                    System.out.println(state.getState());
                }
            }
            System.out.println("X---------------------------------X");
        }
    }

    /**
     * Carga las unidades disponibles de los resorts desde el xml
     * @throws XmlException 
     */
    private void loadFromXmlUnitsAvailable() throws XmlException {
        XmlNode ratesLevelNode = root.getNodesByTagName("NewDataSet").get(0);
        List<XmlNode> rates = ratesLevelNode.getNodesByTagName("UnitAvailable");
        for (XmlNode xmlNode : rates) {
            if (!xmlNode.getNodeValue().toString().isEmpty()) {
                lstResortXml.add(fillXmlBean(xmlNode));
            }
        }
    }
    
    /**
     * Se obtiene el bean de las unidades disponibles de los resorts desde el xml
     * @param xmlNode El nodo parseado desde un inputstream para obtener el xml
     * @return UnitsAvailable
     * @throws XmlException 
     */
    private UnitsAvailable fillXmlBean(XmlNode xmlNode) throws XmlException {
        UnitsAvailable resort = new UnitsAvailable();
        resort.setInvType(xmlNode.getTagValue(ResortsXml.InvType));
        resort.setResortId(xmlNode.getTagValue(ResortsXml.ResortId));
        resort.setResortName(xmlNode.getTagValue(ResortsXml.ResortName));
        resort.setResortHighLights(xmlNode.getTagValue(ResortsXml.ResortHighLights));
        resort.setResortAddress1(xmlNode.getTagValue(ResortsXml.ResortAddress1));
        resort.setResortAddress2(xmlNode.getTagValue(ResortsXml.ResortAddress2));
        resort.setResortAddress3(xmlNode.getTagValue(ResortsXml.ResortAddress3));
        resort.setResortCity(xmlNode.getTagValue(ResortsXml.ResortCity));
        resort.setResortState(xmlNode.getTagValue(ResortsXml.ResortState));
        resort.setResortCountry(xmlNode.getTagValue(ResortsXml.ResortCountry));
        resort.setResortRegion(xmlNode.getTagValue(ResortsXml.ResortRegion));
        resort.setResortPhone(xmlNode.getTagValue(ResortsXml.ResortPhone));
        resort.setUnitType(xmlNode.getTagValue(ResortsXml.UnitType));
        resort.setInventoryID(xmlNode.getTagValue(ResortsXml.InventoryID));
        resort.setCheckInDate(xmlNode.getTagValue(ResortsXml.CheckInDate));
        resort.setCheckOutDate(xmlNode.getTagValue(ResortsXml.CheckOutDate));
        resort.setImagePath(xmlNode.getTagValue(ResortsXml.ImagePath));
        resort.setImages(xmlNode.getTagValue(ResortsXml.Images));
        resort.setMaxOccupancy(xmlNode.getIntTagValue(ResortsXml.MaxOccupancy));
        resort.setPrivacyOccupancy(xmlNode.getIntTagValue(ResortsXml.PrivacyOccupancy));
        resort.setNights(xmlNode.getIntTagValue(ResortsXml.Nights));
        resort.setUnitID(xmlNode.getIntTagValue(ResortsXml.UnitID));
        resort.setPrice(xmlNode.getDoubleTagValue(ResortsXml.Price));
        resort.setAllInclusive(xmlNode.getIntTagValue(ResortsXml.AllInclusive));
        return resort;
    }
    
    /**
     * Carga los paises de los resorts disponibles.
     * @throws XmlException 
     */
    private void loadFromXmlCountry() throws XmlException {
        XmlNode ratesLevelNode = root.getNodesByTagName("NewDataSet").get(0);
        List<XmlNode> xmlCountry = ratesLevelNode.getNodesByTagName("Countries");
        for (XmlNode xmlNode : xmlCountry) {
            ResortsCountry country =  new ResortsCountry();
            if (!xmlNode.getNodeValue().isEmpty()) {
                country.setCountry(xmlNode.getTagValue("Country"));
                lstCountrys.add(country);
            }
        }
    }
    
    /**
     * Carga los estados de los paises de los resorts disponibles.
     * @param country El pais que se quiere buscar los estados
     * @throws XmlException 
     */
    private void loadFromXmlState() throws XmlException {
        XmlNode ratesLevelNode = root.getNodesByTagName("NewDataSet").get(0);
        List<XmlNode> xmlNodeState = ratesLevelNode.getNodesByTagName("StateCodes");
        for (XmlNode xmlNode : xmlNodeState) {
            ResortsState state = new ResortsState();
            if (!xmlNode.getNodeValue().isEmpty()) {
                state.setCountry(xmlNode.getTagValue("Country"));
                state.setStateCode(xmlNode.getTagValue("StateCode"));
                state.setState(xmlNode.getTagValue("State"));
                lstState.add(state);
            }
        }
    }
    
}
