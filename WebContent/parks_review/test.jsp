<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="wrap">
	<div id="header">
<%@include file="reviewdetail.jsp" %>
	
	</div>
<div id="main">



<%@include file="reviewlist.jsp" %>
</div>
<div id="footer">
<h1>하이여</h1>
</div>



</div>
<%
     session.invalidate();
%>


</body>
</html>