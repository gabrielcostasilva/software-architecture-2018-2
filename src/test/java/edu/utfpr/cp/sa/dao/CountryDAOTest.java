package edu.utfpr.cp.sa.dao;

import edu.utfpr.cp.sa.entity.Country;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class CountryDAOTest {
    

    @Test
    public void testCreate() {
        System.out.println("create");
        
        Country country = new Country();
        country.setName("Brazil");
        country.setAcronym("BR");
        country.setPhoneDigits(3);
        
        CountryDAO instance = new CountryDAO();
        
        assertEquals(true, instance.create(country));
    }

    @Test
    public void testRead() {
        System.out.println("read");
        
        Country country = new Country();
        country.setName("BBrazil");
        country.setAcronym("BR");
        country.setPhoneDigits(3);

        CountryDAO instance = new CountryDAO();
        instance.create(country);
        
        assertNotEquals(0, instance.read().size());
    }

    @Test
    public void testUpdate() {
        System.out.println("update");
        
        Country country = new Country();
        country.setName("BBBrazil");
        country.setAcronym("BR");
        country.setPhoneDigits(3);
        
        CountryDAO instance = new CountryDAO();
        instance.create(country);
        
        Country updatedCountry = instance.read().stream().filter(e -> e.getName().equals("BBBrazil")).findAny().get();
        updatedCountry.setName("BBBBRazil");
        
        assertEquals(true, instance.update(country));
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        
        Country country = new Country();
        country.setName("BBBBBBBBBrazil");
        country.setAcronym("BR");
        country.setPhoneDigits(3);
        
        CountryDAO instance = new CountryDAO();
        instance.create(country);

        Country deletedCountry = instance.read().stream().filter(e -> e.getName().equals("BBBBBBBBBrazil")).findAny().get();
        
        assertEquals(true, instance.delete(deletedCountry.getId()));
    }
    
}
