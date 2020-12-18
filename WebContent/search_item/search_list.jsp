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

<div id="box">
<script type="text/javascript"></script>
	<c:if test="${startPageNum>10 }">
	<a href="${cp }/search_list.do?pageNum=${startPageNum-1}&field=${ field }&search=${ search }">[이전]</a>
	</c:if>
	<c:forEach var="i" begin="${startPageNum }" end="${endPageNum }">
		<a href="${cp }/search_list.do?pageNum=${i}&field=${field}&search=${search}">[${i }]</a>
	</c:forEach>
	<c:if test="${endPageNum<pageCount }">
		<a href="${cp }/search_list.do?pageNum=${endPageNum+1}&field=${field}&search=${search}">[다음]</a>
	</c:if>

</div>



</body>
</html>