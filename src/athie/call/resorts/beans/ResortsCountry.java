/*
 * 
 */

package athie.call.resorts.beans;

/**
 * Bean para obtener los paises y los estados
 * @author Jose Fermin Athie Campollo
 */
public class ResortsCountry implements I_ResortCountry {
    private String Country;

    /**
     * @return the Country
     */
    @Override
    public String getCountry() {
        return Country;
    }

    /**
     * @param Country the Country to set
     */
    @Override
    public void setCountry(String Country) {
        this.Country = Country;
    }
    
}
