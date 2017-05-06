package com.vvboot.end.service;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by LONG on 2017/4/18.
 */
public interface AuthMenuService {
    void assignAuthMenu(JSONObject authmenu);

    JSONObject findAllAuthmenuByMenuId(Long menuId);
}
