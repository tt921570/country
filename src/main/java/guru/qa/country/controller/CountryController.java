package guru.qa.country.controller;

import guru.qa.country.domain.Country;
import guru.qa.country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/countries")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.allCountries();
    }

    @PostMapping
    public Country addNewCountry(@RequestBody Country country) {
        return countryService.add(country);
    }

    @PatchMapping("/{code}")
    public Country updateFullName(@PathVariable("code") String code,
                                  @RequestBody Country country) {
        Country updatedCountry = new Country(
                country.fullName(),
                code,
                null,
                country.updatedAt() != null ? country.updatedAt() : LocalDateTime.now()
        );
        return countryService.updateFullName(updatedCountry);
    }

}
