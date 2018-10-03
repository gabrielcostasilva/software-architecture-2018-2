package edu.utfpr.cp.sa.business;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.entity.Country;
import java.util.Set;

public class CountryBusiness {
    public boolean create(Country country) throws Exception{
        return new CountryDAO().create(country);
    }
    
    public Set<Country> read(){
        return new CountryDAO().read();
    }
    
    public boolean update(Country country){
        return new CountryDAO().update(country);
    }
    
    public boolean delete(Long id){
        return new CountryDAO().delete(id);
    }
}
