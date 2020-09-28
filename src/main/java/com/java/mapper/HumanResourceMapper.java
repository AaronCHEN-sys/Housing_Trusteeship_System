package com.java.mapper;

import com.java.pojo.Emp;
import com.java.pojo.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Description:	   <br/>
 * Date:     0020, September 20 15:21 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Repository
public interface HumanResourceMapper {

    /**
     * 员工管理之查询员工信息
     *
     * @param flag  1 员工编号
     *              2 姓名
     *              3 手机号
     * @param value
     * @return
     */
    List<Map<String, Object>> selectEmpInfo(@Param("flag") String flag, @Param("value") String value);

    /**
     * 查询所有部门信息
     *
     * @return
     */
    List<Map<String, Object>> selectDeptInfo();

    /**
     * 添加员工
     *
     * @param emp
     * @return
     */
    int insertEmp(Emp emp);

    /**
     * 添加用户
     *
     * @param yuanGongID 员工ID
     * @return
     */
    int insertUser(@Param("yuanGongID") Long yuanGongID);

    /**
     * 根据手机号查询是否存在该用户
     *
     * @param emp
     * @return
     */
    int selectCountYuanGongByTel(Emp emp);

    /**
     * 根据身份证查询是否存在该用户
     *
     * @param emp
     * @return
     */
    int selectCountYuanGongByIdCard(Emp emp);

    /**
     * 根据ID查询是否存在该用户
     *
     * @param yuanGongID 员工ID
     * @return
     */
    int selectCountYuanGongByYuanGongId(@Param("yuanGongID") String yuanGongID);

    /**
     * 根据ID查询用户信息
     *
     * @param yuanGongID 员工ID
     * @return
     */
    Map<String, Object> selectYuanGongByYuanGongId(@Param("yuanGongID") String yuanGongID);

    /**
     * 根据员工ID修改员工
     *
     * @param staff
     * @return
     */
    int updateStaff(Staff staff);

    /**
     * 根据手机号查询是否存在该用户
     *
     * @param staff
     * @return
     */
    int selectCountYuanGongByTelWhenEdit(Staff staff);

    /**
     * 根据身份证查询是否存在该用户
     *
     * @param staff
     * @return
     */
    int selectCountYuanGongByIdCardWhenEdit(Staff staff);

    /**
     * 根据id查询原手机号和身份证号
     *
     * @param id 员工主键id
     * @return
     */
    Map<String, Object> selectTelAndIdCardById(@Param("id") Long id);

    /**
     * 更新用户状态
     *
     * @param yuanGongID 员工ID
     * @return
     */
    int updateFlagByYuanGongId(@Param("yuanGongID") String yuanGongID);

    /**
     * 根据员工ID查询该员工是否是超级管理员
     *
     * @param yuanGongID 员工ID
     * @return
     */
    int selectAdminUser(@Param("yuanGongID") String yuanGongID);

    /**
     * 批量更新用户状态
     *
     * @param yuanGongIDStr 员工ID字符串
     * @return
     */
    int updateFlagByBatch(@Param("yuanGongIDStr") String yuanGongIDStr);

    /**
     * 根据员工ID查询在当天是否有打卡记录
     *
     * @param yuanGongID 员工ID
     * @return
     */
    int selectCountFirstDaKa(@Param("yuanGongID") String yuanGongID);

    /**
     * 第一次打卡
     *
     * @param yuanGongID 员工ID
     * @param daKa1      打一次打卡时间
     * @param zhuangTai  状态
     * @return
     */
    int insertFirstDaKa(@Param("yuanGongID") String yuanGongID, @Param("daKa1") String daKa1, @Param("zhuangTai") String zhuangTai);

    /**
     * 第二次打卡
     *
     * @param daKa2      打二次打卡时间
     * @param zhuangTai  状态
     * @param yuanGongID 员工ID
     * @return
     */
    int updateDaKa2AndCiShu(@Param("daKa2") String daKa2, @Param("zhuangTai") String zhuangTai, @Param("yuanGongID") String yuanGongID);

}
