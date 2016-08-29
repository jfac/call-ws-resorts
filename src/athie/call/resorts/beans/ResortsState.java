/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package athie.call.resorts.beans;

import java.io.Serializable;

/**
 * Bean para obtener el estado por pais
 * @author Jose Fermin Athie Campollo
 */
public class ResortsState implements I_ResortState{
    private String country;
    private String StateCode;
    private String State;

    /**
     * @return the country
     */
    @Override
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the StateCode
     */
    @Override
    public String getStateCode() {
        return StateCode;
    }

    /**
     * @param StateCode the StateCode to set
     */
    @Override
    public void setStateCode(String StateCode) {
        this.StateCode = StateCode;
    }

    /**
     * @return the State
     */
    @Override
    public String getState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    @Override
    public void setState(String State) {
        this.State = State;
    }
}
