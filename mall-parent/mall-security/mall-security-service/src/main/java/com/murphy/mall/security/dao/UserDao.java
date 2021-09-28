package com.murphy.mall.security.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 用户表
 * @Description:
 */
public interface UserDao extends ICrudDao<User> {

	/**
	 * 删除用户角色关联
	 * @param id
	 * @return
	 */
	public int deleteRoleByUser(Long id);

	/**
	 * 关联用户角色
	 * @param roleId
	 * @param userId
	 * @return
	 */
	public int insertRoleAndUser(@Param("roleId") Long roleId, @Param("userId") Long userId);

	/**
	 * 查询用户的角色
	 * @param id
	 * @return
	 */
	public List<Role> selectRoleByUser(Long id);

}
