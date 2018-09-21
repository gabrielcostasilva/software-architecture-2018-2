package edu.utfpr.cp.sa.dao;

import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomerDAOTest {

    @Test
    public void testCreate() {
        System.out.println("create");

        Country country = new Country();
        country.setName("United States of America");
        country.setAcronym("USA");
        country.setPhoneDigits(3);

        CountryDAO countryDAO = new CountryDAO();
        countryDAO.create(country);
        Country theCountry = countryDAO.read().stream().filter(e -> e.getAcronym().equals("USA")).findAny().get();

        Customer customer = new Customer();
        try {
            customer.setName("John Doe");
            customer.setCountry(theCountry);
            customer.setAge(50);
            customer.setPhone("123");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerDAO instance = new CustomerDAO();

        assertEquals(true, instance.create(customer));
    }

    @Test
    public void testRead() {
        System.out.println("read");
        
        Country country = new Country();
        country.setName("United States of America");
        country.setAcronym("USAA");
        country.setPhoneDigits(3);

        CountryDAO countryDAO = new CountryDAO();
        countryDAO.create(country);
        Country theCountry = countryDAO.read().stream().filter(e -> e.getAcronym().equals("USAA")).findAny().get();

        Customer customer = new Customer();
        try {
            customer.setName("John Doee");
            customer.setCountry(theCountry);
            customer.setAge(50);
            customer.setPhone("123");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerDAO instance = new CustomerDAO();
        instance.create(customer);
        
        assertNotEquals(0, instance.read().size());
    }

    @Test
    public void testUpdate() {
        Country country = new Country();
        country.setName("United States of America");
        country.setAcronym("USAAA");
        country.setPhoneDigits(3);

        CountryDAO countryDAO = new CountryDAO();
        countryDAO.create(country);
        Country theCountry = countryDAO.read().stream().filter(e -> e.getAcronym().equals("USAAA")).findAny().get();

        Customer customer = new Customer();
        try {
            customer.setName("John Doeee");
            customer.setCountry(theCountry);
            customer.setAge(50);
            customer.setPhone("123");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerDAO instance = new CustomerDAO();
        instance.create(customer);
        
        Customer updatedCustomer = instance.read().stream().filter(e -> e.getName().equals("John Doeee")).findAny().get();
        updatedCustomer.setAge(51);
        
        assertEquals(true, instance.update(customer));
    }

    @Test
    public void testDelete() {
        System.out.println("delete");
        
        Country country = new Country();
        country.setName("United States of America");
        country.setAcronym("USAAAA");
        country.setPhoneDigits(3);

        CountryDAO countryDAO = new CountryDAO();
        countryDAO.create(country);
        Country theCountry = countryDAO.read().stream().filter(e -> e.getAcronym().equals("USAAAA")).findAny().get();

        Customer customer = new Customer();
        try {
            customer.setName("John Doeeee");
            customer.setCountry(theCountry);
            customer.setAge(50);
            customer.setPhone("123");

        } catch (Exception e) {
            e.printStackTrace();
        }

        CustomerDAO instance = new CustomerDAO();
        instance.create(customer);
        
        Customer deletedCustomer = instance.read().stream().filter(e -> e.getName().equals("John Doeee")).findAny().get();
        
        assertEquals(true, instance.delete(deletedCustomer.getId()));
    }

}
