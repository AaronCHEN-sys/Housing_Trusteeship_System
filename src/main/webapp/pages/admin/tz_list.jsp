<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath %>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>退租计划</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/select.css"/>
    <script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/editor/kindeditor.js"></script>
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
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab">
            <ul>
                <li><a href="#tab2">退租列表</a></li>
            </ul>
        </div>
        <div id="tab2" class="tabson">
            <ul class="seachform">
                <li>
                    <label>查询方式</label>
                    <div class="vocation">
                        <select class="select3">
                            <option>按编号查询</option>
                            <option>按姓名查询</option>
                            <option>模糊查询</option>
                        </select>
                    </div>
                </li>
                <li>
                    <input name="" type="text" class="scinput"/>
                </li>
                <li>
                    <label>&nbsp;</label>
                    <input name="" type="button" class="scbtn" value="查询"/>
                </li>
            </ul>
            <table class="tablelist">
                <thead>
                <tr>

                    <th>编号<i class="sort"><img src="<%=basePath %>/static/images/px.gif"/></i></th>
                    <th>租客姓名</th>
                    <th>省份证号</th>
                    <th>入住时间</th>
                    <th>退租时间</th>
                    <th>退租原因</th>
                    <th>应退金额</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <td>1</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>刘一</td>
                    <td>422342198009082234</td>
                    <td>2015-6-11</td>
                    <td>2016-6-11</td>
                    <td>去外地工作</td>
                    <td>600</td>
                    <td><a href="#" class="tablelink">编辑</a> <a href="#" class="tablelink"> 删除</a></td>
                </tr>
                </tbody>
            </table>
            <div class="pagin">
                <div class="message">共<i class="blue">8</i>条记录，当前显示第&nbsp;<i class="blue">1&nbsp;</i>页</div>
                <ul class="paginList">
                    <li class="paginItem"><a href="javascript:;"><span class="pagepre"></span></a></li>
                    <li class="paginItem  current"><a href="javascript:;">1</a></li>
                    <li class="paginItem"><a href="javascript:;">2</a></li>
                    <li class="paginItem"><a href="javascript:;">3</a></li>
                    <li class="paginItem"><a href="javascript:;">4</a></li>
                    <li class="paginItem"><a href="javascript:;">5</a></li>
                    <li class="paginItem more"><a href="javascript:;">...</a></li>
                    <li class="paginItem"><a href="javascript:;">10</a></li>
                    <li class="paginItem"><a href="javascript:;"><span class="pagenxt"></span></a></li>
                </ul>
            </div>
        </div>
    </div>
    <script type="text/javascript">
        $("#usual1 ul").idTabs();
    </script>
    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>
</div>
</body>
</html>
