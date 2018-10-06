/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.utfpr.cp.sa.business;

import edu.utfpr.cp.sa.dao.CustomerDAO;
import edu.utfpr.cp.sa.entity.Customer;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Leandro
 */
public class CustomerBusiness {
     private CustomerDAO customerDAO;    
    
    public void create(Customer customer) throws Exception {
        
        if (customerDAO.read().stream().map(Customer::getName).anyMatch(e -> e.equals(customer.getName()))) {
            throw new IllegalArgumentException("There already is a customer with this name!");
        }else{
            customerDAO.create(customer);
        }
    }
    
    public void update(Customer customer) {
        
        if (customerDAO.read().stream().map(Customer::getName).anyMatch(e -> e.equals(customer.getName()))) {
            throw new IllegalArgumentException("There already is a country with this name!");
        }else{
            customerDAO.update(customer);
        }
    }
    
    
    public Set<Customer> read(){
        HashSet<Customer> set = new HashSet<>();
        
        try {
            set = (HashSet<Customer>) customerDAO.read();
        } catch (Exception e) {
            System.out.println("Nao foram encontrado dados");
        }
        
        return set;
    }
    
    
    public boolean delete(Long id) {       
        
        if(customerDAO.delete(id)){
            return true;
        }
        
        return false;
    }   
}
