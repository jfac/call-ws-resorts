/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts;

import athie.call.resorts.beans.ResortsCountry;
import athie.call.resorts.beans.ResortsState;
import athie.call.resorts.beans.UnitsAvailable;
import athie.call.resorts.wsdl.DataSet;
import athie.call.resorts.wsdl.callWsdl;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import xml.utils.XmlException;

/**
 *
 * @author oem
 */
public class filtroxmls {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            File dataFile = new File("/home/oem/Documentos/javaxb/xml.xsd");
            DataSet dataSet = new DataSet(dataFile);
            List<UnitsAvailable> lstXmlBean = dataSet.getLstUnitsAvailable();
            dataSet.displayLst();
            dataSet.displayCountryAndStates();
            dataSet.displayBean(dataSet.getBeanUnitsAvailable());
        } catch (XmlException ex) {
            Logger.getLogger(filtroxmls.class.getName()).log(Level.SEVERE, null, ex);
        }
//        callWsdl wsdl = new callWsdl();
//        List<ResortsCountry> lstCountry = wsdl.getCountry();
//        List<ResortsState> lstState = wsdl.getState("Mexico");
//        for (ResortsCountry country : lstCountry) {
//            System.out.println(country.getCountry());
//            for (ResortsState state : lstState) {
//                if(state.getCountry().toString().equals(country.getCountry())){
//                    System.out.println("States");
//                    System.out.println(state.getStateCode());
//                    System.out.println(state.getState());
//                }
//            }
//            System.out.println("X---------------------------------X");
//        }
        
    }
    
}
