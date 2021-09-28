package com.murphy.mall.security.service;


import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;

import java.util.List;

/**
 * @Title: 用户 - 业务
 * @Description:
 */
public interface IUserService extends ICrudService<User> {

    /**
     * 根据用户id查询角色
     * @param id
     * @return
     */
    public List<Role> selectRoleByUser(Long id);

    /**
     * 根据用户名，查询用户个数
     * @param userName
     * @return
     */
    public Integer findCountByUserName(String userName);

    /**
     * 根据用户名查询用户
     * @param userName
     * @return
     */
    public User getUserByUserName(String userName);

}
