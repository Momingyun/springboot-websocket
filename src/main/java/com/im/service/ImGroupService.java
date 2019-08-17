package com.im.service;

import com.im.entity.ImGroup;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 群聊信息表(ImGroup)表业务逻辑层
 *
 * @author liubin
 * @since 2019-08-14 12:01:28
 */
public interface ImGroupService {

    /**
     * 根据用户编号获取所拥有的群信息
     *
     * @param userId 用户编号
     * @return 实例对象
     */
    List<ImGroup> queryById(Long userId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ImGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imGroup 实例对象
     * @return 对象列表
     */
    List<ImGroup> queryAll(ImGroup imGroup);

    /**
     * 新增数据
     *
     * @param imGroup 实例对象
     * @return 影响行数
     */
    int insert(ImGroup imGroup);

    /**
     * 修改数据
     *
     * @param imGroup 实例对象
     * @return 影响行数
     */
    int update(ImGroup imGroup);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

}