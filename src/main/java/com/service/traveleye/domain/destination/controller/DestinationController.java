package com.service.traveleye.domain.destination.controller;


import com.service.traveleye.domain.destination.dto.ArrayCityResDTO;
import com.service.traveleye.domain.destination.dto.ArrayContinentResDTO;
import com.service.traveleye.domain.destination.dto.ArrayDestinationResDTO;
import com.service.traveleye.domain.destination.service.DestinationService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/destination")
public class DestinationController {
private final DestinationService destinationService;

    @GetMapping("/all")
    public List<ArrayDestinationResDTO> getAllDestinations(){
        return destinationService.getAllDestinations();
    }
    @GetMapping("/cities")
    public ArrayCityResDTO getCitiesByCountry(@RequestParam String country){
        return destinationService.getCitiesByCountry(country);
    }
    @GetMapping("/countries")
    public ArrayContinentResDTO getCountriesByContinent(@RequestParam String continent){
        ArrayContinentResDTO countries =  destinationService.getCountriesByContinent(continent);
        return countries;
    }

}
