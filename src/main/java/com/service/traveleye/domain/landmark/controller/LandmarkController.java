package com.service.traveleye.domain.landmark.controller;

import com.service.traveleye.domain.landmark.dto.LandmarkListResDTO;
import com.service.traveleye.domain.landmark.entity.Landmark;
import com.service.traveleye.domain.landmark.repository.LandmarkRepository;
import com.service.traveleye.domain.landmark.service.LandmarkService;
import com.service.traveleye.global.dto.DataResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/map")
public class LandmarkController {
    private final LandmarkService landmarkService;
    @GetMapping("")
    public List<LandmarkListResDTO> getLandmarkList(){
         return landmarkService.getLandmarkList();
    }

//    @GetMapping("/recommend")
//    public DataResDTO<?> myMapList(
//            @RequestParam final Double x,
//            @RequestParam final Double y
//    ){
//        return null;
//    }
}
