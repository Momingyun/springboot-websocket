package com.im.dao;

import com.im.entity.ImUserFriend;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户好友表(ImUserFriend)表数据库访问层
 *
 * @author liubin
 * @since 2019-08-14 12:01:30
 */
@Repository
public interface ImUserFriendDao {

    /**
     * 获取好友列表
     *
     * @param id
     * @return
     */
    List<ImUserFriend> getFriendList(Long id);

    /**
     * 验证是否已经添加好友
     *
     * @param imUserFriend
     * @return
     */
    int isExistFriend(ImUserFriend imUserFriend);

    /**
     * 添加好友
     *
     * @param imUserFriend
     * @return
     */
    int insert(ImUserFriend imUserFriend);

    /**
     * 删除好友
     *
     * @param imUserFriend
     * @return
     */
    int delete(ImUserFriend imUserFriend);

    /**
     * 修改好友申请状态
     *
     * @param imUserFriend
     * @return
     */
    int update(ImUserFriend imUserFriend);
}