package com.service.traveleye.domain.landmark.repository;

import com.service.traveleye.domain.landmark.entity.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {
//    getRecommendList
    List<Landmark> findAll();
    Landmark findByLandmarkId(Long landmarkId);

    Landmark findLandmarkByPlaceStartingWith(String place);


}
