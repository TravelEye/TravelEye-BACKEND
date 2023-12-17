package com.service.traveleye.domain.restaurant.controller;

import com.service.traveleye.domain.restaurant.service.RestaurantService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/food")
public class RestaurantController {
    private final RestaurantService restaurantService;
@GetMapping("/all")
public DataResDTO<?> getAllRestaurants(){
    return restaurantService.getAllRestaurants();
}
}
