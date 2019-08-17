package com.im.common.utils;

import com.im.entity.ImUser;
import com.im.service.ImUserService;

/**
 * @Description 用户编号生成类 6位
 * @data 2019/8/15
 * @Author: LiuBin
 * @Modified By:
 */
public class IdUtil {
    private static ImUserService imUserService;

    //生成随机数
    public static Long getId() {
        double rand = (Math.random() * 9 + 1) * 100000;
        long id = new Double(rand).longValue();
        //验证id是否已经存在
        ImUser imUser = imUserService.queryById(id);
        if (imUser != null) {
            IdUtil.getId();
        }
        return id;
    }
}
