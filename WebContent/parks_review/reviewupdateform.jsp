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
		action="${pageContext.request.contextPath }/reviewupdate">
		<div id="red"
			style="width: 500px; height: 500px; border: 2px solid red">
			제목:<input type="text" value=${vo.title } id="title" name="title" ><br>
			<textarea rows="20" cols="50" id="context" name="context"
					 >${vo.context }</textarea>
			<br> <input type="file" value="사진올리기" id="image"
				onchange="setimg(event);" name="image"><br> <input
				type="submit" value="저장">
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