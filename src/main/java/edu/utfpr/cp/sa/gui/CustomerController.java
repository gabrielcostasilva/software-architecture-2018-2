package edu.utfpr.cp.sa.gui;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import edu.utfpr.cp.sa.business.CountryBusiness;
import edu.utfpr.cp.sa.business.CustomerBusiness;
import edu.utfpr.cp.sa.entity.Country;
import edu.utfpr.cp.sa.entity.Customer;
import lombok.extern.java.Log;

@Controller
public class CustomerController {

    private CustomerBusiness customerBusiness;
    private CountryBusiness countryBusiness;

    @Autowired
    public CustomerController (CustomerBusiness customerBusiness, CountryBusiness countryBusiness) {
        this.countryBusiness = countryBusiness;
        this.customerBusiness = customerBusiness;
    }

    @GetMapping ("/customer")
    public String view(Model model) {

        model.addAttribute("customerList", customerBusiness.read());
        model.addAttribute("countryList", countryBusiness.read());

        return "customer";
    }

    @PostMapping ("/customer/new")
    public String create (CustomerDTO customer) {
        
        try {
            Customer c = new Customer();
            c.setName(customer.getName());
            
            Country country = countryBusiness.read().stream().filter(e -> customer.getCountry().equalsIgnoreCase(e.getName())).findFirst().get();
            c.setCountry(country);

            c.setAge(customer.getAge());
            c.setPhone(customer.getPhone());

            customerBusiness.create(c);

        } catch (Exception e) {
            //TODO: handle exception
        }

        return "redirect:/customer";
    }
}