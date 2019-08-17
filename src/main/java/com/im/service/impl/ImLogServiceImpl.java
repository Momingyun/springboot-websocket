package com.im.service.impl;

import com.github.pagehelper.Page;
import com.im.dao.ImLogDao;
import com.im.entity.ImLog;
import com.im.service.ImLogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日志表(ImLog)表服务实现类
 *
 * @author liubin
 * @since 2019-08-16 15:02:33
 */
@Service("imLogService")
public class ImLogServiceImpl implements ImLogService {
    @Resource
    private ImLogDao imLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ImLog queryById(Long id) {
        return this.imLogDao.queryById(id);
    }

    /**
     * 通过实体作为筛选条件查询
     *
     * @param imLog 实例对象
     * @return 对象列表
     */
    @Override
    public Page<ImLog> queryAll(ImLog imLog) {
        return this.imLogDao.queryAll(imLog);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    @Override
    public List<ImLog> queryAllByLimit(int offset, int limit) {
        return this.imLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param imLog 实例对象
     * @return 实例对象
     */
    @Override
    public int insert(ImLog imLog) {
        return this.imLogDao.insert(imLog);
    }

    /**
     * 修改数据
     *
     * @param imLog 实例对象
     * @return 实例对象
     */
    @Override
    public int update(ImLog imLog) {
        return this.imLogDao.update(imLog);
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Long id) {
        return this.imLogDao.deleteById(id) > 0;
    }
}