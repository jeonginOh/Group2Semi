<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MainCss.css">
<title>관리자 메인 페이지</title>
</head>
<%
	String spage=request.getParameter("spage");
	if(spage==null){
		spage="totalcontent.jsp";
	}
	
%>
<body>
	<div id = "wrap">
		<div id = header class ="main">
			<jsp:include page="adminHeader.jsp"/>
		</div>
		<div id = "leftcontent" class ="main">
			<jsp:include page="adminBanner.jsp"/>
		</div>
		<div id = "totalcontent" class ="main">
			<jsp:include page="<%=spage %>"/>
		</div>
		<div id = "footer" class ="main">
			<jsp:include page="adminFooter.jsp"/>
		</div>
	</div>
</body>
</html>