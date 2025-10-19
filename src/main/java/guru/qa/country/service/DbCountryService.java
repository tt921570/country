package guru.qa.country.service;

import guru.qa.country.data.CountryEntity;
import guru.qa.country.data.CountryRepository;
import guru.qa.country.domain.Country;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DbCountryService implements CountryService{

    private final CountryRepository countryRepository;

    @Autowired
    public DbCountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> allCountries() {
        return countryRepository.findAll().stream()
                .map(countryEntity -> new Country(
                                countryEntity.getFullName(),
                                countryEntity.getCode(),
                                countryEntity.getCreatedAt(),
                                countryEntity.getUpdatedAt()
                        )
                )
                .toList();
    }

    @Transactional
    @Override
    public Country add(Country newCountry) {
        countryRepository.findByCode(newCountry.code())
                .ifPresent(ec -> {
                    throw new EntityExistsException("Can not save existed country: " + ec);
                });

        CountryEntity countryEntity = new CountryEntity();
        countryEntity.setCode(newCountry.code());
        countryEntity.setFullName(newCountry.fullName());

        CountryEntity savedCountryEntity = countryRepository.save(countryEntity);
        return new Country(
                savedCountryEntity.getFullName(),
                savedCountryEntity.getCode(),
                savedCountryEntity.getCreatedAt(),
                savedCountryEntity.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public Country updateFullName(Country country) {
        CountryEntity existedCountryEntity = countryRepository.findByCode(country.code())
                .orElseThrow(() -> new EntityNotFoundException("Not found country: " + country));

        existedCountryEntity.setFullName(country.fullName());

        CountryEntity savedCountryWithNewFullName = countryRepository.save(existedCountryEntity);
        return new Country(
                savedCountryWithNewFullName.getFullName(),
                savedCountryWithNewFullName.getCode(),
                savedCountryWithNewFullName.getCreatedAt(),
                savedCountryWithNewFullName.getUpdatedAt()
        );
    }
}
