package com.service.traveleye.domain.destination.repository;

import com.service.traveleye.domain.destination.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

    List<Destination> findByCountry(String country);

    List<Destination> findAll();
    @Query("SELECT DISTINCT  d.country FROM Destination d WHERE d.continent = :continent")
    List<String> findUniqueCountriesByContinent(@Param("continent") String continent);
}
