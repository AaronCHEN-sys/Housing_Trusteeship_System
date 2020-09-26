<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>无标题文档</title>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/style.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath %>/static/css/select.css"/>
    <script type="text/javascript" src="<%=basePath %>/static/js/jquery.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/select-ui.min.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/editor/kindeditor.js"></script>
    <script type="text/javascript" src="<%=basePath %>/static/js/jquery.datepair.min.js"></script>
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
        <li><a href="#">房源托管</a></li>
        <li><a href="#">添加房源</a></li>
    </ul>
</div>
<div class="formbody">
    <div id="usual1" class="usual">
        <div class="itab">
            <ul>
                <li><a href="#tab1" class="selected">添加房源</a></li>
            </ul>
        </div>
        <div id="tab1" class="tabson">
            <form action="doform.html" method="get">
                <ul class="forminfo">

                    <li>
                        <label>小区名称<b>*</b></label>
                        <input name="" type="text" class="dfinput" placeholder="请填写小区名称" style="width:345px;"/>
                    </li>
                    <li>
                        <label>房东姓名<b>*</b></label>
                        <input name="" type="text" class="dfinput" value="" style="width:100px;"/>
                    </li>
                    <li>
                        <label>业务员姓名<b>*</b></label>
                        <input name="" type="text" class="dfinput" value="" style="width:100px;"/>
                    </li>
                    <li>
                        <label>房屋户型<b>*</b></label>
                        <div class="usercity">
                            <div class="cityleft">
                                <select class="select2">
                                    <option>1室</option>
                                    <option>2室</option>
                                    <option>3室</option>
                                    <option>4室</option>
                                </select>
                            </div>
                            <div class="cityleft">
                                <select class="select2">
                                    <option>0厅</option>
                                    <option>1厅</option>
                                    <option>2厅</option>
                                </select>
                            </div>
                            <div class="cityleft">
                                <select class="select2">
                                    <option>0卫</option>
                                    <option>1卫</option>
                                    <option>2卫</option>
                                </select>
                            </div>
                            <div class="cityleft">
                                共&nbsp;&nbsp;<input name="" type="text" class="dfinput" value="" style="width:45px;"/>&nbsp;&nbsp;㎡
                            </div>
                        </div>
                    </li>
                    <li>
                        <label>托管时间<b>*</b></label>

                        <input name="" type="text" style="text-align:center;width:120px;" class="dfinput" value=""
                               id="c-xl">&nbsp;&nbsp;到&nbsp;&nbsp;
                        <input name="" type="text" style="text-align:center;width:120px;" class="dfinput" value=""
                               id="c-x2"/>


                    </li>
                    <script type="text/javascript" src="<%=basePath %>/static/js/laydate.dev.js"></script>
                    <script type="text/javascript">
                        laydate({
                            elem: '#c-xl'
                        });
                        laydate({
                            elem: '#c-x2'
                        });
                    </script>

                    <li>
                        <label>托管金额<b>*</b></label>
                        <input name="" type="text" class="dfinput" placeholder="请输入金额数" style="width:345px;"/>&nbsp;&nbsp;元
                    </li>

                    <li>
                        <label>房源描述<b>*</b></label>
                        <textarea id="content7" name="content"
                                  style="width:700px;height:250px;visibility:hidden;"></textarea>
                    </li>
                    <li>
                        <label>&nbsp;</label>
                        <input name="" type="submit" class="btn" value="添加"/>
                    </li>
                </ul>
            </form>

        </div>
    </div>
    <script type="text/javascript">
        $('.tablelist tbody tr:odd').addClass('odd');
    </script>
</div>
</body>
</html>
