package edu.utfpr.cp.sa.business;

import edu.utfpr.cp.sa.dao.CountryDAO;
import edu.utfpr.cp.sa.dao.CustomerDAO;
import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;
import java.util.Set;
        
public class CustomerBusiness {
    public boolean create(Customer customer) throws Exception{
        //Regra de negócio: limite de credito de acordo a idade do Cliente
        if (customer.getAge() <= 18)
			customer.setCreditLimit(customer.getCreditLimit() + 100.0);
		
	else if (customer.getAge() <= 35)
			customer.setCreditLimit(customer.getCreditLimit() + 300.0);
		
	else
			customer.setCreditLimit(customer.getCreditLimit() + 500.0);
        
        //Regra de negócio: limite + 100  país = "Brazil"
        if (customer.getCountry().getName().equalsIgnoreCase("Brazil"))
			customer.setCreditLimit(customer.getCreditLimit() + 100.0);
        
        return new CustomerDAO().create(customer);
    }
    
    public Set<Customer> read() {
        return new CustomerDAO().read();
    }
    
    public boolean update(Customer customer) {
        return new CustomerDAO().update(customer);
    }
    
    public boolean delete(Long id){
        return new CustomerDAO().delete(id);
    }
}
