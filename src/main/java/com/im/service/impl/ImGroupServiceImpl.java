package com.im.service.impl;

import com.im.dao.ImGroupDao;
import com.im.entity.ImGroup;
import com.im.service.ImGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 群聊信息表(ImGroup)表业务逻辑层实现类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Service("ImGroupService")
public class ImGroupServiceImpl implements ImGroupService {
   @Autowired
   private ImGroupDao imGroupDao;
    /**
     * 根据用户编号获取所拥有的群信息
     *
     * @param userId 用户编号
     * @return 实例对象
     */
    @Override
    public List<ImGroup> queryById(Long userId) {
        return imGroupDao.queryById(userId);
    }

    @Override
    public List<ImGroup> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<ImGroup> queryAll(ImGroup imGroup) {
        return null;
    }

    @Override
    public int insert(ImGroup imGroup) {
        return 0;
    }

    @Override
    public int update(ImGroup imGroup) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }
}
