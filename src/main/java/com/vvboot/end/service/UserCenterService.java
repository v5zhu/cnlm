package com.vvboot.end.service;


import com.vvboot.end.commons.PageObject;
import com.vvboot.end.dto.UserDto;

/**
 * Created by LONG on 2017/4/18.
 */
public interface UserCenterService {
    PageObject<UserDto> findAllUsers(int page, int pageSize);
}
