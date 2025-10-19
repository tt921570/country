package guru.qa.country.controller;

import guru.qa.country.domain.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/all")
    public List<Country> getAllCountries() {
        return countryService.allCountries();
    }

    @PostMapping("/add")
    public Country addNewCountry(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PatchMapping("/update_full_name")
    public Country updateFullName(@RequestBody Country country) {
        return countryService.updateFullName(country);
    }

}
