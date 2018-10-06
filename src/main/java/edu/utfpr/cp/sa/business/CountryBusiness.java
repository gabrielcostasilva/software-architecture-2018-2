/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.cp.sa.business;

import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.dao.CountryDAO;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Leandro
 */
public class CountryBusiness {
    
    private CountryDAO countryDAO;    
    
    public void create(Country country) throws Exception {
        
        if (countryDAO.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) {
            throw new IllegalArgumentException("There already is a country with this name!");
        }else{
            countryDAO.create(country);
        }
    }
    
    public void update(Country country) {
        
        if (countryDAO.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) {
            throw new IllegalArgumentException("There already is a country with this name!");
        }else{
            countryDAO.update(country);
        }
    }
    
    
    public Set<Country> read(){
        HashSet<Country> set = new HashSet<>();
        
        try {
            set = (HashSet<Country>) countryDAO.read();
        } catch (Exception e) {
            System.out.println("Nao foram encontrado dados");
        }
        
        return set;
    }
    
    
    public boolean delete(Long id) {       
        
        if(countryDAO.delete(id)){
            return true;
        }
        
        return false;
    }
}
