package com.service.traveleye.domain.trip.repository;


import com.service.traveleye.domain.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends JpaRepository<Trip,Long> {

void deleteById(Long id);
Trip getById(Long id);

}
