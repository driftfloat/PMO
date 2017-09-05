<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
</head>
<body>
    <!-- topbar starts -->
    <div class="navbar navbar-default" role="navigation">

        <div class="navbar-inner">
        	<button type="button" class="navbar-toggle pull-left animated flip">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html"> <img alt="Charisma Logo" src="<%=path %>/img/title_logo.png" class="hidden-xs"/>
                <span>PMO</span></a>
            <!-- user dropdown starts -->
            <div class="btn-group pull-right">
            	<span style="float: left;margin-right: 20px;font-size: 1.2em;" >你好：${ sessionScope.loginUser.nickname}</span>
                <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"> ${sessionScope.employee.getName()}</span>
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu">
<!--                      <li><a href="#">个人信息</a></li>
					 <li><a href="#">密码修改</a></li>
 -->                    <li class="divider"></li>
                    <li><a href="<%=path %>/service/manage/logout.html">注销</a></li>
                </ul>
            </div>
            <!-- user dropdown ends -->
        </div>
    </div>
    <!-- topbar ends -->
</body>
</html>


