<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>${sessionScope.SystemName}</title>
    <meta name="author" content="moocss">
    <link rel="shortcut icon" href="favicon.ico">
<link href="<%=path %>/css/error.css" rel="stylesheet">
    <title>Error</title>
</head>
<body>

<p class="error-code">
    400
</p>

<p class="not-found">Wrong<br/>Request</p>

<div class="clear"></div>
<div class="content">
    Request message error. 
    <br>
    <a href="<%=path%>/service/manage/loginPage">Go Home</a>
</div>
</body>
</html>