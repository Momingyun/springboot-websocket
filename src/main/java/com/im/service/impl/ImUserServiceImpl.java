package com.im.service.impl;

import com.im.common.utils.IdUtil;
import com.im.common.utils.IpUtil;
import com.im.dao.ImUserDao;
import com.im.entity.ImUser;
import com.im.service.ImUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Description 用户信息表(ImUser)表业务逻辑层实现类
 * @data 2019/8/14
 * @Author: LiuBin
 * @Modified By:
 */
@Service("ImUserService")
public class ImUserServiceImpl implements ImUserService {
    @Autowired
    private ImUserDao imUserDao;
    @Autowired
    private HttpServletRequest request;

    /**
     * 获取个人信息
     *
     * @param id 主键
     * @return
     */
    @Override
    public ImUser queryById(Long id) {
        return this.imUserDao.queryById(id);
    }

    @Override
    public List<ImUser> queryAllByLimit(int offset, int limit) {
        return null;
    }

    @Override
    public List<ImUser> queryAll(ImUser imUser) {
        return null;
    }

    /**
     * 新增用户
     *
     * @param imUser 实例对象
     * @return
     */
    @Override
    public int insert(ImUser imUser) {
        //判断用户是否存在
        ImUser user = new ImUser();
        user.setAccount(imUser.getAccount());
        List<ImUser> imUsers = this.imUserDao.queryAll(user);
        if (imUsers.size() > 0) {
            return -1;
        }
        imUser.setId(IdUtil.getId());
        int insert = this.imUserDao.insert(imUser);
        return insert;
    }

    /**
     * 修改资料
     *
     * @param imUser 实例对象
     * @return
     */
    @Override
    public int update(ImUser imUser) {
        int update = this.imUserDao.update(imUser);
        return update;
    }

    @Override
    public int deleteById(Long id) {
        return 0;
    }

    /**
     * 用户登录
     *
     * @param account
     * @param password
     * @return
     */
    @Override
    public ImUser login(String account, String password) {
        ImUser login = this.imUserDao.login(account, password);
        if (login != null) {
            login.setLoginIp(IpUtil.getIpAddr(request));
            this.imUserDao.update(login);
            login.setLineStatus(1);
            return login;
        } else {
            return null;
        }

    }
}
