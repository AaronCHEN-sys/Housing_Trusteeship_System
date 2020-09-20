package com.java.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.java.mapper.UserMapper;
import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description:	   <br/>
 * Date:     0015, September 15 22:13 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findLoginInfo(Long yuanGongID, String password) {
        //数据校验
        if (yuanGongID == null || password == null || !yuanGongID.toString().matches("\\d{8,12}") || !password.matches("\\w{8,16}")) {
            return false;
        }
        return userMapper.selectLoginInfo(yuanGongID, SecureUtil.md5(password)) == 1;
    }

    @Override
    public String findYuangongName(Long yuanGongID) {
        return userMapper.selectYuangongName(yuanGongID);
    }

    @Override
    public boolean modifyPassword(Long yuanGongID, String oldPassword, String newPassword, String confirmPassword) {
        if (yuanGongID == null || oldPassword == null || newPassword == null || confirmPassword == null || !newPassword.equals(confirmPassword)) {
            return false;
        }
        if (!yuanGongID.toString().matches("\\d{8,12}") || !newPassword.matches("\\w{8,16}")) {
            return false;
        }
        return userMapper.updatePassword(yuanGongID, SecureUtil.md5(oldPassword), SecureUtil.md5(newPassword)) == 1;
    }

    @Override
    public String findHeadshot(Long yuanGongID) {
        return userMapper.selectHeadshot(yuanGongID);
    }

    @Override
    public boolean modifyHeadshot(String touXiangPath, Long yuanGongID) {
        if (touXiangPath == null || yuanGongID == null) {
            return false;
        }
        return userMapper.updateHeadshot(touXiangPath, yuanGongID) == 1;
    }

}
