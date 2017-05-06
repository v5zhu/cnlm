package com.vvboot.end.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by LONG on 2017/4/18.
 */
public interface AuthRoleService {
    void assignAuthRole(JSONObject authrole);

    JSONObject findAllAuthroleByRoleId(Long roleId);
}
