<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%  
    // 权限验证  
    if(session.getAttribute("userName")==null){  
        response.sendRedirect("welcome.jsp");  
        return;  
    }  
%>
<script type="text/javascript">
 window.location.href="<%=path%>/service/manage/loginPage"; 
</script>
