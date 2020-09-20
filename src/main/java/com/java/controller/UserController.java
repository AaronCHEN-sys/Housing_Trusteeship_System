package com.java.controller;

import com.java.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Description:	   <br/>
 * Date:     0015, September 15 22:03 <br/>
 *
 * @author Aaron CHEN
 * @see
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param yuanGongID 用户名
     * @param password   密码
     * @param password1  隐藏的密码
     * @param session
     * @return
     */
    @RequestMapping("/getLogin.do")
    public String getLogin(Long yuanGongID, String password, String password1, HttpSession session) {
        boolean flag = userService.findLoginInfo(yuanGongID, "".equals(password) ? password1 : password);
        if (flag) {
            session.setAttribute("username", userService.findYuangongName(yuanGongID));
            session.setAttribute("yuanGongID", yuanGongID);
            session.setAttribute("headshot", userService.findHeadshot(yuanGongID));
            return "redirect:/pages/admin/main.jsp";
        } else {
            return "redirect:/pages/admin/login.jsp";
        }
    }

    /**
     * 安全退出
     *
     * @param session
     * @return
     */
    @RequestMapping("/getLogout.do")
    public String getLogout(HttpSession session) {
        //让session失效
        session.invalidate();
        return "redirect:/pages/admin/login.jsp";
    }

    /**
     * 修改密码
     *
     * @param oldPassword     原始密码
     * @param newPassword     新密码
     * @param confirmPassword 确认密码
     * @return
     */
    @RequestMapping("/alterPassword.do")
    @ResponseBody
    public boolean alterPassword(String oldPassword, String newPassword, String confirmPassword, HttpSession session) {
        Object yuanGongID = session.getAttribute("yuanGongID");
        if (yuanGongID == null) {
            return userService.modifyPassword(null, oldPassword, newPassword, confirmPassword);
        } else {
            return userService.modifyPassword((Long) yuanGongID, oldPassword, newPassword, confirmPassword);
        }
    }


    /**
     * 头像上传
     *
     * @param touXiang
     * @return
     */
    @RequestMapping("/uploadHeadshotIcon.do")
    public String uploadHeadshotIcon(MultipartFile touXiang, HttpServletRequest request) {
        try {
            ServletContext application = request.getServletContext();
            //1.获取此工程才本地电脑上的绝对路径
            String realPath = application.getRealPath("/static/uploads");
            //2.动态产生子文件夹
            String dateStr = new SimpleDateFormat("yyyyMMdd\\HH\\mm\\ss").format(new Date());
            //3.确定最终的文件路径
            String finalDirectory = realPath + "\\" + dateStr + "\\";
            File file = new File(finalDirectory);
            if (!file.exists()) {
                file.mkdirs();
            }
            //4.动态生成文件名字
            String uuid = UUID.randomUUID().toString();
            //5.获取文件类型名称
            String originalFilename = touXiang.getOriginalFilename();
            String fileType = originalFilename.substring(originalFilename.lastIndexOf("."));
            //6.拼接出上传的文件在本地电脑的最终路径
            String finalPath = finalDirectory + uuid + fileType;
            touXiang.transferTo(new File(finalPath));
            //7.保存文件路径保存到数据库
            Object yuanGongID = request.getSession().getAttribute("yuanGongID");
            boolean flag;
            if (yuanGongID == null) {
                flag = userService.modifyHeadshot(dateStr + "\\" + uuid + fileType, null);
            } else {
                flag = userService.modifyHeadshot(dateStr + "\\" + uuid + fileType, (Long) yuanGongID);
            }
            if (flag) {
                request.getSession().setAttribute("headshot", dateStr + "\\" + uuid + fileType);
                return "/pages/admin/main.jsp";
            } else {
                return "/pages/admin/changeHeadshot.jsp";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "/pages/admin/changeHeadshot.jsp";
        }
    }
}
