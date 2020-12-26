<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
session.setMaxInactiveInterval(60*60);
  session.setAttribute("memid", "2");
	String memid1=(String)session.getAttribute("memid");
%>
<a href="${pageContext.request.contextPath }/ask_list">문의게시판</a>
<a href="${pageContext.request.contextPath }/ask_list">문의게시판</a>
</body>
</html>