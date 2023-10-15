package com.service.traveleye.domain.user.controller;

import com.service.traveleye.domain.user.dto.UserLoginReqDTO;
import com.service.traveleye.domain.user.service.UserService;
import com.service.traveleye.global.dto.DataResDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }
    @PostMapping("/login")
    public DataResDTO<?> login(@RequestBody UserLoginReqDTO userLoginReqDTO) {
        return userService.login(userLoginReqDTO);
    }

}
