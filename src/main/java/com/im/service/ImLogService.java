package com.im.service;

import com.github.pagehelper.Page;
import com.im.entity.ImLog;
import java.util.List;

/**
 * 日志表(ImLog)表服务接口
 *
 * @author liubin
 * @since 2019-08-16 15:02:33
 */
public interface ImLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImLog queryById(Long id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ImLog> queryAllByLimit(int offset, int limit);

     /**
     * 通过实体作为筛选条件查询
     *
     * @param imLog 实例对象
     * @return 对象列表
     */
    Page<ImLog> queryAll(ImLog imLog);

    /**
     * 新增数据
     *
     * @param imLog 实例对象
     * @return 实例对象
     */
    int insert(ImLog imLog);

    /**
     * 修改数据
     *
     * @param imLog 实例对象
     * @return 实例对象
     */
    int update(ImLog imLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Long id);

}