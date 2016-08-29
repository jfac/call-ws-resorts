/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts.beans;

import java.io.Serializable;

/**
 *
 * @author Jose Fermin Athie Campollo
 */
public interface I_ResortState extends Serializable {

    /**
     * @return the country
     */
    String getCountry();

    /**
     * @return the State
     */
    String getState();

    /**
     * @return the StateCode
     */
    String getStateCode();

    /**
     * @param country the country to set
     */
    void setCountry(String country);

    /**
     * @param State the State to set
     */
    void setState(String State);

    /**
     * @param StateCode the StateCode to set
     */
    void setStateCode(String StateCode);
    
}
