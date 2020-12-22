<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_jeungin/storageinfo.jsp</title>
</head>
<body>
	<c:set var="cp" value="${pageContext.request.contextPath }"/>
	<form>
	<div>
		<span>구분</span>
		<select name = 'bigCate'>
			<option value = '전체'>전체</option>
			<option value = '채소'>채소</option>
			<option value = '과일'>과일</option>
			<option value = '수산'>수산</option>
			<option value = '정육'>정육</option>
			<option value = '완제품'>완제품</option>
			<option value = '음료'>음료</option>
		</select>
		<select name = 'smallCate'>
		<c:choose>
			<c:when test="${ document.bigCate=='전체' }">
				<option value="콩나물/버섯류">콩나물/버섯류</option>
				<option value="시금치/부추/나물">시금치/부추/나물</option>
				<option value="양파/마늘/생강/파">양파/마늘/생강/파</option>
				<option value="기본 채소">기본 채소</option>
				<option value="간편 채소">간편 채소</option>
			</c:when>
		</c:choose>
		</select>
	</div>
	</form>
</body>
</html>