package com.murphy.mall.auth.service.impl;

import com.murphy.mall.auth.client.UserClient;
import com.murphy.mall.security.po.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义 UserDetail - 查询RBAC权限数据 - 即令牌所需要的数据
 *
 * @author murphy
 * @since 2021/9/28 4:19 下午
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 通过Feign调用用户微服务
        com.murphy.mall.security.po.User user = userClient.getByUserName(username);
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        if (user != null) {
            LOGGER.debug("Current User = " + user);
            // 获取用户的权限(角色)
            List<Role> roles = userClient.selectRolesByUserId(user.getId());
            for (Role role : roles) {
                if (role != null && role.getName() != null) {
                    // Spring Security - 权限名称要包含前缀 - ROLE_
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + role.getName());
                    grantedAuthorities.add(grantedAuthority);
                }
            }
        }
        LOGGER.debug("Granted Authorities = " + grantedAuthorities);
        return new User(user.getUserName(), user.getPassword(), grantedAuthorities);
    }
}
