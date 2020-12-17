<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>

<div>
<form method="post" action="${cp }/search_list.do">
	<select name="field">
		<option value="itemname" <c:if test="${field==' itemname' }">selected</c:if>>상품명</option>
		<option value="factory" <c:if test="${field==' factory' }">selected</c:if>>제조사</option>
		<option value="origin" <c:if test="${field==' origin' }">selected</c:if>>원산지</option>
		</select>>
<input type="text" id="search" name="search" value="">
<input type="submit" value="검색">
</form>
</div>
</body>
</html>