package com.java.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.java.mapper.HumanResourceMapper;
import com.java.pojo.Emp;
import com.java.pojo.Staff;
import com.java.service.HumanResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Description:	   <br/>
 * Date:     0020, September 20 15:22 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Service
@Transactional(readOnly = false)
public class HumanResourceServiceImpl implements HumanResourceService {

    @Autowired
    private HumanResourceMapper humanResourceMapper;

    @Override
    public PageInfo<Map<String, Object>> findEmpInfo(String pageNum, String pageSize, String flag, String value) {
        //1.数据校验
        if (pageNum == null || pageSize == null || !pageNum.matches("[1-9]\\d*") || !pageSize.matches("[1-9]\\d*")) {
            pageNum = "1";
            pageSize = "7";
        }
        if (value != null) {
            value = value.trim();
        }
        //2.开始分页
        PageHelper.startPage(Integer.parseInt(pageNum), Integer.parseInt(pageSize));
        //3.查询数据
        List<Map<String, Object>> empInfoList = humanResourceMapper.selectEmpInfo(flag, value);
        PageInfo<Map<String, Object>> pageInfo = new PageInfo<>(empInfoList);
        return pageInfo;
    }

    @Override
    public List<Map<String, Object>> findDeptInfo() {
        return humanResourceMapper.selectDeptInfo();
    }

    @Override
    public boolean saveEmp(Emp emp, HttpSession session) throws SQLException {
        //查询手机号码或者身份证号是否存在
        int i = humanResourceMapper.selectCountYuanGongByTel(emp);
        int j = humanResourceMapper.selectCountYuanGongByIdCard(emp);
        Map<String, Object> errorMap = new HashMap<>();
        if (i == 1) {
            errorMap.put("tel", "*手机号重复!");
        }
        if (j == 1) {
            errorMap.put("idCard", "*身份证号重复!");
        }
        if (i == 1 || j == 1) {
            session.setAttribute("errorMap", errorMap);
            return false;
        }
        int flag1 = humanResourceMapper.insertEmp(emp);
        int flag2 = humanResourceMapper.insertUser(emp.getId());
        if (flag1 == 1 && flag2 == 1) {
            return true;
        } else {
            throw new SQLException("");
        }
    }

    @Override
    public Map<String, Object> findYuanGongInfoWhenEdit(String yuanGongID) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("flag", true);
        //1.数据校验
        if (yuanGongID == null || !yuanGongID.matches("[1-9]\\d*")) {
            resultMap.put("flag", false);
        }
        //2.yuanGongID数据格式正确,则查询此员工编号是否存在
        int i = humanResourceMapper.selectCountYuanGongByYuanGongId(yuanGongID);
        if (i != 1) {
            resultMap.put("flag", false);
        }
        //3.如果存在,再查询员工详细信息和所有部门信息
        List<Map<String, Object>> deptInfoList = humanResourceMapper.selectDeptInfo();
        Map<String, Object> yuanGongMap = humanResourceMapper.selectYuanGongByYuanGongId(yuanGongID);
        resultMap.put("deptInfoList", deptInfoList);
        resultMap.put("yuanGongMap", yuanGongMap);
        return resultMap;
    }

    @Override
    public boolean modifyStaff(Staff staff, HttpServletRequest request) throws SQLException {
        //判断手机号码和身份证是否有修改过
        Map<String, Object> TelAndIdCardMap = humanResourceMapper.selectTelAndIdCardById(Long.parseLong(staff.getYuanGongID()));
        String tel = (String) TelAndIdCardMap.get("tel");
        String idCard = (String) TelAndIdCardMap.get("idCard");

        //查询手机号码或者身份证号是否存在
        Map<String, Object> errorMap = new HashMap<>();
        int i = 0;
        int j = 0;
        if (!tel.equals(staff.getTel())) {
            i = humanResourceMapper.selectCountYuanGongByTelWhenEdit(staff);
            if (i == 1) {
                errorMap.put("tel", "*手机号重复!");
            }
        }
        if (!idCard.equals(staff.getIdCard())) {
            j = humanResourceMapper.selectCountYuanGongByIdCardWhenEdit(staff);
            if (j == 1) {
                errorMap.put("idCard", "*身份证号重复!");
            }
        }
        if (i == 1 || j == 1) {
            request.setAttribute("errorMap", errorMap);
            return false;
        }
        //修改员工信息
        int flag1 = humanResourceMapper.updateStaff(staff);
        if (flag1 == 1) {
            return true;
        } else {
            throw new SQLException("");
        }
    }

    @Override
    public Map<String, Object> modifyFlagByYuanGongId(String yuanGongID, String currentYuanGongID) {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("flag", true);
        //1.数据校验
        if (yuanGongID == null || !yuanGongID.matches("[1-9]\\d*")) {
            resultMap.put("flag", false);
            resultMap.put("errorMsg", "亲！请不要乱搞！");
            return resultMap;
        }
        //2.判断是否自己删除自己
        if (yuanGongID.equals(currentYuanGongID)) {
            resultMap.put("flag", false);
            resultMap.put("errorMsg", "亲！不能删除自己！");
            return resultMap;
        }
        //3.判断当前登录的用户是否是超级管理员
        int i = humanResourceMapper.selectAdminUser(currentYuanGongID);
        if (i != 1) {
            resultMap.put("flag", false);
            resultMap.put("errorMsg", "不是超级管理员，没有删除权限！");
            return resultMap;
        }
        //4.执行更新操作
        int j = humanResourceMapper.updateFlagByYuanGongId(yuanGongID);
        if (j != 1) {
            resultMap.put("flag", false);
            resultMap.put("errorMsg", "删除失败！此员工不存在！");
            return resultMap;
        }
        return resultMap;
    }

}
