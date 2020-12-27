<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>메인페이지</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.css"/>
<script type="text/javascript" src="https://cdn.jsdelivr.net/npm/slick-carousel@1.8.1/slick/slick.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/css/MainCss.css">
<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
</head>
<%
	String spage=request.getParameter("spage");
	String rpage=request.getParameter("rpage");
	if(spage==null){
		spage="totalcontent.jsp";
	}
	if(session.getAttribute("memid")==null){
		rpage="/yang/recentShop.jsp";
	}else{
		rpage="/yang/mainbdList.jsp";
	}
	
%>
<body>
	<div id = "wrap">
		<div id = header class ="main">
			<jsp:include page="header.jsp"/>
		</div>
		<div id = "leftcontent" class ="main">
			<jsp:include page="categoryBanner.jsp"/>
		</div>
		<div id = "totalcontent" class ="main">
			<jsp:include page="<%=spage %>"/>
		</div>
		<c:choose>
		<c:when test="${param.spage!='/yang/basketListpage.jsp' }">
		<div id = "rightcontent" class ="main">
			<jsp:include page="<%=rpage %>"/>
		</div>
		</c:when>
		<c:otherwise>
		<div id = "rightcontent" class ="main">
			<jsp:include page="/jeungIn/rightcontent.jsp"/>
		</div>
		</c:otherwise>
		</c:choose>
		<div id = "footer" class ="main">
			<jsp:include page="footer.jsp"/>
		</div>
	</div>
</body>
</html>