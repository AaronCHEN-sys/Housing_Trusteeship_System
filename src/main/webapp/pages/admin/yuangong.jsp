<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link href="<%=basePath %>/static/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=basePath %>/static/css/select.css" rel="stylesheet" type="text/css"/>
    <link rel="stylesheet" href="<%=basePath%>/static/js/bootstrap/bootstrap.css"/>
    <link rel="stylesheet" href="<%=basePath %>/static/js/sweetalert/sweetalert.css">
    <script type="text/javascript" src="<%=basePath %>/static/js/sweetalert/sweetalert.min.js"></script>
    <script src="<%=basePath%>/static/js/bootstrap/jquery.min.js"></script>
    <%--<script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>--%>
    <script type="text/javascript" src="<%=basePath %>/static/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/editor/kindeditor.js"></script>
    <!-- 引入bootstrap分页 -->
    <script src="<%=basePath%>/static/js/bootstrap/bootstrap.min.js"></script>
    <script src="<%=basePath%>/static/js/bootstrap/bootstrap-paginator.js"></script>
    <script>
        $(function () {
            $('#pagination').bootstrapPaginator({
                bootstrapMajorVersion: 3,
                currentPage: ${requestScope.pageInfo.pageNum },
                totalPages: ${requestScope.pageInfo.pages },
                pageUrl: function (type, page, current) {
                    return 'hr/getStaffInfo.do?pageNum=' + page + "&flag=${requestScope.flag}&keyword=${requestScope.keyword}";
                },
                itemTexts: function (type, page, current) {
                    switch (type) {
                        case "first":
                            return "首页";
                        case "prev":
                            return "上一页";
                        case "next":
                            return "下一页";
                        case "last":
                            return "末页";
                        case "page":
                            return page;
                    }
                }
            });
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

            /*全选与全不选*/
            $("#selectAll").click(function () {
                var flag = $(this).prop("checked");
                if (flag) {
                    //全选
                    $("tbody input[type=checkbox]").prop("checked", true);
                } else {
                    //全不选
                    $("tbody input[type=checkbox]").prop("checked", false);
                }
            });

            /*批量删除用户*/
            $("#batchDeleteUser").click(function () {
                var $cks = $("tbody input[type=checkbox]:checked");
                var size = $cks.size();
                if (size >= 1) {
                    var yuanGongIDStr = "";
                    for (var i = 0; i < size; i++) {
                        var val = $cks.eq(i).val();
                        yuanGongIDStr += val + ",";
                    }
                    $.ajax({
                        url: "<%=basePath %>/hr/abandonYuanGongByBatch.do",
                        type: "POST",
                        dataType: "JSON",
                        data: {
                            "yuanGongIDStr": yuanGongIDStr
                        },
                        success: function (rs) {
                            if (rs.flag) {
                                //删除成功
                                swal({
                                    title: "提示",
                                    text: "删除成功!",
                                }, function () {
                                    window.location.reload();
                                });
                            } else {
                                //删除失败
                                var errorMsg = rs.errorMsg;
                                swal({
                                    title: "提示",
                                    text: errorMsg
                                });
                            }
                        }
                    });
                } else {
                    swal({
                        title: "提示",
                        text: "请选择需要删除的用户!"
                    });
                }
            });

        });
    </script>
</head>

<body>
<div class="place"><span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">人事管理</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab">
            <ul>
                <li><a href="#tab2">员工信息列表</a></li>
            </ul>
        </div>
        <div id="tab2" class="tabson">
            <form action="<%=basePath %>/hr/getEmpInfo.do" method="post">
                <ul class="seachform">
                    <li>
                        <label>查询方式</label>
                        <div class="vocation">
                            <select class="select3" name="flag">
                                <option value="1" ${flag=='1'?'selected':''}>员工编号</option>
                                <option value="2" ${flag=='2'?'selected':''}>员工姓名</option>
                                <option value="3" ${flag=='3'?'selected':''}>员工手机号</option>
                            </select>
                        </div>
                    </li>
                    <li>
                        <input name="value" value="${requestScope.value}" type="text" class="scinput"/>
                    </li>
                    <li>
                        <label>&nbsp;</label>
                        <input type="submit" class="scbtn" value="查询"/>
                    </li>
                </ul>
            </form>

            <div class="tools">
                <ul class="toolbar">
                    <li class="click" onclick="location.href='<%=basePath %>/hr/toAddEmp.do'">
                        <span><img src="<%=basePath %>/static/images/t01.png"></span>添加
                    </li>
                    <li id="batchDeleteUser">
                        <span><img src="<%=basePath %>/static/images/t03.png"></span>删除
                    </li>
                </ul>
            </div>
            <div class="clear"></div>
            <table class="tablelist">
                <thead>
                <tr>
                    <th><input name="" id="selectAll" type="checkbox" value=""/></th>
                    <th>序号<i class="sort"><img src="<%=basePath %>/static/images/px.gif"/></i></th>
                    <th>员工编号</th>
                    <th>员工姓名</th>
                    <th>性别</th>
                    <th>身份证号</th>
                    <th>所属部门</th>
                    <th>地址</th>
                    <th>电话</th>
                    <th>邮箱</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.empInfoList.list}" var="empInfo" varStatus="num">
                    <tr>
                        <td><input type="checkbox" value="${empInfo.yuanGongID}"/></td>
                        <td>${num.count}</td>
                        <td>${empInfo.yuanGongID}</td>
                        <td>${empInfo.name}</td>
                        <td>${empInfo.gender}</td>
                        <td>${empInfo.idCard}4</td>
                        <td>${empInfo.deptName}</td>
                        <td>${empInfo.address}</td>
                        <td>${empInfo.tel}</td>
                        <td>${empInfo.email}</td>
                        <td>
                            <a href="<%=basePath %>/hr/toEditStaff.do?yuanGongID=${empInfo.yuanGongID}"
                               class="tablelink">编辑</a>
                            <a href="javascript:void(0)" class="tablelink"
                               onclick="singleDelete(${empInfo.yuanGongID})"> 删除</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <!-- 把分页搞出来 -->
            <ul id="pagination"></ul>

        </div>
    </div>
    <script type="text/javascript">

        /*单个删除员工*/
        function singleDelete(yuanGongID) {
            $.ajax({
                url: "<%=basePath %>/hr/abandonYuanGong.do",
                type: "POST",
                dataType: "JSON",
                data: {
                    "yuanGongID": yuanGongID
                },
                success: function (rs) {
                    if (rs.flag) {
                        //删除成功
                        swal({
                            title: "提示",
                            text: "删除成功!",
                        }, function () {
                            window.location.reload();
                        });
                    } else {
                        //删除失败
                        var errorMsg = rs.errorMsg;
                        swal({
                            title: "提示",
                            text: errorMsg
                        });
                    }
                }
            });
        }

        jQuery("#usual1 ul").idTabs();

    </script>
    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>
</div>
</body>
</html>
