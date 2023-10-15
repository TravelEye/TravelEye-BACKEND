package com.service.traveleye.domain.survey.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class SurveyController {
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
}
