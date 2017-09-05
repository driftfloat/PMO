<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html lang="en">
<head>
  	<meta charset="utf-8">
    <title>PMO</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
    <meta name="author" content="Muhammad Usman">

    <!-- The styles -->
    <link
		href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
		rel='stylesheet'>

    <link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">

    <link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
    <link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='<%=path %>/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
    <link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
    <link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
    <link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
    <link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
    <link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
    <link href='<%=path %>/css/animate.min.css' rel='stylesheet'>
    <link href="<%=path %>/css/login.css" rel="stylesheet">

    <!-- jQuery -->
    <script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>

    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
	<style type="text/css">
	small{font-size:16px;}
	</style>
</head>

<body class="login-background">
<div class="ch-container">
        
    <div class="row">
        <div style="min-width:319px" class="col-md-12 center login-header login-header" >
        		 
        		 		 <div style="min-width:319px" class="login-header-img">
        		 		 		<img src="<%=path %>/img/img-logo1.png" />
        		 		</div>
        		 	
        </div>
        		
           
        <!--/span-->
    </div><!--/row-->

    <div class="row">
        <div style="min-width: 320px;" class="well col-md-5 center login-box new-login-box">
    
            <div class="alert alert-info new-login-box-header" id="loginAlert" style="display: none;">
                <!-- Welcome to SpiderNet -->
            </div>
            <form id="loginForm" class="form-horizontal" method="post" action="/Pmo/service/user/login">
                <fieldset>
              		<div class="form-group" style="border-color:none ">
	              		
		                    <div class="input-group input-group-lg">
		                      	<span  class="input-group-addon input-area-span"><i class="glyphicon glyphicon-user green-1"></i></span>

								<input type="text" id="userName" name="userName" class="form-control input-area" placeholder="用户名">
		                    </div>
		                
	                    
					</div>
					<div class="clearfix"></div><br>
					<div class="form-group" style="border-color:">
						
		                    <div class="input-group input-group-lg">
		                        <span  class="input-group-addon input-area-span"><i class="glyphicon glyphicon-user green-1"></i></span>

		                        <input type="password" id="password" name="password" class="form-control input-area" placeholder="密码">
		                    </div>
	                   
	                    
					</div>
					<div class="clearfix"></div>
                   <!-- <div class="input-prepend">
                        <label class="remember" for="remember"><input type="checkbox" id="remember">  记住用户名</label>
                    </div>-->
                    <div class="clearfix"></div>
						
                    <p class="center col-md-5 p-style">
                    	<!-- <input type="submit" class="btn btn-primary btn-primary-new" value="登录"/> -->
                        <button type="submit" id="submitBtn" class="btn btn-primary btn-primary-new">登录</button>
                    </p>
                </fieldset>
            </form>
        </div>
        <!--/span-->
    </div><!--/row-->
</div><!--/fluid-row-->
	
<script type="text/javascript">
var path = "<%=path%>";

</script>
	
<!-- external javascript -->

<script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="<%=path %>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>

<!-- library for cookie management -->
<script src="<%=path %>/js/jquery.cookie.js"></script>
<!-- calender plugin -->
<script src='<%=path %>/bower_components/moment/min/moment.min.js'></script>
<script src='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<script src='<%=path %>/js/jquery.dataTables.min.js'></script>

<!-- select or dropdown enhancer -->
<script src="<%=path %>/bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="<%=path %>/bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<script src="<%=path %>/js/jquery.noty.js"></script>
<!-- library for making tables responsive -->
<script src="<%=path %>/bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="<%=path %>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<script src="<%=path %>/js/jquery.raty.min.js"></script>
<!-- for iOS style toggle switch -->
<script src="<%=path %>/js/jquery.iphone.toggle.js"></script>
<!-- autogrowing textarea plugin -->
<script src="<%=path %>/js/jquery.autogrow-textarea.js"></script>
<!-- multiple file upload plugin -->
<script src="<%=path %>/js/jquery.uploadify-3.1.min.js"></script>
<!-- history.js for cross-browser state change on ajax -->
<script src="<%=path %>/js/jquery.history.js"></script>
<!-- application script for Charisma demo -->
<script src="<%=path %>/js/charisma.js"></script>
<script src="<%=path %>/js/login.js"></script>

</body>
</html>
