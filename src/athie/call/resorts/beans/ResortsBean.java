/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts.beans;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
@XmlRootElement(name="DataSet")
@XmlAccessorType(XmlAccessType.FIELD)
public class ResortsBean {
    
    @XmlElement(name="InvType")
    private String InvType;
    
    @XmlElement(name="ResortId")
    private String ResortId;
    
    @XmlElement(name="ResortName")
    private String ResortName;
    
    @XmlElement(name="ResortHighLights")
    private String ResortHighLights;
    
    @XmlElement(name="ResortAddress1")
    private String ResortAddress1;
    
    @XmlElement(name="ResortAddress2")
    private String ResortAddress2;
    
    @XmlElement(name="ResortAddress3")
    private String ResortAddress3;
    
    @XmlElement(name="ResortCity")
    private String ResortCity;
    
    @XmlElement(name="ResortState")
    private String ResortState;
    
    @XmlElement(name="ResortCountry")
    private String ResortCountry;
    
    @XmlElement(name="ResortRegion")
    private String ResortRegion;
    
    @XmlElement(name="ResortPhone")
    private String ResortPhone;
    
    @XmlElement(name="UnitType")
    private String UnitType;
    
    @XmlElement(name="InventoryID")
    private String InventoryID;
    
    @XmlElement(name="CheckInDate")
    private String CheckInDate;
    
    @XmlElement(name="CheckOutDate")
    private String CheckOutDate;
    
    @XmlElement(name="ImagePath")
    private String ImagePath;
    
    @XmlElement(name="InvType")
    private String Images;
    
    @XmlElement(name="MaxOccupancy")
    private int MaxOccupancy;
    
    @XmlElement(name="PrivacyOccupancy")
    private int PrivacyOccupancy;
    
    @XmlElement(name="Nights")
    private int Nights;
    
    @XmlElement(name="UnitID")
    private BigDecimal UnitID;
    
    @XmlElement(name="Price")
    private BigDecimal Price;
    
    @XmlElement(name="AllInclusive")
    private BigDecimal AllInclusive;
    
}