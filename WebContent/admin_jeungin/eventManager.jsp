<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id = "topEventDiv">
		<form action ="<%=request.getContextPath() %>/eventManage">
			
			<label></label><input type = "text" name = "menu">
			<label></label><input type = "text" name = "word">
			
			<input type = "submit">
		</form>
	</div>
</body>
</html>