package com.im.dao;

import com.im.entity.ImUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 用户信息表(ImUser)表数据库访问层
 *
 * @author liubin
 * @since 2019-08-14 12:01:30
 */
@Repository
public interface ImUserDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ImUser queryById(Long id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ImUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param imUser 实例对象
     * @return 对象列表
     */
    List<ImUser> queryAll(ImUser imUser);

    /**
     * 新增数据
     *
     * @param imUser 实例对象
     * @return 影响行数
     */
    int insert(ImUser imUser);

    /**
     * 修改数据
     *
     * @param imUser 实例对象
     * @return 影响行数
     */
    int update(ImUser imUser);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Long id);

    /**
     * 登录
     * @param account
     * @param password
     * @return
     */
    public ImUser login(String account, String password);

}