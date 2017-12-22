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
<meta charset="utf-8">
<title>Pmo</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">
<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css'	rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'	rel='stylesheet'>
<link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
<link href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css' rel='stylesheet'>
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'>
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'>
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'>
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'>
<link href='<%=path %>/css/uploadify.css' rel='stylesheet'>
<link href='<%=path %>/css/animate.min.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'>
<!-- jQuery -->
<script src="<%=path %>/bower_components/jquery/jquery.min.js"></script>
<!-- The fav icon -->
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico">
<style>
.table th, .table td { 
text-align: center;
vertical-align: middle!important;
}
.bootstrap-dialog-message{
	overflow: auto;
}
.modal-lg {
    width: 1070px!important;
}
.bootstrap-dialog-message {
    overflow: auto;
    height: 400px;
}
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>

	<!-- topbar starts -->
	<c:import url="/service/manage/top" />
	<!-- topbar ends -->
	<div class="ch-container">
		<div class="row">
			<!-- left menu starts -->
			<c:import url="/service/manage/left" />
			<!-- left menu ends -->
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row" >					
				<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Onboard
								</h2>
							</div>
							<div id="addDemand" class="box-content">
							    <form id="stayinForm" method="post" class="form-horizontal">
									<div class="group">
										<label class="col-sm-2 control-label">Candidate Name</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateName" id="candidateName" />
										</div>
									</div>
									
									<div class="group">
										<label class="col-sm-2 control-label">Tel</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateTel" id="tel" />
										</div>
									</div>
									<br/><br/><br/>
									
									
									<div class="group">
										<label class="col-sm-2 control-label">Email</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="email" id="email" />
										</div>
									</div>
									<div class="group">
										<label class="col-sm-2 control-label">Skill</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="请选择技能" id="skill" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
									</div>
		                            <br/><br/><br/>
							       <div class="group">
										<label class="col-sm-2 control-label">Role</label>
										<div class="col-lg-4">
											<select class="form-control" name="role" data-bv-notempty
												data-bv-notempty-message="请选择技能" id="role" data-bv-group=".group">
												<option value="">-- Option--</option>
											</select>
										</div>
									</div>
									<div class="form-group">
									    <div style="text-align:center;width:50%;float:left;">
									    	<input type="button" value="Search"
												name="searchStayinBtn" id="searchStayinBtn" 
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div>
									   
									   <!--  <div style="text-align:center;width:50%;float:left;">
									    	<input type="reset" value="导出Excel" disabled="disabled"
												name="exportCandidateExcel" id="exportCandidateExcel"
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div> -->
									</div>
								    
								    <br/><br/>
									<div style="overflow: auto;">
									<table id="candidateList"
										class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>Candidate Name</th>
												<th>Sex</th>
												<th>Age</th>
												<th>Tel</th>
												<th>Email</th>
												<th>Skill</th>
												<th>Role</th>
												<th>College</th>
												<th>Major Status</th>
												<th>English Level</th>
												<th>Education</th>
												<th>Work Years</th>
												<!-- <th>DEMANDID</th>
 -->												<th>Operate</th>
											</tr>
										</thead>
										
										<tbody>
									    </tbody>
									</table>
									
									<div>
										<div style="max-width:400px;float:left">
											<ul class="pagination pagination-centered">
												<li><a href="#" id="fristPage" >First</a></li>
												<li><a href="#" id="previousPage" >Last</a></li>
												<li><a href="#" id="nextPage" >Next</a></li>
												<li><a href="#" id="lastPage" >End</a></li>
											</ul>
										</div>
										<div style="max-width:400px;float:right;margin-top:30px;">
										<b><span id="currentPage"></span> / <span id="pageCount"></span></b>
										</div>
									</div>
									</div>
								</form>
								<form action="" id="editForm" method="post" target="_blank">
									<input id="candidateId" name="candidateId" type="hidden" />
								</form>
							</div>
						</div>
					</div>
				</div>
				<!--/row-->
				<!-- content ends -->
			</div>
			<!--/#content.col-md-0-->
		</div>
		<hr>
	<!-- 	<div class="modal fade" id="myStayinListModal" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="box-header well" data-original-title="">
						<h2>
							<i class="glyphicon glyphicon-user"></i> 勾选导出项
						</h2>
						<div class="box-icon">
							<a href="#" class="btn btn-round btn-default  btn-minimize "><i
								class="glyphicon glyphicon-chevron-up"></i></a> <a
								class="btn btn-round btn-default" href="#" data-dismiss="modal">
								<i class="glyphicon glyphicon-remove"></i>
							</a>
						</div>
					</div>
					<div id="excelCheckBox" class="box-content">
						<table id="excelBox"
							class="table table-striped table-bordered">
							<thead>
								<tr>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_NAME" value="Name"/>Name&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_SEX" value="Sex"/>Sex&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_AGE" value="Age"/>Age&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="TEL" value="Tel"/>Tel&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EMAIL" value="Email"/>Email&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="SOURCE" value="Source"/>Source&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CANDIDATE_STATUS" value="Status"/>Status&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EDUCATION" value="Education"/>Education&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EXPERIENCE_YEARS" value="Work Years"/>Work Years&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="MAJOR_STATUS" value="Major Type"/>Major Type&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ENGLISH_LEVEL" value="English Level"/>English Level&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="SKILL" value="Skill"/>Skill&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="COLLEGE" value="College"/>College&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="GRADUATE_DATE" value="Graduate Date"/>Graduate Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="b.USER_NAME as lockHR" value="Lock HR"/>Lock HR&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="CREATE_DATE" value="Create Date"/>Create Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="UPDATE_DATE" value="Update Date"/>Update Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="EXPECTED_SALARY" value="Old Company"/>Expected Salary&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="REAL_SALARY" value="Old Company"/>Real Salary&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ROLE" value="Role"/>Role&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="OLD_COMPANY" value="Old Company"/>Old Company&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ENTY_DATE" value="Enty Date"/>Enty Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="ARRIVAL_DATE" value="Arrival Date"/>Arrival Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="c.USER_NAME as createUser" value="Create User"/>Create User&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="REMARK" value="Remark"/>Remark&nbsp;&nbsp;</label>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="exportCondition()"> 
								<i class="glyphicon glyphicon-ok icon-white" ></i> 确定
							</a> 
							<a class="btn btn-info" href="#" data-dismiss="modal"> 
								<i class="glyphicon glyphicon-remove icon-white"></i> 取消
							</a>
						</div>
					</div>
				</div>
			</div>
			/span
		</div> -->

        <div style="display: none" id="table_area" ></div>
		<c:import url="/service/manage/footer" />
	</div>
	<!--/.fluid-container-->

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

	<script type="text/javascript" src="<%=path %>/js/pmo/stayinEmployee.js"></script>
	<!-- 郭开峰引入的关于对待入职的员工onborad的js -->
	<script type="text/javascript" src="<%=path %>/js/pmo/employeeOnborad.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
    <script type="text/javascript" src="<%=path %>/js/bootstrap-dialog.js"></script>
</body>
</html>


