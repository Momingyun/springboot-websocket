package com.im.service;

import com.im.entity.ImMessage;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.Date;
import java.util.List;

/**
 * 消息参数表(ImMessage)表业务逻辑层
 *
 * @author liubin
 * @since 2019-08-14 12:01:30
 */
public interface ImMessageService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImMessage queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<ImMessage> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imMessage 实例对象
     * @return 对象列表
     */
    List<ImMessage> queryAll(ImMessage imMessage);

    /**
     * 新增数据
     *
     * @param imMessage 实例对象
     * @return 影响行数
     */
    int insert(ImMessage imMessage);

    /**
     * 修改数据
     *
     * @param imMessage 实例对象
     * @return 影响行数
     */
    int update(ImMessage imMessage);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);
    /**
     * 删除某一时间段的消息
     *
     * @param date
     * @return 影响行数
     */
    int deleteByDate(Date date);

}