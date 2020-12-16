<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:choose>
	<c:when test="${code=='success' }">
	<h1>작성완료.....리뷰페이지로 돌아갑니다.</h1>
	<meta http-equiv="refresh" content="1; url=/Group2Semi/reviewlist2.do?itemid=${itemid }">
	</c:when>
	<c:otherwise>
	<h1>작성실패.....리뷰페이지로 돌아갑니다.</h1>
		<meta http-equiv="refresh" content="1; url=/Group2Semi/reviewlist2.do?itemid=${itemid }">
	</c:otherwise>
</c:choose>
</body>
</html>