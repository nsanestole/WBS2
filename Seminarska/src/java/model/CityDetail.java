/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package model;

/**
 *
 * @author martin
 */
public class CityDetail {
    private String Name;
    private String population;
    private String postalCode;
    private String leader;

    public CityDetail(String Name, String population, String postalCode, String leader) {
        this.Name = Name;
        this.population = population;
        this.postalCode = postalCode;
        this.leader = leader;
    }

    public CityDetail() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
    
    
}
