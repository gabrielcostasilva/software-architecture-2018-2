package edu.utfpr.cp.sa.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import edu.utfpr.cp.sa.dao.CustomerDAO;
import edu.utfpr.cp.sa.entity.Customer;

@Component
public class CustomerBusiness {

    private CustomerDAO customerDAO;

    @Autowired
    public CustomerBusiness (CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    private boolean validatePhone(Customer customer) throws Exception {

        if (customer.getPhone().length() != customer.getCountry().getPhoneDigits())
            throw new Exception("Phone does not conform to country!");

        else
            return true;
    }

    private void setCreditLimitByAge(Customer customer) {
        if (customer.getAge() <= 18)
            customer.setCreditLimit(customer.getCreditLimit() + 100.0);

        else if (customer.getAge() <= 35)
            customer.setCreditLimit(customer.getCreditLimit() + 300.0);

        else
            customer.setCreditLimit(customer.getCreditLimit() + 500.0);
    }

    private void setCreditLimitByCountry(Customer customer) {

        if (customer.getCountry().getName().equalsIgnoreCase("Brazil"))
            customer.setCreditLimit(customer.getCreditLimit() + 100.0);
    }

    public boolean create(Customer customer) throws Exception {

        if (this.read().stream().map(Customer::getName).anyMatch(e -> e.equals(customer.getName())))
            throw new IllegalArgumentException("There already is a customer with this name!");

        else {
            try {
                this.validatePhone(customer);

            } catch (Exception e) {
                throw e;
            }

            this.setCreditLimitByAge(customer);
            this.setCreditLimitByCountry(customer);

            customerDAO.save(customer);
            return true;
        }

    }

    public List<Customer> read() {
        return customerDAO.findAll();
    }

    public boolean update(Customer customer) throws Exception {
        if (this.read().stream().map(Customer::getName).anyMatch(e -> e.equals(customer.getName())))
            throw new IllegalArgumentException("There already is a customer with this name!");

        else {
            try {
                this.validatePhone(customer);

            } catch (Exception e) {
                throw e;
            }

            this.setCreditLimitByAge(customer);
            this.setCreditLimitByCountry(customer);

            customerDAO.saveAndFlush(customer);
            return true;
        }

    }

    public boolean delete(Long id) {
        Customer c = this.read().stream().filter(e -> e.getId() == id).findAny().get();

        customerDAO.delete(c);
        return true;
    }

}