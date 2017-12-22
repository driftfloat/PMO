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
        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">
                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Functional Module</li>
                        <c:forEach var="userAuthority" items="${list}" >	
                           	<c:if test="${userAuthority.menuParentId==null}">			   
						    <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;${userAuthority.menuName}</span></a>
                            <ul class="nav nav-pills nav-stacked">                              
                                 <c:forEach var="userAuthority0" items="${list}" >
                                  <c:if test="${userAuthority0.menuParentId==userAuthority.menuId}">
									    <li><a class="ajax-link" href="<%=path %>${userAuthority0.menuUrl}"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;${userAuthority0.menuName}</span></a></li>
								  </c:if>
                                 </c:forEach>
                            </ul>
                           </li> 
                           </c:if>
						</c:forEach>
						
						
                      <%--   <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;员工管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                              <li><a class="ajax-link" href="<%=path %>/service/employee/index.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;员工注册</span></a></li>
                              <li><a class="ajax-link" href="<%=path %>/service/employee/employeeInfo.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;员工信息维护</span></a></li>
                            </ul>
                        </li> 
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;招聘管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                              <li><a class="ajax-link" href="<%=path %>/service/resume/input.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;候选人录入</span></a></li>
                              <li><a class="ajax-link" href="<%=path %>/service/candidate/getCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;候选人列表</span></a></li>
  						      <li><a class="ajax-link" href="<%=path %>/service/candidate/getMyCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;我的候选人</span></a></li>
  						      <li><a class="ajax-link" href="<%=path %>/service/candidate/getMyWaitEntryCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;待入职候选人</span></a></li>
                            </ul>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;需求管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                               <li><a class="ajax-link" href="<%=path %>/service/demand/recruitdemand.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;招聘需求</span></a></li>
                               <li><a class="ajax-link" href="<%=path %>/service/demand/demandInfo.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;招聘需求查询</span></a></li>
                               <li><a class="ajax-link" href="<%=path %>/service/stayin/stayin.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;RM待入职员工查看</span></a>
                               </li>
                            </ul>
                        </li>
                        
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;面试管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a class="ajax-link" href="<%=path %>/service/interviewer/rm.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;面试官列表</span></a>
                                </li>

	                            <li><a class="ajax-link" href="<%=path %>/service/candidate/interviewFeedBackInfo.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;面试官反馈</span></a></li>
		                        
		                        <li><a class="ajax-link" href="<%=path %>/service/interview/interviewRecordInfo.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;面试记录</span></a></li>
		                        
		                        <li><a class="ajax-link" href="<%=path %>/service/rmCandidate/myCandidate.html"><i class="glyphicon glyphicon-home"></i><span>&nbsp;&nbsp;&nbsp;&nbsp;已推送的候选人</span></a></li>
                            </ul>
                        </li> --%>
                    </ul>
                </div>
            </div>
        </div>
</body>
</html>


