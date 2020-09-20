package com.java.service;

import org.apache.ibatis.annotations.Param;

/**
 * Description:	   <br/>
 * Date:     0015, September 15 22:27 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
public interface UserService {

    /**
     * 查询登录信息
     *
     * @param yuanGongID 用户ID
     * @param password   密码
     * @return
     */
    boolean findLoginInfo(Long yuanGongID, String password);

    /**
     * 根据员工号查询员工的姓名
     *
     * @param yuanGongID 用户ID
     * @return
     */
    String findYuangongName(Long yuanGongID);

    /**
     * 修改密码
     *
     * @param yuanGongID  员工ID
     * @param oldPassword 原始密码
     * @param newPassword 新密码
     * @return
     */
    boolean modifyPassword(Long yuanGongID, String oldPassword, String newPassword, String confirmPassword);

    /**
     * 根据员工号查询员工的头像路径
     * 员工ID
     *
     * @param yuanGongID
     * @return
     */
    String findHeadshot(@Param("yuanGongID") Long yuanGongID);

    /**
     * 根据员工号修改员工的头像路径
     *
     * @param touXiangPath 头像路径
     * @param yuanGongID   员工ID
     * @return
     */
    boolean modifyHeadshot(String touXiangPath, Long yuanGongID);
}
