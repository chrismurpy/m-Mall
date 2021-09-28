package com.murphy.mall.item.dao;

import com.murphy.mall.core.dao.ICrudDao;
import com.murphy.mall.item.po.SpecParam;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 规格参数 - CRUD操作
 *
 * @author murphy
 * @since 2021/9/18 3:21 下午
 */
public interface SpecParamDao extends ICrudDao<SpecParam> {

    /**
     * 查询当前指定分组的规格项
     * @param groupId
     * @return
     */
    @Select("select * from spec_param_ where group_id_ = #{groupId}")
    public List<SpecParam> findByGroupId(Integer groupId);

}
