package edu.utfpr.cp.sa.gui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.utfpr.cp.sa.business.CountryBusiness;
import edu.utfpr.cp.sa.entity.Country;

@Controller
public class CountryController<countryBusiness> {

    private CountryBusiness countryBusiness;

    @Autowired
    public CountryController(CountryBusiness countryBusiness) {
        this.countryBusiness = countryBusiness;

    }

    @GetMapping("/country")
    public String view(Model model) {

        model.addAttribute("countryList", countryBusiness.read());

        return "country";
    }

    @PostMapping ("/country/new")
    public String create (CountryDTO country) {

        try {
            Country c = new Country();
            c.setName(country.getName());
            c.setAcronym(country.getAcronym());
            c.setPhoneDigits(country.getPhoneDigits());

            countryBusiness.create(c);

        } catch (Exception e) {
            //TODO: handle exception
        }
        
        return "redirect:/country";
    }

    @RequestMapping("country/delete/{id}")
    public String delete (@PathVariable Long id){

        countryBusiness.delete(id);
        
        return "redirect:/country";
    }
}