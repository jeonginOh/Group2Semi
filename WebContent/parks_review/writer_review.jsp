<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form method="post" enctype="multipart/form-data"
		action="../reviewinsert.do?itemid=1&memid=1">
		<div id="red"
			style="width: 500px; height: 500px; border: 2px solid red">
			제목:<input type="text" value="test" id="title" name="title"><br>
			<textarea rows="20" cols="50" id="context" name="context">내용을 입력하세요</textarea>
			<br> <input type="file" value="사진올리기" id="image"
				onchange="setimg(event);" name="image"><br> <input
				type="submit" value="저장">
			<div id="image_set"></div>
		</div>
	</form>
	<script type="text/javascript">
		function insertReview() {

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
	</script>


</body>
</html>