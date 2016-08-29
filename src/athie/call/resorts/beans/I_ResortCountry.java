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
public interface I_ResortCountry extends Serializable {

    /**
     * @return the Country
     */
    String getCountry();

    /**
     * @param Country the Country to set
     */
    void setCountry(String Country);
    
}
