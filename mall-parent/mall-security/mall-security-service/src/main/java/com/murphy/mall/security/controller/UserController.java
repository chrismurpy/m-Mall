package com.murphy.mall.security.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.core.po.ResponseBean;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import com.murphy.mall.security.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @Title: 用户 - Controller
 * @Description:
 */
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController<IUserService, User> {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @ApiOperation("通过登录获得用户")
    @GetMapping("/get/{userName}")
    public User getByUserName(@PathVariable("userName") String userName) {
        return service.getUserByUserName(userName);
    }

    @ApiOperation("通过用户ID获得角色")
    @GetMapping("/select-roles/{id}")
    public List<Role> selectRolesByUserId(@PathVariable("id") Long id) {
        return service.selectRoleByUser(id);
    }

    /**
     * 验证用户名是否存在
     */
    @ApiOperation("验证用户名是否存在")
    @PostMapping("/validate-name/{userName}")
    public String validUserName(@PathVariable String userName, Long id) {
        long rowCount = service.findCountByUserName(userName);

        //修改时=原来的用户名
        if (id != null) {
            User user = service.getById(id);
            if (null != userName && userName.equals(user.getUserName())) {
                return "{\"success\": true}";
            }
        }

        if (rowCount > 0) {
            return "{\"success\": false}";
        } else {
            return "{\"success\": true}";
        }
    }

    /**
     * 锁定用户
     */
    @GetMapping("/lock/{id}")
    @ApiOperation("锁定账户")
    public ResponseBean lock(@PathVariable Long id) throws Exception {
        ResponseBean rm = new ResponseBean();
        try {
            User u = service.getById(id);

            User user = new User();
            user.setId(id);
            if (null != u.getLock() && u.getLock()) {
                rm.setMsg("用户已解锁");
                user.setLock(false);
            } else {
                rm.setMsg("用户已锁定");
                user.setLock(true);
            }
            service.updateById(user);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败");
        }

        return rm;
    }

    @Override
    public void afterEdit(User domain) {
        //生成角色列表, 如：1,3,4
        List<Role> roles = service.selectRoleByUser(domain.getId());
        Long[] ids = new Long[roles.size()];
        for (int i = 0; i < roles.size(); i++) {
            ids[i] = roles.get(i).getId();
        }
        domain.setRoleIds(ids);
    }

}
