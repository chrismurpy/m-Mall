package com.murphy.mall.security.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.security.dao.UserDao;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import com.murphy.mall.security.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl extends CrudServiceImpl<User> implements IUserService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<Role> selectRoleByUser(Long id) {
		return ((UserDao) getBaseMapper()).selectRoleByUser(id);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean saveOrUpdate(User entity) {
		//添加时，设置lock=false
		if (null == entity.getId()){
			entity.setLock(false);
		}

		//TODO 加入oath2后改回来了
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
//		passwordHelper.encryptPassword(entity); //加密md5(md5(password,salt))

		boolean result = super.saveOrUpdate(entity);

		((UserDao) getBaseMapper()).deleteRoleByUser(entity.getId());

		Long[] roleIds = entity.getRoleIds();
		if (null != roleIds) {
			for (Long roleId : roleIds) {
				((UserDao) getBaseMapper()).insertRoleAndUser(roleId, entity.getId());
			}
		}
		return result;

	}

	@Override
	public Integer findCountByUserName(String userName) {
		return getBaseMapper().selectCount(Wrappers.<User>query().eq("user_name_", userName));
	}

	@Override
	public User getUserByUserName(String userName) {
		return getBaseMapper().selectOne(Wrappers.<User>query().eq("user_name_", userName));
	}

	/**
	 * 增加会员积分
	 *
	 * @param point
	 * @param username
	 */
	@Override
	public void addPoint(Long point, String username) {
		((UserDao) getBaseMapper()).addPoint(point, username);
	}

}
