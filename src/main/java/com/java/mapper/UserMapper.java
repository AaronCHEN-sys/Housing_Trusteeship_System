package com.java.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * Description:	   <br/>
 * Date:     0015, September 15 22:09 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Repository
public interface UserMapper {
    /**
     * 根据员工号查询员工的登录信息
     *
     * @param yuanGongID 用户ID
     * @param password   密码
     * @return
     */
    int selectLoginInfo(@Param("yuanGongID") Long yuanGongID, @Param("password") String password);

    /**
     * 根据员工号查询员工的姓名
     *
     * @param yuanGongID 用户ID
     * @return
     */
    String selectYuangongName(@Param("yuanGongID") Long yuanGongID);

    /**
     * 修改密码
     *
     * @param yuanGongID  员工ID
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @return
     */
    int updatePassword(@Param("yuanGongID") Long yuanGongID, @Param("oldPassword") String oldPassword, @Param("newPassword") String newPassword);

    /**
     * 根据员工号查询员工的头像路径
     *
     * @param yuanGongID 员工ID
     * @return
     */
    String selectHeadshot(@Param("yuanGongID") Long yuanGongID);

    /**
     * 根据员工号修改员工的头像路径
     *
     * @param touXiangPath 头像路径
     * @param yuanGongID   员工ID
     * @return
     */
    int updateHeadshot(@Param("touXiangPath") String touXiangPath, @Param("yuanGongID") Long yuanGongID);
}
