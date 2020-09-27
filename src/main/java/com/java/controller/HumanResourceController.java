package com.java.controller;

import com.github.pagehelper.PageInfo;
import com.java.pojo.Emp;
import com.java.pojo.Staff;
import com.java.service.HumanResourceService;
import com.java.utils.PojoValidationTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Description:	   <br/>
 * Date:     0020, September 20 15:20 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Controller
@RequestMapping("/hr")
public class HumanResourceController {

    @Autowired
    private HumanResourceService humanResourceService;

    /**
     * 员工管理之查询员工信息
     *
     * @param pageNum
     * @param pageSize
     * @param flag     1 员工编号
     *                 2 姓名
     *                 3 手机号
     * @param value
     * @param model
     * @return
     */
    @RequestMapping("/getEmpInfo.do")
    public String getEmpInfo
    (
            @RequestParam(defaultValue = "1") String pageNum,
            @RequestParam(defaultValue = "6") String pageSize,
            String flag,
            String value,
            Model model) {
        PageInfo<Map<String, Object>> empInfoList = humanResourceService.findEmpInfo(pageNum, pageSize, flag, value);
        model.addAttribute("empInfoList", empInfoList);
        model.addAttribute("flag", flag);
        model.addAttribute("value", value);
        return "/pages/admin/yuangong.jsp";
    }


    /**
     * 跳转到添加员工页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toAddEmp.do")
    public String toAddEmp(Model model) {
        List<Map<String, Object>> deptInfoList = humanResourceService.findDeptInfo();
        model.addAttribute("deptInfoList", deptInfoList);
        return "/pages/admin/yuangong_add.jsp";
    }

    /**
     * 添加员工和用户
     *
     * @param emp
     * @param bindingResult
     * @param session
     * @return
     */
    @RequestMapping("/addEmp.do")
    public String addEmp(@Valid Emp emp, BindingResult bindingResult, HttpSession session) {
        try {
            session.setAttribute("emp", emp);
            Map<String, Object> errorMap = PojoValidationTool.validatePojo(bindingResult);
            if (errorMap == null) {
                boolean flag = humanResourceService.saveEmp(emp, session);
                if (flag) {
                    session.removeAttribute("emp");
                    session.removeAttribute("errorMap");
                    return "redirect:/hr/getEmpInfo.do";
                } else {
                    return "redirect:/hr/toAddEmp.do";
                }
            } else {
                session.setAttribute("errorMap", errorMap);
                return "redirect:/hr/toAddEmp.do";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:/hr/toAddEmp.do";
        }
    }

    /**
     * 跳转到编辑员工的页面
     *
     * @param model
     * @return
     */
    @RequestMapping("/toEditStaff.do")
    public String toEditStaff(String yuanGongID, Model model) {
        Map<String, Object> yuanGongInfoMap = humanResourceService.findYuanGongInfoWhenEdit(yuanGongID);
        boolean flag = (boolean) yuanGongInfoMap.get("flag");
        if (flag) {
            model.addAttribute("yuanGongInfoMap", yuanGongInfoMap);
            return "/pages/admin/yuangong_edit.jsp";
        } else {
            return "redirect:/hr/getEmpInfo.do";
        }
    }

    /**
     * 编辑员工
     *
     * @param staff
     * @param bindingResult
     * @param request
     * @return
     */
    @RequestMapping("/editStaff.do")
    public String editEmp(@Valid Staff staff, BindingResult bindingResult, HttpServletRequest request) {
        try {
            request.setAttribute("staff", staff);
            request.setAttribute("deptInfoList", humanResourceService.findDeptInfo());
            Map<String, Object> errorMap = PojoValidationTool.validatePojo(bindingResult);
            if (errorMap == null) {
                boolean flag = humanResourceService.modifyStaff(staff, request);
                if (flag) {
                    return "redirect:/hr/getEmpInfo.do";
                } else {
                    return "/pages/admin/yuangong_edit.jsp";
                }
            } else {
                request.setAttribute("errorMap", errorMap);
                return "/pages/admin/yuangong_edit.jsp";
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "redirect:/hr/toEditStaff.do";
        }
    }


    /**
     * 删除员工
     *
     * @param yuanGongID 员工ID
     * @param session
     * @return
     */
    @RequestMapping("/abandonYuanGong.do")
    @ResponseBody
    public Map<String, Object> abandonYuanGong(String yuanGongID, HttpSession session) {
        String currentYuanGongID = session.getAttribute("yuanGongID").toString();
        return humanResourceService.modifyFlagByYuanGongId(yuanGongID, currentYuanGongID);
    }

    /**
     * 批量删除员工
     *
     * @param yuanGongIDStr 员工ID字符串
     * @return
     */
    @RequestMapping("/abandonYuanGongByBatch.do")
    @ResponseBody
    public Map<String, Object> abandonYuanGongByBatch(String yuanGongIDStr, HttpSession session) {
        String currentYuanGongID = session.getAttribute("yuanGongID").toString();
        return humanResourceService.modifyFlagByBatch(yuanGongIDStr, currentYuanGongID);
    }
}
