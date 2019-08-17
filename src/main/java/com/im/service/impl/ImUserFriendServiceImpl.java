package com.im.service.impl;

import com.im.dao.ImUserFriendDao;
import com.im.entity.ImUser;
import com.im.entity.ImUserFriend;
import com.im.service.ImUserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description 用户好友表(ImUserFriend)表业务逻辑层实现类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Service("ImUserFriendService")
public class ImUserFriendServiceImpl implements ImUserFriendService {
    @Autowired
    private ImUserFriendDao imUserFriendDao;

    /**
     * 获取好友列表
     *
     * @param id
     * @return
     */
    @Override
    public List<ImUser> getFriendList(Long id) {
        List<ImUser> friendList = this.imUserFriendDao.getFriendList(id);
        return friendList;
    }

    /**
     * 添加好友
     * @param imUserFriend
     * @return
     */
    @Override
    public int addFriend(ImUserFriend imUserFriend) {
        //验证是否已经加好友
        ImUserFriend friend=new ImUserFriend();
        friend.setFriendId(imUserFriend.getFriendId());
        friend.setUserId(imUserFriend.getUserId());
        int count=this.imUserFriendDao.isExistFriend(friend);
        if (count>0){
            return -1;
        }
        int insert= this.imUserFriendDao.insert(imUserFriend);
        return insert;
    }

    /**
     * 删除好友
     * @param imUserFriend
     * @return
     */
    @Override
    public int deleteFriend(ImUserFriend imUserFriend) {
        return this.imUserFriendDao.delete(imUserFriend);
    }

    /**
     * 修改好友申请状态
     * @param imUserFriend
     * @return
     */
    @Override
    public int update(ImUserFriend imUserFriend) {
        return this.imUserFriendDao.update(imUserFriend);
    }
}
