package com.zl.webService.service.impl;

import com.zl.webService.dao.impl.BaseDAO;

import java.util.List;

/**
 * Created by Administrator on 2015/12/12.
 */
public interface BaseService<T> {
    /**
     * 创建信息
     *
     * @param entity
     * @return
     */
    T create(T entity);

    /**
     * 删除信息
     *
     * @param entity
     */
    void delete(T entity);

    /**
     * 查询信息
     *
     * @param entity
     * @return
     */
    List<T> search(T entity, int pageNumber, int pageSize);

    /**
     * 修改信息
     *
     * @param entity
     * @return
     */
    void update(T entity);
}
