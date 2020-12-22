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
<div id="askbox" align="center">
<h1>환영합니다</h1>
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/ask_detail"  onsubmit="return cktitle()">
		<input type="hidden" id="askid" name="askid" value="${list.askid }">
		<select name="askcat">
		<option value="item" <c:if test="${askcat=='item'}">selected</c:if>>상품문의</option>
		<option value="basong" <c:if test="${askcat=='basong'}">selected</c:if>>배송문의</option>
		<option value="hwanbull" <c:if test="${askcat=='hwanbull'}">selected</c:if>>환불문의</option>
		<option value="etc" <c:if test="${askcat=='etc'}">selected</c:if>>기타문의</option>
		</select>
		
		<input type="text" id="title" name="title" value=${list.title } style="width:400px ">
		<br>
				 
		
		<textarea rows="20" cols="65" id="context" name="context" >${list.context }</textarea><br>
		<input type="file" value="image" id="image" onchange="setimg(event);"  name="image" accept=".jpg, .png, .gif">
		<br>
		<input type="submit" value="수정하기"><br>
		<label>현재 등록된 사진</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label>변경될 사진</label><br>
		<div id="red">	
		<br>
		
		<c:choose>
		<c:when test="${list.image==null }">
		<br>
		<br>
		<br>
		<label>등록된사진이 없습니다.</label>
		</c:when>
		<c:when test="${list.image!=null }">

		<img src="fileFolder/${list.image }" style="width:200px;" id="img1" name="img1">
		<input type="hidden" value="${list.image }" id="image1" name="image1">
			
		
		</c:when>
		</c:choose>
		</div>
		</form>

		</div>
		<script type="text/javascript">
		function setimg(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");

				var imgeset = document.getElementById("askbox");
				img.setAttribute("src", event.target.result);
				imgset.innerHTML="";

				imgeset.appendChild(img);
				img.style.width = "200px";
				
			};
			reader.readAsDataURL(event.target.files[0]);
		}
		function setimg(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");
				var imgeset = document.getElementById("red");
				img.setAttribute("src", event.target.result);
				imgeset.removeChild(imgeset.lastChild);
				alert("사진은 한장만 추가 가능");
				imgeset.appendChild(img);
				img.style.width = "200px";
				
			};
			reader.readAsDataURL(event.target.files[0]);
		}

	
	</script>
</body>
</html>