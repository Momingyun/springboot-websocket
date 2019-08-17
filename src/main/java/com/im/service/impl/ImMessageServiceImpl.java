package com.im.service.impl;

import com.im.dao.ImMessageDao;
import com.im.entity.ImMessage;
import com.im.service.ImMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description 消息参数表(ImMessage)表业务逻辑层实现类
 * @data 2019/8/15
 * @Author: LiuBin
 * @Modified By:
 */
@Service("ImMessageService")
public class ImMessageServiceImpl implements ImMessageService {
    @Autowired
    private ImMessageDao imMessageDao;

    @Override
    public ImMessage queryById(Long id) {
        return this.imMessageDao.queryById(id);
    }

    @Override
    public List<ImMessage> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<ImMessage> queryAll(ImMessage imMessage) {
        return this.imMessageDao.queryAll(imMessage);
    }

    @Override
    public int insert(ImMessage imMessage) {
        return 0;
    }

    @Override
    public int update(ImMessage imMessage) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    /**
     * 删除某一时间段的消息
     *
     * @param date
     * @return 影响行数
     */
    @Override
    public int deleteByDate(Date date) {
        return this.imMessageDao.deleteByDate(date);
    }
}
