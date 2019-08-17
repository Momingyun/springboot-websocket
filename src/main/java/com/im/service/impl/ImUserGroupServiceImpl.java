package com.im.service.impl;

import com.im.dao.ImUserGroupDao;
import com.im.entity.ImUserGroup;
import com.im.service.ImUserGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户群组关联表(ImUserGroup)表业务逻辑层实现类
 * @data 2019/8/15
 * @Author: LiuBin
 * @Modified By:
 */
@Service
public class ImUserGroupServiceImpl implements ImUserGroupService {

    @Autowired
    private ImUserGroupDao imUserGroupDao;

    /**
     * 根据群号获取所有群成员
     *
     * @param groupId 群编号
     * @return 实例对象集合
     */
    @Override
    public List<ImUserGroup> queryById(Long groupId) {
        return this.imUserGroupDao.queryById(groupId);
    }

    @Override
    public List<ImUserGroup> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<ImUserGroup> queryAll(ImUserGroup imUserGroup) {
        return null;
    }

    @Override
    public int insert(ImUserGroup imUserGroup) {
        return 0;
    }

    @Override
    public int update(ImUserGroup imUserGroup) {
        return 0;
    }

    @Override
    public int deleteById() {
        return 0;
    }
}
