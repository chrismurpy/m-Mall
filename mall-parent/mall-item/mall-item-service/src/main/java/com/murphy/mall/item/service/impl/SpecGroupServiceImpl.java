package com.murphy.mall.item.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import com.murphy.mall.item.dao.SpecGroupDao;
import com.murphy.mall.item.dao.SpecParamDao;
import com.murphy.mall.item.po.SpecGroup;
import com.murphy.mall.item.po.SpecParam;
import com.murphy.mall.item.service.ISpecGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 商品规格参数分组
 *
 * @author murphy
 * @since 2021/9/18 3:46 下午
 */
@Service
public class SpecGroupServiceImpl extends CrudServiceImpl<SpecGroup> implements ISpecGroupService {

    @Resource
    private SpecParamDao specParamDao;

    @Override
    public List<SpecGroup> list(SpecGroup entity) {
        return ((SpecGroupDao) getBaseMapper()).selectList(entity);
    }

    /**
     * 根据前端传递的规格参数列表，保存规格参数
     *
     * @param cid    分类ID
     * @param groups 前端传递的分组列表 - [{[cid:1, name:'', params:[..]},...]
     */
    @Override
    public void saveGroup(Long cid, List<SpecGroup> groups) {
        // 根据Cid删除所有的规格参数分组和规则参数项
        getBaseMapper().delete(Wrappers.<SpecGroup>query().eq("cid_", cid));
        specParamDao.delete(Wrappers.<SpecParam>query().eq("cid_", cid));

        // 逐个添加规格参数分组和规格项
        for (SpecGroup group : groups) {
            getBaseMapper().insert(group);
            for (SpecParam param : group.getParams()) {
                param.setGroupId(group.getId());
                specParamDao.insert(param);
            }
        }
    }
}
