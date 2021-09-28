package com.murphy.mall.security.service.impl;


import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.security.dao.RoleDao;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import com.murphy.mall.security.service.IRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Title: 角色资源 - 实现类
 * @Description:
 */
@Service
public class RoleServiceImpl extends CrudServiceImpl<Role> implements IRoleService {

	@Override
	public List<User> selectUserByRole(Long id) {
		return ((RoleDao) getBaseMapper()).selectUserByRole(id);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean saveOrUpdate(Role entity) {
		RoleDao dao = ((RoleDao) getBaseMapper());
		boolean result = super.saveOrUpdate(entity);

		dao.deleteUserByRole(entity.getId()); //删除角色的用户

		//添加角色和权限关系
		Role role = (Role) entity;

		//添加用户和角色关系
		if (null != role.getUserIds()) {
			for (Long userId : role.getUserIds()) {
				dao.insertUserAndRole(userId, entity.getId());
			}
		}
		return result;
	}
}
