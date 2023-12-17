package com.service.traveleye.domain.restaurant.service;

import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


public interface RestaurantService {
    DataResDTO<?> getAllRestaurants();
}
