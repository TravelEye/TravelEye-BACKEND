package com.service.traveleye.domain.country.controller;

import com.service.traveleye.domain.checklist.entity.Checklist;
import com.service.traveleye.domain.country.dto.ArrayCityReqDTO;
import com.service.traveleye.domain.country.dto.CityReqDTO;
import com.service.traveleye.domain.country.dto.CountryReqDTO;
import com.service.traveleye.domain.country.entity.Country;
import com.service.traveleye.domain.country.service.CountryService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
//@RequestMapping("/country")
public class CountryController {
private final CountryService countryService;
    @GetMapping("/country")
    public ArrayCityReqDTO getCitiesByCountry(@RequestParam String country){
        return countryService.getCitiesByCountry(country);
    }

    @GetMapping("/continent/{continent}")
    public List<CountryReqDTO> getCountriesByContinent(@PathVariable String continent){
        List<CountryReqDTO> countries =  countryService.getCountriesByContinent(continent);
        return countries;
    }
}
