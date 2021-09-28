package com.murphy.mall.security.controller;

import com.murphy.mall.core.controller.BaseController;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import com.murphy.mall.security.service.IRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Title: 角色 - Controller
 * @Description:
 */
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<IRoleService, Role> {

	@Override
	public void afterEdit(Role entity) {
		//生成用户列表, 如：1,3,4
		List<User> users = service.selectUserByRole(entity.getId());
		Long[] ids = new Long[users.size()];
		for (int i=0; i< users.size(); i++) {
			ids[i] = users.get(i).getId();
		}
		entity.setUserIds(ids);
	}

}
