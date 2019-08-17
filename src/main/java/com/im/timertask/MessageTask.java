package com.im.timertask;

import com.im.common.utils.TimeUtil;
import com.im.service.ImLogService;
import com.im.service.ImMessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.text.ParseException;
import java.util.Date;

/**
 * @Description 消息管理定时器
 * @data 2019/8/17
 * @Author: LiuBin
 * @Modified By:
 */
@Slf4j
public class MessageTask {
    @Autowired
    private ImMessageService imMessageService;

    /**
     * 每天晚上23点
     * 清楚七天前的消息
     */
    @Scheduled(cron = "0 0 23 * * ?")
    public void clearMessage() {
        try {
            //获取七天前的时间
            Date date = TimeUtil.getDate(7);
            int delete = this.imMessageService.deleteByDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
