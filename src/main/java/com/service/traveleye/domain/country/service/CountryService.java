package com.service.traveleye.domain.country.service;

import com.service.traveleye.domain.country.dto.ArrayCityReqDTO;
import com.service.traveleye.domain.country.dto.CityReqDTO;
import com.service.traveleye.domain.country.dto.CountryReqDTO;
import com.service.traveleye.domain.country.entity.Country;

import java.util.List;

public interface CountryService {
    ArrayCityReqDTO getCitiesByCountry(String country);
    List<CountryReqDTO> getCountriesByContinent(String continent);

}
