package com.service.traveleye.domain.location.controller;

import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class LocationController {
    @GetMapping("/recommend")
    public DataResDTO<?> myMapList(
            @RequestParam final Double x,
            @RequestParam final Double y
    ){
        return null;
    }
}
