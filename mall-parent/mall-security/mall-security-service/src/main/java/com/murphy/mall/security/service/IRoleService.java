package com.murphy.mall.security.service;


import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;

import java.util.List;

/**
 * @Title: 角色 - 查询
 * @Description: 
 *
 */
public interface IRoleService extends ICrudService<Role> {

    /**
     * 查询角色的用户
     * @param id
     * @return
     */
    public List<User> selectUserByRole(Long id);

}
