<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	String path = request.getContextPath();
	String candidateId = request.getParameter("candidateId");
	request.getSession().setAttribute("candidateId",candidateId);
%>
<html>
<head>
    <title>简历在线阅览</title>
</head>
<body>
<iframe src="<c:url value="/pdf/web/viewer.html" />?file=<%=path %>/service/display/displayPDF.html"
        width="100%" height="800">
</iframe>
</body>
</html>