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
			제목:<input type="text" value=${vo.title } id="title" name="title" required="required" placeholder="${vo.title }"><br>
			<textarea rows="20" cols="50" id="context" name="context"
					placeholder="${vo.context }" ></textarea>
			<br> <input type="file" value="사진올리기" id="image"
				onchange="setimg(event);" name="image" accept=".jpg, .png, .gif" ><br> <input
				type="submit" value="저장"><br>
				<label >현재 등록된 사진</label> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<label>변경될사진</label><div id="red1">
				<br>
				<c:choose>
						<c:when test="${ vo.image==null }">
						등록된 사진이 없습니다.
					
						</c:when>
						<c:when test="${vo.image!=null }">
						<img src="fileFolder/${vo.image }" style="width: 200px;"
						onclick="imgup(event); imgout(event);" id="img1" name="img1">
						<input type="hidden" value="${vo.image }" id="image1" name="image1">
						</c:when>
						</c:choose>
					</div>	
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
	<script type="text/javascript">
	function setimg(event) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var img = document.createElement("img");

			var imgeset = document.getElementById("red1");
			img.setAttribute("src", event.target.result);
			imgset.innerHTML="";

			imgeset.appendChild(img);
			img.style.width = "200px";
			img.style.height = "150px";
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	function setimg(event) {
		var reader = new FileReader();
		reader.onload = function(event) {
			var img = document.createElement("img");
			var imgeset = document.getElementById("red1");
			img.setAttribute("src", event.target.result);
			imgeset.removeChild(imgeset.lastChild);
			alert("사진은 한장만 추가 가능");
			imgeset.appendChild(img);
			img.style.width = "200px";
			img.style.height = "150px";
			
		};
		reader.readAsDataURL(event.target.files[0]);
	}
	
	</script>
	
	
</body>
</html>