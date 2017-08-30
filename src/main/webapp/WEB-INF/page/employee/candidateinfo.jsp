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
<title>CandidateList</title>
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
									<i class="glyphicon glyphicon-user">&nbsp;CANDIDATE&nbsp;LIST</i> 
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
							    <form id="candidateForm" method="post" class="form-horizontal">
									<div class="group">
										<label class="col-sm-2 control-label">NAME</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateName" id="candidateName" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">SOURCE</label>
										<div class="col-lg-4">
											<select class="form-control" name="source" data-bv-notempty
												data-bv-notempty-message="请选择来源" id="source" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">51job</option>
												<option value="1">智联招聘</option>
												<option value="2">Boss直聘</option>
												<option value="3">中华英才网</option>
												<option value="4">其他</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">TEL</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="candidateTel" id="candidateTel" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">CANDIDATE STATUS</label>
										<div class="col-lg-4">
											<select class="form-control" name="candidateStatus" data-bv-notempty
												data-bv-notempty-message="请选择状态" id="candidateStatus" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">招聘中</option>
												<option value="1">offer中</option>
												<option value="2">已入职</option>
												<option value="3">闲置中</option>
												<option value="4">暂不关注</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">EMAIL</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="email" id="email" />
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">EDUCATION</label>
										<div class="col-lg-4">
											<select class="form-control" name="education" data-bv-notempty
												data-bv-notempty-message="请选择学历" id="education" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">博士</option>
												<option value="1">研究生</option>
												<option value="2">本科</option>
												<option value="3">大专</option>
												<option value="4">高中</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">WORK YEARS</label>
										<div class="col-lg-4">
											<select class="form-control" name="experienceYears" data-bv-notempty
												data-bv-notempty-message="请选择工作年限" id="experienceYears" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">2年以下</option>
												<option value="1">3~5年</option>
												<option value="2">6~10年</option>
												<option value="3">11年以上</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">MAJOR TYPE</label>
										<div class="col-lg-4">
											<select class="form-control" name="majorStatus" data-bv-notempty
												data-bv-notempty-message="请选择专业" id="majorStatus" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">计算机相关专业</option>
												<option value="1">非计算机相关专业</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">SKILL</label>
										<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="请选择技能" id="skill" data-bv-group=".group">
												<option value="">-- ALL--</option>
											</select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">ENGLIST LEVEL</label>
										<div class="col-lg-4">
											<select class="form-control" name="englishLevel" data-bv-notempty
												data-bv-notempty-message="请选择英语级别" id="englishLevel" data-bv-group=".group">
												<option value="">-- ALL--</option>
												<option value="0">简单使用</option>
												<option value="1">流利沟通</option>
											</select>
										</div>
									</div>
									<br/><br/><br/>
									<div class="group">
										<label class="col-sm-2 control-label">LOCK HR</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="userName" id="userName" />
										</div>
									</div>
									<div class="form-group">
									    <div style="text-align:center;width:25%;float:right;">
									    	<input type="button" value="查&nbsp;&nbsp;询"
												name="searchBtn" id="searchBtn" 
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div>
									    <div style="text-align:center;width:25%;float:right;">
									    	<input type="reset" value="导出Excel" disabled="disabled"
												name="exportExcel" id="exportExcel"
												class="button btn btn-primary" data-dismiss="modal"
												style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto;cursor:pointer;">
									    </div>
									</div>
								        
							        <div >
								    	<a href="" id="exceltHref" style="display:none;">导出</a>
								    </div>
								    
									<div style="overflow: auto;">
									<table id="candidateList" class="table table-striped table-bordered">
										<thead>
											<tr>
												<th>NAME</th>
												<th>SEX</th>
												<th>AGE</th>
												<th>TEL</th>
												<th>EMAIL</th>
												<th>SOURCE</th>
												<th>STATUS</th>
												<th>EDUCATION</th>
												<th>WORK YEARS</th>
												<th>MAJOR TYPE</th>
												<th>ENGLISH LEVEL</th>
												<th>SKILL</th>
												<th>LOCK HR</th>
												<th>OPERATION</th>
											</tr>
										</thead>
										<tbody></tbody>
									</table>
									</div>
									<div>
										<div style="max-width:400px;float:left">
											<ul class="pagination pagination-centered">
												<li><a href="#" id="fristPage" onclick="loadCandidateList('frist')">首页</a></li>
												<li><a href="#" id="previousPage" onclick="loadCandidateList('previous')">上一页</a></li>
												<li><a href="#" id="nextPage" onclick="loadCandidateList('next')">下一页</a></li>
												<li><a href="#" id="lastPage" onclick="loadCandidateList('last')">末页</a></li>
											</ul>
										</div>
										<div style="max-width:400px;float:right;margin-top:30px;">
										每页<span id="pageDataCount"></span>条&nbsp;&nbsp;第<span id="currentPage"></span>页&nbsp;/&nbsp;共<span id="pageCount"></span>页&nbsp;&nbsp;共<span id="dataCount"></span>条
										</div>
									</div>
								</form>
								<form action="" id="editForm" method="post" target="_blank">
									<input id="employeeId" name="employeeId" type="hidden" />
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
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
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
									<label><input type="checkbox" checked="checked" name="candidateName"/>Name&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="candidateSex"/>Sex&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="candidateAge"/>Age&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="candidateTel"/>Tel&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="email"/>Email&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="source"/>Source&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="candidateStatus"/>Status&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="education">Education&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="experienceYears"/>Work Years&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="majorStatus"/>Major Type&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="englishLevel"/>English Level&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="skill"/>Skill&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="college"/>College&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="graduate"/>Graduate&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="lockPerson"/>Lock HR&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="createDate"/>Create Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="updateDate"/>Update Date&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="Role"/>Role&nbsp;&nbsp;</label>
									<label><input type="checkbox" checked="checked" name="entyDate"/>ENTY Date&nbsp;&nbsp;</label>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
						<div class="center">
							<a class="btn btn-success" href="#" onClick="exportCondition()"> <i
								class="glyphicon glyphicon-ok icon-white" ></i> 确定
							</a> <a class="btn btn-info" href="#" data-dismiss="modal"> <i
								class="glyphicon glyphicon-remove icon-white"></i> 取消
							</a>
						</div>
					</div>
				</div>
			</div>
			<!--/span-->
		</div>

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

	<script type="text/javascript" src="<%=path %>/js/pmo/candidateInfo.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


