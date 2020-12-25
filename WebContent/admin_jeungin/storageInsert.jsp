<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin_jeungin/storageInsert.jsp</title>
<script type ="text/javascript">
	
</script>
</head>
<body>
	<form method ="post" enctype="multipart/form-data" action="<%= request.getContextPath()%>/storInsert.do">
		<div>
			<label>분류</label>
			<select>
				
			</select>
		
			<label>상품 이름</label>
			<input type="text" name ="itemname">
			<label></label>
		</div>
	</form>
</body>
</html>