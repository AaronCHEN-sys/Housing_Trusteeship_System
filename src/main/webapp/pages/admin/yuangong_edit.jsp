<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=basePath%>static/css/select.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath%>static/css/style.css" rel="stylesheet" type="text/css"/>
    <style type="text/css">
        .errMsg {
            color: red;
            font-size: 12px;
        }
    </style>
    <script type="text/javascript" src="<%=basePath%>static/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath%>/static/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>static/js/editor/kindeditor.js"></script>
    <script src="<%=basePath%>static/js/jquery.datepair.min.js"></script>
    <script type="text/javascript">
        KE.show({
            id: 'content7',
        });
    </script>
    <script type="text/javascript">
        $(document).ready(function (e) {
            $(".select1").uedSelect({
                width: 345
            });
            $(".select2").uedSelect({
                width: 70
            });
            $(".select3").uedSelect({
                width: 100
            });
        });
    </script>
</head>

<body>
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">系统设置</a></li>
        <li><a href="#">编辑员工信息</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab">
            <ul>
                <li><a href="#tab1" class="selected">编辑员工信息</a></li>
            </ul>
        </div>
        <div id="tab1" class="tabson">
            <form action="<%=basePath%>/hr/editStaff.do" method="post">
                <ul class="forminfo">
                    <li>
                        <label>员工姓名<b>*</b></label>
                        <input type="hidden" name="yuanGongID"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.yuanGongID}">
                        <input placeholder="2-20位简体中文" required name="name" type="text" class="dfinput"
                               pattern="[\u4e00-\u9fa5]{2,20}" style="width:345px;"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.name}${requestScope.staff.name}"/>
                        <span class="errMsg">${requestScope.errorMap.name}</span>
                    </li>
                    <li>
                        <label>电话号码<b>*</b></label>
                        <input name="tel" type="text" placeholder="请输入手机号码" required class="dfinput"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.tel}${requestScope.staff.tel}"
                               style="width:345px;"/>
                        <span class="errMsg">${requestScope.errorMap.tel}</span>
                    </li>
                    <li>
                        <label>性别<b>*</b></label>
                        <input type="radio" name="gender"
                               value="男" ${requestScope.yuanGongInfoMap.yuanGongMap.gender=='男'?'checked':''}${requestScope.staff.gender=='男'?'checked':''}>
                        <span style="font-size: 15px;">男</span>
                        <input type="radio" name="gender"
                               value="女" ${requestScope.yuanGongInfoMap.yuanGongMap.gender=='女'?'checked':''}${requestScope.staff.gender=='女'?'checked':''}>
                        <span style="font-size: 15px;">女</span>
                    </li>
                    <li>
                        <label>身份证号<b>*</b></label>
                        <input name="idCard" type="text" required placeholder="请输入身份证" class="dfinput"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.idCard}${requestScope.staff.idCard}"
                               style="width:345px;"/>
                        <span class="errMsg">${requestScope.errorMap.idCard}</span>
                    </li>
                    <li>
                        <label>所属部门<b>*</b></label>
                        <c:if test="">
                            <c:forEach
                                    items="${(deptInfoList==null || deptInfoList=='')?requestScope.yuanGongInfoMap.deptInfoList:deptInfoList}"
                                    var="deptInfo">
                                <input type="radio" name="deptID"
                                       value="${deptInfo.id}" ${deptInfo.id==requestScope.yuanGongInfoMap.yuanGongMap.deptID?'checked':''} ${deptInfo.id==requestScope.staff.deptID?'checked':''}>
                                <span style="font-size: 15px;">${deptInfo.deptName}</span>
                            </c:forEach>
                        </c:if>
                    </li>
                    <li>
                        <label>地址<b>*</b></label>
                        <input name="address" placeholder="长度为2-200字符" required type="text" class="dfinput"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.address}${requestScope.staff.address}"
                               style="width:345px;"/>
                        <span class="errMsg">${requestScope.errorMap.address}</span>
                    </li>
                    <li>
                        <label>邮箱<b>*</b></label>
                        <input required placeholder="请输入邮箱" name="email" type="text" class="dfinput"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.email}${requestScope.staff.email}"
                               style="width:345px;"/>
                        <span class="errMsg">${requestScope.errorMap.email}</span>
                    </li>
                    <li>
                        <label>入职时间<b>*</b></label>
                        <input name="beginDate" type="text" style="text-align:center;width:120px;" class="dfinput"
                               placeholder="年/月/日" id="c-xl"
                               value="${requestScope.yuanGongInfoMap.yuanGongMap.beginDate}${requestScope.staff.beginDate}"/>
                        <span class="errMsg">${requestScope.errorMap.beginDate}</span>
                    </li>
                    <script type="text/javascript" src="<%=basePath%>static/js/laydate.dev.js"></script>
                    <script type="text/javascript">
                        laydate({
                            elem: '#c-xl'
                        });
                        laydate({
                            elem: '#c-x2'
                        });
                    </script>
                    <li>
                        <label>&nbsp;</label>
                        <input type="submit" id="addYuanGong" class="btn" value="编辑"/>
                    </li>
                </ul>
            </form>

        </div>
    </div>
    <script type="text/javascript">
        $(function () {
            $("#addYuanGong").click(function () {
                $(this).prop("disabled", "disabled");
            });
        });
    </script>
</div>
</body>
</html>
