package com.vvboot.end.service;


import com.vvboot.end.commons.PageObject;
import com.vvboot.end.entity.Auth;

import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
public interface AuthService {
    Auth addAuth(Auth auth);

    Auth updateAuth(Auth auth);

    List<Auth> authList();

    Auth findByAuthId(Long authId);

    void deleteAuth(Long authId);

    PageObject<Auth> findAllAuths(int page, int pageSize);

    void lock(Long authId);

    void unlock(Long authId);
}
