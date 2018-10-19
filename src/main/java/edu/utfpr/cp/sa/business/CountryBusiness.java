package edu.utfpr.cp.sa.business;

import java.util.Set;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.entity.Country;

public class CountryBusiness {
    
    public boolean create(Country country) throws Exception {

        if (this.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) 
            throw new IllegalArgumentException("There already is a country with this name!");
        
        else {
            new CountryDAO().create(country);
            return true;
        }
            
    }

    public Set<Country> read() {
        return new CountryDAO().read();
    }

    public boolean update(Country country) throws Exception {
        if (this.read().stream().map(Country::getName).anyMatch(e -> e.equals(country.getName()))) 
            throw new IllegalArgumentException("There already is a country with this name!");

        else {
            new CountryDAO().update(country);
            return true;
        }
    }

    public boolean delete(Long id) {
        return new CountryDAO().delete(id);
    }

}