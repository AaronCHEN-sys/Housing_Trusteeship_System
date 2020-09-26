package com.java.service;

import com.github.pagehelper.PageInfo;
import com.java.pojo.Emp;
import com.java.pojo.Staff;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Description:	   <br/>
 * Date:     0020, September 20 16:19 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
public interface HumanResourceService {

    /**
     * 员工管理之查询员工信息
     *
     * @param pageNum
     * @param pageSize
     * @param flag     1 员工编号
     *                 2 姓名
     *                 3 手机号
     * @param value
     * @return
     */
    PageInfo<Map<String, Object>> findEmpInfo(String pageNum, String pageSize, String flag, String value);

    /**
     * 查询所有部门信息
     *
     * @return
     */
    List<Map<String, Object>> findDeptInfo();

    /**
     * 添加员工和用户
     *
     * @param emp
     * @param session
     * @return
     * @throws SQLException
     */
    boolean saveEmp(Emp emp, HttpSession session) throws SQLException;

    /**
     * 编辑时查询员工详细信息
     *
     * @param yuanGongID 员工ID
     * @return
     */
    Map<String, Object> findYuanGongInfoWhenEdit(String yuanGongID);

    /**
     * 编辑员工
     *
     * @param staff
     * @param request
     * @return
     * @throws SQLException
     */
    boolean modifyStaff(Staff staff, HttpServletRequest request) throws SQLException;
}
