<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
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
<h1>리뷰작성 <small>item review</small></h1>
	<form method="post" enctype="multipart/form-data" 
		action="${pageContext.request.contextPath }/reviewinsert.do" onsubmit="return cktitle()"  class="form-horizontal">
			<input type="hidden" id="itemid" name="itemid" value="${itemid }"><br>
		
			  <div class="form-group">

    <div class="col-sm-10" id='red'>
    <input type="text"  id="title" name="title"  required="required" onclick="if(this.value==this.defaultValue){this.value=''}" 
		onblur="if (this.value == '') { this.value = this.defaultValue;"  placeholder="제목을입력하세요" class='form-control input-lg'>
		<textarea rows="10"  style="resize: none;"
			 id="context" name="context" onclick="if(this.value==this.defaultValue){this.value=''}" 
		onblur="if (this.value == '') { this.value = this.defaultValue;" placeholder="내용을 입력하세요" class="form-control"></textarea>
		 <input type="file" value="image" id="image"
				onchange="setimg(event);"  name="image" accept=".jpg, .png, .gif" class="btn btn-danger"><input
				type="submit" value="저장" class="btn btn-primary btn-lg" ><br>
				
		
    </div>
  </div>

		
			
		
		
	
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
<br><a href="${pageContext.request.contextPath }/jeungIn/main.jsp?spage=/jeungIn/itemdetail.jsp?itemid=${itemid }">리뷰목록으로....</a>
	</form>
 	<script type="text/javascript">
/*  			function insertReview() {

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
		}  */

		function setimg(event) {
			var reader = new FileReader();
			reader.onload = function(event) {
				var img = document.createElement("img");

				var imgeset = document.getElementById("red");
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