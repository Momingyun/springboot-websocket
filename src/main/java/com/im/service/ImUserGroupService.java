package com.im.service;

import com.im.entity.ImUserGroup;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * 用户群组关联表(ImUserGroup)表业务逻辑层
 *
 * @author liubin
 * @since 2019-08-14 12:01:30
 */
public interface ImUserGroupService {

    /**
     * 根据群号获取所有群成员
     *
     * @param groupId 群编号
     * @return 实例对象集合
     */
    List<ImUserGroup> queryById(Long groupId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ImUserGroup> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imUserGroup 实例对象
     * @return 对象列表
     */
    List<ImUserGroup> queryAll(ImUserGroup imUserGroup);

    /**
     * 新增数据
     *
     * @param imUserGroup 实例对象
     * @return 影响行数
     */
    int insert(ImUserGroup imUserGroup);

    /**
     * 修改数据
     *
     * @param imUserGroup 实例对象
     * @return 影响行数
     */
    int update(ImUserGroup imUserGroup);

    /**
     * 通过主键删除数据
     *
     * @param 主键
     * @return 影响行数
     */
    int deleteById();

    /**
     * 根据用户编号获取所有群信息
     *
     * @param userId
     * @return
     */
    List<ImUserGroup> getGroups(Long userId);
}