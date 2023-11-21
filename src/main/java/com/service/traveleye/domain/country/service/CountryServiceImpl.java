package com.service.traveleye.domain.country.service;


import com.service.traveleye.domain.country.dto.ArrayCityReqDTO;
import com.service.traveleye.domain.country.dto.CityReqDTO;
import com.service.traveleye.domain.country.dto.CountryReqDTO;
import com.service.traveleye.domain.country.entity.Country;
import com.service.traveleye.domain.country.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;
    @Override
    public ArrayCityReqDTO getCitiesByCountry(String country) {
        List<Country> countryArray = countryRepository.findByCountry(country);
        List<String> cityNames = countryArray.stream()
                .map(Country::getCity)
                .collect(Collectors.toList());
        ArrayCityReqDTO result = new ArrayCityReqDTO(cityNames);
        return result;

    }

    @Override
    public List<CountryReqDTO> getCountriesByContinent(String continent) {
        return null;
    }
}
