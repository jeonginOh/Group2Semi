<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function eventlistsearch(){
		var xhr = null;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 &&xhr.status==200){
				var arr = json.parse(xhr.responseText);

			}
		}
	}
</script>
</head>
<body>
	<div id = "topEventDiv">
		<form action ="<%=request.getContextPath() %>/eventManage">
			
			<label>검색조건</label>
			<select name = "menu">
				<option value="avail">상태</option>
				<option value="catname">카테고리</option>
				<option value="itemname">상품명</option>
				<option value="factory">제조사</option>
				<option value="origin">원산지</option>
			</select>
			<label>검색어</label><input type = "text" name = "word">
				
			<input type = "submit" value = "검색">
		</form>
	</div>
	<div id = "list">
		
	</div>
</body>
</html>