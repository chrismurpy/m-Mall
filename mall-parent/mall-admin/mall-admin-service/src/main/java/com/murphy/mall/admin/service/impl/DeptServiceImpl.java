package com.murphy.mall.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.murphy.mall.admin.po.Dept;
import com.murphy.mall.admin.service.IDeptService;
import com.murphy.mall.core.service.impl.CrudServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门 - CURD实现
 */
@Service
public class DeptServiceImpl extends CrudServiceImpl<Dept> implements IDeptService {

    public List<Dept> list(Dept entity) {
        QueryWrapper<Dept> queryWrapper = Wrappers.<Dept>query();
        if (null != entity.getAddress() && !"".equals(entity.getAddress().trim())) {
            queryWrapper.like("address", entity.getAddress());
        }
        if (null != entity.getTitle() && !"".equals(entity.getTitle().trim())) {
            queryWrapper.like("title", entity.getTitle());
        }
        return baseMapper.selectList(queryWrapper);
    }
}
