package com.service.traveleye.domain.country.repository;

import com.service.traveleye.domain.country.dto.CityReqDTO;
import com.service.traveleye.domain.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Long> {

    List<Country> findByCountry(String country);
}
