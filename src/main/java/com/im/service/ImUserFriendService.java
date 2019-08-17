package com.im.service;

import com.im.entity.ImUser;
import com.im.entity.ImUserFriend;

import java.util.List;

/**
 * 用户好友表(ImUserFriend)表业务逻辑层
 *
 * @author liubin
 * @since 2019-08-14 12:01:30
 */
public interface ImUserFriendService {
    /**
     * 获取好友列表
     *
     * @param id
     * @return
     */
    List<ImUser> getFriendList(Long id);

    /**
     * 添加好友
     * @param imUserFriend
     * @return
     */
    int addFriend(ImUserFriend imUserFriend);

    /**
     * 删除好友
     * @param imUserFriend
     * @return
     */
    int deleteFriend(ImUserFriend imUserFriend);

    /**
     * 修改好友申请状态
     * @param imUserFriend
     * @return
     */
    int update(ImUserFriend imUserFriend);
}