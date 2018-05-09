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
<meta charset="utf-8"/>
<title>PMO</title>
<link rel="shortcut icon" href="<%=path %>/img/favicon.ico"/>
<!-- The styles -->
<link href="<%=path %>/css/bootstrap-cerulean.min.css" rel="stylesheet"/>
<link href="<%=path %>/css/charisma-app.css" rel="stylesheet"/>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'/>
<link href='<%=path %>/bower_components/chosen/chosen.min.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'/>
<link href='<%=path %>/bower_components/bootstrap-val/bootstrapValidator.css' rel='stylesheet'/>
<link href='<%=path %>/css/jquery.noty.css' rel='stylesheet'/>
<link href='<%=path %>/css/noty_theme_default.css' rel='stylesheet'/>
<link href='<%=path %>/css/elfinder.min.css' rel='stylesheet'/>
<link href='<%=path %>/css/elfinder.theme.css' rel='stylesheet'/>
<link href='<%=path %>/css/jquery.iphone.toggle.css' rel='stylesheet'/>
<link href='<%=path %>/css/uploadify.css' rel='stylesheet'/>
<link href='<%=path %>/css/animate.min.css' rel='stylesheet'/>
<link href='<%=path%>/css/bootstrap-datetimepicker.css' rel='stylesheet'/>
<link href='<%=path%>/css/bootstrap-datetimepicker.min.css' rel='stylesheet'/>
<style type="text/css">
   .ssf{
      font-size:15px;
      font-weight:bold;
      color:red;
   }
</style>
</head>
<script>
var path='<%=path%>';
</script>
<body>
	<c:import url="/service/manage/top" />
	<div class="ch-container">
		<div class="row">
			<c:import url="/service/manage/left" />
			<div id="content" class="col-lg-10 col-sm-10">
				<!-- content starts -->
				<div class="row" >					
				<div class="box col-md-12">
						<div class="box-inner">
							<div class="box-header well" data-original-title="">
								<h2>
									<i class="glyphicon glyphicon-user"></i> Information Maintenance
								</h2>
							</div>
							<div id="employeeInfo" class="box-content">
							<div class="panel panel-default">
							  <div class="panel-heading">Query Conditions</div>
                              <div class="panel-body">
                                 <div class="group">
										<label class="col-lg-2 control-label">ProjectNumber</label>
										<div class="col-lg-4">
											<input type="text" class="form-control" id="projectNumber"/>
										</div>
										</div>
										<div class="group">
										<label class="col-lg-2 control-label">DU</label>
										<div class="col-lg-4">
											<select class="form-control" name="csSubDept" data-bv-notempty
												data-bv-notempty-message="请选择角色" id="csSubDept" data-bv-group=".group">
												<!-- RM -->
												<c:if test="${userType == '5'}">
												<c:forEach items="${csdeptList}" varStatus="i" var="item" >
												<option value="${item.csSubDeptId}">${item.csSubDeptName}</option>
												</c:forEach>
												</c:if>
												<!-- 交付部经理或者助理,职能部门经理，职能部门助理,HRBP经理,HRBP助理 -->
												<c:if test="${userType == '3' || userType == '4' || userType == '11' || userType == '12' || userType == '13' || userType == '14'}">
												<option value="">--option--</option>
												<c:forEach items="${csdeptList}" varStatus="i" var="item" >
												<option value="${item.csSubDeptId}">${item.csSubDeptName}</option>
												</c:forEach>
												</c:if>
												<!-- 业务线或者管理员 -->
												<c:if test="${userType == '15' || userType == '0'}">
												<option value="">--option--</option>
												<c:forEach items="${csdeptList}" varStatus="i" var="item" >
												<option value="${item.csSubDeptId}">${item.csSubDeptName}</option>
												</c:forEach>
												</c:if>
											</select>
										</div>
										</div>
										</br></br></br>
										<div class="group">
											<label class="col-sm-2 control-label">ProjectName</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="projectName"/>
											</div>
										</div>
										<div class="group">
											<label class="col-sm-2 control-label">StaffName</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="staffName"/>
											</div>
										</div>
										</br></br></br>
										
										<div class="group">
											<label class="col-sm-2 control-label">E-HR</label>
											<div class="col-sm-4">
												<input type="text" class="form-control" id="ehr"/>
											</div>
										</div>
										<div class="group">
											<div class="col-sm-4">
												<button onClick="search()" type="button" style="margin-left:50px" id="btn_query" class="btn btn-primary">Search</button>
											</div>
										</div>
                               </div>
                             </div>
                             <!-- 工具栏  -->
                             <div id="toolbar" class="btn-group">
                               <button id="btn_add" type="button" onclick="exportData()" class="btn btn-default">
                               <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>Export
                               </button>
                             </div>
                             <!-- 数据列汇总 -->
                             <table id="columnSummary" border="1" width="100%">
                               <tr align="center">
                                 <td colspan="8" align="center">列数据汇总</td>
                               </tr>
                               <tr>
                                 <td>实际工时</td>
                                 <td><div id="s1" class="ssf"></div></td>
                                 <td>无效工时</td>
                                 <td><div id="s2" class="ssf"></div></td>
                                 <td>加班工时</td>
                                 <td><div id="s3" class="ssf"></div></td>
                                 <td>调休工时</td>
                                 <td><div id="s4" class="ssf"></div></td>
                               </tr>
                               <tr>
                                 <td>调整上月工时</td>
                                 <td><div id="s5" class="ssf"></div></td>
                                 <td>实际工时收入</td>
                                 <td><div id="s6" class="ssf"></div></td>
                                 <td>无效工时收入</td>
                                 <td><div id="s7" class="ssf"></div></td>
                                 <td>加班工时收入</td>
                                 <td><div id="s8" class="ssf"></div></td>
                               </tr>
                               <tr>
                                 <td>调休工时收入</td>
                                 <td><div id="s9" class="ssf"></div></td>
                                 <td>调整上月工时收入</td>
                                 <td><div id="s10" class="ssf"></div></td>
                                 <td>差旅收入</td>
                                 <td><div id="s11" class="ssf"></div></td>
                                 <td>付费设备收入</td>
                                 <td><div id="s12" class="ssf"></div></td>
                               </tr>
                               <tr>
                                 <td>分包收入</td>
                                 <td><div id="s13" class="ssf"></div></td>
                                 <td>月收入合计-原币种</td>
                                 <td><div id="s14" class="ssf"></div></td>
                                 <td>月收入合计-人民币</td>
                                 <td><div id="s15" class="ssf"></div></td>
                                 <td>当月有效收入</td>
                                 <td><div id="s16" class="ssf"></div></td>
                               </tr>
                               <tr>
                                 <td>有效NR</td>
                                 <td><div id="s17" class="ssf"></div></td>
                                 <td>当月有效人力</td>
                                 <td><div id="s18" class="ssf"></div></td>
                                 <td>当月无效人力</td>
                                 <td colspan="5"><div id="s19" class="ssf"></div></td>
                               </tr>
                             </table>
                             <!-- 数据列表  -->
                             <table id="OfflineOperList"></table>
                            </div>        
						</div>
				</div>
				</div>
			</div>
		</div>
	</div>
	<c:import url="/service/manage/footer" />
	
	<!-- CSS引用 -->
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.css" type="text/css" />
    <link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table.min.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/extensionjs/bootstrap3-editable/css/bootstrap-editable.css" type="text/css" />
	<link rel="stylesheet" href="<%=path %>/extensioncss/bootstarp-table/bootstrap-table-fixed-columns.css" type="text/css" />      
  
    <!-- JS引用 -->
    <script src="<%=path %>/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-locale-all.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/bootstrap-table-fixed-columns.js" type="text/javascript"></script>
	<script src="<%=path %>/extensionjs/bootstrap-table/dist/locale/bootstrap-table-zh-CN.js"></script>
    <script src="<%=path %>/extensionjs/bootstrap3-editable/js/bootstrap-editable.js" type="text/javascript"></script>
    <script src="<%=path %>/extensionjs/bootstrap-table/dist/extensions/editable/bootstrap-table-editable.js" type="text/javascript"></script>
    
   
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
	
    <script type="text/javascript" src="<%=path %>/js/pmo/offlineOpe.js"></script>

</body>
</html>


