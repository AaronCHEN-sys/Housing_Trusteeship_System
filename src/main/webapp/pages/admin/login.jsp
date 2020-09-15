<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath %>">
    <title>登陆</title>
    <meta http-equiv="content-Type" content="text/html" charset="utf-8">
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/index.css">

    <script type="text/javascript" src="<%=basePath %>/static/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/index1.js"></script>

</head>
<body>
<div class="content">
    <div id="header">
        <div class="header_title">
            <span class="title_con">房屋托管系统</span>
        </div>
    </div>
    <form id="#mainForm1" class="mainForm mainForm1">
        <div class="con_title">
            <span class="con_title_sp">欢迎登录房屋托管系统</span>
        </div>
        <div class="normalInput">
            <input type="text" class="phone" maxlength="11" placeholder="用户名" style="">
        </div>
        <span class="error error1"></span>

        <div class="normalInput">
            <input type="text" class="password" maxlength="16" autocomplete="off" placeholder="密码">
            <input type="password" class="password1" maxlength="16" autocomplete="off" placeholder="密码">
            <a id="pwdBtn" href="##" class="pwdBtnShow" isshow="false">
                <i class="i_icon"></i>
            </a>
        </div>
        <span class="error error3"></span>
        <a href="<%=basePath %>/pages/admin/main.jsp" class="fullBtnBlue">登陆</a>
    </form>
</div>

</body>
</html>