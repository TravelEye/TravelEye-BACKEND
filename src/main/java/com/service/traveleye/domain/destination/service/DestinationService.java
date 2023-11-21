package com.service.traveleye.domain.destination.service;

import com.service.traveleye.domain.destination.dto.ArrayCityResDTO;
import com.service.traveleye.domain.destination.dto.ArrayContinentResDTO;
import com.service.traveleye.domain.destination.dto.ArrayDestinationResDTO;

import java.util.List;

public interface DestinationService {
    ArrayCityResDTO getCitiesByCountry(String country);
    ArrayContinentResDTO getCountriesByContinent(String continent);

    List<ArrayDestinationResDTO> getAllDestinations();

}
