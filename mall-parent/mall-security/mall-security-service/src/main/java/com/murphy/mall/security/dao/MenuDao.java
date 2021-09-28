package com.murphy.mall.security.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.security.po.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @file MenuDao.java
 */
public interface MenuDao extends ICrudDao<Menu> {

    /**
     * 根据角色查询菜单
     * @param roleId
     * @return
     */
    public List<Menu> selectByRoleId(Long roleId);

    /**
     * 根据用户查询菜单
     * @param userId
     * @return
     */
    public List<Menu> selectByUserId(Long userId);

    /**
     * 删除角色的菜单
     * @param id
     * @return
     */
    public int deleteMenuByRole(Long id);

    /**
     * 关联菜单和角色
     * @param menuId
     * @param roleId
     * @return
     */
    public int insertMenuAndRole(@Param("menuId") Long menuId, @Param("roleId") Long roleId);

}
