package com.murphy.mall.security.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.security.po.Role;
import com.murphy.mall.security.po.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Title: 角色Dao
 * @Description: 
 *
 */
public interface RoleDao extends ICrudDao<Role> {

	/**
	 * 删除用户角色关联
	 * @param id
	 * @return
	 */
	public int deleteUserByRole(Long id);

	/**
	 * 关联角色用户
	 * @param userId
	 * @param roleId
	 * @return
	 */
	public int insertUserAndRole(@Param("userId") Long userId, @Param("roleId") Long roleId);

	/**
	 * 删除权限菜单关联
	 * @param id
	 * @return
	 */
	public int deleteMenuByRole(Long id);

	/**
	 * 关联角色和菜单
	 * @param menuId
	 * @param roleId
	 * @return
	 */
	public int insertMenuAndRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

	/**
	 * 查询角色的用户
	 * @param id
	 * @return
	 */
	public List<User> selectUserByRole(Long id);

}
