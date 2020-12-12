<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>yang/baskettest.jsp</title>
</head>
<body>
<input type="button" value="찜하기" onclick="insertDibs()">
<input type="button" value="찜삭제" onclick="deleteDibs()">
<script type="text/javascript">
	function insertDibs(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				if(json.code=="success"){
					alert("찜 성공!");
				}
			}
		}
		xhr.open('get','../basketinsert.do?itemid=231',true);
		xhr.send();
	}
	function deleteDibs(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				if(json.code=="success"){
					alert("찜삭제 성공!");
				}
			}
		}
		xhr.open('get','../basketdelete.do?itemid=11',true);
		xhr.send();
	}
</script>
</body>
</html>