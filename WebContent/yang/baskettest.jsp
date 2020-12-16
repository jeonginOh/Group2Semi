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
<br>
<input type="button" value="장바구니담기" onclick="insertBasket()">
<script type="text/javascript">
	function insertDibs(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				if(json.code=="success"){
					alert("찜 성공!");
				}else if(json.code=="overlap"){
					alert("이미 찜 목록에 있는 상품입니다");
				}else if(json.code=="fail"){
					alert("오류로 인해 실패");
				}
			}
		}
		xhr.open('get','../basketinsert.do?itemid=231&bd=d',true);
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
	function insertBasket(){
		xhr=new XMLHttpRequest();
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4 && xhr.status==200){
				var json=JSON.parse(xhr.responseText);
				if(json.code=="success"){
					alert("장바구니 담기 성공!");
				}else if(json.code=="overlap"){
					alert("이미 장바구니 목록에 있는 상품입니다");
				}else if(json.code=="fail"){
					alert("오류로 인해 실패");
				}
			}
		}
		xhr.open('get','../basketinsert.do?itemid=15&bd=b',true);
		xhr.send();
	}
</script>
</body>
</html>