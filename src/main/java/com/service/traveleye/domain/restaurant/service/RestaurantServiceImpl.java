package com.service.traveleye.domain.restaurant.service;

import com.service.traveleye.domain.restaurant.repository.RestaurantRepository;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class RestaurantServiceImpl implements RestaurantService {

    private final RestaurantRepository restaurantRepository;
    @Override
    public DataResDTO<?> getAllRestaurants() {
        return DataResDTO.builder()
                .message("모든 맛집 가져오기 완료")
                .data(restaurantRepository.findAll())
                .build();
    }
}
