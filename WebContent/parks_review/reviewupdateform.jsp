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

	<form name="form1" method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/reviewupdate">
		<div id="red"
			style="width: 500px; height: 500px;">
			제목:<input type="text" value=${vo.title } id="title" name="title" required="required"><br>
			<textarea rows="20" cols="50" id="context" name="context"
					 >${vo.context }</textarea>
			<br> <input type="file" value="사진올리기" id="image"
				onchange="setimg(event);" name="image" accept=".jpg, .png, .gif" required="required"><br> <input
				type="submit" value="저장"><br>
				<label >현재 등록된 사진</label>
				<br>
				<c:choose>
						<c:when test="${ vo.image==null }">
						등록된 사진이 없습니다.
						</c:when>
						<c:when test="${vo.image!=null }">
						<img src="fileFolder/${vo.image }" style="width: 200px;"
						onclick="imgup(event); imgout(event);" id="img1" name="img1">
						</c:when>	
						</c:choose>			
				<input type="hidden" name="revid" value=${vo.revid }>
				<input type="hidden" name="itemid" value=${vo.itemid }>
			<div id="image_set"></div>
					<input type="radio" name="star" id="star" value=1>
		<label  >★</label>
		<input type="radio" name="star" id="star" value=2>
		<label  >★★</label>
		<input type="radio" name="star" id="star" value=3>
		<label  >★★★</label>
		<input type="radio" name="star" id="star" value=4>
		<label >★★★★</label>
		<input type="radio" name="star" id="star" value=5 checked="checked">
		<label  >★★★★★</label>
		</div>
	</form>
</body>
</html>