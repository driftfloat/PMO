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
<title>PMO</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description"
	content="Charisma, a fully featured, responsive, HTML5, Bootstrap admin template.">
<meta name="author" content="Muhammad Usman">

<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet">

<link href="<%=path %>/css/charisma-app.css" rel="stylesheet">
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css'
	rel='stylesheet' media='print'>
<link href='<%=path %>/bower_components/chosen/chosen.min.css'
	rel='stylesheet'>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/responsive-tables/responsive-tables.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css'
	rel='stylesheet'>
<link
	href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css'
	rel='stylesheet'>
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

<!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
    <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

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
									<i class="glyphicon glyphicon-user"></i> 招聘需求信息
								</h2>
							</div>
							<div id="register" class="box-content" style="overflow: auto;">
							<form id="recruitdemandForm" method="post">
							    <div class="form-group">
									    <input type="hidden" name="demandId" id="demandId" value="${demandId}"/>
								</div>
									
							    <br/>
								<div class="form-group">
								<div id="successAlert" class="alert alert-success" style="display: none;"></div>
										<div class="group">
											<label class="col-sm-2 control-label">RR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="rr"
													id="rr" />
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">Job Code</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="jobCode"
													id="jobCode" />
											</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
											<label class="col-lg-2 control-label">SKILL</label>
											<div class="col-lg-4">
											<select class="form-control" name="skill" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="skill" data-bv-group=".group">
												<option value="">-- 请选择--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">REQUESTOR</label>
										<div class="col-lg-4">
											
												<input type="text" class="form-control" name="requestor"
													id="requestor" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">POSITION</label>
										<div class="col-lg-4">
											<select class="form-control" name="position" data-bv-notempty
												data-bv-notempty-message="请选择职位" id="position" data-bv-group=".group">
												<option value="">-- 请选择--</option>								
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">HSBC SubDeptId</label>
										<div class="col-sm-4">
											<input type="text" class="form-control" name="hsbcSubDeptId"
													id="hsbcSubDeptId" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">LOCATION</label>
										<div class="col-lg-4">
											<select class="form-control" name="location" data-bv-notempty
												data-bv-notempty-message="请选择" id="location" data-bv-group=".group">
												<option value="">-- 请选择--</option>								
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">ReqPublishedDate</label>
										<div class="col-md-4">
											<div class="input-group date form_datetime col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" disabled="disabled"
													id="reqPublishedDate1" name="reqPublishedDate1"> 
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-th">
														</span>
													</span> 
													<input type="hidden" id="reqPublishedDate2" name="reqPublishedDate2" />
											</div>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-sm-2 control-label">STATUS</label>
										<div class="col-lg-4">
											<select class="form-control" name="status" data-bv-notempty
												data-bv-notempty-message="请选择状态" id="status" data-bv-group=".group">
												<option value="">-- 请选择--</option>
												
									        </select>
										</div>
									    </div>
									    <div class="group">
										<label class="col-lg-2 control-label">Staff Name</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" name="staffName"
													id="staffName" />
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Proposed Joining Date</label>
										<div class="col-lg-4">
											<div class="input-group date form_datetime col-sm-12"
												data-link-field="dt_set_order_time_input">
												<input class="form-control" type="text" disabled="disabled"
													id="proposedJoiningDate1" name="proposedJoiningDate1"> 
													<span class="input-group-addon">
														<span class="glyphicon glyphicon-th">
														</span>
													</span> 
													<input type="hidden" id="proposedJoiningDate2" name="proposedJoiningDate2" />
											</div>
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">DC Cleared</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="dcCleared"
													id="dcCleared" />
										</div>
									</div>
								</div>
								<br/><br/>
								<div class="form-group">
									<div class="group">
										<label class="col-sm-2 control-label">Sow Signed</label>
										<div class="col-md-4">
											<select class="form-control" name="sowSigned" data-bv-notempty
												data-bv-notempty-message="请选择" id="sowSigned" data-bv-group=".group">
												<option value="">-- 请选择--</option>
												
									        </select>
										</div>
									</div>
									<div class="group">
										<label class="col-lg-2 control-label">Onboarded</label>
										<div class="col-lg-4">
											<select class="form-control" name="onboarded" data-bv-notempty
												data-bv-notempty-message="请选择" id="onboarded" data-bv-group=".group">
												<option value="">-- 请选择--</option>
												
									        </select>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Abort</label>
										<div class="col-lg-4">
											<select class="form-control" name="abort" data-bv-notempty
												data-bv-notempty-message="请选择" id="abort" data-bv-group=".group">
												<option value="">-- 请选择--</option>
											</select>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">Delayed</label>
										<div class="col-lg-4">
											<select class="form-control" name="delayed" data-bv-notempty
												data-bv-notempty-message="请选择" id="delayed" data-bv-group=".group">
												<option value="">-- 请选择--</option>
											</select>
										</div>
										</div>
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">Reason</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" name="reason"
													id="reason" />
										</div>
										</div>
										<div class="group">
										<label class="col-sm-2 control-label">Remark</label>
										<div class="col-md-4">
											<input type="text" class="form-control" name="remark"
													id="remark" />
										</div>
									    </div>
								</div>
								<br/><br/>
								
								<div class="form-group">
								        <div class="group">
											<label class="col-sm-2 control-label">CS SubDept</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" name="csSubDept"
													id="csSubDept" />
											</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">DO Number</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" name="doNumber"
													id="doNumber" />
										</div>
										</div>
										
								</div>
								<br/><br/>
								<div class="form-group">
										<div class="group">
										<label class="col-lg-2 control-label">hrPriority</label>
										<div class="col-lg-4">
											<select class="form-control" name="hrPriority" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="hrPriority" data-bv-group=".group">
												<option value="">-- 请选择--</option>
											</select>
										</div>
										</div>
								</div>
												
								<br/><br/><br/>
								
								<div class="form-group">
									    <div style="text-align:center;width:50%;float:left">
									    <input type="button" value="添&nbsp;&nbsp;加"
										name="subscribe" id="sub_search" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										onclick="addDemand()"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
									    <div style="text-align:center;width:50%;float:right">
									    <input type="reset" value="重&nbsp;&nbsp;置"
										name="subscribe" id="sub_add" href="#"
										class="button btn btn-primary" data-dismiss="modal"
										style="background-color: #D5D5D5; border: 0 none; border-radius: 4px; color: #FFFFFF; cursor: pointer; display: inline-block; font-size: 15px; font-weight: bold; height: 32px; line-height: 32px; margin: 0 5px 10px 0; padding: 0; text-align: center; text-decoration: none; vertical-align: top; white-space: nowrap; width: 100px; margin:auto ;">
									    </div>
								</div>
								<br/><br/>
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

		<c:import url="/service/manage/footer" />

	</div>
	<!--/.fluid-container-->

	<!-- external javascript -->

	<script
		src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
	<script
		src="<%=path %>/bower_components/bootstrap-val/bootstrapValidator.min.js"></script>

	<!-- library for cookie management -->
	<script src="<%=path %>/js/jquery.cookie.js"></script>
	<!-- calender plugin -->
	<script src='<%=path %>/bower_components/moment/min/moment.min.js'></script>
	<script
		src='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
	<!-- data table plugin -->
	<script src='<%=path %>/js/jquery.dataTables.min.js'></script>

	<!-- select or dropdown enhancer -->
	<script src="<%=path %>/bower_components/chosen/chosen.jquery.min.js"></script>
	<!-- plugin for gallery image view -->
	<script
		src="<%=path %>/bower_components/colorbox/jquery.colorbox-min.js"></script>
	<!-- notification plugin -->
	<script src="<%=path %>/js/jquery.noty.js"></script>
	<!-- library for making tables responsive -->
	<script
		src="<%=path %>/bower_components/responsive-tables/responsive-tables.js"></script>
	<!-- tour plugin -->
	<script
		src="<%=path %>/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
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

	<script type="text/javascript" src="<%=path %>/js/pmo/recruitdemand.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/bootstrap-datetimepicker.zh-CN.js"></script>

</body>
</html>


