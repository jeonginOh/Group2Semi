<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.askbox{height: 200px; width: 850px;}


</style>
</head>

<body>
<c:set var="cp" value="${pageContext.request.contextPath }"/>
<c:forEach var="list" items="${list }">
<div class="askbox" align="center" style="display: inline-block;" >
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/ask_detail"  onsubmit="return cktitle()">
 		<input type="hidden" id="askid" name="askid" value="${list.askid }" readonly="readonly"> 
		<select name="askcat">
		<option value="item" <c:if test="${askcat=='item'}">selected</c:if>>상품문의</option>
		<option value="basong" <c:if test="${askcat=='basong'}">selected</c:if>>배송문의</option>
		<option value="hwanbull" <c:if test="${askcat=='hwanbull'}">selected</c:if>>환불문의</option>
		<option value="etc" <c:if test="${askcat=='etc'}">selected</c:if>>기타문의</option>
		</select>
	<%-- <a>${username }님 환영합니다.</a><br>  --%>
		
<%-- 		<input type="text" id="title" name="title" value=${list.title } style="width:400px " readonly="readonly">
 --%>		<br>
				 
		
		<textarea rows="20" cols="65" id="context" name="context" readonly="readonly">${list.ansdate}에 작성된 답변입니다..&#10;&#13;&#10;
${list.context }</textarea><br>
		<input type="file" value="image" id="image" onchange="setimg(event);"  name="image" accept=".jpg, .png, .gif" readonly="readonly">
		
	
		<a href="${pageContext.request.contextPath }/ans_delete?ansid=${list.ansid }" style="padding-left: 15px">삭제하기</a>
					<a href="${pageContext.request.contextPath }/ask_list">목록으로..</a><br>
		<br>
		<label>등록되어있는 사진</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<br>
		<div id="red">	
		<br>
		
		<c:choose>
		<c:when test="${list.image==null }">
		<br>
		<br>
		<br>
		<label>현재 등록된사진이 없습니다.</label>
		</c:when>
		<c:when test="${list.image!=null }">

		<img src="fileFolder/${list.image }" style="width:0x;" id="img1" name="img1" >
		<input type="hidden" value="${list.image }" id="image1" name="image1" >
			
		
		</c:when>
		</c:choose>
		</div>
		</form>

		</div>
		</c:forEach>

</body>
</html>