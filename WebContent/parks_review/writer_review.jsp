<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript">
function cktitle(){
	var title=document.getElementById("title");
	if(title.value.trim()==""){
		alert("제목을 입력해주세요");
		return false;
	}
	
}

</script>

	<form method="post" enctype="multipart/form-data"
		action="${pageContext.request.contextPath }/reviewinsert.do" onsubmit="return cktitle()" style="margin:0 auto; width: 40%; margin-top: 100px" >
			<input type="hidden" id="itemid" name="itemid" value="${itemid }">
		<div id="red"
			style="width: 500px; height: 500px; border: 2px solid red" align="center">
			제목:<input type="text"  id="title" name="title"  required="required" onclick="if(this.value==this.defaultValue){this.value=''}" 
		onblur="if (this.value == '') { this.value = this.defaultValue;" value="제목을입력하세요 " >
<br>
			<textarea rows="20" cols="50" id="context" name="context" onclick="if(this.value==this.defaultValue){this.value=''}" 
		onblur="if (this.value == '') { this.value = this.defaultValue;">내용을 입력하세요 ${itemid }</textarea>
			<br> <input type="file" value="image" id="image"
				onchange="setimg(event);"  name="image" accept=".jpg, .png, .gif"><br> <input
				type="submit" value="저장" ><br>
				
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
		<br>
		<a href="${pageContext.request.contextPath }/reviewlist2.do?itemid=${itemid}">리뷰목록으로....</a>
			
		</div>
	


	</form>
 	<script type="text/javascript">
 	<!--		function insertReview() {

			var xhr = new XMLHttpRequest();
			xhr.onreadystatechange = function() {
				if (xhr.readyState == 4 && xhr.status == 200) {
					var json = JSON.parse(xhr.responseText);
					if (json.code == 'success') {
						location.href = "review.html";
					} else {
						alert("실패");
					}
					function setimg(event) {
						var reader = new FileReader();
						reader.onload = function(event) {
							var img = document.createElement("img");
							var imgeset = document.getElementById("red");
							img.setAttribute("src", event.target.result);
							imgeset.appendChild(img);
							img.style.width = "100px";
							img.style.height = "100px";

						};

						reader.readAsDataURL(event.target.files[0]);

					}
				}

			};
			xhr.open('post', '../upload1.do?itemid=1', true);
			xhr.setRequestHeader('Content-Type',
					'application/x-www-form-urlencoded');
			var title = document.getElementById("title").value;
			var context = document.getElementById("context").value;
			var image = document.getElementById("image").value;
			var param = "title=" + title + "&context=" + context
					+ "&itemid=1&memid=1";
			xhr.send(param);
		} 
-->
		function setimg(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");

				var imgeset = document.getElementById("red");
				img.setAttribute("src", event.target.result);
				imgset.innerHTML="";

				imgeset.appendChild(img);
				img.style.width = "100px";
				img.style.height = "100px";
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
				img.style.width = "100px";
				img.style.height = "100px";
			};
			reader.readAsDataURL(event.target.files[0]);
		}
	</script>
</body>
</html>