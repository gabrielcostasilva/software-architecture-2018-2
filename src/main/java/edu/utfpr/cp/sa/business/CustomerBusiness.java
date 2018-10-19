package edu.utfpr.cp.sa.business;

import java.util.Set;

import edu.utfpr.cp.sa.dao.CustomerDAO;
import edu.utfpr.cp.sa.entity.Customer;

public class CustomerBusiness {

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

            return new CustomerDAO().create(customer);
        }

    }

    public Set<Customer> read() {
        return new CustomerDAO().read();
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

            return new CustomerDAO().update(customer);
        }

    }

    public boolean delete(Long id) {
        return new CustomerDAO().delete(id);
    }

}