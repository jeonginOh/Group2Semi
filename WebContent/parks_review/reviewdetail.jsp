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
	<h1>상세보기</h1>
	<table border="1" width="500">
		<tr>
			<td>제목</td>
			<td>${vo.title }</td>
		</tr>
		<tr>
			<td>작성일</td>
			<td>${vo.revdate }</td>
		</tr>
		<tr>
			<td>작성자</td>
			<td>${username }</td>
		<tr>
			<td>이미지</td>
			<td><c:choose>
						<c:when test="${ vo.image==null }">
						등록된 사진이 없습니다.
						</c:when>
						<c:when test="${vo.image!=null }">
						<img src="fileFolder/${vo.image }" style="width: 200px;"
						onclick="imgup(event); imgout(event);" id="img1">
						</c:when>	
						</c:choose></td>
		</tr>
		<tr>
			<td>물품명</td>
			<td>${itemname }</td>
		</tr>
		<tr>	
			<td>현재별점</td>
			<td><c:choose>
					<c:when test="${vo.star>4.6 }">
						<br>
		평점 : ${ vo.star} <BR>
		★★★★★
		<br>
					</c:when>
					<c:when test="${vo.star>3.6 }">
						<br>
 	평점 : ${ vo.star}  
		<br>
		★★★★
		<br>

					</c:when>
					<c:when test="${vo.star>2.6 }">
						<br>
		평점 : ${ vo.star}  
		<br>
		★★★
		<br>

					</c:when>
					<c:when test="${vo.star>1.6 }">
						<br>
		평점 : ${ vo.star}  
		<br>
		★★
		<br>

					</c:when>
					<c:when test="${vo.star>0.6 }">
						<br>
		평점 : ${ vo.star}  
		<br>
		★
		<br>
					</c:when>

				</c:choose></td>
		<tr>
			<td>글내용</td>
			<td><textarea rows="5" cols="50" readonly="readonly">${vo.context }</textarea></td>
		</tr>

	</table>
	<a
		href="${pageContext.request.contextPath }/reviewdelete?revid=${vo.revid }&itemid=${vo.itemid}">삭제</a>
	<a
		href="${pageContext.request.contextPath }/reviewupdate?revid=${vo.revid }&itemid=${vo.itemid}">수정</a>
</body>
</html>