package com.murphy.mall.security.api;

import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 角色信息调用接口
 *
 * @author murphy
 * @since 2021/9/25 3:49 下午
 */
@RequestMapping(value = "/user")
public interface UserApi {

    /**
     * 通过用户名获取角色信息
     * @param userName
     * @return
     */
    @GetMapping("/get/{userName}")
    public User getByUserName(@PathVariable("userName") String userName);

    /**
     * 通过用户ID获取角色信息
     * @param id
     * @return
     */
    @GetMapping("/select-roles/{id}")
    public List<Role> selectRolesByUserId(@PathVariable("id") Long id);

    /**
     * 增加积分
     * @param point
     * @param username
     */
    @GetMapping(value = "/add-point")
    public void addPoint(@RequestParam("point") Long point, @RequestParam("username") String username);
}
