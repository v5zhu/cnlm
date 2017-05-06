package com.vvboot.end.service;


import com.vvboot.end.commons.PageObject;
import com.vvboot.end.entity.Role;

import java.util.List;

/**
 * Created by LONG on 2017/4/18.
 */
public interface RoleService {
    Role addRole(Role role);

    Role updateRole(Role role);

    List<Role> roleList();

    Role findByRoleId(Long roleId);

    void deleteRole(Long roleId);

    PageObject<Role> findAllRoles(int page, int pageSize);

    void lock(Long roleId);

    void unlock(Long roleId);
}
