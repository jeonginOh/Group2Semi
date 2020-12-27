<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#askbox{width:850px; height:1000px;  padding-left: 150px; padding-top: 100px;  margin-top:50px; border: 2px solid black}

</style>
</head>
<body>
<script type="text/javascript">
function cktitle(){
	var title=document.getElementById("title");
	var context=document.getElementById("context");
	if(title.value.trim()==""){
		alert("제목을 입력해주세요");
		return false;
	}else if(context.value.trim()==""){
		alert("내용을 입력해주세요");
		return false;
		
	}
	

}
</script>
<div id="askbox">
<a href="${pageContext.request.contextPath }/ask_list">목록으로...</a>
	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/ask_insert"  onsubmit="return cktitle()">
		
		<select name="askcat" id="askcat" onchange="jinc1()">
		<option value="item" <c:if test="${askcat=='item'}">selected</c:if> onclick=bics()>상품문의</option>
		<option value="basong" <c:if test="${askcat=='basong'}">selected</c:if>>배송문의</option>
		<option value="hwanbull" <c:if test="${askcat=='hwanbull'}">selected</c:if>>환불문의</option>
		<option value="etc" <c:if test="${askcat=='etc'}">selected</c:if>>기타문의</option>
		</select>
		
		<input type="text" id="title" name="title" style="width:400px ">
		<br>
		<input type="file" value="image" id="image"
				onchange="setimg(event);"  name="image" accept=".jpg, .png, .gif"><br> <input
				type="submit" value="저장" >
		<br>
		<textarea rows="20" cols="65" id="context" name="context" style="width: 600px"></textarea>
		<br>
		<input type="submit" value="등록"><br>
			<label>사진미리보기</label>
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
		var imgeset = document.getElementById("askbox");
		img.setAttribute("src", event.target.result);
		imgeset.removeChild(imgeset.lastChild);
		alert("사진은 한장만 추가 가능");
		imgeset.appendChild(img);
		img.style.width = "200px";
		
	};
	reader.readAsDataURL(event.target.files[0]);
}
var n=0;
function jinc(){
	var context=document.getElementById("context");

	if(n==0){
		context.style.fontWeight="bold";
		n++;
	}else{
		context.style.fontWeight="normal";
		n--;
	}
}
function jinc1(){
	var f = document.getElementById("askcat");
	var field = f.options[f.selectedIndex].value;
	var title=document.getElementById("title");
	if(field=="12"){
		title.style.fontSize="12px";
	}else if(field=="50"){
		title.style.fontSize="50px";
	}
}


</script>
</body>
</html>