package com.service.traveleye.domain.user.service;

import com.service.traveleye.domain.user.dto.UserLoginReqDTO;
import com.service.traveleye.global.dto.DataResDTO;

public interface UserService {
    DataResDTO<?> signup();
    DataResDTO<?> login(UserLoginReqDTO userLoginReqDTO);
    DataResDTO<?> logout();

}
