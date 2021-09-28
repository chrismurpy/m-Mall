package com.murphy.mall.core.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.pagehelper.PageInfo;
import com.murphy.mall.core.po.BaseEntity;
import com.murphy.mall.core.po.ResponseBean;
import com.murphy.mall.core.json.JSON;
import com.murphy.mall.core.service.ICrudService;
import com.murphy.mall.core.utils.GenericUtil;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author murphy
 * @file BaseController.java
 */
public abstract class BaseController<S extends ICrudService<T>, T extends BaseEntity> {

    @Autowired
    protected S service;

    protected Logger LOG = LoggerFactory.getLogger(this.getClass());

    /**
     * 域对象类型
     */
    protected Class<T> entityClass;

    public BaseController() {
        this.entityClass = GenericUtil.getSuperGenericClass2(this.getClass());
    }

    /**
     * 加载
     *
     * @param id
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "加载", notes = "根据ID加载")
    @GetMapping("/edit/{id}")
    public T edit(@PathVariable Long id) throws Exception {
        T entity = service.getById(id);
        afterEdit(entity);
        return entity;
    }

    /**
     * 分页查询
     *
     * @param entity
     * @param page
     * @param rows
     * @return
     */
    @ApiOperation(value = "分页查询", notes = "分页查询")
    @PostMapping("/list-page")
    @JSON(type = BaseEntity.class, filter = "desc") //无效
    public PageInfo<T> listPage(T entity,
                                @RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                @RequestParam(name = "rows", defaultValue = "10", required = false) int rows) {
        PageInfo<T> result = service.listPage(entity, page, rows);
        return result;
    }

    /**
     * 根据实体条件查询
     *
     * @return
     */
    @ApiOperation(value = "查询", notes = "根据实体条件查询")
    @RequestMapping(value = "/list", method = {RequestMethod.POST, RequestMethod.GET})
    @JSON(type = BaseEntity.class, filter = "desc")
    public List<T> list(T entity) {
        List<T> list = service.list(entity);
        return list;
    }

    /**
     * 增加，修改
     */
    @ApiOperation(value = "保存", notes = "ID存在修改，不存在添加")
    @PostMapping("/save")
    public ResponseBean save(T entity) throws Exception {
        ResponseBean rm = new ResponseBean();
        try {
            beforeSave(entity); //保存前处理实体类
            service.saveOrUpdate(entity);
            rm.setModel(entity);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败");
        }
        return rm;
    }

    /**
     * 删除
     */
    @ApiOperation(value = "删除", notes = "根据ID删除")
    @GetMapping("/delete/{id}")
    public ResponseBean delete(@PathVariable Long id) throws Exception {
        ResponseBean rm = new ResponseBean();
        try {
            service.removeById(id);
            rm.setModel(null);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setSuccess(false);
            rm.setMsg("保存失败");
        }
        return rm;
    }

    /**
     * 批量删除
     */
    @ApiOperation(value = "删除", notes = "批量删除")
    @RequestMapping(value = "/delete", method = {RequestMethod.POST, RequestMethod.GET})
    public ResponseBean delete(@RequestParam List<Long> ids) {
        ResponseBean rm = new ResponseBean();
        try {
            service.removeByIds(ids);
        } catch (Exception e) {
            e.printStackTrace();
            rm.setMsg("删除失败");
            rm.setSuccess(false);
        }
        return rm;
    }

    /**
     * 保存前执行
     *
     * @param entity
     * @throws Exception
     */
    public void beforeSave(T entity) throws Exception {
    }

    /**
     * 模板方法：在加载后执行
     *
     * @param entity
     */
    public void afterEdit(T entity) {

    }

}


