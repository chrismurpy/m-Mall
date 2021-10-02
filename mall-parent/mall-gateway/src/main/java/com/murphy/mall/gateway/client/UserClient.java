package com.murphy.mall.gateway.client;

import com.murphy.mall.security.api.UserApi;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * 用户登录
 *
 * @author murphy
 * @since 2021/9/25 10:57 下午
 */
@FeignClient(name = "security-service", fallback = UserClient.UserClientFallback.class)
public interface UserClient extends UserApi {

    @Component
    @RequestMapping("/fallback-user")
    public class UserClientFallback implements UserClient {

        private static final Logger LOGGER = LoggerFactory.getLogger(UserClientFallback.class);

        /**
         * 通过用户名获取角色信息
         *
         * @param userName
         * @return
         */
        @Override
        public User getByUserName(String userName) {
            LOGGER.info("异常发生，进入fallback方法！ - User");
            return null;
        }

        /**
         * 通过用户ID获取角色信息
         *
         * @param id
         * @return
         */
        @Override
        public List<Role> selectRolesByUserId(Long id) {
            LOGGER.info("异常发生，进入fallback方法！ - User");
            return null;
        }

        @Override
        public void addPoint(Long point, String username) {
            LOGGER.info("发生异常，进入Fallback方法 - User");
        }
    }

}
