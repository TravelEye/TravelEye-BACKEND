package com.service.traveleye.domain.destination.service;


import com.service.traveleye.domain.destination.dto.ArrayCityResDTO;
import com.service.traveleye.domain.destination.dto.ArrayContinentResDTO;

import com.service.traveleye.domain.destination.dto.ArrayDestinationResDTO;
import com.service.traveleye.domain.destination.dto.CityResDTO;
import com.service.traveleye.domain.destination.entity.Destination;
import com.service.traveleye.domain.destination.repository.DestinationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class DestinationServiceImpl implements DestinationService {

    private final DestinationRepository destinationRepository;
    @Override
    public ArrayCityResDTO getCitiesByCountry(String country) {
        List<Destination> countryArray = destinationRepository.findByCountry(country);
        List<CityResDTO> cityNames = countryArray.stream()
                .map(countryEntity -> new CityResDTO(countryEntity.getId(), countryEntity.getCity()))
                .collect(Collectors.toList());
        ArrayCityResDTO result = new ArrayCityResDTO(cityNames);
        return result;

    }

    @Override
    public ArrayContinentResDTO getCountriesByContinent(String continent) {

        List<String> countryArray = destinationRepository.findUniqueCountriesByContinent(continent);
        ArrayContinentResDTO countries = new ArrayContinentResDTO(countryArray);
        return countries;
    }

    @Override
    public List<ArrayDestinationResDTO> getAllDestinations() {
        List<Destination> destinations =  destinationRepository.findAll();
        return destinations.stream()
                .map(destinationEntity -> new ArrayDestinationResDTO(
                        destinationEntity.getId(),
                        destinationEntity.getContinent(),
                        destinationEntity.getCountry(),
                        destinationEntity.getCity()
                ))
                .collect(Collectors.toList());
    }
}
